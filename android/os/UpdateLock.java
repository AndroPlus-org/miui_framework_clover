// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;

// Referenced classes of package android.os:
//            Binder, RemoteException, IUpdateLock, ServiceManager, 
//            IBinder

public class UpdateLock
{

    public UpdateLock(String s)
    {
        mCount = 0;
        mRefCounted = true;
        mHeld = false;
        mTag = s;
        mToken = new Binder();
    }

    private void acquireLocked()
    {
label0:
        {
            if(mRefCounted)
            {
                int i = mCount;
                mCount = i + 1;
                if(i != 0)
                    break label0;
            }
            if(sService != null)
                try
                {
                    sService.acquireUpdateLock(mToken, mTag);
                }
                catch(RemoteException remoteexception)
                {
                    Log.e("UpdateLock", "Unable to contact service to acquire");
                }
            mHeld = true;
        }
    }

    private static void checkService()
    {
        if(sService == null)
            sService = IUpdateLock.Stub.asInterface(ServiceManager.getService("updatelock"));
    }

    private void releaseLocked()
    {
label0:
        {
            if(mRefCounted)
            {
                int i = mCount - 1;
                mCount = i;
                if(i != 0)
                    break label0;
            }
            if(sService != null)
                try
                {
                    sService.releaseUpdateLock(mToken);
                }
                catch(RemoteException remoteexception)
                {
                    Log.e("UpdateLock", "Unable to contact service to release");
                }
            mHeld = false;
        }
        if(mCount < 0)
            throw new RuntimeException("UpdateLock under-locked");
        else
            return;
    }

    public void acquire()
    {
        checkService();
        IBinder ibinder = mToken;
        ibinder;
        JVM INSTR monitorenter ;
        acquireLocked();
        ibinder;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        IBinder ibinder = mToken;
        ibinder;
        JVM INSTR monitorenter ;
        if(!mHeld)
            break MISSING_BLOCK_LABEL_34;
        Log.wtf("UpdateLock", "UpdateLock finalized while still held");
        sService.releaseUpdateLock(mToken);
_L1:
        ibinder;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        Log.e("UpdateLock", "Unable to contact service to release");
          goto _L1
        obj;
        throw obj;
    }

    public boolean isHeld()
    {
        IBinder ibinder = mToken;
        ibinder;
        JVM INSTR monitorenter ;
        boolean flag = mHeld;
        ibinder;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void release()
    {
        checkService();
        IBinder ibinder = mToken;
        ibinder;
        JVM INSTR monitorenter ;
        releaseLocked();
        ibinder;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setReferenceCounted(boolean flag)
    {
        mRefCounted = flag;
    }

    private static final boolean DEBUG = false;
    public static final String NOW_IS_CONVENIENT = "nowisconvenient";
    private static final String TAG = "UpdateLock";
    public static final String TIMESTAMP = "timestamp";
    public static final String UPDATE_LOCK_CHANGED = "android.os.UpdateLock.UPDATE_LOCK_CHANGED";
    private static IUpdateLock sService;
    int mCount;
    boolean mHeld;
    boolean mRefCounted;
    final String mTag;
    IBinder mToken;
}
