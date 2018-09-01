// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import miui.maml.*;

public class RendererCoreCache
    implements miui.maml.RendererCore.OnReleaseListener
{
    private class CheckCacheRunnable
        implements Runnable
    {

        public void run()
        {
            RendererCoreCache._2D_wrap0(RendererCoreCache.this, mKey);
        }

        private Object mKey;
        final RendererCoreCache this$0;

        public CheckCacheRunnable(Object obj)
        {
            this$0 = RendererCoreCache.this;
            super();
            mKey = obj;
        }
    }

    public static interface OnCreateRootCallback
    {

        public abstract void onCreateRoot(ScreenElementRoot screenelementroot);
    }

    public static class RendererCoreInfo
    {

        public long accessTime;
        public long cacheTime;
        public CheckCacheRunnable checkCache;
        public RendererCore r;

        public RendererCoreInfo(RendererCore renderercore)
        {
            accessTime = 0x7fffffffffffffffL;
            r = renderercore;
        }
    }


    static void _2D_wrap0(RendererCoreCache renderercorecache, Object obj)
    {
        renderercorecache.checkCache(obj);
    }

    public RendererCoreCache()
    {
        mCaches = new HashMap();
        mHandler = new Handler();
    }

    public RendererCoreCache(Handler handler)
    {
        mCaches = new HashMap();
        mHandler = handler;
    }

    private void checkCache(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = JVM INSTR new #55  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("RendererCoreCache", ((StringBuilder) (obj1)).append("checkCache: ").append(obj).toString());
        obj1 = (RendererCoreInfo)mCaches.get(obj);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_75;
        obj1 = JVM INSTR new #55  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("RendererCoreCache", ((StringBuilder) (obj1)).append("checkCache: the key does not exist, ").append(obj).toString());
        this;
        JVM INSTR monitorexit ;
        return;
        long l = ((RendererCoreInfo) (obj1)).accessTime;
        if(l != 0x7fffffffffffffffL)
            break MISSING_BLOCK_LABEL_91;
        this;
        JVM INSTR monitorexit ;
        return;
        long l1 = System.currentTimeMillis() - ((RendererCoreInfo) (obj1)).accessTime;
        if(l1 < ((RendererCoreInfo) (obj1)).cacheTime) goto _L2; else goto _L1
_L1:
        mCaches.remove(obj);
        obj1 = JVM INSTR new #55  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("RendererCoreCache", ((StringBuilder) (obj1)).append("checkCache removed: ").append(obj).toString());
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        l = l1;
        if(l1 >= 0L)
            break MISSING_BLOCK_LABEL_169;
        obj1.accessTime = System.currentTimeMillis();
        l = 0L;
        mHandler.postDelayed(((RendererCoreInfo) (obj1)).checkCache, ((RendererCoreInfo) (obj1)).cacheTime - l);
        StringBuilder stringbuilder = JVM INSTR new #55  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("RendererCoreCache", stringbuilder.append("checkCache resheduled: ").append(obj).append(" after ").append(((RendererCoreInfo) (obj1)).cacheTime - l).toString());
          goto _L3
        obj;
        throw obj;
    }

    private RendererCoreInfo get(Object obj, Context context, long l, ResourceLoader resourceloader, String s, OnCreateRootCallback oncreaterootcallback)
    {
        RendererCoreInfo renderercoreinfo = get(obj, l);
        if(renderercoreinfo != null)
            return renderercoreinfo;
        if(resourceloader != null)
            context = ScreenElementRootFactory.create(new miui.maml.ScreenElementRootFactory.Parameter(context, resourceloader));
        else
            context = ScreenElementRootFactory.create(new miui.maml.ScreenElementRootFactory.Parameter(context, s));
        if(context == null)
        {
            Log.e("RendererCoreCache", (new StringBuilder()).append("fail to get RendererCoreInfo").append(obj).toString());
            return null;
        }
        if(oncreaterootcallback != null)
            oncreaterootcallback.onCreateRoot(context);
        context.setDefaultFramerate(0.0F);
        resourceloader = null;
        if(context.load())
            resourceloader = new RendererCore(context, RenderThread.globalThread(true));
        context = new RendererCoreInfo(resourceloader);
        context.accessTime = 0x7fffffffffffffffL;
        context.cacheTime = l;
        if(resourceloader != null)
        {
            resourceloader.setOnReleaseListener(this);
            context.checkCache = new CheckCacheRunnable(obj);
        }
        mCaches.put(obj, context);
        return context;
    }

    public boolean OnRendererCoreReleased(RendererCore renderercore)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        long l;
        Object obj = JVM INSTR new #55  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.d("RendererCoreCache", ((StringBuilder) (obj)).append("OnRendererCoreReleased: ").append(renderercore).toString());
        obj = mCaches.keySet().iterator();
        Object obj1;
        RendererCoreInfo renderercoreinfo;
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break MISSING_BLOCK_LABEL_110;
            obj1 = ((Iterator) (obj)).next();
            renderercoreinfo = (RendererCoreInfo)mCaches.get(obj1);
        } while(renderercoreinfo.r != renderercore);
        release(obj1);
        l = renderercoreinfo.cacheTime;
        if(l == 0L)
            flag = true;
        this;
        JVM INSTR monitorexit ;
        return flag;
        this;
        JVM INSTR monitorexit ;
        return false;
        renderercore;
        throw renderercore;
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        mCaches.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public RendererCoreInfo get(Object obj, long l)
    {
        this;
        JVM INSTR monitorenter ;
        obj = (RendererCoreInfo)mCaches.get(obj);
        if(obj == null)
            break MISSING_BLOCK_LABEL_45;
        obj.accessTime = 0x7fffffffffffffffL;
        obj.cacheTime = l;
        mHandler.removeCallbacks(((RendererCoreInfo) (obj)).checkCache);
        this;
        JVM INSTR monitorexit ;
        return ((RendererCoreInfo) (obj));
        this;
        JVM INSTR monitorexit ;
        return null;
        obj;
        throw obj;
    }

    public RendererCoreInfo get(Object obj, Context context, long l, String s, OnCreateRootCallback oncreaterootcallback)
    {
        this;
        JVM INSTR monitorenter ;
        obj = get(obj, context, l, null, s, oncreaterootcallback);
        this;
        JVM INSTR monitorexit ;
        return ((RendererCoreInfo) (obj));
        obj;
        throw obj;
    }

    public RendererCoreInfo get(Object obj, Context context, long l, ResourceLoader resourceloader, OnCreateRootCallback oncreaterootcallback)
    {
        this;
        JVM INSTR monitorenter ;
        obj = get(obj, context, l, resourceloader, null, oncreaterootcallback);
        this;
        JVM INSTR monitorexit ;
        return ((RendererCoreInfo) (obj));
        obj;
        throw obj;
    }

    public void release(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = JVM INSTR new #55  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("RendererCoreCache", ((StringBuilder) (obj1)).append("release: ").append(obj).toString());
        obj1 = (RendererCoreInfo)mCaches.get(obj);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_97;
        obj1.accessTime = System.currentTimeMillis();
        if(((RendererCoreInfo) (obj1)).cacheTime != 0L)
            break MISSING_BLOCK_LABEL_100;
        mCaches.remove(obj);
        obj1 = JVM INSTR new #55  <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.d("RendererCoreCache", ((StringBuilder) (obj1)).append("removed: ").append(obj).toString());
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        StringBuilder stringbuilder = JVM INSTR new #55  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("RendererCoreCache", stringbuilder.append("scheduled release: ").append(obj).append(" after ").append(((RendererCoreInfo) (obj1)).cacheTime).toString());
        mHandler.removeCallbacks(((RendererCoreInfo) (obj1)).checkCache);
        mHandler.postDelayed(((RendererCoreInfo) (obj1)).checkCache, ((RendererCoreInfo) (obj1)).cacheTime);
          goto _L1
        obj;
        throw obj;
    }

    private static final String LOG_TAG = "RendererCoreCache";
    public static final int TIME_DAY = 0x5265c00;
    public static final int TIME_HOUR = 0x36ee80;
    public static final int TIME_MIN = 60000;
    private HashMap mCaches;
    private Handler mHandler;
}
