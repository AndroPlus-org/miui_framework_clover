// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.cdma.sms;

import android.util.SparseBooleanArray;
import com.android.internal.telephony.SmsAddress;
import com.android.internal.util.HexDump;

// Referenced classes of package com.android.internal.telephony.cdma.sms:
//            UserData

public class CdmaSmsAddress extends SmsAddress
{

    public CdmaSmsAddress()
    {
    }

    private static String filterNumericSugar(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = s.length();
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            int k = numericCharDialableMap.indexOfKey(c);
            if(k < 0)
                return null;
            if(numericCharDialableMap.valueAt(k))
                stringbuilder.append(c);
            j++;
        }
        return stringbuilder.toString();
    }

    private static String filterWhitespace(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = s.length();
        int j = 0;
        do
        {
            if(j >= i)
                break;
            char c = s.charAt(j);
            if(c != ' ' && c != '\r' && c != '\n' && c != '\t')
                stringbuilder.append(c);
            j++;
        } while(true);
        return stringbuilder.toString();
    }

    public static CdmaSmsAddress parse(String s)
    {
        CdmaSmsAddress cdmasmsaddress;
        byte abyte0[];
        cdmasmsaddress = new CdmaSmsAddress();
        cdmasmsaddress.address = s;
        cdmasmsaddress.ton = 0;
        abyte0 = null;
        String s1 = filterNumericSugar(s);
        if(s1 != null)
            abyte0 = parseToDtmf(s1);
        if(abyte0 == null) goto _L2; else goto _L1
_L1:
        byte abyte2[];
        cdmasmsaddress.digitMode = 0;
        cdmasmsaddress.numberMode = 0;
        abyte2 = abyte0;
        if(s.indexOf('+') != -1)
        {
            cdmasmsaddress.ton = 1;
            abyte2 = abyte0;
        }
_L4:
        cdmasmsaddress.origBytes = abyte2;
        cdmasmsaddress.numberOfDigits = abyte2.length;
        return cdmasmsaddress;
_L2:
        byte abyte1[] = UserData.stringToAscii(filterWhitespace(s));
        if(abyte1 == null)
            return null;
        cdmasmsaddress.digitMode = 1;
        cdmasmsaddress.numberMode = 1;
        abyte2 = abyte1;
        if(s.indexOf('@') != -1)
        {
            cdmasmsaddress.ton = 2;
            abyte2 = abyte1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static byte[] parseToDtmf(String s)
    {
        int i = s.length();
        byte abyte0[] = new byte[i];
        int j = 0;
        while(j < i) 
        {
            int k = s.charAt(j);
            if(k >= 49 && k <= 57)
                k -= 48;
            else
            if(k == 48)
                k = 10;
            else
            if(k == 42)
                k = 11;
            else
            if(k == 35)
                k = 12;
            else
                return null;
            abyte0[j] = (byte)k;
            j++;
        }
        return abyte0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("CdmaSmsAddress ");
        stringbuilder.append("{ digitMode=").append(digitMode);
        stringbuilder.append(", numberMode=").append(numberMode);
        stringbuilder.append(", numberPlan=").append(numberPlan);
        stringbuilder.append(", numberOfDigits=").append(numberOfDigits);
        stringbuilder.append(", ton=").append(ton);
        stringbuilder.append(", address=\"").append(address).append("\"");
        stringbuilder.append(", origBytes=").append(HexDump.toHexString(origBytes));
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public static final int DIGIT_MODE_4BIT_DTMF = 0;
    public static final int DIGIT_MODE_8BIT_CHAR = 1;
    public static final int NUMBERING_PLAN_ISDN_TELEPHONY = 1;
    public static final int NUMBERING_PLAN_UNKNOWN = 0;
    public static final int NUMBER_MODE_DATA_NETWORK = 1;
    public static final int NUMBER_MODE_NOT_DATA_NETWORK = 0;
    public static final int SMS_ADDRESS_MAX = 36;
    public static final int SMS_SUBADDRESS_MAX = 36;
    public static final int TON_ABBREVIATED = 6;
    public static final int TON_ALPHANUMERIC = 5;
    public static final int TON_INTERNATIONAL_OR_IP = 1;
    public static final int TON_NATIONAL_OR_EMAIL = 2;
    public static final int TON_NETWORK = 3;
    public static final int TON_RESERVED = 7;
    public static final int TON_SUBSCRIBER = 4;
    public static final int TON_UNKNOWN = 0;
    private static final SparseBooleanArray numericCharDialableMap;
    private static final char numericCharsDialable[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '*', '#'
    };
    private static final char numericCharsSugar[] = {
        '(', ')', ' ', '-', '+', '.', '/', '\\'
    };
    public int digitMode;
    public int numberMode;
    public int numberOfDigits;
    public int numberPlan;

    static 
    {
        numericCharDialableMap = new SparseBooleanArray(numericCharsDialable.length + numericCharsSugar.length);
        for(int i = 0; i < numericCharsDialable.length; i++)
            numericCharDialableMap.put(numericCharsDialable[i], true);

        for(int j = 0; j < numericCharsSugar.length; j++)
            numericCharDialableMap.put(numericCharsSugar[j], false);

    }
}
