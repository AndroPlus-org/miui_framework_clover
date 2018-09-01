// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.os.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.net:
//            IEthernetManager, IpConfiguration

public class EthernetManager
{
    public static interface Listener
    {

        public abstract void onAvailabilityChanged(boolean flag);
    }


    static Handler _2D_get0(EthernetManager ethernetmanager)
    {
        return ethernetmanager.mHandler;
    }

    static ArrayList _2D_get1(EthernetManager ethernetmanager)
    {
        return ethernetmanager.mListeners;
    }

    public EthernetManager(Context context, IEthernetManager iethernetmanager)
    {
        mContext = context;
        mService = iethernetmanager;
    }

    public void addListener(Listener listener)
    {
        if(listener == null)
            throw new IllegalArgumentException("listener must not be null");
        mListeners.add(listener);
        if(mListeners.size() != 1)
            break MISSING_BLOCK_LABEL_47;
        mService.addListener(mServiceListener);
        return;
        listener;
        throw listener.rethrowFromSystemServer();
    }

    public IpConfiguration getConfiguration()
    {
        IpConfiguration ipconfiguration;
        try
        {
            ipconfiguration = mService.getConfiguration();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ipconfiguration;
    }

    public boolean isAvailable()
    {
        boolean flag;
        try
        {
            flag = mService.isAvailable();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void removeListener(Listener listener)
    {
        if(listener == null)
            throw new IllegalArgumentException("listener must not be null");
        mListeners.remove(listener);
        if(!mListeners.isEmpty())
            break MISSING_BLOCK_LABEL_46;
        mService.removeListener(mServiceListener);
        return;
        listener;
        throw listener.rethrowFromSystemServer();
    }

    public void setConfiguration(IpConfiguration ipconfiguration)
    {
        try
        {
            mService.setConfiguration(ipconfiguration);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IpConfiguration ipconfiguration)
        {
            throw ipconfiguration.rethrowFromSystemServer();
        }
    }

    private static final int MSG_AVAILABILITY_CHANGED = 1000;
    private static final String TAG = "EthernetManager";
    private final Context mContext;
    private final Handler mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            if(message.what == 1000)
            {
                boolean flag;
                if(message.arg1 == 1)
                    flag = true;
                else
                    flag = false;
                for(message = EthernetManager._2D_get1(EthernetManager.this).iterator(); message.hasNext(); ((Listener)message.next()).onAvailabilityChanged(flag));
            }
        }

        final EthernetManager this$0;

            
            {
                this$0 = EthernetManager.this;
                super();
            }
    }
;
    private final ArrayList mListeners = new ArrayList();
    private final IEthernetManager mService;
    private final IEthernetServiceListener.Stub mServiceListener = new IEthernetServiceListener.Stub() {

        public void onAvailabilityChanged(boolean flag)
        {
            Handler handler = EthernetManager._2D_get0(EthernetManager.this);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            handler.obtainMessage(1000, i, 0, null).sendToTarget();
        }

        final EthernetManager this$0;

            
            {
                this$0 = EthernetManager.this;
                super();
            }
    }
;
}
