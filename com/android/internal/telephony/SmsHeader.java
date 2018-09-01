// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import com.android.internal.util.HexDump;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class SmsHeader
{
    public static class ConcatRef
    {

        public boolean isEightBits;
        public int msgCount;
        public int refNumber;
        public int seqNumber;

        public ConcatRef()
        {
        }
    }

    public static class MiscElt
    {

        public byte data[];
        public int id;

        public MiscElt()
        {
        }
    }

    public static class PortAddrs
    {

        public boolean areEightBits;
        public int destPort;
        public int origPort;

        public PortAddrs()
        {
        }
    }

    public static class SpecialSmsMsg
    {

        public int msgCount;
        public int msgIndType;

        public SpecialSmsMsg()
        {
        }
    }


    public SmsHeader()
    {
        specialSmsMsgList = new ArrayList();
        miscEltList = new ArrayList();
    }

    public static SmsHeader fromByteArray(byte abyte0[])
    {
        abyte0 = new ByteArrayInputStream(abyte0);
        SmsHeader smsheader = new SmsHeader();
        do
        {
            if(abyte0.available() <= 0)
                break;
            int i = abyte0.read();
            int j = abyte0.read();
            switch(i)
            {
            default:
                MiscElt miscelt = new MiscElt();
                miscelt.id = i;
                miscelt.data = new byte[j];
                abyte0.read(miscelt.data, 0, j);
                smsheader.miscEltList.add(miscelt);
                break;

            case 0: // '\0'
                ConcatRef concatref = new ConcatRef();
                concatref.refNumber = abyte0.read();
                concatref.msgCount = abyte0.read();
                concatref.seqNumber = abyte0.read();
                concatref.isEightBits = true;
                if(concatref.msgCount != 0 && concatref.seqNumber != 0 && concatref.seqNumber <= concatref.msgCount)
                    smsheader.concatRef = concatref;
                break;

            case 8: // '\b'
                ConcatRef concatref1 = new ConcatRef();
                concatref1.refNumber = abyte0.read() << 8 | abyte0.read();
                concatref1.msgCount = abyte0.read();
                concatref1.seqNumber = abyte0.read();
                concatref1.isEightBits = false;
                if(concatref1.msgCount != 0 && concatref1.seqNumber != 0 && concatref1.seqNumber <= concatref1.msgCount)
                    smsheader.concatRef = concatref1;
                break;

            case 4: // '\004'
                PortAddrs portaddrs = new PortAddrs();
                portaddrs.destPort = abyte0.read();
                portaddrs.origPort = abyte0.read();
                portaddrs.areEightBits = true;
                smsheader.portAddrs = portaddrs;
                break;

            case 5: // '\005'
                PortAddrs portaddrs1 = new PortAddrs();
                portaddrs1.destPort = abyte0.read() << 8 | abyte0.read();
                portaddrs1.origPort = abyte0.read() << 8 | abyte0.read();
                portaddrs1.areEightBits = false;
                smsheader.portAddrs = portaddrs1;
                break;

            case 36: // '$'
                smsheader.languageShiftTable = abyte0.read();
                break;

            case 37: // '%'
                smsheader.languageTable = abyte0.read();
                break;

            case 1: // '\001'
                SpecialSmsMsg specialsmsmsg = new SpecialSmsMsg();
                specialsmsmsg.msgIndType = abyte0.read();
                specialsmsmsg.msgCount = abyte0.read();
                smsheader.specialSmsMsgList.add(specialsmsmsg);
                break;
            }
        } while(true);
        return smsheader;
    }

    public static byte[] toByteArray(SmsHeader smsheader)
    {
        if(smsheader.portAddrs == null && smsheader.concatRef == null && smsheader.specialSmsMsgList.isEmpty() && smsheader.miscEltList.isEmpty() && smsheader.languageShiftTable == 0 && smsheader.languageTable == 0)
            return null;
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(140);
        Object obj = smsheader.concatRef;
        if(obj != null)
        {
            Iterator iterator1;
            if(((ConcatRef) (obj)).isEightBits)
            {
                bytearrayoutputstream.write(0);
                bytearrayoutputstream.write(3);
                bytearrayoutputstream.write(((ConcatRef) (obj)).refNumber);
            } else
            {
                bytearrayoutputstream.write(8);
                bytearrayoutputstream.write(4);
                bytearrayoutputstream.write(((ConcatRef) (obj)).refNumber >>> 8);
                bytearrayoutputstream.write(((ConcatRef) (obj)).refNumber & 0xff);
            }
            bytearrayoutputstream.write(((ConcatRef) (obj)).msgCount);
            bytearrayoutputstream.write(((ConcatRef) (obj)).seqNumber);
        }
        obj = smsheader.portAddrs;
        if(obj != null)
            if(((PortAddrs) (obj)).areEightBits)
            {
                bytearrayoutputstream.write(4);
                bytearrayoutputstream.write(2);
                bytearrayoutputstream.write(((PortAddrs) (obj)).destPort);
                bytearrayoutputstream.write(((PortAddrs) (obj)).origPort);
            } else
            {
                bytearrayoutputstream.write(5);
                bytearrayoutputstream.write(4);
                bytearrayoutputstream.write(((PortAddrs) (obj)).destPort >>> 8);
                bytearrayoutputstream.write(((PortAddrs) (obj)).destPort & 0xff);
                bytearrayoutputstream.write(((PortAddrs) (obj)).origPort >>> 8);
                bytearrayoutputstream.write(((PortAddrs) (obj)).origPort & 0xff);
            }
        if(smsheader.languageShiftTable != 0)
        {
            bytearrayoutputstream.write(36);
            bytearrayoutputstream.write(1);
            bytearrayoutputstream.write(smsheader.languageShiftTable);
        }
        if(smsheader.languageTable != 0)
        {
            bytearrayoutputstream.write(37);
            bytearrayoutputstream.write(1);
            bytearrayoutputstream.write(smsheader.languageTable);
        }
        for(iterator1 = smsheader.specialSmsMsgList.iterator(); iterator1.hasNext(); bytearrayoutputstream.write(((SpecialSmsMsg) (obj)).msgCount & 0xff))
        {
            obj = (SpecialSmsMsg)iterator1.next();
            bytearrayoutputstream.write(1);
            bytearrayoutputstream.write(2);
            bytearrayoutputstream.write(((SpecialSmsMsg) (obj)).msgIndType & 0xff);
        }

        for(Iterator iterator = smsheader.miscEltList.iterator(); iterator.hasNext(); bytearrayoutputstream.write(((MiscElt) (smsheader)).data, 0, ((MiscElt) (smsheader)).data.length))
        {
            smsheader = (MiscElt)iterator.next();
            bytearrayoutputstream.write(((MiscElt) (smsheader)).id);
            bytearrayoutputstream.write(((MiscElt) (smsheader)).data.length);
        }

        return bytearrayoutputstream.toByteArray();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("UserDataHeader ");
        stringbuilder.append("{ ConcatRef ");
        if(concatRef == null)
        {
            stringbuilder.append("unset");
        } else
        {
            stringbuilder.append("{ refNumber=").append(concatRef.refNumber);
            stringbuilder.append(", msgCount=").append(concatRef.msgCount);
            stringbuilder.append(", seqNumber=").append(concatRef.seqNumber);
            stringbuilder.append(", isEightBits=").append(concatRef.isEightBits);
            stringbuilder.append(" }");
        }
        stringbuilder.append(", PortAddrs ");
        if(portAddrs == null)
        {
            stringbuilder.append("unset");
        } else
        {
            stringbuilder.append("{ destPort=").append(portAddrs.destPort);
            stringbuilder.append(", origPort=").append(portAddrs.origPort);
            stringbuilder.append(", areEightBits=").append(portAddrs.areEightBits);
            stringbuilder.append(" }");
        }
        if(languageShiftTable != 0)
            stringbuilder.append(", languageShiftTable=").append(languageShiftTable);
        if(languageTable != 0)
            stringbuilder.append(", languageTable=").append(languageTable);
        for(Iterator iterator = specialSmsMsgList.iterator(); iterator.hasNext(); stringbuilder.append(" }"))
        {
            SpecialSmsMsg specialsmsmsg = (SpecialSmsMsg)iterator.next();
            stringbuilder.append(", SpecialSmsMsg ");
            stringbuilder.append("{ msgIndType=").append(specialsmsmsg.msgIndType);
            stringbuilder.append(", msgCount=").append(specialsmsmsg.msgCount);
        }

        for(Iterator iterator1 = miscEltList.iterator(); iterator1.hasNext(); stringbuilder.append(" }"))
        {
            MiscElt miscelt = (MiscElt)iterator1.next();
            stringbuilder.append(", MiscElt ");
            stringbuilder.append("{ id=").append(miscelt.id);
            stringbuilder.append(", length=").append(miscelt.data.length);
            stringbuilder.append(", data=").append(HexDump.toHexString(miscelt.data));
        }

        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public static final int ELT_ID_APPLICATION_PORT_ADDRESSING_16_BIT = 5;
    public static final int ELT_ID_APPLICATION_PORT_ADDRESSING_8_BIT = 4;
    public static final int ELT_ID_CHARACTER_SIZE_WVG_OBJECT = 25;
    public static final int ELT_ID_COMPRESSION_CONTROL = 22;
    public static final int ELT_ID_CONCATENATED_16_BIT_REFERENCE = 8;
    public static final int ELT_ID_CONCATENATED_8_BIT_REFERENCE = 0;
    public static final int ELT_ID_ENHANCED_VOICE_MAIL_INFORMATION = 35;
    public static final int ELT_ID_EXTENDED_OBJECT = 20;
    public static final int ELT_ID_EXTENDED_OBJECT_DATA_REQUEST_CMD = 26;
    public static final int ELT_ID_HYPERLINK_FORMAT_ELEMENT = 33;
    public static final int ELT_ID_LARGE_ANIMATION = 14;
    public static final int ELT_ID_LARGE_PICTURE = 16;
    public static final int ELT_ID_NATIONAL_LANGUAGE_LOCKING_SHIFT = 37;
    public static final int ELT_ID_NATIONAL_LANGUAGE_SINGLE_SHIFT = 36;
    public static final int ELT_ID_OBJECT_DISTR_INDICATOR = 23;
    public static final int ELT_ID_PREDEFINED_ANIMATION = 13;
    public static final int ELT_ID_PREDEFINED_SOUND = 11;
    public static final int ELT_ID_REPLY_ADDRESS_ELEMENT = 34;
    public static final int ELT_ID_REUSED_EXTENDED_OBJECT = 21;
    public static final int ELT_ID_RFC_822_EMAIL_HEADER = 32;
    public static final int ELT_ID_SMALL_ANIMATION = 15;
    public static final int ELT_ID_SMALL_PICTURE = 17;
    public static final int ELT_ID_SMSC_CONTROL_PARAMS = 6;
    public static final int ELT_ID_SPECIAL_SMS_MESSAGE_INDICATION = 1;
    public static final int ELT_ID_STANDARD_WVG_OBJECT = 24;
    public static final int ELT_ID_TEXT_FORMATTING = 10;
    public static final int ELT_ID_UDH_SOURCE_INDICATION = 7;
    public static final int ELT_ID_USER_DEFINED_SOUND = 12;
    public static final int ELT_ID_USER_PROMPT_INDICATOR = 19;
    public static final int ELT_ID_VARIABLE_PICTURE = 18;
    public static final int ELT_ID_WIRELESS_CTRL_MSG_PROTOCOL = 9;
    public static final int PORT_WAP_PUSH = 2948;
    public static final int PORT_WAP_WSP = 9200;
    public ConcatRef concatRef;
    public int languageShiftTable;
    public int languageTable;
    public ArrayList miscEltList;
    public PortAddrs portAddrs;
    public ArrayList specialSmsMsgList;
}
