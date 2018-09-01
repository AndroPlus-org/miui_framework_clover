// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.os.Parcel;
import android.os.Parcelable;

public final class SnoozeCriterion
    implements Parcelable
{

    protected SnoozeCriterion(Parcel parcel)
    {
        if(parcel.readByte() != 0)
            mId = parcel.readString();
        else
            mId = null;
        if(parcel.readByte() != 0)
            mExplanation = parcel.readCharSequence();
        else
            mExplanation = null;
        if(parcel.readByte() != 0)
            mConfirmation = parcel.readCharSequence();
        else
            mConfirmation = null;
    }

    public SnoozeCriterion(String s, CharSequence charsequence, CharSequence charsequence1)
    {
        mId = s;
        mExplanation = charsequence;
        mConfirmation = charsequence1;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (SnoozeCriterion)obj;
        if(mId == null ? ((SnoozeCriterion) (obj)).mId != null : mId.equals(((SnoozeCriterion) (obj)).mId) ^ true)
            return false;
        if(mExplanation == null ? ((SnoozeCriterion) (obj)).mExplanation != null : mExplanation.equals(((SnoozeCriterion) (obj)).mExplanation) ^ true)
            return false;
        if(mConfirmation == null) goto _L2; else goto _L1
_L1:
        flag = mConfirmation.equals(((SnoozeCriterion) (obj)).mConfirmation);
_L4:
        return flag;
_L2:
        if(((SnoozeCriterion) (obj)).mConfirmation != null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CharSequence getConfirmation()
    {
        return mConfirmation;
    }

    public CharSequence getExplanation()
    {
        return mExplanation;
    }

    public String getId()
    {
        return mId;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        if(mId != null)
            j = mId.hashCode();
        else
            j = 0;
        if(mExplanation != null)
            k = mExplanation.hashCode();
        else
            k = 0;
        if(mConfirmation != null)
            i = mConfirmation.hashCode();
        return (j * 31 + k) * 31 + i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mId != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeString(mId);
        } else
        {
            parcel.writeByte((byte)0);
        }
        if(mExplanation != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeCharSequence(mExplanation);
        } else
        {
            parcel.writeByte((byte)0);
        }
        if(mConfirmation != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeCharSequence(mConfirmation);
        } else
        {
            parcel.writeByte((byte)0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SnoozeCriterion createFromParcel(Parcel parcel)
        {
            return new SnoozeCriterion(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SnoozeCriterion[] newArray(int i)
        {
            return new SnoozeCriterion[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final CharSequence mConfirmation;
    private final CharSequence mExplanation;
    private final String mId;

}
