// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.util.Pair;
import java.util.List;

public abstract class CacheQuotaService extends Service
{
    private final class CacheQuotaServiceWrapper extends ICacheQuotaService.Stub
    {

        public void computeCacheQuotaHints(RemoteCallback remotecallback, List list)
        {
            remotecallback = Pair.create(remotecallback, list);
            remotecallback = CacheQuotaService._2D_get0(CacheQuotaService.this).obtainMessage(1, remotecallback);
            CacheQuotaService._2D_get0(CacheQuotaService.this).sendMessage(remotecallback);
        }

        final CacheQuotaService this$0;

        private CacheQuotaServiceWrapper()
        {
            this$0 = CacheQuotaService.this;
            super();
        }

        CacheQuotaServiceWrapper(CacheQuotaServiceWrapper cachequotaservicewrapper)
        {
            this();
        }
    }

    private final class ServiceHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            int i = message.what;
            i;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 50;
               goto _L1 _L2
_L1:
            Log.w("CacheQuotaService", (new StringBuilder()).append("Handling unknown message: ").append(i).toString());
_L4:
            return;
_L2:
            Pair pair = (Pair)message.obj;
            message = onComputeCacheQuotaHints((List)pair.second);
            Bundle bundle = new Bundle();
            bundle.putParcelableList("requests", message);
            ((RemoteCallback)pair.first).sendResult(bundle);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static final int MSG_SEND_LIST = 1;
        final CacheQuotaService this$0;

        public ServiceHandler(Looper looper)
        {
            this$0 = CacheQuotaService.this;
            super(looper, null, true);
        }
    }


    static Handler _2D_get0(CacheQuotaService cachequotaservice)
    {
        return cachequotaservice.mHandler;
    }

    public CacheQuotaService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return mWrapper;
    }

    public abstract List onComputeCacheQuotaHints(List list);

    public void onCreate()
    {
        super.onCreate();
        mWrapper = new CacheQuotaServiceWrapper(null);
        mHandler = new ServiceHandler(getMainLooper());
    }

    public static final String REQUEST_LIST_KEY = "requests";
    public static final String SERVICE_INTERFACE = "android.app.usage.CacheQuotaService";
    private static final String TAG = "CacheQuotaService";
    private Handler mHandler;
    private CacheQuotaServiceWrapper mWrapper;
}
