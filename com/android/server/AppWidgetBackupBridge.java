// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server;

import java.util.List;

// Referenced classes of package com.android.server:
//            WidgetBackupProvider

public class AppWidgetBackupBridge
{

    public AppWidgetBackupBridge()
    {
    }

    public static List getWidgetParticipants(int i)
    {
        List list = null;
        if(sAppWidgetService != null)
            list = sAppWidgetService.getWidgetParticipants(i);
        return list;
    }

    public static byte[] getWidgetState(String s, int i)
    {
        byte abyte0[] = null;
        if(sAppWidgetService != null)
            abyte0 = sAppWidgetService.getWidgetState(s, i);
        return abyte0;
    }

    public static void register(WidgetBackupProvider widgetbackupprovider)
    {
        sAppWidgetService = widgetbackupprovider;
    }

    public static void restoreFinished(int i)
    {
        if(sAppWidgetService != null)
            sAppWidgetService.restoreFinished(i);
    }

    public static void restoreStarting(int i)
    {
        if(sAppWidgetService != null)
            sAppWidgetService.restoreStarting(i);
    }

    public static void restoreWidgetState(String s, byte abyte0[], int i)
    {
        if(sAppWidgetService != null)
            sAppWidgetService.restoreWidgetState(s, abyte0, i);
    }

    private static WidgetBackupProvider sAppWidgetService;
}
