// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


public interface MediaScannerClient
{

    public abstract void handleStringTag(String s, String s1);

    public abstract void scanFile(String s, long l, long l1, boolean flag, boolean flag1);

    public abstract void setMimeType(String s);
}
