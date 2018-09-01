// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.app.ActivityThreadInjector;
import android.os.Parcel;

// Referenced classes of package android.os.statistics:
//            MicroscopicEvent, JniParcel, OsUtils, PerfSuperviser, 
//            PerfSupervisionSettings, NativeBackTrace

public final class LooperOnce extends MicroscopicEvent
{

    public LooperOnce()
    {
        super(11, "LooperOnce");
        eventFlags = 1;
    }

    void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
    {
        threadId = (int)jniparcel.readLong();
        beginUptimeMillis = jniparcel.readLong();
        endUptimeMillis = jniparcel.readLong();
        schedPolicy = OsUtils.decodeThreadSchedulePolicy((int)jniparcel.readLong());
        schedPriority = (int)jniparcel.readLong();
    }

    public boolean hasMultiplePeerBlockingEvents()
    {
        return false;
    }

    public boolean hasPeerBlockingEvent()
    {
        return false;
    }

    public boolean isBlockedBy(MicroscopicEvent microscopicevent)
    {
        return false;
    }

    public boolean isBlockedBySameProcess()
    {
        return false;
    }

    public boolean isBlockingMultiplePeer()
    {
        return false;
    }

    public boolean isBlockingSameProcess()
    {
        return false;
    }

    boolean isConcerned()
    {
        long l;
        boolean flag;
        if(threadId == PerfSuperviser.MY_PID || OsUtils.isRenderThread(threadId) || ActivityThreadInjector.isSystemThread() && OsUtils.isHighPriority(schedPolicy, schedPriority))
            l = PerfSupervisionSettings.sPerfSupervisionSoftThreshold;
        else
            l = Math.min(300, PerfSupervisionSettings.sPerfSupervisionHardThreshold);
        if(endUptimeMillis - beginUptimeMillis >= l)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPeerBlockingEvent()
    {
        return false;
    }

    public boolean isRootEvent()
    {
        return true;
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LooperOnce createFromParcel(Parcel parcel)
        {
            LooperOnce looperonce = new LooperOnce();
            looperonce.readFromParcel(parcel);
            return looperonce;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LooperOnce[] newArray(int i)
        {
            return new LooperOnce[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;

}
