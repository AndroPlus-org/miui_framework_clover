// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation.interpolater;

import android.text.TextUtils;
import android.view.animation.Interpolator;
import miui.maml.data.*;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.animation.interpolater:
//            InterpolatorFactory

public class InterpolatorHelper
{

    public InterpolatorHelper(Variables variables, String s, String s1)
    {
        mInterpolator = InterpolatorFactory.create(s);
        mEaseExp = Expression.build(variables, s1);
        if(mEaseExp != null)
            mRatioVar = new IndexedVariable("__ratio", variables, true);
    }

    public static InterpolatorHelper create(Variables variables, String s, String s1)
    {
        if(TextUtils.isEmpty(s) && TextUtils.isEmpty(s1))
            return null;
        else
            return new InterpolatorHelper(variables, s, s1);
    }

    public static InterpolatorHelper create(Variables variables, Element element)
    {
        return create(variables, element.getAttribute("easeType"), element.getAttribute("easeExp"));
    }

    public float get(float f)
    {
        if(mEaseExp != null)
        {
            mRatioVar.set(f);
            return (float)mEaseExp.evaluate();
        }
        if(mInterpolator != null)
            return mInterpolator.getInterpolation(f);
        else
            return f;
    }

    public boolean isValid()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mEaseExp == null)
            if(mInterpolator != null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private static final String VAR_RATIO = "__ratio";
    private Expression mEaseExp;
    private Interpolator mInterpolator;
    private IndexedVariable mRatioVar;
}
