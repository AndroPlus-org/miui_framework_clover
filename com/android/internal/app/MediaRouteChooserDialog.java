// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaRouter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;
import java.util.Comparator;

public class MediaRouteChooserDialog extends Dialog
{
    private final class MediaRouterCallback extends android.media.MediaRouter.SimpleCallback
    {

        public void onRouteAdded(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoutes();
        }

        public void onRouteChanged(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoutes();
        }

        public void onRouteRemoved(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoutes();
        }

        public void onRouteSelected(MediaRouter mediarouter, int i, android.media.MediaRouter.RouteInfo routeinfo)
        {
            dismiss();
        }

        final MediaRouteChooserDialog this$0;

        private MediaRouterCallback()
        {
            this$0 = MediaRouteChooserDialog.this;
            super();
        }

        MediaRouterCallback(MediaRouterCallback mediaroutercallback)
        {
            this();
        }
    }

    private final class RouteAdapter extends ArrayAdapter
        implements android.widget.AdapterView.OnItemClickListener
    {

        public boolean areAllItemsEnabled()
        {
            return false;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = view;
            if(view == null)
                view1 = mInflater.inflate(0x1090082, viewgroup, false);
            view = (android.media.MediaRouter.RouteInfo)getItem(i);
            Object obj = (TextView)view1.findViewById(0x1020014);
            viewgroup = (TextView)view1.findViewById(0x1020015);
            ((TextView) (obj)).setText(view.getName());
            obj = view.getDescription();
            if(TextUtils.isEmpty(((CharSequence) (obj))))
            {
                viewgroup.setVisibility(8);
                viewgroup.setText("");
            } else
            {
                viewgroup.setVisibility(0);
                viewgroup.setText(((CharSequence) (obj)));
            }
            view1.setEnabled(view.isEnabled());
            return view1;
        }

        public boolean isEnabled(int i)
        {
            return ((android.media.MediaRouter.RouteInfo)getItem(i)).isEnabled();
        }

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            adapterview = (android.media.MediaRouter.RouteInfo)getItem(i);
            if(adapterview.isEnabled())
            {
                adapterview.select();
                dismiss();
            }
        }

        public void update()
        {
            clear();
            int i = MediaRouteChooserDialog._2D_get0(MediaRouteChooserDialog.this).getRouteCount();
            for(int j = 0; j < i; j++)
            {
                android.media.MediaRouter.RouteInfo routeinfo = MediaRouteChooserDialog._2D_get0(MediaRouteChooserDialog.this).getRouteAt(j);
                if(onFilterRoute(routeinfo))
                    add(routeinfo);
            }

            sort(RouteComparator.sInstance);
            notifyDataSetChanged();
        }

        private final LayoutInflater mInflater;
        final MediaRouteChooserDialog this$0;

        public RouteAdapter(Context context)
        {
            this$0 = MediaRouteChooserDialog.this;
            super(context, 0);
            mInflater = LayoutInflater.from(context);
        }
    }

    private static final class RouteComparator
        implements Comparator
    {

        public int compare(android.media.MediaRouter.RouteInfo routeinfo, android.media.MediaRouter.RouteInfo routeinfo1)
        {
            return routeinfo.getName().toString().compareTo(routeinfo1.getName().toString());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((android.media.MediaRouter.RouteInfo)obj, (android.media.MediaRouter.RouteInfo)obj1);
        }

        public static final RouteComparator sInstance = new RouteComparator();


        private RouteComparator()
        {
        }
    }


    static MediaRouter _2D_get0(MediaRouteChooserDialog mediaroutechooserdialog)
    {
        return mediaroutechooserdialog.mRouter;
    }

    public MediaRouteChooserDialog(Context context, int i)
    {
        super(context, i);
        mRouter = (MediaRouter)context.getSystemService("media_router");
    }

    static boolean isLightTheme(Context context)
    {
        boolean flag = true;
        TypedValue typedvalue = new TypedValue();
        if(context.getTheme().resolveAttribute(0x111004e, typedvalue, true))
        {
            if(typedvalue.data == 0)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private void updateExtendedSettingsButton()
    {
        if(mExtendedSettingsButton != null)
        {
            mExtendedSettingsButton.setOnClickListener(mExtendedSettingsClickListener);
            Button button = mExtendedSettingsButton;
            int i;
            if(mExtendedSettingsClickListener != null)
                i = 0;
            else
                i = 8;
            button.setVisibility(i);
        }
    }

    public int getRouteTypes()
    {
        return mRouteTypes;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mAttachedToWindow = true;
        mRouter.addCallback(mRouteTypes, mCallback, 1);
        refreshRoutes();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().requestFeature(3);
        setContentView(0x1090080);
        int i;
        if(mRouteTypes == 4)
            i = 0x104034a;
        else
            i = 0x1040349;
        setTitle(i);
        bundle = getWindow();
        if(isLightTheme(getContext()))
            i = 0x108044c;
        else
            i = 0x108044b;
        bundle.setFeatureDrawableResource(3, i);
        mAdapter = new RouteAdapter(getContext());
        mListView = (ListView)findViewById(0x10202fa);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mAdapter);
        mListView.setEmptyView(findViewById(0x1020004));
        mExtendedSettingsButton = (Button)findViewById(0x10202f9);
        updateExtendedSettingsButton();
    }

    public void onDetachedFromWindow()
    {
        mAttachedToWindow = false;
        mRouter.removeCallback(mCallback);
        super.onDetachedFromWindow();
    }

    public boolean onFilterRoute(android.media.MediaRouter.RouteInfo routeinfo)
    {
        boolean flag;
        if(!routeinfo.isDefault() && routeinfo.isEnabled())
            flag = routeinfo.matchesTypes(mRouteTypes);
        else
            flag = false;
        return flag;
    }

    public void refreshRoutes()
    {
        if(mAttachedToWindow)
            mAdapter.update();
    }

    public void setExtendedSettingsClickListener(android.view.View.OnClickListener onclicklistener)
    {
        if(onclicklistener != mExtendedSettingsClickListener)
        {
            mExtendedSettingsClickListener = onclicklistener;
            updateExtendedSettingsButton();
        }
    }

    public void setRouteTypes(int i)
    {
        if(mRouteTypes != i)
        {
            mRouteTypes = i;
            if(mAttachedToWindow)
            {
                mRouter.removeCallback(mCallback);
                mRouter.addCallback(i, mCallback, 1);
            }
            refreshRoutes();
        }
    }

    private RouteAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback = new MediaRouterCallback(null);
    private Button mExtendedSettingsButton;
    private android.view.View.OnClickListener mExtendedSettingsClickListener;
    private ListView mListView;
    private int mRouteTypes;
    private final MediaRouter mRouter;
}
