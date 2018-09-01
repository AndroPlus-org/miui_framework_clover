// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.database;

import android.database.*;
import android.util.Log;

public class SortCursor extends AbstractCursor
{

    static int _2D_set0(SortCursor sortcursor, int i)
    {
        sortcursor.mPos = i;
        return i;
    }

    public SortCursor(Cursor acursor[], String s)
    {
        int i;
        int j;
        ROWCACHESIZE = 64;
        mRowNumCache = new int[64];
        mCursorCache = new int[64];
        mLastCacheHit = -1;
        mObserver = new DataSetObserver() {

            public void onChanged()
            {
                SortCursor._2D_set0(SortCursor.this, -1);
            }

            public void onInvalidated()
            {
                SortCursor._2D_set0(SortCursor.this, -1);
            }

            final SortCursor this$0;

            
            {
                this$0 = SortCursor.this;
                super();
            }
        }
;
        mCursors = acursor;
        i = mCursors.length;
        mSortColumns = new int[i];
        j = 0;
        while(j < i) 
        {
            if(mCursors[j] != null)
            {
                mCursors[j].registerDataSetObserver(mObserver);
                mCursors[j].moveToFirst();
                mSortColumns[j] = mCursors[j].getColumnIndexOrThrow(s);
            }
            j++;
        }
        mCursor = null;
        acursor = "";
        j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_248;
        s = acursor;
        if(mCursors[j] != null)
        {
            if(!mCursors[j].isAfterLast())
                break; /* Loop/switch isn't completed */
            s = acursor;
        }
_L5:
        j++;
        acursor = s;
        if(true) goto _L2; else goto _L1
_L1:
        String s1 = mCursors[j].getString(mSortColumns[j]);
        if(mCursor == null) goto _L4; else goto _L3
_L3:
        s = acursor;
        if(s1.compareToIgnoreCase(acursor) >= 0) goto _L5; else goto _L4
_L4:
        s = s1;
        mCursor = mCursors[j];
          goto _L5
        for(int k = mRowNumCache.length - 1; k >= 0; k--)
            mRowNumCache[k] = -2;

        mCurRowNumCache = new int[64][i];
        return;
    }

    public void close()
    {
        int i = mCursors.length;
        int j = 0;
        while(j < i) 
        {
            if(mCursors[j] != null)
                mCursors[j].close();
            j++;
        }
    }

    public void deactivate()
    {
        int i = mCursors.length;
        int j = 0;
        while(j < i) 
        {
            if(mCursors[j] != null)
                mCursors[j].deactivate();
            j++;
        }
    }

    public byte[] getBlob(int i)
    {
        return mCursor.getBlob(i);
    }

    public String[] getColumnNames()
    {
        if(mCursor != null)
            return mCursor.getColumnNames();
        int i = mCursors.length;
        for(int j = 0; j < i; j++)
            if(mCursors[j] != null)
                return mCursors[j].getColumnNames();

        throw new IllegalStateException("No cursor that can return names");
    }

    public int getCount()
    {
        int i = 0;
        int j = mCursors.length;
        for(int k = 0; k < j;)
        {
            int l = i;
            if(mCursors[k] != null)
                l = i + mCursors[k].getCount();
            k++;
            i = l;
        }

        return i;
    }

    public double getDouble(int i)
    {
        return mCursor.getDouble(i);
    }

    public float getFloat(int i)
    {
        return mCursor.getFloat(i);
    }

    public int getInt(int i)
    {
        return mCursor.getInt(i);
    }

    public long getLong(int i)
    {
        return mCursor.getLong(i);
    }

    public short getShort(int i)
    {
        return mCursor.getShort(i);
    }

    public String getString(int i)
    {
        return mCursor.getString(i);
    }

    public int getType(int i)
    {
        return mCursor.getType(i);
    }

    public boolean isNull(int i)
    {
        return mCursor.isNull(i);
    }

