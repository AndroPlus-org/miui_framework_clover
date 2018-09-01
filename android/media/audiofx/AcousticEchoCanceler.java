// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.util.Log;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class AcousticEchoCanceler extends AudioEffect
{

    private AcousticEchoCanceler(int i)
        throws IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        super(EFFECT_TYPE_AEC, EFFECT_TYPE_NULL, 0, i);
    }

    public static AcousticEchoCanceler create(int i)
    {
        AcousticEchoCanceler acousticechocanceler = null;
        AcousticEchoCanceler acousticechocanceler1;
        acousticechocanceler1 = JVM INSTR new #2   <Class AcousticEchoCanceler>;
        acousticechocanceler1.AcousticEchoCanceler(i);
        acousticechocanceler = acousticechocanceler1;
_L2:
        return acousticechocanceler;
        Object obj;
        obj;
        Log.w("AcousticEchoCanceler", "not enough memory");
        continue; /* Loop/switch isn't completed */
        obj;
        Log.w("AcousticEchoCanceler", "not enough resources");
        continue; /* Loop/switch isn't completed */
        obj;
        Log.w("AcousticEchoCanceler", (new StringBuilder()).append("not implemented on this device").append(null).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean isAvailable()
    {
        return AudioEffect.isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_AEC);
    }

    private static final String TAG = "AcousticEchoCanceler";
}
