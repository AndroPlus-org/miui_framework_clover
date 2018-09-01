// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.powercenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class BatterySipper
    implements Comparable
{

    public BatterySipper(Context context, int i, int j, double d)
    {
        uid = -1;
        drainType = i;
        value = d;
        uid = j;
        getNameAndPackageName(context);
    }

    private void getNameAndPackageName(Context context)
    {
        int i;
        Object obj;
        i = 0;
        obj = context.getPackageManager();
        context = ((PackageManager) (obj)).getPackagesForUid(uid);
        if(context == null)
            return;
        if(context.length != 1) goto _L2; else goto _L1
_L1:
        obj = ((PackageManager) (obj)).getApplicationLabel(((PackageManager) (obj)).getApplicationInfo(context[0], 0));
        if(obj == null)
            break MISSING_BLOCK_LABEL_54;
        name = ((CharSequence) (obj)).toString();
        defaultPackageName = context[0];
_L6:
        return;
_L2:
        int j = context.length;
_L4:
        String s;
        if(i >= j)
            continue; /* Loop/switch isn't completed */
        s = context[i];
        Object obj1;
        obj1 = ((PackageManager) (obj)).getPackageInfo(s, 0);
        if(((PackageInfo) (obj1)).sharedUserLabel == 0)
            break MISSING_BLOCK_LABEL_139;
        obj1 = ((PackageManager) (obj)).getText(s, ((PackageInfo) (obj1)).sharedUserLabel, ((PackageInfo) (obj1)).applicationInfo);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_128;
        name = ((CharSequence) (obj1)).toString();
        defaultPackageName = s;
        continue; /* Loop/switch isn't completed */
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        context;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((BatterySipper)obj);
    }

    public int compareTo(BatterySipper batterysipper)
    {
        return Double.compare(batterysipper.getSortValue(), getSortValue());
    }

    public int getDrainType()
    {
        return drainType;
    }

    public Object getObjectValue(String s)
    {
        if(s.equals("name"))
            return name;
        if(s.equals("uid"))
            return Integer.valueOf(uid);
        if(s.equals("value"))
            return Double.valueOf(value);
        if(s.equals("drainType"))
            return Integer.valueOf(drainType);
        if(s.equals("usageTime"))
            return Long.valueOf(usageTime);
        if(s.equals("cpuTime"))
            return Long.valueOf(cpuTime);
        if(s.equals("gpsTime"))
            return Long.valueOf(gpsTime);
        if(s.equals("wifiRunningTime"))
            return Long.valueOf(wifiRunningTime);
        if(s.equals("cpuFgTime"))
            return Long.valueOf(cpuFgTime);
        if(s.equals("wakeLockTime"))
            return Long.valueOf(wakeLockTime);
        if(s.equals("mobileRxBytes"))
            return Long.valueOf(mobileRxBytes);
        if(s.equals("mobileTxBytes"))
            return Long.valueOf(mobileTxBytes);
        if(s.equals("noCoveragePercent"))
            return Double.valueOf(noCoveragePercent);
        if(s.equals("defaultPackageName"))
            return defaultPackageName;
        else
            return null;
    }

    public String getPackageName()
    {
        return defaultPackageName;
    }

    public double getSortValue()
    {
        return value;
    }

    public int getUid()
    {
        return uid;
    }

    public double getValue()
    {
        return value;
    }

    static final int APP = 6;
    static final int BLUETOOTH = 4;
    static final int CAMERA = 9;
    static final int CELL = 1;
    static final int FLASHLIGHT = 7;
    static final int IDLE = 0;
    static final int OTHER = 10;
    static final int PHONE = 2;
    static final int SCREEN = 5;
    static final int USER = 8;
    static final int WIFI = 3;
    long cpuFgTime;
    long cpuTime;
    String defaultPackageName;
    int drainType;
    long gpsTime;
    long mobileRxBytes;
    long mobileTxBytes;
    String name;
    double noCoveragePercent;
    int uid;
    long usageTime;
    double value;
    long wakeLockTime;
    long wifiRunningTime;
}
