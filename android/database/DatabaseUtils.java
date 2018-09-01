// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.content.*;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteStatement;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.CollationKey;
import java.text.Collator;
import java.util.*;

// Referenced classes of package android.database:
//            Cursor, CursorWindow, SQLException

public class DatabaseUtils
{
    public static class InsertHelper
    {

        private void buildSQL()
            throws SQLException
        {
            StringBuilder stringbuilder;
            StringBuilder stringbuilder1;
            int i;
            Object obj;
            Cursor cursor;
            stringbuilder = new StringBuilder(128);
            stringbuilder.append("INSERT INTO ");
            stringbuilder.append(mTableName);
            stringbuilder.append(" (");
            stringbuilder1 = new StringBuilder(128);
            stringbuilder1.append("VALUES (");
            i = 1;
            obj = null;
            cursor = ((Cursor) (obj));
            Object obj1 = mDb;
            cursor = ((Cursor) (obj));
            Object obj2 = JVM INSTR new #48  <Class StringBuilder>;
            cursor = ((Cursor) (obj));
            ((StringBuilder) (obj2)).StringBuilder();
            cursor = ((Cursor) (obj));
            obj2 = ((SQLiteDatabase) (obj1)).rawQuery(((StringBuilder) (obj2)).append("PRAGMA table_info(").append(mTableName).append(")").toString(), null);
            cursor = ((Cursor) (obj2));
            obj = JVM INSTR new #78  <Class HashMap>;
            cursor = ((Cursor) (obj2));
            ((HashMap) (obj)).HashMap(((Cursor) (obj2)).getCount());
            cursor = ((Cursor) (obj2));
            mColumns = ((HashMap) (obj));
_L6:
            cursor = ((Cursor) (obj2));
            if(!((Cursor) (obj2)).moveToNext())
                break MISSING_BLOCK_LABEL_396;
            cursor = ((Cursor) (obj2));
            obj = ((Cursor) (obj2)).getString(1);
            cursor = ((Cursor) (obj2));
            obj1 = ((Cursor) (obj2)).getString(4);
            cursor = ((Cursor) (obj2));
            mColumns.put(obj, Integer.valueOf(i));
            cursor = ((Cursor) (obj2));
            stringbuilder.append("'");
            cursor = ((Cursor) (obj2));
            stringbuilder.append(((String) (obj)));
            cursor = ((Cursor) (obj2));
            stringbuilder.append("'");
            if(obj1 != null) goto _L2; else goto _L1
_L1:
            cursor = ((Cursor) (obj2));
            stringbuilder1.append("?");
_L4:
            cursor = ((Cursor) (obj2));
            if(i == ((Cursor) (obj2)).getCount())
                obj = ") ";
            else
                obj = ", ";
            cursor = ((Cursor) (obj2));
            stringbuilder.append(((String) (obj)));
            cursor = ((Cursor) (obj2));
            if(i == ((Cursor) (obj2)).getCount())
                obj = ");";
            else
                obj = ", ";
            cursor = ((Cursor) (obj2));
            stringbuilder1.append(((String) (obj)));
            i++;
            continue; /* Loop/switch isn't completed */
_L2:
            cursor = ((Cursor) (obj2));
            stringbuilder1.append("COALESCE(?, ");
            cursor = ((Cursor) (obj2));
            stringbuilder1.append(((String) (obj1)));
            cursor = ((Cursor) (obj2));
            stringbuilder1.append(")");
            if(true) goto _L4; else goto _L3
_L3:
            obj;
            if(cursor != null)
                cursor.close();
            throw obj;
            if(obj2 != null)
                ((Cursor) (obj2)).close();
            stringbuilder.append(stringbuilder1);
            mInsertSQL = stringbuilder.toString();
            return;
            if(true) goto _L6; else goto _L5
_L5:
        }

