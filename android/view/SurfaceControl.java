// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.*;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import dalvik.system.CloseGuard;

// Referenced classes of package android.view:
//            Surface, SurfaceSession, WindowAnimationFrameStats, WindowContentFrameStats

public class SurfaceControl
{
    public static final class PhysicalDisplayInfo
    {

        public void copyFrom(PhysicalDisplayInfo physicaldisplayinfo)
        {
            width = physicaldisplayinfo.width;
            height = physicaldisplayinfo.height;
            refreshRate = physicaldisplayinfo.refreshRate;
            density = physicaldisplayinfo.density;
            xDpi = physicaldisplayinfo.xDpi;
            yDpi = physicaldisplayinfo.yDpi;
            secure = physicaldisplayinfo.secure;
            appVsyncOffsetNanos = physicaldisplayinfo.appVsyncOffsetNanos;
            presentationDeadlineNanos = physicaldisplayinfo.presentationDeadlineNanos;
        }

        public boolean equals(PhysicalDisplayInfo physicaldisplayinfo)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(physicaldisplayinfo != null)
            {
                flag1 = flag;
                if(width == physicaldisplayinfo.width)
                {
                    flag1 = flag;
                    if(height == physicaldisplayinfo.height)
                    {
                        flag1 = flag;
                        if(refreshRate == physicaldisplayinfo.refreshRate)
                        {
                            flag1 = flag;
                            if(density == physicaldisplayinfo.density)
                            {
                                flag1 = flag;
                                if(xDpi == physicaldisplayinfo.xDpi)
                                {
                                    flag1 = flag;
                                    if(yDpi == physicaldisplayinfo.yDpi)
                                    {
                                        flag1 = flag;
                                        if(secure == physicaldisplayinfo.secure)
                                        {
                                            flag1 = flag;
                                            if(appVsyncOffsetNanos == physicaldisplayinfo.appVsyncOffsetNanos)
                                            {
                                                flag1 = flag;
                                                if(presentationDeadlineNanos == physicaldisplayinfo.presentationDeadlineNanos)
                                                    flag1 = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return flag1;
        }

        public boolean equals(Object obj)
        {
            boolean flag;
            if(obj instanceof PhysicalDisplayInfo)
                flag = equals((PhysicalDisplayInfo)obj);
            else
                flag = false;
            return flag;
        }

        public int hashCode()
        {
            return 0;
        }

        public String toString()
        {
            return (new StringBuilder()).append("PhysicalDisplayInfo{").append(width).append(" x ").append(height).append(", ").append(refreshRate).append(" fps, ").append("density ").append(density).append(", ").append(xDpi).append(" x ").append(yDpi).append(" dpi, secure ").append(secure).append(", appVsyncOffset ").append(appVsyncOffsetNanos).append(", bufferDeadline ").append(presentationDeadlineNanos).append("}").toString();
        }

        public long appVsyncOffsetNanos;
        public float density;
        public int height;
        public long presentationDeadlineNanos;
        public float refreshRate;
        public boolean secure;
        public int width;
        public float xDpi;
        public float yDpi;

        public PhysicalDisplayInfo()
        {
        }

        public PhysicalDisplayInfo(PhysicalDisplayInfo physicaldisplayinfo)
        {
            copyFrom(physicaldisplayinfo);
        }
    }


    public SurfaceControl(SurfaceControl surfacecontrol)
    {
        mCloseGuard = CloseGuard.get();
        mName = surfacecontrol.mName;
        mNativeObject = surfacecontrol.mNativeObject;
        surfacecontrol.mCloseGuard.close();
        surfacecontrol.mNativeObject = 0L;
        mCloseGuard.open("release");
    }

    public SurfaceControl(SurfaceSession surfacesession, String s, int i, int j, int k, int l)
        throws Surface.OutOfResourcesException
    {
        this(surfacesession, s, i, j, k, l, null, -1, Binder.getCallingUid());
    }

    public SurfaceControl(SurfaceSession surfacesession, String s, int i, int j, int k, int l, int i1, 
            int j1)
        throws Surface.OutOfResourcesException
    {
        this(surfacesession, s, i, j, k, l, null, i1, j1);
    }

    public SurfaceControl(SurfaceSession surfacesession, String s, int i, int j, int k, int l, SurfaceControl surfacecontrol, 
            int i1, int j1)
        throws Surface.OutOfResourcesException
    {
        mCloseGuard = CloseGuard.get();
        if(surfacesession == null)
            throw new IllegalArgumentException("session must not be null");
        if(s == null)
            throw new IllegalArgumentException("name must not be null");
        if((l & 4) == 0)
            Log.w("SurfaceControl", (new StringBuilder()).append("Surfaces should always be created with the HIDDEN flag set to ensure that they are not made visible prematurely before all of the surface's properties have been configured.  Set the other properties and make the surface visible within a transaction.  New surface name: ").append(s).toString(), new Throwable());
        mName = s;
        long l1;
        if(surfacecontrol != null)
            l1 = surfacecontrol.mNativeObject;
        else
            l1 = 0L;
        mNativeObject = nativeCreate(surfacesession, s, i, j, k, l, l1, i1, j1);
        if(mNativeObject == 0L)
        {
            throw new Surface.OutOfResourcesException("Couldn't allocate SurfaceControl native object");
        } else
        {
            mCloseGuard.open("release");
            return;
        }
    }

    private void checkNotReleased()
    {
        if(mNativeObject == 0L)
            throw new NullPointerException("mNativeObject is null. Have you called release() already?");
        else
            return;
    }

    public static boolean clearAnimationFrameStats()
    {
        return nativeClearAnimationFrameStats();
    }

    public static void closeTransaction()
    {
        nativeCloseTransaction(false);
    }

    public static void closeTransactionSync()
    {
        nativeCloseTransaction(true);
    }

    public static IBinder createDisplay(String s, boolean flag)
    {
        if(s == null)
            throw new IllegalArgumentException("name must not be null");
        else
            return nativeCreateDisplay(s, flag);
    }

    public static void destroyDisplay(IBinder ibinder)
    {
        if(ibinder == null)
        {
            throw new IllegalArgumentException("displayToken must not be null");
        } else
        {
            nativeDestroyDisplay(ibinder);
            return;
        }
    }

    public static int getActiveColorMode(IBinder ibinder)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        else
            return nativeGetActiveColorMode(ibinder);
    }

    public static int getActiveConfig(IBinder ibinder)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        else
            return nativeGetActiveConfig(ibinder);
    }

    public static boolean getAnimationFrameStats(WindowAnimationFrameStats windowanimationframestats)
    {
        return nativeGetAnimationFrameStats(windowanimationframestats);
    }

    public static IBinder getBuiltInDisplay(int i)
    {
        return nativeGetBuiltInDisplay(i);
    }

    public static int[] getDisplayColorModes(IBinder ibinder)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        else
            return nativeGetDisplayColorModes(ibinder);
    }

    public static PhysicalDisplayInfo[] getDisplayConfigs(IBinder ibinder)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        else
            return nativeGetDisplayConfigs(ibinder);
    }

    public static Display.HdrCapabilities getHdrCapabilities(IBinder ibinder)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        else
            return nativeGetHdrCapabilities(ibinder);
    }

    private static native boolean nativeClearAnimationFrameStats();

    private static native boolean nativeClearContentFrameStats(long l);

    private static native void nativeCloseTransaction(boolean flag);

    private static native long nativeCreate(SurfaceSession surfacesession, String s, int i, int j, int k, int l, long l1, 
            int i1, int j1)
        throws Surface.OutOfResourcesException;

    private static native IBinder nativeCreateDisplay(String s, boolean flag);

    private static native void nativeDeferTransactionUntil(long l, IBinder ibinder, long l1);

    private static native void nativeDeferTransactionUntilSurface(long l, long l1, long l2);

    private static native void nativeDestroy(long l);

    private static native void nativeDestroyDisplay(IBinder ibinder);

    private static native void nativeDisconnect(long l);

    private static native int nativeGetActiveColorMode(IBinder ibinder);

    private static native int nativeGetActiveConfig(IBinder ibinder);

    private static native boolean nativeGetAnimationFrameStats(WindowAnimationFrameStats windowanimationframestats);

    private static native IBinder nativeGetBuiltInDisplay(int i);

    private static native boolean nativeGetContentFrameStats(long l, WindowContentFrameStats windowcontentframestats);

    private static native int[] nativeGetDisplayColorModes(IBinder ibinder);

    private static native PhysicalDisplayInfo[] nativeGetDisplayConfigs(IBinder ibinder);

    private static native IBinder nativeGetHandle(long l);

    private static native Display.HdrCapabilities nativeGetHdrCapabilities(IBinder ibinder);

    private static native boolean nativeGetTransformToDisplayInverse(long l);

    private static native void nativeOpenTransaction();

    private static native void nativeRelease(long l);

    private static native void nativeReparentChildren(long l, IBinder ibinder);

    private static native Bitmap nativeScreenshot(IBinder ibinder, Rect rect, int i, int j, int k, int l, boolean flag, boolean flag1, 
            int i1);

    private static native void nativeScreenshot(IBinder ibinder, Surface surface, Rect rect, int i, int j, int k, int l, boolean flag, 
            boolean flag1);

    private static native void nativeScreenshotExternal(IBinder ibinder, Surface surface, Rect rect, int i, int j, int k, int l, boolean flag, 
            boolean flag1, boolean flag2);

    private static native GraphicBuffer nativeScreenshotToBuffer(IBinder ibinder, Rect rect, int i, int j, int k, int l, boolean flag, boolean flag1, 
            int i1);

    private static native boolean nativeSetActiveColorMode(IBinder ibinder, int i);

    private static native boolean nativeSetActiveConfig(IBinder ibinder, int i);

    private static native void nativeSetAlpha(long l, float f);

    private static native void nativeSetAnimationTransaction();

    private static native void nativeSetDisplayLayerStack(IBinder ibinder, int i);

    private static native void nativeSetDisplayPowerMode(IBinder ibinder, int i);

    private static native void nativeSetDisplayProjection(IBinder ibinder, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2);

    private static native void nativeSetDisplaySize(IBinder ibinder, int i, int j);

    private static native void nativeSetDisplaySurface(IBinder ibinder, long l);

    private static native void nativeSetFinalCrop(long l, int i, int j, int k, int i1);

    private static native void nativeSetFlags(long l, int i, int j);

    private static native void nativeSetGeometryAppliesWithResize(long l);

    private static native void nativeSetLayer(long l, int i);

    private static native void nativeSetLayerStack(long l, int i);

    private static native void nativeSetMatrix(long l, float f, float f1, float f2, float f3);

    private static native void nativeSetOverrideScalingMode(long l, int i);

    private static native void nativeSetPosition(long l, float f, float f1);

    private static native void nativeSetRelativeLayer(long l, IBinder ibinder, int i);

    private static native void nativeSetSize(long l, int i, int j);

    private static native void nativeSetTransparentRegionHint(long l, Region region);

    private static native void nativeSetWindowCrop(long l, int i, int j, int k, int i1);

    private static native void nativeSeverChildren(long l);

    public static void openTransaction()
    {
        nativeOpenTransaction();
    }

    public static Bitmap screenshot(int i, int j)
    {
        return nativeScreenshot(getBuiltInDisplay(0), new Rect(), i, j, 0, 0, true, false, 0);
    }

    public static Bitmap screenshot(Rect rect, int i, int j, int k, int l, boolean flag, int i1)
    {
        return nativeScreenshot(getBuiltInDisplay(0), rect, i, j, k, l, false, flag, i1);
    }

    public static void screenshot(IBinder ibinder, Surface surface)
    {
        screenshot(ibinder, surface, new Rect(), 0, 0, 0, 0, true, false);
    }

    public static void screenshot(IBinder ibinder, Surface surface, int i, int j)
    {
        screenshot(ibinder, surface, new Rect(), i, j, 0, 0, true, false);
    }

    public static void screenshot(IBinder ibinder, Surface surface, int i, int j, int k, int l, boolean flag)
    {
        screenshot(ibinder, surface, new Rect(), i, j, k, l, false, flag);
    }

    private static void screenshot(IBinder ibinder, Surface surface, Rect rect, int i, int j, int k, int l, boolean flag, 
            boolean flag1)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        if(surface == null)
        {
            throw new IllegalArgumentException("consumer must not be null");
        } else
        {
            nativeScreenshot(ibinder, surface, rect, i, j, k, l, flag, flag1);
            return;
        }
    }

    public static void screenshotExternal(IBinder ibinder, Surface surface)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        if(surface == null)
        {
            throw new IllegalArgumentException("consumer must not be null");
        } else
        {
            nativeScreenshotExternal(ibinder, surface, new Rect(), 0, 0, 0, 0, true, false, true);
            return;
        }
    }

