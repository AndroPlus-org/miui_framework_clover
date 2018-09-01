// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;

import android.content.Context;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

// Referenced classes of package android.opengl:
//            EGLLogWrapper, GLDebugHelper

public class GLSurfaceView extends SurfaceView
    implements android.view.SurfaceHolder.Callback2
{
    private abstract class BaseConfigChooser
        implements EGLConfigChooser
    {

        private int[] filterConfigSpec(int ai[])
        {
            if(GLSurfaceView._2D_get2(GLSurfaceView.this) != 2 && GLSurfaceView._2D_get2(GLSurfaceView.this) != 3)
                return ai;
            int i = ai.length;
            int ai1[] = new int[i + 2];
            System.arraycopy(ai, 0, ai1, 0, i - 1);
            ai1[i - 1] = 12352;
            if(GLSurfaceView._2D_get2(GLSurfaceView.this) == 2)
                ai1[i] = 4;
            else
                ai1[i] = 64;
            ai1[i + 1] = 12344;
            return ai1;
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay egldisplay)
        {
            int ai[] = new int[1];
            if(!egl10.eglChooseConfig(egldisplay, mConfigSpec, null, 0, ai))
                throw new IllegalArgumentException("eglChooseConfig failed");
            int i = ai[0];
            if(i <= 0)
                throw new IllegalArgumentException("No configs match configSpec");
            EGLConfig aeglconfig[] = new EGLConfig[i];
            if(!egl10.eglChooseConfig(egldisplay, mConfigSpec, aeglconfig, i, ai))
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            egl10 = chooseConfig(egl10, egldisplay, aeglconfig);
            if(egl10 == null)
                throw new IllegalArgumentException("No config chosen");
            else
                return egl10;
        }

        abstract EGLConfig chooseConfig(EGL10 egl10, EGLDisplay egldisplay, EGLConfig aeglconfig[]);

        protected int mConfigSpec[];
        final GLSurfaceView this$0;

        public BaseConfigChooser(int ai[])
        {
            this$0 = GLSurfaceView.this;
            super();
            mConfigSpec = filterConfigSpec(ai);
        }
    }

    private class ComponentSizeChooser extends BaseConfigChooser
    {

        private int findConfigAttrib(EGL10 egl10, EGLDisplay egldisplay, EGLConfig eglconfig, int i, int j)
        {
            if(egl10.eglGetConfigAttrib(egldisplay, eglconfig, i, mValue))
                return mValue[0];
            else
                return j;
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay egldisplay, EGLConfig aeglconfig[])
        {
            int i = aeglconfig.length;
            for(int j = 0; j < i; j++)
            {
                EGLConfig eglconfig = aeglconfig[j];
                int k = findConfigAttrib(egl10, egldisplay, eglconfig, 12325, 0);
                int l = findConfigAttrib(egl10, egldisplay, eglconfig, 12326, 0);
                if(k < mDepthSize || l < mStencilSize)
                    continue;
                l = findConfigAttrib(egl10, egldisplay, eglconfig, 12324, 0);
                k = findConfigAttrib(egl10, egldisplay, eglconfig, 12323, 0);
                int i1 = findConfigAttrib(egl10, egldisplay, eglconfig, 12322, 0);
                int j1 = findConfigAttrib(egl10, egldisplay, eglconfig, 12321, 0);
                if(l == mRedSize && k == mGreenSize && i1 == mBlueSize && j1 == mAlphaSize)
                    return eglconfig;
            }

            return null;
        }

        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int mValue[];
        final GLSurfaceView this$0;

        public ComponentSizeChooser(int i, int j, int k, int l, int i1, int j1)
        {
            this$0 = GLSurfaceView.this;
            super(new int[] {
                12324, i, 12323, j, 12322, k, 12321, l, 12325, i1, 
                12326, j1, 12344
            });
            mValue = new int[1];
            mRedSize = i;
            mGreenSize = j;
            mBlueSize = k;
            mAlphaSize = l;
            mDepthSize = i1;
            mStencilSize = j1;
        }
    }

    private class DefaultContextFactory
        implements EGLContextFactory
    {

        public EGLContext createContext(EGL10 egl10, EGLDisplay egldisplay, EGLConfig eglconfig)
        {
            int ai[] = new int[3];
            ai[0] = EGL_CONTEXT_CLIENT_VERSION;
            ai[1] = GLSurfaceView._2D_get2(GLSurfaceView.this);
            ai[2] = 12344;
            EGLContext eglcontext = EGL10.EGL_NO_CONTEXT;
            if(GLSurfaceView._2D_get2(GLSurfaceView.this) == 0)
                ai = null;
            return egl10.eglCreateContext(egldisplay, eglconfig, eglcontext, ai);
        }

        public void destroyContext(EGL10 egl10, EGLDisplay egldisplay, EGLContext eglcontext)
        {
            if(!egl10.eglDestroyContext(egldisplay, eglcontext))
            {
                Log.e("DefaultContextFactory", (new StringBuilder()).append("display:").append(egldisplay).append(" context: ").append(eglcontext).toString());
                EglHelper.throwEglException("eglDestroyContex", egl10.eglGetError());
            }
        }

        private int EGL_CONTEXT_CLIENT_VERSION;
        final GLSurfaceView this$0;

        private DefaultContextFactory()
        {
            this$0 = GLSurfaceView.this;
            super();
            EGL_CONTEXT_CLIENT_VERSION = 12440;
        }

        DefaultContextFactory(DefaultContextFactory defaultcontextfactory)
        {
            this();
        }
    }

    private static class DefaultWindowSurfaceFactory
        implements EGLWindowSurfaceFactory
    {

        public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay egldisplay, EGLConfig eglconfig, Object obj)
        {
            Object obj1 = null;
            try
            {
                egl10 = egl10.eglCreateWindowSurface(egldisplay, eglconfig, obj, null);
            }
            // Misplaced declaration of an exception variable
            catch(EGL10 egl10)
            {
                Log.e("GLSurfaceView", "eglCreateWindowSurface", egl10);
                egl10 = obj1;
            }
            return egl10;
        }

        public void destroySurface(EGL10 egl10, EGLDisplay egldisplay, EGLSurface eglsurface)
        {
            egl10.eglDestroySurface(egldisplay, eglsurface);
        }

        private DefaultWindowSurfaceFactory()
        {
        }

        DefaultWindowSurfaceFactory(DefaultWindowSurfaceFactory defaultwindowsurfacefactory)
        {
            this();
        }
    }

    public static interface EGLConfigChooser
    {

        public abstract EGLConfig chooseConfig(EGL10 egl10, EGLDisplay egldisplay);
    }

    public static interface EGLContextFactory
    {

        public abstract EGLContext createContext(EGL10 egl10, EGLDisplay egldisplay, EGLConfig eglconfig);

        public abstract void destroyContext(EGL10 egl10, EGLDisplay egldisplay, EGLContext eglcontext);
    }

    public static interface EGLWindowSurfaceFactory
    {

        public abstract EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay egldisplay, EGLConfig eglconfig, Object obj);

        public abstract void destroySurface(EGL10 egl10, EGLDisplay egldisplay, EGLSurface eglsurface);
    }

    private static class EglHelper
    {

        private void destroySurfaceImp()
        {
            if(mEglSurface != null && mEglSurface != EGL10.EGL_NO_SURFACE)
            {
                mEgl.eglMakeCurrent(mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                GLSurfaceView glsurfaceview = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
                if(glsurfaceview != null)
                    GLSurfaceView._2D_get4(glsurfaceview).destroySurface(mEgl, mEglDisplay, mEglSurface);
                mEglSurface = null;
            }
        }

        public static String formatEglError(String s, int i)
        {
            return (new StringBuilder()).append(s).append(" failed: ").append(EGLLogWrapper.getErrorString(i)).toString();
        }

        public static void logEglErrorAsWarning(String s, String s1, int i)
        {
            Log.w(s, formatEglError(s1, i));
        }

        private void throwEglException(String s)
        {
            throwEglException(s, mEgl.eglGetError());
        }

        public static void throwEglException(String s, int i)
        {
            throw new RuntimeException(formatEglError(s, i));
        }

        GL createGL()
        {
            GL gl = mEglContext.getGL();
            GLSurfaceView glsurfaceview = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
            Object obj = gl;
            if(glsurfaceview != null)
            {
                GL gl1 = gl;
                if(GLSurfaceView._2D_get5(glsurfaceview) != null)
                    gl1 = GLSurfaceView._2D_get5(glsurfaceview).wrap(gl);
                obj = gl1;
                if((GLSurfaceView._2D_get0(glsurfaceview) & 3) != 0)
                {
                    int i = 0;
                    obj = null;
                    if((GLSurfaceView._2D_get0(glsurfaceview) & 1) != 0)
                        i = 1;
                    if((GLSurfaceView._2D_get0(glsurfaceview) & 2) != 0)
                        obj = new LogWriter();
                    obj = GLDebugHelper.wrap(gl1, i, ((Writer) (obj)));
                }
            }
            return ((GL) (obj));
        }

        public boolean createSurface()
        {
            if(mEgl == null)
                throw new RuntimeException("egl not initialized");
            if(mEglDisplay == null)
                throw new RuntimeException("eglDisplay not initialized");
            if(mEglConfig == null)
                throw new RuntimeException("mEglConfig not initialized");
            destroySurfaceImp();
            GLSurfaceView glsurfaceview = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
            if(glsurfaceview != null)
                mEglSurface = GLSurfaceView._2D_get4(glsurfaceview).createWindowSurface(mEgl, mEglDisplay, mEglConfig, glsurfaceview.getHolder());
            else
                mEglSurface = null;
            if(mEglSurface == null || mEglSurface == EGL10.EGL_NO_SURFACE)
            {
                if(mEgl.eglGetError() == 12299)
                    Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                return false;
            }
            if(!mEgl.eglMakeCurrent(mEglDisplay, mEglSurface, mEglSurface, mEglContext))
            {
                logEglErrorAsWarning("EGLHelper", "eglMakeCurrent", mEgl.eglGetError());
                return false;
            } else
            {
                return true;
            }
        }

        public void destroySurface()
        {
            destroySurfaceImp();
        }

        public void finish()
        {
            if(mEglContext != null)
            {
                GLSurfaceView glsurfaceview = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
                if(glsurfaceview != null)
                    GLSurfaceView._2D_get3(glsurfaceview).destroyContext(mEgl, mEglDisplay, mEglContext);
                mEglContext = null;
            }
            if(mEglDisplay != null)
            {
                mEgl.eglTerminate(mEglDisplay);
                mEglDisplay = null;
            }
        }

        public void start()
        {
            mEgl = (EGL10)EGLContext.getEGL();
            mEglDisplay = mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if(mEglDisplay == EGL10.EGL_NO_DISPLAY)
                throw new RuntimeException("eglGetDisplay failed");
            int ai[] = new int[2];
            if(!mEgl.eglInitialize(mEglDisplay, ai))
                throw new RuntimeException("eglInitialize failed");
            GLSurfaceView glsurfaceview = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
            if(glsurfaceview == null)
            {
                mEglConfig = null;
                mEglContext = null;
            } else
            {
                mEglConfig = GLSurfaceView._2D_get1(glsurfaceview).chooseConfig(mEgl, mEglDisplay);
                mEglContext = GLSurfaceView._2D_get3(glsurfaceview).createContext(mEgl, mEglDisplay, mEglConfig);
            }
            if(mEglContext == null || mEglContext == EGL10.EGL_NO_CONTEXT)
            {
                mEglContext = null;
                throwEglException("createContext");
            }
            mEglSurface = null;
        }

        public int swap()
        {
            if(!mEgl.eglSwapBuffers(mEglDisplay, mEglSurface))
                return mEgl.eglGetError();
            else
                return 12288;
        }

        EGL10 mEgl;
        EGLConfig mEglConfig;
        EGLContext mEglContext;
        EGLDisplay mEglDisplay;
        EGLSurface mEglSurface;
        private WeakReference mGLSurfaceViewWeakRef;

        public EglHelper(WeakReference weakreference)
        {
            mGLSurfaceViewWeakRef = weakreference;
        }
    }

    static class GLThread extends Thread
    {

        static boolean _2D_set0(GLThread glthread, boolean flag)
        {
            glthread.mExited = flag;
            return flag;
        }

        private void guardedRun()
            throws InterruptedException
        {
            GL10 gl10;
            int i;
            int j;
            int k;
            int l;
            int i1;
            boolean flag;
            int j1;
            int k1;
            int l1;
            int i2;
            Object obj;
            Object obj1;
            mEglHelper = new EglHelper(mGLSurfaceViewWeakRef);
            mHaveEglContext = false;
            mHaveEglSurface = false;
            mWantRenderNotification = false;
            gl10 = null;
            i = 0;
            j = 0;
            k = 0;
            l = 0;
            i1 = 0;
            flag = false;
            j1 = 0;
            k1 = 0;
            l1 = 0;
            i2 = 0;
            obj = null;
            obj1 = null;
_L28:
            Object obj2 = GLSurfaceView._2D_get8();
            obj2;
            JVM INSTR monitorenter ;
            int j2;
            int k2;
            j2 = l;
            k2 = j1;
            j1 = k1;
_L17:
            boolean flag1 = mShouldExit;
            if(!flag1)
                break MISSING_BLOCK_LABEL_128;
            obj2;
            JVM INSTR monitorexit ;
            Object obj3 = GLSurfaceView._2D_get8();
            obj3;
            JVM INSTR monitorenter ;
            stopEglSurfaceLocked();
            stopEglContextLocked();
            obj3;
            JVM INSTR monitorexit ;
            return;
            obj1;
            throw obj1;
            if(mEventQueue.isEmpty()) goto _L2; else goto _L1
_L1:
            Runnable runnable = (Runnable)mEventQueue.remove(0);
            boolean flag2;
            int l2;
            int i3;
            int j3;
            flag2 = flag;
            l2 = l1;
            l = j2;
            i3 = i2;
            l1 = k2;
            k2 = j;
            j = i;
            j3 = j1;
_L14:
            obj2;
            JVM INSTR monitorexit ;
            if(runnable == null)
                break; /* Loop/switch isn't completed */
            runnable.run();
            obj = null;
            k1 = j3;
            i = j;
            j = k2;
            j1 = l1;
            i2 = i3;
            l1 = l2;
            flag = flag2;
            continue; /* Loop/switch isn't completed */
_L2:
            flag1 = false;
            if(mPaused != mRequestPaused)
            {
                flag1 = mRequestPaused;
                mPaused = mRequestPaused;
                GLSurfaceView._2D_get8().notifyAll();
            }
            l = j1;
            if(!mShouldReleaseEglContext)
                break MISSING_BLOCK_LABEL_287;
            stopEglSurfaceLocked();
            stopEglContextLocked();
            mShouldReleaseEglContext = false;
            l = 1;
            int k3;
            k3 = j2;
            if(j2 == 0)
                break MISSING_BLOCK_LABEL_307;
            stopEglSurfaceLocked();
            stopEglContextLocked();
            k3 = 0;
            if(!flag1)
                break MISSING_BLOCK_LABEL_323;
            if(mHaveEglSurface)
                stopEglSurfaceLocked();
            if(!flag1) goto _L4; else goto _L3
_L3:
            if(!mHaveEglContext) goto _L4; else goto _L5
_L5:
            obj3 = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
            if(obj3 != null) goto _L7; else goto _L6
_L6:
            flag1 = false;
_L15:
            if(flag1) goto _L4; else goto _L8
_L8:
            stopEglContextLocked();
_L4:
            if(!mHasSurface && mWaitingForSurface ^ true)
            {
                if(mHaveEglSurface)
                    stopEglSurfaceLocked();
                mWaitingForSurface = true;
                mSurfaceIsBad = false;
                GLSurfaceView._2D_get8().notifyAll();
            }
            if(mHasSurface && mWaitingForSurface)
            {
                mWaitingForSurface = false;
                GLSurfaceView._2D_get8().notifyAll();
            }
            j2 = k2;
            if(k2 == 0)
                break MISSING_BLOCK_LABEL_460;
            mWantRenderNotification = false;
            j2 = 0;
            mRenderComplete = true;
            GLSurfaceView._2D_get8().notifyAll();
            obj3 = obj1;
            if(mFinishDrawingRunnable != null)
            {
                obj3 = mFinishDrawingRunnable;
                mFinishDrawingRunnable = null;
            }
            if(!readyToDraw())
                break MISSING_BLOCK_LABEL_834;
            k1 = l;
            j1 = i;
            if(mHaveEglContext) goto _L10; else goto _L9
_L9:
            if(l == 0) goto _L12; else goto _L11
_L11:
            k1 = 0;
            j1 = i;
_L10:
            k2 = j;
            i = k;
            l = i1;
            if(!mHaveEglContext)
                break MISSING_BLOCK_LABEL_563;
            k2 = j;
            i = k;
            l = i1;
            if(!(mHaveEglSurface ^ true))
                break MISSING_BLOCK_LABEL_563;
            mHaveEglSurface = true;
            k2 = 1;
            i = 1;
            l = 1;
            int l3;
            int i4;
            int j4;
            j3 = k1;
            l2 = j1;
            l3 = k2;
            i4 = i;
            obj1 = obj3;
            j4 = l;
            if(!mHaveEglSurface)
                break MISSING_BLOCK_LABEL_897;
            j4 = k2;
            l3 = l;
            i4 = l1;
            if(!mSizeChanged)
                break MISSING_BLOCK_LABEL_640;
            l3 = 1;
            i4 = mWidth;
            i2 = mHeight;
            mWantRenderNotification = true;
            j4 = 1;
            mSizeChanged = false;
            mRequestRender = false;
            GLSurfaceView._2D_get8().notifyAll();
            j3 = k1;
            j = j1;
            k2 = j4;
            k = i;
            l1 = j2;
            runnable = ((Runnable) (obj));
            obj1 = obj3;
            i3 = i2;
            l = k3;
            i1 = l3;
            l2 = i4;
            flag2 = flag;
            if(!mWantRenderNotification) goto _L14; else goto _L13
_L13:
            flag2 = true;
            j3 = k1;
            j = j1;
            k2 = j4;
            k = i;
            l1 = j2;
            runnable = ((Runnable) (obj));
            obj1 = obj3;
            i3 = i2;
            l = k3;
            i1 = l3;
            l2 = i4;
              goto _L14
_L7:
            flag1 = GLSurfaceView._2D_get6(((GLSurfaceView) (obj3)));
              goto _L15
_L12:
            mEglHelper.start();
            mHaveEglContext = true;
            j1 = 1;
            GLSurfaceView._2D_get8().notifyAll();
            k1 = l;
              goto _L10
            obj1;
            obj2;
            JVM INSTR monitorexit ;
            throw obj1;
            obj3;
            obj1 = GLSurfaceView._2D_get8();
            obj1;
            JVM INSTR monitorenter ;
            stopEglSurfaceLocked();
            stopEglContextLocked();
            obj1;
            JVM INSTR monitorexit ;
            throw obj3;
            obj1;
            GLSurfaceView._2D_get8().releaseEglContextLocked(this);
            throw obj1;
            j3 = l;
            l2 = i;
            l3 = j;
            i4 = k;
            obj1 = obj3;
            j4 = i1;
            if(obj3 == null)
                break MISSING_BLOCK_LABEL_897;
            Log.w("GLSurfaceView", "Warning, !readyToDraw() but waiting for draw finished! Early reporting draw finished.");
            ((Runnable) (obj3)).run();
            obj1 = null;
            j4 = i1;
            i4 = k;
            l3 = j;
            l2 = i;
            j3 = l;
            GLSurfaceView._2D_get8().wait();
            j1 = j3;
            i = l2;
            j = l3;
            k = i4;
            k2 = j2;
            j2 = k3;
            i1 = j4;
            if(true) goto _L17; else goto _L16
_L16:
            k3 = k2;
            if(k2 == 0) goto _L19; else goto _L18
_L18:
            if(!mEglHelper.createSurface()) goto _L21; else goto _L20
_L20:
            obj3 = GLSurfaceView._2D_get8();
            obj3;
            JVM INSTR monitorenter ;
            mFinishedCreatingEglSurface = true;
            GLSurfaceView._2D_get8().notifyAll();
            obj3;
            JVM INSTR monitorexit ;
            k3 = 0;
_L19:
            k2 = k;
            obj2 = gl10;
            if(k == 0)
                break MISSING_BLOCK_LABEL_1003;
            obj2 = (GL10)mEglHelper.createGL();
            k2 = 0;
            j2 = j;
            if(j == 0)
                break MISSING_BLOCK_LABEL_1063;
            obj3 = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
            if(obj3 == null)
                break MISSING_BLOCK_LABEL_1060;
            Trace.traceBegin(8L, "onSurfaceCreated");
            GLSurfaceView._2D_get7(((GLSurfaceView) (obj3))).onSurfaceCreated(((GL10) (obj2)), mEglHelper.mEglConfig);
            Trace.traceEnd(8L);
            j2 = 0;
            j4 = i1;
            if(i1 == 0)
                break MISSING_BLOCK_LABEL_1122;
            obj3 = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
            if(obj3 == null)
                break MISSING_BLOCK_LABEL_1119;
            Trace.traceBegin(8L, "onSurfaceChanged");
            GLSurfaceView._2D_get7(((GLSurfaceView) (obj3))).onSurfaceChanged(((GL10) (obj2)), l2, i3);
            Trace.traceEnd(8L);
            j4 = 0;
            obj = (GLSurfaceView)mGLSurfaceViewWeakRef.get();
            obj3 = obj1;
            if(obj == null)
                break MISSING_BLOCK_LABEL_1206;
            Trace.traceBegin(8L, "onDrawFrame");
            if(mFinishDrawingRunnable != null)
            {
                obj1 = mFinishDrawingRunnable;
                mFinishDrawingRunnable = null;
            }
            GLSurfaceView._2D_get7(((GLSurfaceView) (obj))).onDrawFrame(((GL10) (obj2)));
            obj3 = obj1;
            if(obj1 == null)
                break MISSING_BLOCK_LABEL_1200;
            ((Runnable) (obj1)).run();
            obj3 = null;
            Trace.traceEnd(8L);
            j1 = mEglHelper.swap();
            l3 = l;
            j1;
            JVM INSTR lookupswitch 2: default 1248
        //                       12288: 1283
        //                       12302: 1495;
               goto _L22 _L23 _L24
_L23:
            break MISSING_BLOCK_LABEL_1283;
_L22:
            EglHelper.logEglErrorAsWarning("GLThread", "eglSwapBuffers", j1);
            obj1 = GLSurfaceView._2D_get8();
            obj1;
            JVM INSTR monitorenter ;
            mSurfaceIsBad = true;
            GLSurfaceView._2D_get8().notifyAll();
            obj1;
            JVM INSTR monitorexit ;
            l3 = l;
_L26:
            k1 = j3;
            i = j2;
            j = k3;
            k = k2;
            j1 = l1;
            obj = runnable;
            obj1 = obj3;
            gl10 = ((GL10) (obj2));
            i2 = i3;
            l = l3;
            i1 = j4;
            l1 = l2;
            flag = flag2;
            if(flag2)
            {
                j1 = 1;
                flag = false;
                k1 = j3;
                i = j2;
                j = k3;
                k = k2;
                obj = runnable;
                obj1 = obj3;
                gl10 = ((GL10) (obj2));
                i2 = i3;
                l = l3;
                i1 = j4;
                l1 = l2;
            }
            continue; /* Loop/switch isn't completed */
            obj1;
            obj3;
            JVM INSTR monitorexit ;
            throw obj1;
_L21:
            obj3 = GLSurfaceView._2D_get8();
            obj3;
            JVM INSTR monitorenter ;
            mFinishedCreatingEglSurface = true;
            mSurfaceIsBad = true;
            GLSurfaceView._2D_get8().notifyAll();
            obj3;
            JVM INSTR monitorexit ;
            k1 = j3;
            i = j;
            j = k2;
            j1 = l1;
            obj = runnable;
            i2 = i3;
            l1 = l2;
            flag = flag2;
            continue; /* Loop/switch isn't completed */
            obj1;
            obj3;
            JVM INSTR monitorexit ;
            throw obj1;
            obj1;
            Trace.traceEnd(8L);
            throw obj1;
            obj1;
            Trace.traceEnd(8L);
            throw obj1;
            obj1;
            Trace.traceEnd(8L);
            throw obj1;
_L24:
            l3 = 1;
            if(true) goto _L26; else goto _L25
_L25:
            Exception exception;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
            exception;
            obj1;
            JVM INSTR monitorexit ;
            throw exception;
            if(true) goto _L28; else goto _L27
_L27:
        }

        private boolean readyToDraw()
        {
            boolean flag = true;
            boolean flag1;
            if(!mPaused && mHasSurface && mSurfaceIsBad ^ true && mWidth > 0 && mHeight > 0)
            {
                flag1 = flag;
                if(!mRequestRender)
                    if(mRenderMode == 1)
                        flag1 = flag;
                    else
                        flag1 = false;
            } else
            {
                flag1 = false;
            }
            return flag1;
        }

        private void stopEglContextLocked()
        {
            if(mHaveEglContext)
            {
                mEglHelper.finish();
                mHaveEglContext = false;
                GLSurfaceView._2D_get8().releaseEglContextLocked(this);
            }
        }

        private void stopEglSurfaceLocked()
        {
            if(mHaveEglSurface)
            {
                mHaveEglSurface = false;
                mEglHelper.destroySurface();
            }
        }

        public boolean ableToDraw()
        {
            boolean flag;
            if(mHaveEglContext && mHaveEglSurface)
                flag = readyToDraw();
            else
                flag = false;
            return flag;
        }

        public int getRenderMode()
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            int i = mRenderMode;
            glthreadmanager;
            JVM INSTR monitorexit ;
            return i;
            Exception exception;
            exception;
            throw exception;
        }

        public void onPause()
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            mRequestPaused = true;
            GLSurfaceView._2D_get8().notifyAll();
_L1:
            boolean flag;
            if(mExited)
                break MISSING_BLOCK_LABEL_59;
            flag = mPaused;
            if(!(flag ^ true))
                break MISSING_BLOCK_LABEL_59;
            GLSurfaceView._2D_get8().wait();
              goto _L1
            Object obj;
            obj;
            Thread.currentThread().interrupt();
              goto _L1
            obj;
            throw obj;
            glthreadmanager;
            JVM INSTR monitorexit ;
        }

        public void onResume()
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            mRequestPaused = false;
            mRequestRender = true;
            mRenderComplete = false;
            GLSurfaceView._2D_get8().notifyAll();
