// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;


public class DisconnectCause
{

    private DisconnectCause()
    {
    }

    public static String toString(int i)
    {
        switch(i)
        {
        case 6: // '\006'
        case 60: // '<'
        case 94: // '^'
        default:
            return (new StringBuilder()).append("INVALID: ").append(i).toString();

        case 0: // '\0'
            return "NOT_DISCONNECTED";

        case 1: // '\001'
            return "INCOMING_MISSED";

        case 2: // '\002'
            return "NORMAL";

        case 3: // '\003'
            return "LOCAL";

        case 4: // '\004'
            return "BUSY";

        case 5: // '\005'
            return "CONGESTION";

        case 7: // '\007'
            return "INVALID_NUMBER";

        case 8: // '\b'
            return "NUMBER_UNREACHABLE";

        case 9: // '\t'
            return "SERVER_UNREACHABLE";

        case 10: // '\n'
            return "INVALID_CREDENTIALS";

        case 11: // '\013'
            return "OUT_OF_NETWORK";

        case 12: // '\f'
            return "SERVER_ERROR";

        case 13: // '\r'
            return "TIMED_OUT";

        case 14: // '\016'
            return "LOST_SIGNAL";

        case 15: // '\017'
            return "LIMIT_EXCEEDED";

        case 16: // '\020'
            return "INCOMING_REJECTED";

        case 17: // '\021'
            return "POWER_OFF";

        case 18: // '\022'
            return "OUT_OF_SERVICE";

        case 19: // '\023'
            return "ICC_ERROR";

        case 20: // '\024'
            return "CALL_BARRED";

        case 21: // '\025'
            return "FDN_BLOCKED";

        case 22: // '\026'
            return "CS_RESTRICTED";

        case 23: // '\027'
            return "CS_RESTRICTED_NORMAL";

        case 24: // '\030'
            return "CS_RESTRICTED_EMERGENCY";

        case 25: // '\031'
            return "UNOBTAINABLE_NUMBER";

        case 26: // '\032'
            return "CDMA_LOCKED_UNTIL_POWER_CYCLE";

        case 27: // '\033'
            return "CDMA_DROP";

        case 28: // '\034'
            return "CDMA_INTERCEPT";

        case 29: // '\035'
            return "CDMA_REORDER";

        case 30: // '\036'
            return "CDMA_SO_REJECT";

        case 31: // '\037'
            return "CDMA_RETRY_ORDER";

        case 32: // ' '
            return "CDMA_ACCESS_FAILURE";

        case 33: // '!'
            return "CDMA_PREEMPTED";

        case 34: // '"'
            return "CDMA_NOT_EMERGENCY";

        case 35: // '#'
            return "CDMA_ACCESS_BLOCKED";

        case 37: // '%'
            return "EMERGENCY_ONLY";

        case 38: // '&'
            return "NO_PHONE_NUMBER_SUPPLIED";

        case 39: // '\''
            return "DIALED_MMI";

        case 40: // '('
            return "VOICEMAIL_NUMBER_MISSING";

        case 41: // ')'
            return "CDMA_CALL_LOST";

        case 42: // '*'
            return "EXITED_ECM";

        case 46: // '.'
            return "DIAL_MODIFIED_TO_USSD";

        case 47: // '/'
            return "DIAL_MODIFIED_TO_SS";

        case 48: // '0'
            return "DIAL_MODIFIED_TO_DIAL";

        case 111: // 'o'
            return "DIAL_MODIFIED_TO_DIAL_VIDEO";

        case 112: // 'p'
            return "DIAL_VIDEO_MODIFIED_TO_SS";

        case 113: // 'q'
            return "DIAL_VIDEO_MODIFIED_TO_USSD";

        case 114: // 'r'
            return "DIAL_VIDEO_MODIFIED_TO_DIAL";

        case 115: // 's'
            return "DIAL_VIDEO_MODIFIED_TO_DIAL_VIDEO";

        case 36: // '$'
            return "ERROR_UNSPECIFIED";

        case 43: // '+'
            return "OUTGOING_FAILURE";

        case 44: // ','
            return "OUTGOING_CANCELED";

        case 45: // '-'
            return "IMS_MERGED_SUCCESSFULLY";

        case 49: // '1'
            return "CDMA_ALREADY_ACTIVATED";

        case 50: // '2'
            return "VIDEO_CALL_NOT_ALLOWED_WHILE_TTY_ENABLED";

        case 51: // '3'
            return "CALL_PULLED";

        case 52: // '4'
            return "ANSWERED_ELSEWHERE";

        case 53: // '5'
            return "MAXIMUM_NUMER_OF_CALLS_REACHED";

        case 54: // '6'
            return "DATA_DISABLED";

        case 55: // '7'
            return "DATA_LIMIT_REACHED";

        case 56: // '8'
            return "DIALED_ON_WRONG_SLOT";

        case 57: // '9'
            return "DIALED_CALL_FORWARDING_WHILE_ROAMING";

        case 58: // ':'
            return "IMEI_NOT_ACCEPTED";

        case 59: // ';'
            return "WIFI_LOST";

        case 96: // '`'
            return "NO_CIRCUIT_AVAIL";

        case 97: // 'a'
            return "NO_ROUTE_TO_DESTINAON";

        case 98: // 'b'
            return "OPERATOR_DETERMINED_BARRING";

        case 99: // 'c'
            return "CALL_FAIL_NO_USER_RESPONDING";

        case 100: // 'd'
            return "CALL_FAIL_NO_ANSWER_FROM_USER";

        case 101: // 'e'
            return "CALL_FAIL_DESTINATION_OUT_OF_ORDER";

        case 102: // 'f'
            return "BEARER_CAPABILITY_NOT_AUTHORIZED";

        case 103: // 'g'
            return "CHANNEL_UNACCEPTABLE";

        case 104: // 'h'
            return "CALL_REJECTED";

        case 61: // '='
            return "PREEMPTION";

        case 62: // '>'
            return "FACILITY_REJECTED";

        case 63: // '?'
            return "RESP_TO_STATUS_ENQUIRY";

        case 64: // '@'
            return "NORMAL_UNSPECIFIED";

        case 65: // 'A'
            return "NETWORK_OUT_OF_ORDER";

        case 66: // 'B'
            return "TEMPORARY_FAILURE";

        case 67: // 'C'
            return "SWITCHING_EQUIPMENT_CONGESTION";

        case 68: // 'D'
            return "ACCESS_INFORMATION_DISCARDED";

        case 69: // 'E'
            return "REQUESTED_CIRCUIT_OR_CHANNEL_NOT_AVAILABLE";

        case 70: // 'F'
            return "RESOURCES_UNAVAILABLE_OR_UNSPECIFIED";

        case 71: // 'G'
            return "QOS_UNAVAILABLE";

        case 72: // 'H'
            return "REQUESTED_FACILITY_NOT_SUBSCRIBED";

        case 73: // 'I'
            return "INCOMING_CALLS_BARRED_WITHIN_CUG";

        case 74: // 'J'
            return "BEARER_CAPABILITY_UNAVAILABLE";

        case 75: // 'K'
            return "SERVICE_OPTION_NOT_AVAILABLE";

        case 76: // 'L'
            return "BEARER_SERVICE_NOT_IMPLEMENTED";

        case 77: // 'M'
            return "REQUESTED_FACILITY_NOT_IMPLEMENTED";

        case 78: // 'N'
            return "ONLY_DIGITAL_INFORMATION_BEARER_AVAILABLE";

        case 79: // 'O'
            return "SERVICE_OR_OPTION_NOT_IMPLEMENTED";

        case 80: // 'P'
            return "INVALID_TRANSACTION_IDENTIFIER";

        case 81: // 'Q'
            return "USER_NOT_MEMBER_OF_CUG";

        case 82: // 'R'
            return "INCOMPATIBLE_DESTINATION";

        case 83: // 'S'
            return "INVALID_TRANSIT_NW_SELECTION";

        case 84: // 'T'
            return "SEMANTICALLY_INCORRECT_MESSAGE";

        case 85: // 'U'
            return "INVALID_MANDATORY_INFORMATION";

        case 86: // 'V'
            return "MESSAGE_TYPE_NON_IMPLEMENTED";

        case 87: // 'W'
            return "MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE";

        case 88: // 'X'
            return "INFORMATION_ELEMENT_NON_EXISTENT";

        case 89: // 'Y'
            return "CONDITIONAL_IE_ERROR";

        case 90: // 'Z'
            return "MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE";

        case 91: // '['
            return "RECOVERY_ON_TIMER_EXPIRED";

        case 92: // '\\'
            return "PROTOCOL_ERROR_UNSPECIFIED";

        case 93: // ']'
            return "INTERWORKING_UNSPECIFIED";

        case 105: // 'i'
            return "EMERGENCY_TEMP_FAILURE";

        case 106: // 'j'
            return "EMERGENCY_PERM_FAILURE";

        case 107: // 'k'
            return "HO_NOT_FEASIBLE";

        case 108: // 'l'
            return "NON_SELECTED_USER_CLEARING";

        case 109: // 'm'
            return "IMS_ACCESS_BLOCKED";

        case 95: // '_'
            return "LOW_BATTERY";

        case 110: // 'n'
            return "DIAL_LOW_BATTERY";
        }
    }

