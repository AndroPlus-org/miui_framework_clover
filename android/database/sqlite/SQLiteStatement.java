// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.os.ParcelFileDescriptor;

// Referenced classes of package android.database.sqlite:
//            SQLiteProgram, SQLiteDatabaseCorruptException, SQLiteSession, SQLiteDatabase

public final class SQLiteStatement extends SQLiteProgram
{

    SQLiteStatement(SQLiteDatabase sqlitedatabase, String s, Object aobj[])
    {
        super(sqlitedatabase, s, aobj, null);
    }

    public void execute()
    {
        acquireReference();
        getSession().execute(getSql(), getBindArgs(), getConnectionFlags(), null);
        releaseReference();
        return;
        Object obj;
        obj;
        onCorruption();
        throw obj;
        obj;
        releaseReference();
        throw obj;
    }

    public long executeInsert()
    {
        acquireReference();
        long l = getSession().executeForLastInsertedRowId(getSql(), getBindArgs(), getConnectionFlags(), null);
        releaseReference();
        return l;
        Object obj;
        obj;
        onCorruption();
        throw obj;
        obj;
        releaseReference();
        throw obj;
    }

    public int executeUpdateDelete()
    {
        acquireReference();
        int i = getSession().executeForChangedRowCount(getSql(), getBindArgs(), getConnectionFlags(), null);
        releaseReference();
        return i;
        Object obj;
        obj;
        onCorruption();
        throw obj;
        obj;
        releaseReference();
        throw obj;
    }

    public ParcelFileDescriptor simpleQueryForBlobFileDescriptor()
    {
        acquireReference();
        ParcelFileDescriptor parcelfiledescriptor = getSession().executeForBlobFileDescriptor(getSql(), getBindArgs(), getConnectionFlags(), null);
        releaseReference();
        return parcelfiledescriptor;
        Object obj;
        obj;
        onCorruption();
        throw obj;
        obj;
        releaseReference();
        throw obj;
    }

    public long simpleQueryForLong()
    {
        acquireReference();
        long l = getSession().executeForLong(getSql(), getBindArgs(), getConnectionFlags(), null);
        releaseReference();
        return l;
        Object obj;
        obj;
        onCorruption();
        throw obj;
        obj;
        releaseReference();
        throw obj;
    }

    public String simpleQueryForString()
    {
        acquireReference();
        String s = getSession().executeForString(getSql(), getBindArgs(), getConnectionFlags(), null);
        releaseReference();
        return s;
        Object obj;
        obj;
        onCorruption();
        throw obj;
        obj;
        releaseReference();
        throw obj;
    }

    public String toString()
    {
        return (new StringBuilder()).append("SQLiteProgram: ").append(getSql()).toString();
    }
}
