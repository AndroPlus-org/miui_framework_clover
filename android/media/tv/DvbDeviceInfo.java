// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public final class DvbDeviceInfo
    implements Parcelable
{

    public DvbDeviceInfo(int i, int j)
    {
        mAdapterId = i;
        mDeviceId = j;
    }

    private DvbDeviceInfo(Parcel parcel)
    {
        mAdapterId = parcel.readInt();
        mDeviceId = parcel.readInt();
    }

    DvbDeviceInfo(Parcel parcel, DvbDeviceInfo dvbdeviceinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAdapterId()
    {
        return mAdapterId;
    }

    public int getDeviceId()
    {
        return mDeviceId;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mAdapterId);
        parcel.writeInt(mDeviceId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DvbDeviceInfo createFromParcel(Parcel parcel)
        {
            try
            {
                parcel = new DvbDeviceInfo(parcel, null);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                Log.e("DvbDeviceInfo", "Exception creating DvbDeviceInfo from parcel", parcel);
                return null;
            }
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DvbDeviceInfo[] newArray(int i)
        {
            return new DvbDeviceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final String TAG = "DvbDeviceInfo";
    private final int mAdapterId;
    private final int mDeviceId;

}
