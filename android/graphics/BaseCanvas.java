// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.text.*;

// Referenced classes of package android.graphics:
//            Paint, BitmapShader, ComposeShader, Bitmap, 
//            Color, RectF, Matrix, Rect, 
//            NinePatch, Path, Region, TemporaryBuffer, 
//            Shader

public abstract class BaseCanvas
{

    public BaseCanvas()
    {
        mScreenDensity = 0;
        mDensity = 0;
        mAllowHwBitmapsInSwMode = false;
    }

    protected static final void checkRange(int i, int j, int k)
    {
        if((j | k) < 0 || j + k > i)
            throw new ArrayIndexOutOfBoundsException();
        else
            return;
    }

    private static native void nDrawArc(long l, float f, float f1, float f2, float f3, float f4, float f5, 
            boolean flag, long l1);

    private static native void nDrawBitmap(long l, Bitmap bitmap, float f, float f1, float f2, float f3, float f4, 
            float f5, float f6, float f7, long l1, int i, int j);

    private static native void nDrawBitmap(long l, Bitmap bitmap, float f, float f1, long l1, int i, 
            int j, int k);

    private static native void nDrawBitmap(long l, int ai[], int i, int j, float f, float f1, int k, 
            int i1, boolean flag, long l1);

    private static native void nDrawBitmapMatrix(long l, Bitmap bitmap, long l1, long l2);

    private static native void nDrawBitmapMesh(long l, Bitmap bitmap, int i, int j, float af[], int k, int ai[], 
            int i1, long l1);

    private static native void nDrawCircle(long l, float f, float f1, float f2, long l1);

    private static native void nDrawColor(long l, int i, int j);

    private static native void nDrawLine(long l, float f, float f1, float f2, float f3, long l1);

    private static native void nDrawLines(long l, float af[], int i, int j, long l1);

    private static native void nDrawNinePatch(long l, long l1, long l2, float f, float f1, 
            float f2, float f3, long l3, int i, int j);

    private static native void nDrawOval(long l, float f, float f1, float f2, float f3, long l1);

    private static native void nDrawPaint(long l, long l1);

    private static native void nDrawPath(long l, long l1, long l2);

    private static native void nDrawPoint(long l, float f, float f1, long l1);

    private static native void nDrawPoints(long l, float af[], int i, int j, long l1);

    private static native void nDrawRect(long l, float f, float f1, float f2, float f3, long l1);

    private static native void nDrawRegion(long l, long l1, long l2);

    private static native void nDrawRoundRect(long l, float f, float f1, float f2, float f3, float f4, float f5, 
            long l1);

    private static native void nDrawText(long l, String s, int i, int j, float f, float f1, int k, 
            long l1, long l2);

    private static native void nDrawText(long l, char ac[], int i, int j, float f, float f1, int k, 
            long l1, long l2);

    private static native void nDrawTextOnPath(long l, String s, long l1, float f, float f1, int i, 
            long l2, long l3);

    private static native void nDrawTextOnPath(long l, char ac[], int i, int j, long l1, float f, 
            float f1, int k, long l2, long l3);

    private static native void nDrawTextRun(long l, String s, int i, int j, int k, int i1, float f, 
            float f1, boolean flag, long l1, long l2);

    private static native void nDrawTextRun(long l, char ac[], int i, int j, int k, int i1, float f, 
            float f1, boolean flag, long l1, long l2);

    private static native void nDrawVertices(long l, int i, int j, float af[], int k, float af1[], int i1, 
            int ai[], int j1, short aword0[], int k1, int l1, long l2);

    private void throwIfHasHwBitmapInSwMode(Paint paint)
    {
        if(isHardwareAccelerated() || paint == null)
        {
            return;
        } else
        {
            throwIfHasHwBitmapInSwMode(paint.getShader());
            return;
        }
    }

    private void throwIfHasHwBitmapInSwMode(Shader shader)
    {
        if(shader == null)
            return;
        if(shader instanceof BitmapShader)
            throwIfHwBitmapInSwMode(((BitmapShader)shader).mBitmap);
        if(shader instanceof ComposeShader)
        {
            throwIfHasHwBitmapInSwMode(((ComposeShader)shader).mShaderA);
            throwIfHasHwBitmapInSwMode(((ComposeShader)shader).mShaderB);
        }
    }

