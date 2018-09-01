// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.Parcel;
import android.os.Parcelable;

public final class HdmiHotplugEvent
    implements Parcelable
{

    public HdmiHotplugEvent(int i, boolean flag)
    {
        mPort = i;
        mConnected = flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getPort()
    {
        return mPort;
    }

    public boolean isConnected()
    {
        return mConnected;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mPort);
        if(mConnected)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public HdmiHotplugEvent createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            boolean flag;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            return new HdmiHotplugEvent(i, flag);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public HdmiHotplugEvent[] newArray(int i)
        {
            return new HdmiHotplugEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final boolean mConnected;
    private final int mPort;

}
