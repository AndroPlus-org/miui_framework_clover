// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.*;

// Referenced classes of package android.bluetooth:
//            BluetoothGattDescriptor, BluetoothGattService

public class BluetoothGattCharacteristic
    implements Parcelable
{

    BluetoothGattCharacteristic(BluetoothGattService bluetoothgattservice, UUID uuid, int i, int j, int k)
    {
        mKeySize = 16;
        initCharacteristic(bluetoothgattservice, uuid, i, j, k);
    }

    private BluetoothGattCharacteristic(Parcel parcel)
    {
        mKeySize = 16;
        mUuid = ((ParcelUuid)parcel.readParcelable(null)).getUuid();
        mInstance = parcel.readInt();
        mProperties = parcel.readInt();
        mPermissions = parcel.readInt();
        mKeySize = parcel.readInt();
        mWriteType = parcel.readInt();
        mDescriptors = new ArrayList();
        parcel = parcel.createTypedArrayList(BluetoothGattDescriptor.CREATOR);
        if(parcel != null)
        {
            for(Iterator iterator = parcel.iterator(); iterator.hasNext(); mDescriptors.add(parcel))
            {
                parcel = (BluetoothGattDescriptor)iterator.next();
                parcel.setCharacteristic(this);
            }

        }
    }

    BluetoothGattCharacteristic(Parcel parcel, BluetoothGattCharacteristic bluetoothgattcharacteristic)
    {
        this(parcel);
    }

    public BluetoothGattCharacteristic(UUID uuid, int i, int j)
    {
        mKeySize = 16;
        initCharacteristic(null, uuid, 0, i, j);
    }

    public BluetoothGattCharacteristic(UUID uuid, int i, int j, int k)
    {
        mKeySize = 16;
        initCharacteristic(null, uuid, i, j, k);
    }

    private float bytesToFloat(byte byte0, byte byte1)
    {
        int i = unsignedToSigned(unsignedByteToInt(byte0) + ((unsignedByteToInt(byte1) & 0xf) << 8), 12);
        int j = unsignedToSigned(unsignedByteToInt(byte1) >> 4, 4);
        return (float)((double)i * Math.pow(10D, j));
    }

    private float bytesToFloat(byte byte0, byte byte1, byte byte2, byte byte3)
    {
        return (float)((double)unsignedToSigned(unsignedByteToInt(byte0) + (unsignedByteToInt(byte1) << 8) + (unsignedByteToInt(byte2) << 16), 24) * Math.pow(10D, byte3));
    }

    private int getTypeLen(int i)
    {
        return i & 0xf;
    }

    private void initCharacteristic(BluetoothGattService bluetoothgattservice, UUID uuid, int i, int j, int k)
    {
        mUuid = uuid;
        mInstance = i;
        mProperties = j;
        mPermissions = k;
        mService = bluetoothgattservice;
        mValue = null;
        mDescriptors = new ArrayList();
        if((mProperties & 4) != 0)
            mWriteType = 1;
        else
            mWriteType = 2;
    }

    private int intToSignedBits(int i, int j)
    {
        int k = i;
        if(i < 0)
            k = (1 << j - 1) + ((1 << j - 1) - 1 & i);
        return k;
    }

    private int unsignedByteToInt(byte byte0)
    {
        return byte0 & 0xff;
    }

    private int unsignedBytesToInt(byte byte0, byte byte1)
    {
        return unsignedByteToInt(byte0) + (unsignedByteToInt(byte1) << 8);
    }

    private int unsignedBytesToInt(byte byte0, byte byte1, byte byte2, byte byte3)
    {
        return unsignedByteToInt(byte0) + (unsignedByteToInt(byte1) << 8) + (unsignedByteToInt(byte2) << 16) + (unsignedByteToInt(byte3) << 24);
    }

    private int unsignedToSigned(int i, int j)
    {
        int k = i;
        if((1 << j - 1 & i) != 0)
            k = ((1 << j - 1) - ((1 << j - 1) - 1 & i)) * -1;
        return k;
    }

    public boolean addDescriptor(BluetoothGattDescriptor bluetoothgattdescriptor)
    {
        mDescriptors.add(bluetoothgattdescriptor);
        bluetoothgattdescriptor.setCharacteristic(this);
        return true;
    }

    public int describeContents()
    {
        return 0;
    }

    public BluetoothGattDescriptor getDescriptor(UUID uuid)
    {
        for(Iterator iterator = mDescriptors.iterator(); iterator.hasNext();)
        {
            BluetoothGattDescriptor bluetoothgattdescriptor = (BluetoothGattDescriptor)iterator.next();
            if(bluetoothgattdescriptor.getUuid().equals(uuid))
                return bluetoothgattdescriptor;
        }

        return null;
    }

    BluetoothGattDescriptor getDescriptor(UUID uuid, int i)
    {
        for(Iterator iterator = mDescriptors.iterator(); iterator.hasNext();)
        {
            BluetoothGattDescriptor bluetoothgattdescriptor = (BluetoothGattDescriptor)iterator.next();
            if(bluetoothgattdescriptor.getUuid().equals(uuid) && bluetoothgattdescriptor.getInstanceId() == i)
                return bluetoothgattdescriptor;
        }

        return null;
    }

    public List getDescriptors()
    {
        return mDescriptors;
    }

    public Float getFloatValue(int i, int j)
    {
        if(getTypeLen(i) + j > mValue.length)
            return null;
        switch(i)
        {
        case 51: // '3'
        default:
            return null;

        case 50: // '2'
            return Float.valueOf(bytesToFloat(mValue[j], mValue[j + 1]));

        case 52: // '4'
            return Float.valueOf(bytesToFloat(mValue[j], mValue[j + 1], mValue[j + 2], mValue[j + 3]));
        }
    }

    public int getInstanceId()
    {
        return mInstance;
    }

    public Integer getIntValue(int i, int j)
    {
        if(getTypeLen(i) + j > mValue.length)
            return null;
        switch(i)
        {
        default:
            return null;

        case 17: // '\021'
            return Integer.valueOf(unsignedByteToInt(mValue[j]));

        case 18: // '\022'
            return Integer.valueOf(unsignedBytesToInt(mValue[j], mValue[j + 1]));

        case 20: // '\024'
            return Integer.valueOf(unsignedBytesToInt(mValue[j], mValue[j + 1], mValue[j + 2], mValue[j + 3]));

        case 33: // '!'
            return Integer.valueOf(unsignedToSigned(unsignedByteToInt(mValue[j]), 8));

        case 34: // '"'
            return Integer.valueOf(unsignedToSigned(unsignedBytesToInt(mValue[j], mValue[j + 1]), 16));

        case 36: // '$'
            return Integer.valueOf(unsignedToSigned(unsignedBytesToInt(mValue[j], mValue[j + 1], mValue[j + 2], mValue[j + 3]), 32));
        }
    }

    public int getKeySize()
    {
        return mKeySize;
    }

    public int getPermissions()
    {
        return mPermissions;
    }

    public int getProperties()
    {
        return mProperties;
    }

    public BluetoothGattService getService()
    {
        return mService;
    }

    public String getStringValue(int i)
    {
        if(mValue == null || i > mValue.length)
            return null;
        byte abyte0[] = new byte[mValue.length - i];
        for(int j = 0; j != mValue.length - i; j++)
            abyte0[j] = mValue[i + j];

        return new String(abyte0);
    }

    public UUID getUuid()
    {
        return mUuid;
    }

    public byte[] getValue()
    {
        return mValue;
    }

    public int getWriteType()
    {
        return mWriteType;
    }

    public void setInstanceId(int i)
    {
        mInstance = i;
    }

    public void setKeySize(int i)
    {
        mKeySize = i;
    }

    void setService(BluetoothGattService bluetoothgattservice)
    {
        mService = bluetoothgattservice;
    }

    public boolean setValue(int i, int j, int k)
    {
        int l;
        int i1;
        int j1;
        l = k + getTypeLen(j);
        if(mValue == null)
            mValue = new byte[l];
        if(l > mValue.length)
            return false;
        i1 = i;
        j1 = i;
        l = i;
        j;
        JVM INSTR lookupswitch 6: default 104
    //                   17: 115
    //                   18: 140
    //                   20: 185
    //                   33: 106
    //                   34: 131
    //                   36: 176;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        return false;
_L5:
        i1 = intToSignedBits(i, 8);
_L2:
        mValue[k] = (byte)(i1 & 0xff);
_L9:
        return true;
_L6:
        j1 = intToSignedBits(i, 16);
_L3:
        mValue[k] = (byte)(j1 & 0xff);
        mValue[k + 1] = (byte)(j1 >> 8 & 0xff);
        continue; /* Loop/switch isn't completed */
_L7:
        l = intToSignedBits(i, 32);
_L4:
        byte abyte0[] = mValue;
        i = k + 1;
        abyte0[k] = (byte)(l & 0xff);
        abyte0 = mValue;
        j = i + 1;
        abyte0[i] = (byte)(l >> 8 & 0xff);
        mValue[j] = (byte)(l >> 16 & 0xff);
        mValue[j + 1] = (byte)(l >> 24 & 0xff);
        if(true) goto _L9; else goto _L8
_L8:
    }

    public boolean setValue(int i, int j, int k, int l)
    {
        int i1 = l + getTypeLen(k);
        if(mValue == null)
            mValue = new byte[i1];
        if(i1 > mValue.length)
            return false;
        k;
        JVM INSTR tableswitch 50 52: default 64
    //                   50 66
    //                   51 64
    //                   52 145;
           goto _L1 _L2 _L1 _L3
_L1:
        return false;
_L2:
        i = intToSignedBits(i, 12);
        k = intToSignedBits(j, 4);
        byte abyte0[] = mValue;
        j = l + 1;
        abyte0[l] = (byte)(i & 0xff);
        mValue[j] = (byte)(i >> 8 & 0xf);
        abyte0 = mValue;
        abyte0[j] = (byte)(abyte0[j] + (byte)((k & 0xf) << 4));
_L5:
        return true;
_L3:
        i = intToSignedBits(i, 24);
        j = intToSignedBits(j, 8);
        byte abyte1[] = mValue;
        int j1 = l + 1;
        abyte1[l] = (byte)(i & 0xff);
        abyte1 = mValue;
        k = j1 + 1;
        abyte1[j1] = (byte)(i >> 8 & 0xff);
        abyte1 = mValue;
        l = k + 1;
        abyte1[k] = (byte)(i >> 16 & 0xff);
        abyte1 = mValue;
        abyte1[l] = (byte)(abyte1[l] + (byte)(j & 0xff));
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean setValue(String s)
    {
        mValue = s.getBytes();
        return true;
    }

    public boolean setValue(byte abyte0[])
    {
        mValue = abyte0;
        return true;
    }

    public void setWriteType(int i)
    {
        mWriteType = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(new ParcelUuid(mUuid), 0);
        parcel.writeInt(mInstance);
        parcel.writeInt(mProperties);
        parcel.writeInt(mPermissions);
        parcel.writeInt(mKeySize);
        parcel.writeInt(mWriteType);
        parcel.writeTypedList(mDescriptors);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothGattCharacteristic createFromParcel(Parcel parcel)
        {
            return new BluetoothGattCharacteristic(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothGattCharacteristic[] newArray(int i)
        {
            return new BluetoothGattCharacteristic[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FORMAT_FLOAT = 52;
    public static final int FORMAT_SFLOAT = 50;
    public static final int FORMAT_SINT16 = 34;
    public static final int FORMAT_SINT32 = 36;
    public static final int FORMAT_SINT8 = 33;
    public static final int FORMAT_UINT16 = 18;
    public static final int FORMAT_UINT32 = 20;
    public static final int FORMAT_UINT8 = 17;
    public static final int PERMISSION_READ = 1;
    public static final int PERMISSION_READ_ENCRYPTED = 2;
    public static final int PERMISSION_READ_ENCRYPTED_MITM = 4;
    public static final int PERMISSION_WRITE = 16;
    public static final int PERMISSION_WRITE_ENCRYPTED = 32;
    public static final int PERMISSION_WRITE_ENCRYPTED_MITM = 64;
    public static final int PERMISSION_WRITE_SIGNED = 128;
    public static final int PERMISSION_WRITE_SIGNED_MITM = 256;
    public static final int PROPERTY_BROADCAST = 1;
    public static final int PROPERTY_EXTENDED_PROPS = 128;
    public static final int PROPERTY_INDICATE = 32;
    public static final int PROPERTY_NOTIFY = 16;
    public static final int PROPERTY_READ = 2;
    public static final int PROPERTY_SIGNED_WRITE = 64;
    public static final int PROPERTY_WRITE = 8;
    public static final int PROPERTY_WRITE_NO_RESPONSE = 4;
    public static final int WRITE_TYPE_DEFAULT = 2;
    public static final int WRITE_TYPE_NO_RESPONSE = 1;
    public static final int WRITE_TYPE_SIGNED = 4;
    protected List mDescriptors;
    protected int mInstance;
    protected int mKeySize;
    protected int mPermissions;
    protected int mProperties;
    protected BluetoothGattService mService;
    protected UUID mUuid;
    protected byte mValue[];
    protected int mWriteType;

}
