// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


public final class Pools
{
    public static interface Pool
    {

        public abstract Object acquire();

        public abstract boolean release(Object obj);
    }

    public static class SimplePool
        implements Pool
    {

        private boolean isInPool(Object obj)
        {
            for(int i = 0; i < mPoolSize; i++)
                if(mPool[i] == obj)
                    return true;

            return false;
        }

        public Object acquire()
        {
            if(mPoolSize > 0)
            {
                int i = mPoolSize - 1;
                Object obj = mPool[i];
                mPool[i] = null;
                mPoolSize = mPoolSize - 1;
                return obj;
            } else
            {
                return null;
            }
        }

        public boolean release(Object obj)
        {
            if(isInPool(obj))
                throw new IllegalStateException("Already in the pool!");
            if(mPoolSize < mPool.length)
            {
                mPool[mPoolSize] = obj;
                mPoolSize = mPoolSize + 1;
                return true;
            } else
            {
                return false;
            }
        }

        private final Object mPool[];
        private int mPoolSize;

        public SimplePool(int i)
        {
            if(i <= 0)
            {
                throw new IllegalArgumentException("The max pool size must be > 0");
            } else
            {
                mPool = new Object[i];
                return;
            }
        }
    }

    public static class SynchronizedPool extends SimplePool
    {

        public Object acquire()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = super.acquire();
            obj;
            JVM INSTR monitorexit ;
            return obj1;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean release(Object obj)
        {
            Object obj1 = mLock;
            obj1;
            JVM INSTR monitorenter ;
            boolean flag = super.release(obj);
            obj1;
            JVM INSTR monitorexit ;
            return flag;
            obj;
            throw obj;
        }

        private final Object mLock = new Object();

        public SynchronizedPool(int i)
        {
            super(i);
        }
    }


    private Pools()
    {
    }
}
