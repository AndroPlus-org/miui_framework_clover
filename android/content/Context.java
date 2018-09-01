// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.app.IApplicationThread;
import android.app.IServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.util.AttributeSet;
import android.view.Display;
import android.view.DisplayAdjustments;
import java.io.*;

// Referenced classes of package android.content:
//            Intent, ServiceConnection, ContentResolver, SharedPreferences, 
//            ComponentCallbacks, BroadcastReceiver, IntentFilter, ComponentName, 
//            IntentSender

public abstract class Context
{

    public Context()
    {
    }

    public void assertRuntimeOverlayThemable()
    {
        if(getResources() == Resources.getSystem())
            throw new IllegalArgumentException("Non-UI context used to display UI; get a UI context from ActivityThread#getSystemUiContext()");
        else
            return;
    }

    public abstract boolean bindService(Intent intent, ServiceConnection serviceconnection, int i);

    public boolean bindServiceAsUser(Intent intent, ServiceConnection serviceconnection, int i, Handler handler, UserHandle userhandle)
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public boolean bindServiceAsUser(Intent intent, ServiceConnection serviceconnection, int i, UserHandle userhandle)
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public abstract boolean canLoadUnsafeResources();

    public boolean canStartActivityForResult()
    {
        return false;
    }

    public abstract int checkCallingOrSelfPermission(String s);

    public abstract int checkCallingOrSelfUriPermission(Uri uri, int i);

    public abstract int checkCallingPermission(String s);

    public abstract int checkCallingUriPermission(Uri uri, int i);

    public abstract int checkPermission(String s, int i, int j);

    public abstract int checkPermission(String s, int i, int j, IBinder ibinder);

    public abstract int checkSelfPermission(String s);

    public abstract int checkUriPermission(Uri uri, int i, int j, int k);

    public abstract int checkUriPermission(Uri uri, int i, int j, int k, IBinder ibinder);

    public abstract int checkUriPermission(Uri uri, String s, String s1, int i, int j, int k);

    public abstract void clearWallpaper()
        throws IOException;

    public abstract Context createApplicationContext(ApplicationInfo applicationinfo, int i)
        throws android.content.pm.PackageManager.NameNotFoundException;

    public abstract Context createConfigurationContext(Configuration configuration);

    public abstract Context createContextForSplit(String s)
        throws android.content.pm.PackageManager.NameNotFoundException;

    public abstract Context createCredentialProtectedStorageContext();

    public abstract Context createDeviceProtectedStorageContext();

    public abstract Context createDisplayContext(Display display);

    public abstract Context createPackageContext(String s, int i)
        throws android.content.pm.PackageManager.NameNotFoundException;

    public abstract Context createPackageContextAsUser(String s, int i, UserHandle userhandle)
        throws android.content.pm.PackageManager.NameNotFoundException;

    public abstract String[] databaseList();

    public abstract boolean deleteDatabase(String s);

    public abstract boolean deleteFile(String s);

    public abstract boolean deleteSharedPreferences(String s);

    public abstract void enforceCallingOrSelfPermission(String s, String s1);

    public abstract void enforceCallingOrSelfUriPermission(Uri uri, int i, String s);

    public abstract void enforceCallingPermission(String s, String s1);

    public abstract void enforceCallingUriPermission(Uri uri, int i, String s);

    public abstract void enforcePermission(String s, int i, int j, String s1);

    public abstract void enforceUriPermission(Uri uri, int i, int j, int k, String s);

    public abstract void enforceUriPermission(Uri uri, String s, String s1, int i, int j, int k, String s2);

    public abstract String[] fileList();

    public IBinder getActivityToken()
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public abstract Context getApplicationContext();

    public abstract ApplicationInfo getApplicationInfo();

    public abstract AssetManager getAssets();

    public android.view.autofill.AutofillManager.AutofillClient getAutofillClient()
    {
        return null;
    }

    public abstract String getBasePackageName();

    public abstract File getCacheDir();

    public abstract ClassLoader getClassLoader();

    public abstract File getCodeCacheDir();

    public final int getColor(int i)
    {
        return getResources().getColor(i, getTheme());
    }

    public final ColorStateList getColorStateList(int i)
    {
        return getResources().getColorStateList(i, getTheme());
    }

    public abstract ContentResolver getContentResolver();

    public ContentResolver getContentResolverForUser(UserHandle userhandle)
    {
        return null;
    }

    public abstract File getDataDir();

    public abstract File getDatabasePath(String s);

