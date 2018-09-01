// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.app.ActivityManager;
import android.content.ContentValues;
import android.database.*;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.FileFilter;
import java.util.*;

// Referenced classes of package android.database.sqlite:
//            SQLiteClosable, SQLiteDatabaseConfiguration, SQLiteGlobal, SQLiteSession, 
//            SQLiteConnectionPool, SQLiteStatement, SQLiteDatabaseCorruptException, SQLiteException, 
//            SQLiteCustomFunction, SQLiteQueryBuilder, SQLiteDirectCursorDriver, SQLiteCursorDriver, 
//            SQLiteTransactionListener, SQLiteQuery

public final class SQLiteDatabase extends SQLiteClosable
{
    public static interface CursorFactory
    {

        public abstract Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery);
    }

    public static interface CustomFunction
    {

        public abstract void callback(String as[]);
    }

    public static final class OpenParams
    {

        static CursorFactory _2D_get0(OpenParams openparams)
        {
            return openparams.mCursorFactory;
        }

        static DatabaseErrorHandler _2D_get1(OpenParams openparams)
        {
            return openparams.mErrorHandler;
        }

        static long _2D_get2(OpenParams openparams)
        {
            return openparams.mIdleConnectionTimeout;
        }

        static int _2D_get3(OpenParams openparams)
        {
            return openparams.mLookasideSlotCount;
        }

        static int _2D_get4(OpenParams openparams)
        {
            return openparams.mLookasideSlotSize;
        }

        static int _2D_get5(OpenParams openparams)
        {
            return openparams.mOpenFlags;
        }

        public CursorFactory getCursorFactory()
        {
            return mCursorFactory;
        }

        public DatabaseErrorHandler getErrorHandler()
        {
            return mErrorHandler;
        }

        public long getIdleConnectionTimeout()
        {
            return mIdleConnectionTimeout;
        }

        public int getLookasideSlotCount()
        {
            return mLookasideSlotCount;
        }

        public int getLookasideSlotSize()
        {
            return mLookasideSlotSize;
        }

        public int getOpenFlags()
        {
            return mOpenFlags;
        }

        public Builder toBuilder()
        {
            return new Builder(this);
        }

        private final CursorFactory mCursorFactory;
        private final DatabaseErrorHandler mErrorHandler;
        private long mIdleConnectionTimeout;
        private final int mLookasideSlotCount;
        private final int mLookasideSlotSize;
        private final int mOpenFlags;

        private OpenParams(int i, CursorFactory cursorfactory, DatabaseErrorHandler databaseerrorhandler, int j, int k, long l)
        {
            mOpenFlags = i;
            mCursorFactory = cursorfactory;
            mErrorHandler = databaseerrorhandler;
            mLookasideSlotSize = j;
            mLookasideSlotCount = k;
            mIdleConnectionTimeout = l;
        }

        OpenParams(int i, CursorFactory cursorfactory, DatabaseErrorHandler databaseerrorhandler, int j, int k, long l, 
                OpenParams openparams)
        {
            this(i, cursorfactory, databaseerrorhandler, j, k, l);
        }
    }

    public static final class OpenParams.Builder
    {

        public OpenParams.Builder addOpenFlags(int i)
        {
            mOpenFlags = mOpenFlags | i;
            return this;
        }

        public OpenParams build()
        {
            return new OpenParams(mOpenFlags, mCursorFactory, mErrorHandler, mLookasideSlotSize, mLookasideSlotCount, mIdleConnectionTimeout, null);
        }

        public boolean isWriteAheadLoggingEnabled()
        {
            boolean flag = false;
            if((mOpenFlags & 0x20000000) != 0)
                flag = true;
            return flag;
        }

        public OpenParams.Builder removeOpenFlags(int i)
        {
            mOpenFlags = mOpenFlags & i;
            return this;
        }

        public OpenParams.Builder setCursorFactory(CursorFactory cursorfactory)
        {
            mCursorFactory = cursorfactory;
            return this;
        }

        public OpenParams.Builder setErrorHandler(DatabaseErrorHandler databaseerrorhandler)
        {
            mErrorHandler = databaseerrorhandler;
            return this;
        }

        public OpenParams.Builder setIdleConnectionTimeout(long l)
        {
            boolean flag;
            if(l >= 0L)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag, "idle connection timeout cannot be negative");
            mIdleConnectionTimeout = l;
            return this;
        }

        public OpenParams.Builder setLookasideConfig(int i, int j)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            if(i >= 0)
                flag1 = true;
            else
                flag1 = false;
            Preconditions.checkArgument(flag1, "lookasideSlotCount cannot be negative");
            if(j >= 0)
                flag1 = true;
            else
                flag1 = false;
            Preconditions.checkArgument(flag1, "lookasideSlotSize cannot be negative");
            if(i <= 0 || j <= 0) goto _L2; else goto _L1
_L1:
            flag1 = flag;
_L4:
            Preconditions.checkArgument(flag1, (new StringBuilder()).append("Invalid configuration: ").append(i).append(", ").append(j).toString());
            mLookasideSlotSize = i;
            mLookasideSlotCount = j;
            return this;
