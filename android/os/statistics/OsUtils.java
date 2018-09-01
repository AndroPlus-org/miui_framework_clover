// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Process;
import android.view.ThreadedRenderer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import miui.util.ReflectionUtils;

class OsUtils
{

    OsUtils()
    {
    }

    public static int decodeThreadSchedulePolicy(int i)
    {
        if(i >= 0)
            i &= 0xbfffffff;
        return i;
    }

    public static int getSchedGroup(int i)
    {
        try
        {
            i = Process.getProcessGroup(i);
        }
        catch(Exception exception)
        {
            return 0x7fffffff;
        }
        return i;
    }

    public static native void getThreadSchedStat(long al[]);

    public static int getThreadSchedulePolicy(int i)
    {
        try
        {
            i = decodeThreadSchedulePolicy(getThreadScheduler(i));
        }
        catch(Exception exception)
        {
            return 0;
        }
        return i;
    }

    private static final native int getThreadScheduler(int i)
        throws IllegalArgumentException;

    public static boolean isHighPriority(int i, int j)
    {
        boolean flag = true;
        if(i == -1)
            return false;
        if(i == 2 || i == 1)
            return true;
        if(i == 0)
        {
            if(j > -2)
                flag = false;
            return flag;
        } else
        {
            return false;
        }
    }

    public static boolean isHighSchedGroup(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == -1) goto _L2; else goto _L1
_L1:
        if(i != 5) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 1)
        {
            flag1 = flag;
            if(i != 9)
            {
                flag1 = flag;
                if(i != 10)
                    flag1 = false;
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isLowPriority(int i, int j)
    {
        boolean flag = true;
        if(i == -1)
            return false;
        if(i == 2 || i == 1)
            return false;
        if(i == 0)
        {
            if(j < 10)
                flag = false;
            return flag;
        } else
        {
            return true;
        }
    }

    public static boolean isRenderThread(int i)
    {
        if(!sDoneFindIsRenderThreadMethod)
        {
            sIsRenderThreadMethod = ReflectionUtils.tryFindMethodBestMatch(android/view/ThreadedRenderer, "isRenderThread", new Class[] {
                java/lang/Integer
            });
            sDoneFindIsRenderThreadMethod = true;
        }
        if(sIsRenderThreadMethod != null)
        {
            boolean flag;
            try
            {
                flag = ((Boolean)sIsRenderThreadMethod.invoke(null, new Object[] {
                    Integer.valueOf(i)
                })).booleanValue();
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                invocationtargetexception.printStackTrace();
                return false;
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                illegalaccessexception.printStackTrace();
                return false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public static native void setThreadPriorityUnconditonally(int i, int j);

    public static final int SCHED_GROUP_UNKNOWN = 0x7fffffff;
    private static boolean sDoneFindIsRenderThreadMethod = false;
    private static Method sIsRenderThreadMethod;

}
