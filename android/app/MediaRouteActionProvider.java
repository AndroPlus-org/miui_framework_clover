// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.media.MediaRouter;
import android.util.Log;
import android.view.*;
import java.lang.ref.WeakReference;

// Referenced classes of package android.app:
//            MediaRouteButton

public class MediaRouteActionProvider extends ActionProvider
{
    private static class MediaRouterCallback extends android.media.MediaRouter.SimpleCallback
    {

        private void refreshRoute(MediaRouter mediarouter)
        {
            MediaRouteActionProvider mediarouteactionprovider = (MediaRouteActionProvider)mProviderWeak.get();
            if(mediarouteactionprovider != null)
                MediaRouteActionProvider._2D_wrap0(mediarouteactionprovider);
            else
                mediarouter.removeCallback(this);
        }

        public void onRouteAdded(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute(mediarouter);
        }

        public void onRouteChanged(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute(mediarouter);
        }

        public void onRouteRemoved(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoute(mediarouter);
        }

        private final WeakReference mProviderWeak;

        public MediaRouterCallback(MediaRouteActionProvider mediarouteactionprovider)
        {
            mProviderWeak = new WeakReference(mediarouteactionprovider);
        }
    }


    static void _2D_wrap0(MediaRouteActionProvider mediarouteactionprovider)
    {
        mediarouteactionprovider.refreshRoute();
    }

    public MediaRouteActionProvider(Context context)
    {
        super(context);
        mContext = context;
        mRouter = (MediaRouter)context.getSystemService("media_router");
        setRouteTypes(1);
    }

    private void refreshRoute()
    {
        refreshVisibility();
    }

    public boolean isVisible()
    {
        return mRouter.isRouteAvailable(mRouteTypes, 1);
    }

    public View onCreateActionView()
    {
        throw new UnsupportedOperationException("Use onCreateActionView(MenuItem) instead.");
    }

    public View onCreateActionView(MenuItem menuitem)
    {
        if(mButton != null)
            Log.e("MediaRouteActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old one...");
        mButton = new MediaRouteButton(mContext);
        mButton.setRouteTypes(mRouteTypes);
        mButton.setExtendedSettingsClickListener(mExtendedSettingsListener);
        mButton.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -1));
        return mButton;
    }

    public boolean onPerformDefaultAction()
    {
        if(mButton != null)
            return mButton.showDialogInternal();
        else
            return false;
    }

    public boolean overridesItemVisibility()
    {
        return true;
    }

    public void setExtendedSettingsClickListener(android.view.View.OnClickListener onclicklistener)
    {
        mExtendedSettingsListener = onclicklistener;
        if(mButton != null)
            mButton.setExtendedSettingsClickListener(onclicklistener);
    }

    public void setRouteTypes(int i)
    {
        if(mRouteTypes != i)
        {
            if(mRouteTypes != 0)
                mRouter.removeCallback(mCallback);
            mRouteTypes = i;
            if(i != 0)
                mRouter.addCallback(i, mCallback, 8);
            refreshRoute();
            if(mButton != null)
                mButton.setRouteTypes(mRouteTypes);
        }
    }

    private static final String TAG = "MediaRouteActionProvider";
    private MediaRouteButton mButton;
    private final MediaRouterCallback mCallback = new MediaRouterCallback(this);
    private final Context mContext;
    private android.view.View.OnClickListener mExtendedSettingsListener;
    private int mRouteTypes;
    private final MediaRouter mRouter;
}
