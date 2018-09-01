// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.euicc.DownloadableSubscription;
import android.telephony.euicc.EuiccInfo;
import android.util.ArraySet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.service.euicc:
//            GetDefaultDownloadableSubscriptionListResult, GetDownloadableSubscriptionMetadataResult, GetEuiccProfileInfoListResult, IDeleteSubscriptionCallback, 
//            IDownloadSubscriptionCallback, IEraseSubscriptionsCallback, IGetDefaultDownloadableSubscriptionListCallback, IGetDownloadableSubscriptionMetadataCallback, 
//            IGetEidCallback, IGetEuiccInfoCallback, IGetEuiccProfileInfoListCallback, IRetainSubscriptionsForFactoryResetCallback, 
//            ISwitchToSubscriptionCallback, IUpdateSubscriptionNicknameCallback

public abstract class EuiccService extends Service
{
    private class IEuiccServiceWrapper extends IEuiccService.Stub
    {

        public void deleteSubscription(final int slotId, final String iccid, IDeleteSubscriptionCallback ideletesubscriptioncallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(ideletesubscriptioncallback. new Runnable() {

                public void run()
                {
                    int i = onDeleteSubscription(slotId, iccid);
                    callback.onComplete(i);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IDeleteSubscriptionCallback val$callback;
                final String val$iccid;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                iccid = s;
                callback = IDeleteSubscriptionCallback.this;
                super();
            }
            }
);
        }

        public void downloadSubscription(final int slotId, final DownloadableSubscription subscription, final boolean switchAfterDownload, final boolean forceDeactivateSim, IDownloadSubscriptionCallback idownloadsubscriptioncallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(idownloadsubscriptioncallback. new Runnable() {

                public void run()
                {
                    int i = onDownloadSubscription(slotId, subscription, switchAfterDownload, forceDeactivateSim);
                    callback.onComplete(i);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IDownloadSubscriptionCallback val$callback;
                final boolean val$forceDeactivateSim;
                final int val$slotId;
                final DownloadableSubscription val$subscription;
                final boolean val$switchAfterDownload;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                subscription = downloadablesubscription;
                switchAfterDownload = flag;
                forceDeactivateSim = flag1;
                callback = IDownloadSubscriptionCallback.this;
                super();
            }
            }
);
        }

        public void eraseSubscriptions(final int slotId, IEraseSubscriptionsCallback ierasesubscriptionscallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(ierasesubscriptionscallback. new Runnable() {

                public void run()
                {
                    int i = onEraseSubscriptions(slotId);
                    callback.onComplete(i);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IEraseSubscriptionsCallback val$callback;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                callback = IEraseSubscriptionsCallback.this;
                super();
            }
            }
);
        }

        public void getDefaultDownloadableSubscriptionList(final int slotId, final boolean forceDeactivateSim, IGetDefaultDownloadableSubscriptionListCallback igetdefaultdownloadablesubscriptionlistcallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(igetdefaultdownloadablesubscriptionlistcallback. new Runnable() {

                public void run()
                {
                    GetDefaultDownloadableSubscriptionListResult getdefaultdownloadablesubscriptionlistresult = onGetDefaultDownloadableSubscriptionList(slotId, forceDeactivateSim);
                    callback.onComplete(getdefaultdownloadablesubscriptionlistresult);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IGetDefaultDownloadableSubscriptionListCallback val$callback;
                final boolean val$forceDeactivateSim;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                forceDeactivateSim = flag;
                callback = IGetDefaultDownloadableSubscriptionListCallback.this;
                super();
            }
            }
);
        }

