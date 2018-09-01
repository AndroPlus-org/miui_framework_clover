// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.gsm;

import android.telephony.PhoneNumberUtils;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.SmsAddress;
import java.text.ParseException;

public class GsmSmsAddress extends SmsAddress
{

    public GsmSmsAddress(byte abyte0[], int i, int j)
        throws ParseException
    {
        origBytes = new byte[j];
        System.arraycopy(abyte0, i, origBytes, 0, j);
        int k = origBytes[0] & 0xff;
        int i1 = origBytes[1] & 0xff;
        ton = i1 >> 4 & 7;
        if((i1 & 0x80) != 128)
            throw new ParseException((new StringBuilder()).append("Invalid TOA - high bit must be set. toa = ").append(i1).toString(), i + 1);
        if(isAlphanumeric())
        {
            i = (k * 4) / 7;
            address = GsmAlphabet.gsm7BitPackedToString(origBytes, 2, i);
        } else
        {
            i = origBytes[j - 1];
            if((k & 1) == 1)
            {
                abyte0 = origBytes;
                int l = j - 1;
                abyte0[l] = (byte)(abyte0[l] | 0xf0);
            }
            address = PhoneNumberUtils.calledPartyBCDToString(origBytes, 1, j - 1);
            origBytes[j - 1] = (byte)i;
        }
    }

    public String getAddressString()
    {
        return address;
    }

    public boolean isAlphanumeric()
    {
        boolean flag;
        if(ton == 5)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isCphsVoiceMessageClear()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isCphsVoiceMessageIndicatorAddress())
        {
            flag1 = flag;
            if((origBytes[2] & 0xff) == 16)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isCphsVoiceMessageIndicatorAddress()
    {
        boolean flag = true;
        if((origBytes[0] & 0xff) == 4 && isAlphanumeric())
        {
            if((origBytes[1] & 0xf) != 0)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean isCphsVoiceMessageSet()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isCphsVoiceMessageIndicatorAddress())
        {
            flag1 = flag;
            if((origBytes[2] & 0xff) == 17)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isNetworkSpecific()
    {
        boolean flag;
        if(ton == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static final int OFFSET_ADDRESS_LENGTH = 0;
    static final int OFFSET_ADDRESS_VALUE = 2;
    static final int OFFSET_TOA = 1;
}
