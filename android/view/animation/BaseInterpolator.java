// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;


// Referenced classes of package android.view.animation:
//            Interpolator

public abstract class BaseInterpolator
    implements Interpolator
{

    public BaseInterpolator()
    {
    }

    public int getChangingConfiguration()
    {
        return mChangingConfiguration;
    }

    void setChangingConfiguration(int i)
    {
        mChangingConfiguration = i;
    }

    private int mChangingConfiguration;
}
