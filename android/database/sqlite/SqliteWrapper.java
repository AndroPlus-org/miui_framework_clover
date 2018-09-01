// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

// Referenced classes of package android.database.sqlite:
//            SQLiteException

public final class SqliteWrapper
{

    private SqliteWrapper()
    {
    }

    public static void checkSQLiteException(Context context, SQLiteException sqliteexception)
    {
        if(isLowMemory(sqliteexception))
        {
            Toast.makeText(context, 0x104033e, 0).show();
            return;
        } else
        {
            throw sqliteexception;
        }
    }

    public static int delete(Context context, ContentResolver contentresolver, Uri uri, String s, String as[])
    {
        int i;
        try
        {
            i = contentresolver.delete(uri, s, as);
        }
        // Misplaced declaration of an exception variable
        catch(ContentResolver contentresolver)
        {
            Log.e("SqliteWrapper", "Catch a SQLiteException when delete: ", contentresolver);
            checkSQLiteException(context, contentresolver);
            return -1;
        }
        return i;
    }

    public static Uri insert(Context context, ContentResolver contentresolver, Uri uri, ContentValues contentvalues)
    {
        try
        {
            contentresolver = contentresolver.insert(uri, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch(ContentResolver contentresolver)
        {
            Log.e("SqliteWrapper", "Catch a SQLiteException when insert: ", contentresolver);
            checkSQLiteException(context, contentresolver);
            return null;
        }
        return contentresolver;
    }

    private static boolean isLowMemory(SQLiteException sqliteexception)
    {
        return sqliteexception.getMessage().equals("unable to open database file");
    }

    public static Cursor query(Context context, ContentResolver contentresolver, Uri uri, String as[], String s, String as1[], String s1)
    {
        try
        {
            contentresolver = contentresolver.query(uri, as, s, as1, s1);
        }
        // Misplaced declaration of an exception variable
        catch(ContentResolver contentresolver)
        {
            Log.e("SqliteWrapper", "Catch a SQLiteException when query: ", contentresolver);
            checkSQLiteException(context, contentresolver);
            return null;
        }
        return contentresolver;
    }

    public static boolean requery(Context context, Cursor cursor)
    {
        boolean flag;
        try
        {
            flag = cursor.requery();
        }
        // Misplaced declaration of an exception variable
        catch(Cursor cursor)
        {
            Log.e("SqliteWrapper", "Catch a SQLiteException when requery: ", cursor);
            checkSQLiteException(context, cursor);
            return false;
        }
        return flag;
    }

    public static int update(Context context, ContentResolver contentresolver, Uri uri, ContentValues contentvalues, String s, String as[])
    {
        int i;
        try
        {
            i = contentresolver.update(uri, contentvalues, s, as);
        }
        // Misplaced declaration of an exception variable
        catch(ContentResolver contentresolver)
        {
            Log.e("SqliteWrapper", "Catch a SQLiteException when update: ", contentresolver);
            checkSQLiteException(context, contentresolver);
            return -1;
        }
        return i;
    }

    private static final String SQLITE_EXCEPTION_DETAIL_MESSAGE = "unable to open database file";
    private static final String TAG = "SqliteWrapper";
}
