// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.hardware.radio:
//            RadioTuner, ITuner, ProgramSelector

class TunerAdapter extends RadioTuner
{

    TunerAdapter(ITuner ituner, int i)
    {
        mIsClosed = false;
        if(ituner == null)
        {
            throw new NullPointerException();
        } else
        {
            mTuner = ituner;
            mBand = i;
            return;
        }
    }

    public int cancel()
    {
        try
        {
            mTuner.cancel();
        }
        catch(IllegalStateException illegalstateexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "Can't cancel", illegalstateexception);
            return -38;
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", remoteexception);
            return -32;
        }
        return 0;
    }

    public void cancelAnnouncement()
    {
        try
        {
            mTuner.cancelAnnouncement();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("service died", remoteexception);
        }
    }

    public void close()
    {
        ITuner ituner = mTuner;
        ituner;
        JVM INSTR monitorenter ;
        if(!mIsClosed)
            break MISSING_BLOCK_LABEL_25;
        Log.v("BroadcastRadio.TunerAdapter", "Tuner is already closed");
        ituner;
        JVM INSTR monitorexit ;
        return;
        mIsClosed = true;
        ituner;
        JVM INSTR monitorexit ;
        mTuner.close();
_L1:
        return;
        Object obj;
        obj;
        throw obj;
        obj;
        Log.e("BroadcastRadio.TunerAdapter", "Exception trying to close tuner", ((Throwable) (obj)));
          goto _L1
    }

    public int getConfiguration(RadioManager.BandConfig abandconfig[])
    {
        if(abandconfig == null || abandconfig.length != 1)
            throw new IllegalArgumentException("The argument must be an array of length 1");
        try
        {
            abandconfig[0] = mTuner.getConfiguration();
        }
        // Misplaced declaration of an exception variable
        catch(RadioManager.BandConfig abandconfig[])
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", abandconfig);
            return -32;
        }
        return 0;
    }

    public Bitmap getMetadataImage(int i)
    {
        Bitmap bitmap;
        try
        {
            bitmap = mTuner.getImage(i);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("service died", remoteexception);
        }
        return bitmap;
    }

    public boolean getMute()
    {
        boolean flag;
        try
        {
            flag = mTuner.isMuted();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", remoteexception);
            return true;
        }
        return flag;
    }

    public int getProgramInformation(RadioManager.ProgramInfo aprograminfo[])
    {
        if(aprograminfo == null || aprograminfo.length != 1)
            throw new IllegalArgumentException("The argument must be an array of length 1");
        try
        {
            aprograminfo[0] = mTuner.getProgramInformation();
        }
        // Misplaced declaration of an exception variable
        catch(RadioManager.ProgramInfo aprograminfo[])
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", aprograminfo);
            return -32;
        }
        return 0;
    }

    public List getProgramList(Map map)
    {
        try
        {
            map = mTuner.getProgramList(map);
        }
        // Misplaced declaration of an exception variable
        catch(Map map)
        {
            throw new RuntimeException("service died", map);
        }
        return map;
    }

    public boolean hasControl()
    {
        boolean flag;
        try
        {
            flag = mTuner.isClosed();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag ^ true;
    }

    public boolean isAnalogForced()
    {
        boolean flag;
        try
        {
            flag = mTuner.isAnalogForced();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("service died", remoteexception);
        }
        return flag;
    }

    public boolean isAntennaConnected()
    {
        boolean flag;
        try
        {
            flag = mTuner.isAntennaConnected();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("service died", remoteexception);
        }
        return flag;
    }

    public int scan(int i, boolean flag)
    {
        boolean flag1 = true;
        ITuner ituner;
        try
        {
            ituner = mTuner;
        }
        catch(IllegalStateException illegalstateexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "Can't scan", illegalstateexception);
            return -38;
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", remoteexception);
            return -32;
        }
        if(i != 1)
            flag1 = false;
        ituner.scan(flag1, flag);
        return 0;
    }

    public void setAnalogForced(boolean flag)
    {
        try
        {
            mTuner.setAnalogForced(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("service died", remoteexception);
        }
    }

    public int setConfiguration(RadioManager.BandConfig bandconfig)
    {
        try
        {
            mTuner.setConfiguration(bandconfig);
            mBand = bandconfig.getType();
        }
        // Misplaced declaration of an exception variable
        catch(RadioManager.BandConfig bandconfig)
        {
            Log.e("BroadcastRadio.TunerAdapter", "Can't set configuration", bandconfig);
            return -22;
        }
        // Misplaced declaration of an exception variable
        catch(RadioManager.BandConfig bandconfig)
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", bandconfig);
            return -32;
        }
        return 0;
    }

    public int setMute(boolean flag)
    {
        try
        {
            mTuner.setMuted(flag);
        }
        catch(IllegalStateException illegalstateexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "Can't set muted", illegalstateexception);
            return 0x80000000;
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", remoteexception);
            return -32;
        }
        return 0;
    }

    public boolean startBackgroundScan()
    {
        boolean flag;
        try
        {
            flag = mTuner.startBackgroundScan();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("service died", remoteexception);
        }
        return flag;
    }

    public int step(int i, boolean flag)
    {
        boolean flag1 = true;
        ITuner ituner;
        try
        {
            ituner = mTuner;
        }
        catch(IllegalStateException illegalstateexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "Can't step", illegalstateexception);
            return -38;
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", remoteexception);
            return -32;
        }
        if(i != 1)
            flag1 = false;
        ituner.step(flag1, flag);
        return 0;
    }

    public int tune(int i, int j)
    {
        try
        {
            mTuner.tune(ProgramSelector.createAmFmSelector(mBand, i, j));
        }
        catch(IllegalStateException illegalstateexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "Can't tune", illegalstateexception);
            return -38;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "Can't tune", illegalargumentexception);
            return -22;
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BroadcastRadio.TunerAdapter", "service died", remoteexception);
            return -32;
        }
        return 0;
    }

    public void tune(ProgramSelector programselector)
    {
        try
        {
            mTuner.tune(programselector);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ProgramSelector programselector)
        {
            throw new RuntimeException("service died", programselector);
        }
    }

    private static final String TAG = "BroadcastRadio.TunerAdapter";
    private int mBand;
    private boolean mIsClosed;
    private final ITuner mTuner;
}
