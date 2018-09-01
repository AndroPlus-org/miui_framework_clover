// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import android.os.Handler;

// Referenced classes of package android.hardware.camera2.utils:
//            TaskDrainer

public class TaskSingleDrainer
{

    public TaskSingleDrainer(Handler handler, TaskDrainer.DrainListener drainlistener)
    {
        mSingleTask = new Object();
        mTaskDrainer = new TaskDrainer(handler, drainlistener);
    }

    public TaskSingleDrainer(Handler handler, TaskDrainer.DrainListener drainlistener, String s)
    {
        mSingleTask = new Object();
        mTaskDrainer = new TaskDrainer(handler, drainlistener, s);
    }

    public void beginDrain()
    {
        mTaskDrainer.beginDrain();
    }

    public void taskFinished()
    {
        mTaskDrainer.taskFinished(mSingleTask);
    }

    public void taskStarted()
    {
        mTaskDrainer.taskStarted(mSingleTask);
    }

    private final Object mSingleTask;
    private final TaskDrainer mTaskDrainer;
}
