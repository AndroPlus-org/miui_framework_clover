// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.util.Log;
import java.lang.ref.WeakReference;

// Referenced classes of package android.net.wifi.aware:
//            DiscoverySession, WifiAwareManager, SubscribeConfig

public class SubscribeDiscoverySession extends DiscoverySession
{

    public SubscribeDiscoverySession(WifiAwareManager wifiawaremanager, int i, int j)
    {
        super(wifiawaremanager, i, j);
    }

    public void updateSubscribe(SubscribeConfig subscribeconfig)
    {
        if(mTerminated)
        {
            Log.w("SubscribeDiscSession", "updateSubscribe: called on terminated session");
            return;
        }
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("SubscribeDiscSession", "updateSubscribe: called post GC on WifiAwareManager");
            return;
        } else
        {
            wifiawaremanager.updateSubscribe(mClientId, mSessionId, subscribeconfig);
            return;
        }
    }

    private static final String TAG = "SubscribeDiscSession";
}