    public static final int ACCESS_INFORMATION_DISCARDED = 68;
    public static final int ANSWERED_ELSEWHERE = 52;
    public static final int BEARER_CAPABILITY_NOT_AUTHORIZED = 102;
    public static final int BEARER_CAPABILITY_UNAVAILABLE = 74;
    public static final int BEARER_SERVICE_NOT_IMPLEMENTED = 76;
    public static final int BUSY = 4;
    public static final int CALL_BARRED = 20;
    public static final int CALL_FAIL_DESTINATION_OUT_OF_ORDER = 101;
    public static final int CALL_FAIL_NO_ANSWER_FROM_USER = 100;
    public static final int CALL_FAIL_NO_USER_RESPONDING = 99;
    public static final int CALL_PULLED = 51;
    public static final int CALL_REJECTED = 104;
    public static final int CDMA_ACCESS_BLOCKED = 35;
    public static final int CDMA_ACCESS_FAILURE = 32;
    public static final int CDMA_ALREADY_ACTIVATED = 49;
    public static final int CDMA_CALL_LOST = 41;
    public static final int CDMA_DROP = 27;
    public static final int CDMA_INTERCEPT = 28;
    public static final int CDMA_LOCKED_UNTIL_POWER_CYCLE = 26;
    public static final int CDMA_NOT_EMERGENCY = 34;
    public static final int CDMA_PREEMPTED = 33;
    public static final int CDMA_REORDER = 29;
    public static final int CDMA_RETRY_ORDER = 31;
    public static final int CDMA_SO_REJECT = 30;
    public static final int CHANNEL_UNACCEPTABLE = 103;
    public static final int CONDITIONAL_IE_ERROR = 89;
    public static final int CONGESTION = 5;
    public static final int CS_RESTRICTED = 22;
    public static final int CS_RESTRICTED_EMERGENCY = 24;
    public static final int CS_RESTRICTED_NORMAL = 23;
    public static final int DATA_DISABLED = 54;
    public static final int DATA_LIMIT_REACHED = 55;
    public static final int DIALED_CALL_FORWARDING_WHILE_ROAMING = 57;
    public static final int DIALED_MMI = 39;
    public static final int DIALED_ON_WRONG_SLOT = 56;
    public static final int DIAL_LOW_BATTERY = 110;
    public static final int DIAL_MODIFIED_TO_DIAL = 48;
    public static final int DIAL_MODIFIED_TO_DIAL_VIDEO = 111;
    public static final int DIAL_MODIFIED_TO_SS = 47;
    public static final int DIAL_MODIFIED_TO_USSD = 46;
    public static final int DIAL_VIDEO_MODIFIED_TO_DIAL = 114;
    public static final int DIAL_VIDEO_MODIFIED_TO_DIAL_VIDEO = 115;
    public static final int DIAL_VIDEO_MODIFIED_TO_SS = 112;
    public static final int DIAL_VIDEO_MODIFIED_TO_USSD = 113;
    public static final int EMERGENCY_ONLY = 37;
    public static final int EMERGENCY_PERM_FAILURE = 106;
    public static final int EMERGENCY_TEMP_FAILURE = 105;
    public static final int ERROR_UNSPECIFIED = 36;
    public static final int EXITED_ECM = 42;
    public static final int FACILITY_REJECTED = 62;
    public static final int FDN_BLOCKED = 21;
    public static final int HO_NOT_FEASIBLE = 107;
    public static final int ICC_ERROR = 19;
    public static final int IMEI_NOT_ACCEPTED = 58;
    public static final int IMS_ACCESS_BLOCKED = 109;
    public static final int IMS_MERGED_SUCCESSFULLY = 45;
    public static final int INCOMING_CALLS_BARRED_WITHIN_CUG = 73;
    public static final int INCOMING_MISSED = 1;
    public static final int INCOMING_REJECTED = 16;
    public static final int INCOMPATIBLE_DESTINATION = 82;
    public static final int INFORMATION_ELEMENT_NON_EXISTENT = 88;
    public static final int INTERWORKING_UNSPECIFIED = 93;
    public static final int INVALID_CREDENTIALS = 10;
    public static final int INVALID_MANDATORY_INFORMATION = 85;
    public static final int INVALID_NUMBER = 7;
    public static final int INVALID_TRANSACTION_IDENTIFIER = 80;
    public static final int INVALID_TRANSIT_NW_SELECTION = 83;
    public static final int LIMIT_EXCEEDED = 15;
    public static final int LOCAL = 3;
    public static final int LOCAL_LOW_BATTERY = 94;
    public static final int LOST_SIGNAL = 14;
    public static final int LOW_BATTERY = 95;
    public static final int MAXIMUM_NUMBER_OF_CALLS_REACHED = 53;
    public static final int MAXIMUM_VALID_VALUE = 115;
    public static final int MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE = 90;
    public static final int MESSAGE_TYPE_NON_IMPLEMENTED = 86;
    public static final int MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE = 87;
    public static final int MINIMUM_VALID_VALUE = 0;
    public static final int MMI = 6;
    public static final int NETWORK_OUT_OF_ORDER = 65;
    public static final int NON_SELECTED_USER_CLEARING = 108;
    public static final int NORMAL = 2;
    public static final int NORMAL_UNSPECIFIED = 64;
    public static final int NOT_DISCONNECTED = 0;
    public static final int NOT_VALID = -1;
    public static final int NO_CIRCUIT_AVAIL = 96;
    public static final int NO_PHONE_NUMBER_SUPPLIED = 38;
    public static final int NO_ROUTE_TO_DESTINAON = 97;
    public static final int NUMBER_CHANGED = 60;
    public static final int NUMBER_UNREACHABLE = 8;
    public static final int ONLY_DIGITAL_INFORMATION_BEARER_AVAILABLE = 78;
    public static final int OPERATOR_DETERMINED_BARRING = 98;
    public static final int OUTGOING_CANCELED = 44;
    public static final int OUTGOING_FAILURE = 43;
    public static final int OUT_OF_NETWORK = 11;
    public static final int OUT_OF_SERVICE = 18;
    public static final int POWER_OFF = 17;
    public static final int PREEMPTION = 61;
    public static final int PROTOCOL_ERROR_UNSPECIFIED = 92;
    public static final int QOS_UNAVAILABLE = 71;
    public static final int RECOVERY_ON_TIMER_EXPIRED = 91;
    public static final int REQUESTED_CIRCUIT_OR_CHANNEL_NOT_AVAILABLE = 69;
    public static final int REQUESTED_FACILITY_NOT_IMPLEMENTED = 77;
    public static final int REQUESTED_FACILITY_NOT_SUBSCRIBED = 72;
    public static final int RESOURCES_UNAVAILABLE_OR_UNSPECIFIED = 70;
    public static final int RESP_TO_STATUS_ENQUIRY = 63;
    public static final int SEMANTICALLY_INCORRECT_MESSAGE = 84;
    public static final int SERVER_ERROR = 12;
    public static final int SERVER_UNREACHABLE = 9;
    public static final int SERVICE_OPTION_NOT_AVAILABLE = 75;
    public static final int SERVICE_OR_OPTION_NOT_IMPLEMENTED = 79;
    public static final int SWITCHING_EQUIPMENT_CONGESTION = 67;
    public static final int TEMPORARY_FAILURE = 66;
    public static final int TIMED_OUT = 13;
    public static final int UNOBTAINABLE_NUMBER = 25;
    public static final int USER_NOT_MEMBER_OF_CUG = 81;
    public static final int VIDEO_CALL_NOT_ALLOWED_WHILE_TTY_ENABLED = 50;
    public static final int VOICEMAIL_NUMBER_MISSING = 40;
    public static final int WIFI_LOST = 59;
}
