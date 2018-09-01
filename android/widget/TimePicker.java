// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.*;
import android.icu.util.Calendar;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import android.view.View;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import java.util.Locale;
import libcore.icu.LocaleData;

// Referenced classes of package android.widget:
//            FrameLayout, TimePickerSpinnerDelegate, TimePickerClockDelegate

public class TimePicker extends FrameLayout
{
    static abstract class AbstractTimePickerDelegate
        implements TimePickerDelegate
    {

        public final void autofill(AutofillValue autofillvalue)
        {
            if(autofillvalue == null || autofillvalue.isDate() ^ true)
            {
                Log.w(TimePicker._2D_get0(), (new StringBuilder()).append(autofillvalue).append(" could not be autofilled into ").append(this).toString());
                return;
            } else
            {
                long l = autofillvalue.getDateValue();
                autofillvalue = Calendar.getInstance(mLocale);
                autofillvalue.setTimeInMillis(l);
                setDate(autofillvalue.get(11), autofillvalue.get(12));
                mAutofilledValue = l;
                return;
            }
        }

        public final AutofillValue getAutofillValue()
        {
            if(mAutofilledValue != 0L)
            {
                return AutofillValue.forDate(mAutofilledValue);
            } else
            {
                Calendar calendar = Calendar.getInstance(mLocale);
                calendar.set(11, getHour());
                calendar.set(12, getMinute());
                return AutofillValue.forDate(calendar.getTimeInMillis());
            }
        }

        protected void resetAutofilledValue()
        {
            mAutofilledValue = 0L;
        }

        public void setAutoFillChangeListener(OnTimeChangedListener ontimechangedlistener)
        {
            mAutoFillChangeListener = ontimechangedlistener;
        }

        public void setOnTimeChangedListener(OnTimeChangedListener ontimechangedlistener)
        {
            mOnTimeChangedListener = ontimechangedlistener;
        }

        protected OnTimeChangedListener mAutoFillChangeListener;
        private long mAutofilledValue;
        protected final Context mContext;
        protected final TimePicker mDelegator;
        protected final Locale mLocale;
        protected OnTimeChangedListener mOnTimeChangedListener;

        public AbstractTimePickerDelegate(TimePicker timepicker, Context context)
        {
            mDelegator = timepicker;
            mContext = context;
            mLocale = context.getResources().getConfiguration().locale;
        }
    }

    protected static class AbstractTimePickerDelegate.SavedState extends android.view.View.BaseSavedState
    {

        public int getCurrentItemShowing()
        {
            return mCurrentItemShowing;
        }

        public int getHour()
        {
            return mHour;
        }

        public int getMinute()
        {
            return mMinute;
        }

        public boolean is24HourMode()
        {
            return mIs24HourMode;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(mHour);
            parcel.writeInt(mMinute);
            if(mIs24HourMode)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(mCurrentItemShowing);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public AbstractTimePickerDelegate.SavedState createFromParcel(Parcel parcel)
            {
                return new AbstractTimePickerDelegate.SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public AbstractTimePickerDelegate.SavedState[] newArray(int i)
            {
                return new AbstractTimePickerDelegate.SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int mCurrentItemShowing;
        private final int mHour;
        private final boolean mIs24HourMode;
        private final int mMinute;


        private AbstractTimePickerDelegate.SavedState(Parcel parcel)
        {
            boolean flag = true;
            super(parcel);
            mHour = parcel.readInt();
            mMinute = parcel.readInt();
            if(parcel.readInt() != 1)
                flag = false;
            mIs24HourMode = flag;
            mCurrentItemShowing = parcel.readInt();
        }

        AbstractTimePickerDelegate.SavedState(Parcel parcel, AbstractTimePickerDelegate.SavedState savedstate)
        {
            this(parcel);
        }

        public AbstractTimePickerDelegate.SavedState(Parcelable parcelable, int i, int j, boolean flag)
        {
            this(parcelable, i, j, flag, 0);
        }

        public AbstractTimePickerDelegate.SavedState(Parcelable parcelable, int i, int j, boolean flag, int k)
        {
            super(parcelable);
            mHour = i;
            mMinute = j;
            mIs24HourMode = flag;
            mCurrentItemShowing = k;
        }
    }

    public static interface OnTimeChangedListener
    {

        public abstract void onTimeChanged(TimePicker timepicker, int i, int j);
    }

    static interface TimePickerDelegate
    {

        public abstract void autofill(AutofillValue autofillvalue);

        public abstract boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent);

        public abstract View getAmView();

        public abstract AutofillValue getAutofillValue();

        public abstract int getBaseline();

        public abstract int getHour();

        public abstract View getHourView();

        public abstract int getMinute();

        public abstract View getMinuteView();

        public abstract View getPmView();

        public abstract boolean is24Hour();

        public abstract boolean isEnabled();

        public abstract void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent);

        public abstract void onRestoreInstanceState(Parcelable parcelable);

        public abstract Parcelable onSaveInstanceState(Parcelable parcelable);

        public abstract void setAutoFillChangeListener(OnTimeChangedListener ontimechangedlistener);

        public abstract void setDate(int i, int j);

        public abstract void setEnabled(boolean flag);

        public abstract void setHour(int i);

        public abstract void setIs24Hour(boolean flag);

        public abstract void setMinute(int i);

        public abstract void setOnTimeChangedListener(OnTimeChangedListener ontimechangedlistener);

        public abstract boolean validateInput();
    }


