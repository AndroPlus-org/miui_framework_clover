// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.database.sqlite.SQLiteDatabase;

public interface DatabaseErrorHandler
{

    public abstract void onCorruption(SQLiteDatabase sqlitedatabase);
}
