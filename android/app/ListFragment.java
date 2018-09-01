// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.*;

// Referenced classes of package android.app:
//            Fragment

public class ListFragment extends Fragment
{

    public ListFragment()
    {
    }

    private void ensureList()
    {
        if(mList != null)
            return;
        Object obj = getView();
        if(obj == null)
            throw new IllegalStateException("Content view not yet created");
        if(obj instanceof ListView)
        {
            mList = (ListView)obj;
        } else
        {
            mStandardEmptyView = (TextView)((View) (obj)).findViewById(0x10202bd);
            if(mStandardEmptyView == null)
                mEmptyView = ((View) (obj)).findViewById(0x1020004);
            else
                mStandardEmptyView.setVisibility(8);
            mProgressContainer = ((View) (obj)).findViewById(0x1020393);
            mListContainer = ((View) (obj)).findViewById(0x10202df);
            obj = ((View) (obj)).findViewById(0x102000a);
            if(!(obj instanceof ListView))
                throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
            mList = (ListView)obj;
            if(mList == null)
                throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
            if(mEmptyView != null)
                mList.setEmptyView(mEmptyView);
            else
            if(mEmptyText != null)
            {
                mStandardEmptyView.setText(mEmptyText);
                mList.setEmptyView(mStandardEmptyView);
            }
        }
        mListShown = true;
        mList.setOnItemClickListener(mOnClickListener);
        if(mAdapter != null)
        {
            obj = mAdapter;
            mAdapter = null;
            setListAdapter(((ListAdapter) (obj)));
        } else
        if(mProgressContainer != null)
            setListShown(false, false);
        mHandler.post(mRequestFocus);
    }

    private void setListShown(boolean flag, boolean flag1)
    {
        ensureList();
        if(mProgressContainer == null)
            throw new IllegalStateException("Can't be used with a custom content view");
        if(mListShown == flag)
            return;
        mListShown = flag;
        if(flag)
        {
            if(flag1)
            {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 0x10a0001));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 0x10a0000));
            } else
            {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(8);
            mListContainer.setVisibility(0);
        } else
        {
            if(flag1)
            {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 0x10a0000));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 0x10a0001));
            } else
            {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(0);
            mListContainer.setVisibility(8);
        }
    }

    public ListAdapter getListAdapter()
    {
        return mAdapter;
    }

    public ListView getListView()
    {
        ensureList();
        return mList;
    }

    public long getSelectedItemId()
    {
        ensureList();
        return mList.getSelectedItemId();
    }

    public int getSelectedItemPosition()
    {
        ensureList();
        return mList.getSelectedItemPosition();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x1090014, viewgroup, false);
    }

    public void onDestroyView()
    {
        mHandler.removeCallbacks(mRequestFocus);
        mList = null;
        mListShown = false;
        mListContainer = null;
        mProgressContainer = null;
        mEmptyView = null;
        mStandardEmptyView = null;
        super.onDestroyView();
    }

    public void onListItemClick(ListView listview, View view, int i, long l)
    {
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        ensureList();
    }

    public void setEmptyText(CharSequence charsequence)
    {
        ensureList();
        if(mStandardEmptyView == null)
            throw new IllegalStateException("Can't be used with a custom content view");
        mStandardEmptyView.setText(charsequence);
        if(mEmptyText == null)
            mList.setEmptyView(mStandardEmptyView);
        mEmptyText = charsequence;
    }

    public void setListAdapter(ListAdapter listadapter)
    {
        boolean flag;
        if(mAdapter != null)
            flag = true;
        else
            flag = false;
        mAdapter = listadapter;
        if(mList != null)
        {
            mList.setAdapter(listadapter);
            if(!mListShown && flag ^ true)
            {
                boolean flag1;
                if(getView().getWindowToken() != null)
                    flag1 = true;
                else
                    flag1 = false;
                setListShown(true, flag1);
            }
        }
    }

    public void setListShown(boolean flag)
    {
        setListShown(flag, true);
    }

    public void setListShownNoAnimation(boolean flag)
    {
        setListShown(flag, false);
    }

    public void setSelection(int i)
    {
        ensureList();
        mList.setSelection(i);
    }

    ListAdapter mAdapter;
    CharSequence mEmptyText;
    View mEmptyView;
    private final Handler mHandler = new Handler();
    ListView mList;
    View mListContainer;
    boolean mListShown;
    private final android.widget.AdapterView.OnItemClickListener mOnClickListener = new android.widget.AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            onListItemClick((ListView)adapterview, view, i, l);
        }

        final ListFragment this$0;

            
            {
                this$0 = ListFragment.this;
                super();
            }
    }
;
    View mProgressContainer;
    private final Runnable mRequestFocus = new Runnable() {

        public void run()
        {
            mList.focusableViewAvailable(mList);
        }

        final ListFragment this$0;

            
            {
                this$0 = ListFragment.this;
                super();
            }
    }
;
    TextView mStandardEmptyView;
}
