// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.content.*;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;
import miui.securityspace.CrossUserUtils;
import miui.telephony.PhoneNumberUtils;
import miui.util.IOUtils;

public final class ExtraTelephony
{
    public static class AdvancedSeen
    {

        public static final int NON_SP_UNSEEN = 0;
        public static final int SEEN = 3;
        public static final int SP_NOTIFIED = 2;
        public static final int SP_UNSEEN = 1;

        public AdvancedSeen()
        {
        }
    }

    public static interface AntiSpamAccount
        extends BaseColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final String ACCOUNT_TYPE = "account_type";
        public static final Uri CONTENT_URI = Uri.parse("content://antispam/account");
        public static final String DATA = "data";

    }

    public static final class AntiSpamMode
        implements BaseColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/antispam-mode";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/antispam-mode";
        public static final Uri CONTENT_URI = Uri.parse("content://antispam/mode");
        public static final String NAME = "name";
        public static final String STATE = "state";


        public AntiSpamMode()
        {
        }
    }

    public static final class AntiSpamSim
        implements BaseColumns
    {

        public static final String BACKSOUND_MODE = "backsound_mode";
        public static final String CALL_WAIT = "call_wait";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/antispam-sim";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/antispam-sim";
        public static final Uri CONTENT_URI = Uri.parse("content://antispam/sim");
        public static final String NAME = "name";
        public static final String SIM_ID = "sim_id";
        public static final String STATE = "state";


        public AntiSpamSim()
        {
        }
    }

    public static final class Blacklist
        implements BaseColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/firewall-blacklist";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/firewall-blacklist";
        public static final Uri CONTENT_URI = Uri.parse("content://firewall/blacklist");
        public static final String DISPLAY_NUMBER = "display_number";
        public static final String NOTES = "notes";
        public static final String NUMBER = "number";
        public static final String STATE = "state";


        public Blacklist()
        {
        }
    }

    public static interface DeletableSyncColumns
        extends SyncColumns
    {

        public static final String DELETED = "deleted";
    }

    public static final class FirewallLog
        implements BaseColumns
    {

        public static final String CALL_TYPE = "callType";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/antispam-log";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/antispam-log";
        public static final Uri CONTENT_URI = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/log"), 0);
        public static final Uri CONTENT_URI_LOG_CONVERSATION = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/logconversation"), 0);
        public static final Uri CONTENT_URI_SMS_LOG = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/log_sms"), 0);
        public static final String DATA1 = "data1";
        public static final String DATA2 = "data2";
        public static final String DATE = "date";
        public static final String MODE = "mode";
        public static final String NUMBER = "number";
        public static final String READ = "read";
        public static final String REASON = "reason";
        public static final String SIM_ID = "simid";
        public static final String TYPE = "type";
        public static final int TYPE_CALL = 1;
        public static final int TYPE_SMS = 2;


        public FirewallLog()
        {
        }
    }

    public static interface FirewallLog.CallBlockType
    {

        public static final int ADDRESS = 13;
        public static final int AGENT = 10;
        public static final int BLACKLIST = 3;
        public static final int CALL_TRANSFER = 15;
        public static final int CLOUDS = 16;
        public static final int CONTACT = 9;
        public static final int FRAUD = 8;
        public static final int HARASS = 14;
        public static final int IMPORT = 5;
        public static final int MUTE_BY_QM = 1;
        public static final int MUTE_NEED_CHECK = 2;
        public static final int NONE = 0;
        public static final int NONE_NEED_CHECK = -1;
        public static final int OVERSEA = 17;
        public static final int PREFIX = 6;
        public static final int PRIVATE_CALL = 4;
        public static final int SELL = 12;
        public static final int STRANGER = 7;
    }

    public static interface FirewallLog.SmsBlockType
    {

        public static final int ADDRESS = 13;
        public static final int BLACKLIST = 3;
        public static final int CLOUDS = 16;
        public static final int CONTACT = 9;
        public static final int FILTER = 4;
        public static final int IMPORT = 5;
        public static final int KEYWORDS = 12;
        public static final int NONE = 0;
        public static final int NONE_BUT_MUTE = 1;
        public static final int PREFIX = 6;
        public static final int SERVICE = 10;
        public static final int STRANGER = 7;
        public static final int URL = 8;
    }

    public static final class Hms
        implements BaseColumns
    {

        public static final String ADDRESS = "address";
        public static final String ADVANCED_SEEN = "advanced_seen";
        public static final Uri CONTENT_URI;
        public static final String DATE = "date";
        public static final String MX_CONTENT = "mx_content";
        public static final String MX_EXTENSION = "mx_extension";
        public static final String MX_MESSAGE_ID = "mx_message_id";
        public static final String MX_SEQ = "mx_seq";
        public static final String MX_TYPE = "mx_type";
        public static final String READ = "read";
        public static final String SEEN = "seen";
        public static final String SNIPPET = "snippet";
        public static final String THREAD_ID = "thread_id";
        public static final Uri THREAD_ID_CONTENT_URI;
        public static final String TYPE = "type";

        static 
        {
            CONTENT_URI = Uri.parse("content://hms/");
            THREAD_ID_CONTENT_URI = Uri.withAppendedPath(CONTENT_URI, "threadId");
        }

        public Hms()
        {
        }
    }

    public static final class Judge
        implements BaseColumns
    {

        public static final Uri CALL_CONTENT_URI = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/call_judge"), 0);
        public static final Uri CALL_TRANSFER_CONTENT_URI = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/call_transfer_intercept_judge"), 0);
        public static final int FORWARD_CALL_ALLOW = 0;
        public static final int FORWARD_CALL_INTERCEPT = 1;
        public static final String IS_FORWARD_CALL = "is_forward_call";
        public static final String IS_REPEATED_BLOCKED_CALL = "is_repeated_blocked_call";
        public static final String IS_REPEATED_NORMAL_CALL = "is_repeated_normal_call";
        public static final Uri SERVICE_NUM_CONTENT_URI = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/service_num_judge"), 0);
        public static final Uri SMS_CONTENT_URI = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/sms_judge"), 0);
        public static final Uri URL_CONTENT_URI = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/url_judge"), 0);
        public static final int URL_SCAN_RESULT_DANGEROUS = 2;
        public static final int URL_SCAN_RESULT_NORMAL = 0;
        public static final int URL_SCAN_RESULT_RISKY = 1;
        public static final int URL_SCAN_RESULT_UNKNOWN = -1;


        public Judge()
        {
        }
    }

    public static final class Keyword
        implements BaseColumns
    {

        public static final String CLOUD_UID = "cloudUid";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/antispam-keyword";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/antispam-keyword";
        public static final Uri CONTENT_URI = Uri.parse("content://antispam/keyword");
        public static final String DATA = "data";
        public static final String SIM_ID = "sim_id";
        public static final String TYPE = "type";
        public static final int TYPE_CLOUDS_BLACK = 2;
        public static final int TYPE_CLOUDS_WHITE = 3;
        public static final int TYPE_LOCAL_BLACK = 1;
        public static final int TYPE_LOCAL_WHITE = 4;


        public Keyword()
        {
        }
    }

    public static final class Mms
        implements DeletableSyncColumns
    {

        public static final String ACCOUNT = "account";
        public static final String ADDRESSES = "addresses";
        public static final String ADVANCED_SEEN = "advanced_seen";
        public static final String BIND_ID = "bind_id";
        public static final String BLOCK_TYPE = "block_type";
        public static final String DATE_FULL = "date_full";
        public static final String DATE_MS_PART = "date_ms_part";
        public static final String ERROR_TYPE = "error_type";
        public static final String FAVORITE_DATE = "favorite_date";
        public static final String FILE_ID = "file_id";
        public static final String MX_EXTENSION = "mx_extension";
        public static final String MX_ID = "mx_id";
        public static final String MX_STATUS = "mx_status";
        public static final String MX_TYPE = "mx_type";
        public static final String NEED_DOWNLOAD = "need_download";
        public static final String OUT_TIME = "out_time";
        public static final String PREVIEW_DATA = "preview_data";
        public static final String PREVIEW_DATA_TS = "preview_data_ts";
        public static final String PREVIEW_TYPE = "preview_type";
        public static final String SIM_ID = "sim_id";
        public static final String SNIPPET = "snippet";
        public static final String TIMED = "timed";

        public Mms()
        {
        }
    }

    public static final class Mms.Intents
    {

        public static final String MAKE_MMS_PREVIEW_ACTION = "android.provider.Telephony.MAKE_MMS_PREVIEW";

        public Mms.Intents()
        {
        }
    }

    public static final class Mms.PreviewType
    {

        public static final int AUDIO = 3;
        public static final int IMAGE = 2;
        public static final int NONE = 1;
        public static final int SLIDESHOW = 6;
        public static final int UNKNOWN = 0;
        public static final int VCARD = 5;
        public static final int VIDEO = 4;

        public Mms.PreviewType()
        {
        }
    }

    public static final class MmsSms
    {

        public static final Uri BLOCKED_CONVERSATION_CONTENT_URI = Uri.parse("content://mms-sms/blocked");
        public static final Uri BLOCKED_THREAD_CONTENT_URI = Uri.parse("content://mms-sms/blocked-thread");
        public static final Uri CONTENT_ALL_LOCKED_URI = Uri.parse("content://mms-sms/locked/all");
        public static final Uri CONTENT_ALL_UNDERSTAND_INFO_URI = Uri.parse("content://mms-sms/understand-info/all");
        public static final Uri CONTENT_EXPIRED_URI = Uri.parse("content://mms-sms/expired");
        public static final Uri CONTENT_PREVIEW_URI = Uri.parse("content://mms-sms/message/preview");
        public static final Uri CONTENT_RECENT_RECIPIENTS_URI = Uri.parse("content://mms-sms/recent-recipients");
        public static final Uri CONTENT_UNDERSTAND_INFO_URI = Uri.parse("content://mms-sms/understand-info");
        public static final String EXCLUDE_VERIFICATION_CODES_FLAG = "exclude_verification_codes";
        public static final String EXCLUDE_VERIFICATION_CODES_FLAG_EXCLUDE = "1";
        public static final String EXCLUDE_VERIFICATION_CODES_FLAG_NOT_EXCLUDE = "0";
        public static final String INSERT_PATH_IGNORED = "ignored";
        public static final String INSERT_PATH_INSERTED = "inserted";
        public static final String INSERT_PATH_RESTORED = "restored";
        public static final String INSERT_PATH_UPDATED = "updated";
        public static final int PREVIEW_ADDRESS_COLUMN_INDEX = 1;
        public static final int PREVIEW_BODY_COLUMN_INDEX = 4;
        public static final int PREVIEW_CHARSET_COLUMN_INDEX = 5;
        public static final int PREVIEW_DATE_COLUMN_INDEX = 2;
        public static final int PREVIEW_ID_COLUMN_INDEX = 0;
        public static final int PREVIEW_THREAD_ID_COLUMN_INDEX = 6;
        public static final int PREVIEW_TYPE_COLUMN_INDEX = 3;
        public static final int SYNC_STATE_DIRTY = 0;
        public static final int SYNC_STATE_ERROR = 3;
        public static final int SYNC_STATE_MARKED_DELETING = 0x10002;
        public static final int SYNC_STATE_NOT_UPLOADABLE = 4;
        public static final int SYNC_STATE_SYNCED = 2;
        public static final int SYNC_STATE_SYNCING = 1;


        public MmsSms()
        {
        }
    }

    public static final class Mx
    {

        public static final int TYPE_COMMON = 0;
        public static final int TYPE_DELIVERED = 17;
        public static final int TYPE_FAILED = 0x20001;
        public static final int TYPE_INCOMING = 0x10001;
        public static final int TYPE_PENDING = 1;
        public static final int TYPE_READ = 256;
        public static final int TYPE_SENT = 16;
        public static final int TYPE_WEB = 0x30001;

        public Mx()
        {
        }
    }

    public static class MxType
    {

        public static final int AUDIO = 3;
        public static final int IMAGE = 2;
        public static final int MMS = 1;
        public static final int NONE_MX = 0;
        public static final int RED = 5;
        public static final int VIDEO = 4;

        public MxType()
        {
        }
    }

    public static final class Phonelist
        implements BaseColumns
    {

        public static final String CLOUD_UUID = "cloudUid";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/antispam-phone_list";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/antispam-phone_list";
        public static final Uri CONTENT_URI = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/phone_list"), 0);
        public static final Uri CONTENT_URI_SYNCED_COUNT = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/synced_count"), 0);
        public static final Uri CONTENT_URI_UNSYNCED_COUNT = CrossUserUtils.addUserIdForUri(Uri.parse("content://antispam/unsynced_count"), 0);
        public static final String DISPLAY_NUMBER = "display_number";
        public static final String E_TAG = "e_tag";
        public static final String IS_DISPLAY = "isdisplay";
        public static final String LOCATION = "location";
        public static final String NOTES = "notes";
        public static final String NUMBER = "number";
        public static final String RECORD_ID = "record_id";
        public static final String SIM_ID = "sim_id";
        public static final String STATE = "state";
        public static final String SYNC_DIRTY = "sync_dirty";
        public static final String TYPE = "type";
        public static final String TYPE_BLACK = "1";
        public static final String TYPE_CLOUDS_BLACK = "4";
        public static final String TYPE_CLOUDS_WHITE = "5";
        public static final String TYPE_STRONG_CLOUDS_BLACK = "6";
        public static final String TYPE_STRONG_CLOUDS_WHITE = "7";
        public static final String TYPE_VIP = "3";
        public static final String TYPE_WHITE = "2";
        public static final String UNKNOWN_NUMBER = "-1";


        public Phonelist()
        {
        }
    }

    public static final class Phonelist.Location
    {

        public static final int IS_CLOUD = 1;
        public static final int IS_LOCAL = 0;

        public Phonelist.Location()
        {
        }
    }

    public static final class Phonelist.State
    {

        public static final int ALL = 0;
        public static final int CALL = 2;
        public static final int MSG = 1;

        public Phonelist.State()
        {
        }
    }

    public static final class Phonelist.SyncDirty
    {

        public static final int ADD = 0;
        public static final int DELETE = 1;
        public static final int SYNCED = 3;
        public static final int UPDATE = 2;

        public Phonelist.SyncDirty()
        {
        }
    }

    public static interface PrivateAddresses
        extends DeletableSyncColumns
    {

        public static final String ADDRESS = "address";
        public static final Uri CONTENT_URI = Uri.parse("content://mms-sms/private-addresses");
        public static final String _ID = "_id";

    }

    public static interface QuietModeEnableListener
    {

        public abstract void onQuietModeEnableChange(boolean flag);
    }

    public static class ServiceCategory
    {

        public static final int DEFAULT_SERVICE_NUMBER = 1;
        public static final int FINANCE_NUMBER = 2;
        public static final int NOT_SERVICE_NUMBER = 0;

        public ServiceCategory()
        {
        }
    }

    private static class SilentModeObserver extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            super.onChange(flag);
            if(ExtraTelephony._2D_get0() != null)
            {
                for(Iterator iterator = ExtraTelephony._2D_get0().iterator(); iterator.hasNext(); ((QuietModeEnableListener)iterator.next()).onQuietModeEnableChange(android.provider.MiuiSettings.SilenceMode.isSilenceModeEnable(mContext)));
            }
        }

        private Context mContext;

        public SilentModeObserver(Context context, Handler handler)
        {
            super(handler);
            handler = context;
            if(context.getApplicationContext() != null)
                handler = context.getApplicationContext();
            mContext = handler;
        }
    }

    public static final class SimCards
    {

        public static final String BIND_ID = "bind_id";
        public static final Uri CONTENT_URI = Uri.parse("content://mms-sms/sim-cards");
        public static final String DL_STATUS = "download_status";
        public static final String IMSI = "imsi";
        public static final String MARKER1 = "marker1";
        public static final String MARKER2 = "marker2";
        public static final String MARKER_BASE = "marker_base";
        public static final String NUMBER = "number";
        public static final String SIM_ID = "sim_id";
        public static final String SLOT = "slot";
        public static final String SYNC_ENABLED = "sync_enabled";
        public static final String SYNC_EXTRA_INFO = "sync_extra_info";
        public static final String _ID = "_id";


        public SimCards()
        {
        }
    }

    public static final class SimCards.DLStatus
    {

        public static final int FINISH = 2;
        public static final int INIT = 0;
        public static final int NEED = 1;

        public SimCards.DLStatus()
        {
        }
    }

    public static final class SimCards.SyncStatus
    {

        public static final int ACTIVE = 1;
        public static final int CLOSED = 2;
        public static final int DIRTY_MASK = 10000;
        public static final int INACTIVE = 0;

        public SimCards.SyncStatus()
        {
        }
    }

    public static final class Sms
        implements TextBasedSmsColumns, DeletableSyncColumns
    {

        public static final String ACCOUNT = "account";
        public static final String ADDRESSES = "addresses";
        public static final String ADVANCED_SEEN = "advanced_seen";
        public static final String B2C_NUMBERS = "b2c_numbers";
        public static final String B2C_TTL = "b2c_ttl";
        public static final String BIND_ID = "bind_id";
        public static final String BLOCK_TYPE = "block_type";
        public static final String FAKE_CELL_TYPE = "fake_cell_type";
        public static final String FAVORITE_DATE = "favorite_date";
        public static final String MX_ID = "mx_id";
        public static final String MX_STATUS = "mx_status";
        public static final String OUT_TIME = "out_time";
        public static final String SIM_ID = "sim_id";
        public static final String TIMED = "timed";
        public static final String URL_RISKY_TYPE = "url_risky_type";

        public Sms()
        {
        }
    }

    public static final class Sms.FakeCellType
    {

        public static final int CHECKED_SAFE = -1;
        public static final int FAKE = 1;
        public static final int NORMAL = 0;

        public Sms.FakeCellType()
        {
        }
    }

    public static final class Sms.Intents
    {

        public static final String DISMISS_NEW_MESSAGE_NOTIFICATION_ACTION = "android.provider.Telephony.DISMISS_NEW_MESSAGE_NOTIFICATION";

        public Sms.Intents()
        {
        }
    }

    public static final class Sms.UrlRiskyType
    {

        public static final int URL_FRAUD_DANGEROUS = 3;
        public static final int URL_RISKY = 2;
        public static final int URL_SAFE = 0;
        public static final int URL_SUSPICIOUS = 1;

        public Sms.UrlRiskyType()
        {
        }
    }

    public static final class SmsPhrase
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/smsphrase";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/smsphrase";

        public SmsPhrase()
        {
        }
    }

    public static interface SyncColumns
    {

        public static final String MARKER = "marker";
        public static final String SOURCE = "source";
        public static final String SYNC_STATE = "sync_state";
    }

    public static interface TextBasedSmsColumns
    {

        public static final int MESSAGE_TYPE_INVALID = 7;
    }

    public static final class Threads
        implements ThreadsColumns
    {

        public Threads()
        {
        }
    }

    public static final class Threads.Intents
    {

        public static final String THREADS_OBSOLETED_ACTION = "android.intent.action.SMS_THREADS_OBSOLETED_ACTION";

        public Threads.Intents()
        {
        }
    }

    public static interface ThreadsColumns
        extends SyncColumns
    {

        public static final String HAS_DRAFT = "has_draft";
        public static final String LAST_SIM_ID = "last_sim_id";
        public static final String MX_SEQ = "mx_seq";
        public static final String PRIVATE_ADDR_IDS = "private_addr_ids";
        public static final String RMS_TYPE = "rms_type";
        public static final String SP_TYPE = "sp_type";
        public static final String STICK_TIME = "stick_time";
        public static final String UNREAD_COUNT = "unread_count";
    }

    public static final class UnderstandInfo
    {

        public static final String CLASS = "class";
        public static final String MSG_ID = "msg_id";
        public static final String OUT_OF_DATE = "out_of_date";
        public static final String VERSION = "version";

        public UnderstandInfo()
        {
        }
    }

    public static final class UnderstandInfo.UnderstandClass
    {

        public static final int NORMAL = 0;
        public static final int VERIFICATION_CODE = 1;

        public UnderstandInfo.UnderstandClass()
        {
        }
    }

    public static final class Whitelist
        implements BaseColumns
    {

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/firewall-whitelist";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/firewall-whitelist";
        public static final Uri CONTENT_URI = Uri.parse("content://firewall/whitelist");
        public static final String DISPLAY_NUMBER = "display_number";
        public static final String ISDISPLAY = "isdisplay";
        public static final String NOTES = "notes";
        public static final String NUMBER = "number";
        public static final String STATE = "state";
        public static final String VIP = "vip";


        public Whitelist()
        {
        }
    }


    static Set _2D_get0()
    {
        return mQuietListeners;
    }

    public ExtraTelephony()
    {
    }

    private static void appendNonSeparator(StringBuilder stringbuilder, char c, int i)
    {
        if(i == 0 && c == '+')
            return;
        if(Character.digit(c, 10) == -1 && PhoneNumberUtils.isNonSeparator(c))
            stringbuilder.append(c);
    }

    public static boolean checkKeyguardForQuiet(Context context, String s)
    {
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            return checkKeyguardForSilentMode(context);
        if(android.os.Build.VERSION.SDK_INT < 21)
            return android.provider.MiuiSettings.AntiSpam.isQuietModeEnable(context);
        if(!android.provider.MiuiSettings.AntiSpam.isQuietModeEnable(context))
            break MISSING_BLOCK_LABEL_128;
        if("com.android.mms".equals(s))
            return true;
        if("com.android.incallui".equals(s) || "com.android.server.telecom".equals(s))
            return true;
        context = context.getContentResolver().query(Uri.withAppendedPath(Uri.parse("content://antispamCommon/zenmode"), "4"), null, null, null, null);
        if(context != null)
        {
            if(context != null)
                context.close();
            return false;
        }
        if(context != null)
            context.close();
_L2:
        return true;
        context;
        context.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
        context;
        throw context;
        return false;
    }

    public static boolean checkKeyguardForSilentMode(Context context)
    {
        boolean flag = true;
        if(android.os.Build.VERSION.SDK_INT < 21)
            return android.provider.MiuiSettings.SilenceMode.isSilenceModeEnable(context);
        if(android.provider.MiuiSettings.SilenceMode.getZenMode(context) != 1)
            flag = false;
        return flag;
    }

    public static boolean checkMarkedNumberIntercept(Context context, int i, int j, String s, int k, boolean flag, int l)
    {
        String s1 = (String)((HashMap)android.provider.MiuiSettings.AntiSpam.mapIdToState.get(Integer.valueOf(i))).get(Integer.valueOf(j));
        if(s1 == null)
        {
            Slog.d("ExtraTelephony", "the mark type of cid is not found ... allow");
            return false;
        }
        boolean flag1;
        if(android.provider.MiuiSettings.AntiSpam.getMode(context, s1, 1) == 0)
            flag1 = true;
        else
            flag1 = false;
        if(!flag1)
        {
            Slog.d("ExtraTelephony", (new StringBuilder()).append("the switch of ").append(s1).append(" is not open ... allow").toString());
            return false;
        }
        if(isRelatedNumber(context, s))
        {
            Slog.d("ExtraTelephony", "call number is a related number... allow");
            return false;
        }
        boolean flag2;
        if(android.provider.MiuiSettings.AntiSpam.getMode(context, (String)((HashMap)android.provider.MiuiSettings.AntiSpam.mapIdToMarkTime.get(Integer.valueOf(i))).get(Integer.valueOf(j)), 50) <= l)
            flag2 = true;
        else
            flag2 = false;
        Slog.d("ExtraTelephony", (new StringBuilder()).append("marking threshold reached ? ").append(flag2).toString());
        while(flag || k == 398 || flag2) 
        {
            Slog.d("ExtraTelephony", "should intercept this marked call !");
            return true;
        }
        return false;
    }

    public static boolean containsKeywords(Context context, String s, int i, int j)
    {
        context = context.getContentResolver().query(Keyword.CONTENT_URI, null, "type = ? AND sim_id = ? ", new String[] {
            String.valueOf(i), String.valueOf(j)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_108;
_L2:
        boolean flag;
        if(!context.moveToNext())
            break; /* Loop/switch isn't completed */
        String s1 = context.getString(context.getColumnIndex("data")).trim();
        if(TextUtils.isEmpty(s1))
            continue; /* Loop/switch isn't completed */
        flag = s.toLowerCase().contains(s1.toLowerCase());
        if(flag)
        {
            context.close();
            return true;
        }
        if(true) goto _L2; else goto _L1
_L1:
        context.close();
_L4:
        return false;
        s;
        Log.e("ExtraTelephony", "Cursor exception in shouldFilter()", s);
        context.close();
        if(true) goto _L4; else goto _L3
_L3:
        s;
        context.close();
        throw s;
    }

    private static String convertPresentationToFilterNumber(int i, String s)
    {
        if(i != 2) goto _L2; else goto _L1
_L1:
        s = "-2";
_L4:
        return s;
_L2:
        if(i == 4)
            s = "-3";
        else
        if(TextUtils.isEmpty(s) || i == 3)
            s = "-1";
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int getCallBlockType(Context context, String s, int i, boolean flag, boolean flag1, boolean flag2)
    {
        if(TextUtils.isEmpty(s))
            return 0;
        boolean flag3 = false;
        context = new FutureTask(new Callable(context, s, i, flag, flag1, flag2) {

            public Integer call()
                throws Exception
            {
                ContentResolver contentresolver = context.getContentResolver();
                Uri uri = Judge.CALL_CONTENT_URI;
                ContentValues contentvalues = new ContentValues();
                String s1 = number;
                int j = slotId;
                String s2;
                String s3;
                String s4;
                if(isForwardCall)
                    s2 = "is_forward_call";
                else
                    s2 = "";
                if(isRepeated)
                    s3 = "is_repeated_normal_call";
                else
                    s3 = "";
                if(isRepeatedBlocked)
                    s4 = "is_repeated_blocked_call";
                else
                    s4 = "";
                return Integer.valueOf(contentresolver.update(uri, contentvalues, null, new String[] {
                    s1, String.valueOf(j), s2, s3, s4
                }));
            }

            public volatile Object call()
                throws Exception
            {
                return call();
            }

            final Context val$context;
            final boolean val$isForwardCall;
            final boolean val$isRepeated;
            final boolean val$isRepeatedBlocked;
            final String val$number;
            final int val$slotId;

            
            {
                context = context1;
                number = s;
                slotId = i;
                isForwardCall = flag;
                isRepeated = flag1;
                isRepeatedBlocked = flag2;
                super();
            }
        }
);
        try
        {
            s = JVM INSTR new #488 <Class Thread>;
            s.Thread(context);
            s.start();
            i = ((Integer)context.get(5000L, TimeUnit.MILLISECONDS)).intValue();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("ExtraTelephony", "InterruptedException when getCallBlockType", context);
            i = ((flag3) ? 1 : 0);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("ExtraTelephony", "ExecutionException when getCallBlockType", context);
            i = ((flag3) ? 1 : 0);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            if(!context.isDone())
                context.cancel(true);
            Log.e("ExtraTelephony", "TimeoutException when getCallBlockType", s);
            i = ((flag3) ? 1 : 0);
        }
        return i;
    }

    public static int getRealBlockType(int i)
    {
        return i & 0x7f;
    }

    public static int getSmsBlockType(Context context, String s, String s1, int i)
    {
        if(TextUtils.isEmpty(s))
            return 0;
        if(s1 == null)
            s1 = "";
        int j = 0;
        try
        {
            ContentResolver contentresolver = context.getContentResolver();
            Uri uri = Judge.SMS_CONTENT_URI;
            context = JVM INSTR new #533 <Class ContentValues>;
            context.ContentValues();
            i = contentresolver.update(uri, context, null, new String[] {
                s, s1, String.valueOf(i)
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("ExtraTelephony", "getSmsBlockType error", context);
            i = j;
        }
        j = i;
        if(i < 0)
            j = 0;
        return j;
    }

    public static int getSmsURLScanResult(Context context, String s, String s1)
    {
        s = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(s).getNormalizedNumber(false, true);
        int i;
        try
        {
            context = context.getContentResolver();
            Uri uri = Judge.URL_CONTENT_URI;
            ContentValues contentvalues = JVM INSTR new #533 <Class ContentValues>;
            contentvalues.ContentValues();
            i = context.update(uri, contentvalues, null, new String[] {
                s, s1
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("ExtraTelephony", "Exception when getSmsURLScanResult()", context);
            return -1;
        }
        return i;
    }

    public static boolean isAddressInBlack(Context context, String s, int i, int j)
    {
        context = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ? ", new String[] {
            (new StringBuilder()).append("***").append(miui.telephony.PhoneNumberUtils.PhoneNumber.getLocationAreaCode(context, s)).toString(), "1", String.valueOf(j), String.valueOf(1)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_117;
        if(!context.moveToNext())
            break MISSING_BLOCK_LABEL_111;
        j = context.getInt(context.getColumnIndex("state"));
        if(j == 0 || j == i)
        {
            context.close();
            return true;
        }
        context.close();
_L2:
        return false;
        s;
        Log.e("ExtraTelephony", "Cursor exception in isAddressInBlack(): ", s);
        context.close();
        if(true) goto _L2; else goto _L1
_L1:
        s;
        context.close();
        throw s;
    }

    public static boolean isCallTransferBlocked(Context context, int i)
    {
        boolean flag = true;
        try
        {
            ContentResolver contentresolver = context.getContentResolver();
            Uri uri = Judge.CALL_TRANSFER_CONTENT_URI;
            context = JVM INSTR new #533 <Class ContentValues>;
            context.ContentValues();
            i = contentresolver.update(uri, context, null, new String[] {
                String.valueOf(i)
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("ExtraTelephony", "Exception when isCallTransferBlocked()", context);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public static boolean isInBlacklist(Context context, String s)
    {
        Object obj;
        if(TextUtils.isEmpty(s))
            return false;
        Object obj1;
        int i;
        if(s.contains("*"))
            obj = normalizeNumber(s);
        else
            obj = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(s).getNormalizedNumber(false, true);
        if(TextUtils.isEmpty(((CharSequence) (obj))))
            obj1 = s;
        else
            obj1 = obj;
        obj = null;
        s = null;
        context = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sync_dirty <> ? ", new String[] {
            obj1, "1", String.valueOf(1)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_128;
        s = context;
        obj = context;
        i = context.getCount();
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        IOUtils.closeQuietly(context);
        return flag;
        IOUtils.closeQuietly(context);
_L2:
        return false;
        context;
        obj = s;
        Log.e("ExtraTelephony", "Cursor exception in isInBlacklist(): ", context);
        IOUtils.closeQuietly(s);
        if(true) goto _L2; else goto _L1
_L1:
        context;
        IOUtils.closeQuietly(((java.io.Closeable) (obj)));
        throw context;
    }

    public static boolean isInBlacklist(Context context, String s, int i)
    {
        if(TextUtils.isEmpty(s))
            return false;
        String s1;
        if(s.contains("*"))
            s1 = normalizeNumber(s);
        else
            s1 = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(s).getNormalizedNumber(false, true);
        if(!TextUtils.isEmpty(s1))
            s = s1;
        context = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ? ", new String[] {
            s, "1", String.valueOf(i), String.valueOf(1)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_142;
        i = context.getCount();
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        context.close();
        return flag;
        s;
        Log.e("ExtraTelephony", "Cursor exception in isInBlacklist(): ", s);
        context.close();
        return false;
        s;
        context.close();
        throw s;
    }

    public static boolean isInBlacklist(Context context, String s, int i, int j)
    {
        if(TextUtils.isEmpty(s))
            return false;
        context = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ? ", new String[] {
            s, "1", String.valueOf(j), String.valueOf(1)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_104;
        if(!context.moveToNext())
            break MISSING_BLOCK_LABEL_98;
        j = context.getInt(context.getColumnIndex("state"));
        if(j == 0 || j == i)
        {
            context.close();
            return true;
        }
        context.close();
_L2:
        return false;
        s;
        Log.e("ExtraTelephony", "Cursor exception in isInBlacklist(): ", s);
        context.close();
        if(true) goto _L2; else goto _L1
_L1:
        s;
        context.close();
        throw s;
    }

    public static boolean isInCloudPhoneList(Context context, String s, int i, String s1)
    {
        Object obj;
        if(TextUtils.isEmpty(s))
            return false;
        obj = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? ", new String[] {
            s, s1
        }, null);
        if(obj == null)
            break MISSING_BLOCK_LABEL_99;
        int j;
        if(!((Cursor) (obj)).moveToNext())
            break MISSING_BLOCK_LABEL_92;
        j = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndex("state"));
        if(j == 0 || j == i)
        {
            ((Cursor) (obj)).close();
            return true;
        }
        ((Cursor) (obj)).close();
_L5:
        obj = "";
        j = 0;
_L2:
        Object obj1;
        if(j >= s.length())
            break; /* Loop/switch isn't completed */
        obj = (new StringBuilder()).append(((String) (obj))).append(s.charAt(j)).toString();
        obj1 = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? ", new String[] {
            (new StringBuilder()).append(((String) (obj))).append("*").toString(), s1
        }, null);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_285;
        int k;
        if(!((Cursor) (obj1)).moveToNext())
            break MISSING_BLOCK_LABEL_278;
        k = ((Cursor) (obj1)).getInt(((Cursor) (obj1)).getColumnIndex("state"));
        if(k == 0 || k == i)
        {
            ((Cursor) (obj1)).close();
            return true;
        }
        break MISSING_BLOCK_LABEL_278;
        obj1;
        Log.e("ExtraTelephony", "Cursor exception when check complete cloudPhoneList: ", ((Throwable) (obj1)));
        ((Cursor) (obj)).close();
        continue; /* Loop/switch isn't completed */
        context;
        ((Cursor) (obj)).close();
        throw context;
        ((Cursor) (obj1)).close();
_L3:
        j++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        Log.e("ExtraTelephony", "Cursor exception when check prefix cloudPhoneList: ", exception);
        ((Cursor) (obj1)).close();
          goto _L3
        context;
        ((Cursor) (obj1)).close();
        throw context;
_L1:
        return false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static boolean isInSmsWhiteList(Context context, String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        ContentResolver contentresolver = context.getContentResolver();
        context = miui.yellowpage.YellowPageContract.AntispamWhiteList.CONTNET_URI;
        String s1 = (new StringBuilder()).append("number LIKE '").append(s).append("%'").toString();
        context = contentresolver.query(context, new String[] {
            "number"
        }, s1, null, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_118;
label0:
        {
            boolean flag;
            do
            {
                if(!context.moveToNext())
                    break label0;
                if(TextUtils.equals(s, context.getString(0)))
                    break;
                flag = s.startsWith("106");
            } while(!flag);
            context.close();
            return true;
        }
        context.close();
_L2:
        return false;
        s;
        s.printStackTrace();
        context.close();
        if(true) goto _L2; else goto _L1
_L1:
        s;
        context.close();
        throw s;
    }

    public static boolean isInVipList(Context context, String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        context = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sync_dirty <> ? ", new String[] {
            s, "3", String.valueOf(1)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_93;
        int i = context.getCount();
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        context.close();
        return flag;
        s;
        Log.e("ExtraTelephony", "Cursor exception in isInVipList(): ", s);
        context.close();
        return false;
        s;
        context.close();
        throw s;
    }

    public static boolean isInWhiteList(Context context, String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        int i;
        if(s.contains("*"))
            s = normalizeNumber(s);
        else
            s = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(s).getNormalizedNumber(false, true);
        context = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sync_dirty <> ? ", new String[] {
            s, "2", String.valueOf(1)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_120;
        i = context.getCount();
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        context.close();
        return flag;
        s;
        Log.e("ExtraTelephony", "Cursor exception in isInWhiteList(): ", s);
        context.close();
        return false;
        s;
        context.close();
        throw s;
    }

    public static boolean isInWhiteList(Context context, String s, int i)
    {
        if(TextUtils.isEmpty(s))
            return false;
        if(s.contains("*"))
            s = normalizeNumber(s);
        else
            s = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(s).getNormalizedNumber(false, true);
        context = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ? ", new String[] {
            s, "2", String.valueOf(i), String.valueOf(1)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_127;
        i = context.getCount();
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        context.close();
        return flag;
        s;
        Log.e("ExtraTelephony", "Cursor exception in isInWhiteList(): ", s);
        context.close();
        return false;
        s;
        context.close();
        throw s;
    }

    public static boolean isInWhiteList(Context context, String s, int i, int j)
    {
        Object obj;
        if(TextUtils.isEmpty(s))
            return false;
        obj = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ? ", new String[] {
            (new StringBuilder()).append("***").append(miui.telephony.PhoneNumberUtils.PhoneNumber.getLocationAreaCode(context, s)).toString(), "2", String.valueOf(j), String.valueOf(1)
        }, null);
        if(obj == null)
            break MISSING_BLOCK_LABEL_136;
        int k;
        if(!((Cursor) (obj)).moveToNext())
            break MISSING_BLOCK_LABEL_129;
        k = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndex("state"));
        if(k == 0 || k == i)
        {
            ((Cursor) (obj)).close();
            return true;
        }
        ((Cursor) (obj)).close();
_L7:
        obj = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(s).getNormalizedNumber(false, true);
        s = "";
        k = 0;
_L2:
        Object obj1;
        if(k >= ((String) (obj)).length())
            break; /* Loop/switch isn't completed */
        s = (new StringBuilder()).append(s).append(((String) (obj)).charAt(k)).toString();
        obj1 = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ? ", new String[] {
            (new StringBuilder()).append(s).append("*").toString(), "2", String.valueOf(j), String.valueOf(1)
        }, null);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_346;
        int l;
        if(!((Cursor) (obj1)).moveToNext())
            break MISSING_BLOCK_LABEL_339;
        l = ((Cursor) (obj1)).getInt(((Cursor) (obj1)).getColumnIndex("state"));
        if(l == 0 || l == i)
        {
            ((Cursor) (obj1)).close();
            return true;
        }
        break MISSING_BLOCK_LABEL_339;
        obj1;
        Log.e("ExtraTelephony", "Cursor exception when area check in whiteList: ", ((Throwable) (obj1)));
        ((Cursor) (obj)).close();
        continue; /* Loop/switch isn't completed */
        context;
        ((Cursor) (obj)).close();
        throw context;
        ((Cursor) (obj1)).close();
_L3:
        k++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        Log.e("ExtraTelephony", "Cursor exception when prefix check in whiteList: ", exception);
        ((Cursor) (obj1)).close();
          goto _L3
        context;
        ((Cursor) (obj1)).close();
        throw context;
_L1:
        context = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number= ? AND type= ? AND sim_id = ? AND sync_dirty <> ? ", new String[] {
            obj, "2", String.valueOf(j), String.valueOf(1)
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_481;
        if(!context.moveToNext())
            break MISSING_BLOCK_LABEL_475;
        j = context.getInt(context.getColumnIndex("state"));
        if(j == 0 || j == i)
        {
            context.close();
            return true;
        }
        context.close();
_L5:
        return false;
        s;
        Log.e("ExtraTelephony", "Cursor exception when complete check in whiteList: ", s);
        context.close();
        if(true) goto _L5; else goto _L4
_L4:
        s;
        context.close();
        throw s;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static boolean isPrefixInBlack(Context context, String s, int i, int j)
    {
        String s1;
        int k;
        if(TextUtils.isEmpty(s))
            return false;
        s1 = "";
        k = 0;
_L2:
        Cursor cursor;
        if(k >= s.length())
            break; /* Loop/switch isn't completed */
        s1 = (new StringBuilder()).append(s1).append(s.charAt(k)).toString();
        cursor = context.getContentResolver().query(Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ? ", new String[] {
            (new StringBuilder()).append(s1).append("*").toString(), "1", String.valueOf(j), String.valueOf(1)
        }, null);
        if(cursor == null)
            break MISSING_BLOCK_LABEL_177;
        int l;
        if(!cursor.moveToNext())
            break MISSING_BLOCK_LABEL_170;
        l = cursor.getInt(cursor.getColumnIndex("state"));
        if(l == 0 || l == i)
        {
            cursor.close();
            return true;
        }
        cursor.close();
_L3:
        k++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        Log.e("ExtraTelephony", "Cursor exception in isPrefixInBlack(): ", exception);
        cursor.close();
          goto _L3
        context;
        cursor.close();
        throw context;
_L1:
        return false;
    }

    private static boolean isRelatedNumber(Context context, String s)
    {
        Context context1;
        Context context2;
        context1 = null;
        context2 = null;
        context = context.getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, new String[] {
            "type"
        }, "number = ? OR normalized_number = ? ", new String[] {
            s, s
        }, "date DESC");
        if(context == null) goto _L2; else goto _L1
_L1:
        context2 = context;
        context1 = context;
        if(!context.moveToNext())
            break; /* Loop/switch isn't completed */
        context2 = context;
        context1 = context;
        int i = context.getInt(0);
        if(i == 2)
        {
            IOUtils.closeQuietly(context);
            return true;
        }
        if(true) goto _L1; else goto _L2
_L2:
        IOUtils.closeQuietly(context);
_L4:
        return false;
        context;
        context1 = context2;
        Log.e("ExtraTelephony", "Cursor exception in isRelatedNumber(): ", context);
        IOUtils.closeQuietly(context2);
        if(true) goto _L4; else goto _L3
_L3:
        context;
        IOUtils.closeQuietly(context1);
        throw context;
    }

    public static boolean isServiceNumber(String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        miui.telephony.PhoneNumberUtils.PhoneNumber phonenumber = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(s);
        if(phonenumber.isServiceNumber())
            return true;
        return phonenumber.isChineseNumber() && s.startsWith("106");
    }

    public static boolean isTargetServiceNum(Context context, String s)
    {
        boolean flag = true;
        s = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(s).getNormalizedNumber(false, true);
        int i;
        try
        {
            ContentResolver contentresolver = context.getContentResolver();
            context = Judge.SERVICE_NUM_CONTENT_URI;
            ContentValues contentvalues = JVM INSTR new #533 <Class ContentValues>;
            contentvalues.ContentValues();
            i = contentresolver.update(context, contentvalues, null, new String[] {
                s
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("ExtraTelephony", "Exception when isTargetServiceNum()", context);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public static boolean isURLFlagRisky(int i)
    {
        boolean flag;
        if((i & 0x80) == 128)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static String normalizeNumber(String s)
    {
        if(TextUtils.isEmpty(s))
            return "";
        StringBuilder stringbuilder = new StringBuilder();
        int i = s.length();
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            appendNonSeparator(stringbuilder, c, j);
            int k = Character.digit(c, 10);
            if(k != -1)
                stringbuilder.append(k);
            else
            if(j == 0 && c == '+')
                stringbuilder.append(c);
            else
                while(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') 
                    return normalizeNumber(PhoneNumberUtils.convertKeypadLettersToDigits(s));
            j++;
        }
        return stringbuilder.toString();
    }

    public static void registerQuietModeEnableListener(Context context, QuietModeEnableListener quietmodeenablelistener)
    {
        mQuietListeners.add(quietmodeenablelistener);
        if(mSilentModeObserver == null)
            mSilentModeObserver = new SilentModeObserver(context, new Handler());
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
        {
            context.getContentResolver().registerContentObserver(android.provider.Settings.Global.getUriFor("zen_mode"), false, mSilentModeObserver, -1);
            context.getContentResolver().registerContentObserver(android.provider.Settings.System.getUriFor("vibrate_in_silent"), false, mSilentModeObserver, -1);
            context.getContentResolver().registerContentObserver(android.provider.Settings.System.getUriFor("show_notification"), false, mSilentModeObserver, -1);
        } else
        {
            context.getContentResolver().registerContentObserver(android.provider.Settings.Secure.getUriFor("quiet_mode_enable"), false, mSilentModeObserver, -1);
        }
    }

    public static void sendCallInterceptNotification(Context context, String s, int i, int j)
    {
        Intent intent;
        intent = new Intent("miui.intent.action.FIREWALL_UPDATED");
        intent.putExtra("key_sim_id", j);
        intent.putExtra("notification_intercept_number", s);
        intent.putExtra("key_block_log_type", 1);
        intent.putExtra("notification_block_type", i);
        break MISSING_BLOCK_LABEL_52;
        if(i == 3 || i == 6 || i == 13)
            intent.putExtra("notification_show_type", 0);
        else
            intent.putExtra("notification_show_type", 1);
        context.sendBroadcastAsUser(intent, UserHandle.CURRENT_OR_SELF);
        return;
    }

    public static void sendMsgInterceptNotification(Context context, int i, int j)
    {
        Intent intent;
        intent = new Intent("miui.intent.action.FIREWALL_UPDATED");
        intent.putExtra("key_sim_id", j);
        intent.putExtra("key_block_log_type", 2);
        break MISSING_BLOCK_LABEL_29;
        if(i == 3 || i == 13 || i == 6 || i == 12)
            intent.putExtra("notification_show_type", 0);
        else
            intent.putExtra("notification_show_type", 1);
        context.sendBroadcast(intent);
        return;
    }

    public static void unRegisterQuietModeEnableListener(Context context, QuietModeEnableListener quietmodeenablelistener)
    {
        mQuietListeners.remove(quietmodeenablelistener);
        if(mQuietListeners.size() <= 0 && mSilentModeObserver != null)
        {
            context.getContentResolver().unregisterContentObserver(mSilentModeObserver);
            mSilentModeObserver = null;
        }
    }

    public static final String BANK_CATEGORY_NUMBER_PREFIX_106 = "106";
    public static final Pattern BANK_CATEGORY_PATTERN = Pattern.compile("\u94F6\u884C|\u4FE1\u7528\u5361|Bank|BANK|\u652F\u4ED8\u5B9D|\u4E2D\u56FD\u94F6\u8054");
    public static final Pattern BANK_CATEGORY_SNIPPET_PATTERN = Pattern.compile("((\\[[\\s\\S]*(\u94F6\u884C|\u4FE1\u7528\u5361|Bank|BANK|\u652F\u4ED8\u5B9D|\u4E2D\u56FD\u94F6\u8054)\\])|(\\\u3010[\\s\\S]*(\u94F6\u884C|\u4FE1\u7528\u5361|Bank|BANK|\u652F\u4ED8\u5B9D|\u4E2D\u56FD\u94F6\u8054)\\\u3011))$");
    public static final String BLOCKED_CONV_ADDR = "blocked_conv_addr";
    public static final String BLOCKED_FLAG = "blocked_flag";
    public static final String BLOCKED_FLAG_ALL_MSG = "2";
    public static final String BLOCKED_FLAG_BLOCKED_MSG = "1";
    public static final String BLOCKED_FLAG_NO_BLOCKED_MSG = "0";
    public static final String CALLER_IS_SYNCADAPTER = "caller_is_syncadapter";
    public static final String CHECK_DUPLICATION = "check_duplication";
    public static final int DEFAULT_THREADS_LIST_TYPE_SP = 1;
    public static final String DIRTY_QUERY_LIMIT = "dirty_query_limit";
    public static final String FORCE_DELETE = "force_delete";
    public static final int INTERCEPT_STATE_ALL = 0;
    public static final int INTERCEPT_STATE_CALL = 2;
    public static final int INTERCEPT_STATE_SMS = 1;
    public static final String LOCAL_PRIVATE_ADDRESS_SYNC = "local.priaddr.sync";
    public static final String LOCAL_SMS_SYNC = "local.sms.sync";
    public static final String LOCAL_STICKY_THREAD_SYNC = "local.stkthrd.sync";
    public static final String LOCAL_SYNC_NAME = "localName";
    public static final String NEED_FULL_INSERT_URI = "need_full_insert_uri";
    public static final String NO_NOTIFY_FLAG = "no_notify";
    public static final String PRIVACY_FLAG = "privacy_flag";
    public static final String PRIVACY_FLAG_ALL_MSG = "2";
    public static final String PRIVACY_FLAG_NO_PRIVATE_MSG = "0";
    public static final String PRIVACY_FLAG_PRIVATE_MSG = "1";
    public static final String PROVIDER_NAME = "antispam";
    public static final String PrefixCode = "***";
    public static final int SOURCE_ANYONE = 0;
    public static final int SOURCE_CONTACT = 1;
    public static final int SOURCE_STAR = 2;
    public static final int SOURCE_VIP = 3;
    public static final String SUPPRESS_MAKING_MMS_PREVIEW = "supress_making_mms_preview";
    private static final String TAG = "ExtraTelephony";
    public static final String THREADS_LIST_TYPE = "threads_list_type";
    public static final int THREADS_LIST_TYPE_COMPOSITE = 0;
    public static final int TYPE_INTERCEPT_ADDRESS = 2;
    public static final int TYPE_INTERCEPT_NUMBER = 1;
    public static final int TYPE_INTERCEPT_NUMBER_FRAGMENT = 3;
    public static final String ZEN_MODE = "zen_mode";
    public static final int ZEN_MODE_ALARMS = 3;
    public static final int ZEN_MODE_IMPORTANT_INTERRUPTIONS = 1;
    public static final int ZEN_MODE_MIUI_SILENT = 4;
    public static final int ZEN_MODE_NO_INTERRUPTIONS = 2;
    public static final int ZEN_MODE_OFF = 0;
    private static Set mQuietListeners = new HashSet();
    private static SilentModeObserver mSilentModeObserver;

}
