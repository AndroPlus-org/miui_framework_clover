// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.appwidget;

import android.app.PendingIntent;
import android.content.*;
import android.content.pm.ParceledListSlice;
import android.content.res.Resources;
import android.os.*;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;
import com.android.internal.appwidget.IAppWidgetService;
import java.util.*;

// Referenced classes of package android.appwidget:
//            AppWidgetProviderInfo

public class AppWidgetManager
{

    public AppWidgetManager(Context context, IAppWidgetService iappwidgetservice)
    {
        mPackageName = context.getOpPackageName();
        mService = iappwidgetservice;
        mDisplayMetrics = context.getResources().getDisplayMetrics();
    }

    private boolean bindAppWidgetIdIfAllowed(int i, int j, ComponentName componentname, Bundle bundle)
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.bindAppWidgetId(mPackageName, i, j, componentname, bundle);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public static AppWidgetManager getInstance(Context context)
    {
        return (AppWidgetManager)context.getSystemService("appwidget");
    }

    public void bindAppWidgetId(int i, ComponentName componentname)
    {
        if(mService == null)
        {
            return;
        } else
        {
            bindAppWidgetId(i, componentname, null);
            return;
        }
    }

    public void bindAppWidgetId(int i, ComponentName componentname, Bundle bundle)
    {
        if(mService == null)
        {
            return;
        } else
        {
            bindAppWidgetIdIfAllowed(i, Process.myUserHandle(), componentname, bundle);
            return;
        }
    }

    public boolean bindAppWidgetIdIfAllowed(int i, ComponentName componentname)
    {
        if(mService == null)
            return false;
        else
            return bindAppWidgetIdIfAllowed(i, UserHandle.myUserId(), componentname, null);
    }

    public boolean bindAppWidgetIdIfAllowed(int i, ComponentName componentname, Bundle bundle)
    {
        if(mService == null)
            return false;
        else
            return bindAppWidgetIdIfAllowed(i, UserHandle.myUserId(), componentname, bundle);
    }

    public boolean bindAppWidgetIdIfAllowed(int i, UserHandle userhandle, ComponentName componentname, Bundle bundle)
    {
        if(mService == null)
            return false;
        else
            return bindAppWidgetIdIfAllowed(i, userhandle.getIdentifier(), componentname, bundle);
    }

