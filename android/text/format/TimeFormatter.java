// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.format;

import android.content.res.Resources;
import java.nio.CharBuffer;
import java.util.Formatter;
import java.util.Locale;
import libcore.icu.LocaleData;
import libcore.util.ZoneInfo;

class TimeFormatter
{

    public TimeFormatter()
    {
        android/text/format/TimeFormatter;
        JVM INSTR monitorenter ;
        Object obj = Locale.getDefault();
        if(sLocale == null || ((Locale) (obj)).equals(sLocale) ^ true)
        {
            sLocale = ((Locale) (obj));
            sLocaleData = LocaleData.get(((Locale) (obj)));
            obj = Resources.getSystem();
            sTimeOnlyFormat = ((Resources) (obj)).getString(0x1040638);
            sDateOnlyFormat = ((Resources) (obj)).getString(0x10403b8);
            sDateTimeFormat = ((Resources) (obj)).getString(0x1040182);
        }
        dateTimeFormat = sDateTimeFormat;
        timeOnlyFormat = sTimeOnlyFormat;
        dateOnlyFormat = sDateOnlyFormat;
        localeData = sLocaleData;
        android/text/format/TimeFormatter;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static boolean brokenIsLower(char c)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(c >= 'a')
        {
            flag1 = flag;
            if(c <= 'z')
                flag1 = true;
        }
        return flag1;
    }

    private static boolean brokenIsUpper(char c)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(c >= 'A')
        {
            flag1 = flag;
            if(c <= 'Z')
                flag1 = true;
        }
        return flag1;
    }

    private static char brokenToLower(char c)
    {
        if(c >= 'A' && c <= 'Z')
            return (char)((c - 65) + 97);
        else
            return c;
    }

    private static char brokenToUpper(char c)
    {
        if(c >= 'a' && c <= 'z')
            return (char)((c - 97) + 65);
        else
            return c;
    }

    private void formatInternal(String s, libcore.util.ZoneInfo.WallTime walltime, ZoneInfo zoneinfo)
    {
        for(s = CharBuffer.wrap(s); s.remaining() > 0; s.position(s.position() + 1))
        {
            boolean flag = true;
            if(s.get(s.position()) == '%')
                flag = handleToken(s, walltime, zoneinfo);
            if(flag)
                outputBuilder.append(s.get(s.position()));
        }

    }

    private static String getFormat(int i, String s, String s1, String s2, String s3)
    {
        switch(i)
        {
        default:
            return s;

        case 95: // '_'
            return s1;

        case 45: // '-'
            return s2;

        case 48: // '0'
            return s3;
        }
    }

    private boolean handleToken(CharBuffer charbuffer, libcore.util.ZoneInfo.WallTime walltime, ZoneInfo zoneinfo)
    {
        int i = 0;
_L10:
        if(charbuffer.remaining() <= 1) goto _L2; else goto _L1
_L1:
        char c;
        charbuffer.position(charbuffer.position() + 1);
        c = charbuffer.get(charbuffer.position());
        c;
        JVM INSTR tableswitch 35 122: default 400
    //                   35 707
    //                   36 400
    //                   37 400
    //                   38 400
    //                   39 400
    //                   40 400
    //                   41 400
    //                   42 400
    //                   43 1914
    //                   44 400
    //                   45 707
    //                   46 400
    //                   47 400
    //                   48 707
    //                   49 400
    //                   50 400
    //                   51 400
    //                   52 400
    //                   53 400
    //                   54 400
    //                   55 400
    //                   56 400
    //                   57 400
    //                   58 400
    //                   59 400
    //                   60 400
    //                   61 400
    //                   62 400
    //                   63 400
    //                   64 400
    //                   65 402
    //                   66 494
    //                   67 634
    //                   68 660
    //                   69 3
    //                   70 751
    //                   71 1362
    //                   72 761
    //                   73 798
    //                   74 400
    //                   75 400
    //                   76 400
    //                   77 1000
    //                   78 400
    //                   79 3
    //                   80 1129
    //                   81 400
    //                   82 1169
    //                   83 1189
    //                   84 1248
    //                   85 1270
    //                   86 1362
    //                   87 1608
    //                   88 1710
    //                   89 1748
    //                   90 1762
    //                   91 400
    //                   92 400
    //                   93 400
    //                   94 707
    //                   95 707
    //                   96 400
    //                   97 448
    //                   98 590
    //                   99 648
    //                   100 670
    //                   101 714
    //                   102 400
    //                   103 1362
    //                   104 590
    //                   105 400
    //                   106 859
    //                   107 902
    //                   108 939
    //                   109 1037
    //                   110 1076
    //                   111 400
    //                   112 1088
    //                   113 400
    //                   114 1179
    //                   115 1226
    //                   116 1258
    //                   117 1318
    //                   118 1598
    //                   119 1684
    //                   120 1722
    //                   121 1734
    //                   122 1802;
           goto _L3 _L4 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L5 _L3 _L4 _L3 _L3 _L4 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L3 _L3 _L3 _L15 _L3 _L10 _L16 _L3 _L17 _L18 _L19 _L20 _L12 _L21 _L22 _L23 _L24 _L3 _L3 _L3 _L4 _L4 _L3 _L25 _L26 _L27 _L28 _L29 _L3 _L12 _L26 _L3 _L30 _L31 _L32 _L33 _L34 _L3 _L35 _L3 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44
_L3:
        return true;
_L6:
        if(walltime.getWeekDay() < 0 || walltime.getWeekDay() >= 7)
            charbuffer = "?";
        else
            charbuffer = localeData.longWeekdayNames[walltime.getWeekDay() + 1];
        modifyAndAppend(charbuffer, i);
        return false;
_L25:
        if(walltime.getWeekDay() < 0 || walltime.getWeekDay() >= 7)
            charbuffer = "?";
        else
            charbuffer = localeData.shortWeekdayNames[walltime.getWeekDay() + 1];
        modifyAndAppend(charbuffer, i);
        return false;
_L7:
        if(i == 45)
        {
            if(walltime.getMonth() < 0 || walltime.getMonth() >= 12)
                charbuffer = "?";
            else
                charbuffer = localeData.longStandAloneMonthNames[walltime.getMonth()];
            modifyAndAppend(charbuffer, i);
        } else
        {
            if(walltime.getMonth() < 0 || walltime.getMonth() >= 12)
                charbuffer = "?";
            else
                charbuffer = localeData.longMonthNames[walltime.getMonth()];
            modifyAndAppend(charbuffer, i);
        }
        return false;
_L26:
        if(walltime.getMonth() < 0 || walltime.getMonth() >= 12)
            charbuffer = "?";
        else
            charbuffer = localeData.shortMonthNames[walltime.getMonth()];
        modifyAndAppend(charbuffer, i);
        return false;
_L8:
        outputYear(walltime.getYear(), true, false, i);
        return false;
_L27:
        formatInternal(dateTimeFormat, walltime, zoneinfo);
        return false;
_L9:
        formatInternal("%m/%d/%y", walltime, zoneinfo);
        return false;
_L28:
        numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(walltime.getMonthDay())
        });
        return false;
