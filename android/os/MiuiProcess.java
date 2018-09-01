// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;

// Referenced classes of package android.os:
//            Process

public class MiuiProcess
{
    private static final class PriorityState
    {

        static int _2D_get0(PriorityState prioritystate)
        {
            return prioritystate.prevPriority;
        }

        static int _2D_get1(PriorityState prioritystate)
        {
            return prioritystate.regionCounter;
        }

        static int _2D_set0(PriorityState prioritystate, int i)
        {
            prioritystate.prevPriority = i;
            return i;
        }

        static int _2D_set1(PriorityState prioritystate, int i)
        {
            prioritystate.regionCounter = i;
            return i;
        }

        private int prevPriority;
        private int regionCounter;

        private PriorityState()
        {
            regionCounter = 0;
            prevPriority = 0x80000000;
        }

        PriorityState(PriorityState prioritystate)
        {
            this();
        }
    }


    public MiuiProcess()
    {
    }

    public static void boostPriorityForLockedSection()
    {
        int i = Process.myTid();
        int j = Process.getThreadPriority(i);
        Log.d("LockBoost", (new StringBuilder()).append("tid=").append(i).append(", prevPriority=").append(j).toString());
        PriorityState prioritystate = (PriorityState)sThreadPriorityState.get();
        if(PriorityState._2D_get1(prioritystate) == 0 && j > -2)
        {
            PriorityState._2D_set0(prioritystate, j);
            Process.setThreadPriority(i, -2);
            Log.d("LockBoost", (new StringBuilder()).append("thread tid=").append(i).append(" priority is boosted to -2").toString());
        }
        PriorityState._2D_set1(prioritystate, PriorityState._2D_get1(prioritystate) + 1);
    }

    public static void resetPriorityAfterLockedSection()
    {
        PriorityState prioritystate = (PriorityState)sThreadPriorityState.get();
        PriorityState._2D_set1(prioritystate, PriorityState._2D_get1(prioritystate) - 1);
        if(PriorityState._2D_get1(prioritystate) == 0 && PriorityState._2D_get0(prioritystate) > -2)
        {
            Process.setThreadPriority(Process.myTid(), PriorityState._2D_get0(prioritystate));
            Log.d("LockBoost", (new StringBuilder()).append("thread tid=").append(Process.myTid()).append(" priority is reset to ").append(PriorityState._2D_get0(prioritystate)).toString());
        }
    }

    public static void setThreadPriority(int i, int j, String s)
    {
        String s1;
        s1 = s;
        if(s == null)
            s1 = "MiuiProcess";
        Process.setThreadPriority(i, j);
        s = JVM INSTR new #59  <Class StringBuilder>;
        s.StringBuilder();
        Log.d(s1, s.append("thread tid=").append(i).append(", priority is set to ").append(j).toString());
_L1:
        return;
        s;
        Log.e(s1, (new StringBuilder()).append("thread tid=").append(i).append(", set priority error").toString());
        s.printStackTrace();
          goto _L1
    }

    public static void setThreadPriority(int i, String s)
    {
        setThreadPriority(Process.myTid(), i, s);
    }

    public static final int BACKUP_UID = 9800;
    public static final int FINDDEVICE_UID = 9810;
    public static final int SCHED_RESET_ON_FORK = 0x40000000;
    private static final String TAG = "LockBoost";
    public static final int THEME_UID = 9801;
    public static final int THREAD_GROUP_DEFAULT = -1;
    public static final int THREAD_GROUP_FG_LIMITED = 10;
    public static final int THREAD_GROUP_FG_SERVICE = 9;
    public static final int THREAD_GROUP_FOREGROUND = 1;
    public static final int THREAD_GROUP_TOP_APP = 5;
    public static final int UPDATER_UID = 9802;
    static ThreadLocal sThreadPriorityState = new ThreadLocal() {

        protected PriorityState initialValue()
        {
            return new PriorityState(null);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;

}
