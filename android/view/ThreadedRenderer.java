// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.*;
import android.util.Log;
import com.android.internal.util.VirtualRefBasePtr;
import java.io.*;
import java.util.List;

// Referenced classes of package android.view:
//            RenderNode, View, Surface, DisplayListCanvas, 
//            FrameMetricsObserver, HardwareLayer, ViewRootImpl, Choreographer, 
//            FrameInfo, Display, IGraphicsStats, IGraphicsStatsCallback

public final class ThreadedRenderer
{
    static interface DrawCallbacks
    {

        public abstract void onPostDraw(DisplayListCanvas displaylistcanvas);

        public abstract void onPreDraw(DisplayListCanvas displaylistcanvas);
    }

    private static class ProcessInitializer
    {

        static void _2D_wrap0(ProcessInitializer processinitializer)
        {
            processinitializer.rotateBuffer();
        }

        private void initGraphicsStats()
        {
            android.os.IBinder ibinder = ServiceManager.getService("graphicsstats");
            if(ibinder == null)
                return;
            mGraphicsStatsService = IGraphicsStats.Stub.asInterface(ibinder);
            requestBuffer();
_L1:
            return;
            Throwable throwable;
            throwable;
            Log.w("ThreadedRenderer", "Could not acquire gfx stats buffer", throwable);
              goto _L1
        }

        private void initSched(long l)
        {
            int i = ThreadedRenderer._2D_wrap0(l);
            ActivityManager.getService().setRenderThread(i);
_L1:
            return;
            Throwable throwable;
            throwable;
            Log.w("ThreadedRenderer", "Failed to set scheduler for RenderThread", throwable);
              goto _L1
        }

        private void requestBuffer()
        {
            Object obj = mAppContext.getApplicationInfo().packageName;
            obj = mGraphicsStatsService.requestBufferForProcess(((String) (obj)), mGraphicsStatsCallback);
            ThreadedRenderer._2D_wrap2(((ParcelFileDescriptor) (obj)).getFd());
            ((ParcelFileDescriptor) (obj)).close();
_L1:
            return;
            Throwable throwable;
            throwable;
            Log.w("ThreadedRenderer", "Could not acquire gfx stats buffer", throwable);
              goto _L1
        }

        private void rotateBuffer()
        {
            ThreadedRenderer._2D_wrap1();
            requestBuffer();
        }

        void init(Context context, long l)
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mInitialized;
            if(!flag)
                break MISSING_BLOCK_LABEL_16;
            this;
            JVM INSTR monitorexit ;
            return;
            mInitialized = true;
            mAppContext = context.getApplicationContext();
            initSched(l);
            initGraphicsStats();
            this;
            JVM INSTR monitorexit ;
            return;
            context;
            throw context;
        }

        static ProcessInitializer sInstance = new ProcessInitializer();
        private Context mAppContext;
        private IGraphicsStatsCallback mGraphicsStatsCallback;
        private IGraphicsStats mGraphicsStatsService;
        private boolean mInitialized;


