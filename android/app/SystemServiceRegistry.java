// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.accounts.AccountManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.timezone.RulesManager;
import android.app.trust.TrustManager;
import android.app.usage.NetworkStatsManager;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.companion.CompanionDeviceManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.RestrictionsManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutManager;
import android.content.res.Resources;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.SerialManager;
import android.hardware.SystemSensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.hdmi.HdmiControlManager;
import android.hardware.input.InputManager;
import android.hardware.location.ContextHubManager;
import android.hardware.radio.RadioManager;
import android.hardware.usb.UsbManager;
import android.location.CountryDetector;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.midi.MidiManager;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.soundtrigger.SoundTriggerManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.ConnectivityThread;
import android.net.EthernetManager;
import android.net.IpSecManager;
import android.net.NetworkPolicyManager;
import android.net.NetworkScoreManager;
import android.net.lowpan.LowpanManager;
import android.net.nsd.NsdManager;
import android.net.wifi.RttManager;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiScanner;
import android.net.wifi.aware.WifiAwareManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.DropBoxManager;
import android.os.Handler;
import android.os.HardwarePropertiesManager;
import android.os.IncidentManager;
import android.os.PowerManager;
import android.os.Process;
import android.os.RecoverySystem;
import android.os.ServiceManager;
import android.os.SystemVibrator;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.health.SystemHealthManager;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.service.oemlock.OemLockManager;
import android.service.persistentdata.PersistentDataBlockManager;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.euicc.EuiccManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.autofill.AutofillManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassificationManager;
import android.view.textservice.TextServicesManager;
import com.android.internal.policy.PhoneLayoutInflater;
import java.util.HashMap;

// Referenced classes of package android.app:
//            ActivityManager, AlarmManager, DownloadManager, KeyguardManager, 
//            NotificationManager, SearchManager, StatusBarManager, UiModeManager, 
//            WallpaperManager, AppOpsManager, VrManager, ContextImplInjector, 
//            ContextImpl, ActivityThread, JobSchedulerImpl

final class SystemServiceRegistry
{
    static abstract class CachedServiceFetcher
        implements ServiceFetcher
    {

        public abstract Object createService(ContextImpl contextimpl)
            throws android.os.ServiceManager.ServiceNotFoundException;

        public final Object getService(ContextImpl contextimpl)
        {
            Object aobj[] = contextimpl.mServiceCache;
            aobj;
            JVM INSTR monitorenter ;
            Object obj = aobj[mCacheIndex];
            Object obj1;
            obj1 = obj;
            if(obj != null)
                break MISSING_BLOCK_LABEL_43;
            obj1 = obj;
            contextimpl = ((ContextImpl) (createService(contextimpl)));
            obj1 = contextimpl;
            aobj[mCacheIndex] = contextimpl;
            obj1 = contextimpl;
_L2:
            aobj;
            JVM INSTR monitorexit ;
            return obj1;
            contextimpl;
            SystemServiceRegistry.onServiceNotFound(contextimpl);
            if(true) goto _L2; else goto _L1
_L1:
            contextimpl;
            throw contextimpl;
        }

        private final int mCacheIndex;

        public CachedServiceFetcher()
        {
            int i = SystemServiceRegistry._2D_get0();
            SystemServiceRegistry._2D_set0(i + 1);
            mCacheIndex = i;
        }
    }

    static interface ServiceFetcher
    {

        public abstract Object getService(ContextImpl contextimpl);
    }

    static abstract class StaticApplicationContextServiceFetcher
        implements ServiceFetcher
    {

        public abstract Object createService(Context context)
            throws android.os.ServiceManager.ServiceNotFoundException;

        public final Object getService(ContextImpl contextimpl)
        {
            this;
            JVM INSTR monitorenter ;
            Object obj;
            if(mCachedInstance != null)
                break MISSING_BLOCK_LABEL_27;
            obj = contextimpl.getApplicationContext();
            if(obj == null)
                obj = contextimpl;
            mCachedInstance = createService(((Context) (obj)));
_L1:
            contextimpl = ((ContextImpl) (mCachedInstance));
            this;
            JVM INSTR monitorexit ;
            return contextimpl;
            contextimpl;
            SystemServiceRegistry.onServiceNotFound(contextimpl);
              goto _L1
            contextimpl;
            throw contextimpl;
        }

        private Object mCachedInstance;

        StaticApplicationContextServiceFetcher()
        {
        }
    }

