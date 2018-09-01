// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.Parcel;
import android.os.Parcelable;

public class ImsReasonInfo
    implements Parcelable
{

    public ImsReasonInfo()
    {
        mCode = 0;
        mExtraCode = 0;
        mExtraMessage = null;
    }

    public ImsReasonInfo(int i, int j)
    {
        mCode = i;
        mExtraCode = j;
        mExtraMessage = null;
    }

    public ImsReasonInfo(int i, int j, String s)
    {
        mCode = i;
        mExtraCode = j;
        mExtraMessage = s;
    }

    public ImsReasonInfo(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        mCode = parcel.readInt();
        mExtraCode = parcel.readInt();
        mExtraMessage = parcel.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCode()
    {
        return mCode;
    }

    public int getExtraCode()
    {
        return mExtraCode;
    }

    public String getExtraMessage()
    {
        return mExtraMessage;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ImsReasonInfo :: {").append(mCode).append(", ").append(mExtraCode).append(", ").append(mExtraMessage).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCode);
        parcel.writeInt(mExtraCode);
        parcel.writeString(mExtraMessage);
    }

    public static final int CODE_ACCESS_CLASS_BLOCKED = 1512;
    public static final int CODE_ANSWERED_ELSEWHERE = 1014;
    public static final int CODE_BLACKLISTED_CALL_ID = 506;
    public static final int CODE_CALL_DROP_IWLAN_TO_LTE_UNAVAILABLE = 1100;
    public static final int CODE_CALL_END_CAUSE_CALL_PULL = 1016;
    public static final int CODE_CALL_PULL_OUT_OF_SYNC = 1015;
    public static final int CODE_DATA_DISABLED = 1406;
    public static final int CODE_DATA_LIMIT_REACHED = 1405;
    public static final int CODE_DIAL_MODIFIED_TO_DIAL = 246;
    public static final int CODE_DIAL_MODIFIED_TO_DIAL_VIDEO = 247;
    public static final int CODE_DIAL_MODIFIED_TO_SS = 245;
    public static final int CODE_DIAL_MODIFIED_TO_USSD = 244;
    public static final int CODE_DIAL_VIDEO_MODIFIED_TO_DIAL = 248;
    public static final int CODE_DIAL_VIDEO_MODIFIED_TO_DIAL_VIDEO = 249;
    public static final int CODE_DIAL_VIDEO_MODIFIED_TO_SS = 250;
    public static final int CODE_DIAL_VIDEO_MODIFIED_TO_USSD = 251;
    public static final int CODE_ECBM_NOT_SUPPORTED = 901;
    public static final int CODE_EMERGENCY_PERM_FAILURE = 364;
    public static final int CODE_EMERGENCY_TEMP_FAILURE = 363;
    public static final int CODE_EPDG_TUNNEL_ESTABLISH_FAILURE = 1400;
    public static final int CODE_EPDG_TUNNEL_LOST_CONNECTION = 1402;
    public static final int CODE_EPDG_TUNNEL_REKEY_FAILURE = 1401;
    public static final int CODE_FDN_BLOCKED = 241;
    public static final int CODE_IKEV2_AUTH_FAILURE = 1408;
    public static final int CODE_IMEI_NOT_ACCEPTED = 243;
    public static final int CODE_IWLAN_DPD_FAILURE = 1300;
    public static final int CODE_LOCAL_CALL_BUSY = 142;
    public static final int CODE_LOCAL_CALL_CS_RETRY_REQUIRED = 146;
    public static final int CODE_LOCAL_CALL_DECLINE = 143;
    public static final int CODE_LOCAL_CALL_EXCEEDED = 141;
    public static final int CODE_LOCAL_CALL_RESOURCE_RESERVATION_FAILED = 145;
    public static final int CODE_LOCAL_CALL_TERMINATED = 148;
    public static final int CODE_LOCAL_CALL_VCC_ON_PROGRESSING = 144;
    public static final int CODE_LOCAL_CALL_VOLTE_RETRY_REQUIRED = 147;
    public static final int CODE_LOCAL_ENDED_BY_CONFERENCE_MERGE = 108;
    public static final int CODE_LOCAL_HO_NOT_FEASIBLE = 149;
    public static final int CODE_LOCAL_ILLEGAL_ARGUMENT = 101;
    public static final int CODE_LOCAL_ILLEGAL_STATE = 102;
    public static final int CODE_LOCAL_IMS_SERVICE_DOWN = 106;
    public static final int CODE_LOCAL_INTERNAL_ERROR = 103;
    public static final int CODE_LOCAL_LOW_BATTERY = 112;
    public static final int CODE_LOCAL_NETWORK_IP_CHANGED = 124;
    public static final int CODE_LOCAL_NETWORK_NO_LTE_COVERAGE = 122;
    public static final int CODE_LOCAL_NETWORK_NO_SERVICE = 121;
    public static final int CODE_LOCAL_NETWORK_ROAMING = 123;
    public static final int CODE_LOCAL_NOT_REGISTERED = 132;
    public static final int CODE_LOCAL_NO_PENDING_CALL = 107;
    public static final int CODE_LOCAL_POWER_OFF = 111;
    public static final int CODE_LOCAL_SERVICE_UNAVAILABLE = 131;
    public static final int CODE_LOW_BATTERY = 505;
    public static final int CODE_MAXIMUM_NUMBER_OF_CALLS_REACHED = 1403;
    public static final int CODE_MEDIA_INIT_FAILED = 401;
    public static final int CODE_MEDIA_NOT_ACCEPTABLE = 403;
    public static final int CODE_MEDIA_NO_DATA = 402;
    public static final int CODE_MEDIA_UNSPECIFIED = 404;
    public static final int CODE_MULTIENDPOINT_NOT_SUPPORTED = 902;
    public static final int CODE_NETWORK_DETACH = 1513;
    public static final int CODE_NETWORK_REJECT = 1504;
    public static final int CODE_NETWORK_RESP_TIMEOUT = 1503;
    public static final int CODE_NO_VALID_SIM = 1501;
    public static final int CODE_OEM_CAUSE_1 = 61441;
    public static final int CODE_OEM_CAUSE_10 = 61450;
    public static final int CODE_OEM_CAUSE_11 = 61451;
    public static final int CODE_OEM_CAUSE_12 = 61452;
    public static final int CODE_OEM_CAUSE_13 = 61453;
    public static final int CODE_OEM_CAUSE_14 = 61454;
    public static final int CODE_OEM_CAUSE_15 = 61455;
    public static final int CODE_OEM_CAUSE_2 = 61442;
    public static final int CODE_OEM_CAUSE_3 = 61443;
    public static final int CODE_OEM_CAUSE_4 = 61444;
    public static final int CODE_OEM_CAUSE_5 = 61445;
    public static final int CODE_OEM_CAUSE_6 = 61446;
    public static final int CODE_OEM_CAUSE_7 = 61447;
    public static final int CODE_OEM_CAUSE_8 = 61448;
    public static final int CODE_OEM_CAUSE_9 = 61449;
    public static final int CODE_RADIO_ACCESS_FAILURE = 1505;
    public static final int CODE_RADIO_INTERNAL_ERROR = 1502;
    public static final int CODE_RADIO_LINK_FAILURE = 1506;
    public static final int CODE_RADIO_LINK_LOST = 1507;
    public static final int CODE_RADIO_OFF = 1500;
    public static final int CODE_RADIO_RELEASE_ABNORMAL = 1511;
    public static final int CODE_RADIO_RELEASE_NORMAL = 1510;
    public static final int CODE_RADIO_SETUP_FAILURE = 1509;
    public static final int CODE_RADIO_UPLINK_FAILURE = 1508;
    public static final int CODE_REGISTRATION_ERROR = 1000;
    public static final int CODE_REMOTE_CALL_DECLINE = 1404;
    public static final int CODE_SIP_BAD_ADDRESS = 337;
    public static final int CODE_SIP_BAD_REQUEST = 331;
    public static final int CODE_SIP_BUSY = 338;
    public static final int CODE_SIP_CLIENT_ERROR = 342;
    public static final int CODE_SIP_FORBIDDEN = 332;
    public static final int CODE_SIP_GLOBAL_ERROR = 362;
    public static final int CODE_SIP_NOT_ACCEPTABLE = 340;
    public static final int CODE_SIP_NOT_FOUND = 333;
    public static final int CODE_SIP_NOT_REACHABLE = 341;
    public static final int CODE_SIP_NOT_SUPPORTED = 334;
    public static final int CODE_SIP_REDIRECTED = 321;
    public static final int CODE_SIP_REQUEST_CANCELLED = 339;
    public static final int CODE_SIP_REQUEST_TIMEOUT = 335;
    public static final int CODE_SIP_SERVER_ERROR = 354;
    public static final int CODE_SIP_SERVER_INTERNAL_ERROR = 351;
    public static final int CODE_SIP_SERVER_TIMEOUT = 353;
    public static final int CODE_SIP_SERVICE_UNAVAILABLE = 352;
    public static final int CODE_SIP_TEMPRARILY_UNAVAILABLE = 336;
    public static final int CODE_SIP_USER_REJECTED = 361;
    public static final int CODE_SUPP_SVC_CANCELLED = 1202;
    public static final int CODE_SUPP_SVC_FAILED = 1201;
    public static final int CODE_SUPP_SVC_REINVITE_COLLISION = 1203;
    public static final int CODE_TIMEOUT_1XX_WAITING = 201;
    public static final int CODE_TIMEOUT_NO_ANSWER = 202;
    public static final int CODE_TIMEOUT_NO_ANSWER_CALL_UPDATE = 203;
    public static final int CODE_UNSPECIFIED = 0;
    public static final int CODE_USER_DECLINE = 504;
    public static final int CODE_USER_IGNORE = 503;
    public static final int CODE_USER_NOANSWER = 502;
    public static final int CODE_USER_TERMINATED = 501;
    public static final int CODE_USER_TERMINATED_BY_REMOTE = 510;
    public static final int CODE_UT_CB_PASSWORD_MISMATCH = 821;
    public static final int CODE_UT_NETWORK_ERROR = 804;
    public static final int CODE_UT_NOT_SUPPORTED = 801;
    public static final int CODE_UT_OPERATION_NOT_ALLOWED = 803;
    public static final int CODE_UT_SERVICE_UNAVAILABLE = 802;
    public static final int CODE_UT_SS_MODIFIED_TO_DIAL = 822;
    public static final int CODE_UT_SS_MODIFIED_TO_DIAL_VIDEO = 825;
    public static final int CODE_UT_SS_MODIFIED_TO_SS = 824;
    public static final int CODE_UT_SS_MODIFIED_TO_USSD = 823;
    public static final int CODE_WIFI_LOST = 1407;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsReasonInfo createFromParcel(Parcel parcel)
        {
            return new ImsReasonInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsReasonInfo[] newArray(int i)
        {
            return new ImsReasonInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int EXTRA_CODE_CALL_RETRY_BY_SETTINGS = 3;
    public static final int EXTRA_CODE_CALL_RETRY_NORMAL = 1;
    public static final int EXTRA_CODE_CALL_RETRY_SILENT_REDIAL = 2;
    public static final String EXTRA_MSG_SERVICE_NOT_AUTHORIZED = "Forbidden. Not Authorized for Service";
    public int mCode;
    public int mExtraCode;
    public String mExtraMessage;

}
