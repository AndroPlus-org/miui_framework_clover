// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.text.TextUtils;
import android.util.Slog;
import dalvik.system.VMRuntime;
import java.util.Objects;

// Referenced classes of package android.os:
//            SystemProperties, RemoteException, ServiceManager, IDeviceIdentifiersPolicyService, 
//            VintfObject

public class Build
{
    public static class VERSION
    {

        public static final String ACTIVE_CODENAMES[];
        private static final String ALL_CODENAMES[];
        public static final String BASE_OS = SystemProperties.get("ro.build.version.base_os", "");
        public static final String CODENAME = Build._2D_wrap1("ro.build.version.codename");
        public static final String INCREMENTAL = Build._2D_wrap1("ro.build.version.incremental");
        public static final int PREVIEW_SDK_INT = SystemProperties.getInt("ro.build.version.preview_sdk", 0);
        public static final String RELEASE = Build._2D_wrap1("ro.build.version.release");
        public static final int RESOURCES_SDK_INT;
        public static final String SDK = Build._2D_wrap1("ro.build.version.sdk");
        public static final int SDK_INT;
        public static final String SECURITY_PATCH = SystemProperties.get("ro.build.version.security_patch", "");

        static 
        {
            SDK_INT = SystemProperties.getInt("ro.build.version.sdk", 0);
            ALL_CODENAMES = Build._2D_wrap0("ro.build.version.all_codenames", ",");
            String as[];
            if("REL".equals(ALL_CODENAMES[0]))
                as = new String[0];
            else
                as = ALL_CODENAMES;
            ACTIVE_CODENAMES = as;
            RESOURCES_SDK_INT = SDK_INT + ACTIVE_CODENAMES.length;
        }

        public VERSION()
        {
        }
    }

    public static class VERSION_CODES
    {

        public static final int BASE = 1;
        public static final int BASE_1_1 = 2;
        public static final int CUPCAKE = 3;
        public static final int CUR_DEVELOPMENT = 10000;
        public static final int DONUT = 4;
        public static final int ECLAIR = 5;
        public static final int ECLAIR_0_1 = 6;
        public static final int ECLAIR_MR1 = 7;
        public static final int FROYO = 8;
        public static final int GINGERBREAD = 9;
        public static final int GINGERBREAD_MR1 = 10;
        public static final int HONEYCOMB = 11;
        public static final int HONEYCOMB_MR1 = 12;
        public static final int HONEYCOMB_MR2 = 13;
        public static final int ICE_CREAM_SANDWICH = 14;
        public static final int ICE_CREAM_SANDWICH_MR1 = 15;
        public static final int JELLY_BEAN = 16;
        public static final int JELLY_BEAN_MR1 = 17;
        public static final int JELLY_BEAN_MR2 = 18;
        public static final int KITKAT = 19;
        public static final int KITKAT_WATCH = 20;
        public static final int L = 21;
        public static final int LOLLIPOP = 21;
        public static final int LOLLIPOP_MR1 = 22;
        public static final int M = 23;
        public static final int N = 24;
        public static final int N_MR1 = 25;
        public static final int O = 26;
        public static final int O_MR1 = 27;

        public VERSION_CODES()
        {
        }
    }


    static String[] _2D_wrap0(String s, String s1)
    {
        return getStringList(s, s1);
    }

    static String _2D_wrap1(String s)
    {
        return getString(s);
    }

    public Build()
    {
    }

    private static String deriveFingerprint()
    {
        String s = SystemProperties.get("ro.build.fingerprint");
        String s1 = s;
        if(TextUtils.isEmpty(s))
            s1 = (new StringBuilder()).append(getString("ro.product.brand")).append('/').append(getString("ro.product.name")).append('/').append(getString("ro.product.device")).append(':').append(getString("ro.build.version.release")).append('/').append(getString("ro.build.id")).append('/').append(getString("ro.build.version.incremental")).append(':').append(getString("ro.build.type")).append('/').append(getString("ro.build.tags")).toString();
        return s1;
    }

    public static void ensureFingerprintProperty()
    {
        if(!TextUtils.isEmpty(SystemProperties.get("ro.build.fingerprint")))
            break MISSING_BLOCK_LABEL_19;
        SystemProperties.set("ro.build.fingerprint", FINGERPRINT);
_L1:
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        Slog.e("Build", "Failed to set fingerprint property", illegalargumentexception);
          goto _L1
    }

    private static long getLong(String s)
    {
        long l;
        try
        {
            l = Long.parseLong(SystemProperties.get(s));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return -1L;
        }
        return l;
    }

    public static String getRadioVersion()
    {
        return SystemProperties.get("gsm.version.baseband", null);
    }

    public static String getSerial()
    {
        Object obj = IDeviceIdentifiersPolicyService.Stub.asInterface(ServiceManager.getService("device_identifiers"));
        try
        {
            obj = ((IDeviceIdentifiersPolicyService) (obj)).getSerial();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.rethrowFromSystemServer();
            return "unknown";
        }
        return ((String) (obj));
    }

