// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.dreams;

import android.content.*;
import android.content.res.Resources;
import android.os.*;
import android.util.Slog;

// Referenced classes of package android.service.dreams:
//            IDreamManager

public final class Sandman
{

    private Sandman()
    {
    }

    private static boolean isScreenSaverActivatedOnDock(Context context)
    {
        boolean flag = false;
        int i;
        if(context.getResources().getBoolean(0x112004b))
            i = 1;
        else
            i = 0;
        if(android.provider.Settings.Secure.getIntForUser(context.getContentResolver(), "screensaver_activate_on_dock", i, -2) != 0)
            flag = true;
        return flag;
    }

    private static boolean isScreenSaverEnabled(Context context)
    {
        boolean flag = false;
        int i;
        if(context.getResources().getBoolean(0x112004d))
            i = 1;
        else
            i = 0;
        if(android.provider.Settings.Secure.getIntForUser(context.getContentResolver(), "screensaver_enabled", i, -2) != 0)
            flag = true;
        return flag;
    }

    public static boolean shouldStartDockApp(Context context, Intent intent)
    {
        context = intent.resolveActivity(context.getPackageManager());
        boolean flag;
        if(context != null)
            flag = context.equals(SOMNAMBULATOR_COMPONENT) ^ true;
        else
            flag = false;
        return flag;
    }

    private static void startDream(Context context, boolean flag)
    {
        IDreamManager idreammanager = IDreamManager.Stub.asInterface(ServiceManager.getService("dreams"));
        if(idreammanager == null) goto _L2; else goto _L1
_L1:
        if(!(idreammanager.isDreaming() ^ true)) goto _L2; else goto _L3
_L3:
        if(!flag) goto _L5; else goto _L4
_L4:
        Slog.i("Sandman", "Activating dream while docked.");
        ((PowerManager)context.getSystemService("power")).wakeUp(SystemClock.uptimeMillis(), "android.service.dreams:DREAM");
_L6:
        idreammanager.dream();
_L2:
        return;
_L5:
        Slog.i("Sandman", "Activating dream by user request.");
          goto _L6
        context;
        Slog.e("Sandman", "Could not start dream when docked.", context);
          goto _L2
    }

    public static void startDreamByUserRequest(Context context)
    {
        startDream(context, false);
    }

    public static void startDreamWhenDockedIfAppropriate(Context context)
    {
        if(!isScreenSaverEnabled(context) || isScreenSaverActivatedOnDock(context) ^ true)
        {
            Slog.i("Sandman", "Dreams currently disabled for docks.");
            return;
        } else
        {
            startDream(context, true);
            return;
        }
    }

    private static final ComponentName SOMNAMBULATOR_COMPONENT = new ComponentName("com.android.systemui", "com.android.systemui.Somnambulator");
    private static final String TAG = "Sandman";

}
