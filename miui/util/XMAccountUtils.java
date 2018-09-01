// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMAccountUtils
{

    public XMAccountUtils()
    {
    }

    public static boolean isNumeric(String s)
    {
        return Pattern.compile("[0-9]*").matcher(s).matches();
    }

    public static boolean isXiaomiAccount(String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        else
            return s.endsWith("@xiaomi.com");
    }

    public static boolean isXiaomiJID(String s)
    {
        if(!isXiaomiAccount(s))
            return false;
        else
            return isNumeric(trimDomainSuffix(s));
    }

    public static String trimDomainSuffix(String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        int i = s.indexOf("@");
        if(i > 0)
            return s.substring(0, i);
        else
            return s;
    }
}
