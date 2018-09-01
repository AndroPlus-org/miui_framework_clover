// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.net.Uri;
import android.os.*;

// Referenced classes of package android.database:
//            BulkCursorNative, CrossProcessCursor, CrossProcessCursorWrapper, CursorWindow, 
//            StaleDataException, BulkCursorDescriptor, Cursor, IContentObserver, 
//            ContentObserver

public final class CursorToBulkCursorAdaptor extends BulkCursorNative
    implements android.os.IBinder.DeathRecipient
{
    private static final class ContentObserverProxy extends ContentObserver
    {

        public boolean deliverSelfNotifications()
        {
            return false;
        }

        public void onChange(boolean flag, Uri uri)
        {
            mRemote.onChange(flag, uri, Process.myUid());
_L2:
            return;
            uri;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public boolean unlinkToDeath(android.os.IBinder.DeathRecipient deathrecipient)
        {
            return mRemote.asBinder().unlinkToDeath(deathrecipient, 0);
        }

        protected IContentObserver mRemote;

        public ContentObserverProxy(IContentObserver icontentobserver, android.os.IBinder.DeathRecipient deathrecipient)
        {
            super(null);
            mRemote = icontentobserver;
            icontentobserver.asBinder().linkToDeath(deathrecipient, 0);
_L2:
            return;
            icontentobserver;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }


    public CursorToBulkCursorAdaptor(Cursor cursor, IContentObserver icontentobserver, String s)
    {
        mLock = new Object();
        if(cursor instanceof CrossProcessCursor)
            mCursor = (CrossProcessCursor)cursor;
        else
            mCursor = new CrossProcessCursorWrapper(cursor);
        mProviderName = s;
        cursor = ((Cursor) (mLock));
        cursor;
        JVM INSTR monitorenter ;
        createAndRegisterObserverProxyLocked(icontentobserver);
        cursor;
        JVM INSTR monitorexit ;
        return;
        icontentobserver;
        throw icontentobserver;
    }

    private void closeFilledWindowLocked()
    {
        if(mFilledWindow != null)
        {
            mFilledWindow.close();
            mFilledWindow = null;
        }
    }

    private void createAndRegisterObserverProxyLocked(IContentObserver icontentobserver)
    {
        if(mObserver != null)
        {
            throw new IllegalStateException("an observer is already registered");
        } else
        {
            mObserver = new ContentObserverProxy(icontentobserver, this);
            mCursor.registerContentObserver(mObserver);
            return;
        }
    }

    private void disposeLocked()
    {
        if(mCursor != null)
        {
            unregisterObserverProxyLocked();
            mCursor.close();
            mCursor = null;
        }
        closeFilledWindowLocked();
    }

    private void throwIfCursorIsClosed()
    {
        if(mCursor == null)
            throw new StaleDataException("Attempted to access a cursor after it has been closed.");
        else
            return;
    }

    private void unregisterObserverProxyLocked()
    {
        if(mObserver != null)
        {
            mCursor.unregisterContentObserver(mObserver);
            mObserver.unlinkToDeath(this);
            mObserver = null;
        }
    }

    public void binderDied()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        disposeLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void close()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        disposeLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void deactivate()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCursor != null)
        {
            unregisterObserverProxyLocked();
            mCursor.deactivate();
        }
        closeFilledWindowLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public BulkCursorDescriptor getBulkCursorDescriptor()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        BulkCursorDescriptor bulkcursordescriptor;
        throwIfCursorIsClosed();
        bulkcursordescriptor = JVM INSTR new #104 <Class BulkCursorDescriptor>;
        bulkcursordescriptor.BulkCursorDescriptor();
        bulkcursordescriptor.cursor = this;
        bulkcursordescriptor.columnNames = mCursor.getColumnNames();
        bulkcursordescriptor.wantsAllOnMoveCalls = mCursor.getWantsAllOnMoveCalls();
        bulkcursordescriptor.count = mCursor.getCount();
        bulkcursordescriptor.window = mCursor.getWindow();
        if(bulkcursordescriptor.window != null)
            bulkcursordescriptor.window.acquireReference();
        obj;
        JVM INSTR monitorexit ;
        return bulkcursordescriptor;
        Exception exception;
        exception;
        throw exception;
    }

    public Bundle getExtras()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Bundle bundle;
        throwIfCursorIsClosed();
        bundle = mCursor.getExtras();
        obj;
        JVM INSTR monitorexit ;
        return bundle;
        Exception exception;
        exception;
        throw exception;
    }

    public CursorWindow getWindow(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCursorIsClosed();
        if(mCursor.moveToPosition(i))
            break MISSING_BLOCK_LABEL_32;
        closeFilledWindowLocked();
        obj;
        JVM INSTR monitorexit ;
        return null;
        Object obj1 = mCursor.getWindow();
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        closeFilledWindowLocked();
_L4:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_58;
        ((CursorWindow) (obj1)).acquireReference();
        obj;
        JVM INSTR monitorexit ;
        return ((CursorWindow) (obj1));
_L2:
        CursorWindow cursorwindow = mFilledWindow;
        if(cursorwindow != null)
            break; /* Loop/switch isn't completed */
        obj1 = JVM INSTR new #53  <Class CursorWindow>;
        ((CursorWindow) (obj1)).CursorWindow(mProviderName);
        mFilledWindow = ((CursorWindow) (obj1));
        obj1 = mFilledWindow;
_L7:
        mCursor.fillWindow(i, ((CursorWindow) (obj1)));
        if(true) goto _L4; else goto _L3
        obj1;
        throw obj1;
_L3:
        if(i < cursorwindow.getStartPosition()) goto _L6; else goto _L5
_L5:
        obj1 = cursorwindow;
        if(i < cursorwindow.getStartPosition() + cursorwindow.getNumRows()) goto _L7; else goto _L6
_L6:
        cursorwindow.clear();
        obj1 = cursorwindow;
          goto _L7
    }

    public void onMove(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCursorIsClosed();
        mCursor.onMove(mCursor.getPosition(), i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int requery(IContentObserver icontentobserver)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCursorIsClosed();
        closeFilledWindowLocked();
        boolean flag = mCursor.requery();
        if(flag)
            break MISSING_BLOCK_LABEL_91;
        obj;
        JVM INSTR monitorexit ;
        return -1;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        IllegalStateException illegalstateexception1 = JVM INSTR new #60  <Class IllegalStateException>;
        icontentobserver = JVM INSTR new #180 <Class StringBuilder>;
        icontentobserver.StringBuilder();
        illegalstateexception1.IllegalStateException(icontentobserver.append(mProviderName).append(" Requery misuse db, mCursor isClosed:").append(mCursor.isClosed()).toString(), illegalstateexception);
        throw illegalstateexception1;
        icontentobserver;
        obj;
        JVM INSTR monitorexit ;
        throw icontentobserver;
        int i;
        unregisterObserverProxyLocked();
        createAndRegisterObserverProxyLocked(icontentobserver);
        i = mCursor.getCount();
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public Bundle respond(Bundle bundle)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfCursorIsClosed();
        bundle = mCursor.respond(bundle);
        obj;
        JVM INSTR monitorexit ;
        return bundle;
        bundle;
        throw bundle;
    }

    private static final String TAG = "Cursor";
    private CrossProcessCursor mCursor;
    private CursorWindow mFilledWindow;
    private final Object mLock;
    private ContentObserverProxy mObserver;
    private final String mProviderName;
}
