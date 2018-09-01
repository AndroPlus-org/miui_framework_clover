// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Shader

public class RadialGradient extends Shader
{

    public RadialGradient(float f, float f1, float f2, int i, int j, Shader.TileMode tilemode)
    {
        if(f2 <= 0.0F)
        {
            throw new IllegalArgumentException("radius must be > 0");
        } else
        {
            mType = 2;
            mX = f;
            mY = f1;
            mRadius = f2;
            mCenterColor = i;
            mEdgeColor = j;
            mTileMode = tilemode;
            return;
        }
    }

    public RadialGradient(float f, float f1, float f2, int ai[], float af[], Shader.TileMode tilemode)
    {
        if(f2 <= 0.0F)
            throw new IllegalArgumentException("radius must be > 0");
        if(ai.length < 2)
            throw new IllegalArgumentException("needs >= 2 number of colors");
        if(af != null && ai.length != af.length)
            throw new IllegalArgumentException("color and position arrays must be of equal length");
        mType = 1;
        mX = f;
        mY = f1;
        mRadius = f2;
        mColors = (int[])ai.clone();
        if(af != null)
            ai = (float[])af.clone();
        else
            ai = null;
        mPositions = ai;
        mTileMode = tilemode;
    }

    private static native long nativeCreate1(long l, float f, float f1, float f2, int ai[], float af[], int i);

    private static native long nativeCreate2(long l, float f, float f1, float f2, int i, int j, int k);

    protected Shader copy()
    {
        float af[] = null;
        RadialGradient radialgradient;
        if(mType == 1)
        {
            float f = mX;
            float f1 = mY;
            float f2 = mRadius;
            int ai[] = (int[])mColors.clone();
            if(mPositions != null)
                af = (float[])mPositions.clone();
            radialgradient = new RadialGradient(f, f1, f2, ai, af, mTileMode);
        } else
        {
            radialgradient = new RadialGradient(mX, mY, mRadius, mCenterColor, mEdgeColor, mTileMode);
        }
        copyLocalMatrix(radialgradient);
        return radialgradient;
    }

    long createNativeInstance(long l)
    {
        if(mType == 1)
            return nativeCreate1(l, mX, mY, mRadius, mColors, mPositions, mTileMode.nativeInt);
        else
            return nativeCreate2(l, mX, mY, mRadius, mCenterColor, mEdgeColor, mTileMode.nativeInt);
    }

    private static final int TYPE_COLORS_AND_POSITIONS = 1;
    private static final int TYPE_COLOR_CENTER_AND_COLOR_EDGE = 2;
    private int mCenterColor;
    private int mColors[];
    private int mEdgeColor;
    private float mPositions[];
    private float mRadius;
    private Shader.TileMode mTileMode;
    private int mType;
    private float mX;
    private float mY;
}
