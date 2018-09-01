// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;


public interface Response
{

    public abstract void onError(Object obj, int i, String s);

    public transient abstract void onResult(Object obj, Object aobj[]);
}
