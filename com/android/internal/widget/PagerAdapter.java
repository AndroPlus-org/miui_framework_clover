// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter
{

    public PagerAdapter()
    {
        mObservable = new DataSetObservable();
    }

    public void destroyItem(View view, int i, Object obj)
    {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        destroyItem(((View) (viewgroup)), i, obj);
    }

    public void finishUpdate(View view)
    {
    }

    public void finishUpdate(ViewGroup viewgroup)
    {
        finishUpdate(((View) (viewgroup)));
    }

    public abstract int getCount();

    public int getItemPosition(Object obj)
    {
        return -1;
    }

    public CharSequence getPageTitle(int i)
    {
        return null;
    }

    public float getPageWidth(int i)
    {
        return 1.0F;
    }

    public Object instantiateItem(View view, int i)
    {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object instantiateItem(ViewGroup viewgroup, int i)
    {
        return instantiateItem(((View) (viewgroup)), i);
    }

    public abstract boolean isViewFromObject(View view, Object obj);

    public void notifyDataSetChanged()
    {
        mObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver datasetobserver)
    {
        mObservable.registerObserver(datasetobserver);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classloader)
    {
    }

    public Parcelable saveState()
    {
        return null;
    }

    public void setPrimaryItem(View view, int i, Object obj)
    {
    }

    public void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        setPrimaryItem(((View) (viewgroup)), i, obj);
    }

    public void startUpdate(View view)
    {
    }

    public void startUpdate(ViewGroup viewgroup)
    {
        startUpdate(((View) (viewgroup)));
    }

    public void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
        mObservable.unregisterObserver(datasetobserver);
    }

    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private DataSetObservable mObservable;
}