        private ProcessInitializer()
        {
            mInitialized = false;
            mGraphicsStatsCallback = new _cls1();
        }
    }


    static int _2D_wrap0(long l)
    {
        return nGetRenderThreadTid(l);
    }

    static void _2D_wrap1()
    {
        nRotateProcessStatsBuffer();
    }

    static void _2D_wrap2(int i)
    {
        nSetProcessStatsBuffer(i);
    }

    ThreadedRenderer(Context context, boolean flag, String s)
    {
        mInitialized = false;
        mRequested = true;
        mIsOpaque = false;
        TypedArray typedarray = context.obtainStyledAttributes(null, com.android.internal.R.styleable.Lighting, 0, 0);
        mLightY = typedarray.getDimension(3, 0.0F);
        mLightZ = typedarray.getDimension(4, 0.0F);
        mLightRadius = typedarray.getDimension(2, 0.0F);
        mAmbientShadowAlpha = (int)(typedarray.getFloat(0, 0.0F) * 255F + 0.5F);
        mSpotShadowAlpha = (int)(typedarray.getFloat(1, 0.0F) * 255F + 0.5F);
        typedarray.recycle();
        long l = nCreateRootRenderNode();
        mRootNode = RenderNode.adopt(l);
        mRootNode.setClipToBounds(false);
        mIsOpaque = flag ^ true;
        mNativeProxy = nCreateProxy(flag, l);
        nSetName(mNativeProxy, s);
        ProcessInitializer.sInstance.init(context, mNativeProxy);
        loadSystemProperties();
    }

    public static int copySurfaceInto(Surface surface, Rect rect, Bitmap bitmap)
    {
        if(rect == null)
            return nCopySurfaceInto(surface, 0, 0, 0, 0, bitmap);
        else
            return nCopySurfaceInto(surface, rect.left, rect.top, rect.right, rect.bottom, bitmap);
    }

    public static ThreadedRenderer create(Context context, boolean flag, String s)
    {
        ThreadedRenderer threadedrenderer = null;
        if(isAvailable())
            threadedrenderer = new ThreadedRenderer(context, flag, s);
        return threadedrenderer;
    }

    public static Bitmap createHardwareBitmap(RenderNode rendernode, int i, int j)
    {
        return nCreateHardwareBitmap(rendernode.getNativeDisplayList(), i, j);
    }

    private static void destroyResources(View view)
    {
        view.destroyHardwareResources();
    }

    public static void disable(boolean flag)
    {
        sRendererDisabled = true;
        if(flag)
            sSystemRendererDisabled = true;
    }

    public static native void disableVsync();

    public static void enableForegroundTrimming()
    {
        sTrimForeground = true;
    }

    static void invokeFunctor(long l, boolean flag)
    {
        nInvokeFunctor(l, flag);
    }

    public static boolean isAvailable()
    {
        boolean flag = true;
        if(sSupportsOpenGL != null)
            return sSupportsOpenGL.booleanValue();
        if(SystemProperties.getInt("persist.sys.force_sw_gles", 0) == 1)
        {
            sSupportsOpenGL = Boolean.valueOf(false);
            return false;
        }
        if(SystemProperties.getInt("ro.kernel.qemu", 0) == 0)
        {
            sSupportsOpenGL = Boolean.valueOf(true);
            return true;
        }
        int i = SystemProperties.getInt("qemu.gles", -1);
        if(i == -1)
            return false;
        if(i <= 0)
            flag = false;
        sSupportsOpenGL = Boolean.valueOf(flag);
        return sSupportsOpenGL.booleanValue();
    }

    private static native long nAddFrameMetricsObserver(long l, FrameMetricsObserver framemetricsobserver);

    private static native void nAddRenderNode(long l, long l1, boolean flag);

    private static native void nBuildLayer(long l, long l1);

    private static native void nCancelLayerUpdate(long l, long l1);

    private static native boolean nCopyLayerInto(long l, long l1, Bitmap bitmap);

    private static native int nCopySurfaceInto(Surface surface, int i, int j, int k, int l, Bitmap bitmap);

    private static native Bitmap nCreateHardwareBitmap(long l, int i, int j);

    private static native long nCreateProxy(boolean flag, long l);

    private static native long nCreateRootRenderNode();

    private static native long nCreateTextureLayer(long l);

    private static native void nDeleteProxy(long l);

    private static native void nDestroy(long l, long l1);

    private static native void nDestroyHardwareResources(long l);

    private static native void nDetachSurfaceTexture(long l, long l1);

    private static native void nDrawRenderNode(long l, long l1);

    private static native void nDumpProfileInfo(long l, FileDescriptor filedescriptor, int i);

    private static native void nFence(long l);

    private static native int nGetRenderThreadTid(long l);

    private static native void nInitialize(long l, Surface surface);

    private static native void nInvokeFunctor(long l, boolean flag);

    private static native boolean nLoadSystemProperties(long l);

    private static native void nNotifyFramePending(long l);

    private static native void nOverrideProperty(String s, String s1);

    private static native boolean nPauseSurface(long l, Surface surface);

    private static native void nPushLayerUpdate(long l, long l1);

    private static native void nRegisterAnimatingRenderNode(long l, long l1);

    private static native void nRegisterVectorDrawableAnimator(long l, long l1);

    private static native void nRemoveFrameMetricsObserver(long l, long l1);

    private static native void nRemoveRenderNode(long l, long l1);

    private static native void nRotateProcessStatsBuffer();

    private static native void nSerializeDisplayListTree(long l);

    private static native void nSetContentDrawBounds(long l, int i, int j, int k, int i1);

    private static native void nSetLightCenter(long l, float f, float f1, float f2);

    private static native void nSetName(long l, String s);

    private static native void nSetOpaque(long l, boolean flag);

    private static native void nSetProcessStatsBuffer(int i);

    private static native void nSetStopped(long l, boolean flag);

    private static native void nSetWideGamut(long l, boolean flag);

    private static native void nSetup(long l, float f, int i, int j);

    private static native void nStopDrawing(long l);

    private static native int nSyncAndDrawFrame(long l, long al[], int i);

    private static native void nTrimMemory(int i);

    private static native void nUpdateSurface(long l, Surface surface);

    public static void overrideProperty(String s, String s1)
    {
        if(s == null || s1 == null)
        {
            throw new IllegalArgumentException("name and value must be non-null");
        } else
        {
            nOverrideProperty(s, s1);
            return;
        }
    }

    public static void setupDiskCache(File file)
    {
        setupShadersDiskCache((new File(file, "com.android.opengl.shaders_cache")).getAbsolutePath());
    }

    static native void setupShadersDiskCache(String s);

    public static void trimMemory(int i)
    {
        nTrimMemory(i);
    }

    private void updateEnabledState(Surface surface)
    {
        if(surface == null || surface.isValid() ^ true)
            setEnabled(false);
        else
            setEnabled(mInitialized);
    }

    private void updateRootDisplayList(View view, DrawCallbacks drawcallbacks)
    {
        DisplayListCanvas displaylistcanvas;
        Trace.traceBegin(8L, "Record View#draw()");
        updateViewTreeDisplayList(view);
        if(!mRootNodeNeedsUpdate && !(mRootNode.isValid() ^ true))
            break MISSING_BLOCK_LABEL_118;
        displaylistcanvas = mRootNode.start(mSurfaceWidth, mSurfaceHeight);
        int i = displaylistcanvas.save();
        displaylistcanvas.translate(mInsetLeft, mInsetTop);
        drawcallbacks.onPreDraw(displaylistcanvas);
        displaylistcanvas.insertReorderBarrier();
        displaylistcanvas.drawRenderNode(view.updateDisplayListIfDirty());
        displaylistcanvas.insertInorderBarrier();
        drawcallbacks.onPostDraw(displaylistcanvas);
        displaylistcanvas.restoreToCount(i);
        mRootNodeNeedsUpdate = false;
        mRootNode.end(displaylistcanvas);
        Trace.traceEnd(8L);
        return;
        view;
        mRootNode.end(displaylistcanvas);
        throw view;
    }

    private void updateViewTreeDisplayList(View view)
    {
        view.mPrivateFlags = view.mPrivateFlags | 0x20;
        boolean flag;
        if((view.mPrivateFlags & 0x80000000) == 0x80000000)
            flag = true;
        else
            flag = false;
        view.mRecreateDisplayList = flag;
        view.mPrivateFlags = view.mPrivateFlags & 0x7fffffff;
        view.updateDisplayListIfDirty();
        view.mRecreateDisplayList = false;
    }

    void addFrameMetricsObserver(FrameMetricsObserver framemetricsobserver)
    {
        framemetricsobserver.mNative = new VirtualRefBasePtr(nAddFrameMetricsObserver(mNativeProxy, framemetricsobserver));
    }

    public void addRenderNode(RenderNode rendernode, boolean flag)
    {
        nAddRenderNode(mNativeProxy, rendernode.mNativeRenderNode, flag);
    }

    void buildLayer(RenderNode rendernode)
    {
        nBuildLayer(mNativeProxy, rendernode.getNativeDisplayList());
    }

    boolean copyLayerInto(HardwareLayer hardwarelayer, Bitmap bitmap)
    {
        return nCopyLayerInto(mNativeProxy, hardwarelayer.getDeferredLayerUpdater(), bitmap);
    }

    HardwareLayer createTextureLayer()
    {
        return HardwareLayer.adoptTextureLayer(this, nCreateTextureLayer(mNativeProxy));
    }

    void destroy()
    {
        mInitialized = false;
        updateEnabledState(null);
        nDestroy(mNativeProxy, mRootNode.mNativeRenderNode);
    }

    void destroyHardwareResources(View view)
    {
        destroyResources(view);
        nDestroyHardwareResources(mNativeProxy);
    }

    void detachSurfaceTexture(long l)
    {
        nDetachSurfaceTexture(mNativeProxy, l);
    }

    void draw(View view, View.AttachInfo attachinfo, DrawCallbacks drawcallbacks)
    {
        attachinfo.mIgnoreDirtyState = true;
        Choreographer choreographer = attachinfo.mViewRootImpl.mChoreographer;
        choreographer.mFrameInfo.markDrawStart();
        updateRootDisplayList(view, drawcallbacks);
        attachinfo.mIgnoreDirtyState = false;
        if(attachinfo.mPendingAnimatingRenderNodes != null)
        {
            int i = attachinfo.mPendingAnimatingRenderNodes.size();
            for(int j = 0; j < i; j++)
                registerAnimatingRenderNode((RenderNode)attachinfo.mPendingAnimatingRenderNodes.get(j));

            attachinfo.mPendingAnimatingRenderNodes.clear();
            attachinfo.mPendingAnimatingRenderNodes = null;
        }
        view = choreographer.mFrameInfo.mFrameInfo;
        int k = nSyncAndDrawFrame(mNativeProxy, view, view.length);
        if((k & 2) != 0)
        {
            setEnabled(false);
            attachinfo.mViewRootImpl.mSurface.release();
            attachinfo.mViewRootImpl.invalidate();
        }
        if((k & 1) != 0)
            attachinfo.mViewRootImpl.invalidate();
    }

    public void drawRenderNode(RenderNode rendernode)
    {
        nDrawRenderNode(mNativeProxy, rendernode.mNativeRenderNode);
    }

    void dumpGfxInfo(PrintWriter printwriter, FileDescriptor filedescriptor, String as[])
    {
        printwriter.flush();
        int i = 0;
        int j = 0;
        while(j < as.length) 
        {
            printwriter = as[j];
            int k;
            if(printwriter.equals("framestats"))
            {
                k = i | 1;
            } else
            {
                k = i;
                if(printwriter.equals("reset"))
                    k = i | 2;
            }
            j++;
            i = k;
        }
        nDumpProfileInfo(mNativeProxy, filedescriptor, i);
    }

    void fence()
    {
        nFence(mNativeProxy);
    }

    protected void finalize()
        throws Throwable
    {
        nDeleteProxy(mNativeProxy);
        mNativeProxy = 0L;
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    int getHeight()
    {
        return mHeight;
    }

    int getWidth()
    {
        return mWidth;
    }

    boolean initialize(Surface surface)
        throws Surface.OutOfResourcesException
    {
        boolean flag = mInitialized;
        mInitialized = true;
        updateEnabledState(surface);
        nInitialize(mNativeProxy, surface);
        return flag ^ true;
    }

    boolean initializeIfNeeded(int i, int j, View.AttachInfo attachinfo, Surface surface, Rect rect)
        throws Surface.OutOfResourcesException
    {
        if(isRequested() && !isEnabled() && initialize(surface))
        {
            setup(i, j, attachinfo, rect);
            return true;
        } else
        {
            return false;
        }
    }

    void invalidateRoot()
    {
        mRootNodeNeedsUpdate = true;
    }

    boolean isEnabled()
    {
        return mEnabled;
    }

    boolean isOpaque()
    {
        return mIsOpaque;
    }

    boolean isRequested()
    {
        return mRequested;
    }

    boolean loadSystemProperties()
    {
        boolean flag = nLoadSystemProperties(mNativeProxy);
        if(flag)
            invalidateRoot();
        return flag;
    }

    public void notifyFramePending()
    {
        nNotifyFramePending(mNativeProxy);
    }

    void onLayerDestroyed(HardwareLayer hardwarelayer)
    {
        nCancelLayerUpdate(mNativeProxy, hardwarelayer.getDeferredLayerUpdater());
    }

    boolean pauseSurface(Surface surface)
    {
        return nPauseSurface(mNativeProxy, surface);
    }

    void pushLayerUpdate(HardwareLayer hardwarelayer)
    {
        nPushLayerUpdate(mNativeProxy, hardwarelayer.getDeferredLayerUpdater());
    }

    void registerAnimatingRenderNode(RenderNode rendernode)
    {
        nRegisterAnimatingRenderNode(mRootNode.mNativeRenderNode, rendernode.mNativeRenderNode);
    }

    void registerVectorDrawableAnimator(android.graphics.drawable.AnimatedVectorDrawable.VectorDrawableAnimatorRT vectordrawableanimatorrt)
    {
        nRegisterVectorDrawableAnimator(mRootNode.mNativeRenderNode, vectordrawableanimatorrt.getAnimatorNativePtr());
    }

    void removeFrameMetricsObserver(FrameMetricsObserver framemetricsobserver)
    {
        nRemoveFrameMetricsObserver(mNativeProxy, framemetricsobserver.mNative.get());
        framemetricsobserver.mNative = null;
    }

    public void removeRenderNode(RenderNode rendernode)
    {
        nRemoveRenderNode(mNativeProxy, rendernode.mNativeRenderNode);
    }

    public void serializeDisplayListTree()
    {
        nSerializeDisplayListTree(mNativeProxy);
    }

    public void setContentDrawBounds(int i, int j, int k, int l)
    {
        nSetContentDrawBounds(mNativeProxy, i, j, k, l);
    }

    void setEnabled(boolean flag)
    {
        mEnabled = flag;
    }

    void setLightCenter(View.AttachInfo attachinfo)
    {
        Point point = attachinfo.mPoint;
        attachinfo.mDisplay.getRealSize(point);
        float f = (float)point.x / 2.0F;
        float f1 = attachinfo.mWindowLeft;
        float f2 = mLightY;
        float f3 = attachinfo.mWindowTop;
        nSetLightCenter(mNativeProxy, f - f1, f2 - f3, mLightZ);
    }

    void setOpaque(boolean flag)
    {
        if(flag)
            flag = mHasInsets ^ true;
        else
            flag = false;
        mIsOpaque = flag;
        nSetOpaque(mNativeProxy, mIsOpaque);
    }

    void setRequested(boolean flag)
    {
        mRequested = flag;
    }

    void setStopped(boolean flag)
    {
        nSetStopped(mNativeProxy, flag);
    }

    void setWideGamut(boolean flag)
    {
        nSetWideGamut(mNativeProxy, flag);
    }

    void setup(int i, int j, View.AttachInfo attachinfo, Rect rect)
    {
        mWidth = i;
        mHeight = j;
        break MISSING_BLOCK_LABEL_10;
        if(rect != null && (rect.left != 0 || rect.right != 0 || rect.top != 0 || rect.bottom != 0))
        {
            mHasInsets = true;
            mInsetLeft = rect.left;
            mInsetTop = rect.top;
            mSurfaceWidth = mInsetLeft + i + rect.right;
            mSurfaceHeight = mInsetTop + j + rect.bottom;
            setOpaque(false);
        } else
        {
            mHasInsets = false;
            mInsetLeft = 0;
            mInsetTop = 0;
            mSurfaceWidth = i;
            mSurfaceHeight = j;
        }
        mRootNode.setLeftTopRightBottom(-mInsetLeft, -mInsetTop, mSurfaceWidth, mSurfaceHeight);
        nSetup(mNativeProxy, mLightRadius, mAmbientShadowAlpha, mSpotShadowAlpha);
        setLightCenter(attachinfo);
        return;
    }

    void stopDrawing()
    {
        nStopDrawing(mNativeProxy);
    }

    void updateSurface(Surface surface)
        throws Surface.OutOfResourcesException
    {
        updateEnabledState(surface);
        nUpdateSurface(mNativeProxy, surface);
    }

    private static final String CACHE_PATH_SHADERS = "com.android.opengl.shaders_cache";
    public static final String DEBUG_DIRTY_REGIONS_PROPERTY = "debug.hwui.show_dirty_regions";
    public static final String DEBUG_OVERDRAW_PROPERTY = "debug.hwui.overdraw";
    public static final String DEBUG_RENDERER_PROPERTY = "debug.hwui.renderer";
    public static final String DEBUG_SHOW_LAYERS_UPDATES_PROPERTY = "debug.hwui.show_layers_updates";
    public static final String DEBUG_SHOW_NON_RECTANGULAR_CLIP_PROPERTY = "debug.hwui.show_non_rect_clip";
    private static final int FLAG_DUMP_FRAMESTATS = 1;
    private static final int FLAG_DUMP_RESET = 2;
    private static final String LOG_TAG = "ThreadedRenderer";
    public static final String OVERDRAW_PROPERTY_SHOW = "show";
    static final String PRINT_CONFIG_PROPERTY = "debug.hwui.print_config";
    static final String PROFILE_MAXFRAMES_PROPERTY = "debug.hwui.profile.maxframes";
    public static final String PROFILE_PROPERTY = "debug.hwui.profile";
    public static final String PROFILE_PROPERTY_VISUALIZE_BARS = "visual_bars";
    private static final int SYNC_CONTEXT_IS_STOPPED = 4;
    private static final int SYNC_INVALIDATE_REQUIRED = 1;
    private static final int SYNC_LOST_SURFACE_REWARD_IF_FOUND = 2;
    private static final int SYNC_OK = 0;
    private static final String VISUALIZERS[] = {
        "visual_bars"
    };
    public static boolean sRendererDisabled = false;
    private static Boolean sSupportsOpenGL;
    public static boolean sSystemRendererDisabled = false;
    public static boolean sTrimForeground = false;
    private final int mAmbientShadowAlpha;
    private boolean mEnabled;
    private boolean mHasInsets;
    private int mHeight;
    private boolean mInitialized;
    private int mInsetLeft;
    private int mInsetTop;
    private boolean mIsOpaque;
    private final float mLightRadius;
    private final float mLightY;
    private final float mLightZ;
    private long mNativeProxy;
    private boolean mRequested;
    private RenderNode mRootNode;
    private boolean mRootNodeNeedsUpdate;
    private final int mSpotShadowAlpha;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    private int mWidth;

    static 
    {
        isAvailable();
    }

    // Unreferenced inner class android/view/ThreadedRenderer$ProcessInitializer$1

/* anonymous class */
    class ProcessInitializer._cls1 extends IGraphicsStatsCallback.Stub
    {

        public void onRotateGraphicsStatsBuffer()
            throws RemoteException
        {
            ProcessInitializer._2D_wrap0(ProcessInitializer.this);
        }

        final ProcessInitializer this$1;

            
            {
                this$1 = ProcessInitializer.this;
                super();
            }
    }

}
