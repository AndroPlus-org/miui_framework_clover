// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;
import java.util.function.Predicate;

public class WatchHeaderListView extends ListView
{
    private static class WatchHeaderListAdapter extends HeaderViewListAdapter
    {

        private int getTopPanelCount()
        {
            int i;
            if(mTopPanel == null || mTopPanel.getVisibility() == 8)
                i = 0;
            else
                i = 1;
            return i;
        }

        public boolean areAllItemsEnabled()
        {
            boolean flag = false;
            if(getTopPanelCount() == 0)
                flag = super.areAllItemsEnabled();
            return flag;
        }

        public int getCount()
        {
            return super.getCount() + getTopPanelCount();
        }

        public Object getItem(int i)
        {
            int j = getTopPanelCount();
            Object obj;
            if(i < j)
                obj = null;
            else
                obj = super.getItem(i - j);
            return obj;
        }

        public long getItemId(int i)
        {
            int j = getHeadersCount() + getTopPanelCount();
            if(getWrappedAdapter() != null && i >= j)
            {
                i -= j;
                if(i < getWrappedAdapter().getCount())
                    return getWrappedAdapter().getItemId(i);
            }
            return -1L;
        }

        public int getItemViewType(int i)
        {
            int j = getHeadersCount() + getTopPanelCount();
            if(getWrappedAdapter() != null && i >= j)
            {
                i -= j;
                if(i < getWrappedAdapter().getCount())
                    return getWrappedAdapter().getItemViewType(i);
            }
            return -2;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            int j = getTopPanelCount();
            if(i < j)
                view = mTopPanel;
            else
                view = super.getView(i - j, view, viewgroup);
            return view;
        }

        public boolean isEnabled(int i)
        {
            int j = getTopPanelCount();
            boolean flag;
            if(i < j)
                flag = false;
            else
                flag = super.isEnabled(i - j);
            return flag;
        }

        public void setTopPanel(View view)
        {
            mTopPanel = view;
        }

        private View mTopPanel;

        public WatchHeaderListAdapter(ArrayList arraylist, ArrayList arraylist1, ListAdapter listadapter)
        {
            super(arraylist, arraylist1, listadapter);
        }
    }


    public WatchHeaderListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public WatchHeaderListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public WatchHeaderListView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    private void wrapAdapterIfNecessary()
    {
        ListAdapter listadapter = getAdapter();
        if(listadapter != null && mTopPanel != null)
        {
            if(!(listadapter instanceof WatchHeaderListAdapter))
                wrapHeaderListAdapterInternal();
            ((WatchHeaderListAdapter)getAdapter()).setTopPanel(mTopPanel);
            dispatchDataSetObserverOnChangedInternal();
        }
    }

    public void addView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(mTopPanel == null)
        {
            setTopPanel(view);
            return;
        } else
        {
            throw new IllegalStateException("WatchHeaderListView can host only one header");
        }
    }

    protected View findViewByPredicateTraversal(Predicate predicate, View view)
    {
        View view1 = super.findViewByPredicateTraversal(predicate, view);
        if(view1 == null && mTopPanel != null && mTopPanel != view && mTopPanel.isRootNamespace() ^ true)
            return mTopPanel.findViewByPredicate(predicate);
        else
            return view1;
    }

    protected View findViewTraversal(int i)
    {
        View view = super.findViewTraversal(i);
        if(view == null && mTopPanel != null && mTopPanel.isRootNamespace() ^ true)
            return mTopPanel.findViewById(i);
        else
            return view;
    }

    protected View findViewWithTagTraversal(Object obj)
    {
        View view = super.findViewWithTagTraversal(obj);
        if(view == null && mTopPanel != null && mTopPanel.isRootNamespace() ^ true)
            return mTopPanel.findViewWithTag(obj);
        else
            return view;
    }

    public int getHeaderViewsCount()
    {
        int i;
        if(mTopPanel == null)
        {
            i = super.getHeaderViewsCount();
        } else
        {
            int j = super.getHeaderViewsCount();
            if(mTopPanel.getVisibility() == 8)
                i = 0;
            else
                i = 1;
            i += j;
        }
        return i;
    }

    public void setAdapter(ListAdapter listadapter)
    {
        super.setAdapter(listadapter);
        wrapAdapterIfNecessary();
    }

    public void setTopPanel(View view)
    {
        mTopPanel = view;
        wrapAdapterIfNecessary();
    }

    protected HeaderViewListAdapter wrapHeaderListAdapterInternal(ArrayList arraylist, ArrayList arraylist1, ListAdapter listadapter)
    {
        return new WatchHeaderListAdapter(arraylist, arraylist1, listadapter);
    }

    private View mTopPanel;
}
