// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.graphics:
//            Canvas, Rect

public class GraphicBuffer
    implements Parcelable
{

    static long _2D_wrap0(Parcel parcel)
    {
        return nReadGraphicBufferFromParcel(parcel);
    }

    private GraphicBuffer(int i, int j, int k, int l, long l1)
    {
        mWidth = i;
        mHeight = j;
        mFormat = k;
        mUsage = l;
        mNativeObject = l1;
    }

    GraphicBuffer(int i, int j, int k, int l, long l1, GraphicBuffer graphicbuffer)
    {
        this(i, j, k, l, l1);
    }

    public static GraphicBuffer create(int i, int j, int k, int l)
    {
        long l1 = nCreateGraphicBuffer(i, j, k, l);
        if(l1 != 0L)
            return new GraphicBuffer(i, j, k, l, l1);
        else
            return null;
    }

    public static GraphicBuffer createFromExisting(int i, int j, int k, int l, long l1)
    {
        l1 = nWrapGraphicBuffer(l1);
        if(l1 != 0L)
            return new GraphicBuffer(i, j, k, l, l1);
        else
            return null;
    }

    private static native long nCreateGraphicBuffer(int i, int j, int k, int l);

    private static native void nDestroyGraphicBuffer(long l);

    private static native boolean nLockCanvas(long l, Canvas canvas, Rect rect);

    private static native long nReadGraphicBufferFromParcel(Parcel parcel);

    private static native boolean nUnlockCanvasAndPost(long l, Canvas canvas);

    private static native long nWrapGraphicBuffer(long l);

    private static native void nWriteGraphicBufferToParcel(long l, Parcel parcel);

    public int describeContents()
    {
        return 0;
    }

    public void destroy()
    {
        if(!mDestroyed)
        {
            mDestroyed = true;
            nDestroyGraphicBuffer(mNativeObject);
        }
    }

    protected void finalize()
        throws Throwable
    {
        if(!mDestroyed)
            nDestroyGraphicBuffer(mNativeObject);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getFormat()
    {
        return mFormat;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getUsage()
    {
        return mUsage;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public boolean isDestroyed()
    {
        return mDestroyed;
    }

    public Canvas lockCanvas()
    {
        return lockCanvas(null);
    }

    public Canvas lockCanvas(Rect rect)
    {
        if(mDestroyed)
            return null;
        if(mCanvas == null)
            mCanvas = new Canvas();
        if(nLockCanvas(mNativeObject, mCanvas, rect))
        {
            mSaveCount = mCanvas.save();
            return mCanvas;
        } else
        {
            return null;
        }
    }

    public void unlockCanvasAndPost(Canvas canvas)
    {
        if(!mDestroyed && mCanvas != null && canvas == mCanvas)
        {
            canvas.restoreToCount(mSaveCount);
            mSaveCount = 0;
            nUnlockCanvasAndPost(mNativeObject, mCanvas);
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mDestroyed)
        {
            throw new IllegalStateException("This GraphicBuffer has been destroyed and cannot be written to a parcel.");
        } else
        {
            parcel.writeInt(mWidth);
            parcel.writeInt(mHeight);
            parcel.writeInt(mFormat);
            parcel.writeInt(mUsage);
            nWriteGraphicBufferToParcel(mNativeObject, parcel);
            return;
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GraphicBuffer createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            int k = parcel.readInt();
            int l = parcel.readInt();
            long l1 = GraphicBuffer._2D_wrap0(parcel);
            if(l1 != 0L)
                return new GraphicBuffer(i, j, k, l, l1, null);
            else
                return null;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GraphicBuffer[] newArray(int i)
        {
            return new GraphicBuffer[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int USAGE_HW_2D = 1024;
    public static final int USAGE_HW_COMPOSER = 2048;
    public static final int USAGE_HW_MASK = 0x71f00;
    public static final int USAGE_HW_RENDER = 512;
    public static final int USAGE_HW_TEXTURE = 256;
    public static final int USAGE_HW_VIDEO_ENCODER = 0x10000;
    public static final int USAGE_PROTECTED = 16384;
    public static final int USAGE_SOFTWARE_MASK = 255;
    public static final int USAGE_SW_READ_MASK = 15;
    public static final int USAGE_SW_READ_NEVER = 0;
    public static final int USAGE_SW_READ_OFTEN = 3;
    public static final int USAGE_SW_READ_RARELY = 2;
    public static final int USAGE_SW_WRITE_MASK = 240;
    public static final int USAGE_SW_WRITE_NEVER = 0;
    public static final int USAGE_SW_WRITE_OFTEN = 48;
    public static final int USAGE_SW_WRITE_RARELY = 32;
    private Canvas mCanvas;
    private boolean mDestroyed;
    private final int mFormat;
    private final int mHeight;
    private final long mNativeObject;
    private int mSaveCount;
    private final int mUsage;
    private final int mWidth;

}
