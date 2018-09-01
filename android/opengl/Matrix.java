// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;


public class Matrix
{

    public Matrix()
    {
    }

    public static void frustumM(float af[], int i, float f, float f1, float f2, float f3, float f4, float f5)
    {
        if(f == f1)
            throw new IllegalArgumentException("left == right");
        if(f3 == f2)
            throw new IllegalArgumentException("top == bottom");
        if(f4 == f5)
            throw new IllegalArgumentException("near == far");
        if(f4 <= 0.0F)
            throw new IllegalArgumentException("near <= 0.0f");
        if(f5 <= 0.0F)
        {
            throw new IllegalArgumentException("far <= 0.0f");
        } else
        {
            float f6 = 1.0F / (f1 - f);
            float f7 = 1.0F / (f3 - f2);
            float f8 = 1.0F / (f4 - f5);
            af[i + 0] = 2.0F * (f4 * f6);
            af[i + 5] = 2.0F * (f4 * f7);
            af[i + 8] = (f1 + f) * f6;
            af[i + 9] = (f3 + f2) * f7;
            af[i + 10] = (f5 + f4) * f8;
            af[i + 14] = 2.0F * (f5 * f4 * f8);
            af[i + 11] = -1F;
            af[i + 1] = 0.0F;
            af[i + 2] = 0.0F;
            af[i + 3] = 0.0F;
            af[i + 4] = 0.0F;
            af[i + 6] = 0.0F;
            af[i + 7] = 0.0F;
            af[i + 12] = 0.0F;
            af[i + 13] = 0.0F;
            af[i + 15] = 0.0F;
            return;
        }
    }

    public static boolean invertM(float af[], int i, float af1[], int j)
    {
        float f = af1[j + 0];
        float f1 = af1[j + 1];
        float f2 = af1[j + 2];
        float f3 = af1[j + 3];
        float f4 = af1[j + 4];
        float f5 = af1[j + 5];
        float f6 = af1[j + 6];
        float f7 = af1[j + 7];
        float f8 = af1[j + 8];
        float f9 = af1[j + 9];
        float f10 = af1[j + 10];
        float f11 = af1[j + 11];
        float f12 = af1[j + 12];
        float f13 = af1[j + 13];
        float f14 = af1[j + 14];
        float f15 = af1[j + 15];
        float f16 = f10 * f15;
        float f17 = f14 * f11;
        float f18 = f6 * f15;
        float f19 = f14 * f7;
        float f20 = f6 * f11;
        float f21 = f10 * f7;
        float f22 = f2 * f15;
        float f23 = f14 * f3;
        float f24 = f2 * f11;
        float f25 = f10 * f3;
        float f26 = f2 * f7;
        float f27 = f6 * f3;
        float f28 = (f16 * f5 + f19 * f9 + f20 * f13) - (f17 * f5 + f18 * f9 + f21 * f13);
        float f29 = (f17 * f1 + f22 * f9 + f25 * f13) - (f16 * f1 + f23 * f9 + f24 * f13);
        float f30 = (f18 * f1 + f23 * f5 + f26 * f13) - (f19 * f1 + f22 * f5 + f27 * f13);
        float f31 = (f21 * f1 + f24 * f5 + f27 * f9) - (f20 * f1 + f25 * f5 + f26 * f9);
        float f32 = f8 * f13;
        float f33 = f12 * f9;
        float f34 = f4 * f13;
        float f35 = f12 * f5;
        float f36 = f4 * f9;
        float f37 = f8 * f5;
        float f38 = f * f13;
        f13 = f12 * f1;
        float f39 = f * f9;
        f9 = f8 * f1;
        f5 = f * f5;
        f1 = f4 * f1;
        float f40 = f * f28 + f4 * f29 + f8 * f30 + f12 * f31;
        if(f40 == 0.0F)
        {
            return false;
        } else
        {
            f40 = 1.0F / f40;
            af[i] = f28 * f40;
            af[i + 1] = f29 * f40;
            af[i + 2] = f30 * f40;
            af[i + 3] = f31 * f40;
            af[i + 4] = ((f17 * f4 + f18 * f8 + f21 * f12) - (f16 * f4 + f19 * f8 + f20 * f12)) * f40;
            af[i + 5] = ((f16 * f + f23 * f8 + f24 * f12) - (f17 * f + f22 * f8 + f25 * f12)) * f40;
            af[i + 6] = ((f19 * f + f22 * f4 + f27 * f12) - (f18 * f + f23 * f4 + f26 * f12)) * f40;
            af[i + 7] = ((f20 * f + f25 * f4 + f26 * f8) - (f21 * f + f24 * f4 + f27 * f8)) * f40;
            af[i + 8] = ((f32 * f7 + f35 * f11 + f36 * f15) - (f33 * f7 + f34 * f11 + f37 * f15)) * f40;
            af[i + 9] = ((f33 * f3 + f38 * f11 + f9 * f15) - (f32 * f3 + f13 * f11 + f39 * f15)) * f40;
            af[i + 10] = ((f34 * f3 + f13 * f7 + f5 * f15) - (f35 * f3 + f38 * f7 + f1 * f15)) * f40;
            af[i + 11] = ((f37 * f3 + f39 * f7 + f1 * f11) - (f36 * f3 + f9 * f7 + f5 * f11)) * f40;
            af[i + 12] = ((f34 * f10 + f37 * f14 + f33 * f6) - (f36 * f14 + f32 * f6 + f35 * f10)) * f40;
            af[i + 13] = ((f39 * f14 + f32 * f2 + f13 * f10) - (f38 * f10 + f9 * f14 + f33 * f2)) * f40;
            af[i + 14] = ((f38 * f6 + f1 * f14 + f35 * f2) - (f5 * f14 + f34 * f2 + f13 * f6)) * f40;
            af[i + 15] = ((f5 * f10 + f36 * f2 + f9 * f6) - (f39 * f6 + f1 * f10 + f37 * f2)) * f40;
            return true;
        }
    }

