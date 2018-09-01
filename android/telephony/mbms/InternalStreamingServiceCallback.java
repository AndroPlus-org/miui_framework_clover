// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.Handler;
import android.os.RemoteException;

// Referenced classes of package android.telephony.mbms:
//            StreamingServiceCallback

public class InternalStreamingServiceCallback extends IStreamingServiceCallback.Stub
{

    static StreamingServiceCallback _2D_get0(InternalStreamingServiceCallback internalstreamingservicecallback)
    {
        return internalstreamingservicecallback.mAppCallback;
    }

    public InternalStreamingServiceCallback(StreamingServiceCallback streamingservicecallback, Handler handler)
    {
        mIsStopped = false;
        mAppCallback = streamingservicecallback;
        mHandler = handler;
    }

    public void onBroadcastSignalStrengthUpdated(final int signalStrength)
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
                    InternalStreamingServiceCallback._2D_get0(InternalStreamingServiceCallback.this).onBroadcastSignalStrengthUpdated(signalStrength);
                }

                final InternalStreamingServiceCallback this$0;
                final int val$signalStrength;

            
            {
                this$0 = InternalStreamingServiceCallback.this;
                signalStrength = i;
                super();
            }
            }
);
            return;
        }
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
                    InternalStreamingServiceCallback._2D_get0(InternalStreamingServiceCallback.this).onError(errorCode, message);
                }

                final InternalStreamingServiceCallback this$0;
                final int val$errorCode;
                final String val$message;

            
            {
                this$0 = InternalStreamingServiceCallback.this;
                errorCode = i;
                message = s;
                super();
            }
            }
);
            return;
        }
    }

    public void onMediaDescriptionUpdated()
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
                    InternalStreamingServiceCallback._2D_get0(InternalStreamingServiceCallback.this).onMediaDescriptionUpdated();
                }

                final InternalStreamingServiceCallback this$0;

            
            {
                this$0 = InternalStreamingServiceCallback.this;
                super();
            }
            }
);
            return;
        }
    }

    public void onStreamMethodUpdated(final int methodType)
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
                    InternalStreamingServiceCallback._2D_get0(InternalStreamingServiceCallback.this).onStreamMethodUpdated(methodType);
                }

                final InternalStreamingServiceCallback this$0;
                final int val$methodType;

            
            {
                this$0 = InternalStreamingServiceCallback.this;
                methodType = i;
                super();
            }
            }
);
            return;
        }
    }

    public void onStreamStateUpdated(final int state, final int reason)
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
                    InternalStreamingServiceCallback._2D_get0(InternalStreamingServiceCallback.this).onStreamStateUpdated(state, reason);
                }

                final InternalStreamingServiceCallback this$0;
                final int val$reason;
                final int val$state;

            
            {
                this$0 = InternalStreamingServiceCallback.this;
                state = i;
                reason = j;
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

    private final StreamingServiceCallback mAppCallback;
    private final Handler mHandler;
    private volatile boolean mIsStopped;
}
