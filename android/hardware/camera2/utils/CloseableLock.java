// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import android.util.Log;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CloseableLock
    implements AutoCloseable
{
    public class ScopedLock
        implements AutoCloseable
    {

        public void close()
        {
            releaseLock();
        }

        final CloseableLock this$0;

        private ScopedLock()
        {
            this$0 = CloseableLock.this;
            super();
        }

        ScopedLock(ScopedLock scopedlock)
        {
            this();
        }
    }


    public CloseableLock()
    {
        TAG = "CloseableLock";
        mClosed = false;
        mExclusive = false;
        mSharedLocks = 0;
        mLock = new ReentrantLock();
        mCondition = mLock.newCondition();
        mLockCount = new ThreadLocal() {

            protected Integer initialValue()
            {
                return Integer.valueOf(0);
            }

            protected volatile Object initialValue()
            {
                return initialValue();
            }

            final CloseableLock this$0;

            
            {
                this$0 = CloseableLock.this;
                super();
            }
        }
;
        mName = "";
    }

    public CloseableLock(String s)
    {
        TAG = "CloseableLock";
        mClosed = false;
        mExclusive = false;
        mSharedLocks = 0;
        mLock = new ReentrantLock();
        mCondition = mLock.newCondition();
        mLockCount = new _cls1();
        mName = s;
    }

    private void log(String s)
    {
        Log.v((new StringBuilder()).append("CloseableLock[").append(mName).append("]").toString(), s);
    }

    public ScopedLock acquireExclusiveLock()
    {
        boolean flag;
        mLock.lock();
        flag = mClosed;
        if(flag)
        {
            mLock.unlock();
            return null;
        }
        int i = ((Integer)mLockCount.get()).intValue();
        if(mExclusive || i <= 0)
            break MISSING_BLOCK_LABEL_72;
        IllegalStateException illegalstateexception = JVM INSTR new #108 <Class IllegalStateException>;
        illegalstateexception.IllegalStateException("Cannot acquire exclusive lock while holding shared lock");
        throw illegalstateexception;
        Exception exception;
        exception;
        mLock.unlock();
        throw exception;
_L2:
        if(i != 0)
            break MISSING_BLOCK_LABEL_117;
        if(!mExclusive && mSharedLocks <= 0)
            break MISSING_BLOCK_LABEL_117;
        mCondition.awaitUninterruptibly();
        flag = mClosed;
        if(!flag) goto _L2; else goto _L1
_L1:
        mLock.unlock();
        return null;
        mExclusive = true;
        int j = ((Integer)mLockCount.get()).intValue();
        mLockCount.set(Integer.valueOf(j + 1));
        mLock.unlock();
        return new ScopedLock(null);
    }

    public ScopedLock acquireLock()
    {
        boolean flag;
        mLock.lock();
        flag = mClosed;
        if(flag)
        {
            mLock.unlock();
            return null;
        }
        int i = ((Integer)mLockCount.get()).intValue();
        if(!mExclusive || i <= 0)
            break MISSING_BLOCK_LABEL_72;
        IllegalStateException illegalstateexception = JVM INSTR new #108 <Class IllegalStateException>;
        illegalstateexception.IllegalStateException("Cannot acquire shared lock while holding exclusive lock");
        throw illegalstateexception;
        Exception exception;
        exception;
        mLock.unlock();
        throw exception;
label0:
        {
            boolean flag1;
            do
            {
                if(!mExclusive)
                    break label0;
                mCondition.awaitUninterruptibly();
                flag1 = mClosed;
            } while(!flag1);
            mLock.unlock();
            return null;
        }
        mSharedLocks = mSharedLocks + 1;
        int j = ((Integer)mLockCount.get()).intValue();
        mLockCount.set(Integer.valueOf(j + 1));
        mLock.unlock();
        return new ScopedLock(null);
    }

    public void close()
    {
        if(mClosed)
            return;
        if(acquireExclusiveLock() == null)
            return;
        if(((Integer)mLockCount.get()).intValue() != 1)
            throw new IllegalStateException("Cannot close while one or more acquired locks are being held by this thread; release all other locks first");
        mLock.lock();
        mClosed = true;
        mExclusive = false;
        mSharedLocks = 0;
        mLockCount.remove();
        mCondition.signalAll();
        mLock.unlock();
        return;
        Exception exception;
        exception;
        mLock.unlock();
        throw exception;
    }

    public void releaseLock()
    {
        if(((Integer)mLockCount.get()).intValue() <= 0)
            throw new IllegalStateException("Cannot release lock that was not acquired by this thread");
        mLock.lock();
        if(mClosed)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #108 <Class IllegalStateException>;
            illegalstateexception.IllegalStateException("Do not release after the lock has been closed");
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_62;
        Exception exception;
        exception;
        mLock.unlock();
        throw exception;
        if(mExclusive) goto _L2; else goto _L1
_L1:
        mSharedLocks = mSharedLocks - 1;
_L7:
        int i;
        i = ((Integer)mLockCount.get()).intValue() - 1;
        mLockCount.set(Integer.valueOf(i));
        if(i != 0) goto _L4; else goto _L3
_L3:
        if(!mExclusive) goto _L4; else goto _L5
_L5:
        mExclusive = false;
        mCondition.signalAll();
_L9:
        mLock.unlock();
        return;
_L2:
        if(mSharedLocks == 0) goto _L7; else goto _L6
_L6:
        AssertionError assertionerror = JVM INSTR new #149 <Class AssertionError>;
        StringBuilder stringbuilder = JVM INSTR new #67  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        assertionerror.AssertionError(stringbuilder.append("Too many shared locks ").append(mSharedLocks).toString());
        throw assertionerror;
_L4:
        if(i != 0) goto _L9; else goto _L8
_L8:
        if(mSharedLocks != 0) goto _L9; else goto _L10
_L10:
        mCondition.signalAll();
          goto _L9
    }

    private static final boolean VERBOSE = false;
    private final String TAG;
    private volatile boolean mClosed;
    private final Condition mCondition;
    private boolean mExclusive;
    private final ReentrantLock mLock;
    private final ThreadLocal mLockCount;
    private final String mName;
    private int mSharedLocks;
}
