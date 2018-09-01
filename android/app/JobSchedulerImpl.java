// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.app.job.IJobScheduler;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.os.RemoteException;
import java.util.List;

public class JobSchedulerImpl extends JobScheduler
{

    JobSchedulerImpl(IJobScheduler ijobscheduler)
    {
        mBinder = ijobscheduler;
    }

    public void cancel(int i)
    {
        mBinder.cancel(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void cancelAll()
    {
        mBinder.cancelAll();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int enqueue(JobInfo jobinfo, JobWorkItem jobworkitem)
    {
        int i;
        try
        {
            i = mBinder.enqueue(jobinfo, jobworkitem);
        }
        // Misplaced declaration of an exception variable
        catch(JobInfo jobinfo)
        {
            return 0;
        }
        return i;
    }

    public List getAllPendingJobs()
    {
        List list;
        try
        {
            list = mBinder.getAllPendingJobs();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return list;
    }

    public JobInfo getPendingJob(int i)
    {
        JobInfo jobinfo;
        try
        {
            jobinfo = mBinder.getPendingJob(i);
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return jobinfo;
    }

    public int schedule(JobInfo jobinfo)
    {
        int i;
        try
        {
            i = mBinder.schedule(jobinfo);
        }
        // Misplaced declaration of an exception variable
        catch(JobInfo jobinfo)
        {
            return 0;
        }
        return i;
    }

    public int scheduleAsPackage(JobInfo jobinfo, String s, int i, String s1)
    {
        try
        {
            i = mBinder.scheduleAsPackage(jobinfo, s, i, s1);
        }
        // Misplaced declaration of an exception variable
        catch(JobInfo jobinfo)
        {
            return 0;
        }
        return i;
    }

    IJobScheduler mBinder;
}
