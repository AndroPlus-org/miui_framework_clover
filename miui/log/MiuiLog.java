// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.util.Log;

// Referenced classes of package miui.log:
//            Tags, MiuiTag

public final class MiuiLog
{

    public MiuiLog()
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

    public static void d(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.d(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Log.d(miuitag.name, s);
    }

    public static void d(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.d(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Log.d(miuitag.name, s, throwable);
    }

    public static void e(int j, Object obj, String s)
    {
        e(Tags.getMiuiTag(j), obj, s);
    }

    public static void e(int j, Object obj, String s, Throwable throwable)
    {
        e(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void e(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.e(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Log.e(miuitag.name, s);
    }

    public static void e(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.e(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Log.e(miuitag.name, s, throwable);
    }

    public static void i(int j, Object obj, String s)
    {
        i(Tags.getMiuiTag(j), obj, s);
    }

    public static void i(int j, Object obj, String s, Throwable throwable)
    {
        i(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void i(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.i(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Log.i(miuitag.name, s);
    }

    public static void i(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.i(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Log.i(miuitag.name, s, throwable);
    }

    public static boolean isLoggable(MiuiTag miuitag, int j)
    {
        if(!miuitag.isOn())
            return false;
        else
            return Log.isLoggable(miuitag.name, j);
    }

    public static void println(int j, Object obj, int k, String s)
    {
        println(j, obj, Tags.getMiuiTag(k), s);
    }

    public static void println(int j, Object obj, MiuiTag miuitag, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.println(j, miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Log.println(j, miuitag.name, s);
    }

    public static void v(int j, Object obj, String s)
    {
        v(Tags.getMiuiTag(j), obj, s);
    }

    public static void v(int j, Object obj, String s, Throwable throwable)
    {
        v(Tags.getMiuiTag(j), obj, s, throwable);
    }

    public static void v(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.v(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Log.v(miuitag.name, s);
    }

    public static void v(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.v(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Log.v(miuitag.name, s, throwable);
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

    public static void w(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.w(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Log.w(miuitag.name, s);
    }

    public static void w(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.w(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Log.w(miuitag.name, s, throwable);
    }

    public static void w(MiuiTag miuitag, Object obj, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.w(miuitag.name, String.format("%s", new Object[] {
                    obj.toString()
                }), throwable);
            else
                Log.w(miuitag.name, throwable);
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
                Log.wtf(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Log.wtf(miuitag.name, s);
    }

    public static void wtf(MiuiTag miuitag, Object obj, String s, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.wtf(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }), throwable);
            else
                Log.wtf(miuitag.name, s, throwable);
    }

    public static void wtf(MiuiTag miuitag, Object obj, Throwable throwable)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.wtf(miuitag.name, String.format("%s", new Object[] {
                    obj.toString()
                }), throwable);
            else
                Log.wtf(miuitag.name, throwable);
    }

    public static void wtfStack(int j, Object obj, String s)
    {
        wtfStack(Tags.getMiuiTag(j), obj, s);
    }

    public static void wtfStack(MiuiTag miuitag, Object obj, String s)
    {
        if(miuitag.isOn())
            if(obj != null)
                Log.wtfStack(miuitag.name, String.format("[%s] %s", new Object[] {
                    obj.toString(), s
                }));
            else
                Log.wtfStack(miuitag.name, s);
    }
}
