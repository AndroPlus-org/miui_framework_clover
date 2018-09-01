// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.pm.FeatureInfo;
import android.os.*;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.*;
import java.util.*;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SystemConfig
{
    public static final class PermissionEntry
    {

        public int gids[];
        public final String name;
        public boolean perUser;

        PermissionEntry(String s, boolean flag)
        {
            name = s;
            perUser = flag;
        }
    }


    SystemConfig()
    {
        readPermissions(Environment.buildPath(Environment.getRootDirectory(), new String[] {
            "etc", "sysconfig"
        }), -1);
        readPermissions(Environment.buildPath(Environment.getRootDirectory(), new String[] {
            "etc", "permissions"
        }), -1);
        readPermissions(Environment.buildPath(Environment.getVendorDirectory(), new String[] {
            "etc", "sysconfig"
        }), 15);
        readPermissions(Environment.buildPath(Environment.getVendorDirectory(), new String[] {
            "etc", "permissions"
        }), 15);
        readPermissions(Environment.buildPath(Environment.getOdmDirectory(), new String[] {
            "etc", "sysconfig"
        }), 11);
        readPermissions(Environment.buildPath(Environment.getOdmDirectory(), new String[] {
            "etc", "permissions"
        }), 11);
        readPermissions(Environment.buildPath(Environment.getOemDirectory(), new String[] {
            "etc", "sysconfig"
        }), 1);
        readPermissions(Environment.buildPath(Environment.getOemDirectory(), new String[] {
            "etc", "permissions"
        }), 1);
        if(SystemProperties.getBoolean("persist.graphics.vulkan.disable", false))
        {
            removeFeature("android.hardware.vulkan.level");
            removeFeature("android.hardware.vulkan.version");
            removeFeature("android.hardware.vulkan.compute");
        }
        int i = SystemProperties.getInt("ro.opengles.version", 0);
        if(i > 0 && i == 0x30000 && mAvailableFeatures.remove("android.hardware.opengles.aep") != null)
            Slog.d("SystemConfig", "Removed android.hardware.opengles.aep feature for opengles 3.0");
        if(SystemProperties.getBoolean("ro.radio.noril", false))
        {
            removeFeature("android.hardware.telephony");
            removeFeature("android.hardware.telephony.cdma");
            removeFeature("android.hardware.telephony.gsm");
        }
        String s = SystemProperties.get("ro.boot.baseband", "");
        if(s.equals("sda") || s.equals("apq"))
        {
            if(mAvailableFeatures.remove("android.hardware.location.gps") != null)
                Slog.d("SystemConfig", "Removed android.hardware.location.gps feature for SDA/APQ device");
            if(mAvailableFeatures.remove("android.hardware.telephony") != null)
                Slog.d("SystemConfig", "Removed android.hardware.telephony feature for SDA/APQ device");
            if(mAvailableFeatures.remove("android.hardware.telephony.cdma") != null)
                Slog.d("SystemConfig", "Removed android.hardware.telephony.cdma feature for SDA/APQ device");
            if(mAvailableFeatures.remove("android.hardware.telephony.gsm") != null)
                Slog.d("SystemConfig", "Removed android.hardware.telephony.gsm feature for SDA/APQ device");
        }
    }

    private void addFeature(String s, int i)
    {
        FeatureInfo featureinfo = (FeatureInfo)mAvailableFeatures.get(s);
        if(featureinfo == null)
        {
            featureinfo = new FeatureInfo();
            featureinfo.name = s;
            featureinfo.version = i;
            mAvailableFeatures.put(s, featureinfo);
        } else
        {
            featureinfo.version = Math.max(featureinfo.version, i);
        }
    }

    public static SystemConfig getInstance()
    {
        com/android/server/SystemConfig;
        JVM INSTR monitorenter ;
        SystemConfig systemconfig1;
        if(sInstance == null)
        {
            SystemConfig systemconfig = JVM INSTR new #2   <Class SystemConfig>;
            systemconfig.SystemConfig();
            sInstance = systemconfig;
        }
        systemconfig1 = sInstance;
        com/android/server/SystemConfig;
        JVM INSTR monitorexit ;
        return systemconfig1;
        Exception exception;
        exception;
        throw exception;
    }

    private void readPermissionsFromXml(File file, int i)
    {
        FileReader filereader;
        boolean flag;
        XmlPullParser xmlpullparser;
        int j;
        try
        {
            filereader = new FileReader(file);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Slog.w("SystemConfig", (new StringBuilder()).append("Couldn't find or open permissions file ").append(file).toString());
            return;
        }
        flag = ActivityManager.isLowRamDeviceStatic();
        xmlpullparser = Xml.newPullParser();
        xmlpullparser.setInput(filereader);
        do
            j = xmlpullparser.next();
        while(j != 2 && j != 1);
        if(j == 2) goto _L2; else goto _L1
_L1:
        file = JVM INSTR new #244 <Class XmlPullParserException>;
        file.XmlPullParserException("No start tag found");
        throw file;
        file;
        Slog.w("SystemConfig", "Got exception parsing permissions.", file);
        IoUtils.closeQuietly(filereader);
_L3:
        if(StorageManager.isFileEncryptedNativeOnly())
        {
            addFeature("android.software.file_based_encryption", 0);
            addFeature("android.software.securely_removes_users", 0);
        }
        boolean flag1;
        Object obj;
        Object obj1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        int k;
        String s;
        int l;
        Object obj2;
        if(ActivityManager.isLowRamDeviceStatic())
            addFeature("android.hardware.ram.low", 0);
        else
            addFeature("android.hardware.ram.normal", 0);
        for(file = mUnavailableFeatures.iterator(); file.hasNext(); removeFeature((String)file.next()));
        break MISSING_BLOCK_LABEL_2756;
_L2:
        if(!xmlpullparser.getName().equals("permissions") && xmlpullparser.getName().equals("config") ^ true)
        {
            obj = JVM INSTR new #244 <Class XmlPullParserException>;
            obj1 = JVM INSTR new #318 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            ((XmlPullParserException) (obj)).XmlPullParserException(((StringBuilder) (obj1)).append("Unexpected start tag in ").append(file).append(": found ").append(xmlpullparser.getName()).append(", expected 'permissions' or 'config'").toString());
            throw obj;
        }
        break MISSING_BLOCK_LABEL_293;
        file;
        Slog.w("SystemConfig", "Got exception parsing permissions.", file);
        IoUtils.closeQuietly(filereader);
          goto _L3
        if(i == -1)
            flag1 = true;
        else
            flag1 = false;
        if((i & 2) != 0)
            flag2 = true;
        else
            flag2 = false;
        if((i & 1) != 0)
            flag3 = true;
        else
            flag3 = false;
        if((i & 4) != 0)
            flag4 = true;
        else
            flag4 = false;
        if((i & 8) != 0)
            flag5 = true;
        else
            flag5 = false;
        if((i & 0x10) != 0)
            i = 1;
        else
            i = 0;
_L4:
        XmlUtils.nextElement(xmlpullparser);
        k = xmlpullparser.getEventType();
label0:
        {
            if(k != 1)
                break label0;
            IoUtils.closeQuietly(filereader);
        }
          goto _L3
        obj = xmlpullparser.getName();
        if(!"group".equals(obj) || !flag1)
            break MISSING_BLOCK_LABEL_537;
        obj = xmlpullparser.getAttributeValue(null, "gid");
        if(obj == null)
            break MISSING_BLOCK_LABEL_487;
        k = Process.getGidForName(((String) (obj)));
        mGlobalGids = ArrayUtils.appendInt(mGlobalGids, k);
_L5:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        file;
        IoUtils.closeQuietly(filereader);
        throw file;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<group> without gid in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
          goto _L5
        if(!"permission".equals(obj) || !flag4)
            break MISSING_BLOCK_LABEL_640;
        obj = xmlpullparser.getAttributeValue(null, "name");
        if(obj != null)
            break MISSING_BLOCK_LABEL_626;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<permission> without name in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        readPermission(xmlpullparser, ((String) (obj)).intern());
          goto _L4
        if(!"assign-permission".equals(obj) || !flag4)
            break MISSING_BLOCK_LABEL_947;
        obj1 = xmlpullparser.getAttributeValue(null, "name");
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_729;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<assign-permission> without name in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        obj = xmlpullparser.getAttributeValue(null, "uid");
        if(obj != null)
            break MISSING_BLOCK_LABEL_802;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<assign-permission> without uid in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        k = Process.getUidForName(((String) (obj)));
        if(k >= 0)
            break MISSING_BLOCK_LABEL_880;
        obj1 = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj1)).append("<assign-permission> with unknown uid \"").append(((String) (obj))).append("  in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        s = ((String) (obj1)).intern();
        obj1 = (ArraySet)mSystemPermissions.get(k);
        obj = obj1;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_931;
        obj = JVM INSTR new #75  <Class ArraySet>;
        ((ArraySet) (obj)).ArraySet();
        mSystemPermissions.put(k, obj);
        ((ArraySet) (obj)).add(s);
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        if(!"library".equals(obj) || !flag2)
            break MISSING_BLOCK_LABEL_1119;
        obj = xmlpullparser.getAttributeValue(null, "name");
        obj1 = xmlpullparser.getAttributeValue(null, "file");
        if(obj != null) goto _L7; else goto _L6
_L6:
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<library> without name in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L8:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
_L7:
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_1104;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<library> without file in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
          goto _L8
        mSharedLibraries.put(obj, obj1);
          goto _L8
        if(!"feature".equals(obj) || !flag3)
            break MISSING_BLOCK_LABEL_1267;
        obj = xmlpullparser.getAttributeValue(null, "name");
        l = XmlUtils.readIntAttribute(xmlpullparser, "version", 0);
        if(flag) goto _L10; else goto _L9
_L9:
        k = 1;
_L13:
        if(obj != null) goto _L12; else goto _L11
_L11:
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<feature> without name in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L15:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
_L10:
        k = "true".equals(xmlpullparser.getAttributeValue(null, "notLowRam")) ^ true;
          goto _L13
_L12:
        if(!k) goto _L15; else goto _L14
_L14:
        addFeature(((String) (obj)), l);
          goto _L15
        if(!"unavailable-feature".equals(obj) || !flag3)
            break MISSING_BLOCK_LABEL_1369;
        obj = xmlpullparser.getAttributeValue(null, "name");
        if(obj != null)
            break MISSING_BLOCK_LABEL_1356;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<unavailable-feature> without name in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L16:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mUnavailableFeatures.add(obj);
          goto _L16
        if(!"allow-in-power-save-except-idle".equals(obj) || !flag1)
            break MISSING_BLOCK_LABEL_1471;
        obj = xmlpullparser.getAttributeValue(null, "package");
        if(obj != null)
            break MISSING_BLOCK_LABEL_1458;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<allow-in-power-save-except-idle> without package in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L17:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mAllowInPowerSaveExceptIdle.add(obj);
          goto _L17
        if(!"allow-in-power-save".equals(obj) || !flag1)
            break MISSING_BLOCK_LABEL_1573;
        obj = xmlpullparser.getAttributeValue(null, "package");
        if(obj != null)
            break MISSING_BLOCK_LABEL_1560;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<allow-in-power-save> without package in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L18:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mAllowInPowerSave.add(obj);
          goto _L18
        if(!"allow-in-data-usage-save".equals(obj) || !flag1)
            break MISSING_BLOCK_LABEL_1675;
        obj = xmlpullparser.getAttributeValue(null, "package");
        if(obj != null)
            break MISSING_BLOCK_LABEL_1662;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<allow-in-data-usage-save> without package in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L19:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mAllowInDataUsageSave.add(obj);
          goto _L19
        if(!"allow-unthrottled-location".equals(obj) || !flag1)
            break MISSING_BLOCK_LABEL_1777;
        obj = xmlpullparser.getAttributeValue(null, "package");
        if(obj != null)
            break MISSING_BLOCK_LABEL_1764;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<allow-unthrottled-location> without package in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L20:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mAllowUnthrottledLocation.add(obj);
          goto _L20
        if(!"allow-implicit-broadcast".equals(obj) || !flag1)
            break MISSING_BLOCK_LABEL_1879;
        obj = xmlpullparser.getAttributeValue(null, "action");
        if(obj != null)
            break MISSING_BLOCK_LABEL_1866;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<allow-implicit-broadcast> without action in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L21:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mAllowImplicitBroadcasts.add(obj);
          goto _L21
        if(!"app-link".equals(obj) || !flag5)
            break MISSING_BLOCK_LABEL_1981;
        obj = xmlpullparser.getAttributeValue(null, "package");
        if(obj != null)
            break MISSING_BLOCK_LABEL_1968;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<app-link> without package in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L22:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mLinkedApps.add(obj);
          goto _L22
        if(!"system-user-whitelisted-app".equals(obj) || !flag5)
            break MISSING_BLOCK_LABEL_2083;
        obj = xmlpullparser.getAttributeValue(null, "package");
        if(obj != null)
            break MISSING_BLOCK_LABEL_2070;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<system-user-whitelisted-app> without package in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L23:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mSystemUserWhitelistedApps.add(obj);
          goto _L23
        if(!"system-user-blacklisted-app".equals(obj) || !flag5)
            break MISSING_BLOCK_LABEL_2185;
        obj = xmlpullparser.getAttributeValue(null, "package");
        if(obj != null)
            break MISSING_BLOCK_LABEL_2172;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<system-user-blacklisted-app without package in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L24:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
        mSystemUserBlacklistedApps.add(obj);
          goto _L24
        if(!"default-enabled-vr-app".equals(obj) || !flag5)
            break MISSING_BLOCK_LABEL_2373;
        obj = xmlpullparser.getAttributeValue(null, "package");
        s = xmlpullparser.getAttributeValue(null, "class");
        if(obj != null) goto _L26; else goto _L25
_L25:
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<default-enabled-vr-app without package in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L27:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
_L26:
        if(s != null)
            break MISSING_BLOCK_LABEL_2342;
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<default-enabled-vr-app without class in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
          goto _L27
        obj2 = mDefaultVrComponents;
        obj1 = JVM INSTR new #492 <Class ComponentName>;
        ((ComponentName) (obj1)).ComponentName(((String) (obj)), s);
        ((ArraySet) (obj2)).add(obj1);
          goto _L27
        if(!"backup-transport-whitelisted-service".equals(obj) || !flag3)
            break MISSING_BLOCK_LABEL_2548;
        obj = xmlpullparser.getAttributeValue(null, "service");
        if(obj != null) goto _L29; else goto _L28
_L28:
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<backup-transport-whitelisted-service> without service in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L30:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
_L29:
        obj1 = ComponentName.unflattenFromString(((String) (obj)));
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_2535;
        obj1 = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj1)).append("<backup-transport-whitelisted-service> with invalid service name ").append(((String) (obj))).append(" in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
          goto _L30
        mBackupTransportWhitelist.add(obj1);
          goto _L30
        if(!"disabled-until-used-preinstalled-carrier-associated-app".equals(obj) || !flag5)
            break MISSING_BLOCK_LABEL_2713;
        obj2 = xmlpullparser.getAttributeValue(null, "package");
        s = xmlpullparser.getAttributeValue(null, "carrierAppPackage");
        if(obj2 != null && s != null) goto _L32; else goto _L31
_L31:
        obj = JVM INSTR new #318 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.w("SystemConfig", ((StringBuilder) (obj)).append("<disabled-until-used-preinstalled-carrier-associated-app without package or carrierAppPackage in ").append(file).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
_L33:
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
_L32:
        obj1 = (List)mDisabledUntilUsedPreinstalledCarrierAssociatedApps.get(s);
        obj = obj1;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_2700;
        obj = JVM INSTR new #519 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        mDisabledUntilUsedPreinstalledCarrierAssociatedApps.put(s, obj);
        ((List) (obj)).add(obj2);
          goto _L33
        if(!"privapp-permissions".equals(obj) || i == 0)
            break MISSING_BLOCK_LABEL_2737;
        readPrivAppPermissions(xmlpullparser);
          goto _L4
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
    }

    private void removeFeature(String s)
    {
        if(mAvailableFeatures.remove(s) != null)
            Slog.d("SystemConfig", (new StringBuilder()).append("Removed unavailable feature ").append(s).toString());
    }

    public ArraySet getAllowImplicitBroadcasts()
    {
        return mAllowImplicitBroadcasts;
    }

    public ArraySet getAllowInDataUsageSave()
    {
        return mAllowInDataUsageSave;
    }

    public ArraySet getAllowInPowerSave()
    {
        return mAllowInPowerSave;
    }

    public ArraySet getAllowInPowerSaveExceptIdle()
    {
        return mAllowInPowerSaveExceptIdle;
    }

    public ArraySet getAllowUnthrottledLocation()
    {
        return mAllowUnthrottledLocation;
    }

    public ArrayMap getAvailableFeatures()
    {
        return mAvailableFeatures;
    }

    public ArraySet getBackupTransportWhitelist()
    {
        return mBackupTransportWhitelist;
    }

    public ArraySet getDefaultVrComponents()
    {
        return mDefaultVrComponents;
    }

    public ArrayMap getDisabledUntilUsedPreinstalledCarrierAssociatedApps()
    {
        return mDisabledUntilUsedPreinstalledCarrierAssociatedApps;
    }

    public int[] getGlobalGids()
    {
        return mGlobalGids;
    }

    public ArraySet getLinkedApps()
    {
        return mLinkedApps;
    }

    public ArrayMap getPermissions()
    {
        return mPermissions;
    }

    public ArraySet getPrivAppDenyPermissions(String s)
    {
        return (ArraySet)mPrivAppDenyPermissions.get(s);
    }

    public ArraySet getPrivAppPermissions(String s)
    {
        return (ArraySet)mPrivAppPermissions.get(s);
    }

    public ArrayMap getSharedLibraries()
    {
        return mSharedLibraries;
    }

    public SparseArray getSystemPermissions()
    {
        return mSystemPermissions;
    }

    public ArraySet getSystemUserBlacklistedApps()
    {
        return mSystemUserBlacklistedApps;
    }

    public ArraySet getSystemUserWhitelistedApps()
    {
        return mSystemUserWhitelistedApps;
    }

    void readPermission(XmlPullParser xmlpullparser, String s)
        throws IOException, XmlPullParserException
    {
        if(mPermissions.containsKey(s))
            throw new IllegalStateException((new StringBuilder()).append("Duplicate permission definition for ").append(s).toString());
        PermissionEntry permissionentry = new PermissionEntry(s, XmlUtils.readBooleanAttribute(xmlpullparser, "perUser", false));
        mPermissions.put(s, permissionentry);
        int i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 1 || j == 3 && xmlpullparser.getDepth() <= i)
                break;
            if(j != 3 && j != 4)
            {
                if("group".equals(xmlpullparser.getName()))
                {
                    s = xmlpullparser.getAttributeValue(null, "gid");
                    if(s != null)
                    {
                        int k = Process.getGidForName(s);
                        permissionentry.gids = ArrayUtils.appendInt(permissionentry.gids, k);
                    } else
                    {
                        Slog.w("SystemConfig", (new StringBuilder()).append("<group> without gid at ").append(xmlpullparser.getPositionDescription()).toString());
                    }
                }
                XmlUtils.skipCurrentTag(xmlpullparser);
            }
        } while(true);
    }

    void readPermissions(File file, int i)
    {
        if(!file.exists() || file.isDirectory() ^ true)
        {
            if(i == -1)
                Slog.w("SystemConfig", (new StringBuilder()).append("No directory ").append(file).append(", skipping").toString());
            return;
        }
        if(!file.canRead())
        {
            Slog.w("SystemConfig", (new StringBuilder()).append("Directory ").append(file).append(" cannot be read").toString());
            return;
        }
        File file1 = null;
        File afile[] = file.listFiles();
        int j = 0;
        int k = afile.length;
        while(j < k) 
        {
            File file2 = afile[j];
            if(file2.getPath().endsWith("etc/permissions/platform.xml"))
                file1 = file2;
            else
            if(!file2.getPath().endsWith(".xml"))
                Slog.i("SystemConfig", (new StringBuilder()).append("Non-xml file ").append(file2).append(" in ").append(file).append(" directory, ignoring").toString());
            else
            if(!file2.canRead())
                Slog.w("SystemConfig", (new StringBuilder()).append("Permissions library file ").append(file2).append(" cannot be read").toString());
            else
                readPermissionsFromXml(file2, i);
            j++;
        }
        if(file1 != null)
            readPermissionsFromXml(file1, i);
    }

    void readPrivAppPermissions(XmlPullParser xmlpullparser)
        throws IOException, XmlPullParserException
    {
        String s = xmlpullparser.getAttributeValue(null, "package");
        if(TextUtils.isEmpty(s))
        {
            Slog.w("SystemConfig", (new StringBuilder()).append("package is required for <privapp-permissions> in ").append(xmlpullparser.getPositionDescription()).toString());
            return;
        }
        ArraySet arrayset = (ArraySet)mPrivAppPermissions.get(s);
        ArraySet arrayset1 = arrayset;
        if(arrayset == null)
            arrayset1 = new ArraySet();
        arrayset = (ArraySet)mPrivAppDenyPermissions.get(s);
        int i = xmlpullparser.getDepth();
        do
        {
            if(!XmlUtils.nextElementWithin(xmlpullparser, i))
                break;
            String s1 = xmlpullparser.getName();
            if("permission".equals(s1))
            {
                s1 = xmlpullparser.getAttributeValue(null, "name");
                if(TextUtils.isEmpty(s1))
                    Slog.w("SystemConfig", (new StringBuilder()).append("name is required for <permission> in ").append(xmlpullparser.getPositionDescription()).toString());
                else
                    arrayset1.add(s1);
            } else
            if("deny-permission".equals(s1))
            {
                String s2 = xmlpullparser.getAttributeValue(null, "name");
                if(TextUtils.isEmpty(s2))
                {
                    Slog.w("SystemConfig", (new StringBuilder()).append("name is required for <deny-permission> in ").append(xmlpullparser.getPositionDescription()).toString());
                } else
                {
                    ArraySet arrayset2 = arrayset;
                    if(arrayset == null)
                        arrayset2 = new ArraySet();
                    arrayset2.add(s2);
                    arrayset = arrayset2;
                }
            }
        } while(true);
        mPrivAppPermissions.put(s, arrayset1);
        if(arrayset != null)
            mPrivAppDenyPermissions.put(s, arrayset);
    }

    private static final int ALLOW_ALL = -1;
    private static final int ALLOW_APP_CONFIGS = 8;
    private static final int ALLOW_FEATURES = 1;
    private static final int ALLOW_LIBS = 2;
    private static final int ALLOW_PERMISSIONS = 4;
    private static final int ALLOW_PRIVAPP_PERMISSIONS = 16;
    static final String TAG = "SystemConfig";
    static SystemConfig sInstance;
    final ArraySet mAllowImplicitBroadcasts = new ArraySet();
    final ArraySet mAllowInDataUsageSave = new ArraySet();
    final ArraySet mAllowInPowerSave = new ArraySet();
    final ArraySet mAllowInPowerSaveExceptIdle = new ArraySet();
    final ArraySet mAllowUnthrottledLocation = new ArraySet();
    final ArrayMap mAvailableFeatures = new ArrayMap();
    final ArraySet mBackupTransportWhitelist = new ArraySet();
    final ArraySet mDefaultVrComponents = new ArraySet();
    final ArrayMap mDisabledUntilUsedPreinstalledCarrierAssociatedApps = new ArrayMap();
    int mGlobalGids[];
    final ArraySet mLinkedApps = new ArraySet();
    final ArrayMap mPermissions = new ArrayMap();
    final ArrayMap mPrivAppDenyPermissions = new ArrayMap();
    final ArrayMap mPrivAppPermissions = new ArrayMap();
    final ArrayMap mSharedLibraries = new ArrayMap();
    final SparseArray mSystemPermissions = new SparseArray();
    final ArraySet mSystemUserBlacklistedApps = new ArraySet();
    final ArraySet mSystemUserWhitelistedApps = new ArraySet();
    final ArraySet mUnavailableFeatures = new ArraySet();
}
