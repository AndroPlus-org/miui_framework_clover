// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.util.Log;
import java.text.*;
import java.util.Locale;

// Referenced classes of package android.widget:
//            FrameLayout, CalendarViewLegacyDelegate, CalendarViewMaterialDelegate

public class CalendarView extends FrameLayout
{
    static abstract class AbstractCalendarViewDelegate
        implements CalendarViewDelegate
    {

        public int getFocusedMonthDateColor()
        {
            return 0;
        }

        public Drawable getSelectedDateVerticalBar()
        {
            return null;
        }

        public int getSelectedWeekBackgroundColor()
        {
            return 0;
        }

        public boolean getShowWeekNumber()
        {
            return false;
        }

        public int getShownWeekCount()
        {
            return 0;
        }

        public int getUnfocusedMonthDateColor()
        {
            return 0;
        }

        public int getWeekNumberColor()
        {
            return 0;
        }

        public int getWeekSeparatorLineColor()
        {
            return 0;
        }

        public void onConfigurationChanged(Configuration configuration)
        {
        }

        protected void setCurrentLocale(Locale locale)
        {
            if(locale.equals(mCurrentLocale))
            {
                return;
            } else
            {
                mCurrentLocale = locale;
                return;
            }
        }

        public void setFocusedMonthDateColor(int i)
        {
        }

        public void setSelectedDateVerticalBar(int i)
        {
        }

        public void setSelectedDateVerticalBar(Drawable drawable)
        {
        }

        public void setSelectedWeekBackgroundColor(int i)
        {
        }

        public void setShowWeekNumber(boolean flag)
        {
        }

        public void setShownWeekCount(int i)
        {
        }

        public void setUnfocusedMonthDateColor(int i)
        {
        }

        public void setWeekNumberColor(int i)
        {
        }

        public void setWeekSeparatorLineColor(int i)
        {
        }

        protected static final String DEFAULT_MAX_DATE = "01/01/2100";
        protected static final String DEFAULT_MIN_DATE = "01/01/1900";
        protected Context mContext;
        protected Locale mCurrentLocale;
        protected CalendarView mDelegator;

        AbstractCalendarViewDelegate(CalendarView calendarview, Context context)
        {
            mDelegator = calendarview;
            mContext = context;
            setCurrentLocale(Locale.getDefault());
        }
    }

    private static interface CalendarViewDelegate
    {

        public abstract boolean getBoundsForDate(long l, Rect rect);

        public abstract long getDate();

        public abstract int getDateTextAppearance();

        public abstract int getFirstDayOfWeek();

        public abstract int getFocusedMonthDateColor();

        public abstract long getMaxDate();

        public abstract long getMinDate();

        public abstract Drawable getSelectedDateVerticalBar();

        public abstract int getSelectedWeekBackgroundColor();

        public abstract boolean getShowWeekNumber();

        public abstract int getShownWeekCount();

        public abstract int getUnfocusedMonthDateColor();

        public abstract int getWeekDayTextAppearance();

        public abstract int getWeekNumberColor();

        public abstract int getWeekSeparatorLineColor();

        public abstract void onConfigurationChanged(Configuration configuration);

        public abstract void setDate(long l);

        public abstract void setDate(long l, boolean flag, boolean flag1);

        public abstract void setDateTextAppearance(int i);

        public abstract void setFirstDayOfWeek(int i);

        public abstract void setFocusedMonthDateColor(int i);

        public abstract void setMaxDate(long l);

        public abstract void setMinDate(long l);

        public abstract void setOnDateChangeListener(OnDateChangeListener ondatechangelistener);

        public abstract void setSelectedDateVerticalBar(int i);

        public abstract void setSelectedDateVerticalBar(Drawable drawable);

        public abstract void setSelectedWeekBackgroundColor(int i);

        public abstract void setShowWeekNumber(boolean flag);

        public abstract void setShownWeekCount(int i);

        public abstract void setUnfocusedMonthDateColor(int i);

        public abstract void setWeekDayTextAppearance(int i);

        public abstract void setWeekNumberColor(int i);

        public abstract void setWeekSeparatorLineColor(int i);
    }

    public static interface OnDateChangeListener
    {

        public abstract void onSelectedDayChange(CalendarView calendarview, int i, int j, int k);
    }


    public CalendarView(Context context)
    {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101035d);
    }

    public CalendarView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public CalendarView(Context context, AttributeSet attributeset, int i, int j)
    {
        int k;
        super(context, attributeset, i, j);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.CalendarView, i, j);
        k = typedarray.getInt(13, 0);
        typedarray.recycle();
        k;
        JVM INSTR tableswitch 0 1: default 60
    //                   0 70
    //                   1 88;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException("invalid calendarViewMode attribute");
