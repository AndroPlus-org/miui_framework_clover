// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.content.Context;
import android.net.NetworkSpecifier;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.nio.BufferOverflowException;
import libcore.util.HexEncoding;

// Referenced classes of package android.net.wifi.aware:
//            IWifiAwareManager, PeerHandle, WifiAwareNetworkSpecifier, AttachCallback, 
//            IdentityChangedListener, ConfigRequest, Characteristics, PublishConfig, 
//            DiscoverySessionCallback, SubscribeConfig, PublishDiscoverySession, SubscribeDiscoverySession, 
//            DiscoverySession, WifiAwareSession

public class WifiAwareManager
{
    private static class WifiAwareDiscoverySessionCallbackProxy extends IWifiAwareDiscoverySessionCallback.Stub
    {

        static WeakReference _2D_get0(WifiAwareDiscoverySessionCallbackProxy wifiawarediscoverysessioncallbackproxy)
        {
            return wifiawarediscoverysessioncallbackproxy.mAwareManager;
        }

        static DiscoverySessionCallback _2D_get1(WifiAwareDiscoverySessionCallbackProxy wifiawarediscoverysessioncallbackproxy)
        {
            return wifiawarediscoverysessioncallbackproxy.mOriginalCallback;
        }

        static DiscoverySession _2D_get2(WifiAwareDiscoverySessionCallbackProxy wifiawarediscoverysessioncallbackproxy)
        {
            return wifiawarediscoverysessioncallbackproxy.mSession;
        }

        public void onMatch(int i, byte abyte0[], byte abyte1[])
        {
            Bundle bundle = new Bundle();
            bundle.putByteArray("message", abyte0);
            bundle.putByteArray("message2", abyte1);
            abyte0 = mHandler.obtainMessage(4);
            abyte0.arg1 = i;
            abyte0.setData(bundle);
            mHandler.sendMessage(abyte0);
        }

        public void onMessageReceived(int i, byte abyte0[])
        {
            Message message = mHandler.obtainMessage(7);
            message.arg1 = i;
            message.obj = abyte0;
            mHandler.sendMessage(message);
        }

        public void onMessageSendFail(int i, int j)
        {
            Message message = mHandler.obtainMessage(6);
            message.arg1 = i;
            message.arg2 = j;
            mHandler.sendMessage(message);
        }

        public void onMessageSendSuccess(int i)
        {
            Message message = mHandler.obtainMessage(5);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }

        public void onProxySessionStarted(int i)
        {
            if(mSession != null)
            {
                Log.e("WifiAwareManager", (new StringBuilder()).append("onSessionStarted: sessionId=").append(i).append(": session already created!?").toString());
                throw new IllegalStateException((new StringBuilder()).append("onSessionStarted: sessionId=").append(i).append(": session already created!?").toString());
            }
            Object obj = (WifiAwareManager)mAwareManager.get();
            if(obj == null)
            {
                Log.w("WifiAwareManager", "onProxySessionStarted: mgr GC'd");
                return;
            }
            if(mIsPublish)
            {
                obj = new PublishDiscoverySession(((WifiAwareManager) (obj)), mClientId, i);
                mSession = ((DiscoverySession) (obj));
                mOriginalCallback.onPublishStarted(((PublishDiscoverySession) (obj)));
            } else
            {
                obj = new SubscribeDiscoverySession(((WifiAwareManager) (obj)), mClientId, i);
                mSession = ((DiscoverySession) (obj));
                mOriginalCallback.onSubscribeStarted(((SubscribeDiscoverySession) (obj)));
            }
        }

        public void onProxySessionTerminated(int i)
        {
            if(mSession != null)
            {
                mSession.setTerminated();
                mSession = null;
            } else
            {
                Log.w("WifiAwareManager", "Proxy: onSessionTerminated called but mSession is null!?");
            }
            mAwareManager.clear();
            mOriginalCallback.onSessionTerminated();
        }

        public void onSessionConfigFail(int i)
        {
            Message message = mHandler.obtainMessage(2);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }

        public void onSessionConfigSuccess()
        {
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
        }

        public void onSessionStarted(int i)
        {
            Message message = mHandler.obtainMessage(0);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }

