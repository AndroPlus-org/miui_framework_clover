// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.*;
import android.util.Log;
import com.android.internal.util.AsyncChannel;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.net.wifi:
//            IWifiManager, WifiConfiguration

public class MiuiWifiManager
{

    private MiuiWifiManager()
    {
        IWifiManager iwifimanager = IWifiManager.Stub.asInterface(ServiceManager.getService("wifi"));
        HandlerThread handlerthread = JVM INSTR new #86  <Class HandlerThread>;
        handlerthread.HandlerThread("MiuiWifiManager");
        handlerthread.start();
        AsyncChannel asyncchannel = JVM INSTR new #94  <Class AsyncChannel>;
        asyncchannel.AsyncChannel();
        mAsyncChannel = asyncchannel;
        asyncchannel = mAsyncChannel;
        Handler handler = JVM INSTR new #99  <Class Handler>;
        handler.Handler(handlerthread.getLooper());
        asyncchannel.connect(null, handler, iwifimanager.getWifiServiceMessenger());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MiuiWifiManager", (new StringBuilder()).append("build WifiConfigForSupplicant failed exception ").append(remoteexception).toString());
          goto _L1
    }

    public static int calculateSignalLevel(int i, int j)
    {
        if(i <= -100)
            return 0;
        if(i >= -65)
        {
            return j - 1;
        } else
        {
            float f = j - 1;
            return (int)(((float)(i + 100) * f) / 35F);
        }
    }

    public static MiuiWifiManager getInstance()
    {
        if(sInstance == null)
            sInstance = new MiuiWifiManager();
        return sInstance;
    }

    public static void ignoreApsForScanObserver(ArrayList arraylist, boolean flag)
    {
        Log.d("MiuiWifiManager", (new StringBuilder()).append("Ignore observed: ").append(flag).toString());
        Message message = Message.obtain();
        message.what = 0x25fa7;
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("bssid", arraylist);
        message.obj = bundle;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        message.arg1 = i;
        getInstance().sendAsyncMessage(message);
    }

    public static boolean verifyPreSharedKey(WifiConfiguration wificonfiguration, String s)
    {
        Log.d("MiuiWifiManager", "Verify shared key");
        if(android.os.Build.VERSION.SDK_INT < 23)
        {
            Log.e("MiuiWifiManager", "Cannot verify shared key in api lower than 23");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", wificonfiguration);
        bundle.putString("key", s);
        wificonfiguration = Message.obtain();
        wificonfiguration.obj = bundle;
        wificonfiguration.what = 0x25fa6;
        wificonfiguration = getInstance().sendSyncMessage(wificonfiguration);
        boolean flag;
        if(((Message) (wificonfiguration)).arg1 == 1)
            flag = true;
        else
            flag = false;
        wificonfiguration.recycle();
        return flag;
    }

    protected void finalize()
        throws Throwable
    {
        if(mAsyncChannel != null)
            mAsyncChannel.disconnect();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public List getObservedAccessPionts()
    {
        Object obj = Message.obtain();
        obj.what = 0x25fa3;
        Message message = mAsyncChannel.sendMessageSynchronously(((Message) (obj)));
        Object obj1 = null;
        Bundle bundle = (Bundle)message.obj;
        obj = obj1;
        if(message.arg1 == 1)
        {
            obj = obj1;
            if(bundle != null)
                obj = bundle.getStringArrayList("extra_aps");
        }
        message.recycle();
        return ((List) (obj));
    }

    public void sendAsyncMessage(Message message)
    {
        mAsyncChannel.sendMessage(message);
    }

    public Message sendSyncMessage(Message message)
    {
        return mAsyncChannel.sendMessageSynchronously(message);
    }

    public void setCompatibleMode(boolean flag)
    {
        AsyncChannel asyncchannel = mAsyncChannel;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        asyncchannel.sendMessage(0x25fa4, i);
    }

    public void setNetworkExplicited()
    {
        Message message = Message.obtain();
        message.what = 0x25fa5;
        sendAsyncMessage(message);
    }

    public void setObservedAccessPionts(List list)
    {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("extra_aps", new ArrayList(list));
        list = Message.obtain();
        list.what = 0x25fa2;
        list.obj = bundle;
        sendAsyncMessage(list);
    }

    private static final int BASE = 0x25fa0;
    public static final int CMD_GET_OBSERVED_ACCESSPOINTS = 0x25fa3;
    public static final int CMD_IGNORE_OBSERVED_AP = 0x25fa7;
    public static final int CMD_SET_OBSERVED_ACCESSPOINTS = 0x25fa2;
    public static final int CMD_SET_WIFI_EXPLICITED = 0x25fa5;
    public static final int CMD_SET_WIRELESS_CONNECT_MODE = 0x25fa4;
    public static final int CMD_VERIFY_PRE_SHARED_KEY = 0x25fa6;
    public static final String EXTRA_APS = "extra_aps";
    public static final String EXTRA_BSSID = "bssid";
    public static final String EXTRA_CONFIG = "config";
    public static final String EXTRA_KEY = "key";
    public static final String EXTRA_SSID = "ssid";
    public static final int FAILED = 2;
    public static final int GET_SUPPLICANT_CONFIGURATION = 0x25fa1;
    private static final int MAX_RSSI = -65;
    private static final int MIN_RSSI = -100;
    public static final String OBSERVED_ACCESSPOINTS_CHANGED = "android.net.wifi.observed_accesspionts_changed";
    public static final String OBSERVED_OPENAPS_CHANGED = "android.net.wifi.observed_open_accesspionts_changed";
    public static final int SUCCESS = 1;
    private static final String TAG = "MiuiWifiManager";
    public static final String WPS_DEVICE_GUEST = "guest";
    public static final String WPS_DEVICE_XIAOMI = "xiaomi";
    private static MiuiWifiManager sInstance;
    private AsyncChannel mAsyncChannel;
}
