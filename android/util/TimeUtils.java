// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.icu.util.TimeZone;
import android.os.SystemClock;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import libcore.util.TimeZoneFinder;
import libcore.util.ZoneInfoDB;

public class TimeUtils
{

    public TimeUtils()
    {
    }

    private static int accumField(int i, int j, boolean flag, int k)
    {
        if(i > 999)
        {
            k = 0;
            for(; i != 0; i /= 10)
                k++;

            return k + j;
        }
        if(i > 99 || flag && k >= 3)
            return j + 3;
        if(i > 9 || flag && k >= 2)
            return j + 2;
        if(flag || i > 0)
            return j + 1;
        else
            return 0;
    }

    private static List extractZoneIds(List list)
    {
        ArrayList arraylist = new ArrayList(list.size());
        for(list = list.iterator(); list.hasNext(); arraylist.add(((TimeZone)list.next()).getID()));
        return Collections.unmodifiableList(arraylist);
    }

    public static void formatDuration(long l, long l1, PrintWriter printwriter)
    {
        if(l == 0L)
        {
            printwriter.print("--");
            return;
        } else
        {
            formatDuration(l - l1, printwriter, 0);
            return;
        }
    }

    public static void formatDuration(long l, PrintWriter printwriter)
    {
        formatDuration(l, printwriter, 0);
    }

    public static void formatDuration(long l, PrintWriter printwriter, int i)
    {
        Object obj = sFormatSync;
        obj;
        JVM INSTR monitorenter ;
        i = formatDurationLocked(l, i);
        String s = JVM INSTR new #130 <Class String>;
        s.String(sFormatStr, 0, i);
        printwriter.print(s);
        obj;
        JVM INSTR monitorexit ;
        return;
        printwriter;
        throw printwriter;
    }

    public static void formatDuration(long l, StringBuilder stringbuilder)
    {
        Object obj = sFormatSync;
        obj;
        JVM INSTR monitorenter ;
        int i = formatDurationLocked(l, 0);
        stringbuilder.append(sFormatStr, 0, i);
        obj;
        JVM INSTR monitorexit ;
        return;
        stringbuilder;
        throw stringbuilder;
    }

    private static int formatDurationLocked(long l, int i)
    {
        if(sFormatStr.length < i)
            sFormatStr = new char[i];
        char ac[] = sFormatStr;
        if(l == 0L)
        {
            int j;
            for(j = 0; j < i - 1; j++)
                ac[j] = (char)32;

            ac[j] = (char)48;
            return j + 1;
        }
        int k;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        boolean flag;
        if(l > 0L)
        {
            i1 = 43;
        } else
        {
            i1 = 45;
            l = -l;
        }
        j1 = (int)(l % 1000L);
        k = (int)Math.floor(l / 1000L);
        k1 = 0;
        l1 = 0;
        i2 = 0;
        j2 = k;
        if(k >= 0x15180)
        {
            k1 = k / 0x15180;
            j2 = k - 0x15180 * k1;
        }
        k = j2;
        if(j2 >= 3600)
        {
            l1 = j2 / 3600;
            k = j2 - l1 * 3600;
        }
        j2 = k;
        if(k >= 60)
        {
            i2 = k / 60;
            j2 = k - i2 * 60;
        }
        k2 = 0;
        flag = false;
        if(i != 0)
        {
            k = accumField(k1, 1, false, 0);
            boolean flag1;
            int l2;
            if(k > 0)
                flag1 = true;
            else
                flag1 = false;
            k += accumField(l1, 1, flag1, 2);
            if(k > 0)
                flag1 = true;
            else
                flag1 = false;
            k += accumField(i2, 1, flag1, 2);
            if(k > 0)
                flag1 = true;
            else
                flag1 = false;
            l2 = k + accumField(j2, 1, flag1, 2);
            if(l2 > 0)
                k = 3;
            else
                k = 0;
            l2 += accumField(j1, 2, true, k) + 1;
            k = ((flag) ? 1 : 0);
            do
            {
                k2 = k;
                if(l2 >= i)
                    break;
                ac[k] = (char)32;
                k++;
                l2++;
            } while(true);
        }
        ac[k2] = (char)i1;
        i1 = k2 + 1;
        boolean flag2;
        if(i != 0)
            i = 1;
        else
            i = 0;
        k1 = printFieldLocked(ac, k1, 'd', i1, false, 0);
        if(k1 != i1)
            flag2 = true;
        else
            flag2 = false;
        if(i != 0)
            k = 2;
        else
            k = 0;
        k1 = printFieldLocked(ac, l1, 'h', k1, flag2, k);
        if(k1 != i1)
            flag2 = true;
        else
            flag2 = false;
        if(i != 0)
            k = 2;
        else
            k = 0;
        i2 = printFieldLocked(ac, i2, 'm', k1, flag2, k);
        if(i2 != i1)
            flag2 = true;
        else
            flag2 = false;
        if(i != 0)
            k = 2;
        else
            k = 0;
        k = printFieldLocked(ac, j2, 's', i2, flag2, k);
        if(i != 0 && k != i1)
            i = 3;
        else
            i = 0;
        i = printFieldLocked(ac, j1, 'm', k, true, i);
        ac[i] = (char)115;
        return i + 1;
    }

    public static String formatForLogging(long l)
    {
        if(l <= 0L)
            return "unknown";
        else
            return sLoggingFormat.format(new Date(l));
    }

