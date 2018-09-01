// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.*;
import android.telecom.PhoneAccountHandle;

public final class VisualVoicemailSms
    implements Parcelable
{
    public static class Builder
    {

        static Bundle _2D_get0(Builder builder)
        {
            return builder.mFields;
        }

        static String _2D_get1(Builder builder)
        {
            return builder.mMessageBody;
        }

        static PhoneAccountHandle _2D_get2(Builder builder)
        {
            return builder.mPhoneAccountHandle;
        }

        static String _2D_get3(Builder builder)
        {
            return builder.mPrefix;
        }

        public VisualVoicemailSms build()
        {
            return new VisualVoicemailSms(this);
        }

        public Builder setFields(Bundle bundle)
        {
            mFields = bundle;
            return this;
        }

        public Builder setMessageBody(String s)
        {
            mMessageBody = s;
            return this;
        }

        public Builder setPhoneAccountHandle(PhoneAccountHandle phoneaccounthandle)
        {
            mPhoneAccountHandle = phoneaccounthandle;
            return this;
        }

        public Builder setPrefix(String s)
        {
            mPrefix = s;
            return this;
        }

        private Bundle mFields;
        private String mMessageBody;
        private PhoneAccountHandle mPhoneAccountHandle;
        private String mPrefix;

        public Builder()
        {
        }
    }


    VisualVoicemailSms(Builder builder)
    {
        mPhoneAccountHandle = Builder._2D_get2(builder);
        mPrefix = Builder._2D_get3(builder);
        mFields = Builder._2D_get0(builder);
        mMessageBody = Builder._2D_get1(builder);
    }

    public int describeContents()
    {
        return 0;
    }

    public Bundle getFields()
    {
        return mFields;
    }

    public String getMessageBody()
    {
        return mMessageBody;
    }

    public PhoneAccountHandle getPhoneAccountHandle()
    {
        return mPhoneAccountHandle;
    }

    public String getPrefix()
    {
        return mPrefix;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(getPhoneAccountHandle(), i);
        parcel.writeString(getPrefix());
        parcel.writeBundle(getFields());
        parcel.writeString(getMessageBody());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VisualVoicemailSms createFromParcel(Parcel parcel)
        {
            return (new Builder()).setPhoneAccountHandle((PhoneAccountHandle)parcel.readParcelable(null)).setPrefix(parcel.readString()).setFields(parcel.readBundle()).setMessageBody(parcel.readString()).build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VisualVoicemailSms[] newArray(int i)
        {
            return new VisualVoicemailSms[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Bundle mFields;
    private final String mMessageBody;
    private final PhoneAccountHandle mPhoneAccountHandle;
    private final String mPrefix;

}
