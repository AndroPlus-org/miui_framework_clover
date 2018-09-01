// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.database.CursorWindow;
import android.os.CancellationSignal;
import android.util.Log;

// Referenced classes of package android.database.sqlite:
//            SQLiteProgram, SQLiteDatabaseCorruptException, SQLiteException, SQLiteSession, 
//            SQLiteDatabase

public final class SQLiteQuery extends SQLiteProgram
{

    SQLiteQuery(SQLiteDatabase sqlitedatabase, String s, CancellationSignal cancellationsignal)
    {
        super(sqlitedatabase, s, null, cancellationsignal);
        mCancellationSignal = cancellationsignal;
    }

    int fillWindow(CursorWindow cursorwindow, int i, int j, boolean flag)
    {
        acquireReference();
        cursorwindow.acquireReference();
        i = getSession().executeForCursorWindow(getSql(), getBindArgs(), cursorwindow, i, j, flag, getConnectionFlags(), mCancellationSignal);
        cursorwindow.releaseReference();
        releaseReference();
        return i;
        Object obj;
        obj;
        StringBuilder stringbuilder = JVM INSTR new #59  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("SQLiteQuery", stringbuilder.append("exception: ").append(((SQLiteException) (obj)).getMessage()).append("; query: ").append(getSql()).toString());
        throw obj;
        obj;
        cursorwindow.releaseReference();
        throw obj;
        cursorwindow;
        releaseReference();
        throw cursorwindow;
        obj;
        onCorruption();
        throw obj;
    }

    public String toString()
    {
        return (new StringBuilder()).append("SQLiteQuery: ").append(getSql()).toString();
    }

    private static final String TAG = "SQLiteQuery";
    private final CancellationSignal mCancellationSignal;
}
