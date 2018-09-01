// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

// Referenced classes of package android.net.wifi:
//            ScanResult

public class BatchedScanResult
    implements Parcelable
{

    public BatchedScanResult()
    {
        scanResults = new ArrayList();
    }

    public BatchedScanResult(BatchedScanResult batchedscanresult)
    {
        scanResults = new ArrayList();
        truncated = batchedscanresult.truncated;
        for(Iterator iterator = batchedscanresult.scanResults.iterator(); iterator.hasNext(); scanResults.add(new ScanResult(batchedscanresult)))
            batchedscanresult = (ScanResult)iterator.next();

    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("BatchedScanResult: ").append("truncated: ").append(String.valueOf(truncated)).append("scanResults: [");
        ScanResult scanresult;
        for(Iterator iterator = scanResults.iterator(); iterator.hasNext(); stringbuffer.append(" <").append(scanresult.toString()).append("> "))
            scanresult = (ScanResult)iterator.next();

        stringbuffer.append(" ]");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if(truncated)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(scanResults.size());
        for(Iterator iterator = scanResults.iterator(); iterator.hasNext(); ((ScanResult)iterator.next()).writeToParcel(parcel, i));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BatchedScanResult createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            BatchedScanResult batchedscanresult = new BatchedScanResult();
            if(parcel.readInt() != 1)
                flag = false;
            batchedscanresult.truncated = flag;
            for(int i = parcel.readInt(); i > 0; i--)
                batchedscanresult.scanResults.add((ScanResult)ScanResult.CREATOR.createFromParcel(parcel));

            return batchedscanresult;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BatchedScanResult[] newArray(int i)
        {
            return new BatchedScanResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "BatchedScanResult";
    public final List scanResults;
    public boolean truncated;

}
