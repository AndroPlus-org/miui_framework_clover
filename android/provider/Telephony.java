// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.telephony.*;
import android.text.TextUtils;
import android.util.Patterns;
import android.util.SeempLog;
import com.android.internal.telephony.SmsApplication;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.provider:
//            BaseColumns

public final class Telephony
{
    public static interface BaseMmsColumns
        extends BaseColumns
    {

        public static final String ADAPTATION_ALLOWED = "adp_a";
        public static final String APPLIC_ID = "apl_id";
        public static final String AUX_APPLIC_ID = "aux_apl_id";
        public static final String CANCEL_ID = "cl_id";
        public static final String CANCEL_STATUS = "cl_st";
        public static final String CONTENT_CLASS = "ct_cls";
        public static final String CONTENT_LOCATION = "ct_l";
        public static final String CONTENT_TYPE = "ct_t";
        public static final String CREATOR = "creator";
        public static final String DATE = "date";
        public static final String DATE_SENT = "date_sent";
        public static final String DELIVERY_REPORT = "d_rpt";
        public static final String DELIVERY_TIME = "d_tm";
        public static final String DELIVERY_TIME_TOKEN = "d_tm_tok";
        public static final String DISTRIBUTION_INDICATOR = "d_ind";
        public static final String DRM_CONTENT = "drm_c";
        public static final String ELEMENT_DESCRIPTOR = "e_des";
        public static final String EXPIRY = "exp";
        public static final String LIMIT = "limit";
        public static final String LOCKED = "locked";
        public static final String MBOX_QUOTAS = "mb_qt";
        public static final String MBOX_QUOTAS_TOKEN = "mb_qt_tok";
        public static final String MBOX_TOTALS = "mb_t";
        public static final String MBOX_TOTALS_TOKEN = "mb_t_tok";
        public static final String MESSAGE_BOX = "msg_box";
        public static final int MESSAGE_BOX_ALL = 0;
        public static final int MESSAGE_BOX_DRAFTS = 3;
        public static final int MESSAGE_BOX_FAILED = 5;
        public static final int MESSAGE_BOX_INBOX = 1;
        public static final int MESSAGE_BOX_OUTBOX = 4;
        public static final int MESSAGE_BOX_SENT = 2;
        public static final String MESSAGE_CLASS = "m_cls";
        public static final String MESSAGE_COUNT = "m_cnt";
        public static final String MESSAGE_ID = "m_id";
        public static final String MESSAGE_SIZE = "m_size";
        public static final String MESSAGE_TYPE = "m_type";
        public static final String MMS_VERSION = "v";
        public static final String MM_FLAGS = "mm_flg";
        public static final String MM_FLAGS_TOKEN = "mm_flg_tok";
        public static final String MM_STATE = "mm_st";
        public static final String PREVIOUSLY_SENT_BY = "p_s_by";
        public static final String PREVIOUSLY_SENT_DATE = "p_s_d";
        public static final String PRIORITY = "pri";
        public static final String QUOTAS = "qt";
        public static final String READ = "read";
        public static final String READ_REPORT = "rr";
        public static final String READ_STATUS = "read_status";
        public static final String RECOMMENDED_RETRIEVAL_MODE = "r_r_mod";
        public static final String RECOMMENDED_RETRIEVAL_MODE_TEXT = "r_r_mod_txt";
        public static final String REPLACE_ID = "repl_id";
        public static final String REPLY_APPLIC_ID = "r_apl_id";
        public static final String REPLY_CHARGING = "r_chg";
        public static final String REPLY_CHARGING_DEADLINE = "r_chg_dl";
        public static final String REPLY_CHARGING_DEADLINE_TOKEN = "r_chg_dl_tok";
        public static final String REPLY_CHARGING_ID = "r_chg_id";
        public static final String REPLY_CHARGING_SIZE = "r_chg_sz";
        public static final String REPORT_ALLOWED = "rpt_a";
        public static final String RESPONSE_STATUS = "resp_st";
        public static final String RESPONSE_TEXT = "resp_txt";
        public static final String RETRIEVE_STATUS = "retr_st";
        public static final String RETRIEVE_TEXT = "retr_txt";
        public static final String RETRIEVE_TEXT_CHARSET = "retr_txt_cs";
        public static final String SEEN = "seen";
        public static final String SENDER_VISIBILITY = "s_vis";
        public static final String START = "start";
        public static final String STATUS = "st";
        public static final String STATUS_TEXT = "st_txt";
        public static final String STORE = "store";
        public static final String STORED = "stored";
        public static final String STORE_STATUS = "store_st";
        public static final String STORE_STATUS_TEXT = "store_st_txt";
        public static final String SUBJECT = "sub";
        public static final String SUBJECT_CHARSET = "sub_cs";
        public static final String SUBSCRIPTION_ID = "sub_id";
        public static final String TEXT_ONLY = "text_only";
        public static final String THREAD_ID = "thread_id";
        public static final String TOTALS = "totals";
        public static final String TRANSACTION_ID = "tr_id";
    }

