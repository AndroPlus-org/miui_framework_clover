// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

// Referenced classes of package com.android.internal.widget:
//            AccountItemView

public class AccountViewAdapter extends BaseAdapter
{
    public static class AccountElements
    {

        public Drawable getDrawable()
        {
            return mDrawable;
        }

        public int getIcon()
        {
            return mIcon;
        }

        public String getName()
        {
            return mName;
        }

        public String getNumber()
        {
            return mNumber;
        }

        private Drawable mDrawable;
        private int mIcon;
        private String mName;
        private String mNumber;

        private AccountElements(int i, Drawable drawable, String s, String s1)
        {
            mIcon = i;
            mDrawable = drawable;
            mName = s;
            mNumber = s1;
        }

        public AccountElements(int i, String s, String s1)
        {
            this(i, null, s, s1);
        }

        public AccountElements(Drawable drawable, String s, String s1)
        {
            this(0, drawable, s, s1);
        }
    }


    public AccountViewAdapter(Context context, List list)
    {
        mContext = context;
        mData = list;
    }

    public int getCount()
    {
        return mData.size();
    }

    public Object getItem(int i)
    {
        return mData.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
            view = new AccountItemView(mContext);
        else
            view = (AccountItemView)view;
        view.setViewItem((AccountElements)getItem(i));
        return view;
    }

    public void updateData(List list)
    {
        mData = list;
        notifyDataSetChanged();
    }

    private Context mContext;
    private List mData;
}
