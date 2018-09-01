// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.juphoon.provider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.BaseColumns;
import java.io.File;

public class RmsDefine
{
    public static final class Blacklist
    {

        public static final String AUTHORITY = "blacklist";
        public static final Uri CONTENT_URI = Uri.parse("content://blacklist/blacklist");
        public static final String NAME = "BLACKLIST_NAME";
        public static final String PHONE_NUMBER = "BLACKLIST_PHONE_NUMBER";
        public static final String TABLE_NAME = "blacklist";
        public static final String _ID = "_id";


        public Blacklist()
        {
        }
    }

    public static final class CallLogBlack
    {

        public static final String AUTHORITY = "calllog_black";
        public static final String CALLID = "call_id";
        public static final Uri CONTENT_URI = Uri.parse("content://calllog_black/logs");
        public static final String NAME = "name";
        public static final String PHONE_NUMBER = "number";
        public static final String TABLE_NAME = "calllog_black";
        public static final String _ID = "_id";


        public CallLogBlack()
        {
        }
    }

    public static final class Capability
    {

        public static final String AUTHORITY = "capability";
        public static final String CAPABILITY_EXTENSIONS = "CAPABILITY_EXTENSIONS";
        public static final String CAPABILITY_FILE_TRANSFER = "CAPABILITY_FILE_TRANSFER";
        public static final String CAPABILITY_GEOLOC_PUSH = "CAPABILITY_GEOLOC_PUSH";
        public static final String CAPABILITY_IMAGE_SHARING = "CAPABILITY_IMAGE_SHARING";
        public static final String CAPABILITY_IM_SESSION = "CAPABILITY_IM_SESSION";
        public static final String CAPABILITY_IP_VIDEO_CALL = "CAPABILITY_IP_VIDEO_CALL";
        public static final String CAPABILITY_IP_VOICE_CALL = "CAPABILITY_IP_VOICE_CALL";
        public static final String CAPABILITY_VIDEO_SHARING = "CAPABILITY_VIDEO_SHARING";
        public static final String CONTACT_NUMBER = "CONTACT_NUMBER";
        public static final Uri CONTENT_URI = Uri.parse("content://capability/capability");
        public static final String TABLE_NAME = "capability";
        public static final String _ID = "_id";


        public Capability()
        {
        }
    }

    public static final class Favorites
    {

        public static final Uri FAVORITE_CONTENT_URI_MMS = Uri.parse("content://favorite-mms");
        public static final Uri FAVORITE_CONTENT_URI_MMSSMS = Uri.parse("content://favorite-mms-sms");
        public static final Uri FAVORITE_CONTENT_URI_RMS = Uri.parse("content://favorite-rms");
        public static final Uri FAVORITE_CONTENT_URI_RMS_LOG = Uri.parse("content://favorite-rms/rms_log");
        public static final Uri FAVORITE_CONTENT_URI_RMS_LOGGROUP = Uri.parse("content://favorite-rms/rms_log_group");
        public static final Uri FAVORITE_CONTENT_URI_SMS = Uri.parse("content://favorite-sms");
        public static final String FAVORITE_MMSSMS_AUTHORITY = "favorite-mms-sms";
        public static final String FAVORITE_MMS_AUTHORITY = "favorite-mms";
        public static final String FAVORITE_RMS_AUTHORITY = "favorite-rms";
        public static final String FAVORITE_SMS_AUTHORITY = "favorite-sms";


        public Favorites()
        {
        }
    }

    public static final class GroupChatMembers
    {

        public static final Uri CONTENT_URI = Uri.parse("content://rcsgroup/group_chat_members");
        public static final String ETYPE = "etype";
        public static final String GROUP_CHAT_ID = "group_chat_id";
        public static final String NAME = "name";
        public static final String NUMBER = "number";
        public static final String PROTRAIT = "protrait";
        public static final String PROTRAIT_TYPE = "protrait_type";
        public static final String STATUS = "status";
        public static final String TABLE_NAME = "group_chat_members";
        public static final String _ID = "_id";


        public GroupChatMembers()
        {
        }
    }

    public static final class Menus
    {

        public static final String AUTHORITY = "menus";
        public static final String COMMAND_ID = "command_id";
        public static final Uri CONTENT_URI = Uri.parse("content://menus/menus");
        public static final String PARENT_MENU_ID = "parent_menu_id";
        public static final String PA_UUID = "pa_uuid";
        public static final String PRIORITY = "priority";
        public static final String TABLE_NAME = "menus";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        public static final int TYPE_APP = 3;
        public static final int TYPE_DEVICE_API = 2;
        public static final int TYPE_LINK = 1;
        public static final int TYPE_SEND_MSG = 0;
        public static final String _ID = "_id";


