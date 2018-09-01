// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import java.util.*;

// Referenced classes of package android.preference:
//            PreferenceGroup, Preference

public class PreferenceGroupAdapter extends BaseAdapter
    implements Preference.OnPreferenceChangeInternalListener
{
    private static class PreferenceLayout
        implements Comparable
    {

        static String _2D_set0(PreferenceLayout preferencelayout, String s)
        {
            preferencelayout.name = s;
            return s;
        }

        static int _2D_set1(PreferenceLayout preferencelayout, int i)
        {
            preferencelayout.resId = i;
            return i;
        }

        static int _2D_set2(PreferenceLayout preferencelayout, int i)
        {
            preferencelayout.widgetResId = i;
            return i;
        }

        public int compareTo(PreferenceLayout preferencelayout)
        {
            int i = name.compareTo(preferencelayout.name);
            if(i == 0)
            {
                if(resId == preferencelayout.resId)
                {
                    if(widgetResId == preferencelayout.widgetResId)
                        return 0;
                    else
                        return widgetResId - preferencelayout.widgetResId;
                } else
                {
                    return resId - preferencelayout.resId;
                }
            } else
            {
                return i;
            }
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((PreferenceLayout)obj);
        }

        private String name;
        private int resId;
        private int widgetResId;

        private PreferenceLayout()
        {
        }

        PreferenceLayout(PreferenceLayout preferencelayout)
        {
            this();
        }
    }


    static void _2D_wrap0(PreferenceGroupAdapter preferencegroupadapter)
    {
        preferencegroupadapter.syncMyPreferences();
    }

    public PreferenceGroupAdapter(PreferenceGroup preferencegroup)
    {
        mTempPreferenceLayout = new PreferenceLayout(null);
        mHasReturnedViewTypeCount = false;
        mIsSyncing = false;
        mHandler = new Handler();
        mSyncRunnable = new Runnable() {

            public void run()
            {
                PreferenceGroupAdapter._2D_wrap0(PreferenceGroupAdapter.this);
            }

            final PreferenceGroupAdapter this$0;

            
            {
                this$0 = PreferenceGroupAdapter.this;
                super();
            }
        }
;
        mHighlightedPosition = -1;
        mPreferenceGroup = preferencegroup;
        mPreferenceGroup.setOnPreferenceChangeInternalListener(this);
        mPreferenceList = new ArrayList();
        mPreferenceLayouts = new ArrayList();
        syncMyPreferences();
    }

    private void addPreferenceClassName(Preference preference)
    {
        preference = createPreferenceLayout(preference, null);
        int i = Collections.binarySearch(mPreferenceLayouts, preference);
        if(i < 0)
            mPreferenceLayouts.add(i * -1 - 1, preference);
    }

    private PreferenceLayout createPreferenceLayout(Preference preference, PreferenceLayout preferencelayout)
    {
        if(preferencelayout == null)
            preferencelayout = new PreferenceLayout(null);
        PreferenceLayout._2D_set0(preferencelayout, preference.getClass().getName());
        PreferenceLayout._2D_set1(preferencelayout, preference.getLayoutResource());
        PreferenceLayout._2D_set2(preferencelayout, preference.getWidgetLayoutResource());
        return preferencelayout;
    }

    private void flattenPreferenceGroup(List list, PreferenceGroup preferencegroup)
    {
        preferencegroup.sortPreferences();
        int i = preferencegroup.getPreferenceCount();
        for(int j = 0; j < i; j++)
        {
            Preference preference = preferencegroup.getPreference(j);
            list.add(preference);
            if(!mHasReturnedViewTypeCount && preference.isRecycleEnabled())
                addPreferenceClassName(preference);
            if(preference instanceof PreferenceGroup)
            {
                PreferenceGroup preferencegroup1 = (PreferenceGroup)preference;
                if(preferencegroup1.isOnSameScreenAsChildren())
                    flattenPreferenceGroup(list, preferencegroup1);
            }
            preference.setOnPreferenceChangeInternalListener(this);
        }

    }

    private int getHighlightItemViewType()
    {
        return getViewTypeCount() - 1;
    }

    private void syncMyPreferences()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIsSyncing;
        if(!flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        mIsSyncing = true;
        this;
        JVM INSTR monitorexit ;
        ArrayList arraylist = new ArrayList(mPreferenceList.size());
        flattenPreferenceGroup(arraylist, mPreferenceGroup);
        mPreferenceList = arraylist;
        notifyDataSetChanged();
        this;
        JVM INSTR monitorenter ;
        mIsSyncing = false;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
        exception;
        throw exception;
    }

    public boolean areAllItemsEnabled()
    {
        return false;
    }

    public int getCount()
    {
        return mPreferenceList.size();
    }

    public Preference getItem(int i)
    {
        if(i < 0 || i >= getCount())
            return null;
        else
            return (Preference)mPreferenceList.get(i);
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public long getItemId(int i)
    {
        if(i < 0 || i >= getCount())
            return 0x8000000000000000L;
        else
            return getItem(i).getId();
    }

    public int getItemViewType(int i)
    {
        if(i == mHighlightedPosition)
            return getHighlightItemViewType();
        if(!mHasReturnedViewTypeCount)
            mHasReturnedViewTypeCount = true;
        Preference preference = getItem(i);
        if(!preference.isRecycleEnabled())
            return -1;
        mTempPreferenceLayout = createPreferenceLayout(preference, mTempPreferenceLayout);
        i = Collections.binarySearch(mPreferenceLayouts, mTempPreferenceLayout);
        if(i < 0)
            return -1;
        else
            return i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        Object obj = getItem(i);
        mTempPreferenceLayout = createPreferenceLayout(((Preference) (obj)), mTempPreferenceLayout);
        if(Collections.binarySearch(mPreferenceLayouts, mTempPreferenceLayout) < 0 || getItemViewType(i) == getHighlightItemViewType())
            view = null;
        obj = ((Preference) (obj)).getView(view, viewgroup);
        view = ((View) (obj));
        if(i == mHighlightedPosition)
        {
            view = ((View) (obj));
            if(mHighlightedDrawable != null)
            {
                view = new FrameLayout(viewgroup.getContext());
                view.setLayoutParams(sWrapperLayoutParams);
                view.setBackgroundDrawable(mHighlightedDrawable);
                view.addView(((View) (obj)));
            }
        }
        return view;
    }

    public int getViewTypeCount()
    {
        if(!mHasReturnedViewTypeCount)
            mHasReturnedViewTypeCount = true;
        return Math.max(1, mPreferenceLayouts.size()) + 1;
    }

    public boolean hasStableIds()
    {
        return true;
    }

    public boolean isEnabled(int i)
    {
        if(i < 0 || i >= getCount())
            return true;
        else
            return getItem(i).isSelectable();
    }

    public void onPreferenceChange(Preference preference)
    {
        notifyDataSetChanged();
    }

    public void onPreferenceHierarchyChange(Preference preference)
    {
        mHandler.removeCallbacks(mSyncRunnable);
        mHandler.post(mSyncRunnable);
    }

    public void setHighlighted(int i)
    {
        mHighlightedPosition = i;
    }

    public void setHighlightedDrawable(Drawable drawable)
    {
        mHighlightedDrawable = drawable;
    }

    private static final String TAG = "PreferenceGroupAdapter";
    private static android.view.ViewGroup.LayoutParams sWrapperLayoutParams = new android.view.ViewGroup.LayoutParams(-1, -2);
    private Handler mHandler;
    private boolean mHasReturnedViewTypeCount;
    private Drawable mHighlightedDrawable;
    private int mHighlightedPosition;
    private volatile boolean mIsSyncing;
    private PreferenceGroup mPreferenceGroup;
    private ArrayList mPreferenceLayouts;
    private List mPreferenceList;
    private Runnable mSyncRunnable;
    private PreferenceLayout mTempPreferenceLayout;

}
