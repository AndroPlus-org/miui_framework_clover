// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Canvas, Matrix

public class Camera
{

    public Camera()
    {
        nativeConstructor();
    }

    private native void nativeApplyToCanvas(long l);

    private native void nativeConstructor();

    private native void nativeDestructor();

    private native void nativeGetMatrix(long l);

    public void applyToCanvas(Canvas canvas)
    {
        if(canvas.isHardwareAccelerated())
        {
            if(mMatrix == null)
                mMatrix = new Matrix();
            getMatrix(mMatrix);
            canvas.concat(mMatrix);
        } else
        {
            nativeApplyToCanvas(canvas.getNativeCanvasWrapper());
        }
    }

    public native float dotWithNormal(float f, float f1, float f2);

    protected void finalize()
        throws Throwable
    {
        nativeDestructor();
        native_instance = 0L;
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public native float getLocationX();

    public native float getLocationY();

    public native float getLocationZ();

    public void getMatrix(Matrix matrix)
    {
        nativeGetMatrix(matrix.native_instance);
    }

    public native void restore();

    public native void rotate(float f, float f1, float f2);

    public native void rotateX(float f);

    public native void rotateY(float f);

    public native void rotateZ(float f);

    public native void save();

    public native void setLocation(float f, float f1, float f2);

    public native void translate(float f, float f1, float f2);

    private Matrix mMatrix;
    long native_instance;
}
