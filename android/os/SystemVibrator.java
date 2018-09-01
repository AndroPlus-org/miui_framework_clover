// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.content.Context;
import android.media.AudioAttributes;
import android.util.Log;

// Referenced classes of package android.os:
//            Vibrator, Binder, ServiceManager, RemoteException, 
//            IVibratorService, VibrationEffect

public class SystemVibrator extends Vibrator
{

    public SystemVibrator()
    {
        mToken = new Binder();
        mService = IVibratorService.Stub.asInterface(ServiceManager.getService("vibrator"));
    }

    public SystemVibrator(Context context)
    {
        super(context);
        mToken = new Binder();
        mService = IVibratorService.Stub.asInterface(ServiceManager.getService("vibrator"));
    }

    private static int usageForAttributes(AudioAttributes audioattributes)
    {
        int i;
        if(audioattributes != null)
            i = audioattributes.getUsage();
        else
            i = 0;
        return i;
    }

    public void cancel()
    {
        if(mService == null)
            return;
        mService.cancelVibrate(mToken);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("Vibrator", "Failed to cancel vibration.", remoteexception);
          goto _L1
    }

    public boolean hasAmplitudeControl()
    {
        if(mService == null)
        {
            Log.w("Vibrator", "Failed to check amplitude control; no vibrator service.");
            return false;
        }
        boolean flag;
        try
        {
            flag = mService.hasAmplitudeControl();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean hasVibrator()
    {
        if(mService == null)
        {
            Log.w("Vibrator", "Failed to vibrate; no vibrator service.");
            return false;
        }
        boolean flag;
        try
        {
            flag = mService.hasVibrator();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public void vibrate(int i, String s, VibrationEffect vibrationeffect, AudioAttributes audioattributes)
    {
        if(mService == null)
        {
            Log.w("Vibrator", "Failed to vibrate; no vibrator service.");
            return;
        }
        mService.vibrate(i, s, vibrationeffect, usageForAttributes(audioattributes), mToken);
_L1:
        return;
        s;
        Log.w("Vibrator", "Failed to vibrate.", s);
          goto _L1
    }

    private static final String TAG = "Vibrator";
    private final IVibratorService mService;
    private final Binder mToken;
}
