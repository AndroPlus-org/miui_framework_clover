// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.util.Log;
import android.util.LruCache;
import java.util.*;

// Referenced classes of package miui.maml:
//            ResourceManager, ResourceLoader

public class LifecycleResourceManager extends ResourceManager
{

    public LifecycleResourceManager(ResourceLoader resourceloader, long l, long l1)
    {
        super(resourceloader);
        mInactiveTime = l;
        mCheckTime = l1;
    }

    public void checkCache()
    {
        long l;
        Object obj;
        l = System.currentTimeMillis();
        if(l - mLastCheckCacheTime < mCheckTime)
            return;
        Log.d("LifecycleResourceManager", "begin check cache... ");
        obj = new ArrayList();
        LruCache lrucache = mBitmapsCache;
        lrucache;
        JVM INSTR monitorenter ;
        Iterator iterator = mBitmapsCache.snapshot().keySet().iterator();
_L2:
        String s;
        ResourceManager.BitmapInfo bitmapinfo;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_136;
            s = (String)iterator.next();
            bitmapinfo = (ResourceManager.BitmapInfo)mBitmapsCache.get(s);
        } while(bitmapinfo == null);
        if(l - bitmapinfo.mLastVisitTime <= mInactiveTime) goto _L2; else goto _L1
_L1:
        ((ArrayList) (obj)).add(s);
          goto _L2
        Exception exception;
        exception;
        throw exception;
        for(Iterator iterator1 = ((Iterable) (obj)).iterator(); iterator1.hasNext(); mBitmapsCache.remove(obj))
        {
            obj = (String)iterator1.next();
            StringBuilder stringbuilder = JVM INSTR new #98  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("LifecycleResourceManager", stringbuilder.append("remove cache: ").append(((String) (obj))).toString());
        }

        lrucache;
        JVM INSTR monitorexit ;
        mLastCheckCacheTime = l;
        return;
    }

    public void finish(boolean flag)
    {
        if(flag)
            checkCache();
        super.finish(flag);
    }

    public void pause()
    {
        checkCache();
    }

    private static final String LOG_TAG = "LifecycleResourceManager";
    public static final int TIME_DAY = 0x5265c00;
    public static final int TIME_HOUR = 0x36ee80;
    private static long mLastCheckCacheTime;
    private long mCheckTime;
    private long mInactiveTime;
}
