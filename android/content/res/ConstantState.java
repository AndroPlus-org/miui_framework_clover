// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;


// Referenced classes of package android.content.res:
//            Resources

public abstract class ConstantState
{

    public ConstantState()
    {
    }

    public abstract int getChangingConfigurations();

    public abstract Object newInstance();

    public Object newInstance(Resources resources)
    {
        return newInstance();
    }

    public Object newInstance(Resources resources, Resources.Theme theme)
    {
        return newInstance(resources);
    }
}
