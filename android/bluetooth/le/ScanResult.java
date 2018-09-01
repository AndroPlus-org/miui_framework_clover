// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

// Referenced classes of package android.bluetooth.le:
//            ScanRecord

public final class ScanResult
    implements Parcelable
{

    public ScanResult(BluetoothDevice bluetoothdevice, int i, int j, int k, int l, int i1, int j1, 
            int k1, ScanRecord scanrecord, long l1)
    {
        mDevice = bluetoothdevice;
        mEventType = i;
        mPrimaryPhy = j;
        mSecondaryPhy = k;
        mAdvertisingSid = l;
        mTxPower = i1;
        mRssi = j1;
        mPeriodicAdvertisingInterval = k1;
        mScanRecord = scanrecord;
        mTimestampNanos = l1;
    }

    public ScanResult(BluetoothDevice bluetoothdevice, ScanRecord scanrecord, int i, long l)
    {
        mDevice = bluetoothdevice;
        mScanRecord = scanrecord;
        mRssi = i;
        mTimestampNanos = l;
        mEventType = 17;
        mPrimaryPhy = 1;
        mSecondaryPhy = 0;
        mAdvertisingSid = 255;
        mTxPower = 127;
        mPeriodicAdvertisingInterval = 0;
    }

    private ScanResult(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    ScanResult(Parcel parcel, ScanResult scanresult)
    {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        if(parcel.readInt() == 1)
            mDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() == 1)
            mScanRecord = ScanRecord.parseFromBytes(parcel.createByteArray());
        mRssi = parcel.readInt();
        mTimestampNanos = parcel.readLong();
        mEventType = parcel.readInt();
        mPrimaryPhy = parcel.readInt();
        mSecondaryPhy = parcel.readInt();
        mAdvertisingSid = parcel.readInt();
        mTxPower = parcel.readInt();
        mPeriodicAdvertisingInterval = parcel.readInt();
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
        obj = (ScanResult)obj;
        if(Objects.equals(mDevice, ((ScanResult) (obj)).mDevice) && mRssi == ((ScanResult) (obj)).mRssi && Objects.equals(mScanRecord, ((ScanResult) (obj)).mScanRecord) && mTimestampNanos == ((ScanResult) (obj)).mTimestampNanos && mEventType == ((ScanResult) (obj)).mEventType && mPrimaryPhy == ((ScanResult) (obj)).mPrimaryPhy && mSecondaryPhy == ((ScanResult) (obj)).mSecondaryPhy && mAdvertisingSid == ((ScanResult) (obj)).mAdvertisingSid && mTxPower == ((ScanResult) (obj)).mTxPower)
        {
            if(mPeriodicAdvertisingInterval != ((ScanResult) (obj)).mPeriodicAdvertisingInterval)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int getAdvertisingSid()
    {
        return mAdvertisingSid;
    }

    public int getDataStatus()
    {
        return mEventType >> 5 & 3;
    }

    public BluetoothDevice getDevice()
    {
        return mDevice;
    }

    public int getPeriodicAdvertisingInterval()
    {
        return mPeriodicAdvertisingInterval;
    }

    public int getPrimaryPhy()
    {
        return mPrimaryPhy;
    }

    public int getRssi()
    {
        return mRssi;
    }

    public ScanRecord getScanRecord()
    {
        return mScanRecord;
    }

    public int getSecondaryPhy()
    {
        return mSecondaryPhy;
    }

    public long getTimestampNanos()
    {
        return mTimestampNanos;
    }

    public int getTxPower()
    {
        return mTxPower;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mDevice, Integer.valueOf(mRssi), mScanRecord, Long.valueOf(mTimestampNanos), Integer.valueOf(mEventType), Integer.valueOf(mPrimaryPhy), Integer.valueOf(mSecondaryPhy), Integer.valueOf(mAdvertisingSid), Integer.valueOf(mTxPower), Integer.valueOf(mPeriodicAdvertisingInterval)
        });
    }

    public boolean isConnectable()
    {
        boolean flag = false;
        if((mEventType & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isLegacy()
    {
        boolean flag = false;
        if((mEventType & 0x10) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ScanResult{device=").append(mDevice).append(", scanRecord=").append(Objects.toString(mScanRecord)).append(", rssi=").append(mRssi).append(", timestampNanos=").append(mTimestampNanos).append(", eventType=").append(mEventType).append(", primaryPhy=").append(mPrimaryPhy).append(", secondaryPhy=").append(mSecondaryPhy).append(", advertisingSid=").append(mAdvertisingSid).append(", txPower=").append(mTxPower).append(", periodicAdvertisingInterval=").append(mPeriodicAdvertisingInterval).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mDevice != null)
        {
            parcel.writeInt(1);
            mDevice.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(mScanRecord != null)
        {
            parcel.writeInt(1);
            parcel.writeByteArray(mScanRecord.getBytes());
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mRssi);
        parcel.writeLong(mTimestampNanos);
        parcel.writeInt(mEventType);
        parcel.writeInt(mPrimaryPhy);
        parcel.writeInt(mSecondaryPhy);
        parcel.writeInt(mAdvertisingSid);
        parcel.writeInt(mTxPower);
        parcel.writeInt(mPeriodicAdvertisingInterval);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ScanResult createFromParcel(Parcel parcel)
        {
            return new ScanResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ScanResult[] newArray(int i)
        {
            return new ScanResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DATA_COMPLETE = 0;
    public static final int DATA_TRUNCATED = 2;
    private static final int ET_CONNECTABLE_MASK = 1;
    private static final int ET_LEGACY_MASK = 16;
    public static final int PERIODIC_INTERVAL_NOT_PRESENT = 0;
    public static final int PHY_UNUSED = 0;
    public static final int SID_NOT_PRESENT = 255;
    public static final int TX_POWER_NOT_PRESENT = 127;
    private int mAdvertisingSid;
    private BluetoothDevice mDevice;
    private int mEventType;
    private int mPeriodicAdvertisingInterval;
    private int mPrimaryPhy;
    private int mRssi;
    private ScanRecord mScanRecord;
    private int mSecondaryPhy;
    private long mTimestampNanos;
    private int mTxPower;

}
