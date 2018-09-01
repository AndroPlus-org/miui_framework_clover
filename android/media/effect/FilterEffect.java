// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

// Referenced classes of package android.media.effect:
//            Effect, EffectContext

public abstract class FilterEffect extends Effect
{

    protected FilterEffect(EffectContext effectcontext, String s)
    {
        mEffectContext = effectcontext;
        mName = s;
    }

    protected void beginGLEffect()
    {
        mEffectContext.assertValidGLState();
        mEffectContext.saveGLState();
    }

    protected void endGLEffect()
    {
        mEffectContext.restoreGLState();
    }

    protected Frame frameFromTexture(int i, int j, int k)
    {
        Frame frame = getFilterContext().getFrameManager().newBoundFrame(ImageFormat.create(j, k, 3, 3), 100, i);
        frame.setTimestamp(-1L);
        return frame;
    }

    protected FilterContext getFilterContext()
    {
        return mEffectContext.mFilterContext;
    }

    public String getName()
    {
        return mName;
    }

    protected EffectContext mEffectContext;
    private String mName;
}
