// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.database.Cursor;

// Referenced classes of package android.widget:
//            Filter

class CursorFilter extends Filter
{
    static interface CursorFilterClient
    {

        public abstract void changeCursor(Cursor cursor);

        public abstract CharSequence convertToString(Cursor cursor);

        public abstract Cursor getCursor();

        public abstract Cursor runQueryOnBackgroundThread(CharSequence charsequence);
    }


    CursorFilter(CursorFilterClient cursorfilterclient)
    {
        mClient = cursorfilterclient;
    }

    public CharSequence convertResultToString(Object obj)
    {
        return mClient.convertToString((Cursor)obj);
    }

    protected Filter.FilterResults performFiltering(CharSequence charsequence)
    {
        Cursor cursor = mClient.runQueryOnBackgroundThread(charsequence);
        charsequence = new Filter.FilterResults();
        if(cursor != null)
        {
            charsequence.count = cursor.getCount();
            charsequence.values = cursor;
        } else
        {
            charsequence.count = 0;
            charsequence.values = null;
        }
        return charsequence;
    }

    protected void publishResults(CharSequence charsequence, Filter.FilterResults filterresults)
    {
        charsequence = mClient.getCursor();
        if(filterresults.values != null && filterresults.values != charsequence)
            mClient.changeCursor((Cursor)filterresults.values);
    }

    CursorFilterClient mClient;
}
