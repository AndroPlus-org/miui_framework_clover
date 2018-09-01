// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.graphics.Bitmap;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.hardware.radio:
//            ProgramSelector, RadioMetadata

public abstract class RadioTuner
{
    public static abstract class Callback
    {

        public void onAntennaState(boolean flag)
        {
        }

        public void onBackgroundScanAvailabilityChange(boolean flag)
        {
        }

        public void onBackgroundScanComplete()
        {
        }

        public void onConfigurationChanged(RadioManager.BandConfig bandconfig)
        {
        }

        public void onControlChanged(boolean flag)
        {
        }

        public void onEmergencyAnnouncement(boolean flag)
        {
        }

        public void onError(int i)
        {
        }

        public void onMetadataChanged(RadioMetadata radiometadata)
        {
        }

        public void onProgramInfoChanged(RadioManager.ProgramInfo programinfo)
        {
        }

        public void onProgramListChanged()
        {
        }

        public void onTrafficAnnouncement(boolean flag)
        {
        }

        public Callback()
        {
        }
    }


    public RadioTuner()
    {
    }

    public abstract int cancel();

    public abstract void cancelAnnouncement();

    public abstract void close();

    public abstract int getConfiguration(RadioManager.BandConfig abandconfig[]);

    public abstract Bitmap getMetadataImage(int i);

    public abstract boolean getMute();

    public abstract int getProgramInformation(RadioManager.ProgramInfo aprograminfo[]);

    public abstract List getProgramList(Map map);

    public abstract boolean hasControl();

    public abstract boolean isAnalogForced();

    public abstract boolean isAntennaConnected();

    public abstract int scan(int i, boolean flag);

    public abstract void setAnalogForced(boolean flag);

    public abstract int setConfiguration(RadioManager.BandConfig bandconfig);

    public abstract int setMute(boolean flag);

    public abstract boolean startBackgroundScan();

    public abstract int step(int i, boolean flag);

    public abstract int tune(int i, int j);

    public abstract void tune(ProgramSelector programselector);

    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_UP = 0;
    public static final int ERROR_BACKGROUND_SCAN_FAILED = 6;
    public static final int ERROR_BACKGROUND_SCAN_UNAVAILABLE = 5;
    public static final int ERROR_CANCELLED = 2;
    public static final int ERROR_CONFIG = 4;
    public static final int ERROR_HARDWARE_FAILURE = 0;
    public static final int ERROR_SCAN_TIMEOUT = 3;
    public static final int ERROR_SERVER_DIED = 1;
}
