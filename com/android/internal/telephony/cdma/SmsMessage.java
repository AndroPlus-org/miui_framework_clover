// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.cdma;

import android.content.res.Resources;
import android.os.SystemProperties;
import android.telephony.*;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.*;
import com.android.internal.telephony.cdma.sms.BearerData;
import com.android.internal.telephony.cdma.sms.CdmaSmsAddress;
import com.android.internal.telephony.cdma.sms.CdmaSmsSubaddress;
import com.android.internal.telephony.cdma.sms.SmsEnvelope;
import com.android.internal.telephony.cdma.sms.UserData;
import com.android.internal.util.BitwiseInputStream;
import com.android.internal.util.HexDump;
import java.io.*;
import java.util.ArrayList;

public class SmsMessage extends SmsMessageBase
{
    public static class SubmitPdu extends com.android.internal.telephony.SmsMessageBase.SubmitPduBase
    {

        public SubmitPdu()
        {
        }
    }


    public SmsMessage()
    {
    }

    public SmsMessage(SmsAddress smsaddress, SmsEnvelope smsenvelope)
    {
        mOriginatingAddress = smsaddress;
        mEnvelope = smsenvelope;
        createPdu();
    }

    public static com.android.internal.telephony.GsmAlphabet.TextEncodingDetails calculateLength(CharSequence charsequence, boolean flag, boolean flag1)
    {
        String s = null;
        if(Resources.getSystem().getBoolean(0x11200a2))
            s = Sms7BitEncodingTranslator.translate(charsequence);
        Object obj = s;
        if(TextUtils.isEmpty(s))
            obj = charsequence;
        return BearerData.calcTextEncodingDetails(((CharSequence) (obj)), flag, flag1);
    }

    public static byte convertDtmfToAscii(byte byte0)
    {
        byte0;
        JVM INSTR tableswitch 0 15: default 80
    //                   0 87
    //                   1 95
    //                   2 103
    //                   3 111
    //                   4 119
    //                   5 127
    //                   6 135
    //                   7 143
    //                   8 151
    //                   9 159
    //                   10 167
    //                   11 175
    //                   12 183
    //                   13 191
    //                   14 199
    //                   15 207;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L1:
        byte byte1;
        byte0 = 32;
        byte1 = byte0;
_L19:
        return byte1;
_L2:
        byte0 = 68;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L3:
        byte0 = 49;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L4:
        byte0 = 50;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L5:
        byte0 = 51;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L6:
        byte0 = 52;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L7:
        byte0 = 53;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L8:
        byte0 = 54;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L9:
        byte0 = 55;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L10:
        byte0 = 56;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L11:
        byte0 = 57;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L12:
        byte0 = 48;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L13:
        byte0 = 42;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L14:
        byte0 = 35;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L15:
        byte0 = 65;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L16:
        byte0 = 66;
        byte1 = byte0;
        continue; /* Loop/switch isn't completed */
_L17:
        byte0 = 67;
        byte1 = byte0;
        if(true) goto _L19; else goto _L18
_L18:
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
        i = abyte0[1] & 0xff;
        abyte1 = new byte[i];
        System.arraycopy(abyte0, 2, abyte1, 0, i);
        smsmessage.parsePduFromEfRecord(abyte1);
        return smsmessage;
    }

    public static SmsMessage createFromPdu(byte abyte0[])
    {
        SmsMessage smsmessage = new SmsMessage();
        try
        {
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
            Log.e("SmsMessage", "SMS PDU parsing failed with out of memory: ", abyte0);
            return null;
        }
        return smsmessage;
    }

