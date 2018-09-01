// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Paint, Canvas, Bitmap, Region, 
//            Rect, RectF

public class NinePatch
{
    public static class InsetStruct
    {

        public static Rect scaleInsets(int i, int j, int k, int l, float f)
        {
            if(f == 1.0F)
            {
                return new Rect(i, j, k, l);
            } else
            {
                Rect rect = new Rect();
                rect.left = (int)Math.ceil((float)i * f);
                rect.top = (int)Math.ceil((float)j * f);
                rect.right = (int)Math.ceil((float)k * f);
                rect.bottom = (int)Math.ceil((float)l * f);
                return rect;
            }
        }

        public final Rect opticalRect;
        public final float outlineAlpha;
        public final float outlineRadius;
        public final Rect outlineRect;

        InsetStruct(int i, int j, int k, int l, int i1, int j1, int k1, 
                int l1, float f, int i2, float f1)
        {
            opticalRect = new Rect(i, j, k, l);
            opticalRect.scale(f1);
            outlineRect = scaleInsets(i1, j1, k1, l1, f1);
            outlineRadius = f * f1;
            outlineAlpha = (float)i2 / 255F;
        }
    }


    public NinePatch(Bitmap bitmap, byte abyte0[])
    {
        this(bitmap, abyte0, null);
    }

    public NinePatch(Bitmap bitmap, byte abyte0[], String s)
    {
        mBitmap = bitmap;
        mSrcName = s;
        mNativeChunk = validateNinePatchChunk(abyte0);
    }

    public NinePatch(NinePatch ninepatch)
    {
        mBitmap = ninepatch.mBitmap;
        mSrcName = ninepatch.mSrcName;
        if(ninepatch.mPaint != null)
            mPaint = new Paint(ninepatch.mPaint);
        mNativeChunk = ninepatch.mNativeChunk;
    }

    public static native boolean isNinePatchChunk(byte abyte0[]);

    private static native void nativeFinalize(long l);

    private static native long nativeGetTransparentRegion(Bitmap bitmap, long l, Rect rect);

    private static native long validateNinePatchChunk(byte abyte0[]);

    public void draw(Canvas canvas, Rect rect)
    {
        canvas.drawPatch(this, rect, mPaint);
    }

    public void draw(Canvas canvas, Rect rect, Paint paint)
    {
        canvas.drawPatch(this, rect, paint);
    }

    public void draw(Canvas canvas, RectF rectf)
    {
        canvas.drawPatch(this, rectf, mPaint);
    }

    protected void finalize()
        throws Throwable
    {
        if(mNativeChunk != 0L)
        {
            nativeFinalize(mNativeChunk);
            mNativeChunk = 0L;
        }
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public Bitmap getBitmap()
    {
        return mBitmap;
    }

    public int getDensity()
    {
        return mBitmap.mDensity;
    }

    public int getHeight()
    {
        return mBitmap.getHeight();
    }

    public String getName()
    {
        return mSrcName;
    }

    public Paint getPaint()
    {
        return mPaint;
    }

    public final Region getTransparentRegion(Rect rect)
    {
        long l = nativeGetTransparentRegion(mBitmap, mNativeChunk, rect);
        if(l != 0L)
            rect = new Region(l);
        else
            rect = null;
        return rect;
    }

    public int getWidth()
    {
        return mBitmap.getWidth();
    }

    public final boolean hasAlpha()
    {
        return mBitmap.hasAlpha();
    }

    public void setPaint(Paint paint)
    {
        mPaint = paint;
    }

    private final Bitmap mBitmap;
    public long mNativeChunk;
    private Paint mPaint;
    private String mSrcName;
}
