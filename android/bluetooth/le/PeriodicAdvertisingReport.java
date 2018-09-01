// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

// Referenced classes of package android.bluetooth.le:
//            ScanRecord

public final class PeriodicAdvertisingReport
    implements Parcelable
{

    public PeriodicAdvertisingReport(int i, int j, int k, int l, ScanRecord scanrecord)
    {
        syncHandle = i;
        txPower = j;
        rssi = k;
        dataStatus = l;
        data = scanrecord;
    }

    private PeriodicAdvertisingReport(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    PeriodicAdvertisingReport(Parcel parcel, PeriodicAdvertisingReport periodicadvertisingreport)
    {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        syncHandle = parcel.readInt();
        txPower = parcel.readInt();
        rssi = parcel.readInt();
        dataStatus = parcel.readInt();
        if(parcel.readInt() == 1)
            data = ScanRecord.parseFromBytes(parcel.createByteArray());
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (PeriodicAdvertisingReport)obj;
        if(syncHandle == ((PeriodicAdvertisingReport) (obj)).syncHandle && txPower == ((PeriodicAdvertisingReport) (obj)).txPower && rssi == ((PeriodicAdvertisingReport) (obj)).rssi && dataStatus == ((PeriodicAdvertisingReport) (obj)).dataStatus && Objects.equals(data, ((PeriodicAdvertisingReport) (obj)).data))
        {
            if(timestampNanos != ((PeriodicAdvertisingReport) (obj)).timestampNanos)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public ScanRecord getData()
    {
        return data;
    }

    public int getDataStatus()
    {
        return dataStatus;
    }

    public int getRssi()
    {
        return rssi;
    }

    public int getSyncHandle()
    {
        return syncHandle;
    }

    public long getTimestampNanos()
    {
        return timestampNanos;
    }

    public int getTxPower()
    {
        return txPower;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(syncHandle), Integer.valueOf(txPower), Integer.valueOf(rssi), Integer.valueOf(dataStatus), data, Long.valueOf(timestampNanos)
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("PeriodicAdvertisingReport{syncHandle=").append(syncHandle).append(", txPower=").append(txPower).append(", rssi=").append(rssi).append(", dataStatus=").append(dataStatus).append(", data=").append(Objects.toString(data)).append(", timestampNanos=").append(timestampNanos).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(syncHandle);
        parcel.writeInt(txPower);
        parcel.writeInt(rssi);
        parcel.writeInt(dataStatus);
        if(data != null)
        {
            parcel.writeInt(1);
            parcel.writeByteArray(data.getBytes());
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PeriodicAdvertisingReport createFromParcel(Parcel parcel)
        {
            return new PeriodicAdvertisingReport(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PeriodicAdvertisingReport[] newArray(int i)
        {
            return new PeriodicAdvertisingReport[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DATA_COMPLETE = 0;
    public static final int DATA_INCOMPLETE_TRUNCATED = 2;
    private ScanRecord data;
    private int dataStatus;
    private int rssi;
    private int syncHandle;
    private long timestampNanos;
    private int txPower;

}
