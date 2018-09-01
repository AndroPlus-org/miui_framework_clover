// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.job;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

// Referenced classes of package android.app.job:
//            JobServiceEngine, JobParameters

public abstract class JobService extends Service
{

    public JobService()
    {
    }

    public final void jobFinished(JobParameters jobparameters, boolean flag)
    {
        mEngine.jobFinished(jobparameters, flag);
    }

    public final IBinder onBind(Intent intent)
    {
        if(mEngine == null)
            mEngine = new JobServiceEngine(this) {

                public boolean onStartJob(JobParameters jobparameters)
                {
                    return JobService.this.onStartJob(jobparameters);
                }

                public boolean onStopJob(JobParameters jobparameters)
                {
                    return JobService.this.onStopJob(jobparameters);
                }

                final JobService this$0;

            
            {
                this$0 = JobService.this;
                super(service);
            }
            }
;
        return mEngine.getBinder();
    }

    public abstract boolean onStartJob(JobParameters jobparameters);

    public abstract boolean onStopJob(JobParameters jobparameters);

    public static final String PERMISSION_BIND = "android.permission.BIND_JOB_SERVICE";
    private static final String TAG = "JobService";
    private JobServiceEngine mEngine;
}
