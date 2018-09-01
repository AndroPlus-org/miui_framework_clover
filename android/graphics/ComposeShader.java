// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Shader, Xfermode

public class ComposeShader extends Shader
{

    private ComposeShader(Shader shader, Shader shader1, int i)
    {
        if(shader == null || shader1 == null)
        {
            throw new IllegalArgumentException("Shader parameters must not be null");
        } else
        {
            mShaderA = shader;
            mShaderB = shader1;
            mPorterDuffMode = i;
            return;
        }
    }

    public ComposeShader(Shader shader, Shader shader1, PorterDuff.Mode mode)
    {
        this(shader, shader1, mode.nativeInt);
    }

    public ComposeShader(Shader shader, Shader shader1, Xfermode xfermode)
    {
        this(shader, shader1, xfermode.porterDuffMode);
    }

    private static native long nativeCreate(long l, long l1, long l2, int i);

    protected Shader copy()
    {
        ComposeShader composeshader = new ComposeShader(mShaderA.copy(), mShaderB.copy(), mPorterDuffMode);
        copyLocalMatrix(composeshader);
        return composeshader;
    }

    long createNativeInstance(long l)
    {
        mNativeInstanceShaderA = mShaderA.getNativeInstance();
        mNativeInstanceShaderB = mShaderB.getNativeInstance();
        return nativeCreate(l, mShaderA.getNativeInstance(), mShaderB.getNativeInstance(), mPorterDuffMode);
    }

    protected void verifyNativeInstance()
    {
        if(mShaderA.getNativeInstance() != mNativeInstanceShaderA || mShaderB.getNativeInstance() != mNativeInstanceShaderB)
            discardNativeInstance();
    }

    private long mNativeInstanceShaderA;
    private long mNativeInstanceShaderB;
    private int mPorterDuffMode;
    Shader mShaderA;
    Shader mShaderB;
}
