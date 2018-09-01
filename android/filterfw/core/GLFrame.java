// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import java.nio.ByteBuffer;

// Referenced classes of package android.filterfw.core:
//            Frame, GLEnvironment, FrameFormat, GLFrameTimer, 
//            StopWatchMap, NativeFrame, SimpleFrame, FrameManager

public class GLFrame extends Frame
{

    GLFrame(FrameFormat frameformat, FrameManager framemanager)
    {
        super(frameformat, framemanager);
        glFrameId = -1;
        mOwnsTexture = true;
    }

    GLFrame(FrameFormat frameformat, FrameManager framemanager, int i, long l)
    {
        super(frameformat, framemanager, i, l);
        glFrameId = -1;
        mOwnsTexture = true;
    }

    private void assertGLEnvValid()
    {
        if(!mGLEnvironment.isContextActive())
        {
            if(GLEnvironment.isAnyContextActive())
                throw new RuntimeException((new StringBuilder()).append("Attempting to access ").append(this).append(" with foreign GL ").append("context active!").toString());
            else
                throw new RuntimeException((new StringBuilder()).append("Attempting to access ").append(this).append(" with no GL context ").append(" active!").toString());
        } else
        {
            return;
        }
    }

    private native boolean generateNativeMipMap();

    private native boolean getNativeBitmap(Bitmap bitmap);

    private native byte[] getNativeData();

    private native int getNativeFboId();

    private native float[] getNativeFloats();

    private native int[] getNativeInts();

    private native int getNativeTextureId();

    private void initNew(boolean flag)
    {
        if(flag)
        {
            if(!nativeAllocateExternal(mGLEnvironment))
                throw new RuntimeException("Could not allocate external GL frame!");
        } else
        if(!nativeAllocate(mGLEnvironment, getFormat().getWidth(), getFormat().getHeight()))
            throw new RuntimeException("Could not allocate GL frame!");
    }

    private void initWithFbo(int i)
    {
        int j = getFormat().getWidth();
        int k = getFormat().getHeight();
        if(!nativeAllocateWithFbo(mGLEnvironment, i, j, k))
            throw new RuntimeException("Could not allocate FBO backed GL frame!");
        else
            return;
    }

    private void initWithTexture(int i)
    {
        int j = getFormat().getWidth();
        int k = getFormat().getHeight();
        if(!nativeAllocateWithTexture(mGLEnvironment, i, j, k))
        {
            throw new RuntimeException("Could not allocate texture backed GL frame!");
        } else
        {
            mOwnsTexture = false;
            markReadOnly();
            return;
        }
    }

    private native boolean nativeAllocate(GLEnvironment glenvironment, int i, int j);

    private native boolean nativeAllocateExternal(GLEnvironment glenvironment);

    private native boolean nativeAllocateWithFbo(GLEnvironment glenvironment, int i, int j, int k);

    private native boolean nativeAllocateWithTexture(GLEnvironment glenvironment, int i, int j, int k);

    private native boolean nativeCopyFromGL(GLFrame glframe);

    private native boolean nativeCopyFromNative(NativeFrame nativeframe);

    private native boolean nativeDeallocate();

    private native boolean nativeDetachTexFromFbo();

    private native boolean nativeFocus();

    private native boolean nativeReattachTexToFbo();

    private native boolean nativeResetParams();

    private native boolean setNativeBitmap(Bitmap bitmap, int i);

    private native boolean setNativeData(byte abyte0[], int i, int j);

    private native boolean setNativeFloats(float af[]);

    private native boolean setNativeInts(int ai[]);

    private native boolean setNativeTextureParam(int i, int j);

    private native boolean setNativeViewport(int i, int j, int k, int l);

    void flushGPU(String s)
    {
        StopWatchMap stopwatchmap = GLFrameTimer.get();
        if(stopwatchmap.LOG_MFF_RUNNING_TIMES)
        {
            stopwatchmap.start((new StringBuilder()).append("glFinish ").append(s).toString());
            GLES20.glFinish();
            stopwatchmap.stop((new StringBuilder()).append("glFinish ").append(s).toString());
        }
    }

    public void focus()
    {
        if(!nativeFocus())
            throw new RuntimeException("Could not focus on GLFrame for drawing!");
        else
            return;
    }

    public void generateMipMap()
    {
        assertFrameMutable();
        assertGLEnvValid();
        if(!generateNativeMipMap())
            throw new RuntimeException("Could not generate mip-map for GL frame!");
        else
            return;
    }

