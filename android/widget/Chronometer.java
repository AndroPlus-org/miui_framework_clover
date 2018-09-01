// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.icu.text.MeasureFormat;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.net.Uri;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.*;

// Referenced classes of package android.widget:
//            TextView

public class Chronometer extends TextView
{
    public static interface OnChronometerTickListener
    {

        public abstract void onChronometerTick(Chronometer chronometer);
    }


    static boolean _2D_get0(Chronometer chronometer)
    {
        return chronometer.mRunning;
    }

    static Runnable _2D_get1(Chronometer chronometer)
    {
        return chronometer.mTickRunnable;
    }

    static void _2D_wrap0(Chronometer chronometer, long l)
    {
        chronometer.updateText(l);
    }

    public Chronometer(Context context)
    {
        this(context, null, 0);
    }

    public Chronometer(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public Chronometer(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Chronometer(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mFormatterArgs = new Object[1];
        mRecycle = new StringBuilder(8);
        mTickRunnable = new Runnable() {

            public void run()
            {
                if(Chronometer._2D_get0(Chronometer.this))
                {
                    Chronometer._2D_wrap0(Chronometer.this, SystemClock.elapsedRealtime());
                    dispatchChronometerTick();
                    postDelayed(Chronometer._2D_get1(Chronometer.this), 1000L);
                }
            }

            final Chronometer this$0;

            
            {
                this$0 = Chronometer.this;
                super();
            }
        }
;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Chronometer, i, j);
        setFormat(context.getString(0));
        setCountDown(context.getBoolean(1, false));
        context.recycle();
        init();
    }

    private static String formatDuration(long l)
    {
        int i = (int)(l / 1000L);
        int j = i;
        if(i < 0)
            j = -i;
        int k = 0;
        int i1 = 0;
        i = j;
        if(j >= 3600)
        {
            k = j / 3600;
            i = j - k * 3600;
        }
        j = i;
        if(i >= 60)
        {
            i1 = i / 60;
            j = i - i1 * 60;
        }
        ArrayList arraylist = new ArrayList();
        if(k > 0)
            arraylist.add(new Measure(Integer.valueOf(k), MeasureUnit.HOUR));
        if(i1 > 0)
            arraylist.add(new Measure(Integer.valueOf(i1), MeasureUnit.MINUTE));
        arraylist.add(new Measure(Integer.valueOf(j), MeasureUnit.SECOND));
        return MeasureFormat.getInstance(Locale.getDefault(), android.icu.text.MeasureFormat.FormatWidth.WIDE).formatMeasures((Measure[])arraylist.toArray(new Measure[arraylist.size()]));
    }

    private void init()
    {
        mBase = SystemClock.elapsedRealtime();
        updateText(mBase);
    }

    private void updateRunning()
    {
        boolean flag;
        if(mVisible && mStarted)
            flag = isShown();
        else
            flag = false;
        if(flag != mRunning)
        {
            if(flag)
            {
                updateText(SystemClock.elapsedRealtime());
                dispatchChronometerTick();
                postDelayed(mTickRunnable, 1000L);
            } else
            {
                removeCallbacks(mTickRunnable);
            }
            mRunning = flag;
        }
    }

    private void updateText(long l)
    {
        this;
        JVM INSTR monitorenter ;
        mNow = l;
        if(!mCountDown) goto _L2; else goto _L1
_L1:
        l = mBase - l;
_L3:
        long l1 = l / 1000L;
        boolean flag;
        flag = false;
        l = l1;
        if(l1 < 0L)
        {
            l = -l1;
            flag = true;
        }
        Object obj = DateUtils.formatElapsedTime(mRecycle, l);
        String s;
        s = ((String) (obj));
        if(!flag)
            break MISSING_BLOCK_LABEL_83;
        s = getResources().getString(0x10403be, new Object[] {
            obj
        });
        obj = s;
        if(mFormat == null)
            break MISSING_BLOCK_LABEL_189;
        obj = Locale.getDefault();
        if(mFormatter == null || ((Locale) (obj)).equals(mFormatterLocale) ^ true)
        {
            mFormatterLocale = ((Locale) (obj));
            Formatter formatter = JVM INSTR new #245 <Class Formatter>;
            formatter.Formatter(mFormatBuilder, ((Locale) (obj)));
            mFormatter = formatter;
        }
        mFormatBuilder.setLength(0);
        mFormatterArgs[0] = s;
        mFormatter.format(mFormat, mFormatterArgs);
        obj = mFormatBuilder.toString();
_L5:
        setText(((CharSequence) (obj)));
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        l -= mBase;
          goto _L3
        IllegalFormatException illegalformatexception;
        illegalformatexception;
        illegalformatexception = s;
        if(mLogged) goto _L5; else goto _L4
_L4:
        illegalformatexception = JVM INSTR new #73  <Class StringBuilder>;
        illegalformatexception.StringBuilder();
        Log.w("Chronometer", illegalformatexception.append("Illegal format string: ").append(mFormat).toString());
        mLogged = true;
        illegalformatexception = s;
          goto _L5
        Exception exception;
        exception;
        throw exception;
          goto _L3
    }

    void dispatchChronometerTick()
    {
        if(mOnChronometerTickListener != null)
            mOnChronometerTickListener.onChronometerTick(this);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/Chronometer.getName();
    }

    public long getBase()
    {
        return mBase;
    }

    public CharSequence getContentDescription()
    {
        return formatDuration(mNow - mBase);
    }

    public String getFormat()
    {
        return mFormat;
    }

    public OnChronometerTickListener getOnChronometerTickListener()
    {
        return mOnChronometerTickListener;
    }

    public boolean isCountDown()
    {
        return mCountDown;
    }

    public boolean isTheFinalCountDown()
    {
        try
        {
            Context context = getContext();
            Intent intent = JVM INSTR new #309 <Class Intent>;
            intent.Intent("android.intent.action.VIEW", Uri.parse("https://youtu.be/9jK-NcRmVcw"));
            context.startActivity(intent.addCategory("android.intent.category.BROWSABLE").addFlags(0x81000));
        }
        catch(Exception exception)
        {
            return false;
        }
        return true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mVisible = false;
        updateRunning();
    }

    protected void onVisibilityChanged(View view, int i)
    {
        super.onVisibilityChanged(view, i);
        updateRunning();
    }

    protected void onWindowVisibilityChanged(int i)
    {
        boolean flag = false;
        super.onWindowVisibilityChanged(i);
        if(i == 0)
            flag = true;
        mVisible = flag;
        updateRunning();
    }

    public void setBase(long l)
    {
        mBase = l;
        dispatchChronometerTick();
        updateText(SystemClock.elapsedRealtime());
    }

    public void setCountDown(boolean flag)
    {
        mCountDown = flag;
        updateText(SystemClock.elapsedRealtime());
    }

    public void setFormat(String s)
    {
        mFormat = s;
        if(s != null && mFormatBuilder == null)
            mFormatBuilder = new StringBuilder(s.length() * 2);
    }

    public void setOnChronometerTickListener(OnChronometerTickListener onchronometerticklistener)
    {
        mOnChronometerTickListener = onchronometerticklistener;
    }

    public void setStarted(boolean flag)
    {
        mStarted = flag;
        updateRunning();
    }

    public void start()
    {
        mStarted = true;
        updateRunning();
    }

    public void stop()
    {
        mStarted = false;
        updateRunning();
    }

    private static final int HOUR_IN_SEC = 3600;
    private static final int MIN_IN_SEC = 60;
    private static final String TAG = "Chronometer";
    private long mBase;
    private boolean mCountDown;
    private String mFormat;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    private Object mFormatterArgs[];
    private Locale mFormatterLocale;
    private boolean mLogged;
    private long mNow;
    private OnChronometerTickListener mOnChronometerTickListener;
    private StringBuilder mRecycle;
    private boolean mRunning;
    private boolean mStarted;
    private final Runnable mTickRunnable;
    private boolean mVisible;
}
