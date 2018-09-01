// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.app.ActivityOptions;
import android.app.AppGlobals;
import android.content.Context;
import android.content.pm.IPackageManager;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.Display;
import android.view.WindowManager;
import java.util.HashMap;
import miui.os.Build;

// Referenced classes of package android.util:
//            Log

public class MiuiMultiWindowUtils
{

    public MiuiMultiWindowUtils()
    {
    }

    public static ActivityOptions getActivityOptions(Context context, String s)
    {
        int i = android.provider.Settings.Secure.getIntForUser(context.getContentResolver(), "gb_boosting", 0, -2);
        boolean flag;
        Object obj;
        if(i == 1)
            flag = Build.IS_INTERNATIONAL_BUILD ^ true;
        else
            flag = false;
        Log.d("FreeformWindow", (new StringBuilder()).append("isLaunchMultiWindow:").append(flag).append(" gameKey:").append(i).toString());
        obj = null;
        context = obj;
        if(flag)
        {
            context = obj;
            if(supportFreeFromWindow(s))
            {
                context = ActivityOptions.makeBasic();
                context.setLaunchStackId(2);
            }
        }
        return context;
    }

    public static Rect getFreeformRect(Context context, Rect rect)
    {
        if(context == null)
            return null;
        WindowManager windowmanager = (WindowManager)context.getSystemService("window");
        context = new Rect();
        windowmanager.getDefaultDisplay().getRectSize(context);
        boolean flag;
        int j;
        int k;
        int l;
        int i1;
        if(context.width() > context.height())
            flag = false;
        else
            flag = true;
        if(flag)
            j = (context.width() - 930) / 2;
        else
            j = 150;
        k = j + 930;
        if(flag)
            l = 150;
        else
            l = (context.height() - 1070) / 2;
        i1 = l + 1070;
        if(rect == null)
            return new Rect(j, l, k, i1);
        int i;
        if(flag)
        {
            i = l;
            if(rect.top == l)
                i = 150;
            i1 = i + 1070;
            l = i;
            i = j;
            j = i1;
        } else
        {
            i = j;
            if(rect.left == j)
                i = context.width() - 930;
            k = i + 930;
            j = i1;
        }
        return new Rect(i, l, k, j);
    }

    public static int getOrientation(Context context)
    {
        if(context == null)
            return -1;
        context = (WindowManager)context.getSystemService("window");
        Rect rect = new Rect();
        context.getDefaultDisplay().getRectSize(rect);
        byte byte0;
        if(rect.width() > rect.height())
            byte0 = 2;
        else
            byte0 = 1;
        return byte0;
    }

    public static boolean supportFreeFromWindow(int i)
    {
        String s = "";
        String s1 = AppGlobals.getPackageManager().getNameForUid(i);
        s = s1;
_L1:
        RemoteException remoteexception;
        boolean flag;
        if(sIMList.get(s) != null)
            flag = true;
        else
            flag = false;
        return flag;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public static boolean supportFreeFromWindow(String s)
    {
        boolean flag;
        if(sIMList.get(s) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static final boolean ENABLE_WHITELIST = false;
    static final int FREEFORM_TO_NAVIBAR = 150;
    static final int FREEFORM_WINDOW_HEIGHT = 1070;
    static final int FREEFORM_WINDOW_WIDTH = 930;
    static final String TAG = "FreeformWindow";
    private static HashMap sGameList;
    private static HashMap sIMList;

    static 
    {
        sGameList = new HashMap();
        sGameList.put("com.happyelements.AndroidAnimal", Integer.valueOf(1));
        sGameList.put("com.tencent.tmgp.sgame", Integer.valueOf(2));
        sGameList.put("com.netease.onmyoji.mi", Integer.valueOf(3));
        sIMList = new HashMap();
        sIMList.put("com.tencent.mm", Integer.valueOf(1));
        sIMList.put("com.tencent.mobileqq", Integer.valueOf(2));
    }
}
