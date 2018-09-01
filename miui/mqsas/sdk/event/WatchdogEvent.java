// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package miui.mqsas.sdk.event:
//            ExceptionEvent

public class WatchdogEvent extends ExceptionEvent
    implements Parcelable
{

    public WatchdogEvent()
    {
    }

    private WatchdogEvent(Parcel parcel)
    {
        boolean flag = true;
        super();
        mType = parcel.readInt();
        mPid = parcel.readInt();
        mProcessName = parcel.readString();
        mPackageName = parcel.readString();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsSystem = flag1;
        mTimeStamp = parcel.readLong();
        mSummary = parcel.readString();
        mDetails = parcel.readString();
        mDigest = parcel.readString();
        mLogName = parcel.readString();
        mKeyWord = parcel.readString();
        mCheckers = parcel.readStringArray();
        mStackTraces = parcel.readStringArray();
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        mIsUpload = flag1;
    }

    WatchdogEvent(Parcel parcel, WatchdogEvent watchdogevent)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String[] getCheckers()
    {
        return mCheckers;
    }

    public String[] getStackTraces()
    {
        return mStackTraces;
    }

    public void initType()
    {
        mType = 2;
    }

    public void setCheckers(String as[])
    {
        mCheckers = as;
    }

    public void setStackTraces(String as[])
    {
        mStackTraces = as;
    }

    public String toShortString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Watchdog { mType=").append(mType).append(" Pid=").append(mPid).append(" processName=").append(mProcessName).append(" mTimeStamp=").append(mTimeStamp).append(" mDigest=").append(mDigest).append(" mSummary=").append(mSummary).append(" mLogName=").append(mLogName).append(" mKeyWord=").append(mKeyWord);
        return stringbuilder.toString();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Watchdog { mType=").append(mType).append(" Pid=").append(mPid).append(" processName=").append(mProcessName).append(" mTimeStamp=").append(mTimeStamp).append(" mSummary=").append(mSummary).append(" mDetails=").append(mDetails).append(" mDigest=").append(mDigest).append(" mLogName=").append(mLogName).append(" mKeyWord=").append(mKeyWord);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mType);
        parcel.writeInt(mPid);
        parcel.writeString(mProcessName);
        parcel.writeString(mPackageName);
        if(mIsSystem)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeLong(mTimeStamp);
        parcel.writeString(mSummary);
        parcel.writeString(mDetails);
        parcel.writeString(mDigest);
        parcel.writeString(mLogName);
        parcel.writeString(mKeyWord);
        parcel.writeStringArray(mCheckers);
        parcel.writeStringArray(mStackTraces);
        if(mIsUpload)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WatchdogEvent createFromParcel(Parcel parcel)
        {
            return new WatchdogEvent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public WatchdogEvent[] newArray(int i)
        {
            return new WatchdogEvent[i];
        }

    }
;
    private String mCheckers[];
    private String mStackTraces[];

}
