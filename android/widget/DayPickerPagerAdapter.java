// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.icu.util.Calendar;
import android.util.SparseArray;
import android.view.*;
import com.android.internal.widget.PagerAdapter;

// Referenced classes of package android.widget:
//            SimpleMonthView

class DayPickerPagerAdapter extends PagerAdapter
{
    public static interface OnDaySelectedListener
    {

        public abstract void onDaySelected(DayPickerPagerAdapter daypickerpageradapter, Calendar calendar);
    }

    private static class ViewHolder
    {

        public final SimpleMonthView calendar;
        public final View container;
        public final int position;

        public ViewHolder(int i, View view, SimpleMonthView simplemonthview)
        {
            position = i;
            container = view;
            calendar = simplemonthview;
        }
    }


    static OnDaySelectedListener _2D_get0(DayPickerPagerAdapter daypickerpageradapter)
    {
        return daypickerpageradapter.mOnDaySelectedListener;
    }

    public DayPickerPagerAdapter(Context context, int i, int j)
    {
        mSelectedDay = null;
        mInflater = LayoutInflater.from(context);
        mLayoutResId = i;
        mCalendarViewId = j;
        context = context.obtainStyledAttributes(new int[] {
            0x101042c
        });
        mDayHighlightColor = context.getColorStateList(0);
        context.recycle();
    }

    private int getMonthForPosition(int i)
    {
        return (mMinDate.get(2) + i) % 12;
    }

    private int getPositionForDay(Calendar calendar)
    {
        if(calendar == null)
            return -1;
        else
            return (calendar.get(1) - mMinDate.get(1)) * 12 + (calendar.get(2) - mMinDate.get(2));
    }

    private int getYearForPosition(int i)
    {
        i = (mMinDate.get(2) + i) / 12;
        return mMinDate.get(1) + i;
    }

