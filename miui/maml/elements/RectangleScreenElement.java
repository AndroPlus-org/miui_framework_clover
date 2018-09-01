// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            GeometryScreenElement

public class RectangleScreenElement extends GeometryScreenElement
{

    public RectangleScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        resolveCornerRadius(element);
    }

    private void resolveCornerRadius(Element element)
    {
        element = getAttr(element, "cornerRadius").split(",");
        if(element.length < 1)
            return;
        if(element.length != 1)
            break MISSING_BLOCK_LABEL_49;
        float f = scale(Float.parseFloat(element[0]));
        mCornerRadiusY = f;
        mCornerRadiusX = f;
_L1:
        return;
        try
        {
            mCornerRadiusX = scale(Float.parseFloat(element[0]));
            mCornerRadiusY = scale(Float.parseFloat(element[1]));
        }
        // Misplaced declaration of an exception variable
        catch(Element element)
        {
            Log.w("RectangleScreenElement", "illegal number format of cornerRadius.");
        }
          goto _L1
    }

    protected void onDraw(Canvas canvas, GeometryScreenElement.DrawMode drawmode)
    {
        float f = getWidth();
        float f1 = getHeight();
        float f2 = getLeft(0.0F, f);
        float f3 = getTop(0.0F, f1);
        float f4;
        float f5;
        float f6;
        float f7;
        if(f <= 0.0F)
            f = 0.0F;
        f4 = f2 + f;
        if(f1 > 0.0F)
            f = f1;
        else
            f = 0.0F;
        f5 = f3 + f;
        if(drawmode == GeometryScreenElement.DrawMode.STROKE_OUTER)
        {
            f6 = f2 - mWeight / 2.0F;
            f = f3 - mWeight / 2.0F;
            f1 = f4 + mWeight / 2.0F;
            f7 = f5 + mWeight / 2.0F;
        } else
        {
            f6 = f2;
            f = f3;
            f1 = f4;
            f7 = f5;
            if(drawmode == GeometryScreenElement.DrawMode.STROKE_INNER)
            {
                f6 = f2 + mWeight / 2.0F;
                f = f3 + mWeight / 2.0F;
                f1 = f4 - mWeight / 2.0F;
                f7 = f5 - mWeight / 2.0F;
            }
        }
        if(mCornerRadiusX <= 0.0F || mCornerRadiusY <= 0.0F)
            canvas.drawRect(f6, f, f1, f7, mPaint);
        else
            canvas.drawRoundRect(new RectF(f6, f, f1, f7), mCornerRadiusX, mCornerRadiusY, mPaint);
    }

    private static final String LOG_TAG = "RectangleScreenElement";
    public static final String TAG_NAME = "Rectangle";
    private float mCornerRadiusX;
    private float mCornerRadiusY;
}
