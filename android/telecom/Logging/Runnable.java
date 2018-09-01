// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom.Logging;

import android.telecom.Log;

// Referenced classes of package android.telecom.Logging:
//            Session

public abstract class Runnable
{

    static Object _2D_get0(Runnable runnable)
    {
        return runnable.mLock;
    }

    static Session _2D_get1(Runnable runnable)
    {
        return runnable.mSubsession;
    }

    static String _2D_get2(Runnable runnable)
    {
        return runnable.mSubsessionName;
    }

    static Session _2D_set0(Runnable runnable, Session session)
    {
        runnable.mSubsession = session;
        return session;
    }

    public Runnable(String s, Object obj)
    {
        if(obj == null)
            mLock = new Object();
        else
            mLock = obj;
        mSubsessionName = s;
    }

    public void cancel()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Log.cancelSubsession(mSubsession);
        mSubsession = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final Runnable getRunnableToCancel()
    {
        return mRunnable;
    }

    public abstract void loggedRun();

    public Runnable prepare()
    {
        cancel();
        mSubsession = Log.createSubsession();
        return mRunnable;
    }

    private final Object mLock;
    private final Runnable mRunnable = new Runnable() {

        public void run()
        {
            Object obj1 = Runnable._2D_get0(Runnable.this);
            obj1;
            JVM INSTR monitorenter ;
            Log.continueSession(Runnable._2D_get1(Runnable.this), Runnable._2D_get2(Runnable.this));
            loggedRun();
            if(Runnable._2D_get1(Runnable.this) != null)
            {
                Log.endSession();
                Runnable._2D_set0(Runnable.this, null);
            }
            obj1;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            if(Runnable._2D_get1(Runnable.this) != null)
            {
                Log.endSession();
                Runnable._2D_set0(Runnable.this, null);
            }
            throw exception;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final Runnable this$0;

            
            {
                this$0 = Runnable.this;
                super();
            }
    }
;
    private Session mSubsession;
    private final String mSubsessionName;
}