    public void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        viewgroup.removeView(((ViewHolder)obj).container);
        mItems.remove(i);
    }

    public boolean getBoundsForDate(Calendar calendar, Rect rect)
    {
        int i = getPositionForDay(calendar);
        ViewHolder viewholder = (ViewHolder)mItems.get(i, null);
        if(viewholder == null)
        {
            return false;
        } else
        {
            int j = calendar.get(5);
            return viewholder.calendar.getBoundsForDay(j, rect);
        }
    }

    public int getCount()
    {
        return mCount;
    }

    int getDayOfWeekTextAppearance()
    {
        return mDayOfWeekTextAppearance;
    }

    int getDayTextAppearance()
    {
        return mDayTextAppearance;
    }

    public int getFirstDayOfWeek()
    {
        return mFirstDayOfWeek;
    }

    public int getItemPosition(Object obj)
    {
        return ((ViewHolder)obj).position;
    }

    public CharSequence getPageTitle(int i)
    {
        SimpleMonthView simplemonthview = ((ViewHolder)mItems.get(i)).calendar;
        if(simplemonthview != null)
            return simplemonthview.getMonthYearLabel();
        else
            return null;
    }

    SimpleMonthView getView(Object obj)
    {
        if(obj == null)
            return null;
        else
            return ((ViewHolder)obj).calendar;
    }

    public Object instantiateItem(ViewGroup viewgroup, int i)
    {
        View view = mInflater.inflate(mLayoutResId, viewgroup, false);
        Object obj = (SimpleMonthView)view.findViewById(mCalendarViewId);
        ((SimpleMonthView) (obj)).setOnDayClickListener(mOnDayClickListener);
        ((SimpleMonthView) (obj)).setMonthTextAppearance(mMonthTextAppearance);
        ((SimpleMonthView) (obj)).setDayOfWeekTextAppearance(mDayOfWeekTextAppearance);
        ((SimpleMonthView) (obj)).setDayTextAppearance(mDayTextAppearance);
        if(mDaySelectorColor != null)
            ((SimpleMonthView) (obj)).setDaySelectorColor(mDaySelectorColor);
        if(mDayHighlightColor != null)
            ((SimpleMonthView) (obj)).setDayHighlightColor(mDayHighlightColor);
        if(mCalendarTextColor != null)
        {
            ((SimpleMonthView) (obj)).setMonthTextColor(mCalendarTextColor);
            ((SimpleMonthView) (obj)).setDayOfWeekTextColor(mCalendarTextColor);
            ((SimpleMonthView) (obj)).setDayTextColor(mCalendarTextColor);
        }
        int j = getMonthForPosition(i);
        int k = getYearForPosition(i);
        int l;
        int i1;
        int j1;
        if(mSelectedDay != null && mSelectedDay.get(2) == j)
            l = mSelectedDay.get(5);
        else
            l = -1;
        if(mMinDate.get(2) == j && mMinDate.get(1) == k)
            i1 = mMinDate.get(5);
        else
            i1 = 1;
        if(mMaxDate.get(2) == j && mMaxDate.get(1) == k)
            j1 = mMaxDate.get(5);
        else
            j1 = 31;
        ((SimpleMonthView) (obj)).setMonthParams(l, j, k, mFirstDayOfWeek, i1, j1);
        obj = new ViewHolder(i, view, ((SimpleMonthView) (obj)));
        mItems.put(i, obj);
        viewgroup.addView(view);
        return obj;
    }

    public boolean isViewFromObject(View view, Object obj)
    {
        boolean flag;
        if(view == ((ViewHolder)obj).container)
            flag = true;
        else
            flag = false;
        return flag;
    }

    void setCalendarTextColor(ColorStateList colorstatelist)
    {
        mCalendarTextColor = colorstatelist;
        notifyDataSetChanged();
    }

    void setDayOfWeekTextAppearance(int i)
    {
        mDayOfWeekTextAppearance = i;
        notifyDataSetChanged();
    }

    void setDaySelectorColor(ColorStateList colorstatelist)
    {
        mDaySelectorColor = colorstatelist;
        notifyDataSetChanged();
    }

    void setDayTextAppearance(int i)
    {
        mDayTextAppearance = i;
        notifyDataSetChanged();
    }

    public void setFirstDayOfWeek(int i)
    {
        mFirstDayOfWeek = i;
        int j = mItems.size();
        for(int k = 0; k < j; k++)
            ((ViewHolder)mItems.valueAt(k)).calendar.setFirstDayOfWeek(i);

    }

    void setMonthTextAppearance(int i)
    {
        mMonthTextAppearance = i;
        notifyDataSetChanged();
    }

    public void setOnDaySelectedListener(OnDaySelectedListener ondayselectedlistener)
    {
        mOnDaySelectedListener = ondayselectedlistener;
    }

    public void setRange(Calendar calendar, Calendar calendar1)
    {
        mMinDate.setTimeInMillis(calendar.getTimeInMillis());
        mMaxDate.setTimeInMillis(calendar1.getTimeInMillis());
        mCount = (mMaxDate.get(1) - mMinDate.get(1)) * 12 + (mMaxDate.get(2) - mMinDate.get(2)) + 1;
        notifyDataSetChanged();
    }

    public void setSelectedDay(Calendar calendar)
    {
        int i = getPositionForDay(mSelectedDay);
        int j = getPositionForDay(calendar);
        if(i != j && i >= 0)
        {
            ViewHolder viewholder = (ViewHolder)mItems.get(i, null);
            if(viewholder != null)
                viewholder.calendar.setSelectedDay(-1);
        }
        if(j >= 0)
        {
            ViewHolder viewholder1 = (ViewHolder)mItems.get(j, null);
            if(viewholder1 != null)
            {
                int k = calendar.get(5);
                viewholder1.calendar.setSelectedDay(k);
            }
        }
        mSelectedDay = calendar;
    }

    private static final int MONTHS_IN_YEAR = 12;
    private ColorStateList mCalendarTextColor;
    private final int mCalendarViewId;
    private int mCount;
    private ColorStateList mDayHighlightColor;
    private int mDayOfWeekTextAppearance;
    private ColorStateList mDaySelectorColor;
    private int mDayTextAppearance;
    private int mFirstDayOfWeek;
    private final LayoutInflater mInflater;
    private final SparseArray mItems = new SparseArray();
    private final int mLayoutResId;
    private final Calendar mMaxDate = Calendar.getInstance();
    private final Calendar mMinDate = Calendar.getInstance();
    private int mMonthTextAppearance;
    private final SimpleMonthView.OnDayClickListener mOnDayClickListener = new SimpleMonthView.OnDayClickListener() {

        public void onDayClick(SimpleMonthView simplemonthview, Calendar calendar)
        {
            if(calendar != null)
            {
                setSelectedDay(calendar);
                if(DayPickerPagerAdapter._2D_get0(DayPickerPagerAdapter.this) != null)
                    DayPickerPagerAdapter._2D_get0(DayPickerPagerAdapter.this).onDaySelected(DayPickerPagerAdapter.this, calendar);
            }
        }

        final DayPickerPagerAdapter this$0;

            
            {
                this$0 = DayPickerPagerAdapter.this;
                super();
            }
    }
;
    private OnDaySelectedListener mOnDaySelectedListener;
    private Calendar mSelectedDay;
}
