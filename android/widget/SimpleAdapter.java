// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.net.Uri;
import android.view.*;
import java.util.*;

// Referenced classes of package android.widget:
//            BaseAdapter, Filterable, ThemedSpinnerAdapter, Checkable, 
//            TextView, ImageView, Filter

public class SimpleAdapter extends BaseAdapter
    implements Filterable, ThemedSpinnerAdapter
{
    private class SimpleFilter extends Filter
    {

        protected Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            Filter.FilterResults filterresults;
            filterresults = new Filter.FilterResults();
            if(SimpleAdapter._2D_get3(SimpleAdapter.this) == null)
                SimpleAdapter._2D_set1(SimpleAdapter.this, new ArrayList(SimpleAdapter._2D_get0(SimpleAdapter.this)));
            if(charsequence != null && charsequence.length() != 0) goto _L2; else goto _L1
_L1:
            charsequence = SimpleAdapter._2D_get3(SimpleAdapter.this);
            filterresults.values = charsequence;
            filterresults.count = charsequence.size();
_L4:
            return filterresults;
_L2:
            String s = charsequence.toString().toLowerCase();
            ArrayList arraylist = SimpleAdapter._2D_get3(SimpleAdapter.this);
            int i = arraylist.size();
            charsequence = new ArrayList(i);
label0:
            for(int j = 0; j < i; j++)
            {
                Map map = (Map)arraylist.get(j);
                if(map == null)
                    continue;
                int k = SimpleAdapter._2D_get2(SimpleAdapter.this).length;
                int l = 0;
                do
                {
                    if(l >= k)
                        continue label0;
                    String as[] = ((String)map.get(SimpleAdapter._2D_get1(SimpleAdapter.this)[l])).split(" ");
                    int i1 = as.length;
                    int j1 = 0;
                    do
                    {
label1:
                        {
                            if(j1 < i1)
                            {
                                if(!as[j1].toLowerCase().startsWith(s))
                                    break label1;
                                charsequence.add(map);
                            }
                            l++;
                        }
                        if(true)
                            break;
                        j1++;
                    } while(true);
                } while(true);
            }

            filterresults.values = charsequence;
            filterresults.count = charsequence.size();
            if(true) goto _L4; else goto _L3
_L3:
        }

        protected void publishResults(CharSequence charsequence, Filter.FilterResults filterresults)
        {
            SimpleAdapter._2D_set0(SimpleAdapter.this, (List)filterresults.values);
            if(filterresults.count > 0)
                notifyDataSetChanged();
            else
                notifyDataSetInvalidated();
        }

        final SimpleAdapter this$0;

        private SimpleFilter()
        {
            this$0 = SimpleAdapter.this;
            super();
        }

        SimpleFilter(SimpleFilter simplefilter)
        {
            this();
        }
    }

    public static interface ViewBinder
    {

        public abstract boolean setViewValue(View view, Object obj, String s);
    }


    static List _2D_get0(SimpleAdapter simpleadapter)
    {
        return simpleadapter.mData;
    }

    static String[] _2D_get1(SimpleAdapter simpleadapter)
    {
        return simpleadapter.mFrom;
    }

    static int[] _2D_get2(SimpleAdapter simpleadapter)
    {
        return simpleadapter.mTo;
    }

    static ArrayList _2D_get3(SimpleAdapter simpleadapter)
    {
        return simpleadapter.mUnfilteredData;
    }

    static List _2D_set0(SimpleAdapter simpleadapter, List list)
    {
        simpleadapter.mData = list;
        return list;
    }

    static ArrayList _2D_set1(SimpleAdapter simpleadapter, ArrayList arraylist)
    {
        simpleadapter.mUnfilteredData = arraylist;
        return arraylist;
    }

    public SimpleAdapter(Context context, List list, int i, String as[], int ai[])
    {
        mData = list;
        mDropDownResource = i;
        mResource = i;
        mFrom = as;
        mTo = ai;
        mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    private void bindView(int i, View view)
    {
        Map map = (Map)mData.get(i);
        if(map == null)
            return;
        ViewBinder viewbinder = mViewBinder;
        String as[] = mFrom;
        int ai[] = mTo;
        int j = ai.length;
        i = 0;
        while(i < j) 
        {
            View view1 = view.findViewById(ai[i]);
            if(view1 == null)
                continue;
            Object obj = map.get(as[i]);
            String s;
            String s1;
            boolean flag;
            if(obj == null)
                s = "";
            else
                s = obj.toString();
            s1 = s;
            if(s == null)
                s1 = "";
            flag = false;
            if(viewbinder != null)
                flag = viewbinder.setViewValue(view1, obj, s1);
            if(!flag)
                if(view1 instanceof Checkable)
                {
                    if(obj instanceof Boolean)
                        ((Checkable)view1).setChecked(((Boolean)obj).booleanValue());
                    else
                    if(view1 instanceof TextView)
                    {
                        setViewText((TextView)view1, s1);
                    } else
                    {
                        StringBuilder stringbuilder = (new StringBuilder()).append(view1.getClass().getName()).append(" should be bound to a Boolean, not a ");
                        if(obj == null)
                            view = "<unknown type>";
                        else
                            view = obj.getClass();
                        throw new IllegalStateException(stringbuilder.append(view).toString());
                    }
                } else
                if(view1 instanceof TextView)
                    setViewText((TextView)view1, s1);
                else
                if(view1 instanceof ImageView)
                {
                    if(obj instanceof Integer)
                        setViewImage((ImageView)view1, ((Integer)obj).intValue());
                    else
                        setViewImage((ImageView)view1, s1);
                } else
                {
                    throw new IllegalStateException((new StringBuilder()).append(view1.getClass().getName()).append(" is not a ").append(" view that can be bounds by this SimpleAdapter").toString());
                }
            i++;
        }
    }

    private View createViewFromResource(LayoutInflater layoutinflater, int i, View view, ViewGroup viewgroup, int j)
    {
        if(view == null)
            layoutinflater = layoutinflater.inflate(j, viewgroup, false);
        else
            layoutinflater = view;
        bindView(i, layoutinflater);
        return layoutinflater;
    }

    public int getCount()
    {
        return mData.size();
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        LayoutInflater layoutinflater;
        if(mDropDownInflater == null)
            layoutinflater = mInflater;
        else
            layoutinflater = mDropDownInflater;
        return createViewFromResource(layoutinflater, i, view, viewgroup, mDropDownResource);
    }

    public android.content.res.Resources.Theme getDropDownViewTheme()
    {
        android.content.res.Resources.Theme theme = null;
        if(mDropDownInflater != null)
            theme = mDropDownInflater.getContext().getTheme();
        return theme;
    }

    public Filter getFilter()
    {
        if(mFilter == null)
            mFilter = new SimpleFilter(null);
        return mFilter;
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
        return createViewFromResource(mInflater, i, view, viewgroup, mResource);
    }

    public ViewBinder getViewBinder()
    {
        return mViewBinder;
    }

    public void setDropDownViewResource(int i)
    {
        mDropDownResource = i;
    }

    public void setDropDownViewTheme(android.content.res.Resources.Theme theme)
    {
        if(theme == null)
            mDropDownInflater = null;
        else
        if(theme == mInflater.getContext().getTheme())
            mDropDownInflater = mInflater;
        else
            mDropDownInflater = LayoutInflater.from(new ContextThemeWrapper(mInflater.getContext(), theme));
    }

    public void setViewBinder(ViewBinder viewbinder)
    {
        mViewBinder = viewbinder;
    }

    public void setViewImage(ImageView imageview, int i)
    {
        imageview.setImageResource(i);
    }

    public void setViewImage(ImageView imageview, String s)
    {
        imageview.setImageResource(Integer.parseInt(s));
_L1:
        return;
        NumberFormatException numberformatexception;
        numberformatexception;
        imageview.setImageURI(Uri.parse(s));
          goto _L1
    }

    public void setViewText(TextView textview, String s)
    {
        textview.setText(s);
    }

    private List mData;
    private LayoutInflater mDropDownInflater;
    private int mDropDownResource;
    private SimpleFilter mFilter;
    private String mFrom[];
    private final LayoutInflater mInflater;
    private int mResource;
    private int mTo[];
    private ArrayList mUnfilteredData;
    private ViewBinder mViewBinder;
}
