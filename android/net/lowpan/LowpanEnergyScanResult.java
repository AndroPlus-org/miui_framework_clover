// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;


public class LowpanEnergyScanResult
{

    LowpanEnergyScanResult()
    {
        mChannel = 0x7fffffff;
        mMaxRssi = 0x7fffffff;
    }

    public int getChannel()
    {
        return mChannel;
    }

    public int getMaxRssi()
    {
        return mMaxRssi;
    }

    void setChannel(int i)
    {
        mChannel = i;
    }

    void setMaxRssi(int i)
    {
        mMaxRssi = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("LowpanEnergyScanResult(channel: ").append(mChannel).append(", maxRssi:").append(mMaxRssi).append(")").toString();
    }

    public static final int UNKNOWN = 0x7fffffff;
    private int mChannel;
    private int mMaxRssi;
}
