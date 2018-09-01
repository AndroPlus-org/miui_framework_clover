// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.media.AudioAttributes;

// Referenced classes of package android.os:
//            Vibrator, VibrationEffect

public class NullVibrator extends Vibrator
{

    private NullVibrator()
    {
    }

    public static NullVibrator getInstance()
    {
        return sInstance;
    }

    public void cancel()
    {
    }

    public boolean hasAmplitudeControl()
    {
        return false;
    }

    public boolean hasVibrator()
    {
        return false;
    }

    public void vibrate(int i, String s, VibrationEffect vibrationeffect, AudioAttributes audioattributes)
    {
    }

    private static final NullVibrator sInstance = new NullVibrator();

}
