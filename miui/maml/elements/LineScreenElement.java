// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            GeometryScreenElement

public class LineScreenElement extends GeometryScreenElement
{

    public LineScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mEndXExp = Expression.build(screenelementroot.getVariables(), element.getAttribute("x1"));
        mEndYExp = Expression.build(screenelementroot.getVariables(), element.getAttribute("y1"));
    }

    private final float getEndX()
    {
        double d;
        if(mEndXExp != null)
            d = mEndXExp.evaluate();
        else
            d = 0.0D;
        return scale(d);
    }

    private final float getEndY()
    {
        double d;
        if(mEndYExp != null)
            d = mEndYExp.evaluate();
        else
            d = 0.0D;
        return scale(d);
    }

    protected void onDraw(Canvas canvas, GeometryScreenElement.DrawMode drawmode)
    {
        canvas.drawLine(0.0F, 0.0F, getEndX() - getX(), getEndY() - getY(), mPaint);
    }

    public static final String TAG_NAME = "Line";
    private Expression mEndXExp;
    private Expression mEndYExp;
}