        public void getDownloadableSubscriptionMetadata(final int slotId, final DownloadableSubscription subscription, final boolean forceDeactivateSim, IGetDownloadableSubscriptionMetadataCallback igetdownloadablesubscriptionmetadatacallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(igetdownloadablesubscriptionmetadatacallback. new Runnable() {

                public void run()
                {
                    GetDownloadableSubscriptionMetadataResult getdownloadablesubscriptionmetadataresult = onGetDownloadableSubscriptionMetadata(slotId, subscription, forceDeactivateSim);
                    callback.onComplete(getdownloadablesubscriptionmetadataresult);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IGetDownloadableSubscriptionMetadataCallback val$callback;
                final boolean val$forceDeactivateSim;
                final int val$slotId;
                final DownloadableSubscription val$subscription;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                subscription = downloadablesubscription;
                forceDeactivateSim = flag;
                callback = IGetDownloadableSubscriptionMetadataCallback.this;
                super();
            }
            }
);
        }

        public void getEid(final int slotId, IGetEidCallback igeteidcallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(igeteidcallback. new Runnable() {

                public void run()
                {
                    String s = onGetEid(slotId);
                    callback.onSuccess(s);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IGetEidCallback val$callback;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                callback = IGetEidCallback.this;
                super();
            }
            }
);
        }

        public void getEuiccInfo(final int slotId, IGetEuiccInfoCallback igeteuiccinfocallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(igeteuiccinfocallback. new Runnable() {

                public void run()
                {
                    EuiccInfo euiccinfo = onGetEuiccInfo(slotId);
                    callback.onSuccess(euiccinfo);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IGetEuiccInfoCallback val$callback;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                callback = IGetEuiccInfoCallback.this;
                super();
            }
            }
);
        }

        public void getEuiccProfileInfoList(final int slotId, IGetEuiccProfileInfoListCallback igeteuiccprofileinfolistcallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(igeteuiccprofileinfolistcallback. new Runnable() {

                public void run()
                {
                    GetEuiccProfileInfoListResult geteuiccprofileinfolistresult = onGetEuiccProfileInfoList(slotId);
                    callback.onComplete(geteuiccprofileinfolistresult);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IGetEuiccProfileInfoListCallback val$callback;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                callback = IGetEuiccProfileInfoListCallback.this;
                super();
            }
            }
);
        }

        public void retainSubscriptionsForFactoryReset(final int slotId, IRetainSubscriptionsForFactoryResetCallback iretainsubscriptionsforfactoryresetcallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(iretainsubscriptionsforfactoryresetcallback. new Runnable() {

                public void run()
                {
                    int i = onRetainSubscriptionsForFactoryReset(slotId);
                    callback.onComplete(i);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IRetainSubscriptionsForFactoryResetCallback val$callback;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                callback = IRetainSubscriptionsForFactoryResetCallback.this;
                super();
            }
            }
);
        }

        public void switchToSubscription(final int slotId, final String iccid, final boolean forceDeactivateSim, ISwitchToSubscriptionCallback iswitchtosubscriptioncallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(iswitchtosubscriptioncallback. new Runnable() {

                public void run()
                {
                    int i = onSwitchToSubscription(slotId, iccid, forceDeactivateSim);
                    callback.onComplete(i);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final ISwitchToSubscriptionCallback val$callback;
                final boolean val$forceDeactivateSim;
                final String val$iccid;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                iccid = s;
                forceDeactivateSim = flag;
                callback = ISwitchToSubscriptionCallback.this;
                super();
            }
            }
);
        }

        public void updateSubscriptionNickname(final int slotId, final String iccid, final String nickname, IUpdateSubscriptionNicknameCallback iupdatesubscriptionnicknamecallback)
        {
            EuiccService._2D_get0(EuiccService.this).execute(iupdatesubscriptionnicknamecallback. new Runnable() {

                public void run()
                {
                    int i = onUpdateSubscriptionNickname(slotId, iccid, nickname);
                    callback.onComplete(i);
_L2:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final IEuiccServiceWrapper this$1;
                final IUpdateSubscriptionNicknameCallback val$callback;
                final String val$iccid;
                final String val$nickname;
                final int val$slotId;

            
            {
                this$1 = final_ieuiccservicewrapper;
                slotId = i;
                iccid = s;
                nickname = s1;
                callback = IUpdateSubscriptionNicknameCallback.this;
                super();
            }
            }
);
        }

        final EuiccService this$0;

        private IEuiccServiceWrapper()
        {
            this$0 = EuiccService.this;
            super();
        }

        IEuiccServiceWrapper(IEuiccServiceWrapper ieuiccservicewrapper)
        {
            this();
        }
    }


