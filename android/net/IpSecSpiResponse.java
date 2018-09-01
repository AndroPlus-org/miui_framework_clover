// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

public final class IpSecSpiResponse
    implements Parcelable
{

    public IpSecSpiResponse(int i)
    {
        if(i == 0)
        {
            throw new IllegalArgumentException("Valid status implies other args must be provided");
        } else
        {
            status = i;
            resourceId = 0;
            spi = 0;
            return;
        }
    }

    public IpSecSpiResponse(int i, int j, int k)
    {
        status = i;
        resourceId = j;
        spi = k;
    }

    private IpSecSpiResponse(Parcel parcel)
    {
        status = parcel.readInt();
        resourceId = parcel.readInt();
        spi = parcel.readInt();
    }

    IpSecSpiResponse(Parcel parcel, IpSecSpiResponse ipsecspiresponse)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(status);
        parcel.writeInt(resourceId);
        parcel.writeInt(spi);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpSecSpiResponse createFromParcel(Parcel parcel)
        {
            return new IpSecSpiResponse(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpSecSpiResponse[] newArray(int i)
        {
            return new IpSecSpiResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "IpSecSpiResponse";
    public final int resourceId;
    public final int spi;
    public final int status;

}