        public void onSessionTerminated(int i)
        {
            Message message = mHandler.obtainMessage(3);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }

        private static final int CALLBACK_MATCH = 4;
        private static final int CALLBACK_MESSAGE_RECEIVED = 7;
        private static final int CALLBACK_MESSAGE_SEND_FAIL = 6;
        private static final int CALLBACK_MESSAGE_SEND_SUCCESS = 5;
        private static final int CALLBACK_SESSION_CONFIG_FAIL = 2;
        private static final int CALLBACK_SESSION_CONFIG_SUCCESS = 1;
        private static final int CALLBACK_SESSION_STARTED = 0;
        private static final int CALLBACK_SESSION_TERMINATED = 3;
        private static final String MESSAGE_BUNDLE_KEY_MESSAGE = "message";
        private static final String MESSAGE_BUNDLE_KEY_MESSAGE2 = "message2";
        private final WeakReference mAwareManager;
        private final int mClientId;
        private final Handler mHandler;
        private final boolean mIsPublish;
        private final DiscoverySessionCallback mOriginalCallback;
        private DiscoverySession mSession;

        WifiAwareDiscoverySessionCallbackProxy(WifiAwareManager wifiawaremanager, Looper looper, boolean flag, DiscoverySessionCallback discoverysessioncallback, int i)
        {
            mAwareManager = new WeakReference(wifiawaremanager);
            mIsPublish = flag;
            mOriginalCallback = discoverysessioncallback;
            mClientId = i;
            mHandler = new _cls1(looper);
        }
    }

    private static class WifiAwareEventCallbackProxy extends IWifiAwareEventCallback.Stub
    {

        static WeakReference _2D_get0(WifiAwareEventCallbackProxy wifiawareeventcallbackproxy)
        {
            return wifiawareeventcallbackproxy.mAwareManager;
        }

        static Binder _2D_get1(WifiAwareEventCallbackProxy wifiawareeventcallbackproxy)
        {
            return wifiawareeventcallbackproxy.mBinder;
        }

        android.net.wifi.RttManager.RttListener getAndRemoveRangingListener(int i)
        {
            WifiAwareManager wifiawaremanager;
            wifiawaremanager = (WifiAwareManager)mAwareManager.get();
            if(wifiawaremanager == null)
            {
                Log.w("WifiAwareManager", "getAndRemoveRangingListener: called post GC");
                return null;
            }
            Object obj = WifiAwareManager._2D_get0(wifiawaremanager);
            obj;
            JVM INSTR monitorenter ;
            android.net.wifi.RttManager.RttListener rttlistener;
            rttlistener = (android.net.wifi.RttManager.RttListener)WifiAwareManager._2D_get1(wifiawaremanager).get(i);
            WifiAwareManager._2D_get1(wifiawaremanager).delete(i);
            obj;
            JVM INSTR monitorexit ;
            return rttlistener;
            Exception exception;
            exception;
            throw exception;
        }

        public void onConnectFail(int i)
        {
            Message message = mHandler.obtainMessage(1);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }

        public void onConnectSuccess(int i)
        {
            Message message = mHandler.obtainMessage(0);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }

        public void onIdentityChanged(byte abyte0[])
        {
            Message message = mHandler.obtainMessage(2);
            message.obj = abyte0;
            mHandler.sendMessage(message);
        }

        public void onRangingAborted(int i)
        {
            Message message = mHandler.obtainMessage(5);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }

        public void onRangingFailure(int i, int j, String s)
        {
            Message message = mHandler.obtainMessage(4);
            message.arg1 = i;
            message.arg2 = j;
            message.obj = s;
            mHandler.sendMessage(message);
        }

        public void onRangingSuccess(int i, android.net.wifi.RttManager.ParcelableRttResults parcelablerttresults)
        {
            Message message = mHandler.obtainMessage(3);
            message.arg1 = i;
            message.obj = parcelablerttresults;
            mHandler.sendMessage(message);
        }

