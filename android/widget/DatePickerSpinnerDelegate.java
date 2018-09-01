// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.icu.util.Calendar;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import libcore.icu.ICU;

// Referenced classes of package android.widget:
//            DatePicker, LinearLayout, CalendarView, NumberPicker, 
//            EditText, TextView

class DatePickerSpinnerDelegate extends DatePicker.AbstractDatePickerDelegate
{

    static NumberPicker _2D_get0(DatePickerSpinnerDelegate datepickerspinnerdelegate)
    {
        return datepickerspinnerdelegate.mDaySpinner;
    }

    static NumberPicker _2D_get1(DatePickerSpinnerDelegate datepickerspinnerdelegate)
    {
        return datepickerspinnerdelegate.mMonthSpinner;
    }

    static Calendar _2D_get2(DatePickerSpinnerDelegate datepickerspinnerdelegate)
    {
        return datepickerspinnerdelegate.mTempDate;
    }

    static NumberPicker _2D_get3(DatePickerSpinnerDelegate datepickerspinnerdelegate)
    {
        return datepickerspinnerdelegate.mYearSpinner;
    }

    static void _2D_wrap0(DatePickerSpinnerDelegate datepickerspinnerdelegate)
    {
        datepickerspinnerdelegate.notifyDateChanged();
    }

    static void _2D_wrap1(DatePickerSpinnerDelegate datepickerspinnerdelegate, int i, int j, int k)
    {
        datepickerspinnerdelegate.setDate(i, j, k);
    }

    static void _2D_wrap2(DatePickerSpinnerDelegate datepickerspinnerdelegate)
    {
        datepickerspinnerdelegate.updateCalendarView();
    }

    static void _2D_wrap3(DatePickerSpinnerDelegate datepickerspinnerdelegate)
    {
        datepickerspinnerdelegate.updateInputState();
    }

    static void _2D_wrap4(DatePickerSpinnerDelegate datepickerspinnerdelegate)
    {
        datepickerspinnerdelegate.updateSpinners();
    }

    DatePickerSpinnerDelegate(DatePicker datepicker, Context context, AttributeSet attributeset, int i, int j)
    {
        super(datepicker, context);
        mIsEnabled = true;
        mDelegator = datepicker;
        mContext = context;
        setCurrentLocale(Locale.getDefault());
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.DatePicker, i, j);
        boolean flag = typedarray.getBoolean(6, true);
        boolean flag1 = typedarray.getBoolean(7, true);
        int k = typedarray.getInt(1, 1900);
        i = typedarray.getInt(2, 2100);
        datepicker = typedarray.getString(4);
        attributeset = typedarray.getString(5);
        j = typedarray.getResourceId(20, 0x109004c);
        typedarray.recycle();
        ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(j, mDelegator, true).setSaveFromParentEnabled(false);
        context = new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker numberpicker, int l, int i1)
            {
                DatePickerSpinnerDelegate._2D_wrap3(DatePickerSpinnerDelegate.this);
                DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).setTimeInMillis(mCurrentDate.getTimeInMillis());
                if(numberpicker == DatePickerSpinnerDelegate._2D_get0(DatePickerSpinnerDelegate.this))
                {
                    int j1 = DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).getActualMaximum(5);
                    if(l == j1 && i1 == 1)
                        DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).add(5, 1);
                    else
                    if(l == 1 && i1 == j1)
                        DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).add(5, -1);
                    else
                        DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).add(5, i1 - l);
                } else
                if(numberpicker == DatePickerSpinnerDelegate._2D_get1(DatePickerSpinnerDelegate.this))
                {
                    if(l == 11 && i1 == 0)
                        DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).add(2, 1);
                    else
                    if(l == 0 && i1 == 11)
                        DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).add(2, -1);
                    else
                        DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).add(2, i1 - l);
                } else
                if(numberpicker == DatePickerSpinnerDelegate._2D_get3(DatePickerSpinnerDelegate.this))
                    DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).set(1, i1);
                else
                    throw new IllegalArgumentException();
                DatePickerSpinnerDelegate._2D_wrap1(DatePickerSpinnerDelegate.this, DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).get(1), DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).get(2), DatePickerSpinnerDelegate._2D_get2(DatePickerSpinnerDelegate.this).get(5));
                DatePickerSpinnerDelegate._2D_wrap4(DatePickerSpinnerDelegate.this);
                DatePickerSpinnerDelegate._2D_wrap2(DatePickerSpinnerDelegate.this);
                DatePickerSpinnerDelegate._2D_wrap0(DatePickerSpinnerDelegate.this);
            }

            final DatePickerSpinnerDelegate this$0;

            
            {
                this$0 = DatePickerSpinnerDelegate.this;
                super();
            }
        }
