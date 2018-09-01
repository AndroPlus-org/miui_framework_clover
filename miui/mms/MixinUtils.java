// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mms;

import android.os.SystemProperties;
import java.util.*;
import miui.os.Build;
import miui.telephony.TelephonyManager;

public class MixinUtils
{

    public MixinUtils()
    {
    }

    private static boolean isInEURegion()
    {
        String s = SystemProperties.get("ro.miui.region", "unknown");
        return EU.contains(s);
    }

    public static boolean isMxSupported()
    {
        if(Build.IS_CM_CUSTOMIZATION || Build.IS_TABLET)
            return false;
        if(!Build.IS_INTERNATIONAL_BUILD)
            return true;
        if(isInEURegion())
            return false;
        int i = TelephonyManager.getDefault().getPhoneCount();
        for(int j = 0; j < i; j++)
            if(TelephonyManager.getDefault().hasIccCard(j) && matchImsi(j, "410"))
                return false;

        return true;
    }

    public static boolean isMxSupported(int i)
    {
        if(Build.IS_CM_CUSTOMIZATION || Build.IS_TABLET)
            return false;
        if(!Build.IS_INTERNATIONAL_BUILD)
            return true;
        if(isInEURegion())
            return false;
        return !matchImsi(i, "410");
    }

    private static boolean matchImsi(int i, String s)
    {
        String s1 = TelephonyManager.getDefault().getNetworkOperatorForSlot(i);
        boolean flag;
        if(s1 != null)
            flag = s1.startsWith(s);
        else
            flag = false;
        return flag;
    }

    private static final Set EU = new HashSet(Arrays.asList(new String[] {
        "AT", "BE", "BG", "CY", "CZ", "DE", "DK", "EE", "ES", "FI", 
        "FR", "GB", "GR", "HR", "HU", "IE", "IT", "LT", "LU", "LV", 
        "MT", "NL", "PL", "PT", "RO", "SE", "SI", "SK"
    }));
    private static final String PK_MCC = "410";

}
