// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.format;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.UserHandle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import libcore.icu.ICU;
import libcore.icu.LocaleData;

public class DateFormat
{

    public DateFormat()
    {
    }

    private static int appendQuotedText(SpannableStringBuilder spannablestringbuilder, int i, int j)
    {
        if(i + 1 < j && spannablestringbuilder.charAt(i + 1) == '\'')
        {
            spannablestringbuilder.delete(i, i + 1);
            return 1;
        }
        boolean flag = false;
        spannablestringbuilder.delete(i, i + 1);
        int k = j - 1;
        j = ((flag) ? 1 : 0);
        do
        {
label0:
            {
                if(i < k)
                {
                    if(spannablestringbuilder.charAt(i) != '\'')
                        break label0;
                    if(i + 1 < k && spannablestringbuilder.charAt(i + 1) == '\'')
                    {
                        spannablestringbuilder.delete(i, i + 1);
                        k--;
                        j++;
                        i++;
                        continue;
                    }
                    spannablestringbuilder.delete(i, i + 1);
                }
                return j;
            }
            i++;
            j++;
        } while(true);
    }

    public static CharSequence format(CharSequence charsequence, long l)
    {
        return format(charsequence, new Date(l));
    }

    public static CharSequence format(CharSequence charsequence, Calendar calendar)
    {
        SpannableStringBuilder spannablestringbuilder;
        LocaleData localedata;
        int i;
        int j;
        spannablestringbuilder = new SpannableStringBuilder(charsequence);
        localedata = LocaleData.get(Locale.getDefault());
        i = charsequence.length();
        j = 0;
_L2:
        int k;
        char c;
        int l;
        if(j >= i)
            break MISSING_BLOCK_LABEL_470;
        k = 1;
        c = spannablestringbuilder.charAt(j);
        if(c != '\'')
            break; /* Loop/switch isn't completed */
        l = appendQuotedText(spannablestringbuilder, j, i);
        i = spannablestringbuilder.length();
_L14:
        j += l;
        if(true) goto _L2; else goto _L1
_L1:
        for(; j + k < i && spannablestringbuilder.charAt(j + k) == c; k++);
        c;
        JVM INSTR lookupswitch 15: default 240
    //                   65: 282
    //                   69: 315
    //                   72: 378
    //                   75: 334
    //                   76: 394
    //                   77: 394
    //                   97: 282
    //                   99: 315
    //                   100: 300
    //                   104: 334
    //                   107: 378
    //                   109: 412
    //                   115: 428
    //                   121: 444
    //                   122: 459;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L8 _L4 _L5 _L9 _L7 _L6 _L10 _L11 _L12 _L13
_L13:
        break MISSING_BLOCK_LABEL_459;
_L3:
        Object obj = null;
_L15:
        l = k;
        if(obj != null)
        {
            spannablestringbuilder.replace(j, j + k, ((CharSequence) (obj)));
            l = ((String) (obj)).length();
            i = spannablestringbuilder.length();
        }
          goto _L14
_L4:
        obj = localedata.amPm[calendar.get(9) + 0];
          goto _L15
_L9:
        obj = zeroPad(calendar.get(5), k);
          goto _L15
_L5:
        obj = getDayOfWeekString(localedata, calendar.get(7), k, c);
          goto _L15
_L7:
        int j1 = calendar.get(10);
        int i1 = j1;
        if(c == 'h')
        {
            i1 = j1;
            if(j1 == 0)
                i1 = 12;
        }
        obj = zeroPad(i1, k);
          goto _L15
_L6:
        obj = zeroPad(calendar.get(11), k);
          goto _L15
_L8:
        obj = getMonthString(localedata, calendar.get(2), k, c);
          goto _L15
_L10:
        obj = zeroPad(calendar.get(12), k);
          goto _L15
_L11:
        obj = zeroPad(calendar.get(13), k);
          goto _L15
_L12:
        obj = getYearString(calendar.get(1), k);
          goto _L15
        obj = getTimeZoneString(calendar, k);
          goto _L15
        if(charsequence instanceof Spanned)
            return new SpannedString(spannablestringbuilder);
        return spannablestringbuilder.toString();
          goto _L14
    }

