// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.*;
import android.database.DataSetObserver;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.*;
import java.util.Locale;
import libcore.icu.LocaleData;

// Referenced classes of package android.widget:
//            CalendarView, ListView, TextView, AbsListView, 
//            BaseAdapter

class CalendarViewLegacyDelegate extends CalendarView.AbstractCalendarViewDelegate
{
    private class ScrollStateRunnable
        implements Runnable
    {

        public void doScrollStateChange(AbsListView abslistview, int i)
        {
            mView = abslistview;
            mNewState = i;
            mDelegator.removeCallbacks(this);
            mDelegator.postDelayed(this, 40L);
        }

        public void run()
        {
            CalendarViewLegacyDelegate._2D_set0(CalendarViewLegacyDelegate.this, mNewState);
            if(mNewState == 0 && CalendarViewLegacyDelegate._2D_get11(CalendarViewLegacyDelegate.this) != 0)
            {
                View view = mView.getChildAt(0);
                if(view == null)
                    return;
                int i = view.getBottom() - CalendarViewLegacyDelegate._2D_get6(CalendarViewLegacyDelegate.this);
                if(i > CalendarViewLegacyDelegate._2D_get6(CalendarViewLegacyDelegate.this))
                    if(CalendarViewLegacyDelegate._2D_get5(CalendarViewLegacyDelegate.this))
                        mView.smoothScrollBy(i - view.getHeight(), 500);
                    else
                        mView.smoothScrollBy(i, 500);
            }
            CalendarViewLegacyDelegate._2D_set1(CalendarViewLegacyDelegate.this, mNewState);
        }

        private int mNewState;
        private AbsListView mView;
        final CalendarViewLegacyDelegate this$0;

        private ScrollStateRunnable()
        {
            this$0 = CalendarViewLegacyDelegate.this;
            super();
        }

        ScrollStateRunnable(ScrollStateRunnable scrollstaterunnable)
        {
            this();
        }
    }

    private class WeekView extends View
    {

        static boolean _2D_get0(WeekView weekview)
        {
            return weekview.mHasFocusedDay;
        }

        static boolean _2D_get1(WeekView weekview)
        {
            return weekview.mHasSelectedDay;
        }

        static boolean _2D_get2(WeekView weekview)
        {
            return weekview.mHasUnfocusedDay;
        }

        private void drawBackground(Canvas canvas)
        {
            int i = 0;
            if(!mHasSelectedDay)
                return;
            mDrawPaint.setColor(CalendarViewLegacyDelegate._2D_get14(CalendarViewLegacyDelegate.this));
            mTempRect.top = CalendarViewLegacyDelegate._2D_get21(CalendarViewLegacyDelegate.this);
            mTempRect.bottom = mHeight;
            boolean flag = isLayoutRtl();
            if(flag)
            {
                mTempRect.left = 0;
                mTempRect.right = mSelectedLeft - 2;
            } else
            {
                Rect rect1 = mTempRect;
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                    i = mWidth / mNumCells;
                rect1.left = i;
                mTempRect.right = mSelectedLeft - 2;
            }
            canvas.drawRect(mTempRect, mDrawPaint);
            if(flag)
            {
                mTempRect.left = mSelectedRight + 3;
                Rect rect = mTempRect;
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                    i = mWidth - mWidth / mNumCells;
                else
                    i = mWidth;
                rect.right = i;
            } else
            {
                mTempRect.left = mSelectedRight + 3;
                mTempRect.right = mWidth;
            }
            canvas.drawRect(mTempRect, mDrawPaint);
        }

        private void drawSelectedDateVerticalBars(Canvas canvas)
        {
            if(!mHasSelectedDay)
            {
                return;
            } else
            {
                CalendarViewLegacyDelegate._2D_get12(CalendarViewLegacyDelegate.this).setBounds(mSelectedLeft - CalendarViewLegacyDelegate._2D_get13(CalendarViewLegacyDelegate.this) / 2, CalendarViewLegacyDelegate._2D_get21(CalendarViewLegacyDelegate.this), mSelectedLeft + CalendarViewLegacyDelegate._2D_get13(CalendarViewLegacyDelegate.this) / 2, mHeight);
                CalendarViewLegacyDelegate._2D_get12(CalendarViewLegacyDelegate.this).draw(canvas);
                CalendarViewLegacyDelegate._2D_get12(CalendarViewLegacyDelegate.this).setBounds(mSelectedRight - CalendarViewLegacyDelegate._2D_get13(CalendarViewLegacyDelegate.this) / 2, CalendarViewLegacyDelegate._2D_get21(CalendarViewLegacyDelegate.this), mSelectedRight + CalendarViewLegacyDelegate._2D_get13(CalendarViewLegacyDelegate.this) / 2, mHeight);
                CalendarViewLegacyDelegate._2D_get12(CalendarViewLegacyDelegate.this).draw(canvas);
                return;
            }
        }

