// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation;

import android.text.TextUtils;
import miui.maml.elements.ScreenElement;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.animation:
//            BaseAnimation

public class AlphaAnimation extends BaseAnimation
{

    public AlphaAnimation(Element element, ScreenElement screenelement)
    {
        super(element, "Alpha", "a", screenelement);
        element = element.getAttribute("delayValue");
        if(TextUtils.isEmpty(element)) goto _L2; else goto _L1
_L1:
        mDelayValue = Integer.parseInt(element);
_L4:
        return;
_L2:
        element = getItem(0);
        if(element != null)
            mDelayValue = (int)element.get(0);
        continue; /* Loop/switch isn't completed */
        element;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public final int getAlpha()
    {
        return (int)getCurValue(0);
    }

    protected double getDefaultValue()
    {
        return 255D;
    }

    protected double getDelayValue(int i)
    {
        return (double)mDelayValue;
    }

    public static final String INNER_TAG_NAME = "Alpha";
    public static final String TAG_NAME = "AlphaAnimation";
    private int mDelayValue;
}
