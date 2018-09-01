// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.database.CursorWindow;
import miui.util.ReflectionUtils;

// Referenced classes of package android.database.sqlite:
//            SQLiteCursor

final class SQLiteCursorInjector
{

    private SQLiteCursorInjector()
    {
    }

    public static void calibRowCount(SQLiteCursor sqlitecursor, CursorWindow cursorwindow, int i, int j)
    {
        middle_onMove_calib_count(sqlitecursor, cursorwindow, i, j);
    }

    public static void middle_onMove_calib_count(SQLiteCursor sqlitecursor, CursorWindow cursorwindow, int i, int j)
    {
        if(cursorwindow != null)
        {
            i = cursorwindow.getStartPosition() + cursorwindow.getNumRows();
            if(i <= j)
                ReflectionUtils.trySetObjectField(sqlitecursor, "mCount", Integer.valueOf(i));
        }
    }

    private static final String COUNT_FIELD_NAME = "mCount";
}
