// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package android.preference:
//            Preference

public abstract class TwoStatePreference extends Preference
{
    static class SavedState extends Preference.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if(checked)
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
        boolean checked;


        public SavedState(Parcel parcel)
        {
            boolean flag = true;
            super(parcel);
            if(parcel.readInt() != 1)
                flag = false;
            checked = flag;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public TwoStatePreference(Context context)
    {
        this(context, null);
    }

    public TwoStatePreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public boolean getDisableDependentsState()
    {
        return mDisableDependentsState;
    }

    public CharSequence getSummaryOff()
    {
        return mSummaryOff;
    }

    public CharSequence getSummaryOn()
    {
        return mSummaryOn;
    }

    public boolean isChecked()
    {
        return mChecked;
    }

    protected void onClick()
    {
        super.onClick();
        boolean flag = isChecked() ^ true;
        if(callChangeListener(Boolean.valueOf(flag)))
            setChecked(flag);
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return Boolean.valueOf(typedarray.getBoolean(i, false));
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable == null || parcelable.getClass().equals(android/preference/TwoStatePreference$SavedState) ^ true)
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            setChecked(((SavedState) (parcelable)).checked);
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
            obj.checked = isChecked();
            return ((Parcelable) (obj));
        }
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
        if(flag)
            flag = getPersistedBoolean(mChecked);
        else
            flag = ((Boolean)obj).booleanValue();
        setChecked(flag);
    }

    public void setChecked(boolean flag)
    {
        boolean flag1;
        if(mChecked != flag)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 || mCheckedSet ^ true)
        {
            mChecked = flag;
            mCheckedSet = true;
            persistBoolean(flag);
            if(flag1)
            {
                notifyDependencyChange(shouldDisableDependents());
                notifyChanged();
            }
        }
    }

    public void setDisableDependentsState(boolean flag)
    {
        mDisableDependentsState = flag;
    }

    public void setSummaryOff(int i)
    {
        setSummaryOff(((CharSequence) (getContext().getString(i))));
    }

    public void setSummaryOff(CharSequence charsequence)
    {
        mSummaryOff = charsequence;
        if(!isChecked())
            notifyChanged();
    }

    public void setSummaryOn(int i)
    {
        setSummaryOn(((CharSequence) (getContext().getString(i))));
    }

    public void setSummaryOn(CharSequence charsequence)
    {
        mSummaryOn = charsequence;
        if(isChecked())
            notifyChanged();
    }

    public boolean shouldDisableDependents()
    {
        boolean flag;
        if(mDisableDependentsState)
            flag = mChecked;
        else
            flag = mChecked ^ true;
        if(!flag)
            flag = super.shouldDisableDependents();
        else
            flag = true;
        return flag;
    }

    void syncSummaryView(View view)
    {
        view = (TextView)view.findViewById(0x1020010);
        if(view == null) goto _L2; else goto _L1
_L1:
        boolean flag = true;
        if(!mChecked || !(TextUtils.isEmpty(mSummaryOn) ^ true)) goto _L4; else goto _L3
_L3:
        byte byte0;
        view.setText(mSummaryOn);
        byte0 = 0;
_L6:
        flag = byte0;
        if(byte0 != 0)
        {
            CharSequence charsequence = getSummary();
            flag = byte0;
            if(!TextUtils.isEmpty(charsequence))
            {
                view.setText(charsequence);
                flag = false;
            }
        }
        byte0 = 8;
        if(!flag)
            byte0 = 0;
        if(byte0 != view.getVisibility())
            view.setVisibility(byte0);
_L2:
        return;
_L4:
        byte0 = flag;
        if(!mChecked)
        {
            byte0 = flag;
            if(TextUtils.isEmpty(mSummaryOff) ^ true)
            {
                view.setText(mSummaryOff);
                byte0 = 0;
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    boolean mChecked;
    private boolean mCheckedSet;
    private boolean mDisableDependentsState;
    private CharSequence mSummaryOff;
    private CharSequence mSummaryOn;
}
