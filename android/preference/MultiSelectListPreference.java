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
import android.util.AttributeSet;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package android.preference:
//            DialogPreference

public class MultiSelectListPreference extends DialogPreference
{
    private static class SavedState extends Preference.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeStringArray((String[])values.toArray(new String[0]));
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
        Set values;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            values = new HashSet();
            parcel = parcel.readStringArray();
            int i = parcel.length;
            for(int j = 0; j < i; j++)
                values.add(parcel[j]);

        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    static CharSequence[] _2D_get0(MultiSelectListPreference multiselectlistpreference)
    {
        return multiselectlistpreference.mEntryValues;
    }

    static Set _2D_get1(MultiSelectListPreference multiselectlistpreference)
    {
        return multiselectlistpreference.mNewValues;
    }

    static boolean _2D_get2(MultiSelectListPreference multiselectlistpreference)
    {
        return multiselectlistpreference.mPreferenceChanged;
    }

    static boolean _2D_set0(MultiSelectListPreference multiselectlistpreference, boolean flag)
    {
        multiselectlistpreference.mPreferenceChanged = flag;
        return flag;
    }

    public MultiSelectListPreference(Context context)
    {
        this(context, null);
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010091);
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mValues = new HashSet();
        mNewValues = new HashSet();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.MultiSelectListPreference, i, j);
        mEntries = context.getTextArray(0);
        mEntryValues = context.getTextArray(1);
        context.recycle();
    }

    private boolean[] getSelectedItems()
    {
        CharSequence acharsequence[] = mEntryValues;
        int i = acharsequence.length;
        Set set = mValues;
        boolean aflag[] = new boolean[i];
        for(int j = 0; j < i; j++)
            aflag[j] = set.contains(acharsequence[j].toString());

        return aflag;
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

    public CharSequence[] getEntryValues()
    {
        return mEntryValues;
    }

    public Set getValues()
    {
        return mValues;
    }

    protected void onDialogClosed(boolean flag)
    {
        super.onDialogClosed(flag);
        if(flag && mPreferenceChanged)
        {
            Set set = mNewValues;
            if(callChangeListener(set))
                setValues(set);
        }
        mPreferenceChanged = false;
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        CharSequence acharsequence[] = typedarray.getTextArray(i);
        int j = acharsequence.length;
        typedarray = new HashSet();
        for(i = 0; i < j; i++)
            typedarray.add(acharsequence[i].toString());

        return typedarray;
    }

    protected void onPrepareDialogBuilder(android.app.AlertDialog.Builder builder)
    {
        super.onPrepareDialogBuilder(builder);
        if(mEntries == null || mEntryValues == null)
        {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        } else
        {
            boolean aflag[] = getSelectedItems();
            builder.setMultiChoiceItems(mEntries, aflag, new android.content.DialogInterface.OnMultiChoiceClickListener() {

                public void onClick(DialogInterface dialoginterface, int i, boolean flag)
                {
                    if(flag)
                    {
                        dialoginterface = MultiSelectListPreference.this;
                        MultiSelectListPreference._2D_set0(dialoginterface, MultiSelectListPreference._2D_get2(dialoginterface) | MultiSelectListPreference._2D_get1(MultiSelectListPreference.this).add(MultiSelectListPreference._2D_get0(MultiSelectListPreference.this)[i].toString()));
                    } else
                    {
                        dialoginterface = MultiSelectListPreference.this;
                        MultiSelectListPreference._2D_set0(dialoginterface, MultiSelectListPreference._2D_get2(dialoginterface) | MultiSelectListPreference._2D_get1(MultiSelectListPreference.this).remove(MultiSelectListPreference._2D_get0(MultiSelectListPreference.this)[i].toString()));
                    }
                }

                final MultiSelectListPreference this$0;

            
            {
                this$0 = MultiSelectListPreference.this;
                super();
            }
            }
);
            mNewValues.clear();
            mNewValues.addAll(mValues);
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
            obj.values = getValues();
            return ((Parcelable) (obj));
        }
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
        if(flag)
            obj = getPersistedStringSet(mValues);
        else
            obj = (Set)obj;
        setValues(((Set) (obj)));
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

    public void setValues(Set set)
    {
        mValues.clear();
        mValues.addAll(set);
        persistStringSet(set);
    }

    private CharSequence mEntries[];
    private CharSequence mEntryValues[];
    private Set mNewValues;
    private boolean mPreferenceChanged;
    private Set mValues;
}
