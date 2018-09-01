// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.RemoteException;

// Referenced classes of package android.media:
//            AudioPlaybackConfiguration, IPlayer

public class PlayerProxy
{

    PlayerProxy(AudioPlaybackConfiguration audioplaybackconfiguration)
    {
        if(audioplaybackconfiguration == null)
        {
            throw new IllegalArgumentException("Illegal null AudioPlaybackConfiguration");
        } else
        {
            mConf = audioplaybackconfiguration;
            return;
        }
    }

    public void applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation)
    {
        try
        {
            mConf.getIPlayer().applyVolumeShaper(configuration, operation);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(VolumeShaper.Configuration configuration)
        {
            throw new IllegalStateException("No player to proxy for applyVolumeShaper operation, player already released?", configuration);
        }
    }

    public void pause()
    {
        try
        {
            mConf.getIPlayer().pause();
            return;
        }
        catch(Object obj)
        {
            throw new IllegalStateException("No player to proxy for pause operation, player already released?", ((Throwable) (obj)));
        }
    }

    public void setPan(float f)
    {
        try
        {
            mConf.getIPlayer().setPan(f);
            return;
        }
        catch(Object obj)
        {
            throw new IllegalStateException("No player to proxy for setPan operation, player already released?", ((Throwable) (obj)));
        }
    }

    public void setStartDelayMs(int i)
    {
        try
        {
            mConf.getIPlayer().setStartDelayMs(i);
            return;
        }
        catch(Object obj)
        {
            throw new IllegalStateException("No player to proxy for setStartDelayMs operation, player already released?", ((Throwable) (obj)));
        }
    }

    public void setVolume(float f)
    {
        try
        {
            mConf.getIPlayer().setVolume(f);
            return;
        }
        catch(Object obj)
        {
            throw new IllegalStateException("No player to proxy for setVolume operation, player already released?", ((Throwable) (obj)));
        }
    }

    public void start()
    {
        try
        {
            mConf.getIPlayer().start();
            return;
        }
        catch(Object obj)
        {
            throw new IllegalStateException("No player to proxy for start operation, player already released?", ((Throwable) (obj)));
        }
    }

    public void stop()
    {
        try
        {
            mConf.getIPlayer().stop();
            return;
        }
        catch(Object obj)
        {
            throw new IllegalStateException("No player to proxy for stop operation, player already released?", ((Throwable) (obj)));
        }
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "PlayerProxy";
    private final AudioPlaybackConfiguration mConf;
}
