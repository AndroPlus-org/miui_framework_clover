// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.*;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.util.*;
import com.android.internal.telephony.ITelephony;
import com.android.internal.util.Preconditions;
import java.net.InetAddress;
import java.util.*;
import libcore.net.event.NetworkEventDispatcher;

// Referenced classes of package android.net:
//            IConnectivityManager, ConnectivityThread, NetworkUtils, Network, 
//            NetworkCapabilities, Proxy, INetworkPolicyManager, NetworkRequest, 
//            LinkProperties, NetworkInfo, NetworkQuotaInfo, ProxyInfo, 
//            NetworkMisc

public class ConnectivityManager
{
    private class CallbackHandler extends Handler
    {

        private Object getObject(Message message, Class class1)
        {
            return message.getData().getParcelable(class1.getSimpleName());
        }

        public void handleMessage(Message message)
        {
            Object obj;
            Network network;
            if(message.what == 0x80008)
            {
                ConnectivityManager._2D_wrap0(ConnectivityManager.this, (NetworkCapabilities)message.obj, message.arg1);
                return;
            }
            obj = (NetworkRequest)getObject(message, android/net/NetworkRequest);
            network = (Network)getObject(message, android/net/Network);
            HashMap hashmap = ConnectivityManager._2D_get1();
            hashmap;
            JVM INSTR monitorenter ;
            obj = (NetworkCallback)ConnectivityManager._2D_get1().get(obj);
            hashmap;
            JVM INSTR monitorexit ;
            if(obj == null)
            {
                Log.w("ConnectivityManager.CallbackHandler", (new StringBuilder()).append("callback not found for ").append(ConnectivityManager.getCallbackName(message.what)).append(" message").toString());
                return;
            }
            break MISSING_BLOCK_LABEL_119;
            message;
            throw message;
            message.what;
            JVM INSTR tableswitch 524289 524298: default 176
        //                       524289 177
        //                       524290 185
        //                       524291 193
        //                       524292 205
        //                       524293 213
        //                       524294 220
        //                       524295 238
        //                       524296 176
        //                       524297 256
        //                       524298 264;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L1 _L9 _L10
_L1:
            return;
_L2:
            ((NetworkCallback) (obj)).onPreCheck(network);
            continue; /* Loop/switch isn't completed */
_L3:
            ((NetworkCallback) (obj)).onAvailable(network);
            continue; /* Loop/switch isn't completed */
_L4:
            ((NetworkCallback) (obj)).onLosing(network, message.arg1);
            continue; /* Loop/switch isn't completed */
_L5:
            ((NetworkCallback) (obj)).onLost(network);
            continue; /* Loop/switch isn't completed */
_L6:
            ((NetworkCallback) (obj)).onUnavailable();
            continue; /* Loop/switch isn't completed */
_L7:
            ((NetworkCallback) (obj)).onCapabilitiesChanged(network, (NetworkCapabilities)getObject(message, android/net/NetworkCapabilities));
            continue; /* Loop/switch isn't completed */
_L8:
            ((NetworkCallback) (obj)).onLinkPropertiesChanged(network, (LinkProperties)getObject(message, android/net/LinkProperties));
            continue; /* Loop/switch isn't completed */
_L9:
            ((NetworkCallback) (obj)).onNetworkSuspended(network);
            continue; /* Loop/switch isn't completed */
_L10:
            ((NetworkCallback) (obj)).onNetworkResumed(network);
            if(true) goto _L1; else goto _L11
_L11:
        }

        private static final boolean DBG = false;
        private static final String TAG = "ConnectivityManager.CallbackHandler";
        final ConnectivityManager this$0;

        CallbackHandler(Handler handler)
        {
            this(((Handler)Preconditions.checkNotNull(handler, "Handler cannot be null.")).getLooper());
        }

        CallbackHandler(Looper looper)
        {
            this$0 = ConnectivityManager.this;
            super(looper);
        }
    }

    public static interface Errors
    {

        public static final int TOO_MANY_REQUESTS = 1;
    }

    private static class LegacyRequest
    {

        static void _2D_wrap0(LegacyRequest legacyrequest)
        {
            legacyrequest.clearDnsBinding();
        }

        private void clearDnsBinding()
        {
            if(currentNetwork != null)
            {
                currentNetwork = null;
                ConnectivityManager.setProcessDefaultNetworkForHostResolution(null);
            }
        }

        Network currentNetwork;
        int delay;
        int expireSequenceNumber;
        NetworkCallback networkCallback = new _cls1();
        NetworkCapabilities networkCapabilities;
        NetworkRequest networkRequest;

        private LegacyRequest()
        {
            delay = -1;
        }

        LegacyRequest(LegacyRequest legacyrequest)
        {
            this();
        }
    }

    public static class NetworkCallback
    {

        static NetworkRequest _2D_get0(NetworkCallback networkcallback)
        {
            return networkcallback.networkRequest;
        }

        static NetworkRequest _2D_set0(NetworkCallback networkcallback, NetworkRequest networkrequest)
        {
            networkcallback.networkRequest = networkrequest;
            return networkrequest;
        }

        public void onAvailable(Network network)
        {
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkcapabilities)
        {
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkproperties)
        {
        }

        public void onLosing(Network network, int i)
        {
        }

        public void onLost(Network network)
        {
        }

        public void onNetworkResumed(Network network)
        {
        }

        public void onNetworkSuspended(Network network)
        {
        }

        public void onPreCheck(Network network)
        {
        }

        public void onUnavailable()
        {
        }

        private NetworkRequest networkRequest;

        public NetworkCallback()
        {
        }
    }

    public static interface OnNetworkActiveListener
    {

        public abstract void onNetworkActive();
    }

    public static abstract class OnStartTetheringCallback
    {

        public void onTetheringFailed()
        {
        }

        public void onTetheringStarted()
        {
        }

        public OnStartTetheringCallback()
        {
        }
    }

    public class PacketKeepalive
    {

        static PacketKeepaliveCallback _2D_get0(PacketKeepalive packetkeepalive)
        {
            return packetkeepalive.mCallback;
        }

        static Messenger _2D_get1(PacketKeepalive packetkeepalive)
        {
            return packetkeepalive.mMessenger;
        }

        static Integer _2D_get2(PacketKeepalive packetkeepalive)
        {
            return packetkeepalive.mSlot;
        }

        static Integer _2D_set0(PacketKeepalive packetkeepalive, Integer integer)
        {
            packetkeepalive.mSlot = integer;
            return integer;
        }

        public void stop()
        {
            ConnectivityManager._2D_get0(ConnectivityManager.this).stopKeepalive(mNetwork, mSlot.intValue());
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("PacketKeepalive", "Error stopping packet keepalive: ", remoteexception);
            stopLooper();
              goto _L1
        }

        void stopLooper()
        {
            mLooper.quit();
        }

        public static final int BINDER_DIED = -10;
        public static final int ERROR_HARDWARE_ERROR = -31;
        public static final int ERROR_HARDWARE_UNSUPPORTED = -30;
        public static final int ERROR_INVALID_INTERVAL = -24;
        public static final int ERROR_INVALID_IP_ADDRESS = -21;
        public static final int ERROR_INVALID_LENGTH = -23;
        public static final int ERROR_INVALID_NETWORK = -20;
        public static final int ERROR_INVALID_PORT = -22;
        public static final int NATT_PORT = 4500;
        public static final int NO_KEEPALIVE = -1;
        public static final int SUCCESS = 0;
        private static final String TAG = "PacketKeepalive";
        private final PacketKeepaliveCallback mCallback;
        private final Looper mLooper;
        private final Messenger mMessenger;
        private final Network mNetwork;
        private volatile Integer mSlot;
        final ConnectivityManager this$0;

