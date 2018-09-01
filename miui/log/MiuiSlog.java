// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.util.Slog;

// Referenced classes of package miui.log:
//            Tags, MiuiTag

public final class MiuiSlog
{

    public MiuiSlog()
    {
    }

    public static void d(int j, Object obj, String s)
    {
        d(Tags.getMiuiTag(j), obj, s);
    }

    public static void d(int j, Object obj, String s, Throwable throwable)
    {
        d(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void d(String s, String s1)
    {
        Slog.d(s, s1);
    }

    public static void d(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.d(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Slog.d(miuitag.name, s);
    }

    public static void d(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.d(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Slog.d(miuitag.name, s, throwable);
    }

    public static void e(int j, Object obj, String s)
    {
        e(Tags.getMiuiTag(j), obj, s);
    }

    public static void e(int j, Object obj, String s, Throwable throwable)
    {
        e(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void e(String s, String s1)
    {
        Slog.e(s, s1);
    }

    public static void e(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.e(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Slog.e(miuitag.name, s);
    }

    public static void e(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.e(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Slog.e(miuitag.name, s, throwable);
    }

    public static void i(int j, Object obj, String s)
    {
        i(Tags.getMiuiTag(j), obj, s);
    }

    public static void i(int j, Object obj, String s, Throwable throwable)
    {
        i(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void i(String s, String s1)
    {
        Slog.i(s, s1);
    }

    public static void i(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.i(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Slog.i(miuitag.name, s);
    }

    public static void i(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.i(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Slog.i(miuitag.name, s, throwable);
    }

    public static void println(int j, Object obj, int k, String s)
    {
        println(j, obj, Tags.getMiuiTag(k), s);
    }

    public static void println(int j, Object obj, MiuiTag miuitag, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.println(j, miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Slog.println(j, miuitag.name, s);
    }

    public static void v(int j, Object obj, String s)
    {
        v(Tags.getMiuiTag(j), obj, s);
    }

    public static void v(int j, Object obj, String s, Throwable throwable)
    {
        v(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void v(String s, String s1)
    {
        Slog.v(s, s1);
    }

    public static void v(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.v(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Slog.v(miuitag.name, s);
    }

    public static void v(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.v(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Slog.v(miuitag.name, s, throwable);
    }

    public static void w(int j, Object obj, String s)
    {
        w(Tags.getMiuiTag(j), obj, s);
    }

    public static void w(int j, Object obj, String s, Throwable throwable)
    {
        w(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void w(int j, Object obj, Throwable throwable)
    {
        w(Tags.getMiuiTag(j), obj, throwable);
    }

    public static void w(String s, String s1)
    {
        Slog.w(s, s1);
    }

    public static void w(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.w(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Slog.w(miuitag.name, s);
    }

    public static void w(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.w(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Slog.w(miuitag.name, s, throwable);
    }

    public static void w(MiuiTag miuitag, Object obj, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.w(miuitag.name, String.format("%s", new Object[] {
                    obj.toString()
                }), throwable);
            else
                Slog.w(miuitag.name, throwable);
    }

    public static void wtf(int j, Object obj, String s)
    {
        wtf(Tags.getMiuiTag(j), obj, s);
    }

    public static void wtf(int j, Object obj, String s, Throwable throwable)
    {
        wtf(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void wtf(int j, Object obj, Throwable throwable)
    {
        wtf(Tags.getMiuiTag(j), obj, throwable);
    }

    public static void wtf(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.wtf(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Slog.wtf(miuitag.name, s);
    }

    public static void wtf(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.wtf(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Slog.wtf(miuitag.name, s, throwable);
    }

    public static void wtf(MiuiTag miuitag, Object obj, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.wtf(miuitag.name, String.format("%s", new Object[] {
                    obj.toString()
                }), throwable);
            else
                Slog.wtf(miuitag.name, throwable);
    }

    public static void wtfStack(int j, Object obj, String s)
    {
        wtfStack(Tags.getMiuiTag(j), obj, s);
    }

    public static void wtfStack(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Slog.wtfStack(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Slog.wtfStack(miuitag.name, s);
    }
}
