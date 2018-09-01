// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.format;

import android.util.TimeFormatException;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;
import libcore.util.ZoneInfo;
import libcore.util.ZoneInfoDB;

// Referenced classes of package android.text.format:
//            TimeFormatter

public class Time
{
    private static class TimeCalculator
    {

        public static int compare(TimeCalculator timecalculator, TimeCalculator timecalculator1)
        {
            int i;
            long l;
            i = 0;
            if(timecalculator.timezone.equals(timecalculator1.timezone))
            {
                i = timecalculator.wallTime.getYear() - timecalculator1.wallTime.getYear();
                if(i != 0)
                    return i;
                i = timecalculator.wallTime.getMonth() - timecalculator1.wallTime.getMonth();
                if(i != 0)
                    return i;
                i = timecalculator.wallTime.getMonthDay() - timecalculator1.wallTime.getMonthDay();
                if(i != 0)
                    return i;
                i = timecalculator.wallTime.getHour() - timecalculator1.wallTime.getHour();
                if(i != 0)
                    return i;
                i = timecalculator.wallTime.getMinute() - timecalculator1.wallTime.getMinute();
                if(i != 0)
                    return i;
                i = timecalculator.wallTime.getSecond() - timecalculator1.wallTime.getSecond();
                if(i != 0)
                    return i;
                else
                    return 0;
            }
            l = timecalculator.toMillis(false) - timecalculator1.toMillis(false);
            if(l >= 0L) goto _L2; else goto _L1
_L1:
            i = -1;
_L4:
            return i;
_L2:
            if(l > 0L)
                i = 1;
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static ZoneInfo lookupZoneInfo(String s)
        {
            Object obj1;
            Object obj;
            try
            {
                obj = ZoneInfoDB.getInstance().makeTimeZone(s);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1)
            {
                throw new AssertionError((new StringBuilder()).append("Error loading timezone: \"").append(s).append("\"").toString(), ((Throwable) (obj1)));
            }
            obj1 = obj;
            if(obj != null)
                break MISSING_BLOCK_LABEL_23;
            obj1 = ZoneInfoDB.getInstance().makeTimeZone("GMT");
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_97;
            obj1 = JVM INSTR new #80  <Class AssertionError>;
            obj = JVM INSTR new #82  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((AssertionError) (obj1)).AssertionError(((StringBuilder) (obj)).append("GMT not found: \"").append(s).append("\"").toString());
            throw obj1;
            return ((ZoneInfo) (obj1));
        }

        private char toChar(int i)
        {
            char c;
            if(i >= 0 && i <= 9)
            {
                i = (char)(i + 48);
                c = i;
            } else
            {
                i = 32;
                c = i;
            }
            return c;
        }

        private void updateZoneInfoFromTimeZone()
        {
            if(!zoneInfo.getID().equals(timezone))
                zoneInfo = lookupZoneInfo(timezone);
        }

        public void copyFieldsFromTime(Time time)
        {
            wallTime.setSecond(time.second);
            wallTime.setMinute(time.minute);
            wallTime.setHour(time.hour);
            wallTime.setMonthDay(time.monthDay);
            wallTime.setMonth(time.month);
            wallTime.setYear(time.year);
            wallTime.setWeekDay(time.weekDay);
            wallTime.setYearDay(time.yearDay);
            wallTime.setIsDst(time.isDst);
            wallTime.setGmtOffset((int)time.gmtoff);
            while(time.allDay && (time.second != 0 || time.minute != 0 || time.hour != 0)) 
                throw new IllegalArgumentException("allDay is true but sec, min, hour are not 0.");
            timezone = time.timezone;
            updateZoneInfoFromTimeZone();
        }

        public void copyFieldsToTime(Time time)
        {
            time.second = wallTime.getSecond();
            time.minute = wallTime.getMinute();
            time.hour = wallTime.getHour();
            time.monthDay = wallTime.getMonthDay();
            time.month = wallTime.getMonth();
            time.year = wallTime.getYear();
            time.weekDay = wallTime.getWeekDay();
            time.yearDay = wallTime.getYearDay();
            time.isDst = wallTime.getIsDst();
            time.gmtoff = wallTime.getGmtOffset();
        }

        public String format(String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "%c";
            return (new TimeFormatter()).format(s1, wallTime, zoneInfo);
        }

        public String format2445(boolean flag)
        {
            int i;
            char ac[];
            if(flag)
                i = 16;
            else
                i = 8;
            ac = new char[i];
            i = wallTime.getYear();
            ac[0] = toChar(i / 1000);
            i %= 1000;
            ac[1] = toChar(i / 100);
            i %= 100;
            ac[2] = toChar(i / 10);
            ac[3] = toChar(i % 10);
            i = wallTime.getMonth() + 1;
            ac[4] = toChar(i / 10);
            ac[5] = toChar(i % 10);
            i = wallTime.getMonthDay();
            ac[6] = toChar(i / 10);
            ac[7] = toChar(i % 10);
            if(!flag)
                return new String(ac, 0, 8);
            ac[8] = (char)84;
            i = wallTime.getHour();
            ac[9] = toChar(i / 10);
            ac[10] = toChar(i % 10);
            i = wallTime.getMinute();
            ac[11] = toChar(i / 10);
            ac[12] = toChar(i % 10);
            i = wallTime.getSecond();
            ac[13] = toChar(i / 10);
            ac[14] = toChar(i % 10);
            if("UTC".equals(timezone))
            {
                ac[15] = (char)90;
                return new String(ac, 0, 16);
            } else
            {
                return new String(ac, 0, 15);
            }
        }

        public void setTimeInMillis(long l)
        {
            int i = (int)(l / 1000L);
            updateZoneInfoFromTimeZone();
            wallTime.localtime(i, zoneInfo);
        }

        public void switchTimeZone(String s)
        {
            int i = wallTime.mktime(zoneInfo);
            timezone = s;
            updateZoneInfoFromTimeZone();
            wallTime.localtime(i, zoneInfo);
        }

        public long toMillis(boolean flag)
        {
            if(flag)
                wallTime.setIsDst(-1);
            int i = wallTime.mktime(zoneInfo);
            if(i == -1)
                return -1L;
            else
                return (long)i * 1000L;
        }

        public String toStringInternal()
        {
            return String.format("%04d%02d%02dT%02d%02d%02d%s(%d,%d,%d,%d,%d)", new Object[] {
                Integer.valueOf(wallTime.getYear()), Integer.valueOf(wallTime.getMonth() + 1), Integer.valueOf(wallTime.getMonthDay()), Integer.valueOf(wallTime.getHour()), Integer.valueOf(wallTime.getMinute()), Integer.valueOf(wallTime.getSecond()), timezone, Integer.valueOf(wallTime.getWeekDay()), Integer.valueOf(wallTime.getYearDay()), Integer.valueOf(wallTime.getGmtOffset()), 
                Integer.valueOf(wallTime.getIsDst()), Long.valueOf(toMillis(false) / 1000L)
            });
        }

        public String timezone;
        public final libcore.util.ZoneInfo.WallTime wallTime = new libcore.util.ZoneInfo.WallTime();
        private ZoneInfo zoneInfo;

        public TimeCalculator(String s)
        {
            zoneInfo = lookupZoneInfo(s);
        }
    }


