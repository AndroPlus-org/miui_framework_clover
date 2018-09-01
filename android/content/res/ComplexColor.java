// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;


// Referenced classes of package android.content.res:
//            ConstantState

public abstract class ComplexColor
{

    public ComplexColor()
    {
    }

    public abstract boolean canApplyTheme();

    public int getChangingConfigurations()
    {
        return mChangingConfigurations;
    }

    public abstract ConstantState getConstantState();

    public abstract int getDefaultColor();

    public boolean isStateful()
    {
        return false;
    }

    public abstract ComplexColor obtainForTheme(Resources.Theme theme);

    final void setBaseChangingConfigurations(int i)
    {
        mChangingConfigurations = i;
    }

    private int mChangingConfigurations;
}
