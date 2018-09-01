// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.appwidget;

import android.content.*;
import android.os.Bundle;

// Referenced classes of package android.appwidget:
//            AppWidgetManager

public class AppWidgetProvider extends BroadcastReceiver
{

    public AppWidgetProvider()
    {
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appwidgetmanager, int i, Bundle bundle)
    {
    }

    public void onDeleted(Context context, int ai[])
    {
    }

    public void onDisabled(Context context)
    {
    }

    public void onEnabled(Context context)
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        String s = intent.getAction();
        if(!"android.appwidget.action.APPWIDGET_UPDATE".equals(s)) goto _L2; else goto _L1
_L1:
        intent = intent.getExtras();
        if(intent != null)
        {
            intent = intent.getIntArray("appWidgetIds");
            if(intent != null && intent.length > 0)
                onUpdate(context, AppWidgetManager.getInstance(context), intent);
        }
_L4:
        return;
_L2:
        if("android.appwidget.action.APPWIDGET_DELETED".equals(s))
        {
            intent = intent.getExtras();
            if(intent != null && intent.containsKey("appWidgetId"))
                onDeleted(context, new int[] {
                    intent.getInt("appWidgetId")
                });
        } else
        if("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS".equals(s))
        {
            intent = intent.getExtras();
            if(intent != null && intent.containsKey("appWidgetId") && intent.containsKey("appWidgetOptions"))
            {
                int i = intent.getInt("appWidgetId");
                intent = intent.getBundle("appWidgetOptions");
                onAppWidgetOptionsChanged(context, AppWidgetManager.getInstance(context), i, intent);
            }
        } else
        if("android.appwidget.action.APPWIDGET_ENABLED".equals(s))
            onEnabled(context);
        else
        if("android.appwidget.action.APPWIDGET_DISABLED".equals(s))
            onDisabled(context);
        else
        if("android.appwidget.action.APPWIDGET_RESTORED".equals(s))
        {
            Bundle bundle = intent.getExtras();
            if(bundle != null)
            {
                intent = bundle.getIntArray("appWidgetOldIds");
                int ai[] = bundle.getIntArray("appWidgetIds");
                if(intent != null && intent.length > 0)
                {
                    onRestored(context, intent, ai);
                    onUpdate(context, AppWidgetManager.getInstance(context), ai);
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onRestored(Context context, int ai[], int ai1[])
    {
    }

    public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
    }
}
