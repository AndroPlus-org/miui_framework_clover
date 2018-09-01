// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.UiccAccessRule;
import android.text.TextUtils;

public final class EuiccProfileInfo
    implements Parcelable
{

    private EuiccProfileInfo(Parcel parcel)
    {
        iccid = parcel.readString();
        accessRules = (UiccAccessRule[])parcel.createTypedArray(UiccAccessRule.CREATOR);
        nickname = parcel.readString();
    }

    EuiccProfileInfo(Parcel parcel, EuiccProfileInfo euiccprofileinfo)
    {
        this(parcel);
    }

    public EuiccProfileInfo(String s, UiccAccessRule auiccaccessrule[], String s1)
    {
        if(!TextUtils.isDigitsOnly(s))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("iccid contains invalid characters: ").append(s).toString());
        } else
        {
            iccid = s;
            accessRules = auiccaccessrule;
            nickname = s1;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(iccid);
        parcel.writeTypedArray(accessRules, i);
        parcel.writeString(nickname);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public EuiccProfileInfo createFromParcel(Parcel parcel)
        {
            return new EuiccProfileInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public EuiccProfileInfo[] newArray(int i)
        {
            return new EuiccProfileInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final UiccAccessRule accessRules[];
    public final String iccid;
    public final String nickname;

}
