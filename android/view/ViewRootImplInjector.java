// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.miui.AppOpsUtils;
import android.os.SystemProperties;
import android.util.Log;
import java.util.*;
import miui.os.Build;

// Referenced classes of package android.view:
//            MotionEvent, ViewRootImpl, View

public class ViewRootImplInjector
{

    public ViewRootImplInjector()
    {
    }

    static boolean allowDrawIfAnimating(String s)
    {
        return PACKAGE_ALLOW_DRAW_IF_ANIMATING.contains(s);
    }

    public static void checkForThreeGesture(MotionEvent motionevent)
    {
        if(motionevent.getPointerCount() == 3 && SystemProperties.getBoolean("sys.miui.screenshot", false))
        {
            motionevent.setAction(3);
            Log.d("ViewRootImpl", "cancle motionEvent because of threeGesture detecting");
        }
    }

    private static String elementToString(StackTraceElement stacktraceelement)
    {
        StringBuilder stringbuilder = new StringBuilder(80);
        stringbuilder.append(stacktraceelement.getClassName());
        stringbuilder.append('.');
        stringbuilder.append(stacktraceelement.getMethodName());
        return stringbuilder.toString();
    }

    static boolean needUpdateWindowState(ViewRootImpl viewrootimpl, boolean flag)
    {
        if(viewrootimpl == null || flag ^ true)
            return true;
        viewrootimpl = viewrootimpl.mContext;
        if(viewrootimpl == null)
            return true;
        String s = viewrootimpl.getPackageName();
        if(!sLauncher.getPackageName().equals(s))
            break MISSING_BLOCK_LABEL_94;
        viewrootimpl = (ActivityManager)viewrootimpl.getSystemService("activity");
        viewrootimpl = ((android.app.ActivityManager.RunningTaskInfo)viewrootimpl.getRunningTasks(1).get(0)).topActivity;
        if(viewrootimpl == null)
            break MISSING_BLOCK_LABEL_94;
        flag = sLauncher.getClassName().equals(viewrootimpl.getClassName());
        if(flag)
            return false;
        break MISSING_BLOCK_LABEL_94;
        viewrootimpl;
        return true;
    }

    public static void transformWindowType(View view, WindowManager.LayoutParams layoutparams)
    {
        if(Build.IS_INTERNATIONAL_BUILD || AppOpsUtils.isXOptMode())
            return;
        if(layoutparams.type != 2005) goto _L2; else goto _L1
_L1:
        view = new ArrayList();
        view.add("android.view.ViewRootImplInjector.transformWindowType");
        view.add("android.view.ViewRootImpl.setView");
        view.add("android.view.WindowManagerGlobal.addView");
        view.add("android.view.WindowManagerImpl.addView");
        view.add("android.widget.Toast$TN.handleShow");
        StackTraceElement astacktraceelement[];
        Exception exception = JVM INSTR new #107 <Class Exception>;
        exception.Exception();
        astacktraceelement = exception.getStackTrace();
        if(astacktraceelement.length <= view.size()) goto _L2; else goto _L3
_L3:
        int i = 0;
_L4:
        if(i >= view.size())
            break; /* Loop/switch isn't completed */
        if(!((String)view.get(i)).equals(elementToString(astacktraceelement[i])))
        {
            layoutparams.type = 2003;
            return;
        }
        i++;
        if(true) goto _L4; else goto _L2
        view;
        Log.e("ViewRootImpl", " transformWindowTye error ", view);
_L2:
    }

    private static final int GESTURE_FINGER_COUNT = 3;
    private static final Set PACKAGE_ALLOW_DRAW_IF_ANIMATING = new HashSet() {

            
            {
                add("com.miui.home");
                add("com.android.systemui");
            }
    }
;
    private static final String PACKAGE_NAME_HOME = "com.miui.home";
    private static final String PACKAGE_NAME_SYSTEMUI = "com.android.systemui";
    private static final String TAG = "ViewRootImpl";
    static ComponentName sLauncher = new ComponentName("com.miui.home", "com.miui.home.launcher.Launcher");

}
