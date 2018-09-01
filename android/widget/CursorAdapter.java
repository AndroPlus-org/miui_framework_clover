// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.database.*;
import android.os.Handler;
import android.view.*;

// Referenced classes of package android.widget:
//            BaseAdapter, Filterable, ThemedSpinnerAdapter, CursorFilter, 
//            FilterQueryProvider, Filter

public abstract class CursorAdapter extends BaseAdapter
    implements Filterable, CursorFilter.CursorFilterClient, ThemedSpinnerAdapter
{
    private class ChangeObserver extends ContentObserver
    {

        public boolean deliverSelfNotifications()
        {
            return true;
        }

        public void onChange(boolean flag)
        {
            onContentChanged();
        }

        final CursorAdapter this$0;

        public ChangeObserver()
        {
            this$0 = CursorAdapter.this;
            super(new Handler());
        }
    }

    private class MyDataSetObserver extends DataSetObserver
    {

        public void onChanged()
        {
            mDataValid = true;
            notifyDataSetChanged();
        }

        public void onInvalidated()
        {
            mDataValid = false;
            notifyDataSetInvalidated();
        }

        final CursorAdapter this$0;

        private MyDataSetObserver()
        {
            this$0 = CursorAdapter.this;
            super();
        }

        MyDataSetObserver(MyDataSetObserver mydatasetobserver)
        {
            this();
        }
    }


    public CursorAdapter(Context context, Cursor cursor)
    {
        init(context, cursor, 1);
    }

    public CursorAdapter(Context context, Cursor cursor, int i)
    {
        init(context, cursor, i);
    }

    public CursorAdapter(Context context, Cursor cursor, boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 2;
        init(context, cursor, i);
    }

    public abstract void bindView(View view, Context context, Cursor cursor);

    public void changeCursor(Cursor cursor)
    {
        cursor = swapCursor(cursor);
        if(cursor != null)
            cursor.close();
    }

    public CharSequence convertToString(Cursor cursor)
    {
        if(cursor == null)
            cursor = "";
        else
            cursor = cursor.toString();
        return cursor;
    }

    public int getCount()
    {
        if(mDataValid && mCursor != null)
            return mCursor.getCount();
        else
            return 0;
    }

    public Cursor getCursor()
    {
        return mCursor;
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        if(mDataValid)
        {
            Context context;
            if(mDropDownContext == null)
                context = mContext;
            else
                context = mDropDownContext;
            mCursor.moveToPosition(i);
            if(view == null)
                view = newDropDownView(context, mCursor, viewgroup);
            bindView(view, context, mCursor);
            return view;
        } else
        {
            return null;
        }
    }

    public android.content.res.Resources.Theme getDropDownViewTheme()
    {
        android.content.res.Resources.Theme theme = null;
        if(mDropDownContext != null)
            theme = mDropDownContext.getTheme();
        return theme;
    }

    public Filter getFilter()
    {
        if(mCursorFilter == null)
            mCursorFilter = new CursorFilter(this);
        return mCursorFilter;
    }

    public FilterQueryProvider getFilterQueryProvider()
    {
        return mFilterQueryProvider;
    }

    public Object getItem(int i)
    {
        if(mDataValid && mCursor != null)
        {
            mCursor.moveToPosition(i);
            return mCursor;
        } else
        {
            return null;
        }
    }

    public long getItemId(int i)
    {
        if(mDataValid && mCursor != null)
        {
            if(mCursor.moveToPosition(i))
                return mCursor.getLong(mRowIDColumn);
            else
                return 0L;
        } else
        {
            return 0L;
        }
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(!mDataValid)
            throw new IllegalStateException("this should only be called when the cursor is valid");
        if(!mCursor.moveToPosition(i))
            throw new IllegalStateException((new StringBuilder()).append("couldn't move cursor to position ").append(i).toString());
        if(view == null)
            view = newView(mContext, mCursor, viewgroup);
        bindView(view, mContext, mCursor);
        return view;
    }

    public boolean hasStableIds()
    {
        return true;
    }

    void init(Context context, Cursor cursor, int i)
    {
        boolean flag;
        int j;
        if((i & 1) == 1)
        {
            i |= 2;
            mAutoRequery = true;
        } else
        {
            mAutoRequery = false;
        }
        if(cursor != null)
            flag = true;
        else
            flag = false;
        mCursor = cursor;
        mDataValid = flag;
        mContext = context;
        if(flag)
            j = cursor.getColumnIndexOrThrow("_id");
        else
            j = -1;
        mRowIDColumn = j;
        if((i & 2) == 2)
        {
            mChangeObserver = new ChangeObserver();
            mDataSetObserver = new MyDataSetObserver(null);
        } else
        {
            mChangeObserver = null;
            mDataSetObserver = null;
        }
        if(flag)
        {
            if(mChangeObserver != null)
                cursor.registerContentObserver(mChangeObserver);
            if(mDataSetObserver != null)
                cursor.registerDataSetObserver(mDataSetObserver);
        }
    }

    protected void init(Context context, Cursor cursor, boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 2;
        init(context, cursor, i);
    }

    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewgroup)
    {
        return newView(context, cursor, viewgroup);
    }

    public abstract View newView(Context context, Cursor cursor, ViewGroup viewgroup);

    protected void onContentChanged()
    {
        if(mAutoRequery && mCursor != null && mCursor.isClosed() ^ true)
            mDataValid = mCursor.requery();
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charsequence)
    {
        if(mFilterQueryProvider != null)
            return mFilterQueryProvider.runQuery(charsequence);
        else
            return mCursor;
    }

    public void setDropDownViewTheme(android.content.res.Resources.Theme theme)
    {
        if(theme == null)
            mDropDownContext = null;
        else
        if(theme == mContext.getTheme())
            mDropDownContext = mContext;
        else
            mDropDownContext = new ContextThemeWrapper(mContext, theme);
    }

    public void setFilterQueryProvider(FilterQueryProvider filterqueryprovider)
    {
        mFilterQueryProvider = filterqueryprovider;
    }

    public Cursor swapCursor(Cursor cursor)
    {
        if(cursor == mCursor)
            return null;
        Cursor cursor1 = mCursor;
        if(cursor1 != null)
        {
            if(mChangeObserver != null)
                cursor1.unregisterContentObserver(mChangeObserver);
            if(mDataSetObserver != null)
                cursor1.unregisterDataSetObserver(mDataSetObserver);
        }
        mCursor = cursor;
        if(cursor != null)
        {
            if(mChangeObserver != null)
                cursor.registerContentObserver(mChangeObserver);
            if(mDataSetObserver != null)
                cursor.registerDataSetObserver(mDataSetObserver);
            mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
            mDataValid = true;
            notifyDataSetChanged();
        } else
        {
            mRowIDColumn = -1;
            mDataValid = false;
            notifyDataSetInvalidated();
        }
        return cursor1;
    }

    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
    protected boolean mAutoRequery;
    protected ChangeObserver mChangeObserver;
    protected Context mContext;
    protected Cursor mCursor;
    protected CursorFilter mCursorFilter;
    protected DataSetObserver mDataSetObserver;
    protected boolean mDataValid;
    protected Context mDropDownContext;
    protected FilterQueryProvider mFilterQueryProvider;
    protected int mRowIDColumn;
}
