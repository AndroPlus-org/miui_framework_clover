// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.net.URI;
import miui.maml.*;
import miui.maml.data.Expression;
import miui.maml.data.IndexedVariable;
import miui.maml.util.Utils;
import miui.maml.util.net.IOUtils;

// Referenced classes of package miui.maml.elements:
//            VirtualScreen

public abstract class BitmapProvider
{
    private static class AppIconProvider extends BitmapProvider
    {

        public VersionedBitmap getBitmap(String s, boolean flag, int i, int j)
        {
            if(mVersionedBitmap.getBitmap() != null || !(mNoIcon ^ true)) goto _L2; else goto _L1
_L1:
            Object obj;
            PackageManager packagemanager = mRoot.getContext().mContext.getPackageManager();
            obj = JVM INSTR new #57  <Class ComponentName>;
            ((ComponentName) (obj)).ComponentName(mPkg, mCls);
            obj = packagemanager.getActivityIcon(((ComponentName) (obj)));
            if(!(obj instanceof BitmapDrawable)) goto _L4; else goto _L3
_L3:
            mVersionedBitmap.setBitmap(((BitmapDrawable)obj).getBitmap());
_L2:
            return mVersionedBitmap;
_L4:
            try
            {
                mNoIcon = true;
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                Log.e("BitmapProvider", (new StringBuilder()).append("fail to get icon for src of ApplicationIcon type: ").append(s).toString());
                mNoIcon = true;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public void init(String s)
        {
            init(s);
            mNoIcon = false;
            if(!TextUtils.isEmpty(s))
            {
                String as[] = s.split(",");
                if(as.length == 2)
                {
                    mPkg = as[0];
                    mCls = as[1];
                } else
                {
                    Log.e("BitmapProvider", (new StringBuilder()).append("invalid src of ApplicationIcon type: ").append(s).toString());
                    mNoIcon = true;
                }
            } else
            {
                Log.e("BitmapProvider", (new StringBuilder()).append("invalid src of ApplicationIcon type: ").append(s).toString());
                mNoIcon = true;
            }
        }

        public static final String TAG_NAME = "ApplicationIcon";
        private String mCls;
        private boolean mNoIcon;
        private String mPkg;

        public AppIconProvider(ScreenElementRoot screenelementroot)
        {
            super(screenelementroot);
        }
    }

    public static class BitmapHolderProvider extends BitmapProvider
    {

        public VersionedBitmap getBitmap(String s, boolean flag, int i, int j)
        {
            s = null;
            if(mBitmapHolder != null)
                s = mBitmapHolder.getBitmap(mId);
            return s;
        }

        public void init(String s)
        {
            init(s);
            if(TextUtils.isEmpty(s))
                return;
            int i = s.indexOf('.');
            if(i != -1)
            {
                String s1 = s.substring(0, i);
                mId = s.substring(i + 1);
                s = s1;
            }
            s = mRoot.findElement(s);
            if(s instanceof IBitmapHolder)
                mBitmapHolder = (IBitmapHolder)s;
        }

        public static final String TAG_NAME = "BitmapHolder";
        private IBitmapHolder mBitmapHolder;
        private String mId;

        public BitmapHolderProvider(ScreenElementRoot screenelementroot)
        {
            super(screenelementroot);
        }
    }

    public static class BitmapVariableProvider extends BitmapProvider
    {

        public VersionedBitmap getBitmap(String s, boolean flag, int i, int j)
        {
            Object obj;
            Object obj1;
            if(!Utils.equals(mCurSrc, s))
            {
                mVar = null;
                mIndexExpression = null;
                if(!TextUtils.isEmpty(s))
                {
                    j = s.indexOf('[');
                    i = s.length();
                    if(j != -1 && j < i - 1 && s.charAt(i - 1) == ']')
                        mIndexExpression = Expression.build(mRoot.getVariables(), s.substring(j + 1, i - 1));
                    if(mIndexExpression == null)
                        obj = s;
                    else
                        obj = s.substring(0, j);
                    mVar = new IndexedVariable(((String) (obj)), mRoot.getVariables(), false);
                }
                mCurSrc = s;
            }
            obj1 = null;
            obj = obj1;
            if(mVar == null) goto _L2; else goto _L1
_L1:
            if(mIndexExpression == null) goto _L4; else goto _L3
_L3:
            obj = (Bitmap)mVar.getArr((int)mIndexExpression.evaluate());
_L2:
            mVersionedBitmap.setBitmap(((Bitmap) (obj)));
            return mVersionedBitmap;
_L4:
            try
            {
                obj = (Bitmap)mVar.get();
            }
            catch(ClassCastException classcastexception)
            {
                Log.w("BitmapProvider", (new StringBuilder()).append("fail to cast as Bitmap from object: ").append(s).toString());
                classcastexception = obj1;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public void init(String s)
        {
            init(s);
            if(TextUtils.isEmpty(s))
            {
                return;
            } else
            {
                mVar = new IndexedVariable(s, mRoot.getVariables(), false);
                mCurSrc = s;
                return;
            }
        }

        public static final String TAG_NAME = "BitmapVar";
        private String mCurSrc;
        private Expression mIndexExpression;
        private IndexedVariable mVar;

        public BitmapVariableProvider(ScreenElementRoot screenelementroot)
        {
            super(screenelementroot);
        }
    }

    private static class FileSystemProvider extends UriProvider
    {

        public VersionedBitmap getBitmap(String s, boolean flag, int i, int j)
        {
            if(TextUtils.isEmpty(s))
            {
                mVersionedBitmap.setBitmap(null);
                return mVersionedBitmap;
            }
            s = (new File(s)).toURI();
            if(s == null)
            {
                mVersionedBitmap.setBitmap(null);
                return mVersionedBitmap;
            } else
            {
                return super.getBitmap(s.toString(), flag, i, j);
            }
        }

        public static final String TAG_NAME = "FileSystem";

        public FileSystemProvider(ScreenElementRoot screenelementroot)
        {
            super(screenelementroot);
        }
    }

    public static interface IBitmapHolder
    {

        public abstract VersionedBitmap getBitmap(String s);
    }

    private static class ResourceImageProvider extends BitmapProvider
    {

        static String _2D_get0(ResourceImageProvider resourceimageprovider)
        {
            return resourceimageprovider.mCachedBitmapName;
        }

        static String _2D_set0(ResourceImageProvider resourceimageprovider, String s)
        {
            resourceimageprovider.mCachedBitmapName = s;
            return s;
        }

        public void finish()
        {
            finish();
            Object obj = mSrcNameLock;
            obj;
            JVM INSTR monitorenter ;
            mLoadingBitmapName = null;
            mCachedBitmapName = null;
            mVersionedBitmap.reset();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public VersionedBitmap getBitmap(String s, boolean flag, int i, int j)
        {
            Object obj;
            Object obj1;
            Object obj2;
            obj = null;
            obj1 = null;
            obj2 = mVersionedBitmap.getBitmap();
            if((obj2 == null || !((Bitmap) (obj2)).isRecycled()) && !(TextUtils.equals(mCachedBitmapName, s) ^ true)) goto _L2; else goto _L1
_L1:
            if(!flag) goto _L4; else goto _L3
_L3:
            obj = mRoot.getContext().mResourceManager.getBitmapInfo(s);
            obj2 = mVersionedBitmap;
            if(obj == null)
                obj = obj1;
            else
                obj = ((miui.maml.ResourceManager.BitmapInfo) (obj)).mBitmap;
            ((VersionedBitmap) (obj2)).setBitmap(((Bitmap) (obj)));
            mCachedBitmapName = s;
_L2:
            return mVersionedBitmap;
_L4:
            miui.maml.ResourceManager.BitmapInfo bitmapinfo = mRoot.getContext().mResourceManager.getBitmapInfoAsync(s, mAsyncLoadListener);
            obj1 = mSrcNameLock;
            obj1;
            JVM INSTR monitorenter ;
            if(bitmapinfo == null)
                break MISSING_BLOCK_LABEL_148;
            if(!(bitmapinfo.mLoading ^ true))
                break MISSING_BLOCK_LABEL_193;
            obj2 = mVersionedBitmap;
            if(bitmapinfo != null) goto _L6; else goto _L5
_L5:
            ((VersionedBitmap) (obj2)).setBitmap(((Bitmap) (obj)));
            mCachedBitmapName = s;
            mLoadingBitmapName = null;
_L7:
            obj1;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
_L6:
            obj = bitmapinfo.mBitmap;
              goto _L5
            mLoadingBitmapName = s;
              goto _L7
            s;
            throw s;
            if(true) goto _L2; else goto _L8
_L8:
        }

        public static final String TAG_NAME = "ResourceImage";
        private miui.maml.ResourceManager.AsyncLoadListener mAsyncLoadListener;
        private String mCachedBitmapName;
        String mLoadingBitmapName;
        Object mSrcNameLock;

        public ResourceImageProvider(ScreenElementRoot screenelementroot)
        {
            super(screenelementroot);
            mSrcNameLock = new Object();
            mAsyncLoadListener = new _cls1();
        }
    }

    private static class UriProvider extends BitmapProvider
    {

        static String _2D_get0(UriProvider uriprovider)
        {
            return uriprovider.mCurLoadingBitmapUri;
        }

        static Object _2D_get1(UriProvider uriprovider)
        {
            return uriprovider.mLock;
        }

        static String _2D_set0(UriProvider uriprovider, String s)
        {
            uriprovider.mCachedBitmapUri = s;
            return s;
        }

        static String _2D_set1(UriProvider uriprovider, String s)
        {
            uriprovider.mCurLoadingBitmapUri = s;
            return s;
        }

        public void finish()
        {
            finish();
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mCachedBitmapUri = null;
            mCurLoadingBitmapUri = null;
            mVersionedBitmap.reset();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public VersionedBitmap getBitmap(String s, boolean flag, int i, int j)
        {
            Bitmap bitmap;
            if(TextUtils.isEmpty(s))
            {
                mVersionedBitmap.setBitmap(null);
                return mVersionedBitmap;
            }
            bitmap = mVersionedBitmap.getBitmap();
            if((bitmap == null || !bitmap.isRecycled()) && !(TextUtils.equals(mCachedBitmapUri, s) ^ true)) goto _L2; else goto _L1
_L1:
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(!TextUtils.equals(mCurLoadingBitmapUri, s) && !TextUtils.equals(mCachedBitmapUri, s))
            {
                mCurLoadingBitmapUri = s;
                LoaderAsyncTask loaderasynctask = JVM INSTR new #7   <Class BitmapProvider$UriProvider$LoaderAsyncTask>;
                loaderasynctask.this. LoaderAsyncTask(s, i, j);
                loaderasynctask.execute(new Object[0]);
            }
            obj;
            JVM INSTR monitorexit ;
_L2:
            mVersionedBitmap.setBitmap(bitmap);
            return mVersionedBitmap;
            s;
            throw s;
        }

        public static final String TAG_NAME = "Uri";
        private String mCachedBitmapUri;
        private String mCurLoadingBitmapUri;
        private Object mLock;

        public UriProvider(ScreenElementRoot screenelementroot)
        {
            super(screenelementroot);
            mLock = new Object();
        }
    }

    private class UriProvider.LoaderAsyncTask extends AsyncTask
    {

        protected transient Bitmap doInBackground(Object aobj[])
        {
            Bitmap bitmap;
            bitmap = getBitmapFromUri(Uri.parse(mUri), mWidth, mHeight);
            if(bitmap == null)
                Log.w("BitmapProvider", (new StringBuilder()).append("fail to decode bitmap: ").append(mUri).toString());
            aobj = ((Object []) (UriProvider._2D_get1(UriProvider.this)));
            aobj;
            JVM INSTR monitorenter ;
            if(TextUtils.equals(mUri, UriProvider._2D_get0(UriProvider.this)))
            {
                mVersionedBitmap.setBitmap(bitmap);
                UriProvider._2D_set0(UriProvider.this, UriProvider._2D_get0(UriProvider.this));
                mRoot.requestUpdate();
                UriProvider._2D_set1(UriProvider.this, null);
            }
            aobj;
            JVM INSTR monitorexit ;
            return bitmap;
            Exception exception;
            exception;
            throw exception;
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground(aobj);
        }

        private int mHeight;
        private String mUri;
        private int mWidth;
        final UriProvider this$1;

        public UriProvider.LoaderAsyncTask(String s, int i, int j)
        {
            this$1 = UriProvider.this;
            super();
            mUri = null;
            mWidth = -1;
            mHeight = -1;
            mUri = s;
            mWidth = i;
            mHeight = j;
        }
    }

    public static class VersionedBitmap
    {

        public static boolean equals(VersionedBitmap versionedbitmap, VersionedBitmap versionedbitmap1)
        {
            boolean flag;
            boolean flag1;
            flag = false;
            flag1 = flag;
            if(versionedbitmap == null) goto _L2; else goto _L1
_L1:
            if(versionedbitmap1 != null) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(versionedbitmap.mBitmap == versionedbitmap1.mBitmap)
            {
                flag1 = flag;
                if(versionedbitmap.mVersion == versionedbitmap1.mVersion)
                    flag1 = true;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public Bitmap getBitmap()
        {
            return mBitmap;
        }

        public void reset()
        {
            mBitmap = null;
            mVersion = 0;
        }

        public void set(VersionedBitmap versionedbitmap)
        {
            if(versionedbitmap != null)
            {
                mBitmap = versionedbitmap.mBitmap;
                mVersion = versionedbitmap.mVersion;
            } else
            {
                reset();
            }
        }

        public boolean setBitmap(Bitmap bitmap)
        {
            if(bitmap != mBitmap)
            {
                mBitmap = bitmap;
                mVersion = mVersion + 1;
            }
            boolean flag;
            if(bitmap != mBitmap)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public int updateVersion()
        {
            int i = mVersion;
            mVersion = i + 1;
            return i;
        }

        private Bitmap mBitmap;
        private int mVersion;

        public VersionedBitmap(Bitmap bitmap)
        {
            mBitmap = bitmap;
        }
    }

    private static class VirtualScreenProvider extends BitmapProvider
    {

        public VersionedBitmap getBitmap(String s, boolean flag, int i, int j)
        {
            s = null;
            VersionedBitmap versionedbitmap = mVersionedBitmap;
            if(mVirtualScreen != null)
                s = mVirtualScreen.getBitmap();
            versionedbitmap.setBitmap(s);
            return mVersionedBitmap;
        }

        public void init(String s)
        {
            init(s);
            s = mRoot.findElement(s);
            if(s instanceof VirtualScreen)
                mVirtualScreen = (VirtualScreen)s;
        }

        public static final String TAG_NAME = "VirtualScreen";
        private VirtualScreen mVirtualScreen;

        public VirtualScreenProvider(ScreenElementRoot screenelementroot)
        {
            super(screenelementroot);
        }
    }


    public BitmapProvider(ScreenElementRoot screenelementroot)
    {
        mVersionedBitmap = new VersionedBitmap(null);
        mRoot = screenelementroot;
    }

    private static int computeSampleSize(android.graphics.BitmapFactory.Options options, int i)
    {
        boolean flag = true;
        double d = Math.sqrt(((double)options.outHeight * (double)options.outWidth) / (double)i);
        for(i = ((flag) ? 1 : 0); (double)(i * 2) <= d; i *= 2);
        return i;
    }

    public static BitmapProvider create(ScreenElementRoot screenelementroot, String s)
    {
        if(TextUtils.equals(s, "ResourceImage"))
            return new ResourceImageProvider(screenelementroot);
        if(TextUtils.equals(s, "VirtualScreen"))
            return new VirtualScreenProvider(screenelementroot);
        if(TextUtils.equals(s, "ApplicationIcon"))
            return new AppIconProvider(screenelementroot);
        if(TextUtils.equals(s, "FileSystem"))
            return new FileSystemProvider(screenelementroot);
        if(TextUtils.equals(s, "Uri"))
            return new UriProvider(screenelementroot);
        if(TextUtils.equals(s, "BitmapHolder"))
            return new BitmapHolderProvider(screenelementroot);
        if(TextUtils.equals(s, "BitmapVar"))
            return new BitmapVariableProvider(screenelementroot);
        miui.maml.ObjectFactory.BitmapProviderFactory bitmapproviderfactory = (miui.maml.ObjectFactory.BitmapProviderFactory)screenelementroot.getContext().getObjectFactory("BitmapProvider");
        if(bitmapproviderfactory != null)
        {
            s = bitmapproviderfactory.create(screenelementroot, s);
            if(s != null)
                return s;
        }
        return new ResourceImageProvider(screenelementroot);
    }

    public void finish()
    {
        mVersionedBitmap.reset();
    }

    public VersionedBitmap getBitmap(String s, boolean flag, int i, int j)
    {
        return mVersionedBitmap;
    }

    protected Bitmap getBitmapFromUri(Uri uri, int i, int j)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Uri uri1;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        uri1 = obj2;
        Object obj6 = mRoot.getContext().mContext.getContentResolver().openInputStream(uri);
        if(i <= 0 || j <= 0)
            break MISSING_BLOCK_LABEL_230;
        obj1 = obj6;
        obj = obj6;
        uri1 = obj2;
        android.graphics.BitmapFactory.Options options = JVM INSTR new #61  <Class android.graphics.BitmapFactory$Options>;
        obj1 = obj6;
        obj = obj6;
        uri1 = obj2;
        options.android.graphics.BitmapFactory.Options();
        uri1 = obj3;
        obj = obj4;
        options.inJustDecodeBounds = true;
        uri1 = obj3;
        obj = obj4;
        BitmapFactory.decodeStream(((java.io.InputStream) (obj6)), null, options);
        uri1 = obj3;
        obj = obj4;
        options.inSampleSize = computeSampleSize(options, i * j);
        uri1 = obj3;
        obj = obj4;
        options.inJustDecodeBounds = false;
        uri1 = obj3;
        obj = obj4;
        options.outHeight = j;
        uri1 = obj3;
        obj = obj4;
        options.outWidth = i;
        uri1 = obj3;
        obj = obj4;
        uri = mRoot.getContext().mContext.getContentResolver().openInputStream(uri);
        uri1 = uri;
        obj = uri;
        obj1 = BitmapFactory.decodeStream(uri, null, options);
        IOUtils.closeQuietly(((java.io.InputStream) (obj6)));
        IOUtils.closeQuietly(uri);
        return ((Bitmap) (obj1));
        obj1 = obj6;
        obj = obj6;
        uri1 = obj2;
        uri = BitmapFactory.decodeStream(((java.io.InputStream) (obj6)), null, null);
        IOUtils.closeQuietly(((java.io.InputStream) (obj6)));
        IOUtils.closeQuietly(null);
        return uri;
        obj;
        uri = obj5;
        obj6 = obj1;
        obj1 = obj;
_L4:
        obj = obj6;
        uri1 = uri;
        Log.d("BitmapProvider", "getBitmapFromUri Exception", ((Throwable) (obj1)));
        IOUtils.closeQuietly(((java.io.InputStream) (obj6)));
        IOUtils.closeQuietly(uri);
        return null;
        uri;
        obj6 = obj;
_L2:
        IOUtils.closeQuietly(((java.io.InputStream) (obj6)));
        IOUtils.closeQuietly(uri1);
        throw uri;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
        obj1;
        uri = ((Uri) (obj));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void init(String s)
    {
        reset();
    }

    public void reset()
    {
    }

    private static final String LOG_TAG = "BitmapProvider";
    protected ScreenElementRoot mRoot;
    protected VersionedBitmap mVersionedBitmap;

    // Unreferenced inner class miui/maml/elements/BitmapProvider$ResourceImageProvider$1

/* anonymous class */
    class ResourceImageProvider._cls1
        implements miui.maml.ResourceManager.AsyncLoadListener
    {

        public void onLoadComplete(String s, miui.maml.ResourceManager.BitmapInfo bitmapinfo)
        {
            Object obj = null;
            Object obj1 = mSrcNameLock;
            obj1;
            JVM INSTR monitorenter ;
            if(!TextUtils.equals(s, mLoadingBitmapName)) goto _L2; else goto _L1
_L1:
            Object obj2;
            obj2 = JVM INSTR new #39  <Class StringBuilder>;
            ((StringBuilder) (obj2)).StringBuilder();
            Log.i("BitmapProvider", ((StringBuilder) (obj2)).append("load image async complete: ").append(s).append(" last cached ").append(ResourceImageProvider._2D_get0(ResourceImageProvider.this)).toString());
            obj2 = mVersionedBitmap;
            if(bitmapinfo != null) goto _L4; else goto _L3
_L3:
            bitmapinfo = obj;
_L8:
            ((VersionedBitmap) (obj2)).setBitmap(bitmapinfo);
            ResourceImageProvider._2D_set0(ResourceImageProvider.this, s);
            mLoadingBitmapName = null;
_L6:
            obj1;
            JVM INSTR monitorexit ;
            mRoot.requestUpdate();
            return;
_L4:
            bitmapinfo = bitmapinfo.mBitmap;
            continue; /* Loop/switch isn't completed */
_L2:
            bitmapinfo = JVM INSTR new #39  <Class StringBuilder>;
            bitmapinfo.StringBuilder();
            Log.i("BitmapProvider", bitmapinfo.append("load image async complete: ").append(s).append(" not equals ").append(mLoadingBitmapName).toString());
            if(true) goto _L6; else goto _L5
_L5:
            s;
            throw s;
            if(true) goto _L8; else goto _L7
_L7:
        }

        final ResourceImageProvider this$1;

            
            {
                this$1 = ResourceImageProvider.this;
                super();
            }
    }

}
