// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.os.Process;

public class MiuiProcessUtil
{

    public MiuiProcessUtil()
    {
    }

    public static long getFreeMemory()
    {
        return Process.getFreeMemory();
    }

    public static long getTotalMemory()
    {
        return Process.getTotalMemory();
    }
}