        private PacketKeepalive(Network network, PacketKeepaliveCallback packetkeepalivecallback)
        {
            this$0 = ConnectivityManager.this;
            super();
            Preconditions.checkNotNull(network, "network cannot be null");
            Preconditions.checkNotNull(packetkeepalivecallback, "callback cannot be null");
            mNetwork = network;
            mCallback = packetkeepalivecallback;
            connectivitymanager = new HandlerThread("PacketKeepalive");
            start();
            mLooper = getLooper();
            mMessenger = new Messenger(new _cls1(mLooper));
        }

        PacketKeepalive(Network network, PacketKeepaliveCallback packetkeepalivecallback, PacketKeepalive packetkeepalive)
        {
            this(network, packetkeepalivecallback);
        }
    }

    public static class PacketKeepaliveCallback
    {

        public void onError(int i)
        {
        }

        public void onStarted()
        {
        }

        public void onStopped()
        {
        }

        public PacketKeepaliveCallback()
        {
        }
    }

    public static class TooManyRequestsException extends RuntimeException
    {

        public TooManyRequestsException()
        {
        }
    }


    static IConnectivityManager _2D_get0(ConnectivityManager connectivitymanager)
    {
        return connectivitymanager.mService;
    }

    static HashMap _2D_get1()
    {
        return sCallbacks;
    }

    static void _2D_wrap0(ConnectivityManager connectivitymanager, NetworkCapabilities networkcapabilities, int i)
    {
        connectivitymanager.expireRequest(networkcapabilities, i);
    }

    public ConnectivityManager(Context context, IConnectivityManager iconnectivitymanager)
    {
        mContext = (Context)Preconditions.checkNotNull(context, "missing context");
        mService = (IConnectivityManager)Preconditions.checkNotNull(iconnectivitymanager, "missing IConnectivityManager");
        sInstance = this;
    }

    private static void checkCallbackNotNull(NetworkCallback networkcallback)
    {
        Preconditions.checkNotNull(networkcallback, "null NetworkCallback");
    }

    public static final boolean checkChangePermission(Context context)
    {
        int i = Binder.getCallingUid();
        return Settings.checkAndNoteChangeNetworkStateOperation(context, i, Settings.getPackageNameForUid(context, i), false);
    }

    private void checkLegacyRoutingApiAccess()
    {
        if(mContext.checkCallingOrSelfPermission("com.android.permission.INJECT_OMADM_SETTINGS") == 0)
        {
            return;
        } else
        {
            unsupportedStartingFrom(23);
            return;
        }
    }

    private static void checkPendingIntentNotNull(PendingIntent pendingintent)
    {
        Preconditions.checkNotNull(pendingintent, "PendingIntent cannot be null.");
    }

    private static void checkTimeout(int i)
    {
        Preconditions.checkArgumentPositive(i, "timeoutMs must be strictly positive.");
    }

    private static RuntimeException convertServiceException(ServiceSpecificException servicespecificexception)
    {
        switch(servicespecificexception.errorCode)
        {
        default:
            Log.w("ConnectivityManager", (new StringBuilder()).append("Unknown service error code ").append(servicespecificexception.errorCode).toString());
            return new RuntimeException(servicespecificexception);

        case 1: // '\001'
            return new TooManyRequestsException();
        }
    }

    public static final void enforceChangePermission(Context context)
    {
        int i = Binder.getCallingUid();
        Settings.checkAndNoteChangeNetworkStateOperation(context, i, Settings.getPackageNameForUid(context, i), true);
    }

    public static final void enforceTetherChangePermission(Context context, String s)
    {
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(s, "callingPkg cannot be null");
        if(context.getResources().getStringArray(0x1070034).length == 2)
            context.enforceCallingOrSelfPermission("android.permission.TETHER_PRIVILEGED", "ConnectivityService");
        else
            Settings.checkAndNoteWriteSettingsOperation(context, Binder.getCallingUid(), s, true);
    }

    private void expireRequest(NetworkCapabilities networkcapabilities, int i)
    {
        HashMap hashmap = sLegacyRequests;
        hashmap;
        JVM INSTR monitorenter ;
        LegacyRequest legacyrequest = (LegacyRequest)sLegacyRequests.get(networkcapabilities);
        if(legacyrequest != null)
            break MISSING_BLOCK_LABEL_26;
        hashmap;
        JVM INSTR monitorexit ;
        return;
        int j;
        j = legacyrequest.expireSequenceNumber;
        if(legacyrequest.expireSequenceNumber == i)
            removeRequestForFeature(networkcapabilities);
        hashmap;
        JVM INSTR monitorexit ;
        Log.d("ConnectivityManager", (new StringBuilder()).append("expireRequest with ").append(j).append(", ").append(i).toString());
        return;
        networkcapabilities;
        throw networkcapabilities;
    }

    private NetworkRequest findRequestForFeature(NetworkCapabilities networkcapabilities)
    {
        HashMap hashmap = sLegacyRequests;
        hashmap;
        JVM INSTR monitorenter ;
        networkcapabilities = (LegacyRequest)sLegacyRequests.get(networkcapabilities);
        if(networkcapabilities == null)
            break MISSING_BLOCK_LABEL_30;
        networkcapabilities = ((LegacyRequest) (networkcapabilities)).networkRequest;
        hashmap;
        JVM INSTR monitorexit ;
        return networkcapabilities;
        hashmap;
        JVM INSTR monitorexit ;
        return null;
        networkcapabilities;
        throw networkcapabilities;
    }

    public static ConnectivityManager from(Context context)
    {
        return (ConnectivityManager)context.getSystemService("connectivity");
    }

