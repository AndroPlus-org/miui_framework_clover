// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.resolver;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import java.util.List;

// Referenced classes of package android.service.resolver:
//            IResolverRankerResult

public abstract class ResolverRankerService extends Service
{
    private class ResolverRankerServiceWrapper extends IResolverRankerService.Stub
    {

        public void predict(final List targets, IResolverRankerResult iresolverrankerresult)
            throws RemoteException
        {
            targets = iresolverrankerresult. new Runnable() {

                public void run()
                {
                    onPredictSharingProbabilities(targets);
                    ResolverRankerService._2D_wrap0(targets, result);
_L1:
                    return;
                    Exception exception;
                    exception;
                    Log.e("ResolverRankerService", (new StringBuilder()).append("onPredictSharingProbabilities failed; send null results: ").append(exception).toString());
                    ResolverRankerService._2D_wrap0(null, result);
                      goto _L1
                }

                final ResolverRankerServiceWrapper this$1;
                final IResolverRankerResult val$result;
                final List val$targets;

            
            {
                this$1 = final_resolverrankerservicewrapper;
                targets = list;
                result = IResolverRankerResult.this;
                super();
            }
            }
;
            iresolverrankerresult = ResolverRankerService._2D_get0(ResolverRankerService.this);
            if(iresolverrankerresult != null)
                iresolverrankerresult.post(targets);
        }

        public void train(final List targets, int i)
            throws RemoteException
        {
            Runnable runnable = i. new Runnable() {

                public void run()
                {
                    onTrainRankingModel(targets, selectedPosition);
_L1:
                    return;
                    Exception exception;
                    exception;
                    Log.e("ResolverRankerService", (new StringBuilder()).append("onTrainRankingModel failed; skip train: ").append(exception).toString());
                      goto _L1
                }

                final ResolverRankerServiceWrapper this$1;
                final int val$selectedPosition;
                final List val$targets;

            
            {
                this$1 = final_resolverrankerservicewrapper;
                targets = list;
                selectedPosition = I.this;
                super();
            }
            }
;
            targets = ResolverRankerService._2D_get0(ResolverRankerService.this);
            if(targets != null)
                targets.post(runnable);
        }

        final ResolverRankerService this$0;

        private ResolverRankerServiceWrapper()
        {
            this$0 = ResolverRankerService.this;
            super();
        }

        ResolverRankerServiceWrapper(ResolverRankerServiceWrapper resolverrankerservicewrapper)
        {
            this();
        }
    }


    static Handler _2D_get0(ResolverRankerService resolverrankerservice)
    {
        return resolverrankerservice.mHandler;
    }

    static void _2D_wrap0(List list, IResolverRankerResult iresolverrankerresult)
    {
        sendResult(list, iresolverrankerresult);
    }

    public ResolverRankerService()
    {
        mWrapper = null;
    }

    private static void sendResult(List list, IResolverRankerResult iresolverrankerresult)
    {
        iresolverrankerresult.sendResult(list);
_L1:
        return;
        list;
        Log.e("ResolverRankerService", (new StringBuilder()).append("failed to send results: ").append(list).toString());
          goto _L1
    }

    public IBinder onBind(Intent intent)
    {
        if(!"android.service.resolver.ResolverRankerService".equals(intent.getAction()))
            return null;
        if(mHandlerThread == null)
        {
            mHandlerThread = new HandlerThread("RESOLVER_RANKER_SERVICE");
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
        }
        if(mWrapper == null)
            mWrapper = new ResolverRankerServiceWrapper(null);
        return mWrapper;
    }

    public void onDestroy()
    {
        mHandler = null;
        if(mHandlerThread != null)
            mHandlerThread.quitSafely();
        super.onDestroy();
    }

    public void onPredictSharingProbabilities(List list)
    {
    }

    public void onTrainRankingModel(List list, int i)
    {
    }

    public static final String BIND_PERMISSION = "android.permission.BIND_RESOLVER_RANKER_SERVICE";
    private static final boolean DEBUG = false;
    private static final String HANDLER_THREAD_NAME = "RESOLVER_RANKER_SERVICE";
    public static final String HOLD_PERMISSION = "android.permission.PROVIDE_RESOLVER_RANKER_SERVICE";
    public static final String SERVICE_INTERFACE = "android.service.resolver.ResolverRankerService";
    private static final String TAG = "ResolverRankerService";
    private volatile Handler mHandler;
    private HandlerThread mHandlerThread;
    private ResolverRankerServiceWrapper mWrapper;
}
