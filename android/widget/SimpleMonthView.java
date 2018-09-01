// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.*;
import android.graphics.*;
import android.icu.text.DisplayContext;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.format.DateFormat;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.widget.ExploreByTouchHelper;
import java.text.NumberFormat;
import java.util.Locale;
import libcore.icu.LocaleData;

class SimpleMonthView extends View
{
    private class MonthViewTouchHelper extends ExploreByTouchHelper
    {

        private CharSequence getDayDescription(int i)
        {
            if(SimpleMonthView._2D_wrap1(SimpleMonthView.this, i))
            {
                mTempCalendar.set(SimpleMonthView._2D_get4(SimpleMonthView.this), SimpleMonthView._2D_get3(SimpleMonthView.this), i);
                return DateFormat.format("dd MMMM yyyy", mTempCalendar.getTimeInMillis());
            } else
            {
                return "";
            }
        }

        private CharSequence getDayText(int i)
        {
            if(SimpleMonthView._2D_wrap1(SimpleMonthView.this, i))
                return SimpleMonthView._2D_get1(SimpleMonthView.this).format(i);
            else
                return null;
        }

        protected int getVirtualViewAt(float f, float f1)
        {
            int i = SimpleMonthView._2D_wrap3(SimpleMonthView.this, (int)(f + 0.5F), (int)(0.5F + f1));
            if(i != -1)
                return i;
            else
                return 0x80000000;
        }

        protected void getVisibleVirtualViews(IntArray intarray)
        {
            for(int i = 1; i <= SimpleMonthView._2D_get2(SimpleMonthView.this); i++)
                intarray.add(i);

        }

        protected boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
        {
            switch(j)
            {
            default:
                return false;

            case 16: // '\020'
                return SimpleMonthView._2D_wrap2(SimpleMonthView.this, i);
            }
        }

        protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
        {
            accessibilityevent.setContentDescription(getDayDescription(i));
        }

        protected void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            if(!getBoundsForDay(i, mTempRect))
            {
                mTempRect.setEmpty();
                accessibilitynodeinfo.setContentDescription("");
                accessibilitynodeinfo.setBoundsInParent(mTempRect);
                accessibilitynodeinfo.setVisibleToUser(false);
                return;
            }
            accessibilitynodeinfo.setText(getDayText(i));
            accessibilitynodeinfo.setContentDescription(getDayDescription(i));
            accessibilitynodeinfo.setBoundsInParent(mTempRect);
            boolean flag = SimpleMonthView._2D_wrap0(SimpleMonthView.this, i);
            if(flag)
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            accessibilitynodeinfo.setEnabled(flag);
            if(i == SimpleMonthView._2D_get0(SimpleMonthView.this))
                accessibilitynodeinfo.setChecked(true);
        }

        private static final String DATE_FORMAT = "dd MMMM yyyy";
        private final Calendar mTempCalendar = Calendar.getInstance();
        private final Rect mTempRect = new Rect();
        final SimpleMonthView this$0;

