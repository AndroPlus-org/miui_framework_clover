// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import java.io.*;

// Referenced classes of package android.graphics:
//            Rect, Bitmap

public final class BitmapRegionDecoder
{

    private BitmapRegionDecoder(long l)
    {
        mNativeBitmapRegionDecoder = l;
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

    private static native BitmapRegionDecoder nativeNewInstance(long l, boolean flag);

    private static native BitmapRegionDecoder nativeNewInstance(FileDescriptor filedescriptor, boolean flag);

    private static native BitmapRegionDecoder nativeNewInstance(InputStream inputstream, byte abyte0[], boolean flag);

    private static native BitmapRegionDecoder nativeNewInstance(byte abyte0[], int i, int j, boolean flag);

    public static BitmapRegionDecoder newInstance(FileDescriptor filedescriptor, boolean flag)
        throws IOException
    {
        return nativeNewInstance(filedescriptor, flag);
    }

    public static BitmapRegionDecoder newInstance(InputStream inputstream, boolean flag)
        throws IOException
    {
        if(inputstream instanceof android.content.res.AssetManager.AssetInputStream)
            return nativeNewInstance(((android.content.res.AssetManager.AssetInputStream)inputstream).getNativeAsset(), flag);
        else
            return nativeNewInstance(inputstream, new byte[16384], flag);
    }

    public static BitmapRegionDecoder newInstance(String s, boolean flag)
        throws IOException
    {
        Object obj = null;
        FileInputStream fileinputstream;
        fileinputstream = JVM INSTR new #59  <Class FileInputStream>;
        fileinputstream.FileInputStream(s);
        s = newInstance(((InputStream) (fileinputstream)), flag);
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj) { }
        return s;
        s;
_L2:
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj) { }
        throw s;
        s;
        obj = fileinputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static BitmapRegionDecoder newInstance(byte abyte0[], int i, int j, boolean flag)
        throws IOException
    {
        if((i | j) < 0 || abyte0.length < i + j)
            throw new ArrayIndexOutOfBoundsException();
        else
            return nativeNewInstance(abyte0, i, j, flag);
    }

    public Bitmap decodeRegion(Rect rect, BitmapFactory.Options options)
    {
        BitmapFactory.Options.validate(options);
        Object obj = mNativeLock;
        obj;
        JVM INSTR monitorenter ;
        checkRecycled("decodeRegion called on recycled region decoder");
        if(rect.right > 0 && rect.bottom > 0) goto _L2; else goto _L1
_L1:
        rect = JVM INSTR new #95  <Class IllegalArgumentException>;
        rect.IllegalArgumentException("rectangle is outside the image");
        throw rect;
        rect;
        obj;
        JVM INSTR monitorexit ;
        throw rect;
_L2:
        if(rect.left >= getWidth() || rect.top >= getHeight()) goto _L1; else goto _L3
_L3:
        rect = nativeDecodeRegion(mNativeBitmapRegionDecoder, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, options);
        obj;
        JVM INSTR monitorexit ;
        return rect;
    }

    protected void finalize()
        throws Throwable
    {
        recycle();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getHeight()
    {
        Object obj = mNativeLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        checkRecycled("getHeight called on recycled region decoder");
        i = nativeGetHeight(mNativeBitmapRegionDecoder);
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getWidth()
    {
        Object obj = mNativeLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        checkRecycled("getWidth called on recycled region decoder");
        i = nativeGetWidth(mNativeBitmapRegionDecoder);
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean isRecycled()
    {
        return mRecycled;
    }

    public void recycle()
    {
        Object obj = mNativeLock;
        obj;
        JVM INSTR monitorenter ;
        if(!mRecycled)
        {
            nativeClean(mNativeBitmapRegionDecoder);
            mRecycled = true;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private long mNativeBitmapRegionDecoder;
    private final Object mNativeLock = new Object();
    private boolean mRecycled;
}
