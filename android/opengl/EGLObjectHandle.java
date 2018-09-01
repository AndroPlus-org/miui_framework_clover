// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;


public abstract class EGLObjectHandle
{

    protected EGLObjectHandle(int i)
    {
        mHandle = i;
    }

    protected EGLObjectHandle(long l)
    {
        mHandle = l;
    }

    public int getHandle()
    {
        if((mHandle & 0xffffffffL) != mHandle)
            throw new UnsupportedOperationException();
        else
            return (int)mHandle;
    }

    public long getNativeHandle()
    {
        return mHandle;
    }

    public int hashCode()
    {
        return (int)(mHandle ^ mHandle >>> 32) + 527;
    }

    private final long mHandle;
}
