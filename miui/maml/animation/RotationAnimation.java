// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation;

import miui.maml.elements.ScreenElement;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.animation:
//            BaseAnimation

public class RotationAnimation extends BaseAnimation
{

    public RotationAnimation(Element element, ScreenElement screenelement)
    {
        super(element, "Rotation", "angle", screenelement);
    }

    public final float getAngle()
    {
        return (float)getCurValue(0);
    }

    public static final String INNER_TAG_NAME = "Rotation";
    public static final String TAG_NAME = "RotationAnimation";
}