    static ThreadPoolExecutor _2D_get0(EuiccService euiccservice)
    {
        return euiccservice.mExecutor;
    }

    public EuiccService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return mStubWrapper;
    }

    public void onCreate()
    {
        super.onCreate();
        mExecutor = new ThreadPoolExecutor(4, 4, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {

            public Thread newThread(Runnable runnable)
            {
                return new Thread(runnable, (new StringBuilder()).append("EuiccService #").append(mCount.getAndIncrement()).toString());
            }

            private final AtomicInteger mCount = new AtomicInteger(1);
            final EuiccService this$0;

            
            {
                this$0 = EuiccService.this;
                super();
            }
        }
);
        mExecutor.allowCoreThreadTimeOut(true);
    }

    public abstract int onDeleteSubscription(int i, String s);

    public void onDestroy()
    {
        mExecutor.shutdownNow();
        super.onDestroy();
    }

    public abstract int onDownloadSubscription(int i, DownloadableSubscription downloadablesubscription, boolean flag, boolean flag1);

    public abstract int onEraseSubscriptions(int i);

    public abstract GetDefaultDownloadableSubscriptionListResult onGetDefaultDownloadableSubscriptionList(int i, boolean flag);

    public abstract GetDownloadableSubscriptionMetadataResult onGetDownloadableSubscriptionMetadata(int i, DownloadableSubscription downloadablesubscription, boolean flag);

    public abstract String onGetEid(int i);

    public abstract EuiccInfo onGetEuiccInfo(int i);

    public abstract GetEuiccProfileInfoListResult onGetEuiccProfileInfoList(int i);

    public abstract int onRetainSubscriptionsForFactoryReset(int i);

    public abstract int onSwitchToSubscription(int i, String s, boolean flag);

    public abstract int onUpdateSubscriptionNickname(int i, String s, String s1);

    public static final String ACTION_MANAGE_EMBEDDED_SUBSCRIPTIONS = "android.service.euicc.action.MANAGE_EMBEDDED_SUBSCRIPTIONS";
    public static final String ACTION_PROVISION_EMBEDDED_SUBSCRIPTION = "android.service.euicc.action.PROVISION_EMBEDDED_SUBSCRIPTION";
    public static final String ACTION_RESOLVE_DEACTIVATE_SIM = "android.service.euicc.action.RESOLVE_DEACTIVATE_SIM";
    public static final String ACTION_RESOLVE_NO_PRIVILEGES = "android.service.euicc.action.RESOLVE_NO_PRIVILEGES";
    public static final String CATEGORY_EUICC_UI = "android.service.euicc.category.EUICC_UI";
    public static final String EUICC_SERVICE_INTERFACE = "android.service.euicc.EuiccService";
    public static final String EXTRA_RESOLUTION_CALLING_PACKAGE = "android.service.euicc.extra.RESOLUTION_CALLING_PACKAGE";
    public static final ArraySet RESOLUTION_ACTIONS;
    public static final String RESOLUTION_EXTRA_CONSENT = "consent";
    public static final int RESULT_FIRST_USER = 1;
    public static final int RESULT_MUST_DEACTIVATE_SIM = -1;
    public static final int RESULT_OK = 0;
    private ThreadPoolExecutor mExecutor;
    private final IEuiccService.Stub mStubWrapper = new IEuiccServiceWrapper(null);

    static 
    {
        RESOLUTION_ACTIONS = new ArraySet();
        RESOLUTION_ACTIONS.add("android.service.euicc.action.RESOLVE_DEACTIVATE_SIM");
        RESOLUTION_ACTIONS.add("android.service.euicc.action.RESOLVE_NO_PRIVILEGES");
    }
}
