// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            Frame

public class NativeBuffer
{

    public NativeBuffer()
    {
        mDataPointer = 0L;
        mSize = 0;
        mOwnsData = false;
        mRefCount = 1;
    }

    public NativeBuffer(int i)
    {
        mDataPointer = 0L;
        mSize = 0;
        mOwnsData = false;
        mRefCount = 1;
        allocate(getElementSize() * i);
        mOwnsData = true;
    }

    private native boolean allocate(int i);

    private native boolean deallocate(boolean flag);

    private native boolean nativeCopyTo(NativeBuffer nativebuffer);

    protected void assertReadable()
    {
        while(mDataPointer == 0L || mSize == 0 || mAttachedFrame != null && mAttachedFrame.hasNativeAllocation() ^ true) 
            throw new NullPointerException("Attempting to read from null data frame!");
    }

    protected void assertWritable()
    {
        if(isReadOnly())
            throw new RuntimeException("Attempting to modify read-only native (structured) data!");
        else
            return;
    }

    void attachToFrame(Frame frame)
    {
        mAttachedFrame = frame;
    }

    public int count()
    {
        int i;
        if(mDataPointer != 0L)
            i = mSize / getElementSize();
        else
            i = 0;
        return i;
    }

    public int getElementSize()
    {
        return 1;
    }

    public boolean isReadOnly()
    {
        boolean flag;
        if(mAttachedFrame != null)
            flag = mAttachedFrame.isReadOnly();
        else
            flag = false;
        return flag;
    }

    public NativeBuffer mutableCopy()
    {
        Object obj;
        try
        {
            obj = (NativeBuffer)getClass().newInstance();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException((new StringBuilder()).append("Unable to allocate a copy of ").append(getClass()).append("! Make ").append("sure the class has a default constructor!").toString());
        }
        if(mSize > 0 && nativeCopyTo(((NativeBuffer) (obj))) ^ true)
            throw new RuntimeException("Failed to copy NativeBuffer to mutable instance!");
        else
            return ((NativeBuffer) (obj));
    }

    public NativeBuffer release()
    {
        boolean flag = false;
        if(mAttachedFrame != null)
        {
            if(mAttachedFrame.release() == null)
                flag = true;
            else
                flag = false;
        } else
        if(mOwnsData)
        {
            mRefCount = mRefCount - 1;
            if(mRefCount == 0)
                flag = true;
            else
                flag = false;
        }
        if(flag)
        {
            deallocate(mOwnsData);
            return null;
        } else
        {
            return this;
        }
    }

    public NativeBuffer retain()
    {
        if(mAttachedFrame == null) goto _L2; else goto _L1
_L1:
        mAttachedFrame.retain();
_L4:
        return this;
_L2:
        if(mOwnsData)
            mRefCount = mRefCount + 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int size()
    {
        return mSize;
    }

    private Frame mAttachedFrame;
    private long mDataPointer;
    private boolean mOwnsData;
    private int mRefCount;
    private int mSize;

    static 
    {
        System.loadLibrary("filterfw");
    }
}
