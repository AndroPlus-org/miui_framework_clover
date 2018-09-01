// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.cardemulation;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;

public abstract class HostApduService extends Service
{
    final class MsgHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 3: default 36
        //                       0 42
        //                       1 191
        //                       2 247
        //                       3 269;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            super.handleMessage(message);
_L7:
            return;
_L2:
            Bundle bundle = message.getData();
            if(bundle == null)
                return;
            if(mNfcService == null)
                mNfcService = message.replyTo;
            message = bundle.getByteArray("data");
            if(message != null)
            {
                byte abyte0[] = processCommandApdu(message, null);
                if(abyte0 != null)
                {
                    if(mNfcService == null)
                    {
                        Log.e("ApduService", "Response not sent; service was deactivated.");
                        return;
                    }
                    Message message1 = Message.obtain(null, 1);
                    message = new Bundle();
                    message.putByteArray("data", abyte0);
                    message1.setData(message);
                    message1.replyTo = mMessenger;
                    try
                    {
                        mNfcService.send(message1);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Message message)
                    {
                        Log.e("TAG", "Response not sent; RemoteException calling into NfcService.");
                    }
                }
            } else
            {
                Log.e("ApduService", "Received MSG_COMMAND_APDU without data.");
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if(mNfcService == null)
            {
                Log.e("ApduService", "Response not sent; service was deactivated.");
                return;
            }
            try
            {
                message.replyTo = mMessenger;
                mNfcService.send(message);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("ApduService", "RemoteException calling into NfcService.");
            }
            continue; /* Loop/switch isn't completed */
_L4:
            mNfcService = null;
            onDeactivated(message.arg1);
            continue; /* Loop/switch isn't completed */
_L5:
            if(mNfcService == null)
            {
                Log.e("ApduService", "notifyUnhandled not sent; service was deactivated.");
                return;
            }
            try
            {
                message.replyTo = mMessenger;
                mNfcService.send(message);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("ApduService", "RemoteException calling into NfcService.");
            }
            if(true) goto _L7; else goto _L6
_L6:
        }

        final HostApduService this$0;

        MsgHandler()
        {
            this$0 = HostApduService.this;
            super();
        }
    }


    public HostApduService()
    {
        mNfcService = null;
    }

    public final void notifyUnhandled()
    {
        Message message = Message.obtain(null, 3);
        mMessenger.send(message);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TAG", "Local messenger has died.");
          goto _L1
    }

    public final IBinder onBind(Intent intent)
    {
        return mMessenger.getBinder();
    }

    public abstract void onDeactivated(int i);

    public abstract byte[] processCommandApdu(byte abyte0[], Bundle bundle);

    public final void sendResponseApdu(byte abyte0[])
    {
        Message message;
        message = Message.obtain(null, 1);
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", abyte0);
        message.setData(bundle);
        mMessenger.send(message);
_L1:
        return;
        abyte0;
        Log.e("TAG", "Local messenger has died.");
          goto _L1
    }

    public static final int DEACTIVATION_DESELECTED = 1;
    public static final int DEACTIVATION_LINK_LOSS = 0;
    public static final String KEY_DATA = "data";
    public static final int MSG_COMMAND_APDU = 0;
    public static final int MSG_DEACTIVATED = 2;
    public static final int MSG_RESPONSE_APDU = 1;
    public static final int MSG_UNHANDLED = 3;
    public static final String SERVICE_INTERFACE = "android.nfc.cardemulation.action.HOST_APDU_SERVICE";
    public static final String SERVICE_META_DATA = "android.nfc.cardemulation.host_apdu_service";
    static final String TAG = "ApduService";
    final Messenger mMessenger = new Messenger(new MsgHandler());
    Messenger mNfcService;
}
