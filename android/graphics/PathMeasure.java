// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Path, Matrix

public class PathMeasure
{

    public PathMeasure()
    {
        mPath = null;
        native_instance = native_create(0L, false);
    }

    public PathMeasure(Path path, boolean flag)
    {
        mPath = path;
        long l;
        if(path != null)
            l = path.readOnlyNI();
        else
            l = 0L;
        native_instance = native_create(l, flag);
    }

    private static native long native_create(long l, boolean flag);

    private static native void native_destroy(long l);

    private static native float native_getLength(long l);

    private static native boolean native_getMatrix(long l, float f, long l1, int i);

    private static native boolean native_getPosTan(long l, float f, float af[], float af1[]);

    private static native boolean native_getSegment(long l, float f, float f1, long l1, boolean flag);

    private static native boolean native_isClosed(long l);

    private static native boolean native_nextContour(long l);

    private static native void native_setPath(long l, long l1, boolean flag);

    protected void finalize()
        throws Throwable
    {
        native_destroy(native_instance);
        native_instance = 0L;
    }

    public float getLength()
    {
        return native_getLength(native_instance);
    }

    public boolean getMatrix(float f, Matrix matrix, int i)
    {
        return native_getMatrix(native_instance, f, matrix.native_instance, i);
    }

    public boolean getPosTan(float f, float af[], float af1[])
    {
        while(af != null && af.length < 2 || af1 != null && af1.length < 2) 
            throw new ArrayIndexOutOfBoundsException();
        return native_getPosTan(native_instance, f, af, af1);
    }

    public boolean getSegment(float f, float f1, Path path, boolean flag)
    {
        float f2 = getLength();
        float f3 = f;
        if(f < 0.0F)
            f3 = 0.0F;
        f = f1;
        if(f1 > f2)
            f = f2;
        if(f3 >= f)
            return false;
        else
            return native_getSegment(native_instance, f3, f, path.mutateNI(), flag);
    }

    public boolean isClosed()
    {
        return native_isClosed(native_instance);
    }

    public boolean nextContour()
    {
        return native_nextContour(native_instance);
    }

    public void setPath(Path path, boolean flag)
    {
        mPath = path;
        long l = native_instance;
        long l1;
        if(path != null)
            l1 = path.readOnlyNI();
        else
            l1 = 0L;
        native_setPath(l, l1, flag);
    }

    public static final int POSITION_MATRIX_FLAG = 1;
    public static final int TANGENT_MATRIX_FLAG = 2;
    private Path mPath;
    private long native_instance;
}