    public Bitmap getBitmap()
    {
        assertGLEnvValid();
        flushGPU("getBitmap");
        Bitmap bitmap = Bitmap.createBitmap(getFormat().getWidth(), getFormat().getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        if(!getNativeBitmap(bitmap))
            throw new RuntimeException("Could not get bitmap data from GL frame!");
        else
            return bitmap;
    }

    public ByteBuffer getData()
    {
        assertGLEnvValid();
        flushGPU("getData");
        return ByteBuffer.wrap(getNativeData());
    }

    public int getFboId()
    {
        return getNativeFboId();
    }

    public float[] getFloats()
    {
        assertGLEnvValid();
        flushGPU("getFloats");
        return getNativeFloats();
    }

    public GLEnvironment getGLEnvironment()
    {
        return mGLEnvironment;
    }

    public int[] getInts()
    {
        assertGLEnvValid();
        flushGPU("getInts");
        return getNativeInts();
    }

    public Object getObjectValue()
    {
        assertGLEnvValid();
        return ByteBuffer.wrap(getNativeData());
    }

    public int getTextureId()
    {
        return getNativeTextureId();
    }

    protected boolean hasNativeAllocation()
    {
        this;
        JVM INSTR monitorenter ;
        int i = glFrameId;
        boolean flag;
        if(i != -1)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    void init(GLEnvironment glenvironment)
    {
        FrameFormat frameformat = getFormat();
        mGLEnvironment = glenvironment;
        if(frameformat.getBytesPerSample() != 4)
            throw new IllegalArgumentException("GL frames must have 4 bytes per sample!");
        if(frameformat.getDimensionCount() != 2)
            throw new IllegalArgumentException("GL frames must be 2-dimensional!");
        if(getFormat().getSize() < 0)
            throw new IllegalArgumentException("Initializing GL frame with zero size!");
        int i = getBindingType();
        boolean flag = true;
        if(i == 0)
            initNew(false);
        else
        if(i == 104)
        {
            initNew(true);
            flag = false;
        } else
        if(i == 100)
            initWithTexture((int)getBindingId());
        else
        if(i == 101)
            initWithFbo((int)getBindingId());
        else
        if(i == 102)
            initWithTexture((int)getBindingId());
        else
        if(i == 103)
            initWithFbo((int)getBindingId());
        else
            throw new RuntimeException((new StringBuilder()).append("Attempting to create GL frame with unknown binding type ").append(i).append("!").toString());
        setReusable(flag);
    }

    protected void onFrameFetch()
    {
        if(!mOwnsTexture)
            nativeReattachTexToFbo();
    }

    protected void onFrameStore()
    {
        if(!mOwnsTexture)
            nativeDetachTexFromFbo();
    }

    protected void releaseNativeAllocation()
    {
        this;
        JVM INSTR monitorenter ;
        nativeDeallocate();
        glFrameId = -1;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void reset(FrameFormat frameformat)
    {
        if(!nativeResetParams())
        {
            throw new RuntimeException("Could not reset GLFrame texture parameters!");
        } else
        {
            super.reset(frameformat);
            return;
        }
    }

    public void setBitmap(Bitmap bitmap)
    {
        assertFrameMutable();
        assertGLEnvValid();
        if(getFormat().getWidth() != bitmap.getWidth() || getFormat().getHeight() != bitmap.getHeight())
            throw new RuntimeException("Bitmap dimensions do not match GL frame dimensions!");
        bitmap = convertBitmapToRGBA(bitmap);
        if(!setNativeBitmap(bitmap, bitmap.getByteCount()))
            throw new RuntimeException("Could not set GL frame bitmap data!");
        else
            return;
    }

    public void setData(ByteBuffer bytebuffer, int i, int j)
    {
        assertFrameMutable();
        assertGLEnvValid();
        bytebuffer = bytebuffer.array();
        if(getFormat().getSize() != bytebuffer.length)
            throw new RuntimeException("Data size in setData does not match GL frame size!");
        if(!setNativeData(bytebuffer, i, j))
            throw new RuntimeException("Could not set GL frame data!");
        else
            return;
    }

    public void setDataFromFrame(Frame frame)
    {
        assertGLEnvValid();
        if(getFormat().getSize() < frame.getFormat().getSize())
            throw new RuntimeException((new StringBuilder()).append("Attempting to assign frame of size ").append(frame.getFormat().getSize()).append(" to ").append("smaller GL frame of size ").append(getFormat().getSize()).append("!").toString());
        if(frame instanceof NativeFrame)
            nativeCopyFromNative((NativeFrame)frame);
        else
        if(frame instanceof GLFrame)
            nativeCopyFromGL((GLFrame)frame);
        else
        if(frame instanceof SimpleFrame)
            setObjectValue(frame.getObjectValue());
        else
            super.setDataFromFrame(frame);
    }

    public void setFloats(float af[])
    {
        assertFrameMutable();
        assertGLEnvValid();
        if(!setNativeFloats(af))
            throw new RuntimeException("Could not set int values for GL frame!");
        else
            return;
    }

    public void setInts(int ai[])
    {
        assertFrameMutable();
        assertGLEnvValid();
        if(!setNativeInts(ai))
            throw new RuntimeException("Could not set int values for GL frame!");
        else
            return;
    }

    public void setTextureParameter(int i, int j)
    {
        assertFrameMutable();
        assertGLEnvValid();
        if(!setNativeTextureParam(i, j))
            throw new RuntimeException((new StringBuilder()).append("Could not set texture value ").append(i).append(" = ").append(j).append(" ").append("for GLFrame!").toString());
        else
            return;
    }

    public void setViewport(int i, int j, int k, int l)
    {
        assertFrameMutable();
        setNativeViewport(i, j, k, l);
    }

    public void setViewport(Rect rect)
    {
        assertFrameMutable();
        setNativeViewport(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }

    public String toString()
    {
        return (new StringBuilder()).append("GLFrame id: ").append(glFrameId).append(" (").append(getFormat()).append(") with texture ID ").append(getTextureId()).append(", FBO ID ").append(getFboId()).toString();
    }

    public static final int EXISTING_FBO_BINDING = 101;
    public static final int EXISTING_TEXTURE_BINDING = 100;
    public static final int EXTERNAL_TEXTURE = 104;
    public static final int NEW_FBO_BINDING = 103;
    public static final int NEW_TEXTURE_BINDING = 102;
    private int glFrameId;
    private GLEnvironment mGLEnvironment;
    private boolean mOwnsTexture;

    static 
    {
        System.loadLibrary("filterfw");
    }
}
