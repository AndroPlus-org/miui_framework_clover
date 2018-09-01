// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import android.os.Handler;
import com.android.internal.util.Preconditions;
import java.util.HashSet;
import java.util.Set;

public class TaskDrainer
{
    public static interface DrainListener
    {

        public abstract void onDrained();
    }


    static DrainListener _2D_get0(TaskDrainer taskdrainer)
    {
        return taskdrainer.mListener;
    }

    public TaskDrainer(Handler handler, DrainListener drainlistener)
    {
        DEBUG = false;
        mTaskSet = new HashSet();
        mEarlyFinishedTaskSet = new HashSet();
        mLock = new Object();
        mDraining = false;
        mDrainFinished = false;
        mHandler = (Handler)Preconditions.checkNotNull(handler, "handler must not be null");
        mListener = (DrainListener)Preconditions.checkNotNull(drainlistener, "listener must not be null");
        mName = null;
    }

    public TaskDrainer(Handler handler, DrainListener drainlistener, String s)
    {
        DEBUG = false;
        mTaskSet = new HashSet();
        mEarlyFinishedTaskSet = new HashSet();
        mLock = new Object();
        mDraining = false;
        mDrainFinished = false;
        mHandler = (Handler)Preconditions.checkNotNull(handler, "handler must not be null");
        mListener = (DrainListener)Preconditions.checkNotNull(drainlistener, "listener must not be null");
        mName = s;
    }

    private void checkIfDrainFinished()
    {
        if(mTaskSet.isEmpty() && mDraining && mDrainFinished ^ true)
        {
            mDrainFinished = true;
            postDrained();
        }
    }

    private void postDrained()
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                TaskDrainer._2D_get0(TaskDrainer.this).onDrained();
            }

            final TaskDrainer this$0;

            
            {
                this$0 = TaskDrainer.this;
                super();
            }
        }
);
    }

    public void beginDrain()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(!mDraining)
        {
            mDraining = true;
            checkIfDrainFinished();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void taskFinished(Object obj)
    {
        synchronized(mLock)
        {
            if(!mTaskSet.remove(obj) && !mEarlyFinishedTaskSet.add(obj))
            {
                IllegalStateException illegalstateexception = JVM INSTR new #102 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #104 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append("Task ").append(obj).append(" was already finished").toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_77;
        }
        checkIfDrainFinished();
        obj1;
        JVM INSTR monitorexit ;
    }

    public void taskStarted(Object obj)
    {
        synchronized(mLock)
        {
            if(mDraining)
            {
                obj = JVM INSTR new #102 <Class IllegalStateException>;
                ((IllegalStateException) (obj)).IllegalStateException("Can't start more tasks after draining has begun");
                throw obj;
            }
            break MISSING_BLOCK_LABEL_31;
        }
        if(!mEarlyFinishedTaskSet.remove(obj) && !mTaskSet.add(obj))
        {
            IllegalStateException illegalstateexception = JVM INSTR new #102 <Class IllegalStateException>;
            StringBuilder stringbuilder = JVM INSTR new #104 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            illegalstateexception.IllegalStateException(stringbuilder.append("Task ").append(obj).append(" was already started").toString());
            throw illegalstateexception;
        }
        obj1;
        JVM INSTR monitorexit ;
    }

    private static final String TAG = "TaskDrainer";
    private final boolean DEBUG;
    private boolean mDrainFinished;
    private boolean mDraining;
    private final Set mEarlyFinishedTaskSet;
    private final Handler mHandler;
    private final DrainListener mListener;
    private final Object mLock;
    private final String mName;
    private final Set mTaskSet;
}