;
        mSpinners = (LinearLayout)mDelegator.findViewById(0x1020377);
        mCalendarView = (CalendarView)mDelegator.findViewById(0x10201e7);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            public void onSelectedDayChange(CalendarView calendarview, int l, int i1, int j1)
            {
                DatePickerSpinnerDelegate._2D_wrap1(DatePickerSpinnerDelegate.this, l, i1, j1);
                DatePickerSpinnerDelegate._2D_wrap4(DatePickerSpinnerDelegate.this);
                DatePickerSpinnerDelegate._2D_wrap0(DatePickerSpinnerDelegate.this);
            }

            final DatePickerSpinnerDelegate this$0;

            
            {
                this$0 = DatePickerSpinnerDelegate.this;
                super();
            }
        }
);
        mDaySpinner = (NumberPicker)mDelegator.findViewById(0x1020219);
        mDaySpinner.setFormatter(NumberPicker.getTwoDigitFormatter());
        mDaySpinner.setOnLongPressUpdateInterval(100L);
        mDaySpinner.setOnValueChangedListener(context);
        mDaySpinnerInput = (EditText)mDaySpinner.findViewById(0x1020344);
        mMonthSpinner = (NumberPicker)mDelegator.findViewById(0x102030f);
        mMonthSpinner.setMinValue(0);
        mMonthSpinner.setMaxValue(mNumberOfMonths - 1);
        mMonthSpinner.setDisplayedValues(mShortMonths);
        mMonthSpinner.setOnLongPressUpdateInterval(200L);
        mMonthSpinner.setOnValueChangedListener(context);
        mMonthSpinnerInput = (EditText)mMonthSpinner.findViewById(0x1020344);
        mYearSpinner = (NumberPicker)mDelegator.findViewById(0x10204bb);
        mYearSpinner.setOnLongPressUpdateInterval(100L);
        mYearSpinner.setOnValueChangedListener(context);
        mYearSpinnerInput = (EditText)mYearSpinner.findViewById(0x1020344);
        if(!flag && flag1 ^ true)
        {
            setSpinnersShown(true);
        } else
        {
            setSpinnersShown(flag);
            setCalendarViewShown(flag1);
        }
        mTempDate.clear();
        if(!TextUtils.isEmpty(datepicker))
        {
            if(!parseDate(datepicker, mTempDate))
                mTempDate.set(k, 0, 1);
        } else
        {
            mTempDate.set(k, 0, 1);
        }
        setMinDate(mTempDate.getTimeInMillis());
        mTempDate.clear();
        if(!TextUtils.isEmpty(attributeset))
        {
            if(!parseDate(attributeset, mTempDate))
                mTempDate.set(i, 11, 31);
        } else
        {
            mTempDate.set(i, 11, 31);
        }
        setMaxDate(mTempDate.getTimeInMillis());
        mCurrentDate.setTimeInMillis(System.currentTimeMillis());
        init(mCurrentDate.get(1), mCurrentDate.get(2), mCurrentDate.get(5), null);
        reorderSpinners();
        setContentDescriptions();
        if(mDelegator.getImportantForAccessibility() == 0)
            mDelegator.setImportantForAccessibility(1);
    }

    private Calendar getCalendarForLocale(Calendar calendar, Locale locale)
    {
        if(calendar == null)
        {
            return Calendar.getInstance(locale);
        } else
        {
            long l = calendar.getTimeInMillis();
            calendar = Calendar.getInstance(locale);
            calendar.setTimeInMillis(l);
            return calendar;
        }
    }

    private boolean isNewDate(int i, int j, int k)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mCurrentDate.get(1) != i) goto _L2; else goto _L1
