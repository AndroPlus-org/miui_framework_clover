// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.*;
import android.icu.util.Calendar;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.util.*;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import java.util.List;
import java.util.Locale;

// Referenced classes of package android.widget:
//            FrameLayout, DatePickerCalendarDelegate, DatePickerSpinnerDelegate, CalendarView

public class DatePicker extends FrameLayout
{
    static abstract class AbstractDatePickerDelegate
        implements DatePickerDelegate
    {

        public final void autofill(AutofillValue autofillvalue)
        {
            if(autofillvalue == null || autofillvalue.isDate() ^ true)
            {
                Log.w(DatePicker._2D_get0(), (new StringBuilder()).append(autofillvalue).append(" could not be autofilled into ").append(this).toString());
                return;
            } else
            {
                long l = autofillvalue.getDateValue();
                autofillvalue = Calendar.getInstance(mCurrentLocale);
                autofillvalue.setTimeInMillis(l);
                updateDate(autofillvalue.get(1), autofillvalue.get(2), autofillvalue.get(5));
                mAutofilledValue = l;
                return;
            }
        }

        public final AutofillValue getAutofillValue()
        {
            long l;
            if(mAutofilledValue != 0L)
                l = mAutofilledValue;
            else
                l = mCurrentDate.getTimeInMillis();
            return AutofillValue.forDate(l);
        }

        protected String getFormattedCurrentDate()
        {
            return DateUtils.formatDateTime(mContext, mCurrentDate.getTimeInMillis(), 22);
        }

        protected void onLocaleChanged(Locale locale)
        {
        }

        public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            accessibilityevent.getText().add(getFormattedCurrentDate());
        }

        protected void onValidationChanged(boolean flag)
        {
            if(mValidationCallback != null)
                mValidationCallback.onValidationChanged(flag);
        }

        protected void resetAutofilledValue()
        {
            mAutofilledValue = 0L;
        }

        public void setAutoFillChangeListener(OnDateChangedListener ondatechangedlistener)
        {
            mAutoFillChangeListener = ondatechangedlistener;
        }

        protected void setCurrentLocale(Locale locale)
        {
            if(!locale.equals(mCurrentLocale))
            {
                mCurrentLocale = locale;
                onLocaleChanged(locale);
            }
        }

        public void setOnDateChangedListener(OnDateChangedListener ondatechangedlistener)
        {
            mOnDateChangedListener = ondatechangedlistener;
        }

        public void setValidationCallback(ValidationCallback validationcallback)
        {
            mValidationCallback = validationcallback;
        }

        protected OnDateChangedListener mAutoFillChangeListener;
        private long mAutofilledValue;
        protected Context mContext;
        protected Calendar mCurrentDate;
        protected Locale mCurrentLocale;
        protected DatePicker mDelegator;
        protected OnDateChangedListener mOnDateChangedListener;
        protected ValidationCallback mValidationCallback;