        private static final int CALLBACK_CONNECT_FAIL = 1;
        private static final int CALLBACK_CONNECT_SUCCESS = 0;
        private static final int CALLBACK_IDENTITY_CHANGED = 2;
        private static final int CALLBACK_RANGING_ABORTED = 5;
        private static final int CALLBACK_RANGING_FAILURE = 4;
        private static final int CALLBACK_RANGING_SUCCESS = 3;
        private final WeakReference mAwareManager;
        private final Binder mBinder;
        private final Handler mHandler;
        private final Looper mLooper;

        WifiAwareEventCallbackProxy(WifiAwareManager wifiawaremanager, final Looper final_looper, Binder binder, AttachCallback attachcallback, IdentityChangedListener identitychangedlistener)
        {
            mAwareManager = new WeakReference(wifiawaremanager);
            mLooper = final_looper;
            mBinder = binder;
            mHandler = attachcallback. new _cls1(identitychangedlistener);
        }
    }


    static Object _2D_get0(WifiAwareManager wifiawaremanager)
    {
        return wifiawaremanager.mLock;
    }

    static SparseArray _2D_get1(WifiAwareManager wifiawaremanager)
    {
        return wifiawaremanager.mRangingListeners;
    }

    public WifiAwareManager(Context context, IWifiAwareManager iwifiawaremanager)
    {
        mRangingListeners = new SparseArray();
        mContext = context;
        mService = iwifiawaremanager;
    }

    public void attach(AttachCallback attachcallback, IdentityChangedListener identitychangedlistener, Handler handler)
    {
        attach(handler, null, attachcallback, identitychangedlistener);
    }

    public void attach(AttachCallback attachcallback, Handler handler)
    {
        attach(handler, null, attachcallback, null);
    }