        private SQLiteStatement getStatement(boolean flag)
            throws SQLException
        {
            if(flag)
            {
                if(mReplaceStatement == null)
                {
                    if(mInsertSQL == null)
                        buildSQL();
                    String s = (new StringBuilder()).append("INSERT OR REPLACE").append(mInsertSQL.substring(6)).toString();
                    mReplaceStatement = mDb.compileStatement(s);
                }
                return mReplaceStatement;
            }
            if(mInsertStatement == null)
            {
                if(mInsertSQL == null)
                    buildSQL();
                mInsertStatement = mDb.compileStatement(mInsertSQL);
            }
            return mInsertStatement;
        }

        private long insertInternal(ContentValues contentvalues, boolean flag)
        {
            mDb.beginTransactionNonExclusive();
            Object obj;
            try
            {
                obj = getStatement(flag);
                ((SQLiteStatement) (obj)).clearBindings();
                java.util.Map.Entry entry;
                for(Iterator iterator = contentvalues.valueSet().iterator(); iterator.hasNext(); DatabaseUtils.bindObjectToProgram(((SQLiteProgram) (obj)), getColumnIndex((String)entry.getKey()), entry.getValue()))
                    entry = (java.util.Map.Entry)iterator.next();

                break MISSING_BLOCK_LABEL_133;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj) { }
            StringBuilder stringbuilder = JVM INSTR new #48  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("DatabaseUtils", stringbuilder.append("Error inserting ").append(contentvalues).append(" into table  ").append(mTableName).toString(), ((Throwable) (obj)));
            mDb.endTransaction();
            return -1L;
            long l;
            l = ((SQLiteStatement) (obj)).executeInsert();
            mDb.setTransactionSuccessful();
            mDb.endTransaction();
            return l;
            contentvalues;
            mDb.endTransaction();
            throw contentvalues;
        }

        public void bind(int i, double d)
        {
            mPreparedStatement.bindDouble(i, d);
        }

        public void bind(int i, float f)
        {
            mPreparedStatement.bindDouble(i, f);
        }

        public void bind(int i, int j)
        {
            mPreparedStatement.bindLong(i, j);
        }

        public void bind(int i, long l)
        {
            mPreparedStatement.bindLong(i, l);
        }

        public void bind(int i, String s)
        {
            if(s == null)
                mPreparedStatement.bindNull(i);
            else
                mPreparedStatement.bindString(i, s);
        }

        public void bind(int i, boolean flag)
        {
            SQLiteStatement sqlitestatement = mPreparedStatement;
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            sqlitestatement.bindLong(i, j);
        }

        public void bind(int i, byte abyte0[])
        {
            if(abyte0 == null)
                mPreparedStatement.bindNull(i);
            else
                mPreparedStatement.bindBlob(i, abyte0);
        }

        public void bindNull(int i)
        {
            mPreparedStatement.bindNull(i);
        }

        public void close()
        {
            if(mInsertStatement != null)
            {
                mInsertStatement.close();
                mInsertStatement = null;
            }
            if(mReplaceStatement != null)
            {
                mReplaceStatement.close();
                mReplaceStatement = null;
            }
            mInsertSQL = null;
            mColumns = null;
        }

        public long execute()
        {
            if(mPreparedStatement == null)
                throw new IllegalStateException("you must prepare this inserter before calling execute");
            long l = mPreparedStatement.executeInsert();
            mPreparedStatement = null;
            return l;
            SQLException sqlexception;
            sqlexception;
            StringBuilder stringbuilder = JVM INSTR new #48  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("DatabaseUtils", stringbuilder.append("Error executing InsertHelper with table ").append(mTableName).toString(), sqlexception);
            mPreparedStatement = null;
            return -1L;
            Exception exception;
            exception;
            mPreparedStatement = null;
            throw exception;
        }

        public int getColumnIndex(String s)
        {
            getStatement(false);
            Integer integer = (Integer)mColumns.get(s);
            if(integer == null)
                throw new IllegalArgumentException((new StringBuilder()).append("column '").append(s).append("' is invalid").toString());
            else
                return integer.intValue();
        }