        public Menus()
        {
        }
    }

    public static final class Numbers
    {

        public static final String ATTRIBUTION = "attribution";
        public static final String AUTHORITY = "numbers";
        public static final Uri CONTENT_URI = Uri.parse("content://numbers/numbers");
        public static final String IS_BLACK = "is_black";
        public static final String NAME = "name";
        public static final String NUMBER = "number";
        public static final String RCS_CAPABILITY = "rcs_capability";
        public static final String TABLE_NAME = "numbers";
        public static final String _ID = "_id";


        public Numbers()
        {
        }
    }

    public static final class Pas
    {

        public static final String ACCEPT_STATUS = "accept_status";
        public static final int ACCEPT_STATUS_ACPT_PUSH = 1;
        public static final int ACCEPT_STATUS_NOT_ACPT_PUSH = 0;
        public static final String ACTIVE_STATUS = "active_status";
        public static final String ADDR = "addr";
        public static final String AUTHORITY = "pas";
        public static final String BODY = "body";
        public static final String COMPANY = "company";
        public static final Uri CONTENT_URI = Uri.parse("content://pas/pas");
        public static final Uri CONTENT_URI_HISTORY_MESSAGE = Uri.parse("content://pas/pas_history_message");
        public static final Uri CONTENT_URI_SEARCH = Uri.parse("content://pas/pas_search");
        public static final String DATE = "date";
        public static final String DEVICE_API_EXTRA = "device_api_extra";
        public static final String EMAIL = "email";
        public static final String FIELD = "field";
        public static final String ID = "id";
        public static final String IDTYPE = "idtype";
        public static final String INTRO = "intro";
        public static final int LEVEL_1 = 1;
        public static final int LEVEL_2 = 2;
        public static final int LEVEL_3 = 3;
        public static final int LEVEL_4 = 4;
        public static final int LEVEL_5 = 5;
        public static final String LOGO = "logo";
        public static final String LOGO_TYPE = "logo_type";
        public static final String MENU_CONFIG = "menu_config";
        public static final String MENU_TIMESTAMP = "menu_timestamp";
        public static final String MENU_TYPE = "menu_type";
        public static final int MENU_TYPE_HAS_MENU = 1;
        public static final int MENU_TYPE_NO_MENU = 0;
        public static final String NAME = "name";
        public static final String OWNER_NUMBER = "owner_number";
        public static final String PA_UUID = "pa_uuid";
        public static final String PINYIN_KEY_ALT = "pinyin_key_alt";
        public static final String QRCODE = "qrcode";
        public static final String RECOMMEND_LEVEL = "recommend_level";
        public static final String SEARCH_KEY_ALT = "search_key_alt";
        public static final String SORT_KEY_ALT = "sort_key_alt";
        public static final String SUBSCRIBE_STATUS = "subscribe_status";
        public static final int SUBSCRIBE_STATUS_FOLLOW = 1;
        public static final int SUBSCRIBE_STATUS_UNFOLLOW = 0;
        public static final String TABLE_NAME = "pas";
        public static final String TABLE_NAME_HISTORY_MESSAGE = "pas_history_message";
        public static final String TABLE_NAME_SEARCH = "pas_search";
        public static final String TEL = "tel";
        public static final String TYPE = "type";
        public static final int TYPE_DEFAULT = 0;
        public static final int TYPE_ENTERTAINMENT = 3;
        public static final int TYPE_LIFE = 1;
        public static final int TYPE_OTHER = 4;
        public static final int TYPE_WORK = 2;
        public static final String UPDATE_TIME = "update_time";
        public static final String ZIP = "zip";
        public static final String _ID = "_id";


        public Pas()
        {
        }
    }

    public static final class ProfileAccounts
    {

        public static final String ACCOUNT = "account";
        public static final String AUTHORITY = "profile_accounts";
        public static final Uri CONTENT_URI = Uri.parse("content://profile_accounts/profile_accounts");
        public static final String TABLE_NAME = "profile_accounts";
        public static final String _ID = "_id";


        public ProfileAccounts()
        {
        }
    }

