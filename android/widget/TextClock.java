// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.ActivityManager;
import android.content.*;
import android.content.res.*;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.*;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.ViewHierarchyEncoder;
import java.util.Calendar;
import java.util.TimeZone;
import libcore.icu.LocaleData;

// Referenced classes of package android.widget:
//            TextView

public class TextClock extends TextView
{
    private class FormatChangeObserver extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            TextClock._2D_wrap0(TextClock.this);
            TextClock._2D_wrap2(TextClock.this);
        }

        public void onChange(boolean flag, Uri uri)
        {
            TextClock._2D_wrap0(TextClock.this);
            TextClock._2D_wrap2(TextClock.this);
        }

        final TextClock this$0;

        public FormatChangeObserver(Handler handler)
        {
            this$0 = TextClock.this;
            super(handler);
        }
    }


    static Runnable _2D_get0(TextClock textclock)
    {
        return textclock.mTicker;
    }

    static String _2D_get1(TextClock textclock)
    {
        return textclock.mTimeZone;
    }

    static void _2D_wrap0(TextClock textclock)
    {
        textclock.chooseFormat();
    }

    static void _2D_wrap1(TextClock textclock, String s)
    {
        textclock.createTime(s);
    }

    static void _2D_wrap2(TextClock textclock)
    {
        textclock.onTimeChanged();
    }

    public TextClock(Context context)
    {
        super(context);
        mIntentReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                if(TextClock._2D_get1(TextClock.this) == null && "android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction()))
                {
                    context1 = intent.getStringExtra("time-zone");
                    TextClock._2D_wrap1(TextClock.this, context1);
                }
                TextClock._2D_wrap2(TextClock.this);
            }

            final TextClock this$0;

            
            {
                this$0 = TextClock.this;
                super();
            }
        }
;
        mTicker = new Runnable() {

            public void run()
            {
                TextClock._2D_wrap2(TextClock.this);
                long l = SystemClock.uptimeMillis();
                getHandler().postAtTime(TextClock._2D_get0(TextClock.this), l + (1000L - l % 1000L));
            }

            final TextClock this$0;

            
            {
                this$0 = TextClock.this;
                super();
            }
        }
