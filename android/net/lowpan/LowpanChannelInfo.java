// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class LowpanChannelInfo
    implements Parcelable
{

    static int _2D_set0(LowpanChannelInfo lowpanchannelinfo, int i)
    {
        lowpanchannelinfo.mIndex = i;
        return i;
    }

    static boolean _2D_set1(LowpanChannelInfo lowpanchannelinfo, boolean flag)
    {
        lowpanchannelinfo.mIsMaskedByRegulatoryDomain = flag;
        return flag;
    }

    static int _2D_set2(LowpanChannelInfo lowpanchannelinfo, int i)
    {
        lowpanchannelinfo.mMaxTransmitPower = i;
        return i;
    }

    static String _2D_set3(LowpanChannelInfo lowpanchannelinfo, String s)
    {
        lowpanchannelinfo.mName = s;
        return s;
    }

    static float _2D_set4(LowpanChannelInfo lowpanchannelinfo, float f)
    {
        lowpanchannelinfo.mSpectrumBandwidth = f;
        return f;
    }

    static float _2D_set5(LowpanChannelInfo lowpanchannelinfo, float f)
    {
        lowpanchannelinfo.mSpectrumCenterFrequency = f;
        return f;
    }

    private LowpanChannelInfo()
    {
        mIndex = 0;
        mName = null;
        mSpectrumCenterFrequency = 0.0F;
        mSpectrumBandwidth = 0.0F;
        mMaxTransmitPower = 0x7fffffff;
        mIsMaskedByRegulatoryDomain = false;
    }

    private LowpanChannelInfo(int i, String s, float f, float f1)
    {
        mIndex = 0;
        mName = null;
        mSpectrumCenterFrequency = 0.0F;
        mSpectrumBandwidth = 0.0F;
        mMaxTransmitPower = 0x7fffffff;
        mIsMaskedByRegulatoryDomain = false;
        mIndex = i;
        mName = s;
        mSpectrumCenterFrequency = f;
        mSpectrumBandwidth = f1;
    }

    LowpanChannelInfo(LowpanChannelInfo lowpanchannelinfo)
    {
        this();
    }

    public static LowpanChannelInfo getChannelInfoForIeee802154Page0(int i)
    {
        LowpanChannelInfo lowpanchannelinfo = new LowpanChannelInfo();
        if(i < 0)
            lowpanchannelinfo = null;
        else
        if(i == 0)
        {
            lowpanchannelinfo.mSpectrumCenterFrequency = 8.683E+008F;
            lowpanchannelinfo.mSpectrumBandwidth = 600000F;
        } else
        if(i < 11)
        {
            lowpanchannelinfo.mSpectrumCenterFrequency = (float)i * 2000000F + 9.04E+008F;
            lowpanchannelinfo.mSpectrumBandwidth = 0.0F;
        } else
        if(i < 26)
        {
            lowpanchannelinfo.mSpectrumCenterFrequency = (float)i * 5000000F + 2.35E+009F;
            lowpanchannelinfo.mSpectrumBandwidth = 2000000F;
        } else
        {
            lowpanchannelinfo = null;
        }
        lowpanchannelinfo.mName = Integer.toString(i);
        return lowpanchannelinfo;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof LowpanChannelInfo))
            return false;
        obj = (LowpanChannelInfo)obj;
        boolean flag1 = flag;
        if(Objects.equals(mName, ((LowpanChannelInfo) (obj)).mName))
        {
            flag1 = flag;
            if(mIndex == ((LowpanChannelInfo) (obj)).mIndex)
            {
                flag1 = flag;
                if(mIsMaskedByRegulatoryDomain == ((LowpanChannelInfo) (obj)).mIsMaskedByRegulatoryDomain)
                {
                    flag1 = flag;
                    if(mSpectrumCenterFrequency == ((LowpanChannelInfo) (obj)).mSpectrumCenterFrequency)
                    {
                        flag1 = flag;
                        if(mSpectrumBandwidth == ((LowpanChannelInfo) (obj)).mSpectrumBandwidth)
                        {
                            flag1 = flag;
                            if(mMaxTransmitPower == ((LowpanChannelInfo) (obj)).mMaxTransmitPower)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public int getIndex()
    {
        return mIndex;
    }

    public int getMaxTransmitPower()
    {
        return mMaxTransmitPower;
    }

    public String getName()
    {
        return mName;
    }

    public float getSpectrumBandwidth()
    {
        return mSpectrumBandwidth;
    }

    public float getSpectrumCenterFrequency()
    {
        return mSpectrumCenterFrequency;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mName, Integer.valueOf(mIndex), Boolean.valueOf(mIsMaskedByRegulatoryDomain), Float.valueOf(mSpectrumCenterFrequency), Float.valueOf(mSpectrumBandwidth), Integer.valueOf(mMaxTransmitPower)
        });
    }

    public boolean isMaskedByRegulatoryDomain()
    {
        return mIsMaskedByRegulatoryDomain;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("Channel ").append(mIndex);
        if(mName != null && mName.equals(Integer.toString(mIndex)) ^ true)
            stringbuffer.append(" (").append(mName).append(")");
        if(mSpectrumCenterFrequency > 0.0F)
            if(mSpectrumCenterFrequency > 1E+009F)
                stringbuffer.append(", SpectrumCenterFrequency: ").append(mSpectrumCenterFrequency / 1E+009F).append("GHz");
            else
            if(mSpectrumCenterFrequency > 1000000F)
                stringbuffer.append(", SpectrumCenterFrequency: ").append(mSpectrumCenterFrequency / 1000000F).append("MHz");
            else
                stringbuffer.append(", SpectrumCenterFrequency: ").append(mSpectrumCenterFrequency / 1000F).append("kHz");
        if(mSpectrumBandwidth > 0.0F)
            if(mSpectrumBandwidth > 1E+009F)
                stringbuffer.append(", SpectrumBandwidth: ").append(mSpectrumBandwidth / 1E+009F).append("GHz");
            else
            if(mSpectrumBandwidth > 1000000F)
                stringbuffer.append(", SpectrumBandwidth: ").append(mSpectrumBandwidth / 1000000F).append("MHz");
            else
                stringbuffer.append(", SpectrumBandwidth: ").append(mSpectrumBandwidth / 1000F).append("kHz");
        if(mMaxTransmitPower != 0x7fffffff)
            stringbuffer.append(", MaxTransmitPower: ").append(mMaxTransmitPower).append("dBm");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mIndex);
        parcel.writeString(mName);
        parcel.writeFloat(mSpectrumCenterFrequency);
        parcel.writeFloat(mSpectrumBandwidth);
        parcel.writeInt(mMaxTransmitPower);
        parcel.writeBoolean(mIsMaskedByRegulatoryDomain);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LowpanChannelInfo createFromParcel(Parcel parcel)
        {
            LowpanChannelInfo lowpanchannelinfo = new LowpanChannelInfo(null);
            LowpanChannelInfo._2D_set0(lowpanchannelinfo, parcel.readInt());
            LowpanChannelInfo._2D_set3(lowpanchannelinfo, parcel.readString());
            LowpanChannelInfo._2D_set5(lowpanchannelinfo, parcel.readFloat());
            LowpanChannelInfo._2D_set4(lowpanchannelinfo, parcel.readFloat());
            LowpanChannelInfo._2D_set2(lowpanchannelinfo, parcel.readInt());
            LowpanChannelInfo._2D_set1(lowpanchannelinfo, parcel.readBoolean());
            return lowpanchannelinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LowpanChannelInfo[] newArray(int i)
        {
            return new LowpanChannelInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final float UNKNOWN_BANDWIDTH = 0F;
    public static final float UNKNOWN_FREQUENCY = 0F;
    public static final int UNKNOWN_POWER = 0x7fffffff;
    private int mIndex;
    private boolean mIsMaskedByRegulatoryDomain;
    private int mMaxTransmitPower;
    private String mName;
    private float mSpectrumBandwidth;
    private float mSpectrumCenterFrequency;

}
