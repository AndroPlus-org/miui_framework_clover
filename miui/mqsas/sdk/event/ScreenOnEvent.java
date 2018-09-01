// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;

public class ScreenOnEvent
    implements Parcelable
{

    public ScreenOnEvent()
    {
        mTimeoutSummary = "";
        mTimeOutDetail = "";
        mWakeSource = "";
        mTimeStamp = "";
        mTotalTime = -1L;
        mSetDisplayTime = -1L;
        mBlockScreenTime = -1L;
        mScreenOnType = "";
    }

    private ScreenOnEvent(Parcel parcel)
    {
        mTimeoutSummary = parcel.readString();
        mTimeOutDetail = parcel.readString();
        mWakeSource = parcel.readString();
        mTimeStamp = parcel.readString();
        mTotalTime = parcel.readLong();
        mSetDisplayTime = parcel.readLong();
        mBlockScreenTime = parcel.readLong();
        mScreenOnType = parcel.readString();
    }

    ScreenOnEvent(Parcel parcel, ScreenOnEvent screenonevent)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public long getBlockScreenTime()
    {
        return mBlockScreenTime;
    }

    public String getScreenOnType()
    {
        return mScreenOnType;
    }

    public long getSetDisplayTime()
    {
        return mSetDisplayTime;
    }

    public String getTimeStamp()
    {
        return mTimeStamp;
    }

    public String getTimeoutSummary()
    {
        return mTimeoutSummary;
    }

    public long getTotalTime()
    {
        return mTotalTime;
    }

    public String getWakeSource()
    {
        return mWakeSource;
    }

    public String getmTimeOutDetail()
    {
        return mTimeOutDetail;
    }

    public void setBlockScreenTime(long l)
    {
        mBlockScreenTime = l;
    }

    public void setScreenOnType(String s)
    {
        mScreenOnType = s;
    }

    public void setSetDisplayTime(long l)
    {
        mSetDisplayTime = l;
    }

    public void setTimeStamp(String s)
    {
        mTimeStamp = s;
    }

    public void setTimeoutSummary(String s)
    {
        mTimeoutSummary = s;
    }

    public void setTotalTime(long l)
    {
        mTotalTime = l;
    }

    public void setWakeSource(String s)
    {
        mWakeSource = s;
    }

    public void setmTimeOutDetail(String s)
    {
        mTimeOutDetail = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("ScreenOnEvent { mTimeoutSummary = ").append(mTimeoutSummary).append(", mTimeOutDetail = ").append(mTimeOutDetail).append(", mWakeSource = ").append(mWakeSource).append(", mTimeStamp = ").append(mTimeStamp).append(", mTotalTime = ").append(mTotalTime).append(", mSetDisplayTime = ").append(mSetDisplayTime).append(", mBlockScreenTime = ").append(mBlockScreenTime).append(", mScreenOnType = ").append(mScreenOnType).append(" }");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mTimeoutSummary);
        parcel.writeString(mTimeOutDetail);
        parcel.writeString(mWakeSource);
        parcel.writeString(mTimeStamp);
        parcel.writeLong(mTotalTime);
        parcel.writeLong(mSetDisplayTime);
        parcel.writeLong(mBlockScreenTime);
        parcel.writeString(mScreenOnType);
    }

    public static final String AVG_SCREEN_ON = "avg_screen_on";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ScreenOnEvent createFromParcel(Parcel parcel)
        {
            return new ScreenOnEvent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public ScreenOnEvent[] newArray(int i)
        {
            return new ScreenOnEvent[i];
        }

    }
;
    public static final String LT_SCREEN_ON = "lt_screen_on";
    public static final String TYPE_SCREEN_ON[] = {
        "avg_screen_on", "power", "dp_center", "keyguard_screenon_notification", "keyguard_screenon_finger_pass", "lid"
    };
    private long mBlockScreenTime;
    private String mScreenOnType;
    private long mSetDisplayTime;
    private String mTimeOutDetail;
    private String mTimeStamp;
    private String mTimeoutSummary;
    private long mTotalTime;
    private String mWakeSource;

}
