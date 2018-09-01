// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.os.*;
import android.util.*;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

// Referenced classes of package android.database.sqlite:
//            SQLiteDatabaseConfiguration, SQLiteConnection, SQLiteGlobal

public final class SQLiteConnectionPool
    implements Closeable
{
    static final class AcquiredConnectionStatus extends Enum
    {

        public static AcquiredConnectionStatus valueOf(String s)
        {
            return (AcquiredConnectionStatus)Enum.valueOf(android/database/sqlite/SQLiteConnectionPool$AcquiredConnectionStatus, s);
        }

        public static AcquiredConnectionStatus[] values()
        {
            return $VALUES;
        }

        private static final AcquiredConnectionStatus $VALUES[];
        public static final AcquiredConnectionStatus DISCARD;
        public static final AcquiredConnectionStatus NORMAL;
        public static final AcquiredConnectionStatus RECONFIGURE;

        static 
        {
            NORMAL = new AcquiredConnectionStatus("NORMAL", 0);
            RECONFIGURE = new AcquiredConnectionStatus("RECONFIGURE", 1);
            DISCARD = new AcquiredConnectionStatus("DISCARD", 2);
            $VALUES = (new AcquiredConnectionStatus[] {
                NORMAL, RECONFIGURE, DISCARD
            });
        }

        private AcquiredConnectionStatus(String s, int i)
        {
            super(s, i);
        }
    }

    private static final class ConnectionWaiter
    {

        public SQLiteConnection mAssignedConnection;
        public int mConnectionFlags;
        public RuntimeException mException;
        public ConnectionWaiter mNext;
        public int mNonce;
        public int mPriority;
        public String mSql;
        public long mStartTime;
        public Thread mThread;
        public boolean mWantPrimaryConnection;

        private ConnectionWaiter()
        {
        }

        ConnectionWaiter(ConnectionWaiter connectionwaiter)
        {
            this();
        }
    }

    private class IdleConnectionHandler extends Handler
    {

        void connectionAcquired(SQLiteConnection sqliteconnection)
        {
            removeMessages(sqliteconnection.getConnectionId());
        }

        void connectionClosed(SQLiteConnection sqliteconnection)
        {
            removeMessages(sqliteconnection.getConnectionId());
        }

        void connectionReleased(SQLiteConnection sqliteconnection)
        {
            sendEmptyMessageDelayed(sqliteconnection.getConnectionId(), mTimeout);
        }

        public void handleMessage(Message message)
        {
            Object obj = SQLiteConnectionPool._2D_get2(SQLiteConnectionPool.this);
            obj;
            JVM INSTR monitorenter ;
            IdleConnectionHandler idleconnectionhandler = SQLiteConnectionPool._2D_get1(SQLiteConnectionPool.this);
            if(this == idleconnectionhandler)
                break MISSING_BLOCK_LABEL_26;
            obj;
            JVM INSTR monitorexit ;
            return;
            if(SQLiteConnectionPool._2D_wrap0(SQLiteConnectionPool.this, message.what) && Log.isLoggable("SQLiteConnectionPool", 3))
            {
                StringBuilder stringbuilder = JVM INSTR new #69  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("SQLiteConnectionPool", stringbuilder.append("Closed idle connection ").append(SQLiteConnectionPool._2D_get0(SQLiteConnectionPool.this).label).append(" ").append(message.what).append(" after ").append(mTimeout).toString());
            }
            obj;
            JVM INSTR monitorexit ;
            return;
            message;
            throw message;
        }

        private final long mTimeout;
        final SQLiteConnectionPool this$0;

        IdleConnectionHandler(Looper looper, long l)
        {
            this$0 = SQLiteConnectionPool.this;
            super(looper);
            mTimeout = l;
        }
    }


    static SQLiteDatabaseConfiguration _2D_get0(SQLiteConnectionPool sqliteconnectionpool)
    {
        return sqliteconnectionpool.mConfiguration;
    }

    static IdleConnectionHandler _2D_get1(SQLiteConnectionPool sqliteconnectionpool)
    {
        return sqliteconnectionpool.mIdleConnectionHandler;
    }

    static Object _2D_get2(SQLiteConnectionPool sqliteconnectionpool)
    {
        return sqliteconnectionpool.mLock;
    }

    static boolean _2D_wrap0(SQLiteConnectionPool sqliteconnectionpool, int i)
    {
        return sqliteconnectionpool.closeAvailableConnectionLocked(i);
    }

    static void _2D_wrap1(SQLiteConnectionPool sqliteconnectionpool, ConnectionWaiter connectionwaiter)
    {
        sqliteconnectionpool.cancelConnectionWaiterLocked(connectionwaiter);
    }

    private SQLiteConnectionPool(SQLiteDatabaseConfiguration sqlitedatabaseconfiguration)
    {
        mConfiguration = new SQLiteDatabaseConfiguration(sqlitedatabaseconfiguration);
        setMaxConnectionPoolSizeLocked();
        if(mConfiguration.idleConnectionTimeoutMs != 0x7fffffffffffffffL)
            setupIdleConnectionHandler(Looper.getMainLooper(), mConfiguration.idleConnectionTimeoutMs);
    }

    private void cancelConnectionWaiterLocked(ConnectionWaiter connectionwaiter)
    {
        if(connectionwaiter.mAssignedConnection != null || connectionwaiter.mException != null)
            return;
        ConnectionWaiter connectionwaiter1 = null;
        for(ConnectionWaiter connectionwaiter2 = mConnectionWaiterQueue; connectionwaiter2 != connectionwaiter; connectionwaiter2 = connectionwaiter2.mNext)
        {
            if(!_2D_assertionsDisabled && connectionwaiter2 == null)
                throw new AssertionError();
            connectionwaiter1 = connectionwaiter2;
        }

        if(connectionwaiter1 != null)
            connectionwaiter1.mNext = connectionwaiter.mNext;
        else
            mConnectionWaiterQueue = connectionwaiter.mNext;
        connectionwaiter.mException = new OperationCanceledException();
        LockSupport.unpark(connectionwaiter.mThread);
        wakeConnectionWaitersLocked();
    }

    private boolean closeAvailableConnectionLocked(int i)
    {
        for(int j = mAvailableNonPrimaryConnections.size() - 1; j >= 0; j--)
        {
            SQLiteConnection sqliteconnection = (SQLiteConnection)mAvailableNonPrimaryConnections.get(j);
            if(sqliteconnection.getConnectionId() == i)
            {
                closeConnectionAndLogExceptionsLocked(sqliteconnection);
                mAvailableNonPrimaryConnections.remove(j);
                return true;
            }
        }

        if(mAvailablePrimaryConnection != null && mAvailablePrimaryConnection.getConnectionId() == i)
        {
            closeConnectionAndLogExceptionsLocked(mAvailablePrimaryConnection);
            mAvailablePrimaryConnection = null;
            return true;
        } else
        {
            return false;
        }
    }

    private void closeAvailableConnectionsAndLogExceptionsLocked()
    {
        closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked();
        if(mAvailablePrimaryConnection != null)
        {
            closeConnectionAndLogExceptionsLocked(mAvailablePrimaryConnection);
            mAvailablePrimaryConnection = null;
        }
    }

    private void closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked()
    {
        int i = mAvailableNonPrimaryConnections.size();
        for(int j = 0; j < i; j++)
            closeConnectionAndLogExceptionsLocked((SQLiteConnection)mAvailableNonPrimaryConnections.get(j));

        mAvailableNonPrimaryConnections.clear();
    }

    private void closeConnectionAndLogExceptionsLocked(SQLiteConnection sqliteconnection)
    {
        sqliteconnection.close();
        if(mIdleConnectionHandler != null)
            mIdleConnectionHandler.connectionClosed(sqliteconnection);
_L1:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        Log.e("SQLiteConnectionPool", (new StringBuilder()).append("Failed to close connection, its fate is now in the hands of the merciful GC: ").append(sqliteconnection).toString(), runtimeexception);
          goto _L1
    }

    private void closeExcessConnectionsAndLogExceptionsLocked()
    {
        int i = mAvailableNonPrimaryConnections.size();
        do
        {
            int j = i - 1;
            if(i > mMaxConnectionPoolSize - 1)
            {
                closeConnectionAndLogExceptionsLocked((SQLiteConnection)mAvailableNonPrimaryConnections.remove(j));
                i = j;
            } else
            {
                return;
            }
        } while(true);
    }

    private void discardAcquiredConnectionsLocked()
    {
        markAcquiredConnectionsLocked(AcquiredConnectionStatus.DISCARD);
    }

    private void dispose(boolean flag)
    {
        if(mCloseGuard != null)
        {
            if(flag)
                mCloseGuard.warnIfOpen();
            mCloseGuard.close();
        }
        if(flag) goto _L2; else goto _L1
_L1:
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        throwIfClosedLocked();
        mIsOpen = false;
        closeAvailableConnectionsAndLogExceptionsLocked();
        i = mAcquiredConnections.size();
        if(i == 0)
            break MISSING_BLOCK_LABEL_120;
        StringBuilder stringbuilder = JVM INSTR new #213 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("SQLiteConnectionPool", stringbuilder.append("The connection pool for ").append(mConfiguration.label).append(" has been closed but there are still ").append(i).append(" connections in use.  They will be closed ").append("as they are released back to the pool.").toString());
        wakeConnectionWaitersLocked();
        obj;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void finishAcquireConnectionLocked(SQLiteConnection sqliteconnection, int i)
    {
        boolean flag;
        if((i & 1) != 0)
            flag = true;
        else
            flag = false;
        try
        {
            sqliteconnection.setOnlyAllowReadOnlyOperations(flag);
            mAcquiredConnections.put(sqliteconnection, AcquiredConnectionStatus.NORMAL);
            return;
        }
        catch(RuntimeException runtimeexception)
        {
            Log.e("SQLiteConnectionPool", (new StringBuilder()).append("Failed to prepare acquired connection for session, closing it: ").append(sqliteconnection).append(", connectionFlags=").append(i).toString());
            closeConnectionAndLogExceptionsLocked(sqliteconnection);
            throw runtimeexception;
        }
    }

    private static int getPriority(int i)
    {
        int j = 0;
        if((i & 4) != 0)
            j = 1;
        return j;
    }

    private boolean isSessionBlockingImportantConnectionWaitersLocked(boolean flag, int i)
    {
        ConnectionWaiter connectionwaiter = mConnectionWaiterQueue;
        if(connectionwaiter == null) goto _L2; else goto _L1
_L1:
        i = getPriority(i);
_L4:
        if(i <= connectionwaiter.mPriority) goto _L3; else goto _L2
_L2:
        return false;
_L3:
        if(flag || connectionwaiter.mWantPrimaryConnection ^ true)
            return true;
        connectionwaiter = connectionwaiter.mNext;
        if(connectionwaiter == null) goto _L2; else goto _L4
    }

    private void logConnectionPoolBusyLocked(long l, int i)
    {
        Thread thread = Thread.currentThread();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("The connection pool for database '").append(mConfiguration.label);
        stringbuilder.append("' has been unable to grant a connection to thread ");
        stringbuilder.append(thread.getId()).append(" (").append(thread.getName()).append(") ");
        stringbuilder.append("with flags 0x").append(Integer.toHexString(i));
        stringbuilder.append(" for ").append((float)l * 0.001F).append(" seconds.\n");
        ArrayList arraylist = new ArrayList();
        int j = 0;
        int k = 0;
        int i1 = 0;
        i = 0;
        if(!mAcquiredConnections.isEmpty())
        {
            Iterator iterator = mAcquiredConnections.keySet().iterator();
            do
            {
                j = k;
                i1 = i;
                if(!iterator.hasNext())
                    break;
                String s = ((SQLiteConnection)iterator.next()).describeCurrentOperationUnsafe();
                if(s != null)
                {
                    arraylist.add(s);
                    k++;
                } else
                {
                    i++;
                }
            } while(true);
        }
        k = mAvailableNonPrimaryConnections.size();
        i = k;
        if(mAvailablePrimaryConnection != null)
            i = k + 1;
        stringbuilder.append("Connections: ").append(j).append(" active, ");
        stringbuilder.append(i1).append(" idle, ");
        stringbuilder.append(i).append(" available.\n");
        if(!arraylist.isEmpty())
        {
            stringbuilder.append("\nRequests in progress:\n");
            String s1;
            for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); stringbuilder.append("  ").append(s1).append("\n"))
                s1 = (String)iterator1.next();

        }
        Log.w("SQLiteConnectionPool", stringbuilder.toString());
    }

    private void markAcquiredConnectionsLocked(AcquiredConnectionStatus acquiredconnectionstatus)
    {
        if(!mAcquiredConnections.isEmpty())
        {
            ArrayList arraylist = new ArrayList(mAcquiredConnections.size());
            Iterator iterator = mAcquiredConnections.entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                AcquiredConnectionStatus acquiredconnectionstatus1 = (AcquiredConnectionStatus)entry.getValue();
                if(acquiredconnectionstatus != acquiredconnectionstatus1 && acquiredconnectionstatus1 != AcquiredConnectionStatus.DISCARD)
                    arraylist.add((SQLiteConnection)entry.getKey());
            } while(true);
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                mAcquiredConnections.put((SQLiteConnection)arraylist.get(j), acquiredconnectionstatus);

        }
    }

    private ConnectionWaiter obtainConnectionWaiterLocked(Thread thread, long l, int i, boolean flag, String s, int j)
    {
        ConnectionWaiter connectionwaiter = mConnectionWaiterPool;
        if(connectionwaiter != null)
        {
            mConnectionWaiterPool = connectionwaiter.mNext;
            connectionwaiter.mNext = null;
        } else
        {
            connectionwaiter = new ConnectionWaiter(null);
        }
        connectionwaiter.mThread = thread;
        connectionwaiter.mStartTime = l;
        connectionwaiter.mPriority = i;
        connectionwaiter.mWantPrimaryConnection = flag;
        connectionwaiter.mSql = s;
        connectionwaiter.mConnectionFlags = j;
        return connectionwaiter;
    }

    public static SQLiteConnectionPool open(SQLiteDatabaseConfiguration sqlitedatabaseconfiguration)
    {
        if(sqlitedatabaseconfiguration == null)
        {
            throw new IllegalArgumentException("configuration must not be null.");
        } else
        {
            sqlitedatabaseconfiguration = new SQLiteConnectionPool(sqlitedatabaseconfiguration);
            sqlitedatabaseconfiguration.open();
            return sqlitedatabaseconfiguration;
        }
    }

    private void open()
    {
        mAvailablePrimaryConnection = openConnectionLocked(mConfiguration, true);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mIdleConnectionHandler != null)
            mIdleConnectionHandler.connectionReleased(mAvailablePrimaryConnection);
        obj;
        JVM INSTR monitorexit ;
        mIsOpen = true;
        mCloseGuard.open("close");
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private SQLiteConnection openConnectionLocked(SQLiteDatabaseConfiguration sqlitedatabaseconfiguration, boolean flag)
    {
        int i = mNextConnectionId;
        mNextConnectionId = i + 1;
        return SQLiteConnection.open(this, sqlitedatabaseconfiguration, i, flag);
    }

    private void reconfigureAllConnectionsLocked()
    {
        int i;
        int j;
        if(mAvailablePrimaryConnection != null)
            try
            {
                mAvailablePrimaryConnection.reconfigure(mConfiguration);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("SQLiteConnectionPool", (new StringBuilder()).append("Failed to reconfigure available primary connection, closing it: ").append(mAvailablePrimaryConnection).toString(), ((Throwable) (obj)));
                closeConnectionAndLogExceptionsLocked(mAvailablePrimaryConnection);
                mAvailablePrimaryConnection = null;
            }
        i = mAvailableNonPrimaryConnections.size();
        j = 0;
        do
        {
            if(j >= i)
                break;
            Object obj = (SQLiteConnection)mAvailableNonPrimaryConnections.get(j);
            try
            {
                ((SQLiteConnection) (obj)).reconfigure(mConfiguration);
            }
            catch(RuntimeException runtimeexception)
            {
                Log.e("SQLiteConnectionPool", (new StringBuilder()).append("Failed to reconfigure available non-primary connection, closing it: ").append(obj).toString(), runtimeexception);
                closeConnectionAndLogExceptionsLocked(((SQLiteConnection) (obj)));
                obj = mAvailableNonPrimaryConnections;
                int k = j - 1;
                ((ArrayList) (obj)).remove(j);
                i--;
                j = k;
            }
            j++;
        } while(true);
        markAcquiredConnectionsLocked(AcquiredConnectionStatus.RECONFIGURE);
    }

    private boolean recycleConnectionLocked(SQLiteConnection sqliteconnection, AcquiredConnectionStatus acquiredconnectionstatus)
    {
        AcquiredConnectionStatus acquiredconnectionstatus1;
        acquiredconnectionstatus1 = acquiredconnectionstatus;
        if(acquiredconnectionstatus != AcquiredConnectionStatus.RECONFIGURE)
            break MISSING_BLOCK_LABEL_19;
        sqliteconnection.reconfigure(mConfiguration);
        acquiredconnectionstatus1 = acquiredconnectionstatus;
_L1:
        if(acquiredconnectionstatus1 == AcquiredConnectionStatus.DISCARD)
        {
            closeConnectionAndLogExceptionsLocked(sqliteconnection);
            return false;
        } else
        {
            return true;
        }
        acquiredconnectionstatus;
        Log.e("SQLiteConnectionPool", (new StringBuilder()).append("Failed to reconfigure released connection, closing it: ").append(sqliteconnection).toString(), acquiredconnectionstatus);
        acquiredconnectionstatus1 = AcquiredConnectionStatus.DISCARD;
          goto _L1
    }

    private void recycleConnectionWaiterLocked(ConnectionWaiter connectionwaiter)
    {
        connectionwaiter.mNext = mConnectionWaiterPool;
        connectionwaiter.mThread = null;
        connectionwaiter.mSql = null;
        connectionwaiter.mAssignedConnection = null;
        connectionwaiter.mException = null;
        connectionwaiter.mNonce = connectionwaiter.mNonce + 1;
        mConnectionWaiterPool = connectionwaiter;
    }

    private void setMaxConnectionPoolSizeLocked()
    {
        if(!mConfiguration.isInMemoryDb() && (mConfiguration.openFlags & 0x20000000) != 0)
            mMaxConnectionPoolSize = SQLiteGlobal.getWALConnectionPoolSize();
        else
            mMaxConnectionPoolSize = 1;
    }

    private void throwIfClosedLocked()
    {
        if(!mIsOpen)
            throw new IllegalStateException("Cannot perform this operation because the connection pool has been closed.");
        else
            return;
    }

    private SQLiteConnection tryAcquireNonPrimaryConnectionLocked(String s, int i)
    {
        int j = mAvailableNonPrimaryConnections.size();
        if(j > 1 && s != null)
        {
            for(int k = 0; k < j; k++)
            {
                SQLiteConnection sqliteconnection = (SQLiteConnection)mAvailableNonPrimaryConnections.get(k);
                if(sqliteconnection.isPreparedStatementInCache(s))
                {
                    mAvailableNonPrimaryConnections.remove(k);
                    finishAcquireConnectionLocked(sqliteconnection, i);
                    return sqliteconnection;
                }
            }

        }
        if(j > 0)
        {
            s = (SQLiteConnection)mAvailableNonPrimaryConnections.remove(j - 1);
            finishAcquireConnectionLocked(s, i);
            return s;
        }
        j = mAcquiredConnections.size();
        int l = j;
        if(mAvailablePrimaryConnection != null)
            l = j + 1;
        if(l >= mMaxConnectionPoolSize)
        {
            return null;
        } else
        {
            s = openConnectionLocked(mConfiguration, false);
            finishAcquireConnectionLocked(s, i);
            return s;
        }
    }

    private SQLiteConnection tryAcquirePrimaryConnectionLocked(int i)
    {
        Object obj = mAvailablePrimaryConnection;
        if(obj != null)
        {
            mAvailablePrimaryConnection = null;
            finishAcquireConnectionLocked(((SQLiteConnection) (obj)), i);
            return ((SQLiteConnection) (obj));
        }
        for(obj = mAcquiredConnections.keySet().iterator(); ((Iterator) (obj)).hasNext();)
            if(((SQLiteConnection)((Iterator) (obj)).next()).isPrimaryConnection())
                return null;

        obj = openConnectionLocked(mConfiguration, true);
        finishAcquireConnectionLocked(((SQLiteConnection) (obj)), i);
        return ((SQLiteConnection) (obj));
    }

    private SQLiteConnection waitForConnection(String s, int i, CancellationSignal cancellationsignal)
    {
        boolean flag;
        Object obj;
        Object obj1;
        final ConnectionWaiter waiter;
        if((i & 2) != 0)
            flag = true;
        else
            flag = false;
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfClosedLocked();
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_30;
        cancellationsignal.throwIfCanceled();
        obj1 = null;
        if(flag)
            break MISSING_BLOCK_LABEL_46;
        obj1 = tryAcquireNonPrimaryConnectionLocked(s, i);
        waiter = ((ConnectionWaiter) (obj1));
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_62;
        waiter = tryAcquirePrimaryConnectionLocked(i);
        if(waiter == null)
            break MISSING_BLOCK_LABEL_79;
        obj;
        JVM INSTR monitorexit ;
        return waiter;
        final int nonce;
        nonce = getPriority(i);
        long l = SystemClock.uptimeMillis();
        waiter = obtainConnectionWaiterLocked(Thread.currentThread(), l, nonce, flag, s, i);
        obj1 = null;
        s = mConnectionWaiterQueue;
_L13:
        if(s == null) goto _L2; else goto _L1
_L1:
        if(nonce <= ((ConnectionWaiter) (s)).mPriority) goto _L4; else goto _L3
_L3:
        waiter.mNext = s;
_L2:
        if(obj1 == null) goto _L6; else goto _L5
_L5:
        obj1.mNext = waiter;
_L14:
        nonce = waiter.mNonce;
        obj;
        JVM INSTR monitorexit ;
        long l2;
        if(cancellationsignal != null)
            cancellationsignal.setOnCancelListener(new android.os.CancellationSignal.OnCancelListener() {

                public void onCancel()
                {
                    Object obj2 = SQLiteConnectionPool._2D_get2(SQLiteConnectionPool.this);
                    obj2;
                    JVM INSTR monitorenter ;
                    if(waiter.mNonce == nonce)
                        SQLiteConnectionPool._2D_wrap1(SQLiteConnectionPool.this, waiter);
                    obj2;
                    JVM INSTR monitorexit ;
                    return;
                    Exception exception1;
                    exception1;
                    throw exception1;
                }

                final SQLiteConnectionPool this$0;
                final int val$nonce;
                final ConnectionWaiter val$waiter;

            
            {
                this$0 = SQLiteConnectionPool.this;
                waiter = connectionwaiter;
                nonce = i;
                super();
            }
            }
);
        l2 = 30000L;
        long l1 = waiter.mStartTime + 30000L;
_L17:
        if(!mConnectionLeaked.compareAndSet(true, false)) goto _L8; else goto _L7
_L7:
        s = ((String) (mLock));
        s;
        JVM INSTR monitorenter ;
        wakeConnectionWaitersLocked();
        s;
        JVM INSTR monitorexit ;
_L8:
        LockSupport.parkNanos(this, 0xf4240L * l2);
        Thread.interrupted();
        s = ((String) (mLock));
        s;
        JVM INSTR monitorenter ;
        throwIfClosedLocked();
        obj = waiter.mAssignedConnection;
        obj1 = waiter.mException;
        if(obj == null && obj1 == null) goto _L10; else goto _L9
_L9:
        recycleConnectionWaiterLocked(waiter);
        if(obj == null) goto _L12; else goto _L11
_L11:
        s;
        JVM INSTR monitorexit ;
        if(cancellationsignal != null)
            cancellationsignal.setOnCancelListener(null);
        return ((SQLiteConnection) (obj));
_L4:
        obj1 = s;
        s = ((ConnectionWaiter) (s)).mNext;
          goto _L13
_L6:
        mConnectionWaiterQueue = waiter;
          goto _L14
        s;
        throw s;
        obj1;
        s;
        JVM INSTR monitorexit ;
        throw obj1;
        s;
        if(cancellationsignal != null)
            cancellationsignal.setOnCancelListener(null);
        throw s;
_L12:
        throw obj1;
        Exception exception;
        exception;
        s;
        JVM INSTR monitorexit ;
        throw exception;
_L10:
        long l3 = SystemClock.uptimeMillis();
        if(l3 >= l1) goto _L16; else goto _L15
_L15:
        l2 = l3 - l1;
        l3 = l1;
        l1 = l2;
_L18:
        s;
        JVM INSTR monitorexit ;
        l2 = l1;
        l1 = l3;
          goto _L17
_L16:
        logConnectionPoolBusyLocked(l3 - waiter.mStartTime, i);
        l1 = 30000L;
        l3 += 30000L;
          goto _L18
    }

    private void wakeConnectionWaitersLocked()
    {
        Object obj;
        Object obj1;
        boolean flag;
        boolean flag1;
        obj = null;
        obj1 = mConnectionWaiterQueue;
        flag = false;
        flag1 = false;
_L5:
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        boolean flag2 = false;
        if(mIsOpen) goto _L4; else goto _L3
_L3:
        boolean flag3;
        boolean flag4;
        flag3 = true;
        flag4 = flag1;
_L6:
        Object obj2 = ((ConnectionWaiter) (obj1)).mNext;
        if(flag3)
        {
            Object obj3;
            boolean flag5;
            if(obj != null)
                obj.mNext = ((ConnectionWaiter) (obj2));
            else
                mConnectionWaiterQueue = ((ConnectionWaiter) (obj2));
            obj1.mNext = null;
            LockSupport.unpark(((ConnectionWaiter) (obj1)).mThread);
        } else
        {
            obj = obj1;
        }
        obj1 = obj2;
        flag1 = flag4;
          goto _L5
_L4:
        obj3 = null;
        obj2 = obj3;
        flag5 = flag1;
        flag4 = flag1;
        flag3 = flag;
        if(((ConnectionWaiter) (obj1)).mWantPrimaryConnection)
            break MISSING_BLOCK_LABEL_157;
        obj2 = obj3;
        flag5 = flag1;
        if(!(flag1 ^ true))
            break MISSING_BLOCK_LABEL_157;
        flag4 = flag1;
        flag3 = flag;
        obj3 = tryAcquireNonPrimaryConnectionLocked(((ConnectionWaiter) (obj1)).mSql, ((ConnectionWaiter) (obj1)).mConnectionFlags);
        obj2 = obj3;
        flag5 = flag1;
        if(obj3 == null)
        {
            flag5 = true;
            obj2 = obj3;
        }
        obj3 = obj2;
        flag1 = flag;
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_218;
        obj3 = obj2;
        flag1 = flag;
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_218;
        flag4 = flag5;
        flag3 = flag;
        obj2 = tryAcquirePrimaryConnectionLocked(((ConnectionWaiter) (obj1)).mConnectionFlags);
        obj3 = obj2;
        flag1 = flag;
        if(obj2 == null)
        {
            flag1 = true;
            obj3 = obj2;
        }
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_250;
        flag4 = flag5;
        flag3 = flag1;
        obj1.mAssignedConnection = ((SQLiteConnection) (obj3));
        flag3 = true;
        flag4 = flag5;
        flag = flag1;
          goto _L6
        flag4 = flag5;
        flag = flag1;
        flag3 = flag2;
        if(!flag5) goto _L6; else goto _L7
_L7:
        flag4 = flag5;
        flag = flag1;
        flag3 = flag2;
        if(!flag1) goto _L6; else goto _L2
_L2:
        return;
        obj2;
        obj1.mException = ((RuntimeException) (obj2));
        flag5 = true;
        flag = flag3;
        flag3 = flag5;
          goto _L6
    }

    public SQLiteConnection acquireConnection(String s, int i, CancellationSignal cancellationsignal)
    {
        cancellationsignal = waitForConnection(s, i, cancellationsignal);
        s = ((String) (mLock));
        s;
        JVM INSTR monitorenter ;
        if(mIdleConnectionHandler != null)
            mIdleConnectionHandler.connectionAcquired(cancellationsignal);
        s;
        JVM INSTR monitorexit ;
        return cancellationsignal;
        cancellationsignal;
        throw cancellationsignal;
    }

    public void close()
    {
        dispose(false);
    }

    public void collectDbStats(ArrayList arraylist)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mAvailablePrimaryConnection != null)
            mAvailablePrimaryConnection.collectDbStats(arraylist);
        for(Iterator iterator = mAvailableNonPrimaryConnections.iterator(); iterator.hasNext(); ((SQLiteConnection)iterator.next()).collectDbStats(arraylist));
        break MISSING_BLOCK_LABEL_62;
        arraylist;
        throw arraylist;
        for(Iterator iterator1 = mAcquiredConnections.keySet().iterator(); iterator1.hasNext(); ((SQLiteConnection)iterator1.next()).collectDbStatsUnsafe(arraylist));
        obj;
        JVM INSTR monitorexit ;
    }

    void disableIdleConnectionHandler()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mIdleConnectionHandler = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void dump(Printer printer, boolean flag)
    {
        Printer printer1 = PrefixPrinter.create(printer, "    ");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        StringBuilder stringbuilder = JVM INSTR new #213 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        printer.println(stringbuilder.append("Connection pool for ").append(mConfiguration.path).append(":").toString());
        stringbuilder = JVM INSTR new #213 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        printer.println(stringbuilder.append("  Open: ").append(mIsOpen).toString());
        stringbuilder = JVM INSTR new #213 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        printer.println(stringbuilder.append("  Max connections: ").append(mMaxConnectionPoolSize).toString());
        if(mConfiguration.isLookasideConfigSet())
        {
            StringBuilder stringbuilder1 = JVM INSTR new #213 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            printer.println(stringbuilder1.append("  Lookaside config: sz=").append(mConfiguration.lookasideSlotSize).append(" cnt=").append(mConfiguration.lookasideSlotCount).toString());
        }
        if(mConfiguration.idleConnectionTimeoutMs != 0x7fffffffffffffffL)
        {
            StringBuilder stringbuilder2 = JVM INSTR new #213 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            printer.println(stringbuilder2.append("  Idle connection timeout: ").append(mConfiguration.idleConnectionTimeoutMs).toString());
        }
        printer.println("  Available primary connection:");
        if(mAvailablePrimaryConnection == null) goto _L2; else goto _L1
_L1:
        mAvailablePrimaryConnection.dump(printer1, flag);
_L7:
        printer.println("  Available non-primary connections:");
        if(mAvailableNonPrimaryConnections.isEmpty()) goto _L4; else goto _L3
_L3:
        int i = mAvailableNonPrimaryConnections.size();
        int j = 0;
_L6:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((SQLiteConnection)mAvailableNonPrimaryConnections.get(j)).dump(printer1, flag);
        j++;
        if(true) goto _L6; else goto _L5
_L2:
        printer1.println("<none>");
          goto _L7
        printer;
        throw printer;
_L4:
        printer1.println("<none>");
_L5:
        printer.println("  Acquired connections:");
        if(!mAcquiredConnections.isEmpty())
        {
            StringBuilder stringbuilder3;
            java.util.Map.Entry entry;
            for(Iterator iterator = mAcquiredConnections.entrySet().iterator(); iterator.hasNext(); printer1.println(stringbuilder3.append("  Status: ").append(entry.getValue()).toString()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                ((SQLiteConnection)entry.getKey()).dumpUnsafe(printer1, flag);
                stringbuilder3 = JVM INSTR new #213 <Class StringBuilder>;
                stringbuilder3.StringBuilder();
            }

            break MISSING_BLOCK_LABEL_474;
        }
        printer1.println("<none>");
        printer.println("  Connection waiters:");
        if(mConnectionWaiterQueue == null) goto _L9; else goto _L8
_L8:
        j = 0;
        long l;
        l = SystemClock.uptimeMillis();
        printer = mConnectionWaiterQueue;
_L11:
        if(printer == null)
            break; /* Loop/switch isn't completed */
        StringBuilder stringbuilder4 = JVM INSTR new #213 <Class StringBuilder>;
        stringbuilder4.StringBuilder();
        printer1.println(stringbuilder4.append(j).append(": waited for ").append((float)(l - ((ConnectionWaiter) (printer)).mStartTime) * 0.001F).append(" ms - thread=").append(((ConnectionWaiter) (printer)).mThread).append(", priority=").append(((ConnectionWaiter) (printer)).mPriority).append(", sql='").append(((ConnectionWaiter) (printer)).mSql).append("'").toString());
        printer = ((ConnectionWaiter) (printer)).mNext;
        j++;
        if(true) goto _L11; else goto _L10
_L9:
        printer1.println("<none>");
_L10:
        obj;
        JVM INSTR monitorexit ;
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

    void onConnectionLeaked()
    {
        Log.w("SQLiteConnectionPool", (new StringBuilder()).append("A SQLiteConnection object for database '").append(mConfiguration.label).append("' was leaked!  Please fix your application ").append("to end transactions in progress properly and to close the database ").append("when it is no longer needed.").toString());
        mConnectionLeaked.set(true);
    }

    public void reconfigure(SQLiteDatabaseConfiguration sqlitedatabaseconfiguration)
    {
        if(sqlitedatabaseconfiguration == null)
            throw new IllegalArgumentException("configuration must not be null.");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfClosedLocked();
        boolean flag;
        if(((sqlitedatabaseconfiguration.openFlags ^ mConfiguration.openFlags) & 0x20000000) != 0)
            flag = true;
        else
            flag = false;
        if(!flag)
            break MISSING_BLOCK_LABEL_114;
        if(!mAcquiredConnections.isEmpty())
        {
            sqlitedatabaseconfiguration = JVM INSTR new #487 <Class IllegalStateException>;
            sqlitedatabaseconfiguration.IllegalStateException("Write Ahead Logging (WAL) mode cannot be enabled or disabled while there are transactions in progress.  Finish all transactions and release all active database connections first.");
            throw sqlitedatabaseconfiguration;
        }
        break MISSING_BLOCK_LABEL_84;
        sqlitedatabaseconfiguration;
        obj;
        JVM INSTR monitorexit ;
        throw sqlitedatabaseconfiguration;
        closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked();
        if(!_2D_assertionsDisabled && !mAvailableNonPrimaryConnections.isEmpty())
        {
            sqlitedatabaseconfiguration = JVM INSTR new #155 <Class AssertionError>;
            sqlitedatabaseconfiguration.AssertionError();
            throw sqlitedatabaseconfiguration;
        }
        boolean flag1;
        if(sqlitedatabaseconfiguration.foreignKeyConstraintsEnabled != mConfiguration.foreignKeyConstraintsEnabled)
            flag1 = true;
        else
            flag1 = false;
        if(!flag1)
            break MISSING_BLOCK_LABEL_165;
        if(!mAcquiredConnections.isEmpty())
        {
            sqlitedatabaseconfiguration = JVM INSTR new #487 <Class IllegalStateException>;
            sqlitedatabaseconfiguration.IllegalStateException("Foreign Key Constraints cannot be enabled or disabled while there are transactions in progress.  Finish all transactions and release all active database connections first.");
            throw sqlitedatabaseconfiguration;
        }
        if(mConfiguration.openFlags == sqlitedatabaseconfiguration.openFlags)
            break MISSING_BLOCK_LABEL_228;
        if(!flag)
            break MISSING_BLOCK_LABEL_187;
        closeAvailableConnectionsAndLogExceptionsLocked();
        SQLiteConnection sqliteconnection = openConnectionLocked(sqlitedatabaseconfiguration, true);
        closeAvailableConnectionsAndLogExceptionsLocked();
        discardAcquiredConnectionsLocked();
        mAvailablePrimaryConnection = sqliteconnection;
        mConfiguration.updateParametersFrom(sqlitedatabaseconfiguration);
        setMaxConnectionPoolSizeLocked();
_L1:
        wakeConnectionWaitersLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        mConfiguration.updateParametersFrom(sqlitedatabaseconfiguration);
        setMaxConnectionPoolSizeLocked();
        closeExcessConnectionsAndLogExceptionsLocked();
        reconfigureAllConnectionsLocked();
          goto _L1
    }

    public void releaseConnection(SQLiteConnection sqliteconnection)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        AcquiredConnectionStatus acquiredconnectionstatus;
        if(mIdleConnectionHandler != null)
            mIdleConnectionHandler.connectionReleased(sqliteconnection);
        acquiredconnectionstatus = (AcquiredConnectionStatus)mAcquiredConnections.remove(sqliteconnection);
        if(acquiredconnectionstatus != null)
            break MISSING_BLOCK_LABEL_56;
        sqliteconnection = JVM INSTR new #487 <Class IllegalStateException>;
        sqliteconnection.IllegalStateException("Cannot perform this operation because the specified connection was not acquired from this pool or has already been released.");
        throw sqliteconnection;
        sqliteconnection;
        obj;
        JVM INSTR monitorexit ;
        throw sqliteconnection;
        if(mIsOpen) goto _L2; else goto _L1
_L1:
        closeConnectionAndLogExceptionsLocked(sqliteconnection);
_L3:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
label0:
        {
            if(!sqliteconnection.isPrimaryConnection())
                break label0;
            if(recycleConnectionLocked(sqliteconnection, acquiredconnectionstatus))
            {
                if(!_2D_assertionsDisabled && mAvailablePrimaryConnection != null)
                {
                    sqliteconnection = JVM INSTR new #155 <Class AssertionError>;
                    sqliteconnection.AssertionError();
                    throw sqliteconnection;
                }
                mAvailablePrimaryConnection = sqliteconnection;
            }
            wakeConnectionWaitersLocked();
        }
          goto _L3
label1:
        {
            if(mAvailableNonPrimaryConnections.size() < mMaxConnectionPoolSize - 1)
                break label1;
            closeConnectionAndLogExceptionsLocked(sqliteconnection);
        }
          goto _L3
        if(recycleConnectionLocked(sqliteconnection, acquiredconnectionstatus))
            mAvailableNonPrimaryConnections.add(sqliteconnection);
        wakeConnectionWaitersLocked();
          goto _L3
    }

    public void setupIdleConnectionHandler(Looper looper, long l)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IdleConnectionHandler idleconnectionhandler = JVM INSTR new #16  <Class SQLiteConnectionPool$IdleConnectionHandler>;
        idleconnectionhandler.this. IdleConnectionHandler(looper, l);
        mIdleConnectionHandler = idleconnectionhandler;
        obj;
        JVM INSTR monitorexit ;
        return;
        looper;
        throw looper;
    }

    public boolean shouldYieldConnection(SQLiteConnection sqliteconnection, int i)
    {
        synchronized(mLock)
        {
            if(!mAcquiredConnections.containsKey(sqliteconnection))
            {
                sqliteconnection = JVM INSTR new #487 <Class IllegalStateException>;
                sqliteconnection.IllegalStateException("Cannot perform this operation because the specified connection was not acquired from this pool or has already been released.");
                throw sqliteconnection;
            }
            break MISSING_BLOCK_LABEL_36;
        }
        boolean flag = mIsOpen;
        if(flag)
            break MISSING_BLOCK_LABEL_51;
        obj;
        JVM INSTR monitorexit ;
        return false;
        flag = isSessionBlockingImportantConnectionWaitersLocked(sqliteconnection.isPrimaryConnection(), i);
        obj;
        JVM INSTR monitorexit ;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("SQLiteConnectionPool: ").append(mConfiguration.path).toString();
    }

    static final boolean _2D_assertionsDisabled = android/database/sqlite/SQLiteConnectionPool.desiredAssertionStatus() ^ true;
    public static final int CONNECTION_FLAG_INTERACTIVE = 4;
    public static final int CONNECTION_FLAG_PRIMARY_CONNECTION_AFFINITY = 2;
    public static final int CONNECTION_FLAG_READ_ONLY = 1;
    private static final long CONNECTION_POOL_BUSY_MILLIS = 30000L;
    private static final String TAG = "SQLiteConnectionPool";
    private final WeakHashMap mAcquiredConnections = new WeakHashMap();
    private final ArrayList mAvailableNonPrimaryConnections = new ArrayList();
    private SQLiteConnection mAvailablePrimaryConnection;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final SQLiteDatabaseConfiguration mConfiguration;
    private final AtomicBoolean mConnectionLeaked = new AtomicBoolean();
    private ConnectionWaiter mConnectionWaiterPool;
    private ConnectionWaiter mConnectionWaiterQueue;
    private IdleConnectionHandler mIdleConnectionHandler;
    private boolean mIsOpen;
    private final Object mLock = new Object();
    private int mMaxConnectionPoolSize;
    private int mNextConnectionId;

}