    public Time()
    {
        initialize(TimeZone.getDefault().getID());
    }

    public Time(Time time)
    {
        initialize(time.timezone);
        set(time);
    }

    public Time(String s)
    {
        if(s == null)
        {
            throw new NullPointerException("timezoneId is null!");
        } else
        {
            initialize(s);
            return;
        }
    }

    private void checkChar(String s, int i, char c)
    {
        char c1 = s.charAt(i);
        if(c1 != c)
            throw new TimeFormatException(String.format("Unexpected character 0x%02d at pos=%d.  Expected 0x%02d ('%c').", new Object[] {
                Integer.valueOf(c1), Integer.valueOf(i), Integer.valueOf(c), Character.valueOf(c)
            }));
        else
            return;
    }

    public static int compare(Time time, Time time1)
    {
        if(time == null)
            throw new NullPointerException("a == null");
        if(time1 == null)
        {
            throw new NullPointerException("b == null");
        } else
        {
            time.calculator.copyFieldsFromTime(time);
            time1.calculator.copyFieldsFromTime(time1);
            return TimeCalculator.compare(time.calculator, time1.calculator);
        }
    }

    private static int getChar(String s, int i, int j)
    {
        char c = s.charAt(i);
        if(Character.isDigit(c))
            return Character.getNumericValue(c) * j;
        else
            throw new TimeFormatException((new StringBuilder()).append("Parse error at pos=").append(i).toString());
    }

