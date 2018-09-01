// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.icu.util.Calendar;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            DayPickerView, CalendarView

class CalendarViewMaterialDelegate extends CalendarView.AbstractCalendarViewDelegate
{

    static CalendarView.OnDateChangeListener _2D_get0(CalendarViewMaterialDelegate calendarviewmaterialdelegate)
    {
        return calendarviewmaterialdelegate.mOnDateChangeListener;
    }

    public CalendarViewMaterialDelegate(CalendarView calendarview, Context context, AttributeSet attributeset, int i, int j)
    {
        super(calendarview, context);
        mDayPickerView = new DayPickerView(context, attributeset, i, j);
        mDayPickerView.setOnDaySelectedListener(mOnDaySelectedListener);
        calendarview.addView(mDayPickerView);
    }

    public boolean getBoundsForDate(long l, Rect rect)
    {
        if(mDayPickerView.getBoundsForDate(l, rect))
        {
            int ai[] = new int[2];
            int ai1[] = new int[2];
            mDayPickerView.getLocationOnScreen(ai);
            mDelegator.getLocationOnScreen(ai1);
            int i = ai[1] - ai1[1];
            rect.top = rect.top + i;
            rect.bottom = rect.bottom + i;
            return true;
        } else
        {
            return false;
        }
    }

    public long getDate()
    {
        return mDayPickerView.getDate();
    }

    public int getDateTextAppearance()
    {
        return mDayPickerView.getDayTextAppearance();
    }

    public int getFirstDayOfWeek()
    {
        return mDayPickerView.getFirstDayOfWeek();
    }

    public long getMaxDate()
    {
        return mDayPickerView.getMaxDate();
    }

    public long getMinDate()
    {
        return mDayPickerView.getMinDate();
    }

    public int getWeekDayTextAppearance()
    {
        return mDayPickerView.getDayOfWeekTextAppearance();
    }

    public void setDate(long l)
    {
        mDayPickerView.setDate(l, true);
    }

    public void setDate(long l, boolean flag, boolean flag1)
    {
        mDayPickerView.setDate(l, flag);
    }

    public void setDateTextAppearance(int i)
    {
        mDayPickerView.setDayTextAppearance(i);
    }

    public void setFirstDayOfWeek(int i)
    {
        mDayPickerView.setFirstDayOfWeek(i);
    }

    public void setMaxDate(long l)
    {
        mDayPickerView.setMaxDate(l);
    }

    public void setMinDate(long l)
    {
        mDayPickerView.setMinDate(l);
    }

    public void setOnDateChangeListener(CalendarView.OnDateChangeListener ondatechangelistener)
    {
        mOnDateChangeListener = ondatechangelistener;
    }

    public void setWeekDayTextAppearance(int i)
    {
        mDayPickerView.setDayOfWeekTextAppearance(i);
    }

    private final DayPickerView mDayPickerView;
    private CalendarView.OnDateChangeListener mOnDateChangeListener;
    private final DayPickerView.OnDaySelectedListener mOnDaySelectedListener = new DayPickerView.OnDaySelectedListener() {

        public void onDaySelected(DayPickerView daypickerview, Calendar calendar)
        {
            if(CalendarViewMaterialDelegate._2D_get0(CalendarViewMaterialDelegate.this) != null)
            {
                int k = calendar.get(1);
                int l = calendar.get(2);
                int i1 = calendar.get(5);
                CalendarViewMaterialDelegate._2D_get0(CalendarViewMaterialDelegate.this).onSelectedDayChange(mDelegator, k, l, i1);
            }
        }

        final CalendarViewMaterialDelegate this$0;

            
            {
                this$0 = CalendarViewMaterialDelegate.this;
                super();
            }
    }
;
}
