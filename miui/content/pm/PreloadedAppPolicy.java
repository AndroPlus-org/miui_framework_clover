// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.content.pm;

import android.content.*;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import miui.os.Build;

// Referenced classes of package miui.content.pm:
//            IPreloadedAppManager

public class PreloadedAppPolicy
{

    public PreloadedAppPolicy()
    {
    }

    public static boolean installPreloadedDataApp(Context context, String s, IPackageInstallObserver ipackageinstallobserver, int i)
    {
        if(TextUtils.isEmpty(s) || sProtectedDataApps.contains(s) ^ true)
            return false;
        Intent intent = new Intent("com.xiaomi.market.PreloadedDataAppInstallService");
        intent.setPackage("com.xiaomi.market");
        List list = context.getPackageManager().queryIntentServices(intent, 0);
        if(list == null || list.isEmpty())
        {
            return false;
        } else
        {
            context.bindService(intent, new ServiceConnection(s, i, context, ipackageinstallobserver) {

                public void onServiceConnected(ComponentName componentname, IBinder ibinder)
                {
                    ibinder = IPreloadedAppManager.Stub.asInterface(ibinder);
                    String s1 = packageName;
                    componentname = JVM INSTR new #13  <Class PreloadedAppPolicy$1$1>;
                    observer. super();
                    ibinder.reinstallPreloadedApp(s1, componentname, flags);
_L1:
                    return;
                    componentname;
                    Log.e(miui/content/pm/PreloadedAppPolicy.getName(), componentname.getMessage(), componentname);
                    context.unbindService(this);
                      goto _L1
                }

                public void onServiceDisconnected(ComponentName componentname)
                {
                }

                final Context val$context;
                final int val$flags;
                final IPackageInstallObserver val$observer;
                final String val$packageName;

            
            {
                packageName = s;
                flags = i;
                context = context1;
                observer = ipackageinstallobserver;
                super();
            }
            }
, 1);
            return true;
        }
    }

    public static boolean isAllowDisableSystemApp(Context context, String s, int i)
    {
        return sAllowDisableSystemApps.contains(s);
    }

    public static boolean isProtectedDataApp(Context context, String s, int i)
    {
        return sProtectedDataApps.contains(s);
    }

    private static ArrayList sAllowDisableSystemApps;
    private static ArrayList sProtectedDataApps;
    public final int INSTALL_FLAG_NEED_CONFIRM = 1;
    public final int INSTALL_FLAG_SHOW_TOAST = 2;

    static 
    {
        sProtectedDataApps = new ArrayList();
        sAllowDisableSystemApps = new ArrayList();
        if(android.os.Build.VERSION.SDK_INT < 23 || !(Build.IS_INTERNATIONAL_BUILD ^ true)) goto _L2; else goto _L1
_L1:
        sProtectedDataApps.add("com.xiaomi.pass");
        sProtectedDataApps.add("com.xiaomi.scanner");
        sProtectedDataApps.add("com.xiaomi.gamecenter");
        sProtectedDataApps.add("com.miui.weather2");
        sProtectedDataApps.add("com.miui.notes");
        sProtectedDataApps.add("com.miui.compass");
        sProtectedDataApps.add("com.miui.calculator");
        sProtectedDataApps.add("com.miui.screenrecorder");
        sProtectedDataApps.add("com.miui.cleanmaster");
        sProtectedDataApps.add("com.android.midrive");
        sProtectedDataApps.add("com.xiaomi.midrop");
        sProtectedDataApps.add("com.duokan.reader");
        sProtectedDataApps.add("com.mfashiongallery.emag");
        sProtectedDataApps.add("com.android.email");
        sProtectedDataApps.add("com.miui.virtualsim");
        sProtectedDataApps.add("com.xiaomi.gamecenter.pad");
        sAllowDisableSystemApps.add("com.miui.personalassistant");
        sAllowDisableSystemApps.add("com.miui.voip");
        sAllowDisableSystemApps.add("com.miui.yellowpage");
_L4:
        return;
_L2:
        if(Build.IS_INTERNATIONAL_BUILD)
        {
            sProtectedDataApps.add("com.facemoji.lite.xiaomi");
            sProtectedDataApps.add("com.kikaoem.xiaomi.qisiemoji.inputmethod");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    // Unreferenced inner class miui/content/pm/PreloadedAppPolicy$1$1

/* anonymous class */
    class _cls1 extends android.content.pm.IPackageInstallObserver.Stub
    {

        public void packageInstalled(String s, int i)
            throws RemoteException
        {
            context.unbindService(conn);
            if(observer != null)
                observer.packageInstalled(s, i);
        }

        final _cls1 this$1;
        final ServiceConnection val$conn;
        final Context val$context;
        final IPackageInstallObserver val$observer;

            
            {
                this$1 = final__pcls1;
                context = context1;
                conn = serviceconnection;
                observer = IPackageInstallObserver.this;
                super();
            }
    }

}
