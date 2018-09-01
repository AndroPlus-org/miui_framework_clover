// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

// Referenced classes of package android.filterfw.core:
//            FrameFormat, FrameManager

public abstract class Frame
{

    Frame(FrameFormat frameformat, FrameManager framemanager)
    {
        mReadOnly = false;
        mReusable = false;
        mRefCount = 1;
        mBindingType = 0;
        mBindingId = 0L;
        mTimestamp = -2L;
        mFormat = frameformat.mutableCopy();
        mFrameManager = framemanager;
    }

    Frame(FrameFormat frameformat, FrameManager framemanager, int i, long l)
    {
        mReadOnly = false;
        mReusable = false;
        mRefCount = 1;
        mBindingType = 0;
        mBindingId = 0L;
        mTimestamp = -2L;
        mFormat = frameformat.mutableCopy();
        mFrameManager = framemanager;
        mBindingType = i;
        mBindingId = l;
    }

    protected static Bitmap convertBitmapToRGBA(Bitmap bitmap)
    {
        if(bitmap.getConfig() == android.graphics.Bitmap.Config.ARGB_8888)
            return bitmap;
        bitmap = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false);
        if(bitmap == null)
            throw new RuntimeException("Error converting bitmap to RGBA!");
        if(bitmap.getRowBytes() != bitmap.getWidth() * 4)
            throw new RuntimeException("Unsupported row byte count in bitmap!");
        else
            return bitmap;
    }

    protected void assertFrameMutable()
    {
        if(isReadOnly())
            throw new RuntimeException("Attempting to modify read-only frame!");
        else
            return;
    }

    final int decRefCount()
    {
        mRefCount = mRefCount - 1;
        return mRefCount;
    }

    public long getBindingId()
    {
        return mBindingId;
    }

    public int getBindingType()
    {
        return mBindingType;
    }

    public abstract Bitmap getBitmap();

    public int getCapacity()
    {
        return getFormat().getSize();
    }

    public abstract ByteBuffer getData();

    public abstract float[] getFloats();

    public FrameFormat getFormat()
    {
        return mFormat;
    }

    public FrameManager getFrameManager()
    {
        return mFrameManager;
    }

    public abstract int[] getInts();

    public abstract Object getObjectValue();

    public int getRefCount()
    {
        return mRefCount;
    }

    public long getTimestamp()
    {
        return mTimestamp;
    }

    protected abstract boolean hasNativeAllocation();

    final int incRefCount()
    {
        mRefCount = mRefCount + 1;
        return mRefCount;
    }

    public boolean isReadOnly()
    {
        return mReadOnly;
    }

    final boolean isReusable()
    {
        return mReusable;
    }

    final void markReadOnly()
    {
        mReadOnly = true;
    }

    protected void onFrameFetch()
    {
    }

    protected void onFrameStore()
    {
    }

    public Frame release()
    {
        if(mFrameManager != null)
            return mFrameManager.releaseFrame(this);
        else
            return this;
    }

    protected abstract void releaseNativeAllocation();

    protected boolean requestResize(int ai[])
    {
        return false;
    }

    protected void reset(FrameFormat frameformat)
    {
        mFormat = frameformat.mutableCopy();
        mReadOnly = false;
        mRefCount = 1;
    }

    public Frame retain()
    {
        if(mFrameManager != null)
            return mFrameManager.retainFrame(this);
        else
            return this;
    }

    public abstract void setBitmap(Bitmap bitmap);

    public void setData(ByteBuffer bytebuffer)
    {
        setData(bytebuffer, 0, bytebuffer.limit());
    }

    public abstract void setData(ByteBuffer bytebuffer, int i, int j);

    public void setData(byte abyte0[], int i, int j)
    {
        setData(ByteBuffer.wrap(abyte0, i, j));
    }

    public void setDataFromFrame(Frame frame)
    {
        setData(frame.getData());
    }

    public abstract void setFloats(float af[]);

    protected void setFormat(FrameFormat frameformat)
    {
        mFormat = frameformat.mutableCopy();
    }

    protected void setGenericObjectValue(Object obj)
    {
        throw new RuntimeException((new StringBuilder()).append("Cannot set object value of unsupported type: ").append(obj.getClass()).toString());
    }

    public abstract void setInts(int ai[]);

    public void setObjectValue(Object obj)
    {
        assertFrameMutable();
        if(obj instanceof int[])
            setInts((int[])obj);
        else
        if(obj instanceof float[])
            setFloats((float[])obj);
        else
        if(obj instanceof ByteBuffer)
            setData((ByteBuffer)obj);
        else
        if(obj instanceof Bitmap)
            setBitmap((Bitmap)obj);
        else
            setGenericObjectValue(obj);
    }

    protected void setReusable(boolean flag)
    {
        mReusable = flag;
    }

    public void setTimestamp(long l)
    {
        mTimestamp = l;
    }

    public static final int NO_BINDING = 0;
    public static final long TIMESTAMP_NOT_SET = -2L;
    public static final long TIMESTAMP_UNKNOWN = -1L;
    private long mBindingId;
    private int mBindingType;
    private FrameFormat mFormat;
    private FrameManager mFrameManager;
    private boolean mReadOnly;
    private int mRefCount;
    private boolean mReusable;
    private long mTimestamp;
}
