// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;

// Referenced classes of package android.preference:
//            DialogPreference

public class ListPreference extends DialogPreference
{
    private static class SavedState extends Preference.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeString(value);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        String value;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            value = parcel.readString();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    static int _2D_set0(ListPreference listpreference, int i)
    {
        listpreference.mClickedDialogEntryIndex = i;
        return i;
    }

    public ListPreference(Context context)
    {
        this(context, null);
    }

    public ListPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010091);
    }

    public ListPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ListPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ListPreference, i, j);
        mEntries = typedarray.getTextArray(0);
        mEntryValues = typedarray.getTextArray(1);
        typedarray.recycle();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Preference, i, j);
        mSummary = context.getString(7);
        context.recycle();
    }

    private int getValueIndex()
    {
        return findIndexOfValue(mValue);
    }

    public int findIndexOfValue(String s)
    {
        if(s != null && mEntryValues != null)
        {
            for(int i = mEntryValues.length - 1; i >= 0; i--)
                if(mEntryValues[i].equals(s))
                    return i;

        }
        return -1;
    }

    public CharSequence[] getEntries()
    {
        return mEntries;
    }

    public CharSequence getEntry()
    {
        Object obj = null;
        int i = getValueIndex();
        CharSequence charsequence = obj;
        if(i >= 0)
        {
            charsequence = obj;
            if(mEntries != null)
                charsequence = mEntries[i];
        }
        return charsequence;
    }

    public CharSequence[] getEntryValues()
    {
        return mEntryValues;
    }

    public CharSequence getSummary()
    {
        CharSequence charsequence = getEntry();
        if(mSummary == null)
            return super.getSummary();
        String s = mSummary;
        Object obj = charsequence;
        if(charsequence == null)
            obj = "";
        return String.format(s, new Object[] {
            obj
        });
    }

    public String getValue()
    {
        return mValue;
    }

    protected void onDialogClosed(boolean flag)
    {
        super.onDialogClosed(flag);
        if(flag && mClickedDialogEntryIndex >= 0 && mEntryValues != null)
        {
            String s = mEntryValues[mClickedDialogEntryIndex].toString();
            if(callChangeListener(s))
                setValue(s);
        }
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return typedarray.getString(i);
    }

    protected void onPrepareDialogBuilder(android.app.AlertDialog.Builder builder)
    {
        super.onPrepareDialogBuilder(builder);
        if(mEntries == null || mEntryValues == null)
        {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        } else
        {
            mClickedDialogEntryIndex = getValueIndex();
            builder.setSingleChoiceItems(mEntries, mClickedDialogEntryIndex, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    ListPreference._2D_set0(ListPreference.this, i);
                    ListPreference.this.onClick(dialoginterface, -1);
                    dialoginterface.dismiss();
                }

                final ListPreference this$0;

            
            {
                this$0 = ListPreference.this;
                super();
            }
            }
);
            builder.setPositiveButton(null, null);
            return;
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable == null || parcelable.getClass().equals(android/preference/ListPreference$SavedState) ^ true)
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            setValue(((SavedState) (parcelable)).value);
            return;
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        Object obj = super.onSaveInstanceState();
        if(isPersistent())
        {
            return ((Parcelable) (obj));
        } else
        {
            obj = new SavedState(((Parcelable) (obj)));
            obj.value = getValue();
            return ((Parcelable) (obj));
        }
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
        if(flag)
            obj = getPersistedString(mValue);
        else
            obj = (String)obj;
        setValue(((String) (obj)));
    }

    public void setEntries(int i)
    {
        setEntries(getContext().getResources().getTextArray(i));
    }

    public void setEntries(CharSequence acharsequence[])
    {
        mEntries = acharsequence;
    }

    public void setEntryValues(int i)
    {
        setEntryValues(getContext().getResources().getTextArray(i));
    }

    public void setEntryValues(CharSequence acharsequence[])
    {
        mEntryValues = acharsequence;
    }

    public void setSummary(CharSequence charsequence)
    {
        super.setSummary(charsequence);
        if(charsequence != null || mSummary == null) goto _L2; else goto _L1
_L1:
        mSummary = null;
_L4:
        return;
_L2:
        if(charsequence != null && charsequence.equals(mSummary) ^ true)
            mSummary = charsequence.toString();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setValue(String s)
    {
        boolean flag = TextUtils.equals(mValue, s) ^ true;
        if(flag || mValueSet ^ true)
        {
            mValue = s;
            mValueSet = true;
            persistString(s);
            if(flag)
                notifyChanged();
        }
    }

    public void setValueIndex(int i)
    {
        if(mEntryValues != null)
            setValue(mEntryValues[i].toString());
    }

    private int mClickedDialogEntryIndex;
    private CharSequence mEntries[];
    private CharSequence mEntryValues[];
    private String mSummary;
    private String mValue;
    private boolean mValueSet;
}
