// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.database.Cursor;

public interface SQLiteCursorDriver
{

    public abstract void cursorClosed();

    public abstract void cursorDeactivated();

    public abstract void cursorRequeried(Cursor cursor);

    public abstract Cursor query(SQLiteDatabase.CursorFactory cursorfactory, String as[]);

    public abstract void setBindArguments(String as[]);
}
