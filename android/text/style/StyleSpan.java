// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            MetricAffectingSpan

public class StyleSpan extends MetricAffectingSpan
    implements ParcelableSpan
{

    public StyleSpan(int i)
    {
        mStyle = i;
    }

    public StyleSpan(Parcel parcel)
    {
        mStyle = parcel.readInt();
    }

    private static void apply(Paint paint, int i)
    {
        Typeface typeface = paint.getTypeface();
        int j;
        if(typeface == null)
            j = 0;
        else
            j = typeface.getStyle();
        i = j | i;
        if(typeface == null)
            typeface = Typeface.defaultFromStyle(i);
        else
            typeface = Typeface.create(typeface, i);
        i &= typeface.getStyle();
        if((i & 1) != 0)
            paint.setFakeBoldText(true);
        if((i & 2) != 0)
            paint.setTextSkewX(-0.25F);
        paint.setTypeface(typeface);
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
        return 7;
    }

    public int getStyle()
    {
        return mStyle;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        apply(textpaint, mStyle);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        apply(textpaint, mStyle);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeInt(mStyle);
    }

    private final int mStyle;
}
