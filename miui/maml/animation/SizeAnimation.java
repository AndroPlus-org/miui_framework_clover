// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation;

import java.util.Iterator;
import miui.maml.elements.ScreenElement;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.animation:
//            BaseAnimation

public class SizeAnimation extends BaseAnimation
{

    public SizeAnimation(Element element, ScreenElement screenelement)
    {
        super(element, "Size", new String[] {
            "w", "h"
        }, screenelement);
        element = mItems.iterator();
        do
        {
            if(!element.hasNext())
                break;
            screenelement = (BaseAnimation.AnimationItem)element.next();
            if(screenelement.get(0) > mMaxW)
                mMaxW = screenelement.get(0);
            if(screenelement.get(1) > mMaxH)
                mMaxH = screenelement.get(1);
        } while(true);
    }

    public final double getHeight()
    {
        return getCurValue(1);
    }

    public final double getMaxHeight()
    {
        return mMaxH;
    }

    public final double getMaxWidth()
    {
        return mMaxW;
    }

    public final double getWidth()
    {
        return getCurValue(0);
    }

    public static final String INNER_TAG_NAME = "Size";
    public static final String TAG_NAME = "SizeAnimation";
    private double mMaxH;
    private double mMaxW;
}