        public AbstractDatePickerDelegate(DatePicker datepicker, Context context)
        {
            mDelegator = datepicker;
            mContext = context;
            setCurrentLocale(Locale.getDefault());
        }
    }

    static class AbstractDatePickerDelegate.SavedState extends android.view.View.BaseSavedState
    {

        public int getCurrentView()
        {
            return mCurrentView;
        }

        public int getListPosition()
        {
            return mListPosition;
        }

        public int getListPositionOffset()
        {
            return mListPositionOffset;
        }

        public long getMaxDate()
        {
            return mMaxDate;
        }

        public long getMinDate()
        {
            return mMinDate;
        }

        public int getSelectedDay()
        {
            return mSelectedDay;
        }

        public int getSelectedMonth()
        {
            return mSelectedMonth;
        }

        public int getSelectedYear()
        {
            return mSelectedYear;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(mSelectedYear);
            parcel.writeInt(mSelectedMonth);
            parcel.writeInt(mSelectedDay);
            parcel.writeLong(mMinDate);
            parcel.writeLong(mMaxDate);
            parcel.writeInt(mCurrentView);
            parcel.writeInt(mListPosition);
            parcel.writeInt(mListPositionOffset);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public AbstractDatePickerDelegate.SavedState createFromParcel(Parcel parcel)
            {
                return new AbstractDatePickerDelegate.SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public AbstractDatePickerDelegate.SavedState[] newArray(int i)
            {
                return new AbstractDatePickerDelegate.SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int mCurrentView;
        private final int mListPosition;
        private final int mListPositionOffset;
        private final long mMaxDate;
        private final long mMinDate;
        private final int mSelectedDay;
        private final int mSelectedMonth;
        private final int mSelectedYear;


        private AbstractDatePickerDelegate.SavedState(Parcel parcel)
        {
            super(parcel);
            mSelectedYear = parcel.readInt();
            mSelectedMonth = parcel.readInt();
            mSelectedDay = parcel.readInt();
            mMinDate = parcel.readLong();
            mMaxDate = parcel.readLong();
            mCurrentView = parcel.readInt();
            mListPosition = parcel.readInt();
            mListPositionOffset = parcel.readInt();
        }

        AbstractDatePickerDelegate.SavedState(Parcel parcel, AbstractDatePickerDelegate.SavedState savedstate)
        {
            this(parcel);
        }

        public AbstractDatePickerDelegate.SavedState(Parcelable parcelable, int i, int j, int k, long l, long l1)
        {
            this(parcelable, i, j, k, l, l1, 0, 0, 0);
        }

        public AbstractDatePickerDelegate.SavedState(Parcelable parcelable, int i, int j, int k, long l, long l1, int i1, int j1, int k1)
        {
            super(parcelable);
            mSelectedYear = i;
            mSelectedMonth = j;
            mSelectedDay = k;
            mMinDate = l;
            mMaxDate = l1;
            mCurrentView = i1;
            mListPosition = j1;
            mListPositionOffset = k1;
        }
    }

    static interface DatePickerDelegate
    {

        public abstract void autofill(AutofillValue autofillvalue);

        public abstract boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent);

        public abstract AutofillValue getAutofillValue();

        public abstract CalendarView getCalendarView();

        public abstract boolean getCalendarViewShown();

        public abstract int getDayOfMonth();

        public abstract int getFirstDayOfWeek();

        public abstract Calendar getMaxDate();

        public abstract Calendar getMinDate();

        public abstract int getMonth();

        public abstract boolean getSpinnersShown();

        public abstract int getYear();

        public abstract void init(int i, int j, int k, OnDateChangedListener ondatechangedlistener);

        public abstract boolean isEnabled();

        public abstract void onConfigurationChanged(Configuration configuration);

        public abstract void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent);

        public abstract void onRestoreInstanceState(Parcelable parcelable);

        public abstract Parcelable onSaveInstanceState(Parcelable parcelable);

        public abstract void setAutoFillChangeListener(OnDateChangedListener ondatechangedlistener);

        public abstract void setCalendarViewShown(boolean flag);

        public abstract void setEnabled(boolean flag);

        public abstract void setFirstDayOfWeek(int i);

        public abstract void setMaxDate(long l);

        public abstract void setMinDate(long l);

        public abstract void setOnDateChangedListener(OnDateChangedListener ondatechangedlistener);

        public abstract void setSpinnersShown(boolean flag);

        public abstract void setValidationCallback(ValidationCallback validationcallback);

        public abstract void updateDate(int i, int j, int k);
    }

    public static interface OnDateChangedListener
    {

        public abstract void onDateChanged(DatePicker datepicker, int i, int j, int k);
    }

    public static interface ValidationCallback
    {

        public abstract void onValidationChanged(boolean flag);
    }


    static String _2D_get0()
    {
        return LOG_TAG;
    }

    public DatePicker(Context context)
    {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101035c);
    }

    public DatePicker(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public DatePicker(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        if(getImportantForAutofill() == 0)
            setImportantForAutofill(1);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.DatePicker, i, j);
        boolean flag = typedarray.getBoolean(17, false);
        int k = typedarray.getInt(16, 1);
        int l = typedarray.getInt(3, 0);
        typedarray.recycle();
        if(k == 2 && flag)
            mMode = context.getResources().getInteger(0x10e00e6);
        else
            mMode = k;
        mMode;
        JVM INSTR tableswitch 2 2: default 116
    //                   2 168;
           goto _L1 _L2
_L1:
        mDelegate = createSpinnerUIDelegate(context, attributeset, i, j);
_L4:
        if(l != 0)
            setFirstDayOfWeek(l);
        mDelegate.setAutoFillChangeListener(new _.Lambda.BV_ZMvzFTXSucX7TdTNW_nTaMgA(this, context));
        return;
_L2:
        mDelegate = createCalendarUIDelegate(context, attributeset, i, j);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private DatePickerDelegate createCalendarUIDelegate(Context context, AttributeSet attributeset, int i, int j)
    {
        return new DatePickerCalendarDelegate(this, context, attributeset, i, j);
    }

    private DatePickerDelegate createSpinnerUIDelegate(Context context, AttributeSet attributeset, int i, int j)
    {
        return new DatePickerSpinnerDelegate(this, context, attributeset, i, j);
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

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        dispatchThawSelfOnly(sparsearray);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/DatePicker.getName();
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

    public CalendarView getCalendarView()
    {
        return mDelegate.getCalendarView();
    }

    public boolean getCalendarViewShown()
    {
        return mDelegate.getCalendarViewShown();
    }

    public int getDayOfMonth()
    {
        return mDelegate.getDayOfMonth();
    }

    public int getFirstDayOfWeek()
    {
        return mDelegate.getFirstDayOfWeek();
    }

    public long getMaxDate()
    {
        return mDelegate.getMaxDate().getTimeInMillis();
    }

    public long getMinDate()
    {
        return mDelegate.getMinDate().getTimeInMillis();
    }

    public int getMode()
    {
        return mMode;
    }

    public int getMonth()
    {
        return mDelegate.getMonth();
    }

    public boolean getSpinnersShown()
    {
        return mDelegate.getSpinnersShown();
    }

    public int getYear()
    {
        return mDelegate.getYear();
    }

    public void init(int i, int j, int k, OnDateChangedListener ondatechangedlistener)
    {
        mDelegate.init(i, j, k, ondatechangedlistener);
    }

    public boolean isEnabled()
    {
        return mDelegate.isEnabled();
    }

    void lambda$_2D_android_widget_DatePicker_6867(Context context, DatePicker datepicker, int i, int j, int k)
    {
        context = (AutofillManager)context.getSystemService(android/view/autofill/AutofillManager);
        if(context != null)
            context.notifyValueChanged(this);
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mDelegate.onConfigurationChanged(configuration);
    }

    public void onPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onPopulateAccessibilityEventInternal(accessibilityevent);
        mDelegate.onPopulateAccessibilityEvent(accessibilityevent);
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

    public void setCalendarViewShown(boolean flag)
    {
        mDelegate.setCalendarViewShown(flag);
    }

    public void setEnabled(boolean flag)
    {
        if(mDelegate.isEnabled() == flag)
        {
            return;
        } else
        {
            super.setEnabled(flag);
            mDelegate.setEnabled(flag);
            return;
        }
    }

    public void setFirstDayOfWeek(int i)
    {
        if(i < 1 || i > 7)
        {
            throw new IllegalArgumentException("firstDayOfWeek must be between 1 and 7");
        } else
        {
            mDelegate.setFirstDayOfWeek(i);
            return;
        }
    }

    public void setMaxDate(long l)
    {
        mDelegate.setMaxDate(l);
    }

    public void setMinDate(long l)
    {
        mDelegate.setMinDate(l);
    }

    public void setOnDateChangedListener(OnDateChangedListener ondatechangedlistener)
    {
        mDelegate.setOnDateChangedListener(ondatechangedlistener);
    }

    public void setSpinnersShown(boolean flag)
    {
        mDelegate.setSpinnersShown(flag);
    }

    public void setValidationCallback(ValidationCallback validationcallback)
    {
        mDelegate.setValidationCallback(validationcallback);
    }

    public void updateDate(int i, int j, int k)
    {
        mDelegate.updateDate(i, j, k);
    }

    private static final String LOG_TAG = android/widget/DatePicker.getSimpleName();
    public static final int MODE_CALENDAR = 2;
    public static final int MODE_SPINNER = 1;
    private final DatePickerDelegate mDelegate;
    private final int mMode;

}
