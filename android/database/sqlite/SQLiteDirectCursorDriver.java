// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.database.Cursor;
import android.os.CancellationSignal;

// Referenced classes of package android.database.sqlite:
//            SQLiteCursorDriver, SQLiteQuery, SQLiteCursor, SQLiteDatabase

public final class SQLiteDirectCursorDriver
    implements SQLiteCursorDriver
{

    public SQLiteDirectCursorDriver(SQLiteDatabase sqlitedatabase, String s, String s1, CancellationSignal cancellationsignal)
    {
        mDatabase = sqlitedatabase;
        mEditTable = s1;
        mSql = s;
        mCancellationSignal = cancellationsignal;
    }

    public void cursorClosed()
    {
    }

    public void cursorDeactivated()
    {
    }

    public void cursorRequeried(Cursor cursor)
    {
    }

    public Cursor query(SQLiteDatabase.CursorFactory cursorfactory, String as[])
    {
        SQLiteQuery sqlitequery = new SQLiteQuery(mDatabase, mSql, mCancellationSignal);
        try
        {
            sqlitequery.bindAllArgsAsStrings(as);
        }
        // Misplaced declaration of an exception variable
        catch(SQLiteDatabase.CursorFactory cursorfactory)
        {
            sqlitequery.close();
            throw cursorfactory;
        }
        if(cursorfactory != null) goto _L2; else goto _L1
_L1:
        cursorfactory = new SQLiteCursor(this, mEditTable, sqlitequery);
_L4:
        mQuery = sqlitequery;
        return cursorfactory;
_L2:
        cursorfactory = cursorfactory.newCursor(mDatabase, this, mEditTable, sqlitequery);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setBindArguments(String as[])
    {
        mQuery.bindAllArgsAsStrings(as);
    }

    public String toString()
    {
        return (new StringBuilder()).append("SQLiteDirectCursorDriver: ").append(mSql).toString();
    }

    private final CancellationSignal mCancellationSignal;
    private final SQLiteDatabase mDatabase;
    private final String mEditTable;
    private SQLiteQuery mQuery;
    private final String mSql;
}
