// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.SeekBar;

// Referenced classes of package android.preference:
//            SeekBarDialogPreference, PreferenceManager, SeekBarVolumizer

public class VolumePreference extends SeekBarDialogPreference
    implements PreferenceManager.OnActivityStopListener, android.view.View.OnKeyListener, SeekBarVolumizer.Callback
{
    private static class SavedState extends Preference.BaseSavedState
    {

        VolumeStore getVolumeStore()
        {
            return mVolumeStore;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(mVolumeStore.volume);
            parcel.writeInt(mVolumeStore.originalVolume);
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
        VolumeStore mVolumeStore;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            mVolumeStore = new VolumeStore();
            mVolumeStore.volume = parcel.readInt();
            mVolumeStore.originalVolume = parcel.readInt();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
            mVolumeStore = new VolumeStore();
        }
    }

    public static class VolumeStore
    {

        public int originalVolume;
        public int volume;

        public VolumeStore()
        {
            volume = -1;
            originalVolume = -1;
        }
    }


    public VolumePreference(Context context)
    {
        this(context, null);
    }

    public VolumePreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x11100b1);
    }

    public VolumePreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public VolumePreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.VolumePreference, i, j);
        mStreamType = context.getInt(0, 0);
        context.recycle();
    }

    private void cleanup()
    {
        getPreferenceManager().unregisterOnActivityStopListener(this);
        if(mSeekBarVolumizer != null)
        {
            Object obj = getDialog();
            if(obj != null && ((Dialog) (obj)).isShowing())
            {
                obj = ((Dialog) (obj)).getWindow().getDecorView().findViewById(0x10203d3);
                if(obj != null)
                    ((View) (obj)).setOnKeyListener(null);
                mSeekBarVolumizer.revertVolume();
            }
            mSeekBarVolumizer.stop();
            mSeekBarVolumizer = null;
        }
    }

    public void onActivityStop()
    {
        if(mSeekBarVolumizer != null)
            mSeekBarVolumizer.stopSample();
    }

    protected void onBindDialogView(View view)
    {
        super.onBindDialogView(view);
        SeekBar seekbar = (SeekBar)view.findViewById(0x10203d3);
        mSeekBarVolumizer = new SeekBarVolumizer(getContext(), mStreamType, null, this);
        mSeekBarVolumizer.start();
        mSeekBarVolumizer.setSeekBar(seekbar);
        getPreferenceManager().registerOnActivityStopListener(this);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    protected void onDialogClosed(boolean flag)
    {
        super.onDialogClosed(flag);
        if(!flag && mSeekBarVolumizer != null)
            mSeekBarVolumizer.revertVolume();
        cleanup();
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if(mSeekBarVolumizer == null)
            return true;
        boolean flag;
        if(keyevent.getAction() == 0)
            flag = true;
        else
            flag = false;
        switch(i)
        {
        default:
            return false;

        case 25: // '\031'
            if(flag)
                mSeekBarVolumizer.changeVolumeBy(-1);
            return true;

        case 24: // '\030'
            if(flag)
                mSeekBarVolumizer.changeVolumeBy(1);
            return true;

        case 164: 
            break;
        }
        if(flag)
            mSeekBarVolumizer.muteVolume();
        return true;
    }

    public void onMuted(boolean flag, boolean flag1)
    {
    }

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable == null || parcelable.getClass().equals(android/preference/VolumePreference$SavedState) ^ true)
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        if(mSeekBarVolumizer != null)
            mSeekBarVolumizer.onRestoreInstanceState(parcelable.getVolumeStore());
    }

    public void onSampleStarting(SeekBarVolumizer seekbarvolumizer)
    {
        if(mSeekBarVolumizer != null && seekbarvolumizer != mSeekBarVolumizer)
            mSeekBarVolumizer.stopSample();
    }

    protected Parcelable onSaveInstanceState()
    {
        Object obj = super.onSaveInstanceState();
        if(isPersistent())
            return ((Parcelable) (obj));
        obj = new SavedState(((Parcelable) (obj)));
        if(mSeekBarVolumizer != null)
            mSeekBarVolumizer.onSaveInstanceState(((SavedState) (obj)).getVolumeStore());
        return ((Parcelable) (obj));
    }

    public void setStreamType(int i)
    {
        mStreamType = i;
    }

    private SeekBarVolumizer mSeekBarVolumizer;
    private int mStreamType;
}
