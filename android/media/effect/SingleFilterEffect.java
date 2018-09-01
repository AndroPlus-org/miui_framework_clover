// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect;

import android.filterfw.core.*;

// Referenced classes of package android.media.effect:
//            FilterEffect, EffectContext

public class SingleFilterEffect extends FilterEffect
{

    public transient SingleFilterEffect(EffectContext effectcontext, String s, Class class1, String s1, String s2, Object aobj[])
    {
        super(effectcontext, s);
        mInputName = s1;
        mOutputName = s2;
        effectcontext = class1.getSimpleName();
        effectcontext = FilterFactory.sharedFactory().createFilterByClass(class1, effectcontext);
        effectcontext.initWithAssignmentList(aobj);
        mFunction = new FilterFunction(getFilterContext(), effectcontext);
    }

    public void apply(int i, int j, int k, int l)
    {
        beginGLEffect();
        Frame frame = frameFromTexture(i, j, k);
        Frame frame1 = frameFromTexture(l, j, k);
        Frame frame2 = mFunction.executeWithArgList(new Object[] {
            mInputName, frame
        });
        frame1.setDataFromFrame(frame2);
        frame.release();
        frame1.release();
        frame2.release();
        endGLEffect();
    }

    public void release()
    {
        mFunction.tearDown();
        mFunction = null;
    }

    public void setParameter(String s, Object obj)
    {
        mFunction.setInputValue(s, obj);
    }

    protected FilterFunction mFunction;
    protected String mInputName;
    protected String mOutputName;
}
