// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.graphics.SurfaceTexture;
import android.media.MediaRecorder;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;

// Referenced classes of package android.filterfw.core:
//            NativeAllocatorTag

public class GLEnvironment
{

    public GLEnvironment()
    {
        mManageContext = true;
        nativeAllocate();
    }

    private GLEnvironment(NativeAllocatorTag nativeallocatortag)
    {
        mManageContext = true;
    }

    public static boolean isAnyContextActive()
    {
        return nativeIsAnyContextActive();
    }

    private native boolean nativeActivate();

    private native boolean nativeActivateSurfaceId(int i);

    private native int nativeAddSurface(Surface surface);

    private native int nativeAddSurfaceFromMediaRecorder(MediaRecorder mediarecorder);

    private native int nativeAddSurfaceWidthHeight(Surface surface, int i, int j);

    private native boolean nativeAllocate();

    private native boolean nativeDeactivate();

    private native boolean nativeDeallocate();

    private native boolean nativeDisconnectSurfaceMediaSource(MediaRecorder mediarecorder);

    private native boolean nativeInitWithCurrentContext();

    private native boolean nativeInitWithNewContext();

    private native boolean nativeIsActive();

    private static native boolean nativeIsAnyContextActive();

    private native boolean nativeIsContextActive();

    private native boolean nativeRemoveSurfaceId(int i);

    private native boolean nativeSetSurfaceTimestamp(long l);

    private native boolean nativeSwapBuffers();

    public void activate()
    {
        if(Looper.myLooper() != null && Looper.myLooper().equals(Looper.getMainLooper()))
            Log.e("FilterFramework", "Activating GL context in UI thread!");
        if(mManageContext && nativeActivate() ^ true)
            throw new RuntimeException("Could not activate GLEnvironment!");
        else
            return;
    }

    public void activateSurfaceWithId(int i)
    {
        if(!nativeActivateSurfaceId(i))
            throw new RuntimeException((new StringBuilder()).append("Could not activate surface ").append(i).append("!").toString());
        else
            return;
    }

    public void deactivate()
    {
        if(mManageContext && nativeDeactivate() ^ true)
            throw new RuntimeException("Could not deactivate GLEnvironment!");
        else
            return;
    }

    protected void finalize()
        throws Throwable
    {
        tearDown();
    }

    public void initWithCurrentContext()
    {
        mManageContext = false;
        if(!nativeInitWithCurrentContext())
            throw new RuntimeException("Could not initialize GLEnvironment with current context!");
        else
            return;
    }

    public void initWithNewContext()
    {
        mManageContext = true;
        if(!nativeInitWithNewContext())
            throw new RuntimeException("Could not initialize GLEnvironment with new context!");
        else
            return;
    }

    public boolean isActive()
    {
        return nativeIsActive();
    }

    public boolean isContextActive()
    {
        return nativeIsContextActive();
    }

    public int registerSurface(Surface surface)
    {
        int i = nativeAddSurface(surface);
        if(i < 0)
            throw new RuntimeException((new StringBuilder()).append("Error registering surface ").append(surface).append("!").toString());
        else
            return i;
    }

    public int registerSurfaceFromMediaRecorder(MediaRecorder mediarecorder)
    {
        int i = nativeAddSurfaceFromMediaRecorder(mediarecorder);
        if(i < 0)
            throw new RuntimeException((new StringBuilder()).append("Error registering surface from MediaRecorder").append(mediarecorder).append("!").toString());
        else
            return i;
    }

    public int registerSurfaceTexture(SurfaceTexture surfacetexture, int i, int j)
    {
        Surface surface = new Surface(surfacetexture);
        i = nativeAddSurfaceWidthHeight(surface, i, j);
        surface.release();
        if(i < 0)
            throw new RuntimeException((new StringBuilder()).append("Error registering surfaceTexture ").append(surfacetexture).append("!").toString());
        else
            return i;
    }

    public void setSurfaceTimestamp(long l)
    {
        if(!nativeSetSurfaceTimestamp(l))
            throw new RuntimeException("Could not set timestamp for current surface!");
        else
            return;
    }

    public void swapBuffers()
    {
        if(!nativeSwapBuffers())
            throw new RuntimeException("Error swapping EGL buffers!");
        else
            return;
    }

    public void tearDown()
    {
        this;
        JVM INSTR monitorenter ;
        if(glEnvId != -1)
        {
            nativeDeallocate();
            glEnvId = -1;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterSurfaceId(int i)
    {
        if(!nativeRemoveSurfaceId(i))
            throw new RuntimeException((new StringBuilder()).append("Could not unregister surface ").append(i).append("!").toString());
        else
            return;
    }

    private int glEnvId;
    private boolean mManageContext;

    static 
    {
        System.loadLibrary("filterfw");
    }
}
