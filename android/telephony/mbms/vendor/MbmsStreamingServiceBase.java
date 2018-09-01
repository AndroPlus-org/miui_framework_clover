// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms.vendor;

import android.net.Uri;
import android.os.*;
import android.telephony.mbms.*;
import java.util.List;

public class MbmsStreamingServiceBase extends IMbmsStreamingService.Stub
{

    public MbmsStreamingServiceBase()
    {
    }

    public void dispose(int i)
        throws RemoteException
    {
    }

    public Uri getPlaybackUri(int i, String s)
        throws RemoteException
    {
        return null;
    }

    public final int initialize(final IMbmsStreamingSessionCallback callback, final int subscriptionId)
        throws RemoteException
    {
        final int uid = Binder.getCallingUid();
        callback.asBinder().linkToDeath(new android.os.IBinder.DeathRecipient() {

            public void binderDied()
            {
                onAppCallbackDied(uid, subscriptionId);
            }

            final MbmsStreamingServiceBase this$0;
            final int val$subscriptionId;
            final int val$uid;

            
            {
                this$0 = MbmsStreamingServiceBase.this;
                uid = i;
                subscriptionId = j;
                super();
            }
        }
, 0);
        return initialize(((MbmsStreamingSessionCallback) (new MbmsStreamingSessionCallback() {

            public void onError(int i, String s)
            {
                callback.onError(i, s);
_L1:
                return;
                s;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            public void onMiddlewareReady()
            {
                callback.onMiddlewareReady();
_L1:
                return;
                RemoteException remoteexception;
                remoteexception;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            public void onStreamingServicesUpdated(List list)
            {
                callback.onStreamingServicesUpdated(list);
_L1:
                return;
                list;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            final MbmsStreamingServiceBase this$0;
            final IMbmsStreamingSessionCallback val$callback;
            final int val$subscriptionId;
            final int val$uid;

            
            {
                this$0 = MbmsStreamingServiceBase.this;
                callback = imbmsstreamingsessioncallback;
                uid = i;
                subscriptionId = j;
                super();
            }
        }
)), subscriptionId);
    }

    public int initialize(MbmsStreamingSessionCallback mbmsstreamingsessioncallback, int i)
        throws RemoteException
    {
        return 0;
    }

    public void onAppCallbackDied(int i, int j)
    {
    }

    public int requestUpdateStreamingServices(int i, List list)
        throws RemoteException
    {
        return 0;
    }

    public int startStreaming(final int subscriptionId, String s, final IStreamingServiceCallback callback)
        throws RemoteException
    {
        final int uid = Binder.getCallingUid();
        callback.asBinder().linkToDeath(new android.os.IBinder.DeathRecipient() {

            public void binderDied()
            {
                onAppCallbackDied(uid, subscriptionId);
            }

            final MbmsStreamingServiceBase this$0;
            final int val$subscriptionId;
            final int val$uid;

            
            {
                this$0 = MbmsStreamingServiceBase.this;
                uid = i;
                subscriptionId = j;
                super();
            }
        }
, 0);
        return startStreaming(subscriptionId, s, ((StreamingServiceCallback) (new StreamingServiceCallback() {

            public void onBroadcastSignalStrengthUpdated(int i)
            {
                callback.onBroadcastSignalStrengthUpdated(i);
_L1:
                return;
                RemoteException remoteexception;
                remoteexception;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            public void onError(int i, String s1)
            {
                callback.onError(i, s1);
_L1:
                return;
                s1;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            public void onMediaDescriptionUpdated()
            {
                callback.onMediaDescriptionUpdated();
_L1:
                return;
                RemoteException remoteexception;
                remoteexception;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            public void onStreamMethodUpdated(int i)
            {
                callback.onStreamMethodUpdated(i);
_L1:
                return;
                RemoteException remoteexception;
                remoteexception;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            public void onStreamStateUpdated(int i, int j)
            {
                callback.onStreamStateUpdated(i, j);
_L1:
                return;
                RemoteException remoteexception;
                remoteexception;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            final MbmsStreamingServiceBase this$0;
            final IStreamingServiceCallback val$callback;
            final int val$subscriptionId;
            final int val$uid;

            
            {
                this$0 = MbmsStreamingServiceBase.this;
                callback = istreamingservicecallback;
                uid = i;
                subscriptionId = j;
                super();
            }
        }
)));
    }

    public int startStreaming(int i, String s, StreamingServiceCallback streamingservicecallback)
        throws RemoteException
    {
        return 0;
    }

    public void stopStreaming(int i, String s)
        throws RemoteException
    {
    }
}
