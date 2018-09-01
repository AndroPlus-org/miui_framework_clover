// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package miui.mqsas.sdk.event:
//            ExceptionEvent

public class BroadcastEvent extends ExceptionEvent
    implements Parcelable
{

    public BroadcastEvent()
    {
        initBroadcastEvent();
    }

    private BroadcastEvent(Parcel parcel)
    {
        boolean flag = true;
        super();
        mType = parcel.readInt();
        mPid = parcel.readInt();
        mProcessName = parcel.readString();
        mPackageName = parcel.readString();
        mTimeStamp = parcel.readLong();
        mSummary = parcel.readString();
        mDetails = parcel.readString();
        mDigest = parcel.readString();
        mLogName = parcel.readString();
        mKeyWord = parcel.readString();
        mAction = parcel.readString();
        mCallerPackage = parcel.readString();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsSystem = flag1;
        mReason = parcel.readString();
        mEnqueueClockTime = parcel.readLong();
        mDispatchClockTime = parcel.readLong();
        mFinishClockTime = parcel.readLong();
        mBroadcastReceiver = parcel.readString();
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsQueuedWorked = flag1;
        mTotalTime = parcel.readLong();
        mCount = parcel.readInt();
        mNumReceivers = parcel.readInt();
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        mIsUpload = flag1;
    }

    BroadcastEvent(Parcel parcel, BroadcastEvent broadcastevent)
    {
        this(parcel);
    }

    private String formatTime(long l)
    {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(l));
    }

    private void initBroadcastEvent()
    {
        mType = -1;
        mAction = "";
        mCallerPackage = "";
        mReason = "";
        mEnqueueClockTime = -1L;
        mDispatchClockTime = -1L;
        mFinishClockTime = -1L;
        mBroadcastReceiver = "";
        mIsQueuedWorked = false;
        mTotalTime = -1L;
        mCount = -1;
        mNumReceivers = -1;
    }

    public void addCount()
    {
        mCount = mCount + 1;
    }

    public void addTotalTime(long l)
    {
        mTotalTime = mTotalTime + l;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getAction()
    {
        return mAction;
    }

    public String getBrReceiver()
    {
        return mBroadcastReceiver;
    }

    public String getCallerPackage()
    {
        return mCallerPackage;
    }

    public long getCount()
    {
        return (long)mCount;
    }

    public long getDisTime()
    {
        return mDispatchClockTime;
    }

    public long getEnTime()
    {
        return mEnqueueClockTime;
    }

    public long getFinTime()
    {
        return mFinishClockTime;
    }

    public int getNumReceivers()
    {
        return mNumReceivers;
    }

    public String getReason()
    {
        return mReason;
    }

    public long getTotalTime()
    {
        return mTotalTime;
    }

    public int getType()
    {
        return mType;
    }

    public void initType()
    {
    }

    public boolean isQuWorked()
    {
        return mIsQueuedWorked;
    }

    public void setAction(String s)
    {
        mAction = s;
    }

    public void setBrReceiver(String s)
    {
        mBroadcastReceiver = s;
    }

    public void setCallerPackage(String s)
    {
        mCallerPackage = s;
    }

    public void setCount(int i)
    {
        mCount = i;
    }

    public void setDisTime(long l)
    {
        mDispatchClockTime = l;
    }

    public void setEnTime(long l)
    {
        mEnqueueClockTime = l;
    }

    public void setFinTime(long l)
    {
        mFinishClockTime = l;
    }

    public void setNumReceivers(int i)
    {
        mNumReceivers = i;
    }

    public void setQuWorked(boolean flag)
    {
        mIsQueuedWorked = flag;
    }

    public void setReason(String s)
    {
        mReason = s;
    }

    public void setTotalTime(long l)
    {
        mTotalTime = l;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("BroadcastEvent { mType=").append(mType).append(" Pid=").append(mPid).append(" processName=").append(mProcessName).append(" mTimeStamp=").append(mTimeStamp).append(" mSummary=").append(mSummary).append(" mDetails=").append(mDetails).append(" mDigest=").append(mDigest).append(" mLogName=").append(mLogName).append(" mKeyWord=").append(mKeyWord).append(" mAction= ").append(mAction).append(" mPackageName=").append(mPackageName).append(" mReason= ").append(mReason).append(" mEnqueueClockTime= ").append(formatTime(mEnqueueClockTime)).append(" mDispatchClockTime= ").append(formatTime(mDispatchClockTime)).append(" mFinishClockTime= ").append(formatTime(mFinishClockTime)).append(" mBroadcastReceiver=").append(mBroadcastReceiver).append(" isQueueWorked=").append(mIsQueuedWorked).append(" mCallerPackage=").append(mCallerPackage).append(" mCount=").append(mCount).append(", mTotalTime=").append(mTotalTime).append(" ms");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mType);
        parcel.writeInt(mPid);
        parcel.writeString(mProcessName);
        parcel.writeString(mPackageName);
        parcel.writeLong(mTimeStamp);
        parcel.writeString(mSummary);
        parcel.writeString(mDetails);
        parcel.writeString(mDigest);
        parcel.writeString(mLogName);
        parcel.writeString(mKeyWord);
        parcel.writeString(mAction);
        parcel.writeString(mCallerPackage);
        if(mIsSystem)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(mReason);
        parcel.writeLong(mEnqueueClockTime);
        parcel.writeLong(mDispatchClockTime);
        parcel.writeLong(mFinishClockTime);
        parcel.writeString(mBroadcastReceiver);
        if(mIsQueuedWorked)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeLong(mTotalTime);
        parcel.writeInt(mCount);
        parcel.writeInt(mNumReceivers);
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

        public BroadcastEvent createFromParcel(Parcel parcel)
        {
            return new BroadcastEvent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public BroadcastEvent[] newArray(int i)
        {
            return new BroadcastEvent[i];
        }

    }
;
    private String mAction;
    private String mBroadcastReceiver;
    private String mCallerPackage;
    private int mCount;
    private long mDispatchClockTime;
    private long mEnqueueClockTime;
    private long mFinishClockTime;
    private boolean mIsQueuedWorked;
    private int mNumReceivers;
    private String mReason;
    private long mTotalTime;

}