        public long insert(ContentValues contentvalues)
        {
            return insertInternal(contentvalues, false);
        }

        public void prepareForInsert()
        {
            mPreparedStatement = getStatement(false);
            mPreparedStatement.clearBindings();
        }

        public void prepareForReplace()
        {
            mPreparedStatement = getStatement(true);
            mPreparedStatement.clearBindings();
        }

        public long replace(ContentValues contentvalues)
        {
            return insertInternal(contentvalues, true);
        }

        public static final int TABLE_INFO_PRAGMA_COLUMNNAME_INDEX = 1;
        public static final int TABLE_INFO_PRAGMA_DEFAULT_INDEX = 4;
        private HashMap mColumns;
        private final SQLiteDatabase mDb;
        private String mInsertSQL;
        private SQLiteStatement mInsertStatement;
        private SQLiteStatement mPreparedStatement;
        private SQLiteStatement mReplaceStatement;
        private final String mTableName;

        public InsertHelper(SQLiteDatabase sqlitedatabase, String s)
        {
            mInsertSQL = null;
            mInsertStatement = null;
            mReplaceStatement = null;
            mPreparedStatement = null;
            mDb = sqlitedatabase;
            mTableName = s;
        }
    }


    public DatabaseUtils()
    {
    }

    public static void appendEscapedSQLString(StringBuilder stringbuilder, String s)
    {
        stringbuilder.append('\'');
        if(s.indexOf('\'') != -1)
        {
            int i = s.length();
            for(int j = 0; j < i; j++)
            {
                char c = s.charAt(j);
                if(c == '\'')
                    stringbuilder.append('\'');
                stringbuilder.append(c);
            }

        } else
        {
            stringbuilder.append(s);
        }
        stringbuilder.append('\'');
    }

    public static String[] appendSelectionArgs(String as[], String as1[])
    {
        if(as == null || as.length == 0)
        {
            return as1;
        } else
        {
            String as2[] = new String[as.length + as1.length];
            System.arraycopy(as, 0, as2, 0, as.length);
            System.arraycopy(as1, 0, as2, as.length, as1.length);
            return as2;
        }
    }

    public static final void appendValueToSql(StringBuilder stringbuilder, Object obj)
    {
        if(obj == null)
            stringbuilder.append("NULL");
        else
        if(obj instanceof Boolean)
        {
            if(((Boolean)obj).booleanValue())
                stringbuilder.append('1');
            else
                stringbuilder.append('0');
        } else
        {
            appendEscapedSQLString(stringbuilder, obj.toString());
        }
    }

    public static void bindObjectToProgram(SQLiteProgram sqliteprogram, int i, Object obj)
    {
        if(obj == null)
            sqliteprogram.bindNull(i);
        else
        if((obj instanceof Double) || (obj instanceof Float))
            sqliteprogram.bindDouble(i, ((Number)obj).doubleValue());
        else
        if(obj instanceof Number)
            sqliteprogram.bindLong(i, ((Number)obj).longValue());
        else
        if(obj instanceof Boolean)
        {
            if(((Boolean)obj).booleanValue())
                sqliteprogram.bindLong(i, 1L);
            else
                sqliteprogram.bindLong(i, 0L);
        } else
        if(obj instanceof byte[])
            sqliteprogram.bindBlob(i, (byte[])obj);
        else
            sqliteprogram.bindString(i, obj.toString());
    }

    public static ParcelFileDescriptor blobFileDescriptorForQuery(SQLiteDatabase sqlitedatabase, String s, String as[])
    {
        sqlitedatabase = sqlitedatabase.compileStatement(s);
        s = blobFileDescriptorForQuery(((SQLiteStatement) (sqlitedatabase)), as);
        sqlitedatabase.close();
        return s;
        s;
        sqlitedatabase.close();
        throw s;
    }

