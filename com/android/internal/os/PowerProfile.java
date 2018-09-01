// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

public class PowerProfile
{
    public static class CpuClusterKey
    {

        static int _2D_get0(CpuClusterKey cpuclusterkey)
        {
            return cpuclusterkey.numCpus;
        }

        static String _2D_get1(CpuClusterKey cpuclusterkey)
        {
            return cpuclusterkey.powerKey;
        }

        static String _2D_get2(CpuClusterKey cpuclusterkey)
        {
            return cpuclusterkey.timeKey;
        }

        private final int numCpus;
        private final String powerKey;
        private final String timeKey;

        private CpuClusterKey(String s, String s1, int i)
        {
            timeKey = s;
            powerKey = s1;
            numCpus = i;
        }

        CpuClusterKey(String s, String s1, int i, CpuClusterKey cpuclusterkey)
        {
            this(s, s1, i);
        }
    }


    public PowerProfile(Context context)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(sPowerMap.size() == 0)
            readPowerValuesFromXml(context);
        initCpuClusters();
        obj;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
    }

    private void initCpuClusters()
    {
        Object obj = sPowerMap.get("cpu.clusters.cores");
        if(obj == null || (obj instanceof Double[]) ^ true)
        {
            mCpuClusters = new CpuClusterKey[1];
            mCpuClusters[0] = new CpuClusterKey("cpu.speeds", "cpu.active", 1, null);
        } else
        {
            Double adouble[] = (Double[])obj;
            mCpuClusters = new CpuClusterKey[adouble.length];
            int i = 0;
            while(i < adouble.length) 
            {
                int j = (int)Math.round(adouble[i].doubleValue());
                mCpuClusters[i] = new CpuClusterKey((new StringBuilder()).append("cpu.speeds.cluster").append(i).toString(), (new StringBuilder()).append("cpu.active.cluster").append(i).toString(), j, null);
                i++;
            }
        }
    }

    private void readPowerValuesFromXml(Context context)
    {
        Resources resources;
        XmlResourceParser xmlresourceparser;
        int i;
        ArrayList arraylist;
        resources = context.getResources();
        xmlresourceparser = resources.getXml(0x1170010);
        i = 0;
        arraylist = new ArrayList();
        context = null;
        XmlUtils.beginDocument(xmlresourceparser, "device");
_L2:
        String s;
        XmlUtils.nextElement(xmlresourceparser);
        s = xmlresourceparser.getName();
        if(s != null)
            break MISSING_BLOCK_LABEL_236;
        if(i == 0)
            break MISSING_BLOCK_LABEL_75;
        sPowerMap.put(context, ((Object) (arraylist.toArray(new Double[arraylist.size()]))));
        double d;
        double d1;
        xmlresourceparser.close();
        context = new int[8];
        context;
        context[0] = 0x10e0021;
        context[1] = 0x10e0025;
        context[2] = 0x10e0026;
        context[3] = 0x10e0024;
        context[4] = 0x10e00d8;
        context[5] = 0x10e00ae;
        context[6] = 0x10e00e0;
        context[7] = 0x10e00dd;
        i = 0;
        while(i < context.length) 
        {
            Object obj = (new String[] {
                "bluetooth.controller.idle", "bluetooth.controller.rx", "bluetooth.controller.tx", "bluetooth.controller.voltage", "wifi.controller.idle", "wifi.controller.rx", "wifi.controller.tx", "wifi.controller.voltage"
            })[i];
            int j;
            String s1;
            if(!sPowerMap.containsKey(obj) || ((Double)sPowerMap.get(obj)).doubleValue() <= 0.0D)
            {
                int k = resources.getInteger(context[i]);
                if(k > 0)
                    sPowerMap.put(obj, Double.valueOf(k));
            }
            i++;
        }
        break MISSING_BLOCK_LABEL_524;
        j = i;
        if(i == 0)
            break MISSING_BLOCK_LABEL_285;
        j = i;
        if(!(s.equals("value") ^ true))
            break MISSING_BLOCK_LABEL_285;
        sPowerMap.put(context, ((Object) (arraylist.toArray(new Double[arraylist.size()]))));
        j = 0;
        if(!s.equals("array"))
            break MISSING_BLOCK_LABEL_316;
        i = 1;
        arraylist.clear();
        context = xmlresourceparser.getAttributeValue(null, "name");
        continue; /* Loop/switch isn't completed */
        if(s.equals("item"))
            break MISSING_BLOCK_LABEL_340;
        i = j;
        if(!s.equals("value"))
            continue; /* Loop/switch isn't completed */
        obj = null;
        if(j != 0)
            break MISSING_BLOCK_LABEL_359;
        obj = xmlresourceparser.getAttributeValue(null, "name");
        i = j;
        if(xmlresourceparser.next() != 4)
            continue; /* Loop/switch isn't completed */
        s1 = xmlresourceparser.getText();
        d = 0.0D;
        try
        {
            d1 = Double.valueOf(s1).doubleValue();
        }
        catch(NumberFormatException numberformatexception)
        {
            d1 = d;
        }
        if(!s.equals("item"))
            break MISSING_BLOCK_LABEL_449;
        sPowerMap.put(obj, Double.valueOf(d1));
        i = j;
        continue; /* Loop/switch isn't completed */
        context;
        obj = JVM INSTR new #300 <Class RuntimeException>;
        ((RuntimeException) (obj)).RuntimeException(context);
        throw obj;
        context;
        xmlresourceparser.close();
        throw context;
        i = j;
        if(j == 0)
            continue; /* Loop/switch isn't completed */
        arraylist.add(Double.valueOf(d1));
        i = j;
        continue; /* Loop/switch isn't completed */
        context;
        obj = JVM INSTR new #300 <Class RuntimeException>;
        ((RuntimeException) (obj)).RuntimeException(context);
        throw obj;
        return;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public double getAveragePower(String s)
    {
        return getAveragePowerOrDefault(s, 0.0D);
    }

    public double getAveragePower(String s, int i)
    {
        if(sPowerMap.containsKey(s))
        {
            s = ((String) (sPowerMap.get(s)));
            if(s instanceof Double[])
            {
                s = (Double[])s;
                if(s.length > i && i >= 0)
                    return s[i].doubleValue();
                if(i < 0 || s.length == 0)
                    return 0.0D;
                else
                    return s[s.length - 1].doubleValue();
            } else
            {
                return ((Double)s).doubleValue();
            }
        } else
        {
            return 0.0D;
        }
    }

    public double getAveragePowerForCpu(int i, int j)
    {
        if(i >= 0 && i < mCpuClusters.length)
            return getAveragePower(CpuClusterKey._2D_get1(mCpuClusters[i]), j);
        else
            return 0.0D;
    }

    public double getAveragePowerOrDefault(String s, double d)
    {
        if(sPowerMap.containsKey(s))
        {
            Object obj = sPowerMap.get(s);
            if(obj instanceof Double[])
                return ((Double[])obj)[0].doubleValue();
            else
                return ((Double)sPowerMap.get(s)).doubleValue();
        } else
        {
            return d;
        }
    }

    public double getBatteryCapacity()
    {
        return getAveragePower("battery.capacity");
    }

    public int getNumCoresInCpuCluster(int i)
    {
        return CpuClusterKey._2D_get0(mCpuClusters[i]);
    }

    public int getNumCpuClusters()
    {
        return mCpuClusters.length;
    }

    public int getNumElements(String s)
    {
        if(sPowerMap.containsKey(s))
        {
            s = ((String) (sPowerMap.get(s)));
            if(s instanceof Double[])
                return ((Double[])s).length;
            else
                return 1;
        } else
        {
            return 0;
        }
    }

    public int getNumSpeedStepsInCpuCluster(int i)
    {
        Object obj = sPowerMap.get(CpuClusterKey._2D_get2(mCpuClusters[i]));
        if(obj != null && (obj instanceof Double[]))
            return ((Double[])obj).length;
        else
            return 1;
    }

    private static final String ATTR_NAME = "name";
    public static final String POWER_AUDIO = "dsp.audio";
    public static final String POWER_BATTERY_CAPACITY = "battery.capacity";
    public static final String POWER_BLUETOOTH_ACTIVE = "bluetooth.active";
    public static final String POWER_BLUETOOTH_AT_CMD = "bluetooth.at";
    public static final String POWER_BLUETOOTH_CONTROLLER_IDLE = "bluetooth.controller.idle";
    public static final String POWER_BLUETOOTH_CONTROLLER_OPERATING_VOLTAGE = "bluetooth.controller.voltage";
    public static final String POWER_BLUETOOTH_CONTROLLER_RX = "bluetooth.controller.rx";
    public static final String POWER_BLUETOOTH_CONTROLLER_TX = "bluetooth.controller.tx";
    public static final String POWER_BLUETOOTH_ON = "bluetooth.on";
    public static final String POWER_CAMERA = "camera.avg";
    public static final String POWER_CPU_ACTIVE = "cpu.active";
    public static final String POWER_CPU_AWAKE = "cpu.awake";
    private static final String POWER_CPU_CLUSTER_ACTIVE_PREFIX = "cpu.active.cluster";
    private static final String POWER_CPU_CLUSTER_CORE_COUNT = "cpu.clusters.cores";
    private static final String POWER_CPU_CLUSTER_SPEED_PREFIX = "cpu.speeds.cluster";
    public static final String POWER_CPU_IDLE = "cpu.idle";
    public static final String POWER_CPU_SPEEDS = "cpu.speeds";
    public static final String POWER_FLASHLIGHT = "camera.flashlight";
    public static final String POWER_GPS_ON = "gps.on";
    public static final String POWER_MEMORY = "memory.bandwidths";
    public static final String POWER_MODEM_CONTROLLER_IDLE = "modem.controller.idle";
    public static final String POWER_MODEM_CONTROLLER_OPERATING_VOLTAGE = "modem.controller.voltage";
    public static final String POWER_MODEM_CONTROLLER_RX = "modem.controller.rx";
    public static final String POWER_MODEM_CONTROLLER_TX = "modem.controller.tx";
    public static final String POWER_NONE = "none";
    public static final String POWER_RADIO_ACTIVE = "radio.active";
    public static final String POWER_RADIO_ON = "radio.on";
    public static final String POWER_RADIO_SCANNING = "radio.scanning";
    public static final String POWER_SCREEN_FULL = "screen.full";
    public static final String POWER_SCREEN_ON = "screen.on";
    public static final String POWER_VIDEO = "dsp.video";
    public static final String POWER_WIFI_ACTIVE = "wifi.active";
    public static final String POWER_WIFI_BATCHED_SCAN = "wifi.batchedscan";
    public static final String POWER_WIFI_CONTROLLER_IDLE = "wifi.controller.idle";
    public static final String POWER_WIFI_CONTROLLER_OPERATING_VOLTAGE = "wifi.controller.voltage";
    public static final String POWER_WIFI_CONTROLLER_RX = "wifi.controller.rx";
    public static final String POWER_WIFI_CONTROLLER_TX = "wifi.controller.tx";
    public static final String POWER_WIFI_CONTROLLER_TX_LEVELS = "wifi.controller.tx_levels";
    public static final String POWER_WIFI_ON = "wifi.on";
    public static final String POWER_WIFI_SCAN = "wifi.scan";
    private static final String TAG_ARRAY = "array";
    private static final String TAG_ARRAYITEM = "value";
    private static final String TAG_DEVICE = "device";
    private static final String TAG_ITEM = "item";
    private static final Object sLock = new Object();
    static final HashMap sPowerMap = new HashMap();
    private CpuClusterKey mCpuClusters[];

}