    public static CharSequence format(CharSequence charsequence, Date date)
    {
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        gregoriancalendar.setTime(date);
        return format(charsequence, ((Calendar) (gregoriancalendar)));
    }

    private static String formatZoneOffset(int i, int j)
    {
        i /= 1000;
        StringBuilder stringbuilder = new StringBuilder();
        if(i < 0)
        {
            stringbuilder.insert(0, "-");
            i = -i;
        } else
        {
            stringbuilder.insert(0, "+");
        }
        j = i / 3600;
        i = (i % 3600) / 60;
        stringbuilder.append(zeroPad(j, 2));
        stringbuilder.append(zeroPad(i, 2));
        return stringbuilder.toString();
    }

    public static String getBestDateTimePattern(Locale locale, String s)
    {
        return ICU.getBestDateTimePattern(s, locale);
    }

    public static java.text.DateFormat getDateFormat(Context context)
    {
        return java.text.DateFormat.getDateInstance(3);
    }

    public static char[] getDateFormatOrder(Context context)
    {
        return ICU.getDateFormatOrder(getDateFormatString());
    }

    private static String getDateFormatString()
    {
        java.text.DateFormat dateformat = java.text.DateFormat.getDateInstance(3);
        if(dateformat instanceof SimpleDateFormat)
            return ((SimpleDateFormat)dateformat).toPattern();
        else
            throw new AssertionError("!(df instanceof SimpleDateFormat)");
    }

    private static String getDayOfWeekString(LocaleData localedata, int i, int j, int k)
    {
        if(k == 99)
            k = 1;
        else
            k = 0;
        if(j == 5)
        {
            if(k != 0)
                localedata = localedata.tinyStandAloneWeekdayNames[i];
            else
                localedata = localedata.tinyWeekdayNames[i];
            return localedata;
        }
        if(j == 4)
        {
            if(k != 0)
                localedata = localedata.longStandAloneWeekdayNames[i];
            else
                localedata = localedata.longWeekdayNames[i];
            return localedata;
        }
        if(k != 0)
            localedata = localedata.shortStandAloneWeekdayNames[i];
        else
            localedata = localedata.shortWeekdayNames[i];
        return localedata;
    }

    public static java.text.DateFormat getLongDateFormat(Context context)
    {
        return java.text.DateFormat.getDateInstance(1);
    }

    public static java.text.DateFormat getMediumDateFormat(Context context)
    {
        return java.text.DateFormat.getDateInstance(2);
    }

    private static String getMonthString(LocaleData localedata, int i, int j, int k)
    {
        if(k == 76)
            k = 1;
        else
            k = 0;
        if(j == 5)
        {
            if(k != 0)
                localedata = localedata.tinyStandAloneMonthNames[i];
            else
                localedata = localedata.tinyMonthNames[i];
            return localedata;
        }
        if(j == 4)
        {
            if(k != 0)
                localedata = localedata.longStandAloneMonthNames[i];
            else
                localedata = localedata.longMonthNames[i];
            return localedata;
        }
        if(j == 3)
        {
            if(k != 0)
                localedata = localedata.shortStandAloneMonthNames[i];
            else
                localedata = localedata.shortMonthNames[i];
            return localedata;
        } else
        {
            return zeroPad(i + 1, j);
        }
    }

    public static java.text.DateFormat getTimeFormat(Context context)
    {
        return new SimpleDateFormat(getTimeFormatString(context));
    }

    public static String getTimeFormatString(Context context)
    {
        return getTimeFormatString(context, UserHandle.myUserId());
    }

    public static String getTimeFormatString(Context context, int i)
    {
        LocaleData localedata = LocaleData.get(context.getResources().getConfiguration().locale);
        if(is24HourFormat(context, i))
            context = localedata.timeFormat_Hm;
        else
            context = localedata.timeFormat_hm;
        return context;
    }

    private static String getTimeZoneString(Calendar calendar, int i)
    {
        TimeZone timezone = calendar.getTimeZone();
        if(i < 2)
            return formatZoneOffset(calendar.get(16) + calendar.get(15), i);
        boolean flag;
        if(calendar.get(16) != 0)
            flag = true;
        else
            flag = false;
        return timezone.getDisplayName(flag, 0);
    }

