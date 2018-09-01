// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.nsd;

import android.content.Context;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.util.AsyncChannel;
import com.android.internal.util.Preconditions;
import java.util.concurrent.CountDownLatch;

// Referenced classes of package android.net.nsd:
//            NsdServiceInfo, INsdManager

public final class NsdManager
{
    public static interface DiscoveryListener
    {

        public abstract void onDiscoveryStarted(String s);

        public abstract void onDiscoveryStopped(String s);

        public abstract void onServiceFound(NsdServiceInfo nsdserviceinfo);

        public abstract void onServiceLost(NsdServiceInfo nsdserviceinfo);

        public abstract void onStartDiscoveryFailed(String s, int i);

        public abstract void onStopDiscoveryFailed(String s, int i);
    }

    public static interface RegistrationListener
    {

        public abstract void onRegistrationFailed(NsdServiceInfo nsdserviceinfo, int i);

        public abstract void onServiceRegistered(NsdServiceInfo nsdserviceinfo);

        public abstract void onServiceUnregistered(NsdServiceInfo nsdserviceinfo);

        public abstract void onUnregistrationFailed(NsdServiceInfo nsdserviceinfo, int i);
    }

    public static interface ResolveListener
    {

        public abstract void onResolveFailed(NsdServiceInfo nsdserviceinfo, int i);

        public abstract void onServiceResolved(NsdServiceInfo nsdserviceinfo);
    }

    class ServiceHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            int i;
            int j;
            i = message.what;
            j = message.arg2;
            i;
            JVM INSTR tableswitch 69632 69636: default 44
        //                       69632 123
        //                       69633 44
        //                       69634 136
        //                       69635 44
        //                       69636 147;
               goto _L1 _L2 _L1 _L3 _L1 _L4
_L1:
            Object obj = NsdManager._2D_get4(NsdManager.this);
            obj;
            JVM INSTR monitorenter ;
            Object obj1;
            NsdServiceInfo nsdserviceinfo;
            obj1 = NsdManager._2D_get3(NsdManager.this).get(j);
            nsdserviceinfo = (NsdServiceInfo)NsdManager._2D_get5(NsdManager.this).get(j);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 == null)
            {
                Log.d(NsdManager._2D_get0(), (new StringBuilder()).append("Stale key ").append(message.arg2).toString());
                return;
            }
              goto _L5
_L2:
            NsdManager._2D_get1(NsdManager.this).sendMessage(0x11001);
            return;
_L3:
            NsdManager._2D_get2(NsdManager.this).countDown();
            return;
_L4:
            Log.e(NsdManager._2D_get0(), "Channel lost");
            return;
            message;
            throw message;
_L5:
            i;
            JVM INSTR tableswitch 393218 393236: default 256
        //                       393218 283
        //                       393219 308
        //                       393220 338
        //                       393221 358
        //                       393222 256
        //                       393223 378
        //                       393224 408
        //                       393225 256
        //                       393226 434
        //                       393227 461
        //                       393228 256
        //                       393229 481
        //                       393230 508
        //                       393231 256
        //                       393232 256
        //                       393233 256
        //                       393234 256
        //                       393235 534
        //                       393236 561;
               goto _L6 _L7 _L8 _L9 _L10 _L6 _L11 _L12 _L6 _L13 _L14 _L6 _L15 _L16 _L6 _L6 _L6 _L6 _L17 _L18
_L6:
            Log.d(NsdManager._2D_get0(), (new StringBuilder()).append("Ignored ").append(message).toString());
_L20:
            return;
_L7:
            message = NsdManager._2D_wrap0((NsdServiceInfo)message.obj);
            ((DiscoveryListener)obj1).onDiscoveryStarted(message);
            continue; /* Loop/switch isn't completed */
_L8:
            NsdManager._2D_wrap1(NsdManager.this, j);
            ((DiscoveryListener)obj1).onStartDiscoveryFailed(NsdManager._2D_wrap0(nsdserviceinfo), message.arg1);
            continue; /* Loop/switch isn't completed */
_L9:
            ((DiscoveryListener)obj1).onServiceFound((NsdServiceInfo)message.obj);
            continue; /* Loop/switch isn't completed */
_L10:
            ((DiscoveryListener)obj1).onServiceLost((NsdServiceInfo)message.obj);
            continue; /* Loop/switch isn't completed */
