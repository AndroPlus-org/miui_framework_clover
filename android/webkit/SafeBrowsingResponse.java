// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;


public abstract class SafeBrowsingResponse
{

    public SafeBrowsingResponse()
    {
    }

    public abstract void backToSafety(boolean flag);

    public abstract void proceed(boolean flag);

    public abstract void showInterstitial(boolean flag);
}
