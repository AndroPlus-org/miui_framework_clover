// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.util.SparseArray;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package android.location:
//            GpsSatellite, GnssStatus

public final class GpsStatus
{
    public static interface Listener
    {

        public abstract void onGpsStatusChanged(int i);
    }

    public static interface NmeaListener
    {

        public abstract void onNmeaReceived(long l, String s);
    }

    private final class SatelliteIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            for(; mIndex < mSatellitesCount; mIndex = mIndex + 1)
                if(((GpsSatellite)GpsStatus._2D_get0(GpsStatus.this).valueAt(mIndex)).mValid)
                    return true;

            return false;
        }

        public GpsSatellite next()
        {
            while(mIndex < mSatellitesCount) 
            {
                GpsSatellite gpssatellite = (GpsSatellite)GpsStatus._2D_get0(GpsStatus.this).valueAt(mIndex);
                mIndex = mIndex + 1;
                if(gpssatellite.mValid)
                    return gpssatellite;
            }
            throw new NoSuchElementException();
        }

        public volatile Object next()
        {
            return next();
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private int mIndex;
        private final int mSatellitesCount;
        final GpsStatus this$0;

        SatelliteIterator()
        {
            this$0 = GpsStatus.this;
            super();
            mIndex = 0;
            mSatellitesCount = GpsStatus._2D_get0(GpsStatus.this).size();
        }
    }


    static SparseArray _2D_get0(GpsStatus gpsstatus)
    {
        return gpsstatus.mSatellites;
    }

    GpsStatus()
    {
        mSatelliteList = new Iterable() {

            public Iterator iterator()
            {
                return new SatelliteIterator();
            }

            final GpsStatus this$0;

            
            {
                this$0 = GpsStatus.this;
                super();
            }
        }
;
    }

    private void clearSatellites()
    {
        int i = mSatellites.size();
        for(int j = 0; j < i; j++)
            ((GpsSatellite)mSatellites.valueAt(j)).mValid = false;

    }

    private void setStatus(int i, int ai[], float af[], float af1[], float af2[])
    {
        int j;
        clearSatellites();
        j = 0;
_L8:
        if(j >= i) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        k = ai[j] >> 4 & 0xf;
        l = ai[j] >> 8;
        if(k != 3) goto _L4; else goto _L3
_L3:
        int i1 = l + 64;
_L6:
        if(i1 > 0 && i1 <= 337)
        {
            GpsSatellite gpssatellite = (GpsSatellite)mSatellites.get(i1);
            GpsSatellite gpssatellite1 = gpssatellite;
            if(gpssatellite == null)
            {
                gpssatellite1 = new GpsSatellite(i1);
                mSatellites.put(i1, gpssatellite1);
            }
            gpssatellite1.mValid = true;
            gpssatellite1.mSnr = af[j];
            gpssatellite1.mElevation = af1[j];
            gpssatellite1.mAzimuth = af2[j];
            boolean flag;
            if((ai[j] & 1) != 0)
                flag = true;
            else
                flag = false;
            gpssatellite1.mHasEphemeris = flag;
            if((ai[j] & 2) != 0)
                flag = true;
            else
                flag = false;
            gpssatellite1.mHasAlmanac = flag;
            if((ai[j] & 4) != 0)
                flag = true;
            else
                flag = false;
            gpssatellite1.mUsedInFix = flag;
        }
_L5:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        if(k == 5)
        {
            i1 = l + 200;
            break; /* Loop/switch isn't completed */
        }
        if(k == 2)
        {
            i1 = l - 87;
            break; /* Loop/switch isn't completed */
        }
        if(k == 6)
        {
            i1 = l + 300;
            break; /* Loop/switch isn't completed */
        }
        i1 = l;
        if(k == 1)
            break; /* Loop/switch isn't completed */
        i1 = l;
        if(k == 4) goto _L6; else goto _L5
_L2:
        return;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public int getMaxSatellites()
    {
        return 337;
    }

    public Iterable getSatellites()
    {
        return mSatelliteList;
    }

    public int getTimeToFirstFix()
    {
        return mTimeToFirstFix;
    }

    void setStatus(GnssStatus gnssstatus, int i)
    {
        mTimeToFirstFix = i;
        setStatus(gnssstatus.mSvCount, gnssstatus.mSvidWithFlags, gnssstatus.mCn0DbHz, gnssstatus.mElevations, gnssstatus.mAzimuths);
    }

    void setTimeToFirstFix(int i)
    {
        mTimeToFirstFix = i;
    }

    private static final int BEIDOU_SVID_OFFSET = 200;
    private static final int GALILEO_SVID_OFFSET = 300;
    private static final int GLONASS_SVID_OFFSET = 64;
    public static final int GPS_EVENT_FIRST_FIX = 3;
    public static final int GPS_EVENT_SATELLITE_STATUS = 4;
    public static final int GPS_EVENT_STARTED = 1;
    public static final int GPS_EVENT_STOPPED = 2;
    private static final int NUM_SATELLITES = 337;
    private static final int SBAS_SVID_OFFSET = -87;
    private Iterable mSatelliteList;
    private final SparseArray mSatellites = new SparseArray();
    private int mTimeToFirstFix;
}
