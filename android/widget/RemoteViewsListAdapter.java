// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.widget:
//            BaseAdapter, RemoteViews

public class RemoteViewsListAdapter extends BaseAdapter
{

    public RemoteViewsListAdapter(Context context, ArrayList arraylist, int i)
    {
        mViewTypes = new ArrayList();
        mContext = context;
        mRemoteViewsList = arraylist;
        mViewTypeCount = i;
        init();
    }

    private void init()
    {
        if(mRemoteViewsList == null)
            return;
        mViewTypes.clear();
        Iterator iterator = mRemoteViewsList.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            RemoteViews remoteviews = (RemoteViews)iterator.next();
            if(!mViewTypes.contains(Integer.valueOf(remoteviews.getLayoutId())))
                mViewTypes.add(Integer.valueOf(remoteviews.getLayoutId()));
        } while(true);
        if(mViewTypes.size() > mViewTypeCount || mViewTypeCount < 1)
            throw new RuntimeException("Invalid view type count -- view type count must be >= 1and must be as large as the total number of distinct view types");
        else
            return;
    }

    public int getCount()
    {
        if(mRemoteViewsList != null)
            return mRemoteViewsList.size();
        else
            return 0;
    }

    public Object getItem(int i)
    {
        return null;
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public int getItemViewType(int i)
    {
        if(i < getCount())
        {
            i = ((RemoteViews)mRemoteViewsList.get(i)).getLayoutId();
            return mViewTypes.indexOf(Integer.valueOf(i));
        } else
        {
            return 0;
        }
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(i < getCount())
        {
            RemoteViews remoteviews = (RemoteViews)mRemoteViewsList.get(i);
            remoteviews.setIsWidgetCollectionChild(true);
            if(view != null && remoteviews != null && view.getId() == remoteviews.getLayoutId())
            {
                viewgroup = view;
                remoteviews.reapply(mContext, view);
            } else
            {
                viewgroup = remoteviews.apply(mContext, viewgroup);
            }
            return viewgroup;
        } else
        {
            return null;
        }
    }

    public int getViewTypeCount()
    {
        return mViewTypeCount;
    }

    public boolean hasStableIds()
    {
        return false;
    }

    public void setViewsList(ArrayList arraylist)
    {
        mRemoteViewsList = arraylist;
        init();
        notifyDataSetChanged();
    }

    private Context mContext;
    private ArrayList mRemoteViewsList;
    private int mViewTypeCount;
    private ArrayList mViewTypes;
}
