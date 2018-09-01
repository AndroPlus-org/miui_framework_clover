// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.Handler;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package android.telephony.mbms:
//            MbmsStreamingSessionCallback

public class InternalStreamingSessionCallback extends IMbmsStreamingSessionCallback.Stub
{

    static MbmsStreamingSessionCallback _2D_get0(InternalStreamingSessionCallback internalstreamingsessioncallback)
    {
        return internalstreamingsessioncallback.mAppCallback;
    }

    public InternalStreamingSessionCallback(MbmsStreamingSessionCallback mbmsstreamingsessioncallback, Handler handler)
    {
        mIsStopped = false;
        mAppCallback = mbmsstreamingsessioncallback;
        mHandler = handler;
    }

    public Handler getHandler()
    {
        return mHandler;
    }

    public void onError(final int errorCode, final String message)
        throws RemoteException
    {
        if(mIsStopped)
        {
            return;
        } else
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    InternalStreamingSessionCallback._2D_get0(InternalStreamingSessionCallback.this).onError(errorCode, message);
                }

                final InternalStreamingSessionCallback this$0;
                final int val$errorCode;
                final String val$message;

            
            {
                this$0 = InternalStreamingSessionCallback.this;
                errorCode = i;
                message = s;
                super();
            }
            }
);
            return;
        }
    }

    public void onMiddlewareReady()
        throws RemoteException
    {
        if(mIsStopped)
        {
            return;
        } else
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    InternalStreamingSessionCallback._2D_get0(InternalStreamingSessionCallback.this).onMiddlewareReady();
                }

                final InternalStreamingSessionCallback this$0;

            
            {
                this$0 = InternalStreamingSessionCallback.this;
                super();
            }
            }
);
            return;
        }
    }

    public void onStreamingServicesUpdated(final List services)
        throws RemoteException
    {
        if(mIsStopped)
        {
            return;
        } else
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    InternalStreamingSessionCallback._2D_get0(InternalStreamingSessionCallback.this).onStreamingServicesUpdated(services);
                }

                final InternalStreamingSessionCallback this$0;
                final List val$services;

            
            {
                this$0 = InternalStreamingSessionCallback.this;
                services = list;
                super();
            }
            }
);
            return;
        }
    }

    public void stop()
    {
        mIsStopped = true;
    }

    private final MbmsStreamingSessionCallback mAppCallback;
    private final Handler mHandler;
    private volatile boolean mIsStopped;
}
