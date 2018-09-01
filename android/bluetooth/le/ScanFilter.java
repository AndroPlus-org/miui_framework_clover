// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.*;
import com.android.internal.util.BitUtils;
import java.util.*;

// Referenced classes of package android.bluetooth.le:
//            ScanResult, ScanRecord

public final class ScanFilter
    implements Parcelable
{
    public static final class Builder
    {

        public ScanFilter build()
        {
            return new ScanFilter(mDeviceName, mDeviceAddress, mServiceUuid, mUuidMask, mServiceDataUuid, mServiceData, mServiceDataMask, mManufacturerId, mManufacturerData, mManufacturerDataMask, null);
        }

        public Builder setDeviceAddress(String s)
        {
            if(s != null && BluetoothAdapter.checkBluetoothAddress(s) ^ true)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid device address ").append(s).toString());
            } else
            {
                mDeviceAddress = s;
                return this;
            }
        }

        public Builder setDeviceName(String s)
        {
            mDeviceName = s;
            return this;
        }

        public Builder setManufacturerData(int i, byte abyte0[])
        {
            if(abyte0 != null && i < 0)
            {
                throw new IllegalArgumentException("invalid manufacture id");
            } else
            {
                mManufacturerId = i;
                mManufacturerData = abyte0;
                mManufacturerDataMask = null;
                return this;
            }
        }

        public Builder setManufacturerData(int i, byte abyte0[], byte abyte1[])
        {
            if(abyte0 != null && i < 0)
                throw new IllegalArgumentException("invalid manufacture id");
            if(mManufacturerDataMask != null)
            {
                if(mManufacturerData == null)
                    throw new IllegalArgumentException("manufacturerData is null while manufacturerDataMask is not null");
                if(mManufacturerData.length != mManufacturerDataMask.length)
                    throw new IllegalArgumentException("size mismatch for manufacturerData and manufacturerDataMask");
            }
            mManufacturerId = i;
            mManufacturerData = abyte0;
            mManufacturerDataMask = abyte1;
            return this;
        }

        public Builder setServiceData(ParcelUuid parceluuid, byte abyte0[])
        {
            if(parceluuid == null)
            {
                throw new IllegalArgumentException("serviceDataUuid is null");
            } else
            {
                mServiceDataUuid = parceluuid;
                mServiceData = abyte0;
                mServiceDataMask = null;
                return this;
            }
        }

        public Builder setServiceData(ParcelUuid parceluuid, byte abyte0[], byte abyte1[])
        {
            if(parceluuid == null)
                throw new IllegalArgumentException("serviceDataUuid is null");
            if(mServiceDataMask != null)
            {
                if(mServiceData == null)
                    throw new IllegalArgumentException("serviceData is null while serviceDataMask is not null");
                if(mServiceData.length != mServiceDataMask.length)
                    throw new IllegalArgumentException("size mismatch for service data and service data mask");
            }
            mServiceDataUuid = parceluuid;
            mServiceData = abyte0;
            mServiceDataMask = abyte1;
            return this;
        }

        public Builder setServiceUuid(ParcelUuid parceluuid)
        {
            mServiceUuid = parceluuid;
            mUuidMask = null;
            return this;
        }

        public Builder setServiceUuid(ParcelUuid parceluuid, ParcelUuid parceluuid1)
        {
            if(mUuidMask != null && mServiceUuid == null)
            {
                throw new IllegalArgumentException("uuid is null while uuidMask is not null!");
            } else
            {
                mServiceUuid = parceluuid;
                mUuidMask = parceluuid1;
                return this;
            }
        }

        private String mDeviceAddress;
        private String mDeviceName;
        private byte mManufacturerData[];
        private byte mManufacturerDataMask[];
        private int mManufacturerId;
        private byte mServiceData[];
        private byte mServiceDataMask[];
        private ParcelUuid mServiceDataUuid;
        private ParcelUuid mServiceUuid;
        private ParcelUuid mUuidMask;

        public Builder()
        {
            mManufacturerId = -1;
        }
    }


    private ScanFilter(String s, String s1, ParcelUuid parceluuid, ParcelUuid parceluuid1, ParcelUuid parceluuid2, byte abyte0[], byte abyte1[], 
            int i, byte abyte2[], byte abyte3[])
    {
        mDeviceName = s;
        mServiceUuid = parceluuid;
        mServiceUuidMask = parceluuid1;
        mDeviceAddress = s1;
        mServiceDataUuid = parceluuid2;
        mServiceData = abyte0;
        mServiceDataMask = abyte1;
        mManufacturerId = i;
        mManufacturerData = abyte2;
        mManufacturerDataMask = abyte3;
    }

    ScanFilter(String s, String s1, ParcelUuid parceluuid, ParcelUuid parceluuid1, ParcelUuid parceluuid2, byte abyte0[], byte abyte1[], 
            int i, byte abyte2[], byte abyte3[], ScanFilter scanfilter)
    {
        this(s, s1, parceluuid, parceluuid1, parceluuid2, abyte0, abyte1, i, abyte2, abyte3);
    }

    private boolean matchesPartialData(byte abyte0[], byte abyte1[], byte abyte2[])
    {
        if(abyte2 == null || abyte2.length < abyte0.length)
            return false;
        if(abyte1 == null)
        {
            for(int i = 0; i < abyte0.length; i++)
                if(abyte2[i] != abyte0[i])
                    return false;

            return true;
        }
        for(int j = 0; j < abyte0.length; j++)
            if((abyte1[j] & abyte2[j]) != (abyte1[j] & abyte0[j]))
                return false;

        return true;
    }

    private static boolean matchesServiceUuid(UUID uuid, UUID uuid1, UUID uuid2)
    {
        return BitUtils.maskedEquals(uuid2, uuid, uuid1);
    }

    public static boolean matchesServiceUuids(ParcelUuid parceluuid, ParcelUuid parceluuid1, List list)
    {
        if(parceluuid == null)
            return true;
        if(list == null)
            return false;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            ParcelUuid parceluuid2 = (ParcelUuid)iterator.next();
            if(parceluuid1 == null)
                list = null;
            else
                list = parceluuid1.getUuid();
            if(matchesServiceUuid(parceluuid.getUuid(), list, parceluuid2.getUuid()))
                return true;
        }

        return false;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (ScanFilter)obj;
        boolean flag1 = flag;
        if(Objects.equals(mDeviceName, ((ScanFilter) (obj)).mDeviceName))
        {
            flag1 = flag;
            if(Objects.equals(mDeviceAddress, ((ScanFilter) (obj)).mDeviceAddress))
            {
                flag1 = flag;
                if(mManufacturerId == ((ScanFilter) (obj)).mManufacturerId)
                {
                    flag1 = flag;
                    if(Objects.deepEquals(mManufacturerData, ((ScanFilter) (obj)).mManufacturerData))
                    {
                        flag1 = flag;
                        if(Objects.deepEquals(mManufacturerDataMask, ((ScanFilter) (obj)).mManufacturerDataMask))
                        {
                            flag1 = flag;
                            if(Objects.equals(mServiceDataUuid, ((ScanFilter) (obj)).mServiceDataUuid))
                            {
                                flag1 = flag;
                                if(Objects.deepEquals(mServiceData, ((ScanFilter) (obj)).mServiceData))
                                {
                                    flag1 = flag;
                                    if(Objects.deepEquals(mServiceDataMask, ((ScanFilter) (obj)).mServiceDataMask))
                                    {
                                        flag1 = flag;
                                        if(Objects.equals(mServiceUuid, ((ScanFilter) (obj)).mServiceUuid))
                                            flag1 = Objects.equals(mServiceUuidMask, ((ScanFilter) (obj)).mServiceUuidMask);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public String getDeviceAddress()
    {
        return mDeviceAddress;
    }

    public String getDeviceName()
    {
        return mDeviceName;
    }

    public byte[] getManufacturerData()
    {
        return mManufacturerData;
    }

    public byte[] getManufacturerDataMask()
    {
        return mManufacturerDataMask;
    }

    public int getManufacturerId()
    {
        return mManufacturerId;
    }

    public byte[] getServiceData()
    {
        return mServiceData;
    }

    public byte[] getServiceDataMask()
    {
        return mServiceDataMask;
    }

    public ParcelUuid getServiceDataUuid()
    {
        return mServiceDataUuid;
    }

    public ParcelUuid getServiceUuid()
    {
        return mServiceUuid;
    }

    public ParcelUuid getServiceUuidMask()
    {
        return mServiceUuidMask;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mDeviceName, mDeviceAddress, Integer.valueOf(mManufacturerId), Integer.valueOf(Arrays.hashCode(mManufacturerData)), Integer.valueOf(Arrays.hashCode(mManufacturerDataMask)), mServiceDataUuid, Integer.valueOf(Arrays.hashCode(mServiceData)), Integer.valueOf(Arrays.hashCode(mServiceDataMask)), mServiceUuid, mServiceUuidMask
        });
    }

    public boolean isAllFieldsEmpty()
    {
        return EMPTY.equals(this);
    }

    public boolean matches(ScanResult scanresult)
    {
        if(scanresult == null)
            return false;
        BluetoothDevice bluetoothdevice = scanresult.getDevice();
        if(mDeviceAddress != null && (bluetoothdevice == null || mDeviceAddress.equals(bluetoothdevice.getAddress()) ^ true))
            return false;
        for(scanresult = scanresult.getScanRecord(); scanresult == null && (mDeviceName != null || mServiceUuid != null || mManufacturerData != null || mServiceData != null);)
            return false;

        if(mDeviceName != null && mDeviceName.equals(scanresult.getDeviceName()) ^ true)
            return false;
        if(mServiceUuid != null && matchesServiceUuids(mServiceUuid, mServiceUuidMask, scanresult.getServiceUuids()) ^ true)
            return false;
        if(mServiceDataUuid != null && !matchesPartialData(mServiceData, mServiceDataMask, scanresult.getServiceData(mServiceDataUuid)))
            return false;
        return mManufacturerId < 0 || matchesPartialData(mManufacturerData, mManufacturerDataMask, scanresult.getManufacturerSpecificData(mManufacturerId));
    }

    public String toString()
    {
        return (new StringBuilder()).append("BluetoothLeScanFilter [mDeviceName=").append(mDeviceName).append(", mDeviceAddress=").append(mDeviceAddress).append(", mUuid=").append(mServiceUuid).append(", mUuidMask=").append(mServiceUuidMask).append(", mServiceDataUuid=").append(Objects.toString(mServiceDataUuid)).append(", mServiceData=").append(Arrays.toString(mServiceData)).append(", mServiceDataMask=").append(Arrays.toString(mServiceDataMask)).append(", mManufacturerId=").append(mManufacturerId).append(", mManufacturerData=").append(Arrays.toString(mManufacturerData)).append(", mManufacturerDataMask=").append(Arrays.toString(mManufacturerDataMask)).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = false;
        int j;
        if(mDeviceName == null)
            j = 0;
        else
            j = 1;
        parcel.writeInt(j);
        if(mDeviceName != null)
            parcel.writeString(mDeviceName);
        if(mDeviceAddress == null)
            j = 0;
        else
            j = 1;
        parcel.writeInt(j);
        if(mDeviceAddress != null)
            parcel.writeString(mDeviceAddress);
        if(mServiceUuid == null)
            j = 0;
        else
            j = 1;
        parcel.writeInt(j);
        if(mServiceUuid != null)
        {
            parcel.writeParcelable(mServiceUuid, i);
            if(mServiceUuidMask == null)
                j = 0;
            else
                j = 1;
            parcel.writeInt(j);
            if(mServiceUuidMask != null)
                parcel.writeParcelable(mServiceUuidMask, i);
        }
        if(mServiceDataUuid == null)
            j = 0;
        else
            j = 1;
        parcel.writeInt(j);
        if(mServiceDataUuid != null)
        {
            parcel.writeParcelable(mServiceDataUuid, i);
            if(mServiceData == null)
                i = 0;
            else
                i = 1;
            parcel.writeInt(i);
            if(mServiceData != null)
            {
                parcel.writeInt(mServiceData.length);
                parcel.writeByteArray(mServiceData);
                if(mServiceDataMask == null)
                    i = 0;
                else
                    i = 1;
                parcel.writeInt(i);
                if(mServiceDataMask != null)
                {
                    parcel.writeInt(mServiceDataMask.length);
                    parcel.writeByteArray(mServiceDataMask);
                }
            }
        }
        parcel.writeInt(mManufacturerId);
        if(mManufacturerData == null)
            i = 0;
        else
            i = 1;
        parcel.writeInt(i);
        if(mManufacturerData != null)
        {
            parcel.writeInt(mManufacturerData.length);
            parcel.writeByteArray(mManufacturerData);
            if(mManufacturerDataMask == null)
                i = ((flag) ? 1 : 0);
            else
                i = 1;
            parcel.writeInt(i);
            if(mManufacturerDataMask != null)
            {
                parcel.writeInt(mManufacturerDataMask.length);
                parcel.writeByteArray(mManufacturerDataMask);
            }
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ScanFilter createFromParcel(Parcel parcel)
        {
            Builder builder = new Builder();
            if(parcel.readInt() == 1)
                builder.setDeviceName(parcel.readString());
            if(parcel.readInt() == 1)
                builder.setDeviceAddress(parcel.readString());
            if(parcel.readInt() == 1)
            {
                ParcelUuid parceluuid = (ParcelUuid)parcel.readParcelable(android/os/ParcelUuid.getClassLoader());
                builder.setServiceUuid(parceluuid);
                if(parcel.readInt() == 1)
                    builder.setServiceUuid(parceluuid, (ParcelUuid)parcel.readParcelable(android/os/ParcelUuid.getClassLoader()));
            }
            int i;
            if(parcel.readInt() == 1)
            {
                ParcelUuid parceluuid1 = (ParcelUuid)parcel.readParcelable(android/os/ParcelUuid.getClassLoader());
                if(parcel.readInt() == 1)
                {
                    byte abyte0[] = new byte[parcel.readInt()];
                    parcel.readByteArray(abyte0);
                    if(parcel.readInt() == 0)
                    {
                        builder.setServiceData(parceluuid1, abyte0);
                    } else
                    {
                        abyte2 = new byte[parcel.readInt()];
                        parcel.readByteArray(abyte2);
                        builder.setServiceData(parceluuid1, abyte0, abyte2);
                    }
                }
            }
            i = parcel.readInt();
            if(parcel.readInt() == 1)
            {
                byte abyte2[] = new byte[parcel.readInt()];
                parcel.readByteArray(abyte2);
                if(parcel.readInt() == 0)
                {
                    builder.setManufacturerData(i, abyte2);
                } else
                {
                    byte abyte1[] = new byte[parcel.readInt()];
                    parcel.readByteArray(abyte1);
                    builder.setManufacturerData(i, abyte2, abyte1);
                }
            }
            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ScanFilter[] newArray(int i)
        {
            return new ScanFilter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final ScanFilter EMPTY = (new Builder()).build();
    private final String mDeviceAddress;
    private final String mDeviceName;
    private final byte mManufacturerData[];
    private final byte mManufacturerDataMask[];
    private final int mManufacturerId;
    private final byte mServiceData[];
    private final byte mServiceDataMask[];
    private final ParcelUuid mServiceDataUuid;
    private final ParcelUuid mServiceUuid;
    private final ParcelUuid mServiceUuidMask;

}