    static abstract class StaticServiceFetcher
        implements ServiceFetcher
    {

        public abstract Object createService()
            throws android.os.ServiceManager.ServiceNotFoundException;

        public final Object getService(ContextImpl contextimpl)
        {
            this;
            JVM INSTR monitorenter ;
            contextimpl = ((ContextImpl) (mCachedInstance));
            if(contextimpl != null)
                break MISSING_BLOCK_LABEL_19;
            mCachedInstance = createService();
_L1:
            contextimpl = ((ContextImpl) (mCachedInstance));
            this;
            JVM INSTR monitorexit ;
            return contextimpl;
            contextimpl;
            SystemServiceRegistry.onServiceNotFound(contextimpl);
              goto _L1
            contextimpl;
            throw contextimpl;
        }

        private Object mCachedInstance;

        StaticServiceFetcher()
        {
        }
    }


    static int _2D_get0()
    {
        return sServiceCacheSize;
    }

    static int _2D_set0(int i)
    {
        sServiceCacheSize = i;
        return i;
    }

    private SystemServiceRegistry()
    {
    }

    public static Object[] createServiceCache()
    {
        return new Object[sServiceCacheSize];
    }

    public static Object getSystemService(ContextImpl contextimpl, String s)
    {
        Object obj = null;
        ServiceFetcher servicefetcher = (ServiceFetcher)SYSTEM_SERVICE_FETCHERS.get(s);
        s = obj;
        if(servicefetcher != null)
            s = ((String) (servicefetcher.getService(contextimpl)));
        return s;
    }

    public static String getSystemServiceName(Class class1)
    {
        return (String)SYSTEM_SERVICE_NAMES.get(class1);
    }

    public static void onServiceNotFound(android.os.ServiceManager.ServiceNotFoundException servicenotfoundexception)
    {
        if(Process.myUid() < 10000)
            Log.wtf("SystemServiceRegistry", servicenotfoundexception.getMessage(), servicenotfoundexception);
        else
            Log.w("SystemServiceRegistry", servicenotfoundexception.getMessage());
    }

    static void registerService(String s, Class class1, ServiceFetcher servicefetcher)
    {
        SYSTEM_SERVICE_NAMES.put(class1, s);
        SYSTEM_SERVICE_FETCHERS.put(s, servicefetcher);
    }

    private static final HashMap SYSTEM_SERVICE_FETCHERS = new HashMap();
    private static final HashMap SYSTEM_SERVICE_NAMES;
    private static final String TAG = "SystemServiceRegistry";
    private static int sServiceCacheSize;

