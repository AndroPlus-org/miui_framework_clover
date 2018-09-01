// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;


public final class GnssStatus
{
    public static abstract class Callback
    {

        public void onFirstFix(int i)
        {
        }

        public void onSatelliteStatusChanged(GnssStatus gnssstatus)
        {
        }

        public void onStarted()
        {
        }

        public void onStopped()
        {
        }

        public Callback()
        {
        }
    }


    GnssStatus(int i, int ai[], float af[], float af1[], float af2[], float af3[])
    {
        mSvCount = i;
        mSvidWithFlags = ai;
        mCn0DbHz = af;
        mElevations = af1;
        mAzimuths = af2;
        mCarrierFrequencies = af3;
    }

    public float getAzimuthDegrees(int i)
    {
        return mAzimuths[i];
    }

    public float getCarrierFrequencyHz(int i)
    {
        return mCarrierFrequencies[i];
    }

    public float getCn0DbHz(int i)
    {
        return mCn0DbHz[i];
    }

    public int getConstellationType(int i)
    {
        return mSvidWithFlags[i] >> 4 & 0xf;
    }

    public float getElevationDegrees(int i)
    {
        return mElevations[i];
    }

    public int getSatelliteCount()
    {
        return mSvCount;
    }

    public int getSvid(int i)
    {
        return mSvidWithFlags[i] >> 8;
    }

    public boolean hasAlmanacData(int i)
    {
        boolean flag = false;
        if((mSvidWithFlags[i] & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean hasCarrierFrequencyHz(int i)
    {
        boolean flag = false;
        if((mSvidWithFlags[i] & 8) != 0)
            flag = true;
        return flag;
    }

    public boolean hasEphemerisData(int i)
    {
        boolean flag = false;
        if((mSvidWithFlags[i] & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean usedInFix(int i)
    {
        boolean flag = false;
        if((mSvidWithFlags[i] & 4) != 0)
            flag = true;
        return flag;
    }

    public static final int CONSTELLATION_BEIDOU = 5;
    public static final int CONSTELLATION_GALILEO = 6;
    public static final int CONSTELLATION_GLONASS = 3;
    public static final int CONSTELLATION_GPS = 1;
    public static final int CONSTELLATION_QZSS = 4;
    public static final int CONSTELLATION_SBAS = 2;
    public static final int CONSTELLATION_TYPE_MASK = 15;
    public static final int CONSTELLATION_TYPE_SHIFT_WIDTH = 4;
    public static final int CONSTELLATION_UNKNOWN = 0;
    public static final int GNSS_SV_FLAGS_HAS_ALMANAC_DATA = 2;
    public static final int GNSS_SV_FLAGS_HAS_CARRIER_FREQUENCY = 8;
    public static final int GNSS_SV_FLAGS_HAS_EPHEMERIS_DATA = 1;
    public static final int GNSS_SV_FLAGS_NONE = 0;
    public static final int GNSS_SV_FLAGS_USED_IN_FIX = 4;
    public static final int SVID_SHIFT_WIDTH = 8;
    final float mAzimuths[];
    final float mCarrierFrequencies[];
    final float mCn0DbHz[];
    final float mElevations[];
    final int mSvCount;
    final int mSvidWithFlags[];
}