_L2:
        mDelegate = new CalendarViewLegacyDelegate(this, context, attributeset, i, j);
_L5:
        return;
_L3:
        mDelegate = new CalendarViewMaterialDelegate(this, context, attributeset, i, j);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static boolean parseDate(String s, Calendar calendar)
    {
        if(s == null || s.isEmpty())
            return false;
        try
        {
            calendar.setTime(DATE_FORMATTER.parse(s));
        }
        // Misplaced declaration of an exception variable
        catch(Calendar calendar)
        {
            Log.w("CalendarView", (new StringBuilder()).append("Date: ").append(s).append(" not in format: ").append("MM/dd/yyyy").toString());
            return false;
        }
        return true;
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/CalendarView.getName();
    }

    public boolean getBoundsForDate(long l, Rect rect)
    {
        return mDelegate.getBoundsForDate(l, rect);
    }

    public long getDate()
    {
        return mDelegate.getDate();
    }

    public int getDateTextAppearance()
    {
        return mDelegate.getDateTextAppearance();
    }

    public int getFirstDayOfWeek()
    {
        return mDelegate.getFirstDayOfWeek();
    }

    public int getFocusedMonthDateColor()
    {
        return mDelegate.getFocusedMonthDateColor();
    }

    public long getMaxDate()
    {
        return mDelegate.getMaxDate();
    }

    public long getMinDate()
    {
        return mDelegate.getMinDate();
    }

    public Drawable getSelectedDateVerticalBar()
    {
        return mDelegate.getSelectedDateVerticalBar();
    }

    public int getSelectedWeekBackgroundColor()
    {
        return mDelegate.getSelectedWeekBackgroundColor();
    }

    public boolean getShowWeekNumber()
    {
        return mDelegate.getShowWeekNumber();
    }

    public int getShownWeekCount()
    {
        return mDelegate.getShownWeekCount();
    }

    public int getUnfocusedMonthDateColor()
    {
        return mDelegate.getUnfocusedMonthDateColor();
    }

    public int getWeekDayTextAppearance()
    {
        return mDelegate.getWeekDayTextAppearance();
    }

    public int getWeekNumberColor()
    {
        return mDelegate.getWeekNumberColor();
    }

    public int getWeekSeparatorLineColor()
    {
        return mDelegate.getWeekSeparatorLineColor();
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mDelegate.onConfigurationChanged(configuration);
    }

    public void setDate(long l)
    {
        mDelegate.setDate(l);
    }

    public void setDate(long l, boolean flag, boolean flag1)
    {
        mDelegate.setDate(l, flag, flag1);
    }

    public void setDateTextAppearance(int i)
    {
        mDelegate.setDateTextAppearance(i);
    }

    public void setFirstDayOfWeek(int i)
    {
        mDelegate.setFirstDayOfWeek(i);
    }

    public void setFocusedMonthDateColor(int i)
    {
        mDelegate.setFocusedMonthDateColor(i);
    }

    public void setMaxDate(long l)
    {
        mDelegate.setMaxDate(l);
    }

    public void setMinDate(long l)
    {
        mDelegate.setMinDate(l);
    }

    public void setOnDateChangeListener(OnDateChangeListener ondatechangelistener)
    {
        mDelegate.setOnDateChangeListener(ondatechangelistener);
    }

    public void setSelectedDateVerticalBar(int i)
    {
        mDelegate.setSelectedDateVerticalBar(i);
    }

    public void setSelectedDateVerticalBar(Drawable drawable)
    {
        mDelegate.setSelectedDateVerticalBar(drawable);
    }

    public void setSelectedWeekBackgroundColor(int i)
    {
        mDelegate.setSelectedWeekBackgroundColor(i);
    }

    public void setShowWeekNumber(boolean flag)
    {
        mDelegate.setShowWeekNumber(flag);
    }

    public void setShownWeekCount(int i)
    {
        mDelegate.setShownWeekCount(i);
    }

    public void setUnfocusedMonthDateColor(int i)
    {
        mDelegate.setUnfocusedMonthDateColor(i);
    }

    public void setWeekDayTextAppearance(int i)
    {
        mDelegate.setWeekDayTextAppearance(i);
    }

    public void setWeekNumberColor(int i)
    {
        mDelegate.setWeekNumberColor(i);
    }

    public void setWeekSeparatorLineColor(int i)
    {
        mDelegate.setWeekSeparatorLineColor(i);
    }

    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
    private static final String LOG_TAG = "CalendarView";
    private static final int MODE_HOLO = 0;
    private static final int MODE_MATERIAL = 1;
    private final CalendarViewDelegate mDelegate;

}
