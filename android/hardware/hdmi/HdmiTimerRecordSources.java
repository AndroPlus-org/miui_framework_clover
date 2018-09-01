// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.util.Log;

public class HdmiTimerRecordSources
{
    public static final class Duration extends TimeUnit
    {

        private Duration(int i, int j)
        {
            super(i, j);
        }

        Duration(int i, int j, Duration duration)
        {
            this(i, j);
        }
    }

    private static class ExternalSourceDecorator extends HdmiRecordSources.RecordSource
    {

        int extraParamToByteArray(byte abyte0[], int i)
        {
            abyte0[i] = (byte)mExternalSourceSpecifier;
            mRecordSource.toByteArray(false, abyte0, i + 1);
            return getDataSize(false);
        }

        private final int mExternalSourceSpecifier;
        private final HdmiRecordSources.RecordSource mRecordSource;

        private ExternalSourceDecorator(HdmiRecordSources.RecordSource recordsource, int i)
        {
            super(recordsource.mSourceType, recordsource.getDataSize(false) + 1);
            mRecordSource = recordsource;
            mExternalSourceSpecifier = i;
        }

        ExternalSourceDecorator(HdmiRecordSources.RecordSource recordsource, int i, ExternalSourceDecorator externalsourcedecorator)
        {
            this(recordsource, i);
        }
    }

    public static final class Time extends TimeUnit
    {

        private Time(int i, int j)
        {
            super(i, j);
        }

        Time(int i, int j, Time time)
        {
            this(i, j);
        }
    }

    static class TimeUnit
    {

        static byte toBcdByte(int i)
        {
            return (byte)((i / 10) % 10 << 4 | i % 10);
        }

        int toByteArray(byte abyte0[], int i)
        {
            abyte0[i] = toBcdByte(mHour);
            abyte0[i + 1] = toBcdByte(mMinute);
            return 2;
        }

        final int mHour;
        final int mMinute;

        TimeUnit(int i, int j)
        {
            mHour = i;
            mMinute = j;
        }
    }

    public static final class TimerInfo
    {

        int getDataSize()
        {
            return 7;
        }

        int toByteArray(byte abyte0[], int i)
        {
            abyte0[i] = (byte)mDayOfMonth;
            i++;
            abyte0[i] = (byte)mMonthOfYear;
            i = ++i + mStartTime.toByteArray(abyte0, i);
            abyte0[i + mDuration.toByteArray(abyte0, i)] = (byte)mRecordingSequence;
            return getDataSize();
        }

        private static final int BASIC_INFO_SIZE = 7;
        private static final int DAY_OF_MONTH_SIZE = 1;
        private static final int DURATION_SIZE = 2;
        private static final int MONTH_OF_YEAR_SIZE = 1;
        private static final int RECORDING_SEQUENCE_SIZE = 1;
        private static final int START_TIME_SIZE = 2;
        private final int mDayOfMonth;
        private final Duration mDuration;
        private final int mMonthOfYear;
        private final int mRecordingSequence;
        private final Time mStartTime;

        private TimerInfo(int i, int j, Time time, Duration duration, int k)
        {
            mDayOfMonth = i;
            mMonthOfYear = j;
            mStartTime = time;
            mDuration = duration;
            mRecordingSequence = k;
        }

        TimerInfo(int i, int j, Time time, Duration duration, int k, TimerInfo timerinfo)
        {
            this(i, j, time, duration, k);
        }
    }

    public static final class TimerRecordSource
    {

        int getDataSize()
        {
            return mTimerInfo.getDataSize() + mRecordSource.getDataSize(false);
        }

        int toByteArray(byte abyte0[], int i)
        {
            int j = mTimerInfo.toByteArray(abyte0, i);
            mRecordSource.toByteArray(false, abyte0, i + j);
            return getDataSize();
        }

        private final HdmiRecordSources.RecordSource mRecordSource;
        private final TimerInfo mTimerInfo;

        private TimerRecordSource(TimerInfo timerinfo, HdmiRecordSources.RecordSource recordsource)
        {
            mTimerInfo = timerinfo;
            mRecordSource = recordsource;
        }

        TimerRecordSource(TimerInfo timerinfo, HdmiRecordSources.RecordSource recordsource, TimerRecordSource timerrecordsource)
        {
            this(timerinfo, recordsource);
        }
    }


    private HdmiTimerRecordSources()
    {
    }

    private static void checkDurationValue(int i, int j)
    {
        if(i < 0 || i > 99)
            throw new IllegalArgumentException((new StringBuilder()).append("Hour should be in rage of [0, 99]:").append(i).toString());
        if(j < 0 || j > 59)
            throw new IllegalArgumentException((new StringBuilder()).append("minute should be in rage of [0, 59]:").append(j).toString());
        else
            return;
    }

    private static void checkTimeValue(int i, int j)
    {
        if(i < 0 || i > 23)
            throw new IllegalArgumentException((new StringBuilder()).append("Hour should be in rage of [0, 23]:").append(i).toString());
        if(j < 0 || j > 59)
            throw new IllegalArgumentException((new StringBuilder()).append("Minute should be in rage of [0, 59]:").append(j).toString());
        else
            return;
    }

