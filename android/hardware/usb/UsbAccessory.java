// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

public class UsbAccessory
    implements Parcelable
{

    public UsbAccessory(String s, String s1, String s2, String s3, String s4, String s5)
    {
        mManufacturer = (String)Preconditions.checkNotNull(s);
        mModel = (String)Preconditions.checkNotNull(s1);
        mDescription = s2;
        mVersion = s3;
        mUri = s4;
        mSerial = s5;
    }

    public UsbAccessory(String as[])
    {
        this(as[0], as[1], as[2], as[3], as[4], as[5]);
    }

    private static boolean compare(String s, String s1)
    {
        if(s == null)
        {
            boolean flag;
            if(s1 == null)
                flag = true;
            else
                flag = false;
            return flag;
        } else
        {
            return s.equals(s1);
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof UsbAccessory)
        {
            obj = (UsbAccessory)obj;
            boolean flag1 = flag;
            if(compare(mManufacturer, ((UsbAccessory) (obj)).getManufacturer()))
            {
                flag1 = flag;
                if(compare(mModel, ((UsbAccessory) (obj)).getModel()))
                {
                    flag1 = flag;
                    if(compare(mDescription, ((UsbAccessory) (obj)).getDescription()))
                    {
                        flag1 = flag;
                        if(compare(mVersion, ((UsbAccessory) (obj)).getVersion()))
                        {
                            flag1 = flag;
                            if(compare(mUri, ((UsbAccessory) (obj)).getUri()))
                                flag1 = compare(mSerial, ((UsbAccessory) (obj)).getSerial());
                        }
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public String getDescription()
    {
        return mDescription;
    }

    public String getManufacturer()
    {
        return mManufacturer;
    }

    public String getModel()
    {
        return mModel;
    }

    public String getSerial()
    {
        return mSerial;
    }

    public String getUri()
    {
        return mUri;
    }

    public String getVersion()
    {
        return mVersion;
    }

    public int hashCode()
    {
        int i = 0;
        int j = mManufacturer.hashCode();
        int k = mModel.hashCode();
        int l;
        int i1;
        int j1;
        if(mDescription == null)
            l = 0;
        else
            l = mDescription.hashCode();
        if(mVersion == null)
            i1 = 0;
        else
            i1 = mVersion.hashCode();
        if(mUri == null)
            j1 = 0;
        else
            j1 = mUri.hashCode();
        if(mSerial != null)
            i = mSerial.hashCode();
        return j1 ^ (k ^ j ^ l ^ i1) ^ i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UsbAccessory[mManufacturer=").append(mManufacturer).append(", mModel=").append(mModel).append(", mDescription=").append(mDescription).append(", mVersion=").append(mVersion).append(", mUri=").append(mUri).append(", mSerial=").append(mSerial).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mManufacturer);
        parcel.writeString(mModel);
        parcel.writeString(mDescription);
        parcel.writeString(mVersion);
        parcel.writeString(mUri);
        parcel.writeString(mSerial);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsbAccessory createFromParcel(Parcel parcel)
        {
            return new UsbAccessory(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsbAccessory[] newArray(int i)
        {
            return new UsbAccessory[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DESCRIPTION_STRING = 2;
    public static final int MANUFACTURER_STRING = 0;
    public static final int MODEL_STRING = 1;
    public static final int SERIAL_STRING = 5;
    private static final String TAG = "UsbAccessory";
    public static final int URI_STRING = 4;
    public static final int VERSION_STRING = 3;
    private final String mDescription;
    private final String mManufacturer;
    private final String mModel;
    private final String mSerial;
    private final String mUri;
    private final String mVersion;

}
