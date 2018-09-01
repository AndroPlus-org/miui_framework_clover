// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.format;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.text.MeasureFormat;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import libcore.icu.*;

// Referenced classes of package android.text.format:
//            DateFormat, Time

public class DateUtils
{

    public DateUtils()
    {
    }

    public static String formatDateRange(Context context, long l, long l1, int i)
    {
        return formatDateRange(context, new Formatter(new StringBuilder(50), Locale.getDefault()), l, l1, i).toString();
    }

    public static Formatter formatDateRange(Context context, Formatter formatter, long l, long l1, int i)
    {
        return formatDateRange(context, formatter, l, l1, i, null);
    }

    public static Formatter formatDateRange(Context context, Formatter formatter, long l, long l1, int i, String s)
    {
        int j = i;
        if((i & 0xc1) == 1)
        {
            if(DateFormat.is24HourFormat(context))
                j = 128;
            else
                j = 64;
            j = i | j;
        }
        context = DateIntervalFormat.formatDateRange(l, l1, j, s);
        try
        {
            formatter.out().append(context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new AssertionError(context);
        }
        return formatter;
    }

    public static String formatDateTime(Context context, long l, int i)
    {
        return formatDateRange(context, l, l, i);
    }

    public static CharSequence formatDuration(long l)
    {
        return formatDuration(l, 10);
    }

    public static CharSequence formatDuration(long l, int i)
    {
        i;
        JVM INSTR lookupswitch 5: default 52
    //                   10: 100
    //                   20: 107
    //                   30: 107
    //                   40: 107
    //                   50: 114;
           goto _L1 _L2 _L3 _L3 _L3 _L4
_L1:
        Object obj = android.icu.text.MeasureFormat.FormatWidth.WIDE;
_L6:
        obj = MeasureFormat.getInstance(Locale.getDefault(), ((android.icu.text.MeasureFormat.FormatWidth) (obj)));
        if(l >= 0x36ee80L)
            return ((MeasureFormat) (obj)).format(new Measure(Integer.valueOf((int)((0x1b7740L + l) / 0x36ee80L)), MeasureUnit.HOUR));
        break; /* Loop/switch isn't completed */
_L2:
        obj = android.icu.text.MeasureFormat.FormatWidth.WIDE;
        continue; /* Loop/switch isn't completed */
_L3:
        obj = android.icu.text.MeasureFormat.FormatWidth.SHORT;
        continue; /* Loop/switch isn't completed */
_L4:
        obj = android.icu.text.MeasureFormat.FormatWidth.NARROW;
        if(true) goto _L6; else goto _L5
_L5:
        if(l >= 60000L)
            return ((MeasureFormat) (obj)).format(new Measure(Integer.valueOf((int)((30000L + l) / 60000L)), MeasureUnit.MINUTE));
        else
            return ((MeasureFormat) (obj)).format(new Measure(Integer.valueOf((int)((500L + l) / 1000L)), MeasureUnit.SECOND));
    }

    public static String formatElapsedTime(long l)
    {
        return formatElapsedTime(null, l);
    }

    public static String formatElapsedTime(StringBuilder stringbuilder, long l)
    {
        long l1 = 0L;
        long l2 = 0L;
        long l3 = l;
        if(l >= 3600L)
        {
            l1 = l / 3600L;
            l3 = l - 3600L * l1;
        }
        l = l3;
        if(l3 >= 60L)
        {
            l2 = l3 / 60L;
            l = l3 - 60L * l2;
        }
        StringBuilder stringbuilder1 = stringbuilder;
        if(stringbuilder == null)
            stringbuilder1 = new StringBuilder(8);
        else
            stringbuilder.setLength(0);
        stringbuilder = new Formatter(stringbuilder1, Locale.getDefault());
        initFormatStrings();
        if(l1 > 0L)
            return stringbuilder.format(sElapsedFormatHMMSS, new Object[] {
                Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(l)
            }).toString();
        else
            return stringbuilder.format(sElapsedFormatMMSS, new Object[] {
                Long.valueOf(l2), Long.valueOf(l)
            }).toString();
    }

    public static final CharSequence formatSameDayTime(long l, long l1, int i, int j)
    {
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        gregoriancalendar.setTimeInMillis(l);
        java.util.Date date = gregoriancalendar.getTime();
        Object obj = new GregorianCalendar();
        ((Calendar) (obj)).setTimeInMillis(l1);
        if(gregoriancalendar.get(1) == ((Calendar) (obj)).get(1) && gregoriancalendar.get(2) == ((Calendar) (obj)).get(2) && gregoriancalendar.get(5) == ((Calendar) (obj)).get(5))
            obj = DateFormat.getTimeInstance(j);
        else
            obj = DateFormat.getDateInstance(i);
        return ((DateFormat) (obj)).format(date);
    }

    public static String getAMPMString(int i)
    {
        return LocaleData.get(Locale.getDefault()).amPm[i + 0];
    }

    public static String getDayOfWeekString(int i, int j)
    {
        Object obj = LocaleData.get(Locale.getDefault());
        j;
        JVM INSTR lookupswitch 5: default 60
    //                   10: 69
    //                   20: 77
    //                   30: 85
    //                   40: 93
    //                   50: 101;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        obj = ((LocaleData) (obj)).shortWeekdayNames;
_L8:
        return obj[i];
_L2:
        obj = ((LocaleData) (obj)).longWeekdayNames;
        continue; /* Loop/switch isn't completed */
_L3:
        obj = ((LocaleData) (obj)).shortWeekdayNames;
        continue; /* Loop/switch isn't completed */
_L4:
        obj = ((LocaleData) (obj)).shortWeekdayNames;
        continue; /* Loop/switch isn't completed */
_L5:
        obj = ((LocaleData) (obj)).shortWeekdayNames;
        continue; /* Loop/switch isn't completed */
_L6:
        obj = ((LocaleData) (obj)).tinyWeekdayNames;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static String getMonthString(int i, int j)
    {
        Object obj = LocaleData.get(Locale.getDefault());
        j;
        JVM INSTR lookupswitch 5: default 60
    //                   10: 69
    //                   20: 77
    //                   30: 85
    //                   40: 93
    //                   50: 101;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        obj = ((LocaleData) (obj)).shortMonthNames;
_L8:
        return obj[i];
_L2:
        obj = ((LocaleData) (obj)).longMonthNames;
        continue; /* Loop/switch isn't completed */
_L3:
        obj = ((LocaleData) (obj)).shortMonthNames;
        continue; /* Loop/switch isn't completed */
_L4:
        obj = ((LocaleData) (obj)).shortMonthNames;
        continue; /* Loop/switch isn't completed */
_L5:
        obj = ((LocaleData) (obj)).shortMonthNames;
        continue; /* Loop/switch isn't completed */
_L6:
        obj = ((LocaleData) (obj)).tinyMonthNames;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static CharSequence getRelativeDateTimeString(Context context, long l, long l1, long l2, int i)
    {
        int j = i;
        if((i & 0xc1) == 1)
        {
            if(DateFormat.is24HourFormat(context))
                j = 128;
            else
                j = 64;
            j = i | j;
        }
        return RelativeDateTimeFormatter.getRelativeDateTimeString(Locale.getDefault(), TimeZone.getDefault(), l, System.currentTimeMillis(), l1, l2, j);
    }

    public static CharSequence getRelativeTimeSpanString(long l)
    {
        return getRelativeTimeSpanString(l, System.currentTimeMillis(), 60000L);
    }

    public static CharSequence getRelativeTimeSpanString(long l, long l1, long l2)
    {
        return getRelativeTimeSpanString(l, l1, l2, 0x10014);
    }

    public static CharSequence getRelativeTimeSpanString(long l, long l1, long l2, int i)
    {
        return RelativeDateTimeFormatter.getRelativeTimeSpanString(Locale.getDefault(), TimeZone.getDefault(), l, l1, l2, i);
    }

    public static CharSequence getRelativeTimeSpanString(Context context, long l)
    {
        return getRelativeTimeSpanString(context, l, false);
    }

    public static CharSequence getRelativeTimeSpanString(Context context, long l, boolean flag)
    {
        long l1 = System.currentTimeMillis();
        long l2 = Math.abs(l1 - l);
        android/text/format/DateUtils;
        JVM INSTR monitorenter ;
        if(sNowTime == null)
        {
            Time time = JVM INSTR new #377 <Class Time>;
            time.Time();
            sNowTime = time;
        }
        if(sThenTime == null)
        {
            Time time1 = JVM INSTR new #377 <Class Time>;
            time1.Time();
            sThenTime = time1;
        }
        sNowTime.set(l1);
        sThenTime.set(l);
        if(l2 >= 0x5265c00L) goto _L2; else goto _L1
_L1:
        if(sNowTime.weekDay != sThenTime.weekDay) goto _L2; else goto _L3
_L3:
        String s = formatDateRange(context, l, l, 1);
        int i = 0x1040548;
_L5:
        String s1;
        s1 = s;
        if(!flag)
            break MISSING_BLOCK_LABEL_140;
        s1 = context.getResources().getString(i, new Object[] {
            s
        });
        android/text/format/DateUtils;
        JVM INSTR monitorexit ;
        return s1;
_L2:
        if(sNowTime.year == sThenTime.year)
            break MISSING_BLOCK_LABEL_180;
        s = formatDateRange(context, l, l, 0x20014);
        i = 0x1040547;
        continue; /* Loop/switch isn't completed */
        s = formatDateRange(context, l, l, 0x10010);
        i = 0x1040547;
        if(true) goto _L5; else goto _L4
_L4:
        context;
        throw context;
    }

    private static void initFormatStrings()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        initFormatStringsLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static void initFormatStringsLocked()
    {
        Resources resources = Resources.getSystem();
        Configuration configuration = resources.getConfiguration();
        if(sLastConfig == null || sLastConfig.equals(configuration) ^ true)
        {
            sLastConfig = configuration;
            sElapsedFormatMMSS = resources.getString(0x10401cd);
            sElapsedFormatHMMSS = resources.getString(0x10401cc);
        }
    }

    public static boolean isToday(long l)
    {
        boolean flag = false;
        Time time = new Time();
        time.set(l);
        int i = time.year;
        int j = time.month;
        int k = time.monthDay;
        time.set(System.currentTimeMillis());
        boolean flag1 = flag;
        if(i == time.year)
        {
            flag1 = flag;
            if(j == time.month)
            {
                flag1 = flag;
                if(k == time.monthDay)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public static final String ABBREV_MONTH_FORMAT = "%b";
    public static final String ABBREV_WEEKDAY_FORMAT = "%a";
    public static final long DAY_IN_MILLIS = 0x5265c00L;
    public static final int FORMAT_12HOUR = 64;
    public static final int FORMAT_24HOUR = 128;
    public static final int FORMAT_ABBREV_ALL = 0x80000;
    public static final int FORMAT_ABBREV_MONTH = 0x10000;
    public static final int FORMAT_ABBREV_RELATIVE = 0x40000;
    public static final int FORMAT_ABBREV_TIME = 16384;
    public static final int FORMAT_ABBREV_WEEKDAY = 32768;
    public static final int FORMAT_CAP_AMPM = 256;
    public static final int FORMAT_CAP_MIDNIGHT = 4096;
    public static final int FORMAT_CAP_NOON = 1024;
    public static final int FORMAT_CAP_NOON_MIDNIGHT = 5120;
    public static final int FORMAT_NO_MIDNIGHT = 2048;
    public static final int FORMAT_NO_MONTH_DAY = 32;
    public static final int FORMAT_NO_NOON = 512;
    public static final int FORMAT_NO_NOON_MIDNIGHT = 2560;
    public static final int FORMAT_NO_YEAR = 8;
    public static final int FORMAT_NUMERIC_DATE = 0x20000;
    public static final int FORMAT_SHOW_DATE = 16;
    public static final int FORMAT_SHOW_TIME = 1;
    public static final int FORMAT_SHOW_WEEKDAY = 2;
    public static final int FORMAT_SHOW_YEAR = 4;
    public static final int FORMAT_UTC = 8192;
    public static final long HOUR_IN_MILLIS = 0x36ee80L;
    public static final String HOUR_MINUTE_24 = "%H:%M";
    public static final int LENGTH_LONG = 10;
    public static final int LENGTH_MEDIUM = 20;
    public static final int LENGTH_SHORT = 30;
    public static final int LENGTH_SHORTER = 40;
    public static final int LENGTH_SHORTEST = 50;
    public static final long MINUTE_IN_MILLIS = 60000L;
    public static final String MONTH_DAY_FORMAT = "%-d";
    public static final String MONTH_FORMAT = "%B";
    public static final String NUMERIC_MONTH_FORMAT = "%m";
    public static final long SECOND_IN_MILLIS = 1000L;
    public static final String WEEKDAY_FORMAT = "%A";
    public static final long WEEK_IN_MILLIS = 0x240c8400L;
    public static final String YEAR_FORMAT = "%Y";
    public static final String YEAR_FORMAT_TWO_DIGITS = "%g";
    public static final long YEAR_IN_MILLIS = 0x7528ad000L;
    private static String sElapsedFormatHMMSS;
    private static String sElapsedFormatMMSS;
    private static Configuration sLastConfig;
    private static final Object sLock = new Object();
    private static Time sNowTime;
    private static Time sThenTime;
    public static final int sameMonthTable[];
    public static final int sameYearTable[];

}
