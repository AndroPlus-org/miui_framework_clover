// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.*;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.icu.util.Calendar;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import java.util.Locale;

// Referenced classes of package android.widget:
//            DatePicker, TextView, ViewAnimator, DayPickerView, 
//            YearPickerView, CalendarView

class DatePickerCalendarDelegate extends DatePicker.AbstractDatePickerDelegate
{

    static TextView _2D_get0(DatePickerCalendarDelegate datepickercalendardelegate)
    {
        return datepickercalendardelegate.mHeaderYear;
    }

    static void _2D_wrap0(DatePickerCalendarDelegate datepickercalendardelegate, boolean flag, boolean flag1)
    {
        datepickercalendardelegate.onDateChanged(flag, flag1);
    }

    static void _2D_wrap1(DatePickerCalendarDelegate datepickercalendardelegate, int i)
    {
        datepickercalendardelegate.setCurrentView(i);
    }

    public DatePickerCalendarDelegate(DatePicker datepicker, Context context, AttributeSet attributeset, int i, int j)
    {
        super(datepicker, context);
        mCurrentView = -1;
        mFirstDayOfWeek = 0;
        datepicker = mCurrentLocale;
        mCurrentDate = Calendar.getInstance(datepicker);
        mTempDate = Calendar.getInstance(datepicker);
        mMinDate = Calendar.getInstance(datepicker);
        mMaxDate = Calendar.getInstance(datepicker);
        mMinDate.set(1900, 0, 1);
        mMaxDate.set(2100, 11, 31);
        Resources resources = mDelegator.getResources();
        attributeset = mContext.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.DatePicker, i, j);
        mContainer = (ViewGroup)((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(attributeset.getResourceId(19, 0x109004e), mDelegator, false);
        mContainer.setSaveFromParentEnabled(false);
        mDelegator.addView(mContainer);
        ViewGroup viewgroup = (ViewGroup)mContainer.findViewById(0x1020214);
        mHeaderYear = (TextView)viewgroup.findViewById(0x1020216);
        mHeaderYear.setOnClickListener(mOnHeaderClickListener);
        mHeaderMonthDay = (TextView)viewgroup.findViewById(0x1020215);
        mHeaderMonthDay.setOnClickListener(mOnHeaderClickListener);
        datepicker = null;
        i = attributeset.getResourceId(10, 0);
        if(i != 0)
        {
            context = mContext.obtainStyledAttributes(null, ATTRS_TEXT_COLOR, 0, i);
            datepicker = applyLegacyColorFixes(context.getColorStateList(0));
            context.recycle();
        }
        context = datepicker;
        if(datepicker == null)
            context = attributeset.getColorStateList(18);
        if(context != null)
        {
            mHeaderYear.setTextColor(context);
            mHeaderMonthDay.setTextColor(context);
        }
        if(attributeset.hasValueOrEmpty(0))
            viewgroup.setBackground(attributeset.getDrawable(0));
        attributeset.recycle();
        mAnimator = (ViewAnimator)mContainer.findViewById(0x10201a9);
        mDayPickerView = (DayPickerView)mAnimator.findViewById(0x1020213);
        mDayPickerView.setFirstDayOfWeek(mFirstDayOfWeek);
        mDayPickerView.setMinDate(mMinDate.getTimeInMillis());
        mDayPickerView.setMaxDate(mMaxDate.getTimeInMillis());
        mDayPickerView.setDate(mCurrentDate.getTimeInMillis());
        mDayPickerView.setOnDaySelectedListener(mOnDaySelectedListener);
        mYearPickerView = (YearPickerView)mAnimator.findViewById(0x1020217);
        mYearPickerView.setRange(mMinDate, mMaxDate);
        mYearPickerView.setYear(mCurrentDate.get(1));
        mYearPickerView.setOnYearSelectedListener(mOnYearSelectedListener);
        mSelectDay = resources.getString(0x10405ac);
        mSelectYear = resources.getString(0x10405b2);
        onLocaleChanged(mCurrentLocale);
        setCurrentView(0);
    }

    private ColorStateList applyLegacyColorFixes(ColorStateList colorstatelist)
    {
        if(colorstatelist == null || colorstatelist.hasState(0x10102fe))
            return colorstatelist;
        int i;
        int j;
        if(colorstatelist.hasState(0x10100a1))
        {
            i = colorstatelist.getColorForState(StateSet.get(10), 0);
            j = colorstatelist.getColorForState(StateSet.get(8), 0);
        } else
        {
            i = colorstatelist.getDefaultColor();
            j = multiplyAlphaComponent(i, mContext.obtainStyledAttributes(ATTRS_DISABLED_ALPHA).getFloat(0, 0.3F));
        }
        if(i == 0 || j == 0)
            return null;
        else
            return new ColorStateList(new int[][] {
                new int[] {
                    0x10102fe
                }, new int[0]
            }, new int[] {
                i, j
            });
    }

    public static int getDaysInMonth(int i, int j)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("Invalid Month");

        case 0: // '\0'
        case 2: // '\002'
        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
        case 11: // '\013'
            return 31;

        case 3: // '\003'
        case 5: // '\005'
        case 8: // '\b'
        case 10: // '\n'
            return 30;

        case 1: // '\001'
            break;
        }
        if(j % 4 == 0)
            i = 29;
        else
            i = 28;
        return i;
    }

    private int multiplyAlphaComponent(int i, float f)
    {
        return (int)((float)(i >> 24 & 0xff) * f + 0.5F) << 24 | i & 0xffffff;
    }

    private void onCurrentDateChanged(boolean flag)
    {
        if(mHeaderYear == null)
            return;
        String s = mYearFormat.format(mCurrentDate.getTime());
        mHeaderYear.setText(s);
        s = mMonthDayFormat.format(mCurrentDate.getTime());
        mHeaderMonthDay.setText(s);
        if(flag)
            mAnimator.announceForAccessibility(getFormattedCurrentDate());
    }

    private void onDateChanged(boolean flag, boolean flag1)
    {
        int i = mCurrentDate.get(1);
        if(flag1 && (mOnDateChangedListener != null || mAutoFillChangeListener != null))
        {
            int j = mCurrentDate.get(2);
            int k = mCurrentDate.get(5);
            if(mOnDateChangedListener != null)
                mOnDateChangedListener.onDateChanged(mDelegator, i, j, k);
            if(mAutoFillChangeListener != null)
                mAutoFillChangeListener.onDateChanged(mDelegator, i, j, k);
        }
        mDayPickerView.setDate(mCurrentDate.getTimeInMillis());
        mYearPickerView.setYear(i);
        onCurrentDateChanged(flag);
        if(flag)
            tryVibrate();
    }

    private void setCurrentView(int i)
    {
        i;
        JVM INSTR tableswitch 0 1: default 24
    //                   0 25
    //                   1 90;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        mDayPickerView.setDate(mCurrentDate.getTimeInMillis());
        if(mCurrentView != i)
        {
            mHeaderMonthDay.setActivated(true);
            mHeaderYear.setActivated(false);
            mAnimator.setDisplayedChild(0);
            mCurrentView = i;
        }
        mAnimator.announceForAccessibility(mSelectDay);
        continue; /* Loop/switch isn't completed */
_L3:
        int j = mCurrentDate.get(1);
        mYearPickerView.setYear(j);
        mYearPickerView.post(new _.Lambda._cls2f4l12BcqlVIiuw8w0ONZMWiEpk((byte)0, this));
        if(mCurrentView != i)
        {
            mHeaderMonthDay.setActivated(false);
            mHeaderYear.setActivated(true);
            mAnimator.setDisplayedChild(1);
            mCurrentView = i;
        }
        mAnimator.announceForAccessibility(mSelectYear);
        if(true) goto _L1; else goto _L4
_L4:
    }

    private void setDate(int i, int j, int k)
    {
        mCurrentDate.set(1, i);
        mCurrentDate.set(2, j);
        mCurrentDate.set(5, k);
        resetAutofilledValue();
    }

    private void tryVibrate()
    {
        mDelegator.performHapticFeedback(5);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        onPopulateAccessibilityEvent(accessibilityevent);
        return true;
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/DatePicker.getName();
    }

    public CalendarView getCalendarView()
    {
        throw new UnsupportedOperationException("Not supported by calendar-mode DatePicker");
    }

    public boolean getCalendarViewShown()
    {
        return false;
    }

    public int getDayOfMonth()
    {
        return mCurrentDate.get(5);
    }

    public int getFirstDayOfWeek()
    {
        if(mFirstDayOfWeek != 0)
            return mFirstDayOfWeek;
        else
            return mCurrentDate.getFirstDayOfWeek();
    }

    public Calendar getMaxDate()
    {
        return mMaxDate;
    }

    public Calendar getMinDate()
    {
        return mMinDate;
    }

    public int getMonth()
    {
        return mCurrentDate.get(2);
    }

    public boolean getSpinnersShown()
    {
        return false;
    }

    public int getYear()
    {
        return mCurrentDate.get(1);
    }

    public void init(int i, int j, int k, DatePicker.OnDateChangedListener ondatechangedlistener)
    {
        setDate(i, j, k);
        onDateChanged(false, false);
        mOnDateChangedListener = ondatechangedlistener;
    }

    public boolean isEnabled()
    {
        return mContainer.isEnabled();
    }

    void lambda$_2D_android_widget_DatePickerCalendarDelegate_11048(View view)
    {
        tryVibrate();
        view.getId();
        JVM INSTR tableswitch 16908821 16908822: default 32
    //                   16908821 41
    //                   16908822 33;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        setCurrentView(1);
        continue; /* Loop/switch isn't completed */
_L2:
        setCurrentView(0);
        if(true) goto _L1; else goto _L4
_L4:
    }

    void lambda$_2D_android_widget_DatePickerCalendarDelegate_13424()
    {
        mYearPickerView.requestFocus();
        View view = mYearPickerView.getSelectedView();
        if(view != null)
            view.requestFocus();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        setCurrentLocale(configuration.locale);
    }

    protected void onLocaleChanged(Locale locale)
    {
        if(mHeaderYear == null)
        {
            return;
        } else
        {
            mMonthDayFormat = DateFormat.getInstanceForSkeleton("EMMMd", locale);
            mMonthDayFormat.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
            mYearFormat = DateFormat.getInstanceForSkeleton("y", locale);
            onCurrentDateChanged(false);
            return;
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!(parcelable instanceof DatePicker.AbstractDatePickerDelegate.SavedState)) goto _L2; else goto _L1
_L1:
        int i;
        int k;
        parcelable = (DatePicker.AbstractDatePickerDelegate.SavedState)parcelable;
        mCurrentDate.set(parcelable.getSelectedYear(), parcelable.getSelectedMonth(), parcelable.getSelectedDay());
        mMinDate.setTimeInMillis(parcelable.getMinDate());
        mMaxDate.setTimeInMillis(parcelable.getMaxDate());
        onCurrentDateChanged(false);
        i = parcelable.getCurrentView();
        setCurrentView(i);
        k = parcelable.getListPosition();
        if(k == -1) goto _L2; else goto _L3
_L3:
        if(i != 0) goto _L5; else goto _L4
_L4:
        mDayPickerView.setPosition(k);
_L2:
        return;
_L5:
        if(i == 1)
        {
            int j = parcelable.getListPositionOffset();
            mYearPickerView.setSelectionFromTop(k, j);
        }
        if(true) goto _L2; else goto _L6
_L6:
    }

    public Parcelable onSaveInstanceState(Parcelable parcelable)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        i = mCurrentDate.get(1);
        j = mCurrentDate.get(2);
        k = mCurrentDate.get(5);
        l = -1;
        i1 = -1;
        if(mCurrentView != 0) goto _L2; else goto _L1
