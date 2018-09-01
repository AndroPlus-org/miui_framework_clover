// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.*;
import com.android.internal.telephony.IPhoneStateListener;
import java.lang.ref.WeakReference;
import java.util.List;

// Referenced classes of package android.telephony:
//            Rlog, CellLocation, DataConnectionRealTimeInfo, PreciseCallState, 
//            PreciseDataConnectionState, ServiceState, SignalStrength, VoLteServiceState

public class PhoneStateListener
{
    private static class IPhoneStateListenerStub extends com.android.internal.telephony.IPhoneStateListener.Stub
    {

        private void send(int i, int j, int k, Object obj)
        {
            PhoneStateListener phonestatelistener = (PhoneStateListener)mPhoneStateListenerWeakRef.get();
            if(phonestatelistener != null)
                Message.obtain(PhoneStateListener._2D_get0(phonestatelistener), i, j, k, obj).sendToTarget();
        }

        public void onCallForwardingIndicatorChanged(boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            send(8, i, 0, null);
        }

        public void onCallStateChanged(int i, String s)
        {
            send(32, i, 0, s);
        }

        public void onCarrierNetworkChange(boolean flag)
        {
            send(0x10000, 0, 0, Boolean.valueOf(flag));
        }

        public void onCellInfoChanged(List list)
        {
            send(1024, 0, 0, list);
        }

        public void onCellLocationChanged(Bundle bundle)
        {
            send(16, 0, 0, CellLocation.newFromBundle(bundle));
        }

        public void onDataActivationStateChanged(int i)
        {
            send(0x40000, 0, 0, Integer.valueOf(i));
        }

        public void onDataActivity(int i)
        {
            send(128, i, 0, null);
        }

        public void onDataConnectionRealTimeInfoChanged(DataConnectionRealTimeInfo dataconnectionrealtimeinfo)
        {
            send(8192, 0, 0, dataconnectionrealtimeinfo);
        }

        public void onDataConnectionStateChanged(int i, int j)
        {
            send(64, i, j, null);
        }

        public void onMessageWaitingIndicatorChanged(boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            send(4, i, 0, null);
        }

        public void onOemHookRawEvent(byte abyte0[])
        {
            send(32768, 0, 0, abyte0);
        }

        public void onOtaspChanged(int i)
        {
            send(512, i, 0, null);
        }

        public void onPreciseCallStateChanged(PreciseCallState precisecallstate)
        {
            send(2048, 0, 0, precisecallstate);
        }

        public void onPreciseDataConnectionStateChanged(PreciseDataConnectionState precisedataconnectionstate)
        {
            send(4096, 0, 0, precisedataconnectionstate);
        }

        public void onServiceStateChanged(ServiceState servicestate)
        {
            send(1, 0, 0, servicestate);
        }

        public void onSignalStrengthChanged(int i)
        {
            send(2, i, 0, null);
        }

        public void onSignalStrengthsChanged(SignalStrength signalstrength)
        {
            send(256, 0, 0, signalstrength);
        }

        public void onVoLteServiceStateChanged(VoLteServiceState volteservicestate)
        {
            send(16384, 0, 0, volteservicestate);
        }

        public void onVoiceActivationStateChanged(int i)
        {
            send(0x20000, 0, 0, Integer.valueOf(i));
        }

        private WeakReference mPhoneStateListenerWeakRef;

        public IPhoneStateListenerStub(PhoneStateListener phonestatelistener)
        {
            mPhoneStateListenerWeakRef = new WeakReference(phonestatelistener);
        }
    }


    static Handler _2D_get0(PhoneStateListener phonestatelistener)
    {
        return phonestatelistener.mHandler;
    }

    public PhoneStateListener()
    {
        this(null, Looper.myLooper());
    }

    public PhoneStateListener(Looper looper)
    {
        this(null, looper);
    }

    public PhoneStateListener(Integer integer)
    {
        this(integer, Looper.myLooper());
    }