    public abstract File getDir(String s, int i);

    public abstract Display getDisplay();

    public abstract DisplayAdjustments getDisplayAdjustments(int i);

    public final Drawable getDrawable(int i)
    {
        return getResources().getDrawable(i, getTheme());
    }

    public abstract File getExternalCacheDir();

    public abstract File[] getExternalCacheDirs();

    public abstract File getExternalFilesDir(String s);

    public abstract File[] getExternalFilesDirs(String s);

    public abstract File[] getExternalMediaDirs();

    public abstract File getFileStreamPath(String s);

    public abstract File getFilesDir();

    public IApplicationThread getIApplicationThread()
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public abstract Looper getMainLooper();

    public Handler getMainThreadHandler()
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public int getNextAutofillId()
    {
        if(sLastAutofillId == 0x3ffffffe)
            sLastAutofillId = -1;
        sLastAutofillId++;
        return sLastAutofillId;
    }

    public abstract File getNoBackupFilesDir();

    public abstract File getObbDir();

    public abstract File[] getObbDirs();

    public abstract String getOpPackageName();

    public abstract String getPackageCodePath();

    public abstract PackageManager getPackageManager();

    public abstract String getPackageName();

    public abstract String getPackageResourcePath();

    public abstract File getPreloadsFileCache();

    public abstract Resources getResources();

    public IServiceConnection getServiceDispatcher(ServiceConnection serviceconnection, Handler handler, int i)
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public abstract SharedPreferences getSharedPreferences(File file, int i);

    public abstract SharedPreferences getSharedPreferences(String s, int i);

    public abstract File getSharedPreferencesPath(String s);

    public File getSharedPrefsFile(String s)
    {
        return getSharedPreferencesPath(s);
    }

    public final String getString(int i)
    {
        return getResources().getString(i);
    }

    public final transient String getString(int i, Object aobj[])
    {
        return getResources().getString(i, aobj);
    }

    public final Object getSystemService(Class class1)
    {
        Object obj = null;
        String s = getSystemServiceName(class1);
        class1 = obj;
        if(s != null)
            class1 = ((Class) (getSystemService(s)));
        return class1;
    }

    public abstract Object getSystemService(String s);

    public abstract String getSystemServiceName(Class class1);

    public final CharSequence getText(int i)
    {
        return getResources().getText(i);
    }

    public abstract android.content.res.Resources.Theme getTheme();

    public int getThemeResId()
    {
        return 0;
    }

    public abstract int getUserId();

    public abstract Drawable getWallpaper();

    public abstract int getWallpaperDesiredMinimumHeight();

    public abstract int getWallpaperDesiredMinimumWidth();

    public abstract void grantUriPermission(String s, Uri uri, int i);

    public abstract boolean isCredentialProtectedStorage();

    public abstract boolean isDeviceProtectedStorage();

    public boolean isRestricted()
    {
        return false;
    }

    public abstract boolean moveDatabaseFrom(Context context, String s);

    public abstract boolean moveSharedPreferencesFrom(Context context, String s);

    public final TypedArray obtainStyledAttributes(int i, int ai[])
        throws android.content.res.Resources.NotFoundException
    {
        return getTheme().obtainStyledAttributes(i, ai);
    }

    public final TypedArray obtainStyledAttributes(AttributeSet attributeset, int ai[])
    {
        return getTheme().obtainStyledAttributes(attributeset, ai, 0, 0);
    }

    public final TypedArray obtainStyledAttributes(AttributeSet attributeset, int ai[], int i, int j)
    {
        return getTheme().obtainStyledAttributes(attributeset, ai, i, j);
    }

    public final TypedArray obtainStyledAttributes(int ai[])
    {
        return getTheme().obtainStyledAttributes(ai);
    }

    public abstract FileInputStream openFileInput(String s)
        throws FileNotFoundException;

    public abstract FileOutputStream openFileOutput(String s, int i)
        throws FileNotFoundException;

    public abstract SQLiteDatabase openOrCreateDatabase(String s, int i, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory);

    public abstract SQLiteDatabase openOrCreateDatabase(String s, int i, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, DatabaseErrorHandler databaseerrorhandler);

    public abstract Drawable peekWallpaper();

    public void registerComponentCallbacks(ComponentCallbacks componentcallbacks)
    {
        getApplicationContext().registerComponentCallbacks(componentcallbacks);
    }

