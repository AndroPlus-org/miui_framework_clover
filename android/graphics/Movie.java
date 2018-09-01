// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import java.io.*;

// Referenced classes of package android.graphics:
//            Canvas, Paint

public class Movie
{

    private Movie(long l)
    {
        if(l == 0L)
        {
            throw new RuntimeException("native movie creation failed");
        } else
        {
            mNativeMovie = l;
            return;
        }
    }

    public static native Movie decodeByteArray(byte abyte0[], int i, int j);

    public static Movie decodeFile(String s)
    {
        try
        {
            s = new FileInputStream(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return decodeTempStream(s);
    }

    public static Movie decodeStream(InputStream inputstream)
    {
        if(inputstream == null)
            return null;
        if(inputstream instanceof android.content.res.AssetManager.AssetInputStream)
            return nativeDecodeAsset(((android.content.res.AssetManager.AssetInputStream)inputstream).getNativeAsset());
        else
            return nativeDecodeStream(inputstream);
    }

    private static Movie decodeTempStream(InputStream inputstream)
    {
        Movie movie = null;
        Movie movie1 = decodeStream(inputstream);
        movie = movie1;
        inputstream.close();
        movie = movie1;
_L2:
        return movie;
        inputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private native void nDraw(long l, float f, float f1, long l1);

    private static native Movie nativeDecodeAsset(long l);

    private static native Movie nativeDecodeStream(InputStream inputstream);

    private static native void nativeDestructor(long l);

    public void draw(Canvas canvas, float f, float f1)
    {
        nDraw(canvas.getNativeCanvasWrapper(), f, f1, 0L);
    }

    public void draw(Canvas canvas, float f, float f1, Paint paint)
    {
        long l = canvas.getNativeCanvasWrapper();
        long l1;
        if(paint != null)
            l1 = paint.getNativeInstance();
        else
            l1 = 0L;
        nDraw(l, f, f1, l1);
    }

    public native int duration();

    protected void finalize()
        throws Throwable
    {
        nativeDestructor(mNativeMovie);
        mNativeMovie = 0L;
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public native int height();

    public native boolean isOpaque();

    public native boolean setTime(int i);

    public native int width();

    private long mNativeMovie;
}
