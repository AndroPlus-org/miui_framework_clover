// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;

// Referenced classes of package android.database:
//            CrossProcessCursor, DataSetObservable, ContentObservable, CursorIndexOutOfBoundsException, 
//            CharArrayBuffer, DatabaseUtils, AbstractCursorInjector, ContentObserver, 
//            CursorWindow, DataSetObserver

public abstract class AbstractCursor
    implements CrossProcessCursor
{
    protected static class SelfContentObserver extends ContentObserver
    {

        public boolean deliverSelfNotifications()
        {
            return false;
        }

        public void onChange(boolean flag)
        {
            AbstractCursor abstractcursor = (AbstractCursor)mCursor.get();
            if(abstractcursor != null)
                abstractcursor.onChange(false);
        }

        WeakReference mCursor;

        public SelfContentObserver(AbstractCursor abstractcursor)
        {
            super(null);
            mCursor = new WeakReference(abstractcursor);
        }
    }


    public AbstractCursor()
    {
        mExtras = Bundle.EMPTY;
        mPos = -1;
    }

    protected void checkPosition()
    {
        if(-1 == mPos || getCount() == mPos)
            throw new CursorIndexOutOfBoundsException(mPos, getCount());
        else
            return;
    }

    public void close()
    {
        mClosed = true;
        mContentObservable.unregisterAll();
        onDeactivateOrClose();
    }

    public void copyStringToBuffer(int i, CharArrayBuffer chararraybuffer)
    {
        String s = getString(i);
        if(s != null)
        {
            char ac[] = chararraybuffer.data;
            if(ac == null || ac.length < s.length())
                chararraybuffer.data = s.toCharArray();
            else
                s.getChars(0, s.length(), ac, 0);
            chararraybuffer.sizeCopied = s.length();
        } else
        {
            chararraybuffer.sizeCopied = 0;
        }
    }

    public void deactivate()
    {
        onDeactivateOrClose();
    }

    public void fillWindow(int i, CursorWindow cursorwindow)
    {
        DatabaseUtils.cursorFillWindow(this, i, cursorwindow);
    }

    protected void finalize()
    {
        if(mSelfObserver != null && mSelfObserverRegistered)
            mContentResolver.unregisterContentObserver(mSelfObserver);
        if(!mClosed)
            close();
_L2:
        return;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public byte[] getBlob(int i)
    {
        throw new UnsupportedOperationException("getBlob is not supported");
    }

    public int getColumnCount()
    {
        return getColumnNames().length;
    }

    public int getColumnIndex(String s)
    {
        int i = s.lastIndexOf('.');
        Object obj = s;
        if(i != -1)
        {
            obj = new Exception();
            Log.e("Cursor", (new StringBuilder()).append("requesting column name with table name -- ").append(s).toString(), ((Throwable) (obj)));
            obj = s.substring(i + 1);
        }
        s = getColumnNames();
        int k = s.length;
        for(int j = 0; j < k; j++)
            if(s[j].equalsIgnoreCase(((String) (obj))))
                return j;

        return -1;
    }

    public int getColumnIndexOrThrow(String s)
    {
        int i = getColumnIndex(s);
        if(i >= 0) goto _L2; else goto _L1
_L1:
        String s1 = "";
        String s2 = Arrays.toString(getColumnNames());
        s1 = s2;
_L3:
        throw new IllegalArgumentException((new StringBuilder()).append("column '").append(s).append("' does not exist. Available columns: ").append(s1).toString());
        Exception exception;
        exception;
        Log.d("Cursor", "Cannot collect column names for debug purposes", exception);
        if(true) goto _L3; else goto _L2
_L2:
        return i;
    }

    public String getColumnName(int i)
    {
        return getColumnNames()[i];
    }

    public abstract String[] getColumnNames();

    public abstract int getCount();

    public abstract double getDouble(int i);

    public Bundle getExtras()
    {
        return mExtras;
    }

    public abstract float getFloat(int i);

    public abstract int getInt(int i);

    public abstract long getLong(int i);

    public Uri getNotificationUri()
    {
        Object obj = mSelfObserverLock;
        obj;
        JVM INSTR monitorenter ;
        Uri uri = mNotifyUri;
        obj;
        JVM INSTR monitorexit ;
        return uri;
        Exception exception;
        exception;
        throw exception;
    }

    public final int getPosition()
    {
        return mPos;
    }

    public abstract short getShort(int i);

    public abstract String getString(int i);

    public int getType(int i)
    {
        return 3;
    }

    protected Object getUpdatedField(int i)
    {
        return null;
    }

    public boolean getWantsAllOnMoveCalls()
    {
        return false;
    }

    public CursorWindow getWindow()
    {
        return null;
    }

    public final boolean isAfterLast()
    {
        boolean flag = true;
        if(getCount() == 0)
            return true;
        if(mPos != getCount())
            flag = false;
        return flag;
    }

    public final boolean isBeforeFirst()
    {
        boolean flag = true;
        if(getCount() == 0)
            return true;
        if(mPos != -1)
            flag = false;
        return flag;
    }

    public boolean isClosed()
    {
        return mClosed;
    }

    protected boolean isFieldUpdated(int i)
    {
        return false;
    }

    public final boolean isFirst()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mPos == 0)
        {
            flag1 = flag;
            if(getCount() != 0)
                flag1 = true;
        }
        return flag1;
    }

    public final boolean isLast()
    {
        boolean flag = false;
        int i = getCount();
        boolean flag1 = flag;
        if(mPos == i - 1)
        {
            flag1 = flag;
            if(i != 0)
                flag1 = true;
        }
        return flag1;
    }

    public abstract boolean isNull(int i);

    public final boolean move(int i)
    {
        return moveToPosition(mPos + i);
    }

    public final boolean moveToFirst()
    {
        return moveToPosition(0);
    }

    public final boolean moveToLast()
    {
        return moveToPosition(getCount() - 1);
    }

    public final boolean moveToNext()
    {
        return moveToPosition(mPos + 1);
    }

    public final boolean moveToPosition(int i)
    {
        int j = getCount();
        if(i >= j)
        {
            mPos = j;
            return false;
        }
        if(i < 0)
        {
            mPos = -1;
            return false;
        }
        if(i == mPos)
            return true;
        boolean flag = onMove(mPos, i);
        if(!flag)
        {
            mPos = -1;
        } else
        {
            if(!AbstractCursorInjector.checkPosition(this, i))
                return false;
            mPos = i;
        }
        return flag;
    }

    public final boolean moveToPrevious()
    {
        return moveToPosition(mPos - 1);
    }

    protected void onChange(boolean flag)
    {
        Object obj = mSelfObserverLock;
        obj;
        JVM INSTR monitorenter ;
        mContentObservable.dispatchChange(flag, null);
        if(mNotifyUri == null || !flag)
            break MISSING_BLOCK_LABEL_42;
        mContentResolver.notifyChange(mNotifyUri, mSelfObserver);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void onDeactivateOrClose()
    {
        if(mSelfObserver != null)
        {
            mContentResolver.unregisterContentObserver(mSelfObserver);
            mSelfObserverRegistered = false;
        }
        mDataSetObservable.notifyInvalidated();
    }

    public boolean onMove(int i, int j)
    {
        return true;
    }

    public void registerContentObserver(ContentObserver contentobserver)
    {
        mContentObservable.registerObserver(contentobserver);
    }

    public void registerDataSetObserver(DataSetObserver datasetobserver)
    {
        mDataSetObservable.registerObserver(datasetobserver);
    }

    public boolean requery()
    {
        if(mSelfObserver != null && !mSelfObserverRegistered)
        {
            mContentResolver.registerContentObserver(mNotifyUri, true, mSelfObserver);
            mSelfObserverRegistered = true;
        }
        mDataSetObservable.notifyChanged();
        return true;
    }

    public Bundle respond(Bundle bundle)
    {
        return Bundle.EMPTY;
    }

    public void setExtras(Bundle bundle)
    {
        Bundle bundle1 = bundle;
        if(bundle == null)
            bundle1 = Bundle.EMPTY;
        mExtras = bundle1;
    }

    public void setNotificationUri(ContentResolver contentresolver, Uri uri)
    {
        setNotificationUri(contentresolver, uri, UserHandle.myUserId());
    }

    public void setNotificationUri(ContentResolver contentresolver, Uri uri, int i)
    {
        Object obj = mSelfObserverLock;
        obj;
        JVM INSTR monitorenter ;
        mNotifyUri = uri;
        mContentResolver = contentresolver;
        if(mSelfObserver != null)
            mContentResolver.unregisterContentObserver(mSelfObserver);
        contentresolver = JVM INSTR new #8   <Class AbstractCursor$SelfContentObserver>;
        contentresolver.SelfContentObserver(this);
        mSelfObserver = contentresolver;
        mContentResolver.registerContentObserver(mNotifyUri, true, mSelfObserver, i);
        mSelfObserverRegistered = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        contentresolver;
        throw contentresolver;
    }

    public void unregisterContentObserver(ContentObserver contentobserver)
    {
        if(!mClosed)
            mContentObservable.unregisterObserver(contentobserver);
    }

    public void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
        mDataSetObservable.unregisterObserver(datasetobserver);
    }

    private static final String TAG = "Cursor";
    protected boolean mClosed;
    private final ContentObservable mContentObservable = new ContentObservable();
    protected ContentResolver mContentResolver;
    protected Long mCurrentRowID;
    private final DataSetObservable mDataSetObservable = new DataSetObservable();
    private Bundle mExtras;
    private Uri mNotifyUri;
    protected int mPos;
    protected int mRowIdColumnIndex;
    private ContentObserver mSelfObserver;
    private final Object mSelfObserverLock = new Object();
    private boolean mSelfObserverRegistered;
    protected HashMap mUpdatedRows;
}
