// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.*;
import android.util.ArrayMap;
import android.util.SparseArray;
import java.util.*;

// Referenced classes of package android.bluetooth.le:
//            BluetoothLeUtils

public final class AdvertiseData
    implements Parcelable
{
    public static final class Builder
    {

        public Builder addManufacturerData(int i, byte abyte0[])
        {
            if(i < 0)
                throw new IllegalArgumentException((new StringBuilder()).append("invalid manufacturerId - ").append(i).toString());
            if(abyte0 == null)
            {
                throw new IllegalArgumentException("manufacturerSpecificData is null");
            } else
            {
                mManufacturerSpecificData.put(i, abyte0);
                return this;
            }
        }

        public Builder addServiceData(ParcelUuid parceluuid, byte abyte0[])
        {
            if(parceluuid == null || abyte0 == null)
            {
                throw new IllegalArgumentException("serviceDataUuid or serviceDataUuid is null");
            } else
            {
                mServiceData.put(parceluuid, abyte0);
                return this;
            }
        }

        public Builder addServiceUuid(ParcelUuid parceluuid)
        {
            if(parceluuid == null)
            {
                throw new IllegalArgumentException("serivceUuids are null");
            } else
            {
                mServiceUuids.add(parceluuid);
                return this;
            }
        }

        public AdvertiseData build()
        {
            return new AdvertiseData(mServiceUuids, mManufacturerSpecificData, mServiceData, mIncludeTxPowerLevel, mIncludeDeviceName, null);
        }

        public Builder setIncludeDeviceName(boolean flag)
        {
            mIncludeDeviceName = flag;
            return this;
        }

        public Builder setIncludeTxPowerLevel(boolean flag)
        {
            mIncludeTxPowerLevel = flag;
            return this;
        }

        private boolean mIncludeDeviceName;
        private boolean mIncludeTxPowerLevel;
        private SparseArray mManufacturerSpecificData;
        private Map mServiceData;
        private List mServiceUuids;

        public Builder()
        {
            mServiceUuids = new ArrayList();
            mManufacturerSpecificData = new SparseArray();
            mServiceData = new ArrayMap();
        }
    }


    private AdvertiseData(List list, SparseArray sparsearray, Map map, boolean flag, boolean flag1)
    {
        mServiceUuids = list;
        mManufacturerSpecificData = sparsearray;
        mServiceData = map;
        mIncludeTxPowerLevel = flag;
        mIncludeDeviceName = flag1;
    }

    AdvertiseData(List list, SparseArray sparsearray, Map map, boolean flag, boolean flag1, AdvertiseData advertisedata)
    {
        this(list, sparsearray, map, flag, flag1);
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
        obj = (AdvertiseData)obj;
        if(Objects.equals(mServiceUuids, ((AdvertiseData) (obj)).mServiceUuids) && BluetoothLeUtils.equals(mManufacturerSpecificData, ((AdvertiseData) (obj)).mManufacturerSpecificData) && BluetoothLeUtils.equals(mServiceData, ((AdvertiseData) (obj)).mServiceData) && mIncludeDeviceName == ((AdvertiseData) (obj)).mIncludeDeviceName)
        {
            if(mIncludeTxPowerLevel != ((AdvertiseData) (obj)).mIncludeTxPowerLevel)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean getIncludeDeviceName()
    {
        return mIncludeDeviceName;
    }

    public boolean getIncludeTxPowerLevel()
    {
        return mIncludeTxPowerLevel;
    }

    public SparseArray getManufacturerSpecificData()
    {
        return mManufacturerSpecificData;
    }

    public Map getServiceData()
    {
        return mServiceData;
    }

    public List getServiceUuids()
    {
        return mServiceUuids;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mServiceUuids, mManufacturerSpecificData, mServiceData, Boolean.valueOf(mIncludeDeviceName), Boolean.valueOf(mIncludeTxPowerLevel)
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("AdvertiseData [mServiceUuids=").append(mServiceUuids).append(", mManufacturerSpecificData=").append(BluetoothLeUtils.toString(mManufacturerSpecificData)).append(", mServiceData=").append(BluetoothLeUtils.toString(mServiceData)).append(", mIncludeTxPowerLevel=").append(mIncludeTxPowerLevel).append(", mIncludeDeviceName=").append(mIncludeDeviceName).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeList(mServiceUuids);
        parcel.writeInt(mManufacturerSpecificData.size());
        int j = 0;
        while(j < mManufacturerSpecificData.size()) 
        {
            parcel.writeInt(mManufacturerSpecificData.keyAt(j));
            byte abyte0[] = (byte[])mManufacturerSpecificData.valueAt(j);
            if(abyte0 == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                parcel.writeInt(abyte0.length);
                parcel.writeByteArray(abyte0);
            }
            j++;
        }
        parcel.writeInt(mServiceData.size());
        for(Iterator iterator = mServiceData.keySet().iterator(); iterator.hasNext();)
        {
            ParcelUuid parceluuid = (ParcelUuid)iterator.next();
            parcel.writeParcelable(parceluuid, i);
            byte abyte1[] = (byte[])mServiceData.get(parceluuid);
            if(abyte1 == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                parcel.writeInt(abyte1.length);
                parcel.writeByteArray(abyte1);
            }
        }

        if(getIncludeTxPowerLevel())
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        if(getIncludeDeviceName())
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeByte((byte)i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AdvertiseData createFromParcel(Parcel parcel)
        {
            Builder builder = new Builder();
            Object obj = parcel.readArrayList(android/os/ParcelUuid.getClassLoader());
            if(obj != null)
                for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); builder.addServiceUuid((ParcelUuid)((Iterator) (obj)).next()));
            int i = parcel.readInt();
            for(int j = 0; j < i; j++)
            {
                int l = parcel.readInt();
                if(parcel.readInt() == 1)
                {
                    byte abyte0[] = new byte[parcel.readInt()];
                    parcel.readByteArray(abyte0);
                    builder.addManufacturerData(l, abyte0);
                }
            }

            i = parcel.readInt();
            for(int k = 0; k < i; k++)
            {
                ParcelUuid parceluuid = (ParcelUuid)parcel.readParcelable(android/os/ParcelUuid.getClassLoader());
                if(parcel.readInt() == 1)
                {
                    byte abyte1[] = new byte[parcel.readInt()];
                    parcel.readByteArray(abyte1);
                    builder.addServiceData(parceluuid, abyte1);
                }
            }

            boolean flag;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            builder.setIncludeTxPowerLevel(flag);
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            builder.setIncludeDeviceName(flag);
            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AdvertiseData[] newArray(int i)
        {
            return new AdvertiseData[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final boolean mIncludeDeviceName;
    private final boolean mIncludeTxPowerLevel;
    private final SparseArray mManufacturerSpecificData;
    private final Map mServiceData;
    private final List mServiceUuids;

}
