// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.AsyncTask;
import android.os.MemoryFile;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.*;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml:
//            ResourceLoader

public class ResourceManager
{
    public static interface AsyncLoadListener
    {

        public abstract void onLoadComplete(String s, BitmapInfo bitmapinfo);
    }

    public static class BitmapInfo
    {

        protected void finalize()
            throws Throwable
        {
            HashMap hashmap = mWeakRefCache;
            hashmap;
            JVM INSTR monitorenter ;
            if(mWeakRefCache != null)
            {
                mWeakRefCache.remove(mKey);
                mWeakRefCache = null;
            }
            hashmap;
            JVM INSTR monitorexit ;
            super.finalize();
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public final Bitmap mBitmap;
        public String mKey;
        public long mLastVisitTime;
        public boolean mLoading;
        public final NinePatch mNinePatch;
        public final Rect mPadding;
        public HashMap mWeakRefCache;

        public BitmapInfo()
        {
            mBitmap = null;
            mPadding = null;
            mNinePatch = null;
        }

        public BitmapInfo(Bitmap bitmap, Rect rect)
        {
            mBitmap = bitmap;
            mPadding = rect;
            if(bitmap != null && bitmap.getNinePatchChunk() != null)
                mNinePatch = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
            else
                mNinePatch = null;
            mLastVisitTime = System.currentTimeMillis();
        }
    }

    private class LoadBitmapAsyncTask extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient BitmapInfo doInBackground(String as[])
        {
            mSrc = as[0];
            if(mSrc != null)
                return ResourceManager._2D_wrap0(ResourceManager.this, mSrc);
            else
                return null;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((BitmapInfo)obj);
        }

        protected void onPostExecute(BitmapInfo bitmapinfo)
        {
            HashSet hashset = ResourceManager._2D_get0(ResourceManager.this);
            hashset;
            JVM INSTR monitorenter ;
            mLoadListener.onLoadComplete(mSrc, bitmapinfo);
            ResourceManager._2D_get0(ResourceManager.this).remove(mSrc);
            hashset;
            JVM INSTR monitorexit ;
            return;
            bitmapinfo;
            throw bitmapinfo;
        }

        private AsyncLoadListener mLoadListener;
        private String mSrc;
        final ResourceManager this$0;

        public LoadBitmapAsyncTask(AsyncLoadListener asyncloadlistener)
        {
            this$0 = ResourceManager.this;
            super();
            mLoadListener = asyncloadlistener;
        }
    }


    static HashSet _2D_get0(ResourceManager resourcemanager)
    {
        return resourcemanager.mLoadingBitmaps;
    }

    static BitmapInfo _2D_wrap0(ResourceManager resourcemanager, String s)
    {
        return resourcemanager.loadBitmap(s);
    }

    public ResourceManager(ResourceLoader resourceloader)
    {
        mResourceLoader = resourceloader;
    }

    private BitmapInfo getCache(String s)
    {
        Object obj = mBitmapsCache;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (BitmapInfo)mBitmapsCache.get(s);
        obj;
        JVM INSTR monitorexit ;
        obj = mWeakRefBitmapsCache;
        obj;
        JVM INSTR monitorenter ;
        WeakReference weakreference = (WeakReference)mWeakRefBitmapsCache.get(s);
        obj;
        JVM INSTR monitorexit ;
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        obj1.mLastVisitTime = System.currentTimeMillis();
        if(weakreference == null) goto _L4; else goto _L3
_L3:
        obj = obj1;
        if(weakreference.get() != null) goto _L5; else goto _L4
_L4:
        obj = mWeakRefBitmapsCache;
        obj;
        JVM INSTR monitorenter ;
        HashMap hashmap = mWeakRefBitmapsCache;
        weakreference = JVM INSTR new #88  <Class WeakReference>;
        weakreference.WeakReference(obj1);
        hashmap.put(s, weakreference);
        s = ((String) (obj));
        obj = obj1;
_L8:
        s;
        JVM INSTR monitorexit ;
_L5:
        return ((BitmapInfo) (obj));
        s;
        throw s;
        s;
        throw s;
        s;
        throw s;
_L2:
        obj = obj1;
        if(weakreference == null)
            continue; /* Loop/switch isn't completed */
        obj = (BitmapInfo)weakreference.get();
        if(obj == null)
            break; /* Loop/switch isn't completed */
        obj.mLastVisitTime = System.currentTimeMillis();
        obj1 = mBitmapsCache;
        obj1;
        JVM INSTR monitorenter ;
        mBitmapsCache.put(s, obj);
        obj1;
        JVM INSTR monitorexit ;
        if(true) goto _L5; else goto _L6
        s;
        throw s;
_L6:
        obj1 = mWeakRefBitmapsCache;
        obj1;
        JVM INSTR monitorenter ;
        mWeakRefBitmapsCache.remove(s);
        s = ((String) (obj1));
        if(true) goto _L8; else goto _L7
_L7:
        s;
        throw s;
    }