    public static float length(float f, float f1, float f2)
    {
        return (float)Math.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    public static native void multiplyMM(float af[], int i, float af1[], int j, float af2[], int k);

    public static native void multiplyMV(float af[], int i, float af1[], int j, float af2[], int k);

    public static void orthoM(float af[], int i, float f, float f1, float f2, float f3, float f4, float f5)
    {
        if(f == f1)
            throw new IllegalArgumentException("left == right");
        if(f2 == f3)
            throw new IllegalArgumentException("bottom == top");
        if(f4 == f5)
        {
            throw new IllegalArgumentException("near == far");
        } else
        {
            float f6 = 1.0F / (f1 - f);
            float f7 = 1.0F / (f3 - f2);
            float f8 = 1.0F / (f5 - f4);
            f = -(f1 + f);
            f1 = -(f3 + f2);
            f2 = -(f5 + f4);
            af[i + 0] = 2.0F * f6;
            af[i + 5] = 2.0F * f7;
            af[i + 10] = -2F * f8;
            af[i + 12] = f * f6;
            af[i + 13] = f1 * f7;
            af[i + 14] = f2 * f8;
            af[i + 15] = 1.0F;
            af[i + 1] = 0.0F;
            af[i + 2] = 0.0F;
            af[i + 3] = 0.0F;
            af[i + 4] = 0.0F;
            af[i + 6] = 0.0F;
            af[i + 7] = 0.0F;
            af[i + 8] = 0.0F;
            af[i + 9] = 0.0F;
            af[i + 11] = 0.0F;
            return;
        }
    }

    public static void perspectiveM(float af[], int i, float f, float f1, float f2, float f3)
    {
        f = 1.0F / (float)Math.tan((double)f * 0.0087266462599716477D);
        float f4 = 1.0F / (f2 - f3);
        af[i + 0] = f / f1;
        af[i + 1] = 0.0F;
        af[i + 2] = 0.0F;
        af[i + 3] = 0.0F;
        af[i + 4] = 0.0F;
        af[i + 5] = f;
        af[i + 6] = 0.0F;
        af[i + 7] = 0.0F;
        af[i + 8] = 0.0F;
        af[i + 9] = 0.0F;
        af[i + 10] = (f3 + f2) * f4;
        af[i + 11] = -1F;
        af[i + 12] = 0.0F;
        af[i + 13] = 0.0F;
        af[i + 14] = 2.0F * f3 * f2 * f4;
        af[i + 15] = 0.0F;
    }

    public static void rotateM(float af[], int i, float f, float f1, float f2, float f3)
    {
        float af1[] = sTemp;
        af1;
        JVM INSTR monitorenter ;
        setRotateM(sTemp, 0, f, f1, f2, f3);
        multiplyMM(sTemp, 16, af, i, sTemp, 0);
        System.arraycopy(sTemp, 16, af, i, 16);
        af1;
        JVM INSTR monitorexit ;
        return;
        af;
        throw af;
    }

    public static void rotateM(float af[], int i, float af1[], int j, float f, float f1, float f2, float f3)
    {
        float af2[] = sTemp;
        af2;
        JVM INSTR monitorenter ;
        setRotateM(sTemp, 0, f, f1, f2, f3);
        multiplyMM(af, i, af1, j, sTemp, 0);
        af2;
        JVM INSTR monitorexit ;
        return;
        af;
        throw af;
    }

    public static void scaleM(float af[], int i, float f, float f1, float f2)
    {
        for(int j = 0; j < 4; j++)
        {
            int k = i + j;
            af[k] = af[k] * f;
            int l = k + 4;
            af[l] = af[l] * f1;
            k += 8;
            af[k] = af[k] * f2;
        }

    }

    public static void scaleM(float af[], int i, float af1[], int j, float f, float f1, float f2)
    {
        for(int k = 0; k < 4; k++)
        {
            int l = i + k;
            int i1 = j + k;
            af[l] = af1[i1] * f;
            af[l + 4] = af1[i1 + 4] * f1;
            af[l + 8] = af1[i1 + 8] * f2;
            af[l + 12] = af1[i1 + 12];
        }

    }

    public static void setIdentityM(float af[], int i)
    {
        for(int j = 0; j < 16; j++)
            af[i + j] = 0.0F;

        for(int k = 0; k < 16; k += 5)
            af[i + k] = 1.0F;

    }

    public static void setLookAtM(float af[], int i, float f, float f1, float f2, float f3, float f4, float f5, 
            float f6, float f7, float f8)
    {
        f3 -= f;
        f4 -= f1;
        f5 -= f2;
        float f9 = 1.0F / length(f3, f4, f5);
        f3 *= f9;
        f4 *= f9;
        f5 *= f9;
        f9 = f4 * f8 - f5 * f7;
        f8 = f5 * f6 - f3 * f8;
        f7 = f3 * f7 - f4 * f6;
        float f10 = 1.0F / length(f9, f8, f7);
        f6 = f9 * f10;
        f8 *= f10;
        f7 *= f10;
        af[i + 0] = f6;
        af[i + 1] = f8 * f5 - f7 * f4;
        af[i + 2] = -f3;
        af[i + 3] = 0.0F;
        af[i + 4] = f8;
        af[i + 5] = f7 * f3 - f6 * f5;
        af[i + 6] = -f4;
        af[i + 7] = 0.0F;
        af[i + 8] = f7;
        af[i + 9] = f6 * f4 - f8 * f3;
        af[i + 10] = -f5;
        af[i + 11] = 0.0F;
        af[i + 12] = 0.0F;
        af[i + 13] = 0.0F;
        af[i + 14] = 0.0F;
        af[i + 15] = 1.0F;
        translateM(af, i, -f, -f1, -f2);
    }

    public static void setRotateEulerM(float af[], int i, float f, float f1, float f2)
    {
        float f3 = f * 0.01745329F;
        float f4 = f1 * 0.01745329F;
        float f5 = f2 * 0.01745329F;
        f = (float)Math.cos(f3);
        f2 = (float)Math.sin(f3);
        f1 = (float)Math.cos(f4);
        f4 = (float)Math.sin(f4);
        f3 = (float)Math.cos(f5);
        float f6 = (float)Math.sin(f5);
        f5 = f * f4;
        float f7 = f2 * f4;
        af[i + 0] = f1 * f3;
        af[i + 1] = -f1 * f6;
        af[i + 2] = f4;
        af[i + 3] = 0.0F;
        af[i + 4] = f5 * f3 + f * f6;
        af[i + 5] = -f5 * f6 + f * f3;
        af[i + 6] = -f2 * f1;
        af[i + 7] = 0.0F;
        af[i + 8] = -f7 * f3 + f2 * f6;
        af[i + 9] = f7 * f6 + f2 * f3;
        af[i + 10] = f * f1;
        af[i + 11] = 0.0F;
        af[i + 12] = 0.0F;
        af[i + 13] = 0.0F;
        af[i + 14] = 0.0F;
        af[i + 15] = 1.0F;
    }

    public static void setRotateM(float af[], int i, float f, float f1, float f2, float f3)
    {
        af[i + 3] = 0.0F;
        af[i + 7] = 0.0F;
        af[i + 11] = 0.0F;
        af[i + 12] = 0.0F;
        af[i + 13] = 0.0F;
        af[i + 14] = 0.0F;
        af[i + 15] = 1.0F;
        f *= 0.01745329F;
        float f4 = (float)Math.sin(f);
        float f5 = (float)Math.cos(f);
        if(1.0F == f1 && 0.0F == f2 && 0.0F == f3)
        {
            af[i + 5] = f5;
            af[i + 10] = f5;
            af[i + 6] = f4;
            af[i + 9] = -f4;
            af[i + 1] = 0.0F;
            af[i + 2] = 0.0F;
            af[i + 4] = 0.0F;
            af[i + 8] = 0.0F;
            af[i + 0] = 1.0F;
        } else
        if(0.0F == f1 && 1.0F == f2 && 0.0F == f3)
        {
            af[i + 0] = f5;
            af[i + 10] = f5;
            af[i + 8] = f4;
            af[i + 2] = -f4;
            af[i + 1] = 0.0F;
            af[i + 4] = 0.0F;
            af[i + 6] = 0.0F;
            af[i + 9] = 0.0F;
            af[i + 5] = 1.0F;
        } else
        if(0.0F == f1 && 0.0F == f2 && 1.0F == f3)
        {
            af[i + 0] = f5;
            af[i + 5] = f5;
            af[i + 1] = f4;
            af[i + 4] = -f4;
            af[i + 2] = 0.0F;
            af[i + 6] = 0.0F;
            af[i + 8] = 0.0F;
            af[i + 9] = 0.0F;
            af[i + 10] = 1.0F;
        } else
        {
            float f6 = length(f1, f2, f3);
            float f7 = f1;
            float f8 = f2;
            f = f3;
            if(1.0F != f6)
            {
                f = 1.0F / f6;
                f7 = f1 * f;
                f8 = f2 * f;
                f = f3 * f;
            }
            float f9 = 1.0F - f5;
            f6 = f7 * f8;
            f3 = f8 * f;
            float f10 = f * f7;
            f2 = f7 * f4;
            f1 = f8 * f4;
            f4 = f * f4;
            af[i + 0] = f7 * f7 * f9 + f5;
            af[i + 4] = f6 * f9 - f4;
            af[i + 8] = f10 * f9 + f1;
            af[i + 1] = f6 * f9 + f4;
            af[i + 5] = f8 * f8 * f9 + f5;
            af[i + 9] = f3 * f9 - f2;
            af[i + 2] = f10 * f9 - f1;
            af[i + 6] = f3 * f9 + f2;
            af[i + 10] = f * f * f9 + f5;
        }
    }

    public static void translateM(float af[], int i, float f, float f1, float f2)
    {
        for(int j = 0; j < 4; j++)
        {
            int k = i + j;
            int l = k + 12;
            af[l] = af[l] + (af[k] * f + af[k + 4] * f1 + af[k + 8] * f2);
        }

    }

    public static void translateM(float af[], int i, float af1[], int j, float f, float f1, float f2)
    {
        for(int k = 0; k < 12; k++)
            af[i + k] = af1[j + k];

        for(int l = 0; l < 4; l++)
        {
            int i1 = j + l;
            af[i + l + 12] = af1[i1] * f + af1[i1 + 4] * f1 + af1[i1 + 8] * f2 + af1[i1 + 12];
        }

    }

    public static void transposeM(float af[], int i, float af1[], int j)
    {
        for(int k = 0; k < 4; k++)
        {
            int l = k * 4 + j;
            af[k + i] = af1[l];
            af[k + 4 + i] = af1[l + 1];
            af[k + 8 + i] = af1[l + 2];
            af[k + 12 + i] = af1[l + 3];
        }

    }

    private static final float sTemp[] = new float[32];

}
