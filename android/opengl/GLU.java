// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;

import javax.microedition.khronos.opengles.GL10;

// Referenced classes of package android.opengl:
//            Matrix

public class GLU
{

    public GLU()
    {
    }

    public static String gluErrorString(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return "no error";

        case 1280: 
            return "invalid enum";

        case 1281: 
            return "invalid value";

        case 1282: 
            return "invalid operation";

        case 1283: 
            return "stack overflow";

        case 1284: 
            return "stack underflow";

        case 1285: 
            return "out of memory";
        }
    }

    public static void gluLookAt(GL10 gl10, float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            float f7, float f8)
    {
        float af[] = sScratch;
        af;
        JVM INSTR monitorenter ;
        Matrix.setLookAtM(af, 0, f, f1, f2, f3, f4, f5, f6, f7, f8);
        gl10.glMultMatrixf(af, 0);
        af;
        JVM INSTR monitorexit ;
        return;
        gl10;
        throw gl10;
    }

    public static void gluOrtho2D(GL10 gl10, float f, float f1, float f2, float f3)
    {
        gl10.glOrthof(f, f1, f2, f3, -1F, 1.0F);
    }

    public static void gluPerspective(GL10 gl10, float f, float f1, float f2, float f3)
    {
        float f4 = f2 * (float)Math.tan((double)f * 0.0087266462599716477D);
        f = -f4;
        gl10.glFrustumf(f * f1, f4 * f1, f, f4, f2, f3);
    }

    public static int gluProject(float f, float f1, float f2, float af[], int i, float af1[], int j, int ai[], 
            int k, float af2[], int l)
    {
        float af3[] = sScratch;
        af3;
        JVM INSTR monitorenter ;
        Matrix.multiplyMM(af3, 0, af1, j, af, i);
        af3[16] = f;
        af3[17] = f1;
        af3[18] = f2;
        af3[19] = 1.0F;
        Matrix.multiplyMV(af3, 20, af3, 0, af3, 16);
        f = af3[23];
        if(f == 0.0F)
            return 0;
        f = 1.0F / f;
        af2[l] = (float)ai[k] + (float)ai[k + 2] * (af3[20] * f + 1.0F) * 0.5F;
        af2[l + 1] = (float)ai[k + 1] + (float)ai[k + 3] * (af3[21] * f + 1.0F) * 0.5F;
        af2[l + 2] = (af3[22] * f + 1.0F) * 0.5F;
        af3;
        JVM INSTR monitorexit ;
        return 1;
        af;
        throw af;
    }

    public static int gluUnProject(float f, float f1, float f2, float af[], int i, float af1[], int j, int ai[], 
            int k, float af2[], int l)
    {
        float af3[] = sScratch;
        af3;
        JVM INSTR monitorenter ;
        boolean flag;
        Matrix.multiplyMM(af3, 0, af1, j, af, i);
        flag = Matrix.invertM(af3, 16, af3, 0);
        if(flag)
            break MISSING_BLOCK_LABEL_43;
        af3;
        JVM INSTR monitorexit ;
        return 0;
        af3[0] = ((f - (float)ai[k + 0]) * 2.0F) / (float)ai[k + 2] - 1.0F;
        af3[1] = ((f1 - (float)ai[k + 1]) * 2.0F) / (float)ai[k + 3] - 1.0F;
        af3[2] = 2.0F * f2 - 1.0F;
        af3[3] = 1.0F;
        Matrix.multiplyMV(af2, l, af3, 16, af3, 0);
        af3;
        JVM INSTR monitorexit ;
        return 1;
        af;
        throw af;
    }

    private static final float sScratch[] = new float[32];

}
