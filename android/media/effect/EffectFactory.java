// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect;

import java.lang.reflect.Constructor;

// Referenced classes of package android.media.effect:
//            Effect, EffectContext

public class EffectFactory
{

    EffectFactory(EffectContext effectcontext)
    {
        mEffectContext = effectcontext;
    }

    private static Class getEffectClassByName(String s)
    {
        Object obj;
        ClassLoader classloader;
        String as[];
        int i;
        int j;
        obj = null;
        classloader = Thread.currentThread().getContextClassLoader();
        as = EFFECT_PACKAGES;
        i = 0;
        j = as.length;
_L2:
        Object obj1;
        String s1;
        obj1 = obj;
        if(i >= j)
            break MISSING_BLOCK_LABEL_79;
        s1 = as[i];
        obj1 = JVM INSTR new #126 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = classloader.loadClass(((StringBuilder) (obj1)).append(s1).append(s).toString());
        obj = obj1;
        obj1 = obj;
        if(obj == null)
            break MISSING_BLOCK_LABEL_87;
        obj1 = obj;
        return ((Class) (obj1));
        obj1;
        obj1 = obj;
        i++;
        obj = obj1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Effect instantiateEffect(Class class1, String s)
    {
        Constructor constructor;
        try
        {
            class1.asSubclass(android/media/effect/Effect);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Attempting to allocate effect '").append(class1).append("' which is not a subclass of Effect!").toString(), s);
        }
        try
        {
            constructor = class1.getConstructor(new Class[] {
                android/media/effect/EffectContext, java/lang/String
            });
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException((new StringBuilder()).append("The effect class '").append(class1).append("' does not have ").append("the required constructor.").toString(), s);
        }
        try
        {
            s = (Effect)constructor.newInstance(new Object[] {
                mEffectContext, s
            });
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException((new StringBuilder()).append("There was an error constructing the effect '").append(class1).append("'!").toString(), s);
        }
        return s;
    }

    public static boolean isEffectSupported(String s)
    {
        boolean flag;
        if(getEffectClassByName(s) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Effect createEffect(String s)
    {
        Class class1 = getEffectClassByName(s);
        if(class1 == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot instantiate unknown effect '").append(s).append("'!").toString());
        else
            return instantiateEffect(class1, s);
    }

    public static final String EFFECT_AUTOFIX = "android.media.effect.effects.AutoFixEffect";
    public static final String EFFECT_BACKDROPPER = "android.media.effect.effects.BackDropperEffect";
    public static final String EFFECT_BITMAPOVERLAY = "android.media.effect.effects.BitmapOverlayEffect";
    public static final String EFFECT_BLACKWHITE = "android.media.effect.effects.BlackWhiteEffect";
    public static final String EFFECT_BRIGHTNESS = "android.media.effect.effects.BrightnessEffect";
    public static final String EFFECT_CONTRAST = "android.media.effect.effects.ContrastEffect";
    public static final String EFFECT_CROP = "android.media.effect.effects.CropEffect";
    public static final String EFFECT_CROSSPROCESS = "android.media.effect.effects.CrossProcessEffect";
    public static final String EFFECT_DOCUMENTARY = "android.media.effect.effects.DocumentaryEffect";
    public static final String EFFECT_DUOTONE = "android.media.effect.effects.DuotoneEffect";
    public static final String EFFECT_FILLLIGHT = "android.media.effect.effects.FillLightEffect";
    public static final String EFFECT_FISHEYE = "android.media.effect.effects.FisheyeEffect";
    public static final String EFFECT_FLIP = "android.media.effect.effects.FlipEffect";
    public static final String EFFECT_GRAIN = "android.media.effect.effects.GrainEffect";
    public static final String EFFECT_GRAYSCALE = "android.media.effect.effects.GrayscaleEffect";
    public static final String EFFECT_IDENTITY = "IdentityEffect";
    public static final String EFFECT_LOMOISH = "android.media.effect.effects.LomoishEffect";
    public static final String EFFECT_NEGATIVE = "android.media.effect.effects.NegativeEffect";
    private static final String EFFECT_PACKAGES[] = {
        "android.media.effect.effects.", ""
    };
    public static final String EFFECT_POSTERIZE = "android.media.effect.effects.PosterizeEffect";
    public static final String EFFECT_REDEYE = "android.media.effect.effects.RedEyeEffect";
    public static final String EFFECT_ROTATE = "android.media.effect.effects.RotateEffect";
    public static final String EFFECT_SATURATE = "android.media.effect.effects.SaturateEffect";
    public static final String EFFECT_SEPIA = "android.media.effect.effects.SepiaEffect";
    public static final String EFFECT_SHARPEN = "android.media.effect.effects.SharpenEffect";
    public static final String EFFECT_STRAIGHTEN = "android.media.effect.effects.StraightenEffect";
    public static final String EFFECT_TEMPERATURE = "android.media.effect.effects.ColorTemperatureEffect";
    public static final String EFFECT_TINT = "android.media.effect.effects.TintEffect";
    public static final String EFFECT_VIGNETTE = "android.media.effect.effects.VignetteEffect";
    private EffectContext mEffectContext;

}