_L1:
            boolean flag;
            if(mExited || !mPaused)
                break MISSING_BLOCK_LABEL_76;
            flag = mRenderComplete;
            if(!(flag ^ true))
                break MISSING_BLOCK_LABEL_76;
            GLSurfaceView._2D_get8().wait();
              goto _L1
            Object obj;
            obj;
            Thread.currentThread().interrupt();
              goto _L1
            obj;
            throw obj;
            glthreadmanager;
            JVM INSTR monitorexit ;
        }

        public void onWindowResize(int i, int j)
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            Thread thread;
            mWidth = i;
            mHeight = j;
            mSizeChanged = true;
            mRequestRender = true;
            mRenderComplete = false;
            thread = Thread.currentThread();
            if(thread != this)
                break MISSING_BLOCK_LABEL_45;
            glthreadmanager;
            JVM INSTR monitorexit ;
            return;
            GLSurfaceView._2D_get8().notifyAll();
_L1:
            boolean flag;
            if(mExited || !(mPaused ^ true) || !(mRenderComplete ^ true))
                break MISSING_BLOCK_LABEL_114;
            flag = ableToDraw();
            if(!flag)
                break MISSING_BLOCK_LABEL_114;
            GLSurfaceView._2D_get8().wait();
              goto _L1
            Object obj;
            obj;
            Thread.currentThread().interrupt();
              goto _L1
            obj;
            throw obj;
            glthreadmanager;
            JVM INSTR monitorexit ;
        }

        public void queueEvent(Runnable runnable)
        {
            if(runnable == null)
                throw new IllegalArgumentException("r must not be null");
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            mEventQueue.add(runnable);
            GLSurfaceView._2D_get8().notifyAll();
            glthreadmanager;
            JVM INSTR monitorexit ;
            return;
            runnable;
            throw runnable;
        }

        public void requestExitAndWait()
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            mShouldExit = true;
            GLSurfaceView._2D_get8().notifyAll();
