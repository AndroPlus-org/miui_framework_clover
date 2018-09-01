// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Shader

public class SweepGradient extends Shader
{

    public SweepGradient(float f, float f1, int i, int j)
    {
        mType = 2;
        mCx = f;
        mCy = f1;
        mColor0 = i;
        mColor1 = j;
        mColors = null;
        mPositions = null;
    }

    public SweepGradient(float f, float f1, int ai[], float af[])
    {
        if(ai.length < 2)
            throw new IllegalArgumentException("needs >= 2 number of colors");
        if(af != null && ai.length != af.length)
            throw new IllegalArgumentException("color and position arrays must be of equal length");
        mType = 1;
        mCx = f;
        mCy = f1;
        mColors = (int[])ai.clone();
        if(af != null)
            ai = (float[])af.clone();
        else
            ai = null;
        mPositions = ai;
    }

    private static native long nativeCreate1(long l, float f, float f1, int ai[], float af[]);

    private static native long nativeCreate2(long l, float f, float f1, int i, int j);

    protected Shader copy()
    {
        float af[] = null;
        SweepGradient sweepgradient;
        if(mType == 1)
        {
            float f = mCx;
            float f1 = mCy;
            int ai[] = (int[])mColors.clone();
            if(mPositions != null)
                af = (float[])mPositions.clone();
            sweepgradient = new SweepGradient(f, f1, ai, af);
        } else
        {
            sweepgradient = new SweepGradient(mCx, mCy, mColor0, mColor1);
        }
        copyLocalMatrix(sweepgradient);
        return sweepgradient;
    }

    long createNativeInstance(long l)
    {
        if(mType == 1)
            return nativeCreate1(l, mCx, mCy, mColors, mPositions);
        else
            return nativeCreate2(l, mCx, mCy, mColor0, mColor1);
    }

    private static final int TYPE_COLORS_AND_POSITIONS = 1;
    private static final int TYPE_COLOR_START_AND_COLOR_END = 2;
    private int mColor0;
    private int mColor1;
    private int mColors[];
    private float mCx;
    private float mCy;
    private float mPositions[];
    private int mType;
}
