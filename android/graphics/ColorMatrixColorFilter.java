// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            ColorFilter, ColorMatrix

public class ColorMatrixColorFilter extends ColorFilter
{

    public ColorMatrixColorFilter(ColorMatrix colormatrix)
    {
        mMatrix = new ColorMatrix();
        mMatrix.set(colormatrix);
    }

    public ColorMatrixColorFilter(float af[])
    {
        mMatrix = new ColorMatrix();
        if(af.length < 20)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            mMatrix.set(af);
            return;
        }
    }

    private static native long nativeColorMatrixFilter(float af[]);

    long createNativeInstance()
    {
        return nativeColorMatrixFilter(mMatrix.getArray());
    }

    public void getColorMatrix(ColorMatrix colormatrix)
    {
        colormatrix.set(mMatrix);
    }

    public void setColorMatrix(ColorMatrix colormatrix)
    {
        discardNativeInstance();
        if(colormatrix == null)
            mMatrix.reset();
        else
            mMatrix.set(colormatrix);
    }

    public void setColorMatrixArray(float af[])
    {
        discardNativeInstance();
        if(af == null)
        {
            mMatrix.reset();
        } else
        {
            if(af.length < 20)
                throw new ArrayIndexOutOfBoundsException();
            mMatrix.set(af);
        }
    }

    private final ColorMatrix mMatrix;
}
