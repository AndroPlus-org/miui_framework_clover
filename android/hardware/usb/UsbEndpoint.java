// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.os.Parcel;
import android.os.Parcelable;

public class UsbEndpoint
    implements Parcelable
{

    public UsbEndpoint(int i, int j, int k, int l)
    {
        mAddress = i;
        mAttributes = j;
        mMaxPacketSize = k;
        mInterval = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAddress()
    {
        return mAddress;
    }

    public int getAttributes()
    {
        return mAttributes;
    }

    public int getDirection()
    {
        return mAddress & 0x80;
    }

    public int getEndpointNumber()
    {
        return mAddress & 0xf;
    }

    public int getInterval()
    {
        return mInterval;
    }

    public int getMaxPacketSize()
    {
        return mMaxPacketSize;
    }

    public int getType()
    {
        return mAttributes & 3;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UsbEndpoint[mAddress=").append(mAddress).append(",mAttributes=").append(mAttributes).append(",mMaxPacketSize=").append(mMaxPacketSize).append(",mInterval=").append(mInterval).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mAddress);
        parcel.writeInt(mAttributes);
        parcel.writeInt(mMaxPacketSize);
        parcel.writeInt(mInterval);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsbEndpoint createFromParcel(Parcel parcel)
        {
            return new UsbEndpoint(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsbEndpoint[] newArray(int i)
        {
            return new UsbEndpoint[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mAddress;
    private final int mAttributes;
    private final int mInterval;
    private final int mMaxPacketSize;

}
