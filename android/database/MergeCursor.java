// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;


// Referenced classes of package android.database:
//            AbstractCursor, Cursor, DataSetObserver, ContentObserver

public class MergeCursor extends AbstractCursor
{

    public MergeCursor(Cursor acursor[])
    {
        mObserver = new DataSetObserver() {

            public void onChanged()
            {
                mPos = -1;
            }

            public void onInvalidated()
            {
                mPos = -1;
            }

            final MergeCursor this$0;

            
            {
                this$0 = MergeCursor.this;
                super();
            }
        }
;
        mCursors = acursor;
        mCursor = acursor[0];
        int i = 0;
        while(i < mCursors.length) 
        {
            if(mCursors[i] != null)
                mCursors[i].registerDataSetObserver(mObserver);
            i++;
        }
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
        super.close();
    }

    public void deactivate()
    {
        int i = mCursors.length;
        for(int j = 0; j < i; j++)
            if(mCursors[j] != null)
                mCursors[j].deactivate();

        super.deactivate();
    }

    public byte[] getBlob(int i)
    {
        return mCursor.getBlob(i);
    }

    public String[] getColumnNames()
    {
        if(mCursor != null)
            return mCursor.getColumnNames();
        else
            return new String[0];
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
        mCursor = null;
        k = 0;
        l = mCursors.length;
        i = 0;
_L5:
        if(i >= l) goto _L2; else goto _L1
_L1:
        if(mCursors[i] != null) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
_L4:
        if(j >= mCursors[i].getCount() + k)
            break MISSING_BLOCK_LABEL_84;
        mCursor = mCursors[i];
_L2:
        if(mCursor != null)
            return mCursor.moveToPosition(j - k);
        else
            return false;
        k += mCursors[i].getCount();
          goto _L3
    }

    public void registerContentObserver(ContentObserver contentobserver)
    {
        int i = mCursors.length;
        for(int j = 0; j < i; j++)
            if(mCursors[j] != null)
                mCursors[j].registerContentObserver(contentobserver);

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

    public void unregisterContentObserver(ContentObserver contentobserver)
    {
        int i = mCursors.length;
        for(int j = 0; j < i; j++)
            if(mCursors[j] != null)
                mCursors[j].unregisterContentObserver(contentobserver);

    }

    public void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
        int i = mCursors.length;
        for(int j = 0; j < i; j++)
            if(mCursors[j] != null)
                mCursors[j].unregisterDataSetObserver(datasetobserver);

    }

    private Cursor mCursor;
    private Cursor mCursors[];
    private DataSetObserver mObserver;
}