    public static ParcelFileDescriptor blobFileDescriptorForQuery(SQLiteStatement sqlitestatement, String as[])
    {
        sqlitestatement.bindAllArgsAsStrings(as);
        return sqlitestatement.simpleQueryForBlobFileDescriptor();
    }

    public static String concatenateWhere(String s, String s1)
    {
        if(TextUtils.isEmpty(s))
            return s1;
        if(TextUtils.isEmpty(s1))
            return s;
        else
            return (new StringBuilder()).append("(").append(s).append(") AND (").append(s1).append(")").toString();
    }

    public static void createDbFromSqlStatements(Context context, String s, int i, String s1)
    {
        int j = 0;
        context = context.openOrCreateDatabase(s, 0, null);
        s = TextUtils.split(s1, ";\n");
        int k = s.length;
        while(j < k) 
        {
            s1 = s[j];
            if(!TextUtils.isEmpty(s1))
                context.execSQL(s1);
            j++;
        }
        context.setVersion(i);
        context.close();
    }

    public static void cursorDoubleToContentValues(Cursor cursor, String s, ContentValues contentvalues, String s1)
    {
        int i = cursor.getColumnIndex(s);
        if(!cursor.isNull(i))
            contentvalues.put(s1, Double.valueOf(cursor.getDouble(i)));
        else
            contentvalues.put(s1, (Double)null);
    }

    public static void cursorDoubleToContentValuesIfPresent(Cursor cursor, ContentValues contentvalues, String s)
    {
        int i = cursor.getColumnIndex(s);
        if(i != -1 && cursor.isNull(i) ^ true)
            contentvalues.put(s, Double.valueOf(cursor.getDouble(i)));
    }

    public static void cursorDoubleToCursorValues(Cursor cursor, String s, ContentValues contentvalues)
    {
        cursorDoubleToContentValues(cursor, s, contentvalues, s);
    }

    public static void cursorFillWindow(Cursor cursor, int i, CursorWindow cursorwindow)
    {
        int j;
        int k;
        if(i < 0 || i >= cursor.getCount())
            return;
        j = cursor.getPosition();
        k = cursor.getColumnCount();
        cursorwindow.clear();
        cursorwindow.setStartPosition(i);
        cursorwindow.setNumColumns(k);
        if(!cursor.moveToPosition(i)) goto _L2; else goto _L1
_L1:
        if(cursorwindow.allocRow()) goto _L3; else goto _L2
_L2:
        cursor.moveToPosition(j);
        return;
_L3:
        int l = 0;
_L13:
        if(l >= k) goto _L5; else goto _L4
_L4:
        cursor.getType(l);
        JVM INSTR tableswitch 0 4: default 124
    //                   0 162
    //                   1 174
    //                   2 194
    //                   3 124
    //                   4 214;
           goto _L6 _L7 _L8 _L9 _L6 _L10
_L6:
        String s = cursor.getString(l);
        byte abyte0[];
        boolean flag;
        if(s != null)
            flag = cursorwindow.putString(s, i, l);
        else
            flag = cursorwindow.putNull(i, l);
        if(flag) goto _L12; else goto _L11
_L11:
        cursorwindow.freeLastRow();
          goto _L2
_L7:
        flag = cursorwindow.putNull(i, l);
        break MISSING_BLOCK_LABEL_150;
_L8:
        flag = cursorwindow.putLong(cursor.getLong(l), i, l);
        break MISSING_BLOCK_LABEL_150;
_L9:
        flag = cursorwindow.putDouble(cursor.getDouble(l), i, l);
        break MISSING_BLOCK_LABEL_150;
_L10:
        abyte0 = cursor.getBlob(l);
        if(abyte0 != null)
            flag = cursorwindow.putBlob(abyte0, i, l);
        else
            flag = cursorwindow.putNull(i, l);
        break MISSING_BLOCK_LABEL_150;
_L12:
        l++;
          goto _L13
_L5:
        i++;
        if(!cursor.moveToNext()) goto _L2; else goto _L1
    }

