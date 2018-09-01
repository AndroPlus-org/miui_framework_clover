// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.carrier;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package android.service.carrier:
//            MessagePdu, ICarrierMessagingCallback

public abstract class CarrierMessagingService extends Service
{
    private class ICarrierMessagingWrapper extends ICarrierMessagingService.Stub
    {

        public void downloadMms(Uri uri, int i, Uri uri1, ICarrierMessagingCallback icarriermessagingcallback)
        {
            onDownloadMms(uri, i, uri1, icarriermessagingcallback. new ResultCallback() {

                public void onReceiveResult(Integer integer)
                    throws RemoteException
                {
                    callback.onDownloadMmsComplete(integer.intValue());
                }

                public volatile void onReceiveResult(Object obj)
                    throws RemoteException
                {
                    onReceiveResult((Integer)obj);
                }

                final ICarrierMessagingWrapper this$1;
                final ICarrierMessagingCallback val$callback;

            
            {
                this$1 = final_icarriermessagingwrapper;
                callback = ICarrierMessagingCallback.this;
                super();
            }
            }
);
        }

        public void filterSms(MessagePdu messagepdu, String s, int i, int j, ICarrierMessagingCallback icarriermessagingcallback)
        {
            onReceiveTextSms(messagepdu, s, i, j, icarriermessagingcallback. new ResultCallback() {

                public void onReceiveResult(Integer integer)
                    throws RemoteException
                {
                    callback.onFilterComplete(integer.intValue());
                }

                public volatile void onReceiveResult(Object obj)
                    throws RemoteException
                {
                    onReceiveResult((Integer)obj);
                }

                final ICarrierMessagingWrapper this$1;
                final ICarrierMessagingCallback val$callback;

            
            {
                this$1 = final_icarriermessagingwrapper;
                callback = ICarrierMessagingCallback.this;
                super();
            }
            }
);
        }

        public void sendDataSms(byte abyte0[], int i, String s, int j, int k, ICarrierMessagingCallback icarriermessagingcallback)
        {
            onSendDataSms(abyte0, i, s, j, k, icarriermessagingcallback. new ResultCallback() {

                public void onReceiveResult(SendSmsResult sendsmsresult)
                    throws RemoteException
                {
                    callback.onSendSmsComplete(sendsmsresult.getSendStatus(), sendsmsresult.getMessageRef());
                }

                public volatile void onReceiveResult(Object obj)
                    throws RemoteException
                {
                    onReceiveResult((SendSmsResult)obj);
                }

                final ICarrierMessagingWrapper this$1;
                final ICarrierMessagingCallback val$callback;

            
            {
                this$1 = final_icarriermessagingwrapper;
                callback = ICarrierMessagingCallback.this;
                super();
            }
            }
);
        }

        public void sendMms(Uri uri, int i, Uri uri1, ICarrierMessagingCallback icarriermessagingcallback)
        {
            onSendMms(uri, i, uri1, icarriermessagingcallback. new ResultCallback() {

                public void onReceiveResult(SendMmsResult sendmmsresult)
                    throws RemoteException
                {
                    callback.onSendMmsComplete(sendmmsresult.getSendStatus(), sendmmsresult.getSendConfPdu());
                }

                public volatile void onReceiveResult(Object obj)
                    throws RemoteException
                {
                    onReceiveResult((SendMmsResult)obj);
                }

                final ICarrierMessagingWrapper this$1;
                final ICarrierMessagingCallback val$callback;

            
            {
                this$1 = final_icarriermessagingwrapper;
                callback = ICarrierMessagingCallback.this;
                super();
            }
            }
);
        }

        public void sendMultipartTextSms(List list, int i, String s, int j, ICarrierMessagingCallback icarriermessagingcallback)
        {
            onSendMultipartTextSms(list, i, s, j, icarriermessagingcallback. new ResultCallback() {

                public void onReceiveResult(SendMultipartSmsResult sendmultipartsmsresult)
                    throws RemoteException
                {
                    callback.onSendMultipartSmsComplete(sendmultipartsmsresult.getSendStatus(), sendmultipartsmsresult.getMessageRefs());
                }

                public volatile void onReceiveResult(Object obj)
                    throws RemoteException
                {
                    onReceiveResult((SendMultipartSmsResult)obj);
                }

                final ICarrierMessagingWrapper this$1;
                final ICarrierMessagingCallback val$callback;

            
            {
                this$1 = final_icarriermessagingwrapper;
                callback = ICarrierMessagingCallback.this;
                super();
            }
            }
);
        }

        public void sendTextSms(String s, int i, String s1, int j, ICarrierMessagingCallback icarriermessagingcallback)
        {
            onSendTextSms(s, i, s1, j, icarriermessagingcallback. new ResultCallback() {

                public void onReceiveResult(SendSmsResult sendsmsresult)
                    throws RemoteException
                {
                    callback.onSendSmsComplete(sendsmsresult.getSendStatus(), sendsmsresult.getMessageRef());
                }

                public volatile void onReceiveResult(Object obj)
                    throws RemoteException
                {
                    onReceiveResult((SendSmsResult)obj);
                }

                final ICarrierMessagingWrapper this$1;
                final ICarrierMessagingCallback val$callback;

            
            {
                this$1 = final_icarriermessagingwrapper;
                callback = ICarrierMessagingCallback.this;
                super();
            }
            }
);
        }

        final CarrierMessagingService this$0;

        private ICarrierMessagingWrapper()
        {
            this$0 = CarrierMessagingService.this;
            super();
        }