    public void bindRemoteViewsService(String s, int i, Intent intent, IBinder ibinder)
    {
        if(mService == null)
            return;
        try
        {
            mService.bindRemoteViewsService(s, i, intent, ibinder);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public int[] getAppWidgetIds(ComponentName componentname)
    {
        if(mService == null)
            return new int[0];
        try
        {
            componentname = mService.getAppWidgetIds(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public AppWidgetProviderInfo getAppWidgetInfo(int i)
    {
        if(mService == null)
            return null;
        AppWidgetProviderInfo appwidgetproviderinfo;
        try
        {
            appwidgetproviderinfo = mService.getAppWidgetInfo(mPackageName, i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(appwidgetproviderinfo == null)
            break MISSING_BLOCK_LABEL_36;
        appwidgetproviderinfo.updateDimensions(mDisplayMetrics);
        return appwidgetproviderinfo;
    }

    public Bundle getAppWidgetOptions(int i)
    {
        if(mService == null)
            return Bundle.EMPTY;
        Bundle bundle;
        try
        {
            bundle = mService.getAppWidgetOptions(mPackageName, i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return bundle;
    }

    public List getInstalledProviders()
    {
        if(mService == null)
            return Collections.emptyList();
        else
            return getInstalledProvidersForProfile(1, null, null);
    }

    public List getInstalledProviders(int i)
    {
        if(mService == null)
            return Collections.emptyList();
        else
            return getInstalledProvidersForProfile(i, null, null);
    }

    public List getInstalledProvidersForPackage(String s, UserHandle userhandle)
    {
        if(s == null)
            throw new NullPointerException("A non-null package must be passed to this method. If you want all widgets regardless of package, see getInstalledProvidersForProfile(UserHandle)");
        if(mService == null)
            return Collections.emptyList();
        else
            return getInstalledProvidersForProfile(1, userhandle, s);
    }

    public List getInstalledProvidersForProfile(int i, UserHandle userhandle, String s)
    {
        if(mService == null)
            return Collections.emptyList();
        UserHandle userhandle1 = userhandle;
        if(userhandle == null)
            userhandle1 = Process.myUserHandle();
        try
        {
            s = mService.getInstalledProvidersForProfile(i, userhandle1.getIdentifier(), s);
        }
        // Misplaced declaration of an exception variable
        catch(UserHandle userhandle)
        {
            throw userhandle.rethrowFromSystemServer();
        }
        if(s != null)
            break MISSING_BLOCK_LABEL_48;
        return Collections.emptyList();
        for(userhandle = s.getList().iterator(); userhandle.hasNext(); ((AppWidgetProviderInfo)userhandle.next()).updateDimensions(mDisplayMetrics));
        userhandle = s.getList();
        return userhandle;
    }

    public List getInstalledProvidersForProfile(UserHandle userhandle)
    {
        if(mService == null)
            return Collections.emptyList();
        else
            return getInstalledProvidersForProfile(1, userhandle, null);
    }

    public boolean hasBindAppWidgetPermission(String s)
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.hasBindAppWidgetPermission(s, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean hasBindAppWidgetPermission(String s, int i)
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.hasBindAppWidgetPermission(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isBoundWidgetPackage(String s, int i)
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.isBoundWidgetPackage(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isRequestPinAppWidgetSupported()
    {
        boolean flag;
        try
        {
            flag = mService.isRequestPinAppWidgetSupported();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void notifyAppWidgetViewDataChanged(int i, int j)
    {
        if(mService == null)
        {
            return;
        } else
        {
            notifyAppWidgetViewDataChanged(new int[] {
                i
            }, j);
            return;
        }
    }

    public void notifyAppWidgetViewDataChanged(int ai[], int i)
    {
        if(mService == null)
            return;
        try
        {
            mService.notifyAppWidgetViewDataChanged(mPackageName, ai, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw ai.rethrowFromSystemServer();
        }
    }

    public void partiallyUpdateAppWidget(int i, RemoteViews remoteviews)
    {
        if(mService == null)
        {
            return;
        } else
        {
            partiallyUpdateAppWidget(new int[] {
                i
            }, remoteviews);
            return;
        }
    }

    public void partiallyUpdateAppWidget(int ai[], RemoteViews remoteviews)
    {
        if(mService == null)
            return;
        try
        {
            mService.partiallyUpdateAppWidgetIds(mPackageName, ai, remoteviews);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw ai.rethrowFromSystemServer();
        }
    }

    public boolean requestPinAppWidget(ComponentName componentname, PendingIntent pendingintent)
    {
        return requestPinAppWidget(componentname, null, pendingintent);
    }

    public boolean requestPinAppWidget(ComponentName componentname, Bundle bundle, PendingIntent pendingintent)
    {
        Object obj = null;
        IAppWidgetService iappwidgetservice;
        String s;
        try
        {
            iappwidgetservice = mService;
            s = mPackageName;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(pendingintent != null) goto _L2; else goto _L1
_L1:
        pendingintent = obj;
_L4:
        return iappwidgetservice.requestPinAppWidget(s, componentname, bundle, pendingintent);
_L2:
        pendingintent = pendingintent.getIntentSender();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setBindAppWidgetPermission(String s, int i, boolean flag)
    {
        if(mService == null)
            return;
        try
        {
            mService.setBindAppWidgetPermission(s, i, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setBindAppWidgetPermission(String s, boolean flag)
    {
        if(mService == null)
        {
            return;
        } else
        {
            setBindAppWidgetPermission(s, UserHandle.myUserId(), flag);
            return;
        }
    }

    public void unbindRemoteViewsService(String s, int i, Intent intent)
    {
        if(mService == null)
            return;
        try
        {
            mService.unbindRemoteViewsService(s, i, intent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void updateAppWidget(int i, RemoteViews remoteviews)
    {
        if(mService == null)
        {
            return;
        } else
        {
            updateAppWidget(new int[] {
                i
            }, remoteviews);
            return;
        }
    }

    public void updateAppWidget(ComponentName componentname, RemoteViews remoteviews)
    {
        if(mService == null)
            return;
        try
        {
            mService.updateAppWidgetProvider(componentname, remoteviews);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void updateAppWidget(int ai[], RemoteViews remoteviews)
    {
        if(mService == null)
            return;
        try
        {
            mService.updateAppWidgetIds(mPackageName, ai, remoteviews);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw ai.rethrowFromSystemServer();
        }
    }

    public void updateAppWidgetOptions(int i, Bundle bundle)
    {
        if(mService == null)
            return;
        try
        {
            mService.updateAppWidgetOptions(mPackageName, i, bundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw bundle.rethrowFromSystemServer();
        }
    }

    public static final String ACTION_APPWIDGET_BIND = "android.appwidget.action.APPWIDGET_BIND";
    public static final String ACTION_APPWIDGET_CONFIGURE = "android.appwidget.action.APPWIDGET_CONFIGURE";
    public static final String ACTION_APPWIDGET_DELETED = "android.appwidget.action.APPWIDGET_DELETED";
    public static final String ACTION_APPWIDGET_DISABLED = "android.appwidget.action.APPWIDGET_DISABLED";
    public static final String ACTION_APPWIDGET_ENABLED = "android.appwidget.action.APPWIDGET_ENABLED";
    public static final String ACTION_APPWIDGET_HOST_RESTORED = "android.appwidget.action.APPWIDGET_HOST_RESTORED";
    public static final String ACTION_APPWIDGET_OPTIONS_CHANGED = "android.appwidget.action.APPWIDGET_UPDATE_OPTIONS";
    public static final String ACTION_APPWIDGET_PICK = "android.appwidget.action.APPWIDGET_PICK";
    public static final String ACTION_APPWIDGET_RESTORED = "android.appwidget.action.APPWIDGET_RESTORED";
    public static final String ACTION_APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
    public static final String ACTION_KEYGUARD_APPWIDGET_PICK = "android.appwidget.action.KEYGUARD_APPWIDGET_PICK";
    public static final String EXTRA_APPWIDGET_ID = "appWidgetId";
    public static final String EXTRA_APPWIDGET_IDS = "appWidgetIds";
    public static final String EXTRA_APPWIDGET_OLD_IDS = "appWidgetOldIds";
    public static final String EXTRA_APPWIDGET_OPTIONS = "appWidgetOptions";
    public static final String EXTRA_APPWIDGET_PREVIEW = "appWidgetPreview";
    public static final String EXTRA_APPWIDGET_PROVIDER = "appWidgetProvider";
    public static final String EXTRA_APPWIDGET_PROVIDER_PROFILE = "appWidgetProviderProfile";
    public static final String EXTRA_CATEGORY_FILTER = "categoryFilter";
    public static final String EXTRA_CUSTOM_EXTRAS = "customExtras";
    public static final String EXTRA_CUSTOM_INFO = "customInfo";
    public static final String EXTRA_CUSTOM_SORT = "customSort";
    public static final String EXTRA_HOST_ID = "hostId";
    public static final int INVALID_APPWIDGET_ID = 0;
    public static final String META_DATA_APPWIDGET_PROVIDER = "android.appwidget.provider";
    public static final String OPTION_APPWIDGET_HOST_CATEGORY = "appWidgetCategory";
    public static final String OPTION_APPWIDGET_MAX_HEIGHT = "appWidgetMaxHeight";
    public static final String OPTION_APPWIDGET_MAX_WIDTH = "appWidgetMaxWidth";
    public static final String OPTION_APPWIDGET_MIN_HEIGHT = "appWidgetMinHeight";
    public static final String OPTION_APPWIDGET_MIN_WIDTH = "appWidgetMinWidth";
    private final DisplayMetrics mDisplayMetrics;
    private final String mPackageName;
    private final IAppWidgetService mService;
}
