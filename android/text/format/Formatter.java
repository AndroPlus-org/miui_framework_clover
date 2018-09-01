// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.format;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.text.*;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.net.NetworkUtils;
import android.os.LocaleList;
import android.text.BidiFormatter;
import android.text.TextUtils;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.util.Locale;

public final class Formatter
{
    public static class BytesResult
    {

        public final long roundedBytes;
        public final String units;
        public final String value;

        public BytesResult(String s, String s1, long l)
        {
            value = s;
            units = s1;
            roundedBytes = l;
        }
    }

    private static class RoundedBytesResult
    {

        static RoundedBytesResult roundBytes(long l, int i)
        {
            boolean flag;
            long l1;
            float f;
            MeasureUnit measureunit;
            float f1;
            float f2;
            int j;
            int k;
            if(l < 0L)
                flag = true;
            else
                flag = false;
            l1 = l;
            if(flag)
                l1 = -l;
            f = l1;
            measureunit = MeasureUnit.BYTE;
            l1 = 1L;
            f1 = f;
            if(f > 900F)
            {
                measureunit = MeasureUnit.KILOBYTE;
                l1 = 1000L;
                f1 = f / 1000F;
            }
            f = f1;
            l = l1;
            if(f1 > 900F)
            {
                measureunit = MeasureUnit.MEGABYTE;
                l = l1 * 1000L;
                f = f1 / 1000F;
            }
            f1 = f;
            l1 = l;
            if(f > 900F)
            {
                measureunit = MeasureUnit.GIGABYTE;
                l1 = l * 1000L;
                f1 = f / 1000F;
            }
            f2 = f1;
            l = l1;
            if(f1 > 900F)
            {
                measureunit = MeasureUnit.TERABYTE;
                l = l1 * 1000L;
                f2 = f1 / 1000F;
            }
            f = f2;
            l1 = l;
            if(f2 > 900F)
            {
                measureunit = Formatter._2D_get0();
                l1 = l * 1000L;
                f = f2 / 1000F;
            }
            if(l1 == 1L || f >= 100F)
            {
                j = 1;
                k = 0;
            } else
            if(f < 1.0F)
            {
                j = 100;
                k = 2;
            } else
            if(f < 10F)
            {
                if((i & 1) != 0)
                {
                    j = 10;
                    k = 1;
                } else
                {
                    j = 100;
                    k = 2;
                }
            } else
            if((i & 1) != 0)
            {
                j = 1;
                k = 0;
            } else
            {
                j = 100;
                k = 2;
            }
            f1 = f;
            if(flag)
                f1 = -f;
            if((i & 2) == 0)
                l = 0L;
            else
                l = ((long)Math.round((float)j * f1) * l1) / (long)j;
            return new RoundedBytesResult(f1, measureunit, k, l);
        }

        public final int fractionDigits;
        public final long roundedBytes;
        public final MeasureUnit units;
        public final float value;

        private RoundedBytesResult(float f, MeasureUnit measureunit, int i, long l)
        {
            value = f;
            units = measureunit;
            fractionDigits = i;
            roundedBytes = l;
        }
    }


    static MeasureUnit _2D_get0()
    {
        return PETABYTE;
    }

    public Formatter()
    {
    }

    private static String bidiWrap(Context context, String s)
    {
        if(TextUtils.getLayoutDirectionFromLocale(localeFromContext(context)) == 1)
            return BidiFormatter.getInstance(true).unicodeWrap(s);
        else
            return s;
    }

    private static MeasureUnit createPetaByte()
    {
        Object obj;
        try
        {
            obj = android/icu/util/MeasureUnit.getDeclaredConstructor(new Class[] {
                java/lang/String, java/lang/String
            });
            ((Constructor) (obj)).setAccessible(true);
            obj = (MeasureUnit)((Constructor) (obj)).newInstance(new Object[] {
                "digital", "petabyte"
            });
        }
        catch(ReflectiveOperationException reflectiveoperationexception)
        {
            throw new RuntimeException("Failed to create petabyte MeasureUnit", reflectiveoperationexception);
        }
        return ((MeasureUnit) (obj));
    }

    private static String deleteFirstFromString(String s, String s1)
    {
        int i = s.indexOf(s1);
        if(i == -1)
            return s;
        else
            return (new StringBuilder()).append(s.substring(0, i)).append(s.substring(s1.length() + i, s.length())).toString();
    }

    public static BytesResult formatBytes(Resources resources, long l, int i)
    {
        RoundedBytesResult roundedbytesresult = RoundedBytesResult.roundBytes(l, i);
        Locale locale = resources.getConfiguration().getLocales().get(0);
        NumberFormat numberformat = getNumberFormatter(locale, roundedbytesresult.fractionDigits);
        String s = numberformat.format(roundedbytesresult.value);
        if(roundedbytesresult.units == MeasureUnit.BYTE || roundedbytesresult.units == PETABYTE)
        {
            resources = getSuffixOverride(resources, roundedbytesresult.units);
        } else
        {
            resources = deleteFirstFromString(formatMeasureShort(locale, numberformat, roundedbytesresult.value, roundedbytesresult.units), s);
            resources = SPACES_AND_CONTROLS.trim(resources).toString();
        }
        return new BytesResult(s, resources, roundedbytesresult.roundedBytes);
    }

