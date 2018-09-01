// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.*;
import android.graphics.*;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.widget.ExploreByTouchHelper;
import java.util.Calendar;
import java.util.Locale;

public class RadialTimePickerView extends View
{
    static interface OnValueSelectedListener
    {

        public abstract void onValueSelected(int i, int j, boolean flag);
    }

    private class RadialPickerTouchHelper extends ExploreByTouchHelper
    {

        private void adjustPicker(int i)
        {
            int j;
            int k;
            int l;
            byte byte0;
            if(RadialTimePickerView._2D_get5(RadialTimePickerView.this))
            {
                j = 1;
                k = getCurrentHour();
                if(RadialTimePickerView._2D_get3(RadialTimePickerView.this))
                {
                    l = 0;
                    byte0 = 23;
                } else
                {
                    k = hour24To12(k);
                    l = 1;
                    byte0 = 12;
                }
            } else
            {
                j = 5;
                k = getCurrentMinute() / 5;
                l = 0;
                byte0 = 55;
            }
            i = MathUtils.constrain((k + i) * j, l, byte0);
            if(RadialTimePickerView._2D_get5(RadialTimePickerView.this))
                setCurrentHour(i);
            else
                setCurrentMinute(i);
        }

        private void getBoundsForVirtualView(int i, Rect rect)
        {
            int j = getTypeFromId(i);
            i = getValueFromId(i);
            float f;
            float f1;
            float f2;
            if(j == 1)
            {
                double d;
                if(RadialTimePickerView._2D_wrap0(RadialTimePickerView.this, i))
                {
                    f = RadialTimePickerView._2D_get1(RadialTimePickerView.this) - RadialTimePickerView._2D_get6(RadialTimePickerView.this)[2];
                    f1 = RadialTimePickerView._2D_get4(RadialTimePickerView.this);
                } else
                {
                    f = RadialTimePickerView._2D_get1(RadialTimePickerView.this) - RadialTimePickerView._2D_get6(RadialTimePickerView.this)[0];
                    f1 = RadialTimePickerView._2D_get4(RadialTimePickerView.this);
                }
                f2 = RadialTimePickerView._2D_wrap2(RadialTimePickerView.this, i);
            } else
            if(j == 2)
            {
                f = RadialTimePickerView._2D_get1(RadialTimePickerView.this) - RadialTimePickerView._2D_get6(RadialTimePickerView.this)[1];
                f2 = RadialTimePickerView._2D_wrap3(RadialTimePickerView.this, i);
                f1 = RadialTimePickerView._2D_get4(RadialTimePickerView.this);
            } else
            {
                f = 0.0F;
                f2 = 0.0F;
                f1 = 0.0F;
            }
            d = Math.toRadians(f2);
            f2 = (float)RadialTimePickerView._2D_get7(RadialTimePickerView.this) + (float)Math.sin(d) * f;
            f = (float)RadialTimePickerView._2D_get8(RadialTimePickerView.this) - (float)Math.cos(d) * f;
            rect.set((int)(f2 - f1), (int)(f - f1), (int)(f2 + f1), (int)(f + f1));
        }

        private int getCircularDiff(int i, int j, int k)
        {
            j = Math.abs(i - j);
            i = j;
            if(j > k / 2)
                i = k - j;
            return i;
        }

        private int getTypeFromId(int i)
        {
            return i >>> 0 & 0xf;
        }

        private int getValueFromId(int i)
        {
            return i >>> 8 & 0xff;
        }

        private CharSequence getVirtualViewDescription(int i, int j)
        {
            String s;
            if(i == 1 || i == 2)
                s = Integer.toString(j);
            else
                s = null;
            return s;
        }

        private int getVirtualViewIdAfter(int i, int j)
        {
            if(i == 1)
            {
                int k = j + 1;
                if(RadialTimePickerView._2D_get3(RadialTimePickerView.this))
                    j = 23;
                else
                    j = 12;
                if(k <= j)
                    return makeId(i, k);
            } else
            if(i == 2)
            {
                int i1 = getCurrentMinute();
                int l = (j - j % 5) + 5;
                if(j < i1 && l > i1)
                    return makeId(i, i1);
                if(l < 60)
                    return makeId(i, l);
            }
            return 0x80000000;
        }

        private int hour12To24(int i, int j)
        {
            int k = i;
            if(i != 12) goto _L2; else goto _L1
_L1:
            if(j == 0)
                k = 0;
_L4:
            return k;
_L2:
            if(j == 1)
                k = i + 12;
            if(true) goto _L4; else goto _L3
_L3:
        }

        private int hour24To12(int i)
        {
            if(i == 0)
                return 12;
            if(i > 12)
                return i - 12;
            else
                return i;
        }

