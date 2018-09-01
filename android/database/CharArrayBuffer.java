// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;


public final class CharArrayBuffer
{

    public CharArrayBuffer(int i)
    {
        data = new char[i];
    }

    public CharArrayBuffer(char ac[])
    {
        data = ac;
    }

    public char data[];
    public int sizeCopied;
}
