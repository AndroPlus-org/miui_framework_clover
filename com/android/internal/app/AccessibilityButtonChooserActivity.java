// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.view.accessibility.AccessibilityManager;
import android.widget.*;
import com.android.internal.widget.ResolverDrawerLayout;
import java.util.*;

public class AccessibilityButtonChooserActivity extends Activity
{
    private static class AccessibilityButtonTarget
    {

        public Drawable getDrawable()
        {
            return mDrawable;
        }

        public String getId()
        {
            return mId;
        }

        public CharSequence getLabel()
        {
            return mLabel;
        }

        public Drawable mDrawable;
        public String mId;
        public CharSequence mLabel;

        public AccessibilityButtonTarget(Context context, AccessibilityServiceInfo accessibilityserviceinfo)
        {
            mId = accessibilityserviceinfo.getComponentName().flattenToString();
            mLabel = accessibilityserviceinfo.getResolveInfo().loadLabel(context.getPackageManager());
            mDrawable = accessibilityserviceinfo.getResolveInfo().loadIcon(context.getPackageManager());
        }

        public AccessibilityButtonTarget(Context context, String s, int i, int j)
        {
            mId = s;
            mLabel = context.getText(i);
            mDrawable = context.getDrawable(j);
        }
    }

    private class TargetAdapter extends BaseAdapter
    {

        public int getCount()
        {
            return AccessibilityButtonChooserActivity._2D_get0(AccessibilityButtonChooserActivity.this).size();
        }

        public Object getItem(int i)
        {
            return null;
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = getLayoutInflater().inflate(0x1090019, viewgroup, false);
            viewgroup = (AccessibilityButtonTarget)AccessibilityButtonChooserActivity._2D_get0(AccessibilityButtonChooserActivity.this).get(i);
            ImageView imageview = (ImageView)view1.findViewById(0x102016b);
            view = (TextView)view1.findViewById(0x102016c);
            imageview.setImageDrawable(viewgroup.getDrawable());
            view.setText(viewgroup.getLabel());
            return view1;
        }

        final AccessibilityButtonChooserActivity this$0;

        private TargetAdapter()
        {
            this$0 = AccessibilityButtonChooserActivity.this;
            super();
        }

        TargetAdapter(TargetAdapter targetadapter)
        {
            this();
        }
    }


    static List _2D_get0(AccessibilityButtonChooserActivity accessibilitybuttonchooseractivity)
    {
        return accessibilitybuttonchooseractivity.mTargets;
    }

    public AccessibilityButtonChooserActivity()
    {
        mMagnificationTarget = null;
        mTargets = null;
    }

    private static List getServiceAccessibilityButtonTargets(Context context)
    {
        List list = ((AccessibilityManager)context.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1);
        if(list == null)
            return Collections.emptyList();
        ArrayList arraylist = new ArrayList(list.size());
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            AccessibilityServiceInfo accessibilityserviceinfo = (AccessibilityServiceInfo)iterator.next();
            if((accessibilityserviceinfo.flags & 0x100) != 0)
                arraylist.add(new AccessibilityButtonTarget(context, accessibilityserviceinfo));
        } while(true);
        return arraylist;
    }

    private void onTargetSelected(AccessibilityButtonTarget accessibilitybuttontarget)
    {
        android.provider.Settings.Secure.putString(getContentResolver(), "accessibility_button_target_component", accessibilitybuttontarget.getId());
        finish();
    }

    void _2D_com_android_internal_app_AccessibilityButtonChooserActivity_2D_mthref_2D_0()
    {
        finish();
    }

    void lambda$_2D_com_android_internal_app_AccessibilityButtonChooserActivity_3326(AdapterView adapterview, View view, int i, long l)
    {
        onTargetSelected((AccessibilityButtonTarget)mTargets.get(i));
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x1090018);
        bundle = (ResolverDrawerLayout)findViewById(0x1020206);
        if(bundle != null)
            bundle.setOnDismissedListener(new _.Lambda._cls0r_TDm8lcq9IOpwsSKOm5X0EL_Q._cls1(this));
        if(TextUtils.isEmpty(android.provider.Settings.Secure.getString(getContentResolver(), "accessibility_button_target_component")))
            ((TextView)findViewById(0x102016a)).setVisibility(0);
        mMagnificationTarget = new AccessibilityButtonTarget(this, "com.android.server.accessibility.MagnificationController", 0x104004b, 0x10802f0);
        mTargets = getServiceAccessibilityButtonTargets(this);
        if(android.provider.Settings.Secure.getInt(getContentResolver(), "accessibility_display_magnification_navbar_enabled", 0) == 1)
            mTargets.add(mMagnificationTarget);
        if(mTargets.size() < 2)
            finish();
        bundle = (GridView)findViewById(0x1020169);
        bundle.setAdapter(new TargetAdapter(null));
        bundle.setOnItemClickListener(new _.Lambda._cls0r_TDm8lcq9IOpwsSKOm5X0EL_Q(this));
    }

    private static final String MAGNIFICATION_COMPONENT_ID = "com.android.server.accessibility.MagnificationController";
    private AccessibilityButtonTarget mMagnificationTarget;
    private List mTargets;
}