    public static String getCallbackName(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 524289: 
            return "CALLBACK_PRECHECK";

        case 524290: 
            return "CALLBACK_AVAILABLE";

        case 524291: 
            return "CALLBACK_LOSING";

        case 524292: 
            return "CALLBACK_LOST";

        case 524293: 
            return "CALLBACK_UNAVAIL";

        case 524294: 
            return "CALLBACK_CAP_CHANGED";

        case 524295: 
            return "CALLBACK_IP_CHANGED";

        case 524296: 
            return "EXPIRE_LEGACY_REQUEST";

        case 524297: 
            return "CALLBACK_SUSPENDED";

        case 524298: 
            return "CALLBACK_RESUMED";
        }
    }

    private CallbackHandler getDefaultHandler()
    {
        HashMap hashmap = sCallbacks;
        hashmap;
        JVM INSTR monitorenter ;
        CallbackHandler callbackhandler1;
        if(sCallbackHandler == null)
        {
            CallbackHandler callbackhandler = JVM INSTR new #10  <Class ConnectivityManager$CallbackHandler>;
            callbackhandler.this. CallbackHandler(ConnectivityThread.getInstanceLooper());
            sCallbackHandler = callbackhandler;
        }
        callbackhandler1 = sCallbackHandler;
        hashmap;
        JVM INSTR monitorexit ;
        return callbackhandler1;
        Exception exception;
        exception;
        throw exception;
    }

    private static ConnectivityManager getInstance()
    {
        if(getInstanceOrNull() == null)
            throw new IllegalStateException("No ConnectivityManager yet constructed");
        else
            return getInstanceOrNull();
    }

    static ConnectivityManager getInstanceOrNull()
    {
        return sInstance;
    }

    private INetworkManagementService getNetworkManagementService()
    {
        this;
        JVM INSTR monitorenter ;
        INetworkManagementService inetworkmanagementservice;
        if(mNMService == null)
            break MISSING_BLOCK_LABEL_18;
        inetworkmanagementservice = mNMService;
        this;
        JVM INSTR monitorexit ;
        return inetworkmanagementservice;
        mNMService = android.os.INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
        inetworkmanagementservice = mNMService;
        this;
        JVM INSTR monitorexit ;
        return inetworkmanagementservice;
        Exception exception;
        exception;
        throw exception;
    }

    private INetworkPolicyManager getNetworkPolicyManager()
    {
        this;
        JVM INSTR monitorenter ;
        INetworkPolicyManager inetworkpolicymanager;
        if(mNPManager == null)
            break MISSING_BLOCK_LABEL_18;
        inetworkpolicymanager = mNPManager;
        this;
        JVM INSTR monitorexit ;
        return inetworkpolicymanager;
        mNPManager = INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy"));
        inetworkpolicymanager = mNPManager;
        this;
        JVM INSTR monitorexit ;
        return inetworkpolicymanager;
        Exception exception;
        exception;
        throw exception;
    }

    public static String getNetworkTypeName(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case -1: 
            return "NONE";

        case 0: // '\0'
            return "MOBILE";

        case 1: // '\001'
            return "WIFI";

        case 2: // '\002'
            return "MOBILE_MMS";

        case 3: // '\003'
            return "MOBILE_SUPL";

        case 4: // '\004'
            return "MOBILE_DUN";

        case 5: // '\005'
            return "MOBILE_HIPRI";

        case 6: // '\006'
            return "WIMAX";

        case 7: // '\007'
            return "BLUETOOTH";

        case 8: // '\b'
            return "DUMMY";

        case 9: // '\t'
            return "ETHERNET";

        case 10: // '\n'
            return "MOBILE_FOTA";

        case 11: // '\013'
            return "MOBILE_IMS";

        case 12: // '\f'
            return "MOBILE_CBS";

        case 13: // '\r'
            return "WIFI_P2P";

        case 14: // '\016'
            return "MOBILE_IA";

        case 15: // '\017'
            return "MOBILE_EMERGENCY";

        case 16: // '\020'
            return "PROXY";

        case 17: // '\021'
            return "VPN";
        }
    }

    public static Network getProcessDefaultNetwork()
    {
        int i = NetworkUtils.getBoundNetworkForProcess();
        if(i == 0)
            return null;
        else
            return new Network(i);
    }

    private int inferLegacyTypeForNetworkCapabilities(NetworkCapabilities networkcapabilities)
    {
        Object obj;
        byte byte0;
        if(networkcapabilities == null)
            return -1;
        if(!networkcapabilities.hasTransport(0))
            return -1;
        if(!networkcapabilities.hasCapability(1))
            return -1;
        obj = null;
        byte0 = -1;
        if(!networkcapabilities.hasCapability(5)) goto _L2; else goto _L1
_L1:
        obj = "enableCBS";
        byte0 = 12;
_L4:
        if(obj != null)
        {
            obj = networkCapabilitiesForFeature(0, ((String) (obj)));
            if(((NetworkCapabilities) (obj)).equalsNetCapabilities(networkcapabilities) && ((NetworkCapabilities) (obj)).equalsTransportTypes(networkcapabilities))
                return byte0;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if(networkcapabilities.hasCapability(4))
        {
            obj = "enableIMS";
            byte0 = 11;
        } else
        if(networkcapabilities.hasCapability(3))
        {
            obj = "enableFOTA";
            byte0 = 10;
        } else
        if(networkcapabilities.hasCapability(2))
        {
            obj = "enableDUN";
            byte0 = 4;
        } else
        if(networkcapabilities.hasCapability(1))
        {
            obj = "enableSUPL";
            byte0 = 3;
        } else
        if(networkcapabilities.hasCapability(12))
        {
            obj = "enableHIPRI";
            byte0 = 5;
        }
        if(true) goto _L4; else goto _L3
_L3:
        return -1;
    }

    public static boolean isNetworkTypeMobile(int i)
    {
        switch(i)
        {
        case 1: // '\001'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 13: // '\r'
        default:
            return false;

        case 0: // '\0'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 14: // '\016'
        case 15: // '\017'
            return true;
        }
    }

    public static boolean isNetworkTypeValid(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= 17)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNetworkTypeWifi(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 1: // '\001'
        case 13: // '\r'
            return true;
        }
    }

    private int legacyTypeForNetworkCapabilities(NetworkCapabilities networkcapabilities)
    {
        if(networkcapabilities == null)
            return -1;
        if(networkcapabilities.hasCapability(5))
            return 12;
        if(networkcapabilities.hasCapability(4))
            return 11;
        if(networkcapabilities.hasCapability(3))
            return 10;
        if(networkcapabilities.hasCapability(2))
            return 4;
        if(networkcapabilities.hasCapability(1))
            return 3;
        if(networkcapabilities.hasCapability(0))
            return 2;
        if(networkcapabilities.hasCapability(12))
            return 5;
        return !networkcapabilities.hasCapability(6) ? -1 : 13;
    }

    private NetworkCapabilities networkCapabilitiesForFeature(int i, String s)
    {
        if(i == 0)
        {
            if(s.equals("enableCBS"))
                return networkCapabilitiesForType(12);
            while(s.equals("enableDUN") || s.equals("enableDUNAlways")) 
                return networkCapabilitiesForType(4);
            if(s.equals("enableFOTA"))
                return networkCapabilitiesForType(10);
            if(s.equals("enableHIPRI"))
                return networkCapabilitiesForType(5);
            if(s.equals("enableIMS"))
                return networkCapabilitiesForType(11);
            if(s.equals("enableMMS"))
                return networkCapabilitiesForType(2);
            if(s.equals("enableSUPL"))
                return networkCapabilitiesForType(3);
            else
                return null;
        }
        if(i == 1 && "p2p".equals(s))
            return networkCapabilitiesForType(13);
        else
            return null;
    }

    public static NetworkCapabilities networkCapabilitiesForType(int i)
    {
        NetworkCapabilities networkcapabilities = new NetworkCapabilities();
        int j = sLegacyTypeToTransport.get(i, -1);
        boolean flag;
        if(j != -1)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, (new StringBuilder()).append("unknown legacy type: ").append(i).toString());
        networkcapabilities.addTransportType(j);
        networkcapabilities.addCapability(sLegacyTypeToCapability.get(i, 12));
        networkcapabilities.maybeMarkCapabilitiesRestricted();
        return networkcapabilities;
    }

    private boolean removeRequestForFeature(NetworkCapabilities networkcapabilities)
    {
        HashMap hashmap = sLegacyRequests;
        hashmap;
        JVM INSTR monitorenter ;
        networkcapabilities = (LegacyRequest)sLegacyRequests.remove(networkcapabilities);
        hashmap;
        JVM INSTR monitorexit ;
        if(networkcapabilities == null)
        {
            return false;
        } else
        {
            unregisterNetworkCallback(((LegacyRequest) (networkcapabilities)).networkCallback);
            LegacyRequest._2D_wrap0(networkcapabilities);
            return true;
        }
        networkcapabilities;
        throw networkcapabilities;
    }

    private void renewRequestLocked(LegacyRequest legacyrequest)
    {
        legacyrequest.expireSequenceNumber = legacyrequest.expireSequenceNumber + 1;
        Log.d("ConnectivityManager", (new StringBuilder()).append("renewing request to seqNum ").append(legacyrequest.expireSequenceNumber).toString());
        sendExpireMsgForFeature(legacyrequest.networkCapabilities, legacyrequest.expireSequenceNumber, legacyrequest.delay);
    }

    private NetworkRequest requestNetworkForFeatureLocked(NetworkCapabilities networkcapabilities)
    {
        int i = legacyTypeForNetworkCapabilities(networkcapabilities);
        int j;
        LegacyRequest legacyrequest;
        try
        {
            j = mService.getRestoreDefaultNetworkDelay(i);
        }
        // Misplaced declaration of an exception variable
        catch(NetworkCapabilities networkcapabilities)
        {
            throw networkcapabilities.rethrowFromSystemServer();
        }
        legacyrequest = new LegacyRequest(null);
        legacyrequest.networkCapabilities = networkcapabilities;
        legacyrequest.delay = j;
        legacyrequest.expireSequenceNumber = 0;
        legacyrequest.networkRequest = sendRequestForNetwork(networkcapabilities, legacyrequest.networkCallback, 0, 2, i, getDefaultHandler());
        if(legacyrequest.networkRequest == null)
        {
            return null;
        } else
        {
            sLegacyRequests.put(networkcapabilities, legacyrequest);
            sendExpireMsgForFeature(networkcapabilities, legacyrequest.expireSequenceNumber, j);
            return legacyrequest.networkRequest;
        }
    }

    private void sendExpireMsgForFeature(NetworkCapabilities networkcapabilities, int i, int j)
    {
        if(j >= 0)
        {
            Log.d("ConnectivityManager", (new StringBuilder()).append("sending expire msg with seqNum ").append(i).append(" and delay ").append(j).toString());
            CallbackHandler callbackhandler = getDefaultHandler();
            callbackhandler.sendMessageDelayed(callbackhandler.obtainMessage(0x80008, i, 0, networkcapabilities), j);
        }
    }

    private NetworkRequest sendRequestForNetwork(NetworkCapabilities networkcapabilities, NetworkCallback networkcallback, int i, int j, int k, CallbackHandler callbackhandler)
    {
        HashMap hashmap;
        Messenger messenger;
        checkCallbackNotNull(networkcallback);
        boolean flag;
        if(j == 2 || networkcapabilities != null)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "null NetworkCapabilities");
        hashmap = sCallbacks;
        hashmap;
        JVM INSTR monitorenter ;
        if(NetworkCallback._2D_get0(networkcallback) != null && NetworkCallback._2D_get0(networkcallback) != ALREADY_UNREGISTERED)
            Log.e("ConnectivityManager", "NetworkCallback was already registered");
        messenger = JVM INSTR new #758 <Class Messenger>;
        messenger.Messenger(callbackhandler);
        callbackhandler = JVM INSTR new #357 <Class Binder>;
        callbackhandler.Binder();
        if(j != 1) goto _L2; else goto _L1
