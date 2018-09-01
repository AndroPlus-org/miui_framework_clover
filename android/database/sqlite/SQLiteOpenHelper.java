// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.os.FileUtils;
import android.util.Log;
import java.io.File;

// Referenced classes of package android.database.sqlite:
//            SQLiteDatabase, SQLiteException

public abstract class SQLiteOpenHelper
{

    public SQLiteOpenHelper(Context context, String s, SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        this(context, s, cursorfactory, i, null);
    }

    public SQLiteOpenHelper(Context context, String s, SQLiteDatabase.CursorFactory cursorfactory, int i, int j, DatabaseErrorHandler databaseerrorhandler)
    {
        if(i < 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Version must be >= 1, was ").append(i).toString());
        } else
        {
            mContext = context;
            mName = s;
            mNewVersion = i;
            mMinimumSupportedVersion = Math.max(0, j);
            mOpenParamsBuilder = new SQLiteDatabase.OpenParams.Builder();
            mOpenParamsBuilder.setCursorFactory(cursorfactory);
            mOpenParamsBuilder.setErrorHandler(databaseerrorhandler);
            mOpenParamsBuilder.addOpenFlags(0x10000000);
            return;
        }
    }

    public SQLiteOpenHelper(Context context, String s, SQLiteDatabase.CursorFactory cursorfactory, int i, DatabaseErrorHandler databaseerrorhandler)
    {
        this(context, s, cursorfactory, i, 0, databaseerrorhandler);
    }

