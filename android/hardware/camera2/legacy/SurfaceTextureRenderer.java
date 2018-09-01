// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Environment;
import android.os.SystemProperties;
import android.text.format.Time;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.hardware.camera2.legacy:
//            PerfMeasurement, LegacyCameraDevice, CaptureCollector, RequestHolder

public class SurfaceTextureRenderer
{
    private class EGLSurfaceHolder
    {

        EGLSurface eglSurface;
        int height;
        Surface surface;
        final SurfaceTextureRenderer this$0;
        int width;

        private EGLSurfaceHolder()
        {
            this$0 = SurfaceTextureRenderer.this;
            super();
        }

        EGLSurfaceHolder(EGLSurfaceHolder eglsurfaceholder)
        {
            this();
        }
    }


    public SurfaceTextureRenderer(int i)
    {
        mEGLDisplay = EGL14.EGL_NO_DISPLAY;
        mEGLContext = EGL14.EGL_NO_CONTEXT;
        mSurfaces = new ArrayList();
        mConversionSurfaces = new ArrayList();
        mMVPMatrix = new float[16];
        mSTMatrix = new float[16];
        mTextureID = 0;
        mPerfMeasurer = null;
        mFacing = i;
        mRegularTriangleVertices = ByteBuffer.allocateDirect(sRegularTriangleVertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mRegularTriangleVertices.put(sRegularTriangleVertices).position(0);
        mHorizontalFlipTriangleVertices = ByteBuffer.allocateDirect(sHorizontalFlipTriangleVertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mHorizontalFlipTriangleVertices.put(sHorizontalFlipTriangleVertices).position(0);
        mVerticalFlipTriangleVertices = ByteBuffer.allocateDirect(sVerticalFlipTriangleVertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVerticalFlipTriangleVertices.put(sVerticalFlipTriangleVertices).position(0);
        mBothFlipTriangleVertices = ByteBuffer.allocateDirect(sBothFlipTriangleVertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mBothFlipTriangleVertices.put(sBothFlipTriangleVertices).position(0);
        Matrix.setIdentityM(mSTMatrix, 0);
    }

    private void addGlTimestamp(long l)
    {
        if(mPerfMeasurer == null)
        {
            return;
        } else
        {
            mPerfMeasurer.addTimestamp(l);
            return;
        }
    }

    private void beginGlTiming()
    {
        if(mPerfMeasurer == null)
        {
            return;
        } else
        {
            mPerfMeasurer.startTimer();
            return;
        }
    }

    private void checkEglError(String s)
    {
        int i = EGL14.eglGetError();
        if(i != 12288)
            throw new IllegalStateException((new StringBuilder()).append(s).append(": EGL error: 0x").append(Integer.toHexString(i)).toString());
        else
            return;
    }

    private void checkGlDrawError(String s)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        boolean flag = false;
        boolean flag1 = false;
        int i;
        do
        {
            i = GLES20.glGetError();
            if(i == 0)
                break;
            if(i == 1285)
                flag = true;
            else
                flag1 = true;
        } while(true);
        if(flag1)
            throw new IllegalStateException((new StringBuilder()).append(s).append(": GLES20 error: 0x").append(Integer.toHexString(i)).toString());
        if(flag)
            throw new LegacyExceptionUtils.BufferQueueAbandonedException();
        else
            return;
    }

    private void checkGlError(String s)
    {
        int i = GLES20.glGetError();
        if(i != 0)
            throw new IllegalStateException((new StringBuilder()).append(s).append(": GLES20 error: 0x").append(Integer.toHexString(i)).toString());
        else
            return;
    }

    private void clearState()
    {
        mSurfaces.clear();
        for(Iterator iterator = mConversionSurfaces.iterator(); iterator.hasNext();)
        {
            EGLSurfaceHolder eglsurfaceholder = (EGLSurfaceHolder)iterator.next();
            try
            {
                LegacyCameraDevice.disconnectSurface(eglsurfaceholder.surface);
            }
            catch(LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception)
            {
                Log.w(TAG, "Surface abandoned, skipping...", bufferqueueabandonedexception);
            }
        }

        mConversionSurfaces.clear();
        mPBufferPixels = null;
        if(mSurfaceTexture != null)
            mSurfaceTexture.release();
        mSurfaceTexture = null;
    }

    private void configureEGLContext()
    {
        mEGLDisplay = EGL14.eglGetDisplay(0);
        if(mEGLDisplay == EGL14.EGL_NO_DISPLAY)
            throw new IllegalStateException("No EGL14 display");
        Object aobj[] = new int[2];
        if(!EGL14.eglInitialize(mEGLDisplay, ((int []) (aobj)), 0, ((int []) (aobj)), 1))
            throw new IllegalStateException("Cannot initialize EGL14");
        aobj = new EGLConfig[1];
        int ai[] = new int[1];
        EGLDisplay egldisplay = mEGLDisplay;
        int i = aobj.length;
        EGL14.eglChooseConfig(egldisplay, new int[] {
            12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 
            12339, 5, 12344
        }, 0, ((EGLConfig []) (aobj)), 0, i, ai, 0);
        checkEglError("eglCreateContext RGB888+recordable ES2");
        mConfigs = aobj[0];
        mEGLContext = EGL14.eglCreateContext(mEGLDisplay, aobj[0], EGL14.EGL_NO_CONTEXT, new int[] {
            12440, 2, 12344
        }, 0);
        checkEglError("eglCreateContext");
        if(mEGLContext == EGL14.EGL_NO_CONTEXT)
            throw new IllegalStateException("No EGLContext could be made");
        else
            return;
    }

    private void configureEGLOutputSurfaces(Collection collection)
    {
        if(collection == null || collection.size() == 0)
            throw new IllegalStateException("No Surfaces were provided to draw to");
        for(collection = collection.iterator(); collection.hasNext(); checkEglError("eglCreateWindowSurface"))
        {
            EGLSurfaceHolder eglsurfaceholder = (EGLSurfaceHolder)collection.next();
            eglsurfaceholder.eglSurface = EGL14.eglCreateWindowSurface(mEGLDisplay, mConfigs, eglsurfaceholder.surface, new int[] {
                12344
            }, 0);
        }

    }

    private void configureEGLPbufferSurfaces(Collection collection)
    {
        if(collection == null || collection.size() == 0)
            throw new IllegalStateException("No Surfaces were provided to draw to");
        int i = 0;
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            collection = (EGLSurfaceHolder)iterator.next();
            int j = ((EGLSurfaceHolder) (collection)).width * ((EGLSurfaceHolder) (collection)).height;
            int k = i;
            if(j > i)
                k = j;
            i = ((EGLSurfaceHolder) (collection)).width;
            j = ((EGLSurfaceHolder) (collection)).height;
            collection.eglSurface = EGL14.eglCreatePbufferSurface(mEGLDisplay, mConfigs, new int[] {
                12375, i, 12374, j, 12344
            }, 0);
            checkEglError("eglCreatePbufferSurface");
            i = k;
        }

        mPBufferPixels = ByteBuffer.allocateDirect(i * 4).order(ByteOrder.nativeOrder());
    }

    private int createProgram(String s, String s1)
    {
        int i = loadShader(35633, s);
        if(i == 0)
            return 0;
        int j = loadShader(35632, s1);
        if(j == 0)
            return 0;
        int k = GLES20.glCreateProgram();
        checkGlError("glCreateProgram");
        if(k == 0)
            Log.e(TAG, "Could not create program");
        GLES20.glAttachShader(k, i);
        checkGlError("glAttachShader");
        GLES20.glAttachShader(k, j);
        checkGlError("glAttachShader");
        GLES20.glLinkProgram(k);
        s = new int[1];
        GLES20.glGetProgramiv(k, 35714, s, 0);
        if(s[0] != 1)
        {
            Log.e(TAG, "Could not link program: ");
            Log.e(TAG, GLES20.glGetProgramInfoLog(k));
            GLES20.glDeleteProgram(k);
            throw new IllegalStateException("Could not link program");
        } else
        {
            return k;
        }
    }

    private void drawFrame(SurfaceTexture surfacetexture, int i, int j, int k)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        checkGlError("onDrawFrame start");
        surfacetexture.getTransformMatrix(mSTMatrix);
        Matrix.setIdentityM(mMVPMatrix, 0);
        float f;
        float f1;
        try
        {
            surfacetexture = LegacyCameraDevice.getTextureSize(surfacetexture);
        }
        // Misplaced declaration of an exception variable
        catch(SurfaceTexture surfacetexture)
        {
            throw new IllegalStateException("Surface abandoned, skipping drawFrame...", surfacetexture);
        }
        f = surfacetexture.getWidth();
        f1 = surfacetexture.getHeight();
        if(f <= 0.0F || f1 <= 0.0F)
            throw new IllegalStateException("Illegal intermediate texture with dimension of 0");
        surfacetexture = new RectF(0.0F, 0.0F, f, f1);
        RectF rectf = new RectF(0.0F, 0.0F, i, j);
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        matrix.setRectToRect(rectf, surfacetexture, android.graphics.Matrix.ScaleToFit.CENTER);
        matrix.mapRect(rectf);
        f = surfacetexture.width() / rectf.width();
        f1 = surfacetexture.height() / rectf.height();
        Matrix.scaleM(mMVPMatrix, 0, f, f1, 1.0F);
        GLES20.glViewport(0, 0, i, j);
        GLES20.glUseProgram(mProgram);
        checkGlError("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, mTextureID);
        k;
        JVM INSTR tableswitch 1 3: default 240
    //                   1 371
    //                   2 379
    //                   3 387;
           goto _L1 _L2 _L3 _L4
_L1:
        surfacetexture = mRegularTriangleVertices;
_L6:
        surfacetexture.position(0);
        GLES20.glVertexAttribPointer(maPositionHandle, 3, 5126, false, 20, surfacetexture);
        checkGlError("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(maPositionHandle);
        checkGlError("glEnableVertexAttribArray maPositionHandle");
        surfacetexture.position(3);
        GLES20.glVertexAttribPointer(maTextureHandle, 2, 5126, false, 20, surfacetexture);
        checkGlError("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(maTextureHandle);
        checkGlError("glEnableVertexAttribArray maTextureHandle");
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(muSTMatrixHandle, 1, false, mSTMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
        checkGlDrawError("glDrawArrays");
        return;
_L2:
        surfacetexture = mHorizontalFlipTriangleVertices;
        continue; /* Loop/switch isn't completed */
_L3:
        surfacetexture = mVerticalFlipTriangleVertices;
        continue; /* Loop/switch isn't completed */
_L4:
        surfacetexture = mBothFlipTriangleVertices;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void dumpGlTiming()
    {
        if(mPerfMeasurer == null)
            return;
        Object obj = new File(Environment.getExternalStorageDirectory(), "CameraLegacy");
        if(!((File) (obj)).exists() && !((File) (obj)).mkdirs())
        {
            Log.e(TAG, "Failed to create directory for data dump");
            return;
        }
        obj = new StringBuilder(((File) (obj)).getPath());
        ((StringBuilder) (obj)).append(File.separator);
        ((StringBuilder) (obj)).append("durations_");
        Time time = new Time();
        time.setToNow();
        ((StringBuilder) (obj)).append(time.format2445());
        ((StringBuilder) (obj)).append("_S");
        EGLSurfaceHolder eglsurfaceholder1;
        for(Iterator iterator = mSurfaces.iterator(); iterator.hasNext(); ((StringBuilder) (obj)).append(String.format("_%d_%d", new Object[] {
    Integer.valueOf(eglsurfaceholder1.width), Integer.valueOf(eglsurfaceholder1.height)
})))
            eglsurfaceholder1 = (EGLSurfaceHolder)iterator.next();

        ((StringBuilder) (obj)).append("_C");
        EGLSurfaceHolder eglsurfaceholder;
        for(Iterator iterator1 = mConversionSurfaces.iterator(); iterator1.hasNext(); ((StringBuilder) (obj)).append(String.format("_%d_%d", new Object[] {
    Integer.valueOf(eglsurfaceholder.width), Integer.valueOf(eglsurfaceholder.height)
})))
            eglsurfaceholder = (EGLSurfaceHolder)iterator1.next();

        ((StringBuilder) (obj)).append(".txt");
        mPerfMeasurer.dumpPerformanceData(((StringBuilder) (obj)).toString());
    }

    private void endGlTiming()
    {
        if(mPerfMeasurer == null)
        {
            return;
        } else
        {
            mPerfMeasurer.stopTimer();
            return;
        }
    }

    private int getTextureId()
    {
        return mTextureID;
    }

    private void initializeGLState()
    {
        mProgram = createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        if(mProgram == 0)
            throw new IllegalStateException("failed creating program");
        maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        checkGlError("glGetAttribLocation aPosition");
        if(maPositionHandle == -1)
            throw new IllegalStateException("Could not get attrib location for aPosition");
        maTextureHandle = GLES20.glGetAttribLocation(mProgram, "aTextureCoord");
        checkGlError("glGetAttribLocation aTextureCoord");
        if(maTextureHandle == -1)
            throw new IllegalStateException("Could not get attrib location for aTextureCoord");
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        checkGlError("glGetUniformLocation uMVPMatrix");
        if(muMVPMatrixHandle == -1)
            throw new IllegalStateException("Could not get attrib location for uMVPMatrix");
        muSTMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uSTMatrix");
        checkGlError("glGetUniformLocation uSTMatrix");
        if(muSTMatrixHandle == -1)
        {
            throw new IllegalStateException("Could not get attrib location for uSTMatrix");
        } else
        {
            int ai[] = new int[1];
            GLES20.glGenTextures(1, ai, 0);
            mTextureID = ai[0];
            GLES20.glBindTexture(36197, mTextureID);
            checkGlError("glBindTexture mTextureID");
            GLES20.glTexParameterf(36197, 10241, 9728F);
            GLES20.glTexParameterf(36197, 10240, 9729F);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            checkGlError("glTexParameter");
            return;
        }
    }

    private int loadShader(int i, String s)
    {
        int j = GLES20.glCreateShader(i);
        checkGlError((new StringBuilder()).append("glCreateShader type=").append(i).toString());
        GLES20.glShaderSource(j, s);
        GLES20.glCompileShader(j);
        s = new int[1];
        GLES20.glGetShaderiv(j, 35713, s, 0);
        if(s[0] == 0)
        {
            Log.e(TAG, (new StringBuilder()).append("Could not compile shader ").append(i).append(":").toString());
            Log.e(TAG, (new StringBuilder()).append(" ").append(GLES20.glGetShaderInfoLog(j)).toString());
            GLES20.glDeleteShader(j);
            throw new IllegalStateException((new StringBuilder()).append("Could not compile shader ").append(i).toString());
        } else
        {
            return j;
        }
    }

    private void makeCurrent(EGLSurface eglsurface)
    {
        EGL14.eglMakeCurrent(mEGLDisplay, eglsurface, eglsurface, mEGLContext);
        checkEglError("makeCurrent");
    }

    private void releaseEGLContext()
    {
        if(mEGLDisplay != EGL14.EGL_NO_DISPLAY)
        {
            EGL14.eglMakeCurrent(mEGLDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            dumpGlTiming();
            if(mSurfaces != null)
            {
                Iterator iterator = mSurfaces.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    EGLSurfaceHolder eglsurfaceholder = (EGLSurfaceHolder)iterator.next();
                    if(eglsurfaceholder.eglSurface != null)
                        EGL14.eglDestroySurface(mEGLDisplay, eglsurfaceholder.eglSurface);
                } while(true);
            }
            if(mConversionSurfaces != null)
            {
                Iterator iterator1 = mConversionSurfaces.iterator();
                do
                {
                    if(!iterator1.hasNext())
                        break;
                    EGLSurfaceHolder eglsurfaceholder1 = (EGLSurfaceHolder)iterator1.next();
                    if(eglsurfaceholder1.eglSurface != null)
                        EGL14.eglDestroySurface(mEGLDisplay, eglsurfaceholder1.eglSurface);
                } while(true);
            }
            EGL14.eglDestroyContext(mEGLDisplay, mEGLContext);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(mEGLDisplay);
        }
        mConfigs = null;
        mEGLDisplay = EGL14.EGL_NO_DISPLAY;
        mEGLContext = EGL14.EGL_NO_CONTEXT;
        clearState();
    }

    private void setupGlTiming()
    {
        if(PerfMeasurement.isGlTimingSupported())
        {
            Log.d(TAG, "Enabling GL performance measurement");
            mPerfMeasurer = new PerfMeasurement();
        } else
        {
            Log.d(TAG, "GL performance measurement not supported on this device");
            mPerfMeasurer = null;
        }
    }

    private boolean swapBuffers(EGLSurface eglsurface)
        throws LegacyExceptionUtils.BufferQueueAbandonedException
    {
        boolean flag = EGL14.eglSwapBuffers(mEGLDisplay, eglsurface);
        int i = EGL14.eglGetError();
        if(i == 12301)
            throw new LegacyExceptionUtils.BufferQueueAbandonedException();
        if(i != 12288)
            throw new IllegalStateException((new StringBuilder()).append("swapBuffers: EGL error: 0x").append(Integer.toHexString(i)).toString());
        else
            return flag;
    }

    public void cleanupEGLContext()
    {
        releaseEGLContext();
    }

    public void configureSurfaces(Collection collection)
    {
        releaseEGLContext();
        if(collection == null || collection.size() == 0)
        {
            Log.w(TAG, "No output surfaces configured for GL drawing.");
            return;
        }
        collection = collection.iterator();
_L2:
        Object obj;
        Surface surface;
        if(!collection.hasNext())
            break; /* Loop/switch isn't completed */
        obj = (Pair)collection.next();
        surface = (Surface)((Pair) (obj)).first;
        obj = (Size)((Pair) (obj)).second;
        EGLSurfaceHolder eglsurfaceholder = JVM INSTR new #6   <Class SurfaceTextureRenderer$EGLSurfaceHolder>;
        eglsurfaceholder.this. EGLSurfaceHolder(null);
        eglsurfaceholder.surface = surface;
        eglsurfaceholder.width = ((Size) (obj)).getWidth();
        eglsurfaceholder.height = ((Size) (obj)).getHeight();
        LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception;
        if(LegacyCameraDevice.needsConversion(surface))
        {
            mConversionSurfaces.add(eglsurfaceholder);
            LegacyCameraDevice.connectSurface(surface);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            mSurfaces.add(eglsurfaceholder);
        }
        // Misplaced declaration of an exception variable
        catch(LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception)
        {
            Log.w(TAG, "Surface abandoned, skipping configuration... ", bufferqueueabandonedexception);
        }
        if(true) goto _L2; else goto _L1
_L1:
        configureEGLContext();
        if(mSurfaces.size() > 0)
            configureEGLOutputSurfaces(mSurfaces);
        if(mConversionSurfaces.size() > 0)
            configureEGLPbufferSurfaces(mConversionSurfaces);
        if(mSurfaces.size() > 0)
            collection = ((EGLSurfaceHolder)mSurfaces.get(0)).eglSurface;
        else
            collection = ((EGLSurfaceHolder)mConversionSurfaces.get(0)).eglSurface;
        makeCurrent(collection);
        initializeGLState();
        mSurfaceTexture = new SurfaceTexture(getTextureId());
        if(SystemProperties.getBoolean("persist.camera.legacy_perf", false))
            setupGlTiming();
        return;
    }

    public void drawIntoSurfaces(CaptureCollector capturecollector)
    {
        boolean flag;
        Pair pair;
        RequestHolder requestholder;
        Object obj;
        Object obj1;
        if((mSurfaces == null || mSurfaces.size() == 0) && (mConversionSurfaces == null || mConversionSurfaces.size() == 0))
            return;
        flag = capturecollector.hasPendingPreviewCaptures();
        checkGlError("before updateTexImage");
        if(flag)
            beginGlTiming();
        mSurfaceTexture.updateTexImage();
        long l = mSurfaceTexture.getTimestamp();
        pair = capturecollector.previewCaptured(l);
        if(pair == null)
        {
            if(flag)
                endGlTiming();
            return;
        }
        requestholder = (RequestHolder)pair.first;
        obj = requestholder.getHolderTargets();
        if(flag)
            addGlTimestamp(l);
        obj1 = new ArrayList();
        obj = LegacyCameraDevice.getSurfaceIds(((Collection) (obj)));
        obj1 = obj;
_L3:
        obj = mSurfaces.iterator();
_L2:
        Object obj2;
        if(!((Iterator) (obj)).hasNext())
            break; /* Loop/switch isn't completed */
        obj2 = (EGLSurfaceHolder)((Iterator) (obj)).next();
        if(!LegacyCameraDevice.containsSurfaceId(((EGLSurfaceHolder) (obj2)).surface, ((Collection) (obj1))))
            continue; /* Loop/switch isn't completed */
        Object obj3;
        int i;
        int j;
        LegacyCameraDevice.setSurfaceDimens(((EGLSurfaceHolder) (obj2)).surface, ((EGLSurfaceHolder) (obj2)).width, ((EGLSurfaceHolder) (obj2)).height);
        makeCurrent(((EGLSurfaceHolder) (obj2)).eglSurface);
        LegacyCameraDevice.setNextTimestamp(((EGLSurfaceHolder) (obj2)).surface, ((Long)pair.second).longValue());
        obj3 = mSurfaceTexture;
        i = ((EGLSurfaceHolder) (obj2)).width;
        j = ((EGLSurfaceHolder) (obj2)).height;
        LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception;
        int k;
        if(mFacing == 0)
            k = 1;
        else
            k = 0;
        try
        {
            drawFrame(((SurfaceTexture) (obj3)), i, j, k);
            swapBuffers(((EGLSurfaceHolder) (obj2)).eglSurface);
        }
        catch(LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception1)
        {
            Log.w(TAG, "Surface abandoned, dropping frame. ", bufferqueueabandonedexception1);
            requestholder.setOutputAbandoned();
        }
        if(true) goto _L2; else goto _L1
        bufferqueueabandonedexception;
        Log.w(TAG, "Surface abandoned, dropping frame. ", bufferqueueabandonedexception);
        requestholder.setOutputAbandoned();
        if(true) goto _L3; else goto _L1
_L1:
        bufferqueueabandonedexception = mConversionSurfaces.iterator();
_L5:
        if(!bufferqueueabandonedexception.hasNext())
            break; /* Loop/switch isn't completed */
        bufferqueueabandonedexception1 = (EGLSurfaceHolder)bufferqueueabandonedexception.next();
        if(!LegacyCameraDevice.containsSurfaceId(((EGLSurfaceHolder) (bufferqueueabandonedexception1)).surface, ((Collection) (obj1))))
            continue; /* Loop/switch isn't completed */
        makeCurrent(((EGLSurfaceHolder) (bufferqueueabandonedexception1)).eglSurface);
        obj2 = mSurfaceTexture;
        i = ((EGLSurfaceHolder) (bufferqueueabandonedexception1)).width;
        j = ((EGLSurfaceHolder) (bufferqueueabandonedexception1)).height;
        int i1;
        if(mFacing == 0)
            i1 = 3;
        else
            i1 = 2;
        try
        {
            drawFrame(((SurfaceTexture) (obj2)), i, j, i1);
        }
        // Misplaced declaration of an exception variable
        catch(CaptureCollector capturecollector)
        {
            throw new IllegalStateException("Surface abandoned, skipping drawFrame...", capturecollector);
        }
        mPBufferPixels.clear();
        GLES20.glReadPixels(0, 0, ((EGLSurfaceHolder) (bufferqueueabandonedexception1)).width, ((EGLSurfaceHolder) (bufferqueueabandonedexception1)).height, 6408, 5121, mPBufferPixels);
        checkGlError("glReadPixels");
        try
        {
            i1 = LegacyCameraDevice.detectSurfaceType(((EGLSurfaceHolder) (bufferqueueabandonedexception1)).surface);
            LegacyCameraDevice.setSurfaceDimens(((EGLSurfaceHolder) (bufferqueueabandonedexception1)).surface, ((EGLSurfaceHolder) (bufferqueueabandonedexception1)).width, ((EGLSurfaceHolder) (bufferqueueabandonedexception1)).height);
            LegacyCameraDevice.setNextTimestamp(((EGLSurfaceHolder) (bufferqueueabandonedexception1)).surface, ((Long)pair.second).longValue());
            LegacyCameraDevice.produceFrame(((EGLSurfaceHolder) (bufferqueueabandonedexception1)).surface, mPBufferPixels.array(), ((EGLSurfaceHolder) (bufferqueueabandonedexception1)).width, ((EGLSurfaceHolder) (bufferqueueabandonedexception1)).height, i1);
        }
        catch(LegacyExceptionUtils.BufferQueueAbandonedException bufferqueueabandonedexception2)
        {
            Log.w(TAG, "Surface abandoned, dropping frame. ", bufferqueueabandonedexception2);
            requestholder.setOutputAbandoned();
        }
        if(true) goto _L5; else goto _L4
_L4:
        capturecollector.previewProduced();
        if(flag)
            endGlTiming();
        return;
    }

    public void flush()
    {
        Log.e(TAG, "Flush not yet implemented.");
    }

    public SurfaceTexture getSurfaceTexture()
    {
        return mSurfaceTexture;
    }

    private static final boolean DEBUG = false;
    private static final int EGL_COLOR_BITLENGTH = 8;
    private static final int EGL_RECORDABLE_ANDROID = 12610;
    private static final int FLIP_TYPE_BOTH = 3;
    private static final int FLIP_TYPE_HORIZONTAL = 1;
    private static final int FLIP_TYPE_NONE = 0;
    private static final int FLIP_TYPE_VERTICAL = 2;
    private static final int FLOAT_SIZE_BYTES = 4;
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    private static final int GLES_VERSION = 2;
    private static final int GL_MATRIX_SIZE = 16;
    private static final String LEGACY_PERF_PROPERTY = "persist.camera.legacy_perf";
    private static final int PBUFFER_PIXEL_BYTES = 4;
    private static final String TAG = android/hardware/camera2/legacy/SurfaceTextureRenderer.getSimpleName();
    private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
    private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 20;
    private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
    private static final int VERTEX_POS_SIZE = 3;
    private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    private static final int VERTEX_UV_SIZE = 2;
    private static final float sBothFlipTriangleVertices[] = {
        -1F, -1F, 0.0F, 1.0F, 1.0F, 1.0F, -1F, 0.0F, 0.0F, 1.0F, 
        -1F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F
    };
    private static final float sHorizontalFlipTriangleVertices[] = {
        -1F, -1F, 0.0F, 1.0F, 0.0F, 1.0F, -1F, 0.0F, 0.0F, 0.0F, 
        -1F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F
    };
    private static final float sRegularTriangleVertices[] = {
        -1F, -1F, 0.0F, 0.0F, 0.0F, 1.0F, -1F, 0.0F, 1.0F, 0.0F, 
        -1F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F
    };
    private static final float sVerticalFlipTriangleVertices[] = {
        -1F, -1F, 0.0F, 0.0F, 1.0F, 1.0F, -1F, 0.0F, 1.0F, 1.0F, 
        -1F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F
    };
    private FloatBuffer mBothFlipTriangleVertices;
    private EGLConfig mConfigs;
    private List mConversionSurfaces;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private final int mFacing;
    private FloatBuffer mHorizontalFlipTriangleVertices;
    private float mMVPMatrix[];
    private ByteBuffer mPBufferPixels;
    private PerfMeasurement mPerfMeasurer;
    private int mProgram;
    private FloatBuffer mRegularTriangleVertices;
    private float mSTMatrix[];
    private volatile SurfaceTexture mSurfaceTexture;
    private List mSurfaces;
    private int mTextureID;
    private FloatBuffer mVerticalFlipTriangleVertices;
    private int maPositionHandle;
    private int maTextureHandle;
    private int muMVPMatrixHandle;
    private int muSTMatrixHandle;

}
