// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;


// Referenced classes of package android.database:
//            AbstractCursor

final class AbstractCursorInjector
{

    private AbstractCursorInjector()
    {
    }

    public static void before_moveToPosition(AbstractCursor abstractcursor, int i)
    {
        if(i >= abstractcursor.getCount())
            return;
        if(i < 0)
            return;
        if(i == abstractcursor.mPos)
        {
            return;
        } else
        {
            abstractcursor.onMove(abstractcursor.mPos, i);
            return;
        }
    }

    public static void calibRowCountForReadRow(AbstractCursor abstractcursor, int i)
    {
        before_moveToPosition(abstractcursor, i);
    }

    public static boolean checkPosition(AbstractCursor abstractcursor, int i)
    {
        int j = abstractcursor.getCount();
        if(i >= j)
        {
            abstractcursor.mPos = j;
            return false;
        } else
        {
            return true;
        }
    }
}