        ICarrierMessagingWrapper(ICarrierMessagingWrapper icarriermessagingwrapper)
        {
            this();
        }
    }

    public static interface ResultCallback
    {

        public abstract void onReceiveResult(Object obj)
            throws RemoteException;
    }

    public static final class SendMmsResult
    {

        public byte[] getSendConfPdu()
        {
            return mSendConfPdu;
        }

        public int getSendStatus()
        {
            return mSendStatus;
        }

        private byte mSendConfPdu[];
        private int mSendStatus;

        public SendMmsResult(int i, byte abyte0[])
        {
            mSendStatus = i;
            mSendConfPdu = abyte0;
        }
    }

    public static final class SendMultipartSmsResult
    {

        public int[] getMessageRefs()
        {
            return mMessageRefs;
        }

        public int getSendStatus()
        {
            return mSendStatus;
        }

        private final int mMessageRefs[];
        private final int mSendStatus;

        public SendMultipartSmsResult(int i, int ai[])
        {
            mSendStatus = i;
            mMessageRefs = ai;
        }
    }

    public static final class SendSmsResult
    {

        public int getMessageRef()
        {
            return mMessageRef;
        }

        public int getSendStatus()
        {
            return mSendStatus;
        }

        private final int mMessageRef;
        private final int mSendStatus;

        public SendSmsResult(int i, int j)
        {
            mSendStatus = i;
            mMessageRef = j;
        }
    }


    public CarrierMessagingService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        if(!"android.service.carrier.CarrierMessagingService".equals(intent.getAction()))
            return null;
        else
            return mWrapper;
    }

    public void onDownloadMms(Uri uri, int i, Uri uri1, ResultCallback resultcallback)
    {
        resultcallback.onReceiveResult(Integer.valueOf(1));
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onFilterSms(MessagePdu messagepdu, String s, int i, int j, ResultCallback resultcallback)
    {
        resultcallback.onReceiveResult(Boolean.valueOf(true));
_L2:
        return;
        messagepdu;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onReceiveTextSms(MessagePdu messagepdu, String s, int i, int j, final ResultCallback callback)
    {
        onFilterSms(messagepdu, s, i, j, new ResultCallback() {

            public void onReceiveResult(Boolean boolean1)
                throws RemoteException
            {
                ResultCallback resultcallback = callback;
                int k;
                if(boolean1.booleanValue())
                    k = 0;
                else
                    k = 3;
                resultcallback.onReceiveResult(Integer.valueOf(k));
            }

            public volatile void onReceiveResult(Object obj)
                throws RemoteException
            {
                onReceiveResult((Boolean)obj);
            }

            final CarrierMessagingService this$0;
            final ResultCallback val$callback;

            
            {
                this$0 = CarrierMessagingService.this;
                callback = resultcallback;
                super();
            }
        }
);
    }

    public void onSendDataSms(byte abyte0[], int i, String s, int j, int k, ResultCallback resultcallback)
    {
        onSendDataSms(abyte0, i, s, j, resultcallback);
    }

    public void onSendDataSms(byte abyte0[], int i, String s, int j, ResultCallback resultcallback)
    {
        abyte0 = JVM INSTR new #32  <Class CarrierMessagingService$SendSmsResult>;
        abyte0.SendSmsResult(1, 0);
        resultcallback.onReceiveResult(abyte0);
_L2:
        return;
        abyte0;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onSendMms(Uri uri, int i, Uri uri1, ResultCallback resultcallback)
    {
        uri = JVM INSTR new #26  <Class CarrierMessagingService$SendMmsResult>;
        uri.SendMmsResult(1, null);
        resultcallback.onReceiveResult(uri);
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onSendMultipartTextSms(List list, int i, String s, int j, ResultCallback resultcallback)
    {
        onSendMultipartTextSms(list, i, s, resultcallback);
    }

    public void onSendMultipartTextSms(List list, int i, String s, ResultCallback resultcallback)
    {
        list = JVM INSTR new #29  <Class CarrierMessagingService$SendMultipartSmsResult>;
        list.SendMultipartSmsResult(1, null);
        resultcallback.onReceiveResult(list);
_L2:
        return;
        list;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onSendTextSms(String s, int i, String s1, int j, ResultCallback resultcallback)
    {
        onSendTextSms(s, i, s1, resultcallback);
    }

    public void onSendTextSms(String s, int i, String s1, ResultCallback resultcallback)
    {
        s = JVM INSTR new #32  <Class CarrierMessagingService$SendSmsResult>;
        s.SendSmsResult(1, 0);
        resultcallback.onReceiveResult(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final int DOWNLOAD_STATUS_ERROR = 2;
    public static final int DOWNLOAD_STATUS_OK = 0;
    public static final int DOWNLOAD_STATUS_RETRY_ON_CARRIER_NETWORK = 1;
    public static final int RECEIVE_OPTIONS_DEFAULT = 0;
    public static final int RECEIVE_OPTIONS_DROP = 1;
    public static final int RECEIVE_OPTIONS_SKIP_NOTIFY_WHEN_CREDENTIAL_PROTECTED_STORAGE_UNAVAILABLE = 2;
    public static final int SEND_FLAG_REQUEST_DELIVERY_STATUS = 1;
    public static final int SEND_STATUS_ERROR = 2;
    public static final int SEND_STATUS_OK = 0;
    public static final int SEND_STATUS_RETRY_ON_CARRIER_NETWORK = 1;
    public static final String SERVICE_INTERFACE = "android.service.carrier.CarrierMessagingService";
    private final ICarrierMessagingWrapper mWrapper = new ICarrierMessagingWrapper(null);
}
