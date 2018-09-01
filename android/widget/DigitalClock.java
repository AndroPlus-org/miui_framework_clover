// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import java.util.Calendar;

// Referenced classes of package android.widget:
//            TextView

public class DigitalClock extends TextView
{
    private class FormatChangeObserver extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            DigitalClock._2D_wrap0(DigitalClock.this);
        }

        final DigitalClock this$0;

        public FormatChangeObserver()
        {
            this$0 = DigitalClock.this;
            super(new Handler());
        }
    }


    static Handler _2D_get0(DigitalClock digitalclock)
    {
        return digitalclock.mHandler;
    }

    static Runnable _2D_get1(DigitalClock digitalclock)
    {
        return digitalclock.mTicker;
    }

    static boolean _2D_get2(DigitalClock digitalclock)
    {
        return digitalclock.mTickerStopped;
    }

    static void _2D_wrap0(DigitalClock digitalclock)
    {
        digitalclock.setFormat();
    }

    public DigitalClock(Context context)
    {
        super(context);
        mTickerStopped = false;
        initClock();
    }

    public DigitalClock(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTickerStopped = false;
        initClock();
    }

    private void initClock()
    {
        if(mCalendar == null)
            mCalendar = Calendar.getInstance();
    }

    private void setFormat()
    {
        mFormat = DateFormat.getTimeFormatString(getContext());
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/DigitalClock.getName();
    }

    protected void onAttachedToWindow()
    {
        mTickerStopped = false;
        super.onAttachedToWindow();
        mFormatChangeObserver = new FormatChangeObserver();
        getContext().getContentResolver().registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, mFormatChangeObserver);
        setFormat();
        mHandler = new Handler();
        mTicker = new Runnable() {

            public void run()
            {
                if(DigitalClock._2D_get2(DigitalClock.this))
                {
                    return;
                } else
                {
                    mCalendar.setTimeInMillis(System.currentTimeMillis());
                    setText(DateFormat.format(mFormat, mCalendar));
                    invalidate();
                    long l = SystemClock.uptimeMillis();
                    DigitalClock._2D_get0(DigitalClock.this).postAtTime(DigitalClock._2D_get1(DigitalClock.this), l + (1000L - l % 1000L));
                    return;
                }
            }

            final DigitalClock this$0;

            
            {
                this$0 = DigitalClock.this;
                super();
            }
        }
;
        mTicker.run();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mTickerStopped = true;
        getContext().getContentResolver().unregisterContentObserver(mFormatChangeObserver);
    }

    Calendar mCalendar;
    String mFormat;
    private FormatChangeObserver mFormatChangeObserver;
    private Handler mHandler;
    private Runnable mTicker;
    private boolean mTickerStopped;
}
