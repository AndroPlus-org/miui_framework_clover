// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public final class VendorTagDescriptor
    implements Parcelable
{

    private VendorTagDescriptor(Parcel parcel)
    {
    }

    VendorTagDescriptor(Parcel parcel, VendorTagDescriptor vendortagdescriptor)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(parcel == null)
            throw new IllegalArgumentException("dest must not be null");
        else
            return;
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VendorTagDescriptor createFromParcel(Parcel parcel)
        {
            try
            {
                parcel = new VendorTagDescriptor(parcel, null);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                Log.e("VendorTagDescriptor", "Exception creating VendorTagDescriptor from parcel", parcel);
                return null;
            }
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VendorTagDescriptor[] newArray(int i)
        {
            return new VendorTagDescriptor[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "VendorTagDescriptor";

}
