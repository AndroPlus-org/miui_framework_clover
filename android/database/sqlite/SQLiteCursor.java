// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.database.*;
import android.os.StrictMode;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package android.database.sqlite:
//            DatabaseObjectNotClosedException, SQLiteQuery, SQLiteDatabase, SQLiteCursorDriver, 
//            SQLiteCursorInjector

public class SQLiteCursor extends AbstractWindowedCursor
{

    public SQLiteCursor(SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        mCount = -1;
        if(sqlitequery == null)
            throw new IllegalArgumentException("query object cannot be null");
        if(StrictMode.vmSqliteObjectLeaksEnabled())
            mStackTrace = (new DatabaseObjectNotClosedException()).fillInStackTrace();
        else
            mStackTrace = null;
        mDriver = sqlitecursordriver;
        mEditTable = s;
        mColumnNameMap = null;
        mQuery = sqlitequery;
        mColumns = sqlitequery.getColumnNames();
    }

    public SQLiteCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        this(sqlitecursordriver, s, sqlitequery);
    }

    private void fillWindow(int i)
    {
        clearOrCreateWindow(getDatabase().getPath());
        if(mCount != -1)
            break MISSING_BLOCK_LABEL_94;
        int j = DatabaseUtils.cursorPickFillWindowStartPosition(i, 0);
        mCount = mQuery.fillWindow(mWindow, j, i, true);
        mCursorWindowCapacity = mWindow.getNumRows();
        if(Log.isLoggable("SQLiteCursor", 3))
        {
            StringBuilder stringbuilder = JVM INSTR new #122 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("SQLiteCursor", stringbuilder.append("received count(*) from native_fill_window: ").append(mCount).toString());
        }
_L1:
        return;
        try
        {
            int k = DatabaseUtils.cursorPickFillWindowStartPosition(i, mCursorWindowCapacity);
            mQuery.fillWindow(mWindow, k, i, false);
        }
        catch(RuntimeException runtimeexception)
        {
            closeWindow();
            throw runtimeexception;
        }
          goto _L1
    }

    public void close()
    {
        super.close();
        this;
        JVM INSTR monitorenter ;
        mQuery.close();
        mDriver.cursorClosed();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void deactivate()
    {
        super.deactivate();
        mDriver.cursorDeactivated();
    }

    protected void finalize()
    {
        String s;
        int i;
        StringBuilder stringbuilder;
        if(mWindow == null)
            break MISSING_BLOCK_LABEL_112;
        if(mStackTrace == null)
            break MISSING_BLOCK_LABEL_108;
        s = mQuery.getSql();
        i = s.length();
        stringbuilder = JVM INSTR new #122 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder = stringbuilder.append("Finalizing a Cursor that has not been deactivated or closed. database = ").append(mQuery.getDatabase().getLabel()).append(", table = ").append(mEditTable).append(", query = ");
        int j;
        j = i;
        if(i > 1000)
            j = 1000;
        StrictMode.onSqliteObjectLeaked(stringbuilder.append(s.substring(0, j)).toString(), mStackTrace);
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getColumnIndex(String s)
    {
        if(mColumnNameMap == null)
        {
            String as[] = mColumns;
            int i = as.length;
            HashMap hashmap = new HashMap(i, 1.0F);
            for(int j = 0; j < i; j++)
                hashmap.put(as[j], Integer.valueOf(j));

            mColumnNameMap = hashmap;
        }
        int k = s.lastIndexOf('.');
        Object obj = s;
        if(k != -1)
        {
            obj = new Exception();
            Log.e("SQLiteCursor", (new StringBuilder()).append("requesting column name with table name -- ").append(s).toString(), ((Throwable) (obj)));
            obj = s.substring(k + 1);
        }
        s = (Integer)mColumnNameMap.get(obj);
        if(s != null)
            return s.intValue();
        else
            return -1;
    }

    public String[] getColumnNames()
    {
        return mColumns;
    }

    public int getCount()
    {
        if(mCount == -1)
            fillWindow(0);
        return mCount;
    }

    public SQLiteDatabase getDatabase()
    {
        return mQuery.getDatabase();
    }

    public boolean onMove(int i, int j)
    {
        if(mWindow == null || j < mWindow.getStartPosition() || j >= mWindow.getStartPosition() + mWindow.getNumRows())
        {
            fillWindow(j);
            SQLiteCursorInjector.calibRowCount(this, mWindow, i, j);
        }
        return true;
    }

    public boolean requery()
    {
        if(isClosed())
            return false;
        this;
        JVM INSTR monitorenter ;
        boolean flag = mQuery.getDatabase().isOpen();
        if(flag)
            break MISSING_BLOCK_LABEL_30;
        this;
        JVM INSTR monitorexit ;
        return false;
        if(mWindow != null)
            mWindow.clear();
        mPos = -1;
        mCount = -1;
        mDriver.cursorRequeried(this);
        this;
        JVM INSTR monitorexit ;
        boolean flag1;
        Exception exception;
        try
        {
            flag1 = super.requery();
        }
        catch(IllegalStateException illegalstateexception)
        {
            Log.w("SQLiteCursor", (new StringBuilder()).append("requery() failed ").append(illegalstateexception.getMessage()).toString(), illegalstateexception);
            return false;
        }
        return flag1;
        exception;
        throw exception;
    }

    public void setSelectionArguments(String as[])
    {
        mDriver.setBindArguments(as);
    }

    public void setWindow(CursorWindow cursorwindow)
    {
        super.setWindow(cursorwindow);
        mCount = -1;
    }

    static final int NO_COUNT = -1;
    static final String TAG = "SQLiteCursor";
    private Map mColumnNameMap;
    private final String mColumns[];
    private int mCount;
    private int mCursorWindowCapacity;
    private final SQLiteCursorDriver mDriver;
    private final String mEditTable;
    private final SQLiteQuery mQuery;
    private final Throwable mStackTrace;
}
