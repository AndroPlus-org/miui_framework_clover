// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


public final class MathUtils
{

    private MathUtils()
    {
    }

    public static float abs(float f)
    {
        if(f <= 0.0F)
            f = -f;
        return f;
    }

    public static float acos(float f)
    {
        return (float)Math.acos(f);
    }

    public static int addOrThrow(int i, int j)
        throws IllegalArgumentException
    {
        if(j == 0)
            return i;
        if(j > 0 && i <= 0x7fffffff - j)
            return i + j;
        if(j < 0 && i >= 0x80000000 - j)
            return i + j;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Addition overflow: ").append(i).append(" + ").append(j).toString());
    }

    public static float asin(float f)
    {
        return (float)Math.asin(f);
    }

    public static float atan(float f)
    {
        return (float)Math.atan(f);
    }

    public static float atan2(float f, float f1)
    {
        return (float)Math.atan2(f, f1);
    }

    public static float constrain(float f, float f1, float f2)
    {
        if(f >= f1)
            if(f > f2)
                f1 = f2;
            else
                f1 = f;
        return f1;
    }

    public static int constrain(int i, int j, int k)
    {
        if(i >= j)
            if(i > k)
                j = k;
            else
                j = i;
        return j;
    }

    public static long constrain(long l, long l1, long l2)
    {
        if(l >= l1)
            if(l > l2)
                l1 = l2;
            else
                l1 = l;
        return l1;
    }

    public static float cross(float f, float f1, float f2, float f3)
    {
        return f * f3 - f1 * f2;
    }

    public static float degrees(float f)
    {
        return 57.29578F * f;
    }

    public static float dist(float f, float f1, float f2, float f3)
    {
        return (float)Math.hypot(f2 - f, f3 - f1);
    }

    public static float dist(float f, float f1, float f2, float f3, float f4, float f5)
    {
        f = f3 - f;
        f1 = f4 - f1;
        f2 = f5 - f2;
        return (float)Math.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    public static float dot(float f, float f1, float f2, float f3)
    {
        return f * f2 + f1 * f3;
    }

    public static float exp(float f)
    {
        return (float)Math.exp(f);
    }

    public static float lerp(float f, float f1, float f2)
    {
        return (f1 - f) * f2 + f;
    }

    public static float lerpDeg(float f, float f1, float f2)
    {
        return (((f1 - f) + 180F) % 360F - 180F) * f2 + f;
    }

    public static float log(float f)
    {
        return (float)Math.log(f);
    }

    public static float mag(float f, float f1)
    {
        return (float)Math.hypot(f, f1);
    }

    public static float mag(float f, float f1, float f2)
    {
        return (float)Math.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    public static float map(float f, float f1, float f2, float f3, float f4)
    {
        return (f3 - f2) * ((f4 - f) / (f1 - f)) + f2;
    }

    public static float max(float f, float f1)
    {
        if(f <= f1)
            f = f1;
        return f;
    }

    public static float max(float f, float f1, float f2)
    {
        if(f <= f1) goto _L2; else goto _L1
_L1:
        float f3;
        f3 = f2;
        if(f > f2)
            f3 = f;
_L4:
        return f3;
_L2:
        f3 = f2;
        if(f1 > f2)
            f3 = f1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static float max(int i, int j)
    {
        if(i <= j)
            i = j;
        return (float)i;
    }

    public static float max(int i, int j, int k)
    {
        if(i <= j) goto _L2; else goto _L1
_L1:
        int l;
        l = k;
        if(i > k)
            l = i;
_L4:
        return (float)l;
_L2:
        l = k;
        if(j > k)
            l = j;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static float min(float f, float f1)
    {
        if(f >= f1)
            f = f1;
        return f;
    }

    public static float min(float f, float f1, float f2)
    {
        if(f >= f1) goto _L2; else goto _L1
_L1:
        float f3;
        f3 = f2;
        if(f < f2)
            f3 = f;
_L4:
        return f3;
_L2:
        f3 = f2;
        if(f1 < f2)
            f3 = f1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static float min(int i, int j)
    {
        if(i >= j)
            i = j;
        return (float)i;
    }

    public static float min(int i, int j, int k)
    {
        if(i >= j) goto _L2; else goto _L1
_L1:
        int l;
        l = k;
        if(i < k)
            l = i;
_L4:
        return (float)l;
_L2:
        l = k;
        if(j < k)
            l = j;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static float norm(float f, float f1, float f2)
    {
        return (f2 - f) / (f1 - f);
    }

    public static float pow(float f, float f1)
    {
        return (float)Math.pow(f, f1);
    }

    public static float radians(float f)
    {
        return 0.01745329F * f;
    }

    public static float sq(float f)
    {
        return f * f;
    }

    public static float tan(float f)
    {
        return (float)Math.tan(f);
    }

    private static final float DEG_TO_RAD = 0.01745329F;
    private static final float RAD_TO_DEG = 57.29578F;
}
