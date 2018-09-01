// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RemoteController;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.*;

public class MusicListenerService extends Service
    implements android.media.RemoteController.OnClientUpdateListener
{
    public class RCBinder extends Binder
    {

        public MusicListenerService getService()
        {
            return MusicListenerService.this;
        }

        final MusicListenerService this$0;

        public RCBinder()
        {
            this$0 = MusicListenerService.this;
            super();
        }
    }


    public MusicListenerService()
    {
        mBinder = new RCBinder();
        mClientUpdateListeners = new ArrayList();
    }

    private void disableRemoteController()
    {
        if(mRemoteControllerEnabled)
        {
            ((AudioManager)mContext.getSystemService("audio")).unregisterRemoteController(mRemoteController);
            mRemoteControllerEnabled = false;
        }
    }

    private void enableRemoteController()
    {
        if(!mRemoteControllerEnabled)
        {
            AudioManager audiomanager = (AudioManager)mContext.getSystemService("audio");
            mRemoteController = new RemoteController(mContext, this);
            try
            {
                mRemoteControllerEnabled = audiomanager.registerRemoteController(mRemoteController);
            }
            catch(Exception exception)
            {
                Log.w("MusicListenerService", "fail to register RemoteController!", exception);
            }
            if(mRemoteControllerEnabled)
            {
                mRemoteController.setArtworkConfiguration(1024, 1024);
                mRemoteController.setSynchronizationMode(1);
            } else
            {
                Log.w("MusicListenerService", "fail to register RemoteController!");
            }
        }
    }

    public RemoteController getRemoteController()
    {
        return mRemoteController;
    }

    public IBinder onBind(Intent intent)
    {
        if(intent.getAction().equals("android.service.notification.MusicListenerService"))
        {
            Log.d("MusicListenerService", "onBind: success");
            return mBinder;
        } else
        {
            Log.d("MusicListenerService", "onBind: fail");
            return null;
        }
    }

    public void onClientChange(boolean flag)
    {
        for(Iterator iterator = mClientUpdateListeners.iterator(); iterator.hasNext();)
        {
            android.media.RemoteController.OnClientUpdateListener onclientupdatelistener = (android.media.RemoteController.OnClientUpdateListener)((WeakReference)iterator.next()).get();
            if(onclientupdatelistener != null)
                onclientupdatelistener.onClientChange(flag);
            else
                iterator.remove();
        }

        if(mClientUpdateListeners.size() == 0)
            disableRemoteController();
    }

    public void onClientFolderInfoBrowsedPlayer(String s)
    {
    }

    public void onClientMetadataUpdate(android.media.RemoteController.MetadataEditor metadataeditor)
    {
        for(Iterator iterator = mClientUpdateListeners.iterator(); iterator.hasNext();)
        {
            android.media.RemoteController.OnClientUpdateListener onclientupdatelistener = (android.media.RemoteController.OnClientUpdateListener)((WeakReference)iterator.next()).get();
            if(onclientupdatelistener != null)
                onclientupdatelistener.onClientMetadataUpdate(metadataeditor);
            else
                iterator.remove();
        }

        if(mClientUpdateListeners.size() == 0)
            disableRemoteController();
    }

    public void onClientNowPlayingContentChange()
    {
    }

    public void onClientPlayItemResponse(boolean flag)
    {
    }

    public void onClientPlaybackStateUpdate(int i)
    {
        for(Iterator iterator = mClientUpdateListeners.iterator(); iterator.hasNext();)
        {
            android.media.RemoteController.OnClientUpdateListener onclientupdatelistener = (android.media.RemoteController.OnClientUpdateListener)((WeakReference)iterator.next()).get();
            if(onclientupdatelistener != null)
                onclientupdatelistener.onClientPlaybackStateUpdate(i);
            else
                iterator.remove();
        }

        if(mClientUpdateListeners.size() == 0)
            disableRemoteController();
    }

    public void onClientPlaybackStateUpdate(int i, long l, long l1, float f)
    {
        for(Iterator iterator = mClientUpdateListeners.iterator(); iterator.hasNext();)
        {
            android.media.RemoteController.OnClientUpdateListener onclientupdatelistener = (android.media.RemoteController.OnClientUpdateListener)((WeakReference)iterator.next()).get();
            if(onclientupdatelistener != null)
                onclientupdatelistener.onClientPlaybackStateUpdate(i, l, l1, f);
            else
                iterator.remove();
        }

        if(mClientUpdateListeners.size() == 0)
            disableRemoteController();
    }

    public void onClientTransportControlUpdate(int i)
    {
        for(Iterator iterator = mClientUpdateListeners.iterator(); iterator.hasNext();)
        {
            android.media.RemoteController.OnClientUpdateListener onclientupdatelistener = (android.media.RemoteController.OnClientUpdateListener)((WeakReference)iterator.next()).get();
            if(onclientupdatelistener != null)
                onclientupdatelistener.onClientTransportControlUpdate(i);
            else
                iterator.remove();
        }

        if(mClientUpdateListeners.size() == 0)
            disableRemoteController();
    }

    public void onClientUpdateNowPlayingEntries(long al[])
    {
    }

    public void onCreate()
    {
        mContext = getApplicationContext();
        mRemoteController = new RemoteController(mContext, this);
    }

    public void onDestroy()
    {
        disableRemoteController();
    }

    public void registerClientUpdateListener(android.media.RemoteController.OnClientUpdateListener onclientupdatelistener)
    {
        enableRemoteController();
        for(Iterator iterator = mClientUpdateListeners.iterator(); iterator.hasNext();)
        {
            android.media.RemoteController.OnClientUpdateListener onclientupdatelistener1 = (android.media.RemoteController.OnClientUpdateListener)((WeakReference)iterator.next()).get();
            if(onclientupdatelistener1 != null && onclientupdatelistener1.equals(onclientupdatelistener))
                return;
        }

        mClientUpdateListeners.add(new WeakReference(onclientupdatelistener));
    }

    public void unregisterClientUpdateListener(android.media.RemoteController.OnClientUpdateListener onclientupdatelistener)
    {
        Iterator iterator = mClientUpdateListeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            android.media.RemoteController.OnClientUpdateListener onclientupdatelistener1 = (android.media.RemoteController.OnClientUpdateListener)((WeakReference)iterator.next()).get();
            if(onclientupdatelistener1 == null || onclientupdatelistener1.equals(onclientupdatelistener))
                iterator.remove();
        } while(true);
        if(mClientUpdateListeners.size() == 0)
            disableRemoteController();
    }

    public static final String ACTION = "android.service.notification.MusicListenerService";
    private static final int BITMAP_HEIGHT = 1024;
    private static final int BITMAP_WIDTH = 1024;
    private static final String LOG_TAG = "MusicListenerService";
    private IBinder mBinder;
    private List mClientUpdateListeners;
    private Context mContext;
    private RemoteController mRemoteController;
    private boolean mRemoteControllerEnabled;
}
