// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;
import libcore.util.EmptyArray;

public class NanoAppInstanceInfo
{

    public NanoAppInstanceInfo()
    {
        mNeededSensors = EmptyArray.INT;
        mOutputEvents = EmptyArray.INT;
    }

    private NanoAppInstanceInfo(Parcel parcel)
    {
        mPublisher = parcel.readString();
        mName = parcel.readString();
        mHandle = parcel.readInt();
        mAppId = parcel.readLong();
        mAppVersion = parcel.readInt();
        mContexthubId = parcel.readInt();
        mNeededReadMemBytes = parcel.readInt();
        mNeededWriteMemBytes = parcel.readInt();
        mNeededExecMemBytes = parcel.readInt();
        mNeededSensors = new int[parcel.readInt()];
        parcel.readIntArray(mNeededSensors);
        mOutputEvents = new int[parcel.readInt()];
        parcel.readIntArray(mOutputEvents);
    }

    NanoAppInstanceInfo(Parcel parcel, NanoAppInstanceInfo nanoappinstanceinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public long getAppId()
    {
        return mAppId;
    }

    public int getAppVersion()
    {
        return mAppVersion;
    }

    public int getContexthubId()
    {
        return mContexthubId;
    }

    public int getHandle()
    {
        return mHandle;
    }

    public String getName()
    {
        return mName;
    }

    public int getNeededExecMemBytes()
    {
        return mNeededExecMemBytes;
    }

    public int getNeededReadMemBytes()
    {
        return mNeededReadMemBytes;
    }

    public int[] getNeededSensors()
    {
        return mNeededSensors;
    }

    public int getNeededWriteMemBytes()
    {
        return mNeededWriteMemBytes;
    }

    public int[] getOutputEvents()
    {
        return mOutputEvents;
    }

    public String getPublisher()
    {
        return mPublisher;
    }

    public void setAppId(long l)
    {
        mAppId = l;
    }

    public void setAppVersion(int i)
    {
        mAppVersion = i;
    }

    public void setContexthubId(int i)
    {
        mContexthubId = i;
    }

    public void setHandle(int i)
    {
        mHandle = i;
    }

    public void setName(String s)
    {
        mName = s;
    }

    public void setNeededExecMemBytes(int i)
    {
        mNeededExecMemBytes = i;
    }

    public void setNeededReadMemBytes(int i)
    {
        mNeededReadMemBytes = i;
    }

    public void setNeededSensors(int ai[])
    {
        if(ai == null)
            ai = EmptyArray.INT;
        mNeededSensors = ai;
    }

    public void setNeededWriteMemBytes(int i)
    {
        mNeededWriteMemBytes = i;
    }

    public void setOutputEvents(int ai[])
    {
        if(ai == null)
            ai = EmptyArray.INT;
        mOutputEvents = ai;
    }

    public void setPublisher(String s)
    {
        mPublisher = s;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("handle : ").append(mHandle).toString();
        s = (new StringBuilder()).append(s).append(", Id : 0x").append(Long.toHexString(mAppId)).toString();
        s = (new StringBuilder()).append(s).append(", Version : ").append(mAppVersion).toString();
        s = (new StringBuilder()).append(s).append(", Name : ").append(mName).toString();
        return (new StringBuilder()).append(s).append(", Publisher : ").append(mPublisher).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPublisher);
        parcel.writeString(mName);
        parcel.writeInt(mHandle);
        parcel.writeLong(mAppId);
        parcel.writeInt(mAppVersion);
        parcel.writeInt(mContexthubId);
        parcel.writeInt(mNeededReadMemBytes);
        parcel.writeInt(mNeededWriteMemBytes);
        parcel.writeInt(mNeededExecMemBytes);
        parcel.writeInt(mNeededSensors.length);
        parcel.writeIntArray(mNeededSensors);
        parcel.writeInt(mOutputEvents.length);
        parcel.writeIntArray(mOutputEvents);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NanoAppInstanceInfo createFromParcel(Parcel parcel)
        {
            return new NanoAppInstanceInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NanoAppInstanceInfo[] newArray(int i)
        {
            return new NanoAppInstanceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long mAppId;
    private int mAppVersion;
    private int mContexthubId;
    private int mHandle;
    private String mName;
    private int mNeededExecMemBytes;
    private int mNeededReadMemBytes;
    private int mNeededSensors[];
    private int mNeededWriteMemBytes;
    private int mOutputEvents[];
    private String mPublisher;

}
