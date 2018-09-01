// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.ActivityThread;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.icu.util.Calendar;
import android.os.Handler;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import libcore.icu.DateUtilsBridge;

// Referenced classes of package android.widget:
//            TextView

public class DateTimeView extends TextView
{
    private static class ReceiverInfo
    {

        static final Context getApplicationContextIfAvailable(Context context)
        {
            return ActivityThread.currentApplication().getApplicationContext();
        }

        static void lambda$_2D_android_widget_DateTimeView$ReceiverInfo_16933(DateTimeView datetimeview)
        {
            datetimeview.clearFormatAndUpdate();
        }

        public void addView(DateTimeView datetimeview)
        {
            ArrayList arraylist = mAttachedViews;
            arraylist;
            JVM INSTR monitorenter ;
            boolean flag;
            flag = mAttachedViews.isEmpty();
            mAttachedViews.add(datetimeview);
            if(!flag)
                break MISSING_BLOCK_LABEL_39;
            register(getApplicationContextIfAvailable(datetimeview.getContext()));
            arraylist;
            JVM INSTR monitorexit ;
            return;
            datetimeview;
            throw datetimeview;
        }

        long getSoonestUpdateTime()
        {
            long l = 0x7fffffffffffffffL;
            ArrayList arraylist = mAttachedViews;
            arraylist;
            JVM INSTR monitorenter ;
            int i = mAttachedViews.size();
            int j = 0;
_L3:
            if(j >= i) goto _L2; else goto _L1
_L1:
            long l1 = DateTimeView._2D_get0((DateTimeView)mAttachedViews.get(j));
            long l2 = l;
            if(l1 < l)
                l2 = l1;
            j++;
            l = l2;
              goto _L3
_L2:
            return l;
            Exception exception;
            exception;
            throw exception;
        }

        void register(Context context)
        {
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.intent.action.TIME_TICK");
            intentfilter.addAction("android.intent.action.TIME_SET");
            intentfilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
            intentfilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            context.registerReceiver(mReceiver, intentfilter, null, mHandler);
        }

        public void removeView(DateTimeView datetimeview)
        {
            ArrayList arraylist = mAttachedViews;
            arraylist;
            JVM INSTR monitorenter ;
            mAttachedViews.remove(datetimeview);
            if(mAttachedViews.isEmpty())
                unregister(getApplicationContextIfAvailable(datetimeview.getContext()));
            arraylist;
            JVM INSTR monitorexit ;
            return;
            datetimeview;
            throw datetimeview;
        }

        public void setHandler(Handler handler)
        {
            mHandler = handler;
            ArrayList arraylist = mAttachedViews;
            arraylist;
            JVM INSTR monitorenter ;
            if(!mAttachedViews.isEmpty())
            {
                unregister(((DateTimeView)mAttachedViews.get(0)).getContext());
                register(((DateTimeView)mAttachedViews.get(0)).getContext());
            }
            arraylist;
            JVM INSTR monitorexit ;
            return;
            handler;
            throw handler;
        }

        void unregister(Context context)
        {
            context.unregisterReceiver(mReceiver);
        }

        void updateAll()
        {
            ArrayList arraylist = mAttachedViews;
            arraylist;
            JVM INSTR monitorenter ;
            int i = mAttachedViews.size();
            int j = 0;
_L2:
            if(j >= i)
                break; /* Loop/switch isn't completed */
            DateTimeView datetimeview = (DateTimeView)mAttachedViews.get(j);
            _.Lambda._cls2f4l12BcqlVIiuw8w0ONZMWiEpk _lcls2f4l12bcqlviiuw8w0onzmwiepk = JVM INSTR new #136 <Class _$Lambda$2f4l12BcqlVIiuw8w0ONZMWiEpk>;
            _lcls2f4l12bcqlviiuw8w0onzmwiepk._.Lambda._cls2f4l12BcqlVIiuw8w0ONZMWiEpk((byte)1, datetimeview);
            datetimeview.post(_lcls2f4l12bcqlviiuw8w0onzmwiepk);
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private final ArrayList mAttachedViews;
        private Handler mHandler;
        private final ContentObserver mObserver;
        private final BroadcastReceiver mReceiver;

        private ReceiverInfo()
        {
            mAttachedViews = new ArrayList();
            mReceiver = new _cls1();
            mObserver = new _cls2(new Handler());
            mHandler = new Handler();
        }

        ReceiverInfo(ReceiverInfo receiverinfo)
        {
            this();
        }
    }


    static long _2D_get0(DateTimeView datetimeview)
    {
        return datetimeview.mUpdateTimeMillis;
    }

    public DateTimeView(Context context)
    {
        this(context, null);
    }

