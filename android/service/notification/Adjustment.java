// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.os.*;

public final class Adjustment
    implements Parcelable
{

    protected Adjustment(Parcel parcel)
    {
        if(parcel.readInt() == 1)
            mPackage = parcel.readString();
        else
            mPackage = null;
        if(parcel.readInt() == 1)
            mKey = parcel.readString();
        else
            mKey = null;
        if(parcel.readInt() == 1)
            mExplanation = parcel.readCharSequence();
        else
            mExplanation = null;
        mSignals = parcel.readBundle();
        mUser = parcel.readInt();
    }

    public Adjustment(String s, String s1, Bundle bundle, CharSequence charsequence, int i)
    {
        mPackage = s;
        mKey = s1;
        mSignals = bundle;
        mExplanation = charsequence;
        mUser = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public CharSequence getExplanation()
    {
        return mExplanation;
    }

    public String getKey()
    {
        return mKey;
    }

    public String getPackage()
    {
        return mPackage;
    }

    public Bundle getSignals()
    {
        return mSignals;
    }

    public int getUser()
    {
        return mUser;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Adjustment{mSignals=").append(mSignals).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mPackage != null)
        {
            parcel.writeInt(1);
            parcel.writeString(mPackage);
        } else
        {
            parcel.writeInt(0);
        }
        if(mKey != null)
        {
            parcel.writeInt(1);
            parcel.writeString(mKey);
        } else
        {
            parcel.writeInt(0);
        }
        if(mExplanation != null)
        {
            parcel.writeInt(1);
            parcel.writeCharSequence(mExplanation);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeBundle(mSignals);
        parcel.writeInt(mUser);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Adjustment createFromParcel(Parcel parcel)
        {
            return new Adjustment(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Adjustment[] newArray(int i)
        {
            return new Adjustment[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String KEY_GROUP_KEY = "key_group_key";
    public static final String KEY_PEOPLE = "key_people";
    public static final String KEY_SNOOZE_CRITERIA = "key_snooze_criteria";
    private final CharSequence mExplanation;
    private final String mKey;
    private final String mPackage;
    private final Bundle mSignals;
    private final int mUser;

}
