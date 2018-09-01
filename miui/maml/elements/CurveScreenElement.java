// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            GeometryScreenElement

public class CurveScreenElement extends GeometryScreenElement
{

    public CurveScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
    }

    protected void onDraw(Canvas canvas, GeometryScreenElement.DrawMode drawmode)
    {
    }

    public static final String TAG_NAME = "Curve";
}
