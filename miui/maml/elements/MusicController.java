// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.content.*;
import android.media.Rating;
import android.media.RemoteController;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

// Referenced classes of package miui.maml.elements:
//            MusicListenerService

public class MusicController
{

    static android.media.RemoteController.OnClientUpdateListener _2D_get0(MusicController musiccontroller)
    {
        return musiccontroller.mClientUpdateListener;
    }

    static MusicListenerService _2D_get1(MusicController musiccontroller)
    {
        return musiccontroller.mRCService;
    }

    static MusicListenerService _2D_set0(MusicController musiccontroller, MusicListenerService musiclistenerservice)
    {
        musiccontroller.mRCService = musiclistenerservice;
        return musiclistenerservice;
    }

    public MusicController(Context context)
    {
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                if(ibinder instanceof MusicListenerService.RCBinder)
                {
                    componentname = (MusicListenerService.RCBinder)ibinder;
                    MusicController._2D_set0(MusicController.this, componentname.getService());
                    MusicController._2D_get1(MusicController.this).registerClientUpdateListener(MusicController._2D_get0(MusicController.this));
                }
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                if(MusicController._2D_get1(MusicController.this) != null)
                    MusicController._2D_get1(MusicController.this).unregisterClientUpdateListener(MusicController._2D_get0(MusicController.this));
            }

            final MusicController this$0;

            
            {
                this$0 = MusicController.this;
                super();
            }
        }
;
        mContext = context;
    }

    private RemoteController getRemoteController()
    {
        RemoteController remotecontroller = null;
        if(mRCService != null)
            remotecontroller = mRCService.getRemoteController();
        return remotecontroller;
    }

    public void enableNotificationService()
    {
    }

    public long getEstimatedMediaPosition()
    {
        RemoteController remotecontroller = getRemoteController();
        long l;
        if(remotecontroller != null)
            l = remotecontroller.getEstimatedMediaPosition();
        else
            l = 0L;
        return l;
    }

    public String getRemoteControlClientPackageName()
    {
        String s = null;
        RemoteController remotecontroller = getRemoteController();
        if(remotecontroller != null)
            s = remotecontroller.getRemoteControlClientPackageName();
        return s;
    }

    public void rating(Rating rating1)
    {
        Object obj;
        obj = getRemoteController();
        if(obj == null)
            return;
        obj = ((RemoteController) (obj)).editMetadata();
        ((android.media.RemoteController.MetadataEditor) (obj)).putObject(0x10000001, rating1);
        ((android.media.RemoteController.MetadataEditor) (obj)).apply();
_L1:
        return;
        rating1;
        Log.w("MAML_MusicController", (new StringBuilder()).append("RATING_KEY_BY_USER: failed: ").append(rating1).toString());
          goto _L1
    }

    public void registerListener(android.media.RemoteController.OnClientUpdateListener onclientupdatelistener)
    {
        if(!mBind)
        {
            mClientUpdateListener = onclientupdatelistener;
            onclientupdatelistener = new Intent("android.service.notification.MusicListenerService");
            onclientupdatelistener.setPackage(mContext.getPackageName());
            mBind = mContext.bindService(onclientupdatelistener, mConnection, 1);
        }
    }

    public boolean seekTo(long l)
    {
        RemoteController remotecontroller = getRemoteController();
        if(remotecontroller == null)
            return false;
        boolean flag;
        try
        {
            flag = remotecontroller.seekTo(l);
        }
        catch(Exception exception)
        {
            return false;
        }
        return flag;
    }

    public boolean sendMediaKeyEvent(int i, int j)
    {
        RemoteController remotecontroller = getRemoteController();
        if(remotecontroller == null)
            return false;
        boolean flag;
        try
        {
            KeyEvent keyevent = JVM INSTR new #134 <Class KeyEvent>;
            keyevent.KeyEvent(i, j);
            keyevent.setSource(4098);
            flag = remotecontroller.sendMediaKeyEvent(keyevent);
        }
        catch(Exception exception)
        {
            return false;
        }
        return flag;
    }

    public void unregisterListener()
    {
        if(mBind)
        {
            mContext.unbindService(mConnection);
            if(mRCService != null)
                mRCService.unregisterClientUpdateListener(mClientUpdateListener);
            mBind = false;
        }
    }

    private static final String TAG = "MAML_MusicController";
    private boolean mBind;
    private android.media.RemoteController.OnClientUpdateListener mClientUpdateListener;
    private ServiceConnection mConnection;
    private Context mContext;
    private MusicListenerService mRCService;
}
