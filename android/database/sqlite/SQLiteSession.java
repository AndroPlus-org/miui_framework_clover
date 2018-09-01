// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;

// Referenced classes of package android.database.sqlite:
//            SQLiteConnectionPool, SQLiteConnection, SQLiteTransactionListener, SQLiteStatementInfo

public final class SQLiteSession
{
    private static final class Transaction
    {

        public boolean mChildFailed;
        public SQLiteTransactionListener mListener;
        public boolean mMarkedSuccessful;
        public int mMode;
        public Transaction mParent;

        private Transaction()
        {
        }

        Transaction(Transaction transaction)
        {
            this();
        }
    }


    public SQLiteSession(SQLiteConnectionPool sqliteconnectionpool)
    {
        if(sqliteconnectionpool == null)
        {
            throw new IllegalArgumentException("connectionPool must not be null");
        } else
        {
            mConnectionPool = sqliteconnectionpool;
            return;
        }
    }

    private void acquireConnection(String s, int i, CancellationSignal cancellationsignal)
    {
        if(mConnection == null)
        {
            if(!_2D_assertionsDisabled && mConnectionUseCount != 0)
                throw new AssertionError();
            mConnection = mConnectionPool.acquireConnection(s, i, cancellationsignal);
            mConnectionFlags = i;
        }
        mConnectionUseCount = mConnectionUseCount + 1;
    }

