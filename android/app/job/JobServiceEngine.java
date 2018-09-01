// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.job;

import android.app.Service;
import android.os.*;
import android.util.Log;
import java.lang.ref.WeakReference;

// Referenced classes of package android.app.job:
//            IJobService, JobParameters, IJobCallback

public abstract class JobServiceEngine
{
    class JobHandler extends Handler
    {

        private void ackStartMessage(JobParameters jobparameters, boolean flag)
        {
            IJobCallback ijobcallback;
            int i;
            ijobcallback = jobparameters.getCallback();
            i = jobparameters.getJobId();
            if(ijobcallback == null)
                break MISSING_BLOCK_LABEL_37;
            ijobcallback.acknowledgeStartMessage(i, flag);
_L1:
            return;
            jobparameters;
            Log.e("JobServiceEngine", "System unreachable for starting job.");
              goto _L1
            if(Log.isLoggable("JobServiceEngine", 3))
                Log.d("JobServiceEngine", "Attempting to ack a job that has already been processed.");
              goto _L1
        }

        private void ackStopMessage(JobParameters jobparameters, boolean flag)
        {
            IJobCallback ijobcallback;
            int i;
            ijobcallback = jobparameters.getCallback();
            i = jobparameters.getJobId();
            if(ijobcallback == null)
                break MISSING_BLOCK_LABEL_37;
            ijobcallback.acknowledgeStopMessage(i, flag);
_L1:
            return;
            jobparameters;
            Log.e("JobServiceEngine", "System unreachable for stopping job.");
              goto _L1
            if(Log.isLoggable("JobServiceEngine", 3))
                Log.d("JobServiceEngine", "Attempting to ack a job that has already been processed.");
              goto _L1
        }

        public void handleMessage(Message message)
        {
            JobParameters jobparameters = (JobParameters)message.obj;
            message.what;
            JVM INSTR tableswitch 0 2: default 40
        //                       0 49
        //                       1 103
        //                       2 138;
               goto _L1 _L2 _L3 _L4
_L1:
            Log.e("JobServiceEngine", "Unrecognised message received.");
_L6:
            return;
_L2:
            try
            {
                ackStartMessage(jobparameters, onStartJob(jobparameters));
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("JobServiceEngine", (new StringBuilder()).append("Error while executing job: ").append(jobparameters.getJobId()).toString());
                throw new RuntimeException(message);
            }
            continue; /* Loop/switch isn't completed */
_L3:
            try
            {
                ackStopMessage(jobparameters, onStopJob(jobparameters));
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("JobServiceEngine", "Application unable to handle onStopJob.", message);
                throw new RuntimeException(message);
            }
            continue; /* Loop/switch isn't completed */
_L4:
            boolean flag;
            if(message.arg2 == 1)
                flag = true;
            else
                flag = false;
            message = jobparameters.getCallback();
            if(message != null)
                try
                {
                    message.jobFinished(jobparameters.getJobId(), flag);
                }
                // Misplaced declaration of an exception variable
                catch(Message message)
                {
                    Log.e("JobServiceEngine", "Error reporting job finish to system: binder has goneaway.");
                }
            else
                Log.e("JobServiceEngine", "finishJob() called for a nonexistent job id.");
            if(true) goto _L6; else goto _L5
_L5:
        }

        final JobServiceEngine this$0;

        JobHandler(Looper looper)
        {
            this$0 = JobServiceEngine.this;
            super(looper);
        }
    }

    static final class JobInterface extends IJobService.Stub
    {

        public void startJob(JobParameters jobparameters)
            throws RemoteException
        {
            JobServiceEngine jobserviceengine = (JobServiceEngine)mService.get();
            if(jobserviceengine != null)
                Message.obtain(jobserviceengine.mHandler, 0, jobparameters).sendToTarget();
        }

        public void stopJob(JobParameters jobparameters)
            throws RemoteException
        {
            JobServiceEngine jobserviceengine = (JobServiceEngine)mService.get();
            if(jobserviceengine != null)
                Message.obtain(jobserviceengine.mHandler, 1, jobparameters).sendToTarget();
        }

        final WeakReference mService;

        JobInterface(JobServiceEngine jobserviceengine)
        {
            mService = new WeakReference(jobserviceengine);
        }
    }


    public JobServiceEngine(Service service)
    {
        mHandler = new JobHandler(service.getMainLooper());
    }

    public final IBinder getBinder()
    {
        return mBinder.asBinder();
    }

    public void jobFinished(JobParameters jobparameters, boolean flag)
    {
        if(jobparameters == null)
            throw new NullPointerException("params");
        jobparameters = Message.obtain(mHandler, 2, jobparameters);
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        jobparameters.arg2 = i;
        jobparameters.sendToTarget();
    }

    public abstract boolean onStartJob(JobParameters jobparameters);

    public abstract boolean onStopJob(JobParameters jobparameters);

    private static final int MSG_EXECUTE_JOB = 0;
    private static final int MSG_JOB_FINISHED = 2;
    private static final int MSG_STOP_JOB = 1;
    private static final String TAG = "JobServiceEngine";
    private final IJobService mBinder = new JobInterface(this);
    JobHandler mHandler;
}
