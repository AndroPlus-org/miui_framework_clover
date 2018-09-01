// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

// Referenced classes of package android.filterfw.core:
//            Frame, FrameFormat, NativeBuffer, GLFrame, 
//            SimpleFrame, FrameManager

public class NativeFrame extends Frame
{

    NativeFrame(FrameFormat frameformat, FrameManager framemanager)
    {
        boolean flag = false;
        super(frameformat, framemanager);
        nativeFrameId = -1;
        int i = frameformat.getSize();
        nativeAllocate(i);
        if(i != 0)
            flag = true;
        setReusable(flag);
    }

    private native boolean getNativeBitmap(Bitmap bitmap, int i, int j);

    private native boolean getNativeBuffer(NativeBuffer nativebuffer);

    private native int getNativeCapacity();

    private native byte[] getNativeData(int i);

    private native float[] getNativeFloats(int i);

    private native int[] getNativeInts(int i);

    private native boolean nativeAllocate(int i);

    private native boolean nativeCopyFromGL(GLFrame glframe);

    private native boolean nativeCopyFromNative(NativeFrame nativeframe);

    private native boolean nativeDeallocate();

    private static native int nativeFloatSize();

    private static native int nativeIntSize();

    private native boolean setNativeBitmap(Bitmap bitmap, int i, int j);

    private native boolean setNativeData(byte abyte0[], int i, int j);

    private native boolean setNativeFloats(float af[]);

    private native boolean setNativeInts(int ai[]);

    public Bitmap getBitmap()
    {
        if(getFormat().getNumberOfDimensions() != 2)
            throw new RuntimeException("Attempting to get Bitmap for non 2-dimensional native frame!");
        Bitmap bitmap = Bitmap.createBitmap(getFormat().getWidth(), getFormat().getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        if(!getNativeBitmap(bitmap, bitmap.getByteCount(), getFormat().getBytesPerSample()))
            throw new RuntimeException("Could not get bitmap data from native frame!");
        else
            return bitmap;
    }

    public int getCapacity()
    {
        return getNativeCapacity();
    }

    public ByteBuffer getData()
    {
        ByteBuffer bytebuffer = null;
        byte abyte0[] = getNativeData(getFormat().getSize());
        if(abyte0 != null)
            bytebuffer = ByteBuffer.wrap(abyte0);
        return bytebuffer;
    }

    public float[] getFloats()
    {
        return getNativeFloats(getFormat().getSize());
    }

    public int[] getInts()
    {
        return getNativeInts(getFormat().getSize());
    }

    public Object getObjectValue()
    {
        if(getFormat().getBaseType() != 8)
            return getData();
        Class class1 = getFormat().getObjectClass();
        if(class1 == null)
            throw new RuntimeException("Attempting to get object data from frame that does not specify a structure object class!");
        if(!android/filterfw/core/NativeBuffer.isAssignableFrom(class1))
            throw new RuntimeException("NativeFrame object class must be a subclass of NativeBuffer!");
        Object obj;
        try
        {
            obj = (NativeBuffer)class1.newInstance();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException((new StringBuilder()).append("Could not instantiate new structure instance of type '").append(class1).append("'!").toString());
        }
        if(!getNativeBuffer(((NativeBuffer) (obj))))
        {
            throw new RuntimeException("Could not get the native structured data for frame!");
        } else
        {
            ((NativeBuffer) (obj)).attachToFrame(this);
            return obj;
        }
    }

    protected boolean hasNativeAllocation()
    {
        this;
        JVM INSTR monitorenter ;
        int i = nativeFrameId;
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

    protected void releaseNativeAllocation()
    {
        this;
        JVM INSTR monitorenter ;
        nativeDeallocate();
        nativeFrameId = -1;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setBitmap(Bitmap bitmap)
    {
        assertFrameMutable();
        if(getFormat().getNumberOfDimensions() != 2)
            throw new RuntimeException("Attempting to set Bitmap for non 2-dimensional native frame!");
        if(getFormat().getWidth() != bitmap.getWidth() || getFormat().getHeight() != bitmap.getHeight())
            throw new RuntimeException("Bitmap dimensions do not match native frame dimensions!");
        bitmap = convertBitmapToRGBA(bitmap);
        if(!setNativeBitmap(bitmap, bitmap.getByteCount(), getFormat().getBytesPerSample()))
            throw new RuntimeException("Could not set native frame bitmap data!");
        else
            return;
    }

    public void setData(ByteBuffer bytebuffer, int i, int j)
    {
        assertFrameMutable();
        byte abyte0[] = bytebuffer.array();
        if(j + i > bytebuffer.limit())
            throw new RuntimeException((new StringBuilder()).append("Offset and length exceed buffer size in native setData: ").append(j + i).append(" bytes given, but only ").append(bytebuffer.limit()).append(" bytes available!").toString());
        if(getFormat().getSize() != j)
            throw new RuntimeException((new StringBuilder()).append("Data size in setData does not match native frame size: Frame size is ").append(getFormat().getSize()).append(" bytes, but ").append(j).append(" bytes given!").toString());
        if(!setNativeData(abyte0, i, j))
            throw new RuntimeException("Could not set native frame data!");
        else
            return;
    }

    public void setDataFromFrame(Frame frame)
    {
        if(getFormat().getSize() < frame.getFormat().getSize())
            throw new RuntimeException((new StringBuilder()).append("Attempting to assign frame of size ").append(frame.getFormat().getSize()).append(" to ").append("smaller native frame of size ").append(getFormat().getSize()).append("!").toString());
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
        if(af.length * nativeFloatSize() > getFormat().getSize())
            throw new RuntimeException((new StringBuilder()).append("NativeFrame cannot hold ").append(af.length).append(" floats. (Can only hold ").append(getFormat().getSize() / nativeFloatSize()).append(" floats).").toString());
        if(!setNativeFloats(af))
            throw new RuntimeException("Could not set int values for native frame!");
        else
            return;
    }

    public void setInts(int ai[])
    {
        assertFrameMutable();
        if(ai.length * nativeIntSize() > getFormat().getSize())
            throw new RuntimeException((new StringBuilder()).append("NativeFrame cannot hold ").append(ai.length).append(" integers. (Can only hold ").append(getFormat().getSize() / nativeIntSize()).append(" integers).").toString());
        if(!setNativeInts(ai))
            throw new RuntimeException("Could not set int values for native frame!");
        else
            return;
    }

    public String toString()
    {
        return (new StringBuilder()).append("NativeFrame id: ").append(nativeFrameId).append(" (").append(getFormat()).append(") of size ").append(getCapacity()).toString();
    }

    private int nativeFrameId;

    static 
    {
        System.loadLibrary("filterfw");
    }
}
