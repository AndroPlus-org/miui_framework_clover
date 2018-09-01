// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;

// Referenced classes of package android.telephony:
//            TelephonyManager, VisualVoicemailSmsFilterSettings, VisualVoicemailSms

public abstract class VisualVoicemailService extends Service
{
    public static class VisualVoicemailTask
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof VisualVoicemailTask))
                return false;
            if(mTaskId == ((VisualVoicemailTask)obj).mTaskId)
                flag = true;
            return flag;
        }

        public final void finish()
        {
            Message message = Message.obtain();
            message.what = 4;
            message.arg1 = mTaskId;
            mReplyTo.send(message);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("VvmService", "Cannot send MSG_TASK_ENDED, remote handler no longer exist");
              goto _L1
        }

        public int hashCode()
        {
            return mTaskId;
        }

        private final Messenger mReplyTo;
        private final int mTaskId;

        private VisualVoicemailTask(Messenger messenger, int i)
        {
            mTaskId = i;
            mReplyTo = messenger;
        }

        VisualVoicemailTask(Messenger messenger, int i, VisualVoicemailTask visualvoicemailtask)
        {
            this(messenger, i);
        }
    }


    public VisualVoicemailService()
    {
    }

    private static int getSubId(Context context, PhoneAccountHandle phoneaccounthandle)
    {
        return ((TelephonyManager)context.getSystemService(android/telephony/TelephonyManager)).getSubIdForPhoneAccount(((TelecomManager)context.getSystemService(android/telecom/TelecomManager)).getPhoneAccount(phoneaccounthandle));
    }

    public static final void sendVisualVoicemailSms(Context context, PhoneAccountHandle phoneaccounthandle, String s, short word0, String s1, PendingIntent pendingintent)
    {
        ((TelephonyManager)context.getSystemService(android/telephony/TelephonyManager)).sendVisualVoicemailSmsForSubscriber(getSubId(context, phoneaccounthandle), s, word0, s1, pendingintent);
    }

    public static final void setSmsFilterSettings(Context context, PhoneAccountHandle phoneaccounthandle, VisualVoicemailSmsFilterSettings visualvoicemailsmsfiltersettings)
    {
        TelephonyManager telephonymanager = (TelephonyManager)context.getSystemService(android/telephony/TelephonyManager);
        int i = getSubId(context, phoneaccounthandle);
        if(visualvoicemailsmsfiltersettings == null)
            telephonymanager.disableVisualVoicemailSmsFilter(i);
        else
            telephonymanager.enableVisualVoicemailSmsFilter(i, visualvoicemailsmsfiltersettings);
    }

    public IBinder onBind(Intent intent)
    {
        return mMessenger.getBinder();
    }

    public abstract void onCellServiceConnected(VisualVoicemailTask visualvoicemailtask, PhoneAccountHandle phoneaccounthandle);

    public abstract void onSimRemoved(VisualVoicemailTask visualvoicemailtask, PhoneAccountHandle phoneaccounthandle);

    public abstract void onSmsReceived(VisualVoicemailTask visualvoicemailtask, VisualVoicemailSms visualvoicemailsms);

    public abstract void onStopped(VisualVoicemailTask visualvoicemailtask);

    public static final String DATA_PHONE_ACCOUNT_HANDLE = "data_phone_account_handle";
    public static final String DATA_SMS = "data_sms";
    public static final int MSG_ON_CELL_SERVICE_CONNECTED = 1;
    public static final int MSG_ON_SIM_REMOVED = 3;
    public static final int MSG_ON_SMS_RECEIVED = 2;
    public static final int MSG_TASK_ENDED = 4;
    public static final int MSG_TASK_STOPPED = 5;
    public static final String SERVICE_INTERFACE = "android.telephony.VisualVoicemailService";
    private static final String TAG = "VvmService";
    private final Messenger mMessenger = new Messenger(new Handler() {

        public void handleMessage(Message message)
        {
            PhoneAccountHandle phoneaccounthandle;
            VisualVoicemailTask visualvoicemailtask;
            phoneaccounthandle = (PhoneAccountHandle)message.getData().getParcelable("data_phone_account_handle");
            visualvoicemailtask = new VisualVoicemailTask(message.replyTo, message.arg1, null);
            message.what;
            JVM INSTR tableswitch 1 5: default 68
        //                       1 74
        //                       2 86
        //                       3 111
        //                       4 68
        //                       5 123;
               goto _L1 _L2 _L3 _L4 _L1 _L5
_L1:
            super.handleMessage(message);
_L7:
            return;
_L2:
            onCellServiceConnected(visualvoicemailtask, phoneaccounthandle);
            continue; /* Loop/switch isn't completed */
_L3:
            message = (VisualVoicemailSms)message.getData().getParcelable("data_sms");
            onSmsReceived(visualvoicemailtask, message);
            continue; /* Loop/switch isn't completed */
_L4:
            onSimRemoved(visualvoicemailtask, phoneaccounthandle);
            continue; /* Loop/switch isn't completed */
_L5:
            onStopped(visualvoicemailtask);
            if(true) goto _L7; else goto _L6
_L6:
        }

        final VisualVoicemailService this$0;

            
            {
                this$0 = VisualVoicemailService.this;
                super();
            }
    }
);
}
