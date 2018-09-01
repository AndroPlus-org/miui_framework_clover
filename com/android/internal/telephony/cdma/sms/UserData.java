// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.cdma.sms;

import android.util.SparseIntArray;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.util.HexDump;

public class UserData
{

    public UserData()
    {
        msgEncodingSet = false;
    }

    public static byte[] stringToAscii(String s)
    {
        int i = s.length();
        byte abyte0[] = new byte[i];
        for(int j = 0; j < i; j++)
        {
            int k = charToAscii.get(s.charAt(j), -1);
            if(k == -1)
                return null;
            abyte0[j] = (byte)k;
        }

        return abyte0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("UserData ");
        StringBuilder stringbuilder1 = stringbuilder.append("{ msgEncoding=");
        Object obj;
        if(msgEncodingSet)
            obj = Integer.valueOf(msgEncoding);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder.append(", msgType=").append(msgType);
        stringbuilder.append(", paddingBits=").append(paddingBits);
        stringbuilder.append(", numFields=").append(numFields);
        stringbuilder.append(", userDataHeader=").append(userDataHeader);
        stringbuilder.append(", payload='").append(HexDump.toHexString(payload)).append("'");
        stringbuilder.append(", payloadStr='").append(payloadStr).append("'");
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public static final int ASCII_CR_INDEX = 13;
    public static final char ASCII_MAP[] = {
        ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', 
        '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', 
        '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', 
        '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 
        'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', 
        '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 
        'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 
        'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 
        'z', '{', '|', '}', '~'
    };
    public static final int ASCII_MAP_BASE_INDEX = 32;
    public static final int ASCII_MAP_MAX_INDEX = (ASCII_MAP.length + 32) - 1;
    public static final int ASCII_NL_INDEX = 10;
    public static final int ENCODING_7BIT_ASCII = 2;
    public static final int ENCODING_GSM_7BIT_ALPHABET = 9;
    public static final int ENCODING_GSM_DCS = 10;
    public static final int ENCODING_GSM_DCS_16BIT = 2;
    public static final int ENCODING_GSM_DCS_7BIT = 0;
    public static final int ENCODING_GSM_DCS_8BIT = 1;
    public static final int ENCODING_IA5 = 3;
    public static final int ENCODING_IS91_EXTENDED_PROTOCOL = 1;
    public static final int ENCODING_KOREAN = 6;
    public static final int ENCODING_LATIN = 8;
    public static final int ENCODING_LATIN_HEBREW = 7;
    public static final int ENCODING_OCTET = 0;
    public static final int ENCODING_SHIFT_JIS = 5;
    public static final int ENCODING_UNICODE_16 = 4;
    public static final int IS91_MSG_TYPE_CLI = 132;
    public static final int IS91_MSG_TYPE_SHORT_MESSAGE = 133;
    public static final int IS91_MSG_TYPE_SHORT_MESSAGE_FULL = 131;
    public static final int IS91_MSG_TYPE_VOICEMAIL_STATUS = 130;
    public static final int PRINTABLE_ASCII_MIN_INDEX = 32;
    static final byte UNENCODABLE_7_BIT_CHAR = 32;
    public static final SparseIntArray charToAscii;
    public int msgEncoding;
    public boolean msgEncodingSet;
    public int msgType;
    public int numFields;
    public int paddingBits;
    public byte payload[];
    public String payloadStr;
    public SmsHeader userDataHeader;

    static 
    {
        charToAscii = new SparseIntArray();
        for(int i = 0; i < ASCII_MAP.length; i++)
            charToAscii.put(ASCII_MAP[i], i + 32);

        charToAscii.put(10, 10);
        charToAscii.put(13, 13);
    }
}
