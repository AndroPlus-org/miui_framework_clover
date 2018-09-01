// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.hardware.usb:
//            UsbInterface

public class UsbConfiguration
    implements Parcelable
{

    public UsbConfiguration(int i, String s, int j, int k)
    {
        mId = i;
        mName = s;
        mAttributes = j;
        mMaxPower = k;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getId()
    {
        return mId;
    }

    public UsbInterface getInterface(int i)
    {
        return (UsbInterface)mInterfaces[i];
    }

    public int getInterfaceCount()
    {
        return mInterfaces.length;
    }

    public int getMaxPower()
    {
        return mMaxPower * 2;
    }

    public String getName()
    {
        return mName;
    }

    public boolean isRemoteWakeup()
    {
        boolean flag = false;
        if((mAttributes & 0x20) != 0)
            flag = true;
        return flag;
    }

    public boolean isSelfPowered()
    {
        boolean flag = false;
        if((mAttributes & 0x40) != 0)
            flag = true;
        return flag;
    }

    public void setInterfaces(Parcelable aparcelable[])
    {
        mInterfaces = (Parcelable[])Preconditions.checkArrayElementsNotNull(aparcelable, "interfaces");
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("UsbConfiguration[mId=").append(mId).append(",mName=").append(mName).append(",mAttributes=").append(mAttributes).append(",mMaxPower=").append(mMaxPower).append(",mInterfaces=[").toString());
        for(int i = 0; i < mInterfaces.length; i++)
        {
            stringbuilder.append("\n");
            stringbuilder.append(mInterfaces[i].toString());
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeInt(mAttributes);
        parcel.writeInt(mMaxPower);
        parcel.writeParcelableArray(mInterfaces, 0);
    }

    private static final int ATTR_REMOTE_WAKEUP = 32;
    private static final int ATTR_SELF_POWERED = 64;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsbConfiguration createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            Object obj = parcel.readString();
            int j = parcel.readInt();
            int k = parcel.readInt();
            parcel = parcel.readParcelableArray(android/hardware/usb/UsbInterface.getClassLoader());
            obj = new UsbConfiguration(i, ((String) (obj)), j, k);
            ((UsbConfiguration) (obj)).setInterfaces(parcel);
            return ((UsbConfiguration) (obj));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsbConfiguration[] newArray(int i)
        {
            return new UsbConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mAttributes;
    private final int mId;
    private Parcelable mInterfaces[];
    private final int mMaxPower;
    private final String mName;

}
