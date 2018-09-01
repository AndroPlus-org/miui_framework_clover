// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Activity;
import android.content.*;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class UnlaunchableAppActivity extends Activity
    implements android.content.DialogInterface.OnDismissListener, android.content.DialogInterface.OnClickListener
{

    public UnlaunchableAppActivity()
    {
    }

    private static final Intent createBaseIntent()
    {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("android", com/android/internal/app/UnlaunchableAppActivity.getName()));
        intent.setFlags(0x10800000);
        return intent;
    }

    public static Intent createInQuietModeDialogIntent(int i)
    {
        Intent intent = createBaseIntent();
        intent.putExtra("unlaunchable_reason", 1);
        intent.putExtra("android.intent.extra.user_handle", i);
        return intent;
    }

    public static Intent createInQuietModeDialogIntent(int i, IntentSender intentsender)
    {
        Intent intent = createInQuietModeDialogIntent(i);
        intent.putExtra("android.intent.extra.INTENT", intentsender);
        return intent;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if(mReason != 1 || i != -1 || !UserManager.get(this).trySetQuietModeDisabled(mUserId, mTarget) || mTarget == null)
            break MISSING_BLOCK_LABEL_51;
        startIntentSenderForResult(mTarget, -1, null, 0, 0, 0);
_L2:
        return;
        dialoginterface;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        bundle = getIntent();
        mReason = bundle.getIntExtra("unlaunchable_reason", -1);
        mUserId = bundle.getIntExtra("android.intent.extra.user_handle", -10000);
        mTarget = (IntentSender)bundle.getParcelableExtra("android.intent.extra.INTENT");
        if(mUserId == -10000)
        {
            Log.wtf("UnlaunchableAppActivity", (new StringBuilder()).append("Invalid user id: ").append(mUserId).append(". Stopping.").toString());
            finish();
            return;
        }
        if(mReason == 1)
        {
            String s = getResources().getString(0x10406d8);
            String s1 = getResources().getString(0x10406d7);
            bundle = LayoutInflater.from(this).inflate(0x1090119, null);
            TextView textview = (TextView)bundle.findViewById(0x1020491);
            TextView textview1 = (TextView)bundle.findViewById(0x1020490);
            textview.setText(s);
            textview1.setText(s1);
            bundle = (new android.app.AlertDialog.Builder(this)).setView(bundle).setOnDismissListener(this);
            if(mReason == 1)
                bundle.setPositiveButton(0x10406d9, this).setNegativeButton(0x1040000, null);
            else
                bundle.setPositiveButton(0x104000a, null);
            bundle.show();
            return;
        } else
        {
            Log.wtf("UnlaunchableAppActivity", (new StringBuilder()).append("Invalid unlaunchable type: ").append(mReason).toString());
            finish();
            return;
        }
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        finish();
    }

    private static final String EXTRA_UNLAUNCHABLE_REASON = "unlaunchable_reason";
    private static final String TAG = "UnlaunchableAppActivity";
    private static final int UNLAUNCHABLE_REASON_QUIET_MODE = 1;
    private int mReason;
    private IntentSender mTarget;
    private int mUserId;
}
