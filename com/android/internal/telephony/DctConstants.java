// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;


public class DctConstants
{
    public static final class Activity extends Enum
    {

        public static Activity valueOf(String s)
        {
            return (Activity)Enum.valueOf(com/android/internal/telephony/DctConstants$Activity, s);
        }

        public static Activity[] values()
        {
            return $VALUES;
        }

        private static final Activity $VALUES[];
        public static final Activity DATAIN;
        public static final Activity DATAINANDOUT;
        public static final Activity DATAOUT;
        public static final Activity DORMANT;
        public static final Activity NONE;

        static 
        {
            NONE = new Activity("NONE", 0);
            DATAIN = new Activity("DATAIN", 1);
            DATAOUT = new Activity("DATAOUT", 2);
            DATAINANDOUT = new Activity("DATAINANDOUT", 3);
            DORMANT = new Activity("DORMANT", 4);
            $VALUES = (new Activity[] {
                NONE, DATAIN, DATAOUT, DATAINANDOUT, DORMANT
            });
        }

        private Activity(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/android/internal/telephony/DctConstants$State, s);
        }

        public static State[] values()
        {
            return $VALUES;
        }

        private static final State $VALUES[];
        public static final State CONNECTED;
        public static final State CONNECTING;
        public static final State DISCONNECTING;
        public static final State FAILED;
        public static final State IDLE;
        public static final State RETRYING;
        public static final State SCANNING;

        static 
        {
            IDLE = new State("IDLE", 0);
            CONNECTING = new State("CONNECTING", 1);
            SCANNING = new State("SCANNING", 2);
            CONNECTED = new State("CONNECTED", 3);
            DISCONNECTING = new State("DISCONNECTING", 4);
            FAILED = new State("FAILED", 5);
            RETRYING = new State("RETRYING", 6);
            $VALUES = (new State[] {
                IDLE, CONNECTING, SCANNING, CONNECTED, DISCONNECTING, FAILED, RETRYING
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    public DctConstants()
    {
    }

    public static final int APN_CBS_ID = 7;
    public static final int APN_DEFAULT_ID = 0;
    public static final int APN_DUN_ID = 3;
    public static final int APN_EMERGENCY_ID = 9;
    public static final int APN_FOTA_ID = 6;
    public static final int APN_HIPRI_ID = 4;
    public static final int APN_IA_ID = 8;
    public static final int APN_IMS_ID = 5;
    public static final int APN_INVALID_ID = -1;
    public static final int APN_MMS_ID = 1;
    public static final int APN_NUM_TYPES = 10;
    public static final int APN_SUPL_ID = 2;
    public static final String APN_TYPE_KEY = "apnType";
    public static final int BASE = 0x42000;
    public static final int CMD_CLEAR_PROVISIONING_SPINNER = 0x4202a;
    public static final int CMD_ENABLE_MOBILE_PROVISIONING = 0x42025;
    public static final int CMD_IS_PROVISIONING_APN = 0x42026;
    public static final int CMD_NET_STAT_POLL = 0x42028;
    public static final int CMD_SET_DEPENDENCY_MET = 0x4201f;
    public static final int CMD_SET_ENABLE_FAIL_FAST_MOBILE_DATA = 0x42024;
    public static final int CMD_SET_POLICY_DATA_ENABLE = 0x42020;
    public static final int CMD_SET_USER_DATA_ENABLE = 0x4201e;
    public static final int DISABLED = 0;
    public static final int ENABLED = 1;
    public static final int EVENT_APN_CHANGED = 0x42013;
    public static final int EVENT_CDMA_DATA_DETACHED = 0x42014;
    public static final int EVENT_CDMA_OTA_PROVISION = 0x42019;
    public static final int EVENT_CDMA_SUBSCRIPTION_SOURCE_CHANGED = 0x42015;
    public static final int EVENT_CLEAN_UP_ALL_CONNECTIONS = 0x4201d;
    public static final int EVENT_CLEAN_UP_CONNECTION = 0x42018;
    public static final int EVENT_DATA_CONNECTION_ATTACHED = 0x42010;
    public static final int EVENT_DATA_CONNECTION_DETACHED = 0x42009;
    public static final int EVENT_DATA_RAT_CHANGED = 0x42029;
    public static final int EVENT_DATA_RECONNECT = 0x4202f;
    public static final int EVENT_DATA_SETUP_COMPLETE = 0x42000;
    public static final int EVENT_DATA_SETUP_COMPLETE_ERROR = 0x42023;
    public static final int EVENT_DATA_STALL_ALARM = 0x42011;
    public static final int EVENT_DATA_STATE_CHANGED = 0x42004;
    public static final int EVENT_DEVICE_PROVISIONED_CHANGE = 0x4202b;
    public static final int EVENT_DISCONNECT_DC_RETRYING = 0x42022;
    public static final int EVENT_DISCONNECT_DONE = 0x4200f;
    public static final int EVENT_DO_RECOVERY = 0x42012;
    public static final int EVENT_ENABLE_NEW_APN = 0x4200d;
    public static final int EVENT_ICC_CHANGED = 0x42021;
    public static final int EVENT_LINK_STATE_CHANGED = 0x4200a;
    public static final int EVENT_PCO_DATA_RECEIVED = 0x4202d;
    public static final int EVENT_POLL_PDP = 0x42005;
    public static final int EVENT_PROVISIONING_APN_ALARM = 0x42027;
    public static final int EVENT_PS_RESTRICT_DISABLED = 0x42017;
    public static final int EVENT_PS_RESTRICT_ENABLED = 0x42016;
    public static final int EVENT_RADIO_AVAILABLE = 0x42001;
    public static final int EVENT_RADIO_OFF_OR_NOT_AVAILABLE = 0x42006;
    public static final int EVENT_RECORDS_LOADED = 0x42002;
    public static final int EVENT_REDIRECTION_DETECTED = 0x4202c;
    public static final int EVENT_RESET_DONE = 0x4201c;
    public static final int EVENT_RESTART_RADIO = 0x4201a;
    public static final int EVENT_RESTORE_DEFAULT_APN = 0x4200e;
    public static final int EVENT_ROAMING_OFF = 0x4200c;
    public static final int EVENT_ROAMING_ON = 0x4200b;
    public static final int EVENT_ROAMING_SETTING_CHANGE = 0x42030;
    public static final int EVENT_SET_CARRIER_DATA_ENABLED = 0x4202e;
    public static final int EVENT_SET_INTERNAL_DATA_ENABLE = 0x4201b;
    public static final int EVENT_TRY_SETUP_DATA = 0x42003;
    public static final int EVENT_VOICE_CALL_ENDED = 0x42008;
    public static final int EVENT_VOICE_CALL_STARTED = 0x42007;
    public static final int INVALID = -1;
    public static final String PROVISIONING_URL_KEY = "provisioningUrl";
}
