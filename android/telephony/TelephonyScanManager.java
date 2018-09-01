// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.*;
import android.util.SparseArray;
import com.android.internal.telephony.ITelephony;
import com.android.internal.util.Preconditions;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package android.telephony:
//            NetworkScan, Rlog, NetworkScanRequest, CellInfo

public final class TelephonyScanManager
{
    public static abstract class NetworkScanCallback
    {

        public void onComplete()
        {
        }

        public void onError(int i)
        {
        }

        public void onResults(List list)
        {
        }

        public NetworkScanCallback()
        {
        }
    }

    private static class NetworkScanInfo
    {

        static NetworkScanCallback _2D_get0(NetworkScanInfo networkscaninfo)
        {
            return networkscaninfo.mCallback;
        }

        private final NetworkScanCallback mCallback;
        private final NetworkScanRequest mRequest;

        NetworkScanInfo(NetworkScanRequest networkscanrequest, NetworkScanCallback networkscancallback)
        {
            mRequest = networkscanrequest;
            mCallback = networkscancallback;
        }
    }


    static SparseArray _2D_get0(TelephonyScanManager telephonyscanmanager)
    {
        return telephonyscanmanager.mScanInfo;
    }

    public TelephonyScanManager()
    {
        mScanInfo = new SparseArray();
        HandlerThread handlerthread = new HandlerThread("TelephonyScanManager");
        handlerthread.start();
        mLooper = handlerthread.getLooper();
        mMessenger = new Messenger(new Handler(mLooper) {

            public void handleMessage(Message message)
            {
                Preconditions.checkNotNull(message, "message cannot be null");
                Object obj = TelephonyScanManager._2D_get0(TelephonyScanManager.this);
                obj;
                JVM INSTR monitorenter ;
                NetworkScanInfo networkscaninfo = (NetworkScanInfo)TelephonyScanManager._2D_get0(TelephonyScanManager.this).get(message.arg2);
                obj;
                JVM INSTR monitorexit ;
                if(networkscaninfo == null)
                    throw new RuntimeException((new StringBuilder()).append("Failed to find NetworkScanInfo with id ").append(message.arg2).toString());
                break MISSING_BLOCK_LABEL_76;
                message;
                throw message;
                obj = NetworkScanInfo._2D_get0(networkscaninfo);
                if(obj == null)
                    throw new RuntimeException((new StringBuilder()).append("Failed to find NetworkScanCallback with id ").append(message.arg2).toString());
                message.what;
                JVM INSTR tableswitch 1 3: default 144
            //                           1 176
            //                           2 243
            //                           3 267;
                   goto _L1 _L2 _L3 _L4
_L1:
                Rlog.e("TelephonyScanManager", (new StringBuilder()).append("Unhandled message ").append(Integer.toHexString(message.what)).toString());
_L8:
                return;
_L2:
                CellInfo acellinfo[];
                message = message.getData().getParcelableArray("scanResult");
                acellinfo = new CellInfo[message.length];
                int i = 0;
_L6:
                if(i >= message.length)
                    break; /* Loop/switch isn't completed */
                acellinfo[i] = (CellInfo)message[i];
                i++;
                if(true) goto _L6; else goto _L5
_L5:
                try
                {
                    ((NetworkScanCallback) (obj)).onResults(Arrays.asList(acellinfo));
                }
                // Misplaced declaration of an exception variable
                catch(Message message)
                {
                    Rlog.e("TelephonyScanManager", "Exception in networkscan callback onResults", message);
                }
                continue; /* Loop/switch isn't completed */
_L3:
                try
                {
                    ((NetworkScanCallback) (obj)).onError(message.arg1);
                }
                // Misplaced declaration of an exception variable
                catch(Message message)
                {
                    Rlog.e("TelephonyScanManager", "Exception in networkscan callback onError", message);
                }
                continue; /* Loop/switch isn't completed */
_L4:
                try
                {
                    ((NetworkScanCallback) (obj)).onComplete();
                    TelephonyScanManager._2D_get0(TelephonyScanManager.this).remove(message.arg2);
                }
                // Misplaced declaration of an exception variable
                catch(Message message)
                {
                    Rlog.e("TelephonyScanManager", "Exception in networkscan callback onComplete", message);
                }
                if(true) goto _L8; else goto _L7
_L7:
            }

            final TelephonyScanManager this$0;

            
            {
                this$0 = TelephonyScanManager.this;
                super(looper);
            }
        }
);
    }

    private ITelephony getITelephony()
    {
        return com.android.internal.telephony.ITelephony.Stub.asInterface(ServiceManager.getService("phone"));
    }

    private void saveScanInfo(int i, NetworkScanRequest networkscanrequest, NetworkScanCallback networkscancallback)
    {
        SparseArray sparsearray = mScanInfo;
        sparsearray;
        JVM INSTR monitorenter ;
        SparseArray sparsearray1 = mScanInfo;
        NetworkScanInfo networkscaninfo = JVM INSTR new #11  <Class TelephonyScanManager$NetworkScanInfo>;
        networkscaninfo.NetworkScanInfo(networkscanrequest, networkscancallback);
        sparsearray1.put(i, networkscaninfo);
        sparsearray;
        JVM INSTR monitorexit ;
        return;
        networkscanrequest;
        throw networkscanrequest;
    }

    public NetworkScan requestNetworkScan(int i, NetworkScanRequest networkscanrequest, NetworkScanCallback networkscancallback)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_73;
        Messenger messenger = mMessenger;
        Binder binder = JVM INSTR new #104 <Class Binder>;
        binder.Binder();
        int j = itelephony.requestNetworkScan(i, networkscanrequest, messenger, binder);
        saveScanInfo(j, networkscanrequest, networkscancallback);
        networkscanrequest = new NetworkScan(j, i);
        return networkscanrequest;
        networkscanrequest;
        Rlog.e("TelephonyScanManager", "requestNetworkScan NPE", networkscanrequest);
_L2:
        return null;
        networkscanrequest;
        Rlog.e("TelephonyScanManager", "requestNetworkScan RemoteException", networkscanrequest);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final int CALLBACK_SCAN_COMPLETE = 3;
    public static final int CALLBACK_SCAN_ERROR = 2;
    public static final int CALLBACK_SCAN_RESULTS = 1;
    public static final String SCAN_RESULT_KEY = "scanResult";
    private static final String TAG = "TelephonyScanManager";
    private final Looper mLooper;
    private final Messenger mMessenger;
    private SparseArray mScanInfo;
}
