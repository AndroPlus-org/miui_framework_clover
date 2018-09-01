// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

// Referenced classes of package android.filterfw.core:
//            Frame, FrameFormat, FrameManager

public class VertexFrame extends Frame
{

    VertexFrame(FrameFormat frameformat, FrameManager framemanager)
    {
        super(frameformat, framemanager);
        vertexFrameId = -1;
        if(getFormat().getSize() <= 0)
            throw new IllegalArgumentException("Initializing vertex frame with zero size!");
        if(!nativeAllocate(getFormat().getSize()))
            throw new RuntimeException("Could not allocate vertex frame!");
        else
            return;
    }

    private native int getNativeVboId();

    private native boolean nativeAllocate(int i);

    private native boolean nativeDeallocate();

    private native boolean setNativeData(byte abyte0[], int i, int j);

    private native boolean setNativeFloats(float af[]);

    private native boolean setNativeInts(int ai[]);

    public Bitmap getBitmap()
    {
        throw new RuntimeException("Vertex frames do not support reading data!");
    }

    public ByteBuffer getData()
    {
        throw new RuntimeException("Vertex frames do not support reading data!");
    }

    public float[] getFloats()
    {
        throw new RuntimeException("Vertex frames do not support reading data!");
    }

    public int[] getInts()
    {
        throw new RuntimeException("Vertex frames do not support reading data!");
    }

    public Object getObjectValue()
    {
        throw new RuntimeException("Vertex frames do not support reading data!");
    }

    public int getVboId()
    {
        return getNativeVboId();
    }

    protected boolean hasNativeAllocation()
    {
        this;
        JVM INSTR monitorenter ;
        int i = vertexFrameId;
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
        vertexFrameId = -1;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setBitmap(Bitmap bitmap)
    {
        throw new RuntimeException("Unsupported: Cannot set vertex frame bitmap value!");
    }

    public void setData(ByteBuffer bytebuffer, int i, int j)
    {
        assertFrameMutable();
        bytebuffer = bytebuffer.array();
        if(getFormat().getSize() != bytebuffer.length)
            throw new RuntimeException("Data size in setData does not match vertex frame size!");
        if(!setNativeData(bytebuffer, i, j))
            throw new RuntimeException("Could not set vertex frame data!");
        else
            return;
    }

    public void setDataFromFrame(Frame frame)
    {
        super.setDataFromFrame(frame);
    }

    public void setFloats(float af[])
    {
        assertFrameMutable();
        if(!setNativeFloats(af))
            throw new RuntimeException("Could not set int values for vertex frame!");
        else
            return;
    }

    public void setInts(int ai[])
    {
        assertFrameMutable();
        if(!setNativeInts(ai))
            throw new RuntimeException("Could not set int values for vertex frame!");
        else
            return;
    }

    public String toString()
    {
        return (new StringBuilder()).append("VertexFrame (").append(getFormat()).append(") with VBO ID ").append(getVboId()).toString();
    }

    private int vertexFrameId;

    static 
    {
        System.loadLibrary("filterfw");
    }
}
