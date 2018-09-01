// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.content.Intent;
import android.text.TextUtils;
import com.android.internal.util.ArrayUtils;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package miui.log:
//            LogSwitchesConfigMonitor, LogSwitchesConfigWriter, AppLogSwitches, LogSwitch

public final class LogSwitchesConfigManager
{

    public LogSwitchesConfigManager(String s, String s1)
    {
        logSwitchesFolder = s;
        logSwitchesFileName = s1;
        logSwitchesFilePath = (new StringBuilder()).append(s).append("/").append(s1).toString();
        logSwitchesMonitor = new LogSwitchesConfigMonitor(s, s1);
        logSwitchesWriter = new LogSwitchesConfigWriter(s, s1);
    }

    private HashMap buildAppLogSwitches(boolean flag, String as[], String as1[], String as2[], boolean flag1)
    {
        HashMap hashmap = new HashMap();
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            Object obj = as[j];
            String s;
            if(flag)
                s = ((String) (obj));
            else
                s = "";
            if(flag)
                obj = "";
            obj = new AppLogSwitches(false, s, ((String) (obj)), buildLogSwitches(as1, as2, flag1));
            hashmap.put(((AppLogSwitches) (obj)).uniqueName, obj);
            j++;
        }
        return hashmap;
    }

    private HashMap buildLogSwitches(String as[], String as1[], boolean flag)
    {
        boolean flag1 = false;
        HashMap hashmap = new HashMap();
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            LogSwitch logswitch = new LogSwitch(as[j], "", flag);
            hashmap.put(logswitch.uniqueName, logswitch);
        }

        i = as1.length;
        for(int k = ((flag1) ? 1 : 0); k < i; k++)
        {
            as = new LogSwitch("", as1[k], flag);
            hashmap.put(((LogSwitch) (as)).uniqueName, as);
        }

        return hashmap;
    }

    private HashMap merge(HashMap hashmap, HashMap hashmap1)
    {
        HashMap hashmap2 = new HashMap();
        Iterator iterator = hashmap.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            AppLogSwitches applogswitches1 = (AppLogSwitches)iterator.next();
            AppLogSwitches applogswitches2 = (AppLogSwitches)hashmap1.get(applogswitches1.uniqueName);
            applogswitches1 = (AppLogSwitches)applogswitches1.clone();
            if(applogswitches2 == null)
            {
                hashmap2.put(applogswitches1.uniqueName, applogswitches1);
            } else
            {
                update(applogswitches1, applogswitches2);
                if(applogswitches1.logSwitches.size() > 0)
                    hashmap2.put(applogswitches1.uniqueName, applogswitches1);
            }
        } while(true);
        hashmap1 = hashmap1.values().iterator();
        do
        {
            if(!hashmap1.hasNext())
                break;
            AppLogSwitches applogswitches = (AppLogSwitches)hashmap1.next();
            if(!hashmap.containsKey(applogswitches.uniqueName))
                hashmap2.put(applogswitches.uniqueName, (AppLogSwitches)applogswitches.clone());
        } while(true);
        return hashmap2;
    }

    private HashMap merge(HashMap hashmap, AppLogSwitches applogswitches)
    {
        HashMap hashmap1 = new HashMap();
        hashmap = hashmap.values().iterator();
        do
        {
            if(!hashmap.hasNext())
                break;
            AppLogSwitches applogswitches1 = (AppLogSwitches)((AppLogSwitches)hashmap.next()).clone();
            update(applogswitches1, applogswitches);
            if(applogswitches1.logSwitches.size() > 0)
                hashmap1.put(applogswitches1.uniqueName, applogswitches1);
        } while(true);
        if(!hashmap1.containsKey(applogswitches.uniqueName))
            hashmap1.put(applogswitches.uniqueName, applogswitches);
        return hashmap1;
    }

    private void revertLogSwitches(Intent intent)
    {
        Object obj;
        boolean flag = intent.getBooleanExtra("allapps", false);
        String s = intent.getStringExtra("packages");
        obj = intent.getStringExtra("programs");
        if(s == null)
            intent = new String[0];
        else
            intent = s.split(",");
        if(obj == null)
            obj = new String[0];
        else
            obj = ((String) (obj)).split(",");
        if(!flag) goto _L2; else goto _L1
_L1:
        logSwitchesWriter.write(new HashMap());
_L4:
        return;
_L2:
        if(intent.length > 0 || obj.length > 0)
        {
            Object obj1 = logSwitchesMonitor.retrieveCurrentLogSwitches();
            HashMap hashmap = new HashMap();
            obj1 = ((HashMap) (obj1)).values().iterator();
            do
            {
                if(!((Iterator) (obj1)).hasNext())
                    break;
                AppLogSwitches applogswitches = (AppLogSwitches)((Iterator) (obj1)).next();
                if(!TextUtils.isEmpty(applogswitches.packageName))
                {
                    if(!ArrayUtils.contains(intent, applogswitches.packageName))
                        hashmap.put(applogswitches.uniqueName, (AppLogSwitches)applogswitches.clone());
                } else
                if(!TextUtils.isEmpty(applogswitches.programName) && !ArrayUtils.contains(((Object []) (obj)), applogswitches.programName))
                    hashmap.put(applogswitches.uniqueName, (AppLogSwitches)applogswitches.clone());
            } while(true);
            logSwitchesWriter.write(hashmap);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void switchStatusOfLogSwitches(Intent intent, boolean flag)
    {
        boolean flag1 = intent.getBooleanExtra("allapps", false);
        String s = intent.getStringExtra("packages");
        String as[] = intent.getStringExtra("programs");
        String as1[] = intent.getStringExtra("tags");
        String as2[] = intent.getStringExtra("groups");
        if(s == null)
            intent = new String[0];
        else
            intent = s.split(",");
        if(as == null)
            as = new String[0];
        else
            as = as.split(",");
        if(as1 == null)
            as1 = new String[0];
        else
            as1 = as1.split(",");
        if(as2 == null)
            as2 = new String[0];
        else
            as2 = as2.split(",");
        if(as1.length == 0 && as2.length == 0)
            return;
        HashMap hashmap1 = logSwitchesMonitor.retrieveCurrentLogSwitches();
        if(flag1)
        {
            intent = merge(hashmap1, new AppLogSwitches(true, "", "", buildLogSwitches(as1, as2, flag)));
        } else
        {
            HashMap hashmap = new HashMap();
            hashmap.putAll(buildAppLogSwitches(true, intent, as1, as2, flag));
            hashmap.putAll(buildAppLogSwitches(false, as, as1, as2, flag));
            intent = merge(hashmap1, hashmap);
        }
        logSwitchesWriter.write(intent);
    }

    private void update(AppLogSwitches applogswitches, AppLogSwitches applogswitches1)
    {
        applogswitches1 = applogswitches1.logSwitches.values().iterator();
        do
        {
            if(!applogswitches1.hasNext())
                break;
            LogSwitch logswitch = (LogSwitch)applogswitches1.next();
            LogSwitch logswitch1 = (LogSwitch)applogswitches.logSwitches.get(logswitch.uniqueName);
            if(logswitch1 == null)
                applogswitches.logSwitches.put(logswitch.uniqueName, logswitch);
            else
            if(logswitch.isOn != logswitch1.isOn)
                applogswitches.logSwitches.remove(logswitch.uniqueName);
        } while(true);
    }

    public void startMonitoring(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag2 = logSwitchesMonitor.isWatching();
        if(!flag2)
            break MISSING_BLOCK_LABEL_17;
        this;
        JVM INSTR monitorexit ;
        return;
        logSwitchesMonitor.startMonitoring(flag, flag1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void updateLogSwitches(Intent intent)
    {
        if(intent == null)
            return;
        if(!"miui.intent.action.REVERT_MIUILOG_SWITCHES".equals(intent.getAction())) goto _L2; else goto _L1
_L1:
        revertLogSwitches(intent);
_L4:
        return;
_L2:
        if("miui.intent.action.SWITCH_ON_MIUILOGS".equals(intent.getAction()))
            switchStatusOfLogSwitches(intent, true);
        else
        if("miui.intent.action.SWITCH_OFF_MIUILOGS".equals(intent.getAction()))
            switchStatusOfLogSwitches(intent, false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void updatePackageName(String s)
    {
        this;
        JVM INSTR monitorenter ;
        logSwitchesMonitor.updatePackageName(s);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void updateProgramName()
    {
        this;
        JVM INSTR monitorenter ;
        logSwitchesMonitor.updateProgramName();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void updateProgramName(String s)
    {
        this;
        JVM INSTR monitorenter ;
        logSwitchesMonitor.updateProgramName(s);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public static final String ACTION_REVERT_MIUILOG_SWITCHES = "miui.intent.action.REVERT_MIUILOG_SWITCHES";
    public static final String ACTION_SWITCH_OFF_MIUILOGS = "miui.intent.action.SWITCH_OFF_MIUILOGS";
    public static final String ACTION_SWITCH_ON_MIUILOGS = "miui.intent.action.SWITCH_ON_MIUILOGS";
    public static final String EXTRA_KEY_PACKAGES = "packages";
    public static final String EXTRA_KEY_PROGRAMS = "programs";
    public static final String EXTRA_KEY_TAGGROUPS = "groups";
    public static final String EXTRA_KEY_TAGS = "tags";
    public static final String EXTRA_KEY_TARGETALL = "allapps";
    public static final String TAG = "LogSwitchesConfigManager";
    private final String logSwitchesFileName;
    private final String logSwitchesFilePath;
    private final String logSwitchesFolder;
    private final LogSwitchesConfigMonitor logSwitchesMonitor;
    private final LogSwitchesConfigWriter logSwitchesWriter;
}