        private void drawWeekNumbersAndDates(Canvas canvas)
        {
            float f = mDrawPaint.getTextSize();
            int i = (int)(((float)mHeight + f) / 2.0F) - CalendarViewLegacyDelegate._2D_get21(CalendarViewLegacyDelegate.this);
            int j = mNumCells;
            int k = j * 2;
            mDrawPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
            mDrawPaint.setTextSize(CalendarViewLegacyDelegate._2D_get1(CalendarViewLegacyDelegate.this));
            int l = 0;
            int j1 = 0;
            if(isLayoutRtl())
            {
                while(j1 < j - 1) 
                {
                    Paint paint = mMonthNumDrawPaint;
                    if(mFocusDay[j1])
                        l = CalendarViewLegacyDelegate._2D_get4(CalendarViewLegacyDelegate.this);
                    else
                        l = CalendarViewLegacyDelegate._2D_get18(CalendarViewLegacyDelegate.this);
                    paint.setColor(l);
                    l = ((j1 * 2 + 1) * mWidth) / k;
                    canvas.drawText(mDayNumbers[j - 1 - j1], l, i, mMonthNumDrawPaint);
                    j1++;
                }
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                {
                    mDrawPaint.setColor(CalendarViewLegacyDelegate._2D_get19(CalendarViewLegacyDelegate.this));
                    int k1 = mWidth;
                    l = mWidth / k;
                    canvas.drawText(mDayNumbers[0], k1 - l, i, mDrawPaint);
                }
            } else
            {
                int l1 = l;
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                {
                    mDrawPaint.setColor(CalendarViewLegacyDelegate._2D_get19(CalendarViewLegacyDelegate.this));
                    l1 = mWidth / k;
                    canvas.drawText(mDayNumbers[0], l1, i, mDrawPaint);
                    l1 = 1;
                }
                while(l1 < j) 
                {
                    Paint paint1 = mMonthNumDrawPaint;
                    int i1;
                    if(mFocusDay[l1])
                        i1 = CalendarViewLegacyDelegate._2D_get4(CalendarViewLegacyDelegate.this);
                    else
                        i1 = CalendarViewLegacyDelegate._2D_get18(CalendarViewLegacyDelegate.this);
                    paint1.setColor(i1);
                    i1 = ((l1 * 2 + 1) * mWidth) / k;
                    canvas.drawText(mDayNumbers[l1], i1, i, mMonthNumDrawPaint);
                    l1++;
                }
            }
        }

        private void drawWeekSeparators(Canvas canvas)
        {
            boolean flag = false;
            int i = CalendarViewLegacyDelegate._2D_get7(CalendarViewLegacyDelegate.this).getFirstVisiblePosition();
            int j = i;
            if(CalendarViewLegacyDelegate._2D_get7(CalendarViewLegacyDelegate.this).getChildAt(0).getTop() < 0)
                j = i + 1;
            if(j == mWeek)
                return;
            mDrawPaint.setColor(CalendarViewLegacyDelegate._2D_get20(CalendarViewLegacyDelegate.this));
            mDrawPaint.setStrokeWidth(CalendarViewLegacyDelegate._2D_get21(CalendarViewLegacyDelegate.this));
            float f;
            float f1;
            if(isLayoutRtl())
            {
                f = 0.0F;
                int k;
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                    k = mWidth - mWidth / mNumCells;
                else
                    k = mWidth;
                f1 = k;
            } else
            {
                int l = ((flag) ? 1 : 0);
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                    l = mWidth / mNumCells;
                f = l;
                f1 = mWidth;
            }
            canvas.drawLine(f, 0.0F, f1, 0.0F, mDrawPaint);
        }

        private void initializePaints()
        {
            mDrawPaint.setFakeBoldText(false);
            mDrawPaint.setAntiAlias(true);
            mDrawPaint.setStyle(android.graphics.Paint.Style.FILL);
            mMonthNumDrawPaint.setFakeBoldText(true);
            mMonthNumDrawPaint.setAntiAlias(true);
            mMonthNumDrawPaint.setStyle(android.graphics.Paint.Style.FILL);
            mMonthNumDrawPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
            mMonthNumDrawPaint.setTextSize(CalendarViewLegacyDelegate._2D_get1(CalendarViewLegacyDelegate.this));
        }

        private void updateSelectionPositions()
        {
            if(mHasSelectedDay)
            {
                boolean flag = isLayoutRtl();
                int i = mSelectedDay - CalendarViewLegacyDelegate._2D_get3(CalendarViewLegacyDelegate.this);
                int j = i;
                if(i < 0)
                    j = i + 7;
                i = j;
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                {
                    i = j;
                    if(flag ^ true)
                        i = j + 1;
                }
                if(flag)
                    mSelectedLeft = ((CalendarViewLegacyDelegate._2D_get2(CalendarViewLegacyDelegate.this) - 1 - i) * mWidth) / mNumCells;
                else
                    mSelectedLeft = (mWidth * i) / mNumCells;
                mSelectedRight = mSelectedLeft + mWidth / mNumCells;
            }
        }

        public boolean getBoundsForDate(Calendar calendar, Rect rect)
        {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(mFirstDay.getTime());
            for(int i = 0; i < CalendarViewLegacyDelegate._2D_get2(CalendarViewLegacyDelegate.this); i++)
            {
                if(calendar.get(1) == calendar1.get(1) && calendar.get(2) == calendar1.get(2) && calendar.get(5) == calendar1.get(5))
                {
                    int j = mWidth / mNumCells;
                    if(isLayoutRtl())
                    {
                        if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                            i = mNumCells - i - 2;
                        else
                            i = mNumCells - i - 1;
                        rect.left = i * j;
                    } else
                    {
                        int k = i;
                        if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                            k = i + 1;
                        rect.left = j * k;
                    }
                    rect.top = 0;
                    rect.right = rect.left + j;
                    rect.bottom = getHeight();
                    return true;
                }
                calendar1.add(5, 1);
            }

            return false;
        }

        public boolean getDayFromLocation(float f, Calendar calendar)
        {
            boolean flag = isLayoutRtl();
            int i;
            int j;
            if(flag)
            {
                i = 0;
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                    j = mWidth - mWidth / mNumCells;
                else
                    j = mWidth;
            } else
            {
                if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                    i = mWidth / mNumCells;
                else
                    i = 0;
                j = mWidth;
            }
            if(f < (float)i || f > (float)j)
            {
                calendar.clear();
                return false;
            }
            i = (int)(((f - (float)i) * (float)CalendarViewLegacyDelegate._2D_get2(CalendarViewLegacyDelegate.this)) / (float)(j - i));
            j = i;
            if(flag)
                j = CalendarViewLegacyDelegate._2D_get2(CalendarViewLegacyDelegate.this) - 1 - i;
            calendar.setTimeInMillis(mFirstDay.getTimeInMillis());
            calendar.add(5, j);
            return true;
        }

