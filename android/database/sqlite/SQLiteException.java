// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.database.SQLException;

public class SQLiteException extends SQLException
{

    public SQLiteException()
    {
    }

    public SQLiteException(String s)
    {
        super(s);
    }

    public SQLiteException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
