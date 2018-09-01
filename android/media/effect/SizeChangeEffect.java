// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect;

import android.filterfw.core.*;

// Referenced classes of package android.media.effect:
//            SingleFilterEffect, EffectContext

public class SizeChangeEffect extends SingleFilterEffect
{

    public transient SizeChangeEffect(EffectContext effectcontext, String s, Class class1, String s1, String s2, Object aobj[])
    {
        super(effectcontext, s, class1, s1, s2, aobj);
    }

    public void apply(int i, int j, int k, int l)
    {
        beginGLEffect();
        Frame frame = frameFromTexture(i, j, k);
        Frame frame1 = mFunction.executeWithArgList(new Object[] {
            mInputName, frame
        });
        Frame frame2 = frameFromTexture(l, frame1.getFormat().getWidth(), frame1.getFormat().getHeight());
        frame2.setDataFromFrame(frame1);
        frame.release();
        frame2.release();
        frame1.release();
        endGLEffect();
    }
}