    public static String getCurrentTimezone()
    {
        return TimeZone.getDefault().getID();
    }

    public static int getJulianDay(long l, long l1)
    {
        return (int)((l + l1 * 1000L) / 0x5265c00L) + 0x253d8c;
    }

    public static int getJulianMondayFromWeeksSinceEpoch(int i)
    {
        return i * 7 + 0x253d89;
    }

    public static int getWeeksSinceEpochFromJulianDay(int i, int j)
    {
        int k = 4 - j;
        j = k;
        if(k < 0)
            j = k + 7;
        return (i - (0x253d8c - j)) / 7;
    }

    private void initialize(String s)
    {
        timezone = s;
        year = 1970;
        monthDay = 1;
        isDst = -1;
        calculator = new TimeCalculator(s);
    }

    public static boolean isEpoch(Time time)
    {
        boolean flag = true;
        if(getJulianDay(time.toMillis(true), 0L) != 0x253d8c)
            flag = false;
        return flag;
    }

    private boolean parse3339Internal(String s)
    {
        int i;
        boolean flag;
        boolean flag1;
        i = s.length();
        if(i < 10)
            throw new TimeFormatException("String too short --- expected at least 10 characters.");
        flag = false;
        flag1 = false;
        year = getChar(s, 0, 1000) + getChar(s, 1, 100) + getChar(s, 2, 10) + getChar(s, 3, 1);
        checkChar(s, 4, '-');
        month = (getChar(s, 5, 10) + getChar(s, 6, 1)) - 1;
        checkChar(s, 7, '-');
        monthDay = getChar(s, 8, 10) + getChar(s, 9, 1);
        if(i < 19) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        char c;
        int i1;
        byte byte0;
        int j1;
        int k1;
label0:
        {
            checkChar(s, 10, 'T');
            allDay = false;
            j = getChar(s, 11, 10) + getChar(s, 12, 1);
            checkChar(s, 13, ':');
            k = getChar(s, 14, 10) + getChar(s, 15, 1);
            checkChar(s, 16, ':');
            second = getChar(s, 17, 10) + getChar(s, 18, 1);
            int l = 19;
            i1 = l;
            if(19 >= i)
                break label0;
            i1 = l;
            if(s.charAt(19) != '.')
                break label0;
            i1 = l;
            do
            {
                l = i1 + 1;
                i1 = l;
                if(l >= i)
                    break label0;
                i1 = l;
            } while(Character.isDigit(s.charAt(l)));
            i1 = l;
        }
        byte0 = 0;
        j1 = j;
        k1 = k;
        if(i <= i1)
            break MISSING_BLOCK_LABEL_514;
        c = s.charAt(i1);
        c;
        JVM INSTR lookupswitch 3: default 344
    //                   43: 445
    //                   45: 439
    //                   90: 377;
           goto _L3 _L4 _L5 _L6
_L3:
        throw new TimeFormatException(String.format("Unexpected character 0x%02d at position %d.  Expected + or -", new Object[] {
            Integer.valueOf(c), Integer.valueOf(i1)
        }));
_L6:
        c = '\0';
_L8:
        flag = true;
        j1 = j;
        flag1 = flag;
        k1 = k;
        byte0 = c;
        if(c == 0)
            break MISSING_BLOCK_LABEL_514;
        if(i < i1 + 6)
            throw new TimeFormatException(String.format("Unexpected length; should be %d characters", new Object[] {
                Integer.valueOf(i1 + 6)
            }));
        break; /* Loop/switch isn't completed */
_L5:
        c = '\001';
        continue; /* Loop/switch isn't completed */
_L4:
        c = '\uFFFF';
        if(true) goto _L8; else goto _L7
_L7:
        j1 = j + (getChar(s, i1 + 1, 10) + getChar(s, i1 + 2, 1)) * c;
        k1 = k + (getChar(s, i1 + 4, 10) + getChar(s, i1 + 5, 1)) * c;
        byte0 = c;
        flag1 = flag;
        hour = j1;
        minute = k1;
        flag = flag1;
        if(byte0 != 0)
        {
            normalize(false);
            flag = flag1;
        }
_L10:
        weekDay = 0;
        yearDay = 0;
        isDst = -1;
        gmtoff = 0L;
        return flag;
_L2:
        allDay = true;
        hour = 0;
        minute = 0;
        second = 0;
        if(true) goto _L10; else goto _L9
_L9:
    }

