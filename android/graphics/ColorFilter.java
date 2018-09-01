// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import libcore.util.NativeAllocationRegistry;

public class ColorFilter
{
    private static class NoImagePreloadHolder
    {

        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(android/graphics/ColorFilter.getClassLoader(), ColorFilter._2D_wrap0(), 50L);


        private NoImagePreloadHolder()
        {
        }
    }


    static long _2D_wrap0()
    {
        return nativeGetFinalizer();
    }

    public ColorFilter()
    {
    }

    private static native long nativeGetFinalizer();

    long createNativeInstance()
    {
        return 0L;
    }

    void discardNativeInstance()
    {
        if(mNativeInstance != 0L)
        {
            mCleaner.run();
            mCleaner = null;
            mNativeInstance = 0L;
        }
    }

    public long getNativeInstance()
    {
        if(mNativeInstance == 0L)
        {
            mNativeInstance = createNativeInstance();
            if(mNativeInstance != 0L)
                mCleaner = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativeInstance);
        }
        return mNativeInstance;
    }

    private Runnable mCleaner;
    private long mNativeInstance;
}
