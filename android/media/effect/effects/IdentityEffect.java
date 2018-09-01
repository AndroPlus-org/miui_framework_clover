// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect.effects;

import android.filterfw.core.Frame;
import android.media.effect.EffectContext;
import android.media.effect.FilterEffect;

public class IdentityEffect extends FilterEffect
{

    public IdentityEffect(EffectContext effectcontext, String s)
    {
        super(effectcontext, s);
    }

    public void apply(int i, int j, int k, int l)
    {
        beginGLEffect();
        Frame frame = frameFromTexture(i, j, k);
        Frame frame1 = frameFromTexture(l, j, k);
        frame1.setDataFromFrame(frame);
        frame.release();
        frame1.release();
        endGLEffect();
    }

    public void release()
    {
    }

    public void setParameter(String s, Object obj)
    {
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown parameter ").append(s).append(" for IdentityEffect!").toString());
    }
}
