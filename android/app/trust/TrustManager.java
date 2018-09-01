// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.trust;

import android.os.*;
import android.util.ArrayMap;

// Referenced classes of package android.app.trust:
//            ITrustManager, ITrustListener

public class TrustManager
{
    public static interface TrustListener
    {

        public abstract void onTrustChanged(boolean flag, int i, int j);

        public abstract void onTrustManagedChanged(boolean flag, int i);
    }


    static Handler _2D_get0(TrustManager trustmanager)
    {
        return trustmanager.mHandler;
    }

    public TrustManager(IBinder ibinder)
    {
        mService = ITrustManager.Stub.asInterface(ibinder);
    }

    public void clearAllFingerprints()
    {
        try
        {
            mService.clearAllFingerprints();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public boolean isTrustUsuallyManaged(int i)
    {
        boolean flag;
        try
        {
            flag = mService.isTrustUsuallyManaged(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void registerTrustListener(TrustListener trustlistener)
    {
        try
        {
            ITrustListener.Stub stub = JVM INSTR new #8   <Class TrustManager$2>;
            stub.this. _cls2();
            mService.registerTrustListener(stub);
            mTrustListeners.put(trustlistener, stub);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(TrustListener trustlistener)
        {
            throw trustlistener.rethrowFromSystemServer();
        }
    }

    public void reportEnabledTrustAgentsChanged(int i)
    {
        try
        {
            mService.reportEnabledTrustAgentsChanged(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void reportKeyguardShowingChanged()
    {
        try
        {
            mService.reportKeyguardShowingChanged();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void reportUnlockAttempt(boolean flag, int i)
    {
        try
        {
            mService.reportUnlockAttempt(flag, i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void reportUnlockLockout(int i, int j)
    {
        try
        {
            mService.reportUnlockLockout(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setDeviceLockedForUser(int i, boolean flag)
    {
        try
        {
            mService.setDeviceLockedForUser(i, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void unlockedByFingerprintForUser(int i)
    {
        try
        {
            mService.unlockedByFingerprintForUser(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void unregisterTrustListener(TrustListener trustlistener)
    {
        trustlistener = (ITrustListener)mTrustListeners.remove(trustlistener);
        if(trustlistener == null)
            break MISSING_BLOCK_LABEL_26;
        mService.unregisterTrustListener(trustlistener);
        return;
        trustlistener;
        throw trustlistener.rethrowFromSystemServer();
    }

    private static final String DATA_FLAGS = "initiatedByUser";
    private static final int MSG_TRUST_CHANGED = 1;
    private static final int MSG_TRUST_MANAGED_CHANGED = 2;
    private static final String TAG = "TrustManager";
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = true;
            message.what;
            JVM INSTR tableswitch 1 2: default 32
        //                       1 33
        //                       2 95;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            int i;
            TrustListener trustlistener;
            if(message.peekData() != null)
                i = message.peekData().getInt("initiatedByUser");
            else
                i = 0;
            trustlistener = (TrustListener)message.obj;
            if(message.arg1 == 0)
                flag1 = false;
            trustlistener.onTrustChanged(flag1, message.arg2, i);
            continue; /* Loop/switch isn't completed */
_L3:
            TrustListener trustlistener1 = (TrustListener)message.obj;
            boolean flag2;
            if(message.arg1 != 0)
                flag2 = flag;
            else
                flag2 = false;
            trustlistener1.onTrustManagedChanged(flag2, message.arg2);
            if(true) goto _L1; else goto _L4
_L4:
        }

        final TrustManager this$0;

            
            {
                this$0 = TrustManager.this;
                super(looper);
            }
    }
;
    private final ITrustManager mService;
    private final ArrayMap mTrustListeners = new ArrayMap();

    // Unreferenced inner class android/app/trust/TrustManager$2

/* anonymous class */
    class _cls2 extends ITrustListener.Stub
    {

        public void onTrustChanged(boolean flag, int i, int j)
        {
            int k = 0;
            Object obj = TrustManager._2D_get0(TrustManager.this);
            if(flag)
                k = 1;
            obj = ((Handler) (obj)).obtainMessage(1, k, i, trustListener);
            if(j != 0)
                ((Message) (obj)).getData().putInt("initiatedByUser", j);
            ((Message) (obj)).sendToTarget();
        }

        public void onTrustManagedChanged(boolean flag, int i)
        {
            Handler handler = TrustManager._2D_get0(TrustManager.this);
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            handler.obtainMessage(2, j, i, trustListener).sendToTarget();
        }

        final TrustManager this$0;
        final TrustListener val$trustListener;

            
            {
                this$0 = TrustManager.this;
                trustListener = trustlistener;
                super();
            }
    }

}
