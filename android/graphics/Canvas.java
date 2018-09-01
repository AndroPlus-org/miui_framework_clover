// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import javax.microedition.khronos.opengles.GL;
import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.graphics:
//            BaseCanvas, Bitmap, Rect, RectF, 
//            Path, Matrix, Picture, Paint, 
//            DrawFilter, Region, NinePatch

public class Canvas extends BaseCanvas
{
    public static final class EdgeType extends Enum
    {

        public static EdgeType valueOf(String s)
        {
            return (EdgeType)Enum.valueOf(android/graphics/Canvas$EdgeType, s);
        }

        public static EdgeType[] values()
        {
            return $VALUES;
        }

        private static final EdgeType $VALUES[];
        public static final EdgeType AA;
        public static final EdgeType BW;
        public final int nativeInt;

        static 
        {
            BW = new EdgeType("BW", 0, 0);
            AA = new EdgeType("AA", 1, 1);
            $VALUES = (new EdgeType[] {
                BW, AA
            });
        }

        private EdgeType(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }

    private static class NoImagePreloadHolder
    {

        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(android/graphics/Canvas.getClassLoader(), Canvas._2D_wrap0(), 525L);


        private NoImagePreloadHolder()
        {
        }
    }

    public static final class VertexMode extends Enum
    {

        public static VertexMode valueOf(String s)
        {
            return (VertexMode)Enum.valueOf(android/graphics/Canvas$VertexMode, s);
        }

        public static VertexMode[] values()
        {
            return $VALUES;
        }

        private static final VertexMode $VALUES[];
        public static final VertexMode TRIANGLES;
        public static final VertexMode TRIANGLE_FAN;
        public static final VertexMode TRIANGLE_STRIP;
        public final int nativeInt;

        static 
        {
            TRIANGLES = new VertexMode("TRIANGLES", 0, 0);
            TRIANGLE_STRIP = new VertexMode("TRIANGLE_STRIP", 1, 1);
            TRIANGLE_FAN = new VertexMode("TRIANGLE_FAN", 2, 2);
            $VALUES = (new VertexMode[] {
                TRIANGLES, TRIANGLE_STRIP, TRIANGLE_FAN
            });
        }

        private VertexMode(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    static long _2D_wrap0()
    {
        return nGetNativeFinalizer();
    }

    public Canvas()
    {
        if(!isHardwareAccelerated())
        {
            mNativeCanvasWrapper = nInitRaster(null);
            mFinalizer = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativeCanvasWrapper);
        } else
        {
            mFinalizer = null;
        }
    }

    public Canvas(long l)
    {
        if(l == 0L)
        {
            throw new IllegalStateException();
        } else
        {
            mNativeCanvasWrapper = l;
            mFinalizer = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativeCanvasWrapper);
            mDensity = Bitmap.getDefaultDensity();
            return;
        }
    }

    public Canvas(Bitmap bitmap)
    {
        if(!bitmap.isMutable())
        {
            throw new IllegalStateException("Immutable bitmap passed to Canvas constructor");
        } else
        {
            throwIfCannotDraw(bitmap);
            mNativeCanvasWrapper = nInitRaster(bitmap);
            mFinalizer = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativeCanvasWrapper);
            mBitmap = bitmap;
            mDensity = bitmap.mDensity;
            return;
        }
    }

    public static void freeCaches()
    {
        nFreeCaches();
    }

    public static void freeTextLayoutCaches()
    {
        nFreeTextLayoutCaches();
    }

    private static native boolean nClipPath(long l, long l1, int i);

    private static native boolean nClipRect(long l, float f, float f1, float f2, float f3, int i);

    private static native void nConcat(long l, long l1);

    private static native void nFreeCaches();

    private static native void nFreeTextLayoutCaches();

    private static native boolean nGetClipBounds(long l, Rect rect);

    private static native int nGetHeight(long l);

    private static native void nGetMatrix(long l, long l1);

    private static native long nGetNativeFinalizer();

    private static native int nGetSaveCount(long l);

    private static native int nGetWidth(long l);