_L4:
        i = c;
          goto _L10
_L29:
        numberFormatter.format(getFormat(i, "%2d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(walltime.getMonthDay())
        });
        return false;
_L11:
        formatInternal("%Y-%m-%d", walltime, zoneinfo);
        return false;
_L13:
        numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(walltime.getHour())
        });
        return false;
_L14:
        int j;
        if(walltime.getHour() % 12 != 0)
            j = walltime.getHour() % 12;
        else
            j = 12;
        numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(j)
        });
        return false;
_L30:
        int k = walltime.getYearDay();
        numberFormatter.format(getFormat(i, "%03d", "%3d", "%d", "%03d"), new Object[] {
            Integer.valueOf(k + 1)
        });
        return false;
_L31:
        numberFormatter.format(getFormat(i, "%2d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(walltime.getHour())
        });
        return false;
_L32:
        int l;
        if(walltime.getHour() % 12 != 0)
            l = walltime.getHour() % 12;
        else
            l = 12;
        numberFormatter.format(getFormat(i, "%2d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(l)
        });
        return false;
_L15:
        numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(walltime.getMinute())
        });
        return false;
_L33:
        numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(walltime.getMonth() + 1)
        });
        return false;
_L34:
        outputBuilder.append('\n');
        return false;
_L35:
        if(walltime.getHour() >= 12)
            charbuffer = localeData.amPm[1];
        else
            charbuffer = localeData.amPm[0];
        modifyAndAppend(charbuffer, i);
        return false;
_L16:
        if(walltime.getHour() >= 12)
            charbuffer = localeData.amPm[1];
        else
            charbuffer = localeData.amPm[0];
        modifyAndAppend(charbuffer, -1);
        return false;
