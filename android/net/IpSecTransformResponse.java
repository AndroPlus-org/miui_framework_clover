// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

public final class IpSecTransformResponse
    implements Parcelable
{

    public IpSecTransformResponse(int i)
    {
        if(i == 0)
        {
            throw new IllegalArgumentException("Valid status implies other args must be provided");
        } else
        {
            status = i;
            resourceId = 0;
            return;
        }
    }

    public IpSecTransformResponse(int i, int j)
    {
        status = i;
        resourceId = j;
    }

    private IpSecTransformResponse(Parcel parcel)
    {
        status = parcel.readInt();
        resourceId = parcel.readInt();
    }

    IpSecTransformResponse(Parcel parcel, IpSecTransformResponse ipsectransformresponse)
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
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IpSecTransformResponse createFromParcel(Parcel parcel)
        {
            return new IpSecTransformResponse(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IpSecTransformResponse[] newArray(int i)
        {
            return new IpSecTransformResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "IpSecTransformResponse";
    public final int resourceId;
    public final int status;

}