    public static GraphicBuffer screenshotToBuffer(Rect rect, int i, int j, int k, int l, boolean flag, int i1)
    {
        return nativeScreenshotToBuffer(getBuiltInDisplay(0), rect, i, j, k, l, false, flag, i1);
    }

    public static boolean setActiveColorMode(IBinder ibinder, int i)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        else
            return nativeSetActiveColorMode(ibinder, i);
    }

    public static boolean setActiveConfig(IBinder ibinder, int i)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        else
            return nativeSetActiveConfig(ibinder, i);
    }

    public static void setAnimationTransaction()
    {
        nativeSetAnimationTransaction();
    }

    public static void setDisplayLayerStack(IBinder ibinder, int i)
    {
        if(ibinder == null)
        {
            throw new IllegalArgumentException("displayToken must not be null");
        } else
        {
            nativeSetDisplayLayerStack(ibinder, i);
            return;
        }
    }

    public static void setDisplayPowerMode(IBinder ibinder, int i)
    {
        if(ibinder == null)
        {
            throw new IllegalArgumentException("displayToken must not be null");
        } else
        {
            nativeSetDisplayPowerMode(ibinder, i);
            return;
        }
    }

    public static void setDisplayProjection(IBinder ibinder, int i, Rect rect, Rect rect1)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        if(rect == null)
            throw new IllegalArgumentException("layerStackRect must not be null");
        if(rect1 == null)
        {
            throw new IllegalArgumentException("displayRect must not be null");
        } else
        {
            nativeSetDisplayProjection(ibinder, i, rect.left, rect.top, rect.right, rect.bottom, rect1.left, rect1.top, rect1.right, rect1.bottom);
            return;
        }
    }

    public static void setDisplaySize(IBinder ibinder, int i, int j)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        if(i <= 0 || j <= 0)
        {
            throw new IllegalArgumentException("width and height must be positive");
        } else
        {
            nativeSetDisplaySize(ibinder, i, j);
            return;
        }
    }

    public static void setDisplaySurface(IBinder ibinder, Surface surface)
    {
        if(ibinder == null)
            throw new IllegalArgumentException("displayToken must not be null");
        if(surface == null) goto _L2; else goto _L1
_L1:
        Object obj = surface.mLock;
        obj;
        JVM INSTR monitorenter ;
        nativeSetDisplaySurface(ibinder, surface.mNativeObject);
        obj;
        JVM INSTR monitorexit ;
_L4:
        return;
        ibinder;
        throw ibinder;
_L2:
        nativeSetDisplaySurface(ibinder, 0L);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean clearContentFrameStats()
    {
        checkNotReleased();
        return nativeClearContentFrameStats(mNativeObject);
    }

    public void deferTransactionUntil(IBinder ibinder, long l)
    {
        if(l > 0L)
            nativeDeferTransactionUntil(mNativeObject, ibinder, l);
    }

    public void deferTransactionUntil(Surface surface, long l)
    {
        if(l > 0L)
            nativeDeferTransactionUntilSurface(mNativeObject, surface.mNativeObject, l);
    }

    public void destroy()
    {
        if(mNativeObject != 0L)
        {
            nativeDestroy(mNativeObject);
            mNativeObject = 0L;
        }
        mCloseGuard.close();
    }

    public void detachChildren()
    {
        nativeSeverChildren(mNativeObject);
    }

    public void disconnect()
    {
        if(mNativeObject != 0L)
            nativeDisconnect(mNativeObject);
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        if(mNativeObject != 0L)
            nativeRelease(mNativeObject);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public boolean getContentFrameStats(WindowContentFrameStats windowcontentframestats)
    {
        checkNotReleased();
        return nativeGetContentFrameStats(mNativeObject, windowcontentframestats);
    }

    public IBinder getHandle()
    {
        return nativeGetHandle(mNativeObject);
    }

    public void hide()
    {
        checkNotReleased();
        nativeSetFlags(mNativeObject, 1, 1);
    }

    public void release()
    {
        if(mNativeObject != 0L)
        {
            nativeRelease(mNativeObject);
            mNativeObject = 0L;
        }
        mCloseGuard.close();
    }

    public void reparentChildren(IBinder ibinder)
    {
        nativeReparentChildren(mNativeObject, ibinder);
    }

    public void setAlpha(float f)
    {
        checkNotReleased();
        nativeSetAlpha(mNativeObject, f);
    }

    public void setBlur(boolean flag)
    {
        checkNotReleased();
        long l = mNativeObject;
        byte byte0;
        if(flag)
            byte0 = 16;
        else
            byte0 = 0;
        nativeSetFlags(l, byte0, 16);
    }

    public void setFinalCrop(Rect rect)
    {
        checkNotReleased();
        if(rect != null)
            nativeSetFinalCrop(mNativeObject, rect.left, rect.top, rect.right, rect.bottom);
        else
            nativeSetFinalCrop(mNativeObject, 0, 0, 0, 0);
    }

    public void setGeometryAppliesWithResize()
    {
        checkNotReleased();
        nativeSetGeometryAppliesWithResize(mNativeObject);
    }

    public void setLayer(int i)
    {
        checkNotReleased();
        nativeSetLayer(mNativeObject, i);
    }

    public void setLayerStack(int i)
    {
        checkNotReleased();
        nativeSetLayerStack(mNativeObject, i);
    }

    public void setMatrix(float f, float f1, float f2, float f3)
    {
        checkNotReleased();
        nativeSetMatrix(mNativeObject, f, f1, f2, f3);
    }

    public void setOpaque(boolean flag)
    {
        checkNotReleased();
        if(flag)
            nativeSetFlags(mNativeObject, 2, 2);
        else
            nativeSetFlags(mNativeObject, 0, 2);
    }

    public void setOverrideScalingMode(int i)
    {
        checkNotReleased();
        nativeSetOverrideScalingMode(mNativeObject, i);
    }

    public void setPosition(float f, float f1)
    {
        checkNotReleased();
        nativeSetPosition(mNativeObject, f, f1);
    }

    public void setRecordHide(boolean flag)
    {
        checkNotReleased();
        long l = mNativeObject;
        byte byte0;
        if(flag)
            byte0 = 32;
        else
            byte0 = 0;
        nativeSetFlags(l, byte0, 32);
    }

    public void setRelativeLayer(IBinder ibinder, int i)
    {
        checkNotReleased();
        nativeSetRelativeLayer(mNativeObject, ibinder, i);
    }

    public void setSecure(boolean flag)
    {
        checkNotReleased();
        if(flag)
            nativeSetFlags(mNativeObject, 128, 128);
        else
            nativeSetFlags(mNativeObject, 0, 128);
    }

    public void setSize(int i, int j)
    {
        checkNotReleased();
        nativeSetSize(mNativeObject, i, j);
    }

    public void setTransparentRegionHint(Region region)
    {
        checkNotReleased();
        nativeSetTransparentRegionHint(mNativeObject, region);
    }

    public void setWindowCrop(Rect rect)
    {
        checkNotReleased();
        if(rect != null)
            nativeSetWindowCrop(mNativeObject, rect.left, rect.top, rect.right, rect.bottom);
        else
            nativeSetWindowCrop(mNativeObject, 0, 0, 0, 0);
    }

    public void show()
    {
        checkNotReleased();
        nativeSetFlags(mNativeObject, 0, 1);
    }

    public String toString()
    {
        return (new StringBuilder()).append("Surface(name=").append(mName).append(")").toString();
    }

    public static final int BUILT_IN_DISPLAY_ID_HDMI = 1;
    public static final int BUILT_IN_DISPLAY_ID_MAIN = 0;
    public static final int CURSOR_WINDOW = 8192;
    private static final int FLAG_BLUR = 16;
    private static final int FLAG_RECORD_HIDE = 32;
    public static final int FX_SURFACE_DIM = 0x20000;
    public static final int FX_SURFACE_MASK = 0xf0000;
    public static final int FX_SURFACE_NORMAL = 0;
    public static final int HIDDEN = 4;
    public static final int NON_PREMULTIPLIED = 256;
    public static final int OPAQUE = 1024;
    public static final int POWER_MODE_DOZE = 1;
    public static final int POWER_MODE_DOZE_SUSPEND = 3;
    public static final int POWER_MODE_NORMAL = 2;
    public static final int POWER_MODE_OFF = 0;
    public static final int PROTECTED_APP = 2048;
    public static final int SECURE = 128;
    private static final int SURFACE_HIDDEN = 1;
    private static final int SURFACE_OPAQUE = 2;
    private static final String TAG = "SurfaceControl";
    public static final int WINDOW_TYPE_DONT_SCREENSHOT = 0x6bd83;
    private final CloseGuard mCloseGuard;
    private final String mName;
    long mNativeObject;
}