    private SQLiteDatabase getDatabaseLocked(boolean flag)
    {
        Object obj;
        Object obj1;
        if(mDatabase != null)
            if(!mDatabase.isOpen())
                mDatabase = null;
            else
            if(!flag || mDatabase.isReadOnly() ^ true)
                return mDatabase;
        if(mIsInitializing)
            throw new IllegalStateException("getDatabase called recursively");
        obj = mDatabase;
        obj1 = obj;
        mIsInitializing = true;
        if(obj == null) goto _L2; else goto _L1
_L1:
        Object obj2;
        obj2 = obj;
        if(!flag)
            break MISSING_BLOCK_LABEL_104;
        obj2 = obj;
        obj1 = obj;
        if(!((SQLiteDatabase) (obj)).isReadOnly())
            break MISSING_BLOCK_LABEL_104;
        obj1 = obj;
        ((SQLiteDatabase) (obj)).reopenReadWrite();
        obj2 = obj;
_L7:
        obj1 = obj2;
        onConfigure(((SQLiteDatabase) (obj2)));
        obj1 = obj2;
        int i = ((SQLiteDatabase) (obj2)).getVersion();
        obj1 = obj2;
        if(i == mNewVersion) goto _L4; else goto _L3
_L3:
        obj1 = obj2;
        if(!((SQLiteDatabase) (obj2)).isReadOnly()) goto _L6; else goto _L5
_L5:
        obj1 = obj2;
        Object obj3 = JVM INSTR new #126 <Class SQLiteException>;
        obj1 = obj2;
        obj = JVM INSTR new #41  <Class StringBuilder>;
        obj1 = obj2;
        ((StringBuilder) (obj)).StringBuilder();
        obj1 = obj2;
        ((SQLiteException) (obj3)).SQLiteException(((StringBuilder) (obj)).append("Can't upgrade read-only database from version ").append(((SQLiteDatabase) (obj2)).getVersion()).append(" to ").append(mNewVersion).append(": ").append(mName).toString());
        obj1 = obj2;
        throw obj3;
        obj2;
        mIsInitializing = false;
        if(obj1 != null && obj1 != mDatabase)
            ((SQLiteDatabase) (obj1)).close();
        throw obj2;
_L2:
        obj1 = obj;
        if(mName != null)
            break MISSING_BLOCK_LABEL_275;
        obj1 = obj;
        obj2 = SQLiteDatabase.createInMemory(mOpenParamsBuilder.build());
          goto _L7
        obj1 = obj;
        obj3 = mContext.getDatabasePath(mName);
        obj1 = obj;
        SQLiteDatabase.OpenParams openparams = mOpenParamsBuilder.build();
        obj1 = obj;
        obj2 = obj;
        obj = SQLiteDatabase.openDatabase(((File) (obj3)), openparams);
        obj1 = obj;
        obj2 = obj;
        setFilePermissionsForDb(((File) (obj3)).getPath());
        obj2 = obj;
          goto _L7
        SQLException sqlexception;
        sqlexception;
        if(!flag)
            break MISSING_BLOCK_LABEL_345;
        obj1 = obj2;
        throw sqlexception;
        obj1 = obj2;
        obj = TAG;
        obj1 = obj2;
        StringBuilder stringbuilder = JVM INSTR new #41  <Class StringBuilder>;
        obj1 = obj2;
        stringbuilder.StringBuilder();
        obj1 = obj2;
        Log.e(((String) (obj)), stringbuilder.append("Couldn't open ").append(mName).append(" for writing (will try read-only):").toString(), sqlexception);
        obj1 = obj2;
        obj2 = SQLiteDatabase.openDatabase(((File) (obj3)), openparams.toBuilder().addOpenFlags(1).build());
          goto _L7
_L6:
        if(i <= 0)
            break MISSING_BLOCK_LABEL_593;
        obj1 = obj2;
        if(i >= mMinimumSupportedVersion)
            break MISSING_BLOCK_LABEL_593;
        obj1 = obj2;
        obj = JVM INSTR new #156 <Class File>;
        obj1 = obj2;
        ((File) (obj)).File(((SQLiteDatabase) (obj2)).getPath());
        obj1 = obj2;
        onBeforeDelete(((SQLiteDatabase) (obj2)));
        obj1 = obj2;
        ((SQLiteDatabase) (obj2)).close();
        obj1 = obj2;
        if(!SQLiteDatabase.deleteDatabase(((File) (obj))))
            break MISSING_BLOCK_LABEL_531;
        obj1 = obj2;
        mIsInitializing = false;
        obj1 = obj2;
        obj = getDatabaseLocked(flag);
        mIsInitializing = false;
        if(obj2 != null && obj2 != mDatabase)
            ((SQLiteDatabase) (obj2)).close();
        return ((SQLiteDatabase) (obj));
        obj1 = obj2;
        obj3 = JVM INSTR new #107 <Class IllegalStateException>;
        obj1 = obj2;
        obj = JVM INSTR new #41  <Class StringBuilder>;
        obj1 = obj2;
        ((StringBuilder) (obj)).StringBuilder();
        obj1 = obj2;
        ((IllegalStateException) (obj3)).IllegalStateException(((StringBuilder) (obj)).append("Unable to delete obsolete database ").append(mName).append(" with version ").append(i).toString());
        obj1 = obj2;
        throw obj3;
        obj1 = obj2;
        ((SQLiteDatabase) (obj2)).beginTransaction();
        if(i != 0) goto _L9; else goto _L8
_L8:
        onCreate(((SQLiteDatabase) (obj2)));
_L10:
        ((SQLiteDatabase) (obj2)).setVersion(mNewVersion);
        ((SQLiteDatabase) (obj2)).setTransactionSuccessful();
        obj1 = obj2;
        ((SQLiteDatabase) (obj2)).endTransaction();
_L4:
        obj1 = obj2;
        onOpen(((SQLiteDatabase) (obj2)));
        obj1 = obj2;
        if(!((SQLiteDatabase) (obj2)).isReadOnly())
            break MISSING_BLOCK_LABEL_706;
        obj1 = obj2;
        obj3 = TAG;
        obj1 = obj2;
        obj = JVM INSTR new #41  <Class StringBuilder>;
        obj1 = obj2;
        ((StringBuilder) (obj)).StringBuilder();
        obj1 = obj2;
        Log.w(((String) (obj3)), ((StringBuilder) (obj)).append("Opened ").append(mName).append(" in read-only mode").toString());
        obj1 = obj2;
        mDatabase = ((SQLiteDatabase) (obj2));
        mIsInitializing = false;
        if(obj2 != null && obj2 != mDatabase)
            ((SQLiteDatabase) (obj2)).close();
        return ((SQLiteDatabase) (obj2));
_L9:
        if(i <= mNewVersion)
            break MISSING_BLOCK_LABEL_780;
        onDowngrade(((SQLiteDatabase) (obj2)), i, mNewVersion);
          goto _L10
        Exception exception;
        exception;
        obj1 = obj2;
        ((SQLiteDatabase) (obj2)).endTransaction();
        obj1 = obj2;
        throw exception;
        onUpgrade(((SQLiteDatabase) (obj2)), i, mNewVersion);
          goto _L10
    }

