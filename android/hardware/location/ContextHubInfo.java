// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;
import java.util.Arrays;

// Referenced classes of package android.hardware.location:
//            MemoryRegion

public class ContextHubInfo
{

    public ContextHubInfo()
    {
    }

    private ContextHubInfo(Parcel parcel)
    {
        mId = parcel.readInt();
        mName = parcel.readString();
        mVendor = parcel.readString();
        mToolchain = parcel.readString();
        mPlatformVersion = parcel.readInt();
        mToolchainVersion = parcel.readInt();
        mStaticSwVersion = parcel.readInt();
        mPeakMips = parcel.readFloat();
        mStoppedPowerDrawMw = parcel.readFloat();
        mSleepPowerDrawMw = parcel.readFloat();
        mPeakPowerDrawMw = parcel.readFloat();
        mMaxPacketLengthBytes = parcel.readInt();
        mSupportedSensors = new int[parcel.readInt()];
        parcel.readIntArray(mSupportedSensors);
        mMemoryRegions = (MemoryRegion[])parcel.createTypedArray(MemoryRegion.CREATOR);
    }

    ContextHubInfo(Parcel parcel, ContextHubInfo contexthubinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getId()
    {
        return mId;
    }

    public int getMaxPacketLengthBytes()
    {
        return mMaxPacketLengthBytes;
    }

    public MemoryRegion[] getMemoryRegions()
    {
        return (MemoryRegion[])Arrays.copyOf(mMemoryRegions, mMemoryRegions.length);
    }

    public String getName()
    {
        return mName;
    }

    public float getPeakMips()
    {
        return mPeakMips;
    }

    public float getPeakPowerDrawMw()
    {
        return mPeakPowerDrawMw;
    }

    public int getPlatformVersion()
    {
        return mPlatformVersion;
    }

    public float getSleepPowerDrawMw()
    {
        return mSleepPowerDrawMw;
    }

    public int getStaticSwVersion()
    {
        return mStaticSwVersion;
    }

    public float getStoppedPowerDrawMw()
    {
        return mStoppedPowerDrawMw;
    }

    public int[] getSupportedSensors()
    {
        return Arrays.copyOf(mSupportedSensors, mSupportedSensors.length);
    }

    public String getToolchain()
    {
        return mToolchain;
    }

    public int getToolchainVersion()
    {
        return mToolchainVersion;
    }

    public String getVendor()
    {
        return mVendor;
    }

    public void setId(int i)
    {
        mId = i;
    }

    public void setMaxPacketLenBytes(int i)
    {
        mMaxPacketLengthBytes = i;
    }

    public void setMemoryRegions(MemoryRegion amemoryregion[])
    {
        mMemoryRegions = (MemoryRegion[])Arrays.copyOf(amemoryregion, amemoryregion.length);
    }

    public void setName(String s)
    {
        mName = s;
    }

    public void setPeakMips(float f)
    {
        mPeakMips = f;
    }

    public void setPeakPowerDrawMw(float f)
    {
        mPeakPowerDrawMw = f;
    }

    public void setPlatformVersion(int i)
    {
        mPlatformVersion = i;
    }

    public void setSleepPowerDrawMw(float f)
    {
        mSleepPowerDrawMw = f;
    }

    public void setStaticSwVersion(int i)
    {
        mStaticSwVersion = i;
    }

    public void setStoppedPowerDrawMw(float f)
    {
        mStoppedPowerDrawMw = f;
    }

    public void setSupportedSensors(int ai[])
    {
        mSupportedSensors = Arrays.copyOf(ai, ai.length);
    }

    public void setToolchain(String s)
    {
        mToolchain = s;
    }

    public void setToolchainVersion(int i)
    {
        mToolchainVersion = i;
    }

    public void setVendor(String s)
    {
        mVendor = s;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("").append("Id : ").append(mId).toString();
        s = (new StringBuilder()).append(s).append(", Name : ").append(mName).toString();
        s = (new StringBuilder()).append(s).append("\n\tVendor : ").append(mVendor).toString();
        s = (new StringBuilder()).append(s).append(", ToolChain : ").append(mToolchain).toString();
        s = (new StringBuilder()).append(s).append("\n\tPlatformVersion : ").append(mPlatformVersion).toString();
        s = (new StringBuilder()).append(s).append(", StaticSwVersion : ").append(mStaticSwVersion).toString();
        s = (new StringBuilder()).append(s).append("\n\tPeakMips : ").append(mPeakMips).toString();
        s = (new StringBuilder()).append(s).append(", StoppedPowerDraw : ").append(mStoppedPowerDrawMw).append(" mW").toString();
        s = (new StringBuilder()).append(s).append(", PeakPowerDraw : ").append(mPeakPowerDrawMw).append(" mW").toString();
        s = (new StringBuilder()).append(s).append(", MaxPacketLength : ").append(mMaxPacketLengthBytes).append(" Bytes").toString();
        s = (new StringBuilder()).append(s).append("\n\tSupported sensors : ").append(Arrays.toString(mSupportedSensors)).toString();
        return (new StringBuilder()).append(s).append("\n\tMemory Regions : ").append(Arrays.toString(mMemoryRegions)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mVendor);
        parcel.writeString(mToolchain);
        parcel.writeInt(mPlatformVersion);
        parcel.writeInt(mToolchainVersion);
        parcel.writeInt(mStaticSwVersion);
        parcel.writeFloat(mPeakMips);
        parcel.writeFloat(mStoppedPowerDrawMw);
        parcel.writeFloat(mSleepPowerDrawMw);
        parcel.writeFloat(mPeakPowerDrawMw);
        parcel.writeInt(mMaxPacketLengthBytes);
        parcel.writeInt(mSupportedSensors.length);
        parcel.writeIntArray(mSupportedSensors);
        parcel.writeTypedArray(mMemoryRegions, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ContextHubInfo createFromParcel(Parcel parcel)
        {
            return new ContextHubInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ContextHubInfo[] newArray(int i)
        {
            return new ContextHubInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mId;
    private int mMaxPacketLengthBytes;
    private MemoryRegion mMemoryRegions[];
    private String mName;
    private float mPeakMips;
    private float mPeakPowerDrawMw;
    private int mPlatformVersion;
    private float mSleepPowerDrawMw;
    private int mStaticSwVersion;
    private float mStoppedPowerDrawMw;
    private int mSupportedSensors[];
    private String mToolchain;
    private int mToolchainVersion;
    private String mVendor;

}
