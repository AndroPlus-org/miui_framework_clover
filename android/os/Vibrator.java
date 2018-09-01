// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.app.ActivityThread;
import android.content.Context;
import android.media.AudioAttributes;
import android.util.Log;

// Referenced classes of package android.os:
//            VibrationEffect, Process

public abstract class Vibrator
{

    public Vibrator()
    {
        mPackageName = ActivityThread.currentPackageName();
    }

    protected Vibrator(Context context)
    {
        mPackageName = context.getOpPackageName();
    }

    public abstract void cancel();

    public abstract boolean hasAmplitudeControl();

    public abstract boolean hasVibrator();

    public abstract void vibrate(int i, String s, VibrationEffect vibrationeffect, AudioAttributes audioattributes);

    public void vibrate(long l)
    {
        vibrate(l, ((AudioAttributes) (null)));
    }

    public void vibrate(long l, AudioAttributes audioattributes)
    {
        vibrate(VibrationEffect.createOneShot(l, -1), audioattributes);
_L1:
        return;
        audioattributes;
        Log.e("Vibrator", "Failed to create VibrationEffect", audioattributes);
          goto _L1
    }

    public void vibrate(VibrationEffect vibrationeffect)
    {
        vibrate(vibrationeffect, ((AudioAttributes) (null)));
    }

    public void vibrate(VibrationEffect vibrationeffect, AudioAttributes audioattributes)
    {
        vibrate(Process.myUid(), mPackageName, vibrationeffect, audioattributes);
    }

    public void vibrate(long al[], int i)
    {
        vibrate(al, i, null);
    }

    public void vibrate(long al[], int i, AudioAttributes audioattributes)
    {
        if(i < -1 || i >= al.length)
        {
            Log.e("Vibrator", (new StringBuilder()).append("vibrate called with repeat index out of bounds (pattern.length=").append(al.length).append(", index=").append(i).append(")").toString());
            throw new ArrayIndexOutOfBoundsException();
        }
        vibrate(VibrationEffect.createWaveform(al, i), audioattributes);
_L1:
        return;
        al;
        Log.e("Vibrator", "Failed to create VibrationEffect", al);
          goto _L1
    }

    private static final String TAG = "Vibrator";
    private final String mPackageName;
}