        public Calendar getFirstDay()
        {
            return mFirstDay;
        }

        public int getMonthOfFirstWeekDay()
        {
            return mMonthOfFirstWeekDay;
        }

        public int getMonthOfLastWeekDay()
        {
            return mLastWeekDayMonth;
        }

        public void init(int i, int j, int k)
        {
            mSelectedDay = j;
            boolean flag;
            int l;
            if(mSelectedDay != -1)
                flag = true;
            else
                flag = false;
            mHasSelectedDay = flag;
            if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
                j = CalendarViewLegacyDelegate._2D_get2(CalendarViewLegacyDelegate.this) + 1;
            else
                j = CalendarViewLegacyDelegate._2D_get2(CalendarViewLegacyDelegate.this);
            mNumCells = j;
            mWeek = i;
            CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).setTimeInMillis(CalendarViewLegacyDelegate._2D_get9(CalendarViewLegacyDelegate.this).getTimeInMillis());
            CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).add(3, mWeek);
            CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).setFirstDayOfWeek(CalendarViewLegacyDelegate._2D_get3(CalendarViewLegacyDelegate.this));
            mDayNumbers = new String[mNumCells];
            mFocusDay = new boolean[mNumCells];
            i = 0;
            if(CalendarViewLegacyDelegate._2D_get15(CalendarViewLegacyDelegate.this))
            {
                mDayNumbers[0] = String.format(Locale.getDefault(), "%d", new Object[] {
                    Integer.valueOf(CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).get(3))
                });
                i = 1;
            }
            l = CalendarViewLegacyDelegate._2D_get3(CalendarViewLegacyDelegate.this);
            j = CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).get(7);
            CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).add(5, l - j);
            mFirstDay = (Calendar)CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).clone();
            mMonthOfFirstWeekDay = CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).get(2);
            mHasUnfocusedDay = true;
            while(i < mNumCells) 
            {
                if(CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).get(2) == k)
                    flag = true;
                else
                    flag = false;
                mFocusDay[i] = flag;
                mHasFocusedDay = mHasFocusedDay | flag;
                mHasUnfocusedDay = mHasUnfocusedDay & (flag ^ true);
                if(CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).before(CalendarViewLegacyDelegate._2D_get9(CalendarViewLegacyDelegate.this)) || CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).after(CalendarViewLegacyDelegate._2D_get8(CalendarViewLegacyDelegate.this)))
                    mDayNumbers[i] = "";
                else
                    mDayNumbers[i] = String.format(Locale.getDefault(), "%d", new Object[] {
                        Integer.valueOf(CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).get(5))
                    });
                CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).add(5, 1);
                i++;
            }
            if(CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).get(5) == 1)
                CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).add(5, -1);
            mLastWeekDayMonth = CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).get(2);
            updateSelectionPositions();
        }

        protected void onDraw(Canvas canvas)
        {
            drawBackground(canvas);
            drawWeekNumbersAndDates(canvas);
            drawWeekSeparators(canvas);
            drawSelectedDateVerticalBars(canvas);
        }

        protected void onMeasure(int i, int j)
        {
            mHeight = (CalendarViewLegacyDelegate._2D_get7(CalendarViewLegacyDelegate.this).getHeight() - CalendarViewLegacyDelegate._2D_get7(CalendarViewLegacyDelegate.this).getPaddingTop() - CalendarViewLegacyDelegate._2D_get7(CalendarViewLegacyDelegate.this).getPaddingBottom()) / CalendarViewLegacyDelegate._2D_get16(CalendarViewLegacyDelegate.this);
            setMeasuredDimension(android.view.View.MeasureSpec.getSize(i), mHeight);
        }

        protected void onSizeChanged(int i, int j, int k, int l)
        {
            mWidth = i;
            updateSelectionPositions();
        }

        private String mDayNumbers[];
        private final Paint mDrawPaint = new Paint();
        private Calendar mFirstDay;
        private boolean mFocusDay[];
        private boolean mHasFocusedDay;
        private boolean mHasSelectedDay;
        private boolean mHasUnfocusedDay;
        private int mHeight;
        private int mLastWeekDayMonth;
        private final Paint mMonthNumDrawPaint = new Paint();
        private int mMonthOfFirstWeekDay;
        private int mNumCells;
        private int mSelectedDay;
        private int mSelectedLeft;
        private int mSelectedRight;
        private final Rect mTempRect = new Rect();
        private int mWeek;
        private int mWidth;
        final CalendarViewLegacyDelegate this$0;

        public WeekView(Context context)
        {
            this$0 = CalendarViewLegacyDelegate.this;
            super(context);
            mMonthOfFirstWeekDay = -1;
            mLastWeekDayMonth = -1;
            mWeek = -1;
            mHasSelectedDay = false;
            mSelectedDay = -1;
            mSelectedLeft = -1;
            mSelectedRight = -1;
            initializePaints();
        }
    }

    private class WeeksAdapter extends BaseAdapter
        implements android.view.View.OnTouchListener
    {

        static Calendar _2D_get0(WeeksAdapter weeksadapter)
        {
            return weeksadapter.mSelectedDate;
        }

        static void _2D_wrap0(WeeksAdapter weeksadapter)
        {
            weeksadapter.init();
        }

        private void init()
        {
            mSelectedWeek = CalendarViewLegacyDelegate._2D_wrap0(CalendarViewLegacyDelegate.this, mSelectedDate);
            mTotalWeekCount = CalendarViewLegacyDelegate._2D_wrap0(CalendarViewLegacyDelegate.this, CalendarViewLegacyDelegate._2D_get8(CalendarViewLegacyDelegate.this));
            if(CalendarViewLegacyDelegate._2D_get9(CalendarViewLegacyDelegate.this).get(7) != CalendarViewLegacyDelegate._2D_get3(CalendarViewLegacyDelegate.this) || CalendarViewLegacyDelegate._2D_get8(CalendarViewLegacyDelegate.this).get(7) != CalendarViewLegacyDelegate._2D_get3(CalendarViewLegacyDelegate.this))
                mTotalWeekCount = mTotalWeekCount + 1;
            notifyDataSetChanged();
        }

        private void onDateTapped(Calendar calendar)
        {
            setSelectedDay(calendar);
            CalendarViewLegacyDelegate._2D_wrap3(CalendarViewLegacyDelegate.this, calendar);
        }

        public int getCount()
        {
            return mTotalWeekCount;
        }

        public Object getItem(int i)
        {
            return null;
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public Calendar getSelectedDay()
        {
            return mSelectedDate;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            int j;
            if(view != null)
            {
                view = (WeekView)view;
            } else
            {
                view = new WeekView(mContext);
                view.setLayoutParams(new AbsListView.LayoutParams(-2, -2));
                view.setClickable(true);
                view.setOnTouchListener(this);
            }
            if(mSelectedWeek == i)
                j = mSelectedDate.get(7);
            else
                j = -1;
            view.init(i, j, mFocusedMonth);
            return view;
        }

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            if(CalendarViewLegacyDelegate._2D_get7(CalendarViewLegacyDelegate.this).isEnabled() && mGestureDetector.onTouchEvent(motionevent))
            {
                if(!((WeekView)view).getDayFromLocation(motionevent.getX(), CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this)))
                    return true;
                if(CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).before(CalendarViewLegacyDelegate._2D_get9(CalendarViewLegacyDelegate.this)) || CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this).after(CalendarViewLegacyDelegate._2D_get8(CalendarViewLegacyDelegate.this)))
                {
                    return true;
                } else
                {
                    onDateTapped(CalendarViewLegacyDelegate._2D_get17(CalendarViewLegacyDelegate.this));
                    return true;
                }
            } else
            {
                return false;
            }
        }

        public void setFocusMonth(int i)
        {
            if(mFocusedMonth == i)
            {
                return;
            } else
            {
                mFocusedMonth = i;
                notifyDataSetChanged();
                return;
            }
        }

        public void setSelectedDay(Calendar calendar)
        {
            if(calendar.get(6) == mSelectedDate.get(6) && calendar.get(1) == mSelectedDate.get(1))
            {
                return;
            } else
            {
                mSelectedDate.setTimeInMillis(calendar.getTimeInMillis());
                mSelectedWeek = CalendarViewLegacyDelegate._2D_wrap0(CalendarViewLegacyDelegate.this, mSelectedDate);
                mFocusedMonth = mSelectedDate.get(2);
                notifyDataSetChanged();
                return;
            }
        }

        private int mFocusedMonth;
        private GestureDetector mGestureDetector;
        private final Calendar mSelectedDate = Calendar.getInstance();
        private int mSelectedWeek;
        private int mTotalWeekCount;
        final CalendarViewLegacyDelegate this$0;

        public WeeksAdapter(Context context)
        {
            this$0 = CalendarViewLegacyDelegate.this;
            super();
            mContext = context;
            mGestureDetector = new GestureDetector(mContext, new CalendarGestureListener());
            init();
        }
    }

    class WeeksAdapter.CalendarGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public boolean onSingleTapUp(MotionEvent motionevent)
        {
            return true;
        }

        final WeeksAdapter this$1;

        WeeksAdapter.CalendarGestureListener()
        {
            this$1 = WeeksAdapter.this;
            super();
        }
    }


    static WeeksAdapter _2D_get0(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mAdapter;
    }

    static int _2D_get1(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mDateTextSize;
    }

    static CalendarView.OnDateChangeListener _2D_get10(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mOnDateChangeListener;
    }

    static int _2D_get11(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mPreviousScrollState;
    }

    static Drawable _2D_get12(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mSelectedDateVerticalBar;
    }

    static int _2D_get13(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mSelectedDateVerticalBarWidth;
    }

    static int _2D_get14(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mSelectedWeekBackgroundColor;
    }

    static boolean _2D_get15(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mShowWeekNumber;
    }

    static int _2D_get16(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mShownWeekCount;
    }

    static Calendar _2D_get17(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mTempDate;
    }

    static int _2D_get18(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mUnfocusedMonthDateColor;
    }

    static int _2D_get19(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mWeekNumberColor;
    }

    static int _2D_get2(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mDaysPerWeek;
    }

    static int _2D_get20(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mWeekSeparatorLineColor;
    }

    static int _2D_get21(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mWeekSeparatorLineWidth;
    }

    static int _2D_get3(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mFirstDayOfWeek;
    }

    static int _2D_get4(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mFocusedMonthDateColor;
    }

    static boolean _2D_get5(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mIsScrollingUp;
    }

    static int _2D_get6(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mListScrollTopOffset;
    }

    static ListView _2D_get7(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mListView;
    }

    static Calendar _2D_get8(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mMaxDate;
    }

    static Calendar _2D_get9(CalendarViewLegacyDelegate calendarviewlegacydelegate)
    {
        return calendarviewlegacydelegate.mMinDate;
    }

    static int _2D_set0(CalendarViewLegacyDelegate calendarviewlegacydelegate, int i)
    {
        calendarviewlegacydelegate.mCurrentScrollState = i;
        return i;
    }

    static int _2D_set1(CalendarViewLegacyDelegate calendarviewlegacydelegate, int i)
    {
        calendarviewlegacydelegate.mPreviousScrollState = i;
        return i;
    }

    static int _2D_wrap0(CalendarViewLegacyDelegate calendarviewlegacydelegate, Calendar calendar)
    {
        return calendarviewlegacydelegate.getWeeksSinceMinDate(calendar);
    }

    static void _2D_wrap1(CalendarViewLegacyDelegate calendarviewlegacydelegate, AbsListView abslistview, int i)
    {
        calendarviewlegacydelegate.onScrollStateChanged(abslistview, i);
    }

    static void _2D_wrap2(CalendarViewLegacyDelegate calendarviewlegacydelegate, AbsListView abslistview, int i, int j, int k)
    {
        calendarviewlegacydelegate.onScroll(abslistview, i, j, k);
    }

    static void _2D_wrap3(CalendarViewLegacyDelegate calendarviewlegacydelegate, Calendar calendar)
    {
        calendarviewlegacydelegate.setMonthDisplayed(calendar);
    }

    CalendarViewLegacyDelegate(CalendarView calendarview, Context context, AttributeSet attributeset, int i, int j)
    {
        super(calendarview, context);
        mListScrollTopOffset = 2;
        mWeekMinVisibleHeight = 12;
        mBottomBuffer = 20;
        mDaysPerWeek = 7;
        mFriction = 0.05F;
        mVelocityScale = 0.333F;
        mCurrentMonthDisplayed = -1;
        mIsScrollingUp = false;
        mPreviousScrollState = 0;
        mCurrentScrollState = 0;
        mScrollStateChangedRunnable = new ScrollStateRunnable(null);
        calendarview = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.CalendarView, i, j);
        mShowWeekNumber = calendarview.getBoolean(1, true);
        mFirstDayOfWeek = calendarview.getInt(0, LocaleData.get(Locale.getDefault()).firstDayOfWeek.intValue());
        if(!CalendarView.parseDate(calendarview.getString(2), mMinDate))
            CalendarView.parseDate("01/01/1900", mMinDate);
        if(!CalendarView.parseDate(calendarview.getString(3), mMaxDate))
            CalendarView.parseDate("01/01/2100", mMaxDate);
        if(mMaxDate.before(mMinDate))
            throw new IllegalArgumentException("Max date cannot be before min date.");
        mShownWeekCount = calendarview.getInt(4, 6);
        mSelectedWeekBackgroundColor = calendarview.getColor(5, 0);
        mFocusedMonthDateColor = calendarview.getColor(6, 0);
        mUnfocusedMonthDateColor = calendarview.getColor(7, 0);
        mWeekSeparatorLineColor = calendarview.getColor(9, 0);
        mWeekNumberColor = calendarview.getColor(8, 0);
        mSelectedDateVerticalBar = calendarview.getDrawable(10);
        mDateTextAppearanceResId = calendarview.getResourceId(12, 0x1030046);
        updateDateTextSize();
        mWeekDayTextAppearanceResId = calendarview.getResourceId(11, -1);
        calendarview.recycle();
        calendarview = mDelegator.getResources().getDisplayMetrics();
        mWeekMinVisibleHeight = (int)TypedValue.applyDimension(1, 12F, calendarview);
        mListScrollTopOffset = (int)TypedValue.applyDimension(1, 2.0F, calendarview);
        mBottomBuffer = (int)TypedValue.applyDimension(1, 20F, calendarview);
        mSelectedDateVerticalBarWidth = (int)TypedValue.applyDimension(1, 6F, calendarview);
        mWeekSeparatorLineWidth = (int)TypedValue.applyDimension(1, 1.0F, calendarview);
        calendarview = ((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(0x1090040, null, false);
        mDelegator.addView(calendarview);
        mListView = (ListView)mDelegator.findViewById(0x102000a);
        mDayNamesHeader = (ViewGroup)calendarview.findViewById(0x102021a);
        mMonthName = (TextView)calendarview.findViewById(0x1020310);
        setUpHeader();
        setUpListView();
        setUpAdapter();
        mTempDate.setTimeInMillis(System.currentTimeMillis());
        if(mTempDate.before(mMinDate))
            goTo(mMinDate, false, true, true);
        else
        if(mMaxDate.before(mTempDate))
            goTo(mMaxDate, false, true, true);
        else
            goTo(mTempDate, false, true, true);
        mDelegator.invalidate();
    }

    private static Calendar getCalendarForLocale(Calendar calendar, Locale locale)
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

    private int getWeeksSinceMinDate(Calendar calendar)
    {
        if(calendar.before(mMinDate))
            throw new IllegalArgumentException((new StringBuilder()).append("fromDate: ").append(mMinDate.getTime()).append(" does not precede toDate: ").append(calendar.getTime()).toString());
        else
            return (int)((((calendar.getTimeInMillis() + (long)calendar.getTimeZone().getOffset(calendar.getTimeInMillis())) - (mMinDate.getTimeInMillis() + (long)mMinDate.getTimeZone().getOffset(mMinDate.getTimeInMillis()))) + (long)(mMinDate.get(7) - mFirstDayOfWeek) * 0x5265c00L) / 0x240c8400L);
    }

    private void goTo(Calendar calendar, boolean flag, boolean flag1, boolean flag2)
    {
        int i;
        int j;
        int l;
        if(calendar.before(mMinDate) || calendar.after(mMaxDate))
            throw new IllegalArgumentException("timeInMillis must be between the values of getMinDate() and getMaxDate()");
        i = mListView.getFirstVisiblePosition();
        View view = mListView.getChildAt(0);
        j = i;
        if(view != null)
        {
            j = i;
            if(view.getTop() < 0)
                j = i + 1;
        }
        l = (mShownWeekCount + j) - 1;
        i = l;
        if(view != null)
        {
            i = l;
            if(view.getTop() > mBottomBuffer)
                i = l - 1;
        }
        if(flag1)
            mAdapter.setSelectedDay(calendar);
        l = getWeeksSinceMinDate(calendar);
        break MISSING_BLOCK_LABEL_140;
        if(l < j || l > i || flag2)
        {
            mFirstDayOfMonth.setTimeInMillis(calendar.getTimeInMillis());
            mFirstDayOfMonth.set(5, 1);
            setMonthDisplayed(mFirstDayOfMonth);
            int k;
            if(mFirstDayOfMonth.before(mMinDate))
                k = 0;
            else
                k = getWeeksSinceMinDate(mFirstDayOfMonth);
            mPreviousScrollState = 2;
            if(flag)
            {
                mListView.smoothScrollToPositionFromTop(k, mListScrollTopOffset, 1000);
            } else
            {
                mListView.setSelectionFromTop(k, mListScrollTopOffset);
                onScrollStateChanged(mListView, 0);
            }
        } else
        if(flag1)
            setMonthDisplayed(calendar);
        return;
    }

    private void invalidateAllWeekViews()
    {
        int i = mListView.getChildCount();
        for(int j = 0; j < i; j++)
            mListView.getChildAt(j).invalidate();

    }

    private static boolean isSameDate(Calendar calendar, Calendar calendar1)
    {
        boolean flag = true;
        if(calendar.get(6) == calendar1.get(6))
        {
            if(calendar.get(1) != calendar1.get(1))
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        WeekView weekview = (WeekView)abslistview.getChildAt(0);
        if(weekview == null)
            return;
        long l = abslistview.getFirstVisiblePosition() * weekview.getHeight() - weekview.getBottom();
        if(l < mPreviousScrollPosition)
            mIsScrollingUp = true;
        else
        if(l > mPreviousScrollPosition)
            mIsScrollingUp = false;
        else
            return;
        if(weekview.getBottom() < mWeekMinVisibleHeight)
            i = 1;
        else
            i = 0;
        if(mIsScrollingUp)
            weekview = (WeekView)abslistview.getChildAt(i + 2);
        else
        if(i != 0)
            weekview = (WeekView)abslistview.getChildAt(i);
        if(weekview != null)
        {
            if(mIsScrollingUp)
                i = weekview.getMonthOfFirstWeekDay();
            else
                i = weekview.getMonthOfLastWeekDay();
            if(mCurrentMonthDisplayed == 11 && i == 0)
                i = 1;
            else
            if(mCurrentMonthDisplayed == 0 && i == 11)
                i = -1;
            else
                i -= mCurrentMonthDisplayed;
            break MISSING_BLOCK_LABEL_116;
        }
        mPreviousScrollPosition = l;
        mPreviousScrollState = mCurrentScrollState;
        return;
        if(!mIsScrollingUp && i > 0 || mIsScrollingUp && i < 0)
        {
            abslistview = weekview.getFirstDay();
            if(mIsScrollingUp)
                abslistview.add(5, -7);
            else
                abslistview.add(5, 7);
            setMonthDisplayed(abslistview);
        }
        break MISSING_BLOCK_LABEL_152;
    }

    private void onScrollStateChanged(AbsListView abslistview, int i)
    {
        mScrollStateChangedRunnable.doScrollStateChange(abslistview, i);
    }

    private void setMonthDisplayed(Calendar calendar)
    {
        mCurrentMonthDisplayed = calendar.get(2);
        mAdapter.setFocusMonth(mCurrentMonthDisplayed);
        long l = calendar.getTimeInMillis();
        calendar = DateUtils.formatDateRange(mContext, l, l, 52);
        mMonthName.setText(calendar);
        mMonthName.invalidate();
    }

    private void setUpAdapter()
    {
        if(mAdapter == null)
        {
            mAdapter = new WeeksAdapter(mContext);
            mAdapter.registerDataSetObserver(new DataSetObserver() {

                public void onChanged()
                {
                    if(CalendarViewLegacyDelegate._2D_get10(CalendarViewLegacyDelegate.this) != null)
                    {
                        Calendar calendar = CalendarViewLegacyDelegate._2D_get0(CalendarViewLegacyDelegate.this).getSelectedDay();
                        CalendarViewLegacyDelegate._2D_get10(CalendarViewLegacyDelegate.this).onSelectedDayChange(mDelegator, calendar.get(1), calendar.get(2), calendar.get(5));
                    }
                }

                final CalendarViewLegacyDelegate this$0;

            
            {
                this$0 = CalendarViewLegacyDelegate.this;
                super();
            }
            }
);
            mListView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void setUpHeader()
    {
        mDayNamesShort = new String[mDaysPerWeek];
        mDayNamesLong = new String[mDaysPerWeek];
        int i = mFirstDayOfWeek;
        int j = mFirstDayOfWeek;
        int k = mDaysPerWeek;
        while(i < j + k) 
        {
            int l;
            if(i > 7)
                l = i - 7;
            else
                l = i;
            mDayNamesShort[i - mFirstDayOfWeek] = DateUtils.getDayOfWeekString(l, 50);
            mDayNamesLong[i - mFirstDayOfWeek] = DateUtils.getDayOfWeekString(l, 10);
            i++;
        }
        TextView textview = (TextView)mDayNamesHeader.getChildAt(0);
        int i1;
        if(mShowWeekNumber)
            textview.setVisibility(0);
        else
            textview.setVisibility(8);
        i = 1;
        i1 = mDayNamesHeader.getChildCount();
        while(i < i1) 
        {
            textview = (TextView)mDayNamesHeader.getChildAt(i);
            if(mWeekDayTextAppearanceResId > -1)
                textview.setTextAppearance(mWeekDayTextAppearanceResId);
            if(i < mDaysPerWeek + 1)
            {
                textview.setText(mDayNamesShort[i - 1]);
                textview.setContentDescription(mDayNamesLong[i - 1]);
                textview.setVisibility(0);
            } else
            {
                textview.setVisibility(8);
            }
            i++;
        }
        mDayNamesHeader.invalidate();
    }

    private void setUpListView()
    {
        mListView.setDivider(null);
        mListView.setItemsCanFocus(true);
        mListView.setVerticalScrollBarEnabled(false);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            public void onScroll(AbsListView abslistview, int i, int j, int k)
            {
                CalendarViewLegacyDelegate._2D_wrap2(CalendarViewLegacyDelegate.this, abslistview, i, j, k);
            }

            public void onScrollStateChanged(AbsListView abslistview, int i)
            {
                CalendarViewLegacyDelegate._2D_wrap1(CalendarViewLegacyDelegate.this, abslistview, i);
            }

            final CalendarViewLegacyDelegate this$0;

            
            {
                this$0 = CalendarViewLegacyDelegate.this;
                super();
            }
        }
);
        mListView.setFriction(mFriction);
        mListView.setVelocityScale(mVelocityScale);
    }

    private void updateDateTextSize()
    {
        TypedArray typedarray = mDelegator.getContext().obtainStyledAttributes(mDateTextAppearanceResId, com.android.internal.R.styleable.TextAppearance);
        mDateTextSize = typedarray.getDimensionPixelSize(0, 14);
        typedarray.recycle();
    }

    public boolean getBoundsForDate(long l, Rect rect)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        int i = mListView.getCount();
        for(int j = 0; j < i; j++)
        {
            WeekView weekview = (WeekView)mListView.getChildAt(j);
            if(weekview.getBoundsForDate(calendar, rect))
            {
                int ai[] = new int[2];
                int ai1[] = new int[2];
                weekview.getLocationOnScreen(ai);
                mDelegator.getLocationOnScreen(ai1);
                j = ai[1] - ai1[1];
                rect.top = rect.top + j;
                rect.bottom = rect.bottom + j;
                return true;
            }
        }

        return false;
    }

    public long getDate()
    {
        return WeeksAdapter._2D_get0(mAdapter).getTimeInMillis();
    }

    public int getDateTextAppearance()
    {
        return mDateTextAppearanceResId;
    }

    public int getFirstDayOfWeek()
    {
        return mFirstDayOfWeek;
    }

    public int getFocusedMonthDateColor()
    {
        return mFocusedMonthDateColor;
    }

    public long getMaxDate()
    {
        return mMaxDate.getTimeInMillis();
    }

    public long getMinDate()
    {
        return mMinDate.getTimeInMillis();
    }

    public Drawable getSelectedDateVerticalBar()
    {
        return mSelectedDateVerticalBar;
    }

    public int getSelectedWeekBackgroundColor()
    {
        return mSelectedWeekBackgroundColor;
    }

    public boolean getShowWeekNumber()
    {
        return mShowWeekNumber;
    }

    public int getShownWeekCount()
    {
        return mShownWeekCount;
    }

    public int getUnfocusedMonthDateColor()
    {
        return mUnfocusedMonthDateColor;
    }

    public int getWeekDayTextAppearance()
    {
        return mWeekDayTextAppearanceResId;
    }

    public int getWeekNumberColor()
    {
        return mWeekNumberColor;
    }

    public int getWeekSeparatorLineColor()
    {
        return mWeekSeparatorLineColor;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        setCurrentLocale(configuration.locale);
    }

    protected void setCurrentLocale(Locale locale)
    {
        super.setCurrentLocale(locale);
        mTempDate = getCalendarForLocale(mTempDate, locale);
        mFirstDayOfMonth = getCalendarForLocale(mFirstDayOfMonth, locale);
        mMinDate = getCalendarForLocale(mMinDate, locale);
        mMaxDate = getCalendarForLocale(mMaxDate, locale);
    }

    public void setDate(long l)
    {
        setDate(l, false, false);
    }

    public void setDate(long l, boolean flag, boolean flag1)
    {
        mTempDate.setTimeInMillis(l);
        if(isSameDate(mTempDate, WeeksAdapter._2D_get0(mAdapter)))
        {
            return;
        } else
        {
            goTo(mTempDate, flag, true, flag1);
            return;
        }
    }

    public void setDateTextAppearance(int i)
    {
        if(mDateTextAppearanceResId != i)
        {
            mDateTextAppearanceResId = i;
            updateDateTextSize();
            invalidateAllWeekViews();
        }
    }

    public void setFirstDayOfWeek(int i)
    {
        if(mFirstDayOfWeek == i)
        {
            return;
        } else
        {
            mFirstDayOfWeek = i;
            WeeksAdapter._2D_wrap0(mAdapter);
            mAdapter.notifyDataSetChanged();
            setUpHeader();
            return;
        }
    }

    public void setFocusedMonthDateColor(int i)
    {
        if(mFocusedMonthDateColor != i)
        {
            mFocusedMonthDateColor = i;
            int j = mListView.getChildCount();
            for(i = 0; i < j; i++)
            {
                WeekView weekview = (WeekView)mListView.getChildAt(i);
                if(WeekView._2D_get0(weekview))
                    weekview.invalidate();
            }

        }
    }

    public void setMaxDate(long l)
    {
        mTempDate.setTimeInMillis(l);
        if(isSameDate(mTempDate, mMaxDate))
            return;
        mMaxDate.setTimeInMillis(l);
        WeeksAdapter._2D_wrap0(mAdapter);
        Calendar calendar = WeeksAdapter._2D_get0(mAdapter);
        if(calendar.after(mMaxDate))
            setDate(mMaxDate.getTimeInMillis());
        else
            goTo(calendar, false, true, false);
    }

    public void setMinDate(long l)
    {
        mTempDate.setTimeInMillis(l);
        if(isSameDate(mTempDate, mMinDate))
            return;
        mMinDate.setTimeInMillis(l);
        Calendar calendar = WeeksAdapter._2D_get0(mAdapter);
        if(calendar.before(mMinDate))
            mAdapter.setSelectedDay(mMinDate);
        WeeksAdapter._2D_wrap0(mAdapter);
        if(calendar.before(mMinDate))
            setDate(mTempDate.getTimeInMillis());
        else
            goTo(calendar, false, true, false);
    }

    public void setOnDateChangeListener(CalendarView.OnDateChangeListener ondatechangelistener)
    {
        mOnDateChangeListener = ondatechangelistener;
    }

    public void setSelectedDateVerticalBar(int i)
    {
        setSelectedDateVerticalBar(mDelegator.getContext().getDrawable(i));
    }

    public void setSelectedDateVerticalBar(Drawable drawable)
    {
        if(mSelectedDateVerticalBar != drawable)
        {
            mSelectedDateVerticalBar = drawable;
            int i = mListView.getChildCount();
            for(int j = 0; j < i; j++)
            {
                drawable = (WeekView)mListView.getChildAt(j);
                if(WeekView._2D_get1(drawable))
                    drawable.invalidate();
            }

        }
    }

    public void setSelectedWeekBackgroundColor(int i)
    {
        if(mSelectedWeekBackgroundColor != i)
        {
            mSelectedWeekBackgroundColor = i;
            int j = mListView.getChildCount();
            for(i = 0; i < j; i++)
            {
                WeekView weekview = (WeekView)mListView.getChildAt(i);
                if(WeekView._2D_get1(weekview))
                    weekview.invalidate();
            }

        }
    }

    public void setShowWeekNumber(boolean flag)
    {
        if(mShowWeekNumber == flag)
        {
            return;
        } else
        {
            mShowWeekNumber = flag;
            mAdapter.notifyDataSetChanged();
            setUpHeader();
            return;
        }
    }

    public void setShownWeekCount(int i)
    {
        if(mShownWeekCount != i)
        {
            mShownWeekCount = i;
            mDelegator.invalidate();
        }
    }

    public void setUnfocusedMonthDateColor(int i)
    {
        if(mUnfocusedMonthDateColor != i)
        {
            mUnfocusedMonthDateColor = i;
            int j = mListView.getChildCount();
            for(i = 0; i < j; i++)
            {
                WeekView weekview = (WeekView)mListView.getChildAt(i);
                if(WeekView._2D_get2(weekview))
                    weekview.invalidate();
            }

        }
    }

    public void setWeekDayTextAppearance(int i)
    {
        if(mWeekDayTextAppearanceResId != i)
        {
            mWeekDayTextAppearanceResId = i;
            setUpHeader();
        }
    }

    public void setWeekNumberColor(int i)
    {
        if(mWeekNumberColor != i)
        {
            mWeekNumberColor = i;
            if(mShowWeekNumber)
                invalidateAllWeekViews();
        }
    }

    public void setWeekSeparatorLineColor(int i)
    {
        if(mWeekSeparatorLineColor != i)
        {
            mWeekSeparatorLineColor = i;
            invalidateAllWeekViews();
        }
    }

    private static final int ADJUSTMENT_SCROLL_DURATION = 500;
    private static final int DAYS_PER_WEEK = 7;
    private static final int DEFAULT_DATE_TEXT_SIZE = 14;
    private static final int DEFAULT_SHOWN_WEEK_COUNT = 6;
    private static final boolean DEFAULT_SHOW_WEEK_NUMBER = true;
    private static final int DEFAULT_WEEK_DAY_TEXT_APPEARANCE_RES_ID = -1;
    private static final int GOTO_SCROLL_DURATION = 1000;
    private static final long MILLIS_IN_DAY = 0x5265c00L;
    private static final long MILLIS_IN_WEEK = 0x240c8400L;
    private static final int SCROLL_CHANGE_DELAY = 40;
    private static final int SCROLL_HYST_WEEKS = 2;
    private static final int UNSCALED_BOTTOM_BUFFER = 20;
    private static final int UNSCALED_LIST_SCROLL_TOP_OFFSET = 2;
    private static final int UNSCALED_SELECTED_DATE_VERTICAL_BAR_WIDTH = 6;
    private static final int UNSCALED_WEEK_MIN_VISIBLE_HEIGHT = 12;
    private static final int UNSCALED_WEEK_SEPARATOR_LINE_WIDTH = 1;
    private WeeksAdapter mAdapter;
    private int mBottomBuffer;
    private int mCurrentMonthDisplayed;
    private int mCurrentScrollState;
    private int mDateTextAppearanceResId;
    private int mDateTextSize;
    private ViewGroup mDayNamesHeader;
    private String mDayNamesLong[];
    private String mDayNamesShort[];
    private int mDaysPerWeek;
    private Calendar mFirstDayOfMonth;
    private int mFirstDayOfWeek;
    private int mFocusedMonthDateColor;
    private float mFriction;
    private boolean mIsScrollingUp;
    private int mListScrollTopOffset;
    private ListView mListView;
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private TextView mMonthName;
    private CalendarView.OnDateChangeListener mOnDateChangeListener;
    private long mPreviousScrollPosition;
    private int mPreviousScrollState;
    private ScrollStateRunnable mScrollStateChangedRunnable;
    private Drawable mSelectedDateVerticalBar;
    private final int mSelectedDateVerticalBarWidth;
    private int mSelectedWeekBackgroundColor;
    private boolean mShowWeekNumber;
    private int mShownWeekCount;
    private Calendar mTempDate;
    private int mUnfocusedMonthDateColor;
    private float mVelocityScale;
    private int mWeekDayTextAppearanceResId;
    private int mWeekMinVisibleHeight;
    private int mWeekNumberColor;
    private int mWeekSeparatorLineColor;
    private final int mWeekSeparatorLineWidth;
}
