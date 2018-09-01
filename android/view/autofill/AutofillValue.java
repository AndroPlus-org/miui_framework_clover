// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import java.util.Objects;

// Referenced classes of package android.view.autofill:
//            Helper

public final class AutofillValue
    implements Parcelable
{

    private AutofillValue(int i, Object obj)
    {
        mType = i;
        mValue = obj;
    }

    private AutofillValue(Parcel parcel)
    {
        boolean flag;
        flag = false;
        super();
        mType = parcel.readInt();
        mType;
        JVM INSTR tableswitch 1 4: default 48
    //                   1 83
    //                   2 92
    //                   3 112
    //                   4 126;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("type=").append(mType).append(" not valid").toString());
_L2:
        mValue = parcel.readCharSequence();
_L7:
        return;
_L3:
        if(parcel.readInt() != 0)
            flag = true;
        mValue = Boolean.valueOf(flag);
        continue; /* Loop/switch isn't completed */
_L4:
        mValue = Integer.valueOf(parcel.readInt());
        continue; /* Loop/switch isn't completed */
_L5:
        mValue = Long.valueOf(parcel.readLong());
        if(true) goto _L7; else goto _L6
_L6:
    }

    AutofillValue(Parcel parcel, AutofillValue autofillvalue)
    {
        this(parcel);
    }

    public static AutofillValue forDate(long l)
    {
        return new AutofillValue(4, Long.valueOf(l));
    }

    public static AutofillValue forList(int i)
    {
        return new AutofillValue(3, Integer.valueOf(i));
    }

    public static AutofillValue forText(CharSequence charsequence)
    {
        Object obj = null;
        if(charsequence == null)
            charsequence = obj;
        else
            charsequence = new AutofillValue(1, TextUtils.trimNoCopySpans(charsequence));
        return charsequence;
    }

    public static AutofillValue forToggle(boolean flag)
    {
        return new AutofillValue(2, Boolean.valueOf(flag));
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (AutofillValue)obj;
        if(mType != ((AutofillValue) (obj)).mType)
            return false;
        if(isText())
            return mValue.toString().equals(((AutofillValue) (obj)).mValue.toString());
        else
            return Objects.equals(mValue, ((AutofillValue) (obj)).mValue);
    }

    public long getDateValue()
    {
        Preconditions.checkState(isDate(), (new StringBuilder()).append("value must be a date value, not type=").append(mType).toString());
        return ((Long)mValue).longValue();
    }

    public int getListValue()
    {
        Preconditions.checkState(isList(), (new StringBuilder()).append("value must be a list value, not type=").append(mType).toString());
        return ((Integer)mValue).intValue();
    }

    public CharSequence getTextValue()
    {
        Preconditions.checkState(isText(), (new StringBuilder()).append("value must be a text value, not type=").append(mType).toString());
        return (CharSequence)mValue;
    }

    public boolean getToggleValue()
    {
        Preconditions.checkState(isToggle(), (new StringBuilder()).append("value must be a toggle value, not type=").append(mType).toString());
        return ((Boolean)mValue).booleanValue();
    }

    public int hashCode()
    {
        return mType + mValue.hashCode();
    }

    public boolean isDate()
    {
        boolean flag;
        if(mType == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEmpty()
    {
        boolean flag;
        if(isText() && ((CharSequence)mValue).length() == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isList()
    {
        boolean flag;
        if(mType == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isText()
    {
        boolean flag = true;
        if(mType != 1)
            flag = false;
        return flag;
    }

    public boolean isToggle()
    {
        boolean flag;
        if(mType == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        if(!Helper.sDebug)
            return super.toString();
        StringBuilder stringbuilder = (new StringBuilder()).append("[type=").append(mType).append(", value=");
        if(isText())
            stringbuilder.append(((CharSequence)mValue).length()).append("_chars");
        else
            stringbuilder.append(mValue);
        return stringbuilder.append(']').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        mType;
        JVM INSTR tableswitch 1 4: default 44
    //                   1 45
    //                   2 59
    //                   3 87
    //                   4 104;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        parcel.writeCharSequence((CharSequence)mValue);
        continue; /* Loop/switch isn't completed */
_L3:
        if(((Boolean)mValue).booleanValue())
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        continue; /* Loop/switch isn't completed */
_L4:
        parcel.writeInt(((Integer)mValue).intValue());
        continue; /* Loop/switch isn't completed */
_L5:
        parcel.writeLong(((Long)mValue).longValue());
        if(true) goto _L1; else goto _L6
_L6:
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AutofillValue createFromParcel(Parcel parcel)
        {
            return new AutofillValue(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AutofillValue[] newArray(int i)
        {
            return new AutofillValue[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mType;
    private final Object mValue;

}
