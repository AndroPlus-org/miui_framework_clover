// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.widget:
//            ListAdapter, SpinnerAdapter

public abstract class BaseAdapter
    implements ListAdapter, SpinnerAdapter
{

    public BaseAdapter()
    {
    }

    public boolean areAllItemsEnabled()
    {
        return true;
    }

    public CharSequence[] getAutofillOptions()
    {
        return mAutofillOptions;
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        return getView(i, view, viewgroup);
    }

    public int getItemViewType(int i)
    {
        return 0;
    }

    public int getViewTypeCount()
    {
        return 1;
    }

    public boolean hasStableIds()
    {
        return false;
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        if(getCount() == 0)
            flag = true;
        return flag;
    }

    public boolean isEnabled(int i)
    {
        return true;
    }

    public void notifyDataSetChanged()
    {
        mDataSetObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated()
    {
        mDataSetObservable.notifyInvalidated();
    }

    public void registerDataSetObserver(DataSetObserver datasetobserver)
    {
        mDataSetObservable.registerObserver(datasetobserver);
    }

    public transient void setAutofillOptions(CharSequence acharsequence[])
    {
        mAutofillOptions = acharsequence;
    }

    public void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
        mDataSetObservable.unregisterObserver(datasetobserver);
    }

    private CharSequence mAutofillOptions[];
    private final DataSetObservable mDataSetObservable = new DataSetObservable();
}
