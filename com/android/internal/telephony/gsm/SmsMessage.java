// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.gsm;

import android.content.res.Resources;
import android.telephony.PhoneNumberUtils;
import android.telephony.Rlog;
import android.text.TextUtils;
import android.text.format.Time;
import com.android.internal.telephony.*;
import com.android.internal.telephony.uicc.IccUtils;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.android.internal.telephony.gsm:
//            GsmSmsAddress

public class SmsMessage extends SmsMessageBase
{
    private static class PduParser
    {

        int constructUserData(boolean flag, boolean flag1)
        {
            boolean flag2 = false;
            int i = mCur;
            byte abyte0[] = mPdu;
            int j = i + 1;
            int k = abyte0[i] & 0xff;
            int l = 0;
            i = 0;
            if(flag)
            {
                byte abyte1[] = mPdu;
                i = j + 1;
                j = abyte1[j] & 0xff;
                abyte1 = new byte[j];
                System.arraycopy(mPdu, i, abyte1, 0, j);
                mUserDataHeader = SmsHeader.fromByteArray(abyte1);
                l = i + j;
                int i1 = (j + 1) * 8;
                int j1 = i1 / 7;
                if(i1 % 7 > 0)
                    i = 1;
                else
                    i = 0;
                j1 += i;
                mUserDataSeptetPadding = j1 * 7 - i1;
                i = j;
                j = l;
                l = j1;
            }
            if(flag1)
            {
                i = mPdu.length - j;
            } else
            {
                int k1;
                if(flag)
                    i++;
                else
                    i = 0;
                k1 = k - i;
                i = k1;
                if(k1 < 0)
                    i = 0;
            }
            mUserData = new byte[i];
            System.arraycopy(mPdu, j, mUserData, 0, mUserData.length);
            mCur = j;
            if(flag1)
            {
                i = k - l;
                if(i < 0)
                    i = ((flag2) ? 1 : 0);
                return i;
            } else
            {
                return mUserData.length;
            }
        }

        GsmSmsAddress getAddress()
        {
            int i = ((mPdu[mCur] & 0xff) + 1) / 2 + 2;
            GsmSmsAddress gsmsmsaddress;
            try
            {
                gsmsmsaddress = new GsmSmsAddress(mPdu, mCur, i);
            }
            catch(ParseException parseexception)
            {
                throw new RuntimeException(parseexception.getMessage());
            }
            mCur = mCur + i;
            return gsmsmsaddress;
        }

        int getByte()
        {
            byte abyte0[] = mPdu;
            int i = mCur;
            mCur = i + 1;
            return abyte0[i] & 0xff;
        }

        String getSCAddress()
        {
            int i = getByte();
            String s;
            if(i == 0)
                s = null;
            else
                try
                {
                    s = PhoneNumberUtils.calledPartyBCDToString(mPdu, mCur, i);
                }
                catch(RuntimeException runtimeexception)
                {
                    Rlog.d("SmsMessage", "invalid SC address: ", runtimeexception);
                    runtimeexception = null;
                }
            mCur = mCur + i;
            return s;
        }

        long getSCTimestampMillis()
        {
            byte abyte0[] = mPdu;
            int i = mCur;
            mCur = i + 1;
            int j = IccUtils.gsmBcdByteToInt(abyte0[i]);
            abyte0 = mPdu;
            i = mCur;
            mCur = i + 1;
            int k = IccUtils.gsmBcdByteToInt(abyte0[i]);
            abyte0 = mPdu;
            i = mCur;
            mCur = i + 1;
            int l = IccUtils.gsmBcdByteToInt(abyte0[i]);
            abyte0 = mPdu;
            i = mCur;
            mCur = i + 1;
            int i1 = IccUtils.gsmBcdByteToInt(abyte0[i]);
            abyte0 = mPdu;
            i = mCur;
            mCur = i + 1;
            int j1 = IccUtils.gsmBcdByteToInt(abyte0[i]);
            abyte0 = mPdu;
            i = mCur;
            mCur = i + 1;
            int k1 = IccUtils.gsmBcdByteToInt(abyte0[i]);
            abyte0 = mPdu;
            i = mCur;
            mCur = i + 1;
            byte byte0 = abyte0[i];
            i = IccUtils.gsmBcdByteToInt((byte)(byte0 & -9));
            Time time;
            if((byte0 & 8) != 0)
                i = -i;
            time = new Time("UTC");
            if(j >= 90)
                j += 1900;
            else
                j += 2000;
            time.year = j;
            time.month = k - 1;
            time.monthDay = l;
            time.hour = i1;
            time.minute = j1;
            time.second = k1;
            return time.toMillis(true) - (long)(i * 15 * 60 * 1000);
        }