    static 
    {
        SYSTEM_SERVICE_NAMES = new HashMap();
        registerService("accessibility", android/view/accessibility/AccessibilityManager, new CachedServiceFetcher() {

            public AccessibilityManager createService(ContextImpl contextimpl)
            {
                return AccessibilityManager.getInstance(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("captioning", android/view/accessibility/CaptioningManager, new CachedServiceFetcher() {

            public CaptioningManager createService(ContextImpl contextimpl)
            {
                return new CaptioningManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("account", android/accounts/AccountManager, new CachedServiceFetcher() {

            public AccountManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new AccountManager(contextimpl, android.accounts.IAccountManager.Stub.asInterface(ServiceManager.getServiceOrThrow("account")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("activity", android/app/ActivityManager, new CachedServiceFetcher() {

            public ActivityManager createService(ContextImpl contextimpl)
            {
                return new ActivityManager(contextimpl.getOuterContext(), contextimpl.mMainThread.getHandler());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("alarm", android/app/AlarmManager, new CachedServiceFetcher() {

            public AlarmManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new AlarmManager(IAlarmManager.Stub.asInterface(ServiceManager.getServiceOrThrow("alarm")), contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("audio", android/media/AudioManager, new CachedServiceFetcher() {

            public AudioManager createService(ContextImpl contextimpl)
            {
                return new AudioManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("media_router", android/media/MediaRouter, new CachedServiceFetcher() {

            public MediaRouter createService(ContextImpl contextimpl)
            {
                return new MediaRouter(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("bluetooth", android/bluetooth/BluetoothManager, new CachedServiceFetcher() {

            public BluetoothManager createService(ContextImpl contextimpl)
            {
                return new BluetoothManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("hdmi_control", android/hardware/hdmi/HdmiControlManager, new StaticServiceFetcher() {

            public HdmiControlManager createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new HdmiControlManager(android.hardware.hdmi.IHdmiControlService.Stub.asInterface(ServiceManager.getServiceOrThrow("hdmi_control")));
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("textclassification", android/view/textclassifier/TextClassificationManager, new CachedServiceFetcher() {

            public TextClassificationManager createService(ContextImpl contextimpl)
            {
                return new TextClassificationManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("clipboard", android/content/ClipboardManager, new CachedServiceFetcher() {

            public ClipboardManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new ClipboardManager(contextimpl.getOuterContext(), contextimpl.mMainThread.getHandler());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        SYSTEM_SERVICE_NAMES.put(android/text/ClipboardManager, "clipboard");
        registerService("connectivity", android/net/ConnectivityManager, new StaticApplicationContextServiceFetcher() {

            public ConnectivityManager createService(Context context)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new ConnectivityManager(context, android.net.IConnectivityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("connectivity")));
            }

            public volatile Object createService(Context context)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(context);
            }

        }
);
        registerService("ipsec", android/net/IpSecManager, new StaticServiceFetcher() {

            public IpSecManager createService()
            {
                return new IpSecManager(android.net.IIpSecService.Stub.asInterface(ServiceManager.getService("ipsec")));
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("country_detector", android/location/CountryDetector, new StaticServiceFetcher() {

            public CountryDetector createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new CountryDetector(android.location.ICountryDetector.Stub.asInterface(ServiceManager.getServiceOrThrow("country_detector")));
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("device_policy", android/app/admin/DevicePolicyManager, new CachedServiceFetcher() {

            public DevicePolicyManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new DevicePolicyManager(contextimpl, android.app.admin.IDevicePolicyManager.Stub.asInterface(ServiceManager.getServiceOrThrow("device_policy")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("download", android/app/DownloadManager, new CachedServiceFetcher() {

            public DownloadManager createService(ContextImpl contextimpl)
            {
                return new DownloadManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("batterymanager", android/os/BatteryManager, new StaticServiceFetcher() {

            public BatteryManager createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new BatteryManager(com.android.internal.app.IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")), android.os.IBatteryPropertiesRegistrar.Stub.asInterface(ServiceManager.getServiceOrThrow("batteryproperties")));
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("nfc", android/nfc/NfcManager, new CachedServiceFetcher() {

            public NfcManager createService(ContextImpl contextimpl)
            {
                return new NfcManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("dropbox", android/os/DropBoxManager, new CachedServiceFetcher() {

            public DropBoxManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new DropBoxManager(contextimpl, com.android.internal.os.IDropBoxManagerService.Stub.asInterface(ServiceManager.getServiceOrThrow("dropbox")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("input", android/hardware/input/InputManager, new StaticServiceFetcher() {

            public InputManager createService()
            {
                return InputManager.getInstance();
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("display", android/hardware/display/DisplayManager, new CachedServiceFetcher() {

            public DisplayManager createService(ContextImpl contextimpl)
            {
                return new DisplayManager(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("input_method", android/view/inputmethod/InputMethodManager, new StaticServiceFetcher() {

            public InputMethodManager createService()
            {
                return InputMethodManager.getInstance();
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("textservices", android/view/textservice/TextServicesManager, new StaticServiceFetcher() {

            public TextServicesManager createService()
            {
                return TextServicesManager.getInstance();
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("keyguard", android/app/KeyguardManager, new CachedServiceFetcher() {

            public KeyguardManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new KeyguardManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("layout_inflater", android/view/LayoutInflater, new CachedServiceFetcher() {

            public LayoutInflater createService(ContextImpl contextimpl)
            {
                return new PhoneLayoutInflater(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("location", android/location/LocationManager, new CachedServiceFetcher() {

            public LocationManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new LocationManager(contextimpl, android.location.ILocationManager.Stub.asInterface(ServiceManager.getServiceOrThrow("location")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("netpolicy", android/net/NetworkPolicyManager, new CachedServiceFetcher() {

            public NetworkPolicyManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new NetworkPolicyManager(contextimpl, android.net.INetworkPolicyManager.Stub.asInterface(ServiceManager.getServiceOrThrow("netpolicy")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("notification", android/app/NotificationManager, new CachedServiceFetcher() {

            public NotificationManager createService(ContextImpl contextimpl)
            {
                Context context = contextimpl.getOuterContext();
                return new NotificationManager(new ContextThemeWrapper(context, Resources.selectSystemTheme(0, context.getApplicationInfo().targetSdkVersion, 0x103000b, 0x103006f, 0x103012e, 0x1030132)), contextimpl.mMainThread.getHandler());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("servicediscovery", android/net/nsd/NsdManager, new CachedServiceFetcher() {

            public NsdManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.net.nsd.INsdManager insdmanager = android.net.nsd.INsdManager.Stub.asInterface(ServiceManager.getServiceOrThrow("servicediscovery"));
                return new NsdManager(contextimpl.getOuterContext(), insdmanager);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("power", android/os/PowerManager, new CachedServiceFetcher() {

            public PowerManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.os.IPowerManager ipowermanager = android.os.IPowerManager.Stub.asInterface(ServiceManager.getServiceOrThrow("power"));
                return new PowerManager(contextimpl.getOuterContext(), ipowermanager, contextimpl.mMainThread.getHandler());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("recovery", android/os/RecoverySystem, new CachedServiceFetcher() {

            public RecoverySystem createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new RecoverySystem(android.os.IRecoverySystem.Stub.asInterface(ServiceManager.getServiceOrThrow("recovery")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("search", android/app/SearchManager, new CachedServiceFetcher() {

            public SearchManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new SearchManager(contextimpl.getOuterContext(), contextimpl.mMainThread.getHandler());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("sensor", android/hardware/SensorManager, new CachedServiceFetcher() {

            public SensorManager createService(ContextImpl contextimpl)
            {
                return new SystemSensorManager(contextimpl.getOuterContext(), contextimpl.mMainThread.getHandler().getLooper());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("statusbar", android/app/StatusBarManager, new CachedServiceFetcher() {

            public StatusBarManager createService(ContextImpl contextimpl)
            {
                return new StatusBarManager(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("storage", android/os/storage/StorageManager, new CachedServiceFetcher() {

            public StorageManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new StorageManager(contextimpl, contextimpl.mMainThread.getHandler().getLooper());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("storagestats", android/app/usage/StorageStatsManager, new CachedServiceFetcher() {

            public StorageStatsManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new StorageStatsManager(contextimpl, android.app.usage.IStorageStatsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("storagestats")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("phone", android/telephony/TelephonyManager, new CachedServiceFetcher() {

            public TelephonyManager createService(ContextImpl contextimpl)
            {
                return new TelephonyManager(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("telephony_subscription_service", android/telephony/SubscriptionManager, new CachedServiceFetcher() {

            public SubscriptionManager createService(ContextImpl contextimpl)
            {
                return new SubscriptionManager(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("carrier_config", android/telephony/CarrierConfigManager, new CachedServiceFetcher() {

            public CarrierConfigManager createService(ContextImpl contextimpl)
            {
                return new CarrierConfigManager();
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("telecom", android/telecom/TelecomManager, new CachedServiceFetcher() {

            public TelecomManager createService(ContextImpl contextimpl)
            {
                return new TelecomManager(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("euicc_service", android/telephony/euicc/EuiccManager, new CachedServiceFetcher() {

            public EuiccManager createService(ContextImpl contextimpl)
            {
                return new EuiccManager(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("uimode", android/app/UiModeManager, new CachedServiceFetcher() {

            public UiModeManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new UiModeManager();
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("usb", android/hardware/usb/UsbManager, new CachedServiceFetcher() {

            public UsbManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new UsbManager(contextimpl, android.hardware.usb.IUsbManager.Stub.asInterface(ServiceManager.getServiceOrThrow("usb")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("serial", android/hardware/SerialManager, new CachedServiceFetcher() {

            public SerialManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new SerialManager(contextimpl, android.hardware.ISerialManager.Stub.asInterface(ServiceManager.getServiceOrThrow("serial")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("vibrator", android/os/Vibrator, new CachedServiceFetcher() {

            public Vibrator createService(ContextImpl contextimpl)
            {
                return new SystemVibrator(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("wallpaper", android/app/WallpaperManager, new CachedServiceFetcher() {

            public WallpaperManager createService(ContextImpl contextimpl)
            {
                return new WallpaperManager(contextimpl.getOuterContext(), contextimpl.mMainThread.getHandler());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("lowpan", android/net/lowpan/LowpanManager, new CachedServiceFetcher() {

            public LowpanManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.net.lowpan.ILowpanManager ilowpanmanager = android.net.lowpan.ILowpanManager.Stub.asInterface(ServiceManager.getServiceOrThrow("lowpan"));
                return new LowpanManager(contextimpl.getOuterContext(), ilowpanmanager, ConnectivityThread.getInstanceLooper());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("wifi", android/net/wifi/WifiManager, new CachedServiceFetcher() {

            public WifiManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.net.wifi.IWifiManager iwifimanager = android.net.wifi.IWifiManager.Stub.asInterface(ServiceManager.getServiceOrThrow("wifi"));
                return new WifiManager(contextimpl.getOuterContext(), iwifimanager, ConnectivityThread.getInstanceLooper());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("wifip2p", android/net/wifi/p2p/WifiP2pManager, new StaticServiceFetcher() {

            public WifiP2pManager createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new WifiP2pManager(android.net.wifi.p2p.IWifiP2pManager.Stub.asInterface(ServiceManager.getServiceOrThrow("wifip2p")));
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("wifiaware", android/net/wifi/aware/WifiAwareManager, new CachedServiceFetcher() {

            public WifiAwareManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.net.wifi.aware.IWifiAwareManager iwifiawaremanager = android.net.wifi.aware.IWifiAwareManager.Stub.asInterface(ServiceManager.getServiceOrThrow("wifiaware"));
                if(iwifiawaremanager == null)
                    return null;
                else
                    return new WifiAwareManager(contextimpl.getOuterContext(), iwifiawaremanager);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("wifiscanner", android/net/wifi/WifiScanner, new CachedServiceFetcher() {

            public WifiScanner createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.net.wifi.IWifiScanner iwifiscanner = android.net.wifi.IWifiScanner.Stub.asInterface(ServiceManager.getServiceOrThrow("wifiscanner"));
                if(iwifiscanner == null)
                    return null;
                else
                    return new WifiScanner(contextimpl.getOuterContext(), iwifiscanner, ConnectivityThread.getInstanceLooper());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("rttmanager", android/net/wifi/RttManager, new CachedServiceFetcher() {

            public RttManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.net.wifi.IRttManager irttmanager = android.net.wifi.IRttManager.Stub.asInterface(ServiceManager.getServiceOrThrow("rttmanager"));
                return new RttManager(contextimpl.getOuterContext(), irttmanager, ConnectivityThread.getInstanceLooper());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("ethernet", android/net/EthernetManager, new CachedServiceFetcher() {

            public EthernetManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.net.IEthernetManager iethernetmanager = android.net.IEthernetManager.Stub.asInterface(ServiceManager.getServiceOrThrow("ethernet"));
                return new EthernetManager(contextimpl.getOuterContext(), iethernetmanager);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("window", android/view/WindowManager, new CachedServiceFetcher() {

            public WindowManager createService(ContextImpl contextimpl)
            {
                return new WindowManagerImpl(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("user", android/os/UserManager, new CachedServiceFetcher() {

            public UserManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new UserManager(contextimpl, android.os.IUserManager.Stub.asInterface(ServiceManager.getServiceOrThrow("user")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("appops", android/app/AppOpsManager, new CachedServiceFetcher() {

            public AppOpsManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new AppOpsManager(contextimpl, com.android.internal.app.IAppOpsService.Stub.asInterface(ServiceManager.getServiceOrThrow("appops")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("camera", android/hardware/camera2/CameraManager, new CachedServiceFetcher() {

            public CameraManager createService(ContextImpl contextimpl)
            {
                return new CameraManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("launcherapps", android/content/pm/LauncherApps, new CachedServiceFetcher() {

            public LauncherApps createService(ContextImpl contextimpl)
            {
                return new LauncherApps(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("restrictions", android/content/RestrictionsManager, new CachedServiceFetcher() {

            public RestrictionsManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new RestrictionsManager(contextimpl, android.content.IRestrictionsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("restrictions")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("print", android/print/PrintManager, new CachedServiceFetcher() {

            public PrintManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.print.IPrintManager iprintmanager = null;
                if(contextimpl.getPackageManager().hasSystemFeature("android.software.print"))
                    iprintmanager = android.print.IPrintManager.Stub.asInterface(ServiceManager.getServiceOrThrow("print"));
                return new PrintManager(contextimpl.getOuterContext(), iprintmanager, UserHandle.myUserId(), UserHandle.getAppId(Process.myUid()));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("companiondevice", android/companion/CompanionDeviceManager, new CachedServiceFetcher() {

            public CompanionDeviceManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.companion.ICompanionDeviceManager icompaniondevicemanager = null;
                if(contextimpl.getPackageManager().hasSystemFeature("android.software.companion_device_setup"))
                    icompaniondevicemanager = android.companion.ICompanionDeviceManager.Stub.asInterface(ServiceManager.getServiceOrThrow("companiondevice"));
                return new CompanionDeviceManager(icompaniondevicemanager, contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("consumer_ir", android/hardware/ConsumerIrManager, new CachedServiceFetcher() {

            public ConsumerIrManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new ConsumerIrManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("media_session", android/media/session/MediaSessionManager, new CachedServiceFetcher() {

            public MediaSessionManager createService(ContextImpl contextimpl)
            {
                return new MediaSessionManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("trust", android/app/trust/TrustManager, new StaticServiceFetcher() {

            public TrustManager createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new TrustManager(ServiceManager.getServiceOrThrow("trust"));
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("fingerprint", android/hardware/fingerprint/FingerprintManager, new CachedServiceFetcher() {

            public FingerprintManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                Object obj;
                if(contextimpl.getApplicationInfo().targetSdkVersion >= 26)
                    obj = ServiceManager.getServiceOrThrow("fingerprint");
                else
                    obj = ServiceManager.getService("fingerprint");
                obj = android.hardware.fingerprint.IFingerprintService.Stub.asInterface(((android.os.IBinder) (obj)));
                return new FingerprintManager(contextimpl.getOuterContext(), ((android.hardware.fingerprint.IFingerprintService) (obj)));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("tv_input", android/media/tv/TvInputManager, new StaticServiceFetcher() {

            public TvInputManager createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new TvInputManager(android.media.tv.ITvInputManager.Stub.asInterface(ServiceManager.getServiceOrThrow("tv_input")), UserHandle.myUserId());
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("network_score", android/net/NetworkScoreManager, new CachedServiceFetcher() {

            public NetworkScoreManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new NetworkScoreManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("usagestats", android/app/usage/UsageStatsManager, new CachedServiceFetcher() {

            public UsageStatsManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.app.usage.IUsageStatsManager iusagestatsmanager = android.app.usage.IUsageStatsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("usagestats"));
                return new UsageStatsManager(contextimpl.getOuterContext(), iusagestatsmanager);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("netstats", android/app/usage/NetworkStatsManager, new CachedServiceFetcher() {

            public NetworkStatsManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new NetworkStatsManager(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("jobscheduler", android/app/job/JobScheduler, new StaticServiceFetcher() {

            public JobScheduler createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new JobSchedulerImpl(android.app.job.IJobScheduler.Stub.asInterface(ServiceManager.getServiceOrThrow("jobscheduler")));
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("persistent_data_block", android/service/persistentdata/PersistentDataBlockManager, new StaticServiceFetcher() {

            public PersistentDataBlockManager createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.service.persistentdata.IPersistentDataBlockService ipersistentdatablockservice = android.service.persistentdata.IPersistentDataBlockService.Stub.asInterface(ServiceManager.getServiceOrThrow("persistent_data_block"));
                if(ipersistentdatablockservice != null)
                    return new PersistentDataBlockManager(ipersistentdatablockservice);
                else
                    return null;
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("oem_lock", android/service/oemlock/OemLockManager, new StaticServiceFetcher() {

            public OemLockManager createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.service.oemlock.IOemLockService ioemlockservice = android.service.oemlock.IOemLockService.Stub.asInterface(ServiceManager.getServiceOrThrow("oem_lock"));
                if(ioemlockservice != null)
                    return new OemLockManager(ioemlockservice);
                else
                    return null;
            }

            public volatile Object createService()
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService();
            }

        }
);
        registerService("media_projection", android/media/projection/MediaProjectionManager, new CachedServiceFetcher() {

            public MediaProjectionManager createService(ContextImpl contextimpl)
            {
                return new MediaProjectionManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("appwidget", android/appwidget/AppWidgetManager, new CachedServiceFetcher() {

            public AppWidgetManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new AppWidgetManager(contextimpl, com.android.internal.appwidget.IAppWidgetService.Stub.asInterface(ServiceManager.getServiceOrThrow("appwidget")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("midi", android/media/midi/MidiManager, new CachedServiceFetcher() {

            public MidiManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new MidiManager(android.media.midi.IMidiManager.Stub.asInterface(ServiceManager.getServiceOrThrow("midi")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("broadcastradio", android/hardware/radio/RadioManager, new CachedServiceFetcher() {

            public RadioManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new RadioManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("hardware_properties", android/os/HardwarePropertiesManager, new CachedServiceFetcher() {

            public HardwarePropertiesManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new HardwarePropertiesManager(contextimpl, android.os.IHardwarePropertiesManager.Stub.asInterface(ServiceManager.getServiceOrThrow("hardware_properties")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("soundtrigger", android/media/soundtrigger/SoundTriggerManager, new CachedServiceFetcher() {

            public SoundTriggerManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new SoundTriggerManager(contextimpl, com.android.internal.app.ISoundTriggerService.Stub.asInterface(ServiceManager.getServiceOrThrow("soundtrigger")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("shortcut", android/content/pm/ShortcutManager, new CachedServiceFetcher() {

            public ShortcutManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new ShortcutManager(contextimpl, android.content.pm.IShortcutService.Stub.asInterface(ServiceManager.getServiceOrThrow("shortcut")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("systemhealth", android/os/health/SystemHealthManager, new CachedServiceFetcher() {

            public SystemHealthManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new SystemHealthManager(com.android.internal.app.IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("contexthub", android/hardware/location/ContextHubManager, new CachedServiceFetcher() {

            public ContextHubManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new ContextHubManager(contextimpl.getOuterContext(), contextimpl.mMainThread.getHandler().getLooper());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("incident", android/os/IncidentManager, new CachedServiceFetcher() {

            public IncidentManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new IncidentManager(contextimpl);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("autofill", android/view/autofill/AutofillManager, new CachedServiceFetcher() {

            public AutofillManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                android.view.autofill.IAutoFillManager iautofillmanager = android.view.autofill.IAutoFillManager.Stub.asInterface(ServiceManager.getService("autofill"));
                return new AutofillManager(contextimpl.getOuterContext(), iautofillmanager);
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("vrmanager", android/app/VrManager, new CachedServiceFetcher() {

            public VrManager createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return new VrManager(android.service.vr.IVrManager.Stub.asInterface(ServiceManager.getServiceOrThrow("vrmanager")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        registerService("timezone", android/app/timezone/RulesManager, new CachedServiceFetcher() {

            public RulesManager createService(ContextImpl contextimpl)
            {
                return new RulesManager(contextimpl.getOuterContext());
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
        ContextImplInjector.registerMiuiServices();
    }
}