        public MonthViewTouchHelper(View view)
        {
            this$0 = SimpleMonthView.this;
            super(view);
        }
    }

    public static interface OnDayClickListener
    {

        public abstract void onDayClick(SimpleMonthView simplemonthview, Calendar calendar);
    }


    static int _2D_get0(SimpleMonthView simplemonthview)
    {
        return simplemonthview.mActivatedDay;
    }

    static NumberFormat _2D_get1(SimpleMonthView simplemonthview)
    {
        return simplemonthview.mDayFormatter;
    }

    static int _2D_get2(SimpleMonthView simplemonthview)
    {
        return simplemonthview.mDaysInMonth;
    }

    static int _2D_get3(SimpleMonthView simplemonthview)
    {
        return simplemonthview.mMonth;
    }

    static int _2D_get4(SimpleMonthView simplemonthview)
    {
        return simplemonthview.mYear;
    }

    static boolean _2D_wrap0(SimpleMonthView simplemonthview, int i)
    {
        return simplemonthview.isDayEnabled(i);
    }

    static boolean _2D_wrap1(SimpleMonthView simplemonthview, int i)
    {
        return simplemonthview.isValidDayOfMonth(i);
    }

    static boolean _2D_wrap2(SimpleMonthView simplemonthview, int i)
    {
        return simplemonthview.onDayClicked(i);
    }

    static int _2D_wrap3(SimpleMonthView simplemonthview, int i, int j)
    {
        return simplemonthview.getDayAtLocation(i, j);
    }

    public SimpleMonthView(Context context)
    {
        this(context, null);
    }

    public SimpleMonthView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101035c);
    }

    public SimpleMonthView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SimpleMonthView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mMonthPaint = new TextPaint();
        mDayOfWeekPaint = new TextPaint();
        mDayPaint = new TextPaint();
        mDaySelectorPaint = new Paint();
        mDayHighlightPaint = new Paint();
        mDayHighlightSelectorPaint = new Paint();
        mDayOfWeekLabels = new String[7];
        mActivatedDay = -1;
        mToday = -1;
        mWeekStart = 1;
        mEnabledDayStart = 1;
        mEnabledDayEnd = 31;
        mHighlightedDay = -1;
        mPreviouslyHighlightedDay = -1;
        mIsTouchHighlighted = false;
        context = context.getResources();
        mDesiredMonthHeight = context.getDimensionPixelSize(0x1050054);
        mDesiredDayOfWeekHeight = context.getDimensionPixelSize(0x105004f);
        mDesiredDayHeight = context.getDimensionPixelSize(0x105004e);
        mDesiredCellWidth = context.getDimensionPixelSize(0x1050053);
        mDesiredDaySelectorRadius = context.getDimensionPixelSize(0x1050051);
        mTouchHelper = new MonthViewTouchHelper(this);
        setAccessibilityDelegate(mTouchHelper);
        setImportantForAccessibility(1);
        mLocale = context.getConfiguration().locale;
        mCalendar = Calendar.getInstance(mLocale);
        mDayFormatter = NumberFormat.getIntegerInstance(mLocale);
        updateMonthYearLabel();
        updateDayOfWeekLabels();
        initPaints(context);
    }

    private ColorStateList applyTextAppearance(Paint paint, int i)
    {
        TypedArray typedarray = mContext.obtainStyledAttributes(null, com.android.internal.R.styleable.TextAppearance, 0, i);
        Object obj = typedarray.getString(12);
        if(obj != null)
            paint.setTypeface(Typeface.create(((String) (obj)), 0));
        paint.setTextSize(typedarray.getDimensionPixelSize(0, (int)paint.getTextSize()));
        obj = typedarray.getColorStateList(3);
        if(obj != null)
            paint.setColor(((ColorStateList) (obj)).getColorForState(ENABLED_STATE_SET, 0));
        typedarray.recycle();
        return ((ColorStateList) (obj));
    }

    private void drawDays(Canvas canvas)
    {
        TextPaint textpaint = mDayPaint;
        int i = mMonthHeight;
        int k = mDayOfWeekHeight;
        int i1 = mDayHeight;
        int j1 = mCellWidth;
        float f = (textpaint.ascent() + textpaint.descent()) / 2.0F;
        int k1 = i + k + i1 / 2;
        int l1 = 1;
        int i2 = findDayOffset();
        while(l1 <= mDaysInMonth) 
        {
            int j2 = j1 * i2 + j1 / 2;
            if(isLayoutRtl())
                j2 = mPaddedWidth - j2;
            int j = 0;
            boolean flag = isDayEnabled(l1);
            if(flag)
                j = 8;
            int l;
            boolean flag1;
            boolean flag2;
            if(mActivatedDay == l1)
                flag1 = true;
            else
                flag1 = false;
            if(mHighlightedDay == l1)
                flag2 = true;
            else
                flag2 = false;
            if(flag1)
            {
                l = j | 0x20;
                Paint paint;
                if(flag2)
                    paint = mDayHighlightSelectorPaint;
                else
                    paint = mDaySelectorPaint;
                canvas.drawCircle(j2, k1, mDaySelectorRadius, paint);
            } else
            {
                l = j;
                if(flag2)
                {
                    j |= 0x10;
                    l = j;
                    if(flag)
                    {
                        canvas.drawCircle(j2, k1, mDaySelectorRadius, mDayHighlightPaint);
                        l = j;
                    }
                }
            }
            if(mToday == l1)
                j = 1;
            else
                j = 0;
            if(j != 0 && flag1 ^ true)
            {
                l = mDaySelectorPaint.getColor();
            } else
            {
                int ai[] = StateSet.get(l);
                l = mDayTextColor.getColorForState(ai, 0);
            }
            textpaint.setColor(l);
            canvas.drawText(mDayFormatter.format(l1), j2, (float)k1 - f, textpaint);
            l = ++i2;
            j = k1;
            if(i2 == 7)
            {
                l = 0;
                j = k1 + i1;
            }
            l1++;
            i2 = l;
            k1 = j;
        }
    }

    private void drawDaysOfWeek(Canvas canvas)
    {
        TextPaint textpaint = mDayOfWeekPaint;
        int i = mMonthHeight;
        int j = mDayOfWeekHeight;
        int l = mCellWidth;
        float f = (textpaint.ascent() + textpaint.descent()) / 2.0F;
        int i1 = j / 2;
        for(int k = 0; k < 7; k++)
        {
            int j1 = l * k + l / 2;
            if(isLayoutRtl())
                j1 = mPaddedWidth - j1;
            canvas.drawText(mDayOfWeekLabels[k], j1, (float)(i + i1) - f, textpaint);
        }

    }

    private void drawMonth(Canvas canvas)
    {
        float f = (float)mPaddedWidth / 2.0F;
        float f1 = mMonthPaint.ascent();
        float f2 = mMonthPaint.descent();
        f2 = ((float)mMonthHeight - (f1 + f2)) / 2.0F;
        canvas.drawText(mMonthYearLabel, f, f2, mMonthPaint);
    }

    private void ensureFocusedDay()
    {
        if(mHighlightedDay != -1)
            return;
        if(mPreviouslyHighlightedDay != -1)
        {
            mHighlightedDay = mPreviouslyHighlightedDay;
            return;
        }
        if(mActivatedDay != -1)
        {
            mHighlightedDay = mActivatedDay;
            return;
        } else
        {
            mHighlightedDay = 1;
            return;
        }
    }

    private int findClosestColumn(Rect rect)
    {
        if(rect == null)
            return 3;
        int i = MathUtils.constrain((rect.centerX() - mPaddingLeft) / mCellWidth, 0, 6);
        int j = i;
        if(isLayoutRtl())
            j = 7 - i - 1;
        return j;
    }

    private int findClosestRow(Rect rect)
    {
        if(rect == null)
            return 3;
        int i = rect.centerY();
        rect = mDayPaint;
        int j = mMonthHeight;
        int k = mDayOfWeekHeight;
        int l = mDayHeight;
        float f = (rect.ascent() + rect.descent()) / 2.0F;
        int i1 = l / 2;
        k = Math.round((float)(int)((float)i - ((float)(j + k + i1) - f)) / (float)l);
        l = findDayOffset() + mDaysInMonth;
        i1 = l / 7;
        if(l % 7 == 0)
            l = 1;
        else
            l = 0;
        return MathUtils.constrain(k, 0, i1 - l);
    }

    private int findDayOffset()
    {
        int i = mDayOfWeekStart - mWeekStart;
        if(mDayOfWeekStart < mWeekStart)
            return i + 7;
        else
            return i;
    }

    private int getDayAtLocation(int i, int j)
    {
        i -= getPaddingLeft();
        if(i < 0 || i >= mPaddedWidth)
            return -1;
        int k = mMonthHeight + mDayOfWeekHeight;
        j -= getPaddingTop();
        if(j < k || j >= mPaddedHeight)
            return -1;
        if(isLayoutRtl())
            i = mPaddedWidth - i;
        j = (j - k) / mDayHeight;
        i = ((i * 7) / mPaddedWidth + j * 7 + 1) - findDayOffset();
        if(!isValidDayOfMonth(i))
            return -1;
        else
            return i;
    }

    private static int getDaysInMonth(int i, int j)
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

    private void initPaints(Resources resources)
    {
        String s = resources.getString(0x104018d);
        String s1 = resources.getString(0x1040183);
        String s2 = resources.getString(0x1040184);
        int i = resources.getDimensionPixelSize(0x1050055);
        int j = resources.getDimensionPixelSize(0x1050050);
        int k = resources.getDimensionPixelSize(0x1050052);
        mMonthPaint.setAntiAlias(true);
        mMonthPaint.setTextSize(i);
        mMonthPaint.setTypeface(Typeface.create(s, 0));
        mMonthPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mMonthPaint.setStyle(android.graphics.Paint.Style.FILL);
        mDayOfWeekPaint.setAntiAlias(true);
        mDayOfWeekPaint.setTextSize(j);
        mDayOfWeekPaint.setTypeface(Typeface.create(s1, 0));
        mDayOfWeekPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mDayOfWeekPaint.setStyle(android.graphics.Paint.Style.FILL);
        mDaySelectorPaint.setAntiAlias(true);
        mDaySelectorPaint.setStyle(android.graphics.Paint.Style.FILL);
        mDayHighlightPaint.setAntiAlias(true);
        mDayHighlightPaint.setStyle(android.graphics.Paint.Style.FILL);
        mDayHighlightSelectorPaint.setAntiAlias(true);
        mDayHighlightSelectorPaint.setStyle(android.graphics.Paint.Style.FILL);
        mDayPaint.setAntiAlias(true);
        mDayPaint.setTextSize(k);
        mDayPaint.setTypeface(Typeface.create(s2, 0));
        mDayPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
        mDayPaint.setStyle(android.graphics.Paint.Style.FILL);
    }

    private boolean isDayEnabled(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= mEnabledDayStart)
        {
            flag1 = flag;
            if(i <= mEnabledDayEnd)
                flag1 = true;
        }
        return flag1;
    }

    private boolean isFirstDayOfWeek(int i)
    {
        boolean flag = false;
        if(((findDayOffset() + i) - 1) % 7 == 0)
            flag = true;
        return flag;
    }

    private boolean isLastDayOfWeek(int i)
    {
        boolean flag = false;
        if((findDayOffset() + i) % 7 == 0)
            flag = true;
        return flag;
    }

    private boolean isValidDayOfMonth(int i)
    {
        boolean flag = true;
        if(i < 1 || i > mDaysInMonth)
            flag = false;
        return flag;
    }

    private static boolean isValidDayOfWeek(int i)
    {
        boolean flag = true;
        if(i < 1 || i > 7)
            flag = false;
        return flag;
    }

    private static boolean isValidMonth(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= 11)
                flag1 = true;
        }
        return flag1;
    }

    private boolean moveOneDay(boolean flag)
    {
        boolean flag1;
        ensureFocusedDay();
        flag1 = false;
        if(!flag) goto _L2; else goto _L1
_L1:
        flag = flag1;
        if(!isLastDayOfWeek(mHighlightedDay))
        {
            flag = flag1;
            if(mHighlightedDay < mDaysInMonth)
            {
                mHighlightedDay = mHighlightedDay + 1;
                flag = true;
            }
        }
_L4:
        return flag;
_L2:
        flag = flag1;
        if(!isFirstDayOfWeek(mHighlightedDay))
        {
            flag = flag1;
            if(mHighlightedDay > 1)
            {
                mHighlightedDay = mHighlightedDay - 1;
                flag = true;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean onDayClicked(int i)
    {
        if(!isValidDayOfMonth(i) || isDayEnabled(i) ^ true)
            return false;
        if(mOnDayClickListener != null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(mYear, mMonth, i);
            mOnDayClickListener.onDayClick(this, calendar);
        }
        mTouchHelper.sendEventForVirtualView(i, 1);
        return true;
    }

    private boolean sameDay(int i, Calendar calendar)
    {
        boolean flag = true;
        if(mYear == calendar.get(1) && mMonth == calendar.get(2))
        {
            if(i != calendar.get(5))
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private void updateDayOfWeekLabels()
    {
        String as[] = LocaleData.get(mLocale).tinyWeekdayNames;
        for(int i = 0; i < 7; i++)
            mDayOfWeekLabels[i] = as[((mWeekStart + i) - 1) % 7 + 1];

    }

    private void updateMonthYearLabel()
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(DateFormat.getBestDateTimePattern(mLocale, "MMMMy"), mLocale);
        simpledateformat.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
        mMonthYearLabel = simpledateformat.format(mCalendar.getTime());
    }

    public boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(!mTouchHelper.dispatchHoverEvent(motionevent))
            flag = super.dispatchHoverEvent(motionevent);
        else
            flag = true;
        return flag;
    }

    public boolean getBoundsForDay(int i, Rect rect)
    {
        if(!isValidDayOfMonth(i))
            return false;
        int j = (i - 1) + findDayOffset();
        i = j % 7;
        int k = mCellWidth;
        int l;
        int i1;
        int j1;
        if(isLayoutRtl())
            i = getWidth() - getPaddingRight() - (i + 1) * k;
        else
            i = getPaddingLeft() + i * k;
        l = j / 7;
        j = mDayHeight;
        i1 = mMonthHeight;
        j1 = mDayOfWeekHeight;
        j1 = getPaddingTop() + (i1 + j1) + l * j;
        rect.set(i, j1, i + k, j1 + j);
        return true;
    }

    public int getCellWidth()
    {
        return mCellWidth;
    }

    public void getFocusedRect(Rect rect)
    {
        if(mHighlightedDay > 0)
            getBoundsForDay(mHighlightedDay, rect);
        else
            super.getFocusedRect(rect);
    }

    public int getMonthHeight()
    {
        return mMonthHeight;
    }

    public String getMonthYearLabel()
    {
        return mMonthYearLabel;
    }

    protected void onDraw(Canvas canvas)
    {
        int i = getPaddingLeft();
        int j = getPaddingTop();
        canvas.translate(i, j);
        drawMonth(canvas);
        drawDaysOfWeek(canvas);
        drawDays(canvas);
        canvas.translate(-i, -j);
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        int j = 1;
        if(!flag) goto _L2; else goto _L1
_L1:
        int j1 = findDayOffset();
        i;
        JVM INSTR lookupswitch 4: default 56
    //                   17: 108
    //                   33: 177
    //                   66: 72
    //                   130: 139;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        ensureFocusedDay();
        invalidate();
_L2:
        super.onFocusChanged(flag, i, rect);
        return;
_L6:
        int k1 = findClosestRow(rect);
        if(k1 != 0)
            j = (k1 * 7 - j1) + 1;
        mHighlightedDay = j;
        continue; /* Loop/switch isn't completed */
_L4:
        int k = findClosestRow(rect);
        mHighlightedDay = Math.min(mDaysInMonth, (k + 1) * 7 - j1);
        continue; /* Loop/switch isn't completed */
_L7:
        j1 = (findClosestColumn(rect) - j1) + 1;
        int l = j1;
        if(j1 < 1)
            l = j1 + 7;
        mHighlightedDay = l;
        continue; /* Loop/switch isn't completed */
_L5:
        j1 = (findClosestColumn(rect) - j1) + ((mDaysInMonth + j1) / 7) * 7 + 1;
        int i1 = j1;
        if(j1 > mDaysInMonth)
            i1 = j1 - 7;
        mHighlightedDay = i1;
        if(true) goto _L3; else goto _L8
_L8:
    }

    protected void onFocusLost()
    {
        if(!mIsTouchHighlighted)
        {
            mPreviouslyHighlightedDay = mHighlightedDay;
            mHighlightedDay = -1;
            invalidate();
        }
        super.onFocusLost();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = false;
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 7: default 72
    //                   19: 134
    //                   20: 177
    //                   21: 86
    //                   22: 109
    //                   23: 225
    //                   61: 247
    //                   66: 225;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L6
_L7:
        break MISSING_BLOCK_LABEL_247;
_L1:
        boolean flag1 = flag;
_L8:
        if(flag1)
        {
            invalidate();
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
_L4:
        flag1 = flag;
        if(keyevent.hasNoModifiers())
            flag1 = moveOneDay(isLayoutRtl());
          goto _L8
_L5:
        flag1 = flag;
        if(keyevent.hasNoModifiers())
            flag1 = moveOneDay(isLayoutRtl() ^ true);
          goto _L8
_L2:
        flag1 = flag;
        if(keyevent.hasNoModifiers())
        {
            ensureFocusedDay();
            flag1 = flag;
            if(mHighlightedDay > 7)
            {
                mHighlightedDay = mHighlightedDay - 7;
                flag1 = true;
            }
        }
          goto _L8
_L3:
        flag1 = flag;
        if(keyevent.hasNoModifiers())
        {
            ensureFocusedDay();
            flag1 = flag;
            if(mHighlightedDay <= mDaysInMonth - 7)
            {
                mHighlightedDay = mHighlightedDay + 7;
                flag1 = true;
            }
        }
          goto _L8
_L6:
        flag1 = flag;
        if(mHighlightedDay == -1) goto _L8; else goto _L9
_L9:
        onDayClicked(mHighlightedDay);
        return true;
        byte byte0 = 0;
        if(keyevent.hasNoModifiers())
            byte0 = 2;
        else
        if(keyevent.hasModifiers(1))
            byte0 = 1;
        flag1 = flag;
        if(byte0 != 0)
        {
            android.view.ViewParent viewparent = getParent();
            Object obj = this;
            View view;
            do
            {
                view = ((View) (obj)).focusSearch(byte0);
                if(view == null || view == this)
                    break;
                obj = view;
            } while(view.getParent() == viewparent);
            flag1 = flag;
            if(view != null)
            {
                view.requestFocus();
                return true;
            }
        }
          goto _L8
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(!flag)
            return;
        int i1 = getPaddingLeft();
        int j1 = getPaddingTop();
        int k1 = getPaddingRight();
        int l1 = getPaddingBottom();
        i = k - i - k1 - i1;
        j = l - j - l1 - j1;
        if(i == mPaddedWidth || j == mPaddedHeight)
        {
            return;
        } else
        {
            mPaddedWidth = i;
            mPaddedHeight = j;
            i = getMeasuredHeight();
            float f = (float)j / (float)(i - j1 - l1);
            j = (int)((float)mDesiredMonthHeight * f);
            i = mPaddedWidth / 7;
            mMonthHeight = j;
            mDayOfWeekHeight = (int)((float)mDesiredDayOfWeekHeight * f);
            mDayHeight = (int)((float)mDesiredDayHeight * f);
            mCellWidth = i;
            i /= 2;
            j = Math.min(i1, k1);
            k = mDayHeight / 2;
            mDaySelectorRadius = Math.min(mDesiredDaySelectorRadius, Math.min(i + j, k + l1));
            mTouchHelper.invalidateRoot();
            return;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k = mDesiredDayHeight;
        int l = mDesiredDayOfWeekHeight;
        int i1 = mDesiredMonthHeight;
        int j1 = getPaddingTop();
        int k1 = getPaddingBottom();
        setMeasuredDimension(resolveSize(mDesiredCellWidth * 7 + getPaddingStart() + getPaddingEnd(), i), resolveSize(k * 6 + l + i1 + j1 + k1, j));
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if(!isEnabled())
            return null;
        if(getDayAtLocation((int)(motionevent.getX() + 0.5F), (int)(motionevent.getY() + 0.5F)) >= 0)
            return PointerIcon.getSystemIcon(getContext(), 1002);
        else
            return super.onResolvePointerIcon(motionevent, i);
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        requestLayout();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        int j;
        int k;
        i = (int)(motionevent.getX() + 0.5F);
        j = (int)(motionevent.getY() + 0.5F);
        k = motionevent.getAction();
        k;
        JVM INSTR tableswitch 0 3: default 60
    //                   0 62
    //                   1 107
    //                   2 62
    //                   3 118;
           goto _L1 _L2 _L3 _L2 _L4
_L1:
        return true;
_L2:
        j = getDayAtLocation(i, j);
        mIsTouchHighlighted = true;
        if(mHighlightedDay != j)
        {
            mHighlightedDay = j;
            mPreviouslyHighlightedDay = j;
            invalidate();
        }
        if(k == 0 && j < 0)
            return false;
        continue; /* Loop/switch isn't completed */
_L3:
        onDayClicked(getDayAtLocation(i, j));
_L4:
        mHighlightedDay = -1;
        mIsTouchHighlighted = false;
        invalidate();
        if(true) goto _L1; else goto _L5
_L5:
    }

    void setDayHighlightColor(ColorStateList colorstatelist)
    {
        int i = colorstatelist.getColorForState(StateSet.get(24), 0);
        mDayHighlightPaint.setColor(i);
        invalidate();
    }

    public void setDayOfWeekTextAppearance(int i)
    {
        applyTextAppearance(mDayOfWeekPaint, i);
        invalidate();
    }

    void setDayOfWeekTextColor(ColorStateList colorstatelist)
    {
        int i = colorstatelist.getColorForState(ENABLED_STATE_SET, 0);
        mDayOfWeekPaint.setColor(i);
        invalidate();
    }

    void setDaySelectorColor(ColorStateList colorstatelist)
    {
        int i = colorstatelist.getColorForState(StateSet.get(40), 0);
        mDaySelectorPaint.setColor(i);
        mDayHighlightSelectorPaint.setColor(i);
        mDayHighlightSelectorPaint.setAlpha(176);
        invalidate();
    }

    public void setDayTextAppearance(int i)
    {
        ColorStateList colorstatelist = applyTextAppearance(mDayPaint, i);
        if(colorstatelist != null)
            mDayTextColor = colorstatelist;
        invalidate();
    }

    void setDayTextColor(ColorStateList colorstatelist)
    {
        mDayTextColor = colorstatelist;
        invalidate();
    }

    public void setFirstDayOfWeek(int i)
    {
        if(isValidDayOfWeek(i))
            mWeekStart = i;
        else
            mWeekStart = mCalendar.getFirstDayOfWeek();
        updateDayOfWeekLabels();
        mTouchHelper.invalidateRoot();
        invalidate();
    }

    void setMonthParams(int i, int j, int k, int l, int i1, int j1)
    {
        mActivatedDay = i;
        if(isValidMonth(j))
            mMonth = j;
        mYear = k;
        mCalendar.set(2, mMonth);
        mCalendar.set(1, mYear);
        mCalendar.set(5, 1);
        mDayOfWeekStart = mCalendar.get(7);
        Calendar calendar;
        if(isValidDayOfWeek(l))
            mWeekStart = l;
        else
            mWeekStart = mCalendar.getFirstDayOfWeek();
        calendar = Calendar.getInstance();
        mToday = -1;
        mDaysInMonth = getDaysInMonth(mMonth, mYear);
        for(i = 0; i < mDaysInMonth; i++)
        {
            j = i + 1;
            if(sameDay(j, calendar))
                mToday = j;
        }

        mEnabledDayStart = MathUtils.constrain(i1, 1, mDaysInMonth);
        mEnabledDayEnd = MathUtils.constrain(j1, mEnabledDayStart, mDaysInMonth);
        updateMonthYearLabel();
        updateDayOfWeekLabels();
        mTouchHelper.invalidateRoot();
        invalidate();
    }

    public void setMonthTextAppearance(int i)
    {
        applyTextAppearance(mMonthPaint, i);
        invalidate();
    }

    void setMonthTextColor(ColorStateList colorstatelist)
    {
        int i = colorstatelist.getColorForState(ENABLED_STATE_SET, 0);
        mMonthPaint.setColor(i);
        invalidate();
    }

    public void setOnDayClickListener(OnDayClickListener ondayclicklistener)
    {
        mOnDayClickListener = ondayclicklistener;
    }

    public void setSelectedDay(int i)
    {
        mActivatedDay = i;
        mTouchHelper.invalidateRoot();
        invalidate();
    }

    private static final int DAYS_IN_WEEK = 7;
    private static final int DEFAULT_SELECTED_DAY = -1;
    private static final int DEFAULT_WEEK_START = 1;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String MONTH_YEAR_FORMAT = "MMMMy";
    private static final int SELECTED_HIGHLIGHT_ALPHA = 176;
    private int mActivatedDay;
    private final Calendar mCalendar;
    private int mCellWidth;
    private final NumberFormat mDayFormatter;
    private int mDayHeight;
    private final Paint mDayHighlightPaint;
    private final Paint mDayHighlightSelectorPaint;
    private int mDayOfWeekHeight;
    private final String mDayOfWeekLabels[];
    private final TextPaint mDayOfWeekPaint;
    private int mDayOfWeekStart;
    private final TextPaint mDayPaint;
    private final Paint mDaySelectorPaint;
    private int mDaySelectorRadius;
    private ColorStateList mDayTextColor;
    private int mDaysInMonth;
    private final int mDesiredCellWidth;
    private final int mDesiredDayHeight;
    private final int mDesiredDayOfWeekHeight;
    private final int mDesiredDaySelectorRadius;
    private final int mDesiredMonthHeight;
    private int mEnabledDayEnd;
    private int mEnabledDayStart;
    private int mHighlightedDay;
    private boolean mIsTouchHighlighted;
    private final Locale mLocale;
    private int mMonth;
    private int mMonthHeight;
    private final TextPaint mMonthPaint;
    private String mMonthYearLabel;
    private OnDayClickListener mOnDayClickListener;
    private int mPaddedHeight;
    private int mPaddedWidth;
    private int mPreviouslyHighlightedDay;
    private int mToday;
    private final MonthViewTouchHelper mTouchHelper;
    private int mWeekStart;
    private int mYear;
}
