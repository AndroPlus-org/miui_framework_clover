// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package miui.mqsas.sdk.event:
//            ExceptionEvent

public class JavaExceptionEvent extends ExceptionEvent
    implements Parcelable
{

    public JavaExceptionEvent()
    {
        mThreadName = "";
        mPrefix = "";
        mExceptionClassName = "";
        mExceptionMessage = "";
        mStackTrace = "";
    }

    private JavaExceptionEvent(Parcel parcel)
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
        mThreadName = parcel.readString();
        mPrefix = parcel.readString();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsSystem = flag1;
        mExceptionClassName = parcel.readString();
        mExceptionMessage = parcel.readString();
        mStackTrace = parcel.readString();
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        mIsUpload = flag1;
    }

    JavaExceptionEvent(Parcel parcel, JavaExceptionEvent javaexceptionevent)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getExceptionClassName()
    {
        return mExceptionClassName;
    }

    public String getExceptionMessage()
    {
        return mExceptionMessage;
    }

    public String getPrefix()
    {
        return mPrefix;
    }

    public String getStackTrace()
    {
        return mStackTrace;
    }

    public String getThreadName()
    {
        return mThreadName;
    }

    public void initType()
    {
        mType = 1;
    }

    public void setExceptionClassName(String s)
    {
        mExceptionClassName = s;
    }

    public void setExceptionMessage(String s)
    {
        mExceptionMessage = s;
    }

    public void setPrefix(String s)
    {
        mPrefix = s;
    }

    public void setStackTrace(String s)
    {
        mStackTrace = s;
    }

    public void setThreadName(String s)
    {
        mThreadName = s;
    }

    public String toShortString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("JavaException { mType=").append(mType).append(" Pid=").append(mPid).append(" processName=").append(mProcessName).append(" mTimeStamp=").append(mTimeStamp).append(" mDigest=").append(mDigest).append(" mSummary=").append(mSummary).append(" mLogName=").append(mLogName).append(" mKeyWord=").append(mKeyWord).append(" mExceptionClassName=").append(mExceptionClassName).append(" mExceptionMessage=").append(mExceptionMessage);
        return stringbuilder.toString();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("JavaException { mType=").append(mType).append(" Pid=").append(mPid).append(" processName=").append(mProcessName).append(" mTimeStamp=").append(mTimeStamp).append(" mSummary=").append(mSummary).append(" mDetails=").append(mDetails).append(" mDigest=").append(mDigest).append(" mLogName=").append(mLogName).append(" mKeyWord=").append(mKeyWord).append(" mExceptionClassName=").append(mExceptionClassName).append(" mExceptionMessage=").append(mExceptionMessage).append(" mStackTrace=").append(mStackTrace).append(" mIsSystem=").append(mIsSystem);
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
        parcel.writeString(mThreadName);
        parcel.writeString(mPrefix);
        if(mIsSystem)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(mExceptionClassName);
        parcel.writeString(mExceptionMessage);
        parcel.writeString(mStackTrace);
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

        public JavaExceptionEvent createFromParcel(Parcel parcel)
        {
            return new JavaExceptionEvent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public JavaExceptionEvent[] newArray(int i)
        {
            return new JavaExceptionEvent[i];
        }

    }
;
    private String mExceptionClassName;
    private String mExceptionMessage;
    private String mPrefix;
    private String mStackTrace;
    private String mThreadName;

}
