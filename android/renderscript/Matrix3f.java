// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Matrix3f
{

    public Matrix3f()
    {
        mMat = new float[9];
        loadIdentity();
    }

    public Matrix3f(float af[])
    {
        mMat = new float[9];
        System.arraycopy(af, 0, mMat, 0, mMat.length);
    }

    public float get(int i, int j)
    {
        return mMat[i * 3 + j];
    }

    public float[] getArray()
    {
        return mMat;
    }

    public void load(Matrix3f matrix3f)
    {
        System.arraycopy(matrix3f.getArray(), 0, mMat, 0, mMat.length);
    }

    public void loadIdentity()
    {
        mMat[0] = 1.0F;
        mMat[1] = 0.0F;
        mMat[2] = 0.0F;
        mMat[3] = 0.0F;
        mMat[4] = 1.0F;
        mMat[5] = 0.0F;
        mMat[6] = 0.0F;
        mMat[7] = 0.0F;
        mMat[8] = 1.0F;
    }

    public void loadMultiply(Matrix3f matrix3f, Matrix3f matrix3f1)
    {
        for(int i = 0; i < 3; i++)
        {
            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            for(int j = 0; j < 3; j++)
            {
                float f3 = matrix3f1.get(i, j);
                f += matrix3f.get(j, 0) * f3;
                f1 += matrix3f.get(j, 1) * f3;
                f2 += matrix3f.get(j, 2) * f3;
            }

            set(i, 0, f);
            set(i, 1, f1);
            set(i, 2, f2);
        }

    }

    public void loadRotate(float f)
    {
        loadIdentity();
        float f1 = f * 0.01745329F;
        f = (float)Math.cos(f1);
        f1 = (float)Math.sin(f1);
        mMat[0] = f;
        mMat[1] = -f1;
        mMat[3] = f1;
        mMat[4] = f;
    }

    public void loadRotate(float f, float f1, float f2, float f3)
    {
        f *= 0.01745329F;
        float f4 = (float)Math.cos(f);
        float f5 = (float)Math.sin(f);
        float f6 = (float)Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
        boolean flag;
        float f7;
        float f8;
        float f9;
        float f10;
        if(f6 != 1.0F)
            flag = true;
        else
            flag = false;
        f7 = f1;
        f8 = f2;
        f = f3;
        if(!flag)
        {
            f = 1.0F / f6;
            f7 = f1 * f;
            f8 = f2 * f;
            f = f3 * f;
        }
        f9 = 1.0F - f4;
        f6 = f7 * f8;
        f10 = f8 * f;
        f3 = f * f7;
        f2 = f7 * f5;
        f1 = f8 * f5;
        f5 = f * f5;
        mMat[0] = f7 * f7 * f9 + f4;
        mMat[3] = f6 * f9 - f5;
        mMat[6] = f3 * f9 + f1;
        mMat[1] = f6 * f9 + f5;
        mMat[4] = f8 * f8 * f9 + f4;
        mMat[7] = f10 * f9 - f2;
        mMat[2] = f3 * f9 - f1;
        mMat[5] = f10 * f9 + f2;
        mMat[8] = f * f * f9 + f4;
    }

    public void loadScale(float f, float f1)
    {
        loadIdentity();
        mMat[0] = f;
        mMat[4] = f1;
    }

    public void loadScale(float f, float f1, float f2)
    {
        loadIdentity();
        mMat[0] = f;
        mMat[4] = f1;
        mMat[8] = f2;
    }

    public void loadTranslate(float f, float f1)
    {
        loadIdentity();
        mMat[6] = f;
        mMat[7] = f1;
    }

    public void multiply(Matrix3f matrix3f)
    {
        Matrix3f matrix3f1 = new Matrix3f();
        matrix3f1.loadMultiply(this, matrix3f);
        load(matrix3f1);
    }

    public void rotate(float f)
    {
        Matrix3f matrix3f = new Matrix3f();
        matrix3f.loadRotate(f);
        multiply(matrix3f);
    }

    public void rotate(float f, float f1, float f2, float f3)
    {
        Matrix3f matrix3f = new Matrix3f();
        matrix3f.loadRotate(f, f1, f2, f3);
        multiply(matrix3f);
    }

    public void scale(float f, float f1)
    {
        Matrix3f matrix3f = new Matrix3f();
        matrix3f.loadScale(f, f1);
        multiply(matrix3f);
    }

    public void scale(float f, float f1, float f2)
    {
        Matrix3f matrix3f = new Matrix3f();
        matrix3f.loadScale(f, f1, f2);
        multiply(matrix3f);
    }

    public void set(int i, int j, float f)
    {
        mMat[i * 3 + j] = f;
    }

    public void translate(float f, float f1)
    {
        Matrix3f matrix3f = new Matrix3f();
        matrix3f.loadTranslate(f, f1);
        multiply(matrix3f);
    }

    public void transpose()
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = i + 1; j < 3; j++)
            {
                float f = mMat[i * 3 + j];
                mMat[i * 3 + j] = mMat[j * 3 + i];
                mMat[j * 3 + i] = f;
            }

        }

    }

    final float mMat[];
}
