// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.job;

import java.util.List;

// Referenced classes of package android.app.job:
//            JobInfo, JobWorkItem

public abstract class JobScheduler
{

    public JobScheduler()
    {
    }

    public abstract void cancel(int i);

    public abstract void cancelAll();

    public abstract int enqueue(JobInfo jobinfo, JobWorkItem jobworkitem);

    public abstract List getAllPendingJobs();

    public abstract JobInfo getPendingJob(int i);

    public abstract int schedule(JobInfo jobinfo);

    public abstract int scheduleAsPackage(JobInfo jobinfo, String s, int i, String s1);

    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
}