    private static String getYearString(int i, int j)
    {
        String s;
        if(j <= 2)
            s = zeroPad(i % 100, 2);
        else
            s = String.format(Locale.getDefault(), "%d", new Object[] {
                Integer.valueOf(i)
            });
        return s;
    }

    public static boolean hasDesignator(CharSequence charsequence, char c)
    {
        int i;
        int j;
        if(charsequence == null)
            return false;
        i = charsequence.length();
        j = 0;
_L2:
        int k;
        char c1;
        if(j >= i)
            break MISSING_BLOCK_LABEL_63;
        k = 1;
        c1 = charsequence.charAt(j);
        if(c1 != '\'')
            break; /* Loop/switch isn't completed */
        k = skipQuotedText(charsequence, j, i);
_L4:
        j += k;
        if(true) goto _L2; else goto _L1
_L1:
        if(c1 != c) goto _L4; else goto _L3
_L3:
        return true;
        return false;
    }

    public static boolean hasSeconds(CharSequence charsequence)
    {
        return hasDesignator(charsequence, 's');
    }

    public static boolean is24HourFormat(Context context)
    {
        return is24HourFormat(context, UserHandle.myUserId());
    }

    public static boolean is24HourFormat(Context context, int i)
    {
        Object obj;
        obj = android.provider.Settings.System.getStringForUser(context.getContentResolver(), "time_12_24", i);
        if(obj != null)
            break MISSING_BLOCK_LABEL_145;
        obj = context.getResources().getConfiguration().locale;
        context = ((Context) (sLocaleLock));
        context;
        JVM INSTR monitorenter ;
        boolean flag;
        if(sIs24HourLocale == null || !sIs24HourLocale.equals(obj))
            break MISSING_BLOCK_LABEL_57;
        flag = sIs24Hour;
        context;
        JVM INSTR monitorexit ;
        return flag;
        context;
        JVM INSTR monitorexit ;
        Object obj1;
        context = java.text.DateFormat.getTimeInstance(1, ((Locale) (obj)));
        if(context instanceof SimpleDateFormat)
        {
            if(((SimpleDateFormat)context).toPattern().indexOf('H') >= 0)
                context = "24";
            else
                context = "12";
        } else
        {
            context = "12";
        }
        obj1 = sLocaleLock;
        obj1;
        JVM INSTR monitorenter ;
        sIs24HourLocale = ((Locale) (obj));
        sIs24Hour = context.equals("24");
        obj1;
        JVM INSTR monitorexit ;
        return sIs24Hour;
        obj;
        throw obj;
        context;
        throw context;
        return ((String) (obj)).equals("24");
    }

    private static int skipQuotedText(CharSequence charsequence, int i, int j)
    {
        if(i + 1 < j && charsequence.charAt(i + 1) == '\'')
            return 2;
        int k = 1;
        int l = i + 1;
        i = k;
        do
        {
            k = i;
            if(l >= j)
                break;
            if(charsequence.charAt(l) == '\'')
            {
                k = ++i;
                if(l + 1 >= j)
                    break;
                k = i;
                if(charsequence.charAt(l + 1) != '\'')
                    break;
                l++;
            } else
            {
                l++;
                i++;
            }
        } while(true);
        return k;
    }

    private static String zeroPad(int i, int j)
    {
        return String.format(Locale.getDefault(), (new StringBuilder()).append("%0").append(j).append("d").toString(), new Object[] {
            Integer.valueOf(i)
        });
    }

    public static final char AM_PM = 97;
    public static final char CAPITAL_AM_PM = 65;
    public static final char DATE = 100;
    public static final char DAY = 69;
    public static final char HOUR = 104;
    public static final char HOUR_OF_DAY = 107;
    public static final char MINUTE = 109;
    public static final char MONTH = 77;
    public static final char QUOTE = 39;
    public static final char SECONDS = 115;
    public static final char STANDALONE_MONTH = 76;
    public static final char TIME_ZONE = 122;
    public static final char YEAR = 121;
    private static boolean sIs24Hour;
    private static Locale sIs24HourLocale;
    private static final Object sLocaleLock = new Object();

}
