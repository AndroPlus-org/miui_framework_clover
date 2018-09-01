// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.util.Calendar;

public class MonthDisplayHelper
{

    public MonthDisplayHelper(int i, int j)
    {
        this(i, j, 1);
    }

    public MonthDisplayHelper(int i, int j, int k)
    {
        if(k < 1 || k > 7)
        {
            throw new IllegalArgumentException();
        } else
        {
            mWeekStartDay = k;
            mCalendar = Calendar.getInstance();
            mCalendar.set(1, i);
            mCalendar.set(2, j);
            mCalendar.set(5, 1);
            mCalendar.set(11, 0);
            mCalendar.set(12, 0);
            mCalendar.set(13, 0);
            mCalendar.getTimeInMillis();
            recalculate();
            return;
        }
    }

    private void recalculate()
    {
        mNumDaysInMonth = mCalendar.getActualMaximum(5);
        mCalendar.add(2, -1);
        mNumDaysInPrevMonth = mCalendar.getActualMaximum(5);
        mCalendar.add(2, 1);
        int i = getFirstDayOfMonth() - mWeekStartDay;
        int j = i;
        if(i < 0)
            j = i + 7;
        mOffset = j;
    }

    public int getColumnOf(int i)
    {
        return ((mOffset + i) - 1) % 7;
    }

    public int getDayAt(int i, int j)
    {
        if(i == 0 && j < mOffset)
            return ((mNumDaysInPrevMonth + j) - mOffset) + 1;
        j = ((i * 7 + j) - mOffset) + 1;
        i = j;
        if(j > mNumDaysInMonth)
            i = j - mNumDaysInMonth;
        return i;
    }

    public int[] getDigitsForRow(int i)
    {
        if(i < 0 || i > 5)
            throw new IllegalArgumentException((new StringBuilder()).append("row ").append(i).append(" out of range (0-5)").toString());
        int ai[] = new int[7];
        for(int j = 0; j < 7; j++)
            ai[j] = getDayAt(i, j);

        return ai;
    }

    public int getFirstDayOfMonth()
    {
        return mCalendar.get(7);
    }

    public int getMonth()
    {
        return mCalendar.get(2);
    }

    public int getNumberOfDaysInMonth()
    {
        return mNumDaysInMonth;
    }

    public int getOffset()
    {
        return mOffset;
    }

    public int getRowOf(int i)
    {
        return ((mOffset + i) - 1) / 7;
    }

    public int getWeekStartDay()
    {
        return mWeekStartDay;
    }

    public int getYear()
    {
        return mCalendar.get(1);
    }

    public boolean isWithinCurrentMonth(int i, int j)
    {
        while(i < 0 || j < 0 || i > 5 || j > 6) 
            return false;
        if(i == 0 && j < mOffset)
            return false;
        return ((i * 7 + j) - mOffset) + 1 <= mNumDaysInMonth;
    }

    public void nextMonth()
    {
        mCalendar.add(2, 1);
        recalculate();
    }

    public void previousMonth()
    {
        mCalendar.add(2, -1);
        recalculate();
    }

    private Calendar mCalendar;
    private int mNumDaysInMonth;
    private int mNumDaysInPrevMonth;
    private int mOffset;
    private final int mWeekStartDay;
}
