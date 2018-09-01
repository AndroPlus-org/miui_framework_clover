// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.util.ArrayUtils;
import com.android.internal.widget.ILockSettings;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package android.provider:
//            BaseColumns, MiuiSettings

public final class Settings
{
    public static final class Bookmarks
        implements BaseColumns
    {

        public static Uri add(ContentResolver contentresolver, Intent intent, String s, String s1, char c, int i)
        {
            if(c != 0)
                contentresolver.delete(CONTENT_URI, "shortcut=?", new String[] {
                    String.valueOf(c)
                });
            ContentValues contentvalues = new ContentValues();
            if(s != null)
                contentvalues.put("title", s);
            if(s1 != null)
                contentvalues.put("folder", s1);
            contentvalues.put("intent", intent.toUri(0));
            if(c != 0)
                contentvalues.put("shortcut", Integer.valueOf(c));
            contentvalues.put("ordering", Integer.valueOf(i));
            return contentresolver.insert(CONTENT_URI, contentvalues);
        }

        public static Intent getIntentForShortcut(ContentResolver contentresolver, char c)
        {
            Cursor cursor;
            Object obj = null;
            cursor = contentresolver.query(CONTENT_URI, sIntentProjection, "shortcut=?", new String[] {
                String.valueOf(c)
            }, "ordering");
            contentresolver = obj;
_L2:
            if(contentresolver != null)
                break MISSING_BLOCK_LABEL_97;
            boolean flag = cursor.moveToNext();
            if(!flag)
                break MISSING_BLOCK_LABEL_97;
            Intent intent = Intent.parseUri(cursor.getString(cursor.getColumnIndexOrThrow("intent")), 0);
            contentresolver = intent;
            continue; /* Loop/switch isn't completed */
            Object obj1;
            obj1;
            Log.w("Bookmarks", "Intent column not found", ((Throwable) (obj1)));
            continue; /* Loop/switch isn't completed */
            contentresolver;
            if(cursor != null)
                cursor.close();
            throw contentresolver;
            if(cursor != null)
                cursor.close();
            return contentresolver;
            obj1;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public static CharSequence getLabelForFolder(Resources resources, String s)
        {
            return s;
        }

        public static CharSequence getTitle(Context context, Cursor cursor)
        {
            int i = cursor.getColumnIndex("title");
            int j = cursor.getColumnIndex("intent");
            if(i == -1 || j == -1)
                throw new IllegalArgumentException("The cursor must contain the TITLE and INTENT columns.");
            String s = cursor.getString(i);
            if(!TextUtils.isEmpty(s))
                return s;
            cursor = cursor.getString(j);
            if(TextUtils.isEmpty(cursor))
                return "";
            try
            {
                cursor = Intent.parseUri(cursor, 0);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                return "";
            }
            context = context.getPackageManager();
            cursor = context.resolveActivity(cursor, 0);
            if(cursor != null)
                context = cursor.loadLabel(context);
            else
                context = "";
            return context;
        }

        public static final Uri CONTENT_URI = Uri.parse("content://settings/bookmarks");
        public static final String FOLDER = "folder";
        public static final String ID = "_id";
        public static final String INTENT = "intent";
        public static final String ORDERING = "ordering";
        public static final String SHORTCUT = "shortcut";
        private static final String TAG = "Bookmarks";
        public static final String TITLE = "title";
        private static final String sIntentProjection[] = {
            "intent"
        };
        private static final String sShortcutProjection[] = {
            "_id", "shortcut"
        };
        private static final String sShortcutSelection = "shortcut=?";


        public Bookmarks()
        {
        }
    }

    private static final class ContentProviderHolder
    {

        public void clearProviderForTest()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mContentProvider = null;
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public IContentProvider getProvider(ContentResolver contentresolver)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(mContentProvider == null)
                mContentProvider = contentresolver.acquireProvider(mUri.getAuthority());
            contentresolver = mContentProvider;
            obj;
            JVM INSTR monitorexit ;
            return contentresolver;
            contentresolver;
            throw contentresolver;
        }

        private IContentProvider mContentProvider;
        private final Object mLock = new Object();
        private final Uri mUri;

        public ContentProviderHolder(Uri uri)
        {
            mUri = uri;
        }
    }

    private static final class GenerationTracker
    {

        private int readCurrentGeneration()
        {
            int i;
            try
            {
                i = mArray.get(mIndex);
            }
            catch(IOException ioexception)
            {
                Log.e("Settings", "Error getting current generation", ioexception);
                if(mErrorHandler != null)
                    mErrorHandler.run();
                return -1;
            }
            return i;
        }

        public void destroy()
        {
            mArray.close();
_L1:
            return;
            IOException ioexception;
            ioexception;
            Log.e("Settings", "Error closing backing array", ioexception);
            if(mErrorHandler != null)
                mErrorHandler.run();
              goto _L1
        }

        public int getCurrentGeneration()
        {
            return mCurrentGeneration;
        }

        public boolean isGenerationChanged()
        {
            int i = readCurrentGeneration();
            if(i >= 0)
            {
                if(i == mCurrentGeneration)
                    return false;
                mCurrentGeneration = i;
            }
            return true;
        }

        private final MemoryIntArray mArray;
        private int mCurrentGeneration;
        private final Runnable mErrorHandler;
        private final int mIndex;

        public GenerationTracker(MemoryIntArray memoryintarray, int i, int j, Runnable runnable)
        {
            mArray = memoryintarray;
            mIndex = i;
            mErrorHandler = runnable;
            mCurrentGeneration = j;
        }
    }

    public static final class Global extends NameValueTable
    {

        public static void clearProviderForTest()
        {
            sProviderHolder.clearProviderForTest();
            sNameValueCache.clearGenerationTrackerForTest();
        }

        public static final String getBluetoothA2dpOptionalCodecsEnabledKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_a2dp_optional_codecs_enabled_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothA2dpSinkPriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_a2dp_sink_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothA2dpSrcPriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_a2dp_src_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothA2dpSupportsOptionalCodecsKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_a2dp_supports_optional_codecs_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothHeadsetPriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_headset_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothInputDevicePriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_input_device_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothMapClientPriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_map_client_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothMapPriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_map_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothPanPriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_pan_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothPbapClientPriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_pbap_client_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static final String getBluetoothSapPriorityKey(String s)
        {
            return (new StringBuilder()).append("bluetooth_sap_priority_").append(s.toUpperCase(Locale.ROOT)).toString();
        }