    static String _2D_get0()
    {
        return LOG_TAG;
    }

    public TimePicker(Context context)
    {
        this(context, null);
    }

    public TimePicker(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101049d);
    }

    public TimePicker(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TimePicker(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        if(getImportantForAutofill() == 0)
            setImportantForAutofill(1);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TimePicker, i, j);
        boolean flag = typedarray.getBoolean(10, false);
        int k = typedarray.getInt(8, 1);
        typedarray.recycle();
        if(k == 2 && flag)
            mMode = context.getResources().getInteger(0x10e010d);
        else
            mMode = k;
        mMode;
        JVM INSTR tableswitch 2 2: default 104
    //                   2 149;
           goto _L1 _L2
_L1:
        mDelegate = new TimePickerSpinnerDelegate(this, context, attributeset, i, j);
_L4:
        mDelegate.setAutoFillChangeListener(new _.Lambda._cls6tyI8UILr_8rz_HNI0sixHbWoHI(this, context));
        return;
_L2:
        mDelegate = new TimePickerClockDelegate(this, context, attributeset, i, j);
        if(true) goto _L4; else goto _L3
_L3:
    }

    static String[] getAmPmStrings(Context context)
    {
        Object obj = LocaleData.get(context.getResources().getConfiguration().locale);
        if(((LocaleData) (obj)).amPm[0].length() > 4)
            context = ((LocaleData) (obj)).narrowAm;
        else
            context = ((LocaleData) (obj)).amPm[0];
        if(((LocaleData) (obj)).amPm[1].length() > 4)
            obj = ((LocaleData) (obj)).narrowPm;
        else
            obj = ((LocaleData) (obj)).amPm[1];
        return (new String[] {
            context, obj
        });
    }

    public void autofill(AutofillValue autofillvalue)
    {
        if(!isEnabled())
        {
            return;
        } else
        {
            mDelegate.autofill(autofillvalue);
            return;
        }
    }

    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        return mDelegate.dispatchPopulateAccessibilityEvent(accessibilityevent);
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        viewstructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewstructure, i);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/TimePicker.getName();
    }

    public View getAmView()
    {
        return mDelegate.getAmView();
    }

    public int getAutofillType()
    {
        byte byte0;
        if(isEnabled())
            byte0 = 4;
        else
            byte0 = 0;
        return byte0;
    }

    public AutofillValue getAutofillValue()
    {
        AutofillValue autofillvalue;
        if(isEnabled())
            autofillvalue = mDelegate.getAutofillValue();
        else
            autofillvalue = null;
        return autofillvalue;
    }

    public int getBaseline()
    {
        return mDelegate.getBaseline();
    }

    public Integer getCurrentHour()
    {
        return Integer.valueOf(getHour());
    }

    public Integer getCurrentMinute()
    {
        return Integer.valueOf(getMinute());
    }

    public int getHour()
    {
        return mDelegate.getHour();
    }

    public View getHourView()
    {
        return mDelegate.getHourView();
    }

    public int getMinute()
    {
        return mDelegate.getMinute();
    }

    public View getMinuteView()
    {
        return mDelegate.getMinuteView();
    }

    public int getMode()
    {
        return mMode;
    }

    public View getPmView()
    {
        return mDelegate.getPmView();
    }

    public boolean is24HourView()
    {
        return mDelegate.is24Hour();
    }

    public boolean isEnabled()
    {
        return mDelegate.isEnabled();
    }

    void lambda$_2D_android_widget_TimePicker_4943(Context context, TimePicker timepicker, int i, int j)
    {
        context = (AutofillManager)context.getSystemService(android/view/autofill/AutofillManager);
        if(context != null)
            context.notifyValueChanged(this);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (android.view.View.BaseSavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        mDelegate.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState()
    {
        Parcelable parcelable = super.onSaveInstanceState();
        return mDelegate.onSaveInstanceState(parcelable);
    }

    public void setCurrentHour(Integer integer)
    {
        setHour(integer.intValue());
    }

    public void setCurrentMinute(Integer integer)
    {
        setMinute(integer.intValue());
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        mDelegate.setEnabled(flag);
    }

    public void setHour(int i)
    {
        mDelegate.setHour(MathUtils.constrain(i, 0, 23));
    }

    public void setIs24HourView(Boolean boolean1)
    {
        if(boolean1 == null)
        {
            return;
        } else
        {
            mDelegate.setIs24Hour(boolean1.booleanValue());
            return;
        }
    }

    public void setMinute(int i)
    {
        mDelegate.setMinute(MathUtils.constrain(i, 0, 59));
    }

    public void setOnTimeChangedListener(OnTimeChangedListener ontimechangedlistener)
    {
        mDelegate.setOnTimeChangedListener(ontimechangedlistener);
    }

    public boolean validateInput()
    {
        return mDelegate.validateInput();
    }

    private static final String LOG_TAG = android/widget/TimePicker.getSimpleName();
    public static final int MODE_CLOCK = 2;
    public static final int MODE_SPINNER = 1;
    private final TimePickerDelegate mDelegate;
    private final int mMode;

}