    public DateTimeView(Context context, AttributeSet attributeset)
    {
        int i;
        int j;
        super(context, attributeset);
        mLastDisplay = -1;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.DateTimeView, 0, 0);
        i = context.getIndexCount();
        j = 0;
_L6:
        if(j >= i) goto _L2; else goto _L1
_L1:
        context.getIndex(j);
        JVM INSTR tableswitch 0 0: default 60
    //                   0 66;
           goto _L3 _L4
_L3:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        setShowRelativeTime(context.getBoolean(j, false));
        if(true) goto _L3; else goto _L2
_L2:
        context.recycle();
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private long computeNextMidnight(TimeZone timezone)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(DateUtilsBridge.icuTimeZone(timezone));
        calendar.add(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private static int dayDistance(TimeZone timezone, long l, long l1)
    {
        return Time.getJulianDay(l1, timezone.getOffset(l1) / 1000) - Time.getJulianDay(l, timezone.getOffset(l) / 1000);
    }

    private java.text.DateFormat getTimeFormat()
    {
        return DateFormat.getTimeFormat(getContext());
    }

    public static void setReceiverHandler(Handler handler)
    {
        ReceiverInfo receiverinfo = (ReceiverInfo)sReceiverInfo.get();
        ReceiverInfo receiverinfo1 = receiverinfo;
        if(receiverinfo == null)
        {
            receiverinfo1 = new ReceiverInfo(null);
            sReceiverInfo.set(receiverinfo1);
        }
        receiverinfo1.setHandler(handler);
    }

    private void updateNowText()
    {
        if(!mShowRelativeTime)
        {
            return;
        } else
        {
            mNowText = getContext().getResources().getString(0x10403f6);
            return;
        }
    }

    private void updateRelativeTime()
    {
        long l = System.currentTimeMillis();
        long l1 = Math.abs(l - mTimeMillis);
        boolean flag;
        if(l >= mTimeMillis)
            flag = true;
        else
            flag = false;
        if(l1 < 60000L)
        {
            setText(mNowText);
            mUpdateTimeMillis = mTimeMillis + 60000L + 1L;
            return;
        }
        Object obj;
        int j1;
        if(l1 < 0x36ee80L)
        {
            int i = (int)(l1 / 60000L);
            obj = getContext().getResources();
            if(flag)
                j1 = 0x115000e;
            else
                j1 = 0x115000f;
            obj = String.format(((Resources) (obj)).getQuantityString(j1, i), new Object[] {
                Integer.valueOf(i)
            });
            l = 60000L;
            j1 = i;
        } else
        if(l1 < 0x5265c00L)
        {
            int j = (int)(l1 / 0x36ee80L);
            obj = getContext().getResources();
            if(flag)
                j1 = 0x1150009;
            else
                j1 = 0x115000a;
            obj = String.format(((Resources) (obj)).getQuantityString(j1, j), new Object[] {
                Integer.valueOf(j)
            });
            l = 0x36ee80L;
            j1 = j;
        } else
        if(l1 < 0x7528ad000L)
        {
            TimeZone timezone = TimeZone.getDefault();
            int k = Math.max(Math.abs(dayDistance(timezone, mTimeMillis, l)), 1);
            obj = getContext().getResources();
            if(flag)
                j1 = 0x1150004;
            else
                j1 = 0x1150005;
            obj = String.format(((Resources) (obj)).getQuantityString(j1, k), new Object[] {
                Integer.valueOf(k)
            });
            if(flag || k != 1)
            {
                mUpdateTimeMillis = computeNextMidnight(timezone);
                l = -1L;
                j1 = k;
            } else
            {
                l = 0x5265c00L;
                j1 = k;
            }
        } else
        {
            int i1 = (int)(l1 / 0x7528ad000L);
            obj = getContext().getResources();
            if(flag)
                j1 = 0x1150013;
            else
                j1 = 0x1150014;
            obj = String.format(((Resources) (obj)).getQuantityString(j1, i1), new Object[] {
                Integer.valueOf(i1)
            });
            l = 0x7528ad000L;
            j1 = i1;
        }
        if(l != -1L)
            if(flag)
                mUpdateTimeMillis = mTimeMillis + (long)(j1 + 1) * l + 1L;
            else
                mUpdateTimeMillis = (mTimeMillis - (long)j1 * l) + 1L;
        setText(((CharSequence) (obj)));
    }

    void clearFormatAndUpdate()
    {
        mLastFormat = null;
        update();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        ReceiverInfo receiverinfo = (ReceiverInfo)sReceiverInfo.get();
        ReceiverInfo receiverinfo1 = receiverinfo;
        if(receiverinfo == null)
        {
            receiverinfo1 = new ReceiverInfo(null);
            sReceiverInfo.set(receiverinfo1);
        }
        receiverinfo1.addView(this);
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        updateNowText();
        update();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        ReceiverInfo receiverinfo = (ReceiverInfo)sReceiverInfo.get();
        if(receiverinfo != null)
            receiverinfo.removeView(this);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(mShowRelativeTime)
        {
            long l = System.currentTimeMillis();
            long l1 = Math.abs(l - mTimeMillis);
            int i;
            Object obj;
            if(l >= mTimeMillis)
                i = 1;
            else
                i = 0;
            if(l1 < 60000L)
                obj = mNowText;
            else
            if(l1 < 0x36ee80L)
            {
                int j = (int)(l1 / 60000L);
                obj = getContext().getResources();
                if(i != 0)
                    i = 0x115000c;
                else
                    i = 0x115000d;
                obj = String.format(((Resources) (obj)).getQuantityString(i, j), new Object[] {
                    Integer.valueOf(j)
                });
            } else
            if(l1 < 0x5265c00L)
            {
                int k = (int)(l1 / 0x36ee80L);
                obj = getContext().getResources();
                if(i != 0)
                    i = 0x1150007;
                else
                    i = 0x1150008;
                obj = String.format(((Resources) (obj)).getQuantityString(i, k), new Object[] {
                    Integer.valueOf(k)
                });
            } else
            if(l1 < 0x7528ad000L)
            {
                int i1 = Math.max(Math.abs(dayDistance(TimeZone.getDefault(), mTimeMillis, l)), 1);
                obj = getContext().getResources();
                if(i != 0)
                    i = 0x1150002;
                else
                    i = 0x1150003;
                obj = String.format(((Resources) (obj)).getQuantityString(i, i1), new Object[] {
                    Integer.valueOf(i1)
                });
            } else
            {
                int j1 = (int)(l1 / 0x7528ad000L);
                obj = getContext().getResources();
                if(i != 0)
                    i = 0x1150011;
                else
                    i = 0x1150012;
                obj = String.format(((Resources) (obj)).getQuantityString(i, j1), new Object[] {
                    Integer.valueOf(j1)
                });
            }
            accessibilitynodeinfo.setText(((CharSequence) (obj)));
        }
    }

    public void setShowRelativeTime(boolean flag)
    {
        mShowRelativeTime = flag;
        updateNowText();
        update();
    }

    public void setTime(long l)
    {
        Time time = new Time();
        time.set(l);
        mTimeMillis = time.toMillis(false);
        mTime = new Date(time.year - 1900, time.month, time.monthDay, time.hour, time.minute, 0);
        update();
    }

    public void setVisibility(int i)
    {
        boolean flag;
        if(i != 8 && getVisibility() == 8)
            flag = true;
        else
            flag = false;
        super.setVisibility(i);
        if(flag)
            update();
    }

    void update()
    {
        if(i != mLastDisplay || mLastFormat == null) goto _L2; else goto _L1
_L1:
        obj = mLastFormat;
_L3:
        setText(((java.text.DateFormat) (obj)).format(mTime));
        if(i == 0)
        {
            if(l1 > l3)
                l = l1;
            else
                l = l3;
            mUpdateTimeMillis = l;
        } else
        if(mTimeMillis < l4)
        {
            mUpdateTimeMillis = 0L;
        } else
        {
            if(l >= l2)
                l = l2;
            mUpdateTimeMillis = l;
        }
        return;
        if(mTime == null || getVisibility() == 8)
            return;
        if(mShowRelativeTime)
        {
            updateRelativeTime();
            return;
        }
        obj = mTime;
        obj = new Time();
        ((Time) (obj)).set(mTimeMillis);
        obj.second = 0;
        obj.hour = ((Time) (obj)).hour - 12;
        l = ((Time) (obj)).toMillis(false);
        obj.hour = ((Time) (obj)).hour + 12;
        long l1 = ((Time) (obj)).toMillis(false);
        obj.hour = 0;
        obj.minute = 0;
        l2 = ((Time) (obj)).toMillis(false);
        obj.monthDay = ((Time) (obj)).monthDay + 1;
        l3 = ((Time) (obj)).toMillis(false);
        ((Time) (obj)).set(System.currentTimeMillis());
        obj.second = 0;
        l4 = ((Time) (obj)).normalize(false);
        if(l4 >= l2 && l4 < l3 || l4 >= l && l4 < l1)
            i = 0;
        else
            i = 1;
        break MISSING_BLOCK_LABEL_162;
_L2:
        switch(i)
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("unknown display value: ").append(i).toString());

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_306;

        case 0: // '\0'
            obj = getTimeFormat();
            break;
        }
_L4:
        mLastFormat = ((java.text.DateFormat) (obj));
          goto _L3
        obj = java.text.DateFormat.getDateInstance(3);
          goto _L4
    }

    private static final int SHOW_MONTH_DAY_YEAR = 1;
    private static final int SHOW_TIME = 0;
    private static final ThreadLocal sReceiverInfo = new ThreadLocal();
    int mLastDisplay;
    java.text.DateFormat mLastFormat;
    private String mNowText;
    private boolean mShowRelativeTime;
    Date mTime;
    long mTimeMillis;
    private long mUpdateTimeMillis;


    // Unreferenced inner class android/widget/DateTimeView$ReceiverInfo$1

/* anonymous class */
    class ReceiverInfo._cls1 extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            if("android.intent.action.TIME_TICK".equals(intent.getAction()) && System.currentTimeMillis() < getSoonestUpdateTime())
            {
                return;
            } else
            {
                updateAll();
                return;
            }
        }

        final ReceiverInfo this$1;

            
            {
                this$1 = ReceiverInfo.this;
                super();
            }
    }


    // Unreferenced inner class android/widget/DateTimeView$ReceiverInfo$2

/* anonymous class */
    class ReceiverInfo._cls2 extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            updateAll();
        }

        final ReceiverInfo this$1;

            
            {
                this$1 = ReceiverInfo.this;
                super(handler);
            }
    }

}