    private static String getString(String s)
    {
        return SystemProperties.get(s, "unknown");
    }

    private static String[] getStringList(String s, String s1)
    {
        s = SystemProperties.get(s);
        if(s.isEmpty())
            return new String[0];
        else
            return s.split(s1);
    }

    public static boolean isBuildConsistent()
    {
        boolean flag = true;
        if(IS_ENG || IS_USERDEBUG)
            return true;
        if(IS_TREBLE_ENABLED)
        {
            int i = VintfObject.verify(new String[0]);
            if(i != 0)
                Slog.e("Build", (new StringBuilder()).append("Vendor interface is incompatible, error=").append(String.valueOf(i)).toString());
            if(i != 0)
                flag = false;
            return flag;
        }
        String s = SystemProperties.get("ro.build.fingerprint");
        String s1 = SystemProperties.get("ro.vendor.build.fingerprint");
        SystemProperties.get("ro.bootimage.build.fingerprint");
        SystemProperties.get("ro.build.expect.bootloader");
        SystemProperties.get("ro.bootloader");
        SystemProperties.get("ro.build.expect.baseband");
        SystemProperties.get("gsm.version.baseband");
        if(TextUtils.isEmpty(s))
        {
            Slog.e("Build", "Required ro.build.fingerprint is empty!");
            return false;
        }
        if(!TextUtils.isEmpty(s1) && !Objects.equals(s, s1))
        {
            Slog.e("Build", (new StringBuilder()).append("Mismatched fingerprints; system reported ").append(s).append(" but vendor reported ").append(s1).toString());
            return false;
        } else
        {
            return true;
        }
    }

    public static final String BOARD = getString("ro.product.board");
    public static final String BOOTLOADER = getString("ro.bootloader");
    public static final String BRAND = getString("ro.product.brand");
    public static final String CPU_ABI;
    public static final String CPU_ABI2;
    public static final String DEVICE = getString("ro.product.device");
    public static final String DISPLAY = getString("ro.build.display.id");
    public static final String FINGERPRINT = deriveFingerprint();
    public static final String HARDWARE = getString("ro.hardware");
    public static final String HOST = getString("ro.build.host");
    public static final String ID = getString("ro.build.id");
    public static final boolean IS_CONTAINER = SystemProperties.getBoolean("ro.boot.container", false);
    public static final boolean IS_DEBUGGABLE;
    public static final boolean IS_EMULATOR = getString("ro.kernel.qemu").equals("1");
    public static final boolean IS_ENG = "eng".equals(TYPE);
    public static final boolean IS_TREBLE_ENABLED = SystemProperties.getBoolean("ro.treble.enabled", false);
    public static final boolean IS_USER = "user".equals(TYPE);
    public static final boolean IS_USERDEBUG = "userdebug".equals(TYPE);
    public static final String MANUFACTURER = getString("ro.product.manufacturer");
    public static final String MODEL = getString("ro.product.model");
    public static final boolean PERMISSIONS_REVIEW_REQUIRED;
    public static final String PRODUCT = getString("ro.product.name");
    public static final String RADIO = getString("gsm.version.baseband");
    public static final String SERIAL = getString("no.such.thing");
    public static final String SUPPORTED_32_BIT_ABIS[];
    public static final String SUPPORTED_64_BIT_ABIS[];
    public static final String SUPPORTED_ABIS[] = getStringList("ro.product.cpu.abilist", ",");
    private static final String TAG = "Build";
    public static final String TAGS = getString("ro.build.tags");
    public static final long TIME = getLong("ro.build.date.utc") * 1000L;
    public static final String TYPE = getString("ro.build.type");
    public static final String UNKNOWN = "unknown";
    public static final String USER = getString("ro.build.user");

    static 
    {
        boolean flag = true;
        SUPPORTED_32_BIT_ABIS = getStringList("ro.product.cpu.abilist32", ",");
        SUPPORTED_64_BIT_ABIS = getStringList("ro.product.cpu.abilist64", ",");
        String as[];
        boolean flag1;
        if(VMRuntime.getRuntime().is64Bit())
            as = SUPPORTED_64_BIT_ABIS;
        else
            as = SUPPORTED_32_BIT_ABIS;
        CPU_ABI = as[0];
        if(as.length > 1)
            CPU_ABI2 = as[1];
        else
            CPU_ABI2 = "";
        if(SystemProperties.getInt("ro.debuggable", 0) == 1)
            flag1 = true;
        else
            flag1 = false;
        IS_DEBUGGABLE = flag1;
        if(SystemProperties.getInt("ro.permission_review_required", 0) == 1)
            flag1 = flag;
        else
            flag1 = false;
        PERMISSIONS_REVIEW_REQUIRED = flag1;
    }
}
