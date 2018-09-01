// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.content.*;
import android.os.*;
import android.telephony.mbms.InternalStreamingServiceCallback;
import android.telephony.mbms.InternalStreamingSessionCallback;
import android.telephony.mbms.MbmsStreamingSessionCallback;
import android.telephony.mbms.MbmsUtils;
import android.telephony.mbms.StreamingService;
import android.telephony.mbms.StreamingServiceCallback;
import android.telephony.mbms.StreamingServiceInfo;
import android.telephony.mbms.vendor.IMbmsStreamingService;
import android.util.ArraySet;
import android.util.Log;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package android.telephony:
//            SubscriptionManager

public class MbmsStreamingSession
    implements AutoCloseable
{

    static android.os.IBinder.DeathRecipient _2D_get0(MbmsStreamingSession mbmsstreamingsession)
    {
        return mbmsstreamingsession.mDeathRecipient;
    }

    static InternalStreamingSessionCallback _2D_get1(MbmsStreamingSession mbmsstreamingsession)
    {
        return mbmsstreamingsession.mInternalCallback;
    }

    static AtomicReference _2D_get2(MbmsStreamingSession mbmsstreamingsession)
    {
        return mbmsstreamingsession.mService;
    }

    static int _2D_get3(MbmsStreamingSession mbmsstreamingsession)
    {
        return mbmsstreamingsession.mSubscriptionId;
    }

    static AtomicBoolean _2D_get4()
    {
        return sIsInitialized;
    }

    static void _2D_wrap0(MbmsStreamingSession mbmsstreamingsession, int i, String s)
    {
        mbmsstreamingsession.sendErrorToApp(i, s);
    }

    private MbmsStreamingSession(Context context, MbmsStreamingSessionCallback mbmsstreamingsessioncallback, int i, Handler handler)
    {
        mService = new AtomicReference(null);
        mDeathRecipient = new android.os.IBinder.DeathRecipient() {

            public void binderDied()
            {
                MbmsStreamingSession._2D_get4().set(false);
                MbmsStreamingSession._2D_wrap0(MbmsStreamingSession.this, 3, "Received death notification");
            }

            final MbmsStreamingSession this$0;

            
            {
                this$0 = MbmsStreamingSession.this;
                super();
            }
        }
;
        mKnownActiveStreamingServices = new ArraySet();
        mSubscriptionId = -1;
        mContext = context;
        mSubscriptionId = i;
        context = handler;
        if(handler == null)
            context = new Handler(Looper.getMainLooper());
        mInternalCallback = new InternalStreamingSessionCallback(mbmsstreamingsessioncallback, context);
    }

    private int bindAndInitialize()
    {
        return MbmsUtils.startBinding(mContext, "android.telephony.action.EmbmsStreaming", new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                componentname = android.telephony.mbms.vendor.IMbmsStreamingService.Stub.asInterface(ibinder);
                int i;
                try
                {
                    i = componentname.initialize(MbmsStreamingSession._2D_get1(MbmsStreamingSession.this), MbmsStreamingSession._2D_get3(MbmsStreamingSession.this));
                }
                // Misplaced declaration of an exception variable
                catch(ComponentName componentname)
                {
                    Log.e("MbmsStreamingSession", "Service died before initialization");
                    MbmsStreamingSession._2D_wrap0(MbmsStreamingSession.this, 103, componentname.toString());
                    MbmsStreamingSession._2D_get4().set(false);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(ComponentName componentname)
                {
                    Log.e("MbmsStreamingSession", "Runtime exception during initialization");
                    MbmsStreamingSession._2D_wrap0(MbmsStreamingSession.this, 103, componentname.toString());
                    MbmsStreamingSession._2D_get4().set(false);
                    return;
                }
                if(i != 0)
                {
                    MbmsStreamingSession._2D_wrap0(MbmsStreamingSession.this, i, "Error returned during initialization");
                    MbmsStreamingSession._2D_get4().set(false);
                    return;
                }
                try
                {
                    componentname.asBinder().linkToDeath(MbmsStreamingSession._2D_get0(MbmsStreamingSession.this), 0);
                }
                // Misplaced declaration of an exception variable
                catch(ComponentName componentname)
                {
                    MbmsStreamingSession._2D_wrap0(MbmsStreamingSession.this, 3, "Middleware lost during initialization");
                    MbmsStreamingSession._2D_get4().set(false);
                    return;
                }
                MbmsStreamingSession._2D_get2(MbmsStreamingSession.this).set(componentname);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                MbmsStreamingSession._2D_get4().set(false);
                MbmsStreamingSession._2D_get2(MbmsStreamingSession.this).set(null);
            }

            final MbmsStreamingSession this$0;

            
            {
                this$0 = MbmsStreamingSession.this;
                super();
            }
        }
);
    }

    public static MbmsStreamingSession create(Context context, MbmsStreamingSessionCallback mbmsstreamingsessioncallback, int i, Handler handler)
    {
        if(!sIsInitialized.compareAndSet(false, true))
            throw new IllegalStateException("Cannot create two instances of MbmsStreamingSession");
        context = new MbmsStreamingSession(context, mbmsstreamingsessioncallback, i, handler);
        i = context.bindAndInitialize();
        if(i != 0)
        {
            sIsInitialized.set(false);
            handler.post(new Runnable(mbmsstreamingsessioncallback, i) {

                public void run()
                {
                    callback.onError(result, null);
                }

                final MbmsStreamingSessionCallback val$callback;
                final int val$result;

            
            {
                callback = mbmsstreamingsessioncallback;
                result = i;
                super();
            }
            }
);
            return null;
        } else
        {
            return context;
        }
    }

    public static MbmsStreamingSession create(Context context, MbmsStreamingSessionCallback mbmsstreamingsessioncallback, Handler handler)
    {
        return create(context, mbmsstreamingsessioncallback, SubscriptionManager.getDefaultSubscriptionId(), handler);
    }

    private void sendErrorToApp(int i, String s)
    {
        mInternalCallback.onError(i, s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void close()
    {
        IMbmsStreamingService imbmsstreamingservice = (IMbmsStreamingService)mService.get();
        if(imbmsstreamingservice == null)
        {
            mService.set(null);
            sIsInitialized.set(false);
            mInternalCallback.stop();
            return;
        }
        imbmsstreamingservice.dispose(mSubscriptionId);
        for(Iterator iterator = mKnownActiveStreamingServices.iterator(); iterator.hasNext(); ((StreamingService)iterator.next()).getCallback().stop());
          goto _L1
        Object obj;
        obj;
        mService.set(null);
        sIsInitialized.set(false);
        mInternalCallback.stop();
_L3:
        return;
_L1:
        mKnownActiveStreamingServices.clear();
        mService.set(null);
        sIsInitialized.set(false);
        mInternalCallback.stop();
        if(true) goto _L3; else goto _L2
_L2:
        obj;
        mService.set(null);
        sIsInitialized.set(false);
        mInternalCallback.stop();
        throw obj;
    }

    public void onStreamingServiceStopped(StreamingService streamingservice)
    {
        mKnownActiveStreamingServices.remove(streamingservice);
    }

    public void requestUpdateStreamingServices(List list)
    {
        IMbmsStreamingService imbmsstreamingservice;
        imbmsstreamingservice = (IMbmsStreamingService)mService.get();
        if(imbmsstreamingservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        int i = imbmsstreamingservice.requestUpdateStreamingServices(mSubscriptionId, list);
        if(i == 0)
            break MISSING_BLOCK_LABEL_47;
        sendErrorToApp(i, null);
_L1:
        return;
        list;
        Log.w("MbmsStreamingSession", "Remote process died");
        mService.set(null);
        sIsInitialized.set(false);
        sendErrorToApp(3, null);
          goto _L1
    }

    public StreamingService startStreaming(StreamingServiceInfo streamingserviceinfo, StreamingServiceCallback streamingservicecallback, Handler handler)
    {
        IMbmsStreamingService imbmsstreamingservice = (IMbmsStreamingService)mService.get();
        if(imbmsstreamingservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        streamingservicecallback = new InternalStreamingServiceCallback(streamingservicecallback, handler);
        handler = new StreamingService(mSubscriptionId, imbmsstreamingservice, this, streamingserviceinfo, streamingservicecallback);
        mKnownActiveStreamingServices.add(handler);
        int i;
        try
        {
            i = imbmsstreamingservice.startStreaming(mSubscriptionId, streamingserviceinfo.getServiceId(), streamingservicecallback);
        }
        // Misplaced declaration of an exception variable
        catch(StreamingServiceInfo streamingserviceinfo)
        {
            Log.w("MbmsStreamingSession", "Remote process died");
            mService.set(null);
            sIsInitialized.set(false);
            sendErrorToApp(3, null);
            return null;
        }
        if(i == 0)
            break MISSING_BLOCK_LABEL_129;
        sendErrorToApp(i, null);
        return null;
        return handler;
    }

    private static final String LOG_TAG = "MbmsStreamingSession";
    public static final String MBMS_STREAMING_SERVICE_ACTION = "android.telephony.action.EmbmsStreaming";
    private static AtomicBoolean sIsInitialized = new AtomicBoolean(false);
    private final Context mContext;
    private android.os.IBinder.DeathRecipient mDeathRecipient;
    private InternalStreamingSessionCallback mInternalCallback;
    private Set mKnownActiveStreamingServices;
    private AtomicReference mService;
    private int mSubscriptionId;

}