        byte[] getUserData()
        {
            return mUserData;
        }

        String getUserDataGSM7Bit(int i, int j, int k)
        {
            String s = GsmAlphabet.gsm7BitPackedToString(mPdu, mCur, i, mUserDataSeptetPadding, j, k);
            mCur = mCur + (i * 7) / 8;
            return s;
        }

        String getUserDataGSM8bit(int i)
        {
            String s = GsmAlphabet.gsm8BitUnpackedToString(mPdu, mCur, i);
            mCur = mCur + i;
            return s;
        }

        SmsHeader getUserDataHeader()
        {
            return mUserDataHeader;
        }

        String getUserDataKSC5601(int i)
        {
            String s;
            try
            {
                s = JVM INSTR new #141 <Class String>;
                s.String(mPdu, mCur, i, "KSC5601");
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                s = "";
                Rlog.e("SmsMessage", "implausible UnsupportedEncodingException", unsupportedencodingexception);
            }
            mCur = mCur + i;
            return s;
        }

        String getUserDataUCS2(int i)
        {
            String s;
            try
            {
                s = JVM INSTR new #141 <Class String>;
                s.String(mPdu, mCur, i, "utf-16");
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                s = "";
                Rlog.e("SmsMessage", "implausible UnsupportedEncodingException", unsupportedencodingexception);
            }
            mCur = mCur + i;
            return s;
        }

        boolean moreDataPresent()
        {
            boolean flag;
            if(mPdu.length > mCur)
                flag = true;
            else
                flag = false;
            return flag;
        }

        int mCur;
        byte mPdu[];
        byte mUserData[];
        SmsHeader mUserDataHeader;
        int mUserDataSeptetPadding;

        PduParser(byte abyte0[])
        {
            mPdu = abyte0;
            mCur = 0;
            mUserDataSeptetPadding = 0;
        }
    }

    public static class SubmitPdu extends com.android.internal.telephony.SmsMessageBase.SubmitPduBase
    {

        public SubmitPdu()
        {
        }
    }


    public SmsMessage()
    {
        mReplyPathPresent = false;
        mIsStatusReportMessage = false;
        mVoiceMailCount = 0;
    }

    public static com.android.internal.telephony.GsmAlphabet.TextEncodingDetails calculateLength(CharSequence charsequence, boolean flag)
    {
        String s = null;
        if(Resources.getSystem().getBoolean(0x11200a2))
            s = Sms7BitEncodingTranslator.translate(charsequence);
        Object obj = s;
        if(TextUtils.isEmpty(s))
            obj = charsequence;
        charsequence = GsmAlphabet.countGsmSeptets(((CharSequence) (obj)), flag);
        if(charsequence == null)
            return SmsMessageBase.calcUnicodeEncodingDetails(((CharSequence) (obj)));
        else
            return charsequence;
    }

    public static SmsMessage createFromEfRecord(int i, byte abyte0[])
    {
        SmsMessage smsmessage;
        byte abyte1[];
        try
        {
            smsmessage = JVM INSTR new #2   <Class SmsMessage>;
            smsmessage.SmsMessage();
            smsmessage.mIndexOnIcc = i;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Rlog.e("SmsMessage", "SMS PDU parsing failed: ", abyte0);
            return null;
        }
        if((abyte0[0] & 1) != 0)
            break MISSING_BLOCK_LABEL_31;
        Rlog.w("SmsMessage", "SMS parsing failed: Trying to parse a free record");
        return null;
        smsmessage.mStatusOnIcc = abyte0[0] & 7;
        i = abyte0.length - 1;
        abyte1 = new byte[i];
        System.arraycopy(abyte0, 1, abyte1, 0, i);
        smsmessage.parsePdu(abyte1);
        return smsmessage;
    }

