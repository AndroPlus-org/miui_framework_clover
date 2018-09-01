// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.widget:
//            ListView, BaseAdapter, TextView, AdapterView

class YearPickerView extends ListView
{
    public static interface OnYearSelectedListener
    {

        public abstract void onYearChanged(YearPickerView yearpickerview, int i);
    }

    private static class YearAdapter extends BaseAdapter
    {

        public boolean areAllItemsEnabled()
        {
            return true;
        }

        public int getCount()
        {
            return mCount;
        }

        public Integer getItem(int i)
        {
            return Integer.valueOf(getYearForPosition(i));
        }

        public volatile Object getItem(int i)
        {
            return getItem(i);
        }

        public long getItemId(int i)
        {
            return (long)getYearForPosition(i);
        }

        public int getItemViewType(int i)
        {
            return 0;
        }

        public int getPositionForYear(int i)
        {
            return i - mMinYear;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            boolean flag;
            int j;
            boolean flag1;
            if(view == null)
                flag = true;
            else
                flag = false;
            if(flag)
                view = (TextView)mInflater.inflate(0x1090125, viewgroup, false);
            else
                view = (TextView)view;
            j = getYearForPosition(i);
            if(mActivatedYear == j)
                flag1 = true;
            else
                flag1 = false;
            if(flag || view.isActivated() != flag1)
            {
                if(flag1)
                    i = 0x1030385;
                else
                    i = 0x1030384;
                view.setTextAppearance(i);
                view.setActivated(flag1);
            }
            view.setText(Integer.toString(j));
            return view;
        }

        public int getViewTypeCount()
        {
            return 1;
        }

        public int getYearForPosition(int i)
        {
            return mMinYear + i;
        }

        public boolean hasStableIds()
        {
            return true;
        }

        public boolean isEmpty()
        {
            return false;
        }

        public boolean isEnabled(int i)
        {
            return true;
        }

        public void setRange(Calendar calendar, Calendar calendar1)
        {
            int i = calendar.get(1);
            int j = (calendar1.get(1) - i) + 1;
            if(mMinYear != i || mCount != j)
            {
                mMinYear = i;
                mCount = j;
                notifyDataSetInvalidated();
            }
        }

        public boolean setSelection(int i)
        {
            if(mActivatedYear != i)
            {
                mActivatedYear = i;
                notifyDataSetChanged();
                return true;
            } else
            {
                return false;
            }
        }

        private static final int ITEM_LAYOUT = 0x1090125;
        private static final int ITEM_TEXT_ACTIVATED_APPEARANCE = 0x1030385;
        private static final int ITEM_TEXT_APPEARANCE = 0x1030384;
        private int mActivatedYear;
        private int mCount;
        private final LayoutInflater mInflater;
        private int mMinYear;

        public YearAdapter(Context context)
        {
            mInflater = LayoutInflater.from(context);
        }
    }


    static YearAdapter _2D_get0(YearPickerView yearpickerview)
    {
        return yearpickerview.mAdapter;
    }

    static OnYearSelectedListener _2D_get1(YearPickerView yearpickerview)
    {
        return yearpickerview.mOnYearSelectedListener;
    }

    public YearPickerView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010074);
    }

    public YearPickerView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public YearPickerView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        context = context.getResources();
        mViewSize = context.getDimensionPixelOffset(0x1050060);
        mChildSize = context.getDimensionPixelOffset(0x1050061);
        setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int k, long l)
            {
                k = YearPickerView._2D_get0(YearPickerView.this).getYearForPosition(k);
                YearPickerView._2D_get0(YearPickerView.this).setSelection(k);
                if(YearPickerView._2D_get1(YearPickerView.this) != null)
                    YearPickerView._2D_get1(YearPickerView.this).onYearChanged(YearPickerView.this, k);
            }

            final YearPickerView this$0;

            
            {
                this$0 = YearPickerView.this;
                super();
            }
        }
);
        mAdapter = new YearAdapter(getContext());
        setAdapter(mAdapter);
    }

    public int getFirstPositionOffset()
    {
        View view = getChildAt(0);
        if(view == null)
            return 0;
        else
            return view.getTop();
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        if(accessibilityevent.getEventType() == 4096)
        {
            accessibilityevent.setFromIndex(0);
            accessibilityevent.setToIndex(0);
        }
    }

    public void setOnYearSelectedListener(OnYearSelectedListener onyearselectedlistener)
    {
        mOnYearSelectedListener = onyearselectedlistener;
    }

    public void setRange(Calendar calendar, Calendar calendar1)
    {
        mAdapter.setRange(calendar, calendar1);
    }

    public void setSelectionCentered(int i)
    {
        setSelectionFromTop(i, mViewSize / 2 - mChildSize / 2);
    }

    public void setYear(final int year)
    {
        mAdapter.setSelection(year);
        post(new Runnable() {

            public void run()
            {
                int i = YearPickerView._2D_get0(YearPickerView.this).getPositionForYear(year);
                if(i >= 0 && i < getCount())
                    setSelectionCentered(i);
            }

            final YearPickerView this$0;
            final int val$year;

            
            {
                this$0 = YearPickerView.this;
                year = i;
                super();
            }
        }
);
    }

    private final YearAdapter mAdapter;
    private final int mChildSize;
    private OnYearSelectedListener mOnYearSelectedListener;
    private final int mViewSize;
}
