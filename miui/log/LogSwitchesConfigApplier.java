// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.text.TextUtils;
import java.util.*;

// Referenced classes of package miui.log:
//            LogSwitch, AppLogSwitches, Tags, LogSwitchesConfigParser

public final class LogSwitchesConfigApplier
{

    public LogSwitchesConfigApplier()
    {
        packageName = "";
        programName = "";
    }

    private void applyLogSwitch(LogSwitch logswitch)
    {
        if(logswitch.isOn)
            switchOn(logswitch);
        else
            switchOff(logswitch);
    }

    private void applyLogSwitchesOfCurrentApp(HashMap hashmap)
    {
        new HashMap();
        Iterator iterator = hashmap.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            LogSwitch logswitch1 = (LogSwitch)iterator.next();
            LogSwitch logswitch2 = (LogSwitch)logSwitchesConfigOfCurrentApp.get(logswitch1.uniqueName);
            if(logswitch2 == null)
            {
                applyLogSwitch(logswitch1);
                logSwitchesConfigOfCurrentApp.put(logswitch1.uniqueName, logswitch1);
            } else
            if(logswitch2.isOn != logswitch1.isOn)
            {
                revertLogSwitch(logswitch2);
                applyLogSwitch(logswitch1);
                logSwitchesConfigOfCurrentApp.remove(logswitch1.uniqueName);
                logSwitchesConfigOfCurrentApp.put(logswitch1.uniqueName, logswitch1);
            }
        } while(true);
        Object obj = new ArrayList();
        Iterator iterator1 = logSwitchesConfigOfCurrentApp.values().iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            LogSwitch logswitch = (LogSwitch)iterator1.next();
            if(!hashmap.containsKey(logswitch.uniqueName))
                ((ArrayList) (obj)).add(logswitch);
        } while(true);
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); logSwitchesConfigOfCurrentApp.remove(((LogSwitch) (hashmap)).uniqueName))
        {
            hashmap = (LogSwitch)((Iterator) (obj)).next();
            revertLogSwitch(hashmap);
        }

    }

    private boolean checkTargetApp(AppLogSwitches applogswitches)
    {
        if(applogswitches.targetAllApps)
            return true;
        if(!TextUtils.isEmpty(applogswitches.packageName) && applogswitches.packageName.equals(packageName))
            return true;
        return !TextUtils.isEmpty(applogswitches.programName) && applogswitches.programName.equals(programName);
    }

    private void clearAllOnLogs()
    {
        this;
        JVM INSTR monitorenter ;
        for(Iterator iterator = logSwitchesConfigOfCurrentApp.values().iterator(); iterator.hasNext(); revertLogSwitch((LogSwitch)iterator.next()));
        break MISSING_BLOCK_LABEL_45;
        Exception exception;
        exception;
        throw exception;
        logSwitchesConfigOfCurrentApp.clear();
        this;
        JVM INSTR monitorexit ;
    }

    private void revertLogSwitch(LogSwitch logswitch)
    {
        if(logswitch.isOn)
            switchOff(logswitch);
        else
            switchOn(logswitch);
    }

    private void switchOff(LogSwitch logswitch)
    {
        if(TextUtils.isEmpty(logswitch.tagGroupName)) goto _L2; else goto _L1
_L1:
        if(Tags.getTagGroup(logswitch.tagGroupName) != null)
            Tags.switchOffTagGroup(logswitch.tagGroupName);
_L4:
        return;
_L2:
        if(!TextUtils.isEmpty(logswitch.tagName))
            if(Tags.getMiuiTag(logswitch.tagName) != null)
                Tags.switchOffMiuiTag(logswitch.tagName);
            else
            if(Tags.getAndroidTag(logswitch.tagName) != null)
                Tags.switchOffAndroidTag(logswitch.tagName);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void switchOn(LogSwitch logswitch)
    {
        if(TextUtils.isEmpty(logswitch.tagGroupName)) goto _L2; else goto _L1
_L1:
        if(Tags.getTagGroup(logswitch.tagGroupName) != null)
            Tags.switchOnTagGroup(logswitch.tagGroupName);
_L4:
        return;
_L2:
        if(!TextUtils.isEmpty(logswitch.tagName))
            if(Tags.getMiuiTag(logswitch.tagName) != null)
                Tags.switchOnMiuiTag(logswitch.tagName);
            else
            if(Tags.getAndroidTag(logswitch.tagName) != null)
                Tags.switchOnAndroidTag(logswitch.tagName);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public HashMap apply(String s)
    {
        this;
        JVM INSTR monitorenter ;
        s = LogSwitchesConfigParser.parseLogSwitchesConfig(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_18;
        if(s.size() != 0)
            break MISSING_BLOCK_LABEL_26;
        clearAllOnLogs();
_L1:
        this;
        JVM INSTR monitorexit ;
        return s;
        apply(((HashMap) (s)));
          goto _L1
        s;
        throw s;
    }

    public void apply(HashMap hashmap)
    {
        this;
        JVM INSTR monitorenter ;
        HashMap hashmap1;
        hashmap1 = JVM INSTR new #22  <Class HashMap>;
        hashmap1.HashMap();
        Object obj = null;
        Iterator iterator = hashmap.values().iterator();
_L2:
        AppLogSwitches applogswitches = obj;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        applogswitches = (AppLogSwitches)iterator.next();
        if(!applogswitches.targetAllApps) goto _L2; else goto _L1
_L1:
        if(applogswitches == null)
            break MISSING_BLOCK_LABEL_70;
        hashmap1.putAll(applogswitches.logSwitches);
        hashmap = hashmap.values().iterator();
        do
        {
            if(!hashmap.hasNext())
                break;
            AppLogSwitches applogswitches1 = (AppLogSwitches)hashmap.next();
            if(!applogswitches1.targetAllApps && checkTargetApp(applogswitches1))
                hashmap1.putAll(applogswitches1.logSwitches);
        } while(true);
        break MISSING_BLOCK_LABEL_134;
        hashmap;
        throw hashmap;
        if(hashmap1.size() != 0)
            break MISSING_BLOCK_LABEL_148;
        clearAllOnLogs();
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
        applyLogSwitchesOfCurrentApp(hashmap1);
          goto _L3
    }

    public void updatePackageName(String s)
    {
        packageName = s;
    }

    public void updateProgramName(String s)
    {
        programName = s;
    }

    private final HashMap logSwitchesConfigOfCurrentApp = new HashMap();
    private String packageName;
    private String programName;
}