    private boolean parseInternal(String s)
    {
        int i = s.length();
        if(i < 8)
            throw new TimeFormatException((new StringBuilder()).append("String is too short: \"").append(s).append("\" Expected at least 8 characters.").toString());
        boolean flag = false;
        year = getChar(s, 0, 1000) + getChar(s, 1, 100) + getChar(s, 2, 10) + getChar(s, 3, 1);
        month = (getChar(s, 4, 10) + getChar(s, 5, 1)) - 1;
        monthDay = getChar(s, 6, 10) + getChar(s, 7, 1);
        if(i > 8)
        {
            if(i < 15)
                throw new TimeFormatException((new StringBuilder()).append("String is too short: \"").append(s).append("\" If there are more than 8 characters there must be at least").append(" 15.").toString());
            checkChar(s, 8, 'T');
            allDay = false;
            hour = getChar(s, 9, 10) + getChar(s, 10, 1);
            minute = getChar(s, 11, 10) + getChar(s, 12, 1);
            second = getChar(s, 13, 10) + getChar(s, 14, 1);
            if(i > 15)
            {
                checkChar(s, 15, 'Z');
                flag = true;
            }
        } else
        {
            allDay = true;
            hour = 0;
            minute = 0;
            second = 0;
        }
        weekDay = 0;
        yearDay = 0;
        isDst = -1;
        gmtoff = 0L;
        return flag;
    }

    public boolean after(Time time)
    {
        boolean flag = false;
        if(compare(this, time) > 0)
            flag = true;
        return flag;
    }

    public boolean before(Time time)
    {
        boolean flag = false;
        if(compare(this, time) < 0)
            flag = true;
        return flag;
    }

    public void clear(String s)
    {
        if(s == null)
        {
            throw new NullPointerException("timezone is null!");
        } else
        {
            timezone = s;
            allDay = false;
            second = 0;
            minute = 0;
            hour = 0;
            monthDay = 0;
            month = 0;
            year = 0;
            weekDay = 0;
            yearDay = 0;
            gmtoff = 0L;
            isDst = -1;
            return;
        }
    }

    public String format(String s)
    {
        calculator.copyFieldsFromTime(this);
        return calculator.format(s);
    }

    public String format2445()
    {
        calculator.copyFieldsFromTime(this);
        return calculator.format2445(allDay ^ true);
    }

    public String format3339(boolean flag)
    {
        if(flag)
            return format("%Y-%m-%d");
        if("UTC".equals(timezone))
            return format("%Y-%m-%dT%H:%M:%S.000Z");
        String s = format("%Y-%m-%dT%H:%M:%S.000");
        String s1;
        int i;
        int j;
        if(gmtoff < 0L)
            s1 = "-";
        else
            s1 = "+";
        i = (int)Math.abs(gmtoff);
        j = (i % 3600) / 60;
        i /= 3600;
        return String.format(Locale.US, "%s%s%02d:%02d", new Object[] {
            s, s1, Integer.valueOf(i), Integer.valueOf(j)
        });
    }

    public int getActualMaximum(int i)
    {
        byte byte0 = 28;
        switch(i)
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("bad field=").append(i).toString());

        case 1: // '\001'
            return 59;

        case 2: // '\002'
            return 59;

        case 3: // '\003'
            return 23;

        case 4: // '\004'
label0:
            {
                i = DAYS_PER_MONTH[month];
                if(i != 28)
                    return i;
                int j = year;
                i = byte0;
                if(j % 4 != 0)
                    break label0;
                if(j % 100 == 0)
                {
                    i = byte0;
                    if(j % 400 != 0)
                        break label0;
                }
                i = 29;
            }
            return i;

        case 5: // '\005'
            return 11;

        case 6: // '\006'
            return 2037;

        case 7: // '\007'
            return 6;

        case 8: // '\b'
            i = year;
            if(i % 4 == 0 && (i % 100 != 0 || i % 400 == 0))
                i = 365;
            else
                i = 364;
            return i;

