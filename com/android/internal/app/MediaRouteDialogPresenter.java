// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.*;
import android.content.Context;
import android.media.MediaRouter;
import android.util.Log;

// Referenced classes of package com.android.internal.app:
//            MediaRouteChooserDialog, MediaRouteControllerDialog, MediaRouteChooserDialogFragment, MediaRouteControllerDialogFragment

public abstract class MediaRouteDialogPresenter
{

    public MediaRouteDialogPresenter()
    {
    }

    public static Dialog createDialog(Context context, int i, android.view.View.OnClickListener onclicklistener)
    {
        Object obj = (MediaRouter)context.getSystemService("media_router");
        int j;
        if(MediaRouteChooserDialog.isLightTheme(context))
            j = 0x1030132;
        else
            j = 0x103012e;
        obj = ((MediaRouter) (obj)).getSelectedRoute();
        if(((android.media.MediaRouter.RouteInfo) (obj)).isDefault() || ((android.media.MediaRouter.RouteInfo) (obj)).matchesTypes(i) ^ true)
        {
            context = new MediaRouteChooserDialog(context, j);
            context.setRouteTypes(i);
            context.setExtendedSettingsClickListener(onclicklistener);
            return context;
        } else
        {
            return new MediaRouteControllerDialog(context, j);
        }
    }

    public static DialogFragment showDialogFragment(Activity activity, int i, android.view.View.OnClickListener onclicklistener)
    {
        Object obj = (MediaRouter)activity.getSystemService("media_router");
        activity = activity.getFragmentManager();
        obj = ((MediaRouter) (obj)).getSelectedRoute();
        if(((android.media.MediaRouter.RouteInfo) (obj)).isDefault() || ((android.media.MediaRouter.RouteInfo) (obj)).matchesTypes(i) ^ true)
            if(activity.findFragmentByTag("android.app.MediaRouteButton:MediaRouteChooserDialogFragment") != null)
            {
                Log.w("MediaRouter", "showDialog(): Route chooser dialog already showing!");
                return null;
            } else
            {
                MediaRouteChooserDialogFragment mediaroutechooserdialogfragment = new MediaRouteChooserDialogFragment();
                mediaroutechooserdialogfragment.setRouteTypes(i);
                mediaroutechooserdialogfragment.setExtendedSettingsClickListener(onclicklistener);
                mediaroutechooserdialogfragment.show(activity, "android.app.MediaRouteButton:MediaRouteChooserDialogFragment");
                return mediaroutechooserdialogfragment;
            }
        if(activity.findFragmentByTag("android.app.MediaRouteButton:MediaRouteControllerDialogFragment") != null)
        {
            Log.w("MediaRouter", "showDialog(): Route controller dialog already showing!");
            return null;
        } else
        {
            onclicklistener = new MediaRouteControllerDialogFragment();
            onclicklistener.show(activity, "android.app.MediaRouteButton:MediaRouteControllerDialogFragment");
            return onclicklistener;
        }
    }

    private static final String CHOOSER_FRAGMENT_TAG = "android.app.MediaRouteButton:MediaRouteChooserDialogFragment";
    private static final String CONTROLLER_FRAGMENT_TAG = "android.app.MediaRouteButton:MediaRouteControllerDialogFragment";
    private static final String TAG = "MediaRouter";
}
