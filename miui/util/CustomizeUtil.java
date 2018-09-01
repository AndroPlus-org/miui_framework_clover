// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.app.AppGlobals;
import android.content.pm.IPackageManager;
import android.graphics.Point;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.Display;
import android.view.DisplayInfo;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import miui.os.Build;
import miui.os.MiuiInit;

// Referenced classes of package miui.util:
//            Log

public class CustomizeUtil
{

    public CustomizeUtil()
    {
    }

    public static DisplayInfo adjustDisplay(DisplayInfo displayinfo, int i, String s)
    {
        DisplayInfo displayinfo1;
        Object obj;
        displayinfo1 = displayinfo;
        obj = displayinfo1;
        if(i == 1000) goto _L2; else goto _L1
_L1:
        obj = displayinfo1;
        if(i == 0) goto _L2; else goto _L3
_L3:
        obj = displayinfo1;
        if(!MiuiInit.isRestrictAspect(s)) goto _L2; else goto _L4
_L4:
        s = JVM INSTR new #175 <Class DisplayInfo>;
        s.DisplayInfo(displayinfo);
        if(((DisplayInfo) (s)).logicalWidth >= ((DisplayInfo) (s)).logicalHeight) goto _L6; else goto _L5
_L5:
        i = (int)((float)((DisplayInfo) (s)).logicalWidth * RESTRICT_ASPECT_RATIO + 0.5F);
        s.logicalHeight = Math.min(((DisplayInfo) (s)).logicalHeight, i);
        i = (int)((float)((DisplayInfo) (s)).appWidth * RESTRICT_ASPECT_RATIO + 0.5F);
        s.appHeight = Math.min(((DisplayInfo) (s)).appHeight, i);
        obj = s;
_L2:
        return ((DisplayInfo) (obj));
_L6:
        i = (int)((float)((DisplayInfo) (s)).logicalHeight * RESTRICT_ASPECT_RATIO + 0.5F);
        s.logicalWidth = Math.min(((DisplayInfo) (s)).logicalWidth, i);
        i = (int)((float)((DisplayInfo) (s)).appHeight * RESTRICT_ASPECT_RATIO + 0.5F);
        s.appWidth = Math.min(((DisplayInfo) (s)).appWidth, i);
        obj = s;
        continue; /* Loop/switch isn't completed */
        displayinfo;
        obj = displayinfo1;
_L8:
        Log.w("CustomizeUtil", "ajsustDisplay failed.", displayinfo);
        if(true) goto _L2; else goto _L7
_L7:
        displayinfo;
        obj = s;
          goto _L8
    }

    public static boolean forceLayoutHideNavigation(String s)
    {
        return sForceLayoutHideNavigationPkgs.contains(s);
    }

    private static String getCallingUidPackage(int i)
    {
        if(i <= 0)
            break MISSING_BLOCK_LABEL_38;
        String as[] = AppGlobals.getPackageManager().getPackagesForUid(i);
        if(as == null)
            break MISSING_BLOCK_LABEL_38;
        if(as.length > 0)
        {
            String s = as[0];
            return s;
        }
        break MISSING_BLOCK_LABEL_38;
        Exception exception;
        exception;
        Log.w("CustomizeUtil", "getCallingUidPackage failed.", exception);
        return null;
    }

    public static File getMiuiAppDir()
    {
        return MIUI_APP_DIR;
    }

    public static File getMiuiCustDir()
    {
        return MIUI_CUST_DIR;
    }

