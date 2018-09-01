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

public class CircleScreenElement extends GeometryScreenElement
{

    public CircleScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mRadiusExp = Expression.build(screenelementroot.getVariables(), getAttr(element, "r"));
        mAlign = ScreenElement.Align.CENTER;
        mAlignV = ScreenElement.AlignV.CENTER;
    }

    private final float getRadius()
    {
        float f;
        if(mRadiusExp != null)
            f = (float)mRadiusExp.evaluate();
        else
            f = 0.0F;
        return scale(f);
    }

    protected void onDraw(Canvas canvas, GeometryScreenElement.DrawMode drawmode)
    {
        float f = getRadius();
        if(drawmode != GeometryScreenElement.DrawMode.STROKE_OUTER) goto _L2; else goto _L1
_L1:
        float f1 = f + mWeight / 2.0F;
_L4:
        canvas.drawCircle(0.0F, 0.0F, f1, mPaint);
        return;
_L2:
        f1 = f;
        if(drawmode == GeometryScreenElement.DrawMode.STROKE_INNER)
            f1 = f - mWeight / 2.0F;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final String TAG_NAME = "Circle";
    private Expression mRadiusExp;
}
