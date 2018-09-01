// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;

public class RssiPacketCountInfo
    implements Parcelable
{

    public RssiPacketCountInfo()
    {
        rxgood = 0;
        txbad = 0;
        txgood = 0;
        rssi = 0;
    }

    private RssiPacketCountInfo(Parcel parcel)
    {
        rssi = parcel.readInt();
        txgood = parcel.readInt();
        txbad = parcel.readInt();
        rxgood = parcel.readInt();
    }

    RssiPacketCountInfo(Parcel parcel, RssiPacketCountInfo rssipacketcountinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(rssi);
        parcel.writeInt(txgood);
        parcel.writeInt(txbad);
        parcel.writeInt(rxgood);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RssiPacketCountInfo createFromParcel(Parcel parcel)
        {
            return new RssiPacketCountInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RssiPacketCountInfo[] newArray(int i)
        {
            return new RssiPacketCountInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int rssi;
    public int rxgood;
    public int txbad;
    public int txgood;

}