    public static interface CanonicalAddressesColumns
        extends BaseColumns
    {

        public static final String ADDRESS = "address";
    }

    public static interface CarrierColumns
        extends BaseColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://carrier_information/carrier");
        public static final String EXPIRATION_TIME = "expiration_time";
        public static final String KEY_IDENTIFIER = "key_identifier";
        public static final String KEY_TYPE = "key_type";
        public static final String LAST_MODIFIED = "last_modified";
        public static final String MCC = "mcc";
        public static final String MNC = "mnc";
        public static final String MVNO_MATCH_DATA = "mvno_match_data";
        public static final String MVNO_TYPE = "mvno_type";
        public static final String PUBLIC_KEY = "public_key";

    }

    public static final class Carriers
        implements BaseColumns
    {

        public static final String APN = "apn";
        public static final String AUTH_TYPE = "authtype";
        public static final String BEARER = "bearer";
        public static final String BEARER_BITMASK = "bearer_bitmask";
        public static final int CARRIER_DELETED = 5;
        public static final int CARRIER_DELETED_BUT_PRESENT_IN_XML = 6;
        public static final int CARRIER_EDITED = 4;
        public static final String CARRIER_ENABLED = "carrier_enabled";
        public static final Uri CONTENT_URI = Uri.parse("content://telephony/carriers");
        public static final String CURRENT = "current";
        public static final String DEFAULT_SORT_ORDER = "name ASC";
        public static final String EDITED = "edited";
        public static final String MAX_CONNS = "max_conns";
        public static final String MAX_CONNS_TIME = "max_conns_time";
        public static final String MCC = "mcc";
        public static final String MMSC = "mmsc";
        public static final String MMSPORT = "mmsport";
        public static final String MMSPROXY = "mmsproxy";
        public static final String MNC = "mnc";
        public static final String MODEM_COGNITIVE = "modem_cognitive";
        public static final String MTU = "mtu";
        public static final String MVNO_MATCH_DATA = "mvno_match_data";
        public static final String MVNO_TYPE = "mvno_type";
        public static final String NAME = "name";
        public static final String NUMERIC = "numeric";
        public static final String PASSWORD = "password";
        public static final String PORT = "port";
        public static final String PROFILE_ID = "profile_id";
        public static final String PROTOCOL = "protocol";
        public static final String PROXY = "proxy";
        public static final String ROAMING_PROTOCOL = "roaming_protocol";
        public static final String SERVER = "server";
        public static final String SUBSCRIPTION_ID = "sub_id";
        public static final String TYPE = "type";
        public static final int UNEDITED = 0;
        public static final String USER = "user";
        public static final int USER_DELETED = 2;
        public static final int USER_DELETED_BUT_PRESENT_IN_XML = 3;
        public static final String USER_EDITABLE = "user_editable";
        public static final int USER_EDITED = 1;
        public static final String USER_VISIBLE = "user_visible";
        public static final String WAIT_TIME = "wait_time";


        private Carriers()
        {
        }
    }

    public static final class CellBroadcasts
        implements BaseColumns
    {

        public static final String CID = "cid";
        public static final String CMAS_CATEGORY = "cmas_category";
        public static final String CMAS_CERTAINTY = "cmas_certainty";
        public static final String CMAS_MESSAGE_CLASS = "cmas_message_class";
        public static final String CMAS_RESPONSE_TYPE = "cmas_response_type";
        public static final String CMAS_SEVERITY = "cmas_severity";
        public static final String CMAS_URGENCY = "cmas_urgency";
        public static final Uri CONTENT_URI = Uri.parse("content://cellbroadcasts");
        public static final String DEFAULT_SORT_ORDER = "date DESC";
        public static final String DELIVERY_TIME = "date";
        public static final String ETWS_WARNING_TYPE = "etws_warning_type";
        public static final String GEOGRAPHICAL_SCOPE = "geo_scope";
        public static final String LAC = "lac";
        public static final String LANGUAGE_CODE = "language";
        public static final String MESSAGE_BODY = "body";
        public static final String MESSAGE_FORMAT = "format";
        public static final String MESSAGE_PRIORITY = "priority";
        public static final String MESSAGE_READ = "read";
        public static final String PLMN = "plmn";
        public static final String QUERY_COLUMNS[] = {
            "_id", "geo_scope", "plmn", "lac", "cid", "serial_number", "service_category", "language", "body", "date", 
            "read", "format", "priority", "etws_warning_type", "cmas_message_class", "cmas_category", "cmas_response_type", "cmas_severity", "cmas_urgency", "cmas_certainty"
        };
        public static final String SERIAL_NUMBER = "serial_number";
        public static final String SERVICE_CATEGORY = "service_category";
        public static final String V1_MESSAGE_CODE = "message_code";
        public static final String V1_MESSAGE_IDENTIFIER = "message_id";


        private CellBroadcasts()
        {
        }
    }

    public static final class Mms
        implements BaseMmsColumns
    {

        public static String extractAddrSpec(String s)
        {
            Matcher matcher = NAME_ADDR_EMAIL_PATTERN.matcher(s);
            if(matcher.matches())
                return matcher.group(2);
            else
                return s;
        }

        public static boolean isEmailAddress(String s)
        {
            if(TextUtils.isEmpty(s))
            {
                return false;
            } else
            {
                s = extractAddrSpec(s);
                return Patterns.EMAIL_ADDRESS.matcher(s).matches();
            }
        }

        public static boolean isPhoneNumber(String s)
        {
            if(TextUtils.isEmpty(s))
                return false;
            else
                return Patterns.PHONE.matcher(s).matches();
        }

        public static Cursor query(ContentResolver contentresolver, String as[])
        {
            SeempLog.record(10);
            return contentresolver.query(CONTENT_URI, as, null, null, "date DESC");
        }

        public static Cursor query(ContentResolver contentresolver, String as[], String s, String s1)
        {
            SeempLog.record(10);
            Uri uri = CONTENT_URI;
            if(s1 == null)
                s1 = "date DESC";
            return contentresolver.query(uri, as, s, null, s1);
        }

        public static final Uri CONTENT_URI;
        public static final String DEFAULT_SORT_ORDER = "date DESC";
        public static final Pattern NAME_ADDR_EMAIL_PATTERN = Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");
        public static final Uri REPORT_REQUEST_URI;
        public static final Uri REPORT_STATUS_URI;

        static 
        {
            CONTENT_URI = Uri.parse("content://mms");
            REPORT_REQUEST_URI = Uri.withAppendedPath(CONTENT_URI, "report-request");
            REPORT_STATUS_URI = Uri.withAppendedPath(CONTENT_URI, "report-status");
        }

        private Mms()
        {
        }
    }

    public static final class Mms.Addr
        implements BaseColumns
    {

        public static final String ADDRESS = "address";
        public static final String CHARSET = "charset";
        public static final String CONTACT_ID = "contact_id";
        public static final String MSG_ID = "msg_id";
        public static final String TYPE = "type";

        private Mms.Addr()
        {
        }
    }

    public static final class Mms.Draft
        implements BaseMmsColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://mms/drafts");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Mms.Draft()
        {
        }
    }

    public static final class Mms.Inbox
        implements BaseMmsColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://mms/inbox");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Mms.Inbox()
        {
        }
    }

    public static final class Mms.Intents
    {

        public static final String CONTENT_CHANGED_ACTION = "android.intent.action.CONTENT_CHANGED";
        public static final String DELETED_CONTENTS = "deleted_contents";

        private Mms.Intents()
        {
        }
    }

    public static final class Mms.Outbox
        implements BaseMmsColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://mms/outbox");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Mms.Outbox()
        {
        }
    }

    public static final class Mms.Part
        implements BaseColumns
    {

        public static final String CHARSET = "chset";
        public static final String CONTENT_DISPOSITION = "cd";
        public static final String CONTENT_ID = "cid";
        public static final String CONTENT_LOCATION = "cl";
        public static final String CONTENT_TYPE = "ct";
        public static final String CT_START = "ctt_s";
        public static final String CT_TYPE = "ctt_t";
        public static final String FILENAME = "fn";
        public static final String MSG_ID = "mid";
        public static final String NAME = "name";
        public static final String SEQ = "seq";
        public static final String TEXT = "text";
        public static final String _DATA = "_data";

        private Mms.Part()
        {
        }
    }

    public static final class Mms.Rate
    {

        public static final Uri CONTENT_URI;
        public static final String SENT_TIME = "sent_time";

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(Mms.CONTENT_URI, "rate");
        }

        private Mms.Rate()
        {
        }
    }

    public static final class Mms.Sent
        implements BaseMmsColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://mms/sent");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Mms.Sent()
        {
        }
    }

    public static final class MmsSms
        implements BaseColumns
    {

        public static final Uri CONTENT_CONVERSATIONS_URI = Uri.parse("content://mms-sms/conversations");
        public static final Uri CONTENT_DRAFT_URI = Uri.parse("content://mms-sms/draft");
        public static final Uri CONTENT_FILTER_BYPHONE_URI = Uri.parse("content://mms-sms/messages/byphone");
        public static final Uri CONTENT_LOCKED_URI = Uri.parse("content://mms-sms/locked");
        public static final Uri CONTENT_UNDELIVERED_URI = Uri.parse("content://mms-sms/undelivered");
        public static final Uri CONTENT_URI = Uri.parse("content://mms-sms/");
        public static final int ERR_TYPE_GENERIC = 1;
        public static final int ERR_TYPE_GENERIC_PERMANENT = 10;
        public static final int ERR_TYPE_MMS_PROTO_PERMANENT = 12;
        public static final int ERR_TYPE_MMS_PROTO_TRANSIENT = 3;
        public static final int ERR_TYPE_SMS_PROTO_PERMANENT = 11;
        public static final int ERR_TYPE_SMS_PROTO_TRANSIENT = 2;
        public static final int ERR_TYPE_TRANSPORT_FAILURE = 4;
        public static final int MMS_PROTO = 1;
        public static final int NO_ERROR = 0;
        public static final Uri SEARCH_URI = Uri.parse("content://mms-sms/search");
        public static final int SMS_PROTO = 0;
        public static final String TYPE_DISCRIMINATOR_COLUMN = "transport_type";


        private MmsSms()
        {
        }
    }

    public static final class MmsSms.PendingMessages
        implements BaseColumns
    {

        public static final Uri CONTENT_URI;
        public static final String DUE_TIME = "due_time";
        public static final String ERROR_CODE = "err_code";
        public static final String ERROR_TYPE = "err_type";
        public static final String LAST_TRY = "last_try";
        public static final String MSG_ID = "msg_id";
        public static final String MSG_TYPE = "msg_type";
        public static final String PROTO_TYPE = "proto_type";
        public static final String RETRY_INDEX = "retry_index";
        public static final String SUBSCRIPTION_ID = "pending_sub_id";

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(MmsSms.CONTENT_URI, "pending");
        }

        private MmsSms.PendingMessages()
        {
        }
    }

    public static final class MmsSms.WordsTable
    {

        public static final String ID = "_id";
        public static final String INDEXED_TEXT = "index_text";
        public static final String SOURCE_ROW_ID = "source_id";
        public static final String TABLE_ID = "table_to_use";

        private MmsSms.WordsTable()
        {
        }
    }

    public static final class ServiceStateTable
    {

        public static ContentValues getContentValuesForServiceState(ServiceState servicestate)
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("voice_reg_state", Integer.valueOf(servicestate.getVoiceRegState()));
            contentvalues.put("data_reg_state", Integer.valueOf(servicestate.getDataRegState()));
            contentvalues.put("voice_roaming_type", Integer.valueOf(servicestate.getVoiceRoamingType()));
            contentvalues.put("data_roaming_type", Integer.valueOf(servicestate.getDataRoamingType()));
            contentvalues.put("voice_operator_alpha_long", servicestate.getVoiceOperatorAlphaLong());
            contentvalues.put("voice_operator_alpha_short", servicestate.getVoiceOperatorAlphaShort());
            contentvalues.put("voice_operator_numeric", servicestate.getVoiceOperatorNumeric());
            contentvalues.put("data_operator_alpha_long", servicestate.getDataOperatorAlphaLong());
            contentvalues.put("data_operator_alpha_short", servicestate.getDataOperatorAlphaShort());
            contentvalues.put("data_operator_numeric", servicestate.getDataOperatorNumeric());
            contentvalues.put("is_manual_network_selection", Boolean.valueOf(servicestate.getIsManualSelection()));
            contentvalues.put("ril_voice_radio_technology", Integer.valueOf(servicestate.getRilVoiceRadioTechnology()));
            contentvalues.put("ril_data_radio_technology", Integer.valueOf(servicestate.getRilDataRadioTechnology()));
            contentvalues.put("css_indicator", Integer.valueOf(servicestate.getCssIndicator()));
            contentvalues.put("network_id", Integer.valueOf(servicestate.getNetworkId()));
            contentvalues.put("system_id", Integer.valueOf(servicestate.getSystemId()));
            contentvalues.put("cdma_roaming_indicator", Integer.valueOf(servicestate.getCdmaRoamingIndicator()));
            contentvalues.put("cdma_default_roaming_indicator", Integer.valueOf(servicestate.getCdmaDefaultRoamingIndicator()));
            contentvalues.put("cdma_eri_icon_index", Integer.valueOf(servicestate.getCdmaEriIconIndex()));
            contentvalues.put("cdma_eri_icon_mode", Integer.valueOf(servicestate.getCdmaEriIconMode()));
            contentvalues.put("is_emergency_only", Boolean.valueOf(servicestate.isEmergencyOnly()));
            contentvalues.put("is_data_roaming_from_registration", Boolean.valueOf(servicestate.getDataRoamingFromRegistration()));
            contentvalues.put("is_using_carrier_aggregation", Boolean.valueOf(servicestate.isUsingCarrierAggregation()));
            return contentvalues;
        }

        public static Uri getUriForSubscriptionId(int i)
        {
            return CONTENT_URI.buildUpon().appendEncodedPath(String.valueOf(i)).build();
        }

        public static Uri getUriForSubscriptionIdAndField(int i, String s)
        {
            return CONTENT_URI.buildUpon().appendEncodedPath(String.valueOf(i)).appendEncodedPath(s).build();
        }

        public static final String AUTHORITY = "service-state";
        public static final String CDMA_DEFAULT_ROAMING_INDICATOR = "cdma_default_roaming_indicator";
        public static final String CDMA_ERI_ICON_INDEX = "cdma_eri_icon_index";
        public static final String CDMA_ERI_ICON_MODE = "cdma_eri_icon_mode";
        public static final String CDMA_ROAMING_INDICATOR = "cdma_roaming_indicator";
        public static final Uri CONTENT_URI = Uri.parse("content://service-state/");
        public static final String CSS_INDICATOR = "css_indicator";
        public static final String DATA_OPERATOR_ALPHA_LONG = "data_operator_alpha_long";
        public static final String DATA_OPERATOR_ALPHA_SHORT = "data_operator_alpha_short";
        public static final String DATA_OPERATOR_NUMERIC = "data_operator_numeric";
        public static final String DATA_REG_STATE = "data_reg_state";
        public static final String DATA_ROAMING_TYPE = "data_roaming_type";
        public static final String IS_DATA_ROAMING_FROM_REGISTRATION = "is_data_roaming_from_registration";
        public static final String IS_EMERGENCY_ONLY = "is_emergency_only";
        public static final String IS_MANUAL_NETWORK_SELECTION = "is_manual_network_selection";
        public static final String IS_USING_CARRIER_AGGREGATION = "is_using_carrier_aggregation";
        public static final String NETWORK_ID = "network_id";
        public static final String RIL_DATA_RADIO_TECHNOLOGY = "ril_data_radio_technology";
        public static final String RIL_VOICE_RADIO_TECHNOLOGY = "ril_voice_radio_technology";
        public static final String SYSTEM_ID = "system_id";
        public static final String VOICE_OPERATOR_ALPHA_LONG = "voice_operator_alpha_long";
        public static final String VOICE_OPERATOR_ALPHA_SHORT = "voice_operator_alpha_short";
        public static final String VOICE_OPERATOR_NUMERIC = "voice_operator_numeric";
        public static final String VOICE_REG_STATE = "voice_reg_state";
        public static final String VOICE_ROAMING_TYPE = "voice_roaming_type";


        private ServiceStateTable()
        {
        }
    }

    public static final class Sms
        implements BaseColumns, TextBasedSmsColumns
    {

        public static Uri addMessageToUri(int i, ContentResolver contentresolver, Uri uri, String s, String s1, String s2, Long long1, boolean flag, 
                boolean flag1)
        {
            return addMessageToUri(i, contentresolver, uri, s, s1, s2, long1, flag, flag1, -1L);
        }

        public static Uri addMessageToUri(int i, ContentResolver contentresolver, Uri uri, String s, String s1, String s2, Long long1, boolean flag, 
                boolean flag1, long l)
        {
            return addMessageToUri(i, contentresolver, uri, s, s1, s2, long1, flag, flag1, l, -1);
        }

        public static Uri addMessageToUri(int i, ContentResolver contentresolver, Uri uri, String s, String s1, String s2, Long long1, boolean flag, 
                boolean flag1, long l, int j)
        {
            ContentValues contentvalues = new ContentValues(8);
            Rlog.v("Telephony", (new StringBuilder()).append("Telephony addMessageToUri sub id: ").append(i).toString());
            contentvalues.put("sub_id", Integer.valueOf(i));
            contentvalues.put("address", s);
            if(long1 != null)
                contentvalues.put("date", long1);
            if(flag)
                s = Integer.valueOf(1);
            else
                s = Integer.valueOf(0);
            contentvalues.put("read", s);
            contentvalues.put("subject", s2);
            contentvalues.put("body", s1);
            contentvalues.put("priority", Integer.valueOf(j));
            if(flag1)
                contentvalues.put("status", Integer.valueOf(32));
            if(l != -1L)
                contentvalues.put("thread_id", Long.valueOf(l));
            return contentresolver.insert(uri, contentvalues);
        }

        public static Uri addMessageToUri(ContentResolver contentresolver, Uri uri, String s, String s1, String s2, Long long1, boolean flag, boolean flag1)
        {
            return addMessageToUri(SubscriptionManager.getDefaultSmsSubscriptionId(), contentresolver, uri, s, s1, s2, long1, flag, flag1, -1L);
        }

        public static Uri addMessageToUri(ContentResolver contentresolver, Uri uri, String s, String s1, String s2, Long long1, boolean flag, boolean flag1, 
                long l)
        {
            return addMessageToUri(SubscriptionManager.getDefaultSmsSubscriptionId(), contentresolver, uri, s, s1, s2, long1, flag, flag1, l);
        }

        public static String getDefaultSmsPackage(Context context)
        {
            context = SmsApplication.getDefaultSmsApplication(context, false);
            if(context != null)
                return context.getPackageName();
            else
                return null;
        }

        public static boolean isOutgoingFolder(int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(i == 5) goto _L2; else goto _L1
_L1:
            if(i != 4) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 2)
            {
                flag1 = flag;
                if(i != 6)
                    flag1 = false;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static boolean moveMessageToFolder(Context context, Uri uri, int i, int j)
        {
            boolean flag1;
            boolean flag2;
            boolean flag3;
            if(uri == null)
                return false;
            boolean flag = false;
            flag1 = false;
            flag2 = flag1;
            flag3 = flag;
            switch(i)
            {
            default:
                return false;

            case 1: // '\001'
            case 3: // '\003'
                break;

            case 5: // '\005'
            case 6: // '\006'
                break MISSING_BLOCK_LABEL_140;

            case 2: // '\002'
            case 4: // '\004'
                flag2 = true;
                flag3 = flag;
                break;
            }
_L1:
            ContentValues contentvalues = new ContentValues(3);
            contentvalues.put("type", Integer.valueOf(i));
            boolean flag4;
            if(flag3)
                contentvalues.put("read", Integer.valueOf(0));
            else
            if(flag2)
                contentvalues.put("read", Integer.valueOf(1));
            contentvalues.put("error_code", Integer.valueOf(j));
            if(1 == SqliteWrapper.update(context, context.getContentResolver(), uri, contentvalues, null, null))
                flag4 = true;
            else
                flag4 = false;
            return flag4;
            flag3 = true;
            flag2 = flag1;
              goto _L1
        }

        public static Cursor query(ContentResolver contentresolver, String as[])
        {
            SeempLog.record(10);
            return contentresolver.query(CONTENT_URI, as, null, null, "date DESC");
        }

        public static Cursor query(ContentResolver contentresolver, String as[], String s, String s1)
        {
            SeempLog.record(10);
            Uri uri = CONTENT_URI;
            if(s1 == null)
                s1 = "date DESC";
            return contentresolver.query(uri, as, s, null, s1);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://sms");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Sms()
        {
        }
    }

    public static final class Sms.Conversations
        implements BaseColumns, TextBasedSmsColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://sms/conversations");
        public static final String DEFAULT_SORT_ORDER = "date DESC";
        public static final String MESSAGE_COUNT = "msg_count";
        public static final String SNIPPET = "snippet";


        private Sms.Conversations()
        {
        }
    }

    public static final class Sms.Draft
        implements BaseColumns, TextBasedSmsColumns
    {

        public static Uri addMessage(int i, ContentResolver contentresolver, String s, String s1, String s2, Long long1)
        {
            return Sms.addMessageToUri(i, contentresolver, CONTENT_URI, s, s1, s2, long1, true, false);
        }

        public static Uri addMessage(ContentResolver contentresolver, String s, String s1, String s2, Long long1)
        {
            return Sms.addMessageToUri(SubscriptionManager.getDefaultSmsSubscriptionId(), contentresolver, CONTENT_URI, s, s1, s2, long1, true, false);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://sms/draft");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Sms.Draft()
        {
        }
    }

    public static final class Sms.Inbox
        implements BaseColumns, TextBasedSmsColumns
    {

        public static Uri addMessage(int i, ContentResolver contentresolver, String s, String s1, String s2, Long long1, boolean flag)
        {
            return Sms.addMessageToUri(i, contentresolver, CONTENT_URI, s, s1, s2, long1, flag, false);
        }

        public static Uri addMessage(ContentResolver contentresolver, String s, String s1, String s2, Long long1, boolean flag)
        {
            return Sms.addMessageToUri(SubscriptionManager.getDefaultSmsSubscriptionId(), contentresolver, CONTENT_URI, s, s1, s2, long1, flag, false);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://sms/inbox");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Sms.Inbox()
        {
        }
    }

    public static final class Sms.Intents
    {

        public static SmsMessage[] getMessagesFromIntent(Intent intent)
        {
            Object aobj[];
            try
            {
                aobj = (Object[])intent.getSerializableExtra("pdus");
            }
            // Misplaced declaration of an exception variable
            catch(Intent intent)
            {
                Rlog.e("Telephony", (new StringBuilder()).append("getMessagesFromIntent: ").append(intent).toString());
                return null;
            }
            if(aobj == null)
            {
                Rlog.e("Telephony", "pdus does not exist in the intent");
                return null;
            }
            String s = intent.getStringExtra("format");
            int i = intent.getIntExtra("subscription", SubscriptionManager.getDefaultSmsSubscriptionId());
            Rlog.v("Telephony", (new StringBuilder()).append(" getMessagesFromIntent sub_id : ").append(i).toString());
            int j = aobj.length;
            intent = new SmsMessage[j];
            for(int k = 0; k < j; k++)
            {
                intent[k] = SmsMessage.createFromPdu((byte[])aobj[k], s);
                if(intent[k] != null)
                    intent[k].setSubId(i);
            }

            return intent;
        }

        public static final String ACTION_CHANGE_DEFAULT = "android.provider.Telephony.ACTION_CHANGE_DEFAULT";
        public static final String ACTION_DEFAULT_SMS_PACKAGE_CHANGED = "android.provider.action.DEFAULT_SMS_PACKAGE_CHANGED";
        public static final String ACTION_EXTERNAL_PROVIDER_CHANGE = "android.provider.action.EXTERNAL_PROVIDER_CHANGE";
        public static final String DATA_SMS_RECEIVED_ACTION = "android.intent.action.DATA_SMS_RECEIVED";
        public static final String EXTRA_IS_DEFAULT_SMS_APP = "android.provider.extra.IS_DEFAULT_SMS_APP";
        public static final String EXTRA_PACKAGE_NAME = "package";
        public static final String MMS_DOWNLOADED_ACTION = "android.provider.Telephony.MMS_DOWNLOADED";
        public static final int RESULT_SMS_DUPLICATED = 5;
        public static final int RESULT_SMS_GENERIC_ERROR = 2;
        public static final int RESULT_SMS_HANDLED = 1;
        public static final int RESULT_SMS_OUT_OF_MEMORY = 3;
        public static final int RESULT_SMS_UNSUPPORTED = 4;
        public static final String SIM_FULL_ACTION = "android.provider.Telephony.SIM_FULL";
        public static final String SMS_CARRIER_PROVISION_ACTION = "android.provider.Telephony.SMS_CARRIER_PROVISION";
        public static final String SMS_CB_RECEIVED_ACTION = "android.provider.Telephony.SMS_CB_RECEIVED";
        public static final String SMS_DELIVER_ACTION = "android.provider.Telephony.SMS_DELIVER";
        public static final String SMS_EMERGENCY_CB_RECEIVED_ACTION = "android.provider.Telephony.SMS_EMERGENCY_CB_RECEIVED";
        public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
        public static final String SMS_REJECTED_ACTION = "android.provider.Telephony.SMS_REJECTED";
        public static final String SMS_SERVICE_CATEGORY_PROGRAM_DATA_RECEIVED_ACTION = "android.provider.Telephony.SMS_SERVICE_CATEGORY_PROGRAM_DATA_RECEIVED";
        public static final String WAP_PUSH_DELIVER_ACTION = "android.provider.Telephony.WAP_PUSH_DELIVER";
        public static final String WAP_PUSH_RECEIVED_ACTION = "android.provider.Telephony.WAP_PUSH_RECEIVED";

        private Sms.Intents()
        {
        }
    }

    public static final class Sms.Outbox
        implements BaseColumns, TextBasedSmsColumns
    {

        public static Uri addMessage(int i, ContentResolver contentresolver, String s, String s1, String s2, Long long1, boolean flag, long l)
        {
            return Sms.addMessageToUri(i, contentresolver, CONTENT_URI, s, s1, s2, long1, true, flag, l);
        }

        public static Uri addMessage(ContentResolver contentresolver, String s, String s1, String s2, Long long1, boolean flag, long l)
        {
            return Sms.addMessageToUri(SubscriptionManager.getDefaultSmsSubscriptionId(), contentresolver, CONTENT_URI, s, s1, s2, long1, true, flag, l);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://sms/outbox");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Sms.Outbox()
        {
        }
    }

    public static final class Sms.Sent
        implements BaseColumns, TextBasedSmsColumns
    {

        public static Uri addMessage(int i, ContentResolver contentresolver, String s, String s1, String s2, Long long1)
        {
            return Sms.addMessageToUri(i, contentresolver, CONTENT_URI, s, s1, s2, long1, true, false);
        }

        public static Uri addMessage(ContentResolver contentresolver, String s, String s1, String s2, Long long1)
        {
            return Sms.addMessageToUri(SubscriptionManager.getDefaultSmsSubscriptionId(), contentresolver, CONTENT_URI, s, s1, s2, long1, true, false);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://sms/sent");
        public static final String DEFAULT_SORT_ORDER = "date DESC";


        private Sms.Sent()
        {
        }
    }

    public static interface TextBasedSmsColumns
    {

        public static final String ADDRESS = "address";
        public static final String BODY = "body";
        public static final String CREATOR = "creator";
        public static final String DATE = "date";
        public static final String DATE_SENT = "date_sent";
        public static final String ERROR_CODE = "error_code";
        public static final String LOCKED = "locked";
        public static final int MESSAGE_TYPE_ALL = 0;
        public static final int MESSAGE_TYPE_DRAFT = 3;
        public static final int MESSAGE_TYPE_FAILED = 5;
        public static final int MESSAGE_TYPE_INBOX = 1;
        public static final int MESSAGE_TYPE_OUTBOX = 4;
        public static final int MESSAGE_TYPE_QUEUED = 6;
        public static final int MESSAGE_TYPE_SENT = 2;
        public static final String MTU = "mtu";
        public static final String PERSON = "person";
        public static final String PRIORITY = "priority";
        public static final String PROTOCOL = "protocol";
        public static final String READ = "read";
        public static final String REPLY_PATH_PRESENT = "reply_path_present";
        public static final String SEEN = "seen";
        public static final String SERVICE_CENTER = "service_center";
        public static final String STATUS = "status";
        public static final int STATUS_COMPLETE = 0;
        public static final int STATUS_FAILED = 64;
        public static final int STATUS_NONE = -1;
        public static final int STATUS_PENDING = 32;
        public static final String SUBJECT = "subject";
        public static final String SUBSCRIPTION_ID = "sub_id";
        public static final String THREAD_ID = "thread_id";
        public static final String TYPE = "type";
    }

    public static final class Threads
        implements ThreadsColumns
    {

        public static long getOrCreateThreadId(Context context, String s)
        {
            HashSet hashset = new HashSet();
            hashset.add(s);
            return getOrCreateThreadId(context, ((Set) (hashset)));
        }

        public static long getOrCreateThreadId(Context context, Set set)
        {
            android.net.Uri.Builder builder = THREAD_ID_CONTENT_URI.buildUpon();
            String s1;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); builder.appendQueryParameter("recipient", s1))
            {
                String s = (String)iterator.next();
                s1 = s;
                if(Mms.isEmailAddress(s))
                    s1 = Mms.extractAddrSpec(s);
            }

            Uri uri = builder.build();
            context = SqliteWrapper.query(context, context.getContentResolver(), uri, ID_PROJECTION, null, null, null);
            if(context == null)
                break MISSING_BLOCK_LABEL_133;
            long l;
            if(!context.moveToFirst())
                break MISSING_BLOCK_LABEL_119;
            l = context.getLong(0);
            context.close();
            return l;
            Rlog.e("Telephony", "getOrCreateThreadId returned no rows!");
            context.close();
            Rlog.e("Telephony", (new StringBuilder()).append("getOrCreateThreadId failed with ").append(set.size()).append(" recipients").toString());
            throw new IllegalArgumentException("Unable to find or allocate a thread ID.");
            set;
            context.close();
            throw set;
        }

        public static final int BROADCAST_THREAD = 1;
        public static final int COMMON_THREAD = 0;
        public static final Uri CONTENT_URI;
        private static final String ID_PROJECTION[] = {
            "_id"
        };
        public static final Uri OBSOLETE_THREADS_URI;
        private static final Uri THREAD_ID_CONTENT_URI = Uri.parse("content://mms-sms/threadID");

        static 
        {
            CONTENT_URI = Uri.withAppendedPath(MmsSms.CONTENT_URI, "conversations");
            OBSOLETE_THREADS_URI = Uri.withAppendedPath(CONTENT_URI, "obsolete");
        }

        private Threads()
        {
        }
    }

    public static interface ThreadsColumns
        extends BaseColumns
    {

        public static final String ARCHIVED = "archived";
        public static final String ATTACHMENT_INFO = "attachment_info";
        public static final String DATE = "date";
        public static final String ERROR = "error";
        public static final String HAS_ATTACHMENT = "has_attachment";
        public static final String MESSAGE_COUNT = "message_count";
        public static final String NOTIFICATION = "notification";
        public static final String READ = "read";
        public static final String RECIPIENT_IDS = "recipient_ids";
        public static final String SNIPPET = "snippet";
        public static final String SNIPPET_CHARSET = "snippet_cs";
        public static final String TYPE = "type";
    }


    private Telephony()
    {
    }

    private static final String TAG = "Telephony";
}
