// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice.recommendation;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import java.util.List;

// Referenced classes of package android.printservice.recommendation:
//            IRecommendationServiceCallbacks

public abstract class RecommendationService extends Service
{
    private class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 33
        //                       2 58
        //                       3 77;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            RecommendationService._2D_set0(RecommendationService.this, (IRecommendationServiceCallbacks)message.obj);
            onConnected();
            continue; /* Loop/switch isn't completed */
_L3:
            onDisconnected();
            RecommendationService._2D_set0(RecommendationService.this, null);
            continue; /* Loop/switch isn't completed */
_L4:
            try
            {
                RecommendationService._2D_get0(RecommendationService.this).onRecommendationsUpdated((List)message.obj);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("PrintServiceRecS", "Could not update recommended services", message);
            }
            if(true) goto _L1; else goto _L5
_L5:
        }

        static final int MSG_CONNECT = 1;
        static final int MSG_DISCONNECT = 2;
        static final int MSG_UPDATE = 3;
        final RecommendationService this$0;

        MyHandler()
        {
            this$0 = RecommendationService.this;
            super(Looper.getMainLooper());
        }
    }


    static IRecommendationServiceCallbacks _2D_get0(RecommendationService recommendationservice)
    {
        return recommendationservice.mCallbacks;
    }

    static Handler _2D_get1(RecommendationService recommendationservice)
    {
        return recommendationservice.mHandler;
    }

    static IRecommendationServiceCallbacks _2D_set0(RecommendationService recommendationservice, IRecommendationServiceCallbacks irecommendationservicecallbacks)
    {
        recommendationservice.mCallbacks = irecommendationservicecallbacks;
        return irecommendationservicecallbacks;
    }

    public RecommendationService()
    {
    }

    protected void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        mHandler = new MyHandler();
    }

    public final IBinder onBind(Intent intent)
    {
        return new IRecommendationService.Stub() {

            public void registerCallbacks(IRecommendationServiceCallbacks irecommendationservicecallbacks)
            {
                if(irecommendationservicecallbacks != null)
                    RecommendationService._2D_get1(RecommendationService.this).obtainMessage(1, irecommendationservicecallbacks).sendToTarget();
                else
                    RecommendationService._2D_get1(RecommendationService.this).obtainMessage(2).sendToTarget();
            }

            final RecommendationService this$0;

            
            {
                this$0 = RecommendationService.this;
                super();
            }
        }
;
    }

    public abstract void onConnected();

    public abstract void onDisconnected();

    public final void updateRecommendations(List list)
    {
        mHandler.obtainMessage(3, list).sendToTarget();
    }

    private static final String LOG_TAG = "PrintServiceRecS";
    public static final String SERVICE_INTERFACE = "android.printservice.recommendation.RecommendationService";
    private IRecommendationServiceCallbacks mCallbacks;
    private Handler mHandler;
}
