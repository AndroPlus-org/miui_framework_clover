// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.gsm;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.*;
import android.util.Pair;
import com.android.internal.telephony.GsmAlphabet;
import java.io.UnsupportedEncodingException;

// Referenced classes of package com.android.internal.telephony.gsm:
//            SmsCbHeader

public class GsmSmsCbMessage
{

    private GsmSmsCbMessage()
    {
    }

    public static SmsCbMessage createSmsCbMessage(Context context, SmsCbHeader smscbheader, SmsCbLocation smscblocation, byte abyte0[][])
        throws IllegalArgumentException
    {
        if(smscbheader.isEtwsPrimaryNotification())
            return new SmsCbMessage(1, smscbheader.getGeographicalScope(), smscbheader.getSerialNumber(), smscblocation, smscbheader.getServiceCategory(), null, getEtwsPrimaryMessage(context, smscbheader.getEtwsInfo().getWarningType()), 3, smscbheader.getEtwsInfo(), smscbheader.getCmasInfo());
        context = null;
        StringBuilder stringbuilder = new StringBuilder();
        int i = abyte0.length;
        for(int j = 0; j < i; j++)
        {
            Pair pair = parseBody(smscbheader, abyte0[j]);
            context = (String)pair.first;
            stringbuilder.append((String)pair.second);
        }

        byte byte0;
        if(smscbheader.isEmergencyMessage())
            byte0 = 3;
        else
            byte0 = 0;
        return new SmsCbMessage(1, smscbheader.getGeographicalScope(), smscbheader.getSerialNumber(), smscblocation, smscbheader.getServiceCategory(), context, stringbuilder.toString(), byte0, smscbheader.getEtwsInfo(), smscbheader.getCmasInfo());
    }

    private static String getEtwsPrimaryMessage(Context context, int i)
    {
        context = context.getResources();
        switch(i)
        {
        default:
            return "";

        case 0: // '\0'
            return context.getString(0x10401dd);

        case 1: // '\001'
            return context.getString(0x10401e1);

        case 2: // '\002'
            return context.getString(0x10401de);

        case 3: // '\003'
            return context.getString(0x10401e0);

        case 4: // '\004'
            return context.getString(0x10401df);
        }
    }

    private static Pair parseBody(SmsCbHeader smscbheader, byte abyte0[])
    {
        String s;
        boolean flag;
        int i;
        s = null;
        flag = false;
        i = smscbheader.getDataCodingScheme();
        (i & 0xf0) >> 4;
        JVM INSTR tableswitch 0 15: default 96
    //                   0 168
    //                   1 184
    //                   2 207
    //                   3 223
    //                   4 229
    //                   5 229
    //                   6 278
    //                   7 278
    //                   8 96
    //                   9 278
    //                   10 96
    //                   11 96
    //                   12 96
    //                   13 96
    //                   14 278
    //                   15 306;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L6 _L7 _L7 _L1 _L7 _L1 _L1 _L1 _L1 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_306;
_L1:
        byte byte0 = 1;
_L9:
        if(smscbheader.isUmtsFormat())
        {
            byte byte1 = abyte0[6];
            if(abyte0.length < byte1 * 83 + 7)
                throw new IllegalArgumentException((new StringBuilder()).append("Pdu length ").append(abyte0.length).append(" does not match ").append(byte1).append(" pages").toString());
            smscbheader = new StringBuilder();
            for(int j = 0; j < byte1; j++)
            {
                int k = j * 83 + 7;
                byte byte2 = abyte0[k + 82];
                if(byte2 > 82)
                    throw new IllegalArgumentException((new StringBuilder()).append("Page length ").append(byte2).append(" exceeds maximum value ").append(82).toString());
                Pair pair = unpackBody(abyte0, byte0, k, byte2, flag, s);
                s = (String)pair.first;
                smscbheader.append((String)pair.second);
            }

            return new Pair(s, smscbheader.toString());
        } else
        {
            return unpackBody(abyte0, byte0, 6, abyte0.length - 6, flag, s);
        }
_L2:
        byte0 = 1;
        s = LANGUAGE_CODES_GROUP_0[i & 0xf];
          goto _L9
_L3:
        flag = true;
        if((i & 0xf) == 1)
            byte0 = 3;
        else
            byte0 = 1;
          goto _L9
_L4:
        byte0 = 1;
        s = LANGUAGE_CODES_GROUP_2[i & 0xf];
          goto _L9
_L5:
        byte0 = 1;
          goto _L9
_L6:
        switch((i & 0xc) >> 2)
        {
        default:
            byte0 = 1;
            break;

        case 1: // '\001'
            byte0 = 2;
            break;

        case 2: // '\002'
            byte0 = 3;
            break;
        }
        if(true) goto _L9; else goto _L10
_L10:
_L7:
        throw new IllegalArgumentException((new StringBuilder()).append("Unsupported GSM dataCodingScheme ").append(i).toString());
        if((i & 4) >> 2 == 1)
            byte0 = 2;
        else
            byte0 = 1;
          goto _L9
    }

    private static Pair unpackBody(byte abyte0[], int i, int j, int k, boolean flag, String s)
    {
        Object obj;
        Object obj1;
        String s1;
        obj = null;
        obj1 = obj;
        s1 = s;
        i;
        JVM INSTR tableswitch 1 3: default 40
    //                   1 100
    //                   2 48
    //                   3 169;
           goto _L1 _L2 _L3 _L4
_L1:
        s1 = s;
        obj1 = obj;
_L3:
        if(obj1 == null) goto _L6; else goto _L5
_L5:
        i = ((String) (obj1)).length() - 1;
_L11:
        abyte0 = ((byte []) (obj1));
        if(i < 0) goto _L8; else goto _L7
_L7:
        if(((String) (obj1)).charAt(i) == '\r') goto _L10; else goto _L9
_L9:
        abyte0 = ((String) (obj1)).substring(0, i + 1);
_L8:
        return new Pair(s1, abyte0);
_L2:
        abyte0 = GsmAlphabet.gsm7BitPackedToString(abyte0, j, (k * 8) / 7);
        obj1 = abyte0;
        s1 = s;
        if(flag)
        {
            obj1 = abyte0;
            s1 = s;
            if(abyte0 != null)
            {
                obj1 = abyte0;
                s1 = s;
                if(abyte0.length() > 2)
                {
                    s1 = abyte0.substring(0, 2);
                    obj1 = abyte0.substring(3);
                }
            }
        }
          goto _L3
_L4:
        int l = j;
        i = k;
        s1 = s;
        if(flag)
        {
            l = j;
            i = k;
            s1 = s;
            if(abyte0.length >= j + 2)
            {
                s1 = GsmAlphabet.gsm7BitPackedToString(abyte0, j, 2);
                l = j + 2;
                i = k - 2;
            }
        }
        try
        {
            obj1 = new String(abyte0, l, 0xfffe & i, "utf-16");
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new IllegalArgumentException("Error decoding UTF-16 message", abyte0);
        }
          goto _L3
_L10:
        i--;
          goto _L11
_L6:
        abyte0 = "";
          goto _L8
    }

    private static final char CARRIAGE_RETURN = 13;
    private static final String LANGUAGE_CODES_GROUP_0[] = {
        "de", "en", "it", "fr", "es", "nl", "sv", "da", "pt", "fi", 
        "no", "el", "tr", "hu", "pl", null
    };
    private static final String LANGUAGE_CODES_GROUP_2[] = {
        "cs", "he", "ar", "ru", "is", null, null, null, null, null, 
        null, null, null, null, null, null
    };
    private static final int PDU_BODY_PAGE_LENGTH = 82;

}
