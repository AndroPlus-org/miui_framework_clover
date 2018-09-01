// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;

// Referenced classes of package android.preference:
//            Preference

public class SeekBarPreference extends Preference
    implements android.widget.SeekBar.OnSeekBarChangeListener
{
    private static class SavedState extends Preference.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(progress);
            parcel.writeInt(max);
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
        int max;
        int progress;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            progress = parcel.readInt();
            max = parcel.readInt();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public SeekBarPreference(Context context)
    {
        this(context, null);
    }

    public SeekBarPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x11100b2);
    }

    public SeekBarPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ProgressBar, i, j);
        setMax(typedarray.getInt(2, mMax));
        typedarray.recycle();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.SeekBarPreference, i, j);
        i = context.getResourceId(0, 0x10900ce);
        context.recycle();
        setLayoutResource(i);
    }

    private void setProgress(int i, boolean flag)
    {
        int j = i;
        if(i > mMax)
            j = mMax;
        i = j;
        if(j < 0)
            i = 0;
        if(i != mProgress)
        {
            mProgress = i;
            persistInt(i);
            if(flag)
                notifyChanged();
        }
    }

    public int getProgress()
    {
        return mProgress;
    }

    protected void onBindView(View view)
    {
        super.onBindView(view);
        view = (SeekBar)view.findViewById(0x10203d3);
        view.setOnSeekBarChangeListener(this);
        view.setMax(mMax);
        view.setProgress(mProgress);
        view.setEnabled(isEnabled());
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return Integer.valueOf(typedarray.getInt(i, 0));
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if(keyevent.getAction() != 0)
            return false;
        view = (SeekBar)view.findViewById(0x10203d3);
        if(view == null)
            return false;
        else
            return view.onKeyDown(i, keyevent);
    }

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
        if(flag && mTrackingTouch ^ true)
            syncProgress(seekbar);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!parcelable.getClass().equals(android/preference/SeekBarPreference$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            mProgress = ((SavedState) (parcelable)).progress;
            mMax = ((SavedState) (parcelable)).max;
            notifyChanged();
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
            obj.progress = mProgress;
            obj.max = mMax;
            return ((Parcelable) (obj));
        }
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
        int i;
        if(flag)
            i = getPersistedInt(mProgress);
        else
            i = ((Integer)obj).intValue();
        setProgress(i);
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
        mTrackingTouch = true;
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        mTrackingTouch = false;
        if(seekbar.getProgress() != mProgress)
            syncProgress(seekbar);
    }

    public void setMax(int i)
    {
        if(i != mMax)
        {
            mMax = i;
            notifyChanged();
        }
    }

    public void setProgress(int i)
    {
        setProgress(i, true);
    }

    void syncProgress(SeekBar seekbar)
    {
        int i = seekbar.getProgress();
        if(i != mProgress)
            if(callChangeListener(Integer.valueOf(i)))
                setProgress(i, false);
            else
                seekbar.setProgress(mProgress);
    }

    private int mMax;
    private int mProgress;
    private boolean mTrackingTouch;
}
