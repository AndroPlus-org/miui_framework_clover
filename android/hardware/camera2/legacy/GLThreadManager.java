// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.graphics.SurfaceTexture;
import android.os.*;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.Collection;

// Referenced classes of package android.hardware.camera2.legacy:
//            SurfaceTextureRenderer, RequestHandlerThread, CaptureCollector, CameraDeviceState

public class GLThreadManager
{
    private static class ConfigureHolder
    {

        public final CaptureCollector collector;
        public final ConditionVariable condition;
        public final Collection surfaces;

        public ConfigureHolder(ConditionVariable conditionvariable, Collection collection, CaptureCollector capturecollector)
        {
            condition = conditionvariable;
            surfaces = collection;
            collector = capturecollector;
        }
    }


    static String _2D_get0(GLThreadManager glthreadmanager)
    {
        return glthreadmanager.TAG;
    }

    static CaptureCollector _2D_get1(GLThreadManager glthreadmanager)
    {
        return glthreadmanager.mCaptureCollector;
    }

    static CameraDeviceState _2D_get2(GLThreadManager glthreadmanager)
    {
        return glthreadmanager.mDeviceState;
    }

    static SurfaceTextureRenderer _2D_get3(GLThreadManager glthreadmanager)
    {
        return glthreadmanager.mTextureRenderer;
    }

    static CaptureCollector _2D_set0(GLThreadManager glthreadmanager, CaptureCollector capturecollector)
    {
        glthreadmanager.mCaptureCollector = capturecollector;
        return capturecollector;
    }

    public GLThreadManager(int i, int j, CameraDeviceState cameradevicestate)
    {
        mTextureRenderer = new SurfaceTextureRenderer(j);
        TAG = String.format("CameraDeviceGLThread-%d", new Object[] {
            Integer.valueOf(i)
        });
        mGLHandlerThread = new RequestHandlerThread(TAG, mGLHandlerCb);
        mDeviceState = cameradevicestate;
    }

    public void allowNewFrames()
    {
        mGLHandlerThread.getHandler().sendEmptyMessage(5);
    }

    public SurfaceTexture getCurrentSurfaceTexture()
    {
        return mTextureRenderer.getSurfaceTexture();
    }

    public void ignoreNewFrames()
    {
        mGLHandlerThread.getHandler().sendEmptyMessage(4);
    }

    public void queueNewFrame()
    {
        Handler handler = mGLHandlerThread.getHandler();
        if(!handler.hasMessages(2))
            handler.sendMessage(handler.obtainMessage(2));
        else
            Log.e(TAG, "GLThread dropping frame.  Not consuming frames quickly enough!");
    }

    public void quit()
    {
        Handler handler = mGLHandlerThread.getHandler();
        handler.sendMessageAtFrontOfQueue(handler.obtainMessage(3));
        mGLHandlerThread.quitSafely();
        mGLHandlerThread.join();
_L1:
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        Log.e(TAG, String.format("Thread %s (%d) interrupted while quitting.", new Object[] {
            mGLHandlerThread.getName(), Long.valueOf(mGLHandlerThread.getId())
        }));
          goto _L1
    }

    public void setConfigurationAndWait(Collection collection, CaptureCollector capturecollector)
    {
        Preconditions.checkNotNull(capturecollector, "collector must not be null");
        Handler handler = mGLHandlerThread.getHandler();
        ConditionVariable conditionvariable = new ConditionVariable(false);
        handler.sendMessage(handler.obtainMessage(1, 0, 0, new ConfigureHolder(conditionvariable, collection, capturecollector)));
        conditionvariable.block();
    }

    public void start()
    {
        mGLHandlerThread.start();
    }

    public void waitUntilIdle()
    {
        mGLHandlerThread.waitUntilIdle();
    }

    public void waitUntilStarted()
    {
        mGLHandlerThread.waitUntilStarted();
    }

    private static final boolean DEBUG = false;
    private static final int MSG_ALLOW_FRAMES = 5;
    private static final int MSG_CLEANUP = 3;
    private static final int MSG_DROP_FRAMES = 4;
    private static final int MSG_NEW_CONFIGURATION = 1;
    private static final int MSG_NEW_FRAME = 2;
    private final String TAG;
    private CaptureCollector mCaptureCollector;
    private final CameraDeviceState mDeviceState;
    private final android.os.Handler.Callback mGLHandlerCb = new android.os.Handler.Callback() {

        public boolean handleMessage(Message message)
        {
            if(mCleanup)
                return true;
            message.what;
            JVM INSTR tableswitch -1 5: default 56
        //                       -1 98
        //                       0 56
        //                       1 100
        //                       2 194
        //                       3 257
        //                       4 280
        //                       5 288;
               goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7
_L7:
            break MISSING_BLOCK_LABEL_288;
_L2:
            break; /* Loop/switch isn't completed */
_L1:
            String s = GLThreadManager._2D_get0(GLThreadManager.this);
            StringBuilder stringbuilder = JVM INSTR new #44  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e(s, stringbuilder.append("Unhandled message ").append(message.what).append(" on GLThread.").toString());
_L8:
            return true;
_L3:
            try
            {
                message = (ConfigureHolder)message.obj;
                GLThreadManager._2D_get3(GLThreadManager.this).cleanupEGLContext();
                GLThreadManager._2D_get3(GLThreadManager.this).configureSurfaces(((ConfigureHolder) (message)).surfaces);
                GLThreadManager._2D_set0(GLThreadManager.this, (CaptureCollector)Preconditions.checkNotNull(((ConfigureHolder) (message)).collector));
                ((ConfigureHolder) (message)).condition.open();
                mConfigured = true;
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e(GLThreadManager._2D_get0(GLThreadManager.this), "Received exception on GL render thread: ", message);
                GLThreadManager._2D_get2(GLThreadManager.this).setError(1);
            }
              goto _L8
_L4:
label0:
            {
                if(!mDroppingFrames)
                    break label0;
                Log.w(GLThreadManager._2D_get0(GLThreadManager.this), "Ignoring frame.");
            }
              goto _L8
            if(!mConfigured)
                Log.e(GLThreadManager._2D_get0(GLThreadManager.this), "Dropping frame, EGL context not configured!");
            GLThreadManager._2D_get3(GLThreadManager.this).drawIntoSurfaces(GLThreadManager._2D_get1(GLThreadManager.this));
              goto _L8
_L5:
            GLThreadManager._2D_get3(GLThreadManager.this).cleanupEGLContext();
            mCleanup = true;
            mConfigured = false;
              goto _L8
_L6:
            mDroppingFrames = true;
              goto _L8
            mDroppingFrames = false;
              goto _L8
        }

        private boolean mCleanup;
        private boolean mConfigured;
        private boolean mDroppingFrames;
        final GLThreadManager this$0;

            
            {
                this$0 = GLThreadManager.this;
                super();
                mCleanup = false;
                mConfigured = false;
                mDroppingFrames = false;
            }
    }
;
    private final RequestHandlerThread mGLHandlerThread;
    private final RequestThreadManager.FpsCounter mPrevCounter = new RequestThreadManager.FpsCounter("GL Preview Producer");
    private final SurfaceTextureRenderer mTextureRenderer;
}
