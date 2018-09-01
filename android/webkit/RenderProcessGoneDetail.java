// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;


public abstract class RenderProcessGoneDetail
{

    public RenderProcessGoneDetail()
    {
    }

    public abstract boolean didCrash();

    public abstract int rendererPriorityAtExit();
}
