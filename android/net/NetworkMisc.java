// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

public class NetworkMisc
    implements Parcelable
{

    public NetworkMisc()
    {
    }

    public NetworkMisc(NetworkMisc networkmisc)
    {
        if(networkmisc != null)
        {
            allowBypass = networkmisc.allowBypass;
            explicitlySelected = networkmisc.explicitlySelected;
            acceptUnvalidated = networkmisc.acceptUnvalidated;
            subscriberId = networkmisc.subscriberId;
            provisioningNotificationDisabled = networkmisc.provisioningNotificationDisabled;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(allowBypass)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(explicitlySelected)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(acceptUnvalidated)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(subscriberId);
        if(provisioningNotificationDisabled)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkMisc createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            NetworkMisc networkmisc = new NetworkMisc();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            networkmisc.allowBypass = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            networkmisc.explicitlySelected = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            networkmisc.acceptUnvalidated = flag1;
            networkmisc.subscriberId = parcel.readString();
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            networkmisc.provisioningNotificationDisabled = flag1;
            return networkmisc;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkMisc[] newArray(int i)
        {
            return new NetworkMisc[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public boolean acceptUnvalidated;
    public boolean allowBypass;
    public boolean explicitlySelected;
    public boolean provisioningNotificationDisabled;
    public String subscriberId;

}
