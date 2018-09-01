// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;

public class YesNoPreference extends DialogPreference
{
    private static class SavedState extends android.preference.Preference.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if(wasPositiveResult)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
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
        boolean wasPositiveResult;


        public SavedState(Parcel parcel)
        {
            boolean flag = true;
            super(parcel);
            if(parcel.readInt() != 1)
                flag = false;
            wasPositiveResult = flag;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public YesNoPreference(Context context)
    {
        this(context, null);
    }

    public YesNoPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010090);
    }

    public YesNoPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public YesNoPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public boolean getValue()
    {
        return mWasPositiveResult;
    }

    protected void onDialogClosed(boolean flag)
    {
        super.onDialogClosed(flag);
        if(callChangeListener(Boolean.valueOf(flag)))
            setValue(flag);
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return Boolean.valueOf(typedarray.getBoolean(i, false));
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!parcelable.getClass().equals(com/android/internal/preference/YesNoPreference$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            setValue(((SavedState) (parcelable)).wasPositiveResult);
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
            obj.wasPositiveResult = getValue();
            return ((Parcelable) (obj));
        }
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
        if(flag)
            flag = getPersistedBoolean(mWasPositiveResult);
        else
            flag = ((Boolean)obj).booleanValue();
        setValue(flag);
    }

    public void setValue(boolean flag)
    {
        mWasPositiveResult = flag;
        persistBoolean(flag);
        notifyDependencyChange(flag ^ true);
    }

    public boolean shouldDisableDependents()
    {
        boolean flag;
        if(mWasPositiveResult)
            flag = super.shouldDisableDependents();
        else
            flag = true;
        return flag;
    }

    private boolean mWasPositiveResult;
}
