// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.Handler;
import android.os.RemoteException;

// Referenced classes of package android.telephony.mbms:
//            DownloadStateCallback, DownloadRequest, FileInfo

public class InternalDownloadStateCallback extends IDownloadStateCallback.Stub
{

    static DownloadStateCallback _2D_get0(InternalDownloadStateCallback internaldownloadstatecallback)
    {
        return internaldownloadstatecallback.mAppCallback;
    }

    public InternalDownloadStateCallback(DownloadStateCallback downloadstatecallback, Handler handler)
    {
        mIsStopped = false;
        mAppCallback = downloadstatecallback;
        mHandler = handler;
    }

    public void onProgressUpdated(final DownloadRequest request, final FileInfo fileInfo, final int currentDownloadSize, final int fullDownloadSize, final int currentDecodedSize, final int fullDecodedSize)
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
                    InternalDownloadStateCallback._2D_get0(InternalDownloadStateCallback.this).onProgressUpdated(request, fileInfo, currentDownloadSize, fullDownloadSize, currentDecodedSize, fullDecodedSize);
                }

                final InternalDownloadStateCallback this$0;
                final int val$currentDecodedSize;
                final int val$currentDownloadSize;
                final FileInfo val$fileInfo;
                final int val$fullDecodedSize;
                final int val$fullDownloadSize;
                final DownloadRequest val$request;

            
            {
                this$0 = InternalDownloadStateCallback.this;
                request = downloadrequest;
                fileInfo = fileinfo;
                currentDownloadSize = i;
                fullDownloadSize = j;
                currentDecodedSize = k;
                fullDecodedSize = l;
                super();
            }
            }
);
            return;
        }
    }

    public void onStateUpdated(final DownloadRequest request, final FileInfo fileInfo, final int state)
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
                    InternalDownloadStateCallback._2D_get0(InternalDownloadStateCallback.this).onStateUpdated(request, fileInfo, state);
                }

                final InternalDownloadStateCallback this$0;
                final FileInfo val$fileInfo;
                final DownloadRequest val$request;
                final int val$state;

            
            {
                this$0 = InternalDownloadStateCallback.this;
                request = downloadrequest;
                fileInfo = fileinfo;
                state = i;
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

    private final DownloadStateCallback mAppCallback;
    private final Handler mHandler;
    private volatile boolean mIsStopped;
}
