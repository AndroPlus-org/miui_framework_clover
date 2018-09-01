// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


public abstract class Spline
{
    public static class LinearSpline extends Spline
    {

        public float interpolate(float f)
        {
            int i = mX.length;
            if(Float.isNaN(f))
                return f;
            if(f <= mX[0])
                return mY[0];
            if(f >= mX[i - 1])
                return mY[i - 1];
            for(i = 0; f >= mX[i + 1];)
            {
                int j = i + 1;
                i = j;
                if(f == mX[j])
                    return mY[j];
            }

            return mY[i] + mM[i] * (f - mX[i]);
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            int i = mX.length;
            stringbuilder.append("LinearSpline{[");
            for(int j = 0; j < i; j++)
            {
                if(j != 0)
                    stringbuilder.append(", ");
                stringbuilder.append("(").append(mX[j]);
                stringbuilder.append(", ").append(mY[j]);
                if(j < i - 1)
                    stringbuilder.append(": ").append(mM[j]);
                stringbuilder.append(")");
            }

            stringbuilder.append("]}");
            return stringbuilder.toString();
        }

        private final float mM[];
        private final float mX[];
        private final float mY[];

        public LinearSpline(float af[], float af1[])
        {
            while(af == null || af1 == null || af.length != af1.length || af.length < 2) 
                throw new IllegalArgumentException("There must be at least two control points and the arrays must be of equal length.");
            int i = af.length;
            mM = new float[i - 1];
            for(int j = 0; j < i - 1; j++)
                mM[j] = (af1[j + 1] - af1[j]) / (af[j + 1] - af[j]);

            mX = af;
            mY = af1;
        }
    }

    public static class MonotoneCubicSpline extends Spline
    {

        public float interpolate(float f)
        {
            int i = mX.length;
            if(Float.isNaN(f))
                return f;
            if(f <= mX[0])
                return mY[0];
            if(f >= mX[i - 1])
                return mY[i - 1];
            for(i = 0; f >= mX[i + 1];)
            {
                int j = i + 1;
                i = j;
                if(f == mX[j])
                    return mY[j];
            }

            float f1 = mX[i + 1] - mX[i];
            f = (f - mX[i]) / f1;
            return (mY[i] * (2.0F * f + 1.0F) + mM[i] * f1 * f) * (1.0F - f) * (1.0F - f) + (mY[i + 1] * (3F - 2.0F * f) + mM[i + 1] * f1 * (f - 1.0F)) * f * f;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            int i = mX.length;
            stringbuilder.append("MonotoneCubicSpline{[");
            for(int j = 0; j < i; j++)
            {
                if(j != 0)
                    stringbuilder.append(", ");
                stringbuilder.append("(").append(mX[j]);
                stringbuilder.append(", ").append(mY[j]);
                stringbuilder.append(": ").append(mM[j]).append(")");
            }

            stringbuilder.append("]}");
            return stringbuilder.toString();
        }

        private float mM[];
        private float mX[];
        private float mY[];

        public MonotoneCubicSpline(float af[], float af1[])
        {
            while(af == null || af1 == null || af.length != af1.length || af.length < 2) 
                throw new IllegalArgumentException("There must be at least two control points and the arrays must be of equal length.");
            int i = af.length;
            float af2[] = new float[i - 1];
            float af3[] = new float[i];
            for(int j = 0; j < i - 1; j++)
            {
                float f = af[j + 1] - af[j];
                if(f <= 0.0F)
                    throw new IllegalArgumentException("The control points must all have strictly increasing X values.");
                af2[j] = (af1[j + 1] - af1[j]) / f;
            }

            af3[0] = af2[0];
            for(int k = 1; k < i - 1; k++)
                af3[k] = (af2[k - 1] + af2[k]) * 0.5F;

            af3[i - 1] = af2[i - 2];
            int l = 0;
            while(l < i - 1) 
            {
                if(af2[l] == 0.0F)
                {
                    af3[l] = 0.0F;
                    af3[l + 1] = 0.0F;
                } else
                {
                    float f1 = af3[l] / af2[l];
                    float f2 = af3[l + 1] / af2[l];
                    if(f1 < 0.0F || f2 < 0.0F)
                        throw new IllegalArgumentException("The control points must have monotonic Y values.");
                    f1 = (float)Math.hypot(f1, f2);
                    if(f1 > 3F)
                    {
                        f1 = 3F / f1;
                        af3[l] = af3[l] * f1;
                        int i1 = l + 1;
                        af3[i1] = af3[i1] * f1;
                    }
                }
                l++;
            }
            mX = af;
            mY = af1;
            mM = af3;
        }
    }


    public Spline()
    {
    }

    public static Spline createLinearSpline(float af[], float af1[])
    {
        return new LinearSpline(af, af1);
    }

    public static Spline createMonotoneCubicSpline(float af[], float af1[])
    {
        return new MonotoneCubicSpline(af, af1);
    }

    public static Spline createSpline(float af[], float af1[])
    {
        if(!isStrictlyIncreasing(af))
            throw new IllegalArgumentException("The control points must all have strictly increasing X values.");
        if(isMonotonic(af1))
            return createMonotoneCubicSpline(af, af1);
        else
            return createLinearSpline(af, af1);
    }

    private static boolean isMonotonic(float af[])
    {
        if(af == null || af.length < 2)
            throw new IllegalArgumentException("There must be at least two control points.");
        float f = af[0];
        for(int i = 1; i < af.length; i++)
        {
            float f1 = af[i];
            if(f1 < f)
                return false;
            f = f1;
        }

        return true;
    }

    private static boolean isStrictlyIncreasing(float af[])
    {
        if(af == null || af.length < 2)
            throw new IllegalArgumentException("There must be at least two control points.");
        float f = af[0];
        for(int i = 1; i < af.length; i++)
        {
            float f1 = af[i];
            if(f1 <= f)
                return false;
            f = f1;
        }

        return true;
    }

    public abstract float interpolate(float f);
}
