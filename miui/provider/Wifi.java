// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Wifi
{
    public static interface SyncState
        extends BaseColumns
    {

        public static final String ACCOUNT_NAME = "account_name";
        public static final Uri CONTENT_URI = Uri.parse("content://wifi/wifi_sync");
        public static final String MARKER = "marker";
        public static final String SYNC_EXTRA_INFO = "sync_extra_info";
        public static final String WIFI_SYNC_TABLE = "wifi_sync";

    }

    public static interface WifiShare
        extends BaseColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://wifi/wifi_share");
        public static final String MARKER = "marker";
        public static final int SHARE_CONNECTING = 3;
        public static final int SHARE_CONNECT_FAIL = 2;
        public static final int SHARE_CONNECT_NONE = 0;
        public static final String SHARE_CONNECT_STATE = "share_connect_state";
        public static final int SHARE_CONNECT_SUCCESS = 1;
        public static final String SHARE_COUNT = "share_count";
        public static final String SHARE_FEEDBACK = "share_feedback";
        public static final int SHARE_FEEDBACK_ERROR = 2;
        public static final int SHARE_FEEDBACK_FINISH = 1;
        public static final int SHARE_NEED_FEEDBACK = 0;
        public static final String SHARE_UPDATE_TIME = "share_upate_time";
        public static final String WIFI_SHARE_TABLE = "wifi_share";

    }


    public Wifi()
    {
    }

    public static final String ACCOUNT = "account";
    public static final String ADHOC = "adhoc";
    public static final String ANONYMOUSIDENTITY = "anonymousIdentity";
    public static final String AUTHALG = "authAlg";
    public static final String AUTHORITY = "wifi";
    public static final String BSSID = "bssid";
    public static final String CACERT = "caCert";
    public static final String CACERT_FILE = "caCertFile";
    public static final String CALLER_IS_SYNCADAPTER = "caller_is_syncadapter";
    public static final String CLIENT_CERT = "clientCert";
    public static final String CLIENT_CERT_FILE = "clientCertFile";
    public static final Uri CONTENT_URI;
    public static final String DELETED = "deleted";
    public static final String EAP = "eap";
    public static final String GROUP = "groupCipher";
    public static final String ID = "_id";
    public static final String IDENTITY = "identity";
    public static final String KEYMGMT = "keyMgmt";
    public static final String LATITUDE = "latitude";
    public static final String LIMIT = "limit";
    public static final String LONGITUDE = "longitude";
    public static final String MARKER = "marker";
    public static final String PAIRWISE = "pairwise";
    public static final String PASSWORD = "password";
    public static final String PHASE2 = "phase2";
    public static final String PRIORITY = "priority";
    public static final String PRIVATE_KEY = "privateKey";
    public static final String PRIVATE_KEY_FILE = "privateKeyFile";
    public static final String PROTO = "proto";
    public static final String PSK = "psk";
    public static final String SCAN_SSID = "scan_ssid";
    public static final String SHARE_STATE = "share_state";
    public static final int SHARE_STATE_DOWNLOAD = 5;
    public static final int SHARE_STATE_ERROR = 6;
    public static final int SHARE_STATE_FAIL = 3;
    public static final int SHARE_STATE_NONE = 0;
    public static final int SHARE_STATE_PENDING = 1;
    public static final int SHARE_STATE_PENDING_DOWNLOAD = 4;
    public static final int SHARE_STATE_SUCCESS = 2;
    public static final String SSID = "ssid";
    public static final String SYNC_STATE = "sync_state";
    public static final int SYNC_STATE_BACKUP = 3;
    public static final int SYNC_STATE_DIRTY = 0;
    public static final int SYNC_STATE_ERROR = 5;
    public static final int SYNC_STATE_RESTORE = 4;
    public static final int SYNC_STATE_SYNCED = 1;
    public static final int SYNC_STATE_SYNCING = 2;
    public static final String UUID = "uuid";
    public static final String WEPKEY0 = "wepkey0";
    public static final String WEPKEY1 = "wepkey1";
    public static final String WEPKEY2 = "wepkey2";
    public static final String WEPKEY3 = "wepkey3";
    public static final String WEP_TX_KEYIDX = "wep_tx_keyidx";
    public static final String WIFI_TABLE = "wifi";
    public static final Uri WIFI_URI;

    static 
    {
        CONTENT_URI = Uri.parse("content://wifi/wifi");
        WIFI_URI = CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", String.valueOf(true)).build();
    }
}
