// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


// Referenced classes of package android.util:
//            MonthDisplayHelper

public class DayOfMonthCursor extends MonthDisplayHelper
{

    public DayOfMonthCursor(int i, int j, int k, int l)
    {
        super(i, j, l);
        mRow = getRowOf(k);
        mColumn = getColumnOf(k);
    }

    public boolean down()
    {
        if(isWithinCurrentMonth(mRow + 1, mColumn))
        {
            mRow = mRow + 1;
            return false;
        }
        nextMonth();
        for(mRow = 0; !isWithinCurrentMonth(mRow, mColumn); mRow = mRow + 1);
        return true;
    }

    public int getSelectedColumn()
    {
        return mColumn;
    }

    public int getSelectedDayOfMonth()
    {
        return getDayAt(mRow, mColumn);
    }

    public int getSelectedMonthOffset()
    {
        if(isWithinCurrentMonth(mRow, mColumn))
            return 0;
        return mRow != 0 ? 1 : -1;
    }

    public int getSelectedRow()
    {
        return mRow;
    }

    public boolean isSelected(int i, int j)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mRow == i)
        {
            flag1 = flag;
            if(mColumn == j)
                flag1 = true;
        }
        return flag1;
    }

    public boolean left()
    {
        if(mColumn == 0)
        {
            mRow = mRow - 1;
            mColumn = 6;
        } else
        {
            mColumn = mColumn - 1;
        }
        if(isWithinCurrentMonth(mRow, mColumn))
        {
            return false;
        } else
        {
            previousMonth();
            int i = getNumberOfDaysInMonth();
            mRow = getRowOf(i);
            mColumn = getColumnOf(i);
            return true;
        }
    }

    public boolean right()
    {
        if(mColumn == 6)
        {
            mRow = mRow + 1;
            mColumn = 0;
        } else
        {
            mColumn = mColumn + 1;
        }
        if(isWithinCurrentMonth(mRow, mColumn))
            return false;
        nextMonth();
        mRow = 0;
        for(mColumn = 0; !isWithinCurrentMonth(mRow, mColumn); mColumn = mColumn + 1);
        return true;
    }

    public void setSelectedDayOfMonth(int i)
    {
        mRow = getRowOf(i);
        mColumn = getColumnOf(i);
    }

    public void setSelectedRowColumn(int i, int j)
    {
        mRow = i;
        mColumn = j;
    }

    public boolean up()
    {
        if(isWithinCurrentMonth(mRow - 1, mColumn))
        {
            mRow = mRow - 1;
            return false;
        }
        previousMonth();
        for(mRow = 5; !isWithinCurrentMonth(mRow, mColumn); mRow = mRow - 1);
        return true;
    }

    private int mColumn;
    private int mRow;
}
