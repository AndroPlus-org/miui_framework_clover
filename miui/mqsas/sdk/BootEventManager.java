// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk;

import android.os.*;
import android.util.Slog;
import miui.mqsas.sdk.event.BootEvent;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package miui.mqsas.sdk:
//            MQSEventManagerDelegate

public class BootEventManager
{

    static String _2D_get0()
    {
        return TAG;
    }

    private BootEventManager()
    {
        bootType = 1;
        phaseSystemRun = 0L;
        phaseZygotePreload = 0L;
        phasePmsScanStart = 0L;
        phasePmsScanEnd = 0L;
        phaseBootDexopt = 0L;
        phaseCoreAppDexopt = 0L;
        phaseAmsReady = 0L;
        phaseUIReady = 0L;
        phaseBootComplete = 0L;
        systemAppCount = 0;
        thirdAppCount = 0;
        prebootAppCount = 0;
        persistAppCount = 0;
        dexoptSysAppCnt = 0;
        dexoptThirdAppCnt = 0;
    }

    private static JSONObject createJsonObject(JSONObject jsonobject, String s, long l)
    {
        if(jsonobject == null)
            jsonobject = new JSONObject();
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #85  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            jsonobject.put(s, stringbuilder.append("").append(l).toString());
        }
        catch(JSONException jsonexception)
        {
            Slog.e(TAG, (new StringBuilder()).append("create jason object exception for ").append(s).append(", ").append(jsonexception).toString());
        }
        return jsonobject;
    }

    private BootEvent getBootEvent()
    {
        BootEvent bootevent = new BootEvent();
        bootevent.setType(128);
        bootevent.setTimeStamp(System.currentTimeMillis());
        bootevent.setBootType(getBootType());
        bootevent.setPeriodSystemRun(getSystemRun());
        bootevent.setPeriodPmsScan(getPmsScanEnd() - getPmsScanStart() - getCoreAppDexopt());
        bootevent.setPeriodDexopt(getBootDexopt() + getCoreAppDexopt());
        bootevent.setPeriodAmsReady(getAmsReady() - getPmsScanEnd() - getBootDexopt());
        bootevent.setPeriodUIReady(getUIReady() - getAmsReady());
        bootevent.setPeriodBootComplete(getBootComplete());
        bootevent.setDetailSystemRun(createJsonObject(null, "zygotePreload", getZygotePreload()).toString());
        bootevent.setDetailPmsScan(createJsonObject(createJsonObject(null, "sysAppCnt", getSystemAppCount()), "thirdAppCnt", getThirdAppCount()).toString());
        bootevent.setDetailDexopt(createJsonObject(createJsonObject(null, "optSysAppCnt", getDexoptSystemAppCount()), "optThirdAppCnt", getDexoptThirdAppCount()).toString());
        bootevent.setDetailAmsReady(createJsonObject(null, "prebootAppCnt", getPrebootAppCount()).toString());
        bootevent.setDetailUIReady(createJsonObject(null, "persistAppCnt", getPersistAppCount()).toString());
        return bootevent;
    }

    public static BootEventManager getInstance()
    {
        miui/mqsas/sdk/BootEventManager;
        JVM INSTR monitorenter ;
        BootEventManager booteventmanager1;
        if(sInstance == null)
        {
            BootEventManager booteventmanager = JVM INSTR new #2   <Class BootEventManager>;
            booteventmanager.BootEventManager();
            sInstance = booteventmanager;
        }
        booteventmanager1 = sInstance;
        miui/mqsas/sdk/BootEventManager;
        JVM INSTR monitorexit ;
        return booteventmanager1;
        Exception exception;
        exception;
        throw exception;
    }

    public static void reportBootEvent()
    {
        Object obj = getInstance();
        Slog.d(TAG, (new StringBuilder()).append("systemRun:").append(((BootEventManager) (obj)).getSystemRun()).append(",").append("zygotePreload:").append(((BootEventManager) (obj)).getZygotePreload()).append(",").append("pmsScan:").append(((BootEventManager) (obj)).getPmsScanEnd() - ((BootEventManager) (obj)).getPmsScanStart() - ((BootEventManager) (obj)).getCoreAppDexopt()).append(",").append("bootDexopt:").append(((BootEventManager) (obj)).getBootDexopt()).append(",").append("coreAppDexopt:").append(((BootEventManager) (obj)).getCoreAppDexopt()).append(",").append("amsReady:").append(((BootEventManager) (obj)).getAmsReady() - ((BootEventManager) (obj)).getPmsScanEnd() - ((BootEventManager) (obj)).getBootDexopt()).append(",").append("UIReady:").append(((BootEventManager) (obj)).getUIReady() - ((BootEventManager) (obj)).getAmsReady()).append(",").append("bootComplete:").append(((BootEventManager) (obj)).getBootComplete()).toString());
        obj = ((BootEventManager) (obj)).getBootEvent();
        Slog.d(TAG, ((BootEvent) (obj)).toString());
        if(SystemProperties.getLong("ro.runtime.firstboot", 0L) > 0L && ((BootEvent) (obj)).getBootType() == 1)
        {
            Slog.d(TAG, "Abnormal boot event, filter it");
            return;
        } else
        {
            (new Handler(Looper.getMainLooper())).postDelayed(new Runnable(((BootEvent) (obj))) {

                public void run()
                {
                    Slog.d(BootEventManager._2D_get0(), "Begin to report boot event");
                    MQSEventManagerDelegate.getInstance().reportBootEvent(event);
                }

                final BootEvent val$event;

            
            {
                event = bootevent;
                super();
            }
            }
, 10000L);
            return;
        }
    }

    public long getAmsReady()
    {
        return phaseAmsReady;
    }

    public long getBootComplete()
    {
        return phaseBootComplete;
    }

    public long getBootDexopt()
    {
        return phaseBootDexopt;
    }

    public int getBootType()
    {
        return bootType;
    }

    public long getCoreAppDexopt()
    {
        return phaseCoreAppDexopt;
    }

    public int getDexoptSystemAppCount()
    {
        return dexoptSysAppCnt;
    }

    public int getDexoptThirdAppCount()
    {
        return dexoptThirdAppCnt;
    }

    public int getPersistAppCount()
    {
        return persistAppCount;
    }

    public long getPmsScanEnd()
    {
        return phasePmsScanEnd;
    }

    public long getPmsScanStart()
    {
        return phasePmsScanStart;
    }

    public int getPrebootAppCount()
    {
        return prebootAppCount;
    }

    public int getSystemAppCount()
    {
        return systemAppCount;
    }

    public long getSystemRun()
    {
        return phaseSystemRun;
    }

    public int getThirdAppCount()
    {
        return thirdAppCount;
    }

    public long getUIReady()
    {
        return phaseUIReady;
    }

    public long getZygotePreload()
    {
        return phaseZygotePreload;
    }

    public void setAmsReady(long l)
    {
        phaseAmsReady = l;
    }

    public void setBootComplete(long l)
    {
        phaseBootComplete = l;
    }

    public void setBootDexopt(long l)
    {
        phaseBootDexopt = l;
    }

    public void setBootType(int i)
    {
        bootType = i;
    }

    public void setCoreAppDexopt(long l)
    {
        phaseCoreAppDexopt = l;
    }

    public void setDexoptSystemAppCount(int i)
    {
        dexoptSysAppCnt = i;
    }

    public void setDexoptThirdAppCount(int i)
    {
        dexoptThirdAppCnt = i;
    }

    public void setPersistAppCount(int i)
    {
        persistAppCount = i;
    }

    public void setPmsScanEnd(long l)
    {
        phasePmsScanEnd = l;
    }

    public void setPmsScanStart(long l)
    {
        phasePmsScanStart = l;
    }

    public void setPrebootAppCount(int i)
    {
        prebootAppCount = i;
    }

    public void setSystemAppCount(int i)
    {
        systemAppCount = i;
    }

    public void setSystemRun(long l)
    {
        phaseSystemRun = l;
    }

    public void setThirdAppCount(int i)
    {
        thirdAppCount = i;
    }

    public void setUIReady(long l)
    {
        phaseUIReady = l;
    }

    public void setZygotePreload(long l)
    {
        phaseZygotePreload = l;
    }

    private static final int DELAY_TIME = 10000;
    private static final String TAG = miui/mqsas/sdk/BootEventManager.getSimpleName();
    private static BootEventManager sInstance = null;
    private int bootType;
    private int dexoptSysAppCnt;
    private int dexoptThirdAppCnt;
    private int persistAppCount;
    private long phaseAmsReady;
    private long phaseBootComplete;
    private long phaseBootDexopt;
    private long phaseCoreAppDexopt;
    private long phasePmsScanEnd;
    private long phasePmsScanStart;
    private long phaseSystemRun;
    private long phaseUIReady;
    private long phaseZygotePreload;
    private int prebootAppCount;
    private int systemAppCount;
    private int thirdAppCount;

}
