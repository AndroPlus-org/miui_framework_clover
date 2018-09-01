// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.IWindowSession;
import android.view.WindowManagerGlobal;
import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import libcore.io.IoUtils;

// Referenced classes of package android.app:
//            IWallpaperManager, WallpaperColors, WallpaperInfo, IWallpaperManagerCallback

public class WallpaperManager
{
    static class FastBitmapDrawable extends Drawable
    {

        public void draw(Canvas canvas)
        {
            canvas.drawBitmap(mBitmap, mDrawLeft, mDrawTop, mPaint);
        }

        public int getIntrinsicHeight()
        {
            return mHeight;
        }

        public int getIntrinsicWidth()
        {
            return mWidth;
        }

        public int getMinimumHeight()
        {
            return mHeight;
        }

        public int getMinimumWidth()
        {
            return mWidth;
        }

        public int getOpacity()
        {
            return -1;
        }

        public void setAlpha(int i)
        {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        public void setBounds(int i, int j, int k, int l)
        {
            mDrawLeft = (k - i - mWidth) / 2 + i;
            mDrawTop = (l - j - mHeight) / 2 + j;
        }

        public void setColorFilter(ColorFilter colorfilter)
        {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        public void setDither(boolean flag)
        {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        public void setFilterBitmap(boolean flag)
        {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        private final Bitmap mBitmap;
        private int mDrawLeft;
        private int mDrawTop;
        private final int mHeight;
        private final Paint mPaint;
        private final int mWidth;

        private FastBitmapDrawable(Bitmap bitmap)
        {
            mBitmap = bitmap;
            mWidth = bitmap.getWidth();
            mHeight = bitmap.getHeight();
            setBounds(0, 0, mWidth, mHeight);
            mPaint = new Paint();
            mPaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
        }

        FastBitmapDrawable(Bitmap bitmap, FastBitmapDrawable fastbitmapdrawable)
        {
            this(bitmap);
        }
    }

    private static class Globals extends IWallpaperManagerCallback.Stub
    {

        static IWallpaperManager _2D_get0(Globals globals)
        {
            return globals.mService;
        }

        private Bitmap getCurrentWallpaperLocked(Context context, int i)
        {
            if(mService == null)
            {
                Log.w(WallpaperManager._2D_get0(), "WallpaperService not running");
                return null;
            }
            Object obj;
            Object obj1;
            try
            {
                Bundle bundle = JVM INSTR new #78  <Class Bundle>;
                bundle.Bundle();
                context = mService.getWallpaper(context.getOpPackageName(), this, 1, bundle, i);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw context.rethrowFromSystemServer();
            }
            if(context == null)
                break MISSING_BLOCK_LABEL_87;
            obj = JVM INSTR new #92  <Class android.graphics.BitmapFactory$Options>;
            ((android.graphics.BitmapFactory.Options) (obj)).android.graphics.BitmapFactory.Options();
            obj = BitmapFactory.decodeFileDescriptor(context.getFileDescriptor(), null, ((android.graphics.BitmapFactory.Options) (obj)));
            IoUtils.closeQuietly(context);
            return ((Bitmap) (obj));
            obj1;
            Log.w(WallpaperManager._2D_get0(), "Can't decode file", ((Throwable) (obj1)));
            IoUtils.closeQuietly(context);
            return null;
            obj1;
            IoUtils.closeQuietly(context);
            throw obj1;
        }

        private Bitmap getDefaultWallpaper(Context context, int i)
        {
            context = WallpaperManager.openDefaultWallpaper(context, i);
            if(context == null)
                break MISSING_BLOCK_LABEL_46;
            Object obj;
            obj = JVM INSTR new #92  <Class android.graphics.BitmapFactory$Options>;
            ((android.graphics.BitmapFactory.Options) (obj)).android.graphics.BitmapFactory.Options();
            obj = BitmapFactory.decodeStream(context, null, ((android.graphics.BitmapFactory.Options) (obj)));
            IoUtils.closeQuietly(context);
            return ((Bitmap) (obj));
            Object obj1;
            obj1;
            Log.w(WallpaperManager._2D_get0(), "Can't decode stream", ((Throwable) (obj1)));
            IoUtils.closeQuietly(context);
            return null;
            obj1;
            IoUtils.closeQuietly(context);
            throw obj1;
        }

        static boolean lambda$_2D_android_app_WallpaperManager$Globals_12368(OnColorsChangedListener oncolorschangedlistener, Pair pair)
        {
            boolean flag;
            if(pair.first == oncolorschangedlistener)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void addOnColorsChangedListener(OnColorsChangedListener oncolorschangedlistener, Handler handler, int i)
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mColorCallbackRegistered;
            if(flag)
                break MISSING_BLOCK_LABEL_29;
            mService.registerWallpaperColorsCallback(this, i);
            mColorCallbackRegistered = true;
_L1:
            ArrayList arraylist = mColorListeners;
            Pair pair = JVM INSTR new #135 <Class Pair>;
            pair.Pair(oncolorschangedlistener, handler);
            arraylist.add(pair);
            this;
            JVM INSTR monitorexit ;
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.w(WallpaperManager._2D_get0(), "Can't register for color updates", remoteexception);
              goto _L1
            oncolorschangedlistener;
            throw oncolorschangedlistener;
        }

        void forgetLoadedWallpaper()
        {
            this;
            JVM INSTR monitorenter ;
            mCachedWallpaper = null;
            mCachedWallpaperUserId = 0;
            mDefaultWallpaper = null;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        WallpaperColors getWallpaperColors(int i, int j)
        {
            if(i != 2 && i != 1)
                throw new IllegalArgumentException("Must request colors for exactly one kind of wallpaper");
            WallpaperColors wallpapercolors;
            try
            {
                wallpapercolors = mService.getWallpaperColors(i, j);
            }
            catch(RemoteException remoteexception)
            {
                return null;
            }
            return wallpapercolors;
        }

        void lambda$_2D_android_app_WallpaperManager$Globals_13333(Pair pair, WallpaperColors wallpapercolors, int i, int j)
        {
            Globals globals = WallpaperManager._2D_get1();
            globals;
            JVM INSTR monitorenter ;
            boolean flag = mColorListeners.contains(pair);
            globals;
            JVM INSTR monitorexit ;
            if(flag)
                ((OnColorsChangedListener)pair.first).onColorsChanged(wallpapercolors, i, j);
            return;
            pair;
            throw pair;
        }

        public void onWallpaperChanged()
        {
            forgetLoadedWallpaper();
        }

        public void onWallpaperColorsChanged(WallpaperColors wallpapercolors, int i, int j)
        {
            this;
            JVM INSTR monitorenter ;
            Handler handler;
            _.Lambda.zUW_hE_1K7BzT3PNwqZSM6y8x_4._cls1 _lcls1;
            for(Iterator iterator = mColorListeners.iterator(); iterator.hasNext(); handler.post(_lcls1))
            {
                Pair pair = (Pair)iterator.next();
                handler = (Handler)pair.second;
                if(pair.second == null)
                    handler = mMainLooperHandler;
                _lcls1 = JVM INSTR new #211 <Class _$Lambda$zUW_hE_1K7BzT3PNwqZSM6y8x_4$1>;
                _lcls1._.Lambda.zUW_hE_1K7BzT3PNwqZSM6y8x_4._cls1(i, j, this, pair, wallpapercolors);
            }

            break MISSING_BLOCK_LABEL_91;
            wallpapercolors;
            throw wallpapercolors;
            this;
            JVM INSTR monitorexit ;
        }

        public Bitmap peekWallpaperBitmap(Context context, boolean flag, int i)
        {
            return peekWallpaperBitmap(context, flag, i, context.getUserId());
        }

        public Bitmap peekWallpaperBitmap(Context context, boolean flag, int i, int j)
        {
            if(mService != null)
            {
                boolean flag1;
                try
                {
                    flag1 = mService.isWallpaperSupported(context.getOpPackageName());
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    throw context.rethrowFromSystemServer();
                }
                if(!flag1)
                    return null;
            }
            this;
            JVM INSTR monitorenter ;
            if(mCachedWallpaper == null || mCachedWallpaperUserId != j)
                break MISSING_BLOCK_LABEL_62;
            context = mCachedWallpaper;
            this;
            JVM INSTR monitorexit ;
            return context;
            mCachedWallpaper = null;
            mCachedWallpaperUserId = 0;
            mCachedWallpaper = getCurrentWallpaperLocked(context, j);
            mCachedWallpaperUserId = j;
_L1:
            if(mCachedWallpaper == null)
                break MISSING_BLOCK_LABEL_180;
            context = mCachedWallpaper;
            this;
            JVM INSTR monitorexit ;
            return context;
            SecurityException securityexception;
            securityexception;
            if(context.getApplicationInfo().targetSdkVersion > 26)
                break MISSING_BLOCK_LABEL_136;
            Log.w(WallpaperManager._2D_get0(), "No permission to access wallpaper, suppressing exception to avoid crashing legacy app.");
              goto _L1
            context;
            throw context;
            throw securityexception;
            OutOfMemoryError outofmemoryerror;
            outofmemoryerror;
            String s = WallpaperManager._2D_get0();
            StringBuilder stringbuilder = JVM INSTR new #248 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.w(s, stringbuilder.append("Out of memory loading the current wallpaper: ").append(outofmemoryerror).toString());
              goto _L1
            this;
            JVM INSTR monitorexit ;
            Bitmap bitmap;
            Bitmap bitmap1;
            if(!flag)
                break MISSING_BLOCK_LABEL_227;
            bitmap1 = mDefaultWallpaper;
            bitmap = bitmap1;
            if(bitmap1 != null) goto _L3; else goto _L2
_L2:
            bitmap = getDefaultWallpaper(context, i);
            this;
            JVM INSTR monitorenter ;
            mDefaultWallpaper = bitmap;
            this;
            JVM INSTR monitorexit ;
_L3:
            return bitmap;
            context;
            throw context;
            return null;
        }

        public void removeOnColorsChangedListener(OnColorsChangedListener oncolorschangedlistener, int i)
        {
            this;
            JVM INSTR monitorenter ;
            ArrayList arraylist = mColorListeners;
            _.Lambda.zUW_hE_1K7BzT3PNwqZSM6y8x_4 zuw_he_1k7bzt3pnwqzsm6y8x_4 = JVM INSTR new #267 <Class _$Lambda$zUW_hE_1K7BzT3PNwqZSM6y8x_4>;
            zuw_he_1k7bzt3pnwqzsm6y8x_4._.Lambda.zUW_hE_1K7BzT3PNwqZSM6y8x_4(oncolorschangedlistener);
            arraylist.removeIf(zuw_he_1k7bzt3pnwqzsm6y8x_4);
            if(mColorListeners.size() != 0 || !mColorCallbackRegistered)
                break MISSING_BLOCK_LABEL_58;
            mColorCallbackRegistered = false;
            mService.unregisterWallpaperColorsCallback(this, i);
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            oncolorschangedlistener;
            Log.w(WallpaperManager._2D_get0(), "Can't unregister color updates", oncolorschangedlistener);
              goto _L1
            oncolorschangedlistener;
            throw oncolorschangedlistener;
        }

        private Bitmap mCachedWallpaper;
        private int mCachedWallpaperUserId;
        private boolean mColorCallbackRegistered;
        private final ArrayList mColorListeners = new ArrayList();
        private Bitmap mDefaultWallpaper;
        private Handler mMainLooperHandler;
        private final IWallpaperManager mService = IWallpaperManager.Stub.asInterface(ServiceManager.getService("wallpaper"));

        Globals(Looper looper)
        {
            mMainLooperHandler = new Handler(looper);
            forgetLoadedWallpaper();
        }
    }

    public static interface OnColorsChangedListener
    {

        public abstract void onColorsChanged(WallpaperColors wallpapercolors, int i);

        public void onColorsChanged(WallpaperColors wallpapercolors, int i, int j)
        {
            onColorsChanged(wallpapercolors, i);
        }
    }

    private class WallpaperSetCompletion extends IWallpaperManagerCallback.Stub
    {

        public void onWallpaperChanged()
            throws RemoteException
        {
            mLatch.countDown();
        }

        public void onWallpaperColorsChanged(WallpaperColors wallpapercolors, int i, int j)
            throws RemoteException
        {
            WallpaperManager._2D_get1().onWallpaperColorsChanged(wallpapercolors, i, j);
        }

        public void waitForCompletion()
        {
            mLatch.await(30L, TimeUnit.SECONDS);
_L2:
            return;
            InterruptedException interruptedexception;
            interruptedexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        final CountDownLatch mLatch = new CountDownLatch(1);
        final WallpaperManager this$0;

        public WallpaperSetCompletion()
        {
            this$0 = WallpaperManager.this;
            super();
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static Globals _2D_get1()
    {
        return sGlobals;
    }

    WallpaperManager(Context context, Handler handler)
    {
        mWallpaperXStep = -1F;
        mWallpaperYStep = -1F;
        mContext = context;
        initGlobals(context.getMainLooper());
    }

    private void copyStreamToWallpaperFile(InputStream inputstream, FileOutputStream fileoutputstream)
        throws IOException
    {
        byte abyte0[] = new byte[32768];
        do
        {
            int i = inputstream.read(abyte0);
            if(i > 0)
                fileoutputstream.write(abyte0, 0, i);
            else
                return;
        } while(true);
    }

    public static ComponentName getDefaultWallpaperComponent(Context context)
    {
        Object obj = SystemProperties.get("ro.config.wallpaper_component");
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = ComponentName.unflattenFromString(((String) (obj)));
            if(obj != null)
                return ((ComponentName) (obj));
        }
        context = context.getString(0x10401a2);
        if(!TextUtils.isEmpty(context))
        {
            context = ComponentName.unflattenFromString(context);
            if(context != null)
                return context;
        }
        return null;
    }

    public static WallpaperManager getInstance(Context context)
    {
        return (WallpaperManager)context.getSystemService("wallpaper");
    }

    private static RectF getMaxCropRect(int i, int j, int k, int l, float f, float f1)
    {
        RectF rectf = new RectF();
        if((float)i / (float)j > (float)k / (float)l)
        {
            rectf.top = 0.0F;
            rectf.bottom = j;
            f1 = (float)k * ((float)j / (float)l);
            rectf.left = ((float)i - f1) * f;
            rectf.right = rectf.left + f1;
        } else
        {
            rectf.left = 0.0F;
            rectf.right = i;
            f = (float)l * ((float)i / (float)k);
            rectf.top = ((float)j - f) * f1;
            rectf.bottom = rectf.top + f;
        }
        return rectf;
    }

    static void initGlobals(Looper looper)
    {
        Object obj = sSync;
        obj;
        JVM INSTR monitorenter ;
        if(sGlobals == null)
        {
            Globals globals = JVM INSTR new #9   <Class WallpaperManager$Globals>;
            globals.Globals(looper);
            sGlobals = globals;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        looper;
        throw looper;
    }

    public static InputStream openDefaultWallpaper(Context context, int i)
    {
        Object obj;
        if(i == 2)
            return null;
        obj = SystemProperties.get("ro.config.wallpaper");
        if(TextUtils.isEmpty(((CharSequence) (obj))))
            break MISSING_BLOCK_LABEL_48;
        obj = new File(((String) (obj)));
        if(!((File) (obj)).exists())
            break MISSING_BLOCK_LABEL_48;
        obj = new FileInputStream(((File) (obj)));
        return ((InputStream) (obj));
        IOException ioexception;
        ioexception;
        try
        {
            context = context.getResources().openRawResource(0x1080265);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        return context;
    }

    private final void validateRect(Rect rect)
    {
        if(rect != null && rect.isEmpty())
            throw new IllegalArgumentException("visibleCrop rectangle must be valid and non-empty");
        else
            return;
    }

    public void addOnColorsChangedListener(OnColorsChangedListener oncolorschangedlistener, Handler handler)
    {
        addOnColorsChangedListener(oncolorschangedlistener, handler, mContext.getUserId());
    }

    public void addOnColorsChangedListener(OnColorsChangedListener oncolorschangedlistener, Handler handler, int i)
    {
        sGlobals.addOnColorsChangedListener(oncolorschangedlistener, handler, i);
    }

    public void clear()
        throws IOException
    {
        setStream(openDefaultWallpaper(mContext, 1), null, false);
    }

    public void clear(int i)
        throws IOException
    {
        if((i & 1) != 0)
            clear();
        if((i & 2) != 0)
            clearWallpaper(2, mContext.getUserId());
    }

    public void clearWallpaper()
    {
        clearWallpaper(2, mContext.getUserId());
        clearWallpaper(1, mContext.getUserId());
    }

    public void clearWallpaper(int i, int j)
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        try
        {
            Globals._2D_get0(sGlobals).clearWallpaper(mContext.getOpPackageName(), i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void clearWallpaperOffsets(IBinder ibinder)
    {
        try
        {
            WindowManagerGlobal.getWindowSession().setWallpaperPosition(ibinder, -1F, -1F, -1F, -1F);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public void forgetLoadedWallpaper()
    {
        sGlobals.forgetLoadedWallpaper();
    }

    public Bitmap getBitmap()
    {
        return getBitmapAsUser(mContext.getUserId());
    }

    public Bitmap getBitmapAsUser(int i)
    {
        return sGlobals.peekWallpaperBitmap(mContext, true, 1, i);
    }

    public Drawable getBuiltInDrawable()
    {
        return getBuiltInDrawable(0, 0, false, 0.0F, 0.0F, 1);
    }

    public Drawable getBuiltInDrawable(int i)
    {
        return getBuiltInDrawable(0, 0, false, 0.0F, 0.0F, i);
    }

    public Drawable getBuiltInDrawable(int i, int j, boolean flag, float f, float f1)
    {
        return getBuiltInDrawable(i, j, flag, f, f1, 1);
    }

    public Drawable getBuiltInDrawable(int i, int j, boolean flag, float f, float f1, int k)
    {
        Resources resources;
        Object obj;
        Object obj1;
        int i1;
        Rect rect;
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        if(k != 1 && k != 2)
            throw new IllegalArgumentException("Must request exactly one kind of wallpaper");
        resources = mContext.getResources();
        f = Math.max(0.0F, Math.min(1.0F, f));
        f1 = Math.max(0.0F, Math.min(1.0F, f1));
        obj = openDefaultWallpaper(mContext, k);
        if(obj == null)
        {
            if(DEBUG)
                Log.w(TAG, (new StringBuilder()).append("default wallpaper stream ").append(k).append(" is null").toString());
            return null;
        }
        obj1 = new BufferedInputStream(((InputStream) (obj)));
        if(i <= 0 || j <= 0)
            return new BitmapDrawable(resources, BitmapFactory.decodeStream(((InputStream) (obj1)), null, null));
        obj = new android.graphics.BitmapFactory.Options();
        obj.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(((InputStream) (obj1)), null, ((android.graphics.BitmapFactory.Options) (obj)));
        if(((android.graphics.BitmapFactory.Options) (obj)).outWidth != 0 && ((android.graphics.BitmapFactory.Options) (obj)).outHeight != 0)
        {
            int l = ((android.graphics.BitmapFactory.Options) (obj)).outWidth;
            int j1 = ((android.graphics.BitmapFactory.Options) (obj)).outHeight;
            obj1 = new BufferedInputStream(openDefaultWallpaper(mContext, k));
            i = Math.min(l, i);
            j = Math.min(j1, j);
            if(flag)
            {
                obj = getMaxCropRect(l, j1, i, j, f, f1);
            } else
            {
                f = (float)(l - i) * f;
                float f2 = i;
                f1 = (float)(j1 - j) * f1;
                obj = new RectF(f, f1, f + f2, f1 + (float)j);
            }
            rect = new Rect();
            ((RectF) (obj)).roundOut(rect);
            if(rect.width() <= 0 || rect.height() <= 0)
            {
                Log.w(TAG, "crop has bad values for full size image");
                return null;
            }
        } else
        {
            Log.e(TAG, "default wallpaper dimensions are 0");
            return null;
        }
        i1 = Math.min(rect.width() / i, rect.height() / j);
        obj = null;
        obj1 = BitmapRegionDecoder.newInstance(((InputStream) (obj1)), true);
        obj = obj1;
_L2:
        Object obj2 = null;
        if(obj != null)
        {
            obj2 = new android.graphics.BitmapFactory.Options();
            if(i1 > 1)
                obj2.inSampleSize = i1;
            obj2 = ((BitmapRegionDecoder) (obj)).decodeRegion(rect, ((android.graphics.BitmapFactory.Options) (obj2)));
            ((BitmapRegionDecoder) (obj)).recycle();
        }
        obj = obj2;
        if(obj2 == null)
        {
            Object obj5 = new BufferedInputStream(openDefaultWallpaper(mContext, k));
            obj = new android.graphics.BitmapFactory.Options();
            if(i1 > 1)
                obj.inSampleSize = i1;
            obj5 = BitmapFactory.decodeStream(((InputStream) (obj5)), null, ((android.graphics.BitmapFactory.Options) (obj)));
            obj = obj2;
            if(obj5 != null)
                obj = Bitmap.createBitmap(((Bitmap) (obj5)), rect.left, rect.top, rect.width(), rect.height());
        }
        if(obj == null)
        {
            Log.w(TAG, "cannot decode default wallpaper");
            return null;
        }
        break; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        Log.w(TAG, "cannot open region decoder for default wallpaper");
        if(true) goto _L2; else goto _L1
_L1:
        Object obj3;
label0:
        {
            obj3 = obj;
            if(i <= 0)
                break label0;
            obj3 = obj;
            if(j <= 0)
                break label0;
            if(((Bitmap) (obj)).getWidth() == i)
            {
                obj3 = obj;
                if(((Bitmap) (obj)).getHeight() == j)
                    break label0;
            }
            Matrix matrix = new Matrix();
            obj3 = new RectF(0.0F, 0.0F, ((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight());
            Object obj4 = new RectF(0.0F, 0.0F, i, j);
            matrix.setRectToRect(((RectF) (obj3)), ((RectF) (obj4)), android.graphics.Matrix.ScaleToFit.FILL);
            obj4 = Bitmap.createBitmap((int)((RectF) (obj4)).width(), (int)((RectF) (obj4)).height(), android.graphics.Bitmap.Config.ARGB_8888);
            obj3 = obj;
            if(obj4 != null)
            {
                Canvas canvas = new Canvas(((Bitmap) (obj4)));
                obj3 = new Paint();
                ((Paint) (obj3)).setFilterBitmap(true);
                canvas.drawBitmap(((Bitmap) (obj)), matrix, ((Paint) (obj3)));
                obj3 = obj4;
            }
        }
        return new BitmapDrawable(resources, ((Bitmap) (obj3)));
    }

    public Intent getCropAndSetWallpaperIntent(Uri uri)
    {
        if(uri == null)
            throw new IllegalArgumentException("Image URI must not be null");
        if(!"content".equals(uri.getScheme()))
            throw new IllegalArgumentException("Image URI must be of the content scheme type");
        PackageManager packagemanager = mContext.getPackageManager();
        Intent intent = new Intent("android.service.wallpaper.CROP_AND_SET_WALLPAPER", uri);
        intent.addFlags(1);
        uri = packagemanager.resolveActivity((new Intent("android.intent.action.MAIN")).addCategory("android.intent.category.HOME"), 0x10000);
        if(uri != null)
        {
            intent.setPackage(((ResolveInfo) (uri)).activityInfo.packageName);
            if(packagemanager.queryIntentActivities(intent, 0).size() > 0)
                return intent;
        }
        intent.setPackage(mContext.getString(0x104015f));
        if(packagemanager.queryIntentActivities(intent, 0).size() > 0)
            return intent;
        else
            throw new IllegalArgumentException("Cannot use passed URI to set wallpaper; check that the type returned by ContentProvider matches image/*");
    }

    public int getDesiredMinimumHeight()
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        int i;
        try
        {
            i = Globals._2D_get0(sGlobals).getHeightHint();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getDesiredMinimumWidth()
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        int i;
        try
        {
            i = Globals._2D_get0(sGlobals).getWidthHint();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public Drawable getDrawable()
    {
        Object obj = sGlobals.peekWallpaperBitmap(mContext, true, 1);
        if(obj != null)
        {
            obj = new BitmapDrawable(mContext.getResources(), ((Bitmap) (obj)));
            ((Drawable) (obj)).setDither(false);
            return ((Drawable) (obj));
        } else
        {
            return null;
        }
    }

    public Drawable getFastDrawable()
    {
        Bitmap bitmap = sGlobals.peekWallpaperBitmap(mContext, true, 1);
        if(bitmap != null)
            return new FastBitmapDrawable(bitmap, null);
        else
            return null;
    }

    public IWallpaperManager getIWallpaperManager()
    {
        return Globals._2D_get0(sGlobals);
    }

    public WallpaperColors getWallpaperColors(int i)
    {
        return getWallpaperColors(i, mContext.getUserId());
    }

    public WallpaperColors getWallpaperColors(int i, int j)
    {
        return sGlobals.getWallpaperColors(i, j);
    }

    public ParcelFileDescriptor getWallpaperFile(int i)
    {
        return getWallpaperFile(i, mContext.getUserId());
    }

    public ParcelFileDescriptor getWallpaperFile(int i, int j)
    {
        if(i != 1 && i != 2)
            throw new IllegalArgumentException("Must request exactly one kind of wallpaper");
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        Object obj;
        try
        {
            obj = JVM INSTR new #582 <Class Bundle>;
            ((Bundle) (obj)).Bundle();
            obj = Globals._2D_get0(sGlobals).getWallpaper(mContext.getOpPackageName(), null, i, ((Bundle) (obj)), j);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        catch(SecurityException securityexception)
        {
            if(mContext.getApplicationInfo().targetSdkVersion <= 26)
            {
                Log.w(TAG, "No permission to access wallpaper, suppressing exception to avoid crashing legacy app.");
                return null;
            } else
            {
                throw securityexception;
            }
        }
        return ((ParcelFileDescriptor) (obj));
    }

    public int getWallpaperId(int i)
    {
        return getWallpaperIdForUser(i, mContext.getUserId());
    }

    public int getWallpaperIdForUser(int i, int j)
    {
        try
        {
            if(Globals._2D_get0(sGlobals) == null)
            {
                Log.w(TAG, "WallpaperService not running");
                RuntimeException runtimeexception = JVM INSTR new #258 <Class RuntimeException>;
                DeadSystemException deadsystemexception = JVM INSTR new #260 <Class DeadSystemException>;
                deadsystemexception.DeadSystemException();
                runtimeexception.RuntimeException(deadsystemexception);
                throw runtimeexception;
            }
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        i = Globals._2D_get0(sGlobals).getWallpaperIdForUser(i, j);
        return i;
    }

    public WallpaperInfo getWallpaperInfo()
    {
        try
        {
            if(Globals._2D_get0(sGlobals) == null)
            {
                Log.w(TAG, "WallpaperService not running");
                RuntimeException runtimeexception = JVM INSTR new #258 <Class RuntimeException>;
                DeadSystemException deadsystemexception = JVM INSTR new #260 <Class DeadSystemException>;
                deadsystemexception.DeadSystemException();
                runtimeexception.RuntimeException(deadsystemexception);
                throw runtimeexception;
            }
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        WallpaperInfo wallpaperinfo = Globals._2D_get0(sGlobals).getWallpaperInfo(UserHandle.myUserId());
        return wallpaperinfo;
    }

    public boolean hasResourceWallpaper(int i)
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        boolean flag;
        try
        {
            Resources resources = mContext.getResources();
            Object obj = JVM INSTR new #323 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            obj = ((StringBuilder) (obj)).append("res:").append(resources.getResourceName(i)).toString();
            flag = Globals._2D_get0(sGlobals).hasNamedWallpaper(((String) (obj)));
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isSetWallpaperAllowed()
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        boolean flag;
        try
        {
            flag = Globals._2D_get0(sGlobals).isSetWallpaperAllowed(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isWallpaperBackupEligible(int i)
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        boolean flag;
        try
        {
            flag = Globals._2D_get0(sGlobals).isWallpaperBackupEligible(i, mContext.getUserId());
        }
        catch(RemoteException remoteexception)
        {
            Log.e(TAG, (new StringBuilder()).append("Exception querying wallpaper backup eligibility: ").append(remoteexception.getMessage()).toString());
            return false;
        }
        return flag;
    }

    public boolean isWallpaperSupported()
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        boolean flag;
        try
        {
            flag = Globals._2D_get0(sGlobals).isWallpaperSupported(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public Drawable peekDrawable()
    {
        Object obj = sGlobals.peekWallpaperBitmap(mContext, false, 1);
        if(obj != null)
        {
            obj = new BitmapDrawable(mContext.getResources(), ((Bitmap) (obj)));
            ((Drawable) (obj)).setDither(false);
            return ((Drawable) (obj));
        } else
        {
            return null;
        }
    }

    public Drawable peekFastDrawable()
    {
        Bitmap bitmap = sGlobals.peekWallpaperBitmap(mContext, false, 1);
        if(bitmap != null)
            return new FastBitmapDrawable(bitmap, null);
        else
            return null;
    }

    public void removeOnColorsChangedListener(OnColorsChangedListener oncolorschangedlistener)
    {
        removeOnColorsChangedListener(oncolorschangedlistener, mContext.getUserId());
    }

    public void removeOnColorsChangedListener(OnColorsChangedListener oncolorschangedlistener, int i)
    {
        sGlobals.removeOnColorsChangedListener(oncolorschangedlistener, i);
    }

    public void sendWallpaperCommand(IBinder ibinder, String s, int i, int j, int k, Bundle bundle)
    {
        try
        {
            WindowManagerGlobal.getWindowSession().sendWallpaperCommand(ibinder, s, i, j, k, bundle, false);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public int setBitmap(Bitmap bitmap, Rect rect, boolean flag)
        throws IOException
    {
        return setBitmap(bitmap, rect, flag, 3);
    }

    public int setBitmap(Bitmap bitmap, Rect rect, boolean flag, int i)
        throws IOException
    {
        return setBitmap(bitmap, rect, flag, i, UserHandle.myUserId());
    }

    public int setBitmap(Bitmap bitmap, Rect rect, boolean flag, int i, int j)
        throws IOException
    {
        android.os.ParcelFileDescriptor.AutoCloseOutputStream autocloseoutputstream;
        validateRect(rect);
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        Bundle bundle = new Bundle();
        WallpaperSetCompletion wallpapersetcompletion = new WallpaperSetCompletion();
        ParcelFileDescriptor parcelfiledescriptor;
        try
        {
            parcelfiledescriptor = Globals._2D_get0(sGlobals).setWallpaper(null, mContext.getOpPackageName(), rect, flag, bundle, i, wallpapersetcompletion, j);
        }
        // Misplaced declaration of an exception variable
        catch(Bitmap bitmap)
        {
            throw bitmap.rethrowFromSystemServer();
        }
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_134;
        rect = null;
        autocloseoutputstream = JVM INSTR new #672 <Class android.os.ParcelFileDescriptor$AutoCloseOutputStream>;
        autocloseoutputstream.android.os.ParcelFileDescriptor.AutoCloseOutputStream(parcelfiledescriptor);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 90, autocloseoutputstream);
        autocloseoutputstream.close();
        wallpapersetcompletion.waitForCompletion();
        IoUtils.closeQuietly(autocloseoutputstream);
        return bundle.getInt("android.service.wallpaper.extra.ID", 0);
        bitmap;
_L2:
        IoUtils.closeQuietly(rect);
        throw bitmap;
        bitmap;
        rect = autocloseoutputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setBitmap(Bitmap bitmap)
        throws IOException
    {
        setBitmap(bitmap, null, true);
    }

    public void setDisplayOffset(IBinder ibinder, int i, int j)
    {
        try
        {
            WindowManagerGlobal.getWindowSession().setWallpaperDisplayOffset(ibinder, i, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public void setDisplayPadding(Rect rect)
    {
        try
        {
            if(Globals._2D_get0(sGlobals) == null)
            {
                Log.w(TAG, "WallpaperService not running");
                rect = JVM INSTR new #258 <Class RuntimeException>;
                DeadSystemException deadsystemexception = JVM INSTR new #260 <Class DeadSystemException>;
                deadsystemexception.DeadSystemException();
                rect.RuntimeException(deadsystemexception);
                throw rect;
            }
        }
        // Misplaced declaration of an exception variable
        catch(Rect rect)
        {
            throw rect.rethrowFromSystemServer();
        }
        Globals._2D_get0(sGlobals).setDisplayPadding(rect, mContext.getOpPackageName());
        return;
    }

    public boolean setLockWallpaperCallback(IWallpaperManagerCallback iwallpapermanagercallback)
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        boolean flag;
        try
        {
            flag = Globals._2D_get0(sGlobals).setLockWallpaperCallback(iwallpapermanagercallback);
        }
        // Misplaced declaration of an exception variable
        catch(IWallpaperManagerCallback iwallpapermanagercallback)
        {
            throw iwallpapermanagercallback.rethrowFromSystemServer();
        }
        return flag;
    }

    public int setResource(int i, int j)
        throws IOException
    {
        Exception exception;
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        Bundle bundle = new Bundle();
        WallpaperSetCompletion wallpapersetcompletion = new WallpaperSetCompletion();
        Resources resources;
        android.os.ParcelFileDescriptor.AutoCloseOutputStream autocloseoutputstream;
        Object obj;
        ParcelFileDescriptor parcelfiledescriptor;
        try
        {
            resources = mContext.getResources();
            IWallpaperManager iwallpapermanager = Globals._2D_get0(sGlobals);
            StringBuilder stringbuilder = JVM INSTR new #323 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            parcelfiledescriptor = iwallpapermanager.setWallpaper(stringbuilder.append("res:").append(resources.getResourceName(i)).toString(), mContext.getOpPackageName(), null, false, bundle, j, wallpapersetcompletion, UserHandle.myUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_170;
        obj = null;
        autocloseoutputstream = JVM INSTR new #672 <Class android.os.ParcelFileDescriptor$AutoCloseOutputStream>;
        autocloseoutputstream.android.os.ParcelFileDescriptor.AutoCloseOutputStream(parcelfiledescriptor);
        copyStreamToWallpaperFile(resources.openRawResource(i), autocloseoutputstream);
        autocloseoutputstream.close();
        wallpapersetcompletion.waitForCompletion();
        IoUtils.closeQuietly(autocloseoutputstream);
        return bundle.getInt("android.service.wallpaper.extra.ID", 0);
        exception;
        autocloseoutputstream = obj;
_L2:
        IoUtils.closeQuietly(autocloseoutputstream);
        throw exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setResource(int i)
        throws IOException
    {
        setResource(i, 3);
    }

    public int setStream(InputStream inputstream, Rect rect, boolean flag)
        throws IOException
    {
        return setStream(inputstream, rect, flag, 3);
    }

    public int setStream(InputStream inputstream, Rect rect, boolean flag, int i)
        throws IOException
    {
        android.os.ParcelFileDescriptor.AutoCloseOutputStream autocloseoutputstream;
        validateRect(rect);
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        Bundle bundle = new Bundle();
        WallpaperSetCompletion wallpapersetcompletion = new WallpaperSetCompletion();
        Object obj;
        try
        {
            rect = Globals._2D_get0(sGlobals).setWallpaper(null, mContext.getOpPackageName(), rect, flag, bundle, i, wallpapersetcompletion, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(InputStream inputstream)
        {
            throw inputstream.rethrowFromSystemServer();
        }
        if(rect == null)
            break MISSING_BLOCK_LABEL_128;
        obj = null;
        autocloseoutputstream = JVM INSTR new #672 <Class android.os.ParcelFileDescriptor$AutoCloseOutputStream>;
        autocloseoutputstream.android.os.ParcelFileDescriptor.AutoCloseOutputStream(rect);
        copyStreamToWallpaperFile(inputstream, autocloseoutputstream);
        autocloseoutputstream.close();
        wallpapersetcompletion.waitForCompletion();
        IoUtils.closeQuietly(autocloseoutputstream);
        return bundle.getInt("android.service.wallpaper.extra.ID", 0);
        rect;
        inputstream = obj;
_L2:
        IoUtils.closeQuietly(inputstream);
        throw rect;
        rect;
        inputstream = autocloseoutputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setStream(InputStream inputstream)
        throws IOException
    {
        setStream(inputstream, null, true);
    }

    public boolean setWallpaperComponent(ComponentName componentname)
    {
        return setWallpaperComponent(componentname, UserHandle.myUserId());
    }

    public boolean setWallpaperComponent(ComponentName componentname, int i)
    {
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
        try
        {
            Globals._2D_get0(sGlobals).setWallpaperComponentChecked(componentname, mContext.getOpPackageName(), i);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return true;
    }

    public void setWallpaperOffsetSteps(float f, float f1)
    {
        mWallpaperXStep = f;
        mWallpaperYStep = f1;
    }

    public void setWallpaperOffsets(IBinder ibinder, float f, float f1)
    {
        try
        {
            WindowManagerGlobal.getWindowSession().setWallpaperPosition(ibinder, f, f1, mWallpaperXStep, mWallpaperYStep);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public void suggestDesiredDimensions(int i, int j)
    {
        int l;
        int i1;
label0:
        {
            int k;
            float f;
            RuntimeException runtimeexception;
            DeadSystemException deadsystemexception;
            try
            {
                k = SystemProperties.getInt("sys.max_texture_size", 0);
            }
            catch(Exception exception)
            {
                k = 0;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            l = i;
            i1 = j;
            if(k <= 0)
                break label0;
            if(i <= k)
            {
                l = i;
                i1 = j;
                if(j <= k)
                    break label0;
            }
            f = (float)j / (float)i;
            if(i > j)
            {
                l = k;
                i1 = (int)((double)((float)k * f) + 0.5D);
            } else
            {
                i1 = k;
                l = (int)((double)((float)k / f) + 0.5D);
            }
        }
        if(Globals._2D_get0(sGlobals) == null)
        {
            Log.w(TAG, "WallpaperService not running");
            runtimeexception = JVM INSTR new #258 <Class RuntimeException>;
            deadsystemexception = JVM INSTR new #260 <Class DeadSystemException>;
            deadsystemexception.DeadSystemException();
            runtimeexception.RuntimeException(deadsystemexception);
            throw runtimeexception;
        }
        Globals._2D_get0(sGlobals).setDimensionHints(l, i1, mContext.getOpPackageName());
        return;
    }

    public static final String ACTION_CHANGE_LIVE_WALLPAPER = "android.service.wallpaper.CHANGE_LIVE_WALLPAPER";
    public static final String ACTION_CROP_AND_SET_WALLPAPER = "android.service.wallpaper.CROP_AND_SET_WALLPAPER";
    public static final String ACTION_LIVE_WALLPAPER_CHOOSER = "android.service.wallpaper.LIVE_WALLPAPER_CHOOSER";
    public static final String COMMAND_DROP = "android.home.drop";
    public static final String COMMAND_SECONDARY_TAP = "android.wallpaper.secondaryTap";
    public static final String COMMAND_TAP = "android.wallpaper.tap";
    private static boolean DEBUG = false;
    public static final String EXTRA_LIVE_WALLPAPER_COMPONENT = "android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT";
    public static final String EXTRA_NEW_WALLPAPER_ID = "android.service.wallpaper.extra.ID";
    public static final int FLAG_LOCK = 2;
    public static final int FLAG_SYSTEM = 1;
    private static final String PROP_LOCK_WALLPAPER = "ro.config.lock_wallpaper";
    private static final String PROP_WALLPAPER = "ro.config.wallpaper";
    private static final String PROP_WALLPAPER_COMPONENT = "ro.config.wallpaper_component";
    private static String TAG = "WallpaperManager";
    public static final String WALLPAPER_PREVIEW_META_DATA = "android.wallpaper.preview";
    private static Globals sGlobals;
    private static final Object sSync = new Object[0];
    private final Context mContext;
    private float mWallpaperXStep;
    private float mWallpaperYStep;

    static 
    {
        DEBUG = false;
    }
}
