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

public class TypefaceSpan extends MetricAffectingSpan
    implements ParcelableSpan
{

    public TypefaceSpan(Parcel parcel)
    {
        mFamily = parcel.readString();
    }

    public TypefaceSpan(String s)
    {
        mFamily = s;
    }

    private static void apply(Paint paint, String s)
    {
        Typeface typeface = paint.getTypeface();
        int i;
        if(typeface == null)
            i = 0;
        else
            i = typeface.getStyle();
        s = Typeface.create(s, i);
        i &= s.getStyle();
        if((i & 1) != 0)
            paint.setFakeBoldText(true);
        if((i & 2) != 0)
            paint.setTextSkewX(-0.25F);
        paint.setTypeface(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getFamily()
    {
        return mFamily;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 13;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        apply(textpaint, mFamily);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        apply(textpaint, mFamily);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeString(mFamily);
    }

    private final String mFamily;
}
