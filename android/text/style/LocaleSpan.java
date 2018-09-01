// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.Paint;
import android.os.LocaleList;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import com.android.internal.util.Preconditions;
import java.util.Locale;

// Referenced classes of package android.text.style:
//            MetricAffectingSpan

public class LocaleSpan extends MetricAffectingSpan
    implements ParcelableSpan
{

    public LocaleSpan(LocaleList localelist)
    {
        Preconditions.checkNotNull(localelist, "locales cannot be null");
        mLocales = localelist;
    }

    public LocaleSpan(Parcel parcel)
    {
        mLocales = (LocaleList)LocaleList.CREATOR.createFromParcel(parcel);
    }

    public LocaleSpan(Locale locale)
    {
        if(locale == null)
            locale = LocaleList.getEmptyLocaleList();
        else
            locale = new LocaleList(new Locale[] {
                locale
            });
        mLocales = locale;
    }

    private static void apply(Paint paint, LocaleList localelist)
    {
        paint.setTextLocales(localelist);
    }

    public int describeContents()
    {
        return 0;
    }

    public Locale getLocale()
    {
        return mLocales.get(0);
    }

    public LocaleList getLocales()
    {
        return mLocales;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 23;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        apply(textpaint, mLocales);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        apply(textpaint, mLocales);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        mLocales.writeToParcel(parcel, i);
    }

    private final LocaleList mLocales;
}
