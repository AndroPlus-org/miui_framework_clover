// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package miui.log:
//            LogSwitch

public class AppLogSwitches
    implements Cloneable
{

    public AppLogSwitches(boolean flag, String s, String s1, HashMap hashmap)
    {
        targetAllApps = flag;
        packageName = s;
        programName = s1;
        if(targetAllApps)
            uniqueName = "all";
        else
        if(!TextUtils.isEmpty(packageName))
            uniqueName = (new StringBuilder()).append("package_").append(packageName).toString();
        else
            uniqueName = (new StringBuilder()).append("program_").append(programName).toString();
        logSwitches = hashmap;
    }

    public static AppLogSwitches parseAppLogSwitches(String s)
    {
        String as[] = s.trim().split("\\|");
        if(as.length != 2)
            return null;
        s = LogSwitch.parseLogSwitches(as[1].trim());
        if(s == null || s.size() == 0)
            return null;
        as = as[0].trim().split("\\s+");
        if(as.length == 1)
            if(as[0].equalsIgnoreCase("all"))
                return new AppLogSwitches(true, "", "", s);
            else
                return null;
        if(as.length != 2)
            return null;
        if(as[0].equalsIgnoreCase("package"))
            return new AppLogSwitches(false, as[1].trim(), "", s);
        if(as[0].equalsIgnoreCase("program"))
            return new AppLogSwitches(false, "", as[1].trim(), s);
        else
            return null;
    }

    public Object clone()
    {
        AppLogSwitches applogswitches = new AppLogSwitches(targetAllApps, packageName, programName, new HashMap());
        LogSwitch logswitch;
        for(Iterator iterator = logSwitches.values().iterator(); iterator.hasNext(); applogswitches.logSwitches.put(logswitch.uniqueName, logswitch))
        {
            logswitch = (LogSwitch)iterator.next();
            LogSwitch logswitch1 = (LogSwitch)logswitch.clone();
        }

        return applogswitches;
    }

    public void merge(AppLogSwitches applogswitches)
    {
        if(!applogswitches.uniqueName.equals(uniqueName))
        {
            return;
        } else
        {
            logSwitches.putAll(applogswitches.logSwitches);
            return;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag;
        if(targetAllApps)
            stringbuilder.append("all");
        else
        if(!TextUtils.isEmpty(packageName))
            stringbuilder.append("package ").append(packageName);
        else
            stringbuilder.append("program ").append(programName);
        stringbuilder.append(" | ");
        flag = true;
        for(Iterator iterator = logSwitches.values().iterator(); iterator.hasNext();)
        {
            LogSwitch logswitch = (LogSwitch)iterator.next();
            if(!flag)
                stringbuilder.append(", ");
            stringbuilder.append(logswitch.toString());
            flag = false;
        }

        return stringbuilder.toString();
    }

    public final HashMap logSwitches;
    public final String packageName;
    public final String programName;
    public final boolean targetAllApps;
    public final String uniqueName;
}