    public static String formatUptime(long l)
    {
        long l1 = l - SystemClock.uptimeMillis();
        if(l1 > 0L)
            return (new StringBuilder()).append(l).append(" (in ").append(l1).append(" ms)").toString();
        if(l1 < 0L)
            return (new StringBuilder()).append(l).append(" (").append(-l1).append(" ms ago)").toString();
        else
            return (new StringBuilder()).append(l).append(" (now)").toString();
    }

    private static TimeZone getIcuTimeZone(int i, boolean flag, long l, String s)
    {
        if(s == null)
        {
            return null;
        } else
        {
            TimeZone timezone = TimeZone.getDefault();
            return TimeZoneFinder.getInstance().lookupTimeZoneByCountryAndOffset(s, i, flag, l, timezone);
        }
    }

    private static List getIcuTimeZones(String s)
    {
        if(s == null)
            return Collections.emptyList();
        s = TimeZoneFinder.getInstance().lookupTimeZonesByCountry(s);
        if(s == null)
            return Collections.emptyList();
        else
            return s;
    }

    public static java.util.TimeZone getTimeZone(int i, boolean flag, long l, String s)
    {
        Object obj = null;
        TimeZone timezone = getIcuTimeZone(i, flag, l, s);
        s = obj;
        if(timezone != null)
            s = java.util.TimeZone.getTimeZone(timezone.getID());
        return s;
    }

    public static String getTimeZoneDatabaseVersion()
    {
        return ZoneInfoDB.getInstance().getVersion();
    }

    public static List getTimeZoneIdsWithUniqueOffsets(String s)
    {
        Object obj = sLastUniqueLockObj;
        obj;
        JVM INSTR monitorenter ;
        if(s == null)
            break MISSING_BLOCK_LABEL_28;
        if(!s.equals(sLastUniqueCountry))
            break MISSING_BLOCK_LABEL_28;
        s = sLastUniqueZoneOffsets;
        obj;
        JVM INSTR monitorexit ;
        return s;
        obj;
        JVM INSTR monitorexit ;
        Iterator iterator;
        List list = getIcuTimeZones(s);
        obj = new ArrayList();
        iterator = list.iterator();
_L5:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj1;
        boolean flag;
        int i;
        obj1 = (TimeZone)iterator.next();
        flag = false;
        i = 0;
_L3:
        boolean flag1 = flag;
        if(i < ((ArrayList) (obj)).size())
        {
            if(((TimeZone)((ArrayList) (obj)).get(i)).getRawOffset() != ((TimeZone) (obj1)).getRawOffset())
                break MISSING_BLOCK_LABEL_129;
            flag1 = true;
        }
        if(!flag1)
            ((ArrayList) (obj)).add(obj1);
        continue; /* Loop/switch isn't completed */
        s;
        throw s;
        i++;
        if(true) goto _L3; else goto _L2
_L2:
        obj1 = sLastUniqueLockObj;
        obj1;
        JVM INSTR monitorenter ;
        sLastUniqueZoneOffsets = extractZoneIds(((List) (obj)));
        sLastUniqueCountry = s;
        s = sLastUniqueZoneOffsets;
        obj1;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static String logTimeOfDay(long l)
    {
        Calendar calendar = Calendar.getInstance();
        if(l >= 0L)
        {
            calendar.setTimeInMillis(l);
            return String.format("%tm-%td %tH:%tM:%tS.%tL", new Object[] {
                calendar, calendar, calendar, calendar, calendar, calendar
            });
        } else
        {
            return Long.toString(l);
        }
    }

    private static int printFieldLocked(char ac[], int i, char c, int j, boolean flag, int k)
    {
        if(flag) goto _L2; else goto _L1
_L1:
        int l = j;
        if(i <= 0) goto _L3; else goto _L2
_L2:
        if(i <= 999) goto _L5; else goto _L4
_L4:
        for(k = 0; i != 0 && k < sTmpFormatStr.length; i /= 10)
        {
            sTmpFormatStr[k] = (char)(i % 10 + 48);
            k++;
        }

        i = k - 1;
        do
        {
            k = j;
            if(i < 0)
                break;
            ac[j] = sTmpFormatStr[i];
            j++;
            i--;
        } while(true);
          goto _L6
_L5:
        if(!flag || k < 3) goto _L8; else goto _L7
_L7:
        int i1;
        l = i / 100;
        ac[j] = (char)(l + 48);
        i1 = j + 1;
        l = i - l * 100;
          goto _L9
_L11:
        j = l / 10;
        ac[i1] = (char)(j + 48);
        i = i1 + 1;
        k = l - j * 10;
_L12:
        ac[i] = (char)(k + 48);
        k = i + 1;
_L6:
        ac[k] = c;
        l = k + 1;
_L3:
        return l;
_L8:
        l = i;
        i1 = j;
        if(i <= 99) goto _L9; else goto _L7
_L9:
        if(flag && k >= 2 || l > 9) goto _L11; else goto _L10
_L10:
        k = l;
        i = i1;
        if(j == i1) goto _L12; else goto _L11
    }

    private static final boolean DBG = false;
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    public static final long NANOS_PER_MS = 0xf4240L;
    private static final int SECONDS_PER_DAY = 0x15180;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final String TAG = "TimeUtils";
    private static char sFormatStr[] = new char[29];
    private static final Object sFormatSync = new Object();
    private static String sLastUniqueCountry = null;
    private static final Object sLastUniqueLockObj = new Object();
    private static List sLastUniqueZoneOffsets = null;
    private static SimpleDateFormat sLoggingFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static char sTmpFormatStr[] = new char[29];

}
