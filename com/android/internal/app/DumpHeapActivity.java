// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.util.DebugUtils;
import android.util.Slog;

public class DumpHeapActivity extends Activity
{

    public DumpHeapActivity()
    {
        mHandled = false;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mProcess = getIntent().getStringExtra("process");
        mSize = getIntent().getLongExtra("size", 0L);
        bundle = getIntent().getStringExtra("direct_launch");
        if(bundle != null)
        {
            Intent intent = new Intent("android.app.action.REPORT_HEAP_LIMIT");
            intent.setPackage(bundle);
            ClipData clipdata = ClipData.newUri(getContentResolver(), "Heap Dump", JAVA_URI);
            intent.setClipData(clipdata);
            intent.addFlags(1);
            intent.setType(clipdata.getDescription().getMimeType(0));
            intent.putExtra("android.intent.extra.STREAM", JAVA_URI);
            try
            {
                startActivity(intent);
                scheduleDelete();
                mHandled = true;
                finish();
                return;
            }
            catch(ActivityNotFoundException activitynotfoundexception)
            {
                Slog.i("DumpHeapActivity", (new StringBuilder()).append("Unable to direct launch to ").append(bundle).append(": ").append(activitynotfoundexception.getMessage()).toString());
            }
        }
        bundle = new android.app.AlertDialog.Builder(this, 0x103023a);
        bundle.setTitle(0x10401be);
        bundle.setMessage(getString(0x10401bd, new Object[] {
            mProcess, DebugUtils.sizeValueToString(mSize, null)
        }));
        bundle.setNegativeButton(0x1040000, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                mHandled = true;
                sendBroadcast(new Intent("com.android.server.am.DELETE_DUMPHEAP"));
                finish();
            }

            final DumpHeapActivity this$0;

            
            {
                this$0 = DumpHeapActivity.this;
                super();
            }
        }
);
        bundle.setPositiveButton(0x104000a, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                mHandled = true;
                scheduleDelete();
                dialoginterface = new Intent("android.intent.action.SEND");
                ClipData clipdata1 = ClipData.newUri(getContentResolver(), "Heap Dump", DumpHeapActivity.JAVA_URI);
                dialoginterface.setClipData(clipdata1);
                dialoginterface.addFlags(1);
                dialoginterface.setType(clipdata1.getDescription().getMimeType(0));
                dialoginterface.putExtra("android.intent.extra.STREAM", DumpHeapActivity.JAVA_URI);
                startActivity(Intent.createChooser(dialoginterface, getText(0x10401be)));
                finish();
            }

            final DumpHeapActivity this$0;

            
            {
                this$0 = DumpHeapActivity.this;
                super();
            }
        }
);
        mDialog = bundle.show();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        mDialog.dismiss();
    }

    protected void onStop()
    {
        super.onStop();
        if(!isChangingConfigurations() && !mHandled)
            sendBroadcast(new Intent("com.android.server.am.DELETE_DUMPHEAP"));
    }

    void scheduleDelete()
    {
        Intent intent = new Intent("com.android.server.am.DELETE_DUMPHEAP");
        intent.putExtra("delay_delete", true);
        sendBroadcast(intent);
    }

    public static final String ACTION_DELETE_DUMPHEAP = "com.android.server.am.DELETE_DUMPHEAP";
    public static final String EXTRA_DELAY_DELETE = "delay_delete";
    public static final Uri JAVA_URI = Uri.parse("content://com.android.server.heapdump/java");
    public static final String KEY_DIRECT_LAUNCH = "direct_launch";
    public static final String KEY_PROCESS = "process";
    public static final String KEY_SIZE = "size";
    AlertDialog mDialog;
    boolean mHandled;
    String mProcess;
    long mSize;

}
