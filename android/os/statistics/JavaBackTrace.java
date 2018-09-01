// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;


class JavaBackTrace
{

    JavaBackTrace()
    {
    }

    private static native StackTraceElement[] nativeResolve(Object obj);

    private static native Class[] nativeResolveClasses(Object obj);

    public static StackTraceElement[] resolve(Object obj)
    {
        if(obj == null)
            return null;
        else
            return nativeResolve(obj);
    }

    public static Class[] resolveClasses(Object obj)
    {
        if(obj == null)
            return null;
        else
            return nativeResolveClasses(obj);
    }
}
