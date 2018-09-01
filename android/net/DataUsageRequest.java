// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

// Referenced classes of package android.net:
//            NetworkTemplate

public final class DataUsageRequest
    implements Parcelable
{

    public DataUsageRequest(int i, NetworkTemplate networktemplate, long l)
    {
        requestId = i;
        template = networktemplate;
        thresholdInBytes = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof DataUsageRequest))
            return false;
        obj = (DataUsageRequest)obj;
        boolean flag1 = flag;
        if(((DataUsageRequest) (obj)).requestId == requestId)
        {
            flag1 = flag;
            if(Objects.equals(((DataUsageRequest) (obj)).template, template))
            {
                flag1 = flag;
                if(((DataUsageRequest) (obj)).thresholdInBytes == thresholdInBytes)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(requestId), template, Long.valueOf(thresholdInBytes)
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("DataUsageRequest [ requestId=").append(requestId).append(", networkTemplate=").append(template).append(", thresholdInBytes=").append(thresholdInBytes).append(" ]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(requestId);
        parcel.writeParcelable(template, i);
        parcel.writeLong(thresholdInBytes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DataUsageRequest createFromParcel(Parcel parcel)
        {
            return new DataUsageRequest(parcel.readInt(), (NetworkTemplate)parcel.readParcelable(null), parcel.readLong());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DataUsageRequest[] newArray(int i)
        {
            return new DataUsageRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String PARCELABLE_KEY = "DataUsageRequest";
    public static final int REQUEST_ID_UNSET = 0;
    public final int requestId;
    public final NetworkTemplate template;
    public final long thresholdInBytes;

}
