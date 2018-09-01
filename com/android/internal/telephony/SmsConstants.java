// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;


public class SmsConstants
{
    public static final class MessageClass extends Enum
    {

        public static MessageClass valueOf(String s)
        {
            return (MessageClass)Enum.valueOf(com/android/internal/telephony/SmsConstants$MessageClass, s);
        }

        public static MessageClass[] values()
        {
            return $VALUES;
        }

        private static final MessageClass $VALUES[];
        public static final MessageClass CLASS_0;
        public static final MessageClass CLASS_1;
        public static final MessageClass CLASS_2;
        public static final MessageClass CLASS_3;
        public static final MessageClass UNKNOWN;

        static 
        {
            UNKNOWN = new MessageClass("UNKNOWN", 0);
            CLASS_0 = new MessageClass("CLASS_0", 1);
            CLASS_1 = new MessageClass("CLASS_1", 2);
            CLASS_2 = new MessageClass("CLASS_2", 3);
            CLASS_3 = new MessageClass("CLASS_3", 4);
            $VALUES = (new MessageClass[] {
                UNKNOWN, CLASS_0, CLASS_1, CLASS_2, CLASS_3
            });
        }

        private MessageClass(String s, int i)
        {
            super(s, i);
        }
    }


    public SmsConstants()
    {
    }

    public static final int ENCODING_16BIT = 3;
    public static final int ENCODING_7BIT = 1;
    public static final int ENCODING_8BIT = 2;
    public static final int ENCODING_KSC5601 = 4;
    public static final int ENCODING_UNKNOWN = 0;
    public static final String FORMAT_3GPP = "3gpp";
    public static final String FORMAT_3GPP2 = "3gpp2";
    public static final String FORMAT_UNKNOWN = "unknown";
    public static final int MAX_USER_DATA_BYTES = 140;
    public static final int MAX_USER_DATA_BYTES_WITH_HEADER = 134;
    public static final int MAX_USER_DATA_SEPTETS = 160;
    public static final int MAX_USER_DATA_SEPTETS_WITH_HEADER = 153;
}
