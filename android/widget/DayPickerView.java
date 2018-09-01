// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.*;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.widget.ViewPager;
import java.util.Locale;
import libcore.icu.LocaleData;

// Referenced classes of package android.widget:
//            DayPickerPagerAdapter, ImageButton, CalendarView, SimpleMonthView

class DayPickerView extends ViewGroup
{
    public static interface OnDaySelectedListener
    {

        public abstract void onDaySelected(DayPickerView daypickerview, Calendar calendar);
    }


    static AccessibilityManager _2D_get0(DayPickerView daypickerview)
    {
        return daypickerview.mAccessibilityManager;
    }

    static ImageButton _2D_get1(DayPickerView daypickerview)
    {
        return daypickerview.mNextButton;
    }

    static OnDaySelectedListener _2D_get2(DayPickerView daypickerview)
    {
        return daypickerview.mOnDaySelectedListener;
    }

    static ImageButton _2D_get3(DayPickerView daypickerview)
    {
        return daypickerview.mPrevButton;
    }

    static ViewPager _2D_get4(DayPickerView daypickerview)
    {
        return daypickerview.mViewPager;
    }

    static void _2D_wrap0(DayPickerView daypickerview, int i)
    {
        daypickerview.updateButtonVisibility(i);
    }

    public DayPickerView(Context context)
    {
        this(context, null);
    }

    public DayPickerView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101035d);
    }

    public DayPickerView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public DayPickerView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mSelectedDay = Calendar.getInstance();
        mMinDate = Calendar.getInstance();
        mMaxDate = Calendar.getInstance();
        mOnPageChangedListener = new com.android.internal.widget.ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int i1)
            {
            }

            public void onPageScrolled(int i1, float f, int j1)
            {
                f = Math.abs(0.5F - f) * 2.0F;
                DayPickerView._2D_get3(DayPickerView.this).setAlpha(f);
                DayPickerView._2D_get1(DayPickerView.this).setAlpha(f);
            }

            public void onPageSelected(int i1)
            {
                DayPickerView._2D_wrap0(DayPickerView.this, i1);
            }

            final DayPickerView this$0;

            
            {
                this$0 = DayPickerView.this;
                super();
            }
        }
;
        mOnClickListener = new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                byte byte0;
                boolean flag;
                int i1;
                if(view1 == DayPickerView._2D_get3(DayPickerView.this))
                    byte0 = -1;
                else
                if(view1 == DayPickerView._2D_get1(DayPickerView.this))
                    byte0 = 1;
                else
                    return;
                flag = DayPickerView._2D_get0(DayPickerView.this).isEnabled();
                i1 = DayPickerView._2D_get4(DayPickerView.this).getCurrentItem();
                DayPickerView._2D_get4(DayPickerView.this).setCurrentItem(i1 + byte0, flag ^ true);
            }

            final DayPickerView this$0;

            
            {
                this$0 = DayPickerView.this;
                super();
            }
        }
