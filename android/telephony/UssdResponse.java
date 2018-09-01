// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public final class UssdResponse
    implements Parcelable
{

    public UssdResponse(String s, CharSequence charsequence)
    {
        mUssdRequest = s;
        mReturnMessage = charsequence;
    }

    public int describeContents()
    {
        return 0;
    }

    public CharSequence getReturnMessage()
    {
        return mReturnMessage;
    }

    public String getUssdRequest()
    {
        return mUssdRequest;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mUssdRequest);
        TextUtils.writeToParcel(mReturnMessage, parcel, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UssdResponse createFromParcel(Parcel parcel)
        {
            return new UssdResponse(parcel.readString(), (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UssdResponse[] newArray(int i)
        {
            return new UssdResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private CharSequence mReturnMessage;
    private String mUssdRequest;

}
