// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import java.util.*;

// Referenced classes of package com.android.internal.app:
//            LocaleHelper

public class SuggestedLocaleAdapter extends BaseAdapter
    implements Filterable
{
    class FilterByNativeAndUiNames extends Filter
    {

        protected android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            android.widget.Filter.FilterResults filterresults = new android.widget.Filter.FilterResults();
            if(SuggestedLocaleAdapter._2D_get1(SuggestedLocaleAdapter.this) == null)
                SuggestedLocaleAdapter._2D_set1(SuggestedLocaleAdapter.this, new ArrayList(SuggestedLocaleAdapter._2D_get0(SuggestedLocaleAdapter.this)));
            ArrayList arraylist = new ArrayList(SuggestedLocaleAdapter._2D_get1(SuggestedLocaleAdapter.this));
            if(charsequence == null || charsequence.length() == 0)
            {
                filterresults.values = arraylist;
                filterresults.count = arraylist.size();
            } else
            {
                Locale locale = Locale.getDefault();
                String s = LocaleHelper.normalizeForSearch(charsequence.toString(), locale);
                int i = arraylist.size();
                ArrayList arraylist1 = new ArrayList();
                for(int j = 0; j < i; j++)
                {
                    LocaleStore.LocaleInfo localeinfo = (LocaleStore.LocaleInfo)arraylist.get(j);
                    charsequence = LocaleHelper.normalizeForSearch(localeinfo.getFullNameInUiLanguage(), locale);
                    if(wordMatches(LocaleHelper.normalizeForSearch(localeinfo.getFullNameNative(), locale), s) || wordMatches(charsequence, s))
                        arraylist1.add(localeinfo);
                }

                filterresults.values = arraylist1;
                filterresults.count = arraylist1.size();
            }
            return filterresults;
        }

        protected void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
        {
            SuggestedLocaleAdapter._2D_set0(SuggestedLocaleAdapter.this, (ArrayList)filterresults.values);
            SuggestedLocaleAdapter._2D_set2(SuggestedLocaleAdapter.this, 0);
            Iterator iterator = SuggestedLocaleAdapter._2D_get0(SuggestedLocaleAdapter.this).iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                if(((LocaleStore.LocaleInfo)iterator.next()).isSuggested())
                {
                    charsequence = SuggestedLocaleAdapter.this;
                    SuggestedLocaleAdapter._2D_set2(charsequence, SuggestedLocaleAdapter._2D_get2(charsequence) + 1);
                }
            } while(true);
            if(filterresults.count > 0)
                notifyDataSetChanged();
            else
                notifyDataSetInvalidated();
        }

        boolean wordMatches(String s, String s1)
        {
            if(s.startsWith(s1))
                return true;
            s = s.split(" ");
            int i = s.length;
            for(int j = 0; j < i; j++)
                if(s[j].startsWith(s1))
                    return true;

            return false;
        }

        final SuggestedLocaleAdapter this$0;

        FilterByNativeAndUiNames()
        {
            this$0 = SuggestedLocaleAdapter.this;
            super();
        }
    }


    static ArrayList _2D_get0(SuggestedLocaleAdapter suggestedlocaleadapter)
    {
        return suggestedlocaleadapter.mLocaleOptions;
    }

    static ArrayList _2D_get1(SuggestedLocaleAdapter suggestedlocaleadapter)
    {
        return suggestedlocaleadapter.mOriginalLocaleOptions;
    }

    static int _2D_get2(SuggestedLocaleAdapter suggestedlocaleadapter)
    {
        return suggestedlocaleadapter.mSuggestionCount;
    }

    static ArrayList _2D_set0(SuggestedLocaleAdapter suggestedlocaleadapter, ArrayList arraylist)
    {
        suggestedlocaleadapter.mLocaleOptions = arraylist;
        return arraylist;
    }

    static ArrayList _2D_set1(SuggestedLocaleAdapter suggestedlocaleadapter, ArrayList arraylist)
    {
        suggestedlocaleadapter.mOriginalLocaleOptions = arraylist;
        return arraylist;
    }

    static int _2D_set2(SuggestedLocaleAdapter suggestedlocaleadapter, int i)
    {
        suggestedlocaleadapter.mSuggestionCount = i;
        return i;
    }

    public SuggestedLocaleAdapter(Set set, boolean flag)
    {
        mDisplayLocale = null;
        mContextOverride = null;
        mCountryMode = flag;
        mLocaleOptions = new ArrayList(set.size());
        for(Iterator iterator = set.iterator(); iterator.hasNext(); mLocaleOptions.add(set))
        {
            set = (LocaleStore.LocaleInfo)iterator.next();
            if(set.isSuggested())
                mSuggestionCount = mSuggestionCount + 1;
        }

    }

    private void setTextTo(TextView textview, int i)
    {
        if(mContextOverride == null)
            textview.setText(i);
        else
            textview.setText(mContextOverride.getText(i));
    }

    private boolean showHeaders()
    {
        boolean flag = false;
        if(mCountryMode && mLocaleOptions.size() < 6)
            return false;
        boolean flag1 = flag;
        if(mSuggestionCount != 0)
        {
            flag1 = flag;
            if(mSuggestionCount != mLocaleOptions.size())
                flag1 = true;
        }
        return flag1;
    }

    public boolean areAllItemsEnabled()
    {
        return false;
    }

    public int getCount()
    {
        if(showHeaders())
            return mLocaleOptions.size() + 2;
        else
            return mLocaleOptions.size();
    }

    public Filter getFilter()
    {
        return new FilterByNativeAndUiNames();
    }

    public Object getItem(int i)
    {
        byte byte0 = 0;
        if(showHeaders())
            if(i > mSuggestionCount)
                byte0 = -2;
            else
                byte0 = -1;
        return mLocaleOptions.get(i + byte0);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public int getItemViewType(int i)
    {
        if(!showHeaders())
            return 2;
        if(i == 0)
            return 0;
        return i != mSuggestionCount + 1 ? 2 : 1;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        int j;
        if(view == null && mInflater == null)
            mInflater = LayoutInflater.from(viewgroup.getContext());
        j = getItemViewType(i);
        j;
        JVM INSTR tableswitch 0 1: default 52
    //                   0 173
    //                   1 173;
           goto _L1 _L2 _L2
_L1:
        View view1 = view;
        if(!(view instanceof ViewGroup))
            view1 = mInflater.inflate(0x1090075, viewgroup, false);
        TextView textview = (TextView)view1.findViewById(0x10202e5);
        viewgroup = (LocaleStore.LocaleInfo)getItem(i);
        textview.setText(viewgroup.getLabel(mCountryMode));
        textview.setTextLocale(viewgroup.getLocale());
        textview.setContentDescription(viewgroup.getContentDescription(mCountryMode));
        view = view1;
        if(mCountryMode)
        {
            i = TextUtils.getLayoutDirectionFromLocale(viewgroup.getParent());
            view1.setLayoutDirection(i);
            if(i == 1)
                i = 4;
            else
                i = 3;
            textview.setTextDirection(i);
            view = view1;
        }
_L5:
        return view;
_L2:
        view1 = view;
        if(!(view instanceof TextView))
            view1 = mInflater.inflate(0x1090076, viewgroup, false);
        viewgroup = (TextView)view1;
        if(j != 0) goto _L4; else goto _L3
_L3:
        setTextTo(viewgroup, 0x10402ee);
_L6:
        if(mDisplayLocale != null)
            view = mDisplayLocale;
        else
            view = Locale.getDefault();
        viewgroup.setTextLocale(view);
        view = view1;
        if(true) goto _L5; else goto _L4
_L4:
        if(mCountryMode)
            setTextTo(viewgroup, 0x1040560);
        else
            setTextTo(viewgroup, 0x10402ed);
          goto _L6
    }

    public int getViewTypeCount()
    {
        return !showHeaders() ? 1 : 3;
    }

    public boolean isEnabled(int i)
    {
        boolean flag;
        if(getItemViewType(i) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setDisplayLocale(Context context, Locale locale)
    {
        if(locale != null) goto _L2; else goto _L1
_L1:
        mDisplayLocale = null;
        mContextOverride = null;
_L4:
        return;
_L2:
        if(!locale.equals(mDisplayLocale))
        {
            mDisplayLocale = locale;
            Configuration configuration = new Configuration();
            configuration.setLocale(locale);
            mContextOverride = context.createConfigurationContext(configuration);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void sort(LocaleHelper.LocaleInfoComparator localeinfocomparator)
    {
        Collections.sort(mLocaleOptions, localeinfocomparator);
    }

    private static final int MIN_REGIONS_FOR_SUGGESTIONS = 6;
    private static final int TYPE_HEADER_ALL_OTHERS = 1;
    private static final int TYPE_HEADER_SUGGESTED = 0;
    private static final int TYPE_LOCALE = 2;
    private Context mContextOverride;
    private final boolean mCountryMode;
    private Locale mDisplayLocale;
    private LayoutInflater mInflater;
    private ArrayList mLocaleOptions;
    private ArrayList mOriginalLocaleOptions;
    private int mSuggestionCount;
}