_L1:
        if(mCurrentDate.get(2) == j) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mCurrentDate.get(5) == k)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void notifyDateChanged()
    {
        mDelegator.sendAccessibilityEvent(4);
        if(mOnDateChangedListener != null)
            mOnDateChangedListener.onDateChanged(mDelegator, getYear(), getMonth(), getDayOfMonth());
        if(mAutoFillChangeListener != null)
            mAutoFillChangeListener.onDateChanged(mDelegator, getYear(), getMonth(), getDayOfMonth());
    }

    private boolean parseDate(String s, Calendar calendar)
    {
        try
        {
            calendar.setTime(mDateFormat.parse(s));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            return false;
        }
        return true;
    }

    private void reorderSpinners()
    {
        char ac[];
        int i;
        int j;
        mSpinners.removeAllViews();
        ac = ICU.getDateFormatOrder(android.text.format.DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd"));
        i = ac.length;
        j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_155;
        switch(ac[j])
        {
        default:
            throw new IllegalArgumentException(Arrays.toString(ac));

        case 77: // 'M'
            break; /* Loop/switch isn't completed */

        case 121: // 'y'
            break MISSING_BLOCK_LABEL_131;

        case 100: // 'd'
            mSpinners.addView(mDaySpinner);
            setImeOptions(mDaySpinner, i, j);
            break;
        }
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        mSpinners.addView(mMonthSpinner);
        setImeOptions(mMonthSpinner, i, j);
          goto _L3
        mSpinners.addView(mYearSpinner);
        setImeOptions(mYearSpinner, i, j);
          goto _L3
    }

    private void setContentDescriptions()
    {
        trySetContentDescription(mDaySpinner, 0x10202a7, 0x1040189);
        trySetContentDescription(mDaySpinner, 0x102021e, 0x1040185);
        trySetContentDescription(mMonthSpinner, 0x10202a7, 0x104018a);
        trySetContentDescription(mMonthSpinner, 0x102021e, 0x1040186);
        trySetContentDescription(mYearSpinner, 0x10202a7, 0x104018b);
        trySetContentDescription(mYearSpinner, 0x102021e, 0x1040187);
    }

    private void setDate(int i, int j, int k)
    {
        mCurrentDate.set(i, j, k);
        resetAutofilledValue();
        if(!mCurrentDate.before(mMinDate)) goto _L2; else goto _L1
_L1:
        mCurrentDate.setTimeInMillis(mMinDate.getTimeInMillis());
_L4:
        return;
_L2:
        if(mCurrentDate.after(mMaxDate))
            mCurrentDate.setTimeInMillis(mMaxDate.getTimeInMillis());
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void setImeOptions(NumberPicker numberpicker, int i, int j)
    {
        if(j < i - 1)
            i = 5;
        else
            i = 6;
        ((TextView)numberpicker.findViewById(0x1020344)).setImeOptions(i);
    }

    private void trySetContentDescription(View view, int i, int j)
    {
        view = view.findViewById(i);
        if(view != null)
            view.setContentDescription(mContext.getString(j));
    }

    private void updateCalendarView()
    {
        mCalendarView.setDate(mCurrentDate.getTimeInMillis(), false, false);
    }

    private void updateInputState()
    {
        InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
        if(inputmethodmanager == null) goto _L2; else goto _L1
_L1:
        if(!inputmethodmanager.isActive(mYearSpinnerInput)) goto _L4; else goto _L3
_L3:
        mYearSpinnerInput.clearFocus();
        inputmethodmanager.hideSoftInputFromWindow(mDelegator.getWindowToken(), 0);
_L2:
        return;
_L4:
        if(inputmethodmanager.isActive(mMonthSpinnerInput))
        {
            mMonthSpinnerInput.clearFocus();
            inputmethodmanager.hideSoftInputFromWindow(mDelegator.getWindowToken(), 0);
        } else
        if(inputmethodmanager.isActive(mDaySpinnerInput))
        {
            mDaySpinnerInput.clearFocus();
            inputmethodmanager.hideSoftInputFromWindow(mDelegator.getWindowToken(), 0);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void updateSpinners()
    {
        String as[];
        if(mCurrentDate.equals(mMinDate))
        {
            mDaySpinner.setMinValue(mCurrentDate.get(5));
            mDaySpinner.setMaxValue(mCurrentDate.getActualMaximum(5));
            mDaySpinner.setWrapSelectorWheel(false);
            mMonthSpinner.setDisplayedValues(null);
            mMonthSpinner.setMinValue(mCurrentDate.get(2));
            mMonthSpinner.setMaxValue(mCurrentDate.getActualMaximum(2));
            mMonthSpinner.setWrapSelectorWheel(false);
        } else
        if(mCurrentDate.equals(mMaxDate))
        {
            mDaySpinner.setMinValue(mCurrentDate.getActualMinimum(5));
            mDaySpinner.setMaxValue(mCurrentDate.get(5));
            mDaySpinner.setWrapSelectorWheel(false);
            mMonthSpinner.setDisplayedValues(null);
            mMonthSpinner.setMinValue(mCurrentDate.getActualMinimum(2));
            mMonthSpinner.setMaxValue(mCurrentDate.get(2));
            mMonthSpinner.setWrapSelectorWheel(false);
        } else
        {
            mDaySpinner.setMinValue(1);
            mDaySpinner.setMaxValue(mCurrentDate.getActualMaximum(5));
            mDaySpinner.setWrapSelectorWheel(true);
            mMonthSpinner.setDisplayedValues(null);
            mMonthSpinner.setMinValue(0);
            mMonthSpinner.setMaxValue(11);
            mMonthSpinner.setWrapSelectorWheel(true);
        }
        as = (String[])Arrays.copyOfRange(mShortMonths, mMonthSpinner.getMinValue(), mMonthSpinner.getMaxValue() + 1);
        mMonthSpinner.setDisplayedValues(as);
        mYearSpinner.setMinValue(mMinDate.get(1));
        mYearSpinner.setMaxValue(mMaxDate.get(1));
        mYearSpinner.setWrapSelectorWheel(false);
        mYearSpinner.setValue(mCurrentDate.get(1));
        mMonthSpinner.setValue(mCurrentDate.get(2));
        mDaySpinner.setValue(mCurrentDate.get(5));
        if(usingNumericMonths())
            mMonthSpinnerInput.setRawInputType(2);
    }

    private boolean usingNumericMonths()
    {
        return Character.isDigit(mShortMonths[0].charAt(0));
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        onPopulateAccessibilityEvent(accessibilityevent);
        return true;
    }

    public CalendarView getCalendarView()
    {
        return mCalendarView;
    }

    public boolean getCalendarViewShown()
    {
        boolean flag = false;
        if(mCalendarView.getVisibility() == 0)
            flag = true;
        return flag;
    }

    public int getDayOfMonth()
    {
        return mCurrentDate.get(5);
    }

    public int getFirstDayOfWeek()
    {
        return mCalendarView.getFirstDayOfWeek();
    }

    public Calendar getMaxDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mCalendarView.getMaxDate());
        return calendar;
    }

    public Calendar getMinDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mCalendarView.getMinDate());
        return calendar;
    }

    public int getMonth()
    {
        return mCurrentDate.get(2);
    }

    public boolean getSpinnersShown()
    {
        return mSpinners.isShown();
    }

    public int getYear()
    {
        return mCurrentDate.get(1);
    }

    public void init(int i, int j, int k, DatePicker.OnDateChangedListener ondatechangedlistener)
    {
        setDate(i, j, k);
        updateSpinners();
        updateCalendarView();
        mOnDateChangedListener = ondatechangedlistener;
    }

    public boolean isEnabled()
    {
        return mIsEnabled;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        setCurrentLocale(configuration.locale);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable instanceof DatePicker.AbstractDatePickerDelegate.SavedState)
        {
            parcelable = (DatePicker.AbstractDatePickerDelegate.SavedState)parcelable;
            setDate(parcelable.getSelectedYear(), parcelable.getSelectedMonth(), parcelable.getSelectedDay());
            updateSpinners();
            updateCalendarView();
        }
    }

    public Parcelable onSaveInstanceState(Parcelable parcelable)
    {
        return new DatePicker.AbstractDatePickerDelegate.SavedState(parcelable, getYear(), getMonth(), getDayOfMonth(), getMinDate().getTimeInMillis(), getMaxDate().getTimeInMillis());
    }

    public void setCalendarViewShown(boolean flag)
    {
        CalendarView calendarview = mCalendarView;
        int i;
        if(flag)
            i = 0;
        else
            i = 8;
        calendarview.setVisibility(i);
    }

    protected void setCurrentLocale(Locale locale)
    {
        super.setCurrentLocale(locale);
        mTempDate = getCalendarForLocale(mTempDate, locale);
        mMinDate = getCalendarForLocale(mMinDate, locale);
        mMaxDate = getCalendarForLocale(mMaxDate, locale);
        mCurrentDate = getCalendarForLocale(mCurrentDate, locale);
        mNumberOfMonths = mTempDate.getActualMaximum(2) + 1;
        mShortMonths = (new DateFormatSymbols()).getShortMonths();
        if(usingNumericMonths())
        {
            mShortMonths = new String[mNumberOfMonths];
            for(int i = 0; i < mNumberOfMonths; i++)
                mShortMonths[i] = String.format("%d", new Object[] {
                    Integer.valueOf(i + 1)
                });

        }
    }

    public void setEnabled(boolean flag)
    {
        mDaySpinner.setEnabled(flag);
        mMonthSpinner.setEnabled(flag);
        mYearSpinner.setEnabled(flag);
        mCalendarView.setEnabled(flag);
        mIsEnabled = flag;
    }

    public void setFirstDayOfWeek(int i)
    {
        mCalendarView.setFirstDayOfWeek(i);
    }

    public void setMaxDate(long l)
    {
        mTempDate.setTimeInMillis(l);
        if(mTempDate.get(1) == mMaxDate.get(1) && mTempDate.get(6) == mMaxDate.get(6))
            return;
        mMaxDate.setTimeInMillis(l);
        mCalendarView.setMaxDate(l);
        if(mCurrentDate.after(mMaxDate))
        {
            mCurrentDate.setTimeInMillis(mMaxDate.getTimeInMillis());
            updateCalendarView();
        }
        updateSpinners();
    }

    public void setMinDate(long l)
    {
        mTempDate.setTimeInMillis(l);
        if(mTempDate.get(1) == mMinDate.get(1) && mTempDate.get(6) == mMinDate.get(6))
            return;
        mMinDate.setTimeInMillis(l);
        mCalendarView.setMinDate(l);
        if(mCurrentDate.before(mMinDate))
        {
            mCurrentDate.setTimeInMillis(mMinDate.getTimeInMillis());
            updateCalendarView();
        }
        updateSpinners();
    }

    public void setSpinnersShown(boolean flag)
    {
        LinearLayout linearlayout = mSpinners;
        int i;
        if(flag)
            i = 0;
        else
            i = 8;
        linearlayout.setVisibility(i);
    }

    public void updateDate(int i, int j, int k)
    {
        if(!isNewDate(i, j, k))
        {
            return;
        } else
        {
            setDate(i, j, k);
            updateSpinners();
            updateCalendarView();
            notifyDateChanged();
            return;
        }
    }

    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final boolean DEFAULT_CALENDAR_VIEW_SHOWN = true;
    private static final boolean DEFAULT_ENABLED_STATE = true;
    private static final int DEFAULT_END_YEAR = 2100;
    private static final boolean DEFAULT_SPINNERS_SHOWN = true;
    private static final int DEFAULT_START_YEAR = 1900;
    private final CalendarView mCalendarView;
    private final DateFormat mDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private final NumberPicker mDaySpinner;
    private final EditText mDaySpinnerInput;
    private boolean mIsEnabled;
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private final NumberPicker mMonthSpinner;
    private final EditText mMonthSpinnerInput;
    private int mNumberOfMonths;
    private String mShortMonths[];
    private final LinearLayout mSpinners;
    private Calendar mTempDate;
    private final NumberPicker mYearSpinner;
    private final EditText mYearSpinnerInput;
}