    public static SmsMessage createFromPdu(byte abyte0[])
    {
        SmsMessage smsmessage;
        try
        {
            smsmessage = JVM INSTR new #2   <Class SmsMessage>;
            smsmessage.SmsMessage();
            smsmessage.parsePdu(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Rlog.e("SmsMessage", "SMS PDU parsing failed: ", abyte0);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Rlog.e("SmsMessage", "SMS PDU parsing failed with out of memory: ", abyte0);
            return null;
        }
        return smsmessage;
    }

    private static byte[] encodeUCS2(String s, byte abyte0[])
        throws UnsupportedEncodingException
    {
        byte abyte1[] = s.getBytes("utf-16be");
        if(abyte0 != null)
        {
            s = new byte[abyte0.length + abyte1.length + 1];
            s[0] = (byte)abyte0.length;
            System.arraycopy(abyte0, 0, s, 1, abyte0.length);
            System.arraycopy(abyte1, 0, s, abyte0.length + 1, abyte1.length);
        } else
        {
            s = abyte1;
        }
        abyte0 = new byte[s.length + 1];
        abyte0[0] = (byte)(s.length & 0xff);
        System.arraycopy(s, 0, abyte0, 1, s.length);
        return abyte0;
    }

    public static int getRelativeValidityPeriod(int i)
    {
        int j;
        j = -1;
        if(i < 5 || i > 0x9b0a0)
        {
            Rlog.e("SmsMessage", (new StringBuilder()).append("Invalid Validity Period").append(i).toString());
            return -1;
        }
        if(i > 720) goto _L2; else goto _L1
_L1:
        j = i / 5 - 1;
_L4:
        return j;
_L2:
        if(i <= 1440)
            j = (i - 720) / 30 + 143;
        else
        if(i <= 43200)
            j = i / 1440 + 166;
        else
        if(i <= 0x9b0a0)
            j = i / 10080 + 192;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, int i, byte abyte0[], boolean flag)
    {
        com.android.internal.telephony.SmsHeader.PortAddrs portaddrs = new com.android.internal.telephony.SmsHeader.PortAddrs();
        portaddrs.destPort = i;
        portaddrs.origPort = 0;
        portaddrs.areEightBits = false;
        Object obj = new SmsHeader();
        obj.portAddrs = portaddrs;
        byte abyte1[] = SmsHeader.toByteArray(((SmsHeader) (obj)));
        if(abyte0.length + abyte1.length + 1 > 140)
        {
            Rlog.e("SmsMessage", (new StringBuilder()).append("SMS data message may only contain ").append(140 - abyte1.length - 1).append(" bytes").toString());
            return null;
        }
        obj = new SubmitPdu();
        s = getSubmitPduHead(s, s1, (byte)65, flag, ((SubmitPdu) (obj)));
        if(s == null)
        {
            return ((SubmitPdu) (obj));
        } else
        {
            s.write(4);
            s.write(abyte0.length + abyte1.length + 1);
            s.write(abyte1.length);
            s.write(abyte1, 0, abyte1.length);
            s.write(abyte0, 0, abyte0.length);
            obj.encodedMessage = s.toByteArray();
            return ((SubmitPdu) (obj));
        }
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag)
    {
        return getSubmitPdu(s, s1, s2, flag, ((byte []) (null)));
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag, int i)
    {
        return getSubmitPdu(s, s1, s2, flag, null, 0, 0, 0, i);
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag, byte abyte0[])
    {
        return getSubmitPdu(s, s1, s2, flag, abyte0, 0, 0, 0);
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag, byte abyte0[], int i, int j, int k)
    {
        return getSubmitPdu(s, s1, s2, flag, abyte0, i, j, k, -1);
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag, byte abyte0[], int i, int j, int k, 
            int l)
    {
        byte abyte1[];
        int i1;
        int j1;
        int k1;
label0:
        {
            if(s2 == null || s1 == null)
                return null;
            abyte1 = abyte0;
            i1 = i;
            if(i != 0)
                break label0;
            abyte1 = calculateLength(s2, false);
            j1 = ((com.android.internal.telephony.GsmAlphabet.TextEncodingDetails) (abyte1)).codeUnitSize;
            i = ((com.android.internal.telephony.GsmAlphabet.TextEncodingDetails) (abyte1)).languageTable;
            k1 = ((com.android.internal.telephony.GsmAlphabet.TextEncodingDetails) (abyte1)).languageShiftTable;
            abyte1 = abyte0;
            i1 = j1;
            j = i;
            k = k1;
            if(j1 != 1)
                break label0;
            if(i == 0)
            {
                abyte1 = abyte0;
                i1 = j1;
                j = i;
                k = k1;
                if(k1 == 0)
                    break label0;
            }
            if(abyte0 == null)
                break MISSING_BLOCK_LABEL_304;
            SmsHeader smsheader = SmsHeader.fromByteArray(abyte0);
            if(smsheader.languageTable == i)
            {
                abyte1 = abyte0;
                i1 = j1;
                j = i;
                k = k1;
                if(smsheader.languageShiftTable == k1)
                    break label0;
            }
            Rlog.w("SmsMessage", (new StringBuilder()).append("Updating language table in SMS header: ").append(smsheader.languageTable).append(" -> ").append(i).append(", ").append(smsheader.languageShiftTable).append(" -> ").append(k1).toString());
            smsheader.languageTable = i;
            smsheader.languageShiftTable = k1;
            abyte1 = SmsHeader.toByteArray(smsheader);
            k = k1;
            j = i;
            i1 = j1;
        }
_L1:
        abyte0 = new SubmitPdu();
        i = 0;
        k1 = getRelativeValidityPeriod(l);
        if(k1 >= 0)
            i = 2;
        if(abyte1 != null)
            l = 64;
        else
            l = 0;
        s1 = getSubmitPduHead(s, s1, (byte)(l | (i << 3 | 1)), flag, abyte0);
        if(s1 == null)
            return abyte0;
        break MISSING_BLOCK_LABEL_355;
        abyte0 = new SmsHeader();
        abyte0.languageTable = i;
        abyte0.languageShiftTable = k1;
        abyte1 = SmsHeader.toByteArray(abyte0);
        i1 = j1;
        j = i;
        k = k1;
          goto _L1
        if(i1 != 1) goto _L3; else goto _L2
_L2:
        try
        {
            s = GsmAlphabet.stringToGsm7BitPackedWithHeader(s2, abyte1, j, k);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            try
            {
                s = encodeUCS2(s2, abyte1);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Rlog.e("SmsMessage", "Implausible UnsupportedEncodingException ", s);
                return null;
            }
            i1 = 3;
        }
        if(i1 != 1) goto _L5; else goto _L4
_L3:
        s = encodeUCS2(s2, abyte1);
        break MISSING_BLOCK_LABEL_372;
        s;
        Rlog.e("SmsMessage", "Implausible UnsupportedEncodingException ", s);
        return null;
_L4:
        if((s[0] & 0xff) > 160)
        {
            Rlog.e("SmsMessage", (new StringBuilder()).append("Message too long (").append(s[0] & 0xff).append(" septets)").toString());
            return null;
        }
        s1.write(0);
_L7:
        if(i == 2)
            s1.write(k1);
        s1.write(s, 0, s.length);
        abyte0.encodedMessage = s1.toByteArray();
        return abyte0;
_L5:
        if((s[0] & 0xff) > 140)
        {
            Rlog.e("SmsMessage", (new StringBuilder()).append("Message too long (").append(s[0] & 0xff).append(" bytes)").toString());
            return null;
        }
        s1.write(8);
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static ByteArrayOutputStream getSubmitPduHead(String s, String s1, byte byte0, boolean flag, SubmitPdu submitpdu)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(180);
        int i;
        if(s == null)
            submitpdu.encodedScAddress = null;
        else
            submitpdu.encodedScAddress = PhoneNumberUtils.networkPortionToCalledPartyBCDWithLength(s);
        i = byte0;
        if(flag)
            i = (byte)(byte0 | 0x20);
        bytearrayoutputstream.write(i);
        bytearrayoutputstream.write(0);
        s = PhoneNumberUtils.networkPortionToCalledPartyBCD(s1);
        if(s == null)
            return null;
        i = s.length;
        if((s[s.length - 1] & 0xf0) == 240)
            byte0 = 1;
        else
            byte0 = 0;
        bytearrayoutputstream.write((i - 1) * 2 - byte0);
        bytearrayoutputstream.write(s, 0, s.length);
        bytearrayoutputstream.write(0);
        return bytearrayoutputstream;
    }

    public static int getTPLayerLengthForPDU(String s)
    {
        return s.length() / 2 - Integer.parseInt(s.substring(0, 2), 16) - 1;
    }

    public static SmsMessage newFromCDS(byte abyte0[])
    {
        SmsMessage smsmessage;
        try
        {
            smsmessage = JVM INSTR new #2   <Class SmsMessage>;
            smsmessage.SmsMessage();
            smsmessage.parsePdu(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Rlog.e("SmsMessage", "CDS SMS PDU parsing failed: ", abyte0);
            return null;
        }
        return smsmessage;
    }

    public static SmsMessage newFromCMT(byte abyte0[])
    {
        SmsMessage smsmessage;
        try
        {
            smsmessage = JVM INSTR new #2   <Class SmsMessage>;
            smsmessage.SmsMessage();
            smsmessage.parsePdu(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Rlog.e("SmsMessage", "SMS PDU parsing failed: ", abyte0);
            return null;
        }
        return smsmessage;
    }

    private void parsePdu(byte abyte0[])
    {
        int i;
        mPdu = abyte0;
        abyte0 = new PduParser(abyte0);
        mScAddress = abyte0.getSCAddress();
        String s = mScAddress;
        i = abyte0.getByte();
        mMti = i & 3;
        mMti;
        JVM INSTR tableswitch 0 3: default 72
    //                   0 83
    //                   1 90
    //                   2 99
    //                   3 83;
           goto _L1 _L2 _L3 _L4 _L2
_L1:
        throw new RuntimeException("Unsupported message type");
_L2:
        parseSmsDeliver(abyte0, i);
_L6:
        return;
_L3:
        parseSmsSubmit(abyte0, i);
        continue; /* Loop/switch isn't completed */
_L4:
        parseSmsStatusReport(abyte0, i);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void parseSmsDeliver(PduParser pduparser, int i)
    {
        boolean flag;
        com.android.internal.telephony.SmsAddress smsaddress;
        if((i & 0x80) == 128)
            flag = true;
        else
            flag = false;
        mReplyPathPresent = flag;
        mOriginatingAddress = pduparser.getAddress();
        smsaddress = mOriginatingAddress;
        mProtocolIdentifier = pduparser.getByte();
        mDataCodingScheme = pduparser.getByte();
        mScTimeMillis = pduparser.getSCTimestampMillis();
        if((i & 0x40) == 64)
            flag = true;
        else
            flag = false;
        parseUserData(pduparser, flag);
    }

    private void parseSmsStatusReport(PduParser pduparser, int i)
    {
        mIsStatusReportMessage = true;
        mMessageRef = pduparser.getByte();
        mRecipientAddress = pduparser.getAddress();
        mScTimeMillis = pduparser.getSCTimestampMillis();
        pduparser.getSCTimestampMillis();
        mStatus = pduparser.getByte();
        if(pduparser.moreDataPresent())
        {
            int j = pduparser.getByte();
            for(int k = j; (k & 0x80) != 0; k = pduparser.getByte());
            if((j & 0x78) == 0)
            {
                if((j & 1) != 0)
                    mProtocolIdentifier = pduparser.getByte();
                if((j & 2) != 0)
                    mDataCodingScheme = pduparser.getByte();
                if((j & 4) != 0)
                {
                    boolean flag;
                    if((i & 0x40) == 64)
                        flag = true;
                    else
                        flag = false;
                    parseUserData(pduparser, flag);
                }
            }
        }
    }

    private void parseSmsSubmit(PduParser pduparser, int i)
    {
        boolean flag = false;
        if((i & 0x80) == 128)
            flag = true;
        mReplyPathPresent = flag;
        mMessageRef = pduparser.getByte();
        mRecipientAddress = pduparser.getAddress();
        com.android.internal.telephony.SmsAddress smsaddress = mRecipientAddress;
        mProtocolIdentifier = pduparser.getByte();
        mDataCodingScheme = pduparser.getByte();
        int j = i >> 3 & 3;
        if(j != 0)
            if(2 == j)
                j = 1;
            else
                j = 7;
        for(j = 0; j > 0; j--)
            pduparser.getByte();

        if((i & 0x40) == 64)
            flag = true;
        else
            flag = false;
        parseUserData(pduparser, flag);
    }

    private void parseUserData(PduParser pduparser, boolean flag)
    {
        boolean flag1;
        int i;
        flag1 = false;
        i = 0;
        if((mDataCodingScheme & 0x80) != 0) goto _L2; else goto _L1
_L1:
        int k;
        int i1;
        Iterator iterator;
        com.android.internal.telephony.SmsHeader.SpecialSmsMsg specialsmsmsg;
        if((mDataCodingScheme & 0x20) != 0)
            k = 1;
        else
            k = 0;
        if((mDataCodingScheme & 0x10) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(k == 0) goto _L4; else goto _L3
_L3:
        Rlog.w("SmsMessage", (new StringBuilder()).append("4 - Unsupported SMS data coding scheme (compression) ").append(mDataCodingScheme & 0xff).toString());
        k = i;
_L18:
        boolean flag2;
        boolean flag3;
        if(k == 1)
            flag2 = true;
        else
            flag2 = false;
        i1 = pduparser.constructUserData(flag, flag2);
        mUserData = pduparser.getUserData();
        mUserDataHeader = pduparser.getUserDataHeader();
        if(!flag || mUserDataHeader.specialSmsMsgList.size() == 0) goto _L6; else goto _L5
_L5:
        iterator = mUserDataHeader.specialSmsMsgList.iterator();
_L12:
        if(!iterator.hasNext()) goto _L6; else goto _L7
_L7:
        specialsmsmsg = (com.android.internal.telephony.SmsHeader.SpecialSmsMsg)iterator.next();
        i = specialsmsmsg.msgIndType & 0xff;
        if(i != 0 && i != 128) goto _L9; else goto _L8
_L8:
        mIsMwi = true;
        if(i != 128) goto _L11; else goto _L10
_L10:
        mMwiDontStore = false;
_L20:
        mVoiceMailCount = specialsmsmsg.msgCount & 0xff;
        if(mVoiceMailCount > 0)
            mMwiSense = true;
        else
            mMwiSense = false;
        Rlog.w("SmsMessage", (new StringBuilder()).append("MWI in TP-UDH for Vmail. Msg Ind = ").append(i).append(" Dont store = ").append(mMwiDontStore).append(" Vmail count = ").append(mVoiceMailCount).toString());
          goto _L12
_L4:
        mDataCodingScheme >> 2 & 3;
        JVM INSTR tableswitch 0 3: default 344
    //                   0 351
    //                   1 363
    //                   2 357
    //                   3 381;
           goto _L13 _L14 _L15 _L16 _L17
_L13:
        k = i;
          goto _L18
_L14:
        k = 1;
          goto _L18
_L16:
        k = 3;
          goto _L18
_L15:
        if(!Resources.getSystem().getBoolean(0x11200a1)) goto _L17; else goto _L19
_L19:
        k = 2;
          goto _L18
_L17:
        Rlog.w("SmsMessage", (new StringBuilder()).append("1 - Unsupported SMS data coding scheme ").append(mDataCodingScheme & 0xff).toString());
        k = 2;
          goto _L18
_L2:
        if((mDataCodingScheme & 0xf0) == 240)
        {
            flag1 = true;
            if((mDataCodingScheme & 4) == 0)
                k = 1;
            else
                k = 2;
        } else
        if((mDataCodingScheme & 0xf0) == 192 || (mDataCodingScheme & 0xf0) == 208 || (mDataCodingScheme & 0xf0) == 224)
        {
            if((mDataCodingScheme & 0xf0) == 224)
                k = 3;
            else
                k = 1;
            if((mDataCodingScheme & 8) == 8)
                flag2 = true;
            else
                flag2 = false;
            if((mDataCodingScheme & 3) == 0)
            {
                mIsMwi = true;
                mMwiSense = flag2;
                if((mDataCodingScheme & 0xf0) == 192)
                    flag3 = true;
                else
                    flag3 = false;
                mMwiDontStore = flag3;
                if(flag2)
                    mVoiceMailCount = -1;
                else
                    mVoiceMailCount = 0;
                Rlog.w("SmsMessage", (new StringBuilder()).append("MWI in DCS for Vmail. DCS = ").append(mDataCodingScheme & 0xff).append(" Dont store = ").append(mMwiDontStore).append(" vmail count = ").append(mVoiceMailCount).toString());
            } else
            {
                mIsMwi = false;
                Rlog.w("SmsMessage", (new StringBuilder()).append("MWI in DCS for fax/email/other: ").append(mDataCodingScheme & 0xff).toString());
            }
        } else
        if((mDataCodingScheme & 0xc0) == 128)
        {
            if(mDataCodingScheme == 132)
            {
                k = 4;
            } else
            {
                Rlog.w("SmsMessage", (new StringBuilder()).append("5 - Unsupported SMS data coding scheme ").append(mDataCodingScheme & 0xff).toString());
                k = i;
            }
        } else
        {
            Rlog.w("SmsMessage", (new StringBuilder()).append("3 - Unsupported SMS data coding scheme ").append(mDataCodingScheme & 0xff).toString());
            k = i;
        }
          goto _L18
_L21:
        mMwiDontStore = true;
          goto _L20
_L11:
        if(mMwiDontStore || ((mDataCodingScheme & 0xf0) == 208 || (mDataCodingScheme & 0xf0) == 224) && (mDataCodingScheme & 3) == 0) goto _L20; else goto _L21
_L9:
        Rlog.w("SmsMessage", (new StringBuilder()).append("TP_UDH fax/email/extended msg/multisubscriber profile. Msg Ind = ").append(i).toString());
          goto _L12
_L6:
        k;
        JVM INSTR tableswitch 0 4: default 960
    //                   0 983
    //                   1 1024
    //                   2 991
    //                   3 1079
    //                   4 1092;
           goto _L22 _L23 _L24 _L25 _L26 _L27
_L22:
        if(mMessageBody != null)
            parseMessageBody();
        if(flag1) goto _L29; else goto _L28
_L28:
        messageClass = com.android.internal.telephony.SmsConstants.MessageClass.UNKNOWN;
_L31:
        return;
_L23:
        mMessageBody = null;
        continue; /* Loop/switch isn't completed */
_L25:
        if(Resources.getSystem().getBoolean(0x11200a1))
            mMessageBody = pduparser.getUserDataGSM8bit(i1);
        else
            mMessageBody = null;
        continue; /* Loop/switch isn't completed */
_L24:
        int j;
        int l;
        if(flag)
            l = mUserDataHeader.languageTable;
        else
            l = 0;
        if(flag)
            j = mUserDataHeader.languageShiftTable;
        else
            j = 0;
        mMessageBody = pduparser.getUserDataGSM7Bit(i1, l, j);
        continue; /* Loop/switch isn't completed */
_L26:
        mMessageBody = pduparser.getUserDataUCS2(i1);
        continue; /* Loop/switch isn't completed */
_L27:
        mMessageBody = pduparser.getUserDataKSC5601(i1);
        continue; /* Loop/switch isn't completed */
_L29:
        switch(mDataCodingScheme & 3)
        {
        case 0: // '\0'
            messageClass = com.android.internal.telephony.SmsConstants.MessageClass.CLASS_0;
            break;

        case 1: // '\001'
            messageClass = com.android.internal.telephony.SmsConstants.MessageClass.CLASS_1;
            break;

        case 2: // '\002'
            messageClass = com.android.internal.telephony.SmsConstants.MessageClass.CLASS_2;
            break;

        case 3: // '\003'
            messageClass = com.android.internal.telephony.SmsConstants.MessageClass.CLASS_3;
            break;
        }
        if(true) goto _L31; else goto _L30
_L30:
        if(true) goto _L22; else goto _L32
_L32:
    }

    int getDataCodingScheme()
    {
        return mDataCodingScheme;
    }

    public com.android.internal.telephony.SmsConstants.MessageClass getMessageClass()
    {
        return messageClass;
    }

    public int getNumOfVoicemails()
    {
        if(!mIsMwi && isCphsMwiMessage())
        {
            if(mOriginatingAddress != null && ((GsmSmsAddress)mOriginatingAddress).isCphsVoiceMessageSet())
                mVoiceMailCount = 255;
            else
                mVoiceMailCount = 0;
            Rlog.v("SmsMessage", "CPHS voice mail message");
        }
        return mVoiceMailCount;
    }

    public int getProtocolIdentifier()
    {
        return mProtocolIdentifier;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public boolean isCphsMwiMessage()
    {
        boolean flag;
        if(!((GsmSmsAddress)mOriginatingAddress).isCphsVoiceMessageClear())
            flag = ((GsmSmsAddress)mOriginatingAddress).isCphsVoiceMessageSet();
        else
            flag = true;
        return flag;
    }

    public boolean isMWIClearMessage()
    {
        if(mIsMwi && mMwiSense ^ true)
            return true;
        boolean flag;
        if(mOriginatingAddress != null)
            flag = ((GsmSmsAddress)mOriginatingAddress).isCphsVoiceMessageClear();
        else
            flag = false;
        return flag;
    }

    public boolean isMWISetMessage()
    {
        if(mIsMwi && mMwiSense)
            return true;
        boolean flag;
        if(mOriginatingAddress != null)
            flag = ((GsmSmsAddress)mOriginatingAddress).isCphsVoiceMessageSet();
        else
            flag = false;
        return flag;
    }

    public boolean isMwiDontStore()
    {
        if(mIsMwi && mMwiDontStore)
            return true;
        return isCphsMwiMessage() && " ".equals(getMessageBody());
    }

    public boolean isReplace()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if((mProtocolIdentifier & 0xc0) == 64)
        {
            flag1 = flag;
            if((mProtocolIdentifier & 0x3f) > 0)
            {
                flag1 = flag;
                if((mProtocolIdentifier & 0x3f) < 8)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public boolean isReplyPathPresent()
    {
        return mReplyPathPresent;
    }

    public boolean isStatusReportMessage()
    {
        return mIsStatusReportMessage;
    }

    public boolean isTypeZero()
    {
        boolean flag;
        if(mProtocolIdentifier == 64)
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean isUsimDataDownload()
    {
        boolean flag = true;
        boolean flag1;
        if(messageClass == com.android.internal.telephony.SmsConstants.MessageClass.CLASS_2)
        {
            flag1 = flag;
            if(mProtocolIdentifier != 127)
                if(mProtocolIdentifier == 124)
                    flag1 = flag;
                else
                    flag1 = false;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    private static final int INVALID_VALIDITY_PERIOD = -1;
    static final String LOG_TAG = "SmsMessage";
    private static final int VALIDITY_PERIOD_FORMAT_ABSOLUTE = 3;
    private static final int VALIDITY_PERIOD_FORMAT_ENHANCED = 1;
    private static final int VALIDITY_PERIOD_FORMAT_NONE = 0;
    private static final int VALIDITY_PERIOD_FORMAT_RELATIVE = 2;
    private static final int VALIDITY_PERIOD_MAX = 0x9b0a0;
    private static final int VALIDITY_PERIOD_MIN = 5;
    private static final boolean VDBG = false;
    private int mDataCodingScheme;
    private boolean mIsStatusReportMessage;
    private int mMti;
    private int mProtocolIdentifier;
    private boolean mReplyPathPresent;
    private int mStatus;
    private int mVoiceMailCount;
    private com.android.internal.telephony.SmsConstants.MessageClass messageClass;
}
