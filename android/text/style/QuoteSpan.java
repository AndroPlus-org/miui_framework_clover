// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.text.Layout;
import android.text.ParcelableSpan;

// Referenced classes of package android.text.style:
//            LeadingMarginSpan

public class QuoteSpan
    implements LeadingMarginSpan, ParcelableSpan
{

    public QuoteSpan()
    {
        mColor = 0xff0000ff;
    }

    public QuoteSpan(int i)
    {
        mColor = i;
    }

    public QuoteSpan(Parcel parcel)
    {
        mColor = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int j, int k, int l, int i1, 
            CharSequence charsequence, int j1, int k1, boolean flag, Layout layout)
    {
        charsequence = paint.getStyle();
        l = paint.getColor();
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setColor(mColor);
        canvas.drawRect(i, k, j * 2 + i, i1, paint);
        paint.setStyle(charsequence);
        paint.setColor(l);
    }

    public int getColor()
    {
        return mColor;
    }

    public int getLeadingMargin(boolean flag)
    {
        return 4;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 9;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeInt(mColor);
    }

    private static final int GAP_WIDTH = 2;
    private static final int STRIPE_WIDTH = 2;
    private final int mColor;
}