    public abstract Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter);

    public abstract Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, int i);

    public abstract Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, String s, Handler handler);

    public abstract Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, String s, Handler handler, int i);

    public abstract Intent registerReceiverAsUser(BroadcastReceiver broadcastreceiver, UserHandle userhandle, IntentFilter intentfilter, String s, Handler handler);

    public abstract void reloadSharedPreferences();

    public abstract void removeStickyBroadcast(Intent intent);

    public abstract void removeStickyBroadcastAsUser(Intent intent, UserHandle userhandle);

    public abstract void revokeUriPermission(Uri uri, int i);

    public abstract void revokeUriPermission(String s, Uri uri, int i);

    public abstract void sendBroadcast(Intent intent);

    public abstract void sendBroadcast(Intent intent, String s);

    public abstract void sendBroadcast(Intent intent, String s, int i);

    public abstract void sendBroadcast(Intent intent, String s, Bundle bundle);

    public abstract void sendBroadcastAsUser(Intent intent, UserHandle userhandle);

    public abstract void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s);

    public abstract void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i);

    public abstract void sendBroadcastAsUser(Intent intent, UserHandle userhandle, String s, Bundle bundle);

    public abstract void sendBroadcastMultiplePermissions(Intent intent, String as[]);

    public abstract void sendOrderedBroadcast(Intent intent, String s);

    public abstract void sendOrderedBroadcast(Intent intent, String s, int i, BroadcastReceiver broadcastreceiver, Handler handler, int j, String s1, 
            Bundle bundle);

    public abstract void sendOrderedBroadcast(Intent intent, String s, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, Bundle bundle);

    public abstract void sendOrderedBroadcast(Intent intent, String s, Bundle bundle, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, 
            Bundle bundle1);

    public abstract void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i, BroadcastReceiver broadcastreceiver, Handler handler, int j, 
            String s1, Bundle bundle);

    public abstract void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, int i, Bundle bundle, BroadcastReceiver broadcastreceiver, Handler handler, 
            int j, String s1, Bundle bundle1);

    public abstract void sendOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, String s, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s1, 
            Bundle bundle);

    public abstract void sendStickyBroadcast(Intent intent);

    public abstract void sendStickyBroadcastAsUser(Intent intent, UserHandle userhandle);

    public abstract void sendStickyBroadcastAsUser(Intent intent, UserHandle userhandle, Bundle bundle);

    public abstract void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s, Bundle bundle);

    public abstract void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle userhandle, BroadcastReceiver broadcastreceiver, Handler handler, int i, String s, Bundle bundle);

    public void setAutofillClient(android.view.autofill.AutofillManager.AutofillClient autofillclient)
    {
    }

    public abstract void setTheme(int i);

    public abstract void setWallpaper(Bitmap bitmap)
        throws IOException;

    public abstract void setWallpaper(InputStream inputstream)
        throws IOException;

    public abstract void startActivities(Intent aintent[]);

    public abstract void startActivities(Intent aintent[], Bundle bundle);

    public void startActivitiesAsUser(Intent aintent[], Bundle bundle, UserHandle userhandle)
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public abstract void startActivity(Intent intent);

    public abstract void startActivity(Intent intent, Bundle bundle);

    public void startActivityAsUser(Intent intent, Bundle bundle, UserHandle userhandle)
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public void startActivityAsUser(Intent intent, UserHandle userhandle)
    {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    public void startActivityForResult(String s, Intent intent, int i, Bundle bundle)
    {
        throw new RuntimeException("This method is only implemented for Activity-based Contexts. Check canStartActivityForResult() before calling.");
    }

    public abstract ComponentName startForegroundService(Intent intent);

    public abstract ComponentName startForegroundServiceAsUser(Intent intent, UserHandle userhandle);

    public abstract boolean startInstrumentation(ComponentName componentname, String s, Bundle bundle);

    public abstract void startIntentSender(IntentSender intentsender, Intent intent, int i, int j, int k)
        throws IntentSender.SendIntentException;

    public abstract void startIntentSender(IntentSender intentsender, Intent intent, int i, int j, int k, Bundle bundle)
        throws IntentSender.SendIntentException;

    public abstract ComponentName startService(Intent intent);

    public abstract ComponentName startServiceAsUser(Intent intent, UserHandle userhandle);

    public abstract boolean stopService(Intent intent);

    public abstract boolean stopServiceAsUser(Intent intent, UserHandle userhandle);

    public abstract void unbindService(ServiceConnection serviceconnection);

    public void unregisterComponentCallbacks(ComponentCallbacks componentcallbacks)
    {
        getApplicationContext().unregisterComponentCallbacks(componentcallbacks);
    }

    public abstract void unregisterReceiver(BroadcastReceiver broadcastreceiver);

    public abstract void updateDisplay(int i);

    public static final String ACCESSIBILITY_SERVICE = "accessibility";
    public static final String ACCOUNT_SERVICE = "account";
    public static final String ACTIVITY_SERVICE = "activity";
    public static final String ALARM_SERVICE = "alarm";
    public static final String APPWIDGET_SERVICE = "appwidget";
    public static final String APP_OPS_SERVICE = "appops";
    public static final String AUDIO_SERVICE = "audio";
    public static final String AUTOFILL_MANAGER_SERVICE = "autofill";
    public static final String BACKUP_SERVICE = "backup";
    public static final String BATTERY_SERVICE = "batterymanager";
    public static final int BIND_ABOVE_CLIENT = 8;
    public static final int BIND_ADJUST_WITH_ACTIVITY = 128;
    public static final int BIND_ALLOW_OOM_MANAGEMENT = 16;
    public static final int BIND_ALLOW_WHITELIST_MANAGEMENT = 0x1000000;
    public static final int BIND_AUTO_CREATE = 1;
    public static final int BIND_DEBUG_UNBIND = 2;
    public static final int BIND_EXTERNAL_SERVICE = 0x80000000;
    public static final int BIND_FOREGROUND_SERVICE = 0x4000000;
    public static final int BIND_FOREGROUND_SERVICE_WHILE_AWAKE = 0x2000000;
    public static final int BIND_IMPORTANT = 64;
    public static final int BIND_IMPORTANT_BACKGROUND = 0x800000;
    public static final int BIND_NOT_FOREGROUND = 4;
    public static final int BIND_NOT_VISIBLE = 0x40000000;
    public static final int BIND_SHOWING_UI = 0x20000000;
    public static final int BIND_TREAT_LIKE_ACTIVITY = 0x8000000;
    public static final int BIND_VISIBLE = 0x10000000;
    public static final int BIND_WAIVE_PRIORITY = 32;
    public static final String BLUETOOTH_SERVICE = "bluetooth";
    public static final String CAMERA_SERVICE = "camera";
    public static final String CAPTIONING_SERVICE = "captioning";
    public static final String CARRIER_CONFIG_SERVICE = "carrier_config";
    public static final String CLIPBOARD_SERVICE = "clipboard";
    public static final String COMPANION_DEVICE_SERVICE = "companiondevice";
    public static final String CONNECTIVITY_SERVICE = "connectivity";
    public static final String CONSUMER_IR_SERVICE = "consumer_ir";
    public static final String CONTEXTHUB_SERVICE = "contexthub";
    public static final int CONTEXT_CREDENTIAL_PROTECTED_STORAGE = 16;
    public static final int CONTEXT_DEVICE_PROTECTED_STORAGE = 8;
    public static final int CONTEXT_IGNORE_SECURITY = 2;
    public static final int CONTEXT_INCLUDE_CODE = 1;
    public static final int CONTEXT_REGISTER_PACKAGE = 0x40000000;
    public static final int CONTEXT_RESTRICTED = 4;
    public static final String COUNTRY_DETECTOR = "country_detector";
    public static final String DEVICE_IDENTIFIERS_SERVICE = "device_identifiers";
    public static final String DEVICE_IDLE_CONTROLLER = "deviceidle";
    public static final String DEVICE_POLICY_SERVICE = "device_policy";
    public static final String DISPLAY_SERVICE = "display";
    public static final String DOWNLOAD_SERVICE = "download";
    public static final String DROPBOX_SERVICE = "dropbox";
    public static final String ETHERNET_SERVICE = "ethernet";
    public static final String EUICC_SERVICE = "euicc_service";
    public static final String FINGERPRINT_SERVICE = "fingerprint";
    public static final String GATEKEEPER_SERVICE = "android.service.gatekeeper.IGateKeeperService";
    public static final String HARDWARE_PROPERTIES_SERVICE = "hardware_properties";
    public static final String HDMI_CONTROL_SERVICE = "hdmi_control";
    public static final String INCIDENT_SERVICE = "incident";
    public static final String INPUT_METHOD_SERVICE = "input_method";
    public static final String INPUT_SERVICE = "input";
    public static final String IPSEC_SERVICE = "ipsec";
    public static final String JOB_SCHEDULER_SERVICE = "jobscheduler";
    public static final String KEYGUARD_SERVICE = "keyguard";
    public static final String LAUNCHER_APPS_SERVICE = "launcherapps";
    public static final String LAYOUT_INFLATER_SERVICE = "layout_inflater";
    public static final String LOCATION_POLICY_SERVICE = "locationpolicy";
    public static final String LOCATION_SERVICE = "location";
    public static final String LOWPAN_SERVICE = "lowpan";
    public static final String MEDIA_PROJECTION_SERVICE = "media_projection";
    public static final String MEDIA_ROUTER_SERVICE = "media_router";
    public static final String MEDIA_SESSION_SERVICE = "media_session";
    public static final String MIDI_SERVICE = "midi";
    public static final int MODE_APPEND = 32768;
    public static final int MODE_ENABLE_WRITE_AHEAD_LOGGING = 8;
    public static final int MODE_MULTI_PROCESS = 4;
    public static final int MODE_NO_LOCALIZED_COLLATORS = 16;
    public static final int MODE_PRIVATE = 0;
    public static final int MODE_WORLD_READABLE = 1;
    public static final int MODE_WORLD_WRITEABLE = 2;
    public static final String NETWORKMANAGEMENT_SERVICE = "network_management";
    public static final String NETWORK_POLICY_SERVICE = "netpolicy";
    public static final String NETWORK_SCORE_SERVICE = "network_score";
    public static final String NETWORK_STATS_SERVICE = "netstats";
    public static final String NFC_SERVICE = "nfc";
    public static final String NOTIFICATION_SERVICE = "notification";
    public static final String NSD_SERVICE = "servicediscovery";
    public static final String OEM_LOCK_SERVICE = "oem_lock";
    public static final String OVERLAY_SERVICE = "overlay";
    public static final String PERSISTENT_DATA_BLOCK_SERVICE = "persistent_data_block";
    public static final String POWER_SERVICE = "power";
    public static final String PRINT_SERVICE = "print";
    public static final String RADIO_SERVICE = "broadcastradio";
    public static final int RECEIVER_VISIBLE_TO_INSTANT_APPS = 1;
    public static final String RECOVERY_SERVICE = "recovery";
    public static final String RESTRICTIONS_SERVICE = "restrictions";
    public static final String SEARCH_SERVICE = "search";
    public static final String SECURITY_SERVICE = "security";
    public static final String SENSOR_SERVICE = "sensor";
    public static final String SERIAL_SERVICE = "serial";
    public static final String SHORTCUT_SERVICE = "shortcut";
    public static final String SIP_SERVICE = "sip";
    public static final String SOUND_TRIGGER_SERVICE = "soundtrigger";
    public static final String STATUS_BAR_SERVICE = "statusbar";
    public static final String STORAGE_SERVICE = "storage";
    public static final String STORAGE_STATS_SERVICE = "storagestats";
    public static final String SYSTEM_HEALTH_SERVICE = "systemhealth";
    public static final String TELECOM_SERVICE = "telecom";
    public static final String TELEPHONY_SERVICE = "phone";
    public static final String TELEPHONY_SUBSCRIPTION_SERVICE = "telephony_subscription_service";
    public static final String TEXT_CLASSIFICATION_SERVICE = "textclassification";
    public static final String TEXT_SERVICES_MANAGER_SERVICE = "textservices";
    public static final String TIME_ZONE_RULES_MANAGER_SERVICE = "timezone";
    public static final String TRUST_SERVICE = "trust";
    public static final String TV_INPUT_SERVICE = "tv_input";
    public static final String UI_MODE_SERVICE = "uimode";
    public static final String UPDATE_LOCK_SERVICE = "updatelock";
    public static final String USAGE_STATS_SERVICE = "usagestats";
    public static final String USB_SERVICE = "usb";
    public static final String USER_SERVICE = "user";
    public static final String VIBRATOR_SERVICE = "vibrator";
    public static final String VOICE_INTERACTION_MANAGER_SERVICE = "voiceinteraction";
    public static final String VR_SERVICE = "vrmanager";
    public static final String WALLPAPER_SERVICE = "wallpaper";
    public static final String WIFI_AWARE_SERVICE = "wifiaware";
    public static final String WIFI_P2P_SERVICE = "wifip2p";
    public static final String WIFI_RTT_SERVICE = "rttmanager";
    public static final String WIFI_SCANNING_SERVICE = "wifiscanner";
    public static final String WIFI_SERVICE = "wifi";
    public static final String WINDOW_SERVICE = "window";
    private static int sLastAutofillId = -1;

}
