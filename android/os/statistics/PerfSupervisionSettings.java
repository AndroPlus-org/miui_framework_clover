// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.text.TextUtils;
import miui.os.Build;
import miui.os.SystemProperties;

public class PerfSupervisionSettings
{

    public PerfSupervisionSettings()
    {
    }

    public static int getSupervisionLevel()
    {
        int i;
        if(sReadySupervision)
            i = sPerfSupervisionLevel;
        else
            i = 0;
        return i;
    }

    public static boolean isAboveHardThreshold(long l)
    {
        boolean flag;
        if(l >= (long)sPerfSupervisionHardThreshold)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isInHeavyMode()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(sReadySupervision)
        {
            flag1 = flag;
            if(sPerfSupervisionLevel >= 2)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isInTestMode()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(sReadySupervision)
        {
            flag1 = flag;
            if(sPerfSupervisionLevel >= 9)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isSupervisionOn()
    {
        boolean flag = true;
        if(getSupervisionLevel() < 1)
            flag = false;
        return flag;
    }

    public static void notifySupervisionReady()
    {
        sReadySupervision = true;
    }

    static int parseIntWithDefault(String s, int i)
    {
        int j = Integer.parseInt(s);
        i = j;
_L2:
        return i;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final int DEFAULT_DIVISION_RATIO = 2;
    private static final int DEFAULT_HARD_THRESHOLD_MS = 1000;
    private static final int DEFAULT_LEVEL;
    private static final int DEFAULT_SOFT_THRESHOLD_MS;
    private static String LOW_STRESS_MONITORING_DEVICES;
    private static final int MIN_DIVISION_RATIO = 1;
    private static final int MIN_HARD_THRESHOLD_MS = 200;
    static final int MIN_SOFT_THRESHOLD_MS;
    static final int PERF_SUPERVISION_OFF = 0;
    static final int PERF_SUPERVISION_ON_HEAVY = 2;
    static final int PERF_SUPERVISION_ON_NORMAL = 1;
    static final int PERF_SUPERVISION_ON_TEST = 9;
    static final int sPerfSupervisionDivisionRatio;
    static final int sPerfSupervisionHardThreshold;
    private static final int sPerfSupervisionLevel;
    static final int sPerfSupervisionSoftThreshold;
    private static boolean sReadySupervision = false;

    static 
    {
        int i;
        String s;
        if(!Build.IS_STABLE_VERSION)
            i = 4;
        else
            i = 16;
        MIN_SOFT_THRESHOLD_MS = i;
        if(Build.IS_ALPHA_BUILD && Build.IS_INTERNATIONAL_BUILD ^ true)
            i = 1;
        else
            i = 0;
        DEFAULT_LEVEL = i;
        LOW_STRESS_MONITORING_DEVICES = "gemini";
        if(LOW_STRESS_MONITORING_DEVICES.contains(Build.DEVICE))
            DEFAULT_SOFT_THRESHOLD_MS = 200;
        else
            DEFAULT_SOFT_THRESHOLD_MS = 100;
        s = SystemProperties.get("persist.sys.perf_mistats_opt", "");
        if(TextUtils.isEmpty(s))
        {
            sPerfSupervisionLevel = DEFAULT_LEVEL;
            sPerfSupervisionSoftThreshold = DEFAULT_SOFT_THRESHOLD_MS;
            sPerfSupervisionHardThreshold = 1000;
            sPerfSupervisionDivisionRatio = 2;
        } else
        {
            String as[] = s.replace(" ", "").split(",");
            if(as.length >= 1 && TextUtils.isEmpty(as[0]) ^ true)
                sPerfSupervisionLevel = parseIntWithDefault(as[0], DEFAULT_LEVEL);
            else
                sPerfSupervisionLevel = DEFAULT_LEVEL;
            if(as.length >= 2 && TextUtils.isEmpty(as[1]) ^ true)
                sPerfSupervisionSoftThreshold = Math.max(parseIntWithDefault(as[1], DEFAULT_SOFT_THRESHOLD_MS), MIN_SOFT_THRESHOLD_MS);
            else
                sPerfSupervisionSoftThreshold = DEFAULT_SOFT_THRESHOLD_MS;
            if(as.length >= 3 && TextUtils.isEmpty(as[2]) ^ true)
                sPerfSupervisionHardThreshold = Math.max(parseIntWithDefault(as[2], 1000), 200);
            else
                sPerfSupervisionHardThreshold = 1000;
            if(as.length >= 4 && TextUtils.isEmpty(as[3]) ^ true)
                sPerfSupervisionDivisionRatio = Math.min(Math.max(parseIntWithDefault(as[3], 2), 1), sPerfSupervisionSoftThreshold / MIN_SOFT_THRESHOLD_MS);
            else
                sPerfSupervisionDivisionRatio = 2;
        }
    }
}
