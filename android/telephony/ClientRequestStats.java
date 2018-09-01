// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.*;

// Referenced classes of package android.telephony:
//            TelephonyHistogram

public final class ClientRequestStats
    implements Parcelable
{

    public ClientRequestStats()
    {
        mCompletedRequestsWakelockTime = 0L;
        mCompletedRequestsCount = 0L;
        mPendingRequestsWakelockTime = 0L;
        mPendingRequestsCount = 0L;
        mRequestHistograms = new SparseArray();
    }

    public ClientRequestStats(Parcel parcel)
    {
        mCompletedRequestsWakelockTime = 0L;
        mCompletedRequestsCount = 0L;
        mPendingRequestsWakelockTime = 0L;
        mPendingRequestsCount = 0L;
        mRequestHistograms = new SparseArray();
        readFromParcel(parcel);
    }

    public ClientRequestStats(ClientRequestStats clientrequeststats)
    {
        mCompletedRequestsWakelockTime = 0L;
        mCompletedRequestsCount = 0L;
        mPendingRequestsWakelockTime = 0L;
        mPendingRequestsCount = 0L;
        mRequestHistograms = new SparseArray();
        mCallingPackage = clientrequeststats.getCallingPackage();
        mCompletedRequestsCount = clientrequeststats.getCompletedRequestsCount();
        mCompletedRequestsWakelockTime = clientrequeststats.getCompletedRequestsWakelockTime();
        mPendingRequestsCount = clientrequeststats.getPendingRequestsCount();
        mPendingRequestsWakelockTime = clientrequeststats.getPendingRequestsWakelockTime();
        TelephonyHistogram telephonyhistogram;
        for(clientrequeststats = clientrequeststats.getRequestHistograms().iterator(); clientrequeststats.hasNext(); mRequestHistograms.put(telephonyhistogram.getId(), telephonyhistogram))
            telephonyhistogram = (TelephonyHistogram)clientrequeststats.next();

    }

    public void addCompletedWakelockTime(long l)
    {
        mCompletedRequestsWakelockTime = mCompletedRequestsWakelockTime + l;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getCallingPackage()
    {
        return mCallingPackage;
    }

    public long getCompletedRequestsCount()
    {
        return mCompletedRequestsCount;
    }

    public long getCompletedRequestsWakelockTime()
    {
        return mCompletedRequestsWakelockTime;
    }

    public long getPendingRequestsCount()
    {
        return mPendingRequestsCount;
    }

    public long getPendingRequestsWakelockTime()
    {
        return mPendingRequestsWakelockTime;
    }

    public List getRequestHistograms()
    {
        SparseArray sparsearray = mRequestHistograms;
        sparsearray;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = JVM INSTR new #105 <Class ArrayList>;
        arraylist.ArrayList(mRequestHistograms.size());
        int i = 0;
_L2:
        if(i >= mRequestHistograms.size())
            break; /* Loop/switch isn't completed */
        TelephonyHistogram telephonyhistogram = JVM INSTR new #92  <Class TelephonyHistogram>;
        telephonyhistogram.TelephonyHistogram((TelephonyHistogram)mRequestHistograms.valueAt(i));
        arraylist.add(telephonyhistogram);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public void incrementCompletedRequestsCount()
    {
        mCompletedRequestsCount = mCompletedRequestsCount + 1L;
    }

    public void readFromParcel(Parcel parcel)
    {
        mCallingPackage = parcel.readString();
        mCompletedRequestsWakelockTime = parcel.readLong();
        mCompletedRequestsCount = parcel.readLong();
        mPendingRequestsWakelockTime = parcel.readLong();
        mPendingRequestsCount = parcel.readLong();
        Object obj = new ArrayList();
        parcel.readTypedList(((List) (obj)), TelephonyHistogram.CREATOR);
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); mRequestHistograms.put(parcel.getId(), parcel))
            parcel = (TelephonyHistogram)((Iterator) (obj)).next();

    }

    public void setCallingPackage(String s)
    {
        mCallingPackage = s;
    }

    public void setPendingRequestsCount(long l)
    {
        mPendingRequestsCount = l;
    }

    public void setPendingRequestsWakelockTime(long l)
    {
        mPendingRequestsWakelockTime = l;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ClientRequestStats{mCallingPackage='").append(mCallingPackage).append('\'').append(", mCompletedRequestsWakelockTime=").append(mCompletedRequestsWakelockTime).append(", mCompletedRequestsCount=").append(mCompletedRequestsCount).append(", mPendingRequestsWakelockTime=").append(mPendingRequestsWakelockTime).append(", mPendingRequestsCount=").append(mPendingRequestsCount).append('}').toString();
    }

    public void updateRequestHistograms(int i, int j)
    {
        SparseArray sparsearray = mRequestHistograms;
        sparsearray;
        JVM INSTR monitorenter ;
        TelephonyHistogram telephonyhistogram = (TelephonyHistogram)mRequestHistograms.get(i);
        TelephonyHistogram telephonyhistogram1;
        telephonyhistogram1 = telephonyhistogram;
        if(telephonyhistogram != null)
            break MISSING_BLOCK_LABEL_52;
        telephonyhistogram1 = JVM INSTR new #92  <Class TelephonyHistogram>;
        telephonyhistogram1.TelephonyHistogram(1, i, 5);
        mRequestHistograms.put(i, telephonyhistogram1);
        telephonyhistogram1.addTimeTaken(j);
        sparsearray;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mCallingPackage);
        parcel.writeLong(mCompletedRequestsWakelockTime);
        parcel.writeLong(mCompletedRequestsCount);
        parcel.writeLong(mPendingRequestsWakelockTime);
        parcel.writeLong(mPendingRequestsCount);
        parcel.writeTypedList(getRequestHistograms());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ClientRequestStats createFromParcel(Parcel parcel)
        {
            return new ClientRequestStats(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ClientRequestStats[] newArray(int i)
        {
            return new ClientRequestStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int REQUEST_HISTOGRAM_BUCKET_COUNT = 5;
    private String mCallingPackage;
    private long mCompletedRequestsCount;
    private long mCompletedRequestsWakelockTime;
    private long mPendingRequestsCount;
    private long mPendingRequestsWakelockTime;
    private SparseArray mRequestHistograms;

}
