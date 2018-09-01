// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation;

import miui.maml.elements.ScreenElement;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.animation:
//            BaseAnimation

public class ScaleAnimation extends BaseAnimation
{

    public ScaleAnimation(Element element, ScreenElement screenelement)
    {
        super(element, "Item", new String[] {
            "value", "x", "y"
        }, screenelement);
        element = getItem(0);
        mDelayValueX = getItemX(element);
        mDelayValueY = getItemY(element);
    }

    private double getItemX(BaseAnimation.AnimationItem animationitem)
    {
        double d;
        if(animationitem == null)
            d = 1.0D;
        else
        if(animationitem.attrExists(0))
            d = animationitem.get(0);
        else
            d = animationitem.get(1);
        return d;
    }

    private double getItemY(BaseAnimation.AnimationItem animationitem)
    {
        double d;
        if(animationitem == null)
            d = 1.0D;
        else
        if(animationitem.attrExists(0))
            d = animationitem.get(0);
        else
            d = animationitem.get(2);
        return d;
    }

    protected double getDelayValue(int i)
    {
        double d;
        if(i == 0 || i == 1)
            d = mDelayValueX;
        else
            d = mDelayValueY;
        return d;
    }

    public final double getScaleX()
    {
        return getCurValue(1);
    }

    public final double getScaleY()
    {
        return getCurValue(2);
    }

    protected void onTick(BaseAnimation.AnimationItem animationitem, BaseAnimation.AnimationItem animationitem1, float f)
    {
        if(animationitem == null && animationitem1 == null)
        {
            return;
        } else
        {
            double d = getItemX(animationitem);
            setCurValue(1, (getItemX(animationitem1) - d) * (double)f + d);
            d = getItemY(animationitem);
            setCurValue(2, (getItemY(animationitem1) - d) * (double)f + d);
            return;
        }
    }

    public static final String TAG_NAME = "ScaleAnimation";
    private double mDelayValueX;
    private double mDelayValueY;
}