;
        init();
    }

    public TextClock(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TextClock(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TextClock(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mIntentReceiver = new _cls1();
        mTicker = new _cls2();
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TextClock, i, j);
        mFormat12 = attributeset.getText(0);
        mFormat24 = attributeset.getText(1);
        mTimeZone = attributeset.getString(2);
        attributeset.recycle();
        init();
        return;
        context;
        attributeset.recycle();
        throw context;
    }

    private static CharSequence abc(CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2)
    {
        if(charsequence == null)
        {
            if(charsequence1 != null)
                charsequence2 = charsequence1;
        } else
        {
            charsequence2 = charsequence;
        }
        return charsequence2;
    }

    private void chooseFormat()
    {
        boolean flag = is24HourModeEnabled();
        LocaleData localedata = LocaleData.get(getContext().getResources().getConfiguration().locale);
        if(flag)
        {
            mFormat = abc(mFormat24, mFormat12, localedata.timeFormat_Hm);
            mDescFormat = abc(mDescFormat24, mDescFormat12, mFormat);
        } else
        {
            mFormat = abc(mFormat12, mFormat24, localedata.timeFormat_hm);
            mDescFormat = abc(mDescFormat12, mDescFormat24, mFormat);
        }
        flag = mHasSeconds;
        mHasSeconds = DateFormat.hasSeconds(mFormat);
        if(mShouldRunTicker && flag != mHasSeconds)
            if(flag)
                getHandler().removeCallbacks(mTicker);
            else
                mTicker.run();
    }

    private void createTime(String s)
    {
        if(s != null)
            mTime = Calendar.getInstance(TimeZone.getTimeZone(s));
        else
            mTime = Calendar.getInstance();
    }

    private void init()
    {
        if(mFormat12 == null || mFormat24 == null)
        {
            LocaleData localedata = LocaleData.get(getContext().getResources().getConfiguration().locale);
            if(mFormat12 == null)
                mFormat12 = localedata.timeFormat_hm;
            if(mFormat24 == null)
                mFormat24 = localedata.timeFormat_Hm;
        }
        createTime(mTimeZone);
        chooseFormat();
    }

    private void onTimeChanged()
    {
        if(mShouldRunTicker)
        {
            mTime.setTimeInMillis(System.currentTimeMillis());
            setText(DateFormat.format(mFormat, mTime));
            setContentDescription(DateFormat.format(mDescFormat, mTime));
        }
    }

    private void registerObserver()
    {
        if(mRegistered)
        {
            if(mFormatChangeObserver == null)
                mFormatChangeObserver = new FormatChangeObserver(getHandler());
            ContentResolver contentresolver = getContext().getContentResolver();
            if(mShowCurrentUserTime)
                contentresolver.registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, mFormatChangeObserver, -1);
            else
                contentresolver.registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, mFormatChangeObserver);
        }
    }

    private void registerReceiver()
    {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.TIME_TICK");
        intentfilter.addAction("android.intent.action.TIME_SET");
        intentfilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        getContext().registerReceiverAsUser(mIntentReceiver, Process.myUserHandle(), intentfilter, null, getHandler());
    }

    private void unregisterObserver()
    {
        if(mFormatChangeObserver != null)
            getContext().getContentResolver().unregisterContentObserver(mFormatChangeObserver);
    }

    private void unregisterReceiver()
    {
        getContext().unregisterReceiver(mIntentReceiver);
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        Object obj = null;
        super.encodeProperties(viewhierarchyencoder);
        Object obj1 = getFormat12Hour();
        if(obj1 == null)
            obj1 = null;
        else
            obj1 = ((CharSequence) (obj1)).toString();
        viewhierarchyencoder.addProperty("format12Hour", ((String) (obj1)));
        obj1 = getFormat24Hour();
        if(obj1 == null)
            obj1 = null;
        else
            obj1 = ((CharSequence) (obj1)).toString();
        viewhierarchyencoder.addProperty("format24Hour", ((String) (obj1)));
        if(mFormat == null)
            obj1 = obj;
        else
            obj1 = mFormat.toString();
        viewhierarchyencoder.addProperty("format", ((String) (obj1)));
        viewhierarchyencoder.addProperty("hasSeconds", mHasSeconds);
    }

    public CharSequence getFormat()
    {
        return mFormat;
    }

    public CharSequence getFormat12Hour()
    {
        return mFormat12;
    }

    public CharSequence getFormat24Hour()
    {
        return mFormat24;
    }

    public String getTimeZone()
    {
        return mTimeZone;
    }

    public boolean is24HourModeEnabled()
    {
        if(mShowCurrentUserTime)
            return DateFormat.is24HourFormat(getContext(), ActivityManager.getCurrentUser());
        else
            return DateFormat.is24HourFormat(getContext());
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(!mRegistered)
        {
            mRegistered = true;
            registerReceiver();
            registerObserver();
            createTime(mTimeZone);
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mRegistered)
        {
            unregisterReceiver();
            unregisterObserver();
            mRegistered = false;
        }
    }

    public void onVisibilityAggregated(boolean flag)
    {
        super.onVisibilityAggregated(flag);
        if(mShouldRunTicker || !flag) goto _L2; else goto _L1
_L1:
        mShouldRunTicker = true;
        if(mHasSeconds)
            mTicker.run();
        else
            onTimeChanged();
_L4:
        return;
_L2:
        if(mShouldRunTicker && flag ^ true)
        {
            mShouldRunTicker = false;
            getHandler().removeCallbacks(mTicker);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setContentDescriptionFormat12Hour(CharSequence charsequence)
    {
        mDescFormat12 = charsequence;
        chooseFormat();
        onTimeChanged();
    }

    public void setContentDescriptionFormat24Hour(CharSequence charsequence)
    {
        mDescFormat24 = charsequence;
        chooseFormat();
        onTimeChanged();
    }

    public void setFormat12Hour(CharSequence charsequence)
    {
        mFormat12 = charsequence;
        chooseFormat();
        onTimeChanged();
    }

    public void setFormat24Hour(CharSequence charsequence)
    {
        mFormat24 = charsequence;
        chooseFormat();
        onTimeChanged();
    }

    public void setShowCurrentUserTime(boolean flag)
    {
        mShowCurrentUserTime = flag;
        chooseFormat();
        onTimeChanged();
        unregisterObserver();
        registerObserver();
    }

    public void setTimeZone(String s)
    {
        mTimeZone = s;
        createTime(s);
        onTimeChanged();
    }

    public static final CharSequence DEFAULT_FORMAT_12_HOUR = "h:mm a";
    public static final CharSequence DEFAULT_FORMAT_24_HOUR = "H:mm";
    private CharSequence mDescFormat;
    private CharSequence mDescFormat12;
    private CharSequence mDescFormat24;
    private CharSequence mFormat;
    private CharSequence mFormat12;
    private CharSequence mFormat24;
    private ContentObserver mFormatChangeObserver;
    private boolean mHasSeconds;
    private final BroadcastReceiver mIntentReceiver;
    private boolean mRegistered;
    private boolean mShouldRunTicker;
    private boolean mShowCurrentUserTime;
    private final Runnable mTicker;
    private Calendar mTime;
    private String mTimeZone;

}
