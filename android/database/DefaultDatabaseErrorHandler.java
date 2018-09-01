// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.util.Iterator;

// Referenced classes of package android.database:
//            DatabaseErrorHandler

public final class DefaultDatabaseErrorHandler
    implements DatabaseErrorHandler
{

    public DefaultDatabaseErrorHandler()
    {
    }

    private void deleteDatabaseFile(String s)
    {
        if(s.equalsIgnoreCase(":memory:") || s.trim().length() == 0)
            return;
        Log.e("DefaultDatabaseErrorHandler", (new StringBuilder()).append("deleting the database file: ").append(s).toString());
        File file = JVM INSTR new #55  <Class File>;
        file.File(s);
        SQLiteDatabase.deleteDatabase(file);
_L1:
        return;
        s;
        Log.w("DefaultDatabaseErrorHandler", (new StringBuilder()).append("delete failed: ").append(s.getMessage()).toString());
          goto _L1
    }

    public void onCorruption(SQLiteDatabase sqlitedatabase)
    {
        Object obj;
        java.util.List list;
        Log.e("DefaultDatabaseErrorHandler", (new StringBuilder()).append("Corruption reported by sqlite on database: ").append(sqlitedatabase.getPath()).toString());
        if(!sqlitedatabase.isOpen())
        {
            deleteDatabaseFile(sqlitedatabase.getPath());
            return;
        }
        obj = null;
        list = null;
        java.util.List list1 = sqlitedatabase.getAttachedDbs();
        list = list1;
_L2:
        obj = list;
        try
        {
            sqlitedatabase.close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        if(list != null)
            for(sqlitedatabase = list.iterator(); sqlitedatabase.hasNext(); deleteDatabaseFile((String)((Pair)sqlitedatabase.next()).second));
        else
            deleteDatabaseFile(sqlitedatabase.getPath());
        break MISSING_BLOCK_LABEL_121;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        if(obj != null)
            for(sqlitedatabase = ((Iterable) (obj)).iterator(); sqlitedatabase.hasNext(); deleteDatabaseFile((String)((Pair)sqlitedatabase.next()).second));
        else
            deleteDatabaseFile(sqlitedatabase.getPath());
        throw exception;
    }

    private static final String TAG = "DefaultDatabaseErrorHandler";
}