_L17:
        formatInternal("%H:%M", walltime, zoneinfo);
        return false;
_L36:
        formatInternal("%I:%M:%S %p", walltime, zoneinfo);
        return false;
_L18:
        numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(walltime.getSecond())
        });
        return false;
_L37:
        int i1 = walltime.mktime(zoneinfo);
        outputBuilder.append(Integer.toString(i1));
        return false;
_L19:
        formatInternal("%H:%M:%S", walltime, zoneinfo);
        return false;
_L38:
        outputBuilder.append('\t');
        return false;
_L20:
        numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(((walltime.getYearDay() + 7) - walltime.getWeekDay()) / 7)
        });
        return false;
_L39:
        int j1;
        if(walltime.getWeekDay() == 0)
            j1 = 7;
        else
            j1 = walltime.getWeekDay();
        numberFormatter.format("%d", new Object[] {
            Integer.valueOf(j1)
        });
        return false;
_L12:
        int k1;
        int j2;
        int i3;
        k1 = walltime.getYear();
        j2 = walltime.getYearDay();
        i3 = walltime.getWeekDay();
_L50:
        int k3;
        char c1;
        int l3;
        int i4;
        if(isLeap(k1))
            c1 = '\u016E';
        else
            c1 = '\u016D';
        k3 = ((j2 + 11) - i3) % 7 - 3;
        l3 = k3 - c1 % 7;
        i4 = l3;
        if(l3 < -3)
            i4 = l3 + 7;
        if(j2 < i4 + c1) goto _L46; else goto _L45
_L45:
        k1++;
        j2 = 1;
_L49:
        int j3;
        if(c == 'V')
            numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
                Integer.valueOf(j2)
            });
        else
        if(c == 'g')
            outputYear(k1, false, true, i);
        else
            outputYear(k1, true, true, i);
        return false;
_L46:
        if(j2 < k3) goto _L48; else goto _L47
_L47:
        j2 = (j2 - k3) / 7 + 1;
          goto _L49
_L48:
        j3 = k1 - 1;
        if(isLeap(j3))
            k1 = 366;
        else
            k1 = 365;
        j2 += k1;
        k1 = j3;
          goto _L50
_L40:
        formatInternal("%e-%b-%Y", walltime, zoneinfo);
        return false;
_L21:
        int k2 = walltime.getYearDay();
        int l1;
        if(walltime.getWeekDay() != 0)
            l1 = walltime.getWeekDay() - 1;
        else
            l1 = 6;
        l1 = ((k2 + 7) - l1) / 7;
        numberFormatter.format(getFormat(i, "%02d", "%2d", "%d", "%02d"), new Object[] {
            Integer.valueOf(l1)
        });
        return false;
_L41:
        numberFormatter.format("%d", new Object[] {
            Integer.valueOf(walltime.getWeekDay())
        });
        return false;
_L22:
        formatInternal(timeOnlyFormat, walltime, zoneinfo);
        return false;
_L42:
        formatInternal(dateOnlyFormat, walltime, zoneinfo);
        return false;
_L43:
        outputYear(walltime.getYear(), false, true, i);
        return false;
_L23:
        outputYear(walltime.getYear(), true, true, i);
        return false;
_L24:
        if(walltime.getIsDst() < 0)
            return false;
        boolean flag;
        if(walltime.getIsDst() != 0)
            flag = true;
        else
            flag = false;
        modifyAndAppend(zoneinfo.getDisplayName(flag, 0), i);
        return false;
_L44:
        if(walltime.getIsDst() < 0)
            return false;
        int i2 = walltime.getGmtOffset();
        int l2;
        char c2;
        if(i2 < 0)
        {
            byte byte0 = 45;
            i2 = -i2;
            c2 = byte0;
        } else
        {
            byte byte1 = 43;
            c2 = byte1;
        }
        outputBuilder.append(c2);
        l2 = i2 / 60;
        i2 = l2 / 60;
        numberFormatter.format(getFormat(i, "%04d", "%4d", "%d", "%04d"), new Object[] {
            Integer.valueOf(i2 * 100 + l2 % 60)
        });
        return false;
_L5:
        formatInternal("%a %b %e %H:%M:%S %Z %Y", walltime, zoneinfo);
        return false;