    public static File getMiuiCustVariantDir()
    {
        if(Build.IS_GLOBAL_BUILD && TextUtils.isEmpty(sCustVariant) ^ true)
            return new File(getMiuiCustDir(), sCustVariant);
        if(!CUST_VARIANT_FILE.exists()) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj7 = obj4;
        obj8 = obj1;
        Object obj9 = JVM INSTR new #250 <Class FileReader>;
        obj7 = obj4;
        obj8 = obj1;
        ((FileReader) (obj9)).FileReader(CUST_VARIANT_FILE);
        obj7 = JVM INSTR new #255 <Class BufferedReader>;
        ((BufferedReader) (obj7)).BufferedReader(((java.io.Reader) (obj9)));
        obj8 = ((BufferedReader) (obj7)).readLine();
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_194;
        obj8 = ((String) (obj8)).trim().replace("\n", "");
        obj8 = new File(getMiuiCustDir(), ((String) (obj8)));
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_147;
        ((FileReader) (obj9)).close();
        if(obj7 != null)
            try
            {
                ((BufferedReader) (obj7)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj9)
            {
                Log.e("CustomizeUtil", (new StringBuilder()).append("getMiuiCustVariantDir finally Exception e:").append(((Exception) (obj9)).getMessage()).toString());
            }
        return ((File) (obj8));
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_204;
        ((FileReader) (obj9)).close();
        if(obj7 != null)
            try
            {
                ((BufferedReader) (obj7)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj9)
            {
                Log.e("CustomizeUtil", (new StringBuilder()).append("getMiuiCustVariantDir finally Exception e:").append(((Exception) (obj9)).getMessage()).toString());
            }
        return null;
        obj5;
        obj9 = obj2;
_L7:
        obj7 = obj6;
        obj8 = obj9;
        ((IOException) (obj5)).printStackTrace();
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_278;
        ((FileReader) (obj9)).close();
        if(obj6 != null)
            try
            {
                ((BufferedReader) (obj6)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj9)
            {
                Log.e("CustomizeUtil", (new StringBuilder()).append("getMiuiCustVariantDir finally Exception e:").append(((Exception) (obj9)).getMessage()).toString());
            }
_L2:
        return null;
        obj5;
        obj9 = obj;
        obj6 = obj3;
_L5:
        obj7 = obj6;
        obj8 = obj9;
        ((FileNotFoundException) (obj5)).printStackTrace();
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_355;
        ((FileReader) (obj9)).close();
        if(obj6 != null)
            try
            {
                ((BufferedReader) (obj6)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj9)
            {
                Log.e("CustomizeUtil", (new StringBuilder()).append("getMiuiCustVariantDir finally Exception e:").append(((Exception) (obj9)).getMessage()).toString());
            }
          goto _L2
        obj9;
_L4:
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_414;
        ((FileReader) (obj8)).close();
        if(obj7 != null)
            try
            {
                ((BufferedReader) (obj7)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj7)
            {
                Log.e("CustomizeUtil", (new StringBuilder()).append("getMiuiCustVariantDir finally Exception e:").append(((Exception) (obj7)).getMessage()).toString());
            }
        throw obj9;
        obj6;
        obj7 = obj5;
        obj8 = obj9;
        obj9 = obj6;
        continue; /* Loop/switch isn't completed */
        obj6;
        obj8 = obj9;
        obj9 = obj6;
        if(true) goto _L4; else goto _L3
_L3:
        obj5;
        obj6 = obj3;
          goto _L5
        obj5;
        obj6 = obj7;
          goto _L5
        obj5;
        continue; /* Loop/switch isn't completed */
        obj5;
        obj6 = obj7;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static File getMiuiCustVariantFile()
    {
        if(!Build.HAS_CUST_PARTITION || Build.IS_GLOBAL_BUILD)
            return new File(MIUI_CUSTOMIZED_DATA_DIR, "cust_variant");
        else
            return new File(MIUI_CUSTOMIZED_CUST_DIR, "cust_variant");
    }

    public static File getMiuiCustomizedAppDir()
    {
        return MIUI_CUSTOMIZED_APP_DIR;
    }

    public static File getMiuiCustomizedDir()
    {
        if(Build.HAS_CUST_PARTITION)
            return MIUI_CUSTOMIZED_CUST_DIR;
        else
            return MIUI_CUSTOMIZED_DATA_DIR;
    }

    public static File getMiuiNoCustomizedAppDir()
    {
        if(Build.HAS_CUST_PARTITION)
            return SYSTEM_NONCUSTOMIZED_APP_DIR;
        else
            return DATA_NONCUSTOMIZED_APP_DIR;
    }

    public static void getRealSize(Display display, Point point)
    {
        android/view/Display.getDeclaredMethod("getRealSize", new Class[] {
            android/graphics/Point, Boolean.TYPE
        }).invoke(display, new Object[] {
            point, Boolean.valueOf(true)
        });
_L1:
        return;
        Exception exception;
        exception;
        Log.w("CustomizeUtil", "no getRealSize hack method");
        display.getRealSize(point);
          goto _L1
    }

    public static boolean isRestrict(float f)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(f > 0.0F)
        {
            flag1 = flag;
            if(f < 3F)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean needChangeSize()
    {
        return false;
    }

    public static void setMiuiCustVariatDir(String s)
    {
        sCustVariant = s;
    }

    public static final String ADJUST = "adjust";
    public static final String ANDROID_MAX_ASPECT = "android.max_aspect";
    private static final String CUST_VARIANT = "cust_variant";
    private static final File CUST_VARIANT_FILE = getMiuiCustVariantFile();
    private static final File DATA_NONCUSTOMIZED_APP_DIR = new File("/data/miui/app/noncustomized");
    public static final int EXTRA_PRIVATE_FLAG_SPECIAL_MODE = 128;
    public static final boolean HAS_NOTCH = "1".equals(SystemProperties.get("ro.miui.notch", "0"));
    public static final float MAX_ASPECT_RATIO = 3F;
    private static final File MIUI_APP_DIR = new File(getMiuiCustomizedDir(), "app");
    private static final File MIUI_CUSTOMIZED_APP_DIR = new File(getMiuiAppDir(), "customized");
    private static final File MIUI_CUSTOMIZED_CUST_DIR = new File("/cust/");
    private static final File MIUI_CUSTOMIZED_DATA_DIR = new File("/data/miui/");
    private static final File MIUI_CUST_DIR = new File(getMiuiCustomizedDir(), "cust");
    public static final String NEED_ADJUST = "need_adjust";
    public static final String NOTCH_CONFIG = "notch.config";
    public static final String PACKAGE = "pkg";
    public static final float RESTRICT_ASPECT_RATIO;
    private static final File SYSTEM_NONCUSTOMIZED_APP_DIR = new File("/system/data-app/");
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_METADATA = 1;
    public static final int TYPE_OTHER = 5;
    public static final int TYPE_RESIZEABLE = 2;
    public static final int TYPE_RESTRICT = 4;
    public static final int TYPE_SUGGEST = 3;
    public static final String UPDATE_SPECIAL_MODE = "upate_specail_mode";
    private static String sCustVariant = "";
    private static ArrayList sForceLayoutHideNavigationPkgs;

    static 
    {
        float f;
        if("lithium".equals(Build.DEVICE))
            f = 1.777778F;
        else
            f = 1.833F;
        RESTRICT_ASPECT_RATIO = f;
        sForceLayoutHideNavigationPkgs = new ArrayList();
        sForceLayoutHideNavigationPkgs.add("android");
        sForceLayoutHideNavigationPkgs.add("com.android.systemui");
        sForceLayoutHideNavigationPkgs.add("com.android.keyguard");
    }
}