    public static String formatFileSize(Context context, long l)
    {
        return formatFileSize(context, l, 0);
    }

    private static String formatFileSize(Context context, long l, int i)
    {
        if(context == null)
            return "";
        else
            return bidiWrap(context, formatRoundedBytesResult(context, RoundedBytesResult.roundBytes(l, i)));
    }

    public static String formatIpAddress(int i)
    {
        return NetworkUtils.intToInetAddress(i).getHostAddress();
    }

    private static String formatMeasureShort(Locale locale, NumberFormat numberformat, float f, MeasureUnit measureunit)
    {
        return MeasureFormat.getInstance(locale, android.icu.text.MeasureFormat.FormatWidth.SHORT, numberformat).format(new Measure(Float.valueOf(f), measureunit));
    }

    private static String formatRoundedBytesResult(Context context, RoundedBytesResult roundedbytesresult)
    {
        Locale locale = localeFromContext(context);
        NumberFormat numberformat = getNumberFormatter(locale, roundedbytesresult.fractionDigits);
        if(roundedbytesresult.units == MeasureUnit.BYTE || roundedbytesresult.units == PETABYTE)
            return context.getString(0x104021b, new Object[] {
                numberformat.format(roundedbytesresult.value), getSuffixOverride(context.getResources(), roundedbytesresult.units)
            });
        else
            return formatMeasureShort(locale, numberformat, roundedbytesresult.value, roundedbytesresult.units);
    }

    public static String formatShortElapsedTime(Context context, long l)
    {
        long l1 = l / 1000L;
        int i = 0;
        int j = 0;
        int k = 0;
        l = l1;
        if(l1 >= 0x15180L)
        {
            i = (int)(l1 / 0x15180L);
            l = l1 - (long)(0x15180 * i);
        }
        l1 = l;
        if(l >= 3600L)
        {
            j = (int)(l / 3600L);
            l1 = l - (long)(j * 3600);
        }
        l = l1;
        if(l1 >= 60L)
        {
            k = (int)(l1 / 60L);
            l = l1 - (long)(k * 60);
        }
        int i1 = (int)l;
        context = MeasureFormat.getInstance(localeFromContext(context), android.icu.text.MeasureFormat.FormatWidth.SHORT);
        if(i >= 2)
            return context.format(new Measure(Integer.valueOf(i + (j + 12) / 24), MeasureUnit.DAY));
        if(i > 0)
            return context.formatMeasures(new Measure[] {
                new Measure(Integer.valueOf(i), MeasureUnit.DAY), new Measure(Integer.valueOf(j), MeasureUnit.HOUR)
            });
        if(j >= 2)
            return context.format(new Measure(Integer.valueOf(j + (k + 30) / 60), MeasureUnit.HOUR));
        if(j > 0)
            return context.formatMeasures(new Measure[] {
                new Measure(Integer.valueOf(j), MeasureUnit.HOUR), new Measure(Integer.valueOf(k), MeasureUnit.MINUTE)
            });
        if(k >= 2)
            return context.format(new Measure(Integer.valueOf(k + (i1 + 30) / 60), MeasureUnit.MINUTE));
        if(k > 0)
            return context.formatMeasures(new Measure[] {
                new Measure(Integer.valueOf(k), MeasureUnit.MINUTE), new Measure(Integer.valueOf(i1), MeasureUnit.SECOND)
            });
        else
            return context.format(new Measure(Integer.valueOf(i1), MeasureUnit.SECOND));
    }

    public static String formatShortElapsedTimeRoundingUpToMinutes(Context context, long l)
    {
        l = ((l + 60000L) - 1L) / 60000L;
        if(l == 0L || l == 1L)
            return MeasureFormat.getInstance(localeFromContext(context), android.icu.text.MeasureFormat.FormatWidth.SHORT).format(new Measure(Long.valueOf(l), MeasureUnit.MINUTE));
        else
            return formatShortElapsedTime(context, l * 60000L);
    }

    public static String formatShortFileSize(Context context, long l)
    {
        return formatFileSize(context, l, 1);
    }

    private static NumberFormat getNumberFormatter(Locale locale, int i)
    {
        locale = NumberFormat.getInstance(locale);
        locale.setMinimumFractionDigits(i);
        locale.setMaximumFractionDigits(i);
        locale.setGroupingUsed(false);
        if(locale instanceof DecimalFormat)
            locale.setRoundingMode(4);
        return locale;
    }

    private static String getSuffixOverride(Resources resources, MeasureUnit measureunit)
    {
        if(measureunit == MeasureUnit.BYTE)
            return resources.getString(0x10400ed);
        else
            return resources.getString(0x1040511);
    }

    private static Locale localeFromContext(Context context)
    {
        return context.getResources().getConfiguration().getLocales().get(0);
    }

    public static final int FLAG_CALCULATE_ROUNDED = 2;
    public static final int FLAG_DEFAULT = 0;
    public static final int FLAG_SHORTER = 1;
    private static final int MILLIS_PER_MINUTE = 60000;
    private static final MeasureUnit PETABYTE = createPetaByte();
    private static final int SECONDS_PER_DAY = 0x15180;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final UnicodeSetSpanner SPACES_AND_CONTROLS = new UnicodeSetSpanner((new UnicodeSet("[[:Zs:][:Cf:]]")).freeze());

}
