// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.util.Log;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class NoiseSuppressor extends AudioEffect
{

    private NoiseSuppressor(int i)
        throws IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        super(EFFECT_TYPE_NS, EFFECT_TYPE_NULL, 0, i);
    }

    public static NoiseSuppressor create(int i)
    {
        NoiseSuppressor noisesuppressor = null;
        NoiseSuppressor noisesuppressor1;
        noisesuppressor1 = JVM INSTR new #2   <Class NoiseSuppressor>;
        noisesuppressor1.NoiseSuppressor(i);
        noisesuppressor = noisesuppressor1;
_L2:
        return noisesuppressor;
        Object obj;
        obj;
        Log.w("NoiseSuppressor", "not enough memory");
        continue; /* Loop/switch isn't completed */
        obj;
        Log.w("NoiseSuppressor", "not enough resources");
        continue; /* Loop/switch isn't completed */
        obj;
        Log.w("NoiseSuppressor", (new StringBuilder()).append("not implemented on this device ").append(null).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean isAvailable()
    {
        return AudioEffect.isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_NS);
    }

    private static final String TAG = "NoiseSuppressor";
}
