// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.*;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;
import java.util.TimeZone;

public class AnalogClock extends View
{

    static Time _2D_set0(AnalogClock analogclock, Time time)
    {
        analogclock.mCalendar = time;
        return time;
    }

    static void _2D_wrap0(AnalogClock analogclock)
    {
        analogclock.onTimeChanged();
    }

    public AnalogClock(Context context)
    {
        this(context, null);
    }

    public AnalogClock(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public AnalogClock(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AnalogClock(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mIntentReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                if(intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED"))
                {
                    context1 = intent.getStringExtra("time-zone");
                    AnalogClock._2D_set0(AnalogClock.this, new Time(TimeZone.getTimeZone(context1).getID()));
                }
                AnalogClock._2D_wrap0(AnalogClock.this);
                invalidate();
            }

            final AnalogClock this$0;

            
            {
                this$0 = AnalogClock.this;
                super();
            }
        }
;
        context.getResources();
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AnalogClock, i, j);
        mDial = attributeset.getDrawable(0);
        if(mDial == null)
            mDial = context.getDrawable(0x108024b);
        mHourHand = attributeset.getDrawable(1);
        if(mHourHand == null)
            mHourHand = context.getDrawable(0x108024c);
        mMinuteHand = attributeset.getDrawable(2);
        if(mMinuteHand == null)
            mMinuteHand = context.getDrawable(0x108024d);
        mCalendar = new Time();
        mDialWidth = mDial.getIntrinsicWidth();
        mDialHeight = mDial.getIntrinsicHeight();
    }

    private void onTimeChanged()
    {
        mCalendar.setToNow();
        int i = mCalendar.hour;
        int j = mCalendar.minute;
        int k = mCalendar.second;
        mMinutes = (float)j + (float)k / 60F;
        mHour = (float)i + mMinutes / 60F;
        mChanged = true;
        updateContentDescription(mCalendar);
    }

    private void updateContentDescription(Time time)
    {
        setContentDescription(DateUtils.formatDateTime(mContext, time.toMillis(false), 129));
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(!mAttached)
        {
            mAttached = true;
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.intent.action.TIME_TICK");
            intentfilter.addAction("android.intent.action.TIME_SET");
            intentfilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            getContext().registerReceiverAsUser(mIntentReceiver, Process.myUserHandle(), intentfilter, null, getHandler());
        }
        mCalendar = new Time();
        onTimeChanged();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mAttached)
        {
            getContext().unregisterReceiver(mIntentReceiver);
            mAttached = false;
        }
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        boolean flag = mChanged;
        if(flag)
            mChanged = false;
        int i = mRight - mLeft;
        int l = mBottom - mTop;
        int i1 = i / 2;
        int j1 = l / 2;
        Drawable drawable = mDial;
        int k1 = drawable.getIntrinsicWidth();
        int l1 = drawable.getIntrinsicHeight();
        boolean flag1 = false;
        if(i < k1 || l < l1)
        {
            flag1 = true;
            float f = Math.min((float)i / (float)k1, (float)l / (float)l1);
            canvas.save();
            canvas.scale(f, f, i1, j1);
        }
        if(flag)
            drawable.setBounds(i1 - k1 / 2, j1 - l1 / 2, k1 / 2 + i1, l1 / 2 + j1);
        drawable.draw(canvas);
        canvas.save();
        canvas.rotate((mHour / 12F) * 360F, i1, j1);
        drawable = mHourHand;
        if(flag)
        {
            int i2 = drawable.getIntrinsicWidth();
            int j = drawable.getIntrinsicHeight();
            drawable.setBounds(i1 - i2 / 2, j1 - j / 2, i2 / 2 + i1, j / 2 + j1);
        }
        drawable.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.rotate((mMinutes / 60F) * 360F, i1, j1);
        drawable = mMinuteHand;
        if(flag)
        {
            int k = drawable.getIntrinsicWidth();
            int j2 = drawable.getIntrinsicHeight();
            drawable.setBounds(i1 - k / 2, j1 - j2 / 2, k / 2 + i1, j2 / 2 + j1);
        }
        drawable.draw(canvas);
        canvas.restore();
        if(flag1)
            canvas.restore();
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1 = android.view.View.MeasureSpec.getMode(j);
        int j1 = android.view.View.MeasureSpec.getSize(j);
        float f = 1.0F;
        float f1 = 1.0F;
        float f2 = f;
        if(k != 0)
        {
            f2 = f;
            if(l < mDialWidth)
                f2 = (float)l / (float)mDialWidth;
        }
        f = f1;
        if(i1 != 0)
        {
            f = f1;
            if(j1 < mDialHeight)
                f = (float)j1 / (float)mDialHeight;
        }
        f2 = Math.min(f2, f);
        setMeasuredDimension(resolveSizeAndState((int)((float)mDialWidth * f2), i, 0), resolveSizeAndState((int)((float)mDialHeight * f2), j, 0));
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        mChanged = true;
    }

    private boolean mAttached;
    private Time mCalendar;
    private boolean mChanged;
    private Drawable mDial;
    private int mDialHeight;
    private int mDialWidth;
    private float mHour;
    private Drawable mHourHand;
    private final BroadcastReceiver mIntentReceiver;
    private Drawable mMinuteHand;
    private float mMinutes;
}
