// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.net.NetworkSpecifier;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;

// Referenced classes of package android.net.wifi.aware:
//            WifiAwareManager, SubscribeDiscoverySession, WifiAwareUtils, PeerHandle

public class DiscoverySession
    implements AutoCloseable
{

    public DiscoverySession(WifiAwareManager wifiawaremanager, int i, int j)
    {
        mTerminated = false;
        mMgr = new WeakReference(wifiawaremanager);
        mClientId = i;
        mSessionId = j;
        mCloseGuard.open("close");
    }

    public static int getMaxSendRetryCount()
    {
        return 5;
    }

    public void close()
    {
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("DiscoverySession", "destroy: called post GC on WifiAwareManager");
            return;
        } else
        {
            wifiawaremanager.terminateSession(mClientId, mSessionId);
            mTerminated = true;
            mMgr.clear();
            mCloseGuard.close();
            return;
        }
    }

    public NetworkSpecifier createNetworkSpecifierOpen(PeerHandle peerhandle)
    {
        if(mTerminated)
        {
            Log.w("DiscoverySession", "createNetworkSpecifierOpen: called on terminated session");
            return null;
        }
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("DiscoverySession", "createNetworkSpecifierOpen: called post GC on WifiAwareManager");
            return null;
        }
        int i;
        if(this instanceof SubscribeDiscoverySession)
            i = 0;
        else
            i = 1;
        return wifiawaremanager.createNetworkSpecifier(mClientId, i, mSessionId, peerhandle, null, null);
    }

    public NetworkSpecifier createNetworkSpecifierPassphrase(PeerHandle peerhandle, String s)
    {
        if(!WifiAwareUtils.validatePassphrase(s))
            throw new IllegalArgumentException("Passphrase must meet length requirements");
        if(mTerminated)
        {
            Log.w("DiscoverySession", "createNetworkSpecifierPassphrase: called on terminated session");
            return null;
        }
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("DiscoverySession", "createNetworkSpecifierPassphrase: called post GC on WifiAwareManager");
            return null;
        }
        int i;
        if(this instanceof SubscribeDiscoverySession)
            i = 0;
        else
            i = 1;
        return wifiawaremanager.createNetworkSpecifier(mClientId, i, mSessionId, peerhandle, null, s);
    }

    public NetworkSpecifier createNetworkSpecifierPmk(PeerHandle peerhandle, byte abyte0[])
    {
        if(!WifiAwareUtils.validatePmk(abyte0))
            throw new IllegalArgumentException("PMK must 32 bytes");
        if(mTerminated)
        {
            Log.w("DiscoverySession", "createNetworkSpecifierPmk: called on terminated session");
            return null;
        }
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("DiscoverySession", "createNetworkSpecifierPmk: called post GC on WifiAwareManager");
            return null;
        }
        int i;
        if(this instanceof SubscribeDiscoverySession)
            i = 0;
        else
            i = 1;
        return wifiawaremanager.createNetworkSpecifier(mClientId, i, mSessionId, peerhandle, abyte0, null);
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

    public void sendMessage(PeerHandle peerhandle, int i, byte abyte0[])
    {
        sendMessage(peerhandle, i, abyte0, 0);
    }

    public void sendMessage(PeerHandle peerhandle, int i, byte abyte0[], int j)
    {
        if(mTerminated)
        {
            Log.w("DiscoverySession", "sendMessage: called on terminated session");
            return;
        }
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("DiscoverySession", "sendMessage: called post GC on WifiAwareManager");
            return;
        } else
        {
            wifiawaremanager.sendMessage(mClientId, mSessionId, peerhandle, abyte0, i, j);
            return;
        }
    }

    public void setTerminated()
    {
        if(mTerminated)
        {
            Log.w("DiscoverySession", "terminate: already terminated.");
            return;
        } else
        {
            mTerminated = true;
            mMgr.clear();
            mCloseGuard.close();
            return;
        }
    }

    public void startRanging(android.net.wifi.RttManager.RttParams arttparams[], android.net.wifi.RttManager.RttListener rttlistener)
    {
        if(mTerminated)
        {
            Log.w("DiscoverySession", "startRanging: called on terminated session");
            return;
        }
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("DiscoverySession", "startRanging: called post GC on WifiAwareManager");
            return;
        } else
        {
            wifiawaremanager.startRanging(mClientId, mSessionId, arttparams, rttlistener);
            return;
        }
    }

    private static final boolean DBG = false;
    private static final int MAX_SEND_RETRY_COUNT = 5;
    private static final String TAG = "DiscoverySession";
    private static final boolean VDBG = false;
    protected final int mClientId;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    protected WeakReference mMgr;
    protected final int mSessionId;
    protected boolean mTerminated;
}
