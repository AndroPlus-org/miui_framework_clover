// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.euicc;

import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.UiccAccessRule;
import com.android.internal.util.Preconditions;

public final class DownloadableSubscription
    implements Parcelable
{

    private DownloadableSubscription(Parcel parcel)
    {
        encodedActivationCode = parcel.readString();
        carrierName = parcel.readString();
        accessRules = (UiccAccessRule[])parcel.createTypedArray(UiccAccessRule.CREATOR);
    }

    DownloadableSubscription(Parcel parcel, DownloadableSubscription downloadablesubscription)
    {
        this(parcel);
    }

    private DownloadableSubscription(String s)
    {
        encodedActivationCode = s;
    }

    public static DownloadableSubscription forActivationCode(String s)
    {
        Preconditions.checkNotNull(s, "Activation code may not be null");
        return new DownloadableSubscription(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public UiccAccessRule[] getAccessRules()
    {
        return accessRules;
    }

    public String getCarrierName()
    {
        return carrierName;
    }

    public void setAccessRules(UiccAccessRule auiccaccessrule[])
    {
        accessRules = auiccaccessrule;
    }

    public void setCarrierName(String s)
    {
        carrierName = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(encodedActivationCode);
        parcel.writeString(carrierName);
        parcel.writeTypedArray(accessRules, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DownloadableSubscription createFromParcel(Parcel parcel)
        {
            return new DownloadableSubscription(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DownloadableSubscription[] newArray(int i)
        {
            return new DownloadableSubscription[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private UiccAccessRule accessRules[];
    private String carrierName;
    public final String encodedActivationCode;

}
