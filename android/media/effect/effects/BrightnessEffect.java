// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect.effects;

import android.filterpacks.imageproc.BrightnessFilter;
import android.media.effect.EffectContext;
import android.media.effect.SingleFilterEffect;

public class BrightnessEffect extends SingleFilterEffect
{

    public BrightnessEffect(EffectContext effectcontext, String s)
    {
        super(effectcontext, s, android/filterpacks/imageproc/BrightnessFilter, "image", "image", new Object[0]);
    }
}