    private BitmapInfo loadBitmap(String s)
    {
        Object obj;
        obj = null;
        boolean flag = true;
        Object obj1 = new android.graphics.BitmapFactory.Options();
        obj1.inScaled = true;
        obj1.inTargetDensity = mTargetDensity;
        boolean flag1 = flag;
        if(mExtraResourceFolder != null)
        {
            Log.i("ResourceManager", (new StringBuilder()).append("try to load resource from extra resource: ").append(mExtraResourceFolder).append(" of ").append(s).toString());
            obj1.inDensity = mExtraResourceDensity;
            Object obj2;
            WeakReference weakreference;
            if(TextUtils.isEmpty(mExtraResourceFolder))
                obj = s;
            else
                obj = (new StringBuilder()).append(mExtraResourceFolder).append("/").append(s).toString();
            obj2 = mResourceLoader.getBitmapInfo(((String) (obj)), ((android.graphics.BitmapFactory.Options) (obj1)));
            obj = obj2;
            flag1 = flag;
            if(obj2 != null)
            {
                flag1 = false;
                obj = obj2;
            }
        }
        obj2 = obj;
        if(obj == null)
        {
            obj1.inDensity = mDefaultResourceDensity;
            obj2 = mResourceLoader.getBitmapInfo(s, ((android.graphics.BitmapFactory.Options) (obj1)));
        }
        if(obj2 == null) goto _L2; else goto _L1
_L1:
        if(!flag1)
            Log.i("ResourceManager", (new StringBuilder()).append("load image from extra resource: ").append(mExtraResourceFolder).append(" of ").append(s).toString());
        obj2.mKey = s;
        obj2.mWeakRefCache = mWeakRefBitmapsCache;
        ((BitmapInfo) (obj2)).mBitmap.setDensity(mTargetDensity);
        obj2.mLastVisitTime = System.currentTimeMillis();
        obj = mBitmapsCache;
        obj;
        JVM INSTR monitorenter ;
        mBitmapsCache.put(s, obj2);
        obj;
        JVM INSTR monitorexit ;
        obj = mWeakRefBitmapsCache;
        obj;
        JVM INSTR monitorenter ;
        obj1 = mWeakRefBitmapsCache;
        weakreference = JVM INSTR new #88  <Class WeakReference>;
        weakreference.WeakReference(obj2);
        ((HashMap) (obj1)).put(s, weakreference);
        obj;
        JVM INSTR monitorexit ;
_L4:
        return ((BitmapInfo) (obj2));
        s;
        throw s;
        s;
        throw s;
_L2:
        Log.e("ResourceManager", (new StringBuilder()).append("fail to load image: ").append(s).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int retranslateDensity(int i)
    {
        if(i > 240 && i <= 360)
            return (int)((double)(i - 240) * 0.66666666666666663D) + 240;
        if(i > 360 && i <= 540)
            return (int)((double)(i - 360) * 0.88888888888888884D) + 320;
        if(i > 540 && i <= 720)
            return (int)((double)(i - 540) * 0.88888888888888884D) + 480;
        else
            return i;
    }

    public static int translateDensity(int i)
    {
        if(i > 240 && i <= 320)
            return (int)((double)(i - 240) * 1.5D) + 240;
        if(i > 320 && i <= 480)
            return (int)((double)(i - 320) * 1.125D) + 360;
        if(i > 480 && i <= 640)
            return (int)((double)(i - 480) * 1.125D) + 540;
        else
            return i;
    }

    public void clear()
    {
        LruCache lrucache = mBitmapsCache;
        lrucache;
        JVM INSTR monitorenter ;
        mBitmapsCache.evictAll();
        lrucache;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void clear(String s)
    {
        LruCache lrucache = mBitmapsCache;
        lrucache;
        JVM INSTR monitorenter ;
        mBitmapsCache.remove(s);
        lrucache;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void finish(boolean flag)
    {
        if(flag) goto _L2; else goto _L1
_L1:
        Object obj = mBitmapsCache;
        obj;
        JVM INSTR monitorenter ;
        mBitmapsCache.evictAll();
        obj;
        JVM INSTR monitorexit ;
        Object obj1 = mWeakRefBitmapsCache;
        obj1;
        JVM INSTR monitorenter ;
        mWeakRefBitmapsCache.clear();
        obj1;
        JVM INSTR monitorexit ;
_L2:
        obj = mLoadingBitmaps;
        obj;
        JVM INSTR monitorenter ;
        mLoadingBitmaps.clear();
        obj;
        JVM INSTR monitorexit ;
        mResourceLoader.finish();
        return;
        obj1;
        throw obj1;
        obj;
        throw obj;
        Exception exception;
        exception;
        throw exception;
    }

    public Bitmap getBitmap(String s)
    {
        Object obj = null;
        BitmapInfo bitmapinfo = getBitmapInfo(s);
        s = obj;
        if(bitmapinfo != null)
            s = bitmapinfo.mBitmap;
        return s;
    }

    public BitmapInfo getBitmapInfo(String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        BitmapInfo bitmapinfo = getCache(s);
        if(bitmapinfo != null)
        {
            return bitmapinfo;
        } else
        {
            Log.i("ResourceManager", (new StringBuilder()).append("load image ").append(s).toString());
            return loadBitmap(s);
        }
    }

    public BitmapInfo getBitmapInfoAsync(String s, AsyncLoadListener asyncloadlistener)
    {
        if(TextUtils.isEmpty(s))
            return null;
        BitmapInfo bitmapinfo = getCache(s);
        if(bitmapinfo != null)
            return bitmapinfo;
        HashSet hashset = mLoadingBitmaps;
        hashset;
        JVM INSTR monitorenter ;
        BitmapInfo bitmapinfo1;
        if(mLoadingBitmaps.contains(s))
            break MISSING_BLOCK_LABEL_121;
        bitmapinfo1 = getCache(s);
        if(bitmapinfo1 == null)
            break MISSING_BLOCK_LABEL_56;
        hashset;
        JVM INSTR monitorexit ;
        return bitmapinfo1;
        mLoadingBitmaps.add(s);
        Object obj = JVM INSTR new #128 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.i("ResourceManager", ((StringBuilder) (obj)).append("load image async: ").append(s).toString());
        obj = JVM INSTR new #14  <Class ResourceManager$LoadBitmapAsyncTask>;
        ((LoadBitmapAsyncTask) (obj)).this. LoadBitmapAsyncTask(asyncloadlistener);
        ((LoadBitmapAsyncTask) (obj)).execute(new String[] {
            s
        });
        hashset;
        JVM INSTR monitorexit ;
        s = new BitmapInfo();
        s.mLoading = true;
        return s;
        s;
        throw s;
    }

    public Element getConfigRoot()
    {
        return mResourceLoader.getConfigRoot();
    }

    public Drawable getDrawable(Resources resources, String s)
    {
        BitmapInfo bitmapinfo = getBitmapInfo(s);
        if(bitmapinfo == null || bitmapinfo.mBitmap == null)
            return null;
        Bitmap bitmap = bitmapinfo.mBitmap;
        if(bitmapinfo.mNinePatch != null)
        {
            resources = new NinePatchDrawable(resources, bitmap, bitmap.getNinePatchChunk(), bitmapinfo.mPadding, s);
            resources.setTargetDensity(mTargetDensity);
            return resources;
        } else
        {
            resources = new BitmapDrawable(resources, bitmap);
            resources.setTargetDensity(mTargetDensity);
            return resources;
        }
    }

    public MemoryFile getFile(String s)
    {
        return mResourceLoader.getFile(s);
    }

    public final InputStream getInputStream(String s)
    {
        return mResourceLoader.getInputStream(s);
    }

    public final InputStream getInputStream(String s, long al[])
    {
        return mResourceLoader.getInputStream(s, al);
    }

    public Element getManifestRoot()
    {
        return mResourceLoader.getManifestRoot();
    }

    public NinePatch getNinePatch(String s)
    {
        Object obj = null;
        BitmapInfo bitmapinfo = getBitmapInfo(s);
        s = obj;
        if(bitmapinfo != null)
            s = bitmapinfo.mNinePatch;
        return s;
    }

    public String getPathForLanguage(String s)
    {
        return mResourceLoader.getPathForLanguage(s);
    }

    public void init()
    {
        mResourceLoader.init();
    }

    public void pause()
    {
    }

    public final boolean resourceExists(String s)
    {
        return mResourceLoader.resourceExists(s);
    }

    public void resume()
    {
    }

    public void setCacheSize(int i)
    {
        LruCache lrucache = mBitmapsCache;
        lrucache;
        JVM INSTR monitorenter ;
        mBitmapsCache.resize(i);
        lrucache;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setDefaultResourceDensity(int i)
    {
        mDefaultResourceDensity = i;
    }

    public void setExtraResource(String s)
    {
        mExtraResourceFolder = s;
    }

    public void setExtraResource(String s, int i)
    {
        mExtraResourceFolder = s;
        mExtraResourceDensity = i;
    }

    public void setExtraResourceDensity(int i)
    {
        mExtraResourceDensity = i;
    }

    public void setLocal(Locale locale)
    {
        if(locale == null)
            return;
        if(!locale.equals(mResourceLoader.getLocale()))
        {
            mResourceLoader.setLocal(locale);
            finish(false);
        }
    }

    public void setTargetDensity(int i)
    {
        mTargetDensity = i;
    }

    private static final int DEF_CACHE_SIZE = 0x10000000;
    private static final int DENSITY_HIGH_R = 240;
    private static final int DENSITY_XHIGH_R = 360;
    private static final int DENSITY_XXHIGH_R = 540;
    private static final int DENSITY_XXXHIGH = 640;
    private static final int DENSITY_XXXHIGH_R = 720;
    private static final String LOG_TAG = "ResourceManager";
    protected final LruCache mBitmapsCache = new LruCache(0x10000000) {

        protected volatile int sizeOf(Object obj, Object obj1)
        {
            return sizeOf((String)obj, (BitmapInfo)obj1);
        }

        protected int sizeOf(String s, BitmapInfo bitmapinfo)
        {
            if(bitmapinfo == null || bitmapinfo.mBitmap == null)
                return 0;
            else
                return bitmapinfo.mBitmap.getAllocationByteCount();
        }

        final ResourceManager this$0;

            
            {
                this$0 = ResourceManager.this;
                super(i);
            }
    }
;
    private int mDefaultResourceDensity;
    private int mExtraResourceDensity;
    private String mExtraResourceFolder;
    private final HashSet mLoadingBitmaps = new HashSet();
    private final ResourceLoader mResourceLoader;
    private int mTargetDensity;
    protected final HashMap mWeakRefBitmapsCache = new HashMap();
}
