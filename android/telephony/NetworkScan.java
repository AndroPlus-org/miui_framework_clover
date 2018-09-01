// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.telephony.ITelephony;

// Referenced classes of package android.telephony:
//            Rlog

public class NetworkScan
{

    public NetworkScan(int i, int j)
    {
        mScanId = i;
        mSubId = j;
    }

    private ITelephony getITelephony()
    {
        return com.android.internal.telephony.ITelephony.Stub.asInterface(ServiceManager.getService("phone"));
    }

    public void stop()
        throws RemoteException
    {
        Object obj = getITelephony();
        if(obj != null)
        {
            try
            {
                ((ITelephony) (obj)).stopNetworkScan(mSubId, mScanId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                Rlog.e("NetworkScan", "stopNetworkScan  RemoteException", remoteexception);
            }
            break MISSING_BLOCK_LABEL_46;
        }
        obj = JVM INSTR new #56  <Class RemoteException>;
        ((RemoteException) (obj)).RemoteException("Failed to get the ITelephony instance.");
        throw obj;
        throw new RemoteException((new StringBuilder()).append("Failed to stop the network scan with id ").append(mScanId).toString());
    }

    public static final int ERROR_INTERRUPTED = 10002;
    public static final int ERROR_INVALID_SCAN = 2;
    public static final int ERROR_INVALID_SCANID = 10001;
    public static final int ERROR_MODEM_BUSY = 3;
    public static final int ERROR_MODEM_ERROR = 1;
    public static final int ERROR_RIL_ERROR = 10000;
    public static final int ERROR_UNSUPPORTED = 4;
    public static final int SUCCESS = 0;
    public static final String TAG = "NetworkScan";
    private final int mScanId;
    private final int mSubId;
}
