// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemProperties
{

    public SystemProperties()
    {
    }

    public static void addChangeCallback(Runnable runnable)
    {
        ArrayList arraylist = sChangeCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        if(sChangeCallbacks.size() == 0)
            native_add_change_callback();
        sChangeCallbacks.add(runnable);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    static void callChangeCallbacks()
    {
        ArrayList arraylist = sChangeCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        int i = sChangeCallbacks.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_20;
        arraylist;
        JVM INSTR monitorexit ;
        return;
        ArrayList arraylist1;
        arraylist1 = JVM INSTR new #28  <Class ArrayList>;
        arraylist1.ArrayList(sChangeCallbacks);
        i = 0;
_L2:
        if(i >= arraylist1.size())
            break; /* Loop/switch isn't completed */
        ((Runnable)arraylist1.get(i)).run();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static String get(String s)
    {
        return native_get(s);
    }

    public static String get(String s, String s1)
    {
        return native_get(s, s1);
    }

    public static boolean getBoolean(String s, boolean flag)
    {
        return native_get_boolean(s, flag);
    }

    public static int getInt(String s, int i)
    {
        return native_get_int(s, i);
    }

    public static long getLong(String s, long l)
    {
        return native_get_long(s, l);
    }

    private static native void native_add_change_callback();

    private static native String native_get(String s);

    private static native String native_get(String s, String s1);

    private static native boolean native_get_boolean(String s, boolean flag);

    private static native int native_get_int(String s, int i);

    private static native long native_get_long(String s, long l);

    private static native void native_report_sysprop_change();

    private static native void native_set(String s, String s1);

    private static IllegalArgumentException newValueTooLargeException(String s, String s1)
    {
        return new IllegalArgumentException((new StringBuilder()).append("value of system property '").append(s).append("' is longer than ").append(91).append(" characters: ").append(s1).toString());
    }

    private static void onKeyAccess(String s)
    {
    }

    public static void reportSyspropChanged()
    {
        native_report_sysprop_change();
    }

    public static void set(String s, String s1)
    {
        if(s1 != null && s1.length() > 91)
        {
            throw newValueTooLargeException(s, s1);
        } else
        {
            native_set(s, s1);
            return;
        }
    }

    public static final int PROP_NAME_MAX = 0x7fffffff;
    public static final int PROP_VALUE_MAX = 91;
    private static final String TAG = "SystemProperties";
    private static final boolean TRACK_KEY_ACCESS = false;
    private static final ArrayList sChangeCallbacks = new ArrayList();
    private static final HashMap sRoReads = null;

}
