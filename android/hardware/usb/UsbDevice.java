// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.hardware.usb:
//            UsbConfiguration, UsbInterface

public class UsbDevice
    implements Parcelable
{

    public UsbDevice(String s, int i, int j, int k, int l, int i1, String s1, 
            String s2, String s3, String s4)
    {
        mName = (String)Preconditions.checkNotNull(s);
        mVendorId = i;
        mProductId = j;
        mClass = k;
        mSubclass = l;
        mProtocol = i1;
        mManufacturerName = s1;
        mProductName = s2;
        mVersion = (String)Preconditions.checkStringNotEmpty(s3);
        mSerialNumber = s4;
    }

    public static int getDeviceId(String s)
    {
        return native_get_device_id(s);
    }

    public static String getDeviceName(int i)
    {
        return native_get_device_name(i);
    }

    private UsbInterface[] getInterfaceList()
    {
        if(mInterfaces == null)
        {
            int i = mConfigurations.length;
            int j = 0;
            for(int k = 0; k < i; k++)
                j += ((UsbConfiguration)mConfigurations[k]).getInterfaceCount();

            mInterfaces = new UsbInterface[j];
            j = 0;
            for(int l = 0; l < i; l++)
            {
                UsbConfiguration usbconfiguration = (UsbConfiguration)mConfigurations[l];
                int i1 = usbconfiguration.getInterfaceCount();
                for(int j1 = 0; j1 < i1;)
                {
                    mInterfaces[j] = usbconfiguration.getInterface(j1);
                    j1++;
                    j++;
                }

            }

        }
        return mInterfaces;
    }

    private static native int native_get_device_id(String s);

    private static native String native_get_device_name(int i);

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof UsbDevice)
            return ((UsbDevice)obj).mName.equals(mName);
        if(obj instanceof String)
            return ((String)obj).equals(mName);
        else
            return false;
    }

    public UsbConfiguration getConfiguration(int i)
    {
        return (UsbConfiguration)mConfigurations[i];
    }

    public int getConfigurationCount()
    {
        return mConfigurations.length;
    }

    public int getDeviceClass()
    {
        return mClass;
    }

    public int getDeviceId()
    {
        return getDeviceId(mName);
    }

    public String getDeviceName()
    {
        return mName;
    }

    public int getDeviceProtocol()
    {
        return mProtocol;
    }

    public int getDeviceSubclass()
    {
        return mSubclass;
    }

    public UsbInterface getInterface(int i)
    {
        return getInterfaceList()[i];
    }

    public int getInterfaceCount()
    {
        return getInterfaceList().length;
    }

    public String getManufacturerName()
    {
        return mManufacturerName;
    }

    public int getProductId()
    {
        return mProductId;
    }

    public String getProductName()
    {
        return mProductName;
    }

    public String getSerialNumber()
    {
        return mSerialNumber;
    }

    public int getVendorId()
    {
        return mVendorId;
    }

    public String getVersion()
    {
        return mVersion;
    }

    public int hashCode()
    {
        return mName.hashCode();
    }

    public void setConfigurations(Parcelable aparcelable[])
    {
        mConfigurations = (Parcelable[])Preconditions.checkArrayElementsNotNull(aparcelable, "configuration");
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("UsbDevice[mName=").append(mName).append(",mVendorId=").append(mVendorId).append(",mProductId=").append(mProductId).append(",mClass=").append(mClass).append(",mSubclass=").append(mSubclass).append(",mProtocol=").append(mProtocol).append(",mManufacturerName=").append(mManufacturerName).append(",mProductName=").append(mProductName).append(",mVersion=").append(mVersion).append(",mSerialNumber=").append(mSerialNumber).append(",mConfigurations=[").toString());
        for(int i = 0; i < mConfigurations.length; i++)
        {
            stringbuilder.append("\n");
            stringbuilder.append(mConfigurations[i].toString());
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mName);
        parcel.writeInt(mVendorId);
        parcel.writeInt(mProductId);
        parcel.writeInt(mClass);
        parcel.writeInt(mSubclass);
        parcel.writeInt(mProtocol);
        parcel.writeString(mManufacturerName);
        parcel.writeString(mProductName);
        parcel.writeString(mVersion);
        parcel.writeString(mSerialNumber);
        parcel.writeParcelableArray(mConfigurations, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsbDevice createFromParcel(Parcel parcel)
        {
            String s = parcel.readString();
            int i = parcel.readInt();
            int j = parcel.readInt();
            int k = parcel.readInt();
            int l = parcel.readInt();
            int i1 = parcel.readInt();
            String s1 = parcel.readString();
            Object obj = parcel.readString();
            String s2 = parcel.readString();
            String s3 = parcel.readString();
            parcel = parcel.readParcelableArray(android/hardware/usb/UsbInterface.getClassLoader());
            obj = new UsbDevice(s, i, j, k, l, i1, s1, ((String) (obj)), s2, s3);
            ((UsbDevice) (obj)).setConfigurations(parcel);
            return ((UsbDevice) (obj));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsbDevice[] newArray(int i)
        {
            return new UsbDevice[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    private static final String TAG = "UsbDevice";
    private final int mClass;
    private Parcelable mConfigurations[];
    private UsbInterface mInterfaces[];
    private final String mManufacturerName;
    private final String mName;
    private final int mProductId;
    private final String mProductName;
    private final int mProtocol;
    private final String mSerialNumber;
    private final int mSubclass;
    private final int mVendorId;
    private final String mVersion;

}
