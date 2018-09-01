// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            MetricAffectingSpan

public class SubscriptSpan extends MetricAffectingSpan
    implements ParcelableSpan
{

    public SubscriptSpan()
    {
    }

    public SubscriptSpan(Parcel parcel)
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 15;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        textpaint.baselineShift = textpaint.baselineShift - (int)(textpaint.ascent() / 2.0F);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        textpaint.baselineShift = textpaint.baselineShift - (int)(textpaint.ascent() / 2.0F);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
    }
}