_L11:
            NsdManager._2D_wrap1(NsdManager.this, j);
            ((DiscoveryListener)obj1).onStopDiscoveryFailed(NsdManager._2D_wrap0(nsdserviceinfo), message.arg1);
            continue; /* Loop/switch isn't completed */
_L12:
            NsdManager._2D_wrap1(NsdManager.this, j);
            ((DiscoveryListener)obj1).onDiscoveryStopped(NsdManager._2D_wrap0(nsdserviceinfo));
            continue; /* Loop/switch isn't completed */
_L13:
            NsdManager._2D_wrap1(NsdManager.this, j);
            ((RegistrationListener)obj1).onRegistrationFailed(nsdserviceinfo, message.arg1);
            continue; /* Loop/switch isn't completed */
_L14:
            ((RegistrationListener)obj1).onServiceRegistered((NsdServiceInfo)message.obj);
            continue; /* Loop/switch isn't completed */
_L15:
            NsdManager._2D_wrap1(NsdManager.this, j);
            ((RegistrationListener)obj1).onUnregistrationFailed(nsdserviceinfo, message.arg1);
            continue; /* Loop/switch isn't completed */
_L16:
            NsdManager._2D_wrap1(NsdManager.this, message.arg2);
            ((RegistrationListener)obj1).onServiceUnregistered(nsdserviceinfo);
            continue; /* Loop/switch isn't completed */
_L17:
            NsdManager._2D_wrap1(NsdManager.this, j);
            ((ResolveListener)obj1).onResolveFailed(nsdserviceinfo, message.arg1);
            continue; /* Loop/switch isn't completed */
_L18:
            NsdManager._2D_wrap1(NsdManager.this, j);
            ((ResolveListener)obj1).onServiceResolved((NsdServiceInfo)message.obj);
            if(true) goto _L20; else goto _L19
