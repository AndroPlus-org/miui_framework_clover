// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.hardware.usb:
//            UsbEndpoint

public class UsbInterface
    implements Parcelable
{

    public UsbInterface(int i, int j, String s, int k, int l, int i1)
    {
        mId = i;
        mAlternateSetting = j;
        mName = s;
        mClass = k;
        mSubclass = l;
        mProtocol = i1;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAlternateSetting()
    {
        return mAlternateSetting;
    }

    public UsbEndpoint getEndpoint(int i)
    {
        return (UsbEndpoint)mEndpoints[i];
    }

    public int getEndpointCount()
    {
        return mEndpoints.length;
    }

    public int getId()
    {
        return mId;
    }

    public int getInterfaceClass()
    {
        return mClass;
    }

    public int getInterfaceProtocol()
    {
        return mProtocol;
    }

    public int getInterfaceSubclass()
    {
        return mSubclass;
    }

    public String getName()
    {
        return mName;
    }

    public void setEndpoints(Parcelable aparcelable[])
    {
        mEndpoints = (Parcelable[])Preconditions.checkArrayElementsNotNull(aparcelable, "endpoints");
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("UsbInterface[mId=").append(mId).append(",mAlternateSetting=").append(mAlternateSetting).append(",mName=").append(mName).append(",mClass=").append(mClass).append(",mSubclass=").append(mSubclass).append(",mProtocol=").append(mProtocol).append(",mEndpoints=[").toString());
        for(int i = 0; i < mEndpoints.length; i++)
        {
            stringbuilder.append("\n");
            stringbuilder.append(mEndpoints[i].toString());
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mId);
        parcel.writeInt(mAlternateSetting);
        parcel.writeString(mName);
        parcel.writeInt(mClass);
        parcel.writeInt(mSubclass);
        parcel.writeInt(mProtocol);
        parcel.writeParcelableArray(mEndpoints, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsbInterface createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            Object obj = parcel.readString();
            int k = parcel.readInt();
            int l = parcel.readInt();
            int i1 = parcel.readInt();
            parcel = parcel.readParcelableArray(android/hardware/usb/UsbEndpoint.getClassLoader());
            obj = new UsbInterface(i, j, ((String) (obj)), k, l, i1);
            ((UsbInterface) (obj)).setEndpoints(parcel);
            return ((UsbInterface) (obj));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsbInterface[] newArray(int i)
        {
            return new UsbInterface[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mAlternateSetting;
    private final int mClass;
    private Parcelable mEndpoints[];
    private final int mId;
    private final String mName;
    private final int mProtocol;
    private final int mSubclass;

}
