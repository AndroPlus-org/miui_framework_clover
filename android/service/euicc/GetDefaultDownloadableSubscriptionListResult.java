// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.euicc.DownloadableSubscription;

public final class GetDefaultDownloadableSubscriptionListResult
    implements Parcelable
{

    public GetDefaultDownloadableSubscriptionListResult(int i, DownloadableSubscription adownloadablesubscription[])
    {
        result = i;
        if(result == 0)
        {
            subscriptions = adownloadablesubscription;
        } else
        {
            if(adownloadablesubscription != null)
                throw new IllegalArgumentException((new StringBuilder()).append("Error result with non-null subscriptions: ").append(i).toString());
            subscriptions = null;
        }
    }

    private GetDefaultDownloadableSubscriptionListResult(Parcel parcel)
    {
        result = parcel.readInt();
        subscriptions = (DownloadableSubscription[])parcel.createTypedArray(DownloadableSubscription.CREATOR);
    }

    GetDefaultDownloadableSubscriptionListResult(Parcel parcel, GetDefaultDownloadableSubscriptionListResult getdefaultdownloadablesubscriptionlistresult)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(result);
        parcel.writeTypedArray(subscriptions, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GetDefaultDownloadableSubscriptionListResult createFromParcel(Parcel parcel)
        {
            return new GetDefaultDownloadableSubscriptionListResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GetDefaultDownloadableSubscriptionListResult[] newArray(int i)
        {
            return new GetDefaultDownloadableSubscriptionListResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int result;
    public final DownloadableSubscription subscriptions[];

}