    private static native long nInitRaster(Bitmap bitmap);

    private static native boolean nIsOpaque(long l);

    private static native boolean nQuickReject(long l, float f, float f1, float f2, float f3);

    private static native boolean nQuickReject(long l, long l1);

    private static native boolean nRestore(long l);

    private static native void nRestoreToCount(long l, int i);

    private static native void nRotate(long l, float f);

    private static native int nSave(long l, int i);

    private static native int nSaveLayer(long l, float f, float f1, float f2, float f3, long l1, 
            int i);

    private static native int nSaveLayerAlpha(long l, float f, float f1, float f2, float f3, int i, int j);

    private static native void nScale(long l, float f, float f1);

    private static native void nSetBitmap(long l, Bitmap bitmap);

    private static native void nSetDrawFilter(long l, long l1);

    private static native void nSetHighContrastText(long l, boolean flag);

    private static native void nSetMatrix(long l, long l1);

    private static native void nSkew(long l, float f, float f1);

    private static native void nTranslate(long l, float f, float f1);

    public boolean clipOutPath(Path path)
    {
        return clipPath(path, Region.Op.DIFFERENCE);
    }

    public boolean clipOutRect(float f, float f1, float f2, float f3)
    {
        return nClipRect(mNativeCanvasWrapper, f, f1, f2, f3, Region.Op.DIFFERENCE.nativeInt);
    }

    public boolean clipOutRect(int i, int j, int k, int l)
    {
        return nClipRect(mNativeCanvasWrapper, i, j, k, l, Region.Op.DIFFERENCE.nativeInt);
    }

    public boolean clipOutRect(Rect rect)
    {
        return nClipRect(mNativeCanvasWrapper, rect.left, rect.top, rect.right, rect.bottom, Region.Op.DIFFERENCE.nativeInt);
    }

    public boolean clipOutRect(RectF rectf)
    {
        return nClipRect(mNativeCanvasWrapper, rectf.left, rectf.top, rectf.right, rectf.bottom, Region.Op.DIFFERENCE.nativeInt);
    }

    public boolean clipPath(Path path)
    {
        return clipPath(path, Region.Op.INTERSECT);
    }

    public boolean clipPath(Path path, Region.Op op)
    {
        return nClipPath(mNativeCanvasWrapper, path.readOnlyNI(), op.nativeInt);
    }

    public boolean clipRect(float f, float f1, float f2, float f3)
    {
        return nClipRect(mNativeCanvasWrapper, f, f1, f2, f3, Region.Op.INTERSECT.nativeInt);
    }

    public boolean clipRect(float f, float f1, float f2, float f3, Region.Op op)
    {
        return nClipRect(mNativeCanvasWrapper, f, f1, f2, f3, op.nativeInt);
    }

    public boolean clipRect(int i, int j, int k, int l)
    {
        return nClipRect(mNativeCanvasWrapper, i, j, k, l, Region.Op.INTERSECT.nativeInt);
    }

    public boolean clipRect(Rect rect)
    {
        return nClipRect(mNativeCanvasWrapper, rect.left, rect.top, rect.right, rect.bottom, Region.Op.INTERSECT.nativeInt);
    }

    public boolean clipRect(Rect rect, Region.Op op)
    {
        return nClipRect(mNativeCanvasWrapper, rect.left, rect.top, rect.right, rect.bottom, op.nativeInt);
    }

    public boolean clipRect(RectF rectf)
    {
        return nClipRect(mNativeCanvasWrapper, rectf.left, rectf.top, rectf.right, rectf.bottom, Region.Op.INTERSECT.nativeInt);
    }

    public boolean clipRect(RectF rectf, Region.Op op)
    {
        return nClipRect(mNativeCanvasWrapper, rectf.left, rectf.top, rectf.right, rectf.bottom, op.nativeInt);
    }

    public boolean clipRegion(Region region)
    {
        return false;
    }

    public boolean clipRegion(Region region, Region.Op op)
    {
        return false;
    }

