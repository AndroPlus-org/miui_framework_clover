// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.Context;
import android.content.Intent;
import android.content.pm.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.Collections;
import java.util.List;
import miui.app.ActionBar;
import miui.app.Activity;

public class ThirdAppPicker extends Activity
    implements android.widget.AdapterView.OnItemClickListener
{
    private class FileListAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            Object obj = null;
            ResolveInfo resolveinfo;
            ImageView imageview;
            TextView textview;
            if(view == null)
                view = mInflater.inflate(0x11030002, viewgroup, false);
            resolveinfo = (ResolveInfo)ThirdAppPicker._2D_get0(ThirdAppPicker.this).get(i);
            imageview = (ImageView)view.findViewById(0x110c0018);
            textview = (TextView)view.findViewById(0x110c0019);
            if(resolveinfo == null)
                viewgroup = obj;
            else
                viewgroup = resolveinfo.loadIcon(ThirdAppPicker._2D_get1(ThirdAppPicker.this));
            imageview.setImageDrawable(viewgroup);
            if(resolveinfo != null)
                textview.setText(resolveinfo.loadLabel(ThirdAppPicker._2D_get1(ThirdAppPicker.this)));
            else
                textview.setText(0x110800b5);
            return view;
        }

        private LayoutInflater mInflater;
        final ThirdAppPicker this$0;

        public FileListAdapter(Context context, int i, List list)
        {
            this$0 = ThirdAppPicker.this;
            super(context, i, list);
            mInflater = LayoutInflater.from(context);
        }
    }


    static List _2D_get0(ThirdAppPicker thirdapppicker)
    {
        return thirdapppicker.mAllApps;
    }

    static PackageManager _2D_get1(ThirdAppPicker thirdapppicker)
    {
        return thirdapppicker.mPackageManager;
    }

    public ThirdAppPicker()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x11030003);
        getActionBar().setTitle(0x110800b6);
        getActionBar().setHomeButtonEnabled(true);
        mPackageManager = getPackageManager();
        bundle = new Intent("android.intent.action.MAIN", null);
        bundle.addCategory("android.intent.category.LAUNCHER");
        mAllApps = mPackageManager.queryIntentActivities(bundle, 0);
        Collections.sort(mAllApps, new android.content.pm.ResolveInfo.DisplayNameComparator(mPackageManager));
        mAllApps.add(null);
        mListView = (ListView)findViewById(0x110c001a);
        mListAdapter = new FileListAdapter(this, 0x11030002, mAllApps);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        view = (ResolveInfo)mAllApps.get(i);
        adapterview = new Intent();
        if(view != null)
        {
            adapterview.putExtra("name", view.loadLabel(mPackageManager));
            adapterview.setClassName(((ResolveInfo) (view)).activityInfo.packageName, ((ResolveInfo) (view)).activityInfo.name);
        }
        if(view == null)
            adapterview = null;
        setResult(-1, adapterview);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if(menuitem.getItemId() == 0x102002c)
            finish();
        return super.onOptionsItemSelected(menuitem);
    }

    private List mAllApps;
    private FileListAdapter mListAdapter;
    private ListView mListView;
    private PackageManager mPackageManager;
}