    public PhoneStateListener(Integer integer, Looper looper)
    {
        callback = new IPhoneStateListenerStub(this);
        mSubId = integer;
        mHandler = new Handler(looper) {

            public void handleMessage(Message message)
            {
                boolean flag;
                boolean flag1;
                flag = true;
                flag1 = true;
                message.what;
                JVM INSTR lookupswitch 19: default 172
            //                           1: 173
            //                           2: 190
            //                           4: 204
            //                           8: 231
            //                           16: 260
            //                           32: 277
            //                           64: 298
            //                           128: 327
            //                           256: 341
            //                           512: 358
            //                           1024: 372
            //                           2048: 389
            //                           4096: 406
            //                           8192: 423
            //                           16384: 440
            //                           32768: 497
            //                           65536: 514
            //                           131072: 457
            //                           262144: 477;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L1:
                return;
_L2:
                onServiceStateChanged((ServiceState)message.obj);
                continue; /* Loop/switch isn't completed */
_L3:
                onSignalStrengthChanged(message.arg1);
                continue; /* Loop/switch isn't completed */
_L4:
                PhoneStateListener phonestatelistener = PhoneStateListener.this;
                if(message.arg1 == 0)
                    flag1 = false;
                phonestatelistener.onMessageWaitingIndicatorChanged(flag1);
                continue; /* Loop/switch isn't completed */
_L5:
                PhoneStateListener phonestatelistener1 = PhoneStateListener.this;
                boolean flag2;
                if(message.arg1 != 0)
                    flag2 = flag;
                else
                    flag2 = false;
                phonestatelistener1.onCallForwardingIndicatorChanged(flag2);
                continue; /* Loop/switch isn't completed */
_L6:
                onCellLocationChanged((CellLocation)message.obj);
                continue; /* Loop/switch isn't completed */
_L7:
                onCallStateChanged(message.arg1, (String)message.obj);
                continue; /* Loop/switch isn't completed */
_L8:
                onDataConnectionStateChanged(message.arg1, message.arg2);
                onDataConnectionStateChanged(message.arg1);
                continue; /* Loop/switch isn't completed */
_L9:
                onDataActivity(message.arg1);
                continue; /* Loop/switch isn't completed */
_L10:
                onSignalStrengthsChanged((SignalStrength)message.obj);
                continue; /* Loop/switch isn't completed */
_L11:
                onOtaspChanged(message.arg1);
                continue; /* Loop/switch isn't completed */
_L12:
                onCellInfoChanged((List)message.obj);
                continue; /* Loop/switch isn't completed */
_L13:
                onPreciseCallStateChanged((PreciseCallState)message.obj);
                continue; /* Loop/switch isn't completed */
_L14:
                onPreciseDataConnectionStateChanged((PreciseDataConnectionState)message.obj);
                continue; /* Loop/switch isn't completed */
_L15:
                onDataConnectionRealTimeInfoChanged((DataConnectionRealTimeInfo)message.obj);
                continue; /* Loop/switch isn't completed */
_L16:
                onVoLteServiceStateChanged((VoLteServiceState)message.obj);
                continue; /* Loop/switch isn't completed */
_L19:
                onVoiceActivationStateChanged(((Integer)message.obj).intValue());
                continue; /* Loop/switch isn't completed */
_L20:
                onDataActivationStateChanged(((Integer)message.obj).intValue());
                continue; /* Loop/switch isn't completed */
_L17:
                onOemHookRawEvent((byte[])message.obj);
                continue; /* Loop/switch isn't completed */
_L18:
                onCarrierNetworkChange(((Boolean)message.obj).booleanValue());
                if(true) goto _L1; else goto _L21
_L21:
            }

            final PhoneStateListener this$0;

            
            {
                this$0 = PhoneStateListener.this;
                super(looper);
            }
        }
;
    }

    private void log(String s)
    {
        Rlog.d("PhoneStateListener", s);
    }

    public int getSubId()
    {
        return mSubId.intValue();
    }

    public void onCallForwardingIndicatorChanged(boolean flag)
    {
    }

    public void onCallStateChanged(int i, String s)
    {
    }

    public void onCarrierNetworkChange(boolean flag)
    {
    }

    public void onCellInfoChanged(List list)
    {
    }

    public void onCellLocationChanged(CellLocation celllocation)
    {
    }

    public void onDataActivationStateChanged(int i)
    {
    }

    public void onDataActivity(int i)
    {
    }

    public void onDataConnectionRealTimeInfoChanged(DataConnectionRealTimeInfo dataconnectionrealtimeinfo)
    {
    }

    public void onDataConnectionStateChanged(int i)
    {
    }

    public void onDataConnectionStateChanged(int i, int j)
    {
    }

    public void onMessageWaitingIndicatorChanged(boolean flag)
    {
    }

    public void onOemHookRawEvent(byte abyte0[])
    {
    }

    public void onOtaspChanged(int i)
    {
    }

    public void onPreciseCallStateChanged(PreciseCallState precisecallstate)
    {
    }

    public void onPreciseDataConnectionStateChanged(PreciseDataConnectionState precisedataconnectionstate)
    {
    }

    public void onServiceStateChanged(ServiceState servicestate)
    {
    }

    public void onSignalStrengthChanged(int i)
    {
    }

    public void onSignalStrengthsChanged(SignalStrength signalstrength)
    {
    }

    public void onVoLteServiceStateChanged(VoLteServiceState volteservicestate)
    {
    }

    public void onVoiceActivationStateChanged(int i)
    {
    }

    public void setSubId(int i)
    {
        mSubId = Integer.valueOf(i);
    }

    public Integer updateSubscription(Integer integer)
    {
        Integer integer1 = mSubId;
        mSubId = integer;
        return integer1;
    }

    private static final boolean DBG = false;
    public static final int LISTEN_CALL_FORWARDING_INDICATOR = 8;
    public static final int LISTEN_CALL_STATE = 32;
    public static final int LISTEN_CARRIER_NETWORK_CHANGE = 0x10000;
    public static final int LISTEN_CELL_INFO = 1024;
    public static final int LISTEN_CELL_LOCATION = 16;
    public static final int LISTEN_DATA_ACTIVATION_STATE = 0x40000;
    public static final int LISTEN_DATA_ACTIVITY = 128;
    public static final int LISTEN_DATA_CONNECTION_REAL_TIME_INFO = 8192;
    public static final int LISTEN_DATA_CONNECTION_STATE = 64;
    public static final int LISTEN_MESSAGE_WAITING_INDICATOR = 4;
    public static final int LISTEN_NONE = 0;
    public static final int LISTEN_OEM_HOOK_RAW_EVENT = 32768;
    public static final int LISTEN_OTASP_CHANGED = 512;
    public static final int LISTEN_PRECISE_CALL_STATE = 2048;
    public static final int LISTEN_PRECISE_DATA_CONNECTION_STATE = 4096;
    public static final int LISTEN_SERVICE_STATE = 1;
    public static final int LISTEN_SIGNAL_STRENGTH = 2;
    public static final int LISTEN_SIGNAL_STRENGTHS = 256;
    public static final int LISTEN_VOICE_ACTIVATION_STATE = 0x20000;
    public static final int LISTEN_VOLTE_STATE = 16384;
    private static final String LOG_TAG = "PhoneStateListener";
    IPhoneStateListener callback;
    private final Handler mHandler;
    protected Integer mSubId;
}