    public static final class ProfilePcc
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String AUTHORITY = "profile_pcc";
        public static final Uri CONTENT_URI = Uri.parse("content://profile_pcc/profile_pcc");
        public static final String DATA1 = "data1";
        public static final String DATA10 = "data10";
        public static final String DATA2 = "data2";
        public static final String DATA3 = "data3";
        public static final String DATA4 = "data4";
        public static final String DATA5 = "data5";
        public static final String DATA6 = "data6";
        public static final String DATA7 = "data7";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String PROP_TYPE = "prop_type";
        public static final int PROP_TYPE_ADDR = 2;
        public static final int PROP_TYPE_BIRTH = 5;
        public static final int PROP_TYPE_CAREER = 6;
        public static final int PROP_TYPE_ICON = 7;
        public static final int PROP_TYPE_NAME = 1;
        public static final int PROP_TYPE_TEL = 3;
        public static final int PROP_TYPE_URI = 4;
        public static final String TABLE_NAME = "profile_pcc";
        public static final String _ID = "_id";


        public ProfilePcc()
        {
        }
    }

    public static final class ProfilePcc.Address
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String ADDRESS_LABEL = "data3";
        public static final String ADDRESS_PREFERENCE = "data2";
        public static final String ADDRESS_TYPE = "data1";
        public static final String ADDRESS_VALUE = "data4";
        public static final String DATA10 = "data10";
        public static final String DATA5 = "data5";
        public static final String DATA6 = "data6";
        public static final String DATA7 = "data7";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String PROP_TYPE = "prop_type";
        public static final String _ID = "_id";

        public ProfilePcc.Address()
        {
        }
    }

    public static final class ProfilePcc.Birth
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String BIRTH_CALENDARS_TYPE = "data4";
        public static final String BIRTH_DATE = "data1";
        public static final String BIRTH_NON_GREGORIAN_DATE = "data3";
        public static final String BIRTH_PLACE = "data2";
        public static final String DATA10 = "data10";
        public static final String DATA5 = "data5";
        public static final String DATA6 = "data6";
        public static final String DATA7 = "data7";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String PROP_TYPE = "prop_type";
        public static final String _ID = "_id";

        public ProfilePcc.Birth()
        {
        }
    }

    public static final class ProfilePcc.Career
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String CAREER_DUTY = "data2";
        public static final String CAREER_EMPLOYER = "data1";
        public static final String DATA10 = "data10";
        public static final String DATA3 = "data3";
        public static final String DATA4 = "data4";
        public static final String DATA5 = "data5";
        public static final String DATA6 = "data6";
        public static final String DATA7 = "data7";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String PROP_TYPE = "prop_type";
        public static final String _ID = "_id";

        public ProfilePcc.Career()
        {
        }
    }

    public static final class ProfilePcc.Icon
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String DATA10 = "data10";
        public static final String DATA3 = "data3";
        public static final String DATA4 = "data4";
        public static final String DATA5 = "data5";
        public static final String DATA6 = "data6";
        public static final String DATA7 = "data7";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String ICON_MIME_TYPE = "data1";
        public static final String ICON_PATH = "data2";
        public static final String PROP_TYPE = "prop_type";
        public static final String _ID = "_id";

        public ProfilePcc.Icon()
        {
        }
    }

    public static final class ProfilePcc.Name
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String DATA10 = "data10";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String DISPLAY_NAME = "data5";
        public static final String FAMILY_NAME = "data4";
        public static final String FIRST_NAME = "data6";
        public static final String GIVEN_NAME = "data7";
        public static final String MIDDLE_NAME = "data3";
        public static final String NAME_PREFERENCE = "data2";
        public static final String NAME_TYPE = "data1";
        public static final String PROP_TYPE = "prop_type";
        public static final String _ID = "_id";

        public ProfilePcc.Name()
        {
        }
    }

    public static final class ProfilePcc.Tel
        implements BaseColumns
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String DATA10 = "data10";
        public static final String DATA6 = "data6";
        public static final String DATA7 = "data7";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String PROP_TYPE = "prop_type";
        public static final String TEL_LABEL = "data3";
        public static final String TEL_PREFERENCE = "data2";
        public static final String TEL_TYPE = "data1";
        public static final String TEL_VALUE = "data4";
        public static final String TEL_XUI_TYPE = "data5";

        public ProfilePcc.Tel()
        {
        }
    }

    public static final class ProfilePcc.URI
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String DATA10 = "data10";
        public static final String DATA5 = "data5";
        public static final String DATA6 = "data6";
        public static final String DATA7 = "data7";
        public static final String DATA8 = "data8";
        public static final String DATA9 = "data9";
        public static final String PROP_TYPE = "prop_type";
        public static final String URI_LABEL = "data3";
        public static final String URI_PREFERENCE = "data2";
        public static final String URI_TYPE = "data1";
        public static final String URI_VALUE = "data4";
        public static final String _ID = "_id";

        public ProfilePcc.URI()
        {
        }
    }

    public static final class ProfileQrcard
    {

        public static final String ACCOUNT_ID = "account_id";
        public static final String AUTHORITY = "profile_qrcard";
        public static final String BUSINESS_FLAG = "business_flag";
        public static final Uri CONTENT_URI = Uri.parse("content://profile_qrcard/profile_qrcard");
        public static final String ICON_MIME_TYPE = "ICON_MIME_TYPE";
        public static final String ICON_PATH = "icon_path";
        public static final String TABLE_NAME = "profile_qrcard";
        public static final String _ID = "_id";


        public ProfileQrcard()
        {
        }
    }

    public static final class Rms
        implements TextBasedRmsColumns
    {

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

        public static Cursor query(ContentResolver contentresolver, String as[])
        {
            return contentresolver.query(CONTENT_URI, as, null, null, "date DESC");
        }

        public static Cursor query(ContentResolver contentresolver, String as[], String s, String s1)
        {
            Uri uri = CONTENT_URI;
            if(s1 == null)
                s1 = "date DESC";
            return contentresolver.query(uri, as, s, null, s1);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://rms");
        public static final Uri CONTENT_URI_LOG = Uri.parse("content://rms/rms_log");
        public static final String DEFAULT_SORT_ORDER = "date DESC";
        public static final String TABLE_NAME = "rms";


        public Rms()
        {
        }
    }

    public static final class RmsGroup
        implements TextBasedRmsGroupColumns
    {

        public static Cursor query(ContentResolver contentresolver, String as[])
        {
            return contentresolver.query(CONTENT_URI, as, null, null, "date DESC");
        }

        public static Cursor query(ContentResolver contentresolver, String as[], String s, String s1)
        {
            Uri uri = CONTENT_URI;
            if(s1 == null)
                s1 = "date DESC";
            return contentresolver.query(uri, as, s, null, s1);
        }

        public static final Uri CONTENT_URI = Uri.parse("content://rcsgroup/rms_log_group");
        public static final String DEFAULT_SORT_ORDER = "date DESC";
        public static final String TABLE_NAME = "groups";


        public RmsGroup()
        {
        }
    }

    public static final class RmsGroupNotification
    {

        public static final Uri CONTENT_URI = Uri.parse("content://rms/rms_log_group_notification");
        public static final String DATE = "date";
        public static final String GROUP_CHAT_ID = "group_chat_id";
        public static final String INFO = "info";
        public static final int INFO_ACCEPTED = 2;
        public static final int INFO_DISSOLVE = 4;
        public static final int INFO_GROUPS_FULL = 6;
        public static final int INFO_INVITED = 0;
        public static final int INFO_KICKOUT = 3;
        public static final int INFO_MEMBER_FULL = 5;
        public static final int INFO_REJECTED = 1;
        public static final String NAME = "name";
        public static final String ORGANIZER_PHONE = "organizer_phone";
        public static final String SESSION_IDENTITY = "session_identity";
        public static final String TABLE_NAME = "group_notifications";
        public static final String _ID = "_id";


        public RmsGroupNotification()
        {
        }
    }

    public static interface TextBasedRmsColumns
    {

        public static final String ACTIVE_STATUS = "active_status";
        public static final String ADDRESS = "rms_address";
        public static final String AT_PHONES = "at_phones";
        public static final String BLOCKED = "block_type";
        public static final String BODY = "rms_body";
        public static final String CONTRIBUTION_ID = "contribution_id";
        public static final String CONVERSATION_ID = "conversation_id";
        public static final String DATE = "date";
        public static final String DATE_SENT = "date_sent";
        public static final String DEVICE_API_EXTRA = "device_api_extra";
        public static final String ERROR_CODE = "rms_error_code";
        public static final String FILE_DURATION = "file_duration";
        public static final String FILE_EXPIRE = "file_expire";
        public static final String FILE_NAME = "file_name";
        public static final String FILE_PATH = "file_path";
        public static final String FILE_SIZE = "file_size";
        public static final String FILE_TYPE = "file_type";
        public static final String FORWARDABLE = "forwardable";
        public static final String GROUP_CHAT_ID = "group_chat_id";
        public static final String IMDN_STATUS = "imdn_status";
        public static final String IMDN_STRING = "imdn_string";
        public static final String IMDN_TYPE = "imdn_type";
        public static final String IS_BURN_MSG = "is_burn_msg";
        public static final String LOCKED = "locked";
        public static final String MEDIA_UUID = "media_uuid";
        public static final String MESSAGE_TYPE = "rms_message_type";
        public static final int MESSAGE_TYPE_ALL = 0;
        public static final int MESSAGE_TYPE_DRAFT = 3;
        public static final int MESSAGE_TYPE_FAILED = 5;
        public static final int MESSAGE_TYPE_INBOX = 1;
        public static final int MESSAGE_TYPE_OUTBOX = 4;
        public static final int MESSAGE_TYPE_QUEUED = 6;
        public static final int MESSAGE_TYPE_SENT = 2;
        public static final int MESSAGE_TYPE_TRASH = 7;
        public static final String MIX_TYPE = "mix_type";
        public static final int MIX_TYPE_AT = 8;
        public static final int MIX_TYPE_CC = 1;
        public static final int MIX_TYPE_COMMON = 0;
        public static final int MIX_TYPE_DIRECT = 2;
        public static final int MIX_TYPE_SILENCE = 4;
        public static final String MSG_UUID = "msg_uuid";
        public static final String ORIGINAL_LINK = "original_link";
        public static final String PA_UUID = "pa_uuid";
        public static final String READ = "read";
        public static final String RMS_EXTENTION = "rms_extension";
        public static final String RMS_EXTRA = "rms_extension";
        public static final int RMS_MESSAGE_TYPE_CARD = 12;
        public static final int RMS_MESSAGE_TYPE_CLOUD_FILE = 11;
        public static final int RMS_MESSAGE_TYPE_EMOTICON = 10;
        public static final int RMS_MESSAGE_TYPE_FILE = 6;
        public static final int RMS_MESSAGE_TYPE_GEO = 4;
        public static final int RMS_MESSAGE_TYPE_IMAGE = 1;
        public static final int RMS_MESSAGE_TYPE_MART = 8;
        public static final int RMS_MESSAGE_TYPE_SART = 7;
        public static final int RMS_MESSAGE_TYPE_SMS = 9;
        public static final int RMS_MESSAGE_TYPE_SYSTEM = 100;
        public static final int RMS_MESSAGE_TYPE_TEXT = 0;
        public static final int RMS_MESSAGE_TYPE_VCARD = 5;
        public static final int RMS_MESSAGE_TYPE_VIDEO = 3;
        public static final int RMS_MESSAGE_TYPE_VOICE = 2;
        public static final String SEEN = "seen";
        public static final String SMS_DIGEST = "sms_digest";
        public static final String STATUS = "rms_status";
        public static final int STATUS_FAIL = 2;
        public static final int STATUS_INIT = 0;
        public static final int STATUS_PENDING = 1;
        public static final int STATUS_RECEIVED = 5;
        public static final int STATUS_SUCC = 3;
        public static final int STATUS_TRANSLATED = 4;
        public static final String SUB_ID = "sub_id";
        public static final String THREAD_ID = "thread_id";
        public static final String THUMB_LINK = "thumb_link";
        public static final String THUMB_PATH = "thumb_path";
        public static final String TIMESTAMP = "timestamp";
        public static final String TITLE = "title";
        public static final String TRANS_ID = "trans_id";
        public static final String TRANS_SIZE = "trans_size";
        public static final String TYPE = "rms_type";
        public static final String _ID = "_id";
    }

    public static interface TextBasedRmsGroupColumns
    {

        public static final String BEGIN_TIME = "begin_time";
        public static final String CHAIRMAN = "chairman";
        public static final String DIRECTION = "direction";
        public static final String DURATION = "duration";
        public static final String END_TIME = "end_time";
        public static final String GROUP_CHAT_ID = "group_chat_id";
        public static final String MY_NICK_NAME = "my_nick_name";
        public static final String NAME = "name";
        public static final String NICK_NAME = "nick_name";
        public static final String ORGANIZER_PHONE = "organizer_phone";
        public static final String RECV_TYPE = "recv_type";
        public static final int RECV_TYPE_NORMAL = 0;
        public static final int RECV_TYPE_NOT_NOTIFY = 1;
        public static final int RECV_TYPE_REJECT = 2;
        public static final String SESSION_IDENTITY = "session_identity";
        public static final String STATE = "state";
        public static final int STATE_ABORTED = 6;
        public static final int STATE_CLOSED_BY_USER = 5;
        public static final int STATE_FAILED = 7;
        public static final int STATE_INITIATED = 2;
        public static final int STATE_INVITED = 1;
        public static final int STATE_STARTED = 3;
        public static final int STATE_TERMINATED = 4;
        public static final String TYPE = "type";
        public static final String _ID = "_id";
    }

    public static final class Threads
    {

        public static final String DATE = "date";
        public static final String ERROR = "error";
        public static final String HAS_ATTACHMENT = "has_attachment";
        public static final String IS_LAST_MESSAGE_BURN = "is_last_message_burn";
        public static final String LAST_FILE_MIME_TYPE = "last_file_mime_type";
        public static final String LAST_MESSAGE_TYPE = "last_message_type";
        public static final String LAST_MESSAGE_TYPE_CARD = "card";
        public static final String LAST_MESSAGE_TYPE_CLOUD_FILE = "cloud_file";
        public static final String LAST_MESSAGE_TYPE_EMOTICON = "emoticon";
        public static final String LAST_MESSAGE_TYPE_FILE = "file";
        public static final String LAST_MESSAGE_TYPE_GEO = "geo";
        public static final String LAST_MESSAGE_TYPE_IMAGE = "image";
        public static final String LAST_MESSAGE_TYPE_MART = "mart";
        public static final String LAST_MESSAGE_TYPE_MMS = "mms";
        public static final String LAST_MESSAGE_TYPE_OTHER = "other";
        public static final String LAST_MESSAGE_TYPE_RMS_SMS = "rms_sms";
        public static final String LAST_MESSAGE_TYPE_SART = "sart";
        public static final String LAST_MESSAGE_TYPE_SMS = "sms";
        public static final String LAST_MESSAGE_TYPE_SYSTEM = "text";
        public static final String LAST_MESSAGE_TYPE_TEXT = "text";
        public static final String LAST_MESSAGE_TYPE_VCARD = "vcard";
        public static final String LAST_MESSAGE_TYPE_VIDEO = "video";
        public static final String LAST_MESSAGE_TYPE_VOICE = "voice";
        public static final String MESSAGE_COUNT = "message_count";
        public static final String PRIORITY = "priority";
        public static final String PRIORITY_ORDER = "priority desc, date desc";
        public static final String READ = "read";
        public static final String RECIPIENTS = "recipients";
        public static final String RECIPIENT_IDS = "recipient_ids";
        public static final String RMS_THREAD_TYPE = "rms_type";
        public static final String SNIPPET = "snippet";
        public static final String SNIPPET_CHARSET = "snippet_cs";
        public static final String TYPE = "type";
        public static final String UNREAD_MESSAGE_COUNT = "unread_message_count";
        public static final Uri URI_DELETE_THREAD_MESSAGES = Uri.parse("content://mms-sms/conversations/messages");
        public static final Uri URI_THREAD_PRIORITY = Uri.parse("content://mms-sms/threadPriority");
        public static final String _ID = "_id";


        public Threads()
        {
        }
    }


    public RmsDefine()
    {
    }

    public static final int BROADCAST_THREAD = 1;
    public static final int COMMON_THREAD = 0;
    public static final String FAVORITES_FILE_PATH = (new StringBuilder()).append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/.Rcs/favoritefiles").toString();
    public static final String PC_ADDRESS = "rcs_pc@rcs.xiaomi.com";
    public static final String RCS_GROUP_AUTHORITY = "rcsgroup";
    public static final String REPOORT_PHONE = "100869999";
    public static final String RMS_AUTHORITY = "rms";
    public static final String RMS_FILE_PATH = (new StringBuilder()).append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/.Rcs/rmsfiles").toString();
    public static final int RMS_GROUP_THREAD = 2;
    public static final String RMS_ICON_PATH = (new StringBuilder()).append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/.Rcs/icon").toString();
    public static final int RMS_PUBLIC_THREAD = 3;
    public static final String RMS_TEMP_FILE_PATH = (new StringBuilder()).append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/.Rcs/temp").toString();
    public static final String RMS_THUMB_PATH = (new StringBuilder()).append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/.Rcs/thumb").toString();

}
