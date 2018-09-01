// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation;

import miui.maml.elements.ScreenElement;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.animation:
//            BaseAnimation

public class PositionAnimation extends BaseAnimation
{

    public PositionAnimation(Element element, String s, ScreenElement screenelement)
    {
        super(element, s, new String[] {
            "x", "y"
        }, screenelement);
    }

    public PositionAnimation(Element element, ScreenElement screenelement)
    {
        this(element, "Position", screenelement);
    }

    public final double getX()
    {
        return getCurValue(0);
    }

    public final double getY()
    {
        return getCurValue(1);
    }

    public static final String INNER_TAG_NAME = "Position";
    public static final String TAG_NAME = "PositionAnimation";
}
