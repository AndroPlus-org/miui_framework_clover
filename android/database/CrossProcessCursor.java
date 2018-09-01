// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;


// Referenced classes of package android.database:
//            Cursor, CursorWindow

public interface CrossProcessCursor
    extends Cursor
{

    public abstract void fillWindow(int i, CursorWindow cursorwindow);

    public abstract CursorWindow getWindow();

    public abstract boolean onMove(int i, int j);
}
