// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.database.*;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.widget:
//            BaseExpandableListAdapter, Filterable, CursorFilter, FilterQueryProvider, 
//            Filter

public abstract class CursorTreeAdapter extends BaseExpandableListAdapter
    implements Filterable, CursorFilter.CursorFilterClient
{
    class MyCursorHelper
    {

        static Cursor _2D_get0(MyCursorHelper mycursorhelper)
        {
            return mycursorhelper.mCursor;
        }

        static boolean _2D_set0(MyCursorHelper mycursorhelper, boolean flag)
        {
            mycursorhelper.mDataValid = flag;
            return flag;
        }

        void changeCursor(Cursor cursor, boolean flag)
        {
            if(cursor == mCursor)
                return;
            deactivate();
            mCursor = cursor;
            if(cursor != null)
            {
                cursor.registerContentObserver(mContentObserver);
                cursor.registerDataSetObserver(mDataSetObserver);
                mRowIDColumn = cursor.getColumnIndex("_id");
                mDataValid = true;
                notifyDataSetChanged(flag);
            } else
            {
                mRowIDColumn = -1;
                mDataValid = false;
                notifyDataSetInvalidated();
            }
        }

        void deactivate()
        {
            if(mCursor == null)
            {
                return;
            } else
            {
                mCursor.unregisterContentObserver(mContentObserver);
                mCursor.unregisterDataSetObserver(mDataSetObserver);
                mCursor.close();
                mCursor = null;
                return;
            }
        }

        int getCount()
        {
            if(mDataValid && mCursor != null)
                return mCursor.getCount();
            else
                return 0;
        }

        Cursor getCursor()
        {
            return mCursor;
        }

        long getId(int i)
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

        boolean isValid()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mDataValid)
            {
                flag1 = flag;
                if(mCursor != null)
                    flag1 = true;
            }
            return flag1;
        }

        Cursor moveTo(int i)
        {
            if(mDataValid && mCursor != null && mCursor.moveToPosition(i))
                return mCursor;
            else
                return null;
        }

        private MyContentObserver mContentObserver;
        private Cursor mCursor;
        private MyDataSetObserver mDataSetObserver;
        private boolean mDataValid;
        private int mRowIDColumn;
        final CursorTreeAdapter this$0;

        MyCursorHelper(Cursor cursor)
        {
            this$0 = CursorTreeAdapter.this;
            super();
            boolean flag;
            int i;
            if(cursor != null)
                flag = true;
            else
                flag = false;
            mCursor = cursor;
            mDataValid = flag;
            if(flag)
                i = cursor.getColumnIndex("_id");
            else
                i = -1;
            mRowIDColumn = i;
            mContentObserver = new MyContentObserver();
            mDataSetObserver = new MyDataSetObserver(null);
            if(flag)
            {
                cursor.registerContentObserver(mContentObserver);
                cursor.registerDataSetObserver(mDataSetObserver);
            }
        }
    }

    private class MyCursorHelper.MyContentObserver extends ContentObserver
    {

        public boolean deliverSelfNotifications()
        {
            return true;
        }

        public void onChange(boolean flag)
        {
            if(CursorTreeAdapter._2D_get0(_fld0) && MyCursorHelper._2D_get0(MyCursorHelper.this) != null && MyCursorHelper._2D_get0(MyCursorHelper.this).isClosed() ^ true)
                MyCursorHelper._2D_set0(MyCursorHelper.this, MyCursorHelper._2D_get0(MyCursorHelper.this).requery());
        }

        final MyCursorHelper this$1;

        public MyCursorHelper.MyContentObserver()
        {
            this$1 = MyCursorHelper.this;
            super(CursorTreeAdapter._2D_get1(_fld0));
        }
    }

    private class MyCursorHelper.MyDataSetObserver extends DataSetObserver
    {

        public void onChanged()
        {
            MyCursorHelper._2D_set0(MyCursorHelper.this, true);
            notifyDataSetChanged();
        }

        public void onInvalidated()
        {
            MyCursorHelper._2D_set0(MyCursorHelper.this, false);
            notifyDataSetInvalidated();
        }

        final MyCursorHelper this$1;

        private MyCursorHelper.MyDataSetObserver()
        {
            this$1 = MyCursorHelper.this;
            super();
        }

        MyCursorHelper.MyDataSetObserver(MyCursorHelper.MyDataSetObserver mydatasetobserver)
        {
            this();
        }
    }


    static boolean _2D_get0(CursorTreeAdapter cursortreeadapter)
    {
        return cursortreeadapter.mAutoRequery;
    }

    static Handler _2D_get1(CursorTreeAdapter cursortreeadapter)
    {
        return cursortreeadapter.mHandler;
    }

    public CursorTreeAdapter(Cursor cursor, Context context)
    {
        init(cursor, context, true);
    }

    public CursorTreeAdapter(Cursor cursor, Context context, boolean flag)
    {
        init(cursor, context, flag);
    }

    private void init(Cursor cursor, Context context, boolean flag)
    {
        mContext = context;
        mHandler = new Handler();
        mAutoRequery = flag;
        mGroupCursorHelper = new MyCursorHelper(cursor);
        mChildrenCursorHelpers = new SparseArray();
    }

    private void releaseCursorHelpers()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mChildrenCursorHelpers.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        ((MyCursorHelper)mChildrenCursorHelpers.valueAt(i)).deactivate();
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        mChildrenCursorHelpers.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected abstract void bindChildView(View view, Context context, Cursor cursor, boolean flag);

    protected abstract void bindGroupView(View view, Context context, Cursor cursor, boolean flag);

    public void changeCursor(Cursor cursor)
    {
        mGroupCursorHelper.changeCursor(cursor, true);
    }

    public volatile CharSequence convertToString(Cursor cursor)
    {
        return convertToString(cursor);
    }

    public String convertToString(Cursor cursor)
    {
        if(cursor == null)
            cursor = "";
        else
            cursor = cursor.toString();
        return cursor;
    }

    void deactivateChildrenCursorHelper(int i)
    {
        this;
        JVM INSTR monitorenter ;
        MyCursorHelper mycursorhelper = getChildrenCursorHelper(i, true);
        mChildrenCursorHelpers.remove(i);
        mycursorhelper.deactivate();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor getChild(int i, int j)
    {
        return getChildrenCursorHelper(i, true).moveTo(j);
    }

    public volatile Object getChild(int i, int j)
    {
        return getChild(i, j);
    }

    public long getChildId(int i, int j)
    {
        return getChildrenCursorHelper(i, true).getId(j);
    }

    public View getChildView(int i, int j, boolean flag, View view, ViewGroup viewgroup)
    {
        Cursor cursor = getChildrenCursorHelper(i, true).moveTo(j);
        if(cursor == null)
            throw new IllegalStateException("this should only be called when the cursor is valid");
        if(view == null)
            view = newChildView(mContext, cursor, flag, viewgroup);
        bindChildView(view, mContext, cursor, flag);
        return view;
    }

    public int getChildrenCount(int i)
    {
        MyCursorHelper mycursorhelper = getChildrenCursorHelper(i, true);
        if(mGroupCursorHelper.isValid() && mycursorhelper != null)
            i = mycursorhelper.getCount();
        else
            i = 0;
        return i;
    }

    protected abstract Cursor getChildrenCursor(Cursor cursor);

    MyCursorHelper getChildrenCursorHelper(int i, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        MyCursorHelper mycursorhelper = (MyCursorHelper)mChildrenCursorHelpers.get(i);
        Object obj;
        obj = mycursorhelper;
        if(mycursorhelper != null)
            break MISSING_BLOCK_LABEL_74;
        obj = mGroupCursorHelper.moveTo(i);
        if(obj != null)
            break MISSING_BLOCK_LABEL_40;
        this;
        JVM INSTR monitorexit ;
        return null;
        Cursor cursor = getChildrenCursor(mGroupCursorHelper.getCursor());
        obj = JVM INSTR new #10  <Class CursorTreeAdapter$MyCursorHelper>;
        ((MyCursorHelper) (obj)).this. MyCursorHelper(cursor);
        mChildrenCursorHelpers.put(i, obj);
        this;
        JVM INSTR monitorexit ;
        return ((MyCursorHelper) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor getCursor()
    {
        return mGroupCursorHelper.getCursor();
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

    public Cursor getGroup(int i)
    {
        return mGroupCursorHelper.moveTo(i);
    }

    public volatile Object getGroup(int i)
    {
        return getGroup(i);
    }

    public int getGroupCount()
    {
        return mGroupCursorHelper.getCount();
    }

    public long getGroupId(int i)
    {
        return mGroupCursorHelper.getId(i);
    }

    public View getGroupView(int i, boolean flag, View view, ViewGroup viewgroup)
    {
        Cursor cursor = mGroupCursorHelper.moveTo(i);
        if(cursor == null)
            throw new IllegalStateException("this should only be called when the cursor is valid");
        if(view == null)
            view = newGroupView(mContext, cursor, flag, viewgroup);
        bindGroupView(view, mContext, cursor, flag);
        return view;
    }

    public boolean hasStableIds()
    {
        return true;
    }

    public boolean isChildSelectable(int i, int j)
    {
        return true;
    }

    protected abstract View newChildView(Context context, Cursor cursor, boolean flag, ViewGroup viewgroup);

    protected abstract View newGroupView(Context context, Cursor cursor, boolean flag, ViewGroup viewgroup);

    public void notifyDataSetChanged()
    {
        notifyDataSetChanged(true);
    }

    public void notifyDataSetChanged(boolean flag)
    {
        if(flag)
            releaseCursorHelpers();
        super.notifyDataSetChanged();
    }

    public void notifyDataSetInvalidated()
    {
        releaseCursorHelpers();
        super.notifyDataSetInvalidated();
    }

    public void onGroupCollapsed(int i)
    {
        deactivateChildrenCursorHelper(i);
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charsequence)
    {
        if(mFilterQueryProvider != null)
            return mFilterQueryProvider.runQuery(charsequence);
        else
            return mGroupCursorHelper.getCursor();
    }

    public void setChildrenCursor(int i, Cursor cursor)
    {
        getChildrenCursorHelper(i, false).changeCursor(cursor, false);
    }

    public void setFilterQueryProvider(FilterQueryProvider filterqueryprovider)
    {
        mFilterQueryProvider = filterqueryprovider;
    }

    public void setGroupCursor(Cursor cursor)
    {
        mGroupCursorHelper.changeCursor(cursor, false);
    }

    private boolean mAutoRequery;
    SparseArray mChildrenCursorHelpers;
    private Context mContext;
    CursorFilter mCursorFilter;
    FilterQueryProvider mFilterQueryProvider;
    MyCursorHelper mGroupCursorHelper;
    private Handler mHandler;
}
