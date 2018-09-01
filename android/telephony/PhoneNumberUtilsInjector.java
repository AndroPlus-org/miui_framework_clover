// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.SystemProperties;
import android.text.TextUtils;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.TelephonyManagerEx;

class PhoneNumberUtilsInjector
{

    PhoneNumberUtilsInjector()
    {
    }

    static void appendNonSeparator(StringBuilder stringbuilder, char c, int i)
    {
        if(i == 0 && c == '+')
            return;
        if(Character.digit(c, 10) == -1 && PhoneNumberUtils.isNonSeparator(c))
            stringbuilder.append(c);
    }

    private static boolean containsCountryCode(String s, String s1)
    {
        if(TextUtils.isEmpty(s))
            return false;
        s = s.split(",");
        int i = s.length;
        for(int j = 0; j < i; j++)
            if(TextUtils.equals(s[j], s1))
                return true;

        return false;
    }

    static String getCdmaTelephonyProperty(String s, String s1)
    {
        String s2 = SystemProperties.get(s);
        if(s2 == null || s2.length() == 0)
            return s1;
        s = s2.split(",");
        if(s.length == 1)
            return s2;
        String s3 = Integer.toString(0);
        s2 = Integer.toString(2);
        for(int i = 0; i < s.length; i++)
            if(s2.equals(TelephonyManagerEx.getDefault().getTelephonyProperty(i, "gsm.current.phone-type", s3)))
            {
                if(s[i] != null)
                    s1 = s[i];
                return s1;
            }

        return s1;
    }

    private static boolean matchBrazilCarrierCodeAndPrefix(String s, int i)
    {
        if(i == 1)
        {
            if(s.charAt(0) == '0')
                return true;
        } else
        if(i == 3 && s.charAt(0) == '0')
        {
            if(s.charAt(1) == '1' && s.charAt(2) == '2')
                return true;
            if(s.charAt(1) == '1' && s.charAt(2) == '5')
                return true;
            if(s.charAt(1) == '1' && s.charAt(2) == '7')
                return true;
            if(s.charAt(1) == '2' && s.charAt(2) == '1')
                return true;
            if(s.charAt(1) == '2' && s.charAt(2) == '3')
                return true;
            if(s.charAt(1) == '2' && s.charAt(2) == '5')
                return true;
            if(s.charAt(1) == '3' && s.charAt(2) == '1')
                return true;
            if(s.charAt(1) == '3' && s.charAt(2) == '2')
                return true;
            if(s.charAt(1) == '4' && s.charAt(2) == '1')
                return true;
            if(s.charAt(1) == '4' && s.charAt(2) == '3')
                return true;
        }
        return false;
    }

    public static boolean matchBrazilSuccess(String s, int i, String s1, int j)
    {
        return containsCountryCode(SystemProperties.get("gsm.sim.operator.iso-country"), "br") && matchBrazilCarrierCodeAndPrefix(s, i) && matchBrazilCarrierCodeAndPrefix(s1, j);
    }

    public static String removeBrazilCarrierCodeAndPrefix(String s)
    {
        if(containsCountryCode(SystemProperties.get("gsm.sim.operator.iso-country"), "br"))
        {
            if(s.length() > 3 && matchBrazilCarrierCodeAndPrefix(s, 3))
                return s.substring(3);
            if(s.length() > 1 && matchBrazilCarrierCodeAndPrefix(s, 1))
                return s.substring(1);
        }
        return s;
    }
}
