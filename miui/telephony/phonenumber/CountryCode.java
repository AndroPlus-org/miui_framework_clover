// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony.phonenumber;

import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package miui.telephony.phonenumber:
//            CountryCodeConverter

public class CountryCode
{

    public CountryCode()
    {
    }

    public static String getIccCountryCode()
    {
        updateIcc();
        return ICC_COUNTRY_CODE;
    }

    public static String getIddCode()
    {
        updateNetwork();
        return (String)NETWORK_IDD_CODE.get(0);
    }

    public static List getIddCodes()
    {
        updateNetwork();
        return NETWORK_IDD_CODE;
    }

    public static String getNetworkCountryCode()
    {
        updateNetwork();
        return NETWORK_COUNTRY_CODE;
    }

    public static String getUserDefinedCountryCode()
    {
        return SystemProperties.get("persist.radio.countrycode", "");
    }

    public static boolean isChinaEnvironment()
    {
        String s = getUserDefinedCountryCode();
        String s1 = s;
        if(TextUtils.isEmpty(s))
            s1 = getIccCountryCode();
        return CountryCodeConverter.isChinaEnvironment(s1, getNetworkCountryCode());
    }

    private static void updateIcc()
    {
        String s = TelephonyManager.getDefault().getSimOperator();
        if(!TextUtils.isEmpty(s) && s.length() >= 3 && s.equals(ICC_OPERATOR) ^ true)
        {
            ICC_OPERATOR = s;
            ICC_COUNTRY_CODE = CountryCodeConverter.getCountryCode(s.substring(0, 3));
        }
    }

    private static void updateNetwork()
    {
        String s = TelephonyManager.getDefault().getNetworkOperator();
        if(!TextUtils.isEmpty(s) && s.length() >= 3 && s.equals(NETWORK_OPERATOR) ^ true)
        {
            NETWORK_OPERATOR = s;
            s = s.substring(0, 3);
            NETWORK_COUNTRY_CODE = CountryCodeConverter.getCountryCode(s);
            NETWORK_IDD_CODE = CountryCodeConverter.getIddCodes(s);
            if(NETWORK_IDD_CODE == null || NETWORK_COUNTRY_CODE.isEmpty())
                NETWORK_IDD_CODE = Arrays.asList(new String[] {
                    "00"
                });
        }
    }

    private static final String DEFAULT_IDD_CODE = "00";
    private static final String EMPTY = "";
    public static final String GSM_GENERAL_IDD_CODE = "+";
    private static String ICC_COUNTRY_CODE = "";
    private static String ICC_OPERATOR = "";
    private static String NETWORK_COUNTRY_CODE = "";
    private static List NETWORK_IDD_CODE = Arrays.asList(new String[] {
        "00"
    });
    private static String NETWORK_OPERATOR = "";

}