    public void concat(Matrix matrix)
    {
        if(matrix != null)
            nConcat(mNativeCanvasWrapper, matrix.native_instance);
    }

    public void drawARGB(int i, int j, int k, int l)
    {
        super.drawARGB(i, j, k, l);
    }

    public void drawArc(float f, float f1, float f2, float f3, float f4, float f5, boolean flag, 
            Paint paint)
    {
        super.drawArc(f, f1, f2, f3, f4, f5, flag, paint);
    }

    public void drawArc(RectF rectf, float f, float f1, boolean flag, Paint paint)
    {
        super.drawArc(rectf, f, f1, flag, paint);
    }

    public void drawBitmap(Bitmap bitmap, float f, float f1, Paint paint)
    {
        super.drawBitmap(bitmap, f, f1, paint);
    }

    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint)
    {
        super.drawBitmap(bitmap, matrix, paint);
    }

    public void drawBitmap(Bitmap bitmap, Rect rect, Rect rect1, Paint paint)
    {
        super.drawBitmap(bitmap, rect, rect1, paint);
    }

    public void drawBitmap(Bitmap bitmap, Rect rect, RectF rectf, Paint paint)
    {
        super.drawBitmap(bitmap, rect, rectf, paint);
    }

    public void drawBitmap(int ai[], int i, int j, float f, float f1, int k, int l, 
            boolean flag, Paint paint)
    {
        super.drawBitmap(ai, i, j, f, f1, k, l, flag, paint);
    }

    public void drawBitmap(int ai[], int i, int j, int k, int l, int i1, int j1, 
            boolean flag, Paint paint)
    {
        super.drawBitmap(ai, i, j, k, l, i1, j1, flag, paint);
    }

    public void drawBitmapMesh(Bitmap bitmap, int i, int j, float af[], int k, int ai[], int l, 
            Paint paint)
    {
        super.drawBitmapMesh(bitmap, i, j, af, k, ai, l, paint);
    }

    public void drawCircle(float f, float f1, float f2, Paint paint)
    {
        super.drawCircle(f, f1, f2, paint);
    }

    public void drawColor(int i)
    {
        super.drawColor(i);
    }

    public void drawColor(int i, PorterDuff.Mode mode)
    {
        super.drawColor(i, mode);
    }

    public void drawLine(float f, float f1, float f2, float f3, Paint paint)
    {
        super.drawLine(f, f1, f2, f3, paint);
    }

    public void drawLines(float af[], int i, int j, Paint paint)
    {
        super.drawLines(af, i, j, paint);
    }

    public void drawLines(float af[], Paint paint)
    {
        super.drawLines(af, paint);
    }

    public void drawOval(float f, float f1, float f2, float f3, Paint paint)
    {
        super.drawOval(f, f1, f2, f3, paint);
    }

    public void drawOval(RectF rectf, Paint paint)
    {
        super.drawOval(rectf, paint);
    }

    public void drawPaint(Paint paint)
    {
        super.drawPaint(paint);
    }

    public void drawPatch(NinePatch ninepatch, Rect rect, Paint paint)
    {
        super.drawPatch(ninepatch, rect, paint);
    }

    public void drawPatch(NinePatch ninepatch, RectF rectf, Paint paint)
    {
        super.drawPatch(ninepatch, rectf, paint);
    }

    public void drawPath(Path path, Paint paint)
    {
        super.drawPath(path, paint);
    }

    public void drawPicture(Picture picture)
    {
        picture.endRecording();
        int i = save();
        picture.draw(this);
        restoreToCount(i);
    }

    public void drawPicture(Picture picture, Rect rect)
    {
        save();
        translate(rect.left, rect.top);
        if(picture.getWidth() > 0 && picture.getHeight() > 0)
            scale((float)rect.width() / (float)picture.getWidth(), (float)rect.height() / (float)picture.getHeight());
        drawPicture(picture);
        restore();
    }

    public void drawPicture(Picture picture, RectF rectf)
    {
        save();
        translate(rectf.left, rectf.top);
        if(picture.getWidth() > 0 && picture.getHeight() > 0)
            scale(rectf.width() / (float)picture.getWidth(), rectf.height() / (float)picture.getHeight());
        drawPicture(picture);
        restore();
    }

    public void drawPoint(float f, float f1, Paint paint)
    {
        super.drawPoint(f, f1, paint);
    }

    public void drawPoints(float af[], int i, int j, Paint paint)
    {
        super.drawPoints(af, i, j, paint);
    }

    public void drawPoints(float af[], Paint paint)
    {
        super.drawPoints(af, paint);
    }

    public void drawPosText(String s, float af[], Paint paint)
    {
        super.drawPosText(s, af, paint);
    }

    public void drawPosText(char ac[], int i, int j, float af[], Paint paint)
    {
        super.drawPosText(ac, i, j, af, paint);
    }

    public void drawRGB(int i, int j, int k)
    {
        super.drawRGB(i, j, k);
    }

    public void drawRect(float f, float f1, float f2, float f3, Paint paint)
    {
        super.drawRect(f, f1, f2, f3, paint);
    }

    public void drawRect(Rect rect, Paint paint)
    {
        super.drawRect(rect, paint);
    }

    public void drawRect(RectF rectf, Paint paint)
    {
        super.drawRect(rectf, paint);
    }

    public void drawRoundRect(float f, float f1, float f2, float f3, float f4, float f5, Paint paint)
    {
        super.drawRoundRect(f, f1, f2, f3, f4, f5, paint);
    }

    public void drawRoundRect(RectF rectf, float f, float f1, Paint paint)
    {
        super.drawRoundRect(rectf, f, f1, paint);
    }

    public void drawText(CharSequence charsequence, int i, int j, float f, float f1, Paint paint)
    {
        super.drawText(charsequence, i, j, f, f1, paint);
    }

    public void drawText(String s, float f, float f1, Paint paint)
    {
        super.drawText(s, f, f1, paint);
    }

    public void drawText(String s, int i, int j, float f, float f1, Paint paint)
    {
        super.drawText(s, i, j, f, f1, paint);
    }

    public void drawText(char ac[], int i, int j, float f, float f1, Paint paint)
    {
        super.drawText(ac, i, j, f, f1, paint);
    }

    public void drawTextOnPath(String s, Path path, float f, float f1, Paint paint)
    {
        super.drawTextOnPath(s, path, f, f1, paint);
    }

    public void drawTextOnPath(char ac[], int i, int j, Path path, float f, float f1, Paint paint)
    {
        super.drawTextOnPath(ac, i, j, path, f, f1, paint);
    }

    public void drawTextRun(CharSequence charsequence, int i, int j, int k, int l, float f, float f1, 
            boolean flag, Paint paint)
    {
        super.drawTextRun(charsequence, i, j, k, l, f, f1, flag, paint);
    }

    public void drawTextRun(char ac[], int i, int j, int k, int l, float f, float f1, 
            boolean flag, Paint paint)
    {
        super.drawTextRun(ac, i, j, k, l, f, f1, flag, paint);
    }

    public void drawVertices(VertexMode vertexmode, int i, float af[], int j, float af1[], int k, int ai[], 
            int l, short aword0[], int i1, int j1, Paint paint)
    {
        super.drawVertices(vertexmode, i, af, j, af1, k, ai, l, aword0, i1, j1, paint);
    }

    public final Rect getClipBounds()
    {
        Rect rect = new Rect();
        getClipBounds(rect);
        return rect;
    }

    public boolean getClipBounds(Rect rect)
    {
        return nGetClipBounds(mNativeCanvasWrapper, rect);
    }

    public int getDensity()
    {
        return mDensity;
    }

    public DrawFilter getDrawFilter()
    {
        return mDrawFilter;
    }

    protected GL getGL()
    {
        return null;
    }

    public int getHeight()
    {
        return nGetHeight(mNativeCanvasWrapper);
    }

    public final Matrix getMatrix()
    {
        Matrix matrix = new Matrix();
        getMatrix(matrix);
        return matrix;
    }

    public void getMatrix(Matrix matrix)
    {
        nGetMatrix(mNativeCanvasWrapper, matrix.native_instance);
    }

    public int getMaximumBitmapHeight()
    {
        return 32766;
    }

    public int getMaximumBitmapWidth()
    {
        return 32766;
    }

    public long getNativeCanvasWrapper()
    {
        return mNativeCanvasWrapper;
    }

    public int getSaveCount()
    {
        return nGetSaveCount(mNativeCanvasWrapper);
    }

    public int getWidth()
    {
        return nGetWidth(mNativeCanvasWrapper);
    }

    public void insertInorderBarrier()
    {
    }

    public void insertReorderBarrier()
    {
    }

    public boolean isHardwareAccelerated()
    {
        return false;
    }

    public boolean isOpaque()
    {
        return nIsOpaque(mNativeCanvasWrapper);
    }

    public boolean isRecordingFor(Object obj)
    {
        return false;
    }

    public boolean quickReject(float f, float f1, float f2, float f3, EdgeType edgetype)
    {
        return nQuickReject(mNativeCanvasWrapper, f, f1, f2, f3);
    }

    public boolean quickReject(Path path, EdgeType edgetype)
    {
        return nQuickReject(mNativeCanvasWrapper, path.readOnlyNI());
    }

    public boolean quickReject(RectF rectf, EdgeType edgetype)
    {
        return nQuickReject(mNativeCanvasWrapper, rectf.left, rectf.top, rectf.right, rectf.bottom);
    }

    public void release()
    {
        mNativeCanvasWrapper = 0L;
        if(mFinalizer != null)
        {
            mFinalizer.run();
            mFinalizer = null;
        }
    }

    public void restore()
    {
        if(!nRestore(mNativeCanvasWrapper) && (!sCompatibilityRestore || isHardwareAccelerated() ^ true))
            throw new IllegalStateException("Underflow in restore - more restores than saves");
        else
            return;
    }

    public void restoreToCount(int i)
    {
        int j = i;
        if(i < 1)
        {
            if(!sCompatibilityRestore || isHardwareAccelerated() ^ true)
                throw new IllegalArgumentException("Underflow in restoreToCount - more restores than saves");
            j = 1;
        }
        nRestoreToCount(mNativeCanvasWrapper, j);
    }

    public void rotate(float f)
    {
        if(f == 0.0F)
        {
            return;
        } else
        {
            nRotate(mNativeCanvasWrapper, f);
            return;
        }
    }

    public final void rotate(float f, float f1, float f2)
    {
        if(f == 0.0F)
        {
            return;
        } else
        {
            translate(f1, f2);
            rotate(f);
            translate(-f1, -f2);
            return;
        }
    }

    public int save()
    {
        return nSave(mNativeCanvasWrapper, 3);
    }

    public int save(int i)
    {
        return nSave(mNativeCanvasWrapper, i);
    }

    public int saveLayer(float f, float f1, float f2, float f3, Paint paint)
    {
        return saveLayer(f, f1, f2, f3, paint, 31);
    }

    public int saveLayer(float f, float f1, float f2, float f3, Paint paint, int i)
    {
        long l = mNativeCanvasWrapper;
        long l1;
        if(paint != null)
            l1 = paint.getNativeInstance();
        else
            l1 = 0L;
        return nSaveLayer(l, f, f1, f2, f3, l1, i);
    }

    public int saveLayer(RectF rectf, Paint paint)
    {
        return saveLayer(rectf, paint, 31);
    }

    public int saveLayer(RectF rectf, Paint paint, int i)
    {
        RectF rectf1 = rectf;
        if(rectf == null)
            rectf1 = new RectF(getClipBounds());
        return saveLayer(rectf1.left, rectf1.top, rectf1.right, rectf1.bottom, paint, i);
    }

    public int saveLayerAlpha(float f, float f1, float f2, float f3, int i)
    {
        return saveLayerAlpha(f, f1, f2, f3, i, 31);
    }

    public int saveLayerAlpha(float f, float f1, float f2, float f3, int i, int j)
    {
        i = Math.min(255, Math.max(0, i));
        return nSaveLayerAlpha(mNativeCanvasWrapper, f, f1, f2, f3, i, j);
    }

    public int saveLayerAlpha(RectF rectf, int i)
    {
        return saveLayerAlpha(rectf, i, 31);
    }

    public int saveLayerAlpha(RectF rectf, int i, int j)
    {
        RectF rectf1 = rectf;
        if(rectf == null)
            rectf1 = new RectF(getClipBounds());
        return saveLayerAlpha(rectf1.left, rectf1.top, rectf1.right, rectf1.bottom, i, j);
    }

    public void scale(float f, float f1)
    {
        if(f == 1.0F && f1 == 1.0F)
        {
            return;
        } else
        {
            nScale(mNativeCanvasWrapper, f, f1);
            return;
        }
    }

    public final void scale(float f, float f1, float f2, float f3)
    {
        if(f == 1.0F && f1 == 1.0F)
        {
            return;
        } else
        {
            translate(f2, f3);
            scale(f, f1);
            translate(-f2, -f3);
            return;
        }
    }

    public void setBitmap(Bitmap bitmap)
    {
        if(isHardwareAccelerated())
            throw new RuntimeException("Can't set a bitmap device on a HW accelerated canvas");
        Object obj = null;
        Matrix matrix = obj;
        if(bitmap != null)
        {
            matrix = obj;
            if(sCompatibilitySetBitmap)
                matrix = getMatrix();
        }
        if(bitmap == null)
        {
            nSetBitmap(mNativeCanvasWrapper, null);
            mDensity = 0;
        } else
        {
            if(!bitmap.isMutable())
                throw new IllegalStateException();
            throwIfCannotDraw(bitmap);
            nSetBitmap(mNativeCanvasWrapper, bitmap);
            mDensity = bitmap.mDensity;
        }
        if(matrix != null)
            setMatrix(matrix);
        mBitmap = bitmap;
    }

    public void setDensity(int i)
    {
        if(mBitmap != null)
            mBitmap.setDensity(i);
        mDensity = i;
    }

    public void setDrawFilter(DrawFilter drawfilter)
    {
        long l = 0L;
        if(drawfilter != null)
            l = drawfilter.mNativeInt;
        mDrawFilter = drawfilter;
        nSetDrawFilter(mNativeCanvasWrapper, l);
    }

    public void setHighContrastText(boolean flag)
    {
        nSetHighContrastText(mNativeCanvasWrapper, flag);
    }

    public void setMatrix(Matrix matrix)
    {
        long l = mNativeCanvasWrapper;
        long l1;
        if(matrix == null)
            l1 = 0L;
        else
            l1 = matrix.native_instance;
        nSetMatrix(l, l1);
    }

    public void setScreenDensity(int i)
    {
        mScreenDensity = i;
    }

    public void skew(float f, float f1)
    {
        if(f == 0.0F && f1 == 0.0F)
        {
            return;
        } else
        {
            nSkew(mNativeCanvasWrapper, f, f1);
            return;
        }
    }

    public void translate(float f, float f1)
    {
        if(f == 0.0F && f1 == 0.0F)
        {
            return;
        } else
        {
            nTranslate(mNativeCanvasWrapper, f, f1);
            return;
        }
    }

    public static final int ALL_SAVE_FLAG = 31;
    public static final int CLIP_SAVE_FLAG = 2;
    public static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
    public static final int FULL_COLOR_LAYER_SAVE_FLAG = 8;
    public static final int HAS_ALPHA_LAYER_SAVE_FLAG = 4;
    public static final int MATRIX_SAVE_FLAG = 1;
    private static final int MAXMIMUM_BITMAP_SIZE = 32766;
    private static final long NATIVE_ALLOCATION_SIZE = 525L;
    public static boolean sCompatibilityRestore = false;
    public static boolean sCompatibilitySetBitmap = false;
    private Bitmap mBitmap;
    private DrawFilter mDrawFilter;
    private Runnable mFinalizer;

}
