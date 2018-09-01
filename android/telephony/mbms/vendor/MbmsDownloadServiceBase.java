// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms.vendor;

import android.os.*;
import android.telephony.mbms.*;
import java.util.*;

public class MbmsDownloadServiceBase extends IMbmsDownloadService.Stub
{
    private static abstract class FilteredDownloadStateCallback extends DownloadStateCallback
    {

        public void onProgressUpdated(DownloadRequest downloadrequest, FileInfo fileinfo, int i, int j, int k, int l)
        {
            if(!isFilterFlagSet(1))
                return;
            mCallback.onProgressUpdated(downloadrequest, fileinfo, i, j, k, l);
_L1:
            return;
            downloadrequest;
            onRemoteException(downloadrequest);
              goto _L1
        }

        protected abstract void onRemoteException(RemoteException remoteexception);

        public void onStateUpdated(DownloadRequest downloadrequest, FileInfo fileinfo, int i)
        {
            if(!isFilterFlagSet(2))
                return;
            mCallback.onStateUpdated(downloadrequest, fileinfo, i);
_L1:
            return;
            downloadrequest;
            onRemoteException(downloadrequest);
              goto _L1
        }

        private final IDownloadStateCallback mCallback;

        public FilteredDownloadStateCallback(IDownloadStateCallback idownloadstatecallback, int i)
        {
            super(i);
            mCallback = idownloadstatecallback;
        }
    }


    static Map _2D_get0(MbmsDownloadServiceBase mbmsdownloadservicebase)
    {
        return mbmsdownloadservicebase.mDownloadCallbackBinderMap;
    }

    static Map _2D_get1(MbmsDownloadServiceBase mbmsdownloadservicebase)
    {
        return mbmsdownloadservicebase.mDownloadCallbackDeathRecipients;
    }

    public MbmsDownloadServiceBase()
    {
    }

    public int cancelDownload(DownloadRequest downloadrequest)
        throws RemoteException
    {
        return 0;
    }

    public void dispose(int i)
        throws RemoteException
    {
    }

    public int download(DownloadRequest downloadrequest)
        throws RemoteException
    {
        return 0;
    }

    public int getDownloadStatus(DownloadRequest downloadrequest, FileInfo fileinfo)
        throws RemoteException
    {
        return 0;
    }

    public final int initialize(final int subscriptionId, final IMbmsDownloadSessionCallback callback)
        throws RemoteException
    {
        final int uid = Binder.getCallingUid();
        callback.asBinder().linkToDeath(new android.os.IBinder.DeathRecipient() {

            public void binderDied()
            {
                onAppCallbackDied(uid, subscriptionId);
            }

            final MbmsDownloadServiceBase this$0;
            final int val$subscriptionId;
            final int val$uid;

            
            {
                this$0 = MbmsDownloadServiceBase.this;
                uid = i;
                subscriptionId = j;
                super();
            }
        }
, 0);
        return initialize(subscriptionId, ((MbmsDownloadSessionCallback) (new MbmsDownloadSessionCallback() {

            public void onError(int i, String s)
            {
                callback.onError(i, s);
_L1:
                return;
                s;
                onAppCallbackDied(uid, subscriptionId);
                  goto _L1
            }

            public void onFileServicesUpdated(List list)
            {
                callback.onFileServicesUpdated(list);
_L1:
                return;
                list;
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

            final MbmsDownloadServiceBase this$0;
            final IMbmsDownloadSessionCallback val$callback;
            final int val$subscriptionId;
            final int val$uid;

            
            {
                this$0 = MbmsDownloadServiceBase.this;
                callback = imbmsdownloadsessioncallback;
                uid = i;
                subscriptionId = j;
                super();
            }
        }
)));
    }

    public int initialize(int i, MbmsDownloadSessionCallback mbmsdownloadsessioncallback)
        throws RemoteException
    {
        return 0;
    }

    public List listPendingDownloads(int i)
        throws RemoteException
    {
        return null;
    }

    public void onAppCallbackDied(int i, int j)
    {
    }

    public int registerStateCallback(DownloadRequest downloadrequest, DownloadStateCallback downloadstatecallback)
        throws RemoteException
    {
        return 0;
    }

    public final int registerStateCallback(final DownloadRequest downloadRequest, final IDownloadStateCallback callback, final int final_i)
        throws RemoteException
    {
        final int uid = Binder.getCallingUid();
        Object obj = new android.os.IBinder.DeathRecipient() {

            public void binderDied()
            {
                onAppCallbackDied(uid, downloadRequest.getSubscriptionId());
                MbmsDownloadServiceBase._2D_get0(MbmsDownloadServiceBase.this).remove(callback.asBinder());
                MbmsDownloadServiceBase._2D_get1(MbmsDownloadServiceBase.this).remove(callback.asBinder());
            }

            final MbmsDownloadServiceBase this$0;
            final IDownloadStateCallback val$callback;
            final DownloadRequest val$downloadRequest;
            final int val$uid;

            
            {
                this$0 = MbmsDownloadServiceBase.this;
                uid = i;
                downloadRequest = downloadrequest;
                callback = idownloadstatecallback;
                super();
            }
        }
;
        mDownloadCallbackDeathRecipients.put(callback.asBinder(), obj);
        callback.asBinder().linkToDeath(((android.os.IBinder.DeathRecipient) (obj)), 0);
        obj = new FilteredDownloadStateCallback(uid, downloadRequest) {

            protected void onRemoteException(RemoteException remoteexception)
            {
                onAppCallbackDied(uid, downloadRequest.getSubscriptionId());
            }

            final MbmsDownloadServiceBase this$0;
            final DownloadRequest val$downloadRequest;
            final int val$uid;

            
            {
                this$0 = MbmsDownloadServiceBase.this;
                uid = j;
                downloadRequest = downloadrequest;
                super(final_idownloadstatecallback, final_i);
            }
        }
;
        mDownloadCallbackBinderMap.put(callback.asBinder(), obj);
        return registerStateCallback(downloadRequest, ((DownloadStateCallback) (obj)));
    }

    public int requestUpdateFileServices(int i, List list)
        throws RemoteException
    {
        return 0;
    }

    public int resetDownloadKnowledge(DownloadRequest downloadrequest)
        throws RemoteException
    {
        return 0;
    }

    public int setTempFileRootDirectory(int i, String s)
        throws RemoteException
    {
        return 0;
    }

    public int unregisterStateCallback(DownloadRequest downloadrequest, DownloadStateCallback downloadstatecallback)
        throws RemoteException
    {
        return 0;
    }

    public final int unregisterStateCallback(DownloadRequest downloadrequest, IDownloadStateCallback idownloadstatecallback)
        throws RemoteException
    {
        android.os.IBinder.DeathRecipient deathrecipient = (android.os.IBinder.DeathRecipient)mDownloadCallbackDeathRecipients.remove(idownloadstatecallback.asBinder());
        if(deathrecipient == null)
            throw new IllegalArgumentException("Unknown callback");
        idownloadstatecallback.asBinder().unlinkToDeath(deathrecipient, 0);
        idownloadstatecallback = (DownloadStateCallback)mDownloadCallbackBinderMap.remove(idownloadstatecallback.asBinder());
        if(idownloadstatecallback == null)
            throw new IllegalArgumentException("Unknown callback");
        else
            return unregisterStateCallback(downloadrequest, ((DownloadStateCallback) (idownloadstatecallback)));
    }

    private final Map mDownloadCallbackBinderMap = new HashMap();
    private final Map mDownloadCallbackDeathRecipients = new HashMap();
}