    public static boolean checkTimerRecordSource(int i, byte abyte0[])
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        int j = abyte0.length - 7;
        switch(i)
        {
        default:
            return false;

        case 1: // '\001'
            if(7 != j)
                flag3 = false;
            return flag3;

        case 2: // '\002'
            boolean flag4;
            if(4 == j)
                flag4 = flag;
            else
                flag4 = false;
            return flag4;

        case 3: // '\003'
            i = abyte0[7];
            break;
        }
        if(i == 4)
        {
            boolean flag5;
            if(2 == j)
                flag5 = flag1;
            else
                flag5 = false;
            return flag5;
        }
        if(i == 5)
        {
            boolean flag6;
            if(3 == j)
                flag6 = flag2;
            else
                flag6 = false;
            return flag6;
        } else
        {
            return false;
        }
    }

    private static void checkTimerRecordSourceInputs(TimerInfo timerinfo, HdmiRecordSources.RecordSource recordsource)
    {
        if(timerinfo == null)
        {
            Log.w("HdmiTimerRecordingSources", "TimerInfo should not be null.");
            throw new IllegalArgumentException("TimerInfo should not be null.");
        }
        if(recordsource == null)
        {
            Log.w("HdmiTimerRecordingSources", "source should not be null.");
            throw new IllegalArgumentException("source should not be null.");
        } else
        {
            return;
        }
    }

    public static Duration durationOf(int i, int j)
    {
        checkDurationValue(i, j);
        return new Duration(i, j, null);
    }

    public static TimerRecordSource ofAnalogueSource(TimerInfo timerinfo, HdmiRecordSources.AnalogueServiceSource analogueservicesource)
    {
        checkTimerRecordSourceInputs(timerinfo, analogueservicesource);
        return new TimerRecordSource(timerinfo, analogueservicesource, null);
    }

    public static TimerRecordSource ofDigitalSource(TimerInfo timerinfo, HdmiRecordSources.DigitalServiceSource digitalservicesource)
    {
        checkTimerRecordSourceInputs(timerinfo, digitalservicesource);
        return new TimerRecordSource(timerinfo, digitalservicesource, null);
    }

    public static TimerRecordSource ofExternalPhysicalAddress(TimerInfo timerinfo, HdmiRecordSources.ExternalPhysicalAddress externalphysicaladdress)
    {
        checkTimerRecordSourceInputs(timerinfo, externalphysicaladdress);
        return new TimerRecordSource(timerinfo, new ExternalSourceDecorator(externalphysicaladdress, 5, null), null);
    }

    public static TimerRecordSource ofExternalPlug(TimerInfo timerinfo, HdmiRecordSources.ExternalPlugData externalplugdata)
    {
        checkTimerRecordSourceInputs(timerinfo, externalplugdata);
        return new TimerRecordSource(timerinfo, new ExternalSourceDecorator(externalplugdata, 4, null), null);
    }

    public static Time timeOf(int i, int j)
    {
        checkTimeValue(i, j);
        return new Time(i, j, null);
    }

    public static TimerInfo timerInfoOf(int i, int j, Time time, Duration duration, int k)
    {
        if(i < 0 || i > 31)
            throw new IllegalArgumentException((new StringBuilder()).append("Day of month should be in range of [0, 31]:").append(i).toString());
        if(j < 1 || j > 12)
            throw new IllegalArgumentException((new StringBuilder()).append("Month of year should be in range of [1, 12]:").append(j).toString());
        checkTimeValue(time.mHour, time.mMinute);
        checkDurationValue(duration.mHour, duration.mMinute);
        if(k != 0 && (k & 0xffffff80) != 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid reecording sequence value:").append(k).toString());
        else
            return new TimerInfo(i, j, time, duration, k, null);
    }

    private static final int EXTERNAL_SOURCE_SPECIFIER_EXTERNAL_PHYSICAL_ADDRESS = 5;
    private static final int EXTERNAL_SOURCE_SPECIFIER_EXTERNAL_PLUG = 4;
    public static final int RECORDING_SEQUENCE_REPEAT_FRIDAY = 32;
    private static final int RECORDING_SEQUENCE_REPEAT_MASK = 127;
    public static final int RECORDING_SEQUENCE_REPEAT_MONDAY = 2;
    public static final int RECORDING_SEQUENCE_REPEAT_ONCE_ONLY = 0;
    public static final int RECORDING_SEQUENCE_REPEAT_SATUREDAY = 64;
    public static final int RECORDING_SEQUENCE_REPEAT_SUNDAY = 1;
    public static final int RECORDING_SEQUENCE_REPEAT_THURSDAY = 16;
    public static final int RECORDING_SEQUENCE_REPEAT_TUESDAY = 4;
    public static final int RECORDING_SEQUENCE_REPEAT_WEDNESDAY = 8;
    private static final String TAG = "HdmiTimerRecordingSources";
}
