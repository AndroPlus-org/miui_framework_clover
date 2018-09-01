// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import java.util.*;

// Referenced classes of package android.content:
//            ContentValues

public class ContentQueryMap extends Observable
{

    static boolean _2D_set0(ContentQueryMap contentquerymap, boolean flag)
    {
        contentquerymap.mDirty = flag;
        return flag;
    }

    public ContentQueryMap(Cursor cursor, String s, boolean flag, Handler handler)
    {
        mHandlerForUpdateNotifications = null;
        mKeepUpdated = false;
        mValues = null;
        mDirty = false;
        mCursor = cursor;
        mColumnNames = mCursor.getColumnNames();
        mKeyColumn = mCursor.getColumnIndexOrThrow(s);
        mHandlerForUpdateNotifications = handler;
        setKeepUpdated(flag);
        if(!flag)
            readCursorIntoCache(cursor);
    }

    private void readCursorIntoCache(Cursor cursor)
    {
        this;
        JVM INSTR monitorenter ;
        if(mValues == null) goto _L2; else goto _L1
_L1:
        int i = mValues.size();
_L9:
        HashMap hashmap = JVM INSTR new #70  <Class HashMap>;
        hashmap.HashMap(i);
        mValues = hashmap;
_L7:
        if(!cursor.moveToNext()) goto _L4; else goto _L3
_L3:
        ContentValues contentvalues;
        contentvalues = JVM INSTR new #79  <Class ContentValues>;
        contentvalues.ContentValues();
        i = 0;
_L6:
        if(i >= mColumnNames.length)
            break; /* Loop/switch isn't completed */
        if(i != mKeyColumn)
            contentvalues.put(mColumnNames[i], cursor.getString(i));
        i++;
        if(true) goto _L6; else goto _L5
_L2:
        i = 0;
        continue; /* Loop/switch isn't completed */
_L5:
        mValues.put(cursor.getString(mKeyColumn), contentvalues);
          goto _L7
        cursor;
        throw cursor;
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        if(mContentObserver != null)
        {
            mCursor.unregisterContentObserver(mContentObserver);
            mContentObserver = null;
        }
        mCursor.close();
        mCursor = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        if(mCursor != null)
            close();
        super.finalize();
    }

    public Map getRows()
    {
        this;
        JVM INSTR monitorenter ;
        Map map;
        if(mDirty)
            requery();
        map = mValues;
        this;
        JVM INSTR monitorexit ;
        return map;
        Exception exception;
        exception;
        throw exception;
    }

    public ContentValues getValues(String s)
    {
        this;
        JVM INSTR monitorenter ;
        if(mDirty)
            requery();
        s = (ContentValues)mValues.get(s);
        this;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public void requery()
    {
        Cursor cursor = mCursor;
        if(cursor == null)
            return;
        mDirty = false;
        if(!cursor.requery())
        {
            return;
        } else
        {
            readCursorIntoCache(cursor);
            setChanged();
            notifyObservers();
            return;
        }
    }

    public void setKeepUpdated(boolean flag)
    {
        if(flag == mKeepUpdated)
            return;
        mKeepUpdated = flag;
        if(!mKeepUpdated)
        {
            mCursor.unregisterContentObserver(mContentObserver);
            mContentObserver = null;
        } else
        {
            if(mHandlerForUpdateNotifications == null)
                mHandlerForUpdateNotifications = new Handler();
            if(mContentObserver == null)
                mContentObserver = new ContentObserver(mHandlerForUpdateNotifications) {

                    public void onChange(boolean flag1)
                    {
                        if(countObservers() != 0)
                            requery();
                        else
                            ContentQueryMap._2D_set0(ContentQueryMap.this, true);
                    }

                    final ContentQueryMap this$0;

            
            {
                this$0 = ContentQueryMap.this;
                super(handler);
            }
                }
;
            mCursor.registerContentObserver(mContentObserver);
            mDirty = true;
        }
    }

    private String mColumnNames[];
    private ContentObserver mContentObserver;
    private volatile Cursor mCursor;
    private boolean mDirty;
    private Handler mHandlerForUpdateNotifications;
    private boolean mKeepUpdated;
    private int mKeyColumn;
    private Map mValues;
}
