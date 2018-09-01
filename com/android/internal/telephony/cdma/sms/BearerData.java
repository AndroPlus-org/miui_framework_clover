// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.cdma.sms;

import android.content.res.Resources;
import android.telephony.Rlog;
import android.telephony.SmsCbCmasInfo;
import android.telephony.cdma.CdmaSmsCbProgramData;
import android.telephony.cdma.CdmaSmsCbProgramResults;
import android.text.format.Time;
import android.util.SparseIntArray;
import com.android.internal.telephony.*;
import com.android.internal.telephony.gsm.SmsMessage;
import com.android.internal.telephony.uicc.IccUtils;
import com.android.internal.util.BitwiseInputStream;
import com.android.internal.util.BitwiseOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

// Referenced classes of package com.android.internal.telephony.cdma.sms:
//            UserData, CdmaSmsAddress

public final class BearerData
{
    private static class CodingException extends Exception
    {

        public CodingException(String s)
        {
            super(s);
        }
    }

    private static class Gsm7bitCodingResult
    {

        byte data[];
        int septets;

        private Gsm7bitCodingResult()
        {
        }

        Gsm7bitCodingResult(Gsm7bitCodingResult gsm7bitcodingresult)
        {
            this();
        }
    }

    public static class TimeStamp extends Time
    {

        public static TimeStamp fromByteArray(byte abyte0[])
        {
            TimeStamp timestamp = new TimeStamp();
            int i = IccUtils.cdmaBcdByteToInt(abyte0[0]);
            if(i > 99 || i < 0)
                return null;
            if(i >= 96)
                i += 1900;
            else
                i += 2000;
            timestamp.year = i;
            i = IccUtils.cdmaBcdByteToInt(abyte0[1]);
            if(i < 1 || i > 12)
                return null;
            timestamp.month = i - 1;
            i = IccUtils.cdmaBcdByteToInt(abyte0[2]);
            if(i < 1 || i > 31)
                return null;
            timestamp.monthDay = i;
            i = IccUtils.cdmaBcdByteToInt(abyte0[3]);
            if(i < 0 || i > 23)
                return null;
            timestamp.hour = i;
            i = IccUtils.cdmaBcdByteToInt(abyte0[4]);
            if(i < 0 || i > 59)
                return null;
            timestamp.minute = i;
            i = IccUtils.cdmaBcdByteToInt(abyte0[5]);
            if(i < 0 || i > 59)
            {
                return null;
            } else
            {
                timestamp.second = i;
                return timestamp;
            }
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("TimeStamp ");
            stringbuilder.append("{ year=").append(year);
            stringbuilder.append(", month=").append(month);
            stringbuilder.append(", day=").append(monthDay);
            stringbuilder.append(", hour=").append(hour);
            stringbuilder.append(", minute=").append(minute);
            stringbuilder.append(", second=").append(second);
            stringbuilder.append(" }");
            return stringbuilder.toString();
        }

        public TimeStamp()
        {
            super(TimeZone.getDefault().getID());
        }
    }


    public BearerData()
    {
        priorityIndicatorSet = false;
        priority = 0;
        privacyIndicatorSet = false;
        privacy = 0;
        alertIndicatorSet = false;
        alert = 0;
        displayModeSet = false;
        displayMode = 1;
        languageIndicatorSet = false;
        language = 0;
        messageStatusSet = false;
        errorClass = 255;
        messageStatus = 255;
        userResponseCodeSet = false;
    }

    public static com.android.internal.telephony.GsmAlphabet.TextEncodingDetails calcTextEncodingDetails(CharSequence charsequence, boolean flag, boolean flag1)
    {
        int i = countAsciiSeptets(charsequence, flag);
        com.android.internal.telephony.GsmAlphabet.TextEncodingDetails textencodingdetails;
        if(i != -1 && i <= 160)
        {
            textencodingdetails = new com.android.internal.telephony.GsmAlphabet.TextEncodingDetails();
            textencodingdetails.msgCount = 1;
            textencodingdetails.codeUnitCount = i;
            textencodingdetails.codeUnitsRemaining = 160 - i;
            textencodingdetails.codeUnitSize = 1;
        } else
        {
            com.android.internal.telephony.GsmAlphabet.TextEncodingDetails textencodingdetails1 = SmsMessage.calculateLength(charsequence, flag);
            textencodingdetails = textencodingdetails1;
            if(textencodingdetails1.msgCount == 1)
            {
                textencodingdetails = textencodingdetails1;
                if(textencodingdetails1.codeUnitSize == 1)
                {
                    textencodingdetails = textencodingdetails1;
                    if(flag1)
                        return SmsMessageBase.calcUnicodeEncodingDetails(charsequence);
                }
            }
        }
        return textencodingdetails;
    }

    private static int countAsciiSeptets(CharSequence charsequence, boolean flag)
    {
        int i = charsequence.length();
        if(flag)
            return i;
        for(int j = 0; j < i; j++)
            if(UserData.charToAscii.get(charsequence.charAt(j), -1) == -1)
                return -1;

        return i;
    }

    public static BearerData decode(byte abyte0[])
    {
        return decode(abyte0, 0);
    }

