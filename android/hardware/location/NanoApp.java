// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;
import android.util.Log;

public class NanoApp
{

    public NanoApp()
    {
        this(0L, ((byte []) (null)));
        mAppIdSet = false;
    }

    public NanoApp(int i, byte abyte0[])
    {
        TAG = "NanoApp";
        UNKNOWN = "Unknown";
        Log.w("NanoApp", "NanoApp(int, byte[]) is deprecated, please use NanoApp(long, byte[]) instead.");
    }

    public NanoApp(long l, byte abyte0[])
    {
        TAG = "NanoApp";
        UNKNOWN = "Unknown";
        mPublisher = "Unknown";
        mName = "Unknown";
        mAppId = l;
        mAppIdSet = true;
        mAppVersion = 0;
        mNeededReadMemBytes = 0;
        mNeededWriteMemBytes = 0;
        mNeededExecMemBytes = 0;
        mNeededSensors = new int[0];
        mOutputEvents = new int[0];
        mAppBinary = abyte0;
    }

    private NanoApp(Parcel parcel)
    {
        TAG = "NanoApp";
        UNKNOWN = "Unknown";
        mPublisher = parcel.readString();
        mName = parcel.readString();
        mAppId = parcel.readLong();
        mAppVersion = parcel.readInt();
        mNeededReadMemBytes = parcel.readInt();
        mNeededWriteMemBytes = parcel.readInt();
        mNeededExecMemBytes = parcel.readInt();
        mNeededSensors = new int[parcel.readInt()];
        parcel.readIntArray(mNeededSensors);
        mOutputEvents = new int[parcel.readInt()];
        parcel.readIntArray(mOutputEvents);
        mAppBinary = new byte[parcel.readInt()];
        parcel.readByteArray(mAppBinary);
    }

    NanoApp(Parcel parcel, NanoApp nanoapp)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getAppBinary()
    {
        return mAppBinary;
    }

    public long getAppId()
    {
        return mAppId;
    }

    public int getAppVersion()
    {
        return mAppVersion;
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

    public void setAppBinary(byte abyte0[])
    {
        mAppBinary = abyte0;
    }

    public void setAppId(long l)
    {
        mAppId = l;
        mAppIdSet = true;
    }

    public void setAppVersion(int i)
    {
        mAppVersion = i;
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
        mNeededSensors = ai;
    }

    public void setNeededWriteMemBytes(int i)
    {
        mNeededWriteMemBytes = i;
    }

    public void setOutputEvents(int ai[])
    {
        mOutputEvents = ai;
    }

    public void setPublisher(String s)
    {
        mPublisher = s;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("Id : ").append(mAppId).toString();
        s = (new StringBuilder()).append(s).append(", Version : ").append(mAppVersion).toString();
        s = (new StringBuilder()).append(s).append(", Name : ").append(mName).toString();
        return (new StringBuilder()).append(s).append(", Publisher : ").append(mPublisher).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mAppBinary == null)
            throw new IllegalStateException((new StringBuilder()).append("Must set non-null AppBinary for nanoapp ").append(mName).toString());
        if(!mAppIdSet)
        {
            throw new IllegalStateException((new StringBuilder()).append("Must set AppId for nanoapp ").append(mName).toString());
        } else
        {
            parcel.writeString(mPublisher);
            parcel.writeString(mName);
            parcel.writeLong(mAppId);
            parcel.writeInt(mAppVersion);
            parcel.writeInt(mNeededReadMemBytes);
            parcel.writeInt(mNeededWriteMemBytes);
            parcel.writeInt(mNeededExecMemBytes);
            parcel.writeInt(mNeededSensors.length);
            parcel.writeIntArray(mNeededSensors);
            parcel.writeInt(mOutputEvents.length);
            parcel.writeIntArray(mOutputEvents);
            parcel.writeInt(mAppBinary.length);
            parcel.writeByteArray(mAppBinary);
            return;
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NanoApp createFromParcel(Parcel parcel)
        {
            return new NanoApp(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NanoApp[] newArray(int i)
        {
            return new NanoApp[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String TAG;
    private final String UNKNOWN;
    private byte mAppBinary[];
    private long mAppId;
    private boolean mAppIdSet;
    private int mAppVersion;
    private String mName;
    private int mNeededExecMemBytes;
    private int mNeededReadMemBytes;
    private int mNeededSensors[];
    private int mNeededWriteMemBytes;
    private int mOutputEvents[];
    private String mPublisher;

}
