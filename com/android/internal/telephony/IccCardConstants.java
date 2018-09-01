// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;


public class IccCardConstants
{
    public static final class State extends Enum
    {

        public static State intToState(int i)
            throws IllegalArgumentException
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException();

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return ABSENT;

            case 2: // '\002'
                return PIN_REQUIRED;

            case 3: // '\003'
                return PUK_REQUIRED;

            case 4: // '\004'
                return NETWORK_LOCKED;

            case 5: // '\005'
                return READY;

            case 6: // '\006'
                return NOT_READY;

            case 7: // '\007'
                return PERM_DISABLED;

            case 8: // '\b'
                return CARD_IO_ERROR;

            case 9: // '\t'
                return CARD_RESTRICTED;
            }
        }

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/android/internal/telephony/IccCardConstants$State, s);
        }

        public static State[] values()
        {
            return $VALUES;
        }

        public boolean iccCardExist()
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(this == PIN_REQUIRED) goto _L2; else goto _L1
_L1:
            if(this != PUK_REQUIRED) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(this != NETWORK_LOCKED)
            {
                flag1 = flag;
                if(this != READY)
                {
                    flag1 = flag;
                    if(this != PERM_DISABLED)
                    {
                        flag1 = flag;
                        if(this != CARD_IO_ERROR)
                        {
                            flag1 = flag;
                            if(this != CARD_RESTRICTED)
                                flag1 = false;
                        }
                    }
                }
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public boolean isPinLocked()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(this != PIN_REQUIRED)
                if(this == PUK_REQUIRED)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        private static final State $VALUES[];
        public static final State ABSENT;
        public static final State CARD_IO_ERROR;
        public static final State CARD_RESTRICTED;
        public static final State NETWORK_LOCKED;
        public static final State NOT_READY;
        public static final State PERM_DISABLED;
        public static final State PIN_REQUIRED;
        public static final State PUK_REQUIRED;
        public static final State READY;
        public static final State UNKNOWN;

        static 
        {
            UNKNOWN = new State("UNKNOWN", 0);
            ABSENT = new State("ABSENT", 1);
            PIN_REQUIRED = new State("PIN_REQUIRED", 2);
            PUK_REQUIRED = new State("PUK_REQUIRED", 3);
            NETWORK_LOCKED = new State("NETWORK_LOCKED", 4);
            READY = new State("READY", 5);
            NOT_READY = new State("NOT_READY", 6);
            PERM_DISABLED = new State("PERM_DISABLED", 7);
            CARD_IO_ERROR = new State("CARD_IO_ERROR", 8);
            CARD_RESTRICTED = new State("CARD_RESTRICTED", 9);
            $VALUES = (new State[] {
                UNKNOWN, ABSENT, PIN_REQUIRED, PUK_REQUIRED, NETWORK_LOCKED, READY, NOT_READY, PERM_DISABLED, CARD_IO_ERROR, CARD_RESTRICTED
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    public IccCardConstants()
    {
    }

    public static final String INTENT_KEY_ICC_STATE = "ss";
    public static final String INTENT_KEY_LOCKED_REASON = "reason";
    public static final String INTENT_VALUE_ABSENT_ON_PERM_DISABLED = "PERM_DISABLED";
    public static final String INTENT_VALUE_ICC_ABSENT = "ABSENT";
    public static final String INTENT_VALUE_ICC_CARD_IO_ERROR = "CARD_IO_ERROR";
    public static final String INTENT_VALUE_ICC_CARD_RESTRICTED = "CARD_RESTRICTED";
    public static final String INTENT_VALUE_ICC_IMSI = "IMSI";
    public static final String INTENT_VALUE_ICC_INTERNAL_LOCKED = "INTERNAL_LOCKED";
    public static final String INTENT_VALUE_ICC_LOADED = "LOADED";
    public static final String INTENT_VALUE_ICC_LOCKED = "LOCKED";
    public static final String INTENT_VALUE_ICC_NOT_READY = "NOT_READY";
    public static final String INTENT_VALUE_ICC_READY = "READY";
    public static final String INTENT_VALUE_ICC_UNKNOWN = "UNKNOWN";
    public static final String INTENT_VALUE_LOCKED_NETWORK = "NETWORK";
    public static final String INTENT_VALUE_LOCKED_ON_PIN = "PIN";
    public static final String INTENT_VALUE_LOCKED_ON_PUK = "PUK";
}