    private void throwIfHwBitmapInSwMode(Bitmap bitmap)
    {
        if(!mAllowHwBitmapsInSwMode && isHardwareAccelerated() ^ true && bitmap.getConfig() == Bitmap.Config.HARDWARE)
            throw new IllegalStateException("Software rendering doesn't support hardware bitmaps");
        else
            return;
    }

    public void drawARGB(int i, int j, int k, int l)
    {
        drawColor(Color.argb(i, j, k, l));
    }

    public void drawArc(float f, float f1, float f2, float f3, float f4, float f5, boolean flag, 
            Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawArc(mNativeCanvasWrapper, f, f1, f2, f3, f4, f5, flag, paint.getNativeInstance());
    }

    public void drawArc(RectF rectf, float f, float f1, boolean flag, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        drawArc(rectf.left, rectf.top, rectf.right, rectf.bottom, f, f1, flag, paint);
    }

    public void drawBitmap(Bitmap bitmap, float f, float f1, Paint paint)
    {
        throwIfCannotDraw(bitmap);
        throwIfHasHwBitmapInSwMode(paint);
        long l = mNativeCanvasWrapper;
        long l1;
        if(paint != null)
            l1 = paint.getNativeInstance();
        else
            l1 = 0L;
        nDrawBitmap(l, bitmap, f, f1, l1, mDensity, mScreenDensity, bitmap.mDensity);
    }

    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        long l = mNativeCanvasWrapper;
        long l1 = matrix.ni();
        long l2;
        if(paint != null)
            l2 = paint.getNativeInstance();
        else
            l2 = 0L;
        nDrawBitmapMatrix(l, bitmap, l1, l2);
    }

    public void drawBitmap(Bitmap bitmap, Rect rect, Rect rect1, Paint paint)
    {
        if(rect1 == null)
            throw new NullPointerException();
        throwIfCannotDraw(bitmap);
        throwIfHasHwBitmapInSwMode(paint);
        long l;
        int i;
        int j;
        int k;
        int i1;
        if(paint == null)
            l = 0L;
        else
            l = paint.getNativeInstance();
        if(rect == null)
        {
            i = 0;
            j = 0;
            k = bitmap.getWidth();
            i1 = bitmap.getHeight();
        } else
        {
            j = rect.left;
            k = rect.right;
            i = rect.top;
            i1 = rect.bottom;
        }
        nDrawBitmap(mNativeCanvasWrapper, bitmap, j, i, k, i1, rect1.left, rect1.top, rect1.right, rect1.bottom, l, mScreenDensity, bitmap.mDensity);
    }

    public void drawBitmap(Bitmap bitmap, Rect rect, RectF rectf, Paint paint)
    {
        if(rectf == null)
            throw new NullPointerException();
        throwIfCannotDraw(bitmap);
        throwIfHasHwBitmapInSwMode(paint);
        long l;
        float f;
        float f1;
        float f2;
        float f3;
        if(paint == null)
            l = 0L;
        else
            l = paint.getNativeInstance();
        if(rect == null)
        {
            f = 0.0F;
            f1 = 0.0F;
            f2 = bitmap.getWidth();
            f3 = bitmap.getHeight();
        } else
        {
            f1 = rect.left;
            f2 = rect.right;
            f = rect.top;
            f3 = rect.bottom;
        }
        nDrawBitmap(mNativeCanvasWrapper, bitmap, f1, f, f2, f3, rectf.left, rectf.top, rectf.right, rectf.bottom, l, mScreenDensity, bitmap.mDensity);
    }

    public void drawBitmap(int ai[], int i, int j, float f, float f1, int k, int l, 
            boolean flag, Paint paint)
    {
        if(k < 0)
            throw new IllegalArgumentException("width must be >= 0");
        if(l < 0)
            throw new IllegalArgumentException("height must be >= 0");
        if(Math.abs(j) < k)
            throw new IllegalArgumentException("abs(stride) must be >= width");
        int i1 = i + (l - 1) * j;
        for(int j1 = ai.length; i < 0 || i + k > j1 || i1 < 0 || i1 + k > j1;)
            throw new ArrayIndexOutOfBoundsException();

        throwIfHasHwBitmapInSwMode(paint);
        if(k == 0 || l == 0)
            return;
        long l1 = mNativeCanvasWrapper;
        long l2;
        if(paint != null)
            l2 = paint.getNativeInstance();
        else
            l2 = 0L;
        nDrawBitmap(l1, ai, i, j, f, f1, k, l, flag, l2);
    }

    public void drawBitmap(int ai[], int i, int j, int k, int l, int i1, int j1, 
            boolean flag, Paint paint)
    {
        drawBitmap(ai, i, j, k, l, i1, j1, flag, paint);
    }

    public void drawBitmapMesh(Bitmap bitmap, int i, int j, float af[], int k, int ai[], int l, 
            Paint paint)
    {
        if((i | j | k | l) < 0)
            throw new ArrayIndexOutOfBoundsException();
        throwIfHasHwBitmapInSwMode(paint);
        if(i == 0 || j == 0)
            return;
        int i1 = (i + 1) * (j + 1);
        checkRange(af.length, k, i1 * 2);
        if(ai != null)
            checkRange(ai.length, l, i1);
        long l1 = mNativeCanvasWrapper;
        long l2;
        if(paint != null)
            l2 = paint.getNativeInstance();
        else
            l2 = 0L;
        nDrawBitmapMesh(l1, bitmap, i, j, af, k, ai, l, l2);
    }

    public void drawCircle(float f, float f1, float f2, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawCircle(mNativeCanvasWrapper, f, f1, f2, paint.getNativeInstance());
    }

    public void drawColor(int i)
    {
        nDrawColor(mNativeCanvasWrapper, i, PorterDuff.Mode.SRC_OVER.nativeInt);
    }

    public void drawColor(int i, PorterDuff.Mode mode)
    {
        nDrawColor(mNativeCanvasWrapper, i, mode.nativeInt);
    }

    public void drawLine(float f, float f1, float f2, float f3, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawLine(mNativeCanvasWrapper, f, f1, f2, f3, paint.getNativeInstance());
    }

    public void drawLines(float af[], int i, int j, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawLines(mNativeCanvasWrapper, af, i, j, paint.getNativeInstance());
    }

    public void drawLines(float af[], Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        drawLines(af, 0, af.length, paint);
    }

    public void drawOval(float f, float f1, float f2, float f3, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawOval(mNativeCanvasWrapper, f, f1, f2, f3, paint.getNativeInstance());
    }

    public void drawOval(RectF rectf, Paint paint)
    {
        if(rectf == null)
        {
            throw new NullPointerException();
        } else
        {
            throwIfHasHwBitmapInSwMode(paint);
            drawOval(rectf.left, rectf.top, rectf.right, rectf.bottom, paint);
            return;
        }
    }

    public void drawPaint(Paint paint)
    {
        nDrawPaint(mNativeCanvasWrapper, paint.getNativeInstance());
    }

    public void drawPatch(NinePatch ninepatch, Rect rect, Paint paint)
    {
        Bitmap bitmap = ninepatch.getBitmap();
        throwIfCannotDraw(bitmap);
        throwIfHasHwBitmapInSwMode(paint);
        long l;
        if(paint == null)
            l = 0L;
        else
            l = paint.getNativeInstance();
        nDrawNinePatch(mNativeCanvasWrapper, bitmap.getNativeInstance(), ninepatch.mNativeChunk, rect.left, rect.top, rect.right, rect.bottom, l, mDensity, ninepatch.getDensity());
    }

    public void drawPatch(NinePatch ninepatch, RectF rectf, Paint paint)
    {
        Bitmap bitmap = ninepatch.getBitmap();
        throwIfCannotDraw(bitmap);
        throwIfHasHwBitmapInSwMode(paint);
        long l;
        if(paint == null)
            l = 0L;
        else
            l = paint.getNativeInstance();
        nDrawNinePatch(mNativeCanvasWrapper, bitmap.getNativeInstance(), ninepatch.mNativeChunk, rectf.left, rectf.top, rectf.right, rectf.bottom, l, mDensity, ninepatch.getDensity());
    }

    public void drawPath(Path path, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        if(path.isSimplePath && path.rects != null)
            nDrawRegion(mNativeCanvasWrapper, path.rects.mNativeRegion, paint.getNativeInstance());
        else
            nDrawPath(mNativeCanvasWrapper, path.readOnlyNI(), paint.getNativeInstance());
    }

    public void drawPoint(float f, float f1, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawPoint(mNativeCanvasWrapper, f, f1, paint.getNativeInstance());
    }

    public void drawPoints(float af[], int i, int j, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawPoints(mNativeCanvasWrapper, af, i, j, paint.getNativeInstance());
    }

    public void drawPoints(float af[], Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        drawPoints(af, 0, af.length, paint);
    }

    public void drawPosText(String s, float af[], Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        drawPosText(s.toCharArray(), 0, s.length(), af, paint);
    }

    public void drawPosText(char ac[], int i, int j, float af[], Paint paint)
    {
        while(i < 0 || i + j > ac.length || j * 2 > af.length) 
            throw new IndexOutOfBoundsException();
        throwIfHasHwBitmapInSwMode(paint);
        for(int k = 0; k < j; k++)
            drawText(ac, i + k, 1, af[k * 2], af[k * 2 + 1], paint);

    }

    public void drawRGB(int i, int j, int k)
    {
        drawColor(Color.rgb(i, j, k));
    }

    public void drawRect(float f, float f1, float f2, float f3, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawRect(mNativeCanvasWrapper, f, f1, f2, f3, paint.getNativeInstance());
    }

    public void drawRect(Rect rect, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        drawRect(rect.left, rect.top, rect.right, rect.bottom, paint);
    }

    public void drawRect(RectF rectf, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawRect(mNativeCanvasWrapper, rectf.left, rectf.top, rectf.right, rectf.bottom, paint.getNativeInstance());
    }

    public void drawRoundRect(float f, float f1, float f2, float f3, float f4, float f5, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawRoundRect(mNativeCanvasWrapper, f, f1, f2, f3, f4, f5, paint.getNativeInstance());
    }

    public void drawRoundRect(RectF rectf, float f, float f1, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        drawRoundRect(rectf.left, rectf.top, rectf.right, rectf.bottom, f, f1, paint);
    }

    public void drawText(CharSequence charsequence, int i, int j, float f, float f1, Paint paint)
    {
        if((i | j | j - i | charsequence.length() - j) < 0)
            throw new IndexOutOfBoundsException();
        throwIfHasHwBitmapInSwMode(paint);
        if((charsequence instanceof String) || (charsequence instanceof SpannedString) || (charsequence instanceof SpannableString))
            nDrawText(mNativeCanvasWrapper, charsequence.toString(), i, j, f, f1, paint.mBidiFlags, paint.getNativeInstance(), paint.mNativeTypeface);
        else
        if(charsequence instanceof GraphicsOperations)
        {
            ((GraphicsOperations)charsequence).drawText(this, i, j, f, f1, paint);
        } else
        {
            char ac[] = TemporaryBuffer.obtain(j - i);
            TextUtils.getChars(charsequence, i, j, ac, 0);
            nDrawText(mNativeCanvasWrapper, ac, 0, j - i, f, f1, paint.mBidiFlags, paint.getNativeInstance(), paint.mNativeTypeface);
            TemporaryBuffer.recycle(ac);
        }
    }

    public void drawText(String s, float f, float f1, Paint paint)
    {
        throwIfHasHwBitmapInSwMode(paint);
        nDrawText(mNativeCanvasWrapper, s, 0, s.length(), f, f1, paint.mBidiFlags, paint.getNativeInstance(), paint.mNativeTypeface);
    }

    public void drawText(String s, int i, int j, float f, float f1, Paint paint)
    {
        if((i | j | j - i | s.length() - j) < 0)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            throwIfHasHwBitmapInSwMode(paint);
            nDrawText(mNativeCanvasWrapper, s, i, j, f, f1, paint.mBidiFlags, paint.getNativeInstance(), paint.mNativeTypeface);
            return;
        }
    }

    public void drawText(char ac[], int i, int j, float f, float f1, Paint paint)
    {
        if((i | j | i + j | ac.length - i - j) < 0)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            throwIfHasHwBitmapInSwMode(paint);
            nDrawText(mNativeCanvasWrapper, ac, i, j, f, f1, paint.mBidiFlags, paint.getNativeInstance(), paint.mNativeTypeface);
            return;
        }
    }

    public void drawTextOnPath(String s, Path path, float f, float f1, Paint paint)
    {
        if(s.length() > 0)
        {
            throwIfHasHwBitmapInSwMode(paint);
            nDrawTextOnPath(mNativeCanvasWrapper, s, path.readOnlyNI(), f, f1, paint.mBidiFlags, paint.getNativeInstance(), paint.mNativeTypeface);
        }
    }

    public void drawTextOnPath(char ac[], int i, int j, Path path, float f, float f1, Paint paint)
    {
        if(i < 0 || i + j > ac.length)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            throwIfHasHwBitmapInSwMode(paint);
            nDrawTextOnPath(mNativeCanvasWrapper, ac, i, j, path.readOnlyNI(), f, f1, paint.mBidiFlags, paint.getNativeInstance(), paint.mNativeTypeface);
            return;
        }
    }

    public void drawTextRun(CharSequence charsequence, int i, int j, int k, int l, float f, float f1, 
            boolean flag, Paint paint)
    {
        if(charsequence == null)
            throw new NullPointerException("text is null");
        if(paint == null)
            throw new NullPointerException("paint is null");
        if((i | j | k | l | i - k | j - i | l - j | charsequence.length() - l) < 0)
            throw new IndexOutOfBoundsException();
        throwIfHasHwBitmapInSwMode(paint);
        if((charsequence instanceof String) || (charsequence instanceof SpannedString) || (charsequence instanceof SpannableString))
            nDrawTextRun(mNativeCanvasWrapper, charsequence.toString(), i, j, k, l, f, f1, flag, paint.getNativeInstance(), paint.mNativeTypeface);
        else
        if(charsequence instanceof GraphicsOperations)
        {
            ((GraphicsOperations)charsequence).drawTextRun(this, i, j, k, l, f, f1, flag, paint);
        } else
        {
            int i1 = l - k;
            char ac[] = TemporaryBuffer.obtain(i1);
            TextUtils.getChars(charsequence, k, l, ac, 0);
            nDrawTextRun(mNativeCanvasWrapper, ac, i - k, j - i, 0, i1, f, f1, flag, paint.getNativeInstance(), paint.mNativeTypeface);
            TemporaryBuffer.recycle(ac);
        }
    }

    public void drawTextRun(char ac[], int i, int j, int k, int l, float f, float f1, 
            boolean flag, Paint paint)
    {
        if(ac == null)
            throw new NullPointerException("text is null");
        if(paint == null)
            throw new NullPointerException("paint is null");
        if((i | j | k | l | i - k | (k + l) - (i + j) | ac.length - (k + l)) < 0)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            throwIfHasHwBitmapInSwMode(paint);
            nDrawTextRun(mNativeCanvasWrapper, ac, i, j, k, l, f, f1, flag, paint.getNativeInstance(), paint.mNativeTypeface);
            return;
        }
    }

    public void drawVertices(Canvas.VertexMode vertexmode, int i, float af[], int j, float af1[], int k, int ai[], 
            int l, short aword0[], int i1, int j1, Paint paint)
    {
        checkRange(af.length, j, i);
        if(isHardwareAccelerated())
            return;
        if(af1 != null)
            checkRange(af1.length, k, i);
        if(ai != null)
            checkRange(ai.length, l, i / 2);
        if(aword0 != null)
            checkRange(aword0.length, i1, j1);
        throwIfHasHwBitmapInSwMode(paint);
        nDrawVertices(mNativeCanvasWrapper, vertexmode.nativeInt, i, af, j, af1, k, ai, l, aword0, i1, j1, paint.getNativeInstance());
    }

    public boolean isHardwareAccelerated()
    {
        return false;
    }

    public boolean isHwBitmapsInSwModeEnabled()
    {
        return mAllowHwBitmapsInSwMode;
    }

    public void setHwBitmapsInSwModeEnabled(boolean flag)
    {
        mAllowHwBitmapsInSwMode = flag;
    }

    protected void throwIfCannotDraw(Bitmap bitmap)
    {
        if(bitmap.isRecycled())
            throw new RuntimeException((new StringBuilder()).append("Canvas: trying to use a recycled bitmap ").append(bitmap).toString());
        if(!bitmap.isPremultiplied() && bitmap.getConfig() == Bitmap.Config.ARGB_8888 && bitmap.hasAlpha())
        {
            throw new RuntimeException((new StringBuilder()).append("Canvas: trying to use a non-premultiplied bitmap ").append(bitmap).toString());
        } else
        {
            throwIfHwBitmapInSwMode(bitmap);
            return;
        }
    }

    private boolean mAllowHwBitmapsInSwMode;
    protected int mDensity;
    protected long mNativeCanvasWrapper;
    protected int mScreenDensity;
}