_L19:
        }

        final NsdManager this$0;

        ServiceHandler(Looper looper)
        {
            this$0 = NsdManager.this;
            super(looper);
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static AsyncChannel _2D_get1(NsdManager nsdmanager)
    {
        return nsdmanager.mAsyncChannel;
    }

    static CountDownLatch _2D_get2(NsdManager nsdmanager)
    {
        return nsdmanager.mConnected;
    }

    static SparseArray _2D_get3(NsdManager nsdmanager)
    {
        return nsdmanager.mListenerMap;
    }

    static Object _2D_get4(NsdManager nsdmanager)
    {
        return nsdmanager.mMapLock;
    }

    static SparseArray _2D_get5(NsdManager nsdmanager)
    {
        return nsdmanager.mServiceMap;
    }

    static String _2D_wrap0(NsdServiceInfo nsdserviceinfo)
    {
        return getNsdServiceInfoType(nsdserviceinfo);
    }

    static void _2D_wrap1(NsdManager nsdmanager, int i)
    {
        nsdmanager.removeListener(i);
    }

    public NsdManager(Context context, INsdManager insdmanager)
    {
        mListenerKey = FIRST_LISTENER_KEY;
        mService = insdmanager;
        mContext = context;
        init();
    }

    private static void checkListener(Object obj)
    {
        Preconditions.checkNotNull(obj, "listener cannot be null");
    }

    private static void checkProtocol(int i)
    {
        boolean flag = true;
        if(i != 1)
            flag = false;
        Preconditions.checkArgument(flag, "Unsupported protocol");
    }

    private static void checkServiceInfo(NsdServiceInfo nsdserviceinfo)
    {
        Preconditions.checkNotNull(nsdserviceinfo, "NsdServiceInfo cannot be null");
        Preconditions.checkStringNotEmpty(nsdserviceinfo.getServiceName(), "Service name cannot be empty");
        Preconditions.checkStringNotEmpty(nsdserviceinfo.getServiceType(), "Service type cannot be empty");
    }

    private static void fatal(String s)
    {
        Log.e(TAG, s);
        throw new RuntimeException(s);
    }

    private int getListenerKey(Object obj)
    {
        checkListener(obj);
        Object obj1 = mMapLock;
        obj1;
        JVM INSTR monitorenter ;
        int i = mListenerMap.indexOfValue(obj);
        boolean flag;
        if(i != -1)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "listener not registered");
        i = mListenerMap.keyAt(i);
        obj1;
        JVM INSTR monitorexit ;
        return i;
        obj;
        throw obj;
    }

    private Messenger getMessenger()
    {
        Messenger messenger;
        try
        {
            messenger = mService.getMessenger();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return messenger;
    }

    private static String getNsdServiceInfoType(NsdServiceInfo nsdserviceinfo)
    {
        if(nsdserviceinfo == null)
            return "?";
        else
            return nsdserviceinfo.getServiceType();
    }

    private void init()
    {
        Messenger messenger = getMessenger();
        if(messenger == null)
            fatal("Failed to obtain service Messenger");
        HandlerThread handlerthread = new HandlerThread("NsdManager");
        handlerthread.start();
        mHandler = new ServiceHandler(handlerthread.getLooper());
        mAsyncChannel.connect(mContext, mHandler, messenger);
        mConnected.await();
_L1:
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        fatal("Interrupted wait at init");
          goto _L1
    }

    public static String nameOf(int i)
    {
        String s = (String)EVENT_NAMES.get(i);
        if(s == null)
            return Integer.toString(i);
        else
            return s;
    }

    private int nextListenerKey()
    {
        mListenerKey = Math.max(FIRST_LISTENER_KEY, mListenerKey + 1);
        return mListenerKey;
    }

    private int putListener(Object obj, NsdServiceInfo nsdserviceinfo)
    {
        checkListener(obj);
        Object obj1 = mMapLock;
        obj1;
        JVM INSTR monitorenter ;
        boolean flag;
        int i;
        if(mListenerMap.indexOfValue(obj) == -1)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "listener already in use");
        i = nextListenerKey();
        mListenerMap.put(i, obj);
        mServiceMap.put(i, nsdserviceinfo);
        obj1;
        JVM INSTR monitorexit ;
        return i;
        obj;
        throw obj;
    }

    private void removeListener(int i)
    {
        Object obj = mMapLock;
        obj;
        JVM INSTR monitorenter ;
        mListenerMap.remove(i);
        mServiceMap.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void disconnect()
    {
        mAsyncChannel.disconnect();
    }

    public void discoverServices(String s, int i, DiscoveryListener discoverylistener)
    {
        Preconditions.checkStringNotEmpty(s, "Service type cannot be empty");
        checkProtocol(i);
        NsdServiceInfo nsdserviceinfo = new NsdServiceInfo();
        nsdserviceinfo.setServiceType(s);
        i = putListener(discoverylistener, nsdserviceinfo);
        mAsyncChannel.sendMessage(0x60001, 0, i, nsdserviceinfo);
    }

    public void registerService(NsdServiceInfo nsdserviceinfo, int i, RegistrationListener registrationlistener)
    {
        boolean flag;
        if(nsdserviceinfo.getPort() > 0)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "Invalid port number");
        checkServiceInfo(nsdserviceinfo);
        checkProtocol(i);
        i = putListener(registrationlistener, nsdserviceinfo);
        mAsyncChannel.sendMessage(0x60009, 0, i, nsdserviceinfo);
    }

    public void resolveService(NsdServiceInfo nsdserviceinfo, ResolveListener resolvelistener)
    {
        checkServiceInfo(nsdserviceinfo);
        int i = putListener(resolvelistener, nsdserviceinfo);
        mAsyncChannel.sendMessage(0x60012, 0, i, nsdserviceinfo);
    }

    public void setEnabled(boolean flag)
    {
        try
        {
            mService.setEnabled(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void stopServiceDiscovery(DiscoveryListener discoverylistener)
    {
        int i = getListenerKey(discoverylistener);
        mAsyncChannel.sendMessage(0x60006, 0, i);
    }

    public void unregisterService(RegistrationListener registrationlistener)
    {
        int i = getListenerKey(registrationlistener);
        mAsyncChannel.sendMessage(0x6000c, 0, i);
    }

    public static final String ACTION_NSD_STATE_CHANGED = "android.net.nsd.STATE_CHANGED";
    private static final int BASE = 0x60000;
    private static final boolean DBG = false;
    public static final int DISABLE = 0x60019;
    public static final int DISCOVER_SERVICES = 0x60001;
    public static final int DISCOVER_SERVICES_FAILED = 0x60003;
    public static final int DISCOVER_SERVICES_STARTED = 0x60002;
    public static final int ENABLE = 0x60018;
    private static final SparseArray EVENT_NAMES;
    public static final String EXTRA_NSD_STATE = "nsd_state";
    public static final int FAILURE_ALREADY_ACTIVE = 3;
    public static final int FAILURE_INTERNAL_ERROR = 0;
    public static final int FAILURE_MAX_LIMIT = 4;
    private static int FIRST_LISTENER_KEY = 0;
    public static final int NATIVE_DAEMON_EVENT = 0x6001a;
    public static final int NSD_STATE_DISABLED = 1;
    public static final int NSD_STATE_ENABLED = 2;
    public static final int PROTOCOL_DNS_SD = 1;
    public static final int REGISTER_SERVICE = 0x60009;
    public static final int REGISTER_SERVICE_FAILED = 0x6000a;
    public static final int REGISTER_SERVICE_SUCCEEDED = 0x6000b;
    public static final int RESOLVE_SERVICE = 0x60012;
    public static final int RESOLVE_SERVICE_FAILED = 0x60013;
    public static final int RESOLVE_SERVICE_SUCCEEDED = 0x60014;
    public static final int SERVICE_FOUND = 0x60004;
    public static final int SERVICE_LOST = 0x60005;
    public static final int STOP_DISCOVERY = 0x60006;
    public static final int STOP_DISCOVERY_FAILED = 0x60007;
    public static final int STOP_DISCOVERY_SUCCEEDED = 0x60008;
    private static final String TAG = android/net/nsd/NsdManager.getSimpleName();
    public static final int UNREGISTER_SERVICE = 0x6000c;
    public static final int UNREGISTER_SERVICE_FAILED = 0x6000d;
    public static final int UNREGISTER_SERVICE_SUCCEEDED = 0x6000e;
    private final AsyncChannel mAsyncChannel = new AsyncChannel();
    private final CountDownLatch mConnected = new CountDownLatch(1);
    private final Context mContext;
    private ServiceHandler mHandler;
    private int mListenerKey;
    private final SparseArray mListenerMap = new SparseArray();
    private final Object mMapLock = new Object();
    private final INsdManager mService;
    private final SparseArray mServiceMap = new SparseArray();

    static 
    {
        EVENT_NAMES = new SparseArray();
        EVENT_NAMES.put(0x60001, "DISCOVER_SERVICES");
        EVENT_NAMES.put(0x60002, "DISCOVER_SERVICES_STARTED");
        EVENT_NAMES.put(0x60003, "DISCOVER_SERVICES_FAILED");
        EVENT_NAMES.put(0x60004, "SERVICE_FOUND");
        EVENT_NAMES.put(0x60005, "SERVICE_LOST");
        EVENT_NAMES.put(0x60006, "STOP_DISCOVERY");
        EVENT_NAMES.put(0x60007, "STOP_DISCOVERY_FAILED");
        EVENT_NAMES.put(0x60008, "STOP_DISCOVERY_SUCCEEDED");
        EVENT_NAMES.put(0x60009, "REGISTER_SERVICE");
        EVENT_NAMES.put(0x6000a, "REGISTER_SERVICE_FAILED");
        EVENT_NAMES.put(0x6000b, "REGISTER_SERVICE_SUCCEEDED");
        EVENT_NAMES.put(0x6000c, "UNREGISTER_SERVICE");
        EVENT_NAMES.put(0x6000d, "UNREGISTER_SERVICE_FAILED");
        EVENT_NAMES.put(0x6000e, "UNREGISTER_SERVICE_SUCCEEDED");
        EVENT_NAMES.put(0x60012, "RESOLVE_SERVICE");
        EVENT_NAMES.put(0x60013, "RESOLVE_SERVICE_FAILED");
        EVENT_NAMES.put(0x60014, "RESOLVE_SERVICE_SUCCEEDED");
        EVENT_NAMES.put(0x60018, "ENABLE");
        EVENT_NAMES.put(0x60019, "DISABLE");
        EVENT_NAMES.put(0x6001a, "NATIVE_DAEMON_EVENT");
        FIRST_LISTENER_KEY = 1;
    }
}