_L1:
            boolean flag = mExited;
            if(flag)
                break MISSING_BLOCK_LABEL_50;
            GLSurfaceView._2D_get8().wait();
              goto _L1
            Object obj;
            obj;
            Thread.currentThread().interrupt();
              goto _L1
            obj;
            throw obj;
            glthreadmanager;
            JVM INSTR monitorexit ;
        }

        public void requestReleaseEglContextLocked()
        {
            mShouldReleaseEglContext = true;
            GLSurfaceView._2D_get8().notifyAll();
        }

        public void requestRender()
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            mRequestRender = true;
            GLSurfaceView._2D_get8().notifyAll();
            glthreadmanager;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void requestRenderAndNotify(Runnable runnable)
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            Thread thread = Thread.currentThread();
            if(thread != this)
                break MISSING_BLOCK_LABEL_18;
            glthreadmanager;
            JVM INSTR monitorexit ;
            return;
            mWantRenderNotification = true;
            mRequestRender = true;
            mRenderComplete = false;
            mFinishDrawingRunnable = runnable;
            GLSurfaceView._2D_get8().notifyAll();
            glthreadmanager;
            JVM INSTR monitorexit ;
            return;
            runnable;
            throw runnable;
        }

        public void run()
        {
            setName((new StringBuilder()).append("GLThread ").append(getId()).toString());
            guardedRun();
            GLSurfaceView._2D_get8().threadExiting(this);
_L2:
            return;
            Object obj;
            obj;
            GLSurfaceView._2D_get8().threadExiting(this);
            if(true) goto _L2; else goto _L1
_L1:
            obj;
            GLSurfaceView._2D_get8().threadExiting(this);
            throw obj;
        }

        public void setRenderMode(int i)
        {
            if(i < 0 || i > 1)
                throw new IllegalArgumentException("renderMode");
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            mRenderMode = i;
            GLSurfaceView._2D_get8().notifyAll();
            glthreadmanager;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void surfaceCreated()
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            mHasSurface = true;
            mFinishedCreatingEglSurface = false;
            GLSurfaceView._2D_get8().notifyAll();
_L1:
            boolean flag;
            if(!mWaitingForSurface || !(mFinishedCreatingEglSurface ^ true))
                break MISSING_BLOCK_LABEL_73;
            flag = mExited;
            if(!(flag ^ true))
                break MISSING_BLOCK_LABEL_73;
            GLSurfaceView._2D_get8().wait();
              goto _L1
            Object obj;
            obj;
            Thread.currentThread().interrupt();
              goto _L1
            obj;
            throw obj;
            glthreadmanager;
            JVM INSTR monitorexit ;
        }

        public void surfaceDestroyed()
        {
            GLThreadManager glthreadmanager = GLSurfaceView._2D_get8();
            glthreadmanager;
            JVM INSTR monitorenter ;
            mHasSurface = false;
            GLSurfaceView._2D_get8().notifyAll();
_L1:
            boolean flag;
            if(mWaitingForSurface)
                break MISSING_BLOCK_LABEL_59;
            flag = mExited;
            if(!(flag ^ true))
                break MISSING_BLOCK_LABEL_59;
            GLSurfaceView._2D_get8().wait();
              goto _L1
            Object obj;
            obj;
            Thread.currentThread().interrupt();
              goto _L1
            obj;
            throw obj;
            glthreadmanager;
            JVM INSTR monitorexit ;
        }

        private EglHelper mEglHelper;
        private ArrayList mEventQueue;
        private boolean mExited;
        private Runnable mFinishDrawingRunnable;
        private boolean mFinishedCreatingEglSurface;
        private WeakReference mGLSurfaceViewWeakRef;
        private boolean mHasSurface;
        private boolean mHaveEglContext;
        private boolean mHaveEglSurface;
        private int mHeight;
        private boolean mPaused;
        private boolean mRenderComplete;
        private int mRenderMode;
        private boolean mRequestPaused;
        private boolean mRequestRender;
        private boolean mShouldExit;
        private boolean mShouldReleaseEglContext;
        private boolean mSizeChanged;
        private boolean mSurfaceIsBad;
        private boolean mWaitingForSurface;
        private boolean mWantRenderNotification;
        private int mWidth;

        GLThread(WeakReference weakreference)
        {
            mEventQueue = new ArrayList();
            mSizeChanged = true;
            mFinishDrawingRunnable = null;
            mWidth = 0;
            mHeight = 0;
            mRequestRender = true;
            mRenderMode = 1;
            mWantRenderNotification = false;
            mGLSurfaceViewWeakRef = weakreference;
        }
    }

    private static class GLThreadManager
    {

        public void releaseEglContextLocked(GLThread glthread)
        {
            notifyAll();
        }

        public void threadExiting(GLThread glthread)
        {
            this;
            JVM INSTR monitorenter ;
            GLThread._2D_set0(glthread, true);
            notifyAll();
            this;
            JVM INSTR monitorexit ;
            return;
            glthread;
            throw glthread;
        }

        private static String TAG = "GLThreadManager";


        private GLThreadManager()
        {
        }

        GLThreadManager(GLThreadManager glthreadmanager)
        {
            this();
        }
    }

    public static interface GLWrapper
    {

        public abstract GL wrap(GL gl);
    }

    static class LogWriter extends Writer
    {

        private void flushBuilder()
        {
            if(mBuilder.length() > 0)
            {
                Log.v("GLSurfaceView", mBuilder.toString());
                mBuilder.delete(0, mBuilder.length());
            }
        }

        public void close()
        {
            flushBuilder();
        }

        public void flush()
        {
            flushBuilder();
        }

        public void write(char ac[], int i, int j)
        {
            int k = 0;
            while(k < j) 
            {
                char c = ac[i + k];
                if(c == '\n')
                    flushBuilder();
                else
                    mBuilder.append(c);
                k++;
            }
        }

        private StringBuilder mBuilder;

        LogWriter()
        {
            mBuilder = new StringBuilder();
        }
    }

    public static interface Renderer
    {

        public abstract void onDrawFrame(GL10 gl10);

        public abstract void onSurfaceChanged(GL10 gl10, int i, int j);

        public abstract void onSurfaceCreated(GL10 gl10, EGLConfig eglconfig);
    }

    private class SimpleEGLConfigChooser extends ComponentSizeChooser
    {

        final GLSurfaceView this$0;

        public SimpleEGLConfigChooser(boolean flag)
        {
            this$0 = GLSurfaceView.this;
            byte byte0;
            if(flag)
                byte0 = 16;
            else
                byte0 = 0;
            super(8, 8, 8, 0, byte0, 0);
        }
    }


    static int _2D_get0(GLSurfaceView glsurfaceview)
    {
        return glsurfaceview.mDebugFlags;
    }

    static EGLConfigChooser _2D_get1(GLSurfaceView glsurfaceview)
    {
        return glsurfaceview.mEGLConfigChooser;
    }

    static int _2D_get2(GLSurfaceView glsurfaceview)
    {
        return glsurfaceview.mEGLContextClientVersion;
    }

    static EGLContextFactory _2D_get3(GLSurfaceView glsurfaceview)
    {
        return glsurfaceview.mEGLContextFactory;
    }

    static EGLWindowSurfaceFactory _2D_get4(GLSurfaceView glsurfaceview)
    {
        return glsurfaceview.mEGLWindowSurfaceFactory;
    }

    static GLWrapper _2D_get5(GLSurfaceView glsurfaceview)
    {
        return glsurfaceview.mGLWrapper;
    }

    static boolean _2D_get6(GLSurfaceView glsurfaceview)
    {
        return glsurfaceview.mPreserveEGLContextOnPause;
    }

    static Renderer _2D_get7(GLSurfaceView glsurfaceview)
    {
        return glsurfaceview.mRenderer;
    }

    static GLThreadManager _2D_get8()
    {
        return sGLThreadManager;
    }

    public GLSurfaceView(Context context)
    {
        super(context);
        mThisWeakRef = new WeakReference(this);
        init();
    }

    public GLSurfaceView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mThisWeakRef = new WeakReference(this);
        init();
    }

    private void checkRenderThreadState()
    {
        if(mGLThread != null)
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        else
            return;
    }

    private void init()
    {
        getHolder().addCallback(this);
    }

    protected void finalize()
        throws Throwable
    {
        if(mGLThread != null)
            mGLThread.requestExitAndWait();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getDebugFlags()
    {
        return mDebugFlags;
    }

    public boolean getPreserveEGLContextOnPause()
    {
        return mPreserveEGLContextOnPause;
    }

    public int getRenderMode()
    {
        return mGLThread.getRenderMode();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mDetached && mRenderer != null)
        {
            int i = 1;
            if(mGLThread != null)
                i = mGLThread.getRenderMode();
            mGLThread = new GLThread(mThisWeakRef);
            if(i != 1)
                mGLThread.setRenderMode(i);
            mGLThread.start();
        }
        mDetached = false;
    }

    protected void onDetachedFromWindow()
    {
        if(mGLThread != null)
            mGLThread.requestExitAndWait();
        mDetached = true;
        super.onDetachedFromWindow();
    }

    public void onPause()
    {
        mGLThread.onPause();
    }

    public void onResume()
    {
        mGLThread.onResume();
    }

    public void queueEvent(Runnable runnable)
    {
        mGLThread.queueEvent(runnable);
    }

    public void requestRender()
    {
        mGLThread.requestRender();
    }

    public void setDebugFlags(int i)
    {
        mDebugFlags = i;
    }

    public void setEGLConfigChooser(int i, int j, int k, int l, int i1, int j1)
    {
        setEGLConfigChooser(((EGLConfigChooser) (new ComponentSizeChooser(i, j, k, l, i1, j1))));
    }

    public void setEGLConfigChooser(EGLConfigChooser eglconfigchooser)
    {
        checkRenderThreadState();
        mEGLConfigChooser = eglconfigchooser;
    }

    public void setEGLConfigChooser(boolean flag)
    {
        setEGLConfigChooser(((EGLConfigChooser) (new SimpleEGLConfigChooser(flag))));
    }

    public void setEGLContextClientVersion(int i)
    {
        checkRenderThreadState();
        mEGLContextClientVersion = i;
    }

    public void setEGLContextFactory(EGLContextFactory eglcontextfactory)
    {
        checkRenderThreadState();
        mEGLContextFactory = eglcontextfactory;
    }

    public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory eglwindowsurfacefactory)
    {
        checkRenderThreadState();
        mEGLWindowSurfaceFactory = eglwindowsurfacefactory;
    }

    public void setGLWrapper(GLWrapper glwrapper)
    {
        mGLWrapper = glwrapper;
    }

    public void setPreserveEGLContextOnPause(boolean flag)
    {
        mPreserveEGLContextOnPause = flag;
    }

    public void setRenderMode(int i)
    {
        mGLThread.setRenderMode(i);
    }

    public void setRenderer(Renderer renderer)
    {
        checkRenderThreadState();
        if(mEGLConfigChooser == null)
            mEGLConfigChooser = new SimpleEGLConfigChooser(true);
        if(mEGLContextFactory == null)
            mEGLContextFactory = new DefaultContextFactory(null);
        if(mEGLWindowSurfaceFactory == null)
            mEGLWindowSurfaceFactory = new DefaultWindowSurfaceFactory(null);
        mRenderer = renderer;
        mGLThread = new GLThread(mThisWeakRef);
        mGLThread.start();
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        mGLThread.onWindowResize(j, k);
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        mGLThread.surfaceCreated();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        mGLThread.surfaceDestroyed();
    }

    public void surfaceRedrawNeeded(SurfaceHolder surfaceholder)
    {
    }

    public void surfaceRedrawNeededAsync(SurfaceHolder surfaceholder, Runnable runnable)
    {
        if(mGLThread != null)
            mGLThread.requestRenderAndNotify(runnable);
    }

    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    private static final boolean LOG_ATTACH_DETACH = false;
    private static final boolean LOG_EGL = false;
    private static final boolean LOG_PAUSE_RESUME = false;
    private static final boolean LOG_RENDERER = false;
    private static final boolean LOG_RENDERER_DRAW_FRAME = false;
    private static final boolean LOG_SURFACE = false;
    private static final boolean LOG_THREADS = false;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;
    private static final String TAG = "GLSurfaceView";
    private static final GLThreadManager sGLThreadManager = new GLThreadManager(null);
    private int mDebugFlags;
    private boolean mDetached;
    private EGLConfigChooser mEGLConfigChooser;
    private int mEGLContextClientVersion;
    private EGLContextFactory mEGLContextFactory;
    private EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
    private GLThread mGLThread;
    private GLWrapper mGLWrapper;
    private boolean mPreserveEGLContextOnPause;
    private Renderer mRenderer;
    private final WeakReference mThisWeakRef;

}
