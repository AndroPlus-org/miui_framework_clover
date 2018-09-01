// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import java.util.List;

// Referenced classes of package android.net.wifi.aware:
//            PeerHandle, PublishDiscoverySession, SubscribeDiscoverySession

public class DiscoverySessionCallback
{

    public DiscoverySessionCallback()
    {
    }

    public void onMessageReceived(PeerHandle peerhandle, byte abyte0[])
    {
    }

    public void onMessageSendFailed(int i)
    {
    }

    public void onMessageSendSucceeded(int i)
    {
    }

    public void onPublishStarted(PublishDiscoverySession publishdiscoverysession)
    {
    }

    public void onServiceDiscovered(PeerHandle peerhandle, byte abyte0[], List list)
    {
    }

    public void onSessionConfigFailed()
    {
    }

    public void onSessionConfigUpdated()
    {
    }

    public void onSessionTerminated()
    {
    }

    public void onSubscribeStarted(SubscribeDiscoverySession subscribediscoverysession)
    {
    }
}