_L2:
        return true;
          goto _L49
    }

    private static boolean isLeap(int i)
    {
        boolean flag = true;
        if(i % 4 != 0) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
        if(i % 100 != 0) goto _L4; else goto _L3
_L3:
        if(i % 400 != 0) goto _L2; else goto _L5
_L5:
        flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = false;
        if(true) goto _L4; else goto _L6
_L6:
    }

    private String localizeDigits(String s)
    {
        int i = s.length();
        char c = localeData.zeroDigit;
        StringBuilder stringbuilder = new StringBuilder(i);
        for(int j = 0; j < i; j++)
        {
            char c1 = s.charAt(j);
            char c2 = c1;
            if(c1 >= '0')
            {
                c2 = c1;
                if(c1 <= '9')
                {
                    c1 += c - 48;
                    c2 = c1;
                }
            }
            stringbuilder.append(c2);
        }

        return stringbuilder.toString();
    }

    private void modifyAndAppend(CharSequence charsequence, int i)
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   -1: 46
    //                   35: 118
    //                   94: 82;
           goto _L1 _L2 _L3 _L4
_L1:
        outputBuilder.append(charsequence);
_L5:
        return;
_L2:
        i = 0;
        while(i < charsequence.length()) 
        {
            outputBuilder.append(brokenToLower(charsequence.charAt(i)));
            i++;
        }
        if(true)
            continue; /* Loop/switch isn't completed */
_L4:
        i = 0;
        while(i < charsequence.length()) 
        {
            outputBuilder.append(brokenToUpper(charsequence.charAt(i)));
            i++;
        }
        if(true) goto _L5; else goto _L3
_L3:
        i = 0;
        while(i < charsequence.length()) 
        {
            char c = charsequence.charAt(i);
            char c3;
            if(brokenIsUpper(c))
            {
                char c1 = brokenToLower(c);
                c3 = c1;
            } else
            {
                c3 = c;
                if(brokenIsLower(c))
                {
                    char c2 = brokenToUpper(c);
                    c3 = c2;
                }
            }
            outputBuilder.append(c3);
            i++;
        }
        if(true) goto _L5; else goto _L6
_L6:
    }

    private void outputYear(int i, boolean flag, boolean flag1, int j)
    {
        int k = i % 100;
        int l = i / 100 + k / 100;
        int i1 = k % 100;
        if(i1 < 0 && l > 0)
        {
            i = i1 + 100;
            k = l - 1;
        } else
        {
            k = l;
            i = i1;
            if(l < 0)
            {
                k = l;
                i = i1;
                if(i1 > 0)
                {
                    i = i1 - 100;
                    k = l + 1;
                }
            }
        }
        if(flag)
            if(k == 0 && i < 0)
                outputBuilder.append("-0");
            else
                numberFormatter.format(getFormat(j, "%02d", "%2d", "%d", "%02d"), new Object[] {
                    Integer.valueOf(k)
                });
        if(flag1)
        {
            if(i < 0)
                i = -i;
            numberFormatter.format(getFormat(j, "%02d", "%2d", "%d", "%02d"), new Object[] {
                Integer.valueOf(i)
            });
        }
    }

    public String format(String s, libcore.util.ZoneInfo.WallTime walltime, ZoneInfo zoneinfo)
    {
        StringBuilder stringbuilder = JVM INSTR new #120 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        outputBuilder = stringbuilder;
        Formatter formatter = JVM INSTR new #191 <Class Formatter>;
        formatter.Formatter(stringbuilder, Locale.US);
        numberFormatter = formatter;
        formatInternal(s, walltime, zoneinfo);
        walltime = stringbuilder.toString();
        s = walltime;
        if(localeData.zeroDigit != '0')
            s = localizeDigits(walltime);
        outputBuilder = null;
        numberFormatter = null;
        return s;
        s;
        outputBuilder = null;
        numberFormatter = null;
        throw s;
    }

    private static final int DAYSPERLYEAR = 366;
    private static final int DAYSPERNYEAR = 365;
    private static final int DAYSPERWEEK = 7;
    private static final int FORCE_LOWER_CASE = -1;
    private static final int HOURSPERDAY = 24;
    private static final int MINSPERHOUR = 60;
    private static final int MONSPERYEAR = 12;
    private static final int SECSPERMIN = 60;
    private static String sDateOnlyFormat;
    private static String sDateTimeFormat;
    private static Locale sLocale;
    private static LocaleData sLocaleData;
    private static String sTimeOnlyFormat;
    private final String dateOnlyFormat;
    private final String dateTimeFormat;
    private final LocaleData localeData;
    private Formatter numberFormatter;
    private StringBuilder outputBuilder;
    private final String timeOnlyFormat;
}
