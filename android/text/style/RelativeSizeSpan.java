// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            MetricAffectingSpan

public class RelativeSizeSpan extends MetricAffectingSpan
    implements ParcelableSpan
{

    public RelativeSizeSpan(float f)
    {
        mProportion = f;
    }

    public RelativeSizeSpan(Parcel parcel)
    {
        mProportion = parcel.readFloat();
    }

    public int describeContents()
    {
        return 0;
    }

    public float getSizeChange()
    {
        return mProportion;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 3;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        textpaint.setTextSize(textpaint.getTextSize() * mProportion);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        textpaint.setTextSize(textpaint.getTextSize() * mProportion);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeFloat(mProportion);
    }

    private final float mProportion;
}
