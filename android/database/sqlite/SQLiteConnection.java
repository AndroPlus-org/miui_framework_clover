// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.os.*;
import android.util.*;
import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package android.database.sqlite:
//            SQLiteDatabaseConfiguration, SQLiteBindOrColumnIndexOutOfRangeException, SQLiteException, SQLiteDebug, 
//            SQLiteCustomFunction, SQLiteGlobal, SQLiteDatabaseLockedException, SQLiteConnectionPool, 
//            SQLiteStatementInfo

public final class SQLiteConnection
    implements android.os.CancellationSignal.OnCancelListener
{
    private static final class Operation
    {

        static String _2D_wrap0(Operation operation)
        {
            return operation.getFormattedStartTime();
        }

        static String _2D_wrap1(Operation operation)
        {
            return operation.getTraceMethodName();
        }

        private String getFormattedStartTime()
        {
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(mStartWallTime));
        }

        private String getStatus()
        {
            if(!mFinished)
                return "running";
            String s;
            if(mException != null)
                s = "failed";
            else
                s = "succeeded";
            return s;
        }

        private String getTraceMethodName()
        {
            String s = (new StringBuilder()).append(mKind).append(" ").append(mSql).toString();
            if(s.length() > 256)
                return s.substring(0, 256);
            else
                return s;
        }

        public void describe(StringBuilder stringbuilder, boolean flag)
        {
            stringbuilder.append(mKind);
            if(mFinished)
                stringbuilder.append(" took ").append(mEndTime - mStartTime).append("ms");
            else
                stringbuilder.append(" started ").append(System.currentTimeMillis() - mStartWallTime).append("ms ago");
            stringbuilder.append(" - ").append(getStatus());
            if(mSql != null)
                stringbuilder.append(", sql=\"").append(SQLiteConnection._2D_wrap0(mSql)).append("\"");
            if(flag && mBindArgs != null && mBindArgs.size() != 0)
            {
                stringbuilder.append(", bindArgs=[");
                int i = mBindArgs.size();
                int j = 0;
                while(j < i) 
                {
                    Object obj = mBindArgs.get(j);
                    if(j != 0)
                        stringbuilder.append(", ");
                    if(obj == null)
                        stringbuilder.append("null");
                    else
                    if(obj instanceof byte[])
                        stringbuilder.append("<byte[]>");
                    else
                    if(obj instanceof String)
                        stringbuilder.append("\"").append((String)obj).append("\"");
                    else
                        stringbuilder.append(obj);
                    j++;
                }
                stringbuilder.append("]");
            }
            if(mException != null)
                stringbuilder.append(", exception=\"").append(mException.getMessage()).append("\"");
        }

        private static final int MAX_TRACE_METHOD_NAME_LEN = 256;
        public ArrayList mBindArgs;
        public int mCookie;
        public long mEndTime;
        public Exception mException;
        public boolean mFinished;
        public String mKind;
        public String mSql;
        public long mStartTime;
        public long mStartWallTime;

        private Operation()
        {
        }

        Operation(Operation operation)
        {
            this();
        }
    }

    private static final class OperationLog
    {

        private boolean endOperationDeferLogLocked(int i)
        {
            boolean flag = false;
            Operation operation = getOperationLocked(i);
            if(operation != null)
            {
                if(Trace.isTagEnabled(0x100000L))
                    Trace.asyncTraceEnd(0x100000L, Operation._2D_wrap1(operation), operation.mCookie);
                operation.mEndTime = SystemClock.uptimeMillis();
                operation.mFinished = true;
                if(SQLiteDebug.DEBUG_LOG_SLOW_QUERIES)
                    flag = SQLiteDebug.shouldLogSlowQuery(operation.mEndTime - operation.mStartTime);
                return flag;
            } else
            {
                return false;
            }
        }

        private Operation getOperationLocked(int i)
        {
            Operation operation = mOperations[i & 0xff];
            if(operation.mCookie != i)
                operation = null;
            return operation;
        }

        private void logOperationLocked(int i, String s)
        {
            Operation operation = getOperationLocked(i);
            StringBuilder stringbuilder = new StringBuilder();
            operation.describe(stringbuilder, false);
            if(s != null)
                stringbuilder.append(", ").append(s);
            Log.d("SQLiteConnection", stringbuilder.toString());
        }

        private int newOperationCookieLocked(int i)
        {
            int j = mGeneration;
            mGeneration = j + 1;
            return j << 8 | i;
        }

        public int beginOperation(String s, String s1, Object aobj[])
        {
            Operation aoperation[] = mOperations;
            aoperation;
            JVM INSTR monitorenter ;
            int i;
            Operation operation;
            i = (mIndex + 1) % 20;
            operation = mOperations[i];
            if(operation != null) goto _L2; else goto _L1
_L1:
            Operation operation1;
            operation1 = JVM INSTR new #24  <Class SQLiteConnection$Operation>;
            operation1.Operation(null);
            mOperations[i] = operation1;
_L15:
            operation1.mStartWallTime = System.currentTimeMillis();
            operation1.mStartTime = SystemClock.uptimeMillis();
            operation1.mKind = s;
            operation1.mSql = s1;
            if(aobj == null) goto _L4; else goto _L3
_L3:
            if(operation1.mBindArgs != null) goto _L6; else goto _L5
_L5:
            s = JVM INSTR new #138 <Class ArrayList>;
            s.ArrayList();
            operation1.mBindArgs = s;
_L12:
            int j = 0;
_L11:
            if(j >= aobj.length) goto _L4; else goto _L7
_L7:
            s = ((String) (aobj[j]));
            if(s == null) goto _L9; else goto _L8
_L8:
            if(!(s instanceof byte[])) goto _L9; else goto _L10
_L10:
            operation1.mBindArgs.add(SQLiteConnection._2D_get0());
_L13:
            j++;
              goto _L11
_L2:
            operation.mFinished = false;
            operation.mException = null;
            operation1 = operation;
            if(operation.mBindArgs == null)
                continue; /* Loop/switch isn't completed */
            operation.mBindArgs.clear();
            operation1 = operation;
            continue; /* Loop/switch isn't completed */
            s;
            throw s;
_L6:
            operation1.mBindArgs.clear();
              goto _L12
_L9:
            operation1.mBindArgs.add(s);
              goto _L13
_L4:
            operation1.mCookie = newOperationCookieLocked(i);
            if(Trace.isTagEnabled(0x100000L))
                Trace.asyncTraceBegin(0x100000L, Operation._2D_wrap1(operation1), operation1.mCookie);
            mIndex = i;
            j = operation1.mCookie;
            aoperation;
            JVM INSTR monitorexit ;
            return j;
            if(true) goto _L15; else goto _L14
_L14:
        }

        public String describeCurrentOperation()
        {
            Operation aoperation[] = mOperations;
            aoperation;
            JVM INSTR monitorenter ;
            Operation operation = mOperations[mIndex];
            if(operation == null)
                break MISSING_BLOCK_LABEL_53;
            Object obj;
            if(!(operation.mFinished ^ true))
                break MISSING_BLOCK_LABEL_53;
            obj = JVM INSTR new #83  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            operation.describe(((StringBuilder) (obj)), false);
            obj = ((StringBuilder) (obj)).toString();
            return ((String) (obj));
            aoperation;
            JVM INSTR monitorexit ;
            return null;
            Exception exception;
            exception;
            throw exception;
        }

        public void dump(Printer printer, boolean flag)
        {
            Operation aoperation[] = mOperations;
            aoperation;
            JVM INSTR monitorenter ;
            int i;
            Operation operation;
            printer.println("  Most recently executed operations:");
            i = mIndex;
            operation = mOperations[i];
            if(operation == null) goto _L2; else goto _L1
_L1:
            int j = 0;
_L4:
            StringBuilder stringbuilder = JVM INSTR new #83  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            stringbuilder.append("    ").append(j).append(": [");
            stringbuilder.append(Operation._2D_wrap0(operation));
            stringbuilder.append("] ");
            operation.describe(stringbuilder, flag);
            printer.println(stringbuilder.toString());
            int k;
            if(i > 0)
                i--;
            else
                i = 19;
            k = j + 1;
            operation = mOperations[i];
            if(operation == null)
                break; /* Loop/switch isn't completed */
            j = k;
            if(k < 20) goto _L4; else goto _L3
_L3:
            aoperation;
            JVM INSTR monitorexit ;
            return;
_L2:
            printer.println("    <none>");
            if(true) goto _L3; else goto _L5
_L5:
            printer;
            throw printer;
        }

        public void endOperation(int i)
        {
            Operation aoperation[] = mOperations;
            aoperation;
            JVM INSTR monitorenter ;
            if(endOperationDeferLogLocked(i))
                logOperationLocked(i, null);
            aoperation;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean endOperationDeferLog(int i)
        {
            Operation aoperation[] = mOperations;
            aoperation;
            JVM INSTR monitorenter ;
            boolean flag = endOperationDeferLogLocked(i);
            aoperation;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public void failOperation(int i, Exception exception)
        {
            Operation aoperation[] = mOperations;
            aoperation;
            JVM INSTR monitorenter ;
            Operation operation = getOperationLocked(i);
            if(operation == null)
                break MISSING_BLOCK_LABEL_25;
            operation.mException = exception;
            aoperation;
            JVM INSTR monitorexit ;
            return;
            exception;
            throw exception;
        }

        public void logOperation(int i, String s)
        {
            Operation aoperation[] = mOperations;
            aoperation;
            JVM INSTR monitorenter ;
            logOperationLocked(i, s);
            aoperation;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        private static final int COOKIE_GENERATION_SHIFT = 8;
        private static final int COOKIE_INDEX_MASK = 255;
        private static final int MAX_RECENT_OPERATIONS = 20;
        private int mGeneration;
        private int mIndex;
        private final Operation mOperations[];

        private OperationLog()
        {
            mOperations = new Operation[20];
        }

        OperationLog(OperationLog operationlog)
        {
            this();
        }
    }

    private static final class PreparedStatement
    {

        public boolean mInCache;
        public boolean mInUse;
        public int mNumParameters;
        public PreparedStatement mPoolNext;
        public boolean mReadOnly;
        public String mSql;
        public long mStatementPtr;
        public int mType;

        private PreparedStatement()
        {
        }

        PreparedStatement(PreparedStatement preparedstatement)
        {
            this();
        }
    }

    private final class PreparedStatementCache extends LruCache
    {

        public void dump(Printer printer)
        {
            printer.println("  Prepared statement cache:");
            Map map = snapshot();
            if(!map.isEmpty())
            {
                int i = 0;
                for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
                {
                    Object obj = (java.util.Map.Entry)iterator.next();
                    PreparedStatement preparedstatement = (PreparedStatement)((java.util.Map.Entry) (obj)).getValue();
                    if(preparedstatement.mInCache)
                    {
                        obj = (String)((java.util.Map.Entry) (obj)).getKey();
                        printer.println((new StringBuilder()).append("    ").append(i).append(": statementPtr=0x").append(Long.toHexString(preparedstatement.mStatementPtr)).append(", numParameters=").append(preparedstatement.mNumParameters).append(", type=").append(preparedstatement.mType).append(", readOnly=").append(preparedstatement.mReadOnly).append(", sql=\"").append(SQLiteConnection._2D_wrap0(((String) (obj)))).append("\"").toString());
                    }
                    i++;
                }

            } else
            {
                printer.println("    <none>");
            }
        }

        protected volatile void entryRemoved(boolean flag, Object obj, Object obj1, Object obj2)
        {
            entryRemoved(flag, (String)obj, (PreparedStatement)obj1, (PreparedStatement)obj2);
        }

        protected void entryRemoved(boolean flag, String s, PreparedStatement preparedstatement, PreparedStatement preparedstatement1)
        {
            preparedstatement.mInCache = false;
            if(!preparedstatement.mInUse)
                SQLiteConnection._2D_wrap1(SQLiteConnection.this, preparedstatement);
        }

        final SQLiteConnection this$0;

        public PreparedStatementCache(int i)
        {
            this$0 = SQLiteConnection.this;
            super(i);
        }
    }


    static byte[] _2D_get0()
    {
        return EMPTY_BYTE_ARRAY;
    }

    static String _2D_wrap0(String s)
    {
        return trimSqlForDisplay(s);
    }

    static void _2D_wrap1(SQLiteConnection sqliteconnection, PreparedStatement preparedstatement)
    {
        sqliteconnection.finalizePreparedStatement(preparedstatement);
    }

    private SQLiteConnection(SQLiteConnectionPool sqliteconnectionpool, SQLiteDatabaseConfiguration sqlitedatabaseconfiguration, int i, boolean flag)
    {
        boolean flag1 = false;
        super();
        mPool = sqliteconnectionpool;
        mConfiguration = new SQLiteDatabaseConfiguration(sqlitedatabaseconfiguration);
        mConnectionId = i;
        mIsPrimaryConnection = flag;
        flag = flag1;
        if((sqlitedatabaseconfiguration.openFlags & 1) != 0)
            flag = true;
        mIsReadOnlyConnection = flag;
        mPreparedStatementCache = new PreparedStatementCache(mConfiguration.maxSqlCacheSize);
        mCloseGuard.open("close");
    }

    private PreparedStatement acquirePreparedStatement(String s)
    {
        PreparedStatement preparedstatement = (PreparedStatement)mPreparedStatementCache.get(s);
        boolean flag = false;
        if(preparedstatement != null)
        {
            if(!preparedstatement.mInUse)
                return preparedstatement;
            flag = true;
        }
        long l = nativePrepareStatement(mConnectionPtr, s);
        PreparedStatement preparedstatement1 = preparedstatement;
        int i;
        int j;
        try
        {
            i = nativeGetParameterCount(mConnectionPtr, l);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            if(preparedstatement1 == null || preparedstatement1.mInCache ^ true)
                nativeFinalizeStatement(mConnectionPtr, l);
            throw s;
        }
        preparedstatement1 = preparedstatement;
        j = DatabaseUtils.getSqlStatementType(s);
        preparedstatement1 = preparedstatement;
        preparedstatement = obtainPreparedStatement(s, l, i, j, nativeIsReadOnly(mConnectionPtr, l));
        if(flag)
            break MISSING_BLOCK_LABEL_122;
        preparedstatement1 = preparedstatement;
        if(!isCacheable(j))
            break MISSING_BLOCK_LABEL_122;
        preparedstatement1 = preparedstatement;
        mPreparedStatementCache.put(s, preparedstatement);
        preparedstatement1 = preparedstatement;
        preparedstatement.mInCache = true;
        preparedstatement.mInUse = true;
        return preparedstatement;
    }

    private void applyBlockGuardPolicy(PreparedStatement preparedstatement)
    {
        if(!mConfiguration.isInMemoryDb())
            if(preparedstatement.mReadOnly)
                BlockGuard.getThreadPolicy().onReadFromDisk();
            else
                BlockGuard.getThreadPolicy().onWriteToDisk();
    }

    private void attachCancellationSignal(CancellationSignal cancellationsignal)
    {
        if(cancellationsignal != null)
        {
            cancellationsignal.throwIfCanceled();
            mCancellationSignalAttachCount = mCancellationSignalAttachCount + 1;
            if(mCancellationSignalAttachCount == 1)
            {
                nativeResetCancel(mConnectionPtr, true);
                cancellationsignal.setOnCancelListener(this);
            }
        }
    }

    private void bindArguments(PreparedStatement preparedstatement, Object aobj[])
    {
label0:
        {
label1:
            {
label2:
                {
label3:
                    {
                        int i;
                        if(aobj != null)
                            i = aobj.length;
                        else
                            i = 0;
                        if(i != preparedstatement.mNumParameters)
                            throw new SQLiteBindOrColumnIndexOutOfRangeException((new StringBuilder()).append("Expected ").append(preparedstatement.mNumParameters).append(" bind arguments but ").append(i).append(" were provided.").toString());
                        if(i == 0)
                            return;
                        long l = preparedstatement.mStatementPtr;
                        int j = 0;
label4:
                        do
                        {
                            {
                                if(j >= i)
                                    break label0;
                                preparedstatement = ((PreparedStatement) (aobj[j]));
                                switch(DatabaseUtils.getTypeOfObject(preparedstatement))
                                {
                                case 3: // '\003'
                                default:
                                    if(preparedstatement instanceof Boolean)
                                    {
                                        long l1 = mConnectionPtr;
                                        int k;
                                        if(((Boolean)preparedstatement).booleanValue())
                                            k = 1;
                                        else
                                            k = 0;
                                        nativeBindLong(l1, l, j + 1, k);
                                    } else
                                    {
                                        nativeBindString(mConnectionPtr, l, j + 1, preparedstatement.toString());
                                    }
                                    break;

                                case 0: // '\0'
                                    break label4;

                                case 1: // '\001'
                                    break label3;

                                case 2: // '\002'
                                    break label2;

                                case 4: // '\004'
                                    break label1;
                                }
                            }
                            j++;
                        } while(true);
                        nativeBindNull(mConnectionPtr, l, j + 1);
                        break MISSING_BLOCK_LABEL_168;
                    }
                    nativeBindLong(mConnectionPtr, l, j + 1, ((Number)preparedstatement).longValue());
                    break MISSING_BLOCK_LABEL_168;
                }
                nativeBindDouble(mConnectionPtr, l, j + 1, ((Number)preparedstatement).doubleValue());
                break MISSING_BLOCK_LABEL_168;
            }
            nativeBindBlob(mConnectionPtr, l, j + 1, (byte[])preparedstatement);
            break MISSING_BLOCK_LABEL_168;
        }
    }

    private static String canonicalizeSyncMode(String s)
    {
        if(s.equals("0"))
            return "OFF";
        if(s.equals("1"))
            return "NORMAL";
        if(s.equals("2"))
            return "FULL";
        else
            return s;
    }

    private void detachCancellationSignal(CancellationSignal cancellationsignal)
    {
        if(cancellationsignal != null)
        {
            if(!_2D_assertionsDisabled && mCancellationSignalAttachCount <= 0)
                throw new AssertionError();
            mCancellationSignalAttachCount = mCancellationSignalAttachCount - 1;
            if(mCancellationSignalAttachCount == 0)
            {
                cancellationsignal.setOnCancelListener(null);
                nativeResetCancel(mConnectionPtr, false);
            }
        }
    }

    private void dispose(boolean flag)
    {
        int i;
        if(mCloseGuard != null)
        {
            if(flag)
                mCloseGuard.warnIfOpen();
            mCloseGuard.close();
        }
        if(mConnectionPtr == 0L)
            break MISSING_BLOCK_LABEL_73;
        i = mRecentOperations.beginOperation("close", null, null);
        mPreparedStatementCache.evictAll();
        nativeClose(mConnectionPtr);
        mConnectionPtr = 0L;
        mRecentOperations.endOperation(i);
        return;
        Exception exception;
        exception;
        mRecentOperations.endOperation(i);
        throw exception;
    }

    private void finalizePreparedStatement(PreparedStatement preparedstatement)
    {
        nativeFinalizeStatement(mConnectionPtr, preparedstatement.mStatementPtr);
        recyclePreparedStatement(preparedstatement);
    }

    private SQLiteDebug.DbStats getMainDbStatsUnsafe(int i, long l, long l1)
    {
        String s = mConfiguration.path;
        String s1 = s;
        if(!mIsPrimaryConnection)
            s1 = (new StringBuilder()).append(s).append(" (").append(mConnectionId).append(")").toString();
        return new SQLiteDebug.DbStats(s1, l, l1, i, mPreparedStatementCache.hitCount(), mPreparedStatementCache.missCount(), mPreparedStatementCache.size());
    }

    private static boolean isCacheable(int i)
    {
        return i == 2 || i == 1;
    }

    private static native void nativeBindBlob(long l, long l1, int i, byte abyte0[]);

    private static native void nativeBindDouble(long l, long l1, int i, double d);

    private static native void nativeBindLong(long l, long l1, int i, long l2);

    private static native void nativeBindNull(long l, long l1, int i);

    private static native void nativeBindString(long l, long l1, int i, String s);

    private static native void nativeCancel(long l);

    private static native void nativeClose(long l);

    private static native void nativeExecute(long l, long l1);

    private static native int nativeExecuteForBlobFileDescriptor(long l, long l1);

    private static native int nativeExecuteForChangedRowCount(long l, long l1);

    private static native long nativeExecuteForCursorWindow(long l, long l1, long l2, int i, int j, 
            boolean flag);

    private static native long nativeExecuteForLastInsertedRowId(long l, long l1);

    private static native long nativeExecuteForLong(long l, long l1);

    private static native String nativeExecuteForString(long l, long l1);

    private static native void nativeFinalizeStatement(long l, long l1);

    private static native int nativeGetColumnCount(long l, long l1);

    private static native String nativeGetColumnName(long l, long l1, int i);

    private static native int nativeGetDbLookaside(long l);

    private static native int nativeGetParameterCount(long l, long l1);

    private static native boolean nativeIsReadOnly(long l, long l1);

    private static native long nativeOpen(String s, int i, String s1, boolean flag, boolean flag1, int j, int k);

    private static native long nativePrepareStatement(long l, String s);

    private static native void nativeRegisterCustomFunction(long l, SQLiteCustomFunction sqlitecustomfunction);

    private static native void nativeRegisterLocalizedCollators(long l, String s);

    private static native void nativeResetCancel(long l, boolean flag);

    private static native void nativeResetStatementAndClearBindings(long l, long l1);

    private PreparedStatement obtainPreparedStatement(String s, long l, int i, int j, boolean flag)
    {
        PreparedStatement preparedstatement = mPreparedStatementPool;
        if(preparedstatement != null)
        {
            mPreparedStatementPool = preparedstatement.mPoolNext;
            preparedstatement.mPoolNext = null;
            preparedstatement.mInCache = false;
        } else
        {
            preparedstatement = new PreparedStatement(null);
        }
        preparedstatement.mSql = s;
        preparedstatement.mStatementPtr = l;
        preparedstatement.mNumParameters = i;
        preparedstatement.mType = j;
        preparedstatement.mReadOnly = flag;
        return preparedstatement;
    }

    static SQLiteConnection open(SQLiteConnectionPool sqliteconnectionpool, SQLiteDatabaseConfiguration sqlitedatabaseconfiguration, int i, boolean flag)
    {
        sqlitedatabaseconfiguration = new SQLiteConnection(sqliteconnectionpool, sqlitedatabaseconfiguration, i, flag);
        try
        {
            sqlitedatabaseconfiguration.open();
        }
        // Misplaced declaration of an exception variable
        catch(SQLiteConnectionPool sqliteconnectionpool)
        {
            sqlitedatabaseconfiguration.dispose(false);
            throw sqliteconnectionpool;
        }
        return sqlitedatabaseconfiguration;
    }

    private void open()
    {
        mConnectionPtr = nativeOpen(mConfiguration.path, mConfiguration.openFlags, mConfiguration.label, SQLiteDebug.DEBUG_SQL_STATEMENTS, SQLiteDebug.DEBUG_SQL_TIME, mConfiguration.lookasideSlotSize, mConfiguration.lookasideSlotCount);
        setPageSize();
        setForeignKeyModeFromConfiguration();
        setWalModeFromConfiguration();
        setJournalSizeLimit();
        setAutoCheckpointInterval();
        setLocaleFromConfiguration();
        int i = mConfiguration.customFunctions.size();
        for(int j = 0; j < i; j++)
        {
            SQLiteCustomFunction sqlitecustomfunction = (SQLiteCustomFunction)mConfiguration.customFunctions.get(j);
            nativeRegisterCustomFunction(mConnectionPtr, sqlitecustomfunction);
        }

    }

    private void recyclePreparedStatement(PreparedStatement preparedstatement)
    {
        preparedstatement.mSql = null;
        preparedstatement.mPoolNext = mPreparedStatementPool;
        mPreparedStatementPool = preparedstatement;
    }

    private void releasePreparedStatement(PreparedStatement preparedstatement)
    {
        preparedstatement.mInUse = false;
        if(!preparedstatement.mInCache)
            break MISSING_BLOCK_LABEL_40;
        nativeResetStatementAndClearBindings(mConnectionPtr, preparedstatement.mStatementPtr);
_L1:
        return;
        SQLiteException sqliteexception;
        sqliteexception;
        mPreparedStatementCache.remove(preparedstatement.mSql);
          goto _L1
        finalizePreparedStatement(preparedstatement);
          goto _L1
    }

    private void setAutoCheckpointInterval()
    {
        if(!mConfiguration.isInMemoryDb() && mIsReadOnlyConnection ^ true)
        {
            long l = SQLiteGlobal.getWALAutoCheckpoint();
            if(executeForLong("PRAGMA wal_autocheckpoint", null, null) != l)
                executeForLong((new StringBuilder()).append("PRAGMA wal_autocheckpoint=").append(l).toString(), null, null);
        }
    }

    private void setForeignKeyModeFromConfiguration()
    {
        if(!mIsReadOnlyConnection)
        {
            int i;
            long l;
            if(mConfiguration.foreignKeyConstraintsEnabled)
                i = 1;
            else
                i = 0;
            l = i;
            if(executeForLong("PRAGMA foreign_keys", null, null) != l)
                execute((new StringBuilder()).append("PRAGMA foreign_keys=").append(l).toString(), null, null);
        }
    }

    private void setJournalMode(String s)
    {
        String s1;
        s1 = executeForString("PRAGMA journal_mode", null, null);
        if(s1.equalsIgnoreCase(s))
            break MISSING_BLOCK_LABEL_135;
        boolean flag;
        StringBuilder stringbuilder = JVM INSTR new #225 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        flag = executeForString(stringbuilder.append("PRAGMA journal_mode=").append(s).toString(), null, null).equalsIgnoreCase(s);
label0:
        {
            if(flag)
                return;
            break label0;
        }
        SQLiteDatabaseLockedException sqlitedatabaselockedexception;
        sqlitedatabaselockedexception;
        Log.w("SQLiteConnection", (new StringBuilder()).append("Could not change the database journal mode of '").append(mConfiguration.label).append("' from '").append(s1).append("' to '").append(s).append("' because the database is locked.  This usually means that ").append("there are other open connections to the database which prevents ").append("the database from enabling or disabling write-ahead logging mode.  ").append("Proceeding without changing the journal mode.").toString());
    }

    private void setJournalSizeLimit()
    {
        if(!mConfiguration.isInMemoryDb() && mIsReadOnlyConnection ^ true)
        {
            long l = SQLiteGlobal.getJournalSizeLimit();
            if(executeForLong("PRAGMA journal_size_limit", null, null) != l)
                executeForLong((new StringBuilder()).append("PRAGMA journal_size_limit=").append(l).toString(), null, null);
        }
    }

    private void setLocaleFromConfiguration()
    {
        Object obj;
        if((mConfiguration.openFlags & 0x10) != 0)
            return;
        String s = mConfiguration.locale.toString();
        nativeRegisterLocalizedCollators(mConnectionPtr, s);
        if(mIsReadOnlyConnection)
            return;
        Exception exception;
        try
        {
            execute("CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT)", null, null);
            obj = executeForString("SELECT locale FROM android_metadata UNION SELECT NULL ORDER BY locale DESC LIMIT 1", null, null);
        }
        catch(RuntimeException runtimeexception)
        {
            throw new SQLiteException((new StringBuilder()).append("Failed to change locale for db '").append(mConfiguration.label).append("' to '").append(s).append("'.").toString(), runtimeexception);
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_73;
        if(((String) (obj)).equals(s))
            return;
        execute("BEGIN", null, null);
        execute("DELETE FROM android_metadata", null, null);
        execute("INSERT INTO android_metadata (locale) VALUES(?)", new Object[] {
            s
        }, null);
        execute("REINDEX LOCALIZED", null, null);
        if(true)
            obj = "COMMIT";
        else
            obj = "ROLLBACK";
        execute(((String) (obj)), null, null);
        return;
        exception;
        if(false)
            obj = "COMMIT";
        else
            runtimeexception = "ROLLBACK";
        execute(((String) (obj)), null, null);
        throw exception;
    }

    private void setPageSize()
    {
        if(!mConfiguration.isInMemoryDb() && mIsReadOnlyConnection ^ true)
        {
            long l = SQLiteGlobal.getDefaultPageSize();
            if(executeForLong("PRAGMA page_size", null, null) != l)
                execute((new StringBuilder()).append("PRAGMA page_size=").append(l).toString(), null, null);
        }
    }

    private void setSyncMode(String s)
    {
        if(!canonicalizeSyncMode(executeForString("PRAGMA synchronous", null, null)).equalsIgnoreCase(canonicalizeSyncMode(s)))
            execute((new StringBuilder()).append("PRAGMA synchronous=").append(s).toString(), null, null);
    }

    private void setWalModeFromConfiguration()
    {
        if(!mConfiguration.isInMemoryDb() && mIsReadOnlyConnection ^ true)
            if((mConfiguration.openFlags & 0x20000000) != 0)
            {
                setJournalMode("WAL");
                setSyncMode(SQLiteGlobal.getWALSyncMode());
            } else
            {
                setJournalMode(SQLiteGlobal.getDefaultJournalMode());
                setSyncMode(SQLiteGlobal.getDefaultSyncMode());
            }
    }

    private void throwIfStatementForbidden(PreparedStatement preparedstatement)
    {
        if(mOnlyAllowReadOnlyOperations && preparedstatement.mReadOnly ^ true)
            throw new SQLiteException("Cannot execute this statement because it might modify the database but the connection is read-only.");
        else
            return;
    }

    private static String trimSqlForDisplay(String s)
    {
        return s.replaceAll("[\\s]*\\n+[\\s]*", " ");
    }

    void close()
    {
        dispose(false);
    }

    void collectDbStats(ArrayList arraylist)
    {
        int i;
        long l;
        long l1;
        i = nativeGetDbLookaside(mConnectionPtr);
        l = 0L;
        l1 = 0L;
        long l2 = executeForLong("PRAGMA page_count;", null, null);
        l = l2;
        long l3 = executeForLong("PRAGMA page_size;", null, null);
        l = l3;
_L8:
        CursorWindow cursorwindow;
        arraylist.add(getMainDbStatsUnsafe(i, l2, l));
        cursorwindow = new CursorWindow("collectDbStats");
        executeForCursorWindow("PRAGMA database_list;", null, cursorwindow, 0, 0, false, null);
        i = 1;
_L3:
        if(i >= cursorwindow.getNumRows()) goto _L2; else goto _L1
_L1:
        Object obj;
        String s;
        obj = cursorwindow.getString(i, 1);
        s = cursorwindow.getString(i, 2);
        l2 = 0L;
        l1 = 0L;
        l = l2;
        Object obj1 = JVM INSTR new #225 <Class StringBuilder>;
        l = l2;
        ((StringBuilder) (obj1)).StringBuilder();
        l = l2;
        l2 = executeForLong(((StringBuilder) (obj1)).append("PRAGMA ").append(((String) (obj))).append(".page_count;").toString(), null, null);
        l = l2;
        obj1 = JVM INSTR new #225 <Class StringBuilder>;
        l = l2;
        ((StringBuilder) (obj1)).StringBuilder();
        l = l2;
        l3 = executeForLong(((StringBuilder) (obj1)).append("PRAGMA ").append(((String) (obj))).append(".page_size;").toString(), null, null);
        l1 = l3;
        l = l2;
_L6:
        obj1 = JVM INSTR new #225 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = ((StringBuilder) (obj1)).append("  (attached) ").append(((String) (obj))).toString();
        obj = obj1;
        if(!s.isEmpty())
        {
            obj = JVM INSTR new #225 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            obj = ((StringBuilder) (obj)).append(((String) (obj1))).append(": ").append(s).toString();
        }
        obj1 = JVM INSTR new #346 <Class SQLiteDebug$DbStats>;
        ((SQLiteDebug.DbStats) (obj1)).SQLiteDebug.DbStats(((String) (obj)), l, l1, 0, 0, 0, 0);
        arraylist.add(obj1);
        i++;
          goto _L3
_L2:
        cursorwindow.close();
_L5:
        return;
        arraylist;
        cursorwindow.close();
        if(true) goto _L5; else goto _L4
_L4:
        arraylist;
        cursorwindow.close();
        throw arraylist;
        SQLiteException sqliteexception1;
        sqliteexception1;
          goto _L6
        SQLiteException sqliteexception;
        sqliteexception;
        l2 = l;
        l = l1;
        if(true) goto _L8; else goto _L7
_L7:
    }

    void collectDbStatsUnsafe(ArrayList arraylist)
    {
        arraylist.add(getMainDbStatsUnsafe(0, 0L, 0L));
    }

    String describeCurrentOperationUnsafe()
    {
        return mRecentOperations.describeCurrentOperation();
    }

    public void dump(Printer printer, boolean flag)
    {
        dumpUnsafe(printer, flag);
    }

    void dumpUnsafe(Printer printer, boolean flag)
    {
        printer.println((new StringBuilder()).append("Connection #").append(mConnectionId).append(":").toString());
        if(flag)
            printer.println((new StringBuilder()).append("  connectionPtr: 0x").append(Long.toHexString(mConnectionPtr)).toString());
        printer.println((new StringBuilder()).append("  isPrimaryConnection: ").append(mIsPrimaryConnection).toString());
        printer.println((new StringBuilder()).append("  onlyAllowReadOnlyOperations: ").append(mOnlyAllowReadOnlyOperations).toString());
        mRecentOperations.dump(printer, flag);
        if(flag)
            mPreparedStatementCache.dump(printer);
    }

    public void execute(String s, Object aobj[], CancellationSignal cancellationsignal)
    {
        int i;
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        i = mRecentOperations.beginOperation("execute", s, aobj);
        s = acquirePreparedStatement(s);
        throwIfStatementForbidden(s);
        bindArguments(s, aobj);
        applyBlockGuardPolicy(s);
        attachCancellationSignal(cancellationsignal);
        nativeExecute(mConnectionPtr, ((PreparedStatement) (s)).mStatementPtr);
        detachCancellationSignal(cancellationsignal);
        releasePreparedStatement(s);
        mRecentOperations.endOperation(i);
        return;
        aobj;
        detachCancellationSignal(cancellationsignal);
        throw aobj;
        aobj;
        try
        {
            releasePreparedStatement(s);
            throw aobj;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        mRecentOperations.failOperation(i, s);
        throw s;
        s;
        mRecentOperations.endOperation(i);
        throw s;
    }

    public ParcelFileDescriptor executeForBlobFileDescriptor(String s, Object aobj[], CancellationSignal cancellationsignal)
    {
        Object obj;
        int i;
        obj = null;
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        i = mRecentOperations.beginOperation("executeForBlobFileDescriptor", s, aobj);
        PreparedStatement preparedstatement = acquirePreparedStatement(s);
        throwIfStatementForbidden(preparedstatement);
        bindArguments(preparedstatement, aobj);
        applyBlockGuardPolicy(preparedstatement);
        attachCancellationSignal(cancellationsignal);
        int j = nativeExecuteForBlobFileDescriptor(mConnectionPtr, preparedstatement.mStatementPtr);
        s = obj;
        if(j < 0)
            break MISSING_BLOCK_LABEL_91;
        s = ParcelFileDescriptor.adoptFd(j);
        detachCancellationSignal(cancellationsignal);
        releasePreparedStatement(preparedstatement);
        mRecentOperations.endOperation(i);
        return s;
        s;
        detachCancellationSignal(cancellationsignal);
        throw s;
        s;
        try
        {
            releasePreparedStatement(preparedstatement);
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        mRecentOperations.failOperation(i, s);
        throw s;
        s;
        mRecentOperations.endOperation(i);
        throw s;
    }

    public int executeForChangedRowCount(String s, Object aobj[], CancellationSignal cancellationsignal)
    {
        int i;
        int j;
        int k;
        int l;
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        i = 0;
        j = 0;
        k = 0;
        l = mRecentOperations.beginOperation("executeForChangedRowCount", s, aobj);
        s = acquirePreparedStatement(s);
        int i1 = k;
        throwIfStatementForbidden(s);
        i1 = k;
        bindArguments(s, aobj);
        i1 = k;
        applyBlockGuardPolicy(s);
        i1 = k;
        attachCancellationSignal(cancellationsignal);
        i = nativeExecuteForChangedRowCount(mConnectionPtr, ((PreparedStatement) (s)).mStatementPtr);
        k = i;
        i1 = k;
        detachCancellationSignal(cancellationsignal);
        i = k;
        j = k;
        releasePreparedStatement(s);
        if(mRecentOperations.endOperationDeferLog(l))
            mRecentOperations.logOperation(l, (new StringBuilder()).append("changedRows=").append(k).toString());
        return k;
        aobj;
        i1 = k;
        detachCancellationSignal(cancellationsignal);
        i1 = k;
        throw aobj;
        aobj;
        i = i1;
        j = i1;
        releasePreparedStatement(s);
        i = i1;
        j = i1;
        try
        {
            throw aobj;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            j = i;
        }
        mRecentOperations.failOperation(l, s);
        j = i;
        throw s;
        s;
        if(mRecentOperations.endOperationDeferLog(l))
            mRecentOperations.logOperation(l, (new StringBuilder()).append("changedRows=").append(j).toString());
        throw s;
    }

    public int executeForCursorWindow(String s, Object aobj[], CursorWindow cursorwindow, int i, int j, boolean flag, CancellationSignal cancellationsignal)
    {
        int k;
        int l;
        int i1;
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(cursorwindow == null)
            throw new IllegalArgumentException("window must not be null.");
        cursorwindow.acquireReference();
        k = -1;
        l = -1;
        i1 = -1;
        int j1 = mRecentOperations.beginOperation("executeForCursorWindow", s, aobj);
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        k1 = k;
        l1 = l;
        i2 = i1;
        j2 = k;
        k2 = l;
        l2 = i1;
        s = acquirePreparedStatement(s);
        int i3;
        int j3;
        int k3;
        i3 = k;
        j3 = l;
        k3 = i1;
        throwIfStatementForbidden(s);
        i3 = k;
        j3 = l;
        k3 = i1;
        bindArguments(s, aobj);
        i3 = k;
        j3 = l;
        k3 = i1;
        applyBlockGuardPolicy(s);
        i3 = k;
        j3 = l;
        k3 = i1;
        attachCancellationSignal(cancellationsignal);
        k2 = l;
        l1 = i1;
        long l3 = nativeExecuteForCursorWindow(mConnectionPtr, ((PreparedStatement) (s)).mStatementPtr, cursorwindow.mWindowPtr, i, j, flag);
        l = (int)(l3 >> 32);
        j = (int)l3;
        k = l;
        k2 = j;
        l1 = i1;
        i1 = cursorwindow.getNumRows();
        k = l;
        k2 = j;
        l1 = i1;
        cursorwindow.setStartPosition(l);
        i3 = l;
        j3 = j;
        k3 = i1;
        detachCancellationSignal(cancellationsignal);
        k1 = l;
        l1 = j;
        i2 = i1;
        j2 = l;
        k2 = j;
        l2 = i1;
        releasePreparedStatement(s);
        if(mRecentOperations.endOperationDeferLog(j1))
        {
            s = mRecentOperations;
            aobj = JVM INSTR new #225 <Class StringBuilder>;
            ((StringBuilder) (aobj)).StringBuilder();
            s.logOperation(j1, ((StringBuilder) (aobj)).append("window='").append(cursorwindow).append("', startPos=").append(i).append(", actualPos=").append(l).append(", filledRows=").append(i1).append(", countedRows=").append(j).toString());
        }
        cursorwindow.releaseReference();
        return j;
        aobj;
        i3 = k;
        j3 = k2;
        k3 = l1;
        detachCancellationSignal(cancellationsignal);
        i3 = k;
        j3 = k2;
        k3 = l1;
        throw aobj;
        aobj;
        k1 = i3;
        l1 = j3;
        i2 = k3;
        j2 = i3;
        k2 = j3;
        l2 = k3;
        releasePreparedStatement(s);
        k1 = i3;
        l1 = j3;
        i2 = k3;
        j2 = i3;
        k2 = j3;
        l2 = k3;
        try
        {
            throw aobj;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            j2 = k1;
        }
        k2 = l1;
        l2 = i2;
        mRecentOperations.failOperation(j1, s);
        j2 = k1;
        k2 = l1;
        l2 = i2;
        throw s;
        cancellationsignal;
        if(mRecentOperations.endOperationDeferLog(j1))
        {
            aobj = mRecentOperations;
            s = JVM INSTR new #225 <Class StringBuilder>;
            s.StringBuilder();
            ((OperationLog) (aobj)).logOperation(j1, s.append("window='").append(cursorwindow).append("', startPos=").append(i).append(", actualPos=").append(j2).append(", filledRows=").append(l2).append(", countedRows=").append(k2).toString());
        }
        throw cancellationsignal;
        s;
        cursorwindow.releaseReference();
        throw s;
    }

    public long executeForLastInsertedRowId(String s, Object aobj[], CancellationSignal cancellationsignal)
    {
        int i;
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        i = mRecentOperations.beginOperation("executeForLastInsertedRowId", s, aobj);
        s = acquirePreparedStatement(s);
        throwIfStatementForbidden(s);
        bindArguments(s, aobj);
        applyBlockGuardPolicy(s);
        attachCancellationSignal(cancellationsignal);
        long l = nativeExecuteForLastInsertedRowId(mConnectionPtr, ((PreparedStatement) (s)).mStatementPtr);
        detachCancellationSignal(cancellationsignal);
        releasePreparedStatement(s);
        mRecentOperations.endOperation(i);
        return l;
        aobj;
        detachCancellationSignal(cancellationsignal);
        throw aobj;
        aobj;
        try
        {
            releasePreparedStatement(s);
            throw aobj;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        mRecentOperations.failOperation(i, s);
        throw s;
        s;
        mRecentOperations.endOperation(i);
        throw s;
    }

    public long executeForLong(String s, Object aobj[], CancellationSignal cancellationsignal)
    {
        int i;
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        i = mRecentOperations.beginOperation("executeForLong", s, aobj);
        s = acquirePreparedStatement(s);
        throwIfStatementForbidden(s);
        bindArguments(s, aobj);
        applyBlockGuardPolicy(s);
        attachCancellationSignal(cancellationsignal);
        long l = nativeExecuteForLong(mConnectionPtr, ((PreparedStatement) (s)).mStatementPtr);
        detachCancellationSignal(cancellationsignal);
        releasePreparedStatement(s);
        mRecentOperations.endOperation(i);
        return l;
        aobj;
        detachCancellationSignal(cancellationsignal);
        throw aobj;
        aobj;
        try
        {
            releasePreparedStatement(s);
            throw aobj;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        mRecentOperations.failOperation(i, s);
        throw s;
        s;
        mRecentOperations.endOperation(i);
        throw s;
    }

    public String executeForString(String s, Object aobj[], CancellationSignal cancellationsignal)
    {
        int i;
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        i = mRecentOperations.beginOperation("executeForString", s, aobj);
        s = acquirePreparedStatement(s);
        throwIfStatementForbidden(s);
        bindArguments(s, aobj);
        applyBlockGuardPolicy(s);
        attachCancellationSignal(cancellationsignal);
        aobj = nativeExecuteForString(mConnectionPtr, ((PreparedStatement) (s)).mStatementPtr);
        detachCancellationSignal(cancellationsignal);
        releasePreparedStatement(s);
        mRecentOperations.endOperation(i);
        return ((String) (aobj));
        aobj;
        detachCancellationSignal(cancellationsignal);
        throw aobj;
        aobj;
        try
        {
            releasePreparedStatement(s);
            throw aobj;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        mRecentOperations.failOperation(i, s);
        throw s;
        s;
        mRecentOperations.endOperation(i);
        throw s;
    }

    protected void finalize()
        throws Throwable
    {
        if(mPool != null && mConnectionPtr != 0L)
            mPool.onConnectionLeaked();
        dispose(true);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getConnectionId()
    {
        return mConnectionId;
    }

    boolean isPreparedStatementInCache(String s)
    {
        boolean flag;
        if(mPreparedStatementCache.get(s) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPrimaryConnection()
    {
        return mIsPrimaryConnection;
    }

    public void onCancel()
    {
        nativeCancel(mConnectionPtr);
    }

    public void prepare(String s, SQLiteStatementInfo sqlitestatementinfo)
    {
        int i;
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        i = mRecentOperations.beginOperation("prepare", s, null);
        s = acquirePreparedStatement(s);
        if(sqlitestatementinfo == null) goto _L2; else goto _L1
_L1:
        int j;
        sqlitestatementinfo.numParameters = ((PreparedStatement) (s)).mNumParameters;
        sqlitestatementinfo.readOnly = ((PreparedStatement) (s)).mReadOnly;
        j = nativeGetColumnCount(mConnectionPtr, ((PreparedStatement) (s)).mStatementPtr);
        if(j != 0) goto _L4; else goto _L3
_L3:
        sqlitestatementinfo.columnNames = EMPTY_STRING_ARRAY;
_L2:
        releasePreparedStatement(s);
        mRecentOperations.endOperation(i);
        return;
_L4:
        sqlitestatementinfo.columnNames = new String[j];
        int k = 0;
_L6:
        if(k >= j)
            break; /* Loop/switch isn't completed */
        sqlitestatementinfo.columnNames[k] = nativeGetColumnName(mConnectionPtr, ((PreparedStatement) (s)).mStatementPtr, k);
        k++;
        if(true) goto _L6; else goto _L5
_L5:
        if(true) goto _L2; else goto _L7
_L7:
        sqlitestatementinfo;
        try
        {
            releasePreparedStatement(s);
            throw sqlitestatementinfo;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        mRecentOperations.failOperation(i, s);
        throw s;
        s;
        mRecentOperations.endOperation(i);
        throw s;
    }

    void reconfigure(SQLiteDatabaseConfiguration sqlitedatabaseconfiguration)
    {
        mOnlyAllowReadOnlyOperations = false;
        int i = sqlitedatabaseconfiguration.customFunctions.size();
        for(int j = 0; j < i; j++)
        {
            SQLiteCustomFunction sqlitecustomfunction = (SQLiteCustomFunction)sqlitedatabaseconfiguration.customFunctions.get(j);
            if(!mConfiguration.customFunctions.contains(sqlitecustomfunction))
                nativeRegisterCustomFunction(mConnectionPtr, sqlitecustomfunction);
        }

        boolean flag;
        boolean flag1;
        boolean flag2;
        if(sqlitedatabaseconfiguration.foreignKeyConstraintsEnabled != mConfiguration.foreignKeyConstraintsEnabled)
            flag1 = true;
        else
            flag1 = false;
        if(((sqlitedatabaseconfiguration.openFlags ^ mConfiguration.openFlags) & 0x20000000) != 0)
            flag = true;
        else
            flag = false;
        flag2 = sqlitedatabaseconfiguration.locale.equals(mConfiguration.locale);
        mConfiguration.updateParametersFrom(sqlitedatabaseconfiguration);
        mPreparedStatementCache.resize(sqlitedatabaseconfiguration.maxSqlCacheSize);
        if(flag1)
            setForeignKeyModeFromConfiguration();
        if(flag)
            setWalModeFromConfiguration();
        if(flag2 ^ true)
            setLocaleFromConfiguration();
    }

    void setOnlyAllowReadOnlyOperations(boolean flag)
    {
        mOnlyAllowReadOnlyOperations = flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("SQLiteConnection: ").append(mConfiguration.path).append(" (").append(mConnectionId).append(")").toString();
    }

    static final boolean _2D_assertionsDisabled = android/database/sqlite/SQLiteConnection.desiredAssertionStatus() ^ true;
    private static final boolean DEBUG = false;
    private static final byte EMPTY_BYTE_ARRAY[] = new byte[0];
    private static final String EMPTY_STRING_ARRAY[] = new String[0];
    private static final String TAG = "SQLiteConnection";
    private int mCancellationSignalAttachCount;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final SQLiteDatabaseConfiguration mConfiguration;
    private final int mConnectionId;
    private long mConnectionPtr;
    private final boolean mIsPrimaryConnection;
    private final boolean mIsReadOnlyConnection;
    private boolean mOnlyAllowReadOnlyOperations;
    private final SQLiteConnectionPool mPool;
    private final PreparedStatementCache mPreparedStatementCache;
    private PreparedStatement mPreparedStatementPool;
    private final OperationLog mRecentOperations = new OperationLog(null);

}
