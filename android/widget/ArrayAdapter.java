// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.*;
import java.util.*;

// Referenced classes of package android.widget:
//            BaseAdapter, Filterable, ThemedSpinnerAdapter, TextView, 
//            Filter

public class ArrayAdapter extends BaseAdapter
    implements Filterable, ThemedSpinnerAdapter
{
    private class ArrayFilter extends Filter
    {

        protected Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            Object obj = new Filter.FilterResults();
            if(ArrayAdapter._2D_get2(ArrayAdapter.this) != null) goto _L2; else goto _L1
_L1:
            Object obj1 = ArrayAdapter._2D_get0(ArrayAdapter.this);
            obj1;
            JVM INSTR monitorenter ;
            ArrayAdapter arrayadapter = ArrayAdapter.this;
            ArrayList arraylist1 = JVM INSTR new #35  <Class ArrayList>;
            arraylist1.ArrayList(ArrayAdapter._2D_get1(ArrayAdapter.this));
            ArrayAdapter._2D_set1(arrayadapter, arraylist1);
            obj1;
            JVM INSTR monitorexit ;
_L2:
            if(charsequence != null && charsequence.length() != 0) goto _L4; else goto _L3
_L3:
            charsequence = ((CharSequence) (ArrayAdapter._2D_get0(ArrayAdapter.this)));
            charsequence;
            JVM INSTR monitorenter ;
            obj1 = new ArrayList(ArrayAdapter._2D_get2(ArrayAdapter.this));
            charsequence;
            JVM INSTR monitorexit ;
            obj.values = obj1;
            obj.count = ((ArrayList) (obj1)).size();
_L12:
            return ((Filter.FilterResults) (obj));
            charsequence;
            throw charsequence;
            obj;
            throw obj;
_L4:
            charsequence = charsequence.toString().toLowerCase();
            Object obj2 = ArrayAdapter._2D_get0(ArrayAdapter.this);
            obj2;
            JVM INSTR monitorenter ;
            obj1 = new ArrayList(ArrayAdapter._2D_get2(ArrayAdapter.this));
            obj2;
            JVM INSTR monitorexit ;
            ArrayList arraylist;
            int i;
            int j;
            i = ((ArrayList) (obj1)).size();
            arraylist = new ArrayList();
            j = 0;
_L6:
            String s;
            if(j >= i)
                break MISSING_BLOCK_LABEL_291;
            obj2 = ((ArrayList) (obj1)).get(j);
            s = obj2.toString().toLowerCase();
            if(!s.startsWith(charsequence))
                break; /* Loop/switch isn't completed */
            arraylist.add(obj2);
_L8:
            j++;
            if(true) goto _L6; else goto _L5
            charsequence;
            throw charsequence;
_L5:
            String as[];
            int k;
            int l;
            as = s.split(" ");
            k = 0;
            l = as.length;
_L10:
            if(k >= l) goto _L8; else goto _L7
_L7:
label0:
            {
                if(!as[k].startsWith(charsequence))
                    break label0;
                arraylist.add(obj2);
            }
            if(true) goto _L8; else goto _L9
_L9:
            k++;
              goto _L10
            obj.values = arraylist;
            obj.count = arraylist.size();
            if(true) goto _L12; else goto _L11
_L11:
        }

        protected void publishResults(CharSequence charsequence, Filter.FilterResults filterresults)
        {
            ArrayAdapter._2D_set0(ArrayAdapter.this, (List)filterresults.values);
            if(filterresults.count > 0)
                notifyDataSetChanged();
            else
                notifyDataSetInvalidated();
        }

        final ArrayAdapter this$0;

        private ArrayFilter()
        {
            this$0 = ArrayAdapter.this;
            super();
        }

        ArrayFilter(ArrayFilter arrayfilter)
        {
            this();
        }
    }


    static Object _2D_get0(ArrayAdapter arrayadapter)
    {
        return arrayadapter.mLock;
    }

    static List _2D_get1(ArrayAdapter arrayadapter)
    {
        return arrayadapter.mObjects;
    }

    static ArrayList _2D_get2(ArrayAdapter arrayadapter)
    {
        return arrayadapter.mOriginalValues;
    }

    static List _2D_set0(ArrayAdapter arrayadapter, List list)
    {
        arrayadapter.mObjects = list;
        return list;
    }

    static ArrayList _2D_set1(ArrayAdapter arrayadapter, ArrayList arraylist)
    {
        arrayadapter.mOriginalValues = arraylist;
        return arraylist;
    }

    public ArrayAdapter(Context context, int i)
    {
        this(context, i, 0, ((List) (new ArrayList())));
    }

    public ArrayAdapter(Context context, int i, int j)
    {
        this(context, i, j, ((List) (new ArrayList())));
    }

    public ArrayAdapter(Context context, int i, int j, List list)
    {
        this(context, i, j, list, false);
    }

    private ArrayAdapter(Context context, int i, int j, List list, boolean flag)
    {
        mLock = new Object();
        mFieldId = 0;
        mNotifyOnChange = true;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDropDownResource = i;
        mResource = i;
        mObjects = list;
        mObjectsFromResources = flag;
        mFieldId = j;
    }

    public ArrayAdapter(Context context, int i, int j, Object aobj[])
    {
        this(context, i, j, Arrays.asList(aobj));
    }

    public ArrayAdapter(Context context, int i, List list)
    {
        this(context, i, 0, list);
    }

    public ArrayAdapter(Context context, int i, Object aobj[])
    {
        this(context, i, 0, Arrays.asList(aobj));
    }

    public static ArrayAdapter createFromResource(Context context, int i, int j)
    {
        return new ArrayAdapter(context, j, 0, Arrays.asList(context.getResources().getTextArray(i)), true);
    }

    private View createViewFromResource(LayoutInflater layoutinflater, int i, View view, ViewGroup viewgroup, int j)
    {
        if(view == null)
            layoutinflater = layoutinflater.inflate(j, viewgroup, false);
        else
            layoutinflater = view;
        if(mFieldId != 0) goto _L2; else goto _L1
_L1:
        view = (TextView)layoutinflater;
_L3:
        viewgroup = ((ViewGroup) (getItem(i)));
        if(viewgroup instanceof CharSequence)
            view.setText((CharSequence)viewgroup);
        else
            view.setText(viewgroup.toString());
        return layoutinflater;
_L2:
        viewgroup = (TextView)layoutinflater.findViewById(mFieldId);
        view = viewgroup;
        if(viewgroup == null)
        {
            try
            {
                layoutinflater = JVM INSTR new #148 <Class RuntimeException>;
                view = JVM INSTR new #150 <Class StringBuilder>;
                view.StringBuilder();
                layoutinflater.RuntimeException(view.append("Failed to find view with ID ").append(mContext.getResources().getResourceName(mFieldId)).append(" in item layout").toString());
                throw layoutinflater;
            }
            // Misplaced declaration of an exception variable
            catch(LayoutInflater layoutinflater)
            {
                Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
            }
            throw new IllegalStateException("ArrayAdapter requires the resource ID to be a TextView", layoutinflater);
        }
          goto _L3
    }

    public void add(Object obj)
    {
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mOriginalValues == null)
            break MISSING_BLOCK_LABEL_42;
        mOriginalValues.add(obj);
