// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation;

import miui.maml.elements.ScreenElement;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.animation:
//            PositionAnimation, BaseAnimation

public class SourcesAnimation extends PositionAnimation
{
    public static class Source extends BaseAnimation.AnimationItem
    {

        public static final String TAG_NAME = "Source";
        public String mSrc;

        public Source(BaseAnimation baseanimation, Element element)
        {
            super(baseanimation, element);
            mSrc = element.getAttribute("src");
        }
    }


    public SourcesAnimation(Element element, ScreenElement screenelement)
    {
        super(element, "Source", screenelement);
    }

    public final String getSrc()
    {
        return mCurrentSrc;
    }

    protected BaseAnimation.AnimationItem onCreateItem(BaseAnimation baseanimation, Element element)
    {
        return new Source(baseanimation, element);
    }

    protected void onTick(BaseAnimation.AnimationItem animationitem, BaseAnimation.AnimationItem animationitem1, float f)
    {
        if(animationitem1 == null)
        {
            setCurValue(0, 0.0D);
            setCurValue(1, 0.0D);
            return;
        } else
        {
            setCurValue(0, animationitem1.get(0));
            setCurValue(1, animationitem1.get(1));
            mCurrentSrc = ((Source)animationitem1).mSrc;
            return;
        }
    }

    public static final String TAG_NAME = "SourcesAnimation";
    private String mCurrentSrc;
}