    private static void setFilePermissionsForDb(String s)
    {
        FileUtils.setPermissions(s, 432, -1, -1);
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        if(mIsInitializing)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #107 <Class IllegalStateException>;
            illegalstateexception.IllegalStateException("Closed during initialization");
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(mDatabase != null && mDatabase.isOpen())
        {
            mDatabase.close();
            mDatabase = null;
        }
        this;
        JVM INSTR monitorexit ;
    }

    public String getDatabaseName()
    {
        return mName;
    }

    public SQLiteDatabase getReadableDatabase()
    {
        this;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = getDatabaseLocked(false);
        this;
        JVM INSTR monitorexit ;
        return sqlitedatabase;
        Exception exception;
        exception;
        throw exception;
    }

    public SQLiteDatabase getWritableDatabase()
    {
        this;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = getDatabaseLocked(true);
        this;
        JVM INSTR monitorexit ;
        return sqlitedatabase;
        Exception exception;
        exception;
        throw exception;
    }

    public void onBeforeDelete(SQLiteDatabase sqlitedatabase)
    {
    }

    public void onConfigure(SQLiteDatabase sqlitedatabase)
    {
    }

    public abstract void onCreate(SQLiteDatabase sqlitedatabase);

    public void onDowngrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        throw new SQLiteException((new StringBuilder()).append("Can't downgrade database from version ").append(i).append(" to ").append(j).toString());
    }

    public void onOpen(SQLiteDatabase sqlitedatabase)
    {
    }

    public abstract void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j);

    public void setIdleConnectionTimeout(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(mDatabase != null && mDatabase.isOpen())
        {
            IllegalStateException illegalstateexception = JVM INSTR new #107 <Class IllegalStateException>;
            illegalstateexception.IllegalStateException("Connection timeout setting cannot be changed after opening the database");
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_36;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mOpenParamsBuilder.setIdleConnectionTimeout(l);
        this;
        JVM INSTR monitorexit ;
    }

    public void setLookasideConfig(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        if(mDatabase != null && mDatabase.isOpen())
        {
            IllegalStateException illegalstateexception = JVM INSTR new #107 <Class IllegalStateException>;
            illegalstateexception.IllegalStateException("Lookaside memory config cannot be changed after opening the database");
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_36;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mOpenParamsBuilder.setLookasideConfig(i, j);
        this;
        JVM INSTR monitorexit ;
    }

    public void setWriteAheadLoggingEnabled(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if(mOpenParamsBuilder.isWriteAheadLoggingEnabled() == flag) goto _L2; else goto _L1
_L1:
        if(mDatabase == null || !mDatabase.isOpen() || !(mDatabase.isReadOnly() ^ true))
            break MISSING_BLOCK_LABEL_54;
        if(!flag)
            break MISSING_BLOCK_LABEL_65;
        mDatabase.enableWriteAheadLogging();
_L3:
        mOpenParamsBuilder.setWriteAheadLoggingEnabled(flag);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        mDatabase.disableWriteAheadLogging();
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    private static final String TAG = android/database/sqlite/SQLiteOpenHelper.getSimpleName();
    private final Context mContext;
    private SQLiteDatabase mDatabase;
    private boolean mIsInitializing;
    private final int mMinimumSupportedVersion;
    private final String mName;
    private final int mNewVersion;
    private final SQLiteDatabase.OpenParams.Builder mOpenParamsBuilder;

}
