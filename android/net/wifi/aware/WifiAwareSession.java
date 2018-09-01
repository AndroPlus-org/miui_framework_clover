// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.net.NetworkSpecifier;
import android.os.*;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;

// Referenced classes of package android.net.wifi.aware:
//            WifiAwareManager, WifiAwareUtils, PublishConfig, DiscoverySessionCallback, 
//            SubscribeConfig

public class WifiAwareSession
    implements AutoCloseable
{

    public WifiAwareSession(WifiAwareManager wifiawaremanager, Binder binder, int i)
    {
        mTerminated = true;
        mMgr = new WeakReference(wifiawaremanager);
        mBinder = binder;
        mClientId = i;
        mTerminated = false;
        mCloseGuard.open("close");
    }

    public void close()
    {
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("WifiAwareSession", "destroy: called post GC on WifiAwareManager");
            return;
        } else
        {
            wifiawaremanager.disconnect(mClientId, mBinder);
            mTerminated = true;
            mMgr.clear();
            mCloseGuard.close();
            return;
        }
    }

    public NetworkSpecifier createNetworkSpecifierOpen(int i, byte abyte0[])
    {
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.e("WifiAwareSession", "createNetworkSpecifierOpen: called post GC on WifiAwareManager");
            return null;
        }
        if(mTerminated)
        {
            Log.e("WifiAwareSession", "createNetworkSpecifierOpen: called after termination");
            return null;
        } else
        {
            return wifiawaremanager.createNetworkSpecifier(mClientId, i, abyte0, null, null);
        }
    }

    public NetworkSpecifier createNetworkSpecifierPassphrase(int i, byte abyte0[], String s)
    {
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.e("WifiAwareSession", "createNetworkSpecifierPassphrase: called post GC on WifiAwareManager");
            return null;
        }
        if(mTerminated)
        {
            Log.e("WifiAwareSession", "createNetworkSpecifierPassphrase: called after termination");
            return null;
        }
        if(!WifiAwareUtils.validatePassphrase(s))
            throw new IllegalArgumentException("Passphrase must meet length requirements");
        else
            return wifiawaremanager.createNetworkSpecifier(mClientId, i, abyte0, null, s);
    }

    public NetworkSpecifier createNetworkSpecifierPmk(int i, byte abyte0[], byte abyte1[])
    {
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.e("WifiAwareSession", "createNetworkSpecifierPmk: called post GC on WifiAwareManager");
            return null;
        }
        if(mTerminated)
        {
            Log.e("WifiAwareSession", "createNetworkSpecifierPmk: called after termination");
            return null;
        }
        if(!WifiAwareUtils.validatePmk(abyte1))
            throw new IllegalArgumentException("PMK must 32 bytes");
        else
            return wifiawaremanager.createNetworkSpecifier(mClientId, i, abyte0, abyte1, null);
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        if(!mTerminated)
            close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void publish(PublishConfig publishconfig, DiscoverySessionCallback discoverysessioncallback, Handler handler)
    {
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.e("WifiAwareSession", "publish: called post GC on WifiAwareManager");
            return;
        }
        if(mTerminated)
        {
            Log.e("WifiAwareSession", "publish: called after termination");
            return;
        }
        int i = mClientId;
        if(handler == null)
            handler = Looper.getMainLooper();
        else
            handler = handler.getLooper();
        wifiawaremanager.publish(i, handler, publishconfig, discoverysessioncallback);
    }

    public void subscribe(SubscribeConfig subscribeconfig, DiscoverySessionCallback discoverysessioncallback, Handler handler)
    {
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.e("WifiAwareSession", "publish: called post GC on WifiAwareManager");
            return;
        }
        if(mTerminated)
        {
            Log.e("WifiAwareSession", "publish: called after termination");
            return;
        }
        int i = mClientId;
        if(handler == null)
            handler = Looper.getMainLooper();
        else
            handler = handler.getLooper();
        wifiawaremanager.subscribe(i, handler, subscribeconfig, discoverysessioncallback);
    }

    private static final boolean DBG = false;
    private static final String TAG = "WifiAwareSession";
    private static final boolean VDBG = false;
    private final Binder mBinder;
    private final int mClientId;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final WeakReference mMgr;
    private boolean mTerminated;
}
