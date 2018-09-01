// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import miui.date.Calendar;
import miui.maml.*;

// Referenced classes of package miui.maml.data:
//            NotifierVariableUpdater, IndexedVariable, VariableUpdaterManager

public class DateTimeVariableUpdater extends NotifierVariableUpdater
{
    public static final class Accuracy extends Enum
    {

        public static Accuracy valueOf(String s)
        {
            return (Accuracy)Enum.valueOf(miui/maml/data/DateTimeVariableUpdater$Accuracy, s);
        }

        public static Accuracy[] values()
        {
            return $VALUES;
        }

        private static final Accuracy $VALUES[];
        public static final Accuracy Day;
        public static final Accuracy Hour;
        public static final Accuracy Minute;
        public static final Accuracy Second;

        static 
        {
            Day = new Accuracy("Day", 0);
            Hour = new Accuracy("Hour", 1);
            Minute = new Accuracy("Minute", 2);
            Second = new Accuracy("Second", 3);
            $VALUES = (new Accuracy[] {
                Day, Hour, Minute, Second
            });
        }

        private Accuracy(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getmiui_2D_maml_2D_data_2D_DateTimeVariableUpdater$AccuracySwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_data_2D_DateTimeVariableUpdater$AccuracySwitchesValues != null)
            return _2D_miui_2D_maml_2D_data_2D_DateTimeVariableUpdater$AccuracySwitchesValues;
        int ai[] = new int[Accuracy.values().length];
        try
        {
            ai[Accuracy.Day.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Accuracy.Hour.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Accuracy.Minute.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Accuracy.Second.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_data_2D_DateTimeVariableUpdater$AccuracySwitchesValues = ai;
        return ai;
    }

    static void _2D_wrap0(DateTimeVariableUpdater datetimevariableupdater)
    {
        datetimevariableupdater.checkUpdateTime();
    }

    public DateTimeVariableUpdater(VariableUpdaterManager variableupdatermanager)
    {
        this(variableupdatermanager, Accuracy.Minute);
    }

    public DateTimeVariableUpdater(VariableUpdaterManager variableupdatermanager, String s)
    {
        super(variableupdatermanager, NotifierManager.TYPE_TIME_CHANGED);
        mCalendar = new Calendar();
        mTimeFormat = -1;
        mTimeUpdater = new Runnable() {

            public void run()
            {
                DateTimeVariableUpdater._2D_wrap0(DateTimeVariableUpdater.this);
            }

            final DateTimeVariableUpdater this$0;

            
            {
                this$0 = DateTimeVariableUpdater.this;
                super();
            }
        }
;
        variableupdatermanager = null;
        Object obj = null;
        if(!TextUtils.isEmpty(s))
        {
            Accuracy aaccuracy[] = Accuracy.values();
            int i = 0;
            int j = aaccuracy.length;
            do
            {
                variableupdatermanager = ((VariableUpdaterManager) (obj));
                if(i >= j)
                    break;
                variableupdatermanager = aaccuracy[i];
                if(variableupdatermanager.name().equals(s))
                    obj = variableupdatermanager;
                i++;
            } while(true);
        }
        obj = variableupdatermanager;
        if(variableupdatermanager == null)
        {
            obj = Accuracy.Minute;
            Log.w("DateTimeVariableUpdater", (new StringBuilder()).append("invalid accuracy tag:").append(s).toString());
        }
        initInner(((Accuracy) (obj)));
    }

    public DateTimeVariableUpdater(VariableUpdaterManager variableupdatermanager, Accuracy accuracy)
    {
        super(variableupdatermanager, NotifierManager.TYPE_TIME_CHANGED);
        mCalendar = new Calendar();
        mTimeFormat = -1;
        mTimeUpdater = new _cls1();
        initInner(accuracy);
    }

    private void checkUpdateTime()
    {
        getContext().getHandler().removeCallbacks(mTimeUpdater);
        long l = System.currentTimeMillis();
        mCalendar.setTimeInMillis(l);
        int ai[] = fields;
        int i = ai.length;
        int j = 0;
        do
        {
label0:
            {
                int k;
                if(j < i)
                {
                    k = ai[j];
                    if(k != mTimeAccuracyField)
                        break label0;
                }
                long l1;
                if(DateFormat.is24HourFormat(getContext().mContext))
                    j = 1;
                else
                    j = 0;
                l1 = mCalendar.getTimeInMillis();
                if(mCurrentTime != l1 || mTimeFormat != j)
                {
                    mCurrentTime = l1;
                    mNextUpdateTime = mCurrentTime + mTimeAccuracy;
                    mTimeFormat = j;
                    mTimeFormatVar.set(mTimeFormat);
                    getRoot().requestUpdate();
                }
                getContext().getHandler().postDelayed(mTimeUpdater, mNextUpdateTime - l);
                return;
            }
            mCalendar.set(k, 0);
            j++;
        } while(true);
    }

    public static String formatDate(CharSequence charsequence, long l)
    {
        if(sCalendar == null)
            sCalendar = new Calendar();
        sCalendar.setTimeInMillis(l);
        return sCalendar.format(charsequence);
    }

    private void initInner(Accuracy accuracy)
    {
        Log.i("DateTimeVariableUpdater", (new StringBuilder()).append("init with accuracy:").append(accuracy.name()).toString());
        _2D_getmiui_2D_maml_2D_data_2D_DateTimeVariableUpdater$AccuracySwitchesValues()[accuracy.ordinal()];
        JVM INSTR tableswitch 1 4: default 68
    //                   1 404
    //                   2 420
    //                   3 436
    //                   4 452;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        mTimeAccuracy = 60000L;
        mTimeAccuracyField = 20;
_L7:
        accuracy = getContext().mVariables;
        mYear = new IndexedVariable("year", accuracy, true);
        mMonth = new IndexedVariable("month", accuracy, true);
        mMonth1 = new IndexedVariable("month1", accuracy, true);
        mDate = new IndexedVariable("date", accuracy, true);
        mYearLunar = new IndexedVariable("year_lunar", accuracy, true);
        mYearLunar1864 = new IndexedVariable("year_lunar1864", accuracy, true);
        mMonthLunar = new IndexedVariable("month_lunar", accuracy, true);
        mMonthLunarLeap = new IndexedVariable("month_lunar_leap", accuracy, true);
        mDateLunar = new IndexedVariable("date_lunar", accuracy, true);
        mDayOfWeek = new IndexedVariable("day_of_week", accuracy, true);
        mAmPm = new IndexedVariable("ampm", accuracy, true);
        mHour12 = new IndexedVariable("hour12", accuracy, true);
        mHour24 = new IndexedVariable("hour24", accuracy, true);
        mMinute = new IndexedVariable("minute", accuracy, true);
        mSecond = new IndexedVariable("second", accuracy, true);
        mTime = new IndexedVariable("time", accuracy, true);
        mTimeSys = new IndexedVariable("time_sys", accuracy, true);
        mTimeSys.set(System.currentTimeMillis());
        mNextAlarm = new IndexedVariable("next_alarm_time", accuracy, false);
        mTimeFormatVar = new IndexedVariable("time_format", accuracy, true);
        return;
_L2:
        mTimeAccuracy = 0x5265c00L;
        mTimeAccuracyField = 9;
        continue; /* Loop/switch isn't completed */
_L3:
        mTimeAccuracy = 0x36ee80L;
        mTimeAccuracyField = 18;
        continue; /* Loop/switch isn't completed */
_L4:
        mTimeAccuracy = 60000L;
        mTimeAccuracyField = 20;
        continue; /* Loop/switch isn't completed */
_L5:
        mTimeAccuracy = 1000L;
        mTimeAccuracyField = 21;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void refreshAlarm()
    {
        String s = android.provider.Settings.System.getString(getContext().mContext.getContentResolver(), "next_alarm_formatted");
        mNextAlarm.set(s);
    }

    private void updateTime()
    {
        long l = System.currentTimeMillis();
        mTimeSys.set(l);
        long l1 = l / 1000L;
        if(l1 != mLastUpdatedTime)
        {
            mCalendar.setTimeInMillis(l);
            int i = mCalendar.get(1);
            int j = mCalendar.get(5);
            int k = mCalendar.get(9);
            mAmPm.set(mCalendar.get(17));
            mHour24.set(mCalendar.get(18));
            mHour12.set(mCalendar.get(18) % 12);
            mMinute.set(mCalendar.get(20));
            mYear.set(i);
            mMonth.set(j);
            mMonth1.set(j + 1);
            mDate.set(k);
            mDayOfWeek.set(mCalendar.get(14));
            mSecond.set(mCalendar.get(21));
            mYearLunar.set(mCalendar.get(2));
            mMonthLunar.set(mCalendar.get(6));
            mDateLunar.set(mCalendar.get(10));
            mYearLunar1864.set(mCalendar.get(4));
            mMonthLunarLeap.set(mCalendar.get(8));
            mLastUpdatedTime = l1;
        }
    }

    public void finish()
    {
        super.finish();
        mLastUpdatedTime = 0L;
        sCalendar = null;
        getContext().getHandler().removeCallbacks(mTimeUpdater);
    }

    public void init()
    {
        super.init();
        refreshAlarm();
        updateTime();
        checkUpdateTime();
    }

    public void onNotify(Context context, Intent intent, Object obj)
    {
        resetCalendar();
        checkUpdateTime();
    }

    public void pause()
    {
        super.pause();
        getContext().getHandler().removeCallbacks(mTimeUpdater);
    }

    protected void resetCalendar()
    {
        mCalendar = new Calendar();
        if(sCalendar != null)
            sCalendar = new Calendar();
    }

    public void resume()
    {
        super.resume();
        refreshAlarm();
        resetCalendar();
        checkUpdateTime();
    }

    public void tick(long l)
    {
        super.tick(l);
        mTime.set(l);
        updateTime();
    }

    private static final int _2D_miui_2D_maml_2D_data_2D_DateTimeVariableUpdater$AccuracySwitchesValues[];
    private static final String LOG_TAG = "DateTimeVariableUpdater";
    private static final int TIME_DAY = 0x5265c00;
    private static final int TIME_HOUR = 0x36ee80;
    private static final int TIME_MINUTE = 60000;
    private static final int TIME_SECOND = 1000;
    public static final String USE_TAG = "DateTime";
    private static final int fields[] = {
        22, 21, 20, 18, 9
    };
    private static Calendar sCalendar;
    private IndexedVariable mAmPm;
    protected Calendar mCalendar;
    private long mCurrentTime;
    private IndexedVariable mDate;
    private IndexedVariable mDateLunar;
    private IndexedVariable mDayOfWeek;
    private IndexedVariable mHour12;
    private IndexedVariable mHour24;
    private long mLastUpdatedTime;
    private IndexedVariable mMinute;
    private IndexedVariable mMonth;
    private IndexedVariable mMonth1;
    private IndexedVariable mMonthLunar;
    private IndexedVariable mMonthLunarLeap;
    private IndexedVariable mNextAlarm;
    private long mNextUpdateTime;
    private IndexedVariable mSecond;
    private IndexedVariable mTime;
    private long mTimeAccuracy;
    private int mTimeAccuracyField;
    private int mTimeFormat;
    private IndexedVariable mTimeFormatVar;
    private IndexedVariable mTimeSys;
    private final Runnable mTimeUpdater;
    private IndexedVariable mYear;
    private IndexedVariable mYearLunar;
    private IndexedVariable mYearLunar1864;

}
