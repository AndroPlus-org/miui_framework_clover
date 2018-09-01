// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Shader

public class LinearGradient extends Shader
{

    public LinearGradient(float f, float f1, float f2, float f3, int i, int j, Shader.TileMode tilemode)
    {
        mType = 2;
        mX0 = f;
        mY0 = f1;
        mX1 = f2;
        mY1 = f3;
        mColor0 = i;
        mColor1 = j;
        mColors = null;
        mPositions = null;
        mTileMode = tilemode;
    }

    public LinearGradient(float f, float f1, float f2, float f3, int ai[], float af[], Shader.TileMode tilemode)
    {
        if(ai.length < 2)
            throw new IllegalArgumentException("needs >= 2 number of colors");
        if(af != null && ai.length != af.length)
            throw new IllegalArgumentException("color and position arrays must be of equal length");
        mType = 1;
        mX0 = f;
        mY0 = f1;
        mX1 = f2;
        mY1 = f3;
        mColors = (int[])ai.clone();
        if(af != null)
            ai = (float[])af.clone();
        else
            ai = null;
        mPositions = ai;
        mTileMode = tilemode;
    }

    private native long nativeCreate1(long l, float f, float f1, float f2, float f3, int ai[], 
            float af[], int i);

    private native long nativeCreate2(long l, float f, float f1, float f2, float f3, int i, 
            int j, int k);

    protected Shader copy()
    {
        float af[] = null;
        LinearGradient lineargradient;
        if(mType == 1)
        {
            float f = mX0;
            float f1 = mY0;
            float f2 = mX1;
            float f3 = mY1;
            int ai[] = (int[])mColors.clone();
            if(mPositions != null)
                af = (float[])mPositions.clone();
            lineargradient = new LinearGradient(f, f1, f2, f3, ai, af, mTileMode);
        } else
        {
            lineargradient = new LinearGradient(mX0, mY0, mX1, mY1, mColor0, mColor1, mTileMode);
        }
        copyLocalMatrix(lineargradient);
        return lineargradient;
    }

    long createNativeInstance(long l)
    {
        if(mType == 1)
            return nativeCreate1(l, mX0, mY0, mX1, mY1, mColors, mPositions, mTileMode.nativeInt);
        else
            return nativeCreate2(l, mX0, mY0, mX1, mY1, mColor0, mColor1, mTileMode.nativeInt);
    }

    private static final int TYPE_COLORS_AND_POSITIONS = 1;
    private static final int TYPE_COLOR_START_AND_COLOR_END = 2;
    private int mColor0;
    private int mColor1;
    private int mColors[];
    private float mPositions[];
    private Shader.TileMode mTileMode;
    private int mType;
    private float mX0;
    private float mX1;
    private float mY0;
    private float mY1;
}
