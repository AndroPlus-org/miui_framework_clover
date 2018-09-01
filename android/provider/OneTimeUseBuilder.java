// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;


public abstract class OneTimeUseBuilder
{

    public OneTimeUseBuilder()
    {
        used = false;
    }

    public abstract Object build();

    protected void checkNotUsed()
    {
        if(used)
            throw new IllegalStateException("This Builder should not be reused. Use a new Builder instance instead");
        else
            return;
    }

    protected void markUsed()
    {
        checkNotUsed();
        used = true;
    }

    private boolean used;
}
