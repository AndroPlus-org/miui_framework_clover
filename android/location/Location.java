// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;
import android.util.Printer;
import android.util.TimeUtils;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Location
    implements Parcelable
{
    private static class BearingDistanceCache
    {

        static float _2D_get0(BearingDistanceCache bearingdistancecache)
        {
            return bearingdistancecache.mDistance;
        }

        static float _2D_get1(BearingDistanceCache bearingdistancecache)
        {
            return bearingdistancecache.mFinalBearing;
        }

        static float _2D_get2(BearingDistanceCache bearingdistancecache)
        {
            return bearingdistancecache.mInitialBearing;
        }

        static double _2D_get3(BearingDistanceCache bearingdistancecache)
        {
            return bearingdistancecache.mLat1;
        }

        static double _2D_get4(BearingDistanceCache bearingdistancecache)
        {
            return bearingdistancecache.mLat2;
        }

        static double _2D_get5(BearingDistanceCache bearingdistancecache)
        {
            return bearingdistancecache.mLon1;
        }

        static double _2D_get6(BearingDistanceCache bearingdistancecache)
        {
            return bearingdistancecache.mLon2;
        }

        static float _2D_set0(BearingDistanceCache bearingdistancecache, float f)
        {
            bearingdistancecache.mDistance = f;
            return f;
        }

        static float _2D_set1(BearingDistanceCache bearingdistancecache, float f)
        {
            bearingdistancecache.mFinalBearing = f;
            return f;
        }

        static float _2D_set2(BearingDistanceCache bearingdistancecache, float f)
        {
            bearingdistancecache.mInitialBearing = f;
            return f;
        }

        static double _2D_set3(BearingDistanceCache bearingdistancecache, double d)
        {
            bearingdistancecache.mLat1 = d;
            return d;
        }

        static double _2D_set4(BearingDistanceCache bearingdistancecache, double d)
        {
            bearingdistancecache.mLat2 = d;
            return d;
        }

        static double _2D_set5(BearingDistanceCache bearingdistancecache, double d)
        {
            bearingdistancecache.mLon1 = d;
            return d;
        }

        static double _2D_set6(BearingDistanceCache bearingdistancecache, double d)
        {
            bearingdistancecache.mLon2 = d;
            return d;
        }

        private float mDistance;
        private float mFinalBearing;
        private float mInitialBearing;
        private double mLat1;
        private double mLat2;
        private double mLon1;
        private double mLon2;

        private BearingDistanceCache()
        {
            mLat1 = 0.0D;
            mLon1 = 0.0D;
            mLat2 = 0.0D;
            mLon2 = 0.0D;
            mDistance = 0.0F;
            mInitialBearing = 0.0F;
            mFinalBearing = 0.0F;
        }

        BearingDistanceCache(BearingDistanceCache bearingdistancecache)
        {
            this();
        }
    }


    static double _2D_set0(Location location, double d)
    {
        location.mAltitude = d;
        return d;
    }

    static float _2D_set1(Location location, float f)
    {
        location.mBearing = f;
        return f;
    }

    static float _2D_set10(Location location, float f)
    {
        location.mSpeedAccuracyMetersPerSecond = f;
        return f;
    }

    static long _2D_set11(Location location, long l)
    {
        location.mTime = l;
        return l;
    }

    static float _2D_set12(Location location, float f)
    {
        location.mVerticalAccuracyMeters = f;
        return f;
    }

    static float _2D_set2(Location location, float f)
    {
        location.mBearingAccuracyDegrees = f;
        return f;
    }

    static long _2D_set3(Location location, long l)
    {
        location.mElapsedRealtimeNanos = l;
        return l;
    }

    static Bundle _2D_set4(Location location, Bundle bundle)
    {
        location.mExtras = bundle;
        return bundle;
    }

    static byte _2D_set5(Location location, byte byte0)
    {
        location.mFieldsMask = byte0;
        return byte0;
    }

    static float _2D_set6(Location location, float f)
    {
        location.mHorizontalAccuracyMeters = f;
        return f;
    }

    static double _2D_set7(Location location, double d)
    {
        location.mLatitude = d;
        return d;
    }

    static double _2D_set8(Location location, double d)
    {
        location.mLongitude = d;
        return d;
    }

    static float _2D_set9(Location location, float f)
    {
        location.mSpeed = f;
        return f;
    }

    public Location(Location location)
    {
        mTime = 0L;
        mElapsedRealtimeNanos = 0L;
        mLatitude = 0.0D;
        mLongitude = 0.0D;
        mAltitude = 0.0D;
        mSpeed = 0.0F;
        mBearing = 0.0F;
        mHorizontalAccuracyMeters = 0.0F;
        mVerticalAccuracyMeters = 0.0F;
        mSpeedAccuracyMetersPerSecond = 0.0F;
        mBearingAccuracyDegrees = 0.0F;
        mExtras = null;
        mFieldsMask = (byte)0;
        set(location);
    }

    public Location(String s)
    {
        mTime = 0L;
        mElapsedRealtimeNanos = 0L;
        mLatitude = 0.0D;
        mLongitude = 0.0D;
        mAltitude = 0.0D;
        mSpeed = 0.0F;
        mBearing = 0.0F;
        mHorizontalAccuracyMeters = 0.0F;
        mVerticalAccuracyMeters = 0.0F;
        mSpeedAccuracyMetersPerSecond = 0.0F;
        mBearingAccuracyDegrees = 0.0F;
        mExtras = null;
        mFieldsMask = (byte)0;
        mProvider = s;
    }

    private static void computeDistanceAndBearing(double d, double d1, double d2, double d3, 
            BearingDistanceCache bearingdistancecache)
    {
        double d4 = d * 0.017453292519943295D;
        double d5 = d2 * 0.017453292519943295D;
        double d6 = d1 * 0.017453292519943295D;
        double d7 = d3 * 0.017453292519943295D;
        double d8 = 21384.685800000094D / 6378137D;
        double d9 = (40680631590769D - 40408299984087.055D) / 40408299984087.055D;
        double d10 = d7 - d6;
        double d11 = 0.0D;
        d1 = Math.atan((1.0D - d8) * Math.tan(d4));
        d = Math.atan((1.0D - d8) * Math.tan(d5));
        double d12 = Math.cos(d1);
        double d13 = Math.cos(d);
        double d14 = Math.sin(d1);
        double d15 = Math.sin(d);
        double d16 = d12 * d13;
        double d17 = d14 * d15;
        d1 = 0.0D;
        d2 = 0.0D;
        d3 = 0.0D;
        d = 0.0D;
        double d18 = d10;
        int i = 0;
        do
        {
            double d22;
label0:
            {
                double d19 = d18;
                if(i < 20)
                {
                    d3 = Math.cos(d19);
                    d = Math.sin(d19);
                    d2 = d13 * d;
                    d1 = d12 * d15 - d14 * d13 * d3;
                    double d20 = Math.sqrt(d2 * d2 + d1 * d1);
                    double d21 = d17 + d16 * d3;
                    d1 = Math.atan2(d20, d21);
                    double d23;
                    double d24;
                    if(d20 == 0.0D)
                        d2 = 0.0D;
                    else
                        d2 = (d16 * d) / d20;
                    d18 = 1.0D - d2 * d2;
                    if(d18 == 0.0D)
                        d22 = 0.0D;
                    else
                        d22 = d21 - (2D * d17) / d18;
                    d23 = d18 * d9;
                    d11 = 1.0D + (d23 / 16384D) * (((320D - 175D * d23) * d23 - 768D) * d23 + 4096D);
                    d24 = (d23 / 1024D) * (((74D - 47D * d23) * d23 - 128D) * d23 + 256D);
                    d23 = (d8 / 16D) * d18 * ((4D - 3D * d18) * d8 + 4D);
                    d18 = d22 * d22;
                    d18 = d24 * d20 * ((d24 / 4D) * ((2D * d18 - 1.0D) * d21 - (d24 / 6D) * d22 * (4D * d20 * d20 - 3D) * (4D * d18 - 3D)) + d22);
                    d22 = d10 + (1.0D - d23) * d8 * d2 * (d23 * d20 * (d23 * d21 * (2D * d22 * d22 - 1.0D) + d22) + d1);
                    if(Math.abs((d22 - d19) / d22) >= 9.9999999999999998E-013D)
                        break label0;
                    d2 = d18;
                }
                BearingDistanceCache._2D_set0(bearingdistancecache, (float)(6356752.3141999999D * d11 * (d1 - d2)));
                BearingDistanceCache._2D_set2(bearingdistancecache, (float)((double)(float)Math.atan2(d13 * d, d12 * d15 - d14 * d13 * d3) * 57.295779513082323D));
                BearingDistanceCache._2D_set1(bearingdistancecache, (float)((double)(float)Math.atan2(d12 * d, -d14 * d13 + d12 * d15 * d3) * 57.295779513082323D));
                BearingDistanceCache._2D_set3(bearingdistancecache, d4);
                BearingDistanceCache._2D_set4(bearingdistancecache, d5);
                BearingDistanceCache._2D_set5(bearingdistancecache, d6);
                BearingDistanceCache._2D_set6(bearingdistancecache, d7);
                return;
            }
            i++;
            d2 = d18;
            d18 = d22;
        } while(true);
    }

    public static double convert(String s)
    {
        boolean flag;
        String s1;
        double d;
        double d1;
        int j;
        boolean flag2;
        if(s == null)
            throw new NullPointerException("coordinate");
        flag = false;
        s1 = s;
        if(s.charAt(0) == '-')
        {
            s1 = s.substring(1);
            flag = true;
        }
        StringTokenizer stringtokenizer = new StringTokenizer(s1, ":");
        int i = stringtokenizer.countTokens();
        if(i < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("coordinate=").append(s1).toString());
        Object obj;
        try
        {
            obj = stringtokenizer.nextToken();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("coordinate=").append(s1).toString());
        }
        if(i != 1)
            break MISSING_BLOCK_LABEL_122;
        d = Double.parseDouble(((String) (obj)));
        d1 = d;
        if(flag)
            d1 = -d;
        return d1;
        s = stringtokenizer.nextToken();
        j = Integer.parseInt(((String) (obj)));
        d1 = 0.0D;
        flag2 = false;
        if(!stringtokenizer.hasMoreTokens())
            break MISSING_BLOCK_LABEL_282;
        d = Integer.parseInt(s);
        d1 = Double.parseDouble(stringtokenizer.nextToken());
        flag2 = true;
_L1:
        boolean flag1;
        if(flag && j == 180 && d == 0.0D)
        {
            if(d1 == 0.0D)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = false;
        }
        if((double)j >= 0.0D && (j <= 179 || !(flag1 ^ true)))
            break MISSING_BLOCK_LABEL_303;
        obj = JVM INSTR new #251 <Class IllegalArgumentException>;
        s = JVM INSTR new #253 <Class StringBuilder>;
        s.StringBuilder();
        ((IllegalArgumentException) (obj)).IllegalArgumentException(s.append("coordinate=").append(s1).toString());
        throw obj;
        d = Double.parseDouble(s);
          goto _L1
        do
        {
            IllegalArgumentException illegalargumentexception = JVM INSTR new #251 <Class IllegalArgumentException>;
            s = JVM INSTR new #253 <Class StringBuilder>;
            s.StringBuilder();
            illegalargumentexception.IllegalArgumentException(s.append("coordinate=").append(s1).toString());
            throw illegalargumentexception;
        } while(d < 0.0D || d >= 60D || flag2 && d > 59D);
        if(d1 >= 0.0D && d1 < 60D)
            break MISSING_BLOCK_LABEL_419;
        IllegalArgumentException illegalargumentexception1 = JVM INSTR new #251 <Class IllegalArgumentException>;
        s = JVM INSTR new #253 <Class StringBuilder>;
        s.StringBuilder();
        illegalargumentexception1.IllegalArgumentException(s.append("coordinate=").append(s1).toString());
        throw illegalargumentexception1;
        d = ((double)j * 3600D + 60D * d + d1) / 3600D;
        d1 = d;
        if(flag)
            d1 = -d;
        return d1;
    }

    public static String convert(double d, int i)
    {
        StringBuilder stringbuilder;
        DecimalFormat decimalformat;
label0:
        {
            while(d < -180D || d > 180D || Double.isNaN(d)) 
                throw new IllegalArgumentException((new StringBuilder()).append("coordinate=").append(d).toString());
            if(i != 0 && i != 1 && i != 2)
                throw new IllegalArgumentException((new StringBuilder()).append("outputType=").append(i).toString());
            stringbuilder = new StringBuilder();
            double d1 = d;
            if(d < 0.0D)
            {
                stringbuilder.append('-');
                d1 = -d;
            }
            decimalformat = new DecimalFormat("###.#####");
            if(i != 1)
            {
                d = d1;
                if(i != 2)
                    break label0;
            }
            int j = (int)Math.floor(d1);
            stringbuilder.append(j);
            stringbuilder.append(':');
            d1 = (d1 - (double)j) * 60D;
            d = d1;
            if(i == 2)
            {
                i = (int)Math.floor(d1);
                stringbuilder.append(i);
                stringbuilder.append(':');
                d = (d1 - (double)i) * 60D;
            }
        }
        stringbuilder.append(decimalformat.format(d));
        return stringbuilder.toString();
    }

    public static void distanceBetween(double d, double d1, double d2, double d3, 
            float af[])
    {
        if(af == null || af.length < 1)
            throw new IllegalArgumentException("results is null or has length < 1");
        BearingDistanceCache bearingdistancecache = (BearingDistanceCache)sBearingDistanceCache.get();
        computeDistanceAndBearing(d, d1, d2, d3, bearingdistancecache);
        af[0] = BearingDistanceCache._2D_get0(bearingdistancecache);
        if(af.length > 1)
        {
            af[1] = BearingDistanceCache._2D_get2(bearingdistancecache);
            if(af.length > 2)
                af[2] = BearingDistanceCache._2D_get1(bearingdistancecache);
        }
    }

    public float bearingTo(Location location)
    {
        BearingDistanceCache bearingdistancecache;
        bearingdistancecache = (BearingDistanceCache)sBearingDistanceCache.get();
        break MISSING_BLOCK_LABEL_10;
        if(mLatitude != BearingDistanceCache._2D_get3(bearingdistancecache) || mLongitude != BearingDistanceCache._2D_get5(bearingdistancecache) || location.mLatitude != BearingDistanceCache._2D_get4(bearingdistancecache) || location.mLongitude != BearingDistanceCache._2D_get6(bearingdistancecache))
            computeDistanceAndBearing(mLatitude, mLongitude, location.mLatitude, location.mLongitude, bearingdistancecache);
        return BearingDistanceCache._2D_get2(bearingdistancecache);
    }

    public int describeContents()
    {
        return 0;
    }

    public float distanceTo(Location location)
    {
        BearingDistanceCache bearingdistancecache;
        bearingdistancecache = (BearingDistanceCache)sBearingDistanceCache.get();
        break MISSING_BLOCK_LABEL_10;
        if(mLatitude != BearingDistanceCache._2D_get3(bearingdistancecache) || mLongitude != BearingDistanceCache._2D_get5(bearingdistancecache) || location.mLatitude != BearingDistanceCache._2D_get4(bearingdistancecache) || location.mLongitude != BearingDistanceCache._2D_get6(bearingdistancecache))
            computeDistanceAndBearing(mLatitude, mLongitude, location.mLatitude, location.mLongitude, bearingdistancecache);
        return BearingDistanceCache._2D_get0(bearingdistancecache);
    }

    public void dump(Printer printer, String s)
    {
        printer.println((new StringBuilder()).append(s).append(toString()).toString());
    }

    public float getAccuracy()
    {
        return mHorizontalAccuracyMeters;
    }

    public double getAltitude()
    {
        return mAltitude;
    }

    public float getBearing()
    {
        return mBearing;
    }

    public float getBearingAccuracyDegrees()
    {
        return mBearingAccuracyDegrees;
    }

    public long getElapsedRealtimeNanos()
    {
        return mElapsedRealtimeNanos;
    }

    public Location getExtraLocation(String s)
    {
        if(mExtras != null)
        {
            s = mExtras.getParcelable(s);
            if(s instanceof Location)
                return (Location)s;
        }
        return null;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public double getLatitude()
    {
        return mLatitude;
    }

    public double getLongitude()
    {
        return mLongitude;
    }

    public String getProvider()
    {
        return mProvider;
    }

    public float getSpeed()
    {
        return mSpeed;
    }

    public float getSpeedAccuracyMetersPerSecond()
    {
        return mSpeedAccuracyMetersPerSecond;
    }

    public long getTime()
    {
        return mTime;
    }

    public float getVerticalAccuracyMeters()
    {
        return mVerticalAccuracyMeters;
    }

    public boolean hasAccuracy()
    {
        boolean flag = false;
        if((mFieldsMask & 8) != 0)
            flag = true;
        return flag;
    }

    public boolean hasAltitude()
    {
        boolean flag = false;
        if((mFieldsMask & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean hasBearing()
    {
        boolean flag = false;
        if((mFieldsMask & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean hasBearingAccuracy()
    {
        boolean flag = false;
        if((mFieldsMask & 0x80) != 0)
            flag = true;
        return flag;
    }

    public boolean hasSpeed()
    {
        boolean flag = false;
        if((mFieldsMask & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean hasSpeedAccuracy()
    {
        boolean flag = false;
        if((mFieldsMask & 0x40) != 0)
            flag = true;
        return flag;
    }

    public boolean hasVerticalAccuracy()
    {
        boolean flag = false;
        if((mFieldsMask & 0x20) != 0)
            flag = true;
        return flag;
    }

    public boolean isComplete()
    {
        if(mProvider == null)
            return false;
        if(!hasAccuracy())
            return false;
        if(mTime == 0L)
            return false;
        return mElapsedRealtimeNanos != 0L;
    }

    public boolean isFromMockProvider()
    {
        boolean flag = false;
        if((mFieldsMask & 0x10) != 0)
            flag = true;
        return flag;
    }

    public void makeComplete()
    {
        if(mProvider == null)
            mProvider = "?";
        if(!hasAccuracy())
        {
            mFieldsMask = (byte)(mFieldsMask | 8);
            mHorizontalAccuracyMeters = 100F;
        }
        if(mTime == 0L)
            mTime = System.currentTimeMillis();
        if(mElapsedRealtimeNanos == 0L)
            mElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
    }

    public void removeAccuracy()
    {
        mHorizontalAccuracyMeters = 0.0F;
        mFieldsMask = (byte)(mFieldsMask & -9);
    }

    public void removeAltitude()
    {
        mAltitude = 0.0D;
        mFieldsMask = (byte)(mFieldsMask & -2);
    }

    public void removeBearing()
    {
        mBearing = 0.0F;
        mFieldsMask = (byte)(mFieldsMask & -5);
    }

    public void removeBearingAccuracy()
    {
        mBearingAccuracyDegrees = 0.0F;
        mFieldsMask = (byte)(mFieldsMask & 0xffffff7f);
    }

    public void removeSpeed()
    {
        mSpeed = 0.0F;
        mFieldsMask = (byte)(mFieldsMask & -3);
    }

    public void removeSpeedAccuracy()
    {
        mSpeedAccuracyMetersPerSecond = 0.0F;
        mFieldsMask = (byte)(mFieldsMask & 0xffffffbf);
    }

    public void removeVerticalAccuracy()
    {
        mVerticalAccuracyMeters = 0.0F;
        mFieldsMask = (byte)(mFieldsMask & 0xffffffdf);
    }

    public void reset()
    {
        mProvider = null;
        mTime = 0L;
        mElapsedRealtimeNanos = 0L;
        mFieldsMask = (byte)0;
        mLatitude = 0.0D;
        mLongitude = 0.0D;
        mAltitude = 0.0D;
        mSpeed = 0.0F;
        mBearing = 0.0F;
        mHorizontalAccuracyMeters = 0.0F;
        mVerticalAccuracyMeters = 0.0F;
        mSpeedAccuracyMetersPerSecond = 0.0F;
        mBearingAccuracyDegrees = 0.0F;
        mExtras = null;
    }

    public void set(Location location)
    {
        Object obj = null;
        mProvider = location.mProvider;
        mTime = location.mTime;
        mElapsedRealtimeNanos = location.mElapsedRealtimeNanos;
        mFieldsMask = location.mFieldsMask;
        mLatitude = location.mLatitude;
        mLongitude = location.mLongitude;
        mAltitude = location.mAltitude;
        mSpeed = location.mSpeed;
        mBearing = location.mBearing;
        mHorizontalAccuracyMeters = location.mHorizontalAccuracyMeters;
        mVerticalAccuracyMeters = location.mVerticalAccuracyMeters;
        mSpeedAccuracyMetersPerSecond = location.mSpeedAccuracyMetersPerSecond;
        mBearingAccuracyDegrees = location.mBearingAccuracyDegrees;
        if(location.mExtras == null)
            location = obj;
        else
            location = new Bundle(location.mExtras);
        mExtras = location;
    }

    public void setAccuracy(float f)
    {
        mHorizontalAccuracyMeters = f;
        mFieldsMask = (byte)(mFieldsMask | 8);
    }

    public void setAltitude(double d)
    {
        mAltitude = d;
        mFieldsMask = (byte)(mFieldsMask | 1);
    }

    public void setBearing(float f)
    {
        float f1;
        do
        {
            f1 = f;
            if(f >= 0.0F)
                break;
            f += 360F;
        } while(true);
        for(; f1 >= 360F; f1 -= 360F);
        mBearing = f1;
        mFieldsMask = (byte)(mFieldsMask | 4);
    }

    public void setBearingAccuracyDegrees(float f)
    {
        mBearingAccuracyDegrees = f;
        mFieldsMask = (byte)(mFieldsMask | 0x80);
    }

    public void setElapsedRealtimeNanos(long l)
    {
        mElapsedRealtimeNanos = l;
    }

    public void setExtraLocation(String s, Location location)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putParcelable(s, location);
    }

    public void setExtras(Bundle bundle)
    {
        Object obj = null;
        if(bundle == null)
            bundle = obj;
        else
            bundle = new Bundle(bundle);
        mExtras = bundle;
    }

    public void setIsFromMockProvider(boolean flag)
    {
        if(flag)
            mFieldsMask = (byte)(mFieldsMask | 0x10);
        else
            mFieldsMask = (byte)(mFieldsMask & 0xffffffef);
    }

    public void setLatitude(double d)
    {
        mLatitude = d;
    }

    public void setLongitude(double d)
    {
        mLongitude = d;
    }

    public void setProvider(String s)
    {
        mProvider = s;
    }

    public void setSpeed(float f)
    {
        mSpeed = f;
        mFieldsMask = (byte)(mFieldsMask | 2);
    }

    public void setSpeedAccuracyMetersPerSecond(float f)
    {
        mSpeedAccuracyMetersPerSecond = f;
        mFieldsMask = (byte)(mFieldsMask | 0x40);
    }

    public void setTime(long l)
    {
        mTime = l;
    }

    public void setVerticalAccuracyMeters(float f)
    {
        mVerticalAccuracyMeters = f;
        mFieldsMask = (byte)(mFieldsMask | 0x20);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Location[");
        stringbuilder.append(mProvider);
        stringbuilder.append(String.format(" %.6f,%.6f", new Object[] {
            Double.valueOf(mLatitude), Double.valueOf(mLongitude)
        }));
        if(hasAccuracy())
            stringbuilder.append(String.format(" hAcc=%.0f", new Object[] {
                Float.valueOf(mHorizontalAccuracyMeters)
            }));
        else
            stringbuilder.append(" hAcc=???");
        if(mTime == 0L)
            stringbuilder.append(" t=?!?");
        if(mElapsedRealtimeNanos == 0L)
        {
            stringbuilder.append(" et=?!?");
        } else
        {
            stringbuilder.append(" et=");
            TimeUtils.formatDuration(mElapsedRealtimeNanos / 0xf4240L, stringbuilder);
        }
        if(hasAltitude())
            stringbuilder.append(" alt=").append(mAltitude);
        if(hasSpeed())
            stringbuilder.append(" vel=").append(mSpeed);
        if(hasBearing())
            stringbuilder.append(" bear=").append(mBearing);
        if(hasVerticalAccuracy())
            stringbuilder.append(String.format(" vAcc=%.0f", new Object[] {
                Float.valueOf(mVerticalAccuracyMeters)
            }));
        else
            stringbuilder.append(" vAcc=???");
        if(hasSpeedAccuracy())
            stringbuilder.append(String.format(" sAcc=%.0f", new Object[] {
                Float.valueOf(mSpeedAccuracyMetersPerSecond)
            }));
        else
            stringbuilder.append(" sAcc=???");
        if(hasBearingAccuracy())
            stringbuilder.append(String.format(" bAcc=%.0f", new Object[] {
                Float.valueOf(mBearingAccuracyDegrees)
            }));
        else
            stringbuilder.append(" bAcc=???");
        if(isFromMockProvider())
            stringbuilder.append(" mock");
        if(mExtras != null)
            stringbuilder.append(" {").append(mExtras).append('}');
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mProvider);
        parcel.writeLong(mTime);
        parcel.writeLong(mElapsedRealtimeNanos);
        parcel.writeByte(mFieldsMask);
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
        parcel.writeDouble(mAltitude);
        parcel.writeFloat(mSpeed);
        parcel.writeFloat(mBearing);
        parcel.writeFloat(mHorizontalAccuracyMeters);
        parcel.writeFloat(mVerticalAccuracyMeters);
        parcel.writeFloat(mSpeedAccuracyMetersPerSecond);
        parcel.writeFloat(mBearingAccuracyDegrees);
        parcel.writeBundle(mExtras);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Location createFromParcel(Parcel parcel)
        {
            Location location = new Location(parcel.readString());
            Location._2D_set11(location, parcel.readLong());
            Location._2D_set3(location, parcel.readLong());
            Location._2D_set5(location, parcel.readByte());
            Location._2D_set7(location, parcel.readDouble());
            Location._2D_set8(location, parcel.readDouble());
            Location._2D_set0(location, parcel.readDouble());
            Location._2D_set9(location, parcel.readFloat());
            Location._2D_set1(location, parcel.readFloat());
            Location._2D_set6(location, parcel.readFloat());
            Location._2D_set12(location, parcel.readFloat());
            Location._2D_set10(location, parcel.readFloat());
            Location._2D_set2(location, parcel.readFloat());
            Location._2D_set4(location, Bundle.setDefusable(parcel.readBundle(), true));
            return location;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Location[] newArray(int i)
        {
            return new Location[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_COARSE_LOCATION = "coarseLocation";
    public static final String EXTRA_NO_GPS_LOCATION = "noGPSLocation";
    public static final int FORMAT_DEGREES = 0;
    public static final int FORMAT_MINUTES = 1;
    public static final int FORMAT_SECONDS = 2;
    private static final int HAS_ALTITUDE_MASK = 1;
    private static final int HAS_BEARING_ACCURACY_MASK = 128;
    private static final int HAS_BEARING_MASK = 4;
    private static final int HAS_HORIZONTAL_ACCURACY_MASK = 8;
    private static final int HAS_MOCK_PROVIDER_MASK = 16;
    private static final int HAS_SPEED_ACCURACY_MASK = 64;
    private static final int HAS_SPEED_MASK = 2;
    private static final int HAS_VERTICAL_ACCURACY_MASK = 32;
    private static ThreadLocal sBearingDistanceCache = new ThreadLocal() {

        protected BearingDistanceCache initialValue()
        {
            return new BearingDistanceCache(null);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;
    private double mAltitude;
    private float mBearing;
    private float mBearingAccuracyDegrees;
    private long mElapsedRealtimeNanos;
    private Bundle mExtras;
    private byte mFieldsMask;
    private float mHorizontalAccuracyMeters;
    private double mLatitude;
    private double mLongitude;
    private String mProvider;
    private float mSpeed;
    private float mSpeedAccuracyMetersPerSecond;
    private long mTime;
    private float mVerticalAccuracyMeters;

}
