// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.graphics.RectF;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            GeometryScreenElement

public class EllipseScreenElement extends GeometryScreenElement
{

    public EllipseScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mAlign = ScreenElement.Align.CENTER;
        mAlignV = ScreenElement.AlignV.CENTER;
    }

    protected void onDraw(Canvas canvas, GeometryScreenElement.DrawMode drawmode)
    {
        float f;
        float f1;
        f = getWidth();
        f1 = getHeight();
        if(f < 0.0F || f1 < 0.0F)
            return;
        if(drawmode != GeometryScreenElement.DrawMode.STROKE_OUTER) goto _L2; else goto _L1
_L1:
        float f2;
        float f3;
        f2 = f + mWeight;
        f3 = f1 + mWeight;
_L4:
        f1 = 0.0F - f2 / 2.0F;
        f = 0.0F - f3 / 2.0F;
        canvas.drawOval(new RectF(f1, f, f1 + f2, f + f3), mPaint);
        return;
_L2:
        f3 = f1;
        f2 = f;
        if(drawmode != GeometryScreenElement.DrawMode.STROKE_INNER) goto _L4; else goto _L3
_L3:
        f2 = f - mWeight;
        f1 -= mWeight;
        if(f2 < 0.0F)
            break; /* Loop/switch isn't completed */
        f3 = f1;
        if(f1 >= 0.0F) goto _L4; else goto _L5
_L5:
    }

    public static final String TAG_NAME = "Ellipse";
}
