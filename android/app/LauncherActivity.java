// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.content.pm.*;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.*;

// Referenced classes of package android.app:
//            ListActivity

public abstract class LauncherActivity extends ListActivity
{
    private class ActivityAdapter extends BaseAdapter
        implements Filterable
    {

        static Object _2D_get0(ActivityAdapter activityadapter)
        {
            return activityadapter.lock;
        }

        static ArrayList _2D_get1(ActivityAdapter activityadapter)
        {
            return activityadapter.mOriginalValues;
        }

        static ArrayList _2D_set0(ActivityAdapter activityadapter, ArrayList arraylist)
        {
            activityadapter.mOriginalValues = arraylist;
            return arraylist;
        }

        private void bindView(View view, ListItem listitem)
        {
            view = (TextView)view;
            view.setText(listitem.label);
            if(mShowIcons)
            {
                if(listitem.icon == null)
                    listitem.icon = mIconResizer.createIconThumbnail(listitem.resolveInfo.loadIcon(getPackageManager()));
                view.setCompoundDrawablesWithIntrinsicBounds(listitem.icon, null, null, null);
            }
        }

        public int getCount()
        {
            int i;
            if(mActivitiesList != null)
                i = mActivitiesList.size();
            else
                i = 0;
            return i;
        }

        public Filter getFilter()
        {
            if(mFilter == null)
                mFilter = new ArrayFilter(null);
            return mFilter;
        }

        public Object getItem(int i)
        {
            return Integer.valueOf(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
                view = mInflater.inflate(0x1090026, viewgroup, false);
            bindView(view, (ListItem)mActivitiesList.get(i));
            return view;
        }

        public Intent intentForPosition(int i)
        {
            if(mActivitiesList == null)
                return null;
            Intent intent = new Intent(mIntent);
            ListItem listitem = (ListItem)mActivitiesList.get(i);
            intent.setClassName(listitem.packageName, listitem.className);
            if(listitem.extras != null)
                intent.putExtras(listitem.extras);
            return intent;
        }

        public ListItem itemForPosition(int i)
        {
            if(mActivitiesList == null)
                return null;
            else
                return (ListItem)mActivitiesList.get(i);
        }

        private final Object lock = new Object();
        protected List mActivitiesList;
        private Filter mFilter;
        protected final IconResizer mIconResizer;
        protected final LayoutInflater mInflater;
        private ArrayList mOriginalValues;
        private final boolean mShowIcons;
        final LauncherActivity this$0;

        public ActivityAdapter(IconResizer iconresizer)
        {
            this$0 = LauncherActivity.this;
            super();
            mIconResizer = iconresizer;
            mInflater = (LayoutInflater)getSystemService("layout_inflater");
            mShowIcons = onEvaluateShowIcons();
            mActivitiesList = makeListItems();
        }
    }

    private class ActivityAdapter.ArrayFilter extends Filter
    {

        protected android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            Object obj = new android.widget.Filter.FilterResults();
            if(ActivityAdapter._2D_get1(ActivityAdapter.this) != null) goto _L2; else goto _L1
_L1:
            Object obj1 = ActivityAdapter._2D_get0(ActivityAdapter.this);
            obj1;
            JVM INSTR monitorenter ;
            ActivityAdapter activityadapter = ActivityAdapter.this;
            ArrayList arraylist1 = JVM INSTR new #38  <Class ArrayList>;
            arraylist1.ArrayList(mActivitiesList);
            ActivityAdapter._2D_set0(activityadapter, arraylist1);
            obj1;
            JVM INSTR monitorexit ;
_L2:
            if(charsequence != null && charsequence.length() != 0) goto _L4; else goto _L3
_L3:
            charsequence = ((CharSequence) (ActivityAdapter._2D_get0(ActivityAdapter.this)));
            charsequence;
            JVM INSTR monitorenter ;
            obj1 = JVM INSTR new #38  <Class ArrayList>;
            ((ArrayList) (obj1)).ArrayList(ActivityAdapter._2D_get1(ActivityAdapter.this));
            obj.values = obj1;
            obj.count = ((ArrayList) (obj1)).size();
            charsequence;
            JVM INSTR monitorexit ;
_L6:
            return ((android.widget.Filter.FilterResults) (obj));
            charsequence;
            throw charsequence;
            obj;
            throw obj;
_L4:
            ArrayList arraylist;
label0:
            {
                String s = charsequence.toString().toLowerCase();
                ArrayList arraylist2 = ActivityAdapter._2D_get1(ActivityAdapter.this);
                int i = arraylist2.size();
                arraylist = new ArrayList(i);
                int j = 0;
                do
                {
                    if(j >= i)
                        break label0;
                    ListItem listitem = (ListItem)arraylist2.get(j);
                    charsequence = listitem.label.toString().toLowerCase().split(" ");
                    int k = charsequence.length;
                    int l = 0;
                    do
                    {
label1:
                        {
                            if(l < k)
                            {
                                if(!charsequence[l].startsWith(s))
                                    break label1;
                                arraylist.add(listitem);
                            }
                            j++;
                        }
                        if(true)
                            break;
                        l++;
                    } while(true);
                } while(true);
            }
            obj.values = arraylist;
            obj.count = arraylist.size();
            if(true) goto _L6; else goto _L5
_L5:
        }

        protected void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
        {
            mActivitiesList = (List)filterresults.values;
            if(filterresults.count > 0)
                notifyDataSetChanged();
            else
                notifyDataSetInvalidated();
        }

        final ActivityAdapter this$1;

        private ActivityAdapter.ArrayFilter()
        {
            this$1 = ActivityAdapter.this;
            super();
        }

        ActivityAdapter.ArrayFilter(ActivityAdapter.ArrayFilter arrayfilter)
        {
            this();
        }
    }

