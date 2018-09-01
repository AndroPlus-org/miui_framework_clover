// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import miui.os.MiuiInit;

// Referenced classes of package miui.util:
//            FeatureParser

public class PreinstallAppUtils
{

    private PreinstallAppUtils()
    {
    }

    public static boolean isPreinstalledPackage(String s)
    {
        return MiuiInit.isPreinstalledPackage(s);
    }

    public static boolean supportSignVerifyInCust()
    {
        return FeatureParser.getBoolean("support_sign_verify_in_cust", false);
    }
}
