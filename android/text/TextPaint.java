// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.graphics.Paint;

public class TextPaint extends Paint
{

    public TextPaint()
    {
        density = 1.0F;
        underlineColor = 0;
    }

    public TextPaint(int i)
    {
        super(i);
        density = 1.0F;
        underlineColor = 0;
    }

    public TextPaint(Paint paint)
    {
        super(paint);
        density = 1.0F;
        underlineColor = 0;
    }

    public float getUnderlineThickness()
    {
        if(underlineColor != 0)
            return underlineThickness;
        else
            return super.getUnderlineThickness();
    }

    public boolean hasEqualAttributes(TextPaint textpaint)
    {
        boolean flag;
        if(bgColor == textpaint.bgColor && baselineShift == textpaint.baselineShift && linkColor == textpaint.linkColor && drawableState == textpaint.drawableState && density == textpaint.density && underlineColor == textpaint.underlineColor && underlineThickness == textpaint.underlineThickness)
            flag = super.hasEqualAttributes(textpaint);
        else
            flag = false;
        return flag;
    }

    public void set(TextPaint textpaint)
    {
        super.set(textpaint);
        bgColor = textpaint.bgColor;
        baselineShift = textpaint.baselineShift;
        linkColor = textpaint.linkColor;
        drawableState = textpaint.drawableState;
        density = textpaint.density;
        underlineColor = textpaint.underlineColor;
        underlineThickness = textpaint.underlineThickness;
    }

    public void setUnderlineText(int i, float f)
    {
        underlineColor = i;
        underlineThickness = f;
    }

    public int baselineShift;
    public int bgColor;
    public float density;
    public int drawableState[];
    public int linkColor;
    public int underlineColor;
    public float underlineThickness;
}
