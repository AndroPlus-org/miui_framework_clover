// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.messages.nano;

import com.android.framework.protobuf.nano.*;
import java.io.IOException;

public interface SystemMessageProto
{
    public static final class SystemMessage extends MessageNano
    {

        public static SystemMessage[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new SystemMessage[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static SystemMessage parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new SystemMessage()).mergeFrom(codedinputbytebuffernano);
        }

        public static SystemMessage parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (SystemMessage)MessageNano.mergeFrom(new SystemMessage(), abyte0);
        }

        public SystemMessage clear()
        {
            cachedSize = -1;
            return this;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public SystemMessage mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L4:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR tableswitch 0 0: default 24
        //                       0 34;
               goto _L1 _L2
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L4; else goto _L3
_L3:
            return this;
_L2:
            return this;
        }

        public static final int NOTE_ACCOUNT_CREDENTIAL_PERMISSION = 38;
        public static final int NOTE_ACCOUNT_REQUIRE_SIGNIN = 37;
        public static final int NOTE_ADB_ACTIVE = 26;
        public static final int NOTE_BAD_CHARGER = 2;
        public static final int NOTE_CAR_MODE_DISABLE = 10;
        public static final int NOTE_DUMP_HEAP_NOTIFICATION = 12;
        public static final int NOTE_FBE_ENCRYPTED_NOTIFICATION = 9;
        public static final int NOTE_FOREGROUND_SERVICES = 40;
        public static final int NOTE_GLOBAL_SCREENSHOT = 1;
        public static final int NOTE_HEAVY_WEIGHT_NOTIFICATION = 11;
        public static final int NOTE_HIDDEN_NOTIFICATIONS = 5;
        public static final int NOTE_HIGH_TEMP = 4;
        public static final int NOTE_INSTANT_APPS = 7;
        public static final int NOTE_LOGOUT_USER = 1011;
        public static final int NOTE_LOW_STORAGE = 23;
        public static final int NOTE_NETWORK_AVAILABLE = 0x1080703;
        public static final int NOTE_NETWORK_LOGGING = 1002;
        public static final int NOTE_NETWORK_LOST_INTERNET = 742;
        public static final int NOTE_NETWORK_NO_INTERNET = 741;
        public static final int NOTE_NETWORK_SIGN_IN = 740;
        public static final int NOTE_NETWORK_SWITCH = 743;
        public static final int NOTE_NET_LIMIT = 35;
        public static final int NOTE_NET_LIMIT_SNOOZED = 36;
        public static final int NOTE_NET_WARNING = 34;
        public static final int NOTE_PACKAGE_STATE = 21;
        public static final int NOTE_PLUGIN = 6;
        public static final int NOTE_POWER_LOW = 3;
        public static final int NOTE_PROFILE_WIPED = 1001;
        public static final int NOTE_REMOTE_BUGREPORT = 0x28700e57;
        public static final int NOTE_REMOVE_GUEST = 1010;
        public static final int NOTE_RETAIL_RESET = 24;
        public static final int NOTE_SELECT_INPUT_METHOD = 8;
        public static final int NOTE_SELECT_KEYBOARD_LAYOUT = 19;
        public static final int NOTE_SSL_CERT_INFO = 33;
        public static final int NOTE_STORAGE_DISK = 0x5344534b;
        public static final int NOTE_STORAGE_MOVE = 0x534d4f56;
        public static final int NOTE_STORAGE_PRIVATE = 0x53505256;
        public static final int NOTE_STORAGE_PUBLIC = 0x53505542;
        public static final int NOTE_SYNC_ERROR = 18;
        public static final int NOTE_SYSTEM_UPGRADING = 13;
        public static final int NOTE_TETHER_BLUETOOTH = 16;
        public static final int NOTE_TETHER_GENERAL = 14;
        public static final int NOTE_TETHER_USB = 15;
        public static final int NOTE_THERMAL_SHUTDOWN = 39;
        public static final int NOTE_TV_PIP = 1100;
        public static final int NOTE_UNKNOWN = 0;
        public static final int NOTE_USB_ACCESSORY = 30;
        public static final int NOTE_USB_AUDIO_ACCESSORY_NOT_SUPPORTED = 41;
        public static final int NOTE_USB_CHARGING = 32;
        public static final int NOTE_USB_MIDI = 29;
        public static final int NOTE_USB_MTP = 27;
        public static final int NOTE_USB_MTP_TAP = 25;
        public static final int NOTE_USB_PTP = 28;
        public static final int NOTE_USB_SUPPLYING = 31;
        public static final int NOTE_VPN_DISCONNECTED = 17;
        public static final int NOTE_VPN_STATUS = 20;
        public static final int NOTE_WIFI_WRONG_PASSWORD = 42;
        private static volatile SystemMessage _emptyArray[];

        public SystemMessage()
        {
            clear();
        }
    }

}