        private boolean isVirtualViewSelected(int i, int j)
        {
            boolean flag;
            if(i == 1)
            {
                if(getCurrentHour() == j)
                    flag = true;
                else
                    flag = false;
            } else
            if(i == 2)
            {
                if(getCurrentMinute() == j)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        private int makeId(int i, int j)
        {
            return i << 0 | j << 8;
        }

        protected int getVirtualViewAt(float f, float f1)
        {
            int i = RadialTimePickerView._2D_wrap4(RadialTimePickerView.this, f, f1, true);
            int k;
            if(i != -1)
            {
                int j = RadialTimePickerView._2D_wrap7(i, 0) % 360;
                if(RadialTimePickerView._2D_get5(RadialTimePickerView.this))
                {
                    boolean flag = RadialTimePickerView._2D_wrap1(RadialTimePickerView.this, f, f1);
                    k = RadialTimePickerView._2D_wrap5(RadialTimePickerView.this, j, flag);
                    if(!RadialTimePickerView._2D_get3(RadialTimePickerView.this))
                        k = hour24To12(k);
                    k = makeId(1, k);
                } else
                {
                    k = getCurrentMinute();
                    i = RadialTimePickerView._2D_wrap6(RadialTimePickerView.this, i);
                    j = RadialTimePickerView._2D_wrap6(RadialTimePickerView.this, j);
                    if(getCircularDiff(k, i, 60) >= getCircularDiff(j, i, 60))
                        k = j;
                    k = makeId(2, k);
                }
            } else
            {
                k = 0x80000000;
            }
            return k;
        }

        protected void getVisibleVirtualViews(IntArray intarray)
        {
            if(RadialTimePickerView._2D_get5(RadialTimePickerView.this))
            {
                int i;
                byte byte0;
                if(RadialTimePickerView._2D_get3(RadialTimePickerView.this))
                    i = 0;
                else
                    i = 1;
                if(RadialTimePickerView._2D_get3(RadialTimePickerView.this))
                    byte0 = 23;
                else
                    byte0 = 12;
                for(; i <= byte0; i++)
                    intarray.add(makeId(1, i));

            } else
            {
                int k = getCurrentMinute();
                for(int j = 0; j < 60; j += 5)
                {
                    intarray.add(makeId(2, j));
                    if(k > j && k < j + 5)
                        intarray.add(makeId(2, k));
                }

            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
        }

        protected boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
        {
            if(j == 16)
            {
                j = getTypeFromId(i);
                i = getValueFromId(i);
                if(j == 1)
                {
                    if(!RadialTimePickerView._2D_get3(RadialTimePickerView.this))
                        i = hour12To24(i, RadialTimePickerView._2D_get0(RadialTimePickerView.this));
                    setCurrentHour(i);
                    return true;
                }
                if(j == 2)
                {
                    setCurrentMinute(i);
                    return true;
                }
            }
            return false;
        }

        protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
        {
            accessibilityevent.setClassName(getClass().getName());
            accessibilityevent.setContentDescription(getVirtualViewDescription(getTypeFromId(i), getValueFromId(i)));
        }

        protected void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            accessibilitynodeinfo.setClassName(getClass().getName());
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            int j = getTypeFromId(i);
            int k = getValueFromId(i);
            accessibilitynodeinfo.setContentDescription(getVirtualViewDescription(j, k));
            getBoundsForVirtualView(i, mTempRect);
            accessibilitynodeinfo.setBoundsInParent(mTempRect);
            accessibilitynodeinfo.setSelected(isVirtualViewSelected(j, k));
            i = getVirtualViewIdAfter(j, k);
            if(i != 0x80000000)
                accessibilitynodeinfo.setTraversalBefore(RadialTimePickerView.this, i);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            if(super.performAccessibilityAction(view, i, bundle))
                return true;
            switch(i)
            {
            default:
                return false;

            case 4096: 
                adjustPicker(1);
                return true;

            case 8192: 
                adjustPicker(-1);
                break;
            }
            return true;
        }

        private final int MASK_TYPE = 15;
        private final int MASK_VALUE = 255;
        private final int MINUTE_INCREMENT = 5;
        private final int SHIFT_TYPE = 0;
        private final int SHIFT_VALUE = 8;
        private final int TYPE_HOUR = 1;
        private final int TYPE_MINUTE = 2;
        private final Rect mTempRect = new Rect();
        final RadialTimePickerView this$0;

        public RadialPickerTouchHelper()
        {
            this$0 = RadialTimePickerView.this;
            super(RadialTimePickerView.this);
        }
    }


    static int _2D_get0(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mAmOrPm;
    }

    static int _2D_get1(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mCircleRadius;
    }

    static float _2D_get2(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mHoursToMinutes;
    }

    static boolean _2D_get3(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mIs24HourMode;
    }

    static int _2D_get4(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mSelectorRadius;
    }

    static boolean _2D_get5(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mShowHours;
    }

    static int[] _2D_get6(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mTextInset;
    }

    static int _2D_get7(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mXCenter;
    }

    static int _2D_get8(RadialTimePickerView radialtimepickerview)
    {
        return radialtimepickerview.mYCenter;
    }

    static float _2D_set0(RadialTimePickerView radialtimepickerview, float f)
    {
        radialtimepickerview.mHoursToMinutes = f;
        return f;
    }

    static boolean _2D_wrap0(RadialTimePickerView radialtimepickerview, int i)
    {
        return radialtimepickerview.getInnerCircleForHour(i);
    }

    static boolean _2D_wrap1(RadialTimePickerView radialtimepickerview, float f, float f1)
    {
        return radialtimepickerview.getInnerCircleFromXY(f, f1);
    }

    static int _2D_wrap2(RadialTimePickerView radialtimepickerview, int i)
    {
        return radialtimepickerview.getDegreesForHour(i);
    }

    static int _2D_wrap3(RadialTimePickerView radialtimepickerview, int i)
    {
        return radialtimepickerview.getDegreesForMinute(i);
    }

    static int _2D_wrap4(RadialTimePickerView radialtimepickerview, float f, float f1, boolean flag)
    {
        return radialtimepickerview.getDegreesFromXY(f, f1, flag);
    }

    static int _2D_wrap5(RadialTimePickerView radialtimepickerview, int i, boolean flag)
    {
        return radialtimepickerview.getHourForDegrees(i, flag);
    }

    static int _2D_wrap6(RadialTimePickerView radialtimepickerview, int i)
    {
        return radialtimepickerview.getMinuteForDegrees(i);
    }

    static int _2D_wrap7(int i, int j)
    {
        return snapOnly30s(i, j);
    }

    public RadialTimePickerView(Context context)
    {
        this(context, null);
    }

    public RadialTimePickerView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101049d);
    }

