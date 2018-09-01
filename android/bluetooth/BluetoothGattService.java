// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.*;

// Referenced classes of package android.bluetooth:
//            BluetoothGattCharacteristic, BluetoothGattIncludedService, BluetoothDevice

public class BluetoothGattService
    implements Parcelable
{

    BluetoothGattService(BluetoothDevice bluetoothdevice, UUID uuid, int i, int j)
    {
        mHandles = 0;
        mDevice = bluetoothdevice;
        mUuid = uuid;
        mInstanceId = i;
        mServiceType = j;
        mCharacteristics = new ArrayList();
        mIncludedServices = new ArrayList();
    }

    private BluetoothGattService(Parcel parcel)
    {
        mHandles = 0;
        mUuid = ((ParcelUuid)parcel.readParcelable(null)).getUuid();
        mInstanceId = parcel.readInt();
        mServiceType = parcel.readInt();
        mCharacteristics = new ArrayList();
        ArrayList arraylist = parcel.createTypedArrayList(BluetoothGattCharacteristic.CREATOR);
        if(arraylist != null)
        {
            BluetoothGattCharacteristic bluetoothgattcharacteristic;
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); mCharacteristics.add(bluetoothgattcharacteristic))
            {
                bluetoothgattcharacteristic = (BluetoothGattCharacteristic)iterator.next();
                bluetoothgattcharacteristic.setService(this);
            }

        }
        mIncludedServices = new ArrayList();
        parcel = parcel.createTypedArrayList(BluetoothGattIncludedService.CREATOR);
        if(arraylist != null)
        {
            BluetoothGattIncludedService bluetoothgattincludedservice;
            for(parcel = parcel.iterator(); parcel.hasNext(); mIncludedServices.add(new BluetoothGattService(null, bluetoothgattincludedservice.getUuid(), bluetoothgattincludedservice.getInstanceId(), bluetoothgattincludedservice.getType())))
                bluetoothgattincludedservice = (BluetoothGattIncludedService)parcel.next();

        }
    }

    BluetoothGattService(Parcel parcel, BluetoothGattService bluetoothgattservice)
    {
        this(parcel);
    }

    public BluetoothGattService(UUID uuid, int i)
    {
        mHandles = 0;
        mDevice = null;
        mUuid = uuid;
        mInstanceId = 0;
        mServiceType = i;
        mCharacteristics = new ArrayList();
        mIncludedServices = new ArrayList();
    }

    public BluetoothGattService(UUID uuid, int i, int j)
    {
        mHandles = 0;
        mDevice = null;
        mUuid = uuid;
        mInstanceId = i;
        mServiceType = j;
        mCharacteristics = new ArrayList();
        mIncludedServices = new ArrayList();
    }

    public boolean addCharacteristic(BluetoothGattCharacteristic bluetoothgattcharacteristic)
    {
        mCharacteristics.add(bluetoothgattcharacteristic);
        bluetoothgattcharacteristic.setService(this);
        return true;
    }

    public void addIncludedService(BluetoothGattService bluetoothgattservice)
    {
        mIncludedServices.add(bluetoothgattservice);
    }

    public boolean addService(BluetoothGattService bluetoothgattservice)
    {
        mIncludedServices.add(bluetoothgattservice);
        return true;
    }

    public int describeContents()
    {
        return 0;
    }

    public BluetoothGattCharacteristic getCharacteristic(UUID uuid)
    {
        for(Iterator iterator = mCharacteristics.iterator(); iterator.hasNext();)
        {
            BluetoothGattCharacteristic bluetoothgattcharacteristic = (BluetoothGattCharacteristic)iterator.next();
            if(uuid.equals(bluetoothgattcharacteristic.getUuid()))
                return bluetoothgattcharacteristic;
        }

        return null;
    }

    BluetoothGattCharacteristic getCharacteristic(UUID uuid, int i)
    {
        for(Iterator iterator = mCharacteristics.iterator(); iterator.hasNext();)
        {
            BluetoothGattCharacteristic bluetoothgattcharacteristic = (BluetoothGattCharacteristic)iterator.next();
            if(uuid.equals(bluetoothgattcharacteristic.getUuid()) && bluetoothgattcharacteristic.getInstanceId() == i)
                return bluetoothgattcharacteristic;
        }

        return null;
    }

    public List getCharacteristics()
    {
        return mCharacteristics;
    }

    BluetoothDevice getDevice()
    {
        return mDevice;
    }

    int getHandles()
    {
        return mHandles;
    }

    public List getIncludedServices()
    {
        return mIncludedServices;
    }

    public int getInstanceId()
    {
        return mInstanceId;
    }

    public int getType()
    {
        return mServiceType;
    }

    public UUID getUuid()
    {
        return mUuid;
    }

    public boolean isAdvertisePreferred()
    {
        return mAdvertisePreferred;
    }

    public void setAdvertisePreferred(boolean flag)
    {
        mAdvertisePreferred = flag;
    }

    void setDevice(BluetoothDevice bluetoothdevice)
    {
        mDevice = bluetoothdevice;
    }

    public void setHandles(int i)
    {
        mHandles = i;
    }

    public void setInstanceId(int i)
    {
        mInstanceId = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(new ParcelUuid(mUuid), 0);
        parcel.writeInt(mInstanceId);
        parcel.writeInt(mServiceType);
        parcel.writeTypedList(mCharacteristics);
        ArrayList arraylist = new ArrayList(mIncludedServices.size());
        BluetoothGattService bluetoothgattservice;
        for(Iterator iterator = mIncludedServices.iterator(); iterator.hasNext(); arraylist.add(new BluetoothGattIncludedService(bluetoothgattservice.getUuid(), bluetoothgattservice.getInstanceId(), bluetoothgattservice.getType())))
            bluetoothgattservice = (BluetoothGattService)iterator.next();

        parcel.writeTypedList(arraylist);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothGattService createFromParcel(Parcel parcel)
        {
            return new BluetoothGattService(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothGattService[] newArray(int i)
        {
            return new BluetoothGattService[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int SERVICE_TYPE_PRIMARY = 0;
    public static final int SERVICE_TYPE_SECONDARY = 1;
    private boolean mAdvertisePreferred;
    protected List mCharacteristics;
    protected BluetoothDevice mDevice;
    protected int mHandles;
    protected List mIncludedServices;
    protected int mInstanceId;
    protected int mServiceType;
    protected UUID mUuid;

}