    public static void cursorFloatToContentValuesIfPresent(Cursor cursor, ContentValues contentvalues, String s)
    {
        int i = cursor.getColumnIndex(s);
        if(i != -1 && cursor.isNull(i) ^ true)
            contentvalues.put(s, Float.valueOf(cursor.getFloat(i)));
    }

    public static void cursorIntToContentValues(Cursor cursor, String s, ContentValues contentvalues)
    {
        cursorIntToContentValues(cursor, s, contentvalues, s);
    }

    public static void cursorIntToContentValues(Cursor cursor, String s, ContentValues contentvalues, String s1)
    {
        int i = cursor.getColumnIndex(s);
        if(!cursor.isNull(i))
            contentvalues.put(s1, Integer.valueOf(cursor.getInt(i)));
        else
            contentvalues.put(s1, (Integer)null);
    }

    public static void cursorIntToContentValuesIfPresent(Cursor cursor, ContentValues contentvalues, String s)
    {
        int i = cursor.getColumnIndex(s);
        if(i != -1 && cursor.isNull(i) ^ true)
            contentvalues.put(s, Integer.valueOf(cursor.getInt(i)));
    }

    public static void cursorLongToContentValues(Cursor cursor, String s, ContentValues contentvalues)
    {
        cursorLongToContentValues(cursor, s, contentvalues, s);
    }

    public static void cursorLongToContentValues(Cursor cursor, String s, ContentValues contentvalues, String s1)
    {
        int i = cursor.getColumnIndex(s);
        if(!cursor.isNull(i))
            contentvalues.put(s1, Long.valueOf(cursor.getLong(i)));
        else
            contentvalues.put(s1, (Long)null);
    }

    public static void cursorLongToContentValuesIfPresent(Cursor cursor, ContentValues contentvalues, String s)
    {
        int i = cursor.getColumnIndex(s);
        if(i != -1 && cursor.isNull(i) ^ true)
            contentvalues.put(s, Long.valueOf(cursor.getLong(i)));
    }

    public static int cursorPickFillWindowStartPosition(int i, int j)
    {
        return Math.max(i - j / 3, 0);
    }

