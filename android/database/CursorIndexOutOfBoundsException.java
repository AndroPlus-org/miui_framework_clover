// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;


public class CursorIndexOutOfBoundsException extends IndexOutOfBoundsException
{

    public CursorIndexOutOfBoundsException(int i, int j)
    {
        super((new StringBuilder()).append("Index ").append(i).append(" requested, with a size of ").append(j).toString());
    }

    public CursorIndexOutOfBoundsException(String s)
    {
        super(s);
    }
}