    public class IconResizer
    {

        public Drawable createIconThumbnail(Drawable drawable)
        {
            int i = mIconWidth;
            int j = mIconHeight;
            int k = drawable.getIntrinsicWidth();
            int l = drawable.getIntrinsicHeight();
            if(drawable instanceof PaintDrawable)
            {
                PaintDrawable paintdrawable = (PaintDrawable)drawable;
                paintdrawable.setIntrinsicWidth(i);
                paintdrawable.setIntrinsicHeight(j);
            }
            Object obj = drawable;
            if(i > 0)
            {
                obj = drawable;
                if(j > 0)
                    if(i < k || j < l)
                    {
                        float f = (float)k / (float)l;
                        int i1;
                        Canvas canvas;
                        if(k > l)
                        {
                            i1 = (int)((float)i / f);
                        } else
                        {
                            i1 = j;
                            if(l > k)
                            {
                                i = (int)((float)j * f);
                                i1 = j;
                            }
                        }
                        if(drawable.getOpacity() != -1)
                            obj = android.graphics.Bitmap.Config.ARGB_8888;
                        else
                            obj = android.graphics.Bitmap.Config.RGB_565;
                        obj = Bitmap.createBitmap(mIconWidth, mIconHeight, ((android.graphics.Bitmap.Config) (obj)));
                        canvas = mCanvas;
                        canvas.setBitmap(((Bitmap) (obj)));
                        mOldBounds.set(drawable.getBounds());
                        j = (mIconWidth - i) / 2;
                        k = (mIconHeight - i1) / 2;
                        drawable.setBounds(j, k, j + i, k + i1);
                        drawable.draw(canvas);
                        drawable.setBounds(mOldBounds);
                        obj = new BitmapDrawable(getResources(), ((Bitmap) (obj)));
                        canvas.setBitmap(null);
                    } else
                    {
                        obj = drawable;
                        if(k < i)
                        {
                            obj = drawable;
                            if(l < j)
                            {
                                obj = android.graphics.Bitmap.Config.ARGB_8888;
                                obj = Bitmap.createBitmap(mIconWidth, mIconHeight, ((android.graphics.Bitmap.Config) (obj)));
                                Canvas canvas1 = mCanvas;
                                canvas1.setBitmap(((Bitmap) (obj)));
                                mOldBounds.set(drawable.getBounds());
                                int j1 = (i - k) / 2;
                                i = (j - l) / 2;
                                drawable.setBounds(j1, i, j1 + k, i + l);
                                drawable.draw(canvas1);
                                drawable.setBounds(mOldBounds);
                                obj = new BitmapDrawable(getResources(), ((Bitmap) (obj)));
                                canvas1.setBitmap(null);
                            }
                        }
                    }
            }
            return ((Drawable) (obj));
        }