    public static void cursorRowToContentValues(Cursor cursor, ContentValues contentvalues)
    {
        String as[] = cursor.getColumnNames();
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            if(cursor.getType(j) == 4)
                contentvalues.put(as[j], cursor.getBlob(j));
            else
                contentvalues.put(as[j], cursor.getString(j));
            j++;
        }
    }

    public static void cursorShortToContentValuesIfPresent(Cursor cursor, ContentValues contentvalues, String s)
    {
        int i = cursor.getColumnIndex(s);
        if(i != -1 && cursor.isNull(i) ^ true)
            contentvalues.put(s, Short.valueOf(cursor.getShort(i)));
    }

    public static void cursorStringToContentValues(Cursor cursor, String s, ContentValues contentvalues)
    {
        cursorStringToContentValues(cursor, s, contentvalues, s);
    }

    public static void cursorStringToContentValues(Cursor cursor, String s, ContentValues contentvalues, String s1)
    {
        contentvalues.put(s1, cursor.getString(cursor.getColumnIndexOrThrow(s)));
    }

    public static void cursorStringToContentValuesIfPresent(Cursor cursor, ContentValues contentvalues, String s)
    {
        int i = cursor.getColumnIndex(s);
        if(i != -1 && cursor.isNull(i) ^ true)
            contentvalues.put(s, cursor.getString(i));
    }

    public static void cursorStringToInsertHelper(Cursor cursor, String s, InsertHelper inserthelper, int i)
    {
        inserthelper.bind(i, cursor.getString(cursor.getColumnIndexOrThrow(s)));
    }

    public static void dumpCurrentRow(Cursor cursor)
    {
        dumpCurrentRow(cursor, System.out);
    }

    public static void dumpCurrentRow(Cursor cursor, PrintStream printstream)
    {
        String as[] = cursor.getColumnNames();
        printstream.println((new StringBuilder()).append("").append(cursor.getPosition()).append(" {").toString());
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s;
            try
            {
                s = cursor.getString(j);
            }
            catch(SQLiteException sqliteexception)
            {
                sqliteexception = "<unprintable>";
            }
            printstream.println((new StringBuilder()).append("   ").append(as[j]).append('=').append(s).toString());
            j++;
        }
        printstream.println("}");
    }

    public static void dumpCurrentRow(Cursor cursor, StringBuilder stringbuilder)
    {
        String as[] = cursor.getColumnNames();
        stringbuilder.append("").append(cursor.getPosition()).append(" {\n");
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s;
            try
            {
                s = cursor.getString(j);
            }
            catch(SQLiteException sqliteexception)
            {
                sqliteexception = "<unprintable>";
            }
            stringbuilder.append("   ").append(as[j]).append('=').append(s).append("\n");
            j++;
        }
        stringbuilder.append("}\n");
    }

    public static String dumpCurrentRowToString(Cursor cursor)
    {
        StringBuilder stringbuilder = new StringBuilder();
        dumpCurrentRow(cursor, stringbuilder);
        return stringbuilder.toString();
    }

    public static void dumpCursor(Cursor cursor)
    {
        dumpCursor(cursor, System.out);
    }

    public static void dumpCursor(Cursor cursor, PrintStream printstream)
    {
        printstream.println((new StringBuilder()).append(">>>>> Dumping cursor ").append(cursor).toString());
        if(cursor != null)
        {
            int i = cursor.getPosition();
            cursor.moveToPosition(-1);
            for(; cursor.moveToNext(); dumpCurrentRow(cursor, printstream));
            cursor.moveToPosition(i);
        }
        printstream.println("<<<<<");
    }

    public static void dumpCursor(Cursor cursor, StringBuilder stringbuilder)
    {
        stringbuilder.append(">>>>> Dumping cursor ").append(cursor).append("\n");
        if(cursor != null)
        {
            int i = cursor.getPosition();
            cursor.moveToPosition(-1);
            for(; cursor.moveToNext(); dumpCurrentRow(cursor, stringbuilder));
            cursor.moveToPosition(i);
        }
        stringbuilder.append("<<<<<\n");
    }

    public static String dumpCursorToString(Cursor cursor)
    {
        StringBuilder stringbuilder = new StringBuilder();
        dumpCursor(cursor, stringbuilder);
        return stringbuilder.toString();
    }

    private static char[] encodeHex(byte abyte0[])
    {
        int i = abyte0.length;
        char ac[] = new char[i << 1];
        int j = 0;
        int k = 0;
        for(; j < i; j++)
        {
            int l = k + 1;
            ac[k] = DIGITS[(abyte0[j] & 0xf0) >>> 4];
            k = l + 1;
            ac[l] = DIGITS[abyte0[j] & 0xf];
        }

        return ac;
    }

    public static int findRowIdColumnIndex(String as[])
    {
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(as[j].equals("_id"))
                return j;

        return -1;
    }

    public static String getCollationKey(String s)
    {
        s = getCollationKeyInBytes(s);
        try
        {
            s = new String(s, 0, getKeyLen(s), "ISO8859_1");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return "";
        }
        return s;
    }

    private static byte[] getCollationKeyInBytes(String s)
    {
        if(mColl == null)
        {
            mColl = Collator.getInstance();
            mColl.setStrength(0);
        }
        return mColl.getCollationKey(s).toByteArray();
    }

    public static String getHexCollationKey(String s)
    {
        s = getCollationKeyInBytes(s);
        return new String(encodeHex(s), 0, getKeyLen(s) * 2);
    }

    private static int getKeyLen(byte abyte0[])
    {
        if(abyte0[abyte0.length - 1] != 0)
            return abyte0.length;
        else
            return abyte0.length - 1;
    }

    public static int getSqlStatementType(String s)
    {
        s = s.trim();
        if(s.length() < 3)
            return 99;
        s = s.substring(0, 3).toUpperCase(Locale.ROOT);
        if(s.equals("SEL"))
            return 1;
        if(s.equals("INS") || s.equals("UPD") || s.equals("REP") || s.equals("DEL"))
            return 2;
        if(s.equals("ATT"))
            return 3;
        if(s.equals("COM"))
            return 5;
        if(s.equals("END"))
            return 5;
        if(s.equals("ROL"))
            return 6;
        if(s.equals("BEG"))
            return 4;
        if(s.equals("PRA"))
            return 7;
        if(s.equals("CRE") || s.equals("DRO") || s.equals("ALT"))
            return 8;
        return !s.equals("ANA") && !s.equals("DET") ? 99 : 9;
    }

    public static int getTypeOfObject(Object obj)
    {
        if(obj == null)
            return 0;
        if(obj instanceof byte[])
            return 4;
        if((obj instanceof Float) || (obj instanceof Double))
            return 2;
        return !(obj instanceof Long) && !(obj instanceof Integer) && !(obj instanceof Short) && !(obj instanceof Byte) ? 3 : 1;
    }

    public static long longForQuery(SQLiteDatabase sqlitedatabase, String s, String as[])
    {
        sqlitedatabase = sqlitedatabase.compileStatement(s);
        long l = longForQuery(((SQLiteStatement) (sqlitedatabase)), as);
        sqlitedatabase.close();
        return l;
        s;
        sqlitedatabase.close();
        throw s;
    }

    public static long longForQuery(SQLiteStatement sqlitestatement, String as[])
    {
        sqlitestatement.bindAllArgsAsStrings(as);
        return sqlitestatement.simpleQueryForLong();
    }

    public static boolean queryIsEmpty(SQLiteDatabase sqlitedatabase, String s)
    {
        boolean flag;
        if(longForQuery(sqlitedatabase, (new StringBuilder()).append("select exists(select 1 from ").append(s).append(")").toString(), null) == 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static long queryNumEntries(SQLiteDatabase sqlitedatabase, String s)
    {
        return queryNumEntries(sqlitedatabase, s, null, null);
    }

    public static long queryNumEntries(SQLiteDatabase sqlitedatabase, String s, String s1)
    {
        return queryNumEntries(sqlitedatabase, s, s1, null);
    }

    public static long queryNumEntries(SQLiteDatabase sqlitedatabase, String s, String s1, String as[])
    {
        if(!TextUtils.isEmpty(s1))
            s1 = (new StringBuilder()).append(" where ").append(s1).toString();
        else
            s1 = "";
        return longForQuery(sqlitedatabase, (new StringBuilder()).append("select count(*) from ").append(s).append(s1).toString(), as);
    }

    public static final void readExceptionFromParcel(Parcel parcel)
    {
        int i = parcel.readExceptionCode();
        if(i == 0)
        {
            return;
        } else
        {
            readExceptionFromParcel(parcel, parcel.readString(), i);
            return;
        }
    }

    private static final void readExceptionFromParcel(Parcel parcel, String s, int i)
    {
        switch(i)
        {
        case 10: // '\n'
        default:
            parcel.readException(i, s);
            return;

        case 2: // '\002'
            throw new IllegalArgumentException(s);

        case 3: // '\003'
            throw new UnsupportedOperationException(s);

        case 4: // '\004'
            throw new SQLiteAbortException(s);

        case 5: // '\005'
            throw new SQLiteConstraintException(s);

        case 6: // '\006'
            throw new SQLiteDatabaseCorruptException(s);

        case 7: // '\007'
            throw new SQLiteFullException(s);

        case 8: // '\b'
            throw new SQLiteDiskIOException(s);

        case 9: // '\t'
            throw new SQLiteException(s);

        case 11: // '\013'
            throw new OperationCanceledException(s);
        }
    }

    public static void readExceptionWithFileNotFoundExceptionFromParcel(Parcel parcel)
        throws FileNotFoundException
    {
        int i = parcel.readExceptionCode();
        if(i == 0)
            return;
        String s = parcel.readString();
        if(i == 1)
        {
            throw new FileNotFoundException(s);
        } else
        {
            readExceptionFromParcel(parcel, s, i);
            return;
        }
    }

    public static void readExceptionWithOperationApplicationExceptionFromParcel(Parcel parcel)
        throws OperationApplicationException
    {
        int i = parcel.readExceptionCode();
        if(i == 0)
            return;
        String s = parcel.readString();
        if(i == 10)
        {
            throw new OperationApplicationException(s);
        } else
        {
            readExceptionFromParcel(parcel, s, i);
            return;
        }
    }

    public static String sqlEscapeString(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        appendEscapedSQLString(stringbuilder, s);
        return stringbuilder.toString();
    }

    public static String stringForQuery(SQLiteDatabase sqlitedatabase, String s, String as[])
    {
        sqlitedatabase = sqlitedatabase.compileStatement(s);
        s = stringForQuery(((SQLiteStatement) (sqlitedatabase)), as);
        sqlitedatabase.close();
        return s;
        s;
        sqlitedatabase.close();
        throw s;
    }

    public static String stringForQuery(SQLiteStatement sqlitestatement, String as[])
    {
        sqlitestatement.bindAllArgsAsStrings(as);
        return sqlitestatement.simpleQueryForString();
    }

    public static final void writeExceptionToParcel(Parcel parcel, Exception exception)
    {
        boolean flag = true;
        int i;
        if(exception instanceof FileNotFoundException)
        {
            i = 1;
            flag = false;
        } else
        if(exception instanceof IllegalArgumentException)
            i = 2;
        else
        if(exception instanceof UnsupportedOperationException)
            i = 3;
        else
        if(exception instanceof SQLiteAbortException)
            i = 4;
        else
        if(exception instanceof SQLiteConstraintException)
            i = 5;
        else
        if(exception instanceof SQLiteDatabaseCorruptException)
            i = 6;
        else
        if(exception instanceof SQLiteFullException)
            i = 7;
        else
        if(exception instanceof SQLiteDiskIOException)
            i = 8;
        else
        if(exception instanceof SQLiteException)
            i = 9;
        else
        if(exception instanceof OperationApplicationException)
            i = 10;
        else
        if(exception instanceof OperationCanceledException)
        {
            i = 11;
            flag = false;
        } else
        {
            parcel.writeException(exception);
            Log.e("DatabaseUtils", "Writing exception to parcel", exception);
            return;
        }
        parcel.writeInt(i);
        parcel.writeString(exception.getMessage());
        if(flag)
            Log.e("DatabaseUtils", "Writing exception to parcel", exception);
    }

    private static final boolean DEBUG = false;
    private static final char DIGITS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };
    public static final int STATEMENT_ABORT = 6;
    public static final int STATEMENT_ATTACH = 3;
    public static final int STATEMENT_BEGIN = 4;
    public static final int STATEMENT_COMMIT = 5;
    public static final int STATEMENT_DDL = 8;
    public static final int STATEMENT_OTHER = 99;
    public static final int STATEMENT_PRAGMA = 7;
    public static final int STATEMENT_SELECT = 1;
    public static final int STATEMENT_UNPREPARED = 9;
    public static final int STATEMENT_UPDATE = 2;
    private static final String TAG = "DatabaseUtils";
    private static Collator mColl = null;

}