        case 9: // '\t'
            throw new RuntimeException("WEEK_NUM not implemented");
        }
    }

    public int getWeekNumber()
    {
        int i = yearDay + sThursdayOffset[weekDay];
        if(i >= 0 && i <= 364)
        {
            return i / 7 + 1;
        } else
        {
            Time time = new Time(this);
            time.monthDay = time.monthDay + sThursdayOffset[weekDay];
            time.normalize(true);
            return time.yearDay / 7 + 1;
        }
    }

    public long normalize(boolean flag)
    {
        calculator.copyFieldsFromTime(this);
        long l = calculator.toMillis(flag);
        calculator.copyFieldsToTime(this);
        return l;
    }

    public boolean parse(String s)
    {
        if(s == null)
            throw new NullPointerException("time string is null");
        if(parseInternal(s))
        {
            timezone = "UTC";
            return true;
        } else
        {
            return false;
        }
    }

    public boolean parse3339(String s)
    {
        if(s == null)
            throw new NullPointerException("time string is null");
        if(parse3339Internal(s))
        {
            timezone = "UTC";
            return true;
        } else
        {
            return false;
        }
    }

    public void set(int i, int j, int k)
    {
        allDay = true;
        second = 0;
        minute = 0;
        hour = 0;
        monthDay = i;
        month = j;
        year = k;
        weekDay = 0;
        yearDay = 0;
        isDst = -1;
        gmtoff = 0L;
    }

    public void set(int i, int j, int k, int l, int i1, int j1)
    {
        allDay = false;
        second = i;
        minute = j;
        hour = k;
        monthDay = l;
        month = i1;
        year = j1;
        weekDay = 0;
        yearDay = 0;
        isDst = -1;
        gmtoff = 0L;
    }

    public void set(long l)
    {
        allDay = false;
        calculator.timezone = timezone;
        calculator.setTimeInMillis(l);
        calculator.copyFieldsToTime(this);
    }

    public void set(Time time)
    {
        timezone = time.timezone;
        allDay = time.allDay;
        second = time.second;
        minute = time.minute;
        hour = time.hour;
        monthDay = time.monthDay;
        month = time.month;
        year = time.year;
        weekDay = time.weekDay;
        yearDay = time.yearDay;
        isDst = time.isDst;
        gmtoff = time.gmtoff;
    }

    public long setJulianDay(int i)
    {
        long l = (long)(i - 0x253d8c) * 0x5265c00L;
        set(l);
        int j = getJulianDay(l, gmtoff);
        monthDay = monthDay + (i - j);
        hour = 0;
        minute = 0;
        second = 0;
        return normalize(true);
    }

    public void setToNow()
    {
        set(System.currentTimeMillis());
    }

    public void switchTimezone(String s)
    {
        calculator.copyFieldsFromTime(this);
        calculator.switchTimeZone(s);
        calculator.copyFieldsToTime(this);
        timezone = s;
    }

    public long toMillis(boolean flag)
    {
        calculator.copyFieldsFromTime(this);
        return calculator.toMillis(flag);
    }

    public String toString()
    {
        TimeCalculator timecalculator = new TimeCalculator(timezone);
        timecalculator.copyFieldsFromTime(this);
        return timecalculator.toStringInternal();
    }

    private static final int DAYS_PER_MONTH[] = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 
        30, 31
    };
    public static final int EPOCH_JULIAN_DAY = 0x253d8c;
    public static final int FRIDAY = 5;
    public static final int HOUR = 3;
    public static final int MINUTE = 2;
    public static final int MONDAY = 1;
    public static final int MONDAY_BEFORE_JULIAN_EPOCH = 0x253d89;
    public static final int MONTH = 5;
    public static final int MONTH_DAY = 4;
    public static final int SATURDAY = 6;
    public static final int SECOND = 1;
    public static final int SUNDAY = 0;
    public static final int THURSDAY = 4;
    public static final String TIMEZONE_UTC = "UTC";
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int WEEK_DAY = 7;
    public static final int WEEK_NUM = 9;
    public static final int YEAR = 6;
    public static final int YEAR_DAY = 8;
    private static final String Y_M_D = "%Y-%m-%d";
    private static final String Y_M_D_T_H_M_S_000 = "%Y-%m-%dT%H:%M:%S.000";
    private static final String Y_M_D_T_H_M_S_000_Z = "%Y-%m-%dT%H:%M:%S.000Z";
    private static final int sThursdayOffset[] = {
        -3, 3, 2, 1, 0, -1, -2
    };
    public boolean allDay;
    private TimeCalculator calculator;
    public long gmtoff;
    public int hour;
    public int isDst;
    public int minute;
    public int month;
    public int monthDay;
    public int second;
    public String timezone;
    public int weekDay;
    public int year;
    public int yearDay;

}
