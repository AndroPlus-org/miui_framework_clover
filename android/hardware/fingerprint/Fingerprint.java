// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.fingerprint;

import android.os.Parcel;
import android.os.Parcelable;

public final class Fingerprint
    implements Parcelable
{

    private Fingerprint(Parcel parcel)
    {
        mName = parcel.readString();
        mGroupId = parcel.readInt();
        mFingerId = parcel.readInt();
        mDeviceId = parcel.readLong();
    }

    Fingerprint(Parcel parcel, Fingerprint fingerprint)
    {
        this(parcel);
    }

    public Fingerprint(CharSequence charsequence, int i, int j, long l)
    {
        mName = charsequence;
        mGroupId = i;
        mFingerId = j;
        mDeviceId = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public long getDeviceId()
    {
        return mDeviceId;
    }

    public int getFingerId()
    {
        return mFingerId;
    }

    public int getGroupId()
    {
        return mGroupId;
    }

    public CharSequence getName()
    {
        return mName;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mName.toString());
        parcel.writeInt(mGroupId);
        parcel.writeInt(mFingerId);
        parcel.writeLong(mDeviceId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Fingerprint createFromParcel(Parcel parcel)
        {
            return new Fingerprint(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Fingerprint[] newArray(int i)
        {
            return new Fingerprint[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long mDeviceId;
    private int mFingerId;
    private int mGroupId;
    private CharSequence mName;

}