_L2:
            if(j == 0)
            {
                flag1 = flag;
                if(i == 0)
                    continue; /* Loop/switch isn't completed */
            }
            flag1 = false;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public OpenParams.Builder setOpenFlags(int i)
        {
            mOpenFlags = i;
            return this;
        }

        public void setWriteAheadLoggingEnabled(boolean flag)
        {
            if(flag)
                addOpenFlags(0x20000000);
            else
                removeOpenFlags(0x20000000);
        }

        private CursorFactory mCursorFactory;
        private DatabaseErrorHandler mErrorHandler;
        private long mIdleConnectionTimeout;
        private int mLookasideSlotCount;
        private int mLookasideSlotSize;
        private int mOpenFlags;

        public OpenParams.Builder()
        {
            mLookasideSlotSize = -1;
            mLookasideSlotCount = -1;
            mIdleConnectionTimeout = -1L;
        }

        public OpenParams.Builder(OpenParams openparams)
        {
            mLookasideSlotSize = -1;
            mLookasideSlotCount = -1;
            mIdleConnectionTimeout = -1L;
            mLookasideSlotSize = OpenParams._2D_get4(openparams);
            mLookasideSlotCount = OpenParams._2D_get3(openparams);
            mOpenFlags = OpenParams._2D_get5(openparams);
            mCursorFactory = OpenParams._2D_get0(openparams);
            mErrorHandler = OpenParams._2D_get1(openparams);
        }
    }


    private SQLiteDatabase(String s, int i, CursorFactory cursorfactory, DatabaseErrorHandler databaseerrorhandler, int j, int k, long l)
    {
        long l1;
        long l2;
        mThreadSession = ThreadLocal.withInitial(new _.Lambda.gPaS7kMbZ8xtrrEx06GlwJ2iDWE(this));
        mLock = new Object();
        mCloseGuardLocked = CloseGuard.get();
        mCursorFactory = cursorfactory;
        if(databaseerrorhandler == null)
            databaseerrorhandler = new DefaultDatabaseErrorHandler();
        mErrorHandler = databaseerrorhandler;
        mConfigurationLocked = new SQLiteDatabaseConfiguration(s, i);
        mConfigurationLocked.lookasideSlotSize = j;
        mConfigurationLocked.lookasideSlotCount = k;
        if(ActivityManager.isLowRamDeviceStatic())
        {
            mConfigurationLocked.lookasideSlotCount = 0;
            mConfigurationLocked.lookasideSlotSize = 0;
        }
        l1 = 0x7fffffffffffffffL;
        l2 = l1;
        if(mConfigurationLocked.isInMemoryDb()) goto _L2; else goto _L1
_L1:
        if(l < 0L) goto _L4; else goto _L3
_L3:
        l2 = l;
_L2:
        mConfigurationLocked.idleConnectionTimeoutMs = l2;
        return;
_L4:
        l2 = l1;
        if(DEBUG_CLOSE_IDLE_CONNECTIONS)
            l2 = SQLiteGlobal.getIdleConnectionTimeout();
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void beginTransaction(SQLiteTransactionListener sqlitetransactionlistener, boolean flag)
    {
        acquireReference();
        SQLiteSession sqlitesession = getThreadSession();
        byte byte0;
        if(flag)
            byte0 = 2;
        else
            byte0 = 1;
        sqlitesession.beginTransaction(byte0, sqlitetransactionlistener, getThreadDefaultConnectionFlags(false), null);
        releaseReference();
        return;
        sqlitetransactionlistener;
        releaseReference();
        throw sqlitetransactionlistener;
    }

    private void collectDbStats(ArrayList arraylist)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mConnectionPoolLocked != null)
            mConnectionPoolLocked.collectDbStats(arraylist);
        obj;
        JVM INSTR monitorexit ;
        return;
        arraylist;
        throw arraylist;
    }

    public static SQLiteDatabase create(CursorFactory cursorfactory)
    {
        return openDatabase(":memory:", cursorfactory, 0x10000000);
    }

    public static SQLiteDatabase createInMemory(OpenParams openparams)
    {
        return openDatabase(":memory:", openparams.toBuilder().addOpenFlags(0x10000000).build());
    }

    public static boolean deleteDatabase(File file)
    {
        if(file == null)
            throw new IllegalArgumentException("file must not be null");
        boolean flag = file.delete() | (new File((new StringBuilder()).append(file.getPath()).append("-journal").toString())).delete() | (new File((new StringBuilder()).append(file.getPath()).append("-shm").toString())).delete() | (new File((new StringBuilder()).append(file.getPath()).append("-wal").toString())).delete();
        File file1 = file.getParentFile();
        boolean flag1 = flag;
        if(file1 != null)
        {
            file = file1.listFiles(new FileFilter((new StringBuilder()).append(file.getName()).append("-mj").toString()) {

                public boolean accept(File file2)
                {
                    return file2.getName().startsWith(prefix);
                }

                final String val$prefix;

            
            {
                prefix = s;
                super();
            }
            }
);
            flag1 = flag;
            if(file != null)
            {
                int i = 0;
                int j = file.length;
                do
                {
                    flag1 = flag;
                    if(i >= j)
                        break;
                    flag |= file[i].delete();
                    i++;
                } while(true);
            }
        }
        return flag1;
    }

    private void dispose(boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCloseGuardLocked == null)
            break MISSING_BLOCK_LABEL_32;
        if(!flag)
            break MISSING_BLOCK_LABEL_25;
        mCloseGuardLocked.warnIfOpen();
        mCloseGuardLocked.close();
        SQLiteConnectionPool sqliteconnectionpool;
        sqliteconnectionpool = mConnectionPoolLocked;
        mConnectionPoolLocked = null;
        obj;
        JVM INSTR monitorexit ;
        if(flag)
            break MISSING_BLOCK_LABEL_72;
        obj = sActiveDatabases;
        obj;
        JVM INSTR monitorenter ;
        sActiveDatabases.remove(this);
        obj;
        JVM INSTR monitorexit ;
        if(sqliteconnectionpool != null)
            sqliteconnectionpool.close();
        return;
        Exception exception;
        exception;
        throw exception;
        exception;
        throw exception;
    }

    private void dump(Printer printer, boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mConnectionPoolLocked != null)
        {
            printer.println("");
            mConnectionPoolLocked.dump(printer, flag);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        printer;
        throw printer;
    }

    static void dumpAll(Printer printer, boolean flag)
    {
        for(Iterator iterator = getActiveDatabases().iterator(); iterator.hasNext(); ((SQLiteDatabase)iterator.next()).dump(printer, flag));
    }

    private int executeSql(String s, Object aobj[])
        throws SQLException
    {
        acquireReference();
        if(DatabaseUtils.getSqlStatementType(s) != 3)
            break MISSING_BLOCK_LABEL_55;
        int i = 0;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mHasAttachedDbsLocked)
            break MISSING_BLOCK_LABEL_44;
        mHasAttachedDbsLocked = true;
        i = 1;
        mConnectionPoolLocked.disableIdleConnectionHandler();
        obj;
        JVM INSTR monitorexit ;
        if(!i)
            break MISSING_BLOCK_LABEL_55;
        disableWriteAheadLogging();
        obj = JVM INSTR new #354 <Class SQLiteStatement>;
        ((SQLiteStatement) (obj)).SQLiteStatement(this, s, aobj);
        i = ((SQLiteStatement) (obj)).executeUpdateDelete();
        ((SQLiteStatement) (obj)).close();
        releaseReference();
        return i;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
        s;
        releaseReference();
        throw s;
        s;
        ((SQLiteStatement) (obj)).close();
        throw s;
    }

    public static String findEditTable(String s)
    {
        if(!TextUtils.isEmpty(s))
        {
            int i = s.indexOf(' ');
            int j = s.indexOf(',');
            if(i > 0 && (i < j || j < 0))
                return s.substring(0, i);
            if(j > 0 && (j < i || i < 0))
                return s.substring(0, j);
            else
                return s;
        } else
        {
            throw new IllegalStateException("Invalid tables");
        }
    }

    private static ArrayList getActiveDatabases()
    {
        ArrayList arraylist = new ArrayList();
        WeakHashMap weakhashmap = sActiveDatabases;
        weakhashmap;
        JVM INSTR monitorenter ;
        arraylist.addAll(sActiveDatabases.keySet());
        weakhashmap;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    static ArrayList getDbStats()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = getActiveDatabases().iterator(); iterator.hasNext(); ((SQLiteDatabase)iterator.next()).collectDbStats(arraylist));
        return arraylist;
    }

    private static boolean isMainThread()
    {
        boolean flag = false;
        Looper looper = Looper.myLooper();
        boolean flag1 = flag;
        if(looper != null)
        {
            flag1 = flag;
            if(looper == Looper.getMainLooper())
                flag1 = true;
        }
        return flag1;
    }

    private boolean isReadOnlyLocked()
    {
        boolean flag = true;
        if((mConfigurationLocked.openFlags & 1) != 1)
            flag = false;
        return flag;
    }

    private void open()
    {
        openInner();
_L1:
        return;
        SQLiteDatabaseCorruptException sqlitedatabasecorruptexception;
        sqlitedatabasecorruptexception;
        try
        {
            onCorruption();
            openInner();
        }
        catch(SQLiteException sqliteexception)
        {
            Log.e("SQLiteDatabase", (new StringBuilder()).append("Failed to open database '").append(getLabel()).append("'.").toString(), sqliteexception);
            close();
            throw sqliteexception;
        }
          goto _L1
    }

    public static SQLiteDatabase openDatabase(File file, OpenParams openparams)
    {
        return openDatabase(file.getPath(), openparams);
    }

    public static SQLiteDatabase openDatabase(String s, CursorFactory cursorfactory, int i)
    {
        return openDatabase(s, cursorfactory, i, null);
    }

    public static SQLiteDatabase openDatabase(String s, CursorFactory cursorfactory, int i, DatabaseErrorHandler databaseerrorhandler)
    {
        s = new SQLiteDatabase(s, i, cursorfactory, databaseerrorhandler, -1, -1, -1L);
        s.open();
        return s;
    }

    private static SQLiteDatabase openDatabase(String s, OpenParams openparams)
    {
        boolean flag;
        if(openparams != null)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "OpenParams cannot be null");
        s = new SQLiteDatabase(s, OpenParams._2D_get5(openparams), OpenParams._2D_get0(openparams), OpenParams._2D_get1(openparams), OpenParams._2D_get4(openparams), OpenParams._2D_get3(openparams), OpenParams._2D_get2(openparams));
        s.open();
        return s;
    }

    private void openInner()
    {
        synchronized(mLock)
        {
            if(!_2D_assertionsDisabled && mConnectionPoolLocked != null)
            {
                AssertionError assertionerror = JVM INSTR new #479 <Class AssertionError>;
                assertionerror.AssertionError();
                throw assertionerror;
            }
            break MISSING_BLOCK_LABEL_35;
        }
        mConnectionPoolLocked = SQLiteConnectionPool.open(mConfigurationLocked);
        mCloseGuardLocked.open("close");
        obj;
        JVM INSTR monitorexit ;
        WeakHashMap weakhashmap = sActiveDatabases;
        weakhashmap;
        JVM INSTR monitorenter ;
        sActiveDatabases.put(this, null);
        weakhashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception1;
        exception1;
        throw exception1;
    }

    public static SQLiteDatabase openOrCreateDatabase(File file, CursorFactory cursorfactory)
    {
        return openOrCreateDatabase(file.getPath(), cursorfactory);
    }

    public static SQLiteDatabase openOrCreateDatabase(String s, CursorFactory cursorfactory)
    {
        return openDatabase(s, cursorfactory, 0x10000000, null);
    }

    public static SQLiteDatabase openOrCreateDatabase(String s, CursorFactory cursorfactory, DatabaseErrorHandler databaseerrorhandler)
    {
        return openDatabase(s, cursorfactory, 0x10000000, databaseerrorhandler);
    }

    public static int releaseMemory()
    {
        return SQLiteGlobal.releaseMemory();
    }

    private void throwIfNotOpenLocked()
    {
        if(mConnectionPoolLocked == null)
            throw new IllegalStateException((new StringBuilder()).append("The database '").append(mConfigurationLocked.label).append("' is not open.").toString());
        else
            return;
    }

    private boolean yieldIfContendedHelper(boolean flag, long l)
    {
        acquireReference();
        flag = getThreadSession().yieldTransaction(l, flag, null);
        releaseReference();
        return flag;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    SQLiteSession _2D_android_database_sqlite_SQLiteDatabase_2D_mthref_2D_0()
    {
        return createSession();
    }

    public void addCustomFunction(String s, int i, CustomFunction customfunction)
    {
        SQLiteCustomFunction sqlitecustomfunction = new SQLiteCustomFunction(s, i, customfunction);
        s = ((String) (mLock));
        s;
        JVM INSTR monitorenter ;
        throwIfNotOpenLocked();
        mConfigurationLocked.customFunctions.add(sqlitecustomfunction);
        mConnectionPoolLocked.reconfigure(mConfigurationLocked);
        s;
        JVM INSTR monitorexit ;
        return;
        customfunction;
        mConfigurationLocked.customFunctions.remove(sqlitecustomfunction);
        throw customfunction;
        customfunction;
        s;
        JVM INSTR monitorexit ;
        throw customfunction;
    }

    public void beginTransaction()
    {
        beginTransaction(null, true);
    }

    public void beginTransactionNonExclusive()
    {
        beginTransaction(null, false);
    }

    public void beginTransactionWithListener(SQLiteTransactionListener sqlitetransactionlistener)
    {
        beginTransaction(sqlitetransactionlistener, true);
    }

    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sqlitetransactionlistener)
    {
        beginTransaction(sqlitetransactionlistener, false);
    }

    public SQLiteStatement compileStatement(String s)
        throws SQLException
    {
        acquireReference();
        s = new SQLiteStatement(this, s, null);
        releaseReference();
        return s;
        s;
        releaseReference();
        throw s;
    }

    SQLiteSession createSession()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        SQLiteConnectionPool sqliteconnectionpool;
        throwIfNotOpenLocked();
        sqliteconnectionpool = mConnectionPoolLocked;
        obj;
        JVM INSTR monitorexit ;
        return new SQLiteSession(sqliteconnectionpool);
        Exception exception;
        exception;
        throw exception;
    }

    public int delete(String s, String s1, String as[])
    {
        acquireReference();
        SQLiteStatement sqlitestatement;
        StringBuilder stringbuilder;
        sqlitestatement = JVM INSTR new #354 <Class SQLiteStatement>;
        stringbuilder = JVM INSTR new #258 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder = stringbuilder.append("DELETE FROM ").append(s);
        if(TextUtils.isEmpty(s1))
            break MISSING_BLOCK_LABEL_98;
        s = JVM INSTR new #258 <Class StringBuilder>;
        s.StringBuilder();
        s = s.append(" WHERE ").append(s1).toString();
_L1:
        sqlitestatement.SQLiteStatement(this, stringbuilder.append(s).toString(), as);
        int i = sqlitestatement.executeUpdateDelete();
        sqlitestatement.close();
        releaseReference();
        return i;
        s = "";
          goto _L1
        s;
        sqlitestatement.close();
        throw s;
        s;
        releaseReference();
        throw s;
    }

    public void disableWriteAheadLogging()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        throwIfNotOpenLocked();
        i = mConfigurationLocked.openFlags;
        if((i & 0x20000000) != 0)
            break MISSING_BLOCK_LABEL_29;
        obj;
        JVM INSTR monitorexit ;
        return;
        SQLiteDatabaseConfiguration sqlitedatabaseconfiguration = mConfigurationLocked;
        sqlitedatabaseconfiguration.openFlags = sqlitedatabaseconfiguration.openFlags & 0xdfffffff;
        mConnectionPoolLocked.reconfigure(mConfigurationLocked);
        obj;
        JVM INSTR monitorexit ;
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        SQLiteDatabaseConfiguration sqlitedatabaseconfiguration1 = mConfigurationLocked;
        sqlitedatabaseconfiguration1.openFlags = sqlitedatabaseconfiguration1.openFlags | 0x20000000;
        throw runtimeexception;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean enableWriteAheadLogging()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        throwIfNotOpenLocked();
        i = mConfigurationLocked.openFlags;
        if((i & 0x20000000) == 0)
            break MISSING_BLOCK_LABEL_30;
        obj;
        JVM INSTR monitorexit ;
        return true;
        boolean flag = isReadOnlyLocked();
        if(!flag)
            break MISSING_BLOCK_LABEL_43;
        obj;
        JVM INSTR monitorexit ;
        return false;
        if(!mConfigurationLocked.isInMemoryDb())
            break MISSING_BLOCK_LABEL_66;
        Log.i("SQLiteDatabase", "can't enable WAL for memory databases.");
        obj;
        JVM INSTR monitorexit ;
        return false;
        if(!mHasAttachedDbsLocked)
            break MISSING_BLOCK_LABEL_129;
        if(Log.isLoggable("SQLiteDatabase", 3))
        {
            StringBuilder stringbuilder = JVM INSTR new #258 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("SQLiteDatabase", stringbuilder.append("this database: ").append(mConfigurationLocked.label).append(" has attached databases. can't  enable WAL.").toString());
        }
        obj;
        JVM INSTR monitorexit ;
        return false;
        SQLiteDatabaseConfiguration sqlitedatabaseconfiguration = mConfigurationLocked;
        sqlitedatabaseconfiguration.openFlags = sqlitedatabaseconfiguration.openFlags | 0x20000000;
        mConnectionPoolLocked.reconfigure(mConfigurationLocked);
        obj;
        JVM INSTR monitorexit ;
        return true;
        RuntimeException runtimeexception;
        runtimeexception;
        SQLiteDatabaseConfiguration sqlitedatabaseconfiguration1 = mConfigurationLocked;
        sqlitedatabaseconfiguration1.openFlags = sqlitedatabaseconfiguration1.openFlags & 0xdfffffff;
        throw runtimeexception;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void endTransaction()
    {
        acquireReference();
        getThreadSession().endTransaction(null);
        releaseReference();
        return;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public void execSQL(String s)
        throws SQLException
    {
        executeSql(s, null);
    }

    public void execSQL(String s, Object aobj[])
        throws SQLException
    {
        if(aobj == null)
        {
            throw new IllegalArgumentException("Empty bindArgs");
        } else
        {
            executeSql(s, aobj);
            return;
        }
    }

    protected void finalize()
        throws Throwable
    {
        dispose(true);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public List getAttachedDbs()
    {
        ArrayList arraylist = new ArrayList();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = mConnectionPoolLocked;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_28;
        obj;
        JVM INSTR monitorexit ;
        return null;
        if(mHasAttachedDbsLocked)
            break MISSING_BLOCK_LABEL_63;
        obj1 = JVM INSTR new #599 <Class Pair>;
        ((Pair) (obj1)).Pair("main", mConfigurationLocked.path);
        arraylist.add(obj1);
        obj;
        JVM INSTR monitorexit ;
        return arraylist;
        acquireReference();
        obj;
        JVM INSTR monitorexit ;
        obj = null;
        obj1 = rawQuery("pragma database_list;", null);
_L2:
        obj = obj1;
        if(!((Cursor) (obj1)).moveToNext())
            break; /* Loop/switch isn't completed */
        obj = obj1;
        Pair pair = JVM INSTR new #599 <Class Pair>;
        obj = obj1;
        pair.Pair(((Cursor) (obj1)).getString(1), ((Cursor) (obj1)).getString(2));
        obj = obj1;
        arraylist.add(pair);
        if(true) goto _L2; else goto _L1
        obj1;
        if(obj == null)
            break MISSING_BLOCK_LABEL_142;
        ((Cursor) (obj)).close();
        throw obj1;
        obj;
        releaseReference();
        throw obj;
        obj1;
        throw obj1;
_L1:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_166;
        ((Cursor) (obj1)).close();
        releaseReference();
        return arraylist;
    }

    String getLabel()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        String s = mConfigurationLocked.label;
        obj;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public long getMaximumSize()
    {
        long l = DatabaseUtils.longForQuery(this, "PRAGMA max_page_count;", null);
        return getPageSize() * l;
    }

    public long getPageSize()
    {
        return DatabaseUtils.longForQuery(this, "PRAGMA page_size;", null);
    }

    public final String getPath()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        String s = mConfigurationLocked.path;
        obj;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public Map getSyncedTables()
    {
        return new HashMap(0);
    }

    int getThreadDefaultConnectionFlags(boolean flag)
    {
        int i;
        int j;
        if(flag)
            i = 1;
        else
            i = 2;
        j = i;
        if(isMainThread())
            j = i | 4;
        return j;
    }

    SQLiteSession getThreadSession()
    {
        return (SQLiteSession)mThreadSession.get();
    }

    public int getVersion()
    {
        return Long.valueOf(DatabaseUtils.longForQuery(this, "PRAGMA user_version;", null)).intValue();
    }

    public boolean inTransaction()
    {
        acquireReference();
        boolean flag = getThreadSession().hasTransaction();
        releaseReference();
        return flag;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public long insert(String s, String s1, ContentValues contentvalues)
    {
        long l;
        try
        {
            l = insertWithOnConflict(s, s1, contentvalues, 0);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("SQLiteDatabase", (new StringBuilder()).append("Error inserting ").append(contentvalues).toString(), s);
            return -1L;
        }
        return l;
    }

    public long insertOrThrow(String s, String s1, ContentValues contentvalues)
        throws SQLException
    {
        return insertWithOnConflict(s, s1, contentvalues, 0);
    }

    public long insertWithOnConflict(String s, String s1, ContentValues contentvalues, int i)
    {
        acquireReference();
        StringBuilder stringbuilder;
        stringbuilder = JVM INSTR new #258 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder.append("INSERT");
        stringbuilder.append(CONFLICT_VALUES[i]);
        stringbuilder.append(" INTO ");
        stringbuilder.append(s);
        stringbuilder.append('(');
        s = null;
        if(contentvalues == null) goto _L2; else goto _L1
_L1:
        if(!(contentvalues.isEmpty() ^ true)) goto _L2; else goto _L3
_L3:
        i = contentvalues.size();
_L8:
        if(i <= 0) goto _L5; else goto _L4
_L4:
        Iterator iterator;
        s1 = ((String) (new Object[i]));
        iterator = contentvalues.keySet().iterator();
        int j = 0;
_L7:
        String s2;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        s2 = (String)iterator.next();
        if(j > 0)
            s = ",";
        else
            s = "";
        stringbuilder.append(s);
        stringbuilder.append(s2);
        s1[j] = contentvalues.get(s2);
        j++;
        if(true) goto _L7; else goto _L6
_L2:
        i = 0;
          goto _L8
_L6:
        stringbuilder.append(')');
        stringbuilder.append(" VALUES (");
        j = 0;
_L10:
        s = s1;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        if(j > 0)
            s = ",?";
        else
            s = "?";
        stringbuilder.append(s);
        j++;
        if(true) goto _L10; else goto _L9
_L5:
        stringbuilder.append(s1).append(") VALUES (NULL");
_L9:
        stringbuilder.append(')');
        s1 = JVM INSTR new #354 <Class SQLiteStatement>;
        s1.SQLiteStatement(this, stringbuilder.toString(), s);
        long l = s1.executeInsert();
        s1.close();
        releaseReference();
        return l;
        s;
        s1.close();
        throw s;
        s;
        releaseReference();
        throw s;
    }

    public boolean isDatabaseIntegrityOk()
    {
        acquireReference();
        Object obj = getAttachedDbs();
        Object obj1;
        obj1 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_94;
        try
        {
            obj1 = JVM INSTR new #380 <Class IllegalStateException>;
            obj = JVM INSTR new #258 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((IllegalStateException) (obj1)).IllegalStateException(((StringBuilder) (obj)).append("databaselist for: ").append(getPath()).append(" couldn't ").append("be retrieved. probably because the database is closed").toString());
            throw obj1;
        }
        catch(SQLiteException sqliteexception) { }
        obj1 = JVM INSTR new #385 <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList();
        sqliteexception = JVM INSTR new #599 <Class Pair>;
        sqliteexception.Pair("main", getPath());
        ((List) (obj1)).add(sqliteexception);
        int i = 0;
_L2:
        Pair pair;
        if(i >= ((List) (obj1)).size())
            break; /* Loop/switch isn't completed */
        pair = (Pair)((List) (obj1)).get(i);
        SQLiteStatement sqlitestatement;
        sqlitestatement = null;
        sqliteexception = sqlitestatement;
        Object obj2 = JVM INSTR new #258 <Class StringBuilder>;
        sqliteexception = sqlitestatement;
        ((StringBuilder) (obj2)).StringBuilder();
        sqliteexception = sqlitestatement;
        sqlitestatement = compileStatement(((StringBuilder) (obj2)).append("PRAGMA ").append((String)pair.first).append(".integrity_check(1);").toString());
        sqliteexception = sqlitestatement;
        obj2 = sqlitestatement.simpleQueryForString();
        sqliteexception = sqlitestatement;
        if(((String) (obj2)).equalsIgnoreCase("ok"))
            break MISSING_BLOCK_LABEL_269;
        sqliteexception = sqlitestatement;
        obj1 = JVM INSTR new #258 <Class StringBuilder>;
        sqliteexception = sqlitestatement;
        ((StringBuilder) (obj1)).StringBuilder();
        sqliteexception = sqlitestatement;
        Log.e("SQLiteDatabase", ((StringBuilder) (obj1)).append("PRAGMA integrity_check on ").append((String)pair.second).append(" returned: ").append(((String) (obj2))).toString());
        if(sqlitestatement == null)
            break MISSING_BLOCK_LABEL_263;
        sqlitestatement.close();
        releaseReference();
        return false;
        if(sqlitestatement == null)
            break MISSING_BLOCK_LABEL_279;
        sqlitestatement.close();
        i++;
        if(true) goto _L2; else goto _L1
        Exception exception1;
        exception1;
        if(sqliteexception == null)
            break MISSING_BLOCK_LABEL_294;
        sqliteexception.close();
        throw exception1;
        Exception exception;
        exception;
_L4:
        releaseReference();
        throw exception;
_L1:
        releaseReference();
        return true;
        exception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean isDbLockedByCurrentThread()
    {
        acquireReference();
        boolean flag = getThreadSession().hasConnection();
        releaseReference();
        return flag;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public boolean isDbLockedByOtherThreads()
    {
        return false;
    }

    public boolean isInMemoryDatabase()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mConfigurationLocked.isInMemoryDb();
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isOpen()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        SQLiteConnectionPool sqliteconnectionpool = mConnectionPoolLocked;
        boolean flag;
        if(sqliteconnectionpool != null)
            flag = true;
        else
            flag = false;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isReadOnly()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = isReadOnlyLocked();
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isWriteAheadLoggingEnabled()
    {
        boolean flag = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        throwIfNotOpenLocked();
        i = mConfigurationLocked.openFlags;
        if((i & 0x20000000) != 0)
            flag = true;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void markTableSyncable(String s, String s1)
    {
    }

    public void markTableSyncable(String s, String s1, String s2)
    {
    }

    public boolean needUpgrade(int i)
    {
        boolean flag;
        if(i > getVersion())
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void onAllReferencesReleased()
    {
        dispose(false);
    }

    void onCorruption()
    {
        EventLog.writeEvent(0x124fc, getLabel());
        mErrorHandler.onCorruption(this);
    }

    public Cursor query(String s, String as[], String s1, String as1[], String s2, String s3, String s4)
    {
        return query(false, s, as, s1, as1, s2, s3, s4, null);
    }

    public Cursor query(String s, String as[], String s1, String as1[], String s2, String s3, String s4, 
            String s5)
    {
        return query(false, s, as, s1, as1, s2, s3, s4, s5);
    }

    public Cursor query(boolean flag, String s, String as[], String s1, String as1[], String s2, String s3, 
            String s4, String s5)
    {
        return queryWithFactory(null, flag, s, as, s1, as1, s2, s3, s4, s5, null);
    }

    public Cursor query(boolean flag, String s, String as[], String s1, String as1[], String s2, String s3, 
            String s4, String s5, CancellationSignal cancellationsignal)
    {
        return queryWithFactory(null, flag, s, as, s1, as1, s2, s3, s4, s5, cancellationsignal);
    }

    public Cursor queryWithFactory(CursorFactory cursorfactory, boolean flag, String s, String as[], String s1, String as1[], String s2, 
            String s3, String s4, String s5)
    {
        return queryWithFactory(cursorfactory, flag, s, as, s1, as1, s2, s3, s4, s5, null);
    }

    public Cursor queryWithFactory(CursorFactory cursorfactory, boolean flag, String s, String as[], String s1, String as1[], String s2, 
            String s3, String s4, String s5, CancellationSignal cancellationsignal)
    {
        acquireReference();
        cursorfactory = rawQueryWithFactory(cursorfactory, SQLiteQueryBuilder.buildQueryString(flag, s, as, s1, s2, s3, s4, s5), as1, findEditTable(s), cancellationsignal);
        releaseReference();
        return cursorfactory;
        cursorfactory;
        releaseReference();
        throw cursorfactory;
    }

    public Cursor rawQuery(String s, String as[])
    {
        return rawQueryWithFactory(null, s, as, null, null);
    }

    public Cursor rawQuery(String s, String as[], CancellationSignal cancellationsignal)
    {
        return rawQueryWithFactory(null, s, as, null, cancellationsignal);
    }

    public Cursor rawQueryWithFactory(CursorFactory cursorfactory, String s, String as[], String s1)
    {
        return rawQueryWithFactory(cursorfactory, s, as, s1, null);
    }

    public Cursor rawQueryWithFactory(CursorFactory cursorfactory, String s, String as[], String s1, CancellationSignal cancellationsignal)
    {
        acquireReference();
        SQLiteDirectCursorDriver sqlitedirectcursordriver;
        sqlitedirectcursordriver = JVM INSTR new #810 <Class SQLiteDirectCursorDriver>;
        sqlitedirectcursordriver.SQLiteDirectCursorDriver(this, s, s1, cancellationsignal);
        if(cursorfactory == null)
            break MISSING_BLOCK_LABEL_40;
_L1:
        cursorfactory = sqlitedirectcursordriver.query(cursorfactory, as);
        releaseReference();
        return cursorfactory;
        cursorfactory = mCursorFactory;
          goto _L1
        cursorfactory;
        releaseReference();
        throw cursorfactory;
    }

    public void reopenReadWrite()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        throwIfNotOpenLocked();
        flag = isReadOnlyLocked();
        if(flag)
            break MISSING_BLOCK_LABEL_23;
        obj;
        JVM INSTR monitorexit ;
        return;
        int i;
        i = mConfigurationLocked.openFlags;
        mConfigurationLocked.openFlags = mConfigurationLocked.openFlags & -2 | 0;
        mConnectionPoolLocked.reconfigure(mConfigurationLocked);
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        mConfigurationLocked.openFlags = i;
        throw obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public long replace(String s, String s1, ContentValues contentvalues)
    {
        long l;
        try
        {
            l = insertWithOnConflict(s, s1, contentvalues, 5);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("SQLiteDatabase", (new StringBuilder()).append("Error inserting ").append(contentvalues).toString(), s);
            return -1L;
        }
        return l;
    }

    public long replaceOrThrow(String s, String s1, ContentValues contentvalues)
        throws SQLException
    {
        return insertWithOnConflict(s, s1, contentvalues, 5);
    }

    public void setForeignKeyConstraintsEnabled(boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag1;
        throwIfNotOpenLocked();
        flag1 = mConfigurationLocked.foreignKeyConstraintsEnabled;
        if(flag1 != flag)
            break MISSING_BLOCK_LABEL_27;
        obj;
        JVM INSTR monitorexit ;
        return;
        mConfigurationLocked.foreignKeyConstraintsEnabled = flag;
        mConnectionPoolLocked.reconfigure(mConfigurationLocked);
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        mConfigurationLocked.foreignKeyConstraintsEnabled = flag ^ true;
        throw obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public void setLocale(Locale locale)
    {
        if(locale == null)
            throw new IllegalArgumentException("locale must not be null.");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Locale locale1;
        throwIfNotOpenLocked();
        locale1 = mConfigurationLocked.locale;
        mConfigurationLocked.locale = locale;
        mConnectionPoolLocked.reconfigure(mConfigurationLocked);
        obj;
        JVM INSTR monitorexit ;
        return;
        locale;
        mConfigurationLocked.locale = locale1;
        throw locale;
        locale;
        obj;
        JVM INSTR monitorexit ;
        throw locale;
    }

    public void setLockingEnabled(boolean flag)
    {
    }

    public void setMaxSqlCacheSize(int i)
    {
        if(i > 100 || i < 0)
            throw new IllegalStateException("expected value between 0 and 100");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int j;
        throwIfNotOpenLocked();
        j = mConfigurationLocked.maxSqlCacheSize;
        mConfigurationLocked.maxSqlCacheSize = i;
        mConnectionPoolLocked.reconfigure(mConfigurationLocked);
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        mConfigurationLocked.maxSqlCacheSize = j;
        throw obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public long setMaximumSize(long l)
    {
        long l1 = getPageSize();
        long l2 = l / l1;
        long l3 = l2;
        if(l % l1 != 0L)
            l3 = l2 + 1L;
        return DatabaseUtils.longForQuery(this, (new StringBuilder()).append("PRAGMA max_page_count = ").append(l3).toString(), null) * l1;
    }

    public void setPageSize(long l)
    {
        execSQL((new StringBuilder()).append("PRAGMA page_size = ").append(l).toString());
    }

    public void setTransactionSuccessful()
    {
        acquireReference();
        getThreadSession().setTransactionSuccessful();
        releaseReference();
        return;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public void setVersion(int i)
    {
        execSQL((new StringBuilder()).append("PRAGMA user_version = ").append(i).toString());
    }

    public String toString()
    {
        return (new StringBuilder()).append("SQLiteDatabase: ").append(getPath()).toString();
    }

    public int update(String s, ContentValues contentvalues, String s1, String as[])
    {
        return updateWithOnConflict(s, contentvalues, s1, as, 0);
    }

    public int updateWithOnConflict(String s, ContentValues contentvalues, String s1, String as[], int i)
    {
        if(contentvalues == null || contentvalues.isEmpty())
            throw new IllegalArgumentException("Empty values");
        acquireReference();
        StringBuilder stringbuilder;
        stringbuilder = JVM INSTR new #258 <Class StringBuilder>;
        stringbuilder.StringBuilder(120);
        stringbuilder.append("UPDATE ");
        stringbuilder.append(CONFLICT_VALUES[i]);
        stringbuilder.append(s);
        stringbuilder.append(" SET ");
        i = contentvalues.size();
        if(as != null) goto _L2; else goto _L1
_L1:
        int j = i;
_L6:
        Object aobj[];
        Iterator iterator;
        aobj = new Object[j];
        iterator = contentvalues.keySet().iterator();
        int k = 0;
_L4:
        String s2;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        s2 = (String)iterator.next();
        if(k > 0)
            s = ",";
        else
            s = "";
        stringbuilder.append(s);
        stringbuilder.append(s2);
        aobj[k] = contentvalues.get(s2);
        stringbuilder.append("=?");
        k++;
        if(true) goto _L4; else goto _L3
_L3:
        break; /* Loop/switch isn't completed */
_L2:
        j = i + as.length;
        if(true) goto _L6; else goto _L5
_L5:
        if(as != null)
        {
            for(int l = i; l < j; l++)
                aobj[l] = as[l - i];

        }
        if(!TextUtils.isEmpty(s1))
        {
            stringbuilder.append(" WHERE ");
            stringbuilder.append(s1);
        }
        s = JVM INSTR new #354 <Class SQLiteStatement>;
        s.SQLiteStatement(this, stringbuilder.toString(), aobj);
        i = s.executeUpdateDelete();
        s.close();
        releaseReference();
        return i;
        contentvalues;
        s.close();
        throw contentvalues;
        s;
        releaseReference();
        throw s;
    }

    public void validateSql(String s, CancellationSignal cancellationsignal)
    {
        getThreadSession().prepare(s, getThreadDefaultConnectionFlags(true), cancellationsignal, null);
    }

    public boolean yieldIfContended()
    {
        return yieldIfContendedHelper(false, -1L);
    }

    public boolean yieldIfContendedSafely()
    {
        return yieldIfContendedHelper(true, -1L);
    }

    public boolean yieldIfContendedSafely(long l)
    {
        return yieldIfContendedHelper(true, l);
    }

    static final boolean _2D_assertionsDisabled = android/database/sqlite/SQLiteDatabase.desiredAssertionStatus() ^ true;
    public static final int CONFLICT_ABORT = 2;
    public static final int CONFLICT_FAIL = 3;
    public static final int CONFLICT_IGNORE = 4;
    public static final int CONFLICT_NONE = 0;
    public static final int CONFLICT_REPLACE = 5;
    public static final int CONFLICT_ROLLBACK = 1;
    private static final String CONFLICT_VALUES[] = {
        "", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "
    };
    public static final int CREATE_IF_NECESSARY = 0x10000000;
    private static final boolean DEBUG_CLOSE_IDLE_CONNECTIONS = SystemProperties.getBoolean("persist.debug.sqlite.close_idle_connections", false);
    public static final int ENABLE_WRITE_AHEAD_LOGGING = 0x20000000;
    private static final int EVENT_DB_CORRUPT = 0x124fc;
    public static final int MAX_SQL_CACHE_SIZE = 100;
    public static final int NO_LOCALIZED_COLLATORS = 16;
    public static final int OPEN_READONLY = 1;
    public static final int OPEN_READWRITE = 0;
    private static final int OPEN_READ_MASK = 1;
    public static final int SQLITE_MAX_LIKE_PATTERN_LENGTH = 50000;
    private static final String TAG = "SQLiteDatabase";
    private static WeakHashMap sActiveDatabases = new WeakHashMap();
    private final CloseGuard mCloseGuardLocked;
    private final SQLiteDatabaseConfiguration mConfigurationLocked;
    private SQLiteConnectionPool mConnectionPoolLocked;
    private final CursorFactory mCursorFactory;
    private final DatabaseErrorHandler mErrorHandler;
    private boolean mHasAttachedDbsLocked;
    private final Object mLock;
    private final ThreadLocal mThreadSession;

}