    public static int getNextMessageId()
    {
        com/android/internal/telephony/cdma/SmsMessage;
        JVM INSTR monitorenter ;
        int i;
        String s;
        i = SystemProperties.getInt("persist.radio.cdma.msgid", 1);
        s = Integer.toString(i % 65535 + 1);
        SystemProperties.set("persist.radio.cdma.msgid", s);
        if(Rlog.isLoggable("CDMA:SMS", 2))
        {
            StringBuilder stringbuilder = JVM INSTR new #168 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Rlog.d("SmsMessage", stringbuilder.append("next persist.radio.cdma.msgid = ").append(s).toString());
            stringbuilder = JVM INSTR new #168 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Rlog.d("SmsMessage", stringbuilder.append("readback gets ").append(SystemProperties.get("persist.radio.cdma.msgid")).toString());
        }
_L2:
        com/android/internal/telephony/cdma/SmsMessage;
        JVM INSTR monitorexit ;
        return i;
        RuntimeException runtimeexception;
        runtimeexception;
        StringBuilder stringbuilder1 = JVM INSTR new #168 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Rlog.e("SmsMessage", stringbuilder1.append("set nextMessage ID failed: ").append(runtimeexception).toString());
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public static SubmitPdu getSubmitPdu(String s, UserData userdata, boolean flag)
    {
        return privateGetSubmitPdu(s, flag, userdata);
    }

    public static SubmitPdu getSubmitPdu(String s, UserData userdata, boolean flag, int i)
    {
        return privateGetSubmitPdu(s, flag, userdata, i);
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, int i, byte abyte0[], boolean flag)
    {
        Object obj = new com.android.internal.telephony.SmsHeader.PortAddrs();
        obj.destPort = i;
        obj.origPort = 0;
        obj.areEightBits = false;
        s = new SmsHeader();
        s.portAddrs = ((com.android.internal.telephony.SmsHeader.PortAddrs) (obj));
        obj = new UserData();
        obj.userDataHeader = s;
        obj.msgEncoding = 0;
        obj.msgEncodingSet = true;
        obj.payload = abyte0;
        return privateGetSubmitPdu(s1, flag, ((UserData) (obj)));
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag, SmsHeader smsheader)
    {
        return getSubmitPdu(s, s1, s2, flag, smsheader, -1);
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag, SmsHeader smsheader, int i)
    {
        if(s2 == null || s1 == null)
        {
            return null;
        } else
        {
            s = new UserData();
            s.payloadStr = s2;
            s.userDataHeader = smsheader;
            return privateGetSubmitPdu(s1, flag, s, i);
        }
    }

    public static int getTPLayerLengthForPDU(String s)
    {
        Rlog.w("SmsMessage", "getTPLayerLengthForPDU: is not supported in CDMA mode.");
        return 0;
    }

    private void parsePdu(byte abyte0[])
    {
        Object obj;
        SmsEnvelope smsenvelope;
        CdmaSmsAddress cdmasmsaddress;
        obj = new DataInputStream(new ByteArrayInputStream(abyte0));
        smsenvelope = new SmsEnvelope();
        cdmasmsaddress = new CdmaSmsAddress();
        int i;
        smsenvelope.messageType = ((DataInputStream) (obj)).readInt();
        smsenvelope.teleService = ((DataInputStream) (obj)).readInt();
        smsenvelope.serviceCategory = ((DataInputStream) (obj)).readInt();
        cdmasmsaddress.digitMode = ((DataInputStream) (obj)).readByte();
        cdmasmsaddress.numberMode = ((DataInputStream) (obj)).readByte();
        cdmasmsaddress.ton = ((DataInputStream) (obj)).readByte();
        cdmasmsaddress.numberPlan = ((DataInputStream) (obj)).readByte();
        i = ((DataInputStream) (obj)).readUnsignedByte();
        cdmasmsaddress.numberOfDigits = i;
        if(i > abyte0.length)
        {
            RuntimeException runtimeexception = JVM INSTR new #98  <Class RuntimeException>;
            obj = JVM INSTR new #168 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            runtimeexception.RuntimeException(((StringBuilder) (obj)).append("createFromPdu: Invalid pdu, addr.numberOfDigits ").append(i).append(" > pdu len ").append(abyte0.length).toString());
            throw runtimeexception;
        }
        cdmasmsaddress.origBytes = new byte[i];
        ((DataInputStream) (obj)).read(cdmasmsaddress.origBytes, 0, i);
        smsenvelope.bearerReply = ((DataInputStream) (obj)).readInt();
        smsenvelope.replySeqNo = ((DataInputStream) (obj)).readByte();
        smsenvelope.errorClass = ((DataInputStream) (obj)).readByte();
        smsenvelope.causeCode = ((DataInputStream) (obj)).readByte();
        i = ((DataInputStream) (obj)).readInt();
        if(i > abyte0.length)
        {
            RuntimeException runtimeexception1 = JVM INSTR new #98  <Class RuntimeException>;
            obj = JVM INSTR new #168 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            runtimeexception1.RuntimeException(((StringBuilder) (obj)).append("createFromPdu: Invalid pdu, bearerDataLength ").append(i).append(" > pdu len ").append(abyte0.length).toString());
            throw runtimeexception1;
        }
          goto _L1
_L3:
        mOriginatingAddress = cdmasmsaddress;
        smsenvelope.origAddress = cdmasmsaddress;
        mEnvelope = smsenvelope;
        mPdu = abyte0;
        parseSms();
        return;
_L1:
        try
        {
            smsenvelope.bearerData = new byte[i];
            ((DataInputStream) (obj)).read(smsenvelope.bearerData, 0, i);
            ((DataInputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new RuntimeException((new StringBuilder()).append("createFromPdu: conversion from byte array to object failed: ").append(abyte0).toString(), abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Rlog.e("SmsMessage", (new StringBuilder()).append("createFromPdu: conversion from byte array to object failed: ").append(obj).toString());
        }
        if(true) goto _L3; else goto _L2
_L2:
    }

    private void parsePduFromEfRecord(byte abyte0[])
    {
        Object obj;
        Object obj1;
        SmsEnvelope smsenvelope;
        CdmaSmsAddress cdmasmsaddress;
        CdmaSmsSubaddress cdmasmssubaddress;
        obj = new ByteArrayInputStream(abyte0);
        obj1 = new DataInputStream(((java.io.InputStream) (obj)));
        smsenvelope = new SmsEnvelope();
        cdmasmsaddress = new CdmaSmsAddress();
        cdmasmssubaddress = new CdmaSmsSubaddress();
        smsenvelope.messageType = ((DataInputStream) (obj1)).readByte();
_L11:
        if(((DataInputStream) (obj1)).available() <= 0) goto _L2; else goto _L1
_L1:
        byte byte0;
        int i;
        byte abyte1[];
        byte0 = ((DataInputStream) (obj1)).readByte();
        i = ((DataInputStream) (obj1)).readUnsignedByte();
        abyte1 = new byte[i];
        byte0;
        JVM INSTR tableswitch 0 8: default 132
    //                   0 234
    //                   1 281
    //                   2 293
    //                   3 635
    //                   4 293
    //                   5 635
    //                   6 742
    //                   7 779
    //                   8 856;
           goto _L3 _L4 _L5 _L6 _L7 _L6 _L7 _L8 _L9 _L10
_L3:
        try
        {
            obj1 = JVM INSTR new #256 <Class Exception>;
            obj = JVM INSTR new #168 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((Exception) (obj1)).Exception(((StringBuilder) (obj)).append("unsupported parameterId (").append(byte0).append(")").toString());
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            Rlog.e("SmsMessage", (new StringBuilder()).append("parsePduFromEfRecord: conversion from pdu to SmsMessage failed").append(obj1).toString());
        }
_L28:
        mOriginatingAddress = cdmasmsaddress;
        smsenvelope.origAddress = cdmasmsaddress;
        smsenvelope.origSubaddress = cdmasmssubaddress;
        mEnvelope = smsenvelope;
        mPdu = abyte0;
        parseSms();
        return;
_L4:
        smsenvelope.teleService = ((DataInputStream) (obj1)).readUnsignedShort();
        abyte1 = JVM INSTR new #168 <Class StringBuilder>;
        abyte1.StringBuilder();
        Rlog.i("SmsMessage", abyte1.append("teleservice = ").append(smsenvelope.teleService).toString());
          goto _L11
_L5:
        smsenvelope.serviceCategory = ((DataInputStream) (obj1)).readUnsignedShort();
          goto _L11
_L6:
        BitwiseInputStream bitwiseinputstream;
        ((DataInputStream) (obj1)).read(abyte1, 0, i);
        bitwiseinputstream = JVM INSTR new #384 <Class BitwiseInputStream>;
        bitwiseinputstream.BitwiseInputStream(abyte1);
        cdmasmsaddress.digitMode = bitwiseinputstream.read(1);
        cdmasmsaddress.numberMode = bitwiseinputstream.read(1);
        i = 0;
        int j;
        if(cdmasmsaddress.digitMode != 1)
            break MISSING_BLOCK_LABEL_391;
        j = bitwiseinputstream.read(3);
        cdmasmsaddress.ton = j;
        i = j;
        if(cdmasmsaddress.numberMode != 0)
            break MISSING_BLOCK_LABEL_391;
        cdmasmsaddress.numberPlan = bitwiseinputstream.read(4);
        i = j;
        cdmasmsaddress.numberOfDigits = bitwiseinputstream.read(8);
        abyte1 = new byte[cdmasmsaddress.numberOfDigits];
        if(cdmasmsaddress.digitMode != 0) goto _L13; else goto _L12
_L12:
        i = 0;
_L15:
        if(i >= cdmasmsaddress.numberOfDigits)
            break; /* Loop/switch isn't completed */
        abyte1[i] = convertDtmfToAscii((byte)(bitwiseinputstream.read(4) & 0xf));
        i++;
        if(true) goto _L15; else goto _L14
_L13:
        if(cdmasmsaddress.digitMode != 1) goto _L17; else goto _L16
_L16:
        if(cdmasmsaddress.numberMode != 0) goto _L19; else goto _L18
_L18:
        i = 0;
_L20:
        if(i >= cdmasmsaddress.numberOfDigits)
            break; /* Loop/switch isn't completed */
        abyte1[i] = (byte)(bitwiseinputstream.read(8) & 0xff);
        i++;
        if(true) goto _L20; else goto _L14
_L19:
        if(cdmasmsaddress.numberMode != 1) goto _L22; else goto _L21
_L21:
        if(i != 2) goto _L24; else goto _L23
_L23:
        Rlog.e("SmsMessage", "TODO: Originating Addr is email id");
_L14:
        cdmasmsaddress.origBytes = abyte1;
        abyte1 = JVM INSTR new #168 <Class StringBuilder>;
        abyte1.StringBuilder();
        Rlog.i("SmsMessage", abyte1.append("Originating Addr=").append(cdmasmsaddress.toString()).toString());
        if(byte0 != 4) goto _L11; else goto _L25
_L25:
        smsenvelope.destAddress = cdmasmsaddress;
        mRecipientAddress = cdmasmsaddress;
          goto _L11
_L24:
        Rlog.e("SmsMessage", "TODO: Originating Addr is data network address");
          goto _L14
_L22:
        Rlog.e("SmsMessage", "Originating Addr is of incorrect type");
          goto _L14
_L17:
        Rlog.e("SmsMessage", "Incorrect Digit mode");
          goto _L14
_L7:
        ((DataInputStream) (obj1)).read(abyte1, 0, i);
        bitwiseinputstream = JVM INSTR new #384 <Class BitwiseInputStream>;
        bitwiseinputstream.BitwiseInputStream(abyte1);
        cdmasmssubaddress.type = bitwiseinputstream.read(3);
        cdmasmssubaddress.odd = bitwiseinputstream.readByteArray(1)[0];
        j = bitwiseinputstream.read(8);
        abyte1 = new byte[j];
        i = 0;
_L27:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        abyte1[i] = convertDtmfToAscii((byte)(bitwiseinputstream.read(4) & 0xff));
        i++;
        if(true) goto _L27; else goto _L26
_L26:
        cdmasmssubaddress.origBytes = abyte1;
          goto _L11
_L8:
        ((DataInputStream) (obj1)).read(abyte1, 0, i);
        BitwiseInputStream bitwiseinputstream1 = JVM INSTR new #384 <Class BitwiseInputStream>;
        bitwiseinputstream1.BitwiseInputStream(abyte1);
        smsenvelope.bearerReply = bitwiseinputstream1.read(6);
          goto _L11
_L9:
        ((DataInputStream) (obj1)).read(abyte1, 0, i);
        BitwiseInputStream bitwiseinputstream2 = JVM INSTR new #384 <Class BitwiseInputStream>;
        bitwiseinputstream2.BitwiseInputStream(abyte1);
        smsenvelope.replySeqNo = bitwiseinputstream2.readByteArray(6)[0];
        smsenvelope.errorClass = bitwiseinputstream2.readByteArray(2)[0];
        if(smsenvelope.errorClass != 0)
            smsenvelope.causeCode = bitwiseinputstream2.readByteArray(8)[0];
          goto _L11
_L10:
        ((DataInputStream) (obj1)).read(abyte1, 0, i);
        smsenvelope.bearerData = abyte1;
          goto _L11
_L2:
        ((ByteArrayInputStream) (obj)).close();
        ((DataInputStream) (obj1)).close();
          goto _L28
    }

    private static SubmitPdu privateGetSubmitPdu(String s, boolean flag, UserData userdata)
    {
        return privateGetSubmitPdu(s, flag, userdata, -1);
    }

    private static SubmitPdu privateGetSubmitPdu(String s, boolean flag, UserData userdata, int i)
    {
        s = CdmaSmsAddress.parse(PhoneNumberUtils.cdmaCheckAndProcessPlusCodeForSms(s));
        if(s == null)
            return null;
        BearerData bearerdata = new BearerData();
        bearerdata.messageType = 2;
        bearerdata.messageId = getNextMessageId();
        bearerdata.deliveryAckReq = flag;
        bearerdata.userAckReq = false;
        bearerdata.readAckReq = false;
        bearerdata.reportReq = false;
        if(i >= 0 && i <= 3)
        {
            bearerdata.priorityIndicatorSet = true;
            bearerdata.priority = i;
        }
        bearerdata.userData = userdata;
        byte abyte0[] = BearerData.encode(bearerdata);
        if(Rlog.isLoggable("CDMA:SMS", 2))
        {
            Rlog.d("SmsMessage", (new StringBuilder()).append("MO (encoded) BearerData = ").append(bearerdata).toString());
            Rlog.d("SmsMessage", (new StringBuilder()).append("MO raw BearerData = '").append(HexDump.toHexString(abyte0)).append("'").toString());
        }
        if(abyte0 == null)
            return null;
        SmsEnvelope smsenvelope;
        if(bearerdata.hasUserDataHeader && userdata.msgEncoding != 2)
            i = 4101;
        else
            i = 4098;
        smsenvelope = new SmsEnvelope();
        smsenvelope.messageType = 0;
        smsenvelope.teleService = i;
        smsenvelope.destAddress = s;
        smsenvelope.bearerReply = 1;
        smsenvelope.bearerData = abyte0;
        try
        {
            userdata = JVM INSTR new #478 <Class ByteArrayOutputStream>;
            userdata.ByteArrayOutputStream(100);
            DataOutputStream dataoutputstream = JVM INSTR new #483 <Class DataOutputStream>;
            dataoutputstream.DataOutputStream(userdata);
            dataoutputstream.writeInt(smsenvelope.teleService);
            dataoutputstream.writeInt(0);
            dataoutputstream.writeInt(0);
            dataoutputstream.write(((CdmaSmsAddress) (s)).digitMode);
            dataoutputstream.write(((CdmaSmsAddress) (s)).numberMode);
            dataoutputstream.write(((CdmaSmsAddress) (s)).ton);
            dataoutputstream.write(((CdmaSmsAddress) (s)).numberPlan);
            dataoutputstream.write(((CdmaSmsAddress) (s)).numberOfDigits);
            dataoutputstream.write(((CdmaSmsAddress) (s)).origBytes, 0, ((CdmaSmsAddress) (s)).origBytes.length);
            dataoutputstream.write(0);
            dataoutputstream.write(0);
            dataoutputstream.write(0);
            dataoutputstream.write(abyte0.length);
            dataoutputstream.write(abyte0, 0, abyte0.length);
            dataoutputstream.close();
            s = JVM INSTR new #6   <Class SmsMessage$SubmitPdu>;
            s.SubmitPdu();
            s.encodedMessage = userdata.toByteArray();
            s.encodedScAddress = null;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Rlog.e("SmsMessage", (new StringBuilder()).append("creating SubmitPdu failed: ").append(s).toString());
            return null;
        }
        return s;
    }

    public void createPdu()
    {
        SmsEnvelope smsenvelope;
        CdmaSmsAddress cdmasmsaddress;
        ByteArrayOutputStream bytearrayoutputstream;
        DataOutputStream dataoutputstream;
        smsenvelope = mEnvelope;
        cdmasmsaddress = smsenvelope.origAddress;
        bytearrayoutputstream = new ByteArrayOutputStream(100);
        dataoutputstream = new DataOutputStream(new BufferedOutputStream(bytearrayoutputstream));
        dataoutputstream.writeInt(smsenvelope.messageType);
        dataoutputstream.writeInt(smsenvelope.teleService);
        dataoutputstream.writeInt(smsenvelope.serviceCategory);
        dataoutputstream.writeByte(cdmasmsaddress.digitMode);
        dataoutputstream.writeByte(cdmasmsaddress.numberMode);
        dataoutputstream.writeByte(cdmasmsaddress.ton);
        dataoutputstream.writeByte(cdmasmsaddress.numberPlan);
        dataoutputstream.writeByte(cdmasmsaddress.numberOfDigits);
        dataoutputstream.write(cdmasmsaddress.origBytes, 0, cdmasmsaddress.origBytes.length);
        dataoutputstream.writeInt(smsenvelope.bearerReply);
        dataoutputstream.writeByte(smsenvelope.replySeqNo);
        dataoutputstream.writeByte(smsenvelope.errorClass);
        dataoutputstream.writeByte(smsenvelope.causeCode);
        dataoutputstream.writeInt(smsenvelope.bearerData.length);
        dataoutputstream.write(smsenvelope.bearerData, 0, smsenvelope.bearerData.length);
        dataoutputstream.close();
        mPdu = bytearrayoutputstream.toByteArray();
_L1:
        return;
        IOException ioexception;
        ioexception;
        Rlog.e("SmsMessage", (new StringBuilder()).append("createPdu: conversion from object to byte array failed: ").append(ioexception).toString());
          goto _L1
    }

    public byte[] getIncomingSmsFingerprint()
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bytearrayoutputstream.write(mEnvelope.serviceCategory);
        bytearrayoutputstream.write(mEnvelope.teleService);
        bytearrayoutputstream.write(mEnvelope.origAddress.origBytes, 0, mEnvelope.origAddress.origBytes.length);
        bytearrayoutputstream.write(mEnvelope.bearerData, 0, mEnvelope.bearerData.length);
        bytearrayoutputstream.write(mEnvelope.origSubaddress.origBytes, 0, mEnvelope.origSubaddress.origBytes.length);
        return bytearrayoutputstream.toByteArray();
    }

    public com.android.internal.telephony.SmsConstants.MessageClass getMessageClass()
    {
        if(mBearerData.displayMode == 0)
            return com.android.internal.telephony.SmsConstants.MessageClass.CLASS_0;
        else
            return com.android.internal.telephony.SmsConstants.MessageClass.UNKNOWN;
    }

    public int getMessageType()
    {
        return mEnvelope.serviceCategory == 0 ? 0 : 1;
    }

    public int getNumOfVoicemails()
    {
        return mBearerData.numberOfMessages;
    }

    public int getProtocolIdentifier()
    {
        Rlog.w("SmsMessage", "getProtocolIdentifier: is not supported in CDMA mode.");
        return 0;
    }

    public ArrayList getSmsCbProgramData()
    {
        return mBearerData.serviceCategoryProgramData;
    }

    public int getStatus()
    {
        return status << 16;
    }

    public int getTeleService()
    {
        return mEnvelope.teleService;
    }

    public boolean isCphsMwiMessage()
    {
        Rlog.w("SmsMessage", "isCphsMwiMessage: is not supported in CDMA mode.");
        return false;
    }

    public boolean isMWIClearMessage()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mBearerData != null)
        {
            flag1 = flag;
            if(mBearerData.numberOfMessages == 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isMWISetMessage()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mBearerData != null)
        {
            flag1 = flag;
            if(mBearerData.numberOfMessages > 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isMwiDontStore()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mBearerData != null)
        {
            flag1 = flag;
            if(mBearerData.numberOfMessages > 0)
            {
                flag1 = flag;
                if(mBearerData.userData == null)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public boolean isReplace()
    {
        Rlog.w("SmsMessage", "isReplace: is not supported in CDMA mode.");
        return false;
    }

    public boolean isReplyPathPresent()
    {
        Rlog.w("SmsMessage", "isReplyPathPresent: is not supported in CDMA mode.");
        return false;
    }

    public boolean isStatusReportMessage()
    {
        boolean flag;
        if(mBearerData.messageType == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public SmsCbMessage parseBroadcastSms()
    {
        BearerData bearerdata = BearerData.decode(mEnvelope.bearerData, mEnvelope.serviceCategory);
        if(bearerdata == null)
        {
            Rlog.w("SmsMessage", "BearerData.decode() returned null");
            return null;
        }
        if(Rlog.isLoggable("CDMA:SMS", 2))
            Rlog.d("SmsMessage", (new StringBuilder()).append("MT raw BearerData = ").append(HexDump.toHexString(mEnvelope.bearerData)).toString());
        SmsCbLocation smscblocation = new SmsCbLocation(TelephonyManager.getDefault().getNetworkOperator());
        return new SmsCbMessage(2, 1, bearerdata.messageId, smscblocation, mEnvelope.serviceCategory, bearerdata.getLanguage(), bearerdata.userData.payloadStr, bearerdata.priority, null, bearerdata.cmasWarningInfo);
    }

    public void parseSms()
    {
        if(mEnvelope.teleService == 0x40000)
        {
            mBearerData = new BearerData();
            if(mEnvelope.bearerData != null)
                mBearerData.numberOfMessages = mEnvelope.bearerData[0] & 0xff;
            return;
        }
        mBearerData = BearerData.decode(mEnvelope.bearerData);
        if(Rlog.isLoggable("CDMA:SMS", 2))
        {
            Rlog.d("SmsMessage", (new StringBuilder()).append("MT raw BearerData = '").append(HexDump.toHexString(mEnvelope.bearerData)).append("'").toString());
            Rlog.d("SmsMessage", (new StringBuilder()).append("MT (decoded) BearerData = ").append(mBearerData).toString());
        }
        mMessageRef = mBearerData.messageId;
        if(mBearerData.userData != null)
        {
            mUserData = mBearerData.userData.payload;
            mUserDataHeader = mBearerData.userData.userDataHeader;
            mMessageBody = mBearerData.userData.payloadStr;
        }
        if(mOriginatingAddress != null)
        {
            mOriginatingAddress.address = new String(mOriginatingAddress.origBytes);
            if(mOriginatingAddress.ton == 1 && mOriginatingAddress.address.charAt(0) != '+')
                mOriginatingAddress.address = (new StringBuilder()).append("+").append(mOriginatingAddress.address).toString();
        }
        if(mBearerData.msgCenterTimeStamp != null)
            mScTimeMillis = mBearerData.msgCenterTimeStamp.toMillis(true);
        if(mBearerData.messageType == 4)
        {
            if(!mBearerData.messageStatusSet)
            {
                StringBuilder stringbuilder = (new StringBuilder()).append("DELIVERY_ACK message without msgStatus (");
                String s;
                if(mUserData == null)
                    s = "also missing";
                else
                    s = "does have";
                Rlog.d("SmsMessage", stringbuilder.append(s).append(" userData).").toString());
                status = 0;
            } else
            {
                status = mBearerData.errorClass << 8;
                status = status | mBearerData.messageStatus;
            }
        } else
        if(mBearerData.messageType != 1 && mBearerData.messageType != 2)
            throw new RuntimeException((new StringBuilder()).append("Unsupported message type: ").append(mBearerData.messageType).toString());
        byte abyte0[];
        if(mMessageBody != null)
            parseMessageBody();
        else
            abyte0 = mUserData;
    }

    protected boolean processCdmaCTWdpHeader(SmsMessage smsmessage)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = false;
        BitwiseInputStream bitwiseinputstream;
        bitwiseinputstream = JVM INSTR new #384 <Class BitwiseInputStream>;
        bitwiseinputstream.BitwiseInputStream(smsmessage.getUserData());
        if(bitwiseinputstream.read(8) == 0)
            break MISSING_BLOCK_LABEL_39;
        Rlog.e("SmsMessage", "Invalid WDP SubparameterId");
        return false;
        if(bitwiseinputstream.read(8) == 3)
            break MISSING_BLOCK_LABEL_61;
        Rlog.e("SmsMessage", "Invalid WDP subparameter length");
        return false;
        int i;
        BearerData bearerdata;
        smsmessage.mBearerData.messageType = bitwiseinputstream.read(4);
        i = bitwiseinputstream.read(8) << 8 | bitwiseinputstream.read(8);
        bearerdata = smsmessage.mBearerData;
        if(bitwiseinputstream.read(1) != 1)
            flag = false;
        bearerdata.hasUserDataHeader = flag;
        if(!smsmessage.mBearerData.hasUserDataHeader)
            break MISSING_BLOCK_LABEL_142;
        Rlog.e("SmsMessage", "Invalid WDP UserData header value");
        return false;
        bitwiseinputstream.skip(3);
        smsmessage.mBearerData.messageId = i;
        smsmessage.mMessageRef = i;
        bitwiseinputstream.read(8);
        i = bitwiseinputstream.read(8);
        smsmessage.mBearerData.userData.msgEncoding = bitwiseinputstream.read(5);
        if(smsmessage.mBearerData.userData.msgEncoding == 0)
            break MISSING_BLOCK_LABEL_220;
        Rlog.e("SmsMessage", "Invalid WDP encoding");
        return false;
        smsmessage.mBearerData.userData.numFields = bitwiseinputstream.read(8);
        i = i * 8 - 13;
        int j = smsmessage.mBearerData.userData.numFields * 8;
        if(j < i)
            i = j;
        smsmessage.mBearerData.userData.payload = bitwiseinputstream.readByteArray(i);
        smsmessage.mUserData = smsmessage.mBearerData.userData.payload;
        flag = true;
_L2:
        return flag;
        smsmessage;
        Rlog.e("SmsMessage", (new StringBuilder()).append("CT WDP Header decode failed: ").append(smsmessage).toString());
        flag = flag1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final byte BEARER_DATA = 8;
    private static final byte BEARER_REPLY_OPTION = 6;
    private static final byte CAUSE_CODES = 7;
    private static final byte DESTINATION_ADDRESS = 4;
    private static final byte DESTINATION_SUB_ADDRESS = 5;
    private static final String LOGGABLE_TAG = "CDMA:SMS";
    static final String LOG_TAG = "SmsMessage";
    private static final byte ORIGINATING_ADDRESS = 2;
    private static final byte ORIGINATING_SUB_ADDRESS = 3;
    private static final int PRIORITY_EMERGENCY = 3;
    private static final int PRIORITY_INTERACTIVE = 1;
    private static final int PRIORITY_NORMAL = 0;
    private static final int PRIORITY_URGENT = 2;
    private static final int RETURN_ACK = 1;
    private static final int RETURN_NO_ACK = 0;
    private static final byte SERVICE_CATEGORY = 1;
    private static final byte TELESERVICE_IDENTIFIER = 0;
    private static final boolean VDBG = false;
    private BearerData mBearerData;
    private SmsEnvelope mEnvelope;
    private int status;
}
