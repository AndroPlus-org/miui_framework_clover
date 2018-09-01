// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

// Referenced classes of package android.content:
//            Context

public class RestrictionEntry
    implements Parcelable
{

    public RestrictionEntry(int i, String s)
    {
        mType = i;
        mKey = s;
    }

    public RestrictionEntry(Parcel parcel)
    {
        mType = parcel.readInt();
        mKey = parcel.readString();
        mTitle = parcel.readString();
        mDescription = parcel.readString();
        mChoiceEntries = parcel.readStringArray();
        mChoiceValues = parcel.readStringArray();
        mCurrentValue = parcel.readString();
        mCurrentValues = parcel.readStringArray();
        parcel = parcel.readParcelableArray(null);
        if(parcel != null)
        {
            mRestrictions = new RestrictionEntry[parcel.length];
            for(int i = 0; i < parcel.length; i++)
                mRestrictions[i] = (RestrictionEntry)parcel[i];

        }
    }

    public RestrictionEntry(String s, int i)
    {
        mKey = s;
        mType = 5;
        setIntValue(i);
    }

    public RestrictionEntry(String s, String s1)
    {
        mKey = s;
        mType = 2;
        mCurrentValue = s1;
    }

    public RestrictionEntry(String s, boolean flag)
    {
        mKey = s;
        mType = 1;
        setSelectedState(flag);
    }

    private RestrictionEntry(String s, RestrictionEntry arestrictionentry[], boolean flag)
    {
        mKey = s;
        if(flag)
        {
            mType = 8;
            if(arestrictionentry != null)
            {
                int i = 0;
                for(int j = arestrictionentry.length; i < j; i++)
                    if(arestrictionentry[i].getType() != 7)
                        throw new IllegalArgumentException("bundle_array restriction can only have nested restriction entries of type bundle");

            }
        } else
        {
            mType = 7;
        }
        setRestrictions(arestrictionentry);
    }

    public RestrictionEntry(String s, String as[])
    {
        mKey = s;
        mType = 4;
        mCurrentValues = as;
    }

    public static RestrictionEntry createBundleArrayEntry(String s, RestrictionEntry arestrictionentry[])
    {
        return new RestrictionEntry(s, arestrictionentry, true);
    }

    public static RestrictionEntry createBundleEntry(String s, RestrictionEntry arestrictionentry[])
    {
        return new RestrictionEntry(s, arestrictionentry, false);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof RestrictionEntry))
            return false;
        obj = (RestrictionEntry)obj;
        if(mType != ((RestrictionEntry) (obj)).mType || mKey.equals(((RestrictionEntry) (obj)).mKey) ^ true)
            return false;
        if(mCurrentValues == null && ((RestrictionEntry) (obj)).mCurrentValues == null && mRestrictions == null && ((RestrictionEntry) (obj)).mRestrictions == null && Objects.equals(mCurrentValue, ((RestrictionEntry) (obj)).mCurrentValue))
            return true;
        if(mCurrentValue == null && ((RestrictionEntry) (obj)).mCurrentValue == null && mRestrictions == null && ((RestrictionEntry) (obj)).mRestrictions == null && Arrays.equals(mCurrentValues, ((RestrictionEntry) (obj)).mCurrentValues))
            return true;
        return mCurrentValue == null && ((RestrictionEntry) (obj)).mCurrentValue == null && mCurrentValue == null && ((RestrictionEntry) (obj)).mCurrentValue == null && Arrays.equals(mRestrictions, ((RestrictionEntry) (obj)).mRestrictions);
    }

    public String[] getAllSelectedStrings()
    {
        return mCurrentValues;
    }

    public String[] getChoiceEntries()
    {
        return mChoiceEntries;
    }

    public String[] getChoiceValues()
    {
        return mChoiceValues;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public int getIntValue()
    {
        return Integer.parseInt(mCurrentValue);
    }

    public String getKey()
    {
        return mKey;
    }

    public RestrictionEntry[] getRestrictions()
    {
        return mRestrictions;
    }

    public boolean getSelectedState()
    {
        return Boolean.parseBoolean(mCurrentValue);
    }

    public String getSelectedString()
    {
        return mCurrentValue;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public int getType()
    {
        return mType;
    }

    public int hashCode()
    {
        int i = mKey.hashCode() + 527;
        if(mCurrentValue == null) goto _L2; else goto _L1
_L1:
        int j = i * 31 + mCurrentValue.hashCode();
_L4:
        return j;
_L2:
        if(mCurrentValues != null)
        {
            String as[] = mCurrentValues;
            int k = 0;
            int l = as.length;
            do
            {
                j = i;
                if(k >= l)
                    continue; /* Loop/switch isn't completed */
                String s = as[k];
                j = i;
                if(s != null)
                    j = i * 31 + s.hashCode();
                k++;
                i = j;
            } while(true);
        }
        j = i;
        if(mRestrictions != null)
            j = i * 31 + Arrays.hashCode(mRestrictions);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setAllSelectedStrings(String as[])
    {
        mCurrentValues = as;
    }

    public void setChoiceEntries(Context context, int i)
    {
        mChoiceEntries = context.getResources().getStringArray(i);
    }

    public void setChoiceEntries(String as[])
    {
        mChoiceEntries = as;
    }

    public void setChoiceValues(Context context, int i)
    {
        mChoiceValues = context.getResources().getStringArray(i);
    }

    public void setChoiceValues(String as[])
    {
        mChoiceValues = as;
    }

    public void setDescription(String s)
    {
        mDescription = s;
    }

    public void setIntValue(int i)
    {
        mCurrentValue = Integer.toString(i);
    }

    public void setRestrictions(RestrictionEntry arestrictionentry[])
    {
        mRestrictions = arestrictionentry;
    }

    public void setSelectedState(boolean flag)
    {
        mCurrentValue = Boolean.toString(flag);
    }

    public void setSelectedString(String s)
    {
        mCurrentValue = s;
    }

    public void setTitle(String s)
    {
        mTitle = s;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("RestrictionEntry{mType=").append(mType).append(", mKey='").append(mKey).append('\'').append(", mTitle='").append(mTitle).append('\'').append(", mDescription='").append(mDescription).append('\'').append(", mChoiceEntries=").append(Arrays.toString(mChoiceEntries)).append(", mChoiceValues=").append(Arrays.toString(mChoiceValues)).append(", mCurrentValue='").append(mCurrentValue).append('\'').append(", mCurrentValues=").append(Arrays.toString(mCurrentValues)).append(", mRestrictions=").append(Arrays.toString(mRestrictions)).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeString(mKey);
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeStringArray(mChoiceEntries);
        parcel.writeStringArray(mChoiceValues);
        parcel.writeString(mCurrentValue);
        parcel.writeStringArray(mCurrentValues);
        parcel.writeParcelableArray(mRestrictions, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RestrictionEntry createFromParcel(Parcel parcel)
        {
            return new RestrictionEntry(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RestrictionEntry[] newArray(int i)
        {
            return new RestrictionEntry[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int TYPE_BOOLEAN = 1;
    public static final int TYPE_BUNDLE = 7;
    public static final int TYPE_BUNDLE_ARRAY = 8;
    public static final int TYPE_CHOICE = 2;
    public static final int TYPE_CHOICE_LEVEL = 3;
    public static final int TYPE_INTEGER = 5;
    public static final int TYPE_MULTI_SELECT = 4;
    public static final int TYPE_NULL = 0;
    public static final int TYPE_STRING = 6;
    private String mChoiceEntries[];
    private String mChoiceValues[];
    private String mCurrentValue;
    private String mCurrentValues[];
    private String mDescription;
    private String mKey;
    private RestrictionEntry mRestrictions[];
    private String mTitle;
    private int mType;

}
