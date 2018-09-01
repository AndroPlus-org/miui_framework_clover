// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            MetricAffectingSpan

public class AbsoluteSizeSpan extends MetricAffectingSpan
    implements ParcelableSpan
{

    public AbsoluteSizeSpan(int i)
    {
        mSize = i;
    }

    public AbsoluteSizeSpan(int i, boolean flag)
    {
        mSize = i;
        mDip = flag;
    }

    public AbsoluteSizeSpan(Parcel parcel)
    {
        boolean flag = false;
        super();
        mSize = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        mDip = flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean getDip()
    {
        return mDip;
    }

    public int getSize()
    {
        return mSize;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 16;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        if(mDip)
            textpaint.setTextSize((float)mSize * textpaint.density);
        else
            textpaint.setTextSize(mSize);
    }

    public void updateMeasureState(TextPaint textpaint)
    {
        if(mDip)
            textpaint.setTextSize((float)mSize * textpaint.density);
        else
            textpaint.setTextSize(mSize);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeInt(mSize);
        if(mDip)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    private boolean mDip;
    private final int mSize;
}
