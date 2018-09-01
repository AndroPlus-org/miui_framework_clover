// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.*;
import android.widget.*;
import java.util.*;

// Referenced classes of package com.android.internal.app:
//            LocalePicker, LocaleStore, SuggestedLocaleAdapter

public class LocalePickerWithRegion extends ListFragment
    implements android.widget.SearchView.OnQueryTextListener
{
    public static interface LocaleSelectedListener
    {

        public abstract void onLocaleSelected(LocaleStore.LocaleInfo localeinfo);
    }


    public LocalePickerWithRegion()
    {
        mTranslatedOnly = false;
        mSearchView = null;
        mPreviousSearch = null;
        mPreviousSearchHadFocus = false;
        mFirstVisiblePosition = 0;
        mTopDistance = 0;
    }

    private static LocalePickerWithRegion createCountryPicker(Context context, LocaleSelectedListener localeselectedlistener, LocaleStore.LocaleInfo localeinfo, boolean flag)
    {
        LocalePickerWithRegion localepickerwithregion = new LocalePickerWithRegion();
        if(localepickerwithregion.setListener(context, localeselectedlistener, localeinfo, flag))
            context = localepickerwithregion;
        else
            context = null;
        return context;
    }

    public static LocalePickerWithRegion createLanguagePicker(Context context, LocaleSelectedListener localeselectedlistener, boolean flag)
    {
        LocalePickerWithRegion localepickerwithregion = new LocalePickerWithRegion();
        localepickerwithregion.setListener(context, localeselectedlistener, null, flag);
        return localepickerwithregion;
    }

    private void returnToParentFrame()
    {
        getFragmentManager().popBackStack("localeListEditor", 1);
    }

    private boolean setListener(Context context, LocaleSelectedListener localeselectedlistener, LocaleStore.LocaleInfo localeinfo, boolean flag)
    {
        mParentLocale = localeinfo;
        mListener = localeselectedlistener;
        mTranslatedOnly = flag;
        setRetainInstance(true);
        HashSet hashset = new HashSet();
        if(!flag)
            Collections.addAll(hashset, LocalePicker.getLocales().toLanguageTags().split(","));
        if(localeinfo != null)
        {
            mLocaleList = LocaleStore.getLevelLocales(context, hashset, localeinfo, flag);
            if(mLocaleList.size() <= 1)
            {
                if(localeselectedlistener != null && mLocaleList.size() == 1)
                    localeselectedlistener.onLocaleSelected((LocaleStore.LocaleInfo)mLocaleList.iterator().next());
                return false;
            }
        } else
        {
            mLocaleList = LocaleStore.getLevelLocales(context, hashset, null, flag);
        }
        return true;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        if(mLocaleList == null)
        {
            returnToParentFrame();
            return;
        }
        boolean flag;
        if(mParentLocale != null)
            flag = true;
        else
            flag = false;
        if(flag)
            bundle = mParentLocale.getLocale();
        else
            bundle = Locale.getDefault();
        mAdapter = new SuggestedLocaleAdapter(mLocaleList, flag);
        bundle = new LocaleHelper.LocaleInfoComparator(bundle, flag);
        mAdapter.sort(bundle);
        setListAdapter(mAdapter);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        if(mParentLocale == null)
        {
            menuinflater.inflate(0x1140000, menu);
            menu = menu.findItem(0x10202e6);
            mSearchView = (SearchView)menu.getActionView();
            mSearchView.setQueryHint(getText(0x10405a3));
            mSearchView.setOnQueryTextListener(this);
            if(mPreviousSearch != null)
            {
                menu.expandActionView();
                mSearchView.setIconified(false);
                mSearchView.setActivated(true);
                if(mPreviousSearchHadFocus)
                    mSearchView.requestFocus();
                mSearchView.setQuery(mPreviousSearch, true);
            } else
            {
                mSearchView.setQuery(null, false);
            }
            getListView().setSelectionFromTop(mFirstVisiblePosition, mTopDistance);
        }
    }

    public void onListItemClick(ListView listview, View view, int i, long l)
    {
        listview = (LocaleStore.LocaleInfo)getListAdapter().getItem(i);
        if(listview.getParent() != null)
        {
            if(mListener != null)
                mListener.onLocaleSelected(listview);
            returnToParentFrame();
        } else
        {
            listview = createCountryPicker(getContext(), mListener, listview, mTranslatedOnly);
            if(listview != null)
                getFragmentManager().beginTransaction().setTransition(4097).replace(getId(), listview).addToBackStack(null).commit();
            else
                returnToParentFrame();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        switch(menuitem.getItemId())
        {
        default:
            return super.onOptionsItemSelected(menuitem);

        case 16908332: 
            getFragmentManager().popBackStack();
            break;
        }
        return true;
    }

    public void onPause()
    {
        int i = 0;
        super.onPause();
        ListView listview;
        View view;
        if(mSearchView != null)
        {
            mPreviousSearchHadFocus = mSearchView.hasFocus();
            mPreviousSearch = mSearchView.getQuery();
        } else
        {
            mPreviousSearchHadFocus = false;
            mPreviousSearch = null;
        }
        listview = getListView();
        view = listview.getChildAt(0);
        mFirstVisiblePosition = listview.getFirstVisiblePosition();
        if(view != null)
            i = view.getTop() - listview.getPaddingTop();
        mTopDistance = i;
    }

    public boolean onQueryTextChange(String s)
    {
        if(mAdapter != null)
            mAdapter.getFilter().filter(s);
        return false;
    }

    public boolean onQueryTextSubmit(String s)
    {
        return false;
    }

    public void onResume()
    {
        super.onResume();
        if(mParentLocale != null)
            getActivity().setTitle(mParentLocale.getFullNameNative());
        else
            getActivity().setTitle(0x10402ef);
        getListView().requestFocus();
    }

    private static final String PARENT_FRAGMENT_NAME = "localeListEditor";
    private SuggestedLocaleAdapter mAdapter;
    private int mFirstVisiblePosition;
    private LocaleSelectedListener mListener;
    private Set mLocaleList;
    private LocaleStore.LocaleInfo mParentLocale;
    private CharSequence mPreviousSearch;
    private boolean mPreviousSearchHadFocus;
    private SearchView mSearchView;
    private int mTopDistance;
    private boolean mTranslatedOnly;
}
