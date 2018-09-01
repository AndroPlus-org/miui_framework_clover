// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Handler;

// Referenced classes of package android.media:
//            AudioDeviceInfo

public interface AudioRouting
{
    public static interface OnRoutingChangedListener
    {

        public abstract void onRoutingChanged(AudioRouting audiorouting);
    }


    public abstract void addOnRoutingChangedListener(OnRoutingChangedListener onroutingchangedlistener, Handler handler);

    public abstract AudioDeviceInfo getPreferredDevice();

    public abstract AudioDeviceInfo getRoutedDevice();

    public abstract void removeOnRoutingChangedListener(OnRoutingChangedListener onroutingchangedlistener);

    public abstract boolean setPreferredDevice(AudioDeviceInfo audiodeviceinfo);
}
