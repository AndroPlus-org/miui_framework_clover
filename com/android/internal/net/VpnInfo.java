// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.net;

import android.os.Parcel;
import android.os.Parcelable;

public class VpnInfo
    implements Parcelable
{

    public VpnInfo()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("VpnInfo{ownerUid=").append(ownerUid).append(", vpnIface='").append(vpnIface).append('\'').append(", primaryUnderlyingIface='").append(primaryUnderlyingIface).append('\'').append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(ownerUid);
        parcel.writeString(vpnIface);
        parcel.writeString(primaryUnderlyingIface);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VpnInfo createFromParcel(Parcel parcel)
        {
            VpnInfo vpninfo = new VpnInfo();
            vpninfo.ownerUid = parcel.readInt();
            vpninfo.vpnIface = parcel.readString();
            vpninfo.primaryUnderlyingIface = parcel.readString();
            return vpninfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VpnInfo[] newArray(int i)
        {
            return new VpnInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int ownerUid;
    public String primaryUnderlyingIface;
    public String vpnIface;

}
