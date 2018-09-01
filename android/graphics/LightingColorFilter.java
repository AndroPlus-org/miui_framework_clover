// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            ColorFilter

public class LightingColorFilter extends ColorFilter
{

    public LightingColorFilter(int i, int j)
    {
        mMul = i;
        mAdd = j;
    }

    private static native long native_CreateLightingFilter(int i, int j);

    long createNativeInstance()
    {
        return native_CreateLightingFilter(mMul, mAdd);
    }

    public int getColorAdd()
    {
        return mAdd;
    }

    public int getColorMultiply()
    {
        return mMul;
    }

    public void setColorAdd(int i)
    {
        if(mAdd != i)
        {
            mAdd = i;
            discardNativeInstance();
        }
    }

    public void setColorMultiply(int i)
    {
        if(mMul != i)
        {
            mMul = i;
            discardNativeInstance();
        }
    }

    private int mAdd;
    private int mMul;
}
