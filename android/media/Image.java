// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Rect;
import java.nio.ByteBuffer;

public abstract class Image
    implements AutoCloseable
{
    public static abstract class Plane
    {

        public abstract ByteBuffer getBuffer();

        public abstract int getPixelStride();

        public abstract int getRowStride();

        protected Plane()
        {
        }
    }


    protected Image()
    {
        mIsImageValid = false;
    }

    public abstract void close();

    public Rect getCropRect()
    {
        throwISEIfImageIsInvalid();
        if(mCropRect == null)
            return new Rect(0, 0, getWidth(), getHeight());
        else
            return new Rect(mCropRect);
    }

    public abstract int getFormat();

    public abstract int getHeight();

    long getNativeContext()
    {
        throwISEIfImageIsInvalid();
        return 0L;
    }

    Object getOwner()
    {
        throwISEIfImageIsInvalid();
        return null;
    }

    public abstract Plane[] getPlanes();

    public abstract long getTimestamp();

    public abstract int getWidth();

    boolean isAttachable()
    {
        throwISEIfImageIsInvalid();
        return false;
    }

    public void setCropRect(Rect rect)
    {
        throwISEIfImageIsInvalid();
        Rect rect1 = rect;
        if(rect != null)
        {
            rect1 = new Rect(rect);
            if(!rect1.intersect(0, 0, getWidth(), getHeight()))
                rect1.setEmpty();
        }
        mCropRect = rect1;
    }

    public void setTimestamp(long l)
    {
        throwISEIfImageIsInvalid();
    }

    protected void throwISEIfImageIsInvalid()
    {
        if(!mIsImageValid)
            throw new IllegalStateException("Image is already closed");
        else
            return;
    }

    private Rect mCropRect;
    protected boolean mIsImageValid;
}
