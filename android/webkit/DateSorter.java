// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Calendar;
import java.util.Locale;
import libcore.icu.LocaleData;

public class DateSorter
{

    public DateSorter(Context context)
    {
        mBins = new long[4];
        mLabels = new String[5];
        Resources resources = context.getResources();
        Object obj = Calendar.getInstance();
        beginningOfDay(((Calendar) (obj)));
        mBins[0] = ((Calendar) (obj)).getTimeInMillis();
        ((Calendar) (obj)).add(6, -1);
        mBins[1] = ((Calendar) (obj)).getTimeInMillis();
        ((Calendar) (obj)).add(6, -6);
        mBins[2] = ((Calendar) (obj)).getTimeInMillis();
        ((Calendar) (obj)).add(6, 7);
        ((Calendar) (obj)).add(2, -1);
        mBins[3] = ((Calendar) (obj)).getTimeInMillis();
        Locale locale = resources.getConfiguration().locale;
        obj = locale;
        if(locale == null)
            obj = Locale.getDefault();
        obj = LocaleData.get(((Locale) (obj)));
        mLabels[0] = ((LocaleData) (obj)).today;
        mLabels[1] = ((LocaleData) (obj)).yesterday;
        obj = resources.getQuantityString(0x1150015, 7);
        mLabels[2] = String.format(((String) (obj)), new Object[] {
            Integer.valueOf(7)
        });
        mLabels[3] = context.getString(0x10402f0);
        mLabels[4] = context.getString(0x10403fd);
    }

    private void beginningOfDay(Calendar calendar)
    {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
    }

    public long getBoundary(int i)
    {
        int j;
label0:
        {
            if(i >= 0)
            {
                j = i;
                if(i <= 4)
                    break label0;
            }
            j = 0;
        }
        if(j == 4)
            return 0x8000000000000000L;
        else
            return mBins[j];
    }

    public int getIndex(long l)
    {
        for(int i = 0; i < 4; i++)
            if(l > mBins[i])
                return i;

        return 4;
    }

    public String getLabel(int i)
    {
        if(i < 0 || i >= 5)
            return "";
        else
            return mLabels[i];
    }

    public static final int DAY_COUNT = 5;
    private static final String LOGTAG = "webkit";
    private static final int NUM_DAYS_AGO = 7;
    private long mBins[];
    private String mLabels[];
}