;
        mAccessibilityManager = (AccessibilityManager)context.getSystemService("accessibility");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.CalendarView, i, j);
        i = typedarray.getInt(0, LocaleData.get(Locale.getDefault()).firstDayOfWeek.intValue());
        String s = typedarray.getString(2);
        attributeset = typedarray.getString(3);
        j = typedarray.getResourceId(16, 0x103039a);
        int k = typedarray.getResourceId(11, 0x1030399);
        int l = typedarray.getResourceId(12, 0x1030398);
        android.content.res.ColorStateList colorstatelist = typedarray.getColorStateList(15);
        typedarray.recycle();
        mAdapter = new DayPickerPagerAdapter(context, 0x109004f, 0x1020311);
        mAdapter.setMonthTextAppearance(j);
        mAdapter.setDayOfWeekTextAppearance(k);
        mAdapter.setDayTextAppearance(l);
        mAdapter.setDaySelectorColor(colorstatelist);
        View view;
        for(context = (ViewGroup)LayoutInflater.from(context).inflate(0x1090051, this, false); context.getChildCount() > 0; addView(view))
        {
            view = context.getChildAt(0);
            context.removeViewAt(0);
        }

        mPrevButton = (ImageButton)findViewById(0x102038b);
        mPrevButton.setOnClickListener(mOnClickListener);
        mNextButton = (ImageButton)findViewById(0x1020322);
        mNextButton.setOnClickListener(mOnClickListener);
        mViewPager = (ViewPager)findViewById(0x102021b);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(mOnPageChangedListener);
        if(j != 0)
        {
            TypedArray typedarray1 = mContext.obtainStyledAttributes(null, ATTRS_TEXT_COLOR, 0, j);
            context = typedarray1.getColorStateList(0);
            if(context != null)
            {
                mPrevButton.setImageTintList(context);
                mNextButton.setImageTintList(context);
            }
            typedarray1.recycle();
        }
        context = Calendar.getInstance();
        if(!CalendarView.parseDate(s, context))
            context.set(1900, 0, 1);
        long l1 = context.getTimeInMillis();
        if(!CalendarView.parseDate(attributeset, context))
            context.set(2100, 11, 31);
        long l2 = context.getTimeInMillis();
        if(l2 < l1)
        {
            throw new IllegalArgumentException("maxDate must be >= minDate");
        } else
        {
            long l3 = MathUtils.constrain(System.currentTimeMillis(), l1, l2);
            setFirstDayOfWeek(i);
            setMinDate(l1);
            setMaxDate(l2);
            setDate(l3, false);
            mAdapter.setOnDaySelectedListener(new DayPickerPagerAdapter.OnDaySelectedListener() {

                public void onDaySelected(DayPickerPagerAdapter daypickerpageradapter, Calendar calendar)
                {
                    if(DayPickerView._2D_get2(DayPickerView.this) != null)
                        DayPickerView._2D_get2(DayPickerView.this).onDaySelected(DayPickerView.this, calendar);
                }

                final DayPickerView this$0;

            
            {
                this$0 = DayPickerView.this;
                super();
            }
            }
);
            return;
        }
    }

    private int getDiffMonths(Calendar calendar, Calendar calendar1)
    {
        int i = calendar1.get(1);
        int j = calendar.get(1);
        return (calendar1.get(2) - calendar.get(2)) + (i - j) * 12;
    }

    private int getPositionFromDay(long l)
    {
        int i = getDiffMonths(mMinDate, mMaxDate);
        return MathUtils.constrain(getDiffMonths(mMinDate, getTempCalendarForTime(l)), 0, i);
    }

    private Calendar getTempCalendarForTime(long l)
    {
        if(mTempCalendar == null)
            mTempCalendar = Calendar.getInstance();
        mTempCalendar.setTimeInMillis(l);
        return mTempCalendar;
    }

    private void setDate(long l, boolean flag, boolean flag1)
    {
        int i = 0;
        if(l >= mMinDate.getTimeInMillis()) goto _L2; else goto _L1
_L1:
        long l1;
        l1 = mMinDate.getTimeInMillis();
        i = 1;
_L4:
        getTempCalendarForTime(l1);
        if(flag1 || i != 0)
            mSelectedDay.setTimeInMillis(l1);
        i = getPositionFromDay(l1);
        if(i != mViewPager.getCurrentItem())
            mViewPager.setCurrentItem(i, flag);
        mAdapter.setSelectedDay(mTempCalendar);
        return;
_L2:
        l1 = l;
        if(l > mMaxDate.getTimeInMillis())
        {
            l1 = mMaxDate.getTimeInMillis();
            i = 1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateButtonVisibility(int i)
    {
        boolean flag = false;
        int j;
        ImageButton imagebutton;
        if(i > 0)
            j = 1;
        else
            j = 0;
        if(i < mAdapter.getCount() - 1)
            i = 1;
        else
            i = 0;
        imagebutton = mPrevButton;
        if(j != 0)
            j = 0;
        else
            j = 4;
        imagebutton.setVisibility(j);
        imagebutton = mNextButton;
        if(i != 0)
            i = ((flag) ? 1 : 0);
        else
            i = 4;
        imagebutton.setVisibility(i);
    }

    public boolean getBoundsForDate(long l, Rect rect)
    {
        if(getPositionFromDay(l) != mViewPager.getCurrentItem())
        {
            return false;
        } else
        {
            mTempCalendar.setTimeInMillis(l);
            return mAdapter.getBoundsForDate(mTempCalendar, rect);
        }
    }

    public long getDate()
    {
        return mSelectedDay.getTimeInMillis();
    }

    public int getDayOfWeekTextAppearance()
    {
        return mAdapter.getDayOfWeekTextAppearance();
    }

    public int getDayTextAppearance()
    {
        return mAdapter.getDayTextAppearance();
    }

    public int getFirstDayOfWeek()
    {
        return mAdapter.getFirstDayOfWeek();
    }

    public long getMaxDate()
    {
        return mMaxDate.getTimeInMillis();
    }

    public long getMinDate()
    {
        return mMinDate.getTimeInMillis();
    }

    public int getMostVisiblePosition()
    {
        return mViewPager.getCurrentItem();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        ImageButton imagebutton;
        ImageButton imagebutton1;
        SimpleMonthView simplemonthview;
        int i1;
        int j1;
        int k1;
        if(isLayoutRtl())
        {
            imagebutton = mNextButton;
            imagebutton1 = mPrevButton;
        } else
        {
            imagebutton = mPrevButton;
            imagebutton1 = mNextButton;
        }
        i = k - i;
        mViewPager.layout(0, 0, i, l - j);
        simplemonthview = (SimpleMonthView)mViewPager.getChildAt(0);
        k = simplemonthview.getMonthHeight();
        j = simplemonthview.getCellWidth();
        i1 = imagebutton.getMeasuredWidth();
        l = imagebutton.getMeasuredHeight();
        j1 = simplemonthview.getPaddingTop() + (k - l) / 2;
        k1 = simplemonthview.getPaddingLeft() + (j - i1) / 2;
        imagebutton.layout(k1, j1, k1 + i1, j1 + l);
        j1 = imagebutton1.getMeasuredWidth();
        l = imagebutton1.getMeasuredHeight();
        k = simplemonthview.getPaddingTop() + (k - l) / 2;
        i = i - simplemonthview.getPaddingRight() - (j - j1) / 2;
        imagebutton1.layout(i - j1, k, i, k + l);
    }

    protected void onMeasure(int i, int j)
    {
        ViewPager viewpager = mViewPager;
        measureChild(viewpager, i, j);
        setMeasuredDimension(viewpager.getMeasuredWidthAndState(), viewpager.getMeasuredHeightAndState());
        j = viewpager.getMeasuredWidth();
        i = viewpager.getMeasuredHeight();
        j = android.view.View.MeasureSpec.makeMeasureSpec(j, 0x80000000);
        i = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x80000000);
        mPrevButton.measure(j, i);
        mNextButton.measure(j, i);
    }

    public void onRangeChanged()
    {
        mAdapter.setRange(mMinDate, mMaxDate);
        setDate(mSelectedDay.getTimeInMillis(), false, false);
        updateButtonVisibility(mViewPager.getCurrentItem());
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        requestLayout();
    }

    public void setDate(long l)
    {
        setDate(l, false);
    }

    public void setDate(long l, boolean flag)
    {
        setDate(l, flag, true);
    }

    public void setDayOfWeekTextAppearance(int i)
    {
        mAdapter.setDayOfWeekTextAppearance(i);
    }

    public void setDayTextAppearance(int i)
    {
        mAdapter.setDayTextAppearance(i);
    }

    public void setFirstDayOfWeek(int i)
    {
        mAdapter.setFirstDayOfWeek(i);
    }

    public void setMaxDate(long l)
    {
        mMaxDate.setTimeInMillis(l);
        onRangeChanged();
    }

    public void setMinDate(long l)
    {
        mMinDate.setTimeInMillis(l);
        onRangeChanged();
    }

    public void setOnDaySelectedListener(OnDaySelectedListener ondayselectedlistener)
    {
        mOnDaySelectedListener = ondayselectedlistener;
    }

    public void setPosition(int i)
    {
        mViewPager.setCurrentItem(i, false);
    }

    private static final int ATTRS_TEXT_COLOR[] = {
        0x1010098
    };
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_LAYOUT = 0x1090051;
    private static final int DEFAULT_START_YEAR = 1900;
    private final AccessibilityManager mAccessibilityManager;
    private final DayPickerPagerAdapter mAdapter;
    private final Calendar mMaxDate;
    private final Calendar mMinDate;
    private final ImageButton mNextButton;
    private final android.view.View.OnClickListener mOnClickListener;
    private OnDaySelectedListener mOnDaySelectedListener;
    private final com.android.internal.widget.ViewPager.OnPageChangeListener mOnPageChangedListener;
    private final ImageButton mPrevButton;
    private final Calendar mSelectedDay;
    private Calendar mTempCalendar;
    private final ViewPager mViewPager;

}
