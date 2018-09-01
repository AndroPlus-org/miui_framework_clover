// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.job;

import android.content.ClipData;
import android.net.Uri;
import android.os.*;

// Referenced classes of package android.app.job:
//            JobWorkItem, IJobCallback

public class JobParameters
    implements Parcelable
{

    public JobParameters(IBinder ibinder, int i, PersistableBundle persistablebundle, Bundle bundle, ClipData clipdata, int j, boolean flag, 
            Uri auri[], String as[])
    {
        jobId = i;
        extras = persistablebundle;
        transientExtras = bundle;
        clipData = clipdata;
        clipGrantFlags = j;
        callback = ibinder;
        overrideDeadlineExpired = flag;
        mTriggeredContentUris = auri;
        mTriggeredContentAuthorities = as;
    }

    private JobParameters(Parcel parcel)
    {
        jobId = parcel.readInt();
        extras = parcel.readPersistableBundle();
        transientExtras = parcel.readBundle();
        boolean flag;
        if(parcel.readInt() != 0)
        {
            clipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel);
            clipGrantFlags = parcel.readInt();
        } else
        {
            clipData = null;
            clipGrantFlags = 0;
        }
        callback = parcel.readStrongBinder();
        if(parcel.readInt() == 1)
            flag = true;
        else
            flag = false;
        overrideDeadlineExpired = flag;
        mTriggeredContentUris = (Uri[])parcel.createTypedArray(Uri.CREATOR);
        mTriggeredContentAuthorities = parcel.createStringArray();
        stopReason = parcel.readInt();
    }

    JobParameters(Parcel parcel, JobParameters jobparameters)
    {
        this(parcel);
    }

    public static String getReasonName(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("unknown:").append(i).toString();

        case 0: // '\0'
            return "canceled";

        case 1: // '\001'
            return "constraints";

        case 2: // '\002'
            return "preempt";

        case 3: // '\003'
            return "timeout";

        case 4: // '\004'
            return "device_idle";
        }
    }

    public void completeWork(JobWorkItem jobworkitem)
    {
        try
        {
            if(!getCallback().completeWork(getJobId(), jobworkitem.getWorkId()))
            {
                IllegalArgumentException illegalargumentexception = JVM INSTR new #163 <Class IllegalArgumentException>;
                StringBuilder stringbuilder = JVM INSTR new #116 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalargumentexception.IllegalArgumentException(stringbuilder.append("Given work is not active: ").append(jobworkitem).toString());
                throw illegalargumentexception;
            }
        }
        // Misplaced declaration of an exception variable
        catch(JobWorkItem jobworkitem)
        {
            throw jobworkitem.rethrowFromSystemServer();
        }
    }

    public JobWorkItem dequeueWork()
    {
        JobWorkItem jobworkitem;
        try
        {
            jobworkitem = getCallback().dequeueWork(getJobId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return jobworkitem;
    }

    public int describeContents()
    {
        return 0;
    }

    public IJobCallback getCallback()
    {
        return IJobCallback.Stub.asInterface(callback);
    }

    public ClipData getClipData()
    {
        return clipData;
    }

    public int getClipGrantFlags()
    {
        return clipGrantFlags;
    }

    public PersistableBundle getExtras()
    {
        return extras;
    }

    public int getJobId()
    {
        return jobId;
    }

    public int getStopReason()
    {
        return stopReason;
    }

    public Bundle getTransientExtras()
    {
        return transientExtras;
    }

    public String[] getTriggeredContentAuthorities()
    {
        return mTriggeredContentAuthorities;
    }

    public Uri[] getTriggeredContentUris()
    {
        return mTriggeredContentUris;
    }

    public boolean isOverrideDeadlineExpired()
    {
        return overrideDeadlineExpired;
    }

    public void setStopReason(int i)
    {
        stopReason = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = 1;
        parcel.writeInt(jobId);
        parcel.writePersistableBundle(extras);
        parcel.writeBundle(transientExtras);
        if(clipData != null)
        {
            parcel.writeInt(1);
            clipData.writeToParcel(parcel, i);
            parcel.writeInt(clipGrantFlags);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeStrongBinder(callback);
        if(!overrideDeadlineExpired)
            j = 0;
        parcel.writeInt(j);
        parcel.writeTypedArray(mTriggeredContentUris, i);
        parcel.writeStringArray(mTriggeredContentAuthorities);
        parcel.writeInt(stopReason);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public JobParameters createFromParcel(Parcel parcel)
        {
            return new JobParameters(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public JobParameters[] newArray(int i)
        {
            return new JobParameters[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int REASON_CANCELED = 0;
    public static final int REASON_CONSTRAINTS_NOT_SATISFIED = 1;
    public static final int REASON_DEVICE_IDLE = 4;
    public static final int REASON_PREEMPT = 2;
    public static final int REASON_TIMEOUT = 3;
    private final IBinder callback;
    private final ClipData clipData;
    private final int clipGrantFlags;
    private final PersistableBundle extras;
    private final int jobId;
    private final String mTriggeredContentAuthorities[];
    private final Uri mTriggeredContentUris[];
    private final boolean overrideDeadlineExpired;
    private int stopReason;
    private final Bundle transientExtras;

}
