// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.content.ContentResolver;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.res.Resources;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.ArrayMap;
import android.util.Slog;
import com.android.server.SystemConfig;
import java.util.*;

public final class CarrierAppUtils
{

    private CarrierAppUtils()
    {
    }

    public static void disableCarrierAppsUntilPrivileged(String s, IPackageManager ipackagemanager, ContentResolver contentresolver, int i)
    {
        com/android/internal/telephony/CarrierAppUtils;
        JVM INSTR monitorenter ;
        SystemConfig systemconfig = SystemConfig.getInstance();
        disableCarrierAppsUntilPrivileged(s, ipackagemanager, null, contentresolver, i, Resources.getSystem().getStringArray(0x1070021), systemconfig.getDisabledUntilUsedPreinstalledCarrierAssociatedApps());
        com/android/internal/telephony/CarrierAppUtils;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public static void disableCarrierAppsUntilPrivileged(String s, IPackageManager ipackagemanager, TelephonyManager telephonymanager, ContentResolver contentresolver, int i)
    {
        com/android/internal/telephony/CarrierAppUtils;
        JVM INSTR monitorenter ;
        SystemConfig systemconfig = SystemConfig.getInstance();
        disableCarrierAppsUntilPrivileged(s, ipackagemanager, telephonymanager, contentresolver, i, Resources.getSystem().getStringArray(0x1070021), systemconfig.getDisabledUntilUsedPreinstalledCarrierAssociatedApps());
        com/android/internal/telephony/CarrierAppUtils;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public static void disableCarrierAppsUntilPrivileged(String s, IPackageManager ipackagemanager, TelephonyManager telephonymanager, ContentResolver contentresolver, int i, String as[], ArrayMap arraymap)
    {
        boolean flag;
        Object obj1;
        Object obj2;
        boolean flag1;
        Object obj = getDefaultCarrierAppCandidatesHelper(ipackagemanager, i, as);
        if(obj == null || ((List) (obj)).isEmpty())
            return;
        arraymap = getDefaultCarrierAssociatedAppsHelper(ipackagemanager, i, arraymap);
        as = new ArrayList();
        Object obj3;
        ApplicationInfo applicationinfo1;
        if(android.provider.Settings.Secure.getIntForUser(contentresolver, "carrier_apps_handled", 0, i) == 1)
            flag = true;
        else
            flag = false;
        obj = ((Iterable) (obj)).iterator();
_L10:
        if(!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        obj1 = (ApplicationInfo)((Iterator) (obj)).next();
        obj2 = ((ApplicationInfo) (obj1)).packageName;
        if(telephonymanager == null) goto _L4; else goto _L3
_L3:
        if(telephonymanager.checkCarrierPrivilegesForPackageAnyPhone(((String) (obj2))) == 1)
            flag1 = true;
        else
            flag1 = false;
_L9:
        if(!flag1) goto _L6; else goto _L5
_L5:
        if(!((ApplicationInfo) (obj1)).isUpdatedSystemApp() && (((ApplicationInfo) (obj1)).enabledSetting == 0 || ((ApplicationInfo) (obj1)).enabledSetting == 4))
        {
            obj3 = JVM INSTR new #105 <Class StringBuilder>;
            ((StringBuilder) (obj3)).StringBuilder();
            Slog.i("CarrierAppUtils", ((StringBuilder) (obj3)).append("Update state(").append(((String) (obj2))).append("): ENABLED for user ").append(i).toString());
            ipackagemanager.setApplicationEnabledSetting(((String) (obj2)), 1, 1, i, s);
        }
        obj2 = (List)arraymap.get(obj2);
        if(obj2 == null) goto _L8; else goto _L7
_L7:
        obj3 = ((Iterable) (obj2)).iterator();
        do
        {
            if(!((Iterator) (obj3)).hasNext())
                break;
            applicationinfo1 = (ApplicationInfo)((Iterator) (obj3)).next();
            if(applicationinfo1.enabledSetting == 0 || applicationinfo1.enabledSetting == 4)
            {
                obj2 = JVM INSTR new #105 <Class StringBuilder>;
                ((StringBuilder) (obj2)).StringBuilder();
                Slog.i("CarrierAppUtils", ((StringBuilder) (obj2)).append("Update associated state(").append(applicationinfo1.packageName).append("): ENABLED for user ").append(i).toString());
                ipackagemanager.setApplicationEnabledSetting(applicationinfo1.packageName, 1, 1, i, s);
            }
        } while(true);
          goto _L8
        s;
        Slog.w("CarrierAppUtils", "Could not reach PackageManager", s);
_L13:
        return;
_L4:
        flag1 = false;
          goto _L9
_L8:
        as.add(((ApplicationInfo) (obj1)).packageName);
          goto _L10
_L6:
        if(!((ApplicationInfo) (obj1)).isUpdatedSystemApp() && ((ApplicationInfo) (obj1)).enabledSetting == 0)
        {
            obj1 = JVM INSTR new #105 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            Slog.i("CarrierAppUtils", ((StringBuilder) (obj1)).append("Update state(").append(((String) (obj2))).append("): DISABLED_UNTIL_USED for user ").append(i).toString());
            ipackagemanager.setApplicationEnabledSetting(((String) (obj2)), 4, 0, i, s);
        }
        if(flag) goto _L10; else goto _L11
_L11:
        obj1 = (List)arraymap.get(obj2);
        if(obj1 == null) goto _L10; else goto _L12
_L12:
        Iterator iterator = ((Iterable) (obj1)).iterator();
        while(iterator.hasNext()) 
        {
            ApplicationInfo applicationinfo = (ApplicationInfo)iterator.next();
            if(applicationinfo.enabledSetting == 0)
            {
                StringBuilder stringbuilder = JVM INSTR new #105 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Slog.i("CarrierAppUtils", stringbuilder.append("Update associated state(").append(applicationinfo.packageName).append("): DISABLED_UNTIL_USED for user ").append(i).toString());
                ipackagemanager.setApplicationEnabledSetting(applicationinfo.packageName, 4, 0, i, s);
            }
        }
          goto _L10
_L2:
        if(flag)
            break MISSING_BLOCK_LABEL_586;
        android.provider.Settings.Secure.putIntForUser(contentresolver, "carrier_apps_handled", 1, i);
        if(!as.isEmpty())
        {
            s = new String[as.size()];
            as.toArray(s);
            ipackagemanager.grantDefaultPermissionsToEnabledCarrierApps(s, i);
        }
          goto _L13
    }

    private static ApplicationInfo getApplicationInfoIfSystemApp(IPackageManager ipackagemanager, int i, String s)
    {
        ipackagemanager = ipackagemanager.getApplicationInfo(s, 32768, i);
        if(ipackagemanager == null)
            break MISSING_BLOCK_LABEL_36;
        boolean flag = ipackagemanager.isSystemApp();
        if(flag)
            return ipackagemanager;
        break MISSING_BLOCK_LABEL_36;
        ipackagemanager;
        Slog.w("CarrierAppUtils", "Could not reach PackageManager", ipackagemanager);
        return null;
    }

    public static List getDefaultCarrierAppCandidates(IPackageManager ipackagemanager, int i)
    {
        return getDefaultCarrierAppCandidatesHelper(ipackagemanager, i, Resources.getSystem().getStringArray(0x1070021));
    }

    private static List getDefaultCarrierAppCandidatesHelper(IPackageManager ipackagemanager, int i, String as[])
    {
        if(as == null || as.length == 0)
            return null;
        ArrayList arraylist = new ArrayList(as.length);
        for(int j = 0; j < as.length; j++)
        {
            ApplicationInfo applicationinfo = getApplicationInfoIfSystemApp(ipackagemanager, i, as[j]);
            if(applicationinfo != null)
                arraylist.add(applicationinfo);
        }

        return arraylist;
    }

    public static List getDefaultCarrierApps(IPackageManager ipackagemanager, TelephonyManager telephonymanager, int i)
    {
        ipackagemanager = getDefaultCarrierAppCandidates(ipackagemanager, i);
        if(ipackagemanager == null || ipackagemanager.isEmpty())
            return null;
        i = ipackagemanager.size() - 1;
        while(i >= 0) 
        {
            boolean flag;
            if(telephonymanager.checkCarrierPrivilegesForPackageAnyPhone(((ApplicationInfo)ipackagemanager.get(i)).packageName) == 1)
                flag = true;
            else
                flag = false;
            if(!flag)
                ipackagemanager.remove(i);
            i--;
        }
        return ipackagemanager;
    }

    private static Map getDefaultCarrierAssociatedAppsHelper(IPackageManager ipackagemanager, int i, ArrayMap arraymap)
    {
        int j = arraymap.size();
        ArrayMap arraymap1 = new ArrayMap(j);
        for(int k = 0; k < j; k++)
        {
            String s = (String)arraymap.keyAt(k);
            List list = (List)arraymap.valueAt(k);
            for(int l = 0; l < list.size(); l++)
            {
                ApplicationInfo applicationinfo = getApplicationInfoIfSystemApp(ipackagemanager, i, (String)list.get(l));
                if(applicationinfo == null || !(applicationinfo.isUpdatedSystemApp() ^ true))
                    continue;
                List list1 = (List)arraymap1.get(s);
                Object obj = list1;
                if(list1 == null)
                {
                    obj = new ArrayList();
                    arraymap1.put(s, obj);
                }
                ((List) (obj)).add(applicationinfo);
            }

        }

        return arraymap1;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "CarrierAppUtils";
}
