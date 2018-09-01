// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony.phonenumber;

import android.text.TextUtils;

public class Prefix
{

    public Prefix()
    {
    }

    public static boolean isSmsPrefix(String s)
    {
        if(!TextUtils.isEmpty(s))
        {
            String as[] = SMS_PREFIXES;
            int i = as.length;
            for(int j = 0; j < i; j++)
                if(as[j].equals(s))
                    return true;

        }
        return false;
    }

    public static String parse(StringBuffer stringbuffer, int i, int j)
    {
        if(j <= 0)
            return "";
        switch(stringbuffer.charAt(i))
        {
        case 49: // '1'
            if(j > 4)
                switch(stringbuffer.charAt(i + 1))
                {
                case 48: // '0'
                    if(stringbuffer.charAt(i + 2) == '1' && stringbuffer.charAt(i + 3) == '9' && stringbuffer.charAt(i + 4) == '3')
                        return "10193";
                    break;

                case 49: // '1'
                    if(stringbuffer.charAt(i + 2) == '8' && stringbuffer.charAt(i + 3) == '0' && stringbuffer.charAt(i + 4) == '8')
                        return "11808";
                    break;

                case 50: // '2'
                    if(stringbuffer.charAt(i + 2) == '5')
                    {
                        if(stringbuffer.charAt(i + 3) == '8' && stringbuffer.charAt(i + 4) == '3')
                            return "12583";
                        if(stringbuffer.charAt(i + 3) == '9' && stringbuffer.charAt(i + 4) == '3')
                            return "12593";
                        if(stringbuffer.charAt(i + 3) == '2' && stringbuffer.charAt(i + 4) == '0')
                            return "12520";
                    }
                    break;

                case 55: // '7'
                    if(stringbuffer.charAt(i + 2) == '9')
                    {
                        if(stringbuffer.charAt(i + 3) == '0' && stringbuffer.charAt(i + 4) == '0')
                            return "17900";
                        if(stringbuffer.charAt(i + 3) == '0' && stringbuffer.charAt(i + 4) == '1')
                            return "17901";
                        if(stringbuffer.charAt(i + 3) == '0' && stringbuffer.charAt(i + 4) == '8')
                            return "17908";
                        if(stringbuffer.charAt(i + 3) == '0' && stringbuffer.charAt(i + 4) == '9')
                            return "17909";
                        if(stringbuffer.charAt(i + 3) == '1' && stringbuffer.charAt(i + 4) == '1')
                            return "17911";
                        if(stringbuffer.charAt(i + 3) == '5' && stringbuffer.charAt(i + 4) == '0')
                            return "17950";
                        if(stringbuffer.charAt(i + 3) == '5' && stringbuffer.charAt(i + 4) == '1')
                            return "17951";
                        if(stringbuffer.charAt(i + 3) == '6' && stringbuffer.charAt(i + 4) == '0')
                            return "17960";
                        if(stringbuffer.charAt(i + 3) == '6' && stringbuffer.charAt(i + 4) == '1')
                            return "17961";
                        if(stringbuffer.charAt(i + 3) == '6' && stringbuffer.charAt(i + 4) == '8')
                            return "17968";
                        if(stringbuffer.charAt(i + 3) == '6' && stringbuffer.charAt(i + 4) == '9')
                            return "17969";
                        if(stringbuffer.charAt(i + 3) == '9' && stringbuffer.charAt(i + 4) == '0')
                            return "17990";
                        if(stringbuffer.charAt(i + 3) == '9' && stringbuffer.charAt(i + 4) == '1')
                            return "17991";
                        if(stringbuffer.charAt(i + 3) == '9' && stringbuffer.charAt(i + 4) == '5')
                            return "17995";
                        if(stringbuffer.charAt(i + 3) == '9' && stringbuffer.charAt(i + 4) == '6')
                            return "17996";
                    }
                    break;
                }
            break;

        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
            if(j >= 10)
                switch(stringbuffer.charAt(i + 1))
                {
                case 48: // '0'
                    if(stringbuffer.charAt(i + 2) >= '1' && stringbuffer.charAt(i + 2) <= '9')
                        return stringbuffer.substring(i, i + 3);
                    break;
                }
            break;
        }
        while(true) 
            return "";
    }

    public static final String EMPTY = "";
    public static final String PREFIX_10193 = "10193";
    public static final String PREFIX_11808 = "11808";
    public static final String PREFIX_12520 = "12520";
    public static final String PREFIX_12583 = "12583";
    public static final String PREFIX_12593 = "12593";
    public static final String PREFIX_17900 = "17900";
    public static final String PREFIX_17901 = "17901";
    public static final String PREFIX_17908 = "17908";
    public static final String PREFIX_17909 = "17909";
    public static final String PREFIX_17911 = "17911";
    public static final String PREFIX_17950 = "17950";
    public static final String PREFIX_17951 = "17951";
    public static final String PREFIX_17960 = "17960";
    public static final String PREFIX_17961 = "17961";
    public static final String PREFIX_17968 = "17968";
    public static final String PREFIX_17969 = "17969";
    public static final String PREFIX_17990 = "17990";
    public static final String PREFIX_17991 = "17991";
    public static final String PREFIX_17995 = "17995";
    public static final String PREFIX_17996 = "17996";
    public static final String SMS_PREFIXES[] = {
        "12520"
    };

}