        public static float getFloat(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            contentresolver = getString(contentresolver, s);
            if(contentresolver == null)
                throw new SettingNotFoundException(s);
            float f;
            try
            {
                f = Float.parseFloat(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return f;
        }

        public static float getFloat(ContentResolver contentresolver, String s, float f)
        {
            contentresolver = getString(contentresolver, s);
            float f1 = f;
            if(contentresolver != null)
                try
                {
                    f1 = Float.parseFloat(contentresolver);
                }
                // Misplaced declaration of an exception variable
                catch(ContentResolver contentresolver)
                {
                    return f;
                }
            return f1;
        }

        public static int getInt(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            contentresolver = getString(contentresolver, s);
            int i;
            try
            {
                i = Integer.parseInt(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return i;
        }

        public static int getInt(ContentResolver contentresolver, String s, int i)
        {
            contentresolver = getString(contentresolver, s);
            int j = i;
            if(contentresolver != null)
                try
                {
                    j = Integer.parseInt(contentresolver);
                }
                // Misplaced declaration of an exception variable
                catch(ContentResolver contentresolver)
                {
                    return i;
                }
            return j;
        }

        public static long getLong(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            contentresolver = getString(contentresolver, s);
            long l;
            try
            {
                l = Long.parseLong(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return l;
        }

        public static long getLong(ContentResolver contentresolver, String s, long l)
        {
            contentresolver = getString(contentresolver, s);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_19;
            long l1 = Long.parseLong(contentresolver);
            l = l1;
_L2:
            return l;
            contentresolver;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public static void getMovedToSecureSettings(Set set)
        {
            set.addAll(MOVED_TO_SECURE);
        }

        public static String getString(ContentResolver contentresolver, String s)
        {
            return getStringForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static String getStringForUser(ContentResolver contentresolver, String s, int i)
        {
            if(MOVED_TO_SECURE.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.Global").append(" to android.provider.Settings.Secure, returning read-only value.").toString());
                return Secure.getStringForUser(contentresolver, s, i);
            } else
            {
                return sNameValueCache.getStringForUser(contentresolver, s, i);
            }
        }

        public static Uri getUriFor(String s)
        {
            return getUriFor(CONTENT_URI, s);
        }

        public static boolean isValidZenMode(int i)
        {
            switch(i)
            {
            default:
                return false;

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
                return true;
            }
        }

        public static boolean putFloat(ContentResolver contentresolver, String s, float f)
        {
            return putString(contentresolver, s, Float.toString(f));
        }

        public static boolean putInt(ContentResolver contentresolver, String s, int i)
        {
            return putString(contentresolver, s, Integer.toString(i));
        }

        public static boolean putLong(ContentResolver contentresolver, String s, long l)
        {
            return putString(contentresolver, s, Long.toString(l));
        }

        public static boolean putString(ContentResolver contentresolver, String s, String s1)
        {
            return putStringForUser(contentresolver, s, s1, null, false, UserHandle.myUserId());
        }

        public static boolean putString(ContentResolver contentresolver, String s, String s1, String s2, boolean flag)
        {
            return putStringForUser(contentresolver, s, s1, s2, flag, UserHandle.myUserId());
        }

        public static boolean putStringForUser(ContentResolver contentresolver, String s, String s1, int i)
        {
            return putStringForUser(contentresolver, s, s1, null, false, i);
        }

        public static boolean putStringForUser(ContentResolver contentresolver, String s, String s1, String s2, boolean flag, int i)
        {
            if(MOVED_TO_SECURE.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.Global").append(" to android.provider.Settings.Secure, value is unchanged.").toString());
                return Secure.putStringForUser(contentresolver, s, s1, s2, flag, i);
            } else
            {
                return sNameValueCache.putStringForUser(contentresolver, s, s1, s2, flag, i);
            }
        }

        public static void resetToDefaults(ContentResolver contentresolver, String s)
        {
            resetToDefaultsAsUser(contentresolver, s, 1, UserHandle.myUserId());
        }

        public static void resetToDefaultsAsUser(ContentResolver contentresolver, String s, int i, int j)
        {
            Bundle bundle;
            bundle = JVM INSTR new #1302 <Class Bundle>;
            bundle.Bundle();
            bundle.putInt("_user", j);
            if(s == null)
                break MISSING_BLOCK_LABEL_32;
            bundle.putString("_tag", s);
            bundle.putInt("_reset_mode", i);
            sProviderHolder.getProvider(contentresolver).call(contentresolver.getPackageName(), "RESET_global", null, bundle);
_L1:
            return;
            contentresolver;
            Log.w("Settings", (new StringBuilder()).append("Can't reset do defaults for ").append(CONTENT_URI).toString(), contentresolver);
              goto _L1
        }

        public static String zenModeToString(int i)
        {
            if(i == 1)
                return "ZEN_MODE_IMPORTANT_INTERRUPTIONS";
            if(i == 3)
                return "ZEN_MODE_ALARMS";
            if(i == 2)
                return "ZEN_MODE_NO_INTERRUPTIONS";
            if(i == 4)
                return "ZEN_MODE_MIUI_SILENT";
            else
                return "ZEN_MODE_OFF";
        }

        public static final String ACTIVITY_MANAGER_CONSTANTS = "activity_manager_constants";
        public static final String ADB_ENABLED = "adb_enabled";
        public static final String ADD_USERS_WHEN_LOCKED = "add_users_when_locked";
        public static final String AIRPLANE_MODE_ON = "airplane_mode_on";
        public static final String AIRPLANE_MODE_RADIOS = "airplane_mode_radios";
        public static final String AIRPLANE_MODE_TOGGLEABLE_RADIOS = "airplane_mode_toggleable_radios";
        public static final String ALARM_MANAGER_CONSTANTS = "alarm_manager_constants";
        public static final String ALLOW_USER_SWITCHING_WHEN_SYSTEM_USER_LOCKED = "allow_user_switching_when_system_user_locked";
        public static final String ALWAYS_FINISH_ACTIVITIES = "always_finish_activities";
        public static final String ALWAYS_ON_DISPLAY_CONSTANTS = "always_on_display_constants";
        public static final String ANIMATOR_DURATION_SCALE = "animator_duration_scale";
        public static final String ANOMALY_DETECTION_CONSTANTS = "anomaly_detection_constants";
        public static final String APN_DB_UPDATE_CONTENT_URL = "apn_db_content_url";
        public static final String APN_DB_UPDATE_METADATA_URL = "apn_db_metadata_url";
        public static final String APP_IDLE_CONSTANTS = "app_idle_constants";
        public static final String ASSISTED_GPS_ENABLED = "assisted_gps_enabled";
        public static final String AUDIO_SAFE_VOLUME_STATE = "audio_safe_volume_state";
        public static final String AUTO_TIME = "auto_time";
        public static final String AUTO_TIME_ZONE = "auto_time_zone";
        public static final String BACKUP_REFACTORED_SERVICE_DISABLED = "backup_refactored_service_disabled";
        public static final String BATTERY_DISCHARGE_DURATION_THRESHOLD = "battery_discharge_duration_threshold";
        public static final String BATTERY_DISCHARGE_THRESHOLD = "battery_discharge_threshold";
        public static final String BATTERY_SAVER_CONSTANTS = "battery_saver_constants";
        public static final String BLE_SCAN_ALWAYS_AVAILABLE = "ble_scan_always_enabled";
        public static final String BLUETOOTH_A2DP_OPTIONAL_CODECS_ENABLED_PREFIX = "bluetooth_a2dp_optional_codecs_enabled_";
        public static final String BLUETOOTH_A2DP_SINK_PRIORITY_PREFIX = "bluetooth_a2dp_sink_priority_";
        public static final String BLUETOOTH_A2DP_SRC_PRIORITY_PREFIX = "bluetooth_a2dp_src_priority_";
        public static final String BLUETOOTH_A2DP_SUPPORTS_OPTIONAL_CODECS_PREFIX = "bluetooth_a2dp_supports_optional_codecs_";
        public static final String BLUETOOTH_DISABLED_PROFILES = "bluetooth_disabled_profiles";
        public static final String BLUETOOTH_HEADSET_PRIORITY_PREFIX = "bluetooth_headset_priority_";
        public static final String BLUETOOTH_INPUT_DEVICE_PRIORITY_PREFIX = "bluetooth_input_device_priority_";
        public static final String BLUETOOTH_INTEROPERABILITY_LIST = "bluetooth_interoperability_list";
        public static final String BLUETOOTH_MAP_CLIENT_PRIORITY_PREFIX = "bluetooth_map_client_priority_";
        public static final String BLUETOOTH_MAP_PRIORITY_PREFIX = "bluetooth_map_priority_";
        public static final String BLUETOOTH_ON = "bluetooth_on";
        public static final String BLUETOOTH_PAN_PRIORITY_PREFIX = "bluetooth_pan_priority_";
        public static final String BLUETOOTH_PBAP_CLIENT_PRIORITY_PREFIX = "bluetooth_pbap_client_priority_";
        public static final String BLUETOOTH_SAP_PRIORITY_PREFIX = "bluetooth_sap_priority_";
        public static final String BOOT_COUNT = "boot_count";
        public static final String BUGREPORT_IN_POWER_MENU = "bugreport_in_power_menu";
        public static final String CALL_AUTO_RETRY = "call_auto_retry";
        public static final String CAPTIVE_PORTAL_DETECTION_ENABLED = "captive_portal_detection_enabled";
        public static final String CAPTIVE_PORTAL_FALLBACK_URL = "captive_portal_fallback_url";
        public static final String CAPTIVE_PORTAL_HTTPS_URL = "captive_portal_https_url";
        public static final String CAPTIVE_PORTAL_HTTP_URL = "captive_portal_http_url";
        public static final String CAPTIVE_PORTAL_MODE = "captive_portal_mode";
        public static final int CAPTIVE_PORTAL_MODE_AVOID = 2;
        public static final int CAPTIVE_PORTAL_MODE_IGNORE = 0;
        public static final int CAPTIVE_PORTAL_MODE_PROMPT = 1;
        public static final String CAPTIVE_PORTAL_OTHER_FALLBACK_URLS = "captive_portal_other_fallback_urls";
        public static final String CAPTIVE_PORTAL_SERVER = "captive_portal_server";
        public static final String CAPTIVE_PORTAL_USER_AGENT = "captive_portal_user_agent";
        public static final String CAPTIVE_PORTAL_USE_HTTPS = "captive_portal_use_https";
        public static final String CARRIER_APP_WHITELIST = "carrier_app_whitelist";
        public static final String CAR_DOCK_SOUND = "car_dock_sound";
        public static final String CAR_UNDOCK_SOUND = "car_undock_sound";
        public static final String CDMA_CELL_BROADCAST_SMS = "cdma_cell_broadcast_sms";
        public static final String CDMA_ROAMING_MODE = "roaming_settings";
        public static final String CDMA_SUBSCRIPTION_MODE = "subscription_mode";
        public static final String CELL_ON = "cell_on";
        public static final String CERT_PIN_UPDATE_CONTENT_URL = "cert_pin_content_url";
        public static final String CERT_PIN_UPDATE_METADATA_URL = "cert_pin_metadata_url";
        public static final String CHARGING_SOUNDS_ENABLED = "charging_sounds_enabled";
        public static final String COMPATIBILITY_MODE = "compatibility_mode";
        public static final String CONNECTIVITY_CHANGE_DELAY = "connectivity_change_delay";
        public static final String CONNECTIVITY_METRICS_BUFFER_SIZE = "connectivity_metrics_buffer_size";
        public static final String CONNECTIVITY_SAMPLING_INTERVAL_IN_SECONDS = "connectivity_sampling_interval_in_seconds";
        public static final String CONTACTS_DATABASE_WAL_ENABLED = "contacts_database_wal_enabled";
        public static final String CONTACT_METADATA_SYNC = "contact_metadata_sync";
        public static final String CONTACT_METADATA_SYNC_ENABLED = "contact_metadata_sync_enabled";
        public static final Uri CONTENT_URI;
        public static final String DATABASE_CREATION_BUILDID = "database_creation_buildid";
        public static final String DATABASE_DOWNGRADE_REASON = "database_downgrade_reason";
        public static final String DATA_ACTIVITY_TIMEOUT_MOBILE = "data_activity_timeout_mobile";
        public static final String DATA_ACTIVITY_TIMEOUT_WIFI = "data_activity_timeout_wifi";
        public static final String DATA_ROAMING = "data_roaming";
        public static final String DATA_STALL_ALARM_AGGRESSIVE_DELAY_IN_MS = "data_stall_alarm_aggressive_delay_in_ms";
        public static final String DATA_STALL_ALARM_NON_AGGRESSIVE_DELAY_IN_MS = "data_stall_alarm_non_aggressive_delay_in_ms";
        public static final String DEBUG_APP = "debug_app";
        public static final String DEBUG_VIEW_ATTRIBUTES = "debug_view_attributes";
        public static final String DEFAULT_DNS_SERVER = "default_dns_server";
        public static final String DEFAULT_INSTALL_LOCATION = "default_install_location";
        public static final String DEFAULT_RESTRICT_BACKGROUND_DATA = "default_restrict_background_data";
        public static final String DEFAULT_SM_DP_PLUS = "default_sm_dp_plus";
        public static final String DESK_DOCK_SOUND = "desk_dock_sound";
        public static final String DESK_UNDOCK_SOUND = "desk_undock_sound";
        public static final String DEVELOPMENT_ENABLE_FREEFORM_WINDOWS_SUPPORT = "enable_freeform_support";
        public static final String DEVELOPMENT_FORCE_RESIZABLE_ACTIVITIES = "force_resizable_activities";
        public static final String DEVELOPMENT_FORCE_RTL = "debug.force_rtl";
        public static final String DEVELOPMENT_SETTINGS_ENABLED = "development_settings_enabled";
        public static final String DEVICE_DEMO_MODE = "device_demo_mode";
        public static final String DEVICE_IDLE_CONSTANTS = "device_idle_constants";
        public static final String DEVICE_IDLE_CONSTANTS_WATCH = "device_idle_constants_watch";
        public static final String DEVICE_NAME = "device_name";
        public static final String DEVICE_POLICY_CONSTANTS = "device_policy_constants";
        public static final String DEVICE_PROVISIONED = "device_provisioned";
        public static final String DEVICE_PROVISIONING_MOBILE_DATA_ENABLED = "device_provisioning_mobile_data";
        public static final String DISK_FREE_CHANGE_REPORTING_THRESHOLD = "disk_free_change_reporting_threshold";
        public static final String DISPLAY_SCALING_FORCE = "display_scaling_force";
        public static final String DISPLAY_SIZE_FORCED = "display_size_forced";
        public static final String DNS_RESOLVER_MAX_SAMPLES = "dns_resolver_max_samples";
        public static final String DNS_RESOLVER_MIN_SAMPLES = "dns_resolver_min_samples";
        public static final String DNS_RESOLVER_SAMPLE_VALIDITY_SECONDS = "dns_resolver_sample_validity_seconds";
        public static final String DNS_RESOLVER_SUCCESS_THRESHOLD_PERCENT = "dns_resolver_success_threshold_percent";
        public static final String DOCK_AUDIO_MEDIA_ENABLED = "dock_audio_media_enabled";
        public static final String DOCK_SOUNDS_ENABLED = "dock_sounds_enabled";
        public static final String DOCK_SOUNDS_ENABLED_WHEN_ACCESSIBILITY = "dock_sounds_enabled_when_accessbility";
        public static final String DOWNLOAD_MAX_BYTES_OVER_MOBILE = "download_manager_max_bytes_over_mobile";
        public static final String DOWNLOAD_RECOMMENDED_MAX_BYTES_OVER_MOBILE = "download_manager_recommended_max_bytes_over_mobile";
        public static final String DROPBOX_AGE_SECONDS = "dropbox_age_seconds";
        public static final String DROPBOX_MAX_FILES = "dropbox_max_files";
        public static final String DROPBOX_QUOTA_KB = "dropbox_quota_kb";
        public static final String DROPBOX_QUOTA_PERCENT = "dropbox_quota_percent";
        public static final String DROPBOX_RESERVE_PERCENT = "dropbox_reserve_percent";
        public static final String DROPBOX_TAG_PREFIX = "dropbox:";
        public static final String EMERGENCY_AFFORDANCE_NEEDED = "emergency_affordance_needed";
        public static final String EMERGENCY_TONE = "emergency_tone";
        public static final String ENABLE_ACCESSIBILITY_GLOBAL_GESTURE_ENABLED = "enable_accessibility_global_gesture_enabled";
        public static final String ENABLE_CACHE_QUOTA_CALCULATION = "enable_cache_quota_calculation";
        public static final String ENABLE_CELLULAR_ON_BOOT = "enable_cellular_on_boot";
        public static final String ENABLE_DELETION_HELPER_NO_THRESHOLD_TOGGLE = "enable_deletion_helper_no_threshold_toggle";
        public static final String ENABLE_DISKSTATS_LOGGING = "enable_diskstats_logging";
        public static final String ENABLE_EPHEMERAL_FEATURE = "enable_ephemeral_feature";
        public static final String ENABLE_V6_ONLY_TETHERING = "enable_v6_only_tethering";
        public static final String ENCODED_SURROUND_OUTPUT = "encoded_surround_output";
        public static final int ENCODED_SURROUND_OUTPUT_ALWAYS = 2;
        public static final int ENCODED_SURROUND_OUTPUT_AUTO = 0;
        public static final int ENCODED_SURROUND_OUTPUT_NEVER = 1;
        public static final String ENHANCED_4G_MODE_ENABLED = "volte_vt_enabled";
        public static final String EPHEMERAL_COOKIE_MAX_SIZE_BYTES = "ephemeral_cookie_max_size_bytes";
        public static final String ERROR_LOGCAT_PREFIX = "logcat_for_";
        public static final String EUICC_FACTORY_RESET_TIMEOUT_MILLIS = "euicc_factory_reset_timeout_millis";
        public static final String EUICC_PROVISIONED = "euicc_provisioned";
        public static final String FANCY_IME_ANIMATIONS = "fancy_ime_animations";
        public static final String FORCE_ALLOW_ON_EXTERNAL = "force_allow_on_external";
        public static final String FSTRIM_MANDATORY_INTERVAL = "fstrim_mandatory_interval";
        public static final String GLOBAL_HTTP_PROXY_EXCLUSION_LIST = "global_http_proxy_exclusion_list";
        public static final String GLOBAL_HTTP_PROXY_HOST = "global_http_proxy_host";
        public static final String GLOBAL_HTTP_PROXY_PAC = "global_proxy_pac_url";
        public static final String GLOBAL_HTTP_PROXY_PORT = "global_http_proxy_port";
        public static final String GPRS_REGISTER_CHECK_PERIOD_MS = "gprs_register_check_period_ms";
        public static final String HDMI_CONTROL_AUTO_DEVICE_OFF_ENABLED = "hdmi_control_auto_device_off_enabled";
        public static final String HDMI_CONTROL_AUTO_WAKEUP_ENABLED = "hdmi_control_auto_wakeup_enabled";
        public static final String HDMI_CONTROL_ENABLED = "hdmi_control_enabled";
        public static final String HDMI_SYSTEM_AUDIO_CONTROL_ENABLED = "hdmi_system_audio_control_enabled";
        public static final String HEADS_UP_NOTIFICATIONS_ENABLED = "heads_up_notifications_enabled";
        public static final int HEADS_UP_OFF = 0;
        public static final int HEADS_UP_ON = 1;
        public static final String HTTP_PROXY = "http_proxy";
        public static final String INET_CONDITION_DEBOUNCE_DOWN_DELAY = "inet_condition_debounce_down_delay";
        public static final String INET_CONDITION_DEBOUNCE_UP_DELAY = "inet_condition_debounce_up_delay";
        public static final String INSTALLED_INSTANT_APP_MAX_CACHE_PERIOD = "installed_instant_app_max_cache_period";
        public static final String INSTALLED_INSTANT_APP_MIN_CACHE_PERIOD = "installed_instant_app_min_cache_period";
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";
        public static final String INSTANT_APP_DEXOPT_ENABLED = "instant_app_dexopt_enabled";
        public static final Set INSTANT_APP_SETTINGS;
        public static final String INTENT_FIREWALL_UPDATE_CONTENT_URL = "intent_firewall_content_url";
        public static final String INTENT_FIREWALL_UPDATE_METADATA_URL = "intent_firewall_metadata_url";
        public static final String JOB_SCHEDULER_CONSTANTS = "job_scheduler_constants";
        public static final String LANG_ID_UPDATE_CONTENT_URL = "lang_id_content_url";
        public static final String LANG_ID_UPDATE_METADATA_URL = "lang_id_metadata_url";
        public static final String LEGACY_RESTORE_SETTINGS[] = new String[0];
        public static final String LOCATION_BACKGROUND_THROTTLE_INTERVAL_MS = "location_background_throttle_interval_ms";
        public static final String LOCATION_BACKGROUND_THROTTLE_PACKAGE_WHITELIST = "location_background_throttle_package_whitelist";
        public static final String LOCATION_BACKGROUND_THROTTLE_PROXIMITY_ALERT_INTERVAL_MS = "location_background_throttle_proximity_alert_interval_ms";
        public static final String LOCATION_SETTINGS_LINK_TO_PERMISSIONS_ENABLED = "location_settings_link_to_permissions_enabled";
        public static final String LOCK_SOUND = "lock_sound";
        public static final String LOW_BATTERY_SOUND = "low_battery_sound";
        public static final String LOW_BATTERY_SOUND_TIMEOUT = "low_battery_sound_timeout";
        public static final String LOW_POWER_MODE = "low_power";
        public static final String LOW_POWER_MODE_TRIGGER_LEVEL = "low_power_trigger_level";
        public static final String LTE_SERVICE_FORCED = "lte_service_forced";
        public static final String MAX_NOTIFICATION_ENQUEUE_RATE = "max_notification_enqueue_rate";
        public static final String MDC_INITIAL_MAX_RETRY = "mdc_initial_max_retry";
        public static final String MHL_INPUT_SWITCHING_ENABLED = "mhl_input_switching_enabled";
        public static final String MHL_POWER_CHARGE_ENABLED = "mhl_power_charge_enabled";
        public static final String MOBILE_DATA = "mobile_data";
        public static final String MOBILE_DATA_ALWAYS_ON = "mobile_data_always_on";
        public static final String MODE_RINGER = "mode_ringer";
        private static final HashSet MOVED_TO_SECURE;
        public static final String MULTI_SIM_DATA_CALL_SUBSCRIPTION = "multi_sim_data_call";
        public static final String MULTI_SIM_SMS_PROMPT = "multi_sim_sms_prompt";
        public static final String MULTI_SIM_SMS_SUBSCRIPTION = "multi_sim_sms";
        public static final String MULTI_SIM_USER_PREFERRED_SUBS[] = {
            "user_preferred_sub1", "user_preferred_sub2", "user_preferred_sub3"
        };
        public static final String MULTI_SIM_VOICE_CALL_SUBSCRIPTION = "multi_sim_voice_call";
        public static final String MULTI_SIM_VOICE_PROMPT = "multi_sim_voice_prompt";
        public static final String NETSTATS_AUGMENT_ENABLED = "netstats_augment_enabled";
        public static final String NETSTATS_DEV_BUCKET_DURATION = "netstats_dev_bucket_duration";
        public static final String NETSTATS_DEV_DELETE_AGE = "netstats_dev_delete_age";
        public static final String NETSTATS_DEV_PERSIST_BYTES = "netstats_dev_persist_bytes";
        public static final String NETSTATS_DEV_ROTATE_AGE = "netstats_dev_rotate_age";
        public static final String NETSTATS_ENABLED = "netstats_enabled";
        public static final String NETSTATS_GLOBAL_ALERT_BYTES = "netstats_global_alert_bytes";
        public static final String NETSTATS_POLL_INTERVAL = "netstats_poll_interval";
        public static final String NETSTATS_SAMPLE_ENABLED = "netstats_sample_enabled";
        public static final String NETSTATS_TIME_CACHE_MAX_AGE = "netstats_time_cache_max_age";
        public static final String NETSTATS_UID_BUCKET_DURATION = "netstats_uid_bucket_duration";
        public static final String NETSTATS_UID_DELETE_AGE = "netstats_uid_delete_age";
        public static final String NETSTATS_UID_PERSIST_BYTES = "netstats_uid_persist_bytes";
        public static final String NETSTATS_UID_ROTATE_AGE = "netstats_uid_rotate_age";
        public static final String NETSTATS_UID_TAG_BUCKET_DURATION = "netstats_uid_tag_bucket_duration";
        public static final String NETSTATS_UID_TAG_DELETE_AGE = "netstats_uid_tag_delete_age";
        public static final String NETSTATS_UID_TAG_PERSIST_BYTES = "netstats_uid_tag_persist_bytes";
        public static final String NETSTATS_UID_TAG_ROTATE_AGE = "netstats_uid_tag_rotate_age";
        public static final String NETWORK_ACCESS_TIMEOUT_MS = "network_access_timeout_ms";
        public static final String NETWORK_AVOID_BAD_WIFI = "network_avoid_bad_wifi";
        public static final String NETWORK_METERED_MULTIPATH_PREFERENCE = "network_metered_multipath_preference";
        public static final String NETWORK_PREFERENCE = "network_preference";
        public static final String NETWORK_RECOMMENDATIONS_ENABLED = "network_recommendations_enabled";
        public static final String NETWORK_RECOMMENDATIONS_PACKAGE = "network_recommendations_package";
        public static final String NETWORK_RECOMMENDATION_REQUEST_TIMEOUT_MS = "network_recommendation_request_timeout_ms";
        public static final String NETWORK_SCORER_APP = "network_scorer_app";
        public static final String NETWORK_SCORING_PROVISIONED = "network_scoring_provisioned";
        public static final String NETWORK_SCORING_UI_ENABLED = "network_scoring_ui_enabled";
        public static final String NETWORK_SWITCH_NOTIFICATION_DAILY_LIMIT = "network_switch_notification_daily_limit";
        public static final String NETWORK_SWITCH_NOTIFICATION_RATE_LIMIT_MILLIS = "network_switch_notification_rate_limit_millis";
        public static final String NEW_CONTACT_AGGREGATOR = "new_contact_aggregator";
        public static final String NITZ_UPDATE_DIFF = "nitz_update_diff";
        public static final String NITZ_UPDATE_SPACING = "nitz_update_spacing";
        public static final String NOTIFICATION_SNOOZE_OPTIONS = "notification_snooze_options";
        public static final String NSD_ON = "nsd_on";
        public static final String NTP_SERVER = "ntp_server";
        public static final String NTP_TIMEOUT = "ntp_timeout";
        public static final String OTA_DISABLE_AUTOMATIC_UPDATE = "ota_disable_automatic_update";
        public static final String OVERLAY_DISPLAY_DEVICES = "overlay_display_devices";
        public static final String PACKAGE_VERIFIER_DEFAULT_RESPONSE = "verifier_default_response";
        public static final String PACKAGE_VERIFIER_ENABLE = "package_verifier_enable";
        public static final String PACKAGE_VERIFIER_INCLUDE_ADB = "verifier_verify_adb_installs";
        public static final String PACKAGE_VERIFIER_SETTING_VISIBLE = "verifier_setting_visible";
        public static final String PACKAGE_VERIFIER_TIMEOUT = "verifier_timeout";
        public static final String PAC_CHANGE_DELAY = "pac_change_delay";
        public static final String PDP_WATCHDOG_ERROR_POLL_COUNT = "pdp_watchdog_error_poll_count";
        public static final String PDP_WATCHDOG_ERROR_POLL_INTERVAL_MS = "pdp_watchdog_error_poll_interval_ms";
        public static final String PDP_WATCHDOG_LONG_POLL_INTERVAL_MS = "pdp_watchdog_long_poll_interval_ms";
        public static final String PDP_WATCHDOG_MAX_PDP_RESET_FAIL_COUNT = "pdp_watchdog_max_pdp_reset_fail_count";
        public static final String PDP_WATCHDOG_POLL_INTERVAL_MS = "pdp_watchdog_poll_interval_ms";
        public static final String PDP_WATCHDOG_TRIGGER_PACKET_COUNT = "pdp_watchdog_trigger_packet_count";
        public static final String POLICY_CONTROL = "policy_control";
        public static final String POWER_MANAGER_CONSTANTS = "power_manager_constants";
        public static final String POWER_SOUNDS_ENABLED = "power_sounds_enabled";
        public static final String PREFERRED_NETWORK_MODE = "preferred_network_mode";
        public static final String PROVISIONING_APN_ALARM_DELAY_IN_MS = "provisioning_apn_alarm_delay_in_ms";
        public static final String RADIO_BLUETOOTH = "bluetooth";
        public static final String RADIO_CELL = "cell";
        public static final String RADIO_NFC = "nfc";
        public static final String RADIO_WIFI = "wifi";
        public static final String RADIO_WIMAX = "wimax";
        public static final String READ_EXTERNAL_STORAGE_ENFORCED_DEFAULT = "read_external_storage_enforced_default";
        public static final String RECOMMENDED_NETWORK_EVALUATOR_CACHE_EXPIRY_MS = "recommended_network_evaluator_cache_expiry_ms";
        public static final String REQUIRE_PASSWORD_TO_DECRYPT = "require_password_to_decrypt";
        public static final String SAFE_BOOT_DISALLOWED = "safe_boot_disallowed";
        public static final String SAMPLING_PROFILER_MS = "sampling_profiler_ms";
        public static final String SELINUX_STATUS = "selinux_status";
        public static final String SELINUX_UPDATE_CONTENT_URL = "selinux_content_url";
        public static final String SELINUX_UPDATE_METADATA_URL = "selinux_metadata_url";
        public static final String SEND_ACTION_APP_ERROR = "send_action_app_error";
        public static final String SETTINGS_TO_BACKUP[] = {
            "bugreport_in_power_menu", "stay_on_while_plugged_in", "auto_time", "auto_time_zone", "power_sounds_enabled", "dock_sounds_enabled", "charging_sounds_enabled", "usb_mass_storage_enabled", "network_recommendations_enabled", "wifi_wakeup_enabled", 
            "wifi_networks_available_notification_on", "use_open_wifi_package", "wifi_watchdog_poor_network_test_enabled", "emergency_tone", "call_auto_retry", "dock_audio_media_enabled", "encoded_surround_output", "low_power_trigger_level", "bluetooth_on"
        };
        public static final String SETUP_PREPAID_DATA_SERVICE_URL = "setup_prepaid_data_service_url";
        public static final String SETUP_PREPAID_DETECTION_REDIR_HOST = "setup_prepaid_detection_redir_host";
        public static final String SETUP_PREPAID_DETECTION_TARGET_URL = "setup_prepaid_detection_target_url";
        public static final String SET_GLOBAL_HTTP_PROXY = "set_global_http_proxy";
        public static final String SET_INSTALL_LOCATION = "set_install_location";
        public static final String SHORTCUT_MANAGER_CONSTANTS = "shortcut_manager_constants";
        public static final String SHOW_NOTIFICATION_CHANNEL_WARNINGS = "show_notification_channel_warnings";
        public static final String SHOW_PROCESSES = "show_processes";
        public static final String SHOW_TEMPERATURE_WARNING = "show_temperature_warning";
        public static final String SMART_SELECTION_UPDATE_CONTENT_URL = "smart_selection_content_url";
        public static final String SMART_SELECTION_UPDATE_METADATA_URL = "smart_selection_metadata_url";
        public static final String SMS_OUTGOING_CHECK_INTERVAL_MS = "sms_outgoing_check_interval_ms";
        public static final String SMS_OUTGOING_CHECK_MAX_COUNT = "sms_outgoing_check_max_count";
        public static final String SMS_SHORT_CODES_UPDATE_CONTENT_URL = "sms_short_codes_content_url";
        public static final String SMS_SHORT_CODES_UPDATE_METADATA_URL = "sms_short_codes_metadata_url";
        public static final String SMS_SHORT_CODE_CONFIRMATION = "sms_short_code_confirmation";
        public static final String SMS_SHORT_CODE_RULE = "sms_short_code_rule";
        public static final String SPEED_LABEL_CACHE_EVICTION_AGE_MILLIS = "speed_label_cache_eviction_age_millis";
        public static final String STAY_ON_WHILE_PLUGGED_IN = "stay_on_while_plugged_in";
        public static final String STORAGE_BENCHMARK_INTERVAL = "storage_benchmark_interval";
        public static final String STORAGE_SETTINGS_CLOBBER_THRESHOLD = "storage_settings_clobber_threshold";
        public static final String SYNC_MAX_RETRY_DELAY_IN_SECONDS = "sync_max_retry_delay_in_seconds";
        public static final String SYS_FREE_STORAGE_LOG_INTERVAL = "sys_free_storage_log_interval";
        public static final String SYS_STORAGE_CACHE_MAX_BYTES = "sys_storage_cache_max_bytes";
        public static final String SYS_STORAGE_CACHE_PERCENTAGE = "sys_storage_cache_percentage";
        public static final String SYS_STORAGE_FULL_THRESHOLD_BYTES = "sys_storage_full_threshold_bytes";
        public static final String SYS_STORAGE_THRESHOLD_MAX_BYTES = "sys_storage_threshold_max_bytes";
        public static final String SYS_STORAGE_THRESHOLD_PERCENTAGE = "sys_storage_threshold_percentage";
        public static final String TCP_DEFAULT_INIT_RWND = "tcp_default_init_rwnd";
        public static final String TETHER_DUN_APN = "tether_dun_apn";
        public static final String TETHER_DUN_REQUIRED = "tether_dun_required";
        public static final String TETHER_OFFLOAD_DISABLED = "tether_offload_disabled";
        public static final String TETHER_SUPPORTED = "tether_supported";
        public static final String TEXT_CLASSIFIER_CONSTANTS = "text_classifier_constants";
        public static final String THEATER_MODE_ON = "theater_mode_on";
        public static final String TRANSITION_ANIMATION_SCALE = "transition_animation_scale";
        public static final String TRUSTED_SOUND = "trusted_sound";
        public static final String TZINFO_UPDATE_CONTENT_URL = "tzinfo_content_url";
        public static final String TZINFO_UPDATE_METADATA_URL = "tzinfo_metadata_url";
        public static final String UNINSTALLED_INSTANT_APP_MAX_CACHE_PERIOD = "uninstalled_instant_app_max_cache_period";
        public static final String UNINSTALLED_INSTANT_APP_MIN_CACHE_PERIOD = "uninstalled_instant_app_min_cache_period";
        public static final String UNLOCK_SOUND = "unlock_sound";
        public static final String UNUSED_STATIC_SHARED_LIB_MIN_CACHE_PERIOD = "unused_static_shared_lib_min_cache_period";
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";
        public static final String USE_GOOGLE_MAIL = "use_google_mail";
        public static final String USE_OPEN_WIFI_PACKAGE = "use_open_wifi_package";
        public static final String VT_IMS_ENABLED = "vt_ims_enabled";
        public static final String WAIT_FOR_DEBUGGER = "wait_for_debugger";
        public static final String WARNING_TEMPERATURE = "warning_temperature";
        public static final String WEBVIEW_DATA_REDUCTION_PROXY_KEY = "webview_data_reduction_proxy_key";
        public static final String WEBVIEW_FALLBACK_LOGIC_ENABLED = "webview_fallback_logic_enabled";
        public static final String WEBVIEW_MULTIPROCESS = "webview_multiprocess";
        public static final String WEBVIEW_PROVIDER = "webview_provider";
        public static final String WFC_IMS_ENABLED = "wfc_ims_enabled";
        public static final String WFC_IMS_MODE = "wfc_ims_mode";
        public static final String WFC_IMS_ROAMING_ENABLED = "wfc_ims_roaming_enabled";
        public static final String WFC_IMS_ROAMING_MODE = "wfc_ims_roaming_mode";
        public static final String WIFI_BADGING_THRESHOLDS = "wifi_badging_thresholds";
        public static final String WIFI_BOUNCE_DELAY_OVERRIDE_MS = "wifi_bounce_delay_override_ms";
        public static final String WIFI_COUNTRY_CODE = "wifi_country_code";
        public static final String WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN = "wifi_device_owner_configs_lockdown";
        public static final String WIFI_DISPLAY_CERTIFICATION_ON = "wifi_display_certification_on";
        public static final String WIFI_DISPLAY_ON = "wifi_display_on";
        public static final String WIFI_DISPLAY_WPS_CONFIG = "wifi_display_wps_config";
        public static final String WIFI_ENHANCED_AUTO_JOIN = "wifi_enhanced_auto_join";
        public static final String WIFI_EPHEMERAL_OUT_OF_RANGE_TIMEOUT_MS = "wifi_ephemeral_out_of_range_timeout_ms";
        public static final String WIFI_FRAMEWORK_SCAN_INTERVAL_MS = "wifi_framework_scan_interval_ms";
        public static final String WIFI_FREQUENCY_BAND = "wifi_frequency_band";
        public static final String WIFI_IDLE_MS = "wifi_idle_ms";
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";
        public static final String WIFI_NETWORK_SHOW_RSSI = "wifi_network_show_rssi";
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";
        public static final String WIFI_ON = "wifi_on";
        public static final String WIFI_P2P_DEVICE_NAME = "wifi_p2p_device_name";
        public static final String WIFI_P2P_PASSIVE_LISTEN_ON = "wifi_p2p_passive_listen_on";
        public static final String WIFI_REENABLE_DELAY_MS = "wifi_reenable_delay";
        public static final String WIFI_SAVED_STATE = "wifi_saved_state";
        public static final String WIFI_SCAN_ALWAYS_AVAILABLE = "wifi_scan_always_enabled";
        public static final String WIFI_SCAN_BACKGROUND_THROTTLE_INTERVAL_MS = "wifi_scan_background_throttle_interval_ms";
        public static final String WIFI_SCAN_BACKGROUND_THROTTLE_PACKAGE_WHITELIST = "wifi_scan_background_throttle_package_whitelist";
        public static final String WIFI_SCAN_INTERVAL_WHEN_P2P_CONNECTED_MS = "wifi_scan_interval_p2p_connected_ms";
        public static final String WIFI_SLEEP_POLICY = "wifi_sleep_policy";
        public static final int WIFI_SLEEP_POLICY_DEFAULT = 0;
        public static final int WIFI_SLEEP_POLICY_NEVER = 2;
        public static final int WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED = 1;
        public static final String WIFI_SUPPLICANT_SCAN_INTERVAL_MS = "wifi_supplicant_scan_interval_ms";
        public static final String WIFI_SUSPEND_OPTIMIZATIONS_ENABLED = "wifi_suspend_optimizations_enabled";
        public static final String WIFI_VERBOSE_LOGGING_ENABLED = "wifi_verbose_logging_enabled";
        public static final String WIFI_WAKEUP_AVAILABLE = "wifi_wakeup_available";
        public static final String WIFI_WAKEUP_ENABLED = "wifi_wakeup_enabled";
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";
        public static final String WIFI_WATCHDOG_POOR_NETWORK_TEST_ENABLED = "wifi_watchdog_poor_network_test_enabled";
        public static final String WIMAX_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wimax_networks_available_notification_on";
        public static final String WINDOW_ANIMATION_SCALE = "window_animation_scale";
        public static final String WIRELESS_CHARGING_STARTED_SOUND = "wireless_charging_started_sound";
        public static final String WTF_IS_FATAL = "wtf_is_fatal";
        public static final String ZEN_MODE = "zen_mode";
        public static final int ZEN_MODE_ALARMS = 3;
        public static final String ZEN_MODE_CONFIG_ETAG = "zen_mode_config_etag";
        public static final int ZEN_MODE_IMPORTANT_INTERRUPTIONS = 1;
        public static final int ZEN_MODE_MIUI_SILENT = 4;
        public static final int ZEN_MODE_NO_INTERRUPTIONS = 2;
        public static final int ZEN_MODE_OFF = 0;
        public static final String ZEN_MODE_RINGER_LEVEL = "zen_mode_ringer_level";
        private static final NameValueCache sNameValueCache;
        private static final ContentProviderHolder sProviderHolder;

        static 
        {
            CONTENT_URI = Uri.parse("content://settings/global");
            sProviderHolder = new ContentProviderHolder(CONTENT_URI);
            sNameValueCache = new NameValueCache(CONTENT_URI, "GET_global", "PUT_global", sProviderHolder);
            MOVED_TO_SECURE = new HashSet(1);
            MOVED_TO_SECURE.add("install_non_market_apps");
            INSTANT_APP_SETTINGS = new ArraySet();
            INSTANT_APP_SETTINGS.add("wait_for_debugger");
            INSTANT_APP_SETTINGS.add("device_provisioned");
            INSTANT_APP_SETTINGS.add("force_resizable_activities");
            INSTANT_APP_SETTINGS.add("debug.force_rtl");
            INSTANT_APP_SETTINGS.add("ephemeral_cookie_max_size_bytes");
            INSTANT_APP_SETTINGS.add("airplane_mode_on");
            INSTANT_APP_SETTINGS.add("window_animation_scale");
            INSTANT_APP_SETTINGS.add("transition_animation_scale");
            INSTANT_APP_SETTINGS.add("animator_duration_scale");
            INSTANT_APP_SETTINGS.add("debug_view_attributes");
            INSTANT_APP_SETTINGS.add("wtf_is_fatal");
            INSTANT_APP_SETTINGS.add("send_action_app_error");
        }

        public Global()
        {
        }
    }

    private static class NameValueCache
    {

        public void clearGenerationTrackerForTest()
        {
            this;
            JVM INSTR monitorenter ;
            if(mGenerationTracker != null)
                mGenerationTracker.destroy();
            mValues.clear();
            mGenerationTracker = null;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public String getStringForUser(ContentResolver contentresolver, String s, int i)
        {
            int k;
            IContentProvider icontentprovider;
            Object obj;
            long l;
            int j;
            byte byte0;
            Object obj1;
            MemoryIntArray memoryintarray;
            GenerationTracker generationtracker;
            if(i == UserHandle.myUserId())
                j = 1;
            else
                j = 0;
            byte0 = -1;
            k = byte0;
            if(j == 0) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorenter ;
            k = byte0;
            if(mGenerationTracker == null) goto _L4; else goto _L3
_L3:
            if(!mGenerationTracker.isGenerationChanged()) goto _L6; else goto _L5
_L5:
            mValues.clear();
_L15:
            k = byte0;
            if(mGenerationTracker != null)
                k = mGenerationTracker.getCurrentGeneration();
_L4:
            this;
            JVM INSTR monitorexit ;
_L2:
            icontentprovider = mProviderHolder.getProvider(contentresolver);
            if(mCallGetCommand == null) goto _L8; else goto _L7
_L7:
            if(j != 0)
                break MISSING_BLOCK_LABEL_907;
            obj = JVM INSTR new #91  <Class Bundle>;
            ((Bundle) (obj)).Bundle();
            obj1 = obj;
            ((Bundle) (obj)).putInt("_user", i);
_L28:
            i = 0;
            obj1 = obj;
            this;
            JVM INSTR monitorenter ;
            if(j == 0)
                break MISSING_BLOCK_LABEL_163;
            if(mGenerationTracker != null)
                break MISSING_BLOCK_LABEL_163;
            i = 1;
            if(obj != null)
                break MISSING_BLOCK_LABEL_155;
            obj = JVM INSTR new #91  <Class Bundle>;
            ((Bundle) (obj)).Bundle();
            ((Bundle) (obj)).putString("_track_generation", null);
            this;
            JVM INSTR monitorexit ;
            if(!Settings.isInSystemServer() || Binder.getCallingUid() == Process.myUid()) goto _L10; else goto _L9
_L9:
            l = Binder.clearCallingIdentity();
            obj = icontentprovider.call(contentresolver.getPackageName(), mCallGetCommand, s, ((Bundle) (obj)));
            Binder.restoreCallingIdentity(l);
_L21:
            if(obj == null) goto _L8; else goto _L11
_L11:
            obj1 = ((Bundle) (obj)).getString("value");
            if(j == 0) goto _L13; else goto _L12
_L12:
            this;
            JVM INSTR monitorenter ;
            if(i == 0)
                break MISSING_BLOCK_LABEL_331;
            memoryintarray = (MemoryIntArray)((Bundle) (obj)).getParcelable("_track_generation");
            i = ((Bundle) (obj)).getInt("_generation_index", -1);
            if(memoryintarray == null || i < 0)
                break MISSING_BLOCK_LABEL_331;
            j = ((Bundle) (obj)).getInt("_generation", 0);
            if(mGenerationTracker != null && !Settings.isInSystemServer())
                mGenerationTracker.destroy();
            generationtracker = JVM INSTR new #60  <Class Settings$GenerationTracker>;
            obj = JVM INSTR new #157 <Class _$Lambda$asz6VwQ86PPY_v8JLMb7rx_pSqg>;
            ((_.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg) (obj))._.Lambda.asz6VwQ86PPY_v8JLMb7rx_pSqg((byte)7, this);
            generationtracker.GenerationTracker(memoryintarray, i, j, ((Runnable) (obj)));
            mGenerationTracker = generationtracker;
            if(mGenerationTracker != null && k == mGenerationTracker.getCurrentGeneration())
                mValues.put(s, obj1);
            this;
            JVM INSTR monitorexit ;
_L13:
            return ((String) (obj1));
_L6:
            if(!mValues.containsKey(s)) goto _L15; else goto _L14
_L14:
            contentresolver = (String)mValues.get(s);
            this;
            JVM INSTR monitorexit ;
            return contentresolver;
            contentresolver;
            throw contentresolver;
            obj;
_L27:
            this;
            JVM INSTR monitorexit ;
            throw obj;
            obj;
_L8:
            Object obj2;
            Object obj3;
            Object obj4;
            obj4 = null;
            obj3 = null;
            obj2 = obj3;
            obj = obj4;
            Bundle bundle = ContentResolver.createSqlQueryBundle("name=?", new String[] {
                s
            }, null);
            obj2 = obj3;
            obj = obj4;
            if(!Settings.isInSystemServer()) goto _L17; else goto _L16
_L16:
            obj2 = obj3;
            obj = obj4;
            if(Binder.getCallingUid() == Process.myUid()) goto _L17; else goto _L18
_L18:
            obj2 = obj3;
            obj = obj4;
            l = Binder.clearCallingIdentity();
            contentresolver = icontentprovider.query(contentresolver.getPackageName(), mUri, SELECT_VALUE_PROJECTION, bundle, null);
            obj2 = contentresolver;
            obj = contentresolver;
            Binder.restoreCallingIdentity(l);
_L22:
            if(contentresolver != null) goto _L20; else goto _L19
_L19:
            obj2 = contentresolver;
            obj = contentresolver;
            obj3 = JVM INSTR new #185 <Class StringBuilder>;
            obj2 = contentresolver;
            obj = contentresolver;
            ((StringBuilder) (obj3)).StringBuilder();
            obj2 = contentresolver;
            obj = contentresolver;
            Log.w("Settings", ((StringBuilder) (obj3)).append("Can't get key ").append(s).append(" from ").append(mUri).toString());
            if(contentresolver != null)
                contentresolver.close();
            return null;
            obj;
            Binder.restoreCallingIdentity(l);
            throw obj;
_L10:
            obj = icontentprovider.call(contentresolver.getPackageName(), mCallGetCommand, s, ((Bundle) (obj)));
              goto _L21
            obj;
            this;
            JVM INSTR monitorexit ;
            throw obj;
            contentresolver;
            obj2 = obj3;
            obj = obj4;
            Binder.restoreCallingIdentity(l);
            obj2 = obj3;
            obj = obj4;
            try
            {
                throw contentresolver;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3)
            {
                obj = obj2;
            }
            contentresolver = JVM INSTR new #185 <Class StringBuilder>;
            obj = obj2;
            contentresolver.StringBuilder();
            obj = obj2;
            Log.w("Settings", contentresolver.append("Can't get key ").append(s).append(" from ").append(mUri).toString(), ((Throwable) (obj3)));
            if(obj2 != null)
                ((Cursor) (obj2)).close();
            return null;
_L17:
            obj2 = obj3;
            obj = obj4;
            contentresolver = icontentprovider.query(contentresolver.getPackageName(), mUri, SELECT_VALUE_PROJECTION, bundle, null);
              goto _L22
_L20:
            obj2 = contentresolver;
            obj = contentresolver;
            if(!contentresolver.moveToNext()) goto _L24; else goto _L23
_L23:
            obj2 = contentresolver;
            obj = contentresolver;
            obj3 = contentresolver.getString(0);
_L26:
            obj2 = contentresolver;
            obj = contentresolver;
            this;
            JVM INSTR monitorenter ;
            if(mGenerationTracker != null && k == mGenerationTracker.getCurrentGeneration())
                mValues.put(s, obj3);
            obj2 = contentresolver;
            obj = contentresolver;
            this;
            JVM INSTR monitorexit ;
            if(contentresolver != null)
                contentresolver.close();
            return ((String) (obj3));
_L24:
            obj3 = null;
            if(true) goto _L26; else goto _L25
_L25:
            Exception exception;
            exception;
            obj2 = contentresolver;
            obj = contentresolver;
            this;
            JVM INSTR monitorexit ;
            obj2 = contentresolver;
            obj = contentresolver;
            throw exception;
            contentresolver;
            if(obj != null)
                ((Cursor) (obj)).close();
            throw contentresolver;
            obj;
              goto _L8
            obj;
              goto _L27
            obj = null;
              goto _L28
        }

        void lambda$_2D_android_provider_Settings$NameValueCache_72263()
        {
            this;
            JVM INSTR monitorenter ;
            Log.e("Settings", "Error accessing generation tracker - removing");
            if(mGenerationTracker != null)
            {
                GenerationTracker generationtracker = mGenerationTracker;
                mGenerationTracker = null;
                if(!Settings.isInSystemServer())
                    generationtracker.destroy();
                mValues.clear();
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean putStringForUser(ContentResolver contentresolver, String s, String s1, String s2, boolean flag, int i)
        {
            Bundle bundle;
            try
            {
                bundle = JVM INSTR new #91  <Class Bundle>;
                bundle.Bundle();
                bundle.putString("value", s1);
                bundle.putInt("_user", i);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                Log.w("Settings", (new StringBuilder()).append("Can't set key ").append(s).append(" in ").append(mUri).toString(), contentresolver);
                return false;
            }
            if(s2 == null)
                break MISSING_BLOCK_LABEL_41;
            bundle.putString("_tag", s2);
            if(!flag)
                break MISSING_BLOCK_LABEL_54;
            bundle.putBoolean("_make_default", true);
            mProviderHolder.getProvider(contentresolver).call(contentresolver.getPackageName(), mCallSetCommand, s, bundle);
            return true;
        }

        private static final boolean DEBUG = false;
        private static final String NAME_EQ_PLACEHOLDER = "name=?";
        private static final String SELECT_VALUE_PROJECTION[] = {
            "value"
        };
        private final String mCallGetCommand;
        private final String mCallSetCommand;
        private GenerationTracker mGenerationTracker;
        private final ContentProviderHolder mProviderHolder;
        private final Uri mUri;
        private final HashMap mValues = new HashMap();


        public NameValueCache(Uri uri, String s, String s1, ContentProviderHolder contentproviderholder)
        {
            mUri = uri;
            mCallGetCommand = s;
            mCallSetCommand = s1;
            mProviderHolder = contentproviderholder;
        }
    }

    public static class NameValueTable
        implements BaseColumns
    {

        public static Uri getUriFor(Uri uri, String s)
        {
            return Uri.withAppendedPath(uri, s);
        }

        protected static boolean putString(ContentResolver contentresolver, Uri uri, String s, String s1)
        {
            try
            {
                ContentValues contentvalues = JVM INSTR new #34  <Class ContentValues>;
                contentvalues.ContentValues();
                contentvalues.put("name", s);
                contentvalues.put("value", s1);
                contentresolver.insert(uri, contentvalues);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                Log.w("Settings", (new StringBuilder()).append("Can't set key ").append(s).append(" in ").append(uri).toString(), contentresolver);
                return false;
            }
            return true;
        }

        public static final String NAME = "name";
        public static final String VALUE = "value";

        public NameValueTable()
        {
        }
    }

    public static final class Secure extends NameValueTable
    {

        public static void clearProviderForTest()
        {
            sProviderHolder.clearProviderForTest();
            sNameValueCache.clearGenerationTrackerForTest();
        }

        public static void getCloneToManagedProfileSettings(Set set)
        {
            set.addAll(CLONE_TO_MANAGED_PROFILE);
        }

        public static float getFloat(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            return getFloatForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static float getFloat(ContentResolver contentresolver, String s, float f)
        {
            return getFloatForUser(contentresolver, s, f, UserHandle.myUserId());
        }

        public static float getFloatForUser(ContentResolver contentresolver, String s, float f, int i)
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            float f1 = f;
            if(contentresolver != null)
                try
                {
                    f1 = Float.parseFloat(contentresolver);
                }
                // Misplaced declaration of an exception variable
                catch(ContentResolver contentresolver)
                {
                    return f;
                }
            return f1;
        }

        public static float getFloatForUser(ContentResolver contentresolver, String s, int i)
            throws SettingNotFoundException
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            if(contentresolver == null)
                throw new SettingNotFoundException(s);
            float f;
            try
            {
                f = Float.parseFloat(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return f;
        }

        public static int getInt(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            return getIntForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static int getInt(ContentResolver contentresolver, String s, int i)
        {
            return getIntForUser(contentresolver, s, i, UserHandle.myUserId());
        }

        public static int getIntForUser(ContentResolver contentresolver, String s, int i)
            throws SettingNotFoundException
        {
            if("location_mode".equals(s))
                return getLocationModeForUser(contentresolver, i);
            contentresolver = getStringForUser(contentresolver, s, i);
            try
            {
                i = Integer.parseInt(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return i;
        }

        public static int getIntForUser(ContentResolver contentresolver, String s, int i, int j)
        {
            if("location_mode".equals(s))
                return getLocationModeForUser(contentresolver, j);
            contentresolver = getStringForUser(contentresolver, s, j);
            j = i;
            if(contentresolver != null)
                try
                {
                    j = Integer.parseInt(contentresolver);
                }
                // Misplaced declaration of an exception variable
                catch(ContentResolver contentresolver)
                {
                    return i;
                }
            return j;
        }

        private static final int getLocationModeForUser(ContentResolver contentresolver, int i)
        {
            Object obj = Settings._2D_get0();
            obj;
            JVM INSTR monitorenter ;
            boolean flag;
            boolean flag1;
            flag = isLocationProviderEnabledForUser(contentresolver, "gps", i);
            flag1 = isLocationProviderEnabledForUser(contentresolver, "network", i);
            if(flag && flag1)
                return 3;
            if(flag)
                return 1;
            if(flag1)
                return 2;
            obj;
            JVM INSTR monitorexit ;
            return 0;
            contentresolver;
            throw contentresolver;
        }

        public static long getLong(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            return getLongForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static long getLong(ContentResolver contentresolver, String s, long l)
        {
            return getLongForUser(contentresolver, s, l, UserHandle.myUserId());
        }

        public static long getLongForUser(ContentResolver contentresolver, String s, int i)
            throws SettingNotFoundException
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            long l;
            try
            {
                l = Long.parseLong(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return l;
        }

        public static long getLongForUser(ContentResolver contentresolver, String s, long l, int i)
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_21;
            long l1 = Long.parseLong(contentresolver);
            l = l1;
_L2:
            return l;
            contentresolver;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public static void getMovedToGlobalSettings(Set set)
        {
            set.addAll(MOVED_TO_GLOBAL);
        }

        public static String getString(ContentResolver contentresolver, String s)
        {
            return getStringForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static String getStringForUser(ContentResolver contentresolver, String s, int i)
        {
            if(MOVED_TO_GLOBAL.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.Secure").append(" to android.provider.Settings.Global.").toString());
                return Global.getStringForUser(contentresolver, s, i);
            }
            if(!MOVED_TO_LOCK_SETTINGS.contains(s))
                break MISSING_BLOCK_LABEL_241;
            android/provider/Settings$Secure;
            JVM INSTR monitorenter ;
            if(sLockSettings != null)
                break MISSING_BLOCK_LABEL_102;
            sLockSettings = com.android.internal.widget.ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));
            boolean flag;
            if(Process.myUid() == 1000)
                flag = true;
            else
                flag = false;
            sIsSystemProcess = flag;
            android/provider/Settings$Secure;
            JVM INSTR monitorexit ;
            if(sLockSettings == null || !(sIsSystemProcess ^ true))
                break MISSING_BLOCK_LABEL_241;
            Object obj = ActivityThread.currentApplication();
            boolean flag1;
            if(obj != null && ((Application) (obj)).getApplicationInfo() != null)
            {
                if(((Application) (obj)).getApplicationInfo().targetSdkVersion <= 22)
                    flag1 = true;
                else
                    flag1 = false;
            } else
            {
                flag1 = false;
            }
            if(!flag1)
                break MISSING_BLOCK_LABEL_199;
            obj = sLockSettings.getString(s, "0", i);
            return ((String) (obj));
            contentresolver;
            throw contentresolver;
            throw new SecurityException((new StringBuilder()).append("Settings.Secure.").append(s).append(" is deprecated and no longer accessible.").append(" See API documentation for potential replacements.").toString());
            RemoteException remoteexception;
            remoteexception;
            return sNameValueCache.getStringForUser(contentresolver, s, i);
        }

        public static Uri getUriFor(String s)
        {
            if(MOVED_TO_GLOBAL.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.Secure").append(" to android.provider.Settings.Global, returning global URI.").toString());
                return Global.getUriFor(Global.CONTENT_URI, s);
            } else
            {
                return getUriFor(CONTENT_URI, s);
            }
        }

        public static final boolean isLocationProviderEnabled(ContentResolver contentresolver, String s)
        {
            return isLocationProviderEnabledForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static final boolean isLocationProviderEnabledForUser(ContentResolver contentresolver, String s, int i)
        {
            return TextUtils.delimitedStringContains(getStringForUser(contentresolver, "location_providers_allowed", i), ',', s);
        }

        public static boolean putFloat(ContentResolver contentresolver, String s, float f)
        {
            return putFloatForUser(contentresolver, s, f, UserHandle.myUserId());
        }

        public static boolean putFloatForUser(ContentResolver contentresolver, String s, float f, int i)
        {
            return putStringForUser(contentresolver, s, Float.toString(f), i);
        }

        public static boolean putInt(ContentResolver contentresolver, String s, int i)
        {
            return putIntForUser(contentresolver, s, i, UserHandle.myUserId());
        }

        public static boolean putIntForUser(ContentResolver contentresolver, String s, int i, int j)
        {
            return putStringForUser(contentresolver, s, Integer.toString(i), j);
        }

        public static boolean putLong(ContentResolver contentresolver, String s, long l)
        {
            return putLongForUser(contentresolver, s, l, UserHandle.myUserId());
        }

        public static boolean putLongForUser(ContentResolver contentresolver, String s, long l, int i)
        {
            return putStringForUser(contentresolver, s, Long.toString(l), i);
        }

        public static boolean putString(ContentResolver contentresolver, String s, String s1)
        {
            return putStringForUser(contentresolver, s, s1, UserHandle.myUserId());
        }

        public static boolean putString(ContentResolver contentresolver, String s, String s1, String s2, boolean flag)
        {
            return putStringForUser(contentresolver, s, s1, s2, flag, UserHandle.myUserId());
        }

        public static boolean putStringForUser(ContentResolver contentresolver, String s, String s1, int i)
        {
            return putStringForUser(contentresolver, s, s1, null, false, i);
        }

        public static boolean putStringForUser(ContentResolver contentresolver, String s, String s1, String s2, boolean flag, int i)
        {
            if("location_mode".equals(s))
                return setLocationModeForUser(contentresolver, Integer.parseInt(s1), i);
            if(MOVED_TO_GLOBAL.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.Secure").append(" to android.provider.Settings.Global").toString());
                return Global.putStringForUser(contentresolver, s, s1, s2, flag, i);
            } else
            {
                return sNameValueCache.putStringForUser(contentresolver, s, s1, s2, flag, i);
            }
        }

        public static void resetToDefaults(ContentResolver contentresolver, String s)
        {
            resetToDefaultsAsUser(contentresolver, s, 1, UserHandle.myUserId());
        }

        public static void resetToDefaultsAsUser(ContentResolver contentresolver, String s, int i, int j)
        {
            Bundle bundle;
            bundle = JVM INSTR new #1258 <Class Bundle>;
            bundle.Bundle();
            bundle.putInt("_user", j);
            if(s == null)
                break MISSING_BLOCK_LABEL_32;
            bundle.putString("_tag", s);
            bundle.putInt("_reset_mode", i);
            sProviderHolder.getProvider(contentresolver).call(contentresolver.getPackageName(), "RESET_secure", null, bundle);
_L1:
            return;
            contentresolver;
            Log.w("Settings", (new StringBuilder()).append("Can't reset do defaults for ").append(CONTENT_URI).toString(), contentresolver);
              goto _L1
        }

        private static final boolean restoreLocationModeForUser(ContentResolver contentresolver, int i)
        {
            int j = getIntForUser(contentresolver, "location_previous_mode", 3, i);
            int k = j;
            if(j == 0)
                k = 3;
            return setLocationModeForUser(contentresolver, k, i);
        }

        private static final boolean saveLocationModeForUser(ContentResolver contentresolver, int i)
        {
            return putIntForUser(contentresolver, "location_previous_mode", getLocationModeForUser(contentresolver, i), i);
        }

        private static final boolean setLocationModeForUser(ContentResolver contentresolver, int i, int j)
        {
            Object obj = Settings._2D_get0();
            obj;
            JVM INSTR monitorenter ;
            boolean flag;
            boolean flag1;
            flag = false;
            flag1 = false;
            i;
            JVM INSTR tableswitch -1 3: default 48
        //                       -1 88
        //                       0 100
        //                       1 144
        //                       2 150
        //                       3 156;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L6:
            break MISSING_BLOCK_LABEL_156;
_L1:
            IllegalArgumentException illegalargumentexception = JVM INSTR new #1301 <Class IllegalArgumentException>;
            contentresolver = JVM INSTR new #1110 <Class StringBuilder>;
            contentresolver.StringBuilder();
            illegalargumentexception.IllegalArgumentException(contentresolver.append("Invalid location mode: ").append(i).toString());
            throw illegalargumentexception;
            contentresolver;
            obj;
            JVM INSTR monitorexit ;
            throw contentresolver;
_L2:
            flag = restoreLocationModeForUser(contentresolver, j);
            obj;
            JVM INSTR monitorexit ;
            return flag;
_L3:
            saveLocationModeForUser(contentresolver, j);
_L7:
            flag1 = setLocationProviderEnabledForUser(contentresolver, "network", flag1, j);
            flag = setLocationProviderEnabledForUser(contentresolver, "gps", flag, j);
            if(flag)
                flag = flag1;
            else
                flag = false;
            obj;
            JVM INSTR monitorexit ;
            return flag;
_L4:
            flag = true;
              goto _L7
_L5:
            flag1 = true;
              goto _L7
            flag = true;
            flag1 = true;
              goto _L7
        }

        public static final void setLocationProviderEnabled(ContentResolver contentresolver, String s, boolean flag)
        {
            setLocationProviderEnabledForUser(contentresolver, s, flag, UserHandle.myUserId());
        }

        public static final boolean setLocationProviderEnabledForUser(ContentResolver contentresolver, String s, boolean flag, int i)
        {
            Object obj = Settings._2D_get0();
            obj;
            JVM INSTR monitorenter ;
            if(!flag)
                break MISSING_BLOCK_LABEL_53;
            StringBuilder stringbuilder = JVM INSTR new #1110 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            s = stringbuilder.append("+").append(s).toString();
_L1:
            flag = putStringForUser(contentresolver, "location_providers_allowed", s, i);
            obj;
            JVM INSTR monitorexit ;
            return flag;
            StringBuilder stringbuilder1 = JVM INSTR new #1110 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            s = stringbuilder1.append("-").append(s).toString();
              goto _L1
            contentresolver;
            throw contentresolver;
        }

        public static final String ACCESSIBILITY_AUTOCLICK_DELAY = "accessibility_autoclick_delay";
        public static final String ACCESSIBILITY_AUTOCLICK_ENABLED = "accessibility_autoclick_enabled";
        public static final String ACCESSIBILITY_BUTTON_TARGET_COMPONENT = "accessibility_button_target_component";
        public static final String ACCESSIBILITY_CAPTIONING_BACKGROUND_COLOR = "accessibility_captioning_background_color";
        public static final String ACCESSIBILITY_CAPTIONING_EDGE_COLOR = "accessibility_captioning_edge_color";
        public static final String ACCESSIBILITY_CAPTIONING_EDGE_TYPE = "accessibility_captioning_edge_type";
        public static final String ACCESSIBILITY_CAPTIONING_ENABLED = "accessibility_captioning_enabled";
        public static final String ACCESSIBILITY_CAPTIONING_FONT_SCALE = "accessibility_captioning_font_scale";
        public static final String ACCESSIBILITY_CAPTIONING_FOREGROUND_COLOR = "accessibility_captioning_foreground_color";
        public static final String ACCESSIBILITY_CAPTIONING_LOCALE = "accessibility_captioning_locale";
        public static final String ACCESSIBILITY_CAPTIONING_PRESET = "accessibility_captioning_preset";
        public static final String ACCESSIBILITY_CAPTIONING_TYPEFACE = "accessibility_captioning_typeface";
        public static final String ACCESSIBILITY_CAPTIONING_WINDOW_COLOR = "accessibility_captioning_window_color";
        public static final String ACCESSIBILITY_DISPLAY_DALTONIZER = "accessibility_display_daltonizer";
        public static final String ACCESSIBILITY_DISPLAY_DALTONIZER_ENABLED = "accessibility_display_daltonizer_enabled";
        public static final String ACCESSIBILITY_DISPLAY_INVERSION_ENABLED = "accessibility_display_inversion_enabled";
        public static final String ACCESSIBILITY_DISPLAY_MAGNIFICATION_AUTO_UPDATE = "accessibility_display_magnification_auto_update";
        public static final String ACCESSIBILITY_DISPLAY_MAGNIFICATION_ENABLED = "accessibility_display_magnification_enabled";
        public static final String ACCESSIBILITY_DISPLAY_MAGNIFICATION_NAVBAR_ENABLED = "accessibility_display_magnification_navbar_enabled";
        public static final String ACCESSIBILITY_DISPLAY_MAGNIFICATION_SCALE = "accessibility_display_magnification_scale";
        public static final String ACCESSIBILITY_ENABLED = "accessibility_enabled";
        public static final String ACCESSIBILITY_HIGH_TEXT_CONTRAST_ENABLED = "high_text_contrast_enabled";
        public static final String ACCESSIBILITY_LARGE_POINTER_ICON = "accessibility_large_pointer_icon";
        public static final String ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN = "accessibility_shortcut_dialog_shown";
        public static final String ACCESSIBILITY_SHORTCUT_ENABLED = "accessibility_shortcut_enabled";
        public static final String ACCESSIBILITY_SHORTCUT_ON_LOCK_SCREEN = "accessibility_shortcut_on_lock_screen";
        public static final String ACCESSIBILITY_SHORTCUT_TARGET_SERVICE = "accessibility_shortcut_target_service";
        public static final String ACCESSIBILITY_SOFT_KEYBOARD_MODE = "accessibility_soft_keyboard_mode";
        public static final String ACCESSIBILITY_SPEAK_PASSWORD = "speak_password";
        public static final String ADB_ENABLED = "adb_enabled";
        public static final String ALLOWED_GEOLOCATION_ORIGINS = "allowed_geolocation_origins";
        public static final String ALLOW_MOCK_LOCATION = "mock_location";
        public static final String ALWAYS_ON_VPN_APP = "always_on_vpn_app";
        public static final String ALWAYS_ON_VPN_LOCKDOWN = "always_on_vpn_lockdown";
        public static final String ANDROID_ID = "android_id";
        public static final String ANR_SHOW_BACKGROUND = "anr_show_background";
        public static final String ASSISTANT = "assistant";
        public static final String ASSIST_DISCLOSURE_ENABLED = "assist_disclosure_enabled";
        public static final String ASSIST_GESTURE_ENABLED = "assist_gesture_enabled";
        public static final String ASSIST_GESTURE_SENSITIVITY = "assist_gesture_sensitivity";
        public static final String ASSIST_GESTURE_SETUP_COMPLETE = "assist_gesture_setup_complete";
        public static final String ASSIST_GESTURE_SILENCE_ALERTS_ENABLED = "assist_gesture_silence_alerts_enabled";
        public static final String ASSIST_GESTURE_WAKE_ENABLED = "assist_gesture_wake_enabled";
        public static final String ASSIST_SCREENSHOT_ENABLED = "assist_screenshot_enabled";
        public static final String ASSIST_STRUCTURE_ENABLED = "assist_structure_enabled";
        public static final String AUTOFILL_SERVICE = "autofill_service";
        public static final String AUTOFILL_SERVICE_SEARCH_URI = "autofill_service_search_uri";
        public static final String AUTOMATIC_STORAGE_MANAGER_BYTES_CLEARED = "automatic_storage_manager_bytes_cleared";
        public static final String AUTOMATIC_STORAGE_MANAGER_DAYS_TO_RETAIN = "automatic_storage_manager_days_to_retain";
        public static final int AUTOMATIC_STORAGE_MANAGER_DAYS_TO_RETAIN_DEFAULT = 90;
        public static final String AUTOMATIC_STORAGE_MANAGER_ENABLED = "automatic_storage_manager_enabled";
        public static final String AUTOMATIC_STORAGE_MANAGER_LAST_RUN = "automatic_storage_manager_last_run";
        public static final String AUTOMATIC_STORAGE_MANAGER_TURNED_OFF_BY_POLICY = "automatic_storage_manager_turned_off_by_policy";
        public static final String BACKGROUND_DATA = "background_data";
        public static final String BACKUP_AUTO_RESTORE = "backup_auto_restore";
        public static final String BACKUP_ENABLED = "backup_enabled";
        public static final String BACKUP_PROVISIONED = "backup_provisioned";
        public static final String BACKUP_TRANSPORT = "backup_transport";
        public static final String BLUETOOTH_ON = "bluetooth_on";
        public static final String BUGREPORT_IN_POWER_MENU = "bugreport_in_power_menu";
        public static final String CAMERA_DOUBLE_TAP_POWER_GESTURE_DISABLED = "camera_double_tap_power_gesture_disabled";
        public static final String CAMERA_DOUBLE_TWIST_TO_FLIP_ENABLED = "camera_double_twist_to_flip_enabled";
        public static final String CAMERA_GESTURE_DISABLED = "camera_gesture_disabled";
        public static final String CAMERA_LIFT_TRIGGER_ENABLED = "camera_lift_trigger_enabled";
        public static final int CAMERA_LIFT_TRIGGER_ENABLED_DEFAULT = 1;
        public static final String CARRIER_APPS_HANDLED = "carrier_apps_handled";
        private static final Set CLONE_TO_MANAGED_PROFILE;
        public static final String CMAS_ADDITIONAL_BROADCAST_PKG = "cmas_additional_broadcast_pkg";
        public static final String COMPLETED_CATEGORY_PREFIX = "suggested.completed_category.";
        public static final String CONNECTIVITY_RELEASE_PENDING_INTENT_DELAY_MS = "connectivity_release_pending_intent_delay_ms";
        public static final Uri CONTENT_URI;
        public static final String DATA_ROAMING = "data_roaming";
        public static final String DEFAULT_INPUT_METHOD = "default_input_method";
        public static final String DEMO_USER_SETUP_COMPLETE = "demo_user_setup_complete";
        public static final String DEVELOPMENT_SETTINGS_ENABLED = "development_settings_enabled";
        public static final String DEVICE_PAIRED = "device_paired";
        public static final String DEVICE_PROVISIONED = "device_provisioned";
        public static final String DIALER_DEFAULT_APPLICATION = "dialer_default_application";
        public static final String DISABLED_PRINT_SERVICES = "disabled_print_services";
        public static final String DISABLED_SYSTEM_INPUT_METHODS = "disabled_system_input_methods";
        public static final String DISPLAY_DENSITY_FORCED = "display_density_forced";
        public static final String DOUBLE_TAP_TO_WAKE = "double_tap_to_wake";
        public static final String DOZE_ALWAYS_ON = "doze_always_on";
        public static final String DOZE_ENABLED = "doze_enabled";
        public static final String DOZE_PULSE_ON_DOUBLE_TAP = "doze_pulse_on_double_tap";
        public static final String DOZE_PULSE_ON_LONG_PRESS = "doze_pulse_on_long_press";
        public static final String DOZE_PULSE_ON_PICK_UP = "doze_pulse_on_pick_up";
        public static final String EMERGENCY_ASSISTANCE_APPLICATION = "emergency_assistance_application";
        public static final String ENABLED_ACCESSIBILITY_SERVICES = "enabled_accessibility_services";
        public static final String ENABLED_INPUT_METHODS = "enabled_input_methods";
        public static final String ENABLED_NOTIFICATION_ASSISTANT = "enabled_notification_assistant";
        public static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
        public static final String ENABLED_NOTIFICATION_POLICY_ACCESS_PACKAGES = "enabled_notification_policy_access_packages";
        public static final String ENABLED_PRINT_SERVICES = "enabled_print_services";
        public static final String ENABLED_VR_LISTENERS = "enabled_vr_listeners";
        public static final String ENHANCED_VOICE_PRIVACY_ENABLED = "enhanced_voice_privacy_enabled";
        public static final String HTTP_PROXY = "http_proxy";
        public static final String IMMERSIVE_MODE_CONFIRMATIONS = "immersive_mode_confirmations";
        public static final String INCALL_BACK_BUTTON_BEHAVIOR = "incall_back_button_behavior";
        public static final int INCALL_BACK_BUTTON_BEHAVIOR_DEFAULT = 0;
        public static final int INCALL_BACK_BUTTON_BEHAVIOR_HANGUP = 1;
        public static final int INCALL_BACK_BUTTON_BEHAVIOR_NONE = 0;
        public static final String INCALL_POWER_BUTTON_BEHAVIOR = "incall_power_button_behavior";
        public static final int INCALL_POWER_BUTTON_BEHAVIOR_DEFAULT = 1;
        public static final int INCALL_POWER_BUTTON_BEHAVIOR_HANGUP = 2;
        public static final int INCALL_POWER_BUTTON_BEHAVIOR_SCREEN_OFF = 1;
        public static final String INPUT_METHODS_SUBTYPE_HISTORY = "input_methods_subtype_history";
        public static final String INPUT_METHOD_SELECTOR_VISIBILITY = "input_method_selector_visibility";
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";
        public static final String INSTANT_APPS_ENABLED = "instant_apps_enabled";
        public static final Set INSTANT_APP_SETTINGS;
        public static final String LAST_SETUP_SHOWN = "last_setup_shown";
        public static final String LEGACY_RESTORE_SETTINGS[] = {
            "enabled_notification_listeners", "enabled_notification_assistant", "enabled_notification_policy_access_packages"
        };
        public static final String LOCATION_MODE = "location_mode";
        public static final int LOCATION_MODE_BATTERY_SAVING = 2;
        public static final int LOCATION_MODE_HIGH_ACCURACY = 3;
        public static final int LOCATION_MODE_OFF = 0;
        public static final int LOCATION_MODE_PREVIOUS = -1;
        public static final int LOCATION_MODE_SENSORS_ONLY = 1;
        public static final String LOCATION_PREVIOUS_MODE = "location_previous_mode";
        public static final String LOCATION_PROVIDERS_ALLOWED = "location_providers_allowed";
        public static final String LOCK_BIOMETRIC_WEAK_FLAGS = "lock_biometric_weak_flags";
        public static final String LOCK_PATTERN_ENABLED = "lock_pattern_autolock";
        public static final String LOCK_PATTERN_TACTILE_FEEDBACK_ENABLED = "lock_pattern_tactile_feedback_enabled";
        public static final String LOCK_PATTERN_VISIBLE = "lock_pattern_visible_pattern";
        public static final String LOCK_SCREEN_ALLOW_PRIVATE_NOTIFICATIONS = "lock_screen_allow_private_notifications";
        public static final String LOCK_SCREEN_ALLOW_REMOTE_INPUT = "lock_screen_allow_remote_input";
        public static final String LOCK_SCREEN_APPWIDGET_IDS = "lock_screen_appwidget_ids";
        public static final String LOCK_SCREEN_FALLBACK_APPWIDGET_ID = "lock_screen_fallback_appwidget_id";
        public static final String LOCK_SCREEN_LOCK_AFTER_TIMEOUT = "lock_screen_lock_after_timeout";
        public static final String LOCK_SCREEN_OWNER_INFO = "lock_screen_owner_info";
        public static final String LOCK_SCREEN_OWNER_INFO_ENABLED = "lock_screen_owner_info_enabled";
        public static final String LOCK_SCREEN_SHOW_NOTIFICATIONS = "lock_screen_show_notifications";
        public static final String LOCK_SCREEN_STICKY_APPWIDGET = "lock_screen_sticky_appwidget";
        public static final String LOCK_TO_APP_EXIT_LOCKED = "lock_to_app_exit_locked";
        public static final String LOGGING_ID = "logging_id";
        public static final String LONG_PRESS_TIMEOUT = "long_press_timeout";
        public static final String MANAGED_PROFILE_CONTACT_REMOTE_SEARCH = "managed_profile_contact_remote_search";
        public static final String MOUNT_PLAY_NOTIFICATION_SND = "mount_play_not_snd";
        public static final String MOUNT_UMS_AUTOSTART = "mount_ums_autostart";
        public static final String MOUNT_UMS_NOTIFY_ENABLED = "mount_ums_notify_enabled";
        public static final String MOUNT_UMS_PROMPT = "mount_ums_prompt";
        private static final HashSet MOVED_TO_GLOBAL;
        private static final HashSet MOVED_TO_LOCK_SETTINGS;
        public static final String MULTI_PRESS_TIMEOUT = "multi_press_timeout";
        public static final String NETWORK_PREFERENCE = "network_preference";
        public static final String NFC_PAYMENT_DEFAULT_COMPONENT = "nfc_payment_default_component";
        public static final String NFC_PAYMENT_FOREGROUND = "nfc_payment_foreground";
        public static final String NIGHT_DISPLAY_ACTIVATED = "night_display_activated";
        public static final String NIGHT_DISPLAY_AUTO_MODE = "night_display_auto_mode";
        public static final String NIGHT_DISPLAY_COLOR_TEMPERATURE = "night_display_color_temperature";
        public static final String NIGHT_DISPLAY_CUSTOM_END_TIME = "night_display_custom_end_time";
        public static final String NIGHT_DISPLAY_CUSTOM_START_TIME = "night_display_custom_start_time";
        public static final String NIGHT_DISPLAY_LAST_ACTIVATED_TIME = "night_display_last_activated_time";
        public static final String NOTIFICATION_BADGING = "notification_badging";
        public static final String OVERVIEW_LAST_STACK_ACTIVE_TIME = "overview_last_stack_active_time";
        public static final String PACKAGE_VERIFIER_STATE = "package_verifier_state";
        public static final String PACKAGE_VERIFIER_USER_CONSENT = "package_verifier_user_consent";
        public static final String PARENTAL_CONTROL_ENABLED = "parental_control_enabled";
        public static final String PARENTAL_CONTROL_LAST_UPDATE = "parental_control_last_update";
        public static final String PARENTAL_CONTROL_REDIRECT_URL = "parental_control_redirect_url";
        public static final String PAYMENT_SERVICE_SEARCH_URI = "payment_service_search_uri";
        public static final String PREFERRED_TTY_MODE = "preferred_tty_mode";
        public static final String PRINT_SERVICE_SEARCH_URI = "print_service_search_uri";
        public static final String QS_AUTO_ADDED_TILES = "qs_auto_tiles";
        public static final String QS_TILES = "sysui_qs_tiles";
        public static final String SCREENSAVER_ACTIVATE_ON_DOCK = "screensaver_activate_on_dock";
        public static final String SCREENSAVER_ACTIVATE_ON_SLEEP = "screensaver_activate_on_sleep";
        public static final String SCREENSAVER_COMPONENTS = "screensaver_components";
        public static final String SCREENSAVER_DEFAULT_COMPONENT = "screensaver_default_component";
        public static final String SCREENSAVER_ENABLED = "screensaver_enabled";
        public static final String SEARCH_GLOBAL_SEARCH_ACTIVITY = "search_global_search_activity";
        public static final String SEARCH_MAX_RESULTS_PER_SOURCE = "search_max_results_per_source";
        public static final String SEARCH_MAX_RESULTS_TO_DISPLAY = "search_max_results_to_display";
        public static final String SEARCH_MAX_SHORTCUTS_RETURNED = "search_max_shortcuts_returned";
        public static final String SEARCH_MAX_SOURCE_EVENT_AGE_MILLIS = "search_max_source_event_age_millis";
        public static final String SEARCH_MAX_STAT_AGE_MILLIS = "search_max_stat_age_millis";
        public static final String SEARCH_MIN_CLICKS_FOR_SOURCE_RANKING = "search_min_clicks_for_source_ranking";
        public static final String SEARCH_MIN_IMPRESSIONS_FOR_SOURCE_RANKING = "search_min_impressions_for_source_ranking";
        public static final String SEARCH_NUM_PROMOTED_SOURCES = "search_num_promoted_sources";
        public static final String SEARCH_PER_SOURCE_CONCURRENT_QUERY_LIMIT = "search_per_source_concurrent_query_limit";
        public static final String SEARCH_PREFILL_MILLIS = "search_prefill_millis";
        public static final String SEARCH_PROMOTED_SOURCE_DEADLINE_MILLIS = "search_promoted_source_deadline_millis";
        public static final String SEARCH_QUERY_THREAD_CORE_POOL_SIZE = "search_query_thread_core_pool_size";
        public static final String SEARCH_QUERY_THREAD_MAX_POOL_SIZE = "search_query_thread_max_pool_size";
        public static final String SEARCH_SHORTCUT_REFRESH_CORE_POOL_SIZE = "search_shortcut_refresh_core_pool_size";
        public static final String SEARCH_SHORTCUT_REFRESH_MAX_POOL_SIZE = "search_shortcut_refresh_max_pool_size";
        public static final String SEARCH_SOURCE_TIMEOUT_MILLIS = "search_source_timeout_millis";
        public static final String SEARCH_THREAD_KEEPALIVE_SECONDS = "search_thread_keepalive_seconds";
        public static final String SEARCH_WEB_RESULTS_OVERRIDE_LIMIT = "search_web_results_override_limit";
        public static final String SELECTED_INPUT_METHOD_SUBTYPE = "selected_input_method_subtype";
        public static final String SELECTED_SPELL_CHECKER = "selected_spell_checker";
        public static final String SELECTED_SPELL_CHECKER_SUBTYPE = "selected_spell_checker_subtype";
        public static final String SETTINGS_CLASSNAME = "settings_classname";
        public static final String SETTINGS_TO_BACKUP[] = {
            "bugreport_in_power_menu", "mock_location", "parental_control_enabled", "parental_control_redirect_url", "usb_mass_storage_enabled", "accessibility_display_inversion_enabled", "accessibility_display_daltonizer", "accessibility_display_daltonizer_enabled", "accessibility_display_magnification_enabled", "accessibility_display_magnification_navbar_enabled", 
            "autofill_service", "accessibility_display_magnification_scale", "enabled_accessibility_services", "enabled_vr_listeners", "enabled_input_methods", "touch_exploration_granted_accessibility_services", "touch_exploration_enabled", "accessibility_enabled", "accessibility_shortcut_target_service", "accessibility_button_target_component", 
            "accessibility_shortcut_dialog_shown", "accessibility_shortcut_enabled", "accessibility_shortcut_on_lock_screen", "speak_password", "high_text_contrast_enabled", "accessibility_captioning_preset", "accessibility_captioning_enabled", "accessibility_captioning_locale", "accessibility_captioning_background_color", "accessibility_captioning_foreground_color", 
            "accessibility_captioning_edge_type", "accessibility_captioning_edge_color", "accessibility_captioning_typeface", "accessibility_captioning_font_scale", "accessibility_captioning_window_color", "tts_use_defaults", "tts_default_rate", "tts_default_pitch", "tts_default_synth", "tts_default_lang", 
            "tts_default_country", "tts_enabled_plugins", "tts_default_locale", "show_ime_with_hard_keyboard", "wifi_networks_available_notification_on", "wifi_networks_available_repeat_delay", "wifi_num_open_networks_kept", "selected_spell_checker", "selected_spell_checker_subtype", "spell_checker_enabled", 
            "mount_play_not_snd", "mount_ums_autostart", "mount_ums_prompt", "mount_ums_notify_enabled", "sleep_timeout", "double_tap_to_wake", "wake_gesture_enabled", "long_press_timeout", "camera_gesture_disabled", "accessibility_autoclick_enabled", 
            "accessibility_autoclick_delay", "accessibility_large_pointer_icon", "preferred_tty_mode", "enhanced_voice_privacy_enabled", "tty_mode_enabled", "incall_power_button_behavior", "night_display_custom_start_time", "night_display_custom_end_time", "night_display_color_temperature", "night_display_auto_mode", 
            "sync_parent_sounds", "camera_double_twist_to_flip_enabled", "camera_double_tap_power_gesture_disabled", "system_navigation_keys_enabled", "sysui_qs_tiles", "doze_enabled", "doze_pulse_on_pick_up", "doze_pulse_on_double_tap", "nfc_payment_default_component", "automatic_storage_manager_days_to_retain", 
            "assist_gesture_enabled", "assist_gesture_sensitivity", "assist_gesture_setup_complete", "assist_gesture_silence_alerts_enabled", "assist_gesture_wake_enabled", "vr_display_mode", "notification_badging", "qs_auto_tiles", "screensaver_enabled", "screensaver_components", 
            "screensaver_activate_on_dock", "screensaver_activate_on_sleep", "wifi_disconnect_delay_duration"
        };
        public static final String SHOW_IME_WITH_HARD_KEYBOARD = "show_ime_with_hard_keyboard";
        public static final int SHOW_MODE_AUTO = 0;
        public static final int SHOW_MODE_HIDDEN = 1;
        public static final String SHOW_NOTE_ABOUT_NOTIFICATION_HIDING = "show_note_about_notification_hiding";
        public static final String SKIP_FIRST_USE_HINTS = "skip_first_use_hints";
        public static final String SLEEP_TIMEOUT = "sleep_timeout";
        public static final String SMS_DEFAULT_APPLICATION = "sms_default_application";
        public static final String SPELL_CHECKER_ENABLED = "spell_checker_enabled";
        public static final String SYNC_PARENT_SOUNDS = "sync_parent_sounds";
        public static final String SYSTEM_NAVIGATION_KEYS_ENABLED = "system_navigation_keys_enabled";
        public static final String TOUCH_EXPLORATION_ENABLED = "touch_exploration_enabled";
        public static final String TOUCH_EXPLORATION_GRANTED_ACCESSIBILITY_SERVICES = "touch_exploration_granted_accessibility_services";
        public static final String TRUST_AGENTS_INITIALIZED = "trust_agents_initialized";
        public static final String TTS_DEFAULT_COUNTRY = "tts_default_country";
        public static final String TTS_DEFAULT_LANG = "tts_default_lang";
        public static final String TTS_DEFAULT_LOCALE = "tts_default_locale";
        public static final String TTS_DEFAULT_PITCH = "tts_default_pitch";
        public static final String TTS_DEFAULT_RATE = "tts_default_rate";
        public static final String TTS_DEFAULT_SYNTH = "tts_default_synth";
        public static final String TTS_DEFAULT_VARIANT = "tts_default_variant";
        public static final String TTS_ENABLED_PLUGINS = "tts_enabled_plugins";
        public static final String TTS_USE_DEFAULTS = "tts_use_defaults";
        public static final String TTY_MODE_ENABLED = "tty_mode_enabled";
        public static final String TV_INPUT_CUSTOM_LABELS = "tv_input_custom_labels";
        public static final String TV_INPUT_HIDDEN_INPUTS = "tv_input_hidden_inputs";
        public static final String TV_USER_SETUP_COMPLETE = "tv_user_setup_complete";
        public static final String UI_NIGHT_MODE = "ui_night_mode";
        public static final String UNKNOWN_SOURCES_DEFAULT_REVERSED = "unknown_sources_default_reversed";
        public static final String UNSAFE_VOLUME_MUSIC_ACTIVE_MS = "unsafe_volume_music_active_ms";
        public static final String USB_AUDIO_AUTOMATIC_ROUTING_DISABLED = "usb_audio_automatic_routing_disabled";
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";
        public static final String USER_SETUP_COMPLETE = "user_setup_complete";
        public static final String USE_GOOGLE_MAIL = "use_google_mail";
        public static final String VOICE_INTERACTION_SERVICE = "voice_interaction_service";
        public static final String VOICE_RECOGNITION_SERVICE = "voice_recognition_service";
        public static final String VR_DISPLAY_MODE = "vr_display_mode";
        public static final int VR_DISPLAY_MODE_LOW_PERSISTENCE = 0;
        public static final int VR_DISPLAY_MODE_OFF = 1;
        public static final String WAKE_GESTURE_ENABLED = "wake_gesture_enabled";
        public static final String WIFI_DISCONNECT_DELAY_DURATION = "wifi_disconnect_delay_duration";
        public static final String WIFI_IDLE_MS = "wifi_idle_ms";
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";
        public static final String WIFI_ON = "wifi_on";
        public static final String WIFI_WATCHDOG_ACCEPTABLE_PACKET_LOSS_PERCENTAGE = "wifi_watchdog_acceptable_packet_loss_percentage";
        public static final String WIFI_WATCHDOG_AP_COUNT = "wifi_watchdog_ap_count";
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_DELAY_MS = "wifi_watchdog_background_check_delay_ms";
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_ENABLED = "wifi_watchdog_background_check_enabled";
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS = "wifi_watchdog_background_check_timeout_ms";
        public static final String WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT = "wifi_watchdog_initial_ignored_ping_count";
        public static final String WIFI_WATCHDOG_MAX_AP_CHECKS = "wifi_watchdog_max_ap_checks";
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";
        public static final String WIFI_WATCHDOG_PING_COUNT = "wifi_watchdog_ping_count";
        public static final String WIFI_WATCHDOG_PING_DELAY_MS = "wifi_watchdog_ping_delay_ms";
        public static final String WIFI_WATCHDOG_PING_TIMEOUT_MS = "wifi_watchdog_ping_timeout_ms";
        public static final String WIFI_WATCHDOG_WATCH_LIST = "wifi_watchdog_watch_list";
        private static boolean sIsSystemProcess;
        private static ILockSettings sLockSettings = null;
        private static final NameValueCache sNameValueCache;
        private static final ContentProviderHolder sProviderHolder;

        static 
        {
            CONTENT_URI = Uri.parse("content://settings/secure");
            sProviderHolder = new ContentProviderHolder(CONTENT_URI);
            sNameValueCache = new NameValueCache(CONTENT_URI, "GET_secure", "PUT_secure", sProviderHolder);
            MOVED_TO_LOCK_SETTINGS = new HashSet(3);
            MOVED_TO_LOCK_SETTINGS.add("lock_pattern_autolock");
            MOVED_TO_LOCK_SETTINGS.add("lock_pattern_visible_pattern");
            MOVED_TO_LOCK_SETTINGS.add("lock_pattern_tactile_feedback_enabled");
            MOVED_TO_GLOBAL = new HashSet();
            MOVED_TO_GLOBAL.add("adb_enabled");
            MOVED_TO_GLOBAL.add("assisted_gps_enabled");
            MOVED_TO_GLOBAL.add("bluetooth_on");
            MOVED_TO_GLOBAL.add("bugreport_in_power_menu");
            MOVED_TO_GLOBAL.add("cdma_cell_broadcast_sms");
            MOVED_TO_GLOBAL.add("roaming_settings");
            MOVED_TO_GLOBAL.add("subscription_mode");
            MOVED_TO_GLOBAL.add("data_activity_timeout_mobile");
            MOVED_TO_GLOBAL.add("data_activity_timeout_wifi");
            MOVED_TO_GLOBAL.add("data_roaming");
            MOVED_TO_GLOBAL.add("development_settings_enabled");
            MOVED_TO_GLOBAL.add("device_provisioned");
            MOVED_TO_GLOBAL.add("display_size_forced");
            MOVED_TO_GLOBAL.add("download_manager_max_bytes_over_mobile");
            MOVED_TO_GLOBAL.add("download_manager_recommended_max_bytes_over_mobile");
            MOVED_TO_GLOBAL.add("mobile_data");
            MOVED_TO_GLOBAL.add("netstats_dev_bucket_duration");
            MOVED_TO_GLOBAL.add("netstats_dev_delete_age");
            MOVED_TO_GLOBAL.add("netstats_dev_persist_bytes");
            MOVED_TO_GLOBAL.add("netstats_dev_rotate_age");
            MOVED_TO_GLOBAL.add("netstats_enabled");
            MOVED_TO_GLOBAL.add("netstats_global_alert_bytes");
            MOVED_TO_GLOBAL.add("netstats_poll_interval");
            MOVED_TO_GLOBAL.add("netstats_sample_enabled");
            MOVED_TO_GLOBAL.add("netstats_time_cache_max_age");
            MOVED_TO_GLOBAL.add("netstats_uid_bucket_duration");
            MOVED_TO_GLOBAL.add("netstats_uid_delete_age");
            MOVED_TO_GLOBAL.add("netstats_uid_persist_bytes");
            MOVED_TO_GLOBAL.add("netstats_uid_rotate_age");
            MOVED_TO_GLOBAL.add("netstats_uid_tag_bucket_duration");
            MOVED_TO_GLOBAL.add("netstats_uid_tag_delete_age");
            MOVED_TO_GLOBAL.add("netstats_uid_tag_persist_bytes");
            MOVED_TO_GLOBAL.add("netstats_uid_tag_rotate_age");
            MOVED_TO_GLOBAL.add("network_preference");
            MOVED_TO_GLOBAL.add("nitz_update_diff");
            MOVED_TO_GLOBAL.add("nitz_update_spacing");
            MOVED_TO_GLOBAL.add("ntp_server");
            MOVED_TO_GLOBAL.add("ntp_timeout");
            MOVED_TO_GLOBAL.add("pdp_watchdog_error_poll_count");
            MOVED_TO_GLOBAL.add("pdp_watchdog_long_poll_interval_ms");
            MOVED_TO_GLOBAL.add("pdp_watchdog_max_pdp_reset_fail_count");
            MOVED_TO_GLOBAL.add("pdp_watchdog_poll_interval_ms");
            MOVED_TO_GLOBAL.add("pdp_watchdog_trigger_packet_count");
            MOVED_TO_GLOBAL.add("sampling_profiler_ms");
            MOVED_TO_GLOBAL.add("setup_prepaid_data_service_url");
            MOVED_TO_GLOBAL.add("setup_prepaid_detection_redir_host");
            MOVED_TO_GLOBAL.add("setup_prepaid_detection_target_url");
            MOVED_TO_GLOBAL.add("tether_dun_apn");
            MOVED_TO_GLOBAL.add("tether_dun_required");
            MOVED_TO_GLOBAL.add("tether_supported");
            MOVED_TO_GLOBAL.add("usb_mass_storage_enabled");
            MOVED_TO_GLOBAL.add("use_google_mail");
            MOVED_TO_GLOBAL.add("wifi_country_code");
            MOVED_TO_GLOBAL.add("wifi_framework_scan_interval_ms");
            MOVED_TO_GLOBAL.add("wifi_frequency_band");
            MOVED_TO_GLOBAL.add("wifi_idle_ms");
            MOVED_TO_GLOBAL.add("wifi_max_dhcp_retry_count");
            MOVED_TO_GLOBAL.add("wifi_mobile_data_transition_wakelock_timeout_ms");
            MOVED_TO_GLOBAL.add("wifi_networks_available_notification_on");
            MOVED_TO_GLOBAL.add("wifi_networks_available_repeat_delay");
            MOVED_TO_GLOBAL.add("wifi_num_open_networks_kept");
            MOVED_TO_GLOBAL.add("wifi_on");
            MOVED_TO_GLOBAL.add("wifi_p2p_device_name");
            MOVED_TO_GLOBAL.add("wifi_saved_state");
            MOVED_TO_GLOBAL.add("wifi_supplicant_scan_interval_ms");
            MOVED_TO_GLOBAL.add("wifi_suspend_optimizations_enabled");
            MOVED_TO_GLOBAL.add("wifi_verbose_logging_enabled");
            MOVED_TO_GLOBAL.add("wifi_enhanced_auto_join");
            MOVED_TO_GLOBAL.add("wifi_network_show_rssi");
            MOVED_TO_GLOBAL.add("wifi_watchdog_on");
            MOVED_TO_GLOBAL.add("wifi_watchdog_poor_network_test_enabled");
            MOVED_TO_GLOBAL.add("wimax_networks_available_notification_on");
            MOVED_TO_GLOBAL.add("package_verifier_enable");
            MOVED_TO_GLOBAL.add("verifier_timeout");
            MOVED_TO_GLOBAL.add("verifier_default_response");
            MOVED_TO_GLOBAL.add("data_stall_alarm_non_aggressive_delay_in_ms");
            MOVED_TO_GLOBAL.add("data_stall_alarm_aggressive_delay_in_ms");
            MOVED_TO_GLOBAL.add("gprs_register_check_period_ms");
            MOVED_TO_GLOBAL.add("wtf_is_fatal");
            MOVED_TO_GLOBAL.add("battery_discharge_duration_threshold");
            MOVED_TO_GLOBAL.add("battery_discharge_threshold");
            MOVED_TO_GLOBAL.add("send_action_app_error");
            MOVED_TO_GLOBAL.add("dropbox_age_seconds");
            MOVED_TO_GLOBAL.add("dropbox_max_files");
            MOVED_TO_GLOBAL.add("dropbox_quota_kb");
            MOVED_TO_GLOBAL.add("dropbox_quota_percent");
            MOVED_TO_GLOBAL.add("dropbox_reserve_percent");
            MOVED_TO_GLOBAL.add("dropbox:");
            MOVED_TO_GLOBAL.add("logcat_for_");
            MOVED_TO_GLOBAL.add("sys_free_storage_log_interval");
            MOVED_TO_GLOBAL.add("disk_free_change_reporting_threshold");
            MOVED_TO_GLOBAL.add("sys_storage_threshold_percentage");
            MOVED_TO_GLOBAL.add("sys_storage_threshold_max_bytes");
            MOVED_TO_GLOBAL.add("sys_storage_full_threshold_bytes");
            MOVED_TO_GLOBAL.add("sync_max_retry_delay_in_seconds");
            MOVED_TO_GLOBAL.add("connectivity_change_delay");
            MOVED_TO_GLOBAL.add("captive_portal_mode");
            MOVED_TO_GLOBAL.add("captive_portal_detection_enabled");
            MOVED_TO_GLOBAL.add("captive_portal_server");
            MOVED_TO_GLOBAL.add("nsd_on");
            MOVED_TO_GLOBAL.add("set_install_location");
            MOVED_TO_GLOBAL.add("default_install_location");
            MOVED_TO_GLOBAL.add("inet_condition_debounce_up_delay");
            MOVED_TO_GLOBAL.add("inet_condition_debounce_down_delay");
            MOVED_TO_GLOBAL.add("read_external_storage_enforced_default");
            MOVED_TO_GLOBAL.add("http_proxy");
            MOVED_TO_GLOBAL.add("global_http_proxy_host");
            MOVED_TO_GLOBAL.add("global_http_proxy_port");
            MOVED_TO_GLOBAL.add("global_http_proxy_exclusion_list");
            MOVED_TO_GLOBAL.add("set_global_http_proxy");
            MOVED_TO_GLOBAL.add("default_dns_server");
            MOVED_TO_GLOBAL.add("preferred_network_mode");
            MOVED_TO_GLOBAL.add("webview_data_reduction_proxy_key");
            CLONE_TO_MANAGED_PROFILE = new ArraySet();
            CLONE_TO_MANAGED_PROFILE.add("accessibility_enabled");
            CLONE_TO_MANAGED_PROFILE.add("mock_location");
            CLONE_TO_MANAGED_PROFILE.add("allowed_geolocation_origins");
            CLONE_TO_MANAGED_PROFILE.add("autofill_service");
            CLONE_TO_MANAGED_PROFILE.add("default_input_method");
            CLONE_TO_MANAGED_PROFILE.add("enabled_accessibility_services");
            CLONE_TO_MANAGED_PROFILE.add("enabled_input_methods");
            CLONE_TO_MANAGED_PROFILE.add("location_mode");
            CLONE_TO_MANAGED_PROFILE.add("location_previous_mode");
            CLONE_TO_MANAGED_PROFILE.add("location_providers_allowed");
            CLONE_TO_MANAGED_PROFILE.add("selected_input_method_subtype");
            CLONE_TO_MANAGED_PROFILE.add("selected_spell_checker");
            CLONE_TO_MANAGED_PROFILE.add("selected_spell_checker_subtype");
            INSTANT_APP_SETTINGS = new ArraySet();
            INSTANT_APP_SETTINGS.add("enabled_accessibility_services");
            INSTANT_APP_SETTINGS.add("speak_password");
            INSTANT_APP_SETTINGS.add("accessibility_display_inversion_enabled");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_enabled");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_preset");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_edge_type");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_edge_color");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_locale");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_background_color");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_foreground_color");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_typeface");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_font_scale");
            INSTANT_APP_SETTINGS.add("accessibility_captioning_window_color");
            INSTANT_APP_SETTINGS.add("accessibility_display_daltonizer_enabled");
            INSTANT_APP_SETTINGS.add("accessibility_display_daltonizer");
            INSTANT_APP_SETTINGS.add("accessibility_autoclick_delay");
            INSTANT_APP_SETTINGS.add("accessibility_autoclick_enabled");
            INSTANT_APP_SETTINGS.add("accessibility_large_pointer_icon");
            INSTANT_APP_SETTINGS.add("default_input_method");
            INSTANT_APP_SETTINGS.add("enabled_input_methods");
            INSTANT_APP_SETTINGS.add("android_id");
            INSTANT_APP_SETTINGS.add("package_verifier_user_consent");
            INSTANT_APP_SETTINGS.add("mock_location");
        }

        public Secure()
        {
        }
    }

    public static class SettingNotFoundException extends AndroidException
    {

        public SettingNotFoundException(String s)
        {
            super(s);
        }
    }

    public static final class System extends NameValueTable
    {

        public static void adjustConfigurationForUser(ContentResolver contentresolver, Configuration configuration, int i, boolean flag)
        {
            String s;
            configuration.fontScale = getFloatForUser(contentresolver, "font_scale", 1.0F, i);
            if(configuration.fontScale < 0.0F)
                configuration.fontScale = 1.0F;
            s = getStringForUser(contentresolver, "system_locales", i);
            if(s == null) goto _L2; else goto _L1
_L1:
            configuration.setLocales(LocaleList.forLanguageTags(s));
_L4:
            MiuiSettings.getConfigurationForUser(contentresolver, configuration, i);
            return;
_L2:
            if(flag)
                putStringForUser(contentresolver, "system_locales", configuration.getLocales().toLanguageTags(), i);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static boolean canWrite(Context context)
        {
            return Settings.isCallingPackageAllowedToWriteSettings(context, Process.myUid(), context.getOpPackageName(), false);
        }

        public static void clearConfiguration(Configuration configuration)
        {
            configuration.fontScale = 0.0F;
            if(!configuration.userSetLocale && configuration.getLocales().isEmpty() ^ true)
                configuration.clearLocales();
        }

        public static void clearProviderForTest()
        {
            sProviderHolder.clearProviderForTest();
            sNameValueCache.clearGenerationTrackerForTest();
        }

        public static void getCloneFromParentOnValueSettings(Map map)
        {
            map.putAll(CLONE_FROM_PARENT_ON_VALUE);
        }

        public static void getCloneToManagedProfileSettings(Set set)
        {
            set.addAll(CLONE_TO_MANAGED_PROFILE);
        }

        public static void getConfiguration(ContentResolver contentresolver, Configuration configuration)
        {
            adjustConfigurationForUser(contentresolver, configuration, UserHandle.myUserId(), false);
        }

        public static float getFloat(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            return getFloatForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static float getFloat(ContentResolver contentresolver, String s, float f)
        {
            return getFloatForUser(contentresolver, s, f, UserHandle.myUserId());
        }

        public static float getFloatForUser(ContentResolver contentresolver, String s, float f, int i)
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            float f1 = f;
            if(contentresolver != null)
                try
                {
                    f1 = Float.parseFloat(contentresolver);
                }
                // Misplaced declaration of an exception variable
                catch(ContentResolver contentresolver)
                {
                    return f;
                }
            return f1;
        }

        public static float getFloatForUser(ContentResolver contentresolver, String s, int i)
            throws SettingNotFoundException
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            if(contentresolver == null)
                throw new SettingNotFoundException(s);
            float f;
            try
            {
                f = Float.parseFloat(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return f;
        }

        public static int getInt(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            return getIntForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static int getInt(ContentResolver contentresolver, String s, int i)
        {
            return getIntForUser(contentresolver, s, i, UserHandle.myUserId());
        }

        public static int getIntForUser(ContentResolver contentresolver, String s, int i)
            throws SettingNotFoundException
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            try
            {
                i = Integer.parseInt(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return i;
        }

        public static int getIntForUser(ContentResolver contentresolver, String s, int i, int j)
        {
            contentresolver = getStringForUser(contentresolver, s, j);
            j = i;
            if(contentresolver != null)
                try
                {
                    j = Integer.parseInt(contentresolver);
                }
                // Misplaced declaration of an exception variable
                catch(ContentResolver contentresolver)
                {
                    return i;
                }
            return j;
        }

        public static long getLong(ContentResolver contentresolver, String s)
            throws SettingNotFoundException
        {
            return getLongForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static long getLong(ContentResolver contentresolver, String s, long l)
        {
            return getLongForUser(contentresolver, s, l, UserHandle.myUserId());
        }

        public static long getLongForUser(ContentResolver contentresolver, String s, int i)
            throws SettingNotFoundException
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            long l;
            try
            {
                l = Long.parseLong(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch(ContentResolver contentresolver)
            {
                throw new SettingNotFoundException(s);
            }
            return l;
        }

        public static long getLongForUser(ContentResolver contentresolver, String s, long l, int i)
        {
            contentresolver = getStringForUser(contentresolver, s, i);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_21;
            long l1 = Long.parseLong(contentresolver);
            l = l1;
_L2:
            return l;
            contentresolver;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public static void getMovedToGlobalSettings(Set set)
        {
            set.addAll(MOVED_TO_GLOBAL);
            set.addAll(MOVED_TO_SECURE_THEN_GLOBAL);
        }

        public static void getMovedToSecureSettings(Set set)
        {
            set.addAll(MOVED_TO_SECURE);
        }

        public static void getNonLegacyMovedKeys(HashSet hashset)
        {
            hashset.addAll(MOVED_TO_GLOBAL);
        }

        public static boolean getShowGTalkServiceStatus(ContentResolver contentresolver)
        {
            return getShowGTalkServiceStatusForUser(contentresolver, UserHandle.myUserId());
        }

        public static boolean getShowGTalkServiceStatusForUser(ContentResolver contentresolver, int i)
        {
            boolean flag = false;
            if(getIntForUser(contentresolver, "SHOW_GTALK_SERVICE_STATUS", 0, i) != 0)
                flag = true;
            return flag;
        }

        public static String getString(ContentResolver contentresolver, String s)
        {
            return getStringForUser(contentresolver, s, UserHandle.myUserId());
        }

        public static String getStringForUser(ContentResolver contentresolver, String s, int i)
        {
            SeempLog.record(SeempLog.getSeempGetApiIdFromValue(s));
            if(MOVED_TO_SECURE.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.System").append(" to android.provider.Settings.Secure, returning read-only value.").toString());
                return Secure.getStringForUser(contentresolver, s, i);
            }
            if(MOVED_TO_GLOBAL.contains(s) || MOVED_TO_SECURE_THEN_GLOBAL.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.System").append(" to android.provider.Settings.Global, returning read-only value.").toString());
                return Global.getStringForUser(contentresolver, s, i);
            } else
            {
                return sNameValueCache.getStringForUser(contentresolver, s, i);
            }
        }

        public static Uri getUriFor(String s)
        {
            if(MOVED_TO_SECURE.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.System").append(" to android.provider.Settings.Secure, returning Secure URI.").toString());
                return Secure.getUriFor(Secure.CONTENT_URI, s);
            }
            if(MOVED_TO_GLOBAL.contains(s) || MOVED_TO_SECURE_THEN_GLOBAL.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.System").append(" to android.provider.Settings.Global, returning read-only global URI.").toString());
                return Global.getUriFor(Global.CONTENT_URI, s);
            } else
            {
                return getUriFor(CONTENT_URI, s);
            }
        }

        public static boolean hasInterestingConfigurationChanges(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if((0x40000000 & i) == 0)
                if((i & 4) != 0)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public static boolean putConfiguration(ContentResolver contentresolver, Configuration configuration)
        {
            return putConfigurationForUser(contentresolver, configuration, UserHandle.myUserId());
        }

        public static boolean putConfigurationForUser(ContentResolver contentresolver, Configuration configuration, int i)
        {
            boolean flag;
            if(putFloatForUser(contentresolver, "font_scale", configuration.fontScale, i))
                flag = putStringForUser(contentresolver, "system_locales", configuration.getLocales().toLanguageTags(), i);
            else
                flag = false;
            return flag;
        }

        public static boolean putFloat(ContentResolver contentresolver, String s, float f)
        {
            return putFloatForUser(contentresolver, s, f, UserHandle.myUserId());
        }

        public static boolean putFloatForUser(ContentResolver contentresolver, String s, float f, int i)
        {
            return putStringForUser(contentresolver, s, Float.toString(f), i);
        }

        public static boolean putInt(ContentResolver contentresolver, String s, int i)
        {
            return putIntForUser(contentresolver, s, i, UserHandle.myUserId());
        }

        public static boolean putIntForUser(ContentResolver contentresolver, String s, int i, int j)
        {
            return putStringForUser(contentresolver, s, Integer.toString(i), j);
        }

        public static boolean putLong(ContentResolver contentresolver, String s, long l)
        {
            return putLongForUser(contentresolver, s, l, UserHandle.myUserId());
        }

        public static boolean putLongForUser(ContentResolver contentresolver, String s, long l, int i)
        {
            return putStringForUser(contentresolver, s, Long.toString(l), i);
        }

        public static boolean putString(ContentResolver contentresolver, String s, String s1)
        {
            return putStringForUser(contentresolver, s, s1, UserHandle.myUserId());
        }

        public static boolean putStringForUser(ContentResolver contentresolver, String s, String s1, int i)
        {
            SeempLog.record(SeempLog.getSeempPutApiIdFromValue(s));
            if(MOVED_TO_SECURE.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.System").append(" to android.provider.Settings.Secure, value is unchanged.").toString());
                return false;
            }
            if(MOVED_TO_GLOBAL.contains(s) || MOVED_TO_SECURE_THEN_GLOBAL.contains(s))
            {
                Log.w("Settings", (new StringBuilder()).append("Setting ").append(s).append(" has moved from android.provider.Settings.System").append(" to android.provider.Settings.Global, value is unchanged.").toString());
                return false;
            } else
            {
                return sNameValueCache.putStringForUser(contentresolver, s, s1, null, false, i);
            }
        }

        public static void setShowGTalkServiceStatus(ContentResolver contentresolver, boolean flag)
        {
            setShowGTalkServiceStatusForUser(contentresolver, flag, UserHandle.myUserId());
        }

        public static void setShowGTalkServiceStatusForUser(ContentResolver contentresolver, boolean flag, int i)
        {
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            putIntForUser(contentresolver, "SHOW_GTALK_SERVICE_STATUS", j, i);
        }

        public static final String ACCELEROMETER_ROTATION = "accelerometer_rotation";
        public static final Validator ACCELEROMETER_ROTATION_VALIDATOR;
        public static final String ADB_ENABLED = "adb_enabled";
        public static final String ADVANCED_SETTINGS = "advanced_settings";
        public static final int ADVANCED_SETTINGS_DEFAULT = 0;
        private static final Validator ADVANCED_SETTINGS_VALIDATOR;
        public static final String AIRPLANE_MODE_ON = "airplane_mode_on";
        public static final String AIRPLANE_MODE_RADIOS = "airplane_mode_radios";
        public static final String AIRPLANE_MODE_TOGGLEABLE_RADIOS = "airplane_mode_toggleable_radios";
        public static final String ALARM_ALERT = "alarm_alert";
        public static final String ALARM_ALERT_CACHE = "alarm_alert_cache";
        public static final Uri ALARM_ALERT_CACHE_URI = getUriFor("alarm_alert_cache");
        private static final Validator ALARM_ALERT_VALIDATOR;
        public static final String ALWAYS_FINISH_ACTIVITIES = "always_finish_activities";
        public static final String ANDROID_ID = "android_id";
        public static final String ANIMATOR_DURATION_SCALE = "animator_duration_scale";
        public static final String APPEND_FOR_LAST_AUDIBLE = "_last_audible";
        public static final String AUTO_TIME = "auto_time";
        public static final String AUTO_TIME_ZONE = "auto_time_zone";
        public static final String BLUETOOTH_DISCOVERABILITY = "bluetooth_discoverability";
        public static final String BLUETOOTH_DISCOVERABILITY_TIMEOUT = "bluetooth_discoverability_timeout";
        private static final Validator BLUETOOTH_DISCOVERABILITY_TIMEOUT_VALIDATOR;
        private static final Validator BLUETOOTH_DISCOVERABILITY_VALIDATOR;
        public static final String BLUETOOTH_ON = "bluetooth_on";
        public static final String CAR_DOCK_SOUND = "car_dock_sound";
        public static final String CAR_UNDOCK_SOUND = "car_undock_sound";
        public static final Map CLONE_FROM_PARENT_ON_VALUE;
        private static final Set CLONE_TO_MANAGED_PROFILE;
        public static final Uri CONTENT_URI;
        public static final String DATA_ROAMING = "data_roaming";
        public static final String DATE_FORMAT = "date_format";
        public static final Validator DATE_FORMAT_VALIDATOR;
        public static final String DEBUG_APP = "debug_app";
        public static final Uri DEFAULT_ALARM_ALERT_URI = getUriFor("alarm_alert");
        private static final float DEFAULT_FONT_SCALE = 1F;
        public static final Uri DEFAULT_NOTIFICATION_URI = getUriFor("notification_sound");
        public static final Uri DEFAULT_RINGTONE_URI = getUriFor("ringtone");
        public static final String DESK_DOCK_SOUND = "desk_dock_sound";
        public static final String DESK_UNDOCK_SOUND = "desk_undock_sound";
        public static final String DEVICE_PROVISIONED = "device_provisioned";
        public static final String DIM_SCREEN = "dim_screen";
        private static final Validator DIM_SCREEN_VALIDATOR;
        public static final String DISPLAY_COLOR_MODE = "display_color_mode";
        public static final String DOCK_SOUNDS_ENABLED = "dock_sounds_enabled";
        public static final String DTMF_TONE_TYPE_WHEN_DIALING = "dtmf_tone_type";
        public static final Validator DTMF_TONE_TYPE_WHEN_DIALING_VALIDATOR;
        public static final String DTMF_TONE_WHEN_DIALING = "dtmf_tone";
        public static final Validator DTMF_TONE_WHEN_DIALING_VALIDATOR;
        public static final String EGG_MODE = "egg_mode";
        public static final Validator EGG_MODE_VALIDATOR;
        public static final String END_BUTTON_BEHAVIOR = "end_button_behavior";
        public static final int END_BUTTON_BEHAVIOR_DEFAULT = 2;
        public static final int END_BUTTON_BEHAVIOR_HOME = 1;
        public static final int END_BUTTON_BEHAVIOR_SLEEP = 2;
        private static final Validator END_BUTTON_BEHAVIOR_VALIDATOR;
        public static final String FONT_SCALE = "font_scale";
        private static final Validator FONT_SCALE_VALIDATOR;
        public static final String HAPTIC_FEEDBACK_ENABLED = "haptic_feedback_enabled";
        public static final Validator HAPTIC_FEEDBACK_ENABLED_VALIDATOR;
        public static final String HEARING_AID = "hearing_aid";
        public static final Validator HEARING_AID_VALIDATOR;
        public static final String HIDE_ROTATION_LOCK_TOGGLE_FOR_ACCESSIBILITY = "hide_rotation_lock_toggle_for_accessibility";
        public static final Validator HIDE_ROTATION_LOCK_TOGGLE_FOR_ACCESSIBILITY_VALIDATOR;
        public static final String HTTP_PROXY = "http_proxy";
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";
        public static final Set INSTANT_APP_SETTINGS;
        public static final String LEGACY_RESTORE_SETTINGS[] = new String[0];
        public static final String LOCATION_PROVIDERS_ALLOWED = "location_providers_allowed";
        public static final String LOCKSCREEN_DISABLED = "lockscreen.disabled";
        public static final Validator LOCKSCREEN_DISABLED_VALIDATOR;
        public static final String LOCKSCREEN_SOUNDS_ENABLED = "lockscreen_sounds_enabled";
        public static final Validator LOCKSCREEN_SOUNDS_ENABLED_VALIDATOR;
        public static final String LOCK_PATTERN_ENABLED = "lock_pattern_autolock";
        public static final String LOCK_PATTERN_TACTILE_FEEDBACK_ENABLED = "lock_pattern_tactile_feedback_enabled";
        public static final String LOCK_PATTERN_VISIBLE = "lock_pattern_visible_pattern";
        public static final String LOCK_SOUND = "lock_sound";
        public static final String LOCK_TO_APP_ENABLED = "lock_to_app_enabled";
        public static final Validator LOCK_TO_APP_ENABLED_VALIDATOR;
        public static final String LOGGING_ID = "logging_id";
        public static final String LOW_BATTERY_SOUND = "low_battery_sound";
        public static final String MASTER_MONO = "master_mono";
        private static final Validator MASTER_MONO_VALIDATOR;
        public static final String MEDIA_BUTTON_RECEIVER = "media_button_receiver";
        private static final Validator MEDIA_BUTTON_RECEIVER_VALIDATOR;
        public static final String MODE_RINGER = "mode_ringer";
        public static final String MODE_RINGER_STREAMS_AFFECTED = "mode_ringer_streams_affected";
        private static final Validator MODE_RINGER_STREAMS_AFFECTED_VALIDATOR;
        private static final HashSet MOVED_TO_GLOBAL;
        private static final HashSet MOVED_TO_SECURE;
        private static final HashSet MOVED_TO_SECURE_THEN_GLOBAL;
        public static final String MUTE_STREAMS_AFFECTED = "mute_streams_affected";
        private static final Validator MUTE_STREAMS_AFFECTED_VALIDATOR;
        public static final String NETWORK_PREFERENCE = "network_preference";
        public static final String NEXT_ALARM_FORMATTED = "next_alarm_formatted";
        private static final Validator NEXT_ALARM_FORMATTED_VALIDATOR;
        public static final String NOTIFICATIONS_USE_RING_VOLUME = "notifications_use_ring_volume";
        private static final Validator NOTIFICATIONS_USE_RING_VOLUME_VALIDATOR;
        public static final String NOTIFICATION_LIGHT_PULSE = "notification_light_pulse";
        public static final Validator NOTIFICATION_LIGHT_PULSE_VALIDATOR;
        public static final String NOTIFICATION_SOUND = "notification_sound";
        public static final String NOTIFICATION_SOUND_CACHE = "notification_sound_cache";
        public static final Uri NOTIFICATION_SOUND_CACHE_URI = getUriFor("notification_sound_cache");
        private static final Validator NOTIFICATION_SOUND_VALIDATOR;
        public static final String PARENTAL_CONTROL_ENABLED = "parental_control_enabled";
        public static final String PARENTAL_CONTROL_LAST_UPDATE = "parental_control_last_update";
        public static final String PARENTAL_CONTROL_REDIRECT_URL = "parental_control_redirect_url";
        public static final String POINTER_LOCATION = "pointer_location";
        public static final Validator POINTER_LOCATION_VALIDATOR;
        public static final String POINTER_SPEED = "pointer_speed";
        public static final Validator POINTER_SPEED_VALIDATOR;
        public static final String POWER_SOUNDS_ENABLED = "power_sounds_enabled";
        public static final Set PRIVATE_SETTINGS;
        public static final Set PUBLIC_SETTINGS;
        public static final String RADIO_BLUETOOTH = "bluetooth";
        public static final String RADIO_CELL = "cell";
        public static final String RADIO_NFC = "nfc";
        public static final String RADIO_WIFI = "wifi";
        public static final String RADIO_WIMAX = "wimax";
        public static final String RINGTONE = "ringtone";
        public static final String RINGTONE_CACHE = "ringtone_cache";
        public static final Uri RINGTONE_CACHE_URI = getUriFor("ringtone_cache");
        private static final Validator RINGTONE_VALIDATOR;
        public static final String SCREEN_AUTO_BRIGHTNESS_ADJ = "screen_auto_brightness_adj";
        private static final Validator SCREEN_AUTO_BRIGHTNESS_ADJ_VALIDATOR;
        public static final String SCREEN_BRIGHTNESS = "screen_brightness";
        public static final String SCREEN_BRIGHTNESS_FOR_VR = "screen_brightness_for_vr";
        private static final Validator SCREEN_BRIGHTNESS_FOR_VR_VALIDATOR;
        public static final String SCREEN_BRIGHTNESS_MODE = "screen_brightness_mode";
        public static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = 1;
        public static final int SCREEN_BRIGHTNESS_MODE_MANUAL = 0;
        private static final Validator SCREEN_BRIGHTNESS_MODE_VALIDATOR;
        private static final Validator SCREEN_BRIGHTNESS_VALIDATOR;
        public static final String SCREEN_OFF_TIMEOUT = "screen_off_timeout";
        private static final Validator SCREEN_OFF_TIMEOUT_VALIDATOR;
        public static final String SETTINGS_CLASSNAME = "settings_classname";
        public static final String SETTINGS_TO_BACKUP[] = {
            "stay_on_while_plugged_in", "wifi_use_static_ip", "wifi_static_ip", "wifi_static_gateway", "wifi_static_netmask", "wifi_static_dns1", "wifi_static_dns2", "bluetooth_discoverability", "bluetooth_discoverability_timeout", "font_scale", 
            "dim_screen", "screen_off_timeout", "screen_brightness", "screen_brightness_mode", "screen_auto_brightness_adj", "screen_brightness_for_vr", "vibrate_input_devices", "mode_ringer_streams_affected", "auto_replace", "auto_caps", 
            "auto_punctuate", "show_password", "auto_time", "auto_time_zone", "time_12_24", "date_format", "dtmf_tone", "dtmf_tone_type", "hearing_aid", "tty_mode", 
            "master_mono", "sound_effects_enabled", "haptic_feedback_enabled", "power_sounds_enabled", "dock_sounds_enabled", "lockscreen_sounds_enabled", "show_web_suggestions", "sip_call_options", "sip_receive_calls", "pointer_speed", 
            "vibrate_when_ringing", "ringtone", "lock_to_app_enabled", "notification_sound", "accelerometer_rotation", "status_bar_show_battery_percent"
        };
        public static final String SETUP_WIZARD_HAS_RUN = "setup_wizard_has_run";
        public static final Validator SETUP_WIZARD_HAS_RUN_VALIDATOR;
        public static final String SHOW_BATTERY_PERCENT = "status_bar_show_battery_percent";
        private static final Validator SHOW_BATTERY_PERCENT_VALIDATOR;
        public static final String SHOW_GTALK_SERVICE_STATUS = "SHOW_GTALK_SERVICE_STATUS";
        private static final Validator SHOW_GTALK_SERVICE_STATUS_VALIDATOR;
        public static final String SHOW_PROCESSES = "show_processes";
        public static final String SHOW_TOUCHES = "show_touches";
        public static final Validator SHOW_TOUCHES_VALIDATOR;
        public static final String SHOW_WEB_SUGGESTIONS = "show_web_suggestions";
        public static final Validator SHOW_WEB_SUGGESTIONS_VALIDATOR;
        public static final String SIP_ADDRESS_ONLY = "SIP_ADDRESS_ONLY";
        public static final Validator SIP_ADDRESS_ONLY_VALIDATOR;
        public static final String SIP_ALWAYS = "SIP_ALWAYS";
        public static final Validator SIP_ALWAYS_VALIDATOR;
        public static final String SIP_ASK_ME_EACH_TIME = "SIP_ASK_ME_EACH_TIME";
        public static final Validator SIP_ASK_ME_EACH_TIME_VALIDATOR;
        public static final String SIP_CALL_OPTIONS = "sip_call_options";
        public static final Validator SIP_CALL_OPTIONS_VALIDATOR;
        public static final String SIP_RECEIVE_CALLS = "sip_receive_calls";
        public static final Validator SIP_RECEIVE_CALLS_VALIDATOR;
        public static final String SOUND_EFFECTS_ENABLED = "sound_effects_enabled";
        public static final Validator SOUND_EFFECTS_ENABLED_VALIDATOR;
        public static final String STAY_ON_WHILE_PLUGGED_IN = "stay_on_while_plugged_in";
        public static final String SYSTEM_LOCALES = "system_locales";
        public static final String TEXT_AUTO_CAPS = "auto_caps";
        private static final Validator TEXT_AUTO_CAPS_VALIDATOR;
        public static final String TEXT_AUTO_PUNCTUATE = "auto_punctuate";
        private static final Validator TEXT_AUTO_PUNCTUATE_VALIDATOR;
        public static final String TEXT_AUTO_REPLACE = "auto_replace";
        private static final Validator TEXT_AUTO_REPLACE_VALIDATOR;
        public static final String TEXT_SHOW_PASSWORD = "show_password";
        private static final Validator TEXT_SHOW_PASSWORD_VALIDATOR;
        public static final String TIME_12_24 = "time_12_24";
        public static final Validator TIME_12_24_VALIDATOR;
        public static final String TRANSITION_ANIMATION_SCALE = "transition_animation_scale";
        public static final String TTY_MODE = "tty_mode";
        public static final Validator TTY_MODE_VALIDATOR;
        public static final String UNLOCK_SOUND = "unlock_sound";
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";
        public static final String USER_ROTATION = "user_rotation";
        public static final Validator USER_ROTATION_VALIDATOR;
        public static final String USE_GOOGLE_MAIL = "use_google_mail";
        public static final Map VALIDATORS;
        public static final String VIBRATE_INPUT_DEVICES = "vibrate_input_devices";
        private static final Validator VIBRATE_INPUT_DEVICES_VALIDATOR;
        public static final String VIBRATE_IN_SILENT = "vibrate_in_silent";
        private static final Validator VIBRATE_IN_SILENT_VALIDATOR;
        public static final String VIBRATE_ON = "vibrate_on";
        private static final Validator VIBRATE_ON_VALIDATOR;
        public static final String VIBRATE_WHEN_RINGING = "vibrate_when_ringing";
        public static final Validator VIBRATE_WHEN_RINGING_VALIDATOR;
        public static final String VOLUME_ACCESSIBILITY = "volume_a11y";
        public static final String VOLUME_ALARM = "volume_alarm";
        public static final String VOLUME_BLUETOOTH_SCO = "volume_bluetooth_sco";
        public static final String VOLUME_MASTER = "volume_master";
        public static final String VOLUME_MUSIC = "volume_music";
        public static final String VOLUME_NOTIFICATION = "volume_notification";
        public static final String VOLUME_RING = "volume_ring";
        public static final String VOLUME_SETTINGS[] = {
            "volume_voice", "volume_system", "volume_ring", "volume_music", "volume_alarm", "volume_notification", "volume_bluetooth_sco"
        };
        public static final String VOLUME_SETTINGS_INT[] = {
            "volume_voice", "volume_system", "volume_ring", "volume_music", "volume_alarm", "volume_notification", "volume_bluetooth_sco", "", "", "", 
            "volume_a11y"
        };
        public static final String VOLUME_SYSTEM = "volume_system";
        public static final String VOLUME_VOICE = "volume_voice";
        public static final String WAIT_FOR_DEBUGGER = "wait_for_debugger";
        public static final String WALLPAPER_ACTIVITY = "wallpaper_activity";
        private static final Validator WALLPAPER_ACTIVITY_VALIDATOR;
        public static final String WHEN_TO_MAKE_WIFI_CALLS = "when_to_make_wifi_calls";
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";
        public static final String WIFI_ON = "wifi_on";
        public static final String WIFI_SLEEP_POLICY = "wifi_sleep_policy";
        public static final int WIFI_SLEEP_POLICY_DEFAULT = 0;
        public static final int WIFI_SLEEP_POLICY_NEVER = 2;
        public static final int WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED = 1;
        public static final String WIFI_STATIC_DNS1 = "wifi_static_dns1";
        private static final Validator WIFI_STATIC_DNS1_VALIDATOR;
        public static final String WIFI_STATIC_DNS2 = "wifi_static_dns2";
        private static final Validator WIFI_STATIC_DNS2_VALIDATOR;
        public static final String WIFI_STATIC_GATEWAY = "wifi_static_gateway";
        private static final Validator WIFI_STATIC_GATEWAY_VALIDATOR;
        public static final String WIFI_STATIC_IP = "wifi_static_ip";
        private static final Validator WIFI_STATIC_IP_VALIDATOR;
        public static final String WIFI_STATIC_NETMASK = "wifi_static_netmask";
        private static final Validator WIFI_STATIC_NETMASK_VALIDATOR;
        public static final String WIFI_USE_STATIC_IP = "wifi_use_static_ip";
        private static final Validator WIFI_USE_STATIC_IP_VALIDATOR;
        public static final String WIFI_WATCHDOG_ACCEPTABLE_PACKET_LOSS_PERCENTAGE = "wifi_watchdog_acceptable_packet_loss_percentage";
        public static final String WIFI_WATCHDOG_AP_COUNT = "wifi_watchdog_ap_count";
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_DELAY_MS = "wifi_watchdog_background_check_delay_ms";
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_ENABLED = "wifi_watchdog_background_check_enabled";
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS = "wifi_watchdog_background_check_timeout_ms";
        public static final String WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT = "wifi_watchdog_initial_ignored_ping_count";
        public static final String WIFI_WATCHDOG_MAX_AP_CHECKS = "wifi_watchdog_max_ap_checks";
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";
        public static final String WIFI_WATCHDOG_PING_COUNT = "wifi_watchdog_ping_count";
        public static final String WIFI_WATCHDOG_PING_DELAY_MS = "wifi_watchdog_ping_delay_ms";
        public static final String WIFI_WATCHDOG_PING_TIMEOUT_MS = "wifi_watchdog_ping_timeout_ms";
        public static final String WINDOW_ANIMATION_SCALE = "window_animation_scale";
        public static final String WINDOW_ORIENTATION_LISTENER_LOG = "window_orientation_listener_log";
        public static final Validator WINDOW_ORIENTATION_LISTENER_LOG_VALIDATOR;
        private static final Validator sBooleanValidator;
        private static final Validator sLenientIpAddressValidator;
        private static final NameValueCache sNameValueCache;
        private static final Validator sNonNegativeIntegerValidator;
        private static final ContentProviderHolder sProviderHolder;
        private static final Validator sUriValidator;

        static 
        {
            CONTENT_URI = Uri.parse("content://settings/system");
            sProviderHolder = new ContentProviderHolder(CONTENT_URI);
            sNameValueCache = new NameValueCache(CONTENT_URI, "GET_system", "PUT_system", sProviderHolder);
            MOVED_TO_SECURE = new HashSet(30);
            MOVED_TO_SECURE.add("android_id");
            MOVED_TO_SECURE.add("http_proxy");
            MOVED_TO_SECURE.add("location_providers_allowed");
            MOVED_TO_SECURE.add("lock_biometric_weak_flags");
            MOVED_TO_SECURE.add("lock_pattern_autolock");
            MOVED_TO_SECURE.add("lock_pattern_visible_pattern");
            MOVED_TO_SECURE.add("lock_pattern_tactile_feedback_enabled");
            MOVED_TO_SECURE.add("logging_id");
            MOVED_TO_SECURE.add("parental_control_enabled");
            MOVED_TO_SECURE.add("parental_control_last_update");
            MOVED_TO_SECURE.add("parental_control_redirect_url");
            MOVED_TO_SECURE.add("settings_classname");
            MOVED_TO_SECURE.add("use_google_mail");
            MOVED_TO_SECURE.add("wifi_networks_available_notification_on");
            MOVED_TO_SECURE.add("wifi_networks_available_repeat_delay");
            MOVED_TO_SECURE.add("wifi_num_open_networks_kept");
            MOVED_TO_SECURE.add("wifi_on");
            MOVED_TO_SECURE.add("wifi_watchdog_acceptable_packet_loss_percentage");
            MOVED_TO_SECURE.add("wifi_watchdog_ap_count");
            MOVED_TO_SECURE.add("wifi_watchdog_background_check_delay_ms");
            MOVED_TO_SECURE.add("wifi_watchdog_background_check_enabled");
            MOVED_TO_SECURE.add("wifi_watchdog_background_check_timeout_ms");
            MOVED_TO_SECURE.add("wifi_watchdog_initial_ignored_ping_count");
            MOVED_TO_SECURE.add("wifi_watchdog_max_ap_checks");
            MOVED_TO_SECURE.add("wifi_watchdog_on");
            MOVED_TO_SECURE.add("wifi_watchdog_ping_count");
            MOVED_TO_SECURE.add("wifi_watchdog_ping_delay_ms");
            MOVED_TO_SECURE.add("wifi_watchdog_ping_timeout_ms");
            MOVED_TO_SECURE.add("install_non_market_apps");
            MOVED_TO_GLOBAL = new HashSet();
            MOVED_TO_SECURE_THEN_GLOBAL = new HashSet();
            MOVED_TO_SECURE_THEN_GLOBAL.add("adb_enabled");
            MOVED_TO_SECURE_THEN_GLOBAL.add("bluetooth_on");
            MOVED_TO_SECURE_THEN_GLOBAL.add("data_roaming");
            MOVED_TO_SECURE_THEN_GLOBAL.add("device_provisioned");
            MOVED_TO_SECURE_THEN_GLOBAL.add("usb_mass_storage_enabled");
            MOVED_TO_SECURE_THEN_GLOBAL.add("http_proxy");
            MOVED_TO_GLOBAL.add("airplane_mode_on");
            MOVED_TO_GLOBAL.add("airplane_mode_radios");
            MOVED_TO_GLOBAL.add("airplane_mode_toggleable_radios");
            MOVED_TO_GLOBAL.add("auto_time");
            MOVED_TO_GLOBAL.add("auto_time_zone");
            MOVED_TO_GLOBAL.add("car_dock_sound");
            MOVED_TO_GLOBAL.add("car_undock_sound");
            MOVED_TO_GLOBAL.add("desk_dock_sound");
            MOVED_TO_GLOBAL.add("desk_undock_sound");
            MOVED_TO_GLOBAL.add("dock_sounds_enabled");
            MOVED_TO_GLOBAL.add("lock_sound");
            MOVED_TO_GLOBAL.add("unlock_sound");
            MOVED_TO_GLOBAL.add("low_battery_sound");
            MOVED_TO_GLOBAL.add("power_sounds_enabled");
            MOVED_TO_GLOBAL.add("stay_on_while_plugged_in");
            MOVED_TO_GLOBAL.add("wifi_sleep_policy");
            MOVED_TO_GLOBAL.add("mode_ringer");
            MOVED_TO_GLOBAL.add("window_animation_scale");
            MOVED_TO_GLOBAL.add("transition_animation_scale");
            MOVED_TO_GLOBAL.add("animator_duration_scale");
            MOVED_TO_GLOBAL.add("fancy_ime_animations");
            MOVED_TO_GLOBAL.add("compatibility_mode");
            MOVED_TO_GLOBAL.add("emergency_tone");
            MOVED_TO_GLOBAL.add("call_auto_retry");
            MOVED_TO_GLOBAL.add("debug_app");
            MOVED_TO_GLOBAL.add("wait_for_debugger");
            MOVED_TO_GLOBAL.add("always_finish_activities");
            MOVED_TO_GLOBAL.add("tzinfo_content_url");
            MOVED_TO_GLOBAL.add("tzinfo_metadata_url");
            MOVED_TO_GLOBAL.add("selinux_content_url");
            MOVED_TO_GLOBAL.add("selinux_metadata_url");
            MOVED_TO_GLOBAL.add("sms_short_codes_content_url");
            MOVED_TO_GLOBAL.add("sms_short_codes_metadata_url");
            MOVED_TO_GLOBAL.add("cert_pin_content_url");
            MOVED_TO_GLOBAL.add("cert_pin_metadata_url");
            sBooleanValidator = new DiscreteValueValidator(new String[] {
                "0", "1"
            });
            sNonNegativeIntegerValidator = new System.Validator() {

                public boolean validate(String s)
                {
                    boolean flag = false;
                    int i;
                    try
                    {
                        i = Integer.parseInt(s);
                    }
                    // Misplaced declaration of an exception variable
                    catch(String s)
                    {
                        return false;
                    }
                    if(i >= 0)
                        flag = true;
                    return flag;
                }

            }
;
            sUriValidator = new System.Validator() {

                public boolean validate(String s)
                {
                    try
                    {
                        Uri.decode(s);
                    }
                    // Misplaced declaration of an exception variable
                    catch(String s)
                    {
                        return false;
                    }
                    return true;
                }

            }
;
            sLenientIpAddressValidator = new System.Validator() {

                public boolean validate(String s)
                {
                    boolean flag;
                    if(s.length() <= 45)
                        flag = true;
                    else
                        flag = false;
                    return flag;
                }

                private static final int MAX_IPV6_LENGTH = 45;

            }
;
            END_BUTTON_BEHAVIOR_VALIDATOR = new InclusiveIntegerRangeValidator(0, 3);
            ADVANCED_SETTINGS_VALIDATOR = sBooleanValidator;
            WIFI_USE_STATIC_IP_VALIDATOR = sBooleanValidator;
            WIFI_STATIC_IP_VALIDATOR = sLenientIpAddressValidator;
            WIFI_STATIC_GATEWAY_VALIDATOR = sLenientIpAddressValidator;
            WIFI_STATIC_NETMASK_VALIDATOR = sLenientIpAddressValidator;
            WIFI_STATIC_DNS1_VALIDATOR = sLenientIpAddressValidator;
            WIFI_STATIC_DNS2_VALIDATOR = sLenientIpAddressValidator;
            BLUETOOTH_DISCOVERABILITY_VALIDATOR = new InclusiveIntegerRangeValidator(0, 2);
            BLUETOOTH_DISCOVERABILITY_TIMEOUT_VALIDATOR = sNonNegativeIntegerValidator;
            NEXT_ALARM_FORMATTED_VALIDATOR = new System.Validator() {

                public boolean validate(String s)
                {
                    boolean flag = true;
                    boolean flag1 = flag;
                    if(s != null)
                        if(s.length() < 1000)
                            flag1 = flag;
                        else
                            flag1 = false;
                    return flag1;
                }

                private static final int MAX_LENGTH = 1000;

            }
;
            FONT_SCALE_VALIDATOR = new System.Validator() {

                public boolean validate(String s)
                {
                    boolean flag = false;
                    float f;
                    try
                    {
                        f = Float.parseFloat(s);
                    }
                    // Misplaced declaration of an exception variable
                    catch(String s)
                    {
                        return false;
                    }
                    if(f >= 0.0F)
                        flag = true;
                    return flag;
                }

            }
;
            DIM_SCREEN_VALIDATOR = sBooleanValidator;
            SCREEN_OFF_TIMEOUT_VALIDATOR = sNonNegativeIntegerValidator;
            SCREEN_BRIGHTNESS_VALIDATOR = new InclusiveIntegerRangeValidator(0, 255);
            SCREEN_BRIGHTNESS_FOR_VR_VALIDATOR = new InclusiveIntegerRangeValidator(0, 255);
            SCREEN_BRIGHTNESS_MODE_VALIDATOR = sBooleanValidator;
            SCREEN_AUTO_BRIGHTNESS_ADJ_VALIDATOR = new InclusiveFloatRangeValidator(-1F, 1.0F);
            MODE_RINGER_STREAMS_AFFECTED_VALIDATOR = sNonNegativeIntegerValidator;
            MUTE_STREAMS_AFFECTED_VALIDATOR = sNonNegativeIntegerValidator;
            VIBRATE_ON_VALIDATOR = sBooleanValidator;
            VIBRATE_INPUT_DEVICES_VALIDATOR = sBooleanValidator;
            MASTER_MONO_VALIDATOR = sBooleanValidator;
            NOTIFICATIONS_USE_RING_VOLUME_VALIDATOR = sBooleanValidator;
            VIBRATE_IN_SILENT_VALIDATOR = sBooleanValidator;
            RINGTONE_VALIDATOR = sUriValidator;
            NOTIFICATION_SOUND_VALIDATOR = sUriValidator;
            ALARM_ALERT_VALIDATOR = sUriValidator;
            MEDIA_BUTTON_RECEIVER_VALIDATOR = new System.Validator() {

                public boolean validate(String s)
                {
                    try
                    {
                        ComponentName.unflattenFromString(s);
                    }
                    // Misplaced declaration of an exception variable
                    catch(String s)
                    {
                        return false;
                    }
                    return true;
                }

            }
;
            TEXT_AUTO_REPLACE_VALIDATOR = sBooleanValidator;
            TEXT_AUTO_CAPS_VALIDATOR = sBooleanValidator;
            TEXT_AUTO_PUNCTUATE_VALIDATOR = sBooleanValidator;
            TEXT_SHOW_PASSWORD_VALIDATOR = sBooleanValidator;
            SHOW_GTALK_SERVICE_STATUS_VALIDATOR = sBooleanValidator;
            WALLPAPER_ACTIVITY_VALIDATOR = new System.Validator() {

                public boolean validate(String s)
                {
                    boolean flag = false;
                    if(s != null && s.length() > 1000)
                        return false;
                    if(ComponentName.unflattenFromString(s) != null)
                        flag = true;
                    return flag;
                }

                private static final int MAX_LENGTH = 1000;

            }
;
            TIME_12_24_VALIDATOR = new DiscreteValueValidator(new String[] {
                "12", "24"
            });
            DATE_FORMAT_VALIDATOR = new System.Validator() {

                public boolean validate(String s)
                {
                    try
                    {
                        new SimpleDateFormat(s);
                    }
                    // Misplaced declaration of an exception variable
                    catch(String s)
                    {
                        return false;
                    }
                    return true;
                }

            }
;
            SETUP_WIZARD_HAS_RUN_VALIDATOR = sBooleanValidator;
            ACCELEROMETER_ROTATION_VALIDATOR = sBooleanValidator;
            USER_ROTATION_VALIDATOR = new InclusiveIntegerRangeValidator(0, 3);
            HIDE_ROTATION_LOCK_TOGGLE_FOR_ACCESSIBILITY_VALIDATOR = sBooleanValidator;
            VIBRATE_WHEN_RINGING_VALIDATOR = sBooleanValidator;
            DTMF_TONE_WHEN_DIALING_VALIDATOR = sBooleanValidator;
            DTMF_TONE_TYPE_WHEN_DIALING_VALIDATOR = sBooleanValidator;
            HEARING_AID_VALIDATOR = sBooleanValidator;
            TTY_MODE_VALIDATOR = new InclusiveIntegerRangeValidator(0, 3);
            SOUND_EFFECTS_ENABLED_VALIDATOR = sBooleanValidator;
            HAPTIC_FEEDBACK_ENABLED_VALIDATOR = sBooleanValidator;
            SHOW_WEB_SUGGESTIONS_VALIDATOR = sBooleanValidator;
            NOTIFICATION_LIGHT_PULSE_VALIDATOR = sBooleanValidator;
            POINTER_LOCATION_VALIDATOR = sBooleanValidator;
            SHOW_TOUCHES_VALIDATOR = sBooleanValidator;
            WINDOW_ORIENTATION_LISTENER_LOG_VALIDATOR = sBooleanValidator;
            LOCKSCREEN_SOUNDS_ENABLED_VALIDATOR = sBooleanValidator;
            LOCKSCREEN_DISABLED_VALIDATOR = sBooleanValidator;
            SIP_RECEIVE_CALLS_VALIDATOR = sBooleanValidator;
            SIP_CALL_OPTIONS_VALIDATOR = new DiscreteValueValidator(new String[] {
                "SIP_ALWAYS", "SIP_ADDRESS_ONLY"
            });
            SIP_ALWAYS_VALIDATOR = sBooleanValidator;
            SIP_ADDRESS_ONLY_VALIDATOR = sBooleanValidator;
            SIP_ASK_ME_EACH_TIME_VALIDATOR = sBooleanValidator;
            POINTER_SPEED_VALIDATOR = new InclusiveFloatRangeValidator(-7F, 7F);
            LOCK_TO_APP_ENABLED_VALIDATOR = sBooleanValidator;
            EGG_MODE_VALIDATOR = new System.Validator() {

                public boolean validate(String s)
                {
                    boolean flag = false;
                    long l;
                    try
                    {
                        l = Long.parseLong(s);
                    }
                    // Misplaced declaration of an exception variable
                    catch(String s)
                    {
                        return false;
                    }
                    if(l >= 0L)
                        flag = true;
                    return flag;
                }

            }
;
            SHOW_BATTERY_PERCENT_VALIDATOR = sBooleanValidator;
            PUBLIC_SETTINGS = new ArraySet();
            PUBLIC_SETTINGS.add("end_button_behavior");
            PUBLIC_SETTINGS.add("wifi_use_static_ip");
            PUBLIC_SETTINGS.add("wifi_static_ip");
            PUBLIC_SETTINGS.add("wifi_static_gateway");
            PUBLIC_SETTINGS.add("wifi_static_netmask");
            PUBLIC_SETTINGS.add("wifi_static_dns1");
            PUBLIC_SETTINGS.add("wifi_static_dns2");
            PUBLIC_SETTINGS.add("bluetooth_discoverability");
            PUBLIC_SETTINGS.add("bluetooth_discoverability_timeout");
            PUBLIC_SETTINGS.add("next_alarm_formatted");
            PUBLIC_SETTINGS.add("font_scale");
            PUBLIC_SETTINGS.add("dim_screen");
            PUBLIC_SETTINGS.add("screen_off_timeout");
            PUBLIC_SETTINGS.add("screen_brightness");
            PUBLIC_SETTINGS.add("screen_brightness_mode");
            PUBLIC_SETTINGS.add("mode_ringer_streams_affected");
            PUBLIC_SETTINGS.add("mute_streams_affected");
            PUBLIC_SETTINGS.add("vibrate_on");
            PUBLIC_SETTINGS.add("volume_ring");
            PUBLIC_SETTINGS.add("volume_system");
            PUBLIC_SETTINGS.add("volume_voice");
            PUBLIC_SETTINGS.add("volume_music");
            PUBLIC_SETTINGS.add("volume_alarm");
            PUBLIC_SETTINGS.add("volume_notification");
            PUBLIC_SETTINGS.add("volume_bluetooth_sco");
            PUBLIC_SETTINGS.add("ringtone");
            PUBLIC_SETTINGS.add("notification_sound");
            PUBLIC_SETTINGS.add("alarm_alert");
            PUBLIC_SETTINGS.add("auto_replace");
            PUBLIC_SETTINGS.add("auto_caps");
            PUBLIC_SETTINGS.add("auto_punctuate");
            PUBLIC_SETTINGS.add("show_password");
            PUBLIC_SETTINGS.add("SHOW_GTALK_SERVICE_STATUS");
            PUBLIC_SETTINGS.add("wallpaper_activity");
            PUBLIC_SETTINGS.add("time_12_24");
            PUBLIC_SETTINGS.add("date_format");
            PUBLIC_SETTINGS.add("setup_wizard_has_run");
            PUBLIC_SETTINGS.add("accelerometer_rotation");
            PUBLIC_SETTINGS.add("user_rotation");
            PUBLIC_SETTINGS.add("dtmf_tone");
            PUBLIC_SETTINGS.add("sound_effects_enabled");
            PUBLIC_SETTINGS.add("haptic_feedback_enabled");
            PUBLIC_SETTINGS.add("show_web_suggestions");
            PUBLIC_SETTINGS.add("vibrate_when_ringing");
            PRIVATE_SETTINGS = new ArraySet();
            PRIVATE_SETTINGS.add("wifi_use_static_ip");
            PRIVATE_SETTINGS.add("end_button_behavior");
            PRIVATE_SETTINGS.add("advanced_settings");
            PRIVATE_SETTINGS.add("screen_auto_brightness_adj");
            PRIVATE_SETTINGS.add("vibrate_input_devices");
            PRIVATE_SETTINGS.add("volume_master");
            PRIVATE_SETTINGS.add("master_mono");
            PRIVATE_SETTINGS.add("notifications_use_ring_volume");
            PRIVATE_SETTINGS.add("vibrate_in_silent");
            PRIVATE_SETTINGS.add("media_button_receiver");
            PRIVATE_SETTINGS.add("hide_rotation_lock_toggle_for_accessibility");
            PRIVATE_SETTINGS.add("dtmf_tone_type");
            PRIVATE_SETTINGS.add("hearing_aid");
            PRIVATE_SETTINGS.add("tty_mode");
            PRIVATE_SETTINGS.add("notification_light_pulse");
            PRIVATE_SETTINGS.add("pointer_location");
            PRIVATE_SETTINGS.add("show_touches");
            PRIVATE_SETTINGS.add("window_orientation_listener_log");
            PRIVATE_SETTINGS.add("power_sounds_enabled");
            PRIVATE_SETTINGS.add("dock_sounds_enabled");
            PRIVATE_SETTINGS.add("lockscreen_sounds_enabled");
            PRIVATE_SETTINGS.add("lockscreen.disabled");
            PRIVATE_SETTINGS.add("low_battery_sound");
            PRIVATE_SETTINGS.add("desk_dock_sound");
            PRIVATE_SETTINGS.add("desk_undock_sound");
            PRIVATE_SETTINGS.add("car_dock_sound");
            PRIVATE_SETTINGS.add("car_undock_sound");
            PRIVATE_SETTINGS.add("lock_sound");
            PRIVATE_SETTINGS.add("unlock_sound");
            PRIVATE_SETTINGS.add("sip_receive_calls");
            PRIVATE_SETTINGS.add("sip_call_options");
            PRIVATE_SETTINGS.add("SIP_ALWAYS");
            PRIVATE_SETTINGS.add("SIP_ADDRESS_ONLY");
            PRIVATE_SETTINGS.add("SIP_ASK_ME_EACH_TIME");
            PRIVATE_SETTINGS.add("pointer_speed");
            PRIVATE_SETTINGS.add("lock_to_app_enabled");
            PRIVATE_SETTINGS.add("egg_mode");
            PRIVATE_SETTINGS.add("status_bar_show_battery_percent");
            VALIDATORS = new ArrayMap();
            VALIDATORS.put("end_button_behavior", END_BUTTON_BEHAVIOR_VALIDATOR);
            VALIDATORS.put("wifi_use_static_ip", WIFI_USE_STATIC_IP_VALIDATOR);
            VALIDATORS.put("bluetooth_discoverability", BLUETOOTH_DISCOVERABILITY_VALIDATOR);
            VALIDATORS.put("bluetooth_discoverability_timeout", BLUETOOTH_DISCOVERABILITY_TIMEOUT_VALIDATOR);
            VALIDATORS.put("next_alarm_formatted", NEXT_ALARM_FORMATTED_VALIDATOR);
            VALIDATORS.put("font_scale", FONT_SCALE_VALIDATOR);
            VALIDATORS.put("dim_screen", DIM_SCREEN_VALIDATOR);
            VALIDATORS.put("screen_off_timeout", SCREEN_OFF_TIMEOUT_VALIDATOR);
            VALIDATORS.put("screen_brightness", SCREEN_BRIGHTNESS_VALIDATOR);
            VALIDATORS.put("screen_brightness_for_vr", SCREEN_BRIGHTNESS_FOR_VR_VALIDATOR);
            VALIDATORS.put("screen_brightness_mode", SCREEN_BRIGHTNESS_MODE_VALIDATOR);
            VALIDATORS.put("mode_ringer_streams_affected", MODE_RINGER_STREAMS_AFFECTED_VALIDATOR);
            VALIDATORS.put("mute_streams_affected", MUTE_STREAMS_AFFECTED_VALIDATOR);
            VALIDATORS.put("vibrate_on", VIBRATE_ON_VALIDATOR);
            VALIDATORS.put("ringtone", RINGTONE_VALIDATOR);
            VALIDATORS.put("notification_sound", NOTIFICATION_SOUND_VALIDATOR);
            VALIDATORS.put("alarm_alert", ALARM_ALERT_VALIDATOR);
            VALIDATORS.put("auto_replace", TEXT_AUTO_REPLACE_VALIDATOR);
            VALIDATORS.put("auto_caps", TEXT_AUTO_CAPS_VALIDATOR);
            VALIDATORS.put("auto_punctuate", TEXT_AUTO_PUNCTUATE_VALIDATOR);
            VALIDATORS.put("show_password", TEXT_SHOW_PASSWORD_VALIDATOR);
            VALIDATORS.put("SHOW_GTALK_SERVICE_STATUS", SHOW_GTALK_SERVICE_STATUS_VALIDATOR);
            VALIDATORS.put("wallpaper_activity", WALLPAPER_ACTIVITY_VALIDATOR);
            VALIDATORS.put("time_12_24", TIME_12_24_VALIDATOR);
            VALIDATORS.put("date_format", DATE_FORMAT_VALIDATOR);
            VALIDATORS.put("setup_wizard_has_run", SETUP_WIZARD_HAS_RUN_VALIDATOR);
            VALIDATORS.put("accelerometer_rotation", ACCELEROMETER_ROTATION_VALIDATOR);
            VALIDATORS.put("user_rotation", USER_ROTATION_VALIDATOR);
            VALIDATORS.put("dtmf_tone", DTMF_TONE_WHEN_DIALING_VALIDATOR);
            VALIDATORS.put("sound_effects_enabled", SOUND_EFFECTS_ENABLED_VALIDATOR);
            VALIDATORS.put("haptic_feedback_enabled", HAPTIC_FEEDBACK_ENABLED_VALIDATOR);
            VALIDATORS.put("show_web_suggestions", SHOW_WEB_SUGGESTIONS_VALIDATOR);
            VALIDATORS.put("wifi_use_static_ip", WIFI_USE_STATIC_IP_VALIDATOR);
            VALIDATORS.put("end_button_behavior", END_BUTTON_BEHAVIOR_VALIDATOR);
            VALIDATORS.put("advanced_settings", ADVANCED_SETTINGS_VALIDATOR);
            VALIDATORS.put("screen_auto_brightness_adj", SCREEN_AUTO_BRIGHTNESS_ADJ_VALIDATOR);
            VALIDATORS.put("vibrate_input_devices", VIBRATE_INPUT_DEVICES_VALIDATOR);
            VALIDATORS.put("master_mono", MASTER_MONO_VALIDATOR);
            VALIDATORS.put("notifications_use_ring_volume", NOTIFICATIONS_USE_RING_VOLUME_VALIDATOR);
            VALIDATORS.put("vibrate_in_silent", VIBRATE_IN_SILENT_VALIDATOR);
            VALIDATORS.put("media_button_receiver", MEDIA_BUTTON_RECEIVER_VALIDATOR);
            VALIDATORS.put("hide_rotation_lock_toggle_for_accessibility", HIDE_ROTATION_LOCK_TOGGLE_FOR_ACCESSIBILITY_VALIDATOR);
            VALIDATORS.put("vibrate_when_ringing", VIBRATE_WHEN_RINGING_VALIDATOR);
            VALIDATORS.put("dtmf_tone_type", DTMF_TONE_TYPE_WHEN_DIALING_VALIDATOR);
            VALIDATORS.put("hearing_aid", HEARING_AID_VALIDATOR);
            VALIDATORS.put("tty_mode", TTY_MODE_VALIDATOR);
            VALIDATORS.put("notification_light_pulse", NOTIFICATION_LIGHT_PULSE_VALIDATOR);
            VALIDATORS.put("pointer_location", POINTER_LOCATION_VALIDATOR);
            VALIDATORS.put("show_touches", SHOW_TOUCHES_VALIDATOR);
            VALIDATORS.put("window_orientation_listener_log", WINDOW_ORIENTATION_LISTENER_LOG_VALIDATOR);
            VALIDATORS.put("lockscreen_sounds_enabled", LOCKSCREEN_SOUNDS_ENABLED_VALIDATOR);
            VALIDATORS.put("lockscreen.disabled", LOCKSCREEN_DISABLED_VALIDATOR);
            VALIDATORS.put("sip_receive_calls", SIP_RECEIVE_CALLS_VALIDATOR);
            VALIDATORS.put("sip_call_options", SIP_CALL_OPTIONS_VALIDATOR);
            VALIDATORS.put("SIP_ALWAYS", SIP_ALWAYS_VALIDATOR);
            VALIDATORS.put("SIP_ADDRESS_ONLY", SIP_ADDRESS_ONLY_VALIDATOR);
            VALIDATORS.put("SIP_ASK_ME_EACH_TIME", SIP_ASK_ME_EACH_TIME_VALIDATOR);
            VALIDATORS.put("pointer_speed", POINTER_SPEED_VALIDATOR);
            VALIDATORS.put("lock_to_app_enabled", LOCK_TO_APP_ENABLED_VALIDATOR);
            VALIDATORS.put("egg_mode", EGG_MODE_VALIDATOR);
            VALIDATORS.put("wifi_static_ip", WIFI_STATIC_IP_VALIDATOR);
            VALIDATORS.put("wifi_static_gateway", WIFI_STATIC_GATEWAY_VALIDATOR);
            VALIDATORS.put("wifi_static_netmask", WIFI_STATIC_NETMASK_VALIDATOR);
            VALIDATORS.put("wifi_static_dns1", WIFI_STATIC_DNS1_VALIDATOR);
            VALIDATORS.put("wifi_static_dns2", WIFI_STATIC_DNS2_VALIDATOR);
            VALIDATORS.put("status_bar_show_battery_percent", SHOW_BATTERY_PERCENT_VALIDATOR);
            CLONE_TO_MANAGED_PROFILE = new ArraySet();
            CLONE_TO_MANAGED_PROFILE.add("date_format");
            CLONE_TO_MANAGED_PROFILE.add("haptic_feedback_enabled");
            CLONE_TO_MANAGED_PROFILE.add("sound_effects_enabled");
            CLONE_TO_MANAGED_PROFILE.add("show_password");
            CLONE_TO_MANAGED_PROFILE.add("time_12_24");
            CLONE_FROM_PARENT_ON_VALUE = new ArrayMap();
            CLONE_FROM_PARENT_ON_VALUE.put("ringtone", "sync_parent_sounds");
            CLONE_FROM_PARENT_ON_VALUE.put("notification_sound", "sync_parent_sounds");
            CLONE_FROM_PARENT_ON_VALUE.put("alarm_alert", "sync_parent_sounds");
            INSTANT_APP_SETTINGS = new ArraySet();
            INSTANT_APP_SETTINGS.add("auto_replace");
            INSTANT_APP_SETTINGS.add("auto_caps");
            INSTANT_APP_SETTINGS.add("auto_punctuate");
            INSTANT_APP_SETTINGS.add("show_password");
            INSTANT_APP_SETTINGS.add("date_format");
            INSTANT_APP_SETTINGS.add("font_scale");
            INSTANT_APP_SETTINGS.add("haptic_feedback_enabled");
            INSTANT_APP_SETTINGS.add("time_12_24");
            INSTANT_APP_SETTINGS.add("sound_effects_enabled");
            INSTANT_APP_SETTINGS.add("accelerometer_rotation");
        }

        public System()
        {
        }
    }

    private static final class System.DiscreteValueValidator
        implements System.Validator
    {

        public boolean validate(String s)
        {
            return ArrayUtils.contains(mValues, s);
        }

        private final String mValues[];

        public System.DiscreteValueValidator(String as[])
        {
            mValues = as;
        }
    }

    private static final class System.InclusiveFloatRangeValidator
        implements System.Validator
    {

        public boolean validate(String s)
        {
            boolean flag = false;
            float f;
            boolean flag1;
            float f1;
            try
            {
                f = Float.parseFloat(s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return false;
            }
            flag1 = flag;
            if(f < mMin)
                break MISSING_BLOCK_LABEL_38;
            f1 = mMax;
            flag1 = flag;
            if(f <= f1)
                flag1 = true;
            return flag1;
        }

        private final float mMax;
        private final float mMin;

        public System.InclusiveFloatRangeValidator(float f, float f1)
        {
            mMin = f;
            mMax = f1;
        }
    }

    private static final class System.InclusiveIntegerRangeValidator
        implements System.Validator
    {

        public boolean validate(String s)
        {
            boolean flag = false;
            int i;
            boolean flag1;
            int j;
            try
            {
                i = Integer.parseInt(s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return false;
            }
            flag1 = flag;
            if(i < mMin)
                break MISSING_BLOCK_LABEL_36;
            j = mMax;
            flag1 = flag;
            if(i <= j)
                flag1 = true;
            return flag1;
        }

        private final int mMax;
        private final int mMin;

        public System.InclusiveIntegerRangeValidator(int i, int j)
        {
            mMin = i;
            mMax = j;
        }
    }

    public static interface System.Validator
    {

        public abstract boolean validate(String s);
    }


    static Object _2D_get0()
    {
        return mLocationSettingsLock;
    }

    public Settings()
    {
    }

    public static boolean canDrawOverlays(Context context)
    {
        return isCallingPackageAllowedToDrawOverlays(context, Process.myUid(), context.getOpPackageName(), false);
    }

    public static boolean checkAndNoteChangeNetworkStateOperation(Context context, int i, String s, boolean flag)
    {
        if(context.checkCallingOrSelfPermission("android.permission.CHANGE_NETWORK_STATE") == 0)
            return true;
        else
            return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, s, flag, 23, PM_CHANGE_NETWORK_STATE, true);
    }

    public static boolean checkAndNoteDrawOverlaysOperation(Context context, int i, String s, boolean flag)
    {
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, s, flag, 24, PM_SYSTEM_ALERT_WINDOW, true);
    }

    public static boolean checkAndNoteWriteSettingsOperation(Context context, int i, String s, boolean flag)
    {
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, s, flag, 23, PM_WRITE_SETTINGS, true);
    }

    public static String getGTalkDeviceId(long l)
    {
        return (new StringBuilder()).append("android-").append(Long.toHexString(l)).toString();
    }

    public static String getPackageNameForUid(Context context, int i)
    {
        context = context.getPackageManager().getPackagesForUid(i);
        if(context == null)
            return null;
        if(i == 1000)
            return "android";
        else
            return context[0];
    }

    public static boolean isCallingPackageAllowedToDrawOverlays(Context context, int i, String s, boolean flag)
    {
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, s, flag, 24, PM_SYSTEM_ALERT_WINDOW, false);
    }

    public static boolean isCallingPackageAllowedToPerformAppOpsProtectedOperation(Context context, int i, String s, boolean flag, int j, String as[], boolean flag1)
    {
        if(s == null)
            return false;
        AppOpsManager appopsmanager = (AppOpsManager)context.getSystemService("appops");
        if(flag1)
            i = appopsmanager.noteOpNoThrow(j, i, s);
        else
            i = appopsmanager.checkOpNoThrow(j, i, s);
        i;
        JVM INSTR tableswitch 0 3: default 64
    //                   0 83
    //                   1 64
    //                   2 64
    //                   3 85;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        if(!flag)
            return false;
        break; /* Loop/switch isn't completed */
_L2:
        return true;
_L3:
        i = 0;
        j = as.length;
        while(i < j) 
        {
            if(context.checkCallingOrSelfPermission(as[i]) == 0)
                return true;
            i++;
        }
        if(true) goto _L1; else goto _L4
_L4:
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(s);
        stringbuilder.append(" was not granted ");
        if(as.length > 1)
            stringbuilder.append(" either of these permissions: ");
        else
            stringbuilder.append(" this permission: ");
        i = 0;
        while(i < as.length) 
        {
            stringbuilder.append(as[i]);
            if(i == as.length - 1)
                context = ".";
            else
                context = ", ";
            stringbuilder.append(context);
            i++;
        }
        throw new SecurityException(stringbuilder.toString());
    }

    public static boolean isCallingPackageAllowedToWriteSettings(Context context, int i, String s, boolean flag)
    {
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, s, flag, 23, PM_WRITE_SETTINGS, false);
    }

    public static boolean isInSystemServer()
    {
        Object obj = sInSystemServerLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = sInSystemServer;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public static void setInSystemServer()
    {
        Object obj = sInSystemServerLock;
        obj;
        JVM INSTR monitorenter ;
        sInSystemServer = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static final String ACTION_ACCESSIBILITY_SETTINGS = "android.settings.ACCESSIBILITY_SETTINGS";
    public static final String ACTION_ADD_ACCOUNT = "android.settings.ADD_ACCOUNT_SETTINGS";
    public static final String ACTION_AIRPLANE_MODE_SETTINGS = "android.settings.AIRPLANE_MODE_SETTINGS";
    public static final String ACTION_APN_SETTINGS = "android.settings.APN_SETTINGS";
    public static final String ACTION_APPLICATION_DETAILS_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";
    public static final String ACTION_APPLICATION_DEVELOPMENT_SETTINGS = "android.settings.APPLICATION_DEVELOPMENT_SETTINGS";
    public static final String ACTION_APPLICATION_SETTINGS = "android.settings.APPLICATION_SETTINGS";
    public static final String ACTION_APP_NOTIFICATION_REDACTION = "android.settings.ACTION_APP_NOTIFICATION_REDACTION";
    public static final String ACTION_APP_NOTIFICATION_SETTINGS = "android.settings.APP_NOTIFICATION_SETTINGS";
    public static final String ACTION_APP_OPS_SETTINGS = "android.settings.APP_OPS_SETTINGS";
    public static final String ACTION_ASSIST_GESTURE_SETTINGS = "android.settings.ASSIST_GESTURE_SETTINGS";
    public static final String ACTION_BATTERY_SAVER_SETTINGS = "android.settings.BATTERY_SAVER_SETTINGS";
    public static final String ACTION_BLUETOOTH_SETTINGS = "android.settings.BLUETOOTH_SETTINGS";
    public static final String ACTION_CAPTIONING_SETTINGS = "android.settings.CAPTIONING_SETTINGS";
    public static final String ACTION_CAST_SETTINGS = "android.settings.CAST_SETTINGS";
    public static final String ACTION_CHANNEL_NOTIFICATION_SETTINGS = "android.settings.CHANNEL_NOTIFICATION_SETTINGS";
    public static final String ACTION_CONDITION_PROVIDER_SETTINGS = "android.settings.ACTION_CONDITION_PROVIDER_SETTINGS";
    public static final String ACTION_DATA_ROAMING_SETTINGS = "android.settings.DATA_ROAMING_SETTINGS";
    public static final String ACTION_DATE_SETTINGS = "android.settings.DATE_SETTINGS";
    public static final String ACTION_DEVICE_INFO_SETTINGS = "android.settings.DEVICE_INFO_SETTINGS";
    public static final String ACTION_DISPLAY_SETTINGS = "android.settings.DISPLAY_SETTINGS";
    public static final String ACTION_DREAM_SETTINGS = "android.settings.DREAM_SETTINGS";
    public static final String ACTION_ENTERPRISE_PRIVACY_SETTINGS = "android.settings.ENTERPRISE_PRIVACY_SETTINGS";
    public static final String ACTION_FOREGROUND_SERVICES_SETTINGS = "android.settings.FOREGROUND_SERVICES_SETTINGS";
    public static final String ACTION_HARD_KEYBOARD_SETTINGS = "android.settings.HARD_KEYBOARD_SETTINGS";
    public static final String ACTION_HOME_SETTINGS = "android.settings.HOME_SETTINGS";
    public static final String ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS = "android.settings.IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS";
    public static final String ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS = "android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS";
    public static final String ACTION_INPUT_METHOD_SETTINGS = "android.settings.INPUT_METHOD_SETTINGS";
    public static final String ACTION_INPUT_METHOD_SUBTYPE_SETTINGS = "android.settings.INPUT_METHOD_SUBTYPE_SETTINGS";
    public static final String ACTION_INTERNAL_STORAGE_SETTINGS = "android.settings.INTERNAL_STORAGE_SETTINGS";
    public static final String ACTION_LOCALE_SETTINGS = "android.settings.LOCALE_SETTINGS";
    public static final String ACTION_LOCATION_SOURCE_SETTINGS = "android.settings.LOCATION_SOURCE_SETTINGS";
    public static final String ACTION_MANAGED_PROFILE_SETTINGS = "android.settings.MANAGED_PROFILE_SETTINGS";
    public static final String ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS = "android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS";
    public static final String ACTION_MANAGE_APPLICATIONS_SETTINGS = "android.settings.MANAGE_APPLICATIONS_SETTINGS";
    public static final String ACTION_MANAGE_DEFAULT_APPS_SETTINGS = "android.settings.MANAGE_DEFAULT_APPS_SETTINGS";
    public static final String ACTION_MANAGE_OVERLAY_PERMISSION = "android.settings.action.MANAGE_OVERLAY_PERMISSION";
    public static final String ACTION_MANAGE_UNKNOWN_APP_SOURCES = "android.settings.MANAGE_UNKNOWN_APP_SOURCES";
    public static final String ACTION_MANAGE_WRITE_SETTINGS = "android.settings.action.MANAGE_WRITE_SETTINGS";
    public static final String ACTION_MEMORY_CARD_SETTINGS = "android.settings.MEMORY_CARD_SETTINGS";
    public static final String ACTION_MOBILE_DATA_USAGE = "android.settings.MOBILE_DATA_USAGE";
    public static final String ACTION_MONITORING_CERT_INFO = "com.android.settings.MONITORING_CERT_INFO";
    public static final String ACTION_NETWORK_OPERATOR_SETTINGS = "android.settings.NETWORK_OPERATOR_SETTINGS";
    public static final String ACTION_NFCSHARING_SETTINGS = "android.settings.NFCSHARING_SETTINGS";
    public static final String ACTION_NFC_PAYMENT_SETTINGS = "android.settings.NFC_PAYMENT_SETTINGS";
    public static final String ACTION_NFC_SETTINGS = "android.settings.NFC_SETTINGS";
    public static final String ACTION_NIGHT_DISPLAY_SETTINGS = "android.settings.NIGHT_DISPLAY_SETTINGS";
    public static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    public static final String ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS = "android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS";
    public static final String ACTION_NOTIFICATION_SETTINGS = "android.settings.NOTIFICATION_SETTINGS";
    public static final String ACTION_PAIRING_SETTINGS = "android.settings.PAIRING_SETTINGS";
    public static final String ACTION_PICTURE_IN_PICTURE_SETTINGS = "android.settings.PICTURE_IN_PICTURE_SETTINGS";
    public static final String ACTION_PRINT_SETTINGS = "android.settings.ACTION_PRINT_SETTINGS";
    public static final String ACTION_PRIVACY_SETTINGS = "android.settings.PRIVACY_SETTINGS";
    public static final String ACTION_QUICK_LAUNCH_SETTINGS = "android.settings.QUICK_LAUNCH_SETTINGS";
    public static final String ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = "android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS";
    public static final String ACTION_REQUEST_SET_AUTOFILL_SERVICE = "android.settings.REQUEST_SET_AUTOFILL_SERVICE";
    public static final String ACTION_SEARCH_SETTINGS = "android.search.action.SEARCH_SETTINGS";
    public static final String ACTION_SECURITY_SETTINGS = "android.settings.SECURITY_SETTINGS";
    public static final String ACTION_SETTINGS = "android.settings.SETTINGS";
    public static final String ACTION_SHOW_ADMIN_SUPPORT_DETAILS = "android.settings.SHOW_ADMIN_SUPPORT_DETAILS";
    public static final String ACTION_SHOW_REGULATORY_INFO = "android.settings.SHOW_REGULATORY_INFO";
    public static final String ACTION_SHOW_REMOTE_BUGREPORT_DIALOG = "android.settings.SHOW_REMOTE_BUGREPORT_DIALOG";
    public static final String ACTION_SOUND_SETTINGS = "android.settings.SOUND_SETTINGS";
    public static final String ACTION_STORAGE_MANAGER_SETTINGS = "android.settings.STORAGE_MANAGER_SETTINGS";
    public static final String ACTION_SYNC_SETTINGS = "android.settings.SYNC_SETTINGS";
    public static final String ACTION_SYSTEM_UPDATE_SETTINGS = "android.settings.SYSTEM_UPDATE_SETTINGS";
    public static final String ACTION_TETHER_PROVISIONING = "android.settings.TETHER_PROVISIONING_UI";
    public static final String ACTION_TRUSTED_CREDENTIALS_USER = "com.android.settings.TRUSTED_CREDENTIALS_USER";
    public static final String ACTION_USAGE_ACCESS_SETTINGS = "android.settings.USAGE_ACCESS_SETTINGS";
    public static final String ACTION_USER_DICTIONARY_INSERT = "com.android.settings.USER_DICTIONARY_INSERT";
    public static final String ACTION_USER_DICTIONARY_SETTINGS = "android.settings.USER_DICTIONARY_SETTINGS";
    public static final String ACTION_USER_SETTINGS = "android.settings.USER_SETTINGS";
    public static final String ACTION_VOICE_CONTROL_AIRPLANE_MODE = "android.settings.VOICE_CONTROL_AIRPLANE_MODE";
    public static final String ACTION_VOICE_CONTROL_BATTERY_SAVER_MODE = "android.settings.VOICE_CONTROL_BATTERY_SAVER_MODE";
    public static final String ACTION_VOICE_CONTROL_DO_NOT_DISTURB_MODE = "android.settings.VOICE_CONTROL_DO_NOT_DISTURB_MODE";
    public static final String ACTION_VOICE_INPUT_SETTINGS = "android.settings.VOICE_INPUT_SETTINGS";
    public static final String ACTION_VPN_SETTINGS = "android.settings.VPN_SETTINGS";
    public static final String ACTION_VR_LISTENER_SETTINGS = "android.settings.VR_LISTENER_SETTINGS";
    public static final String ACTION_WEBVIEW_SETTINGS = "android.settings.WEBVIEW_SETTINGS";
    public static final String ACTION_WIFI_IP_SETTINGS = "android.settings.WIFI_IP_SETTINGS";
    public static final String ACTION_WIFI_SETTINGS = "android.settings.WIFI_SETTINGS";
    public static final String ACTION_WIRELESS_SETTINGS = "android.settings.WIRELESS_SETTINGS";
    public static final String ACTION_ZEN_MODE_AUTOMATION_SETTINGS = "android.settings.ZEN_MODE_AUTOMATION_SETTINGS";
    public static final String ACTION_ZEN_MODE_EVENT_RULE_SETTINGS = "android.settings.ZEN_MODE_EVENT_RULE_SETTINGS";
    public static final String ACTION_ZEN_MODE_EXTERNAL_RULE_SETTINGS = "android.settings.ZEN_MODE_EXTERNAL_RULE_SETTINGS";
    public static final String ACTION_ZEN_MODE_PRIORITY_SETTINGS = "android.settings.ZEN_MODE_PRIORITY_SETTINGS";
    public static final String ACTION_ZEN_MODE_SCHEDULE_RULE_SETTINGS = "android.settings.ZEN_MODE_SCHEDULE_RULE_SETTINGS";
    public static final String ACTION_ZEN_MODE_SETTINGS = "android.settings.ZEN_MODE_SETTINGS";
    public static final String AUTHORITY = "settings";
    public static final String CALL_METHOD_GENERATION_INDEX_KEY = "_generation_index";
    public static final String CALL_METHOD_GENERATION_KEY = "_generation";
    public static final String CALL_METHOD_GET_GLOBAL = "GET_global";
    public static final String CALL_METHOD_GET_SECURE = "GET_secure";
    public static final String CALL_METHOD_GET_SYSTEM = "GET_system";
    public static final String CALL_METHOD_MAKE_DEFAULT_KEY = "_make_default";
    public static final String CALL_METHOD_PUT_GLOBAL = "PUT_global";
    public static final String CALL_METHOD_PUT_SECURE = "PUT_secure";
    public static final String CALL_METHOD_PUT_SYSTEM = "PUT_system";
    public static final String CALL_METHOD_RESET_GLOBAL = "RESET_global";
    public static final String CALL_METHOD_RESET_MODE_KEY = "_reset_mode";
    public static final String CALL_METHOD_RESET_SECURE = "RESET_secure";
    public static final String CALL_METHOD_TAG_KEY = "_tag";
    public static final String CALL_METHOD_TRACK_GENERATION_KEY = "_track_generation";
    public static final String CALL_METHOD_USER_KEY = "_user";
    public static final String DEVICE_NAME_SETTINGS = "android.settings.DEVICE_NAME";
    public static final String EXTRA_ACCOUNT_TYPES = "account_types";
    public static final String EXTRA_AIRPLANE_MODE_ENABLED = "airplane_mode_enabled";
    public static final String EXTRA_APP_PACKAGE = "android.provider.extra.APP_PACKAGE";
    public static final String EXTRA_APP_UID = "app_uid";
    public static final String EXTRA_AUTHORITIES = "authorities";
    public static final String EXTRA_BATTERY_SAVER_MODE_ENABLED = "android.settings.extra.battery_saver_mode_enabled";
    public static final String EXTRA_CHANNEL_ID = "android.provider.extra.CHANNEL_ID";
    public static final String EXTRA_DO_NOT_DISTURB_MODE_ENABLED = "android.settings.extra.do_not_disturb_mode_enabled";
    public static final String EXTRA_DO_NOT_DISTURB_MODE_MINUTES = "android.settings.extra.do_not_disturb_mode_minutes";
    public static final String EXTRA_INPUT_DEVICE_IDENTIFIER = "input_device_identifier";
    public static final String EXTRA_INPUT_METHOD_ID = "input_method_id";
    public static final String EXTRA_NETWORK_TEMPLATE = "network_template";
    public static final String EXTRA_NUMBER_OF_CERTIFICATES = "android.settings.extra.number_of_certificates";
    public static final String EXTRA_SUB_ID = "sub_id";
    public static final String INTENT_CATEGORY_USAGE_ACCESS_CONFIG = "android.intent.category.USAGE_ACCESS_CONFIG";
    private static final String JID_RESOURCE_PREFIX = "android";
    private static final boolean LOCAL_LOGV = false;
    public static final String METADATA_USAGE_ACCESS_REASON = "android.settings.metadata.USAGE_ACCESS_REASON";
    private static final String PM_CHANGE_NETWORK_STATE[] = {
        "android.permission.CHANGE_NETWORK_STATE", "android.permission.WRITE_SETTINGS"
    };
    private static final String PM_SYSTEM_ALERT_WINDOW[] = {
        "android.permission.SYSTEM_ALERT_WINDOW"
    };
    private static final String PM_WRITE_SETTINGS[] = {
        "android.permission.WRITE_SETTINGS"
    };
    public static final int RESET_MODE_PACKAGE_DEFAULTS = 1;
    public static final int RESET_MODE_TRUSTED_DEFAULTS = 4;
    public static final int RESET_MODE_UNTRUSTED_CHANGES = 3;
    public static final int RESET_MODE_UNTRUSTED_DEFAULTS = 2;
    private static final String TAG = "Settings";
    private static final Object mLocationSettingsLock = new Object();
    private static boolean sInSystemServer = false;
    private static final Object sInSystemServerLock = new Object();

}
