// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;


// Referenced classes of package android.database:
//            CursorWrapper, CrossProcessCursor, DatabaseUtils, Cursor, 
//            CursorWindow

public class CrossProcessCursorWrapper extends CursorWrapper
    implements CrossProcessCursor
{

    public CrossProcessCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }

    public void fillWindow(int i, CursorWindow cursorwindow)
    {
        if(mCursor instanceof CrossProcessCursor)
        {
            ((CrossProcessCursor)mCursor).fillWindow(i, cursorwindow);
            return;
        } else
        {
            DatabaseUtils.cursorFillWindow(mCursor, i, cursorwindow);
            return;
        }
    }

    public CursorWindow getWindow()
    {
        if(mCursor instanceof CrossProcessCursor)
            return ((CrossProcessCursor)mCursor).getWindow();
        else
            return null;
    }

    public boolean onMove(int i, int j)
    {
        if(mCursor instanceof CrossProcessCursor)
            return ((CrossProcessCursor)mCursor).onMove(i, j);
        else
            return true;
    }
}
