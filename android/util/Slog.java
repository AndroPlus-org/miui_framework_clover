// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


// Referenced classes of package android.util:
//            Log

public final class Slog
{

    private Slog()
    {
    }

    public static int d(String s, String s1)
    {
        return Log.println_native(3, 3, s, s1);
    }

    public static int d(String s, String s1, Throwable throwable)
    {
        return Log.println_native(3, 3, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int e(String s, String s1)
    {
        return Log.println_native(3, 6, s, s1);
    }

    public static int e(String s, String s1, Throwable throwable)
    {
        return Log.println_native(3, 6, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int i(String s, String s1)
    {
        return Log.println_native(3, 4, s, s1);
    }

    public static int i(String s, String s1, Throwable throwable)
    {
        return Log.println_native(3, 4, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int println(int j, String s, String s1)
    {
        return Log.println_native(3, j, s, s1);
    }

    public static int v(String s, String s1)
    {
        return Log.println_native(3, 2, s, s1);
    }

    public static int v(String s, String s1, Throwable throwable)
    {
        return Log.println_native(3, 2, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int w(String s, String s1)
    {
        return Log.println_native(3, 5, s, s1);
    }

    public static int w(String s, String s1, Throwable throwable)
    {
        return Log.println_native(3, 5, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int w(String s, Throwable throwable)
    {
        return Log.println_native(3, 5, s, Log.getStackTraceString(throwable));
    }

    public static int wtf(String s, String s1)
    {
        return Log.wtf(3, s, s1, null, false, true);
    }

    public static int wtf(String s, String s1, Throwable throwable)
    {
        return Log.wtf(3, s, s1, throwable, false, true);
    }

    public static int wtf(String s, Throwable throwable)
    {
        return Log.wtf(3, s, throwable.getMessage(), throwable, false, true);
    }

    public static void wtfQuiet(String s, String s1)
    {
        Log.wtfQuiet(3, s, s1, true);
    }

    public static int wtfStack(String s, String s1)
    {
        return Log.wtf(3, s, s1, null, true, true);
    }
}
