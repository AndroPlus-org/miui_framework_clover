// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.Handler;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package android.telephony.mbms:
//            MbmsDownloadSessionCallback

public class InternalDownloadSessionCallback extends IMbmsDownloadSessionCallback.Stub
{

    static MbmsDownloadSessionCallback _2D_get0(InternalDownloadSessionCallback internaldownloadsessioncallback)
    {
        return internaldownloadsessioncallback.mAppCallback;
    }

    public InternalDownloadSessionCallback(MbmsDownloadSessionCallback mbmsdownloadsessioncallback, Handler handler)
    {
        mIsStopped = false;
        mAppCallback = mbmsdownloadsessioncallback;
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
                    InternalDownloadSessionCallback._2D_get0(InternalDownloadSessionCallback.this).onError(errorCode, message);
                }

                final InternalDownloadSessionCallback this$0;
                final int val$errorCode;
                final String val$message;

            
            {
                this$0 = InternalDownloadSessionCallback.this;
                errorCode = i;
                message = s;
                super();
            }
            }
);
            return;
        }
    }

    public void onFileServicesUpdated(final List services)
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
                    InternalDownloadSessionCallback._2D_get0(InternalDownloadSessionCallback.this).onFileServicesUpdated(services);
                }

                final InternalDownloadSessionCallback this$0;
                final List val$services;

            
            {
                this$0 = InternalDownloadSessionCallback.this;
                services = list;
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
                    InternalDownloadSessionCallback._2D_get0(InternalDownloadSessionCallback.this).onMiddlewareReady();
                }

                final InternalDownloadSessionCallback this$0;

            
            {
                this$0 = InternalDownloadSessionCallback.this;
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

    private final MbmsDownloadSessionCallback mAppCallback;
    private final Handler mHandler;
    private volatile boolean mIsStopped;
}