_L1:
        networkcapabilities = mService.listenForNetwork(networkcapabilities, messenger, callbackhandler);
_L4:
        if(networkcapabilities == null)
            break MISSING_BLOCK_LABEL_115;
        sCallbacks.put(networkcapabilities, networkcallback);
        NetworkCallback._2D_set0(networkcallback, networkcapabilities);
        hashmap;
        JVM INSTR monitorexit ;
        return networkcapabilities;
_L2:
        networkcapabilities = mService.requestNetwork(networkcapabilities, messenger, i, callbackhandler, k);
        if(true) goto _L4; else goto _L3
_L3:
        networkcapabilities;
        hashmap;
        JVM INSTR monitorexit ;
        throw networkcapabilities;
        networkcapabilities;
        throw networkcapabilities.rethrowFromSystemServer();
        networkcapabilities;
        throw convertServiceException(networkcapabilities);
    }

    public static boolean setProcessDefaultNetwork(Network network)
    {
        int i;
        if(network == null)
            i = 0;
        else
            i = network.netId;
        if(i == NetworkUtils.getBoundNetworkForProcess())
            return true;
        if(NetworkUtils.bindProcessToNetwork(i))
        {
            try
            {
                Proxy.setHttpProxySystemProperty(getInstance().getDefaultProxy());
            }
            // Misplaced declaration of an exception variable
            catch(Network network)
            {
                Log.e("ConnectivityManager", "Can't set proxy properties", network);
            }
            InetAddress.clearDnsCache();
            NetworkEventDispatcher.getInstance().onNetworkConfigurationChanged();
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean setProcessDefaultNetworkForHostResolution(Network network)
    {
        int i;
        if(network == null)
            i = 0;
        else
            i = network.netId;
        return NetworkUtils.bindProcessToNetworkForHostResolution(i);
    }

    private void unsupportedStartingFrom(int i)
    {
        if(Process.myUid() == 1000)
            return;
        if(mContext.getApplicationInfo().targetSdkVersion >= i)
            throw new UnsupportedOperationException((new StringBuilder()).append("This method is not supported in target SDK version ").append(i).append(" and above").toString());
        else
            return;
    }

    public void addDefaultNetworkActiveListener(final OnNetworkActiveListener l)
    {
        android.os.INetworkActivityListener.Stub stub = new android.os.INetworkActivityListener.Stub() {

            public void onNetworkActive()
                throws RemoteException
            {
                l.onNetworkActive();
            }

            final ConnectivityManager this$0;
            final OnNetworkActiveListener val$l;

            
            {
                this$0 = ConnectivityManager.this;
                l = onnetworkactivelistener;
                super();
            }
        }
;
        try
        {
            getNetworkManagementService().registerNetworkActivityListener(stub);
            mNetworkActivityListeners.put(l, stub);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(final OnNetworkActiveListener l)
        {
            throw l.rethrowFromSystemServer();
        }
    }

    public boolean bindProcessToNetwork(Network network)
    {
        return setProcessDefaultNetwork(network);
    }

    public int checkMobileProvisioning(int i)
    {
        try
        {
            i = mService.checkMobileProvisioning(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public void factoryReset()
    {
        try
        {
            mService.factoryReset();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public LinkProperties getActiveLinkProperties()
    {
        LinkProperties linkproperties;
        try
        {
            linkproperties = mService.getActiveLinkProperties();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return linkproperties;
    }

    public Network getActiveNetwork()
    {
        Network network;
        try
        {
            network = mService.getActiveNetwork();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return network;
    }

    public Network getActiveNetworkForUid(int i)
    {
        return getActiveNetworkForUid(i, false);
    }

    public Network getActiveNetworkForUid(int i, boolean flag)
    {
        Network network;
        try
        {
            network = mService.getActiveNetworkForUid(i, flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return network;
    }

    public NetworkInfo getActiveNetworkInfo()
    {
        NetworkInfo networkinfo;
        try
        {
            networkinfo = mService.getActiveNetworkInfo();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return networkinfo;
    }

    public NetworkInfo getActiveNetworkInfoForUid(int i)
    {
        return getActiveNetworkInfoForUid(i, false);
    }

    public NetworkInfo getActiveNetworkInfoForUid(int i, boolean flag)
    {
        NetworkInfo networkinfo;
        try
        {
            networkinfo = mService.getActiveNetworkInfoForUid(i, flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return networkinfo;
    }

    public NetworkQuotaInfo getActiveNetworkQuotaInfo()
    {
        NetworkQuotaInfo networkquotainfo;
        try
        {
            networkquotainfo = mService.getActiveNetworkQuotaInfo();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return networkquotainfo;
    }

    public NetworkInfo[] getAllNetworkInfo()
    {
        NetworkInfo anetworkinfo[];
        try
        {
            anetworkinfo = mService.getAllNetworkInfo();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return anetworkinfo;
    }

    public Network[] getAllNetworks()
    {
        Network anetwork[];
        try
        {
            anetwork = mService.getAllNetworks();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return anetwork;
    }

    public String getAlwaysOnVpnPackageForUser(int i)
    {
        String s;
        try
        {
            s = mService.getAlwaysOnVpnPackage(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public boolean getBackgroundDataSetting()
    {
        return true;
    }

    public Network getBoundNetworkForProcess()
    {
        return getProcessDefaultNetwork();
    }

    public String getCaptivePortalServerUrl()
    {
        String s;
        try
        {
            s = mService.getCaptivePortalServerUrl();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public NetworkCapabilities[] getDefaultNetworkCapabilitiesForUser(int i)
    {
        NetworkCapabilities anetworkcapabilities[];
        try
        {
            anetworkcapabilities = mService.getDefaultNetworkCapabilitiesForUser(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return anetworkcapabilities;
    }

    public ProxyInfo getDefaultProxy()
    {
        return getProxyForNetwork(getBoundNetworkForProcess());
    }

    public ProxyInfo getGlobalProxy()
    {
        ProxyInfo proxyinfo;
        try
        {
            proxyinfo = mService.getGlobalProxy();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return proxyinfo;
    }

    public int getLastTetherError(String s)
    {
        int i;
        try
        {
            i = mService.getLastTetherError(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public LinkProperties getLinkProperties(int i)
    {
        LinkProperties linkproperties;
        try
        {
            linkproperties = mService.getLinkPropertiesForType(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return linkproperties;
    }

    public LinkProperties getLinkProperties(Network network)
    {
        try
        {
            network = mService.getLinkProperties(network);
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
        return network;
    }

    public boolean getMobileDataEnabled()
    {
        Object obj = ServiceManager.getService("phone");
        if(obj != null)
        {
            boolean flag;
            try
            {
                obj = com.android.internal.telephony.ITelephony.Stub.asInterface(((android.os.IBinder) (obj)));
                int i = SubscriptionManager.getDefaultDataSubscriptionId();
                StringBuilder stringbuilder = JVM INSTR new #402 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("ConnectivityManager", stringbuilder.append("getMobileDataEnabled()+ subId=").append(i).toString());
                flag = ((ITelephony) (obj)).getDataEnabled(i);
                obj = JVM INSTR new #402 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                Log.d("ConnectivityManager", ((StringBuilder) (obj)).append("getMobileDataEnabled()- subId=").append(i).append(" retVal=").append(flag).toString());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            Log.d("ConnectivityManager", "getMobileDataEnabled()- remote exception retVal=false");
            return false;
        }
    }

    public String getMobileProvisioningUrl()
    {
        String s;
        try
        {
            s = mService.getMobileProvisioningUrl();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public int getMultipathPreference(Network network)
    {
        int i;
        try
        {
            i = mService.getMultipathPreference(network);
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
        return i;
    }

    public NetworkCapabilities getNetworkCapabilities(Network network)
    {
        try
        {
            network = mService.getNetworkCapabilities(network);
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
        return network;
    }

    public Network getNetworkForType(int i)
    {
        Network network;
        try
        {
            network = mService.getNetworkForType(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return network;
    }

    public NetworkInfo getNetworkInfo(int i)
    {
        NetworkInfo networkinfo;
        try
        {
            networkinfo = mService.getNetworkInfo(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return networkinfo;
    }

    public NetworkInfo getNetworkInfo(Network network)
    {
        return getNetworkInfoForUid(network, Process.myUid(), false);
    }

    public NetworkInfo getNetworkInfoForUid(Network network, int i, boolean flag)
    {
        try
        {
            network = mService.getNetworkInfoForUid(network, i, flag);
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
        return network;
    }

    public int getNetworkPreference()
    {
        return -1;
    }

    public ProxyInfo getProxyForNetwork(Network network)
    {
        try
        {
            network = mService.getProxyForNetwork(network);
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
        return network;
    }

    public int getRestrictBackgroundStatus()
    {
        int i;
        try
        {
            i = getNetworkPolicyManager().getRestrictBackgroundByCaller();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public String[] getTetherableBluetoothRegexs()
    {
        String as[];
        try
        {
            as = mService.getTetherableBluetoothRegexs();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public String[] getTetherableIfaces()
    {
        String as[];
        try
        {
            as = mService.getTetherableIfaces();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public String[] getTetherableUsbRegexs()
    {
        String as[];
        try
        {
            as = mService.getTetherableUsbRegexs();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public String[] getTetherableWifiRegexs()
    {
        String as[];
        try
        {
            as = mService.getTetherableWifiRegexs();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public String[] getTetheredDhcpRanges()
    {
        String as[];
        try
        {
            as = mService.getTetheredDhcpRanges();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public String[] getTetheredIfaces()
    {
        String as[];
        try
        {
            as = mService.getTetheredIfaces();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public String[] getTetheringErroredIfaces()
    {
        String as[];
        try
        {
            as = mService.getTetheringErroredIfaces();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public boolean isActiveNetworkMetered()
    {
        boolean flag;
        try
        {
            flag = mService.isActiveNetworkMetered();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isAlwaysOnVpnPackageSupportedForUser(int i, String s)
    {
        boolean flag;
        try
        {
            flag = mService.isAlwaysOnVpnPackageSupported(i, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isDefaultNetworkActive()
    {
        boolean flag;
        try
        {
            flag = getNetworkManagementService().isNetworkActive();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isNetworkSupported(int i)
    {
        boolean flag;
        try
        {
            flag = mService.isNetworkSupported(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isTetheringSupported()
    {
        String s = mContext.getOpPackageName();
        boolean flag;
        try
        {
            flag = mService.isTetheringSupported(s);
        }
        catch(SecurityException securityexception)
        {
            return false;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void registerDefaultNetworkCallback(NetworkCallback networkcallback)
    {
        registerDefaultNetworkCallback(networkcallback, ((Handler) (getDefaultHandler())));
    }

    public void registerDefaultNetworkCallback(NetworkCallback networkcallback, Handler handler)
    {
        sendRequestForNetwork(null, networkcallback, 0, 2, -1, new CallbackHandler(handler));
    }

    public int registerNetworkAgent(Messenger messenger, NetworkInfo networkinfo, LinkProperties linkproperties, NetworkCapabilities networkcapabilities, int i, NetworkMisc networkmisc)
    {
        try
        {
            i = mService.registerNetworkAgent(messenger, networkinfo, linkproperties, networkcapabilities, i, networkmisc);
        }
        // Misplaced declaration of an exception variable
        catch(Messenger messenger)
        {
            throw messenger.rethrowFromSystemServer();
        }
        return i;
    }

    public void registerNetworkCallback(NetworkRequest networkrequest, PendingIntent pendingintent)
    {
        checkPendingIntentNotNull(pendingintent);
        try
        {
            mService.pendingListenForNetwork(networkrequest.networkCapabilities, pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(NetworkRequest networkrequest)
        {
            throw networkrequest.rethrowFromSystemServer();
        }
        // Misplaced declaration of an exception variable
        catch(NetworkRequest networkrequest)
        {
            throw convertServiceException(networkrequest);
        }
    }

    public void registerNetworkCallback(NetworkRequest networkrequest, NetworkCallback networkcallback)
    {
        registerNetworkCallback(networkrequest, networkcallback, ((Handler) (getDefaultHandler())));
    }

    public void registerNetworkCallback(NetworkRequest networkrequest, NetworkCallback networkcallback, Handler handler)
    {
        handler = new CallbackHandler(handler);
        sendRequestForNetwork(networkrequest.networkCapabilities, networkcallback, 0, 1, -1, handler);
    }

    public void registerNetworkFactory(Messenger messenger, String s)
    {
        try
        {
            mService.registerNetworkFactory(messenger, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Messenger messenger)
        {
            throw messenger.rethrowFromSystemServer();
        }
    }

    public void releaseNetworkRequest(PendingIntent pendingintent)
    {
        checkPendingIntentNotNull(pendingintent);
        try
        {
            mService.releasePendingNetworkRequest(pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void removeDefaultNetworkActiveListener(OnNetworkActiveListener onnetworkactivelistener)
    {
        onnetworkactivelistener = (INetworkActivityListener)mNetworkActivityListeners.get(onnetworkactivelistener);
        boolean flag;
        if(onnetworkactivelistener != null)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "Listener was not registered.");
        try
        {
            getNetworkManagementService().unregisterNetworkActivityListener(onnetworkactivelistener);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(OnNetworkActiveListener onnetworkactivelistener)
        {
            throw onnetworkactivelistener.rethrowFromSystemServer();
        }
    }

    public void reportBadNetwork(Network network)
    {
        try
        {
            mService.reportNetworkConnectivity(network, true);
            mService.reportNetworkConnectivity(network, false);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
    }

    public void reportInetCondition(int i, int j)
    {
        try
        {
            mService.reportInetCondition(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void reportNetworkConnectivity(Network network, boolean flag)
    {
        try
        {
            mService.reportNetworkConnectivity(network, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
    }

    public boolean requestBandwidthUpdate(Network network)
    {
        boolean flag;
        try
        {
            flag = mService.requestBandwidthUpdate(network);
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
        return flag;
    }

    public void requestNetwork(NetworkRequest networkrequest, PendingIntent pendingintent)
    {
        checkPendingIntentNotNull(pendingintent);
        try
        {
            mService.pendingRequestForNetwork(networkrequest.networkCapabilities, pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(NetworkRequest networkrequest)
        {
            throw networkrequest.rethrowFromSystemServer();
        }
        // Misplaced declaration of an exception variable
        catch(NetworkRequest networkrequest)
        {
            throw convertServiceException(networkrequest);
        }
    }

    public void requestNetwork(NetworkRequest networkrequest, NetworkCallback networkcallback)
    {
        requestNetwork(networkrequest, networkcallback, ((Handler) (getDefaultHandler())));
    }

    public void requestNetwork(NetworkRequest networkrequest, NetworkCallback networkcallback, int i)
    {
        checkTimeout(i);
        requestNetwork(networkrequest, networkcallback, i, inferLegacyTypeForNetworkCapabilities(networkrequest.networkCapabilities), ((Handler) (getDefaultHandler())));
    }

    public void requestNetwork(NetworkRequest networkrequest, NetworkCallback networkcallback, int i, int j, Handler handler)
    {
        handler = new CallbackHandler(handler);
        sendRequestForNetwork(networkrequest.networkCapabilities, networkcallback, i, 2, j, handler);
    }

    public void requestNetwork(NetworkRequest networkrequest, NetworkCallback networkcallback, Handler handler)
    {
        requestNetwork(networkrequest, networkcallback, 0, inferLegacyTypeForNetworkCapabilities(networkrequest.networkCapabilities), ((Handler) (new CallbackHandler(handler))));
    }

    public void requestNetwork(NetworkRequest networkrequest, NetworkCallback networkcallback, Handler handler, int i)
    {
        checkTimeout(i);
        requestNetwork(networkrequest, networkcallback, i, inferLegacyTypeForNetworkCapabilities(networkrequest.networkCapabilities), ((Handler) (new CallbackHandler(handler))));
    }

    public boolean requestRouteToHost(int i, int j)
    {
        return requestRouteToHostAddress(i, NetworkUtils.intToInetAddress(j));
    }

    public boolean requestRouteToHostAddress(int i, InetAddress inetaddress)
    {
        checkLegacyRoutingApiAccess();
        boolean flag;
        try
        {
            flag = mService.requestRouteToHostAddress(i, inetaddress.getAddress());
        }
        // Misplaced declaration of an exception variable
        catch(InetAddress inetaddress)
        {
            throw inetaddress.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setAcceptUnvalidated(Network network, boolean flag, boolean flag1)
    {
        try
        {
            mService.setAcceptUnvalidated(network, flag, flag1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
    }

    public void setAirplaneMode(boolean flag)
    {
        try
        {
            mService.setAirplaneMode(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public boolean setAlwaysOnVpnPackageForUser(int i, String s, boolean flag)
    {
        try
        {
            flag = mService.setAlwaysOnVpnPackage(i, s, flag);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setAvoidUnvalidated(Network network)
    {
        try
        {
            mService.setAvoidUnvalidated(network);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
    }

    public void setBackgroundDataSetting(boolean flag)
    {
    }

    public void setGlobalProxy(ProxyInfo proxyinfo)
    {
        try
        {
            mService.setGlobalProxy(proxyinfo);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ProxyInfo proxyinfo)
        {
            throw proxyinfo.rethrowFromSystemServer();
        }
    }

    public void setNetworkPreference(int i)
    {
    }

    public void setProvisioningNotificationVisible(boolean flag, int i, String s)
    {
        try
        {
            mService.setProvisioningNotificationVisible(flag, i, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public int setUsbTethering(boolean flag)
    {
        int i;
        try
        {
            String s = mContext.getOpPackageName();
            StringBuilder stringbuilder = JVM INSTR new #402 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("ConnectivityManager", stringbuilder.append("setUsbTethering caller:").append(s).toString());
            i = mService.setUsbTethering(flag, s);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public void startCaptivePortalApp(Network network)
    {
        try
        {
            mService.startCaptivePortalApp(network);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            throw network.rethrowFromSystemServer();
        }
    }

    public PacketKeepalive startNattKeepalive(Network network, int i, PacketKeepaliveCallback packetkeepalivecallback, InetAddress inetaddress, int j, InetAddress inetaddress1)
    {
        packetkeepalivecallback = new PacketKeepalive(network, packetkeepalivecallback, null);
        try
        {
            IConnectivityManager iconnectivitymanager = mService;
            Messenger messenger = PacketKeepalive._2D_get1(packetkeepalivecallback);
            Binder binder = JVM INSTR new #357 <Class Binder>;
            binder.Binder();
            iconnectivitymanager.startNattKeepalive(network, i, messenger, binder, inetaddress.getHostAddress(), j, inetaddress1.getHostAddress());
        }
        // Misplaced declaration of an exception variable
        catch(Network network)
        {
            Log.e("ConnectivityManager", "Error starting packet keepalive: ", network);
            packetkeepalivecallback.stopLooper();
            return null;
        }
        return packetkeepalivecallback;
    }

    public void startTethering(int i, boolean flag, OnStartTetheringCallback onstarttetheringcallback)
    {
        startTethering(i, flag, onstarttetheringcallback, null);
    }

    public void startTethering(int i, boolean flag, OnStartTetheringCallback onstarttetheringcallback, final Handler final_handler)
    {
        Preconditions.checkNotNull(onstarttetheringcallback, "OnStartTetheringCallback cannot be null.");
        onstarttetheringcallback = new ResultReceiver(onstarttetheringcallback) {

            protected void onReceiveResult(int j, Bundle bundle)
            {
                if(j == 0)
                    callback.onTetheringStarted();
                else
                    callback.onTetheringFailed();
            }

            final ConnectivityManager this$0;
            final OnStartTetheringCallback val$callback;

            
            {
                this$0 = ConnectivityManager.this;
                callback = onstarttetheringcallback;
                super(final_handler);
            }
        }
;
        final_handler = mContext.getOpPackageName();
        StringBuilder stringbuilder = JVM INSTR new #402 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("ConnectivityManager", stringbuilder.append("startTethering caller:").append(final_handler).toString());
        mService.startTethering(i, onstarttetheringcallback, flag, final_handler);
_L1:
        return;
        final_handler;
        Log.e("ConnectivityManager", "Exception trying to start tethering.", final_handler);
        onstarttetheringcallback.send(2, null);
          goto _L1
    }

    public int startUsingNetworkFeature(int i, String s)
    {
        return startUsingNetworkFeature(i, s, -1);
    }

    public int startUsingNetworkFeature(int i, String s, int j)
    {
        Object obj;
        checkLegacyRoutingApiAccess();
        obj = networkCapabilitiesForFeature(i, s);
        if(obj == null)
        {
            Log.d("ConnectivityManager", (new StringBuilder()).append("Can't satisfy startUsingNetworkFeature for ").append(i).append(", ").append(s).toString());
            return 3;
        }
        if(j != -1)
        {
            s = SubscriptionManager.getSubId(j);
            if(s != null && s.length > 0)
                ((NetworkCapabilities) (obj)).setNetworkSpecifier(((NetworkCapabilities) (obj)).getNetworkSpecifier());
        }
        s = sLegacyRequests;
        s;
        JVM INSTR monitorenter ;
        LegacyRequest legacyrequest = (LegacyRequest)sLegacyRequests.get(obj);
        if(legacyrequest == null)
            break MISSING_BLOCK_LABEL_170;
        obj = JVM INSTR new #402 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.d("ConnectivityManager", ((StringBuilder) (obj)).append("renewing startUsingNetworkFeature request ").append(legacyrequest.networkRequest).toString());
        renewRequestLocked(legacyrequest);
        obj = legacyrequest.currentNetwork;
        if(obj != null)
            return 0;
        s;
        JVM INSTR monitorexit ;
        return 1;
        obj = requestNetworkForFeatureLocked(((NetworkCapabilities) (obj)));
        s;
        JVM INSTR monitorexit ;
        Exception exception;
        if(obj != null)
        {
            Log.d("ConnectivityManager", (new StringBuilder()).append("starting startUsingNetworkFeature for request ").append(obj).toString());
            return 1;
        } else
        {
            Log.d("ConnectivityManager", " request Failed");
            return 3;
        }
        exception;
        throw exception;
    }

    public void stopTethering(int i)
    {
        try
        {
            String s = mContext.getOpPackageName();
            StringBuilder stringbuilder = JVM INSTR new #402 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("ConnectivityManager", stringbuilder.append("stopTethering caller:").append(s).toString());
            mService.stopTethering(i, s);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int stopUsingNetworkFeature(int i, String s)
    {
        return stopUsingNetworkFeature(i, s, -1);
    }

    public int stopUsingNetworkFeature(int i, String s, int j)
    {
        checkLegacyRoutingApiAccess();
        NetworkCapabilities networkcapabilities = networkCapabilitiesForFeature(i, s);
        if(networkcapabilities == null)
        {
            Log.d("ConnectivityManager", (new StringBuilder()).append("Can't satisfy stopUsingNetworkFeature for ").append(i).append(", ").append(s).toString());
            return -1;
        }
        if(j != -1)
        {
            int ai[] = SubscriptionManager.getSubId(j);
            if(ai != null && ai.length > 0)
                networkcapabilities.setNetworkSpecifier(networkcapabilities.getNetworkSpecifier());
        }
        if(removeRequestForFeature(networkcapabilities))
            Log.d("ConnectivityManager", (new StringBuilder()).append("stopUsingNetworkFeature for ").append(i).append(", ").append(s).toString());
        return 1;
    }

    public int tether(String s)
    {
        int i;
        try
        {
            String s1 = mContext.getOpPackageName();
            StringBuilder stringbuilder = JVM INSTR new #402 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("ConnectivityManager", stringbuilder.append("tether caller:").append(s1).toString());
            i = mService.tether(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public void unregisterNetworkCallback(PendingIntent pendingintent)
    {
        checkPendingIntentNotNull(pendingintent);
        releaseNetworkRequest(pendingintent);
    }

    public void unregisterNetworkCallback(NetworkCallback networkcallback)
    {
        boolean flag;
        ArrayList arraylist;
        flag = true;
        checkCallbackNotNull(networkcallback);
        arraylist = new ArrayList();
        HashMap hashmap = sCallbacks;
        hashmap;
        JVM INSTR monitorenter ;
        boolean flag1;
        Object obj;
        if(NetworkCallback._2D_get0(networkcallback) != null)
            flag1 = true;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1, "NetworkCallback was not registered");
        if(NetworkCallback._2D_get0(networkcallback) != ALREADY_UNREGISTERED)
            flag1 = flag;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1, "NetworkCallback was already unregistered");
        obj = sCallbacks.entrySet().iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
            if(entry.getValue() == networkcallback)
                arraylist.add((NetworkRequest)entry.getKey());
        } while(true);
        break MISSING_BLOCK_LABEL_145;
        networkcallback;
        throw networkcallback;
        Iterator iterator = arraylist.iterator();
_L2:
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj = (NetworkRequest)iterator.next();
        mService.releaseNetworkRequest(((NetworkRequest) (obj)));
        sCallbacks.remove(obj);
        if(true) goto _L2; else goto _L1
        networkcallback;
        throw networkcallback.rethrowFromSystemServer();
_L1:
        NetworkCallback._2D_set0(networkcallback, ALREADY_UNREGISTERED);
        hashmap;
        JVM INSTR monitorexit ;
    }

    public void unregisterNetworkFactory(Messenger messenger)
    {
        try
        {
            mService.unregisterNetworkFactory(messenger);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Messenger messenger)
        {
            throw messenger.rethrowFromSystemServer();
        }
    }

    public int untether(String s)
    {
        int i;
        try
        {
            String s1 = mContext.getOpPackageName();
            StringBuilder stringbuilder = JVM INSTR new #402 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("ConnectivityManager", stringbuilder.append("untether caller:").append(s1).toString());
            i = mService.untether(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public boolean updateLockdownVpn()
    {
        boolean flag;
        try
        {
            flag = mService.updateLockdownVpn();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public static final String ACTION_BACKGROUND_DATA_SETTING_CHANGED = "android.net.conn.BACKGROUND_DATA_SETTING_CHANGED";
    public static final String ACTION_CAPTIVE_PORTAL_SIGN_IN = "android.net.conn.CAPTIVE_PORTAL";
    public static final String ACTION_CAPTIVE_PORTAL_TEST_COMPLETED = "android.net.conn.CAPTIVE_PORTAL_TEST_COMPLETED";
    public static final String ACTION_DATA_ACTIVITY_CHANGE = "android.net.conn.DATA_ACTIVITY_CHANGE";
    public static final String ACTION_PROMPT_LOST_VALIDATION = "android.net.conn.PROMPT_LOST_VALIDATION";
    public static final String ACTION_PROMPT_UNVALIDATED = "android.net.conn.PROMPT_UNVALIDATED";
    public static final String ACTION_RESTRICT_BACKGROUND_CHANGED = "android.net.conn.RESTRICT_BACKGROUND_CHANGED";
    public static final String ACTION_TETHER_STATE_CHANGED = "android.net.conn.TETHER_STATE_CHANGED";
    private static final NetworkRequest ALREADY_UNREGISTERED = (new NetworkRequest.Builder()).clearCapabilities().build();
    private static final int BASE = 0x80000;
    public static final int CALLBACK_AVAILABLE = 0x80002;
    public static final int CALLBACK_CAP_CHANGED = 0x80006;
    public static final int CALLBACK_IP_CHANGED = 0x80007;
    public static final int CALLBACK_LOSING = 0x80003;
    public static final int CALLBACK_LOST = 0x80004;
    public static final int CALLBACK_PRECHECK = 0x80001;
    public static final int CALLBACK_RESUMED = 0x8000a;
    public static final int CALLBACK_SUSPENDED = 0x80009;
    public static final int CALLBACK_UNAVAIL = 0x80005;
    public static final String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String CONNECTIVITY_ACTION_SUPL = "android.net.conn.CONNECTIVITY_CHANGE_SUPL";
    public static final int DEFAULT_NETWORK_PREFERENCE = 1;
    private static final int EXPIRE_LEGACY_REQUEST = 0x80008;
    public static final String EXTRA_ACTIVE_LOCAL_ONLY = "localOnlyArray";
    public static final String EXTRA_ACTIVE_TETHER = "tetherArray";
    public static final String EXTRA_ADD_TETHER_TYPE = "extraAddTetherType";
    public static final String EXTRA_AVAILABLE_TETHER = "availableArray";
    public static final String EXTRA_CAPTIVE_PORTAL = "android.net.extra.CAPTIVE_PORTAL";
    public static final String EXTRA_CAPTIVE_PORTAL_URL = "android.net.extra.CAPTIVE_PORTAL_URL";
    public static final String EXTRA_CAPTIVE_PORTAL_USER_AGENT = "android.net.extra.CAPTIVE_PORTAL_USER_AGENT";
    public static final String EXTRA_DEVICE_TYPE = "deviceType";
    public static final String EXTRA_ERRORED_TETHER = "erroredArray";
    public static final String EXTRA_EXTRA_INFO = "extraInfo";
    public static final String EXTRA_INET_CONDITION = "inetCondition";
    public static final String EXTRA_IS_ACTIVE = "isActive";
    public static final String EXTRA_IS_CAPTIVE_PORTAL = "captivePortal";
    public static final String EXTRA_IS_FAILOVER = "isFailover";
    public static final String EXTRA_NETWORK = "android.net.extra.NETWORK";
    public static final String EXTRA_NETWORK_INFO = "networkInfo";
    public static final String EXTRA_NETWORK_REQUEST = "android.net.extra.NETWORK_REQUEST";
    public static final String EXTRA_NETWORK_TYPE = "networkType";
    public static final String EXTRA_NO_CONNECTIVITY = "noConnectivity";
    public static final String EXTRA_OTHER_NETWORK_INFO = "otherNetwork";
    public static final String EXTRA_PROVISION_CALLBACK = "extraProvisionCallback";
    public static final String EXTRA_REALTIME_NS = "tsNanos";
    public static final String EXTRA_REASON = "reason";
    public static final String EXTRA_REM_TETHER_TYPE = "extraRemTetherType";
    public static final String EXTRA_RUN_PROVISION = "extraRunProvision";
    public static final String EXTRA_SET_ALARM = "extraSetAlarm";
    public static final String INET_CONDITION_ACTION = "android.net.conn.INET_CONDITION_ACTION";
    private static final int LISTEN = 1;
    public static final int MAX_NETWORK_TYPE = 17;
    public static final int MAX_RADIO_TYPE = 17;
    private static final int MIN_NETWORK_TYPE = 0;
    public static final int MULTIPATH_PREFERENCE_HANDOVER = 1;
    public static final int MULTIPATH_PREFERENCE_PERFORMANCE = 4;
    public static final int MULTIPATH_PREFERENCE_RELIABILITY = 2;
    public static final int MULTIPATH_PREFERENCE_UNMETERED = 7;
    public static final int NETID_UNSET = 0;
    private static final int REQUEST = 2;
    public static final int REQUEST_ID_UNSET = 0;
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;
    private static final String TAG = "ConnectivityManager";
    public static final int TETHERING_BLUETOOTH = 2;
    public static final int TETHERING_INVALID = -1;
    public static final int TETHERING_USB = 1;
    public static final int TETHERING_WIFI = 0;
    public static final int TETHERING_WIGIG = 3;
    public static final String TETHER_CONNECT_STATE_CHANGED = "codeaurora.net.conn.TETHER_CONNECT_STATE_CHANGED";
    public static final int TETHER_ERROR_DISABLE_NAT_ERROR = 9;
    public static final int TETHER_ERROR_ENABLE_NAT_ERROR = 8;
    public static final int TETHER_ERROR_IFACE_CFG_ERROR = 10;
    public static final int TETHER_ERROR_MASTER_ERROR = 5;
    public static final int TETHER_ERROR_NO_ERROR = 0;
    public static final int TETHER_ERROR_PROVISION_FAILED = 11;
    public static final int TETHER_ERROR_SERVICE_UNAVAIL = 2;
    public static final int TETHER_ERROR_TETHER_IFACE_ERROR = 6;
    public static final int TETHER_ERROR_UNAVAIL_IFACE = 4;
    public static final int TETHER_ERROR_UNKNOWN_IFACE = 1;
    public static final int TETHER_ERROR_UNSUPPORTED = 3;
    public static final int TETHER_ERROR_UNTETHER_IFACE_ERROR = 7;
    public static final int TYPE_BLUETOOTH = 7;
    public static final int TYPE_DUMMY = 8;
    public static final int TYPE_ETHERNET = 9;
    public static final int TYPE_MOBILE = 0;
    public static final int TYPE_MOBILE_CBS = 12;
    public static final int TYPE_MOBILE_DUN = 4;
    public static final int TYPE_MOBILE_EMERGENCY = 15;
    public static final int TYPE_MOBILE_FOTA = 10;
    public static final int TYPE_MOBILE_HIPRI = 5;
    public static final int TYPE_MOBILE_IA = 14;
    public static final int TYPE_MOBILE_IMS = 11;
    public static final int TYPE_MOBILE_MMS = 2;
    public static final int TYPE_MOBILE_SUPL = 3;
    public static final int TYPE_NONE = -1;
    public static final int TYPE_PROXY = 16;
    public static final int TYPE_VPN = 17;
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_WIFI_P2P = 13;
    public static final int TYPE_WIMAX = 6;
    private static CallbackHandler sCallbackHandler;
    private static final HashMap sCallbacks = new HashMap();
    private static ConnectivityManager sInstance;
    private static HashMap sLegacyRequests = new HashMap();
    private static final SparseIntArray sLegacyTypeToCapability;
    private static final SparseIntArray sLegacyTypeToTransport;
    private final Context mContext;
    private INetworkManagementService mNMService;
    private INetworkPolicyManager mNPManager;
    private final ArrayMap mNetworkActivityListeners = new ArrayMap();
    private final IConnectivityManager mService;

    static 
    {
        sLegacyTypeToTransport = new SparseIntArray();
        sLegacyTypeToTransport.put(0, 0);
        sLegacyTypeToTransport.put(12, 0);
        sLegacyTypeToTransport.put(4, 0);
        sLegacyTypeToTransport.put(10, 0);
        sLegacyTypeToTransport.put(5, 0);
        sLegacyTypeToTransport.put(11, 0);
        sLegacyTypeToTransport.put(2, 0);
        sLegacyTypeToTransport.put(3, 0);
        sLegacyTypeToTransport.put(1, 1);
        sLegacyTypeToTransport.put(13, 1);
        sLegacyTypeToTransport.put(7, 2);
        sLegacyTypeToTransport.put(9, 3);
        sLegacyTypeToCapability = new SparseIntArray();
        sLegacyTypeToCapability.put(12, 5);
        sLegacyTypeToCapability.put(4, 2);
        sLegacyTypeToCapability.put(10, 3);
        sLegacyTypeToCapability.put(11, 4);
        sLegacyTypeToCapability.put(2, 0);
        sLegacyTypeToCapability.put(3, 1);
        sLegacyTypeToCapability.put(13, 6);
    }

    // Unreferenced inner class android/net/ConnectivityManager$LegacyRequest$1

/* anonymous class */
    class LegacyRequest._cls1 extends NetworkCallback
    {

        public void onAvailable(Network network)
        {
            currentNetwork = network;
            Log.d("ConnectivityManager", (new StringBuilder()).append("startUsingNetworkFeature got Network:").append(network).toString());
            ConnectivityManager.setProcessDefaultNetworkForHostResolution(network);
        }

        public void onLost(Network network)
        {
            if(network.equals(currentNetwork))
                LegacyRequest._2D_wrap0(LegacyRequest.this);
            Log.d("ConnectivityManager", (new StringBuilder()).append("startUsingNetworkFeature lost Network:").append(network).toString());
        }

        final LegacyRequest this$1;

            
            {
                this$1 = LegacyRequest.this;
                super();
            }
    }


    // Unreferenced inner class android/net/ConnectivityManager$PacketKeepalive$1

/* anonymous class */
    class PacketKeepalive._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 528397 528397: default 24
        //                       528397 56;
               goto _L1 _L2
_L1:
            Log.e("PacketKeepalive", (new StringBuilder()).append("Unhandled message ").append(Integer.toHexString(message.what)).toString());
_L7:
            return;
_L2:
            int i = message.arg2;
            if(i != 0) goto _L4; else goto _L3
_L3:
            if(PacketKeepalive._2D_get2(PacketKeepalive.this) != null) goto _L6; else goto _L5
_L5:
            PacketKeepalive._2D_set0(PacketKeepalive.this, Integer.valueOf(message.arg1));
            PacketKeepalive._2D_get0(PacketKeepalive.this).onStarted();
              goto _L7
_L6:
            try
            {
                PacketKeepalive._2D_set0(PacketKeepalive.this, null);
                stopLooper();
                PacketKeepalive._2D_get0(PacketKeepalive.this).onStopped();
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.e("PacketKeepalive", (new StringBuilder()).append("Exception in keepalive callback(").append(i).append(")").toString(), message);
            }
              goto _L7
_L4:
            stopLooper();
            PacketKeepalive._2D_get0(PacketKeepalive.this).onError(i);
              goto _L7
        }

        final PacketKeepalive this$1;

            
            {
                this$1 = PacketKeepalive.this;
                super(looper);
            }
    }

}