        private Canvas mCanvas;
        private int mIconHeight;
        private int mIconWidth;
        private final Rect mOldBounds = new Rect();
        final LauncherActivity this$0;

        public IconResizer()
        {
            this$0 = LauncherActivity.this;
            super();
            mIconWidth = -1;
            mIconHeight = -1;
            mCanvas = new Canvas();
            mCanvas.setDrawFilter(new PaintFlagsDrawFilter(4, 2));
            int i = (int)getResources().getDimension(0x1050000);
            mIconHeight = i;
            mIconWidth = i;
        }
    }

    public static class ListItem
    {

        public String className;
        public Bundle extras;
        public Drawable icon;
        public CharSequence label;
        public String packageName;
        public ResolveInfo resolveInfo;

        public ListItem()
        {
        }

        ListItem(PackageManager packagemanager, ResolveInfo resolveinfo, IconResizer iconresizer)
        {
            resolveInfo = resolveinfo;
            label = resolveinfo.loadLabel(packagemanager);
            ActivityInfo activityinfo = resolveinfo.activityInfo;
            Object obj = activityinfo;
            if(activityinfo == null)
                obj = resolveinfo.serviceInfo;
            if(label == null && obj != null)
                label = resolveinfo.activityInfo.name;
            if(iconresizer != null)
                icon = iconresizer.createIconThumbnail(resolveinfo.loadIcon(packagemanager));
            packageName = ((ComponentInfo) (obj)).applicationInfo.packageName;
            className = ((ComponentInfo) (obj)).name;
        }
    }


    public LauncherActivity()
    {
    }

    private void updateAlertTitle()
    {
        TextView textview = (TextView)findViewById(0x1020199);
        if(textview != null)
            textview.setText(getTitle());
    }

    private void updateButtonText()
    {
        Button button = (Button)findViewById(0x1020019);
        if(button != null)
            button.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    finish();
                }

                final LauncherActivity this$0;

            
            {
                this$0 = LauncherActivity.this;
                super();
            }
            }
);
    }

    protected Intent getTargetIntent()
    {
        return new Intent();
    }

    protected Intent intentForPosition(int i)
    {
        return ((ActivityAdapter)mAdapter).intentForPosition(i);
    }

    protected ListItem itemForPosition(int i)
    {
        return ((ActivityAdapter)mAdapter).itemForPosition(i);
    }

    public List makeListItems()
    {
        List list = onQueryPackageManager(mIntent);
        onSortResultList(list);
        ArrayList arraylist = new ArrayList(list.size());
        int i = list.size();
        for(int j = 0; j < i; j++)
        {
            ResolveInfo resolveinfo = (ResolveInfo)list.get(j);
            arraylist.add(new ListItem(mPackageManager, resolveinfo, null));
        }

        return arraylist;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mPackageManager = getPackageManager();
        if(!mPackageManager.hasSystemFeature("android.hardware.type.watch"))
        {
            requestWindowFeature(5);
            setProgressBarIndeterminateVisibility(true);
        }
        onSetContentView();
        mIconResizer = new IconResizer();
        mIntent = new Intent(getTargetIntent());
        mIntent.setComponent(null);
        mAdapter = new ActivityAdapter(mIconResizer);
        setListAdapter(mAdapter);
        getListView().setTextFilterEnabled(true);
        updateAlertTitle();
        updateButtonText();
        if(!mPackageManager.hasSystemFeature("android.hardware.type.watch"))
            setProgressBarIndeterminateVisibility(false);
    }

    protected boolean onEvaluateShowIcons()
    {
        return true;
    }

    protected void onListItemClick(ListView listview, View view, int i, long l)
    {
        startActivity(intentForPosition(i));
    }

    protected List onQueryPackageManager(Intent intent)
    {
        return mPackageManager.queryIntentActivities(intent, 0);
    }

    protected void onSetContentView()
    {
        setContentView(0x1090025);
    }

    protected void onSortResultList(List list)
    {
        Collections.sort(list, new android.content.pm.ResolveInfo.DisplayNameComparator(mPackageManager));
    }

    public void setTitle(int i)
    {
        super.setTitle(i);
        updateAlertTitle();
    }

    public void setTitle(CharSequence charsequence)
    {
        super.setTitle(charsequence);
        updateAlertTitle();
    }

    IconResizer mIconResizer;
    Intent mIntent;
    PackageManager mPackageManager;
}
