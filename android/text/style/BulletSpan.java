// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.*;
import android.os.Parcel;
import android.text.*;

// Referenced classes of package android.text.style:
//            LeadingMarginSpan

public class BulletSpan
    implements LeadingMarginSpan, ParcelableSpan
{

    public BulletSpan()
    {
        mGapWidth = 2;
        mWantColor = false;
        mColor = 0;
    }

    public BulletSpan(int i)
    {
        mGapWidth = i;
        mWantColor = false;
        mColor = 0;
    }

    public BulletSpan(int i, int j)
    {
        mGapWidth = i;
        mWantColor = true;
        mColor = j;
    }

    public BulletSpan(Parcel parcel)
    {
        boolean flag = false;
        super();
        mGapWidth = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        mWantColor = flag;
        mColor = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int j, int k, int l, int i1, 
            CharSequence charsequence, int j1, int k1, boolean flag, Layout layout)
    {
        if(((Spanned)charsequence).getSpanStart(this) == j1)
        {
            charsequence = paint.getStyle();
            l = 0;
            if(mWantColor)
            {
                l = paint.getColor();
                paint.setColor(mColor);
            }
            paint.setStyle(android.graphics.Paint.Style.FILL);
            if(canvas.isHardwareAccelerated())
            {
                if(sBulletPath == null)
                {
                    sBulletPath = new Path();
                    sBulletPath.addCircle(0.0F, 0.0F, 3.6F, android.graphics.Path.Direction.CW);
                }
                canvas.save();
                canvas.translate(j * 3 + i, (float)(k + i1) / 2.0F);
                canvas.drawPath(sBulletPath, paint);
                canvas.restore();
            } else
            {
                canvas.drawCircle(j * 3 + i, (float)(k + i1) / 2.0F, 3F, paint);
            }
            if(mWantColor)
                paint.setColor(l);
            paint.setStyle(charsequence);
        }
    }

    public int getLeadingMargin(boolean flag)
    {
        return mGapWidth + 6;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 8;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeInt(mGapWidth);
        if(mWantColor)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mColor);
    }

    private static final int BULLET_RADIUS = 3;
    public static final int STANDARD_GAP_WIDTH = 2;
    private static Path sBulletPath = null;
    private final int mColor;
    private final int mGapWidth;
    private final boolean mWantColor;

}
