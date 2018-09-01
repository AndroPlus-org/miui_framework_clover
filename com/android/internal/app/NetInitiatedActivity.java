// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.*;
import android.location.LocationManager;
import android.os.*;
import android.util.Log;
import android.widget.Toast;

// Referenced classes of package com.android.internal.app:
//            AlertActivity

public class NetInitiatedActivity extends AlertActivity
    implements android.content.DialogInterface.OnClickListener
{

    static int _2D_get0(NetInitiatedActivity netinitiatedactivity)
    {
        return netinitiatedactivity.default_response;
    }

    static int _2D_get1(NetInitiatedActivity netinitiatedactivity)
    {
        return netinitiatedactivity.notificationId;
    }

    static void _2D_wrap0(NetInitiatedActivity netinitiatedactivity, Intent intent)
    {
        netinitiatedactivity.handleNIVerify(intent);
    }

    static void _2D_wrap1(NetInitiatedActivity netinitiatedactivity, int i)
    {
        netinitiatedactivity.sendUserResponse(i);
    }

    public NetInitiatedActivity()
    {
        notificationId = -1;
        timeout = -1;
        default_response = -1;
        default_response_timeout = 6;
        mNetInitiatedReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                Log.d("NetInitiatedActivity", (new StringBuilder()).append("NetInitiatedReceiver onReceive: ").append(intent.getAction()).toString());
                if(intent.getAction() == "android.intent.action.NETWORK_INITIATED_VERIFY")
                    NetInitiatedActivity._2D_wrap0(NetInitiatedActivity.this, intent);
            }

            final NetInitiatedActivity this$0;

            
            {
                this$0 = NetInitiatedActivity.this;
                super();
            }
        }
;
    }

    private void handleNIVerify(Intent intent)
    {
        notificationId = intent.getIntExtra("notif_id", -1);
        Log.d("NetInitiatedActivity", (new StringBuilder()).append("handleNIVerify action: ").append(intent.getAction()).toString());
    }

    private void sendUserResponse(int i)
    {
        Log.d("NetInitiatedActivity", (new StringBuilder()).append("sendUserResponse, response: ").append(i).toString());
        ((LocationManager)getSystemService("location")).sendNiResponse(notificationId, i);
    }

    private void showNIError()
    {
        Toast.makeText(this, "NI error", 1).show();
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if(i == -1)
            sendUserResponse(1);
        if(i == -2)
            sendUserResponse(2);
        finish();
        notificationId = -1;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Intent intent = getIntent();
        bundle = mAlertParams;
        Context context = getApplicationContext();
        bundle.mTitle = intent.getStringExtra("title");
        bundle.mMessage = intent.getStringExtra("message");
        bundle.mPositiveButtonText = String.format(context.getString(0x104025a), new Object[0]);
        bundle.mPositiveButtonListener = this;
        bundle.mNegativeButtonText = String.format(context.getString(0x1040259), new Object[0]);
        bundle.mNegativeButtonListener = this;
        notificationId = intent.getIntExtra("notif_id", -1);
        timeout = intent.getIntExtra("timeout", default_response_timeout);
        default_response = intent.getIntExtra("default_resp", 1);
        Log.d("NetInitiatedActivity", (new StringBuilder()).append("onCreate() : notificationId: ").append(notificationId).append(" timeout: ").append(timeout).append(" default_response:").append(default_response).toString());
        mHandler.sendMessageDelayed(mHandler.obtainMessage(1), timeout * 1000);
        setupAlert();
    }

    protected void onPause()
    {
        super.onPause();
        Log.d("NetInitiatedActivity", "onPause");
        unregisterReceiver(mNetInitiatedReceiver);
    }

    protected void onResume()
    {
        super.onResume();
        Log.d("NetInitiatedActivity", "onResume");
        registerReceiver(mNetInitiatedReceiver, new IntentFilter("android.intent.action.NETWORK_INITIATED_VERIFY"));
    }

    private static final boolean DEBUG = true;
    private static final int GPS_NO_RESPONSE_TIME_OUT = 1;
    private static final int NEGATIVE_BUTTON = -2;
    private static final int POSITIVE_BUTTON = -1;
    private static final String TAG = "NetInitiatedActivity";
    private static final boolean VERBOSE = false;
    private int default_response;
    private int default_response_timeout;
    private final Handler mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 25;
               goto _L1 _L2
_L1:
            return;
_L2:
            if(NetInitiatedActivity._2D_get1(NetInitiatedActivity.this) != -1)
                NetInitiatedActivity._2D_wrap1(NetInitiatedActivity.this, NetInitiatedActivity._2D_get0(NetInitiatedActivity.this));
            finish();
            if(true) goto _L1; else goto _L3
_L3:
        }

        final NetInitiatedActivity this$0;

            
            {
                this$0 = NetInitiatedActivity.this;
                super();
            }
    }
;
    private BroadcastReceiver mNetInitiatedReceiver;
    private int notificationId;
    private int timeout;
}
