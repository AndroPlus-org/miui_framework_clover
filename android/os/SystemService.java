// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import com.google.android.collect.Maps;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

// Referenced classes of package android.os:
//            SystemProperties, SystemClock

public class SystemService
{
    public static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(android/os/SystemService$State, s);
        }

        public static State[] values()
        {
            return $VALUES;
        }

        private static final State $VALUES[];
        public static final State RESTARTING;
        public static final State RUNNING;
        public static final State STOPPED;
        public static final State STOPPING;

        static 
        {
            RUNNING = new State("RUNNING", 0, "running");
            STOPPING = new State("STOPPING", 1, "stopping");
            STOPPED = new State("STOPPED", 2, "stopped");
            RESTARTING = new State("RESTARTING", 3, "restarting");
            $VALUES = (new State[] {
                RUNNING, STOPPING, STOPPED, RESTARTING
            });
        }

        private State(String s, int i, String s1)
        {
            super(s, i);
            SystemService._2D_get1().put(s1, this);
        }
    }


    static Object _2D_get0()
    {
        return sPropertyLock;
    }

    static HashMap _2D_get1()
    {
        return sStates;
    }

    public SystemService()
    {
    }

    public static State getState(String s)
    {
        s = SystemProperties.get((new StringBuilder()).append("init.svc.").append(s).toString());
        s = (State)sStates.get(s);
        if(s != null)
            return s;
        else
            return State.STOPPED;
    }

    public static boolean isRunning(String s)
    {
        return State.RUNNING.equals(getState(s));
    }

    public static boolean isStopped(String s)
    {
        return State.STOPPED.equals(getState(s));
    }

    public static void restart(String s)
    {
        SystemProperties.set("ctl.restart", s);
    }

    public static void start(String s)
    {
        SystemProperties.set("ctl.start", s);
    }

    public static void stop(String s)
    {
        SystemProperties.set("ctl.stop", s);
    }

    public static transient void waitForAnyStopped(String as[])
    {
_L4:
        Object obj = sPropertyLock;
        obj;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = as.length;
_L2:
        String s;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        s = as[i];
        boolean flag = State.STOPPED.equals(getState(s));
        if(!flag)
            break MISSING_BLOCK_LABEL_42;
        obj;
        JVM INSTR monitorexit ;
        return;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        try
        {
            sPropertyLock.wait();
        }
        catch(InterruptedException interruptedexception) { }
        obj;
        JVM INSTR monitorexit ;
        if(true) goto _L4; else goto _L3
_L3:
        as;
        throw as;
    }

    public static void waitForState(String s, State state, long l)
        throws TimeoutException
    {
        long l1 = SystemClock.elapsedRealtime();
_L2:
        Object obj = sPropertyLock;
        obj;
        JVM INSTR monitorenter ;
        State state1;
        boolean flag;
        state1 = getState(s);
        flag = state.equals(state1);
        if(!flag)
            break MISSING_BLOCK_LABEL_36;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(SystemClock.elapsedRealtime() >= l1 + l)
        {
            TimeoutException timeoutexception = JVM INSTR new #105 <Class TimeoutException>;
            StringBuilder stringbuilder = JVM INSTR new #44  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            timeoutexception.TimeoutException(stringbuilder.append("Service ").append(s).append(" currently ").append(state1).append("; waited ").append(l).append("ms for ").append(state).toString());
            throw timeoutexception;
        }
        break MISSING_BLOCK_LABEL_118;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
        try
        {
            sPropertyLock.wait(l);
        }
        catch(InterruptedException interruptedexception) { }
        obj;
        JVM INSTR monitorexit ;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static Object sPropertyLock = new Object();
    private static HashMap sStates = Maps.newHashMap();

    static 
    {
        SystemProperties.addChangeCallback(new Runnable() {

            public void run()
            {
                Object obj = SystemService._2D_get0();
                obj;
                JVM INSTR monitorenter ;
                SystemService._2D_get0().notifyAll();
                obj;
                JVM INSTR monitorexit ;
                return;
                Exception exception;
                exception;
                throw exception;
            }

        }
);
    }
}
