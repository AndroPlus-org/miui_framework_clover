// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.input;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Objects;

public final class InputDeviceIdentifier
    implements Parcelable
{

    private InputDeviceIdentifier(Parcel parcel)
    {
        mDescriptor = parcel.readString();
        mVendorId = parcel.readInt();
        mProductId = parcel.readInt();
    }

    InputDeviceIdentifier(Parcel parcel, InputDeviceIdentifier inputdeviceidentifier)
    {
        this(parcel);
    }

    public InputDeviceIdentifier(String s, int i, int j)
    {
        mDescriptor = s;
        mVendorId = i;
        mProductId = j;
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
        if(obj == null || (obj instanceof InputDeviceIdentifier) ^ true)
            return false;
        obj = (InputDeviceIdentifier)obj;
        boolean flag1 = flag;
        if(mVendorId == ((InputDeviceIdentifier) (obj)).mVendorId)
        {
            flag1 = flag;
            if(mProductId == ((InputDeviceIdentifier) (obj)).mProductId)
                flag1 = TextUtils.equals(mDescriptor, ((InputDeviceIdentifier) (obj)).mDescriptor);
        }
        return flag1;
    }

    public String getDescriptor()
    {
        return mDescriptor;
    }

    public int getProductId()
    {
        return mProductId;
    }

    public int getVendorId()
    {
        return mVendorId;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mDescriptor, Integer.valueOf(mVendorId), Integer.valueOf(mProductId)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mDescriptor);
        parcel.writeInt(mVendorId);
        parcel.writeInt(mProductId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InputDeviceIdentifier createFromParcel(Parcel parcel)
        {
            return new InputDeviceIdentifier(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InputDeviceIdentifier[] newArray(int i)
        {
            return new InputDeviceIdentifier[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mDescriptor;
    private final int mProductId;
    private final int mVendorId;

}
