// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.util.Log;
import java.lang.ref.WeakReference;

// Referenced classes of package android.net.wifi.aware:
//            DiscoverySession, WifiAwareManager, PublishConfig

public class PublishDiscoverySession extends DiscoverySession
{

    public PublishDiscoverySession(WifiAwareManager wifiawaremanager, int i, int j)
    {
        super(wifiawaremanager, i, j);
    }

    public void updatePublish(PublishConfig publishconfig)
    {
        if(mTerminated)
        {
            Log.w("PublishDiscoverySession", "updatePublish: called on terminated session");
            return;
        }
        WifiAwareManager wifiawaremanager = (WifiAwareManager)mMgr.get();
        if(wifiawaremanager == null)
        {
            Log.w("PublishDiscoverySession", "updatePublish: called post GC on WifiAwareManager");
            return;
        } else
        {
            wifiawaremanager.updatePublish(mClientId, mSessionId, publishconfig);
            return;
        }
    }

    private static final String TAG = "PublishDiscoverySession";
}
