// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;


// Referenced classes of package android.os.statistics:
//            StackUtils

class NativeBackTrace
{

    public NativeBackTrace(long l)
    {
        mNativePtr = l;
    }

    private static native void nativeDispose(long l);

    private static native String[] nativeResolve(long l);

    private static native int nativeUpdateBacktraceMap();

    public static String[] resolve(NativeBackTrace nativebacktrace)
    {
        if(nativebacktrace == null)
            return null;
        else
            return nativebacktrace.resolve();
    }

    public static int updateBacktraceMap()
    {
        return nativeUpdateBacktraceMap();
    }

    public void dispose()
    {
        if(mNativePtr != 0L)
        {
            nativeDispose(mNativePtr);
            mNativePtr = 0L;
        }
    }

    protected void finalize()
        throws Throwable
    {
        dispose();
        super.finalize();
    }

    public String[] resolve()
    {
        if(mNativePtr == 0L)
            return StackUtils.emptyStack;
        else
            return nativeResolve(mNativePtr);
    }

    private long mNativePtr;
}