    public boolean onMove(int i, int j)
    {
        int k;
        int l;
        int l1;
        int j1;
label0:
        {
            if(i == j)
                return true;
            k = j % 64;
            if(mRowNumCache[k] == j)
            {
                i = mCursorCache[k];
                mCursor = mCursors[i];
                if(mCursor == null)
                {
                    Log.w("SortCursor", "onMove: cache results in a null cursor.");
                    return false;
                } else
                {
                    mCursor.moveToPosition(mCurRowNumCache[k][i]);
                    mLastCacheHit = k;
                    return true;
                }
            }
            mCursor = null;
            l = mCursors.length;
            if(mLastCacheHit >= 0)
            {
                int i1 = 0;
                while(i1 < l) 
                {
                    if(mCursors[i1] != null)
                        mCursors[i1].moveToPosition(mCurRowNumCache[mLastCacheHit][i1]);
                    i1++;
                }
            }
            if(j >= i)
            {
                j1 = i;
                if(i != -1)
                    break label0;
            }
            i = 0;
            while(i < l) 
            {
                if(mCursors[i] != null)
                    mCursors[i].moveToFirst();
                i++;
            }
            j1 = 0;
        }
        i = j1;
        if(j1 < 0)
            i = 0;
        j1 = -1;
        l1 = i;
        i = j1;
_L7:
        int k1;
        String s;
        if(l1 > j)
            break MISSING_BLOCK_LABEL_360;
        s = "";
        i = -1;
        k1 = 0;
_L2:
        String s1;
        int i2;
        if(k1 >= l)
            break MISSING_BLOCK_LABEL_354;
        s1 = s;
        i2 = i;
        if(mCursors[k1] != null)
        {
            if(!mCursors[k1].isAfterLast())
                break; /* Loop/switch isn't completed */
            i2 = i;
            s1 = s;
        }
_L5:
        k1++;
        s = s1;
        i = i2;
        if(true) goto _L2; else goto _L1
_L1:
        String s2 = mCursors[k1].getString(mSortColumns[k1]);
        if(i < 0) goto _L4; else goto _L3
_L3:
        s1 = s;
        i2 = i;
        if(s2.compareToIgnoreCase(s) >= 0) goto _L5; else goto _L4
_L4:
        s1 = s2;
        i2 = k1;
          goto _L5
        if(l1 != j)
            break MISSING_BLOCK_LABEL_426;
        mCursor = mCursors[i];
        mRowNumCache[k] = j;
        mCursorCache[k] = i;
        for(i = 0; i < l; i++)
            if(mCursors[i] != null)
                mCurRowNumCache[k][i] = mCursors[i].getPosition();

        break; /* Loop/switch isn't completed */
        if(mCursors[i] != null)
            mCursors[i].moveToNext();
        l1++;
        if(true) goto _L7; else goto _L6
_L6:
        mLastCacheHit = -1;
        return true;
    }

    public void registerDataSetObserver(DataSetObserver datasetobserver)
    {
        int i = mCursors.length;
        for(int j = 0; j < i; j++)
            if(mCursors[j] != null)
                mCursors[j].registerDataSetObserver(datasetobserver);

    }

    public boolean requery()
    {
        int i;
        int j;
        i = mCursors.length;
        j = 0;
_L3:
        if(j >= i)
            break; /* Loop/switch isn't completed */
          goto _L1
_L5:
        j++;
        if(true) goto _L3; else goto _L2
_L1:
        if(mCursors[j] == null || mCursors[j].requery()) goto _L5; else goto _L4
_L4:
        return false;
_L2:
        return true;
    }

    public void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
        int i = mCursors.length;
        for(int j = 0; j < i; j++)
            if(mCursors[j] != null)
                mCursors[j].unregisterDataSetObserver(datasetobserver);

    }

    private static final String TAG = "SortCursor";
    private final int ROWCACHESIZE;
    private int mCurRowNumCache[][];
    private Cursor mCursor;
    private int mCursorCache[];
    private Cursor mCursors[];
    private int mLastCacheHit;
    private DataSetObserver mObserver;
    private int mRowNumCache[];
    private int mSortColumns[];
}
