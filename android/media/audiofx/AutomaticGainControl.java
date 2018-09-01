// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.util.Log;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class AutomaticGainControl extends AudioEffect
{

    private AutomaticGainControl(int i)
        throws IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        super(EFFECT_TYPE_AGC, EFFECT_TYPE_NULL, 0, i);
    }

    public static AutomaticGainControl create(int i)
    {
        AutomaticGainControl automaticgaincontrol = null;
        AutomaticGainControl automaticgaincontrol1;
        automaticgaincontrol1 = JVM INSTR new #2   <Class AutomaticGainControl>;
        automaticgaincontrol1.AutomaticGainControl(i);
        automaticgaincontrol = automaticgaincontrol1;
_L2:
        return automaticgaincontrol;
        Object obj;
        obj;
        Log.w("AutomaticGainControl", "not enough memory");
        continue; /* Loop/switch isn't completed */
        obj;
        Log.w("AutomaticGainControl", "not enough resources");
        continue; /* Loop/switch isn't completed */
        obj;
        Log.w("AutomaticGainControl", (new StringBuilder()).append("not implemented on this device ").append(null).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean isAvailable()
    {
        return AudioEffect.isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_AGC);
    }

    private static final String TAG = "AutomaticGainControl";
}
