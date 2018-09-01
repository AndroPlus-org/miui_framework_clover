// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import java.io.InputStream;
import java.io.OutputStream;

// Referenced classes of package android.graphics:
//            Canvas, Bitmap

public class Picture
{
    private static class RecordingCanvas extends Canvas
    {

        public void drawPicture(Picture picture)
        {
            if(mPicture == picture)
            {
                throw new RuntimeException("Cannot draw a picture into its recording canvas");
            } else
            {
                super.drawPicture(picture);
                return;
            }
        }

        public void setBitmap(Bitmap bitmap)
        {
            throw new RuntimeException("Cannot call setBitmap on a picture canvas");
        }

        private final Picture mPicture;

        public RecordingCanvas(Picture picture, long l)
        {
            super(l);
            mPicture = picture;
        }
    }


    public Picture()
    {
        this(nativeConstructor(0L));
    }

    private Picture(long l)
    {
        if(l == 0L)
        {
            throw new RuntimeException();
        } else
        {
            mNativePicture = l;
            return;
        }
    }

    public Picture(Picture picture)
    {
        long l;
        if(picture != null)
            l = picture.mNativePicture;
        else
            l = 0L;
        this(nativeConstructor(l));
    }

    public static Picture createFromStream(InputStream inputstream)
    {
        return new Picture(nativeCreateFromStream(inputstream, new byte[16384]));
    }

    private static native long nativeBeginRecording(long l, int i, int j);

    private static native long nativeConstructor(long l);

    private static native long nativeCreateFromStream(InputStream inputstream, byte abyte0[]);

    private static native void nativeDestructor(long l);

    private static native void nativeDraw(long l, long l1);

    private static native void nativeEndRecording(long l);

    private static native int nativeGetHeight(long l);

    private static native int nativeGetWidth(long l);

    private static native boolean nativeWriteToStream(long l, OutputStream outputstream, byte abyte0[]);

    public Canvas beginRecording(int i, int j)
    {
        mRecordingCanvas = new RecordingCanvas(this, nativeBeginRecording(mNativePicture, i, j));
        return mRecordingCanvas;
    }

    public void draw(Canvas canvas)
    {
        if(mRecordingCanvas != null)
            endRecording();
        nativeDraw(canvas.getNativeCanvasWrapper(), mNativePicture);
    }

    public void endRecording()
    {
        if(mRecordingCanvas != null)
        {
            mRecordingCanvas = null;
            nativeEndRecording(mNativePicture);
        }
    }

    protected void finalize()
        throws Throwable
    {
        nativeDestructor(mNativePicture);
        mNativePicture = 0L;
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getHeight()
    {
        return nativeGetHeight(mNativePicture);
    }

    public int getWidth()
    {
        return nativeGetWidth(mNativePicture);
    }

    public void writeToStream(OutputStream outputstream)
    {
        if(outputstream == null)
            throw new NullPointerException();
        if(!nativeWriteToStream(mNativePicture, outputstream, new byte[16384]))
            throw new RuntimeException();
        else
            return;
    }

    private static final int WORKING_STREAM_STORAGE = 16384;
    private long mNativePicture;
    private Canvas mRecordingCanvas;
}
