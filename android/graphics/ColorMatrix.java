// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import java.util.Arrays;

public class ColorMatrix
{

    public ColorMatrix()
    {
        mArray = new float[20];
        reset();
    }

    public ColorMatrix(ColorMatrix colormatrix)
    {
        mArray = new float[20];
        System.arraycopy(colormatrix.mArray, 0, mArray, 0, 20);
    }

    public ColorMatrix(float af[])
    {
        mArray = new float[20];
        System.arraycopy(af, 0, mArray, 0, 20);
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof ColorMatrix))
            return false;
        obj = ((ColorMatrix)obj).mArray;
        for(int i = 0; i < 20; i++)
            if(obj[i] != mArray[i])
                return false;

        return true;
    }

    public final float[] getArray()
    {
        return mArray;
    }

    public void postConcat(ColorMatrix colormatrix)
    {
        setConcat(colormatrix, this);
    }

    public void preConcat(ColorMatrix colormatrix)
    {
        setConcat(this, colormatrix);
    }

    public void reset()
    {
        float af[] = mArray;
        Arrays.fill(af, 0.0F);
        af[18] = 1.0F;
        af[12] = 1.0F;
        af[6] = 1.0F;
        af[0] = 1.0F;
    }

    public void set(ColorMatrix colormatrix)
    {
        System.arraycopy(colormatrix.mArray, 0, mArray, 0, 20);
    }

    public void set(float af[])
    {
        System.arraycopy(af, 0, mArray, 0, 20);
    }

    public void setConcat(ColorMatrix colormatrix, ColorMatrix colormatrix1)
    {
        float af[];
        int i;
        if(colormatrix == this || colormatrix1 == this)
            af = new float[20];
        else
            af = mArray;
        colormatrix = colormatrix.mArray;
        colormatrix1 = colormatrix1.mArray;
        i = 0;
        for(int j = 0; j < 20;)
        {
            for(int k = 0; k < 4;)
            {
                af[i] = colormatrix[j + 0] * colormatrix1[k + 0] + colormatrix[j + 1] * colormatrix1[k + 5] + colormatrix[j + 2] * colormatrix1[k + 10] + colormatrix[j + 3] * colormatrix1[k + 15];
                k++;
                i++;
            }

            int l = i + 1;
            af[i] = colormatrix[j + 0] * colormatrix1[4] + colormatrix[j + 1] * colormatrix1[9] + colormatrix[j + 2] * colormatrix1[14] + colormatrix[j + 3] * colormatrix1[19] + colormatrix[j + 4];
            j += 5;
            i = l;
        }

        if(af != mArray)
            System.arraycopy(af, 0, mArray, 0, 20);
    }

    public void setRGB2YUV()
    {
        reset();
        float af[] = mArray;
        af[0] = 0.299F;
        af[1] = 0.587F;
        af[2] = 0.114F;
        af[5] = -0.16874F;
        af[6] = -0.33126F;
        af[7] = 0.5F;
        af[10] = 0.5F;
        af[11] = -0.41869F;
        af[12] = -0.08131F;
    }

    public void setRotate(int i, float f)
    {
        float f1;
        reset();
        double d = ((double)f * 3.1415926535897931D) / 180D;
        f1 = (float)Math.cos(d);
        f = (float)Math.sin(d);
        i;
        JVM INSTR tableswitch 0 2: default 56
    //                   0 64
    //                   1 104
    //                   2 144;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new RuntimeException();
_L2:
        float af[] = mArray;
        mArray[12] = f1;
        af[6] = f1;
        mArray[7] = f;
        mArray[11] = -f;
_L6:
        return;
_L3:
        float af1[] = mArray;
        mArray[12] = f1;
        af1[0] = f1;
        mArray[2] = -f;
        mArray[10] = f;
        continue; /* Loop/switch isn't completed */
_L4:
        float af2[] = mArray;
        mArray[6] = f1;
        af2[0] = f1;
        mArray[1] = f;
        mArray[5] = -f;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setSaturation(float f)
    {
        reset();
        float af[] = mArray;
        float f1 = 1.0F - f;
        float f2 = 0.213F * f1;
        float f3 = 0.715F * f1;
        f1 = 0.072F * f1;
        af[0] = f2 + f;
        af[1] = f3;
        af[2] = f1;
        af[5] = f2;
        af[6] = f3 + f;
        af[7] = f1;
        af[10] = f2;
        af[11] = f3;
        af[12] = f1 + f;
    }

    public void setScale(float f, float f1, float f2, float f3)
    {
        float af[] = mArray;
        for(int i = 19; i > 0; i--)
            af[i] = 0.0F;

        af[0] = f;
        af[6] = f1;
        af[12] = f2;
        af[18] = f3;
    }

    public void setYUV2RGB()
    {
        reset();
        float af[] = mArray;
        af[2] = 1.402F;
        af[5] = 1.0F;
        af[6] = -0.34414F;
        af[7] = -0.71414F;
        af[10] = 1.0F;
        af[11] = 1.772F;
        af[12] = 0.0F;
    }

    private final float mArray[];
}