_L1:
        l = mDayPickerView.getMostVisiblePosition();
_L4:
        return new DatePicker.AbstractDatePickerDelegate.SavedState(parcelable, i, j, k, mMinDate.getTimeInMillis(), mMaxDate.getTimeInMillis(), mCurrentView, l, i1);
_L2:
        if(mCurrentView == 1)
        {
            l = mYearPickerView.getFirstVisiblePosition();
            i1 = mYearPickerView.getFirstPositionOffset();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setCalendarViewShown(boolean flag)
    {
    }

    public void setEnabled(boolean flag)
    {
        mContainer.setEnabled(flag);
        mDayPickerView.setEnabled(flag);
        mYearPickerView.setEnabled(flag);
        mHeaderYear.setEnabled(flag);
        mHeaderMonthDay.setEnabled(flag);
    }

    public void setFirstDayOfWeek(int i)
    {
        mFirstDayOfWeek = i;
        mDayPickerView.setFirstDayOfWeek(i);
    }

    public void setMaxDate(long l)
    {
        mTempDate.setTimeInMillis(l);
        if(mTempDate.get(1) == mMaxDate.get(1) && mTempDate.get(6) == mMaxDate.get(6))
            return;
        if(mCurrentDate.after(mTempDate))
        {
            mCurrentDate.setTimeInMillis(l);
            onDateChanged(false, true);
        }
        mMaxDate.setTimeInMillis(l);
        mDayPickerView.setMaxDate(l);
        mYearPickerView.setRange(mMinDate, mMaxDate);
    }

    public void setMinDate(long l)
    {
        mTempDate.setTimeInMillis(l);
        if(mTempDate.get(1) == mMinDate.get(1) && mTempDate.get(6) == mMinDate.get(6))
            return;
        if(mCurrentDate.before(mTempDate))
        {
            mCurrentDate.setTimeInMillis(l);
            onDateChanged(false, true);
        }
        mMinDate.setTimeInMillis(l);
        mDayPickerView.setMinDate(l);
        mYearPickerView.setRange(mMinDate, mMaxDate);
    }

    public void setSpinnersShown(boolean flag)
    {
    }

    public void updateDate(int i, int j, int k)
    {
        setDate(i, j, k);
        onDateChanged(false, true);
    }

    private static final int ANIMATION_DURATION = 300;
    private static final int ATTRS_DISABLED_ALPHA[] = {
        0x1010033
    };
    private static final int ATTRS_TEXT_COLOR[] = {
        0x1010098
    };
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final int UNINITIALIZED = -1;
    private static final int USE_LOCALE = 0;
    private static final int VIEW_MONTH_DAY = 0;
    private static final int VIEW_YEAR = 1;
    private ViewAnimator mAnimator;
    private ViewGroup mContainer;
    private int mCurrentView;
    private DayPickerView mDayPickerView;
    private int mFirstDayOfWeek;
    private TextView mHeaderMonthDay;
    private TextView mHeaderYear;
    private final Calendar mMaxDate;
    private final Calendar mMinDate;
    private DateFormat mMonthDayFormat;
    private final DayPickerView.OnDaySelectedListener mOnDaySelectedListener = new DayPickerView.OnDaySelectedListener() {

        public void onDaySelected(DayPickerView daypickerview, Calendar calendar)
        {
            mCurrentDate.setTimeInMillis(calendar.getTimeInMillis());
            DatePickerCalendarDelegate._2D_wrap0(DatePickerCalendarDelegate.this, true, true);
        }

        final DatePickerCalendarDelegate this$0;

            
            {
                this$0 = DatePickerCalendarDelegate.this;
                super();
            }
    }
;
    private final android.view.View.OnClickListener mOnHeaderClickListener = new _.Lambda._cls47CjpztMF6VTC4ohP7T_r8sdL0g(this);
    private final YearPickerView.OnYearSelectedListener mOnYearSelectedListener = new YearPickerView.OnYearSelectedListener() {

        public void onYearChanged(YearPickerView yearpickerview, int k)
        {
            int l = mCurrentDate.get(5);
            int i1 = DatePickerCalendarDelegate.getDaysInMonth(mCurrentDate.get(2), k);
            if(l > i1)
                mCurrentDate.set(5, i1);
            mCurrentDate.set(1, k);
            DatePickerCalendarDelegate._2D_wrap0(DatePickerCalendarDelegate.this, true, true);
            DatePickerCalendarDelegate._2D_wrap1(DatePickerCalendarDelegate.this, 0);
            DatePickerCalendarDelegate._2D_get0(DatePickerCalendarDelegate.this).requestFocus();
        }

        final DatePickerCalendarDelegate this$0;

            
            {
                this$0 = DatePickerCalendarDelegate.this;
                super();
            }
    }
;
    private String mSelectDay;
    private String mSelectYear;
    private final Calendar mTempDate;
    private DateFormat mYearFormat;
    private YearPickerView mYearPickerView;

}
