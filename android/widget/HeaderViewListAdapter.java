// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.widget:
//            WrapperListAdapter, Filterable, ListAdapter, Filter

public class HeaderViewListAdapter
    implements WrapperListAdapter, Filterable
{

    public HeaderViewListAdapter(ArrayList arraylist, ArrayList arraylist1, ListAdapter listadapter)
    {
        mAdapter = listadapter;
        mIsFilterable = listadapter instanceof Filterable;
        boolean flag;
        if(arraylist == null)
            mHeaderViewInfos = EMPTY_INFO_LIST;
        else
            mHeaderViewInfos = arraylist;
        if(arraylist1 == null)
            mFooterViewInfos = EMPTY_INFO_LIST;
        else
            mFooterViewInfos = arraylist1;
        if(areAllListInfosSelectable(mHeaderViewInfos))
            flag = areAllListInfosSelectable(mFooterViewInfos);
        else
            flag = false;
        mAreAllFixedViewsSelectable = flag;
    }

    private boolean areAllListInfosSelectable(ArrayList arraylist)
    {
label0:
        {
            if(arraylist == null)
                break label0;
            arraylist = arraylist.iterator();
            do
                if(!arraylist.hasNext())
                    break label0;
            while(((ListView.FixedViewInfo)arraylist.next()).isSelectable);
            return false;
        }
        return true;
    }

    public boolean areAllItemsEnabled()
    {
        if(mAdapter != null)
        {
            boolean flag;
            if(mAreAllFixedViewsSelectable)
                flag = mAdapter.areAllItemsEnabled();
            else
                flag = false;
            return flag;
        } else
        {
            return true;
        }
    }

    public int getCount()
    {
        if(mAdapter != null)
            return getFootersCount() + getHeadersCount() + mAdapter.getCount();
        else
            return getFootersCount() + getHeadersCount();
    }

    public Filter getFilter()
    {
        if(mIsFilterable)
            return ((Filterable)mAdapter).getFilter();
        else
            return null;
    }

    public int getFootersCount()
    {
        return mFooterViewInfos.size();
    }

    public int getHeadersCount()
    {
        return mHeaderViewInfos.size();
    }

    public Object getItem(int i)
    {
        int j = getHeadersCount();
        if(i < j)
            return ((ListView.FixedViewInfo)mHeaderViewInfos.get(i)).data;
        int l = i - j;
        i = 0;
        if(mAdapter != null)
        {
            int k = mAdapter.getCount();
            i = k;
            if(l < k)
                return mAdapter.getItem(l);
        }
        return ((ListView.FixedViewInfo)mFooterViewInfos.get(l - i)).data;
    }

    public long getItemId(int i)
    {
        int j = getHeadersCount();
        if(mAdapter != null && i >= j)
        {
            i -= j;
            if(i < mAdapter.getCount())
                return mAdapter.getItemId(i);
        }
        return -1L;
    }

    public int getItemViewType(int i)
    {
        int j = getHeadersCount();
        if(mAdapter != null && i >= j)
        {
            i -= j;
            if(i < mAdapter.getCount())
                return mAdapter.getItemViewType(i);
        }
        return -2;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        int j = getHeadersCount();
        if(i < j)
            return ((ListView.FixedViewInfo)mHeaderViewInfos.get(i)).view;
        int l = i - j;
        i = 0;
        if(mAdapter != null)
        {
            int k = mAdapter.getCount();
            i = k;
            if(l < k)
                return mAdapter.getView(l, view, viewgroup);
        }
        return ((ListView.FixedViewInfo)mFooterViewInfos.get(l - i)).view;
    }

    public int getViewTypeCount()
    {
        if(mAdapter != null)
            return mAdapter.getViewTypeCount();
        else
            return 1;
    }

    public ListAdapter getWrappedAdapter()
    {
        return mAdapter;
    }

    public boolean hasStableIds()
    {
        if(mAdapter != null)
            return mAdapter.hasStableIds();
        else
            return false;
    }

    public boolean isEmpty()
    {
        boolean flag;
        if(mAdapter != null)
            flag = mAdapter.isEmpty();
        else
            flag = true;
        return flag;
    }

    public boolean isEnabled(int i)
    {
        int j = getHeadersCount();
        if(i < j)
            return ((ListView.FixedViewInfo)mHeaderViewInfos.get(i)).isSelectable;
        int l = i - j;
        i = 0;
        if(mAdapter != null)
        {
            int k = mAdapter.getCount();
            i = k;
            if(l < k)
                return mAdapter.isEnabled(l);
        }
        return ((ListView.FixedViewInfo)mFooterViewInfos.get(l - i)).isSelectable;
    }

    public void registerDataSetObserver(DataSetObserver datasetobserver)
    {
        if(mAdapter != null)
            mAdapter.registerDataSetObserver(datasetobserver);
    }

    public boolean removeFooter(View view)
    {
        boolean flag = false;
        for(int i = 0; i < mFooterViewInfos.size(); i++)
            if(((ListView.FixedViewInfo)mFooterViewInfos.get(i)).view == view)
            {
                mFooterViewInfos.remove(i);
                if(areAllListInfosSelectable(mHeaderViewInfos))
                    flag = areAllListInfosSelectable(mFooterViewInfos);
                mAreAllFixedViewsSelectable = flag;
                return true;
            }

        return false;
    }

    public boolean removeHeader(View view)
    {
        boolean flag = false;
        for(int i = 0; i < mHeaderViewInfos.size(); i++)
            if(((ListView.FixedViewInfo)mHeaderViewInfos.get(i)).view == view)
            {
                mHeaderViewInfos.remove(i);
                if(areAllListInfosSelectable(mHeaderViewInfos))
                    flag = areAllListInfosSelectable(mFooterViewInfos);
                mAreAllFixedViewsSelectable = flag;
                return true;
            }

        return false;
    }

    public void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
        if(mAdapter != null)
            mAdapter.unregisterDataSetObserver(datasetobserver);
    }

    static final ArrayList EMPTY_INFO_LIST = new ArrayList();
    private final ListAdapter mAdapter;
    boolean mAreAllFixedViewsSelectable;
    ArrayList mFooterViewInfos;
    ArrayList mHeaderViewInfos;
    private final boolean mIsFilterable;

}
