// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.net.Uri;
import android.os.RemoteException;
import android.telephony.MbmsStreamingSession;
import android.telephony.mbms.vendor.IMbmsStreamingService;
import android.util.Log;

// Referenced classes of package android.telephony.mbms:
//            InternalStreamingServiceCallback, StreamingServiceInfo

public class StreamingService
{

    public StreamingService(int i, IMbmsStreamingService imbmsstreamingservice, MbmsStreamingSession mbmsstreamingsession, StreamingServiceInfo streamingserviceinfo, InternalStreamingServiceCallback internalstreamingservicecallback)
    {
        mSubscriptionId = i;
        mParentSession = mbmsstreamingsession;
        mService = imbmsstreamingservice;
        mServiceInfo = streamingserviceinfo;
        mCallback = internalstreamingservicecallback;
    }

    private void sendErrorToApp(int i, String s)
    {
        mCallback.onError(i, s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public InternalStreamingServiceCallback getCallback()
    {
        return mCallback;
    }

    public StreamingServiceInfo getInfo()
    {
        return mServiceInfo;
    }

    public Uri getPlaybackUri()
    {
        if(mService == null)
            throw new IllegalStateException("No streaming service attached");
        Uri uri;
        try
        {
            uri = mService.getPlaybackUri(mSubscriptionId, mServiceInfo.getServiceId());
        }
        catch(RemoteException remoteexception)
        {
            Log.w("MbmsStreamingService", "Remote process died");
            mService = null;
            mParentSession.onStreamingServiceStopped(this);
            sendErrorToApp(3, null);
            return null;
        }
        return uri;
    }

    public void stopStreaming()
    {
        if(mService == null)
            throw new IllegalStateException("No streaming service attached");
        mService.stopStreaming(mSubscriptionId, mServiceInfo.getServiceId());
        mParentSession.onStreamingServiceStopped(this);
_L2:
        return;
        Object obj;
        obj;
        Log.w("MbmsStreamingService", "Remote process died");
        mService = null;
        sendErrorToApp(3, null);
        mParentSession.onStreamingServiceStopped(this);
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mParentSession.onStreamingServiceStopped(this);
        throw obj;
    }

    public static final int BROADCAST_METHOD = 1;
    private static final String LOG_TAG = "MbmsStreamingService";
    public static final int REASON_BY_USER_REQUEST = 1;
    public static final int REASON_END_OF_SESSION = 2;
    public static final int REASON_FREQUENCY_CONFLICT = 3;
    public static final int REASON_LEFT_MBMS_BROADCAST_AREA = 6;
    public static final int REASON_NONE = 0;
    public static final int REASON_NOT_CONNECTED_TO_HOMECARRIER_LTE = 5;
    public static final int REASON_OUT_OF_MEMORY = 4;
    public static final int STATE_STALLED = 3;
    public static final int STATE_STARTED = 2;
    public static final int STATE_STOPPED = 1;
    public static final int UNICAST_METHOD = 2;
    private final InternalStreamingServiceCallback mCallback;
    private final MbmsStreamingSession mParentSession;
    private IMbmsStreamingService mService;
    private final StreamingServiceInfo mServiceInfo;
    private final int mSubscriptionId;
}