    public static BearerData decode(byte abyte0[], int i)
    {
        Object obj;
        obj = JVM INSTR new #269 <Class BitwiseInputStream>;
        ((BitwiseInputStream) (obj)).BitwiseInputStream(abyte0);
        abyte0 = JVM INSTR new #2   <Class BearerData>;
        abyte0.BearerData();
        int j = 0;
_L22:
        int k;
        if(((BitwiseInputStream) (obj)).available() <= 0)
            break MISSING_BLOCK_LABEL_452;
        k = ((BitwiseInputStream) (obj)).read(8);
        int l = 1 << k;
        if((j & l) != 0 && k >= 0 && k <= 23)
        {
            boolean flag;
            try
            {
                abyte0 = JVM INSTR new #6   <Class BearerData$CodingException>;
                obj = JVM INSTR new #282 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                abyte0.CodingException(((StringBuilder) (obj)).append("illegal duplicate subparameter (").append(k).append(")").toString());
                throw abyte0;
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                Rlog.e("BearerData", (new StringBuilder()).append("BearerData decode failed: ").append(abyte0).toString());
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                Rlog.e("BearerData", (new StringBuilder()).append("BearerData decode failed: ").append(abyte0).toString());
            }
            return null;
        }
        k;
        JVM INSTR tableswitch 0 20: default 228
    //                   0 262
    //                   1 272
    //                   2 282
    //                   3 332
    //                   4 342
    //                   5 352
    //                   6 362
    //                   7 372
    //                   8 412
    //                   9 382
    //                   10 292
    //                   11 302
    //                   12 422
    //                   13 392
    //                   14 312
    //                   15 402
    //                   16 228
    //                   17 432
    //                   18 442
    //                   19 228
    //                   20 322;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L1 _L18 _L19 _L1 _L20
_L19:
        break MISSING_BLOCK_LABEL_442;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        flag = decodeReserved(abyte0, ((BitwiseInputStream) (obj)), k);
_L23:
        if(flag && k >= 0 && k <= 23)
            j |= l;
        if(true) goto _L22; else goto _L21
_L21:
        flag = decodeMessageId(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L3:
        flag = decodeUserData(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L4:
        flag = decodeUserResponseCode(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L12:
        flag = decodeReplyOption(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L13:
        flag = decodeMsgCount(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L16:
        flag = decodeCallbackNumber(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L20:
        flag = decodeMsgStatus(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L5:
        flag = decodeMsgCenterTimeStamp(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L6:
        flag = decodeValidityAbs(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L7:
        flag = decodeValidityRel(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L8:
        flag = decodeDeferredDeliveryAbs(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L9:
        flag = decodeDeferredDeliveryRel(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L11:
        flag = decodePrivacyIndicator(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L15:
        flag = decodeLanguageIndicator(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L17:
        flag = decodeDisplayMode(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L10:
        flag = decodePriorityIndicator(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L14:
        flag = decodeMsgDeliveryAlert(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
_L18:
        flag = decodeDepositIndex(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
        flag = decodeServiceCategoryProgramData(abyte0, ((BitwiseInputStream) (obj)));
          goto _L23
        if((j & 1) != 0)
            break MISSING_BLOCK_LABEL_501;
        abyte0 = JVM INSTR new #6   <Class BearerData$CodingException>;
        abyte0.CodingException("missing MESSAGE_IDENTIFIER subparam");
        throw abyte0;
        if(((BearerData) (abyte0)).userData == null) goto _L25; else goto _L24
_L24:
        if(!isCmasAlertCategory(i)) goto _L27; else goto _L26
_L26:
        decodeCmasUserData(abyte0, i);
_L25:
        return abyte0;
_L27:
        if(((BearerData) (abyte0)).userData.msgEncoding != 1)
            break MISSING_BLOCK_LABEL_582;
        if((j ^ 1 ^ 2) == 0)
            break MISSING_BLOCK_LABEL_575;
        StringBuilder stringbuilder = JVM INSTR new #282 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Rlog.e("BearerData", stringbuilder.append("IS-91 must occur without extra subparams (").append(j).append(")").toString());
        decodeIs91(abyte0);
          goto _L25
        decodeUserDataPayload(((BearerData) (abyte0)).userData, ((BearerData) (abyte0)).hasUserDataHeader);
          goto _L25
    }

    private static String decode7bitAscii(byte abyte0[], int i, int j)
        throws CodingException
    {
        int k;
        Object obj;
        BitwiseInputStream bitwiseinputstream;
        i *= 8;
        int l;
        try
        {
            k = (i + 6) / 7;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new CodingException((new StringBuilder()).append("7bit ASCII decode failed: ").append(abyte0).toString());
        }
        j -= k;
        obj = JVM INSTR new #405 <Class StringBuffer>;
        ((StringBuffer) (obj)).StringBuffer(j);
        bitwiseinputstream = JVM INSTR new #269 <Class BitwiseInputStream>;
        bitwiseinputstream.BitwiseInputStream(abyte0);
        l = k * 7 + j * 7;
        if(bitwiseinputstream.available() < l)
        {
            abyte0 = JVM INSTR new #6   <Class BearerData$CodingException>;
            obj = JVM INSTR new #282 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            abyte0.CodingException(((StringBuilder) (obj)).append("insufficient data (wanted ").append(l).append(" bits, but only have ").append(bitwiseinputstream.available()).append(")").toString());
            throw abyte0;
        }
        bitwiseinputstream.skip(i + (k * 7 - i));
        i = 0;
_L2:
        if(i >= j)
            break MISSING_BLOCK_LABEL_251;
        k = bitwiseinputstream.read(7);
        if(k < 32)
            break; /* Loop/switch isn't completed */
        if(k > UserData.ASCII_MAP_MAX_INDEX)
            break; /* Loop/switch isn't completed */
        ((StringBuffer) (obj)).append(UserData.ASCII_MAP[k - 32]);
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(k != 10)
            break MISSING_BLOCK_LABEL_223;
        ((StringBuffer) (obj)).append('\n');
          goto _L3
        if(k != 13)
            break MISSING_BLOCK_LABEL_240;
        ((StringBuffer) (obj)).append('\r');
          goto _L3
        ((StringBuffer) (obj)).append(' ');
          goto _L3
        abyte0 = ((StringBuffer) (obj)).toString();
        return abyte0;
    }

    private static String decode7bitGsm(byte abyte0[], int i, int j)
        throws CodingException
    {
        int k = i * 8;
        int l = (k + 6) / 7;
        abyte0 = GsmAlphabet.gsm7BitPackedToString(abyte0, i, j - l, l * 7 - k, 0, 0);
        if(abyte0 == null)
            throw new CodingException("7bit GSM decoding failed");
        else
            return abyte0;
    }

    private static boolean decodeCallbackNumber(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException, CodingException
    {
        int i = bitwiseinputstream.read(8) * 8;
        if(i < 8)
        {
            bitwiseinputstream.skip(i);
            return false;
        }
        CdmaSmsAddress cdmasmsaddress = new CdmaSmsAddress();
        cdmasmsaddress.digitMode = bitwiseinputstream.read(1);
        int j = 4;
        int k = 1;
        if(cdmasmsaddress.digitMode == 1)
        {
            cdmasmsaddress.ton = bitwiseinputstream.read(3);
            cdmasmsaddress.numberPlan = bitwiseinputstream.read(4);
            j = 8;
            k = (byte)8;
        }
        cdmasmsaddress.numberOfDigits = bitwiseinputstream.read(8);
        k = i - (byte)(k + 8);
        i = cdmasmsaddress.numberOfDigits * j;
        j = k - i;
        if(k < i)
        {
            throw new CodingException((new StringBuilder()).append("CALLBACK_NUMBER subparam encoding size error (remainingBits + ").append(k).append(", dataBits + ").append(i).append(", paddingBits + ").append(j).append(")").toString());
        } else
        {
            cdmasmsaddress.origBytes = bitwiseinputstream.readByteArray(i);
            bitwiseinputstream.skip(j);
            decodeSmsAddress(cdmasmsaddress);
            bearerdata.callbackNumber = cdmasmsaddress;
            return true;
        }
    }

    private static String decodeCharset(byte abyte0[], int i, int j, int k, String s)
        throws CodingException
    {
        int l;
label0:
        {
            if(j >= 0)
            {
                l = j;
                if(j * k + i <= abyte0.length)
                    break label0;
            }
            l = (abyte0.length - i - i % k) / k;
            if(l < 0)
                throw new CodingException((new StringBuilder()).append(s).append(" decode failed: offset out of range").toString());
            Rlog.e("BearerData", (new StringBuilder()).append(s).append(" decode error: offset = ").append(i).append(" numFields = ").append(j).append(" data.length = ").append(abyte0.length).append(" maxNumFields = ").append(l).toString());
        }
        try
        {
            abyte0 = new String(abyte0, i, l * k, s);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new CodingException((new StringBuilder()).append(s).append(" decode failed: ").append(abyte0).toString());
        }
        return abyte0;
    }

    private static void decodeCmasUserData(BearerData bearerdata, int i)
        throws com.android.internal.util.BitwiseInputStream.AccessException, CodingException
    {
        BitwiseInputStream bitwiseinputstream;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        bitwiseinputstream = new BitwiseInputStream(bearerdata.userData.payload);
        if(bitwiseinputstream.available() < 8)
            throw new CodingException("emergency CB with no CMAE_protocol_version");
        j = bitwiseinputstream.read(8);
        if(j != 0)
            throw new CodingException((new StringBuilder()).append("unsupported CMAE_protocol_version ").append(j).toString());
        k = serviceCategoryToCmasMessageClass(i);
        l = -1;
        j = -1;
        i1 = -1;
        j1 = -1;
        k1 = -1;
_L10:
        int l1;
        if(bitwiseinputstream.available() < 16)
            break; /* Loop/switch isn't completed */
        i = bitwiseinputstream.read(8);
        l1 = bitwiseinputstream.read(8);
        i;
        JVM INSTR tableswitch 0 1: default 140
    //                   0 178
    //                   1 339;
           goto _L1 _L2 _L3
_L1:
        Rlog.w("BearerData", (new StringBuilder()).append("skipping unsupported CMAS record type ").append(i).toString());
        bitwiseinputstream.skip(l1 * 8);
        continue; /* Loop/switch isn't completed */
_L2:
        UserData userdata;
        userdata = new UserData();
        userdata.msgEncoding = bitwiseinputstream.read(5);
        userdata.msgEncodingSet = true;
        userdata.msgType = 0;
        userdata.msgEncoding;
        JVM INSTR tableswitch 0 9: default 268
    //                   0 307
    //                   1 268
    //                   2 315
    //                   3 315
    //                   4 329
    //                   5 268
    //                   6 268
    //                   7 268
    //                   8 307
    //                   9 315;
           goto _L4 _L5 _L4 _L6 _L6 _L7 _L4 _L4 _L4 _L5 _L6
_L4:
        i = 0;
_L8:
        userdata.numFields = i;
        userdata.payload = bitwiseinputstream.readByteArray(l1 * 8 - 5);
        decodeUserDataPayload(userdata, false);
        bearerdata.userData = userdata;
        continue; /* Loop/switch isn't completed */
_L5:
        i = l1 - 1;
          goto _L8
_L6:
        i = (l1 * 8 - 5) / 7;
          goto _L8
_L7:
        i = (l1 - 1) / 2;
          goto _L8
_L3:
        l = bitwiseinputstream.read(8);
        j = bitwiseinputstream.read(8);
        i1 = bitwiseinputstream.read(4);
        j1 = bitwiseinputstream.read(4);
        k1 = bitwiseinputstream.read(4);
        bitwiseinputstream.skip(l1 * 8 - 28);
        if(true) goto _L10; else goto _L9
_L9:
        bearerdata.cmasWarningInfo = new SmsCbCmasInfo(k, l, j, i1, j1, k1);
        return;
    }

    private static boolean decodeDeferredDeliveryAbs(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 48)
        {
            j = i - 48;
            flag = true;
            bearerdata.deferredDeliveryTimeAbsolute = TimeStamp.fromByteArray(bitwiseinputstream.readByteArray(48));
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("DEFERRED_DELIVERY_TIME_ABSOLUTE decode ");
            if(flag)
                bearerdata = "succeeded";
            else
                bearerdata = "failed";
            Rlog.d("BearerData", stringbuilder.append(bearerdata).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        return flag;
    }

    private static boolean decodeDeferredDeliveryRel(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.validityPeriodRelative = bitwiseinputstream.read(8);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("DEFERRED_DELIVERY_TIME_RELATIVE decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.validityPeriodRelativeSet = flag;
        return flag;
    }

    private static boolean decodeDepositIndex(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 16)
        {
            j = i - 16;
            flag = true;
            bearerdata.depositIndex = bitwiseinputstream.read(8) << 8 | bitwiseinputstream.read(8);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("MESSAGE_DEPOSIT_INDEX decode ");
            if(flag)
                bearerdata = "succeeded";
            else
                bearerdata = "failed";
            Rlog.d("BearerData", stringbuilder.append(bearerdata).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        return flag;
    }

    private static boolean decodeDisplayMode(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.displayMode = bitwiseinputstream.read(2);
            bitwiseinputstream.skip(6);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("DISPLAY_MODE decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.displayModeSet = flag;
        return flag;
    }

    private static String decodeDtmfSmsAddress(byte abyte0[], int i)
        throws CodingException
    {
        StringBuffer stringbuffer = new StringBuffer(i);
        int j = 0;
        while(j < i) 
        {
            int k = abyte0[j / 2] >>> 4 - (j % 2) * 4 & 0xf;
            if(k >= 1 && k <= 9)
                stringbuffer.append(Integer.toString(k, 10));
            else
            if(k == 10)
                stringbuffer.append('0');
            else
            if(k == 11)
                stringbuffer.append('*');
            else
            if(k == 12)
                stringbuffer.append('#');
            else
                throw new CodingException((new StringBuilder()).append("invalid SMS address DTMF code (").append(k).append(")").toString());
            j++;
        }
        return stringbuffer.toString();
    }

    private static String decodeGsmDcs(byte abyte0[], int i, int j, int k)
        throws CodingException
    {
        if((k & 0xc0) != 0)
            throw new CodingException((new StringBuilder()).append("unsupported coding group (").append(k).append(")").toString());
        switch(k >> 2 & 3)
        {
        default:
            throw new CodingException((new StringBuilder()).append("unsupported user msgType encoding (").append(k).append(")").toString());

        case 0: // '\0'
            return decode7bitGsm(abyte0, i, j);

        case 1: // '\001'
            return decodeUtf8(abyte0, i, j);

        case 2: // '\002'
            return decodeUtf16(abyte0, i, j);
        }
    }

    private static void decodeIs91(BearerData bearerdata)
        throws com.android.internal.util.BitwiseInputStream.AccessException, CodingException
    {
        bearerdata.userData.msgType;
        JVM INSTR tableswitch 130 133: default 36
    //                   130 76
    //                   131 88
    //                   132 81
    //                   133 88;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        throw new CodingException((new StringBuilder()).append("unsupported IS-91 message type (").append(bearerdata.userData.msgType).append(")").toString());
_L2:
        decodeIs91VoicemailStatus(bearerdata);
_L6:
        return;
_L4:
        decodeIs91Cli(bearerdata);
        continue; /* Loop/switch isn't completed */
_L3:
        decodeIs91ShortMessage(bearerdata);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static void decodeIs91Cli(BearerData bearerdata)
        throws CodingException
    {
        int i = (new BitwiseInputStream(bearerdata.userData.payload)).available() / 4;
        int j;
        for(j = bearerdata.userData.numFields; i > 14 || i < 3 || i < j;)
            throw new CodingException("IS-91 voicemail status decoding failed");

        CdmaSmsAddress cdmasmsaddress = new CdmaSmsAddress();
        cdmasmsaddress.digitMode = 0;
        cdmasmsaddress.origBytes = bearerdata.userData.payload;
        cdmasmsaddress.numberOfDigits = (byte)j;
        decodeSmsAddress(cdmasmsaddress);
        bearerdata.callbackNumber = cdmasmsaddress;
    }

    private static void decodeIs91ShortMessage(BearerData bearerdata)
        throws com.android.internal.util.BitwiseInputStream.AccessException, CodingException
    {
        BitwiseInputStream bitwiseinputstream = new BitwiseInputStream(bearerdata.userData.payload);
        int i = bitwiseinputstream.available() / 6;
        int k = bearerdata.userData.numFields;
        if(k > 14 || i < k)
            throw new CodingException("IS-91 short message decoding failed");
        StringBuffer stringbuffer = new StringBuffer(i);
        for(int j = 0; j < k; j++)
            stringbuffer.append(UserData.ASCII_MAP[bitwiseinputstream.read(6)]);

        bearerdata.userData.payloadStr = stringbuffer.toString();
    }

    private static void decodeIs91VoicemailStatus(BearerData bearerdata)
        throws com.android.internal.util.BitwiseInputStream.AccessException, CodingException
    {
        char c;
        Object obj = new BitwiseInputStream(bearerdata.userData.payload);
        int i = ((BitwiseInputStream) (obj)).available() / 6;
        int j;
        for(j = bearerdata.userData.numFields; i > 14 || i < 3 || i < j;)
            throw new CodingException("IS-91 voicemail status decoding failed");

        StringBuffer stringbuffer;
        try
        {
            stringbuffer = JVM INSTR new #405 <Class StringBuffer>;
            stringbuffer.StringBuffer(i);
            for(; ((BitwiseInputStream) (obj)).available() >= 6; stringbuffer.append(UserData.ASCII_MAP[((BitwiseInputStream) (obj)).read(6)]));
        }
        // Misplaced declaration of an exception variable
        catch(BearerData bearerdata)
        {
            throw new CodingException((new StringBuilder()).append("IS-91 voicemail status decoding failed: ").append(bearerdata).toString());
        }
        // Misplaced declaration of an exception variable
        catch(BearerData bearerdata)
        {
            throw new CodingException((new StringBuilder()).append("IS-91 voicemail status decoding failed: ").append(bearerdata).toString());
        }
        obj = stringbuffer.toString();
        bearerdata.numberOfMessages = Integer.parseInt(((String) (obj)).substring(0, 2));
        c = ((String) (obj)).charAt(2);
        if(c != ' ') goto _L2; else goto _L1
_L1:
        bearerdata.priority = 0;
_L3:
        bearerdata.priorityIndicatorSet = true;
        bearerdata.userData.payloadStr = ((String) (obj)).substring(3, j - 3);
        return;
_L2:
        if(c != '!')
            break MISSING_BLOCK_LABEL_229;
        bearerdata.priority = 2;
          goto _L3
        bearerdata = JVM INSTR new #6   <Class BearerData$CodingException>;
        StringBuilder stringbuilder = JVM INSTR new #282 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        bearerdata.CodingException(stringbuilder.append("IS-91 voicemail status decoding failed: illegal priority setting (").append(c).append(")").toString());
        throw bearerdata;
    }

    private static boolean decodeLanguageIndicator(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.language = bitwiseinputstream.read(8);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("LANGUAGE_INDICATOR decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.languageIndicatorSet = flag;
        return flag;
    }

    private static String decodeLatin(byte abyte0[], int i, int j)
        throws CodingException
    {
        return decodeCharset(abyte0, i, j, 1, "ISO-8859-1");
    }

    private static boolean decodeMessageId(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = true;
        boolean flag1 = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 24)
        {
            j = i - 24;
            boolean flag2 = true;
            bearerdata.messageType = bitwiseinputstream.read(4);
            bearerdata.messageId = bitwiseinputstream.read(8) << 8;
            bearerdata.messageId = bearerdata.messageId | bitwiseinputstream.read(8);
            StringBuilder stringbuilder;
            if(bitwiseinputstream.read(1) == 1)
                flag1 = flag;
            else
                flag1 = false;
            bearerdata.hasUserDataHeader = flag1;
            bitwiseinputstream.skip(3);
            flag1 = flag2;
        }
        if(!flag1 || j > 0)
        {
            stringbuilder = (new StringBuilder()).append("MESSAGE_IDENTIFIER decode ");
            if(flag1)
                bearerdata = "succeeded";
            else
                bearerdata = "failed";
            Rlog.d("BearerData", stringbuilder.append(bearerdata).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        return flag1;
    }

    private static boolean decodeMsgCenterTimeStamp(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 48)
        {
            j = i - 48;
            flag = true;
            bearerdata.msgCenterTimeStamp = TimeStamp.fromByteArray(bitwiseinputstream.readByteArray(48));
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("MESSAGE_CENTER_TIME_STAMP decode ");
            if(flag)
                bearerdata = "succeeded";
            else
                bearerdata = "failed";
            Rlog.d("BearerData", stringbuilder.append(bearerdata).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        return flag;
    }

    private static boolean decodeMsgCount(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.numberOfMessages = IccUtils.cdmaBcdByteToInt((byte)bitwiseinputstream.read(8));
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("NUMBER_OF_MESSAGES decode ");
            if(flag)
                bearerdata = "succeeded";
            else
                bearerdata = "failed";
            Rlog.d("BearerData", stringbuilder.append(bearerdata).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        return flag;
    }

    private static boolean decodeMsgDeliveryAlert(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.alert = bitwiseinputstream.read(2);
            bitwiseinputstream.skip(6);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("ALERT_ON_MESSAGE_DELIVERY decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.alertIndicatorSet = flag;
        return flag;
    }

    private static boolean decodeMsgStatus(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.errorClass = bitwiseinputstream.read(2);
            bearerdata.messageStatus = bitwiseinputstream.read(6);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("MESSAGE_STATUS decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.messageStatusSet = flag;
        return flag;
    }

    private static boolean decodePriorityIndicator(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.priority = bitwiseinputstream.read(2);
            bitwiseinputstream.skip(6);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("PRIORITY_INDICATOR decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.priorityIndicatorSet = flag;
        return flag;
    }

    private static boolean decodePrivacyIndicator(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.privacy = bitwiseinputstream.read(2);
            bitwiseinputstream.skip(6);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("PRIVACY_INDICATOR decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.privacyIndicatorSet = flag;
        return flag;
    }

    private static boolean decodeReplyOption(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = true;
        boolean flag1 = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            boolean flag2 = true;
            StringBuilder stringbuilder;
            if(bitwiseinputstream.read(1) == 1)
                flag1 = true;
            else
                flag1 = false;
            bearerdata.userAckReq = flag1;
            if(bitwiseinputstream.read(1) == 1)
                flag1 = true;
            else
                flag1 = false;
            bearerdata.deliveryAckReq = flag1;
            if(bitwiseinputstream.read(1) == 1)
                flag1 = true;
            else
                flag1 = false;
            bearerdata.readAckReq = flag1;
            if(bitwiseinputstream.read(1) == 1)
                flag1 = flag;
            else
                flag1 = false;
            bearerdata.reportReq = flag1;
            bitwiseinputstream.skip(4);
            flag1 = flag2;
        }
        if(!flag1 || j > 0)
        {
            stringbuilder = (new StringBuilder()).append("REPLY_OPTION decode ");
            if(flag1)
                bearerdata = "succeeded";
            else
                bearerdata = "failed";
            Rlog.d("BearerData", stringbuilder.append(bearerdata).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        return flag1;
    }

    private static boolean decodeReserved(BearerData bearerdata, BitwiseInputStream bitwiseinputstream, int i)
        throws com.android.internal.util.BitwiseInputStream.AccessException, CodingException
    {
        boolean flag = false;
        int j = bitwiseinputstream.read(8);
        int k = j * 8;
        if(k <= bitwiseinputstream.available())
        {
            flag = true;
            bitwiseinputstream.skip(k);
        }
        bitwiseinputstream = (new StringBuilder()).append("RESERVED bearer data subparameter ").append(i).append(" decode ");
        if(flag)
            bearerdata = "succeeded";
        else
            bearerdata = "failed";
        Rlog.d("BearerData", bitwiseinputstream.append(bearerdata).append(" (param bits = ").append(k).append(")").toString());
        if(!flag)
            throw new CodingException((new StringBuilder()).append("RESERVED bearer data subparameter ").append(i).append(" had invalid SUBPARAM_LEN ").append(j).toString());
        else
            return flag;
    }

    private static boolean decodeServiceCategoryProgramData(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException, CodingException
    {
        if(bitwiseinputstream.available() < 13)
            throw new CodingException((new StringBuilder()).append("SERVICE_CATEGORY_PROGRAM_DATA decode failed: only ").append(bitwiseinputstream.available()).append(" bits available").toString());
        int i = bitwiseinputstream.read(8);
        int j = bitwiseinputstream.read(5);
        i = i * 8 - 5;
        if(bitwiseinputstream.available() < i)
            throw new CodingException((new StringBuilder()).append("SERVICE_CATEGORY_PROGRAM_DATA decode failed: only ").append(bitwiseinputstream.available()).append(" bits available (").append(i).append(" bits expected)").toString());
        ArrayList arraylist = new ArrayList();
        boolean flag;
        for(flag = false; i >= 48; flag = true)
        {
            int k = bitwiseinputstream.read(4);
            int l = bitwiseinputstream.read(8);
            int i1 = bitwiseinputstream.read(8);
            int j1 = bitwiseinputstream.read(8);
            int k1 = bitwiseinputstream.read(8);
            int l1 = bitwiseinputstream.read(4);
            int i2 = bitwiseinputstream.read(8);
            int j2 = i - 48;
            i = getBitsForNumFields(j, i2);
            if(j2 < i)
                throw new CodingException((new StringBuilder()).append("category name is ").append(i).append(" bits in length,").append(" but there are only ").append(j2).append(" bits available").toString());
            UserData userdata = new UserData();
            userdata.msgEncoding = j;
            userdata.msgEncodingSet = true;
            userdata.numFields = i2;
            userdata.payload = bitwiseinputstream.readByteArray(i);
            i = j2 - i;
            decodeUserDataPayload(userdata, false);
            arraylist.add(new CdmaSmsCbProgramData(k, l << 8 | i1, j1, k1, l1, userdata.payloadStr));
        }

        if(!flag || i > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("SERVICE_CATEGORY_PROGRAM_DATA decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(i).append(')').toString());
        }
        bitwiseinputstream.skip(i);
        bearerdata.serviceCategoryProgramData = arraylist;
        return flag;
    }

    private static String decodeShiftJis(byte abyte0[], int i, int j)
        throws CodingException
    {
        return decodeCharset(abyte0, i, j, 1, "Shift_JIS");
    }

    private static void decodeSmsAddress(CdmaSmsAddress cdmasmsaddress)
        throws CodingException
    {
        if(cdmasmsaddress.digitMode != 1)
            break MISSING_BLOCK_LABEL_47;
        String s = JVM INSTR new #489 <Class String>;
        s.String(cdmasmsaddress.origBytes, 0, cdmasmsaddress.origBytes.length, "US-ASCII");
        cdmasmsaddress.address = s;
_L1:
        return;
        cdmasmsaddress;
        throw new CodingException("invalid SMS address ASCII code");
        cdmasmsaddress.address = decodeDtmfSmsAddress(cdmasmsaddress.origBytes, cdmasmsaddress.numberOfDigits);
          goto _L1
    }

    private static boolean decodeUserData(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        int i = bitwiseinputstream.read(8);
        bearerdata.userData = new UserData();
        bearerdata.userData.msgEncoding = bitwiseinputstream.read(5);
        bearerdata.userData.msgEncodingSet = true;
        bearerdata.userData.msgType = 0;
        byte byte0 = 5;
        if(bearerdata.userData.msgEncoding == 1 || bearerdata.userData.msgEncoding == 10)
        {
            bearerdata.userData.msgType = bitwiseinputstream.read(8);
            byte0 = 13;
        }
        bearerdata.userData.numFields = bitwiseinputstream.read(8);
        bearerdata.userData.payload = bitwiseinputstream.readByteArray(i * 8 - (byte0 + 8));
        return true;
    }

    private static void decodeUserDataPayload(UserData userdata, boolean flag)
        throws CodingException
    {
        int i;
        i = 0;
        if(flag)
        {
            int j = userdata.payload[0] & 0xff;
            i = j + 1 + 0;
            byte abyte0[] = new byte[j];
            System.arraycopy(userdata.payload, 1, abyte0, 0, j);
            userdata.userDataHeader = SmsHeader.fromByteArray(abyte0);
        }
        userdata.msgEncoding;
        JVM INSTR tableswitch 0 10: default 112
    //                   0 149
    //                   1 112
    //                   2 251
    //                   3 251
    //                   4 270
    //                   5 327
    //                   6 112
    //                   7 112
    //                   8 308
    //                   9 289
    //                   10 346;
           goto _L1 _L2 _L1 _L3 _L3 _L4 _L5 _L1 _L1 _L6 _L7 _L8
_L1:
        throw new CodingException((new StringBuilder()).append("unsupported user data encoding (").append(userdata.msgEncoding).append(")").toString());
_L2:
        flag = Resources.getSystem().getBoolean(0x11200a3);
        byte abyte1[] = new byte[userdata.numFields];
        int k;
        if(userdata.numFields < userdata.payload.length)
            k = userdata.numFields;
        else
            k = userdata.payload.length;
        System.arraycopy(userdata.payload, 0, abyte1, 0, k);
        userdata.payload = abyte1;
        if(!flag)
            userdata.payloadStr = decodeLatin(userdata.payload, i, userdata.numFields);
        else
            userdata.payloadStr = decodeUtf8(userdata.payload, i, userdata.numFields);
_L10:
        return;
_L3:
        userdata.payloadStr = decode7bitAscii(userdata.payload, i, userdata.numFields);
        continue; /* Loop/switch isn't completed */
_L4:
        userdata.payloadStr = decodeUtf16(userdata.payload, i, userdata.numFields);
        continue; /* Loop/switch isn't completed */
_L7:
        userdata.payloadStr = decode7bitGsm(userdata.payload, i, userdata.numFields);
        continue; /* Loop/switch isn't completed */
_L6:
        userdata.payloadStr = decodeLatin(userdata.payload, i, userdata.numFields);
        continue; /* Loop/switch isn't completed */
_L5:
        userdata.payloadStr = decodeShiftJis(userdata.payload, i, userdata.numFields);
        continue; /* Loop/switch isn't completed */
_L8:
        userdata.payloadStr = decodeGsmDcs(userdata.payload, i, userdata.numFields, userdata.msgType);
        if(true) goto _L10; else goto _L9
_L9:
    }

    private static boolean decodeUserResponseCode(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.userResponseCode = bitwiseinputstream.read(8);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("USER_RESPONSE_CODE decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.userResponseCodeSet = flag;
        return flag;
    }

    private static String decodeUtf16(byte abyte0[], int i, int j)
        throws CodingException
    {
        return decodeCharset(abyte0, i, j - (i + i % 2) / 2, 2, "utf-16be");
    }

    private static String decodeUtf8(byte abyte0[], int i, int j)
        throws CodingException
    {
        return decodeCharset(abyte0, i, j, 1, "UTF-8");
    }

    private static boolean decodeValidityAbs(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 48)
        {
            j = i - 48;
            flag = true;
            bearerdata.validityPeriodAbsolute = TimeStamp.fromByteArray(bitwiseinputstream.readByteArray(48));
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("VALIDITY_PERIOD_ABSOLUTE decode ");
            if(flag)
                bearerdata = "succeeded";
            else
                bearerdata = "failed";
            Rlog.d("BearerData", stringbuilder.append(bearerdata).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        return flag;
    }

    private static boolean decodeValidityRel(BearerData bearerdata, BitwiseInputStream bitwiseinputstream)
        throws com.android.internal.util.BitwiseInputStream.AccessException
    {
        boolean flag = false;
        int i = bitwiseinputstream.read(8) * 8;
        int j = i;
        if(i >= 8)
        {
            j = i - 8;
            flag = true;
            bearerdata.deferredDeliveryTimeRelative = bitwiseinputstream.read(8);
        }
        if(!flag || j > 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("VALIDITY_PERIOD_RELATIVE decode ");
            String s;
            if(flag)
                s = "succeeded";
            else
                s = "failed";
            Rlog.d("BearerData", stringbuilder.append(s).append(" (extra bits = ").append(j).append(")").toString());
        }
        bitwiseinputstream.skip(j);
        bearerdata.deferredDeliveryTimeRelativeSet = flag;
        return flag;
    }

    public static byte[] encode(BearerData bearerdata)
    {
        boolean flag = true;
        BitwiseOutputStream bitwiseoutputstream;
        if(bearerdata.userData != null)
        {
            if(bearerdata.userData.userDataHeader == null)
                flag = false;
        } else
        {
            flag = false;
        }
        bearerdata.hasUserDataHeader = flag;
        bitwiseoutputstream = JVM INSTR new #775 <Class BitwiseOutputStream>;
        bitwiseoutputstream.BitwiseOutputStream(200);
        bitwiseoutputstream.write(8, 0);
        encodeMessageId(bearerdata, bitwiseoutputstream);
        if(bearerdata.userData != null)
        {
            bitwiseoutputstream.write(8, 1);
            encodeUserData(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.callbackNumber != null)
        {
            bitwiseoutputstream.write(8, 14);
            encodeCallbackNumber(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.userAckReq || bearerdata.deliveryAckReq || bearerdata.readAckReq || bearerdata.reportReq)
        {
            bitwiseoutputstream.write(8, 10);
            encodeReplyOption(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.numberOfMessages != 0)
        {
            bitwiseoutputstream.write(8, 11);
            encodeMsgCount(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.validityPeriodRelativeSet)
        {
            bitwiseoutputstream.write(8, 5);
            encodeValidityPeriodRel(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.privacyIndicatorSet)
        {
            bitwiseoutputstream.write(8, 9);
            encodePrivacyIndicator(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.languageIndicatorSet)
        {
            bitwiseoutputstream.write(8, 13);
            encodeLanguageIndicator(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.displayModeSet)
        {
            bitwiseoutputstream.write(8, 15);
            encodeDisplayMode(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.priorityIndicatorSet)
        {
            bitwiseoutputstream.write(8, 8);
            encodePriorityIndicator(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.alertIndicatorSet)
        {
            bitwiseoutputstream.write(8, 12);
            encodeMsgDeliveryAlert(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.messageStatusSet)
        {
            bitwiseoutputstream.write(8, 20);
            encodeMsgStatus(bearerdata, bitwiseoutputstream);
        }
        if(bearerdata.serviceCategoryProgramResults != null)
        {
            bitwiseoutputstream.write(8, 19);
            encodeScpResults(bearerdata, bitwiseoutputstream);
        }
        bearerdata = bitwiseoutputstream.toByteArray();
        return bearerdata;
        bearerdata;
        Rlog.e("BearerData", (new StringBuilder()).append("BearerData encode failed: ").append(bearerdata).toString());
_L2:
        return null;
        bearerdata;
        Rlog.e("BearerData", (new StringBuilder()).append("BearerData encode failed: ").append(bearerdata).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static void encode16bitEms(UserData userdata, byte abyte0[])
        throws CodingException
    {
        byte abyte1[] = encodeUtf16(userdata.payloadStr);
        int i = abyte0.length + 1;
        int j = (i + 1) / 2;
        int k = abyte1.length / 2;
        userdata.msgEncoding = 4;
        userdata.msgEncodingSet = true;
        userdata.numFields = j + k;
        userdata.payload = new byte[userdata.numFields * 2];
        userdata.payload[0] = (byte)abyte0.length;
        System.arraycopy(abyte0, 0, userdata.payload, 1, abyte0.length);
        System.arraycopy(abyte1, 0, userdata.payload, i, abyte1.length);
    }

    private static byte[] encode7bitAscii(String s, boolean flag)
        throws CodingException
    {
        Object obj;
        int k;
        int i;
        int j;
        CodingException codingexception;
        try
        {
            obj = JVM INSTR new #775 <Class BitwiseOutputStream>;
            ((BitwiseOutputStream) (obj)).BitwiseOutputStream(s.length());
            i = s.length();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new CodingException((new StringBuilder()).append("7bit ASCII encode failed: ").append(s).toString());
        }
        j = 0;
        if(j >= i)
            break MISSING_BLOCK_LABEL_151;
        k = UserData.charToAscii.get(s.charAt(j), -1);
        if(k != -1)
            break MISSING_BLOCK_LABEL_140;
        if(!flag)
            break; /* Loop/switch isn't completed */
        ((BitwiseOutputStream) (obj)).write(7, 32);
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_20;
_L1:
        codingexception = JVM INSTR new #6   <Class BearerData$CodingException>;
        obj = JVM INSTR new #282 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        codingexception.CodingException(((StringBuilder) (obj)).append("cannot ASCII encode (").append(s.charAt(j)).append(")").toString());
        throw codingexception;
        ((BitwiseOutputStream) (obj)).write(7, k);
          goto _L3
        s = ((BitwiseOutputStream) (obj)).toByteArray();
        return s;
    }

    private static void encode7bitAsciiEms(UserData userdata, byte abyte0[], boolean flag)
        throws CodingException
    {
        int i;
        int j;
        int k;
        BitwiseOutputStream bitwiseoutputstream;
        String s;
        int l;
        int i1;
        try
        {
            Rlog.d("BearerData", "encode7bitAsciiEms");
            i = abyte0.length + 1;
            j = (i * 8 + 6) / 7;
        }
        // Misplaced declaration of an exception variable
        catch(UserData userdata)
        {
            throw new CodingException((new StringBuilder()).append("7bit ASCII encode failed: ").append(userdata).toString());
        }
        k = j * 7 - i * 8;
        s = userdata.payloadStr;
        l = s.length();
        bitwiseoutputstream = JVM INSTR new #775 <Class BitwiseOutputStream>;
        if(k > 0)
            i1 = 1;
        else
            i1 = 0;
        bitwiseoutputstream.BitwiseOutputStream(i1 + l);
        bitwiseoutputstream.write(k, 0);
        i1 = 0;
_L2:
        if(i1 >= l)
            break MISSING_BLOCK_LABEL_224;
        k = UserData.charToAscii.get(s.charAt(i1), -1);
        if(k != -1)
            break MISSING_BLOCK_LABEL_212;
        if(!flag)
            break; /* Loop/switch isn't completed */
        bitwiseoutputstream.write(7, 32);
_L3:
        i1++;
        if(true) goto _L2; else goto _L1
_L1:
        userdata = JVM INSTR new #6   <Class BearerData$CodingException>;
        abyte0 = JVM INSTR new #282 <Class StringBuilder>;
        abyte0.StringBuilder();
        userdata.CodingException(abyte0.append("cannot ASCII encode (").append(s.charAt(i1)).append(")").toString());
        throw userdata;
        bitwiseoutputstream.write(7, k);
          goto _L3
        byte abyte1[] = bitwiseoutputstream.toByteArray();
        userdata.msgEncoding = 2;
        userdata.msgEncodingSet = true;
        userdata.numFields = userdata.payloadStr.length() + j;
        userdata.payload = new byte[abyte1.length + i];
        userdata.payload[0] = (byte)abyte0.length;
        System.arraycopy(abyte0, 0, userdata.payload, 1, abyte0.length);
        System.arraycopy(abyte1, 0, userdata.payload, i, abyte1.length);
        return;
    }

    private static void encode7bitEms(UserData userdata, byte abyte0[], boolean flag)
        throws CodingException
    {
        int i = ((abyte0.length + 1) * 8 + 6) / 7;
        Gsm7bitCodingResult gsm7bitcodingresult = encode7bitGsm(userdata.payloadStr, i, flag);
        userdata.msgEncoding = 9;
        userdata.msgEncodingSet = true;
        userdata.numFields = gsm7bitcodingresult.septets;
        userdata.payload = gsm7bitcodingresult.data;
        userdata.payload[0] = (byte)abyte0.length;
        System.arraycopy(abyte0, 0, userdata.payload, 1, abyte0.length);
    }

    private static Gsm7bitCodingResult encode7bitGsm(String s, int i, boolean flag)
        throws CodingException
    {
        try
        {
            byte abyte0[] = GsmAlphabet.stringToGsm7BitPacked(s, i, flag ^ true, 0, 0);
            s = JVM INSTR new #9   <Class BearerData$Gsm7bitCodingResult>;
            s.Gsm7bitCodingResult(null);
            s.data = new byte[abyte0.length - 1];
            System.arraycopy(abyte0, 1, ((Gsm7bitCodingResult) (s)).data, 0, abyte0.length - 1);
            s.septets = abyte0[0] & 0xff;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new CodingException((new StringBuilder()).append("7bit GSM encode failed: ").append(s).toString());
        }
        return s;
    }

    private static void encodeCallbackNumber(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException, CodingException
    {
        bearerdata = bearerdata.callbackNumber;
        encodeCdmaSmsAddress(bearerdata);
        int i = 9;
        int j;
        int k;
        int l;
        if(((CdmaSmsAddress) (bearerdata)).digitMode == 1)
        {
            i = 16;
            j = ((CdmaSmsAddress) (bearerdata)).numberOfDigits * 8;
        } else
        {
            j = ((CdmaSmsAddress) (bearerdata)).numberOfDigits * 4;
        }
        k = i + j;
        l = k / 8;
        if(k % 8 > 0)
            i = 1;
        else
            i = 0;
        i = l + i;
        k = i * 8 - k;
        bitwiseoutputstream.write(8, i);
        bitwiseoutputstream.write(1, ((CdmaSmsAddress) (bearerdata)).digitMode);
        if(((CdmaSmsAddress) (bearerdata)).digitMode == 1)
        {
            bitwiseoutputstream.write(3, ((CdmaSmsAddress) (bearerdata)).ton);
            bitwiseoutputstream.write(4, ((CdmaSmsAddress) (bearerdata)).numberPlan);
        }
        bitwiseoutputstream.write(8, ((CdmaSmsAddress) (bearerdata)).numberOfDigits);
        bitwiseoutputstream.writeByteArray(j, ((CdmaSmsAddress) (bearerdata)).origBytes);
        if(k > 0)
            bitwiseoutputstream.write(k, 0);
    }

    private static void encodeCdmaSmsAddress(CdmaSmsAddress cdmasmsaddress)
        throws CodingException
    {
        if(cdmasmsaddress.digitMode != 1)
            break MISSING_BLOCK_LABEL_35;
        cdmasmsaddress.origBytes = cdmasmsaddress.address.getBytes("US-ASCII");
_L1:
        return;
        cdmasmsaddress;
        throw new CodingException("invalid SMS address, cannot convert to ASCII");
        cdmasmsaddress.origBytes = encodeDtmfSmsAddress(cdmasmsaddress.address);
          goto _L1
    }

    private static void encodeDisplayMode(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        bitwiseoutputstream.write(2, bearerdata.displayMode);
        bitwiseoutputstream.skip(6);
    }

    private static byte[] encodeDtmfSmsAddress(String s)
    {
        int i = 0;
        int k = s.length();
        int l = k * 4;
        int j1 = l / 8;
        if(l % 8 > 0)
            i = 1;
        byte abyte0[] = new byte[j1 + i];
        j1 = 0;
        while(j1 < k) 
        {
            int j = s.charAt(j1);
            int i1;
            if(j >= 49 && j <= 57)
                j -= 48;
            else
            if(j == 48)
                j = 10;
            else
            if(j == 42)
                j = 11;
            else
            if(j == 35)
                j = 12;
            else
                return null;
            i1 = j1 / 2;
            abyte0[i1] = (byte)(abyte0[i1] | j << 4 - (j1 % 2) * 4);
            j1++;
        }
        return abyte0;
    }

    private static void encodeEmsUserDataPayload(UserData userdata)
        throws CodingException
    {
        byte abyte0[] = SmsHeader.toByteArray(userdata.userDataHeader);
        if(userdata.msgEncodingSet)
        {
            if(userdata.msgEncoding == 9)
                encode7bitEms(userdata, abyte0, true);
            else
            if(userdata.msgEncoding == 4)
                encode16bitEms(userdata, abyte0);
            else
            if(userdata.msgEncoding == 2)
                encode7bitAsciiEms(userdata, abyte0, true);
            else
                throw new CodingException((new StringBuilder()).append("unsupported EMS user data encoding (").append(userdata.msgEncoding).append(")").toString());
        } else
        {
            try
            {
                encode7bitEms(userdata, abyte0, false);
            }
            catch(CodingException codingexception)
            {
                encode16bitEms(userdata, abyte0);
            }
        }
    }

    private static void encodeLanguageIndicator(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        bitwiseoutputstream.write(8, bearerdata.language);
    }

    private static void encodeMessageId(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 3);
        bitwiseoutputstream.write(4, bearerdata.messageType);
        bitwiseoutputstream.write(8, bearerdata.messageId >> 8);
        bitwiseoutputstream.write(8, bearerdata.messageId);
        int i;
        if(bearerdata.hasUserDataHeader)
            i = 1;
        else
            i = 0;
        bitwiseoutputstream.write(1, i);
        bitwiseoutputstream.skip(3);
    }

    private static void encodeMsgCount(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        bitwiseoutputstream.write(8, bearerdata.numberOfMessages);
    }

    private static void encodeMsgDeliveryAlert(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        bitwiseoutputstream.write(2, bearerdata.alert);
        bitwiseoutputstream.skip(6);
    }

    private static void encodeMsgStatus(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        bitwiseoutputstream.write(2, bearerdata.errorClass);
        bitwiseoutputstream.write(6, bearerdata.messageStatus);
    }

    private static void encodePriorityIndicator(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        bitwiseoutputstream.write(2, bearerdata.priority);
        bitwiseoutputstream.skip(6);
    }

    private static void encodePrivacyIndicator(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        bitwiseoutputstream.write(2, bearerdata.privacy);
        bitwiseoutputstream.skip(6);
    }

    private static void encodeReplyOption(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        int i;
        if(bearerdata.userAckReq)
            i = 1;
        else
            i = 0;
        bitwiseoutputstream.write(1, i);
        if(bearerdata.deliveryAckReq)
            i = 1;
        else
            i = 0;
        bitwiseoutputstream.write(1, i);
        if(bearerdata.readAckReq)
            i = 1;
        else
            i = 0;
        bitwiseoutputstream.write(1, i);
        if(bearerdata.reportReq)
            i = 1;
        else
            i = 0;
        bitwiseoutputstream.write(1, i);
        bitwiseoutputstream.write(4, 0);
    }

    private static void encodeScpResults(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bearerdata = bearerdata.serviceCategoryProgramResults;
        bitwiseoutputstream.write(8, bearerdata.size() * 4);
        for(bearerdata = bearerdata.iterator(); bearerdata.hasNext(); bitwiseoutputstream.skip(4))
        {
            CdmaSmsCbProgramResults cdmasmscbprogramresults = (CdmaSmsCbProgramResults)bearerdata.next();
            int i = cdmasmscbprogramresults.getCategory();
            bitwiseoutputstream.write(8, i >> 8);
            bitwiseoutputstream.write(8, i);
            bitwiseoutputstream.write(8, cdmasmscbprogramresults.getLanguage());
            bitwiseoutputstream.write(4, cdmasmscbprogramresults.getCategoryResult());
        }

    }

    private static byte[] encodeShiftJis(String s)
        throws CodingException
    {
        try
        {
            s = s.getBytes("Shift_JIS");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new CodingException((new StringBuilder()).append("Shift-JIS encode failed: ").append(s).toString());
        }
        return s;
    }

    private static void encodeUserData(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException, CodingException
    {
        int i;
        int l;
label0:
        {
            encodeUserDataPayload(bearerdata.userData);
            boolean flag;
            if(bearerdata.userData.userDataHeader != null)
                flag = true;
            else
                flag = false;
            bearerdata.hasUserDataHeader = flag;
            if(bearerdata.userData.payload.length > 140)
                throw new CodingException((new StringBuilder()).append("encoded user data too large (").append(bearerdata.userData.payload.length).append(" > ").append(140).append(" bytes)").toString());
            i = bearerdata.userData.payload.length * 8 - bearerdata.userData.paddingBits;
            int j = i + 13;
            if(bearerdata.userData.msgEncoding != 1)
            {
                l = j;
                if(bearerdata.userData.msgEncoding != 10)
                    break label0;
            }
            l = j + 8;
        }
        int i1 = l / 8;
        int k;
        if(l % 8 > 0)
            k = 1;
        else
            k = 0;
        k = i1 + k;
        l = k * 8 - l;
        bitwiseoutputstream.write(8, k);
        bitwiseoutputstream.write(5, bearerdata.userData.msgEncoding);
        if(bearerdata.userData.msgEncoding == 1 || bearerdata.userData.msgEncoding == 10)
            bitwiseoutputstream.write(8, bearerdata.userData.msgType);
        bitwiseoutputstream.write(8, bearerdata.userData.numFields);
        bitwiseoutputstream.writeByteArray(i, bearerdata.userData.payload);
        if(l > 0)
            bitwiseoutputstream.write(l, 0);
    }

    private static void encodeUserDataPayload(UserData userdata)
        throws CodingException
    {
        if(userdata.payloadStr == null && userdata.msgEncoding != 0)
        {
            Rlog.e("BearerData", "user data with null payloadStr");
            userdata.payloadStr = "";
        }
        if(userdata.userDataHeader != null)
        {
            encodeEmsUserDataPayload(userdata);
            return;
        }
        if(!userdata.msgEncodingSet) goto _L2; else goto _L1
_L1:
        if(userdata.msgEncoding == 0)
        {
            if(userdata.payload == null)
            {
                Rlog.e("BearerData", "user data with octet encoding but null payload");
                userdata.payload = new byte[0];
                userdata.numFields = 0;
            } else
            {
                userdata.numFields = userdata.payload.length;
            }
        } else
        {
            if(userdata.payloadStr == null)
            {
                Rlog.e("BearerData", "non-octet user data with null payloadStr");
                userdata.payloadStr = "";
            }
            if(userdata.msgEncoding == 9)
            {
                Gsm7bitCodingResult gsm7bitcodingresult = encode7bitGsm(userdata.payloadStr, 0, true);
                userdata.payload = gsm7bitcodingresult.data;
                userdata.numFields = gsm7bitcodingresult.septets;
            } else
            if(userdata.msgEncoding == 2)
            {
                userdata.payload = encode7bitAscii(userdata.payloadStr, true);
                userdata.numFields = userdata.payloadStr.length();
            } else
            if(userdata.msgEncoding == 4)
            {
                userdata.payload = encodeUtf16(userdata.payloadStr);
                userdata.numFields = userdata.payloadStr.length();
            } else
            if(userdata.msgEncoding == 5)
            {
                userdata.payload = encodeShiftJis(userdata.payloadStr);
                userdata.numFields = userdata.payload.length;
            } else
            {
                throw new CodingException((new StringBuilder()).append("unsupported user data encoding (").append(userdata.msgEncoding).append(")").toString());
            }
        }
_L4:
        return;
_L2:
        try
        {
            userdata.payload = encode7bitAscii(userdata.payloadStr, false);
            userdata.msgEncoding = 2;
        }
        catch(CodingException codingexception)
        {
            userdata.payload = encodeUtf16(userdata.payloadStr);
            userdata.msgEncoding = 4;
        }
        userdata.numFields = userdata.payloadStr.length();
        userdata.msgEncodingSet = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static byte[] encodeUtf16(String s)
        throws CodingException
    {
        try
        {
            s = s.getBytes("utf-16be");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new CodingException((new StringBuilder()).append("UTF-16 encode failed: ").append(s).toString());
        }
        return s;
    }

    private static void encodeValidityPeriodRel(BearerData bearerdata, BitwiseOutputStream bitwiseoutputstream)
        throws com.android.internal.util.BitwiseOutputStream.AccessException
    {
        bitwiseoutputstream.write(8, 1);
        bitwiseoutputstream.write(8, bearerdata.validityPeriodRelative);
    }

    private static int getBitsForNumFields(int i, int j)
        throws CodingException
    {
        switch(i)
        {
        case 1: // '\001'
        default:
            throw new CodingException((new StringBuilder()).append("unsupported message encoding (").append(i).append(')').toString());

        case 0: // '\0'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            return j * 8;

        case 2: // '\002'
        case 3: // '\003'
        case 9: // '\t'
            return j * 7;

        case 4: // '\004'
            return j * 16;
        }
    }

    private static String getLanguageCodeForValue(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 1: // '\001'
            return "en";

        case 2: // '\002'
            return "fr";

        case 3: // '\003'
            return "es";

        case 4: // '\004'
            return "ja";

        case 5: // '\005'
            return "ko";

        case 6: // '\006'
            return "zh";

        case 7: // '\007'
            return "he";
        }
    }

    private static boolean isCmasAlertCategory(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 4096)
        {
            flag1 = flag;
            if(i <= 4351)
                flag1 = true;
        }
        return flag1;
    }

    private static int serviceCategoryToCmasMessageClass(int i)
    {
        switch(i)
        {
        default:
            return -1;

        case 4096: 
            return 0;

        case 4097: 
            return 1;

        case 4098: 
            return 2;

        case 4099: 
            return 3;

        case 4100: 
            return 4;
        }
    }

    public String getLanguage()
    {
        return getLanguageCodeForValue(language);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("BearerData ");
        stringbuilder.append("{ messageType=").append(messageType);
        stringbuilder.append(", messageId=").append(messageId);
        StringBuilder stringbuilder1 = stringbuilder.append(", priority=");
        Object obj;
        if(priorityIndicatorSet)
            obj = Integer.valueOf(priority);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", privacy=");
        if(privacyIndicatorSet)
            obj = Integer.valueOf(privacy);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", alert=");
        if(alertIndicatorSet)
            obj = Integer.valueOf(alert);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", displayMode=");
        if(displayModeSet)
            obj = Integer.valueOf(displayMode);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", language=");
        if(languageIndicatorSet)
            obj = Integer.valueOf(language);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", errorClass=");
        if(messageStatusSet)
            obj = Integer.valueOf(errorClass);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", msgStatus=");
        if(messageStatusSet)
            obj = Integer.valueOf(messageStatus);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", msgCenterTimeStamp=");
        if(msgCenterTimeStamp != null)
            obj = msgCenterTimeStamp;
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", validityPeriodAbsolute=");
        if(validityPeriodAbsolute != null)
            obj = validityPeriodAbsolute;
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", validityPeriodRelative=");
        if(validityPeriodRelativeSet)
            obj = Integer.valueOf(validityPeriodRelative);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", deferredDeliveryTimeAbsolute=");
        if(deferredDeliveryTimeAbsolute != null)
            obj = deferredDeliveryTimeAbsolute;
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder1 = stringbuilder.append(", deferredDeliveryTimeRelative=");
        if(deferredDeliveryTimeRelativeSet)
            obj = Integer.valueOf(deferredDeliveryTimeRelative);
        else
            obj = "unset";
        stringbuilder1.append(obj);
        stringbuilder.append(", userAckReq=").append(userAckReq);
        stringbuilder.append(", deliveryAckReq=").append(deliveryAckReq);
        stringbuilder.append(", readAckReq=").append(readAckReq);
        stringbuilder.append(", reportReq=").append(reportReq);
        stringbuilder.append(", numberOfMessages=").append(numberOfMessages);
        stringbuilder.append(", callbackNumber=").append(Rlog.pii("BearerData", callbackNumber));
        stringbuilder.append(", depositIndex=").append(depositIndex);
        stringbuilder.append(", hasUserDataHeader=").append(hasUserDataHeader);
        stringbuilder.append(", userData=").append(userData);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public static final int ALERT_DEFAULT = 0;
    public static final int ALERT_HIGH_PRIO = 3;
    public static final int ALERT_LOW_PRIO = 1;
    public static final int ALERT_MEDIUM_PRIO = 2;
    public static final int DISPLAY_MODE_DEFAULT = 1;
    public static final int DISPLAY_MODE_IMMEDIATE = 0;
    public static final int DISPLAY_MODE_USER = 2;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_PERMANENT = 3;
    public static final int ERROR_TEMPORARY = 2;
    public static final int ERROR_UNDEFINED = 255;
    public static final int LANGUAGE_CHINESE = 6;
    public static final int LANGUAGE_ENGLISH = 1;
    public static final int LANGUAGE_FRENCH = 2;
    public static final int LANGUAGE_HEBREW = 7;
    public static final int LANGUAGE_JAPANESE = 4;
    public static final int LANGUAGE_KOREAN = 5;
    public static final int LANGUAGE_SPANISH = 3;
    public static final int LANGUAGE_UNKNOWN = 0;
    private static final String LOG_TAG = "BearerData";
    public static final int MESSAGE_TYPE_CANCELLATION = 3;
    public static final int MESSAGE_TYPE_DELIVER = 1;
    public static final int MESSAGE_TYPE_DELIVERY_ACK = 4;
    public static final int MESSAGE_TYPE_DELIVER_REPORT = 7;
    public static final int MESSAGE_TYPE_READ_ACK = 6;
    public static final int MESSAGE_TYPE_SUBMIT = 2;
    public static final int MESSAGE_TYPE_SUBMIT_REPORT = 8;
    public static final int MESSAGE_TYPE_USER_ACK = 5;
    public static final int PRIORITY_EMERGENCY = 3;
    public static final int PRIORITY_INTERACTIVE = 1;
    public static final int PRIORITY_NORMAL = 0;
    public static final int PRIORITY_URGENT = 2;
    public static final int PRIVACY_CONFIDENTIAL = 2;
    public static final int PRIVACY_NOT_RESTRICTED = 0;
    public static final int PRIVACY_RESTRICTED = 1;
    public static final int PRIVACY_SECRET = 3;
    public static final int RELATIVE_TIME_DAYS_LIMIT = 196;
    public static final int RELATIVE_TIME_HOURS_LIMIT = 167;
    public static final int RELATIVE_TIME_INDEFINITE = 245;
    public static final int RELATIVE_TIME_MINS_LIMIT = 143;
    public static final int RELATIVE_TIME_MOBILE_INACTIVE = 247;
    public static final int RELATIVE_TIME_NOW = 246;
    public static final int RELATIVE_TIME_RESERVED = 248;
    public static final int RELATIVE_TIME_WEEKS_LIMIT = 244;
    public static final int STATUS_ACCEPTED = 0;
    public static final int STATUS_BLOCKED_DESTINATION = 7;
    public static final int STATUS_CANCELLED = 3;
    public static final int STATUS_CANCEL_FAILED = 6;
    public static final int STATUS_DELIVERED = 2;
    public static final int STATUS_DEPOSITED_TO_INTERNET = 1;
    public static final int STATUS_DUPLICATE_MESSAGE = 9;
    public static final int STATUS_INVALID_DESTINATION = 10;
    public static final int STATUS_MESSAGE_EXPIRED = 13;
    public static final int STATUS_NETWORK_CONGESTION = 4;
    public static final int STATUS_NETWORK_ERROR = 5;
    public static final int STATUS_TEXT_TOO_LONG = 8;
    public static final int STATUS_UNDEFINED = 255;
    public static final int STATUS_UNKNOWN_ERROR = 31;
    private static final byte SUBPARAM_ALERT_ON_MESSAGE_DELIVERY = 12;
    private static final byte SUBPARAM_CALLBACK_NUMBER = 14;
    private static final byte SUBPARAM_DEFERRED_DELIVERY_TIME_ABSOLUTE = 6;
    private static final byte SUBPARAM_DEFERRED_DELIVERY_TIME_RELATIVE = 7;
    private static final byte SUBPARAM_ID_LAST_DEFINED = 23;
    private static final byte SUBPARAM_LANGUAGE_INDICATOR = 13;
    private static final byte SUBPARAM_MESSAGE_CENTER_TIME_STAMP = 3;
    private static final byte SUBPARAM_MESSAGE_DEPOSIT_INDEX = 17;
    private static final byte SUBPARAM_MESSAGE_DISPLAY_MODE = 15;
    private static final byte SUBPARAM_MESSAGE_IDENTIFIER = 0;
    private static final byte SUBPARAM_MESSAGE_STATUS = 20;
    private static final byte SUBPARAM_NUMBER_OF_MESSAGES = 11;
    private static final byte SUBPARAM_PRIORITY_INDICATOR = 8;
    private static final byte SUBPARAM_PRIVACY_INDICATOR = 9;
    private static final byte SUBPARAM_REPLY_OPTION = 10;
    private static final byte SUBPARAM_SERVICE_CATEGORY_PROGRAM_DATA = 18;
    private static final byte SUBPARAM_SERVICE_CATEGORY_PROGRAM_RESULTS = 19;
    private static final byte SUBPARAM_USER_DATA = 1;
    private static final byte SUBPARAM_USER_RESPONSE_CODE = 2;
    private static final byte SUBPARAM_VALIDITY_PERIOD_ABSOLUTE = 4;
    private static final byte SUBPARAM_VALIDITY_PERIOD_RELATIVE = 5;
    public int alert;
    public boolean alertIndicatorSet;
    public CdmaSmsAddress callbackNumber;
    public SmsCbCmasInfo cmasWarningInfo;
    public TimeStamp deferredDeliveryTimeAbsolute;
    public int deferredDeliveryTimeRelative;
    public boolean deferredDeliveryTimeRelativeSet;
    public boolean deliveryAckReq;
    public int depositIndex;
    public int displayMode;
    public boolean displayModeSet;
    public int errorClass;
    public boolean hasUserDataHeader;
    public int language;
    public boolean languageIndicatorSet;
    public int messageId;
    public int messageStatus;
    public boolean messageStatusSet;
    public int messageType;
    public TimeStamp msgCenterTimeStamp;
    public int numberOfMessages;
    public int priority;
    public boolean priorityIndicatorSet;
    public int privacy;
    public boolean privacyIndicatorSet;
    public boolean readAckReq;
    public boolean reportReq;
    public ArrayList serviceCategoryProgramData;
    public ArrayList serviceCategoryProgramResults;
    public boolean userAckReq;
    public UserData userData;
    public int userResponseCode;
    public boolean userResponseCodeSet;
    public TimeStamp validityPeriodAbsolute;
    public int validityPeriodRelative;
    public boolean validityPeriodRelativeSet;
}
