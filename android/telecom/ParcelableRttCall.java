// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.*;

public class ParcelableRttCall
    implements Parcelable
{

    public ParcelableRttCall(int i, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1)
    {
        mRttMode = i;
        mTransmitStream = parcelfiledescriptor;
        mReceiveStream = parcelfiledescriptor1;
    }

    protected ParcelableRttCall(Parcel parcel)
    {
        mRttMode = parcel.readInt();
        mTransmitStream = (ParcelFileDescriptor)parcel.readParcelable(android/os/ParcelFileDescriptor.getClassLoader());
        mReceiveStream = (ParcelFileDescriptor)parcel.readParcelable(android/os/ParcelFileDescriptor.getClassLoader());
    }

    public int describeContents()
    {
        return 0;
    }

    public ParcelFileDescriptor getReceiveStream()
    {
        return mReceiveStream;
    }

    public int getRttMode()
    {
        return mRttMode;
    }

    public ParcelFileDescriptor getTransmitStream()
    {
        return mTransmitStream;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRttMode);
        parcel.writeParcelable(mTransmitStream, i);
        parcel.writeParcelable(mReceiveStream, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ParcelableRttCall createFromParcel(Parcel parcel)
        {
            return new ParcelableRttCall(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelableRttCall[] newArray(int i)
        {
            return new ParcelableRttCall[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final ParcelFileDescriptor mReceiveStream;
    private final int mRttMode;
    private final ParcelFileDescriptor mTransmitStream;

}
