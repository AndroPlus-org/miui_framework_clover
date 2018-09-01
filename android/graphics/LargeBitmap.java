// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Rect, Bitmap

public final class LargeBitmap
{

    private LargeBitmap(long l)
    {
        mNativeLargeBitmap = l;
        mRecycled = false;
    }

    private void checkRecycled(String s)
    {
        if(mRecycled)
            throw new IllegalStateException(s);
        else
            return;
    }

    private static native void nativeClean(long l);

    private static native Bitmap nativeDecodeRegion(long l, int i, int j, int k, int i1, BitmapFactory.Options options);

    private static native int nativeGetHeight(long l);

    private static native int nativeGetWidth(long l);

    public Bitmap decodeRegion(Rect rect, BitmapFactory.Options options)
    {
        checkRecycled("decodeRegion called on recycled large bitmap");
        while(rect.left < 0 || rect.top < 0 || rect.right > getWidth() || rect.bottom > getHeight()) 
            throw new IllegalArgumentException("rectangle is not inside the image");
        return nativeDecodeRegion(mNativeLargeBitmap, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, options);
    }

    protected void finalize()
    {
        recycle();
    }

    public int getHeight()
    {
        checkRecycled("getHeight called on recycled large bitmap");
        return nativeGetHeight(mNativeLargeBitmap);
    }

    public int getWidth()
    {
        checkRecycled("getWidth called on recycled large bitmap");
        return nativeGetWidth(mNativeLargeBitmap);
    }

    public final boolean isRecycled()
    {
        return mRecycled;
    }

    public void recycle()
    {
        if(!mRecycled)
        {
            nativeClean(mNativeLargeBitmap);
            mRecycled = true;
        }
    }

    private long mNativeLargeBitmap;
    private boolean mRecycled;
}
