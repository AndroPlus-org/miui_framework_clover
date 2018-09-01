// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.dreams;


public abstract class DreamManagerInternal
{

    public DreamManagerInternal()
    {
    }

    public abstract boolean isDreaming();

    public abstract void startDream(boolean flag);

    public abstract void stopDream(boolean flag);
}
