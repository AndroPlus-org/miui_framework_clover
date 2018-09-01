// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package android.database:
//            AbstractWindowedCursor, StaleDataException, IBulkCursor, BulkCursorDescriptor, 
//            CursorWindow, IContentObserver

public final class BulkCursorToCursorAdaptor extends AbstractWindowedCursor
{

    public BulkCursorToCursorAdaptor()
    {
        mObserverBridge = new AbstractCursor.SelfContentObserver(this);
    }

    private void throwIfCursorIsClosed()
    {
        if(mBulkCursor == null)
            throw new StaleDataException("Attempted to access a cursor after it has been closed.");
        else
            return;
    }

    public void close()
    {
        super.close();
        if(mBulkCursor == null) goto _L2; else goto _L1
_L1:
        mBulkCursor.close();
_L4:
        mBulkCursor = null;
_L2:
        return;
        Object obj;
        obj;
        Log.w("BulkCursor", "Remote process exception when closing");
        if(true) goto _L4; else goto _L3
_L3:
        obj;
        mBulkCursor = null;
        throw obj;
    }

    public void deactivate()
    {
        super.deactivate();
        if(mBulkCursor == null)
            break MISSING_BLOCK_LABEL_20;
        mBulkCursor.deactivate();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("BulkCursor", "Remote process exception when deactivating");
          goto _L1
    }

    public String[] getColumnNames()
    {
        throwIfCursorIsClosed();
        return mColumns;
    }

    public int getCount()
    {
        throwIfCursorIsClosed();
        return mCount;
    }

    public Bundle getExtras()
    {
        throwIfCursorIsClosed();
        Bundle bundle;
        try
        {
            bundle = mBulkCursor.getExtras();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException(remoteexception);
        }
        return bundle;
    }

    public IContentObserver getObserver()
    {
        return mObserverBridge.getContentObserver();
    }

    public void initialize(BulkCursorDescriptor bulkcursordescriptor)
    {
        mBulkCursor = bulkcursordescriptor.cursor;
        mColumns = bulkcursordescriptor.columnNames;
        mWantsAllOnMoveCalls = bulkcursordescriptor.wantsAllOnMoveCalls;
        mCount = bulkcursordescriptor.count;
        if(bulkcursordescriptor.window != null)
            setWindow(bulkcursordescriptor.window);
    }

    public boolean onMove(int i, int j)
    {
        throwIfCursorIsClosed();
        if(mWindow != null && j >= mWindow.getStartPosition()) goto _L2; else goto _L1
_L1:
        setWindow(mBulkCursor.getWindow(j));
_L4:
        RemoteException remoteexception;
        return mWindow != null;
_L2:
        if(j >= mWindow.getStartPosition() + mWindow.getNumRows()) goto _L1; else goto _L3
_L3:
        if(mWantsAllOnMoveCalls)
            mBulkCursor.onMove(j);
          goto _L4
        remoteexception;
        Log.e("BulkCursor", "Unable to get window because the remote process is dead");
        return false;
    }

    public boolean requery()
    {
        throwIfCursorIsClosed();
        mCount = mBulkCursor.requery(getObserver());
        if(mCount == -1)
            break MISSING_BLOCK_LABEL_45;
        mPos = -1;
        closeWindow();
        super.requery();
        return true;
        try
        {
            deactivate();
        }
        catch(Exception exception)
        {
            Log.e("BulkCursor", (new StringBuilder()).append("Unable to requery because the remote process exception ").append(exception.getMessage()).toString());
            deactivate();
            return false;
        }
        return false;
    }

    public Bundle respond(Bundle bundle)
    {
        throwIfCursorIsClosed();
        try
        {
            bundle = mBulkCursor.respond(bundle);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            Log.w("BulkCursor", "respond() threw RemoteException, returning an empty bundle.", bundle);
            return Bundle.EMPTY;
        }
        return bundle;
    }

    private static final String TAG = "BulkCursor";
    private IBulkCursor mBulkCursor;
    private String mColumns[];
    private int mCount;
    private AbstractCursor.SelfContentObserver mObserverBridge;
    private boolean mWantsAllOnMoveCalls;
}