_L1:
        mObjectsFromResources = false;
        obj1;
        JVM INSTR monitorexit ;
        if(mNotifyOnChange)
            notifyDataSetChanged();
        return;
        mObjects.add(obj);
          goto _L1
        obj;
        throw obj;
    }

    public void addAll(Collection collection)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mOriginalValues == null)
            break MISSING_BLOCK_LABEL_42;
        mOriginalValues.addAll(collection);
_L1:
        mObjectsFromResources = false;
        obj;
        JVM INSTR monitorexit ;
        if(mNotifyOnChange)
            notifyDataSetChanged();
        return;
        mObjects.addAll(collection);
          goto _L1
        collection;
        throw collection;
    }

    public transient void addAll(Object aobj[])
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mOriginalValues == null)
            break MISSING_BLOCK_LABEL_42;
        Collections.addAll(mOriginalValues, aobj);
_L1:
        mObjectsFromResources = false;
        obj;
        JVM INSTR monitorexit ;
        if(mNotifyOnChange)
            notifyDataSetChanged();
        return;
        Collections.addAll(mObjects, aobj);
          goto _L1
        aobj;
        throw aobj;
    }

    public void clear()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mOriginalValues == null)
            break MISSING_BLOCK_LABEL_40;
        mOriginalValues.clear();
_L1:
        mObjectsFromResources = false;
        obj;
        JVM INSTR monitorexit ;
        if(mNotifyOnChange)
            notifyDataSetChanged();
        return;
        mObjects.clear();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public CharSequence[] getAutofillOptions()
    {
        CharSequence acharsequence[] = super.getAutofillOptions();
        if(acharsequence != null)
            return acharsequence;
        while(!mObjectsFromResources || mObjects == null || mObjects.isEmpty()) 
            return null;
        acharsequence = new CharSequence[mObjects.size()];
        mObjects.toArray(acharsequence);
        return acharsequence;
    }

    public Context getContext()
    {
        return mContext;
    }

    public int getCount()
    {
        return mObjects.size();
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
            mFilter = new ArrayFilter(null);
        return mFilter;
    }

    public Object getItem(int i)
    {
        return mObjects.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public int getPosition(Object obj)
    {
        return mObjects.indexOf(obj);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return createViewFromResource(mInflater, i, view, viewgroup, mResource);
    }

    public void insert(Object obj, int i)
    {
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mOriginalValues == null)
            break MISSING_BLOCK_LABEL_42;
        mOriginalValues.add(i, obj);
_L1:
        mObjectsFromResources = false;
        obj1;
        JVM INSTR monitorexit ;
        if(mNotifyOnChange)
            notifyDataSetChanged();
        return;
        mObjects.add(i, obj);
          goto _L1
        obj;
        throw obj;
    }

    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
        mNotifyOnChange = true;
    }

    public void remove(Object obj)
    {
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mOriginalValues == null)
            break MISSING_BLOCK_LABEL_42;
        mOriginalValues.remove(obj);
_L1:
        mObjectsFromResources = false;
        obj1;
        JVM INSTR monitorexit ;
        if(mNotifyOnChange)
            notifyDataSetChanged();
        return;
        mObjects.remove(obj);
          goto _L1
        obj;
        throw obj;
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
            mDropDownInflater = LayoutInflater.from(new ContextThemeWrapper(mContext, theme));
    }

    public void setNotifyOnChange(boolean flag)
    {
        mNotifyOnChange = flag;
    }

    public void sort(Comparator comparator)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mOriginalValues == null)
            break MISSING_BLOCK_LABEL_36;
        Collections.sort(mOriginalValues, comparator);
_L1:
        obj;
        JVM INSTR monitorexit ;
        if(mNotifyOnChange)
            notifyDataSetChanged();
        return;
        Collections.sort(mObjects, comparator);
          goto _L1
        comparator;
        throw comparator;
    }

    private final Context mContext;
    private LayoutInflater mDropDownInflater;
    private int mDropDownResource;
    private int mFieldId;
    private ArrayFilter mFilter;
    private final LayoutInflater mInflater;
    private final Object mLock;
    private boolean mNotifyOnChange;
    private List mObjects;
    private boolean mObjectsFromResources;
    private ArrayList mOriginalValues;
    private final int mResource;
}