    public void attach(Handler handler, ConfigRequest configrequest, AttachCallback attachcallback, IdentityChangedListener identitychangedlistener)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(handler != null) goto _L2; else goto _L1
_L1:
        handler = Looper.getMainLooper();
_L4:
        Binder binder;
        IWifiAwareManager iwifiawaremanager;
        String s;
        WifiAwareEventCallbackProxy wifiawareeventcallbackproxy;
        binder = JVM INSTR new #77  <Class Binder>;
        binder.Binder();
        iwifiawaremanager = mService;
        s = mContext.getOpPackageName();
        wifiawareeventcallbackproxy = JVM INSTR new #11  <Class WifiAwareManager$WifiAwareEventCallbackProxy>;
        wifiawareeventcallbackproxy.WifiAwareEventCallbackProxy(this, handler, binder, attachcallback, identitychangedlistener);
        boolean flag;
        if(identitychangedlistener != null)
            flag = true;
        else
            flag = false;
        iwifiawaremanager.connect(binder, s, wifiawareeventcallbackproxy, configrequest, flag);
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        handler = handler.getLooper();
        if(true) goto _L4; else goto _L3
_L3:
        handler;
        throw handler.rethrowFromSystemServer();
        handler;
        obj;
        JVM INSTR monitorexit ;
        throw handler;
    }

    public NetworkSpecifier createNetworkSpecifier(int i, int j, int k, PeerHandle peerhandle, byte abyte0[], String s)
    {
        if(j != 0 && j != 1)
            throw new IllegalArgumentException("createNetworkSpecifier: Invalid 'role' argument when creating a network specifier");
        if(j == 0 && peerhandle == null)
            throw new IllegalArgumentException("createNetworkSpecifier: Invalid peer handle (value of null) - not permitted on INITIATOR");
        int l;
        int i1;
        if(peerhandle == null)
            l = 1;
        else
            l = 0;
        if(peerhandle != null)
            i1 = peerhandle.peerId;
        else
            i1 = 0;
        return new WifiAwareNetworkSpecifier(l, j, i, k, i1, null, abyte0, s, Process.myUid());
    }

    public NetworkSpecifier createNetworkSpecifier(int i, int j, byte abyte0[], byte abyte1[], String s)
    {
        if(j != 0 && j != 1)
            throw new IllegalArgumentException("createNetworkSpecifier: Invalid 'role' argument when creating a network specifier");
        if(j == 0 && abyte0 == null)
            throw new IllegalArgumentException("createNetworkSpecifier: Invalid peer MAC address - null not permitted on INITIATOR");
        if(abyte0 != null && abyte0.length != 6)
            throw new IllegalArgumentException("createNetworkSpecifier: Invalid peer MAC address");
        byte byte0;
        if(abyte0 == null)
            byte0 = 3;
        else
            byte0 = 2;
        return new WifiAwareNetworkSpecifier(byte0, j, i, 0, 0, abyte0, abyte1, s, Process.myUid());
    }

    public void disconnect(int i, Binder binder)
    {
        try
        {
            mService.disconnect(i, binder);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Binder binder)
        {
            throw binder.rethrowFromSystemServer();
        }
    }

    public Characteristics getCharacteristics()
    {
        Characteristics characteristics;
        try
        {
            characteristics = mService.getCharacteristics();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return characteristics;
    }

    public boolean isAvailable()
    {
        boolean flag;
        try
        {
            flag = mService.isUsageEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void publish(int i, Looper looper, PublishConfig publishconfig, DiscoverySessionCallback discoverysessioncallback)
    {
        try
        {
            IWifiAwareManager iwifiawaremanager = mService;
            WifiAwareDiscoverySessionCallbackProxy wifiawarediscoverysessioncallbackproxy = JVM INSTR new #6   <Class WifiAwareManager$WifiAwareDiscoverySessionCallbackProxy>;
            wifiawarediscoverysessioncallbackproxy.WifiAwareDiscoverySessionCallbackProxy(this, looper, true, discoverysessioncallback, i);
            iwifiawaremanager.publish(i, publishconfig, wifiawarediscoverysessioncallbackproxy);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Looper looper)
        {
            throw looper.rethrowFromSystemServer();
        }
    }

    public void sendMessage(int i, int j, PeerHandle peerhandle, byte abyte0[], int k, int l)
    {
        if(peerhandle == null)
            throw new IllegalArgumentException("sendMessage: invalid peerHandle - must be non-null");
        try
        {
            mService.sendMessage(i, j, peerhandle.peerId, abyte0, k, l);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PeerHandle peerhandle)
        {
            throw peerhandle.rethrowFromSystemServer();
        }
    }

    public void startRanging(int i, int j, android.net.wifi.RttManager.RttParams arttparams[], android.net.wifi.RttManager.RttListener rttlistener)
    {
        try
        {
            IWifiAwareManager iwifiawaremanager = mService;
            android.net.wifi.RttManager.ParcelableRttParams parcelablerttparams = JVM INSTR new #167 <Class android.net.wifi.RttManager$ParcelableRttParams>;
            parcelablerttparams.android.net.wifi.RttManager.ParcelableRttParams(arttparams);
            i = iwifiawaremanager.startRanging(i, j, parcelablerttparams);
        }
        // Misplaced declaration of an exception variable
        catch(android.net.wifi.RttManager.RttParams arttparams[])
        {
            throw arttparams.rethrowFromSystemServer();
        }
        arttparams = ((android.net.wifi.RttManager.RttParams []) (mLock));
        arttparams;
        JVM INSTR monitorenter ;
        mRangingListeners.put(i, rttlistener);
        arttparams;
        JVM INSTR monitorexit ;
        return;
        rttlistener;
        throw rttlistener;
    }

    public void subscribe(int i, Looper looper, SubscribeConfig subscribeconfig, DiscoverySessionCallback discoverysessioncallback)
    {
        try
        {
            IWifiAwareManager iwifiawaremanager = mService;
            WifiAwareDiscoverySessionCallbackProxy wifiawarediscoverysessioncallbackproxy = JVM INSTR new #6   <Class WifiAwareManager$WifiAwareDiscoverySessionCallbackProxy>;
            wifiawarediscoverysessioncallbackproxy.WifiAwareDiscoverySessionCallbackProxy(this, looper, false, discoverysessioncallback, i);
            iwifiawaremanager.subscribe(i, subscribeconfig, wifiawarediscoverysessioncallbackproxy);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Looper looper)
        {
            throw looper.rethrowFromSystemServer();
        }
    }

    public void terminateSession(int i, int j)
    {
        try
        {
            mService.terminateSession(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void updatePublish(int i, int j, PublishConfig publishconfig)
    {
        try
        {
            mService.updatePublish(i, j, publishconfig);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PublishConfig publishconfig)
        {
            throw publishconfig.rethrowFromSystemServer();
        }
    }

    public void updateSubscribe(int i, int j, SubscribeConfig subscribeconfig)
    {
        try
        {
            mService.updateSubscribe(i, j, subscribeconfig);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(SubscribeConfig subscribeconfig)
        {
            throw subscribeconfig.rethrowFromSystemServer();
        }
    }

    public static final String ACTION_WIFI_AWARE_STATE_CHANGED = "android.net.wifi.aware.action.WIFI_AWARE_STATE_CHANGED";
    private static final boolean DBG = false;
    private static final String TAG = "WifiAwareManager";
    private static final boolean VDBG = false;
    public static final int WIFI_AWARE_DATA_PATH_ROLE_INITIATOR = 0;
    public static final int WIFI_AWARE_DATA_PATH_ROLE_RESPONDER = 1;
    private final Context mContext;
    private final Object mLock = new Object();
    private SparseArray mRangingListeners;
    private final IWifiAwareManager mService;

    // Unreferenced inner class android/net/wifi/aware/WifiAwareManager$WifiAwareDiscoverySessionCallbackProxy$1

/* anonymous class */
    class WifiAwareDiscoverySessionCallbackProxy._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            if(WifiAwareDiscoverySessionCallbackProxy._2D_get0(WifiAwareDiscoverySessionCallbackProxy.this).get() == null)
            {
                Log.w("WifiAwareManager", "WifiAwareDiscoverySessionCallbackProxy: handleMessage post GC");
                return;
            }
            message.what;
            JVM INSTR tableswitch 0 7: default 72
        //                       0 73
        //                       1 87
        //                       2 100
        //                       3 133
        //                       4 147
        //                       5 259
        //                       6 276
        //                       7 293;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
            return;
_L2:
            onProxySessionStarted(message.arg1);
            continue; /* Loop/switch isn't completed */
_L3:
            WifiAwareDiscoverySessionCallbackProxy._2D_get1(WifiAwareDiscoverySessionCallbackProxy.this).onSessionConfigUpdated();
            continue; /* Loop/switch isn't completed */
_L4:
            WifiAwareDiscoverySessionCallbackProxy._2D_get1(WifiAwareDiscoverySessionCallbackProxy.this).onSessionConfigFailed();
            if(WifiAwareDiscoverySessionCallbackProxy._2D_get2(WifiAwareDiscoverySessionCallbackProxy.this) == null)
                WifiAwareDiscoverySessionCallbackProxy._2D_get0(WifiAwareDiscoverySessionCallbackProxy.this).clear();
            continue; /* Loop/switch isn't completed */
_L5:
            onProxySessionTerminated(message.arg1);
            continue; /* Loop/switch isn't completed */
_L6:
            byte abyte0[] = message.getData().getByteArray("message2");
            Object obj;
            try
            {
                obj = JVM INSTR new #95  <Class TlvBufferUtils$TlvIterable>;
                ((TlvBufferUtils.TlvIterable) (obj)).TlvBufferUtils.TlvIterable(0, 1, abyte0);
                obj = ((TlvBufferUtils.TlvIterable) (obj)).toList();
            }
            catch(BufferOverflowException bufferoverflowexception)
            {
                obj = null;
                Log.e("WifiAwareManager", (new StringBuilder()).append("onServiceDiscovered: invalid match filter byte array '").append(new String(HexEncoding.encode(abyte0))).append("' - cannot be parsed: e=").append(bufferoverflowexception).toString());
            }
            WifiAwareDiscoverySessionCallbackProxy._2D_get1(WifiAwareDiscoverySessionCallbackProxy.this).onServiceDiscovered(new PeerHandle(message.arg1), message.getData().getByteArray("message"), ((java.util.List) (obj)));
            continue; /* Loop/switch isn't completed */
_L7:
            WifiAwareDiscoverySessionCallbackProxy._2D_get1(WifiAwareDiscoverySessionCallbackProxy.this).onMessageSendSucceeded(message.arg1);
            continue; /* Loop/switch isn't completed */
_L8:
            WifiAwareDiscoverySessionCallbackProxy._2D_get1(WifiAwareDiscoverySessionCallbackProxy.this).onMessageSendFailed(message.arg1);
            continue; /* Loop/switch isn't completed */
_L9:
            WifiAwareDiscoverySessionCallbackProxy._2D_get1(WifiAwareDiscoverySessionCallbackProxy.this).onMessageReceived(new PeerHandle(message.arg1), (byte[])message.obj);
            if(true) goto _L1; else goto _L10
_L10:
        }

        final WifiAwareDiscoverySessionCallbackProxy this$1;

            
            {
                this$1 = WifiAwareDiscoverySessionCallbackProxy.this;
                super(looper);
            }
    }


    // Unreferenced inner class android/net/wifi/aware/WifiAwareManager$WifiAwareEventCallbackProxy$1

/* anonymous class */
    class WifiAwareEventCallbackProxy._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            WifiAwareManager wifiawaremanager;
            wifiawaremanager = (WifiAwareManager)WifiAwareEventCallbackProxy._2D_get0(WifiAwareEventCallbackProxy.this).get();
            if(wifiawaremanager == null)
            {
                Log.w("WifiAwareManager", "WifiAwareEventCallbackProxy: handleMessage post GC");
                return;
            }
            message.what;
            JVM INSTR tableswitch 0 5: default 68
        //                       0 69
        //                       1 98
        //                       2 118
        //                       3 153
        //                       4 224
        //                       5 296;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
            return;
_L2:
            attachCallback.onAttached(new WifiAwareSession(wifiawaremanager, WifiAwareEventCallbackProxy._2D_get1(WifiAwareEventCallbackProxy.this), message.arg1));
            continue; /* Loop/switch isn't completed */
_L3:
            WifiAwareEventCallbackProxy._2D_get0(WifiAwareEventCallbackProxy.this).clear();
            attachCallback.onAttachFailed();
            continue; /* Loop/switch isn't completed */
_L4:
            if(identityChangedListener == null)
                Log.e("WifiAwareManager", "CALLBACK_IDENTITY_CHANGED: null listener.");
            else
                identityChangedListener.onIdentityChanged((byte[])message.obj);
            continue; /* Loop/switch isn't completed */
_L5:
            android.net.wifi.RttManager.RttListener rttlistener = getAndRemoveRangingListener(message.arg1);
            if(rttlistener == null)
                Log.e("WifiAwareManager", (new StringBuilder()).append("CALLBACK_RANGING_SUCCESS rangingId=").append(message.arg1).append(": no listener registered (anymore)").toString());
            else
                rttlistener.onSuccess(((android.net.wifi.RttManager.ParcelableRttResults)message.obj).mResults);
            continue; /* Loop/switch isn't completed */
_L6:
            android.net.wifi.RttManager.RttListener rttlistener1 = getAndRemoveRangingListener(message.arg1);
            if(rttlistener1 == null)
                Log.e("WifiAwareManager", (new StringBuilder()).append("CALLBACK_RANGING_SUCCESS rangingId=").append(message.arg1).append(": no listener registered (anymore)").toString());
            else
                rttlistener1.onFailure(message.arg2, (String)message.obj);
            continue; /* Loop/switch isn't completed */
_L7:
            android.net.wifi.RttManager.RttListener rttlistener2 = getAndRemoveRangingListener(message.arg1);
            if(rttlistener2 == null)
                Log.e("WifiAwareManager", (new StringBuilder()).append("CALLBACK_RANGING_SUCCESS rangingId=").append(message.arg1).append(": no listener registered (anymore)").toString());
            else
                rttlistener2.onAborted();
            if(true) goto _L1; else goto _L8
_L8:
        }

        final WifiAwareEventCallbackProxy this$1;
        final AttachCallback val$attachCallback;
        final IdentityChangedListener val$identityChangedListener;

            
            {
                this$1 = final_wifiawareeventcallbackproxy;
                attachCallback = AttachCallback.this;
                identityChangedListener = identitychangedlistener;
                super(final_looper);
            }
    }

}
