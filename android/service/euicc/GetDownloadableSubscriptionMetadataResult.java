// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.euicc.DownloadableSubscription;

public final class GetDownloadableSubscriptionMetadataResult
    implements Parcelable
{

    public GetDownloadableSubscriptionMetadataResult(int i, DownloadableSubscription downloadablesubscription)
    {
        result = i;
        if(result == 0)
        {
            subscription = downloadablesubscription;
        } else
        {
            if(downloadablesubscription != null)
                throw new IllegalArgumentException((new StringBuilder()).append("Error result with non-null subscription: ").append(i).toString());
            subscription = null;
        }
    }

    private GetDownloadableSubscriptionMetadataResult(Parcel parcel)
    {
        result = parcel.readInt();
        subscription = (DownloadableSubscription)parcel.readTypedObject(DownloadableSubscription.CREATOR);
    }

    GetDownloadableSubscriptionMetadataResult(Parcel parcel, GetDownloadableSubscriptionMetadataResult getdownloadablesubscriptionmetadataresult)
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
        parcel.writeTypedObject(subscription, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GetDownloadableSubscriptionMetadataResult createFromParcel(Parcel parcel)
        {
            return new GetDownloadableSubscriptionMetadataResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GetDownloadableSubscriptionMetadataResult[] newArray(int i)
        {
            return new GetDownloadableSubscriptionMetadataResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int result;
    public final DownloadableSubscription subscription;

}