    public RadialTimePickerView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public RadialTimePickerView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset);
        HOURS_TO_MINUTES = new FloatProperty("hoursToMinutes") {

            public Float get(RadialTimePickerView radialtimepickerview)
            {
                return Float.valueOf(RadialTimePickerView._2D_get2(radialtimepickerview));
            }

            public volatile Object get(Object obj)
            {
                return get((RadialTimePickerView)obj);
            }

            public void setValue(RadialTimePickerView radialtimepickerview, float f)
            {
                RadialTimePickerView._2D_set0(radialtimepickerview, f);
                radialtimepickerview.invalidate();
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((RadialTimePickerView)obj, f);
            }

            final RadialTimePickerView this$0;

            
            {
                this$0 = RadialTimePickerView.this;
                super(s);
            }
        }
;
        mHours12Texts = new String[12];
        mOuterHours24Texts = new String[12];
        mInnerHours24Texts = new String[12];
        mMinutesTexts = new String[12];
        mPaint = new Paint[2];
        mPaintCenter = new Paint();
        mPaintSelector = new Paint[3];
        mPaintBackground = new Paint();
        mTextColor = new ColorStateList[3];
        mTextSize = new int[3];
        mTextInset = new int[3];
        mOuterTextX = new float[2][12];
        mOuterTextY = new float[2][12];
        mInnerTextX = new float[12];
        mInnerTextY = new float[12];
        mSelectionDegrees = new int[2];
        mSelectorPath = new Path();
        mInputEnabled = true;
        mChangedDuringTouch = false;
        applyAttributes(attributeset, i, j);
        attributeset = new TypedValue();
        context.getTheme().resolveAttribute(0x1010033, attributeset, true);
        mDisabledAlpha = attributeset.getFloat();
        mTypeface = Typeface.create("sans-serif", 0);
        mPaint[0] = new Paint();
        mPaint[0].setAntiAlias(true);
        mPaint[0].setTextAlign(android.graphics.Paint.Align.CENTER);
        mPaint[1] = new Paint();
        mPaint[1].setAntiAlias(true);
        mPaint[1].setTextAlign(android.graphics.Paint.Align.CENTER);
        mPaintCenter.setAntiAlias(true);
        mPaintSelector[0] = new Paint();
        mPaintSelector[0].setAntiAlias(true);
        mPaintSelector[1] = new Paint();
        mPaintSelector[1].setAntiAlias(true);
        mPaintSelector[2] = new Paint();
        mPaintSelector[2].setAntiAlias(true);
        mPaintSelector[2].setStrokeWidth(2.0F);
        mPaintBackground.setAntiAlias(true);
        context = getResources();
        mSelectorRadius = context.getDimensionPixelSize(0x10501a2);
        mSelectorStroke = context.getDimensionPixelSize(0x10501a3);
        mSelectorDotRadius = context.getDimensionPixelSize(0x10501a1);
        mCenterDotRadius = context.getDimensionPixelSize(0x1050199);
        mTextSize[0] = context.getDimensionPixelSize(0x10501a8);
        mTextSize[1] = context.getDimensionPixelSize(0x10501a8);
        mTextSize[2] = context.getDimensionPixelSize(0x10501a7);
        mTextInset[0] = context.getDimensionPixelSize(0x10501a6);
        mTextInset[1] = context.getDimensionPixelSize(0x10501a6);
        mTextInset[2] = context.getDimensionPixelSize(0x10501a5);
        mShowHours = true;
        mHoursToMinutes = 0.0F;
        mIs24HourMode = false;
        mAmOrPm = 0;
        mTouchHelper = new RadialPickerTouchHelper();
        setAccessibilityDelegate(mTouchHelper);
        if(getImportantForAccessibility() == 0)
            setImportantForAccessibility(1);
        initHoursAndMinutesText();
        initData();
        context = Calendar.getInstance(Locale.getDefault());
        i = context.get(11);
        j = context.get(12);
        setCurrentHourInternal(i, false, false);
        setCurrentMinuteInternal(j, false);
        setHapticFeedbackEnabled(true);
    }

    private void animatePicker(boolean flag, long l)
    {
        int i;
        float f;
        if(flag)
            i = 0;
        else
            i = 1;
        f = i;
        if(mHoursToMinutes == f)
        {
            if(mHoursToMinutesAnimator != null && mHoursToMinutesAnimator.isStarted())
            {
                mHoursToMinutesAnimator.cancel();
                mHoursToMinutesAnimator = null;
            }
            return;
        } else
        {
            mHoursToMinutesAnimator = ObjectAnimator.ofFloat(this, HOURS_TO_MINUTES, new float[] {
                f
            });
            mHoursToMinutesAnimator.setAutoCancel(true);
            mHoursToMinutesAnimator.setDuration(l);
            mHoursToMinutesAnimator.start();
            return;
        }
    }

    private static void calculatePositions(Paint paint, float f, float f1, float f2, float f3, float af[], float af1[])
    {
        paint.setTextSize(f3);
        f3 = (paint.descent() + paint.ascent()) / 2.0F;
        for(int i = 0; i < 12; i++)
        {
            af[i] = f1 - COS_30[i] * f;
            af1[i] = f2 - f3 - SIN_30[i] * f;
        }

    }

    private void calculatePositionsHours()
    {
        float f = mCircleRadius - mTextInset[0];
        calculatePositions(mPaint[0], f, mXCenter, mYCenter, mTextSize[0], mOuterTextX[0], mOuterTextY[0]);
        if(mIs24HourMode)
        {
            int i = mCircleRadius;
            int j = mTextInset[2];
            calculatePositions(mPaint[0], i - j, mXCenter, mYCenter, mTextSize[2], mInnerTextX, mInnerTextY);
        }
    }

    private void calculatePositionsMinutes()
    {
        float f = mCircleRadius - mTextInset[1];
        calculatePositions(mPaint[1], f, mXCenter, mYCenter, mTextSize[1], mOuterTextX[1], mOuterTextY[1]);
    }

    private void drawCenter(Canvas canvas, float f)
    {
        mPaintCenter.setAlpha((int)(255F * f + 0.5F));
        canvas.drawCircle(mXCenter, mYCenter, mCenterDotRadius, mPaintCenter);
    }

    private void drawCircleBackground(Canvas canvas)
    {
        canvas.drawCircle(mXCenter, mYCenter, mCircleRadius, mPaintBackground);
    }

    private void drawHours(Canvas canvas, Path path, float f)
    {
        int i = (int)((1.0F - mHoursToMinutes) * 255F * f + 0.5F);
        if(i > 0)
        {
            canvas.save(2);
            canvas.clipPath(path, android.graphics.Region.Op.DIFFERENCE);
            drawHoursClipped(canvas, i, false);
            canvas.restore();
            canvas.save(2);
            canvas.clipPath(path, android.graphics.Region.Op.INTERSECT);
            drawHoursClipped(canvas, i, true);
            canvas.restore();
        }
    }

    private void drawHoursClipped(Canvas canvas, int i, boolean flag)
    {
        float f = mTextSize[0];
        Typeface typeface = mTypeface;
        ColorStateList colorstatelist1 = mTextColor[0];
        String as[] = mOuterTextHours;
        float af1[] = mOuterTextX[0];
        float af2[] = mOuterTextY[0];
        Paint paint = mPaint[0];
        boolean flag1;
        if(flag)
            flag1 = mIsOnInnerCircle ^ true;
        else
            flag1 = false;
        drawTextElements(canvas, f, typeface, colorstatelist1, as, af1, af2, paint, i, flag1, mSelectionDegrees[0], flag);
        if(mIs24HourMode && mInnerTextHours != null)
        {
            float f1 = mTextSize[2];
            Typeface typeface1 = mTypeface;
            ColorStateList colorstatelist = mTextColor[2];
            String as1[] = mInnerTextHours;
            float af3[] = mInnerTextX;
            float af[] = mInnerTextY;
            Paint paint1 = mPaint[0];
            if(flag)
                flag1 = mIsOnInnerCircle;
            else
                flag1 = false;
            drawTextElements(canvas, f1, typeface1, colorstatelist, as1, af3, af, paint1, i, flag1, mSelectionDegrees[0], flag);
        }
    }

    private void drawMinutes(Canvas canvas, Path path, float f)
    {
        int i = (int)(mHoursToMinutes * 255F * f + 0.5F);
        if(i > 0)
        {
            canvas.save(2);
            canvas.clipPath(path, android.graphics.Region.Op.DIFFERENCE);
            drawMinutesClipped(canvas, i, false);
            canvas.restore();
            canvas.save(2);
            canvas.clipPath(path, android.graphics.Region.Op.INTERSECT);
            drawMinutesClipped(canvas, i, true);
            canvas.restore();
        }
    }

    private void drawMinutesClipped(Canvas canvas, int i, boolean flag)
    {
        drawTextElements(canvas, mTextSize[1], mTypeface, mTextColor[1], mMinutesText, mOuterTextX[1], mOuterTextY[1], mPaint[1], i, flag, mSelectionDegrees[1], flag);
    }

    private void drawSelector(Canvas canvas, Path path)
    {
        int i;
        int j;
        int k;
        float f;
        int l;
        int i1;
        float f1;
        float f2;
        double d;
        float f3;
        float f4;
        Paint paint;
        double d1;
        if(mIsOnInnerCircle)
            i = 2;
        else
            i = 0;
        j = mTextInset[i];
        k = mSelectionDegrees[i % 2];
        if(mSelectionDegrees[i % 2] % 30 != 0)
            i = 1;
        else
            i = 0;
        f = i;
        l = mTextInset[1];
        i1 = mSelectionDegrees[1];
        if(mSelectionDegrees[1] % 30 != 0)
            i = 1;
        else
            i = 0;
        f1 = i;
        i = mSelectorRadius;
        f2 = (float)mCircleRadius - MathUtils.lerp(j, l, mHoursToMinutes);
        d = Math.toRadians(MathUtils.lerpDeg(k, i1, mHoursToMinutes));
        f3 = (float)mXCenter + (float)Math.sin(d) * f2;
        f4 = (float)mYCenter - (float)Math.cos(d) * f2;
        paint = mPaintSelector[0];
        paint.setColor(mSelectorColor);
        canvas.drawCircle(f3, f4, i, paint);
        if(path != null)
        {
            path.reset();
            path.addCircle(f3, f4, i, android.graphics.Path.Direction.CCW);
        }
        f1 = MathUtils.lerp(f, f1, mHoursToMinutes);
        if(f1 > 0.0F)
        {
            path = mPaintSelector[1];
            path.setColor(mSelectorDotColor);
            canvas.drawCircle(f3, f4, (float)mSelectorDotRadius * f1, path);
        }
        d1 = Math.sin(d);
        d = Math.cos(d);
        f3 = f2 - (float)i;
        i = mXCenter;
        k = (int)((double)mCenterDotRadius * d1);
        l = mYCenter;
        j = (int)((double)mCenterDotRadius * d);
        f4 = (int)((double)f3 * d1) + (i + k);
        f3 = l - j - (int)((double)f3 * d);
        path = mPaintSelector[2];
        path.setColor(mSelectorColor);
        path.setStrokeWidth(mSelectorStroke);
        canvas.drawLine(mXCenter, mYCenter, f4, f3, path);
    }

    private void drawTextElements(Canvas canvas, float f, Typeface typeface, ColorStateList colorstatelist, String as[], float af[], float af1[], 
            Paint paint, int i, boolean flag, int j, boolean flag1)
    {
        paint.setTextSize(f);
        paint.setTypeface(typeface);
        f = (float)j / 30F;
        int k = (int)f;
        int l = (int)Math.ceil(f);
        j = 0;
        while(j < 12) 
        {
            int i1;
            if(k == j || l % 12 == j)
                i1 = 1;
            else
                i1 = 0;
            if(!flag1 || !(i1 ^ true))
            {
                if(flag && i1)
                    i1 = 32;
                else
                    i1 = 0;
                i1 = colorstatelist.getColorForState(StateSet.get(i1 | 8), 0);
                paint.setColor(i1);
                paint.setAlpha(getMultipliedAlpha(i1, i));
                canvas.drawText(as[j], af[j], af1[j], paint);
            }
            j++;
        }
    }

    private int getDegreesForHour(int i)
    {
        if(!mIs24HourMode) goto _L2; else goto _L1
_L1:
        int j;
        j = i;
        if(i >= 12)
            j = i - 12;
_L4:
        return j * 30;
_L2:
        j = i;
        if(i == 12)
            j = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int getDegreesForMinute(int i)
    {
        return i * 6;
    }

    private int getDegreesFromXY(float f, float f1, boolean flag)
    {
        int i;
        int j;
        double d;
        double d1;
        double d2;
        if(mIs24HourMode && mShowHours)
        {
            i = mMinDistForInnerNumber;
            j = mMaxDistForOuterNumber;
        } else
        {
            if(mShowHours)
                j = 0;
            else
                j = 1;
            j = mCircleRadius - mTextInset[j];
            i = j - mSelectorRadius;
            j += mSelectorRadius;
        }
        d = f - (float)mXCenter;
        d1 = f1 - (float)mYCenter;
        d2 = Math.sqrt(d * d + d1 * d1);
        if(d2 < (double)i || flag && d2 > (double)j)
            return -1;
        j = (int)(Math.toDegrees(Math.atan2(d1, d) + 1.5707963267948966D) + 0.5D);
        if(j < 0)
            return j + 360;
        else
            return j;
    }

    private int getHourForDegrees(int i, boolean flag)
    {
        int j = (i / 30) % 12;
        if(!mIs24HourMode) goto _L2; else goto _L1
_L1:
        if(flag || j != 0) goto _L4; else goto _L3
_L3:
        i = 12;
_L6:
        return i;
_L4:
        i = j;
        if(flag)
        {
            i = j;
            if(j != 0)
                i = j + 12;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        i = j;
        if(mAmOrPm == 1)
            i = j + 12;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean getInnerCircleForHour(int i)
    {
        boolean flag = true;
        if(!mIs24HourMode) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
        if(i == 0) goto _L4; else goto _L3
_L3:
        if(i <= 12) goto _L2; else goto _L5
_L5:
        flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = false;
        if(true) goto _L4; else goto _L6
_L6:
    }

    private boolean getInnerCircleFromXY(float f, float f1)
    {
        boolean flag = false;
        if(mIs24HourMode && mShowHours)
        {
            double d = f - (float)mXCenter;
            double d1 = f1 - (float)mYCenter;
            if(Math.sqrt(d * d + d1 * d1) <= (double)mHalfwayDist)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    private int getMinuteForDegrees(int i)
    {
        return i / 6;
    }

    private int getMultipliedAlpha(int i, int j)
    {
        return (int)((double)Color.alpha(i) * ((double)j / 255D) + 0.5D);
    }

    private boolean handleTouchInput(float f, float f1, boolean flag, boolean flag1)
    {
        boolean flag2 = getInnerCircleFromXY(f, f1);
        int i = getDegreesFromXY(f, f1, false);
        if(i == -1)
            return false;
        animatePicker(mShowHours, 60L);
        int j;
        int k;
        if(mShowHours)
        {
            j = snapOnly30s(i, 0) % 360;
            if(mIsOnInnerCircle == flag2)
            {
                if(mSelectionDegrees[0] != j)
                    i = 1;
                else
                    i = 0;
            } else
            {
                i = 1;
            }
            mIsOnInnerCircle = flag2;
            mSelectionDegrees[0] = j;
            k = 0;
            j = getCurrentHour();
        } else
        {
            j = snapPrefer30s(i) % 360;
            if(mSelectionDegrees[1] != j)
                i = 1;
            else
                i = 0;
            mSelectionDegrees[1] = j;
            k = 1;
            j = getCurrentMinute();
        }
        if(i != 0 || flag || flag1)
        {
            if(mListener != null)
                mListener.onValueSelected(k, j, flag1);
            if(i != 0 || flag)
            {
                performHapticFeedback(4);
                invalidate();
            }
            return true;
        } else
        {
            return false;
        }
    }

    private void initData()
    {
        if(mIs24HourMode)
        {
            mOuterTextHours = mOuterHours24Texts;
            mInnerTextHours = mInnerHours24Texts;
        } else
        {
            mOuterTextHours = mHours12Texts;
            mInnerTextHours = mHours12Texts;
        }
        mMinutesText = mMinutesTexts;
    }

    private void initHoursAndMinutesText()
    {
        for(int i = 0; i < 12; i++)
        {
            mHours12Texts[i] = String.format("%d", new Object[] {
                Integer.valueOf(HOURS_NUMBERS[i])
            });
            mInnerHours24Texts[i] = String.format("%02d", new Object[] {
                Integer.valueOf(HOURS_NUMBERS_24[i])
            });
            mOuterHours24Texts[i] = String.format("%d", new Object[] {
                Integer.valueOf(HOURS_NUMBERS[i])
            });
            mMinutesTexts[i] = String.format("%02d", new Object[] {
                Integer.valueOf(MINUTES_NUMBERS[i])
            });
        }

    }

    private static void preparePrefer30sMap()
    {
        int i = 0;
        int j = 1;
        int k = 8;
        int l = 0;
        do
        {
            while(l < 361) 
            {
                SNAP_PREFER_30S_MAP[l] = i;
                if(j == k)
                {
                    boolean flag;
                    if((i += 6) == 360)
                        j = 7;
                    else
                    if(i % 30 == 0)
                        j = 14;
                    else
                        j = 4;
                    flag = true;
                    k = j;
                    j = ((flag) ? 1 : 0);
                } else
                {
                    j++;
                }
                l++;
            }
            return;
        } while(true);
    }

    private void setCurrentHourInternal(int i, boolean flag, boolean flag1)
    {
        mSelectionDegrees[0] = (i % 12) * 30;
        int j;
        boolean flag2;
        if(i == 0 || i % 24 < 12)
            j = 0;
        else
            j = 1;
        flag2 = getInnerCircleForHour(i);
        if(mAmOrPm != j || mIsOnInnerCircle != flag2)
        {
            mAmOrPm = j;
            mIsOnInnerCircle = flag2;
            initData();
            mTouchHelper.invalidateRoot();
        }
        invalidate();
        if(flag && mListener != null)
            mListener.onValueSelected(0, i, flag1);
    }

    private void setCurrentMinuteInternal(int i, boolean flag)
    {
        mSelectionDegrees[1] = (i % 60) * 6;
        invalidate();
        if(flag && mListener != null)
            mListener.onValueSelected(1, i, false);
    }

    private void showPicker(boolean flag, boolean flag1)
    {
        if(mShowHours == flag)
            return;
        mShowHours = flag;
        if(flag1)
        {
            animatePicker(flag, 500L);
        } else
        {
            if(mHoursToMinutesAnimator != null && mHoursToMinutesAnimator.isStarted())
            {
                mHoursToMinutesAnimator.cancel();
                mHoursToMinutesAnimator = null;
            }
            float f;
            if(flag)
                f = 0.0F;
            else
                f = 1.0F;
            mHoursToMinutes = f;
        }
        initData();
        invalidate();
        mTouchHelper.invalidateRoot();
    }

    private static int snapOnly30s(int i, int j)
    {
        int k = (i / 30) * 30;
        int l = k + 30;
        if(j == 1)
            i = l;
        else
        if(j == -1)
        {
            j = k;
            if(i == k)
                j = k - 30;
            i = j;
        } else
        if(i - k < l - i)
            i = k;
        else
            i = l;
        return i;
    }

    private static int snapPrefer30s(int i)
    {
        if(SNAP_PREFER_30S_MAP == null)
            return -1;
        else
            return SNAP_PREFER_30S_MAP[i];
    }

    void applyAttributes(AttributeSet attributeset, int i, int j)
    {
        Context context = getContext();
        TypedArray typedarray = getContext().obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TimePicker, i, j);
        ColorStateList colorstatelist = typedarray.getColorStateList(3);
        ColorStateList colorstatelist1 = typedarray.getColorStateList(9);
        ColorStateList acolorstatelist1[] = mTextColor;
        attributeset = colorstatelist;
        if(colorstatelist == null)
            attributeset = ColorStateList.valueOf(-65281);
        acolorstatelist1[0] = attributeset;
        ColorStateList acolorstatelist[] = mTextColor;
        attributeset = colorstatelist1;
        if(colorstatelist1 == null)
            attributeset = ColorStateList.valueOf(-65281);
        acolorstatelist[2] = attributeset;
        mTextColor[1] = mTextColor[0];
        attributeset = typedarray.getColorStateList(5);
        if(attributeset != null)
            i = attributeset.getColorForState(StateSet.get(40), 0);
        else
            i = -65281;
        mPaintCenter.setColor(i);
        attributeset = StateSet.get(40);
        mSelectorColor = i;
        mSelectorDotColor = mTextColor[0].getColorForState(attributeset, 0);
        mPaintBackground.setColor(typedarray.getColor(4, context.getColor(0x1060163)));
        typedarray.recycle();
    }

    public boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        if(mTouchHelper.dispatchHoverEvent(motionevent))
            return true;
        else
            return super.dispatchHoverEvent(motionevent);
    }

    public int getAmOrPm()
    {
        return mAmOrPm;
    }

    public int getCurrentHour()
    {
        return getHourForDegrees(mSelectionDegrees[0], mIsOnInnerCircle);
    }

    public int getCurrentItemShowing()
    {
        int i;
        if(mShowHours)
            i = 0;
        else
            i = 1;
        return i;
    }

    public int getCurrentMinute()
    {
        return getMinuteForDegrees(mSelectionDegrees[1]);
    }

    public void initialize(int i, int j, boolean flag)
    {
        if(mIs24HourMode != flag)
        {
            mIs24HourMode = flag;
            initData();
        }
        setCurrentHourInternal(i, false, false);
        setCurrentMinuteInternal(j, false);
    }

    public void onDraw(Canvas canvas)
    {
        float f;
        Path path;
        if(mInputEnabled)
            f = 1.0F;
        else
            f = mDisabledAlpha;
        drawCircleBackground(canvas);
        path = mSelectorPath;
        drawSelector(canvas, path);
        drawHours(canvas, path, f);
        drawMinutes(canvas, path, f);
        drawCenter(canvas, f);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(!flag)
        {
            return;
        } else
        {
            mXCenter = getWidth() / 2;
            mYCenter = getHeight() / 2;
            mCircleRadius = Math.min(mXCenter, mYCenter);
            mMinDistForInnerNumber = mCircleRadius - mTextInset[2] - mSelectorRadius;
            mMaxDistForOuterNumber = (mCircleRadius - mTextInset[0]) + mSelectorRadius;
            mHalfwayDist = mCircleRadius - (mTextInset[0] + mTextInset[2]) / 2;
            calculatePositionsHours();
            calculatePositionsMinutes();
            mTouchHelper.invalidateRoot();
            return;
        }
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if(!isEnabled())
            return null;
        if(getDegreesFromXY(motionevent.getX(), motionevent.getY(), false) != -1)
            return PointerIcon.getSystemIcon(getContext(), 1002);
        else
            return super.onResolvePointerIcon(motionevent, i);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        if(!mInputEnabled)
            return true;
        i = motionevent.getActionMasked();
        break MISSING_BLOCK_LABEL_14;
        if(i == 2 || i == 1 || i == 0)
        {
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2;
            if(i == 0)
            {
                mChangedDuringTouch = false;
                flag2 = flag;
            } else
            {
                flag2 = flag;
                if(i == 1)
                {
                    boolean flag3 = true;
                    flag1 = flag3;
                    flag2 = flag;
                    if(!mChangedDuringTouch)
                    {
                        flag2 = true;
                        flag1 = flag3;
                    }
                }
            }
            mChangedDuringTouch = mChangedDuringTouch | handleTouchInput(motionevent.getX(), motionevent.getY(), flag2, flag1);
        }
        return true;
    }

    public boolean setAmOrPm(int i)
    {
        if(mAmOrPm == i || mIs24HourMode)
        {
            return false;
        } else
        {
            mAmOrPm = i;
            invalidate();
            mTouchHelper.invalidateRoot();
            return true;
        }
    }

    public void setCurrentHour(int i)
    {
        setCurrentHourInternal(i, true, false);
    }

    public void setCurrentItemShowing(int i, boolean flag)
    {
        i;
        JVM INSTR tableswitch 0 1: default 24
    //                   0 51
    //                   1 59;
           goto _L1 _L2 _L3
_L1:
        Log.e("RadialTimePickerView", (new StringBuilder()).append("ClockView does not support showing item ").append(i).toString());
_L5:
        return;
_L2:
        showHours(flag);
        continue; /* Loop/switch isn't completed */
_L3:
        showMinutes(flag);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void setCurrentMinute(int i)
    {
        setCurrentMinuteInternal(i, true);
    }

    public void setInputEnabled(boolean flag)
    {
        mInputEnabled = flag;
        invalidate();
    }

    public void setOnValueSelectedListener(OnValueSelectedListener onvalueselectedlistener)
    {
        mListener = onvalueselectedlistener;
    }

    public void showHours(boolean flag)
    {
        showPicker(true, flag);
    }

    public void showMinutes(boolean flag)
    {
        showPicker(false, flag);
    }

    private static final int AM = 0;
    private static final int ANIM_DURATION_NORMAL = 500;
    private static final int ANIM_DURATION_TOUCH = 60;
    private static final float COS_30[];
    private static final int DEGREES_FOR_ONE_HOUR = 30;
    private static final int DEGREES_FOR_ONE_MINUTE = 6;
    public static final int HOURS = 0;
    private static final int HOURS_INNER = 2;
    private static final int HOURS_IN_CIRCLE = 12;
    private static final int HOURS_NUMBERS[] = {
        12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11
    };
    private static final int HOURS_NUMBERS_24[] = {
        0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 
        22, 23
    };
    public static final int MINUTES = 1;
    private static final int MINUTES_IN_CIRCLE = 60;
    private static final int MINUTES_NUMBERS[] = {
        0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 
        50, 55
    };
    private static final int MISSING_COLOR = -65281;
    private static final int NUM_POSITIONS = 12;
    private static final int PM = 1;
    private static final int SELECTOR_CIRCLE = 0;
    private static final int SELECTOR_DOT = 1;
    private static final int SELECTOR_LINE = 2;
    private static final float SIN_30[];
    private static final int SNAP_PREFER_30S_MAP[] = new int[361];
    private static final String TAG = "RadialTimePickerView";
    private final FloatProperty HOURS_TO_MINUTES;
    private int mAmOrPm;
    private int mCenterDotRadius;
    boolean mChangedDuringTouch;
    private int mCircleRadius;
    private float mDisabledAlpha;
    private int mHalfwayDist;
    private final String mHours12Texts[];
    private float mHoursToMinutes;
    private ObjectAnimator mHoursToMinutesAnimator;
    private final String mInnerHours24Texts[];
    private String mInnerTextHours[];
    private final float mInnerTextX[];
    private final float mInnerTextY[];
    private boolean mInputEnabled;
    private boolean mIs24HourMode;
    private boolean mIsOnInnerCircle;
    private OnValueSelectedListener mListener;
    private int mMaxDistForOuterNumber;
    private int mMinDistForInnerNumber;
    private String mMinutesText[];
    private final String mMinutesTexts[];
    private final String mOuterHours24Texts[];
    private String mOuterTextHours[];
    private final float mOuterTextX[][];
    private final float mOuterTextY[][];
    private final Paint mPaint[];
    private final Paint mPaintBackground;
    private final Paint mPaintCenter;
    private final Paint mPaintSelector[];
    private final int mSelectionDegrees[];
    private int mSelectorColor;
    private int mSelectorDotColor;
    private int mSelectorDotRadius;
    private final Path mSelectorPath;
    private int mSelectorRadius;
    private int mSelectorStroke;
    private boolean mShowHours;
    private final ColorStateList mTextColor[];
    private final int mTextInset[];
    private final int mTextSize[];
    private final RadialPickerTouchHelper mTouchHelper;
    private final Typeface mTypeface;
    private int mXCenter;
    private int mYCenter;

    static 
    {
        COS_30 = new float[12];
        SIN_30 = new float[12];
        preparePrefer30sMap();
        double d = 1.5707963267948966D;
        for(int i = 0; i < 12; i++)
        {
            COS_30[i] = (float)Math.cos(d);
            SIN_30[i] = (float)Math.sin(d);
            d += 0.52359877559829882D;
        }

    }
}