    private void beginTransactionUnchecked(int i, SQLiteTransactionListener sqlitetransactionlistener, int j, CancellationSignal cancellationsignal)
    {
        if(cancellationsignal != null)
            cancellationsignal.throwIfCanceled();
        if(mTransactionStack == null)
            acquireConnection(null, j, cancellationsignal);
        if(mTransactionStack != null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 1 2: default 56
    //                   1 110
    //                   2 139;
           goto _L3 _L4 _L5
_L3:
        mConnection.execute("BEGIN;", null, cancellationsignal);
_L2:
        if(sqlitetransactionlistener == null)
            break MISSING_BLOCK_LABEL_78;
        sqlitetransactionlistener.onBegin();
        sqlitetransactionlistener = obtainTransaction(i, sqlitetransactionlistener);
        sqlitetransactionlistener.mParent = mTransactionStack;
        mTransactionStack = sqlitetransactionlistener;
        if(mTransactionStack == null)
            releaseConnection();
        return;
_L4:
        mConnection.execute("BEGIN IMMEDIATE;", null, cancellationsignal);
        continue; /* Loop/switch isn't completed */
        sqlitetransactionlistener;
        if(mTransactionStack == null)
            releaseConnection();
        throw sqlitetransactionlistener;
_L5:
        mConnection.execute("BEGIN EXCLUSIVE;", null, cancellationsignal);
        if(true) goto _L2; else goto _L6
_L6:
        sqlitetransactionlistener;
        if(mTransactionStack == null)
            mConnection.execute("ROLLBACK;", null, cancellationsignal);
        throw sqlitetransactionlistener;
    }

    private void endTransactionUnchecked(CancellationSignal cancellationsignal, boolean flag)
    {
        boolean flag1;
        Object obj;
        SQLiteTransactionListener sqlitetransactionlistener;
        RuntimeException runtimeexception;
        boolean flag2;
        if(cancellationsignal != null)
            cancellationsignal.throwIfCanceled();
        Transaction transaction = mTransactionStack;
        if(transaction.mMarkedSuccessful || flag)
            flag1 = transaction.mChildFailed ^ true;
        else
            flag1 = false;
        obj = null;
        sqlitetransactionlistener = transaction.mListener;
        runtimeexception = obj;
        flag2 = flag1;
        if(sqlitetransactionlistener == null) goto _L2; else goto _L1
_L1:
        if(!flag1) goto _L4; else goto _L3
_L3:
        try
        {
            sqlitetransactionlistener.onCommit();
        }
        // Misplaced declaration of an exception variable
        catch(RuntimeException runtimeexception)
        {
            flag2 = false;
            continue; /* Loop/switch isn't completed */
        }
        flag2 = flag1;
        runtimeexception = obj;
_L2:
        mTransactionStack = transaction.mParent;
        recycleTransaction(transaction);
        if(mTransactionStack == null) goto _L6; else goto _L5
_L5:
        if(!flag2)
            mTransactionStack.mChildFailed = true;
_L7:
        if(runtimeexception != null)
            throw runtimeexception;
        else
            return;
_L4:
        sqlitetransactionlistener.onRollback();
        runtimeexception = obj;
        flag2 = flag1;
        continue; /* Loop/switch isn't completed */
_L6:
        if(!flag2)
            break MISSING_BLOCK_LABEL_170;
        mConnection.execute("COMMIT;", null, cancellationsignal);
_L8:
        releaseConnection();
          goto _L7
        mConnection.execute("ROLLBACK;", null, cancellationsignal);
          goto _L8
        cancellationsignal;
        releaseConnection();
        throw cancellationsignal;
        if(true) goto _L2; else goto _L9
_L9:
    }

    private boolean executeSpecial(String s, Object aobj[], int i, CancellationSignal cancellationsignal)
    {
        if(cancellationsignal != null)
            cancellationsignal.throwIfCanceled();
        switch(DatabaseUtils.getSqlStatementType(s))
        {
        default:
            return false;

        case 4: // '\004'
            beginTransaction(2, null, i, cancellationsignal);
            return true;

        case 5: // '\005'
            setTransactionSuccessful();
            endTransaction(cancellationsignal);
            return true;

        case 6: // '\006'
            endTransaction(cancellationsignal);
            break;
        }
        return true;
    }

    private Transaction obtainTransaction(int i, SQLiteTransactionListener sqlitetransactionlistener)
    {
        Transaction transaction = mTransactionPool;
        if(transaction != null)
        {
            mTransactionPool = transaction.mParent;
            transaction.mParent = null;
            transaction.mMarkedSuccessful = false;
            transaction.mChildFailed = false;
        } else
        {
            transaction = new Transaction(null);
        }
        transaction.mMode = i;
        transaction.mListener = sqlitetransactionlistener;
        return transaction;
    }

    private void recycleTransaction(Transaction transaction)
    {
        transaction.mParent = mTransactionPool;
        transaction.mListener = null;
        mTransactionPool = transaction;
    }

    private void releaseConnection()
    {
        if(!_2D_assertionsDisabled && mConnection == null)
            throw new AssertionError();
        if(!_2D_assertionsDisabled && mConnectionUseCount <= 0)
            throw new AssertionError();
        int i = mConnectionUseCount - 1;
        mConnectionUseCount = i;
        if(i != 0)
            break MISSING_BLOCK_LABEL_74;
        mConnectionPool.releaseConnection(mConnection);
        mConnection = null;
        return;
        Exception exception;
        exception;
        mConnection = null;
        throw exception;
    }

    private void throwIfNestedTransaction()
    {
        if(hasNestedTransaction())
            throw new IllegalStateException("Cannot perform this operation because a nested transaction is in progress.");
        else
            return;
    }

    private void throwIfNoTransaction()
    {
        if(mTransactionStack == null)
            throw new IllegalStateException("Cannot perform this operation because there is no current transaction.");
        else
            return;
    }

    private void throwIfTransactionMarkedSuccessful()
    {
        if(mTransactionStack != null && mTransactionStack.mMarkedSuccessful)
            throw new IllegalStateException("Cannot perform this operation because the transaction has already been marked successful.  The only thing you can do now is call endTransaction().");
        else
            return;
    }

    private boolean yieldTransactionUnchecked(long l, CancellationSignal cancellationsignal)
    {
        if(cancellationsignal != null)
            cancellationsignal.throwIfCanceled();
        if(!mConnectionPool.shouldYieldConnection(mConnection, mConnectionFlags))
            return false;
        int i = mTransactionStack.mMode;
        SQLiteTransactionListener sqlitetransactionlistener = mTransactionStack.mListener;
        int j = mConnectionFlags;
        endTransactionUnchecked(cancellationsignal, true);
        if(l > 0L)
            try
            {
                Thread.sleep(l);
            }
            catch(InterruptedException interruptedexception) { }
        beginTransactionUnchecked(i, sqlitetransactionlistener, j, cancellationsignal);
        return true;
    }

    public void beginTransaction(int i, SQLiteTransactionListener sqlitetransactionlistener, int j, CancellationSignal cancellationsignal)
    {
        throwIfTransactionMarkedSuccessful();
        beginTransactionUnchecked(i, sqlitetransactionlistener, j, cancellationsignal);
    }

    public void endTransaction(CancellationSignal cancellationsignal)
    {
        throwIfNoTransaction();
        if(!_2D_assertionsDisabled && mConnection == null)
        {
            throw new AssertionError();
        } else
        {
            endTransactionUnchecked(cancellationsignal, false);
            return;
        }
    }

    public void execute(String s, Object aobj[], int i, CancellationSignal cancellationsignal)
    {
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(executeSpecial(s, aobj, i, cancellationsignal))
            return;
        acquireConnection(s, i, cancellationsignal);
        mConnection.execute(s, aobj, cancellationsignal);
        releaseConnection();
        return;
        s;
        releaseConnection();
        throw s;
    }

    public ParcelFileDescriptor executeForBlobFileDescriptor(String s, Object aobj[], int i, CancellationSignal cancellationsignal)
    {
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(executeSpecial(s, aobj, i, cancellationsignal))
            return null;
        acquireConnection(s, i, cancellationsignal);
        s = mConnection.executeForBlobFileDescriptor(s, aobj, cancellationsignal);
        releaseConnection();
        return s;
        s;
        releaseConnection();
        throw s;
    }

    public int executeForChangedRowCount(String s, Object aobj[], int i, CancellationSignal cancellationsignal)
    {
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(executeSpecial(s, aobj, i, cancellationsignal))
            return 0;
        acquireConnection(s, i, cancellationsignal);
        i = mConnection.executeForChangedRowCount(s, aobj, cancellationsignal);
        releaseConnection();
        return i;
        s;
        releaseConnection();
        throw s;
    }

    public int executeForCursorWindow(String s, Object aobj[], CursorWindow cursorwindow, int i, int j, boolean flag, int k, 
            CancellationSignal cancellationsignal)
    {
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(cursorwindow == null)
            throw new IllegalArgumentException("window must not be null.");
        if(executeSpecial(s, aobj, k, cancellationsignal))
        {
            cursorwindow.clear();
            return 0;
        }
        acquireConnection(s, k, cancellationsignal);
        i = mConnection.executeForCursorWindow(s, aobj, cursorwindow, i, j, flag, cancellationsignal);
        releaseConnection();
        return i;
        s;
        releaseConnection();
        throw s;
    }

    public long executeForLastInsertedRowId(String s, Object aobj[], int i, CancellationSignal cancellationsignal)
    {
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(executeSpecial(s, aobj, i, cancellationsignal))
            return 0L;
        acquireConnection(s, i, cancellationsignal);
        long l = mConnection.executeForLastInsertedRowId(s, aobj, cancellationsignal);
        releaseConnection();
        return l;
        s;
        releaseConnection();
        throw s;
    }

    public long executeForLong(String s, Object aobj[], int i, CancellationSignal cancellationsignal)
    {
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(executeSpecial(s, aobj, i, cancellationsignal))
            return 0L;
        acquireConnection(s, i, cancellationsignal);
        long l = mConnection.executeForLong(s, aobj, cancellationsignal);
        releaseConnection();
        return l;
        s;
        releaseConnection();
        throw s;
    }

    public String executeForString(String s, Object aobj[], int i, CancellationSignal cancellationsignal)
    {
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(executeSpecial(s, aobj, i, cancellationsignal))
            return null;
        acquireConnection(s, i, cancellationsignal);
        s = mConnection.executeForString(s, aobj, cancellationsignal);
        releaseConnection();
        return s;
        s;
        releaseConnection();
        throw s;
    }

    public boolean hasConnection()
    {
        boolean flag;
        if(mConnection != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasNestedTransaction()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mTransactionStack != null)
        {
            flag1 = flag;
            if(mTransactionStack.mParent != null)
                flag1 = true;
        }
        return flag1;
    }

    public boolean hasTransaction()
    {
        boolean flag;
        if(mTransactionStack != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void prepare(String s, int i, CancellationSignal cancellationsignal, SQLiteStatementInfo sqlitestatementinfo)
    {
        if(s == null)
            throw new IllegalArgumentException("sql must not be null.");
        if(cancellationsignal != null)
            cancellationsignal.throwIfCanceled();
        acquireConnection(s, i, cancellationsignal);
        mConnection.prepare(s, sqlitestatementinfo);
        releaseConnection();
        return;
        s;
        releaseConnection();
        throw s;
    }

    public void setTransactionSuccessful()
    {
        throwIfNoTransaction();
        throwIfTransactionMarkedSuccessful();
        mTransactionStack.mMarkedSuccessful = true;
    }

    public boolean yieldTransaction(long l, boolean flag, CancellationSignal cancellationsignal)
    {
        if(flag)
        {
            throwIfNoTransaction();
            throwIfTransactionMarkedSuccessful();
            throwIfNestedTransaction();
        } else
        if(mTransactionStack == null || mTransactionStack.mMarkedSuccessful || mTransactionStack.mParent != null)
            return false;
        if(!_2D_assertionsDisabled && mConnection == null)
            throw new AssertionError();
        if(mTransactionStack.mChildFailed)
            return false;
        else
            return yieldTransactionUnchecked(l, cancellationsignal);
    }

    static final boolean _2D_assertionsDisabled = android/database/sqlite/SQLiteSession.desiredAssertionStatus() ^ true;
    public static final int TRANSACTION_MODE_DEFERRED = 0;
    public static final int TRANSACTION_MODE_EXCLUSIVE = 2;
    public static final int TRANSACTION_MODE_IMMEDIATE = 1;
    private SQLiteConnection mConnection;
    private int mConnectionFlags;
    private final SQLiteConnectionPool mConnectionPool;
    private int mConnectionUseCount;
    private Transaction mTransactionPool;
    private Transaction mTransactionStack;

}
