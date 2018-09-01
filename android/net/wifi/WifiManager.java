// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ParceledListSlice;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.*;
import android.util.*;
import com.android.internal.util.AsyncChannel;
import com.android.server.net.NetworkPinner;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

// Referenced classes of package android.net.wifi:
//            IWifiManager, WifiConfiguration, WifiInfo, WifiConnectionStatistics, 
//            WifiActivityEnergyInfo, ScanResult, WpsInfo, WpsResult, 
//            RssiPacketCountInfo

public class WifiManager
{
    public static interface ActionListener
    {

        public abstract void onFailure(int i);

        public abstract void onSuccess();
    }

    public static class LocalOnlyHotspotCallback
    {

        public void onFailed(int i)
        {
        }

        public void onStarted(LocalOnlyHotspotReservation localonlyhotspotreservation)
        {
        }

        public void onStopped()
        {
        }

        public static final int ERROR_GENERIC = 2;
        public static final int ERROR_INCOMPATIBLE_MODE = 3;
        public static final int ERROR_NO_CHANNEL = 1;
        public static final int ERROR_TETHERING_DISALLOWED = 4;
        public static final int REQUEST_REGISTERED = 0;

        public LocalOnlyHotspotCallback()
        {
        }
    }

    private static class LocalOnlyHotspotCallbackProxy
    {

        static WeakReference _2D_get0(LocalOnlyHotspotCallbackProxy localonlyhotspotcallbackproxy)
        {
            return localonlyhotspotcallbackproxy.mWifiManager;
        }

        public Messenger getMessenger()
        {
            return mMessenger;
        }

        public void notifyFailed(int i)
            throws RemoteException
        {
            Message message = Message.obtain();
            message.what = 2;
            message.arg1 = i;
            mMessenger.send(message);
        }

        private final Handler mHandler;
        private final Looper mLooper;
        private final Messenger mMessenger;
        private final WeakReference mWifiManager;

        LocalOnlyHotspotCallbackProxy(WifiManager wifimanager, Looper looper, LocalOnlyHotspotCallback localonlyhotspotcallback)
        {
            mWifiManager = new WeakReference(wifimanager);
            mLooper = looper;
            mHandler = looper. new _cls1(localonlyhotspotcallback);
            mMessenger = new Messenger(mHandler);
        }
    }

    public static class LocalOnlyHotspotObserver
    {

        public void onRegistered(LocalOnlyHotspotSubscription localonlyhotspotsubscription)
        {
        }

        public void onStarted(WifiConfiguration wificonfiguration)
        {
        }

        public void onStopped()
        {
        }

        public LocalOnlyHotspotObserver()
        {
        }
    }

    private static class LocalOnlyHotspotObserverProxy
    {

        static WeakReference _2D_get0(LocalOnlyHotspotObserverProxy localonlyhotspotobserverproxy)
        {
            return localonlyhotspotobserverproxy.mWifiManager;
        }

        public Messenger getMessenger()
        {
            return mMessenger;
        }

        public void registered()
            throws RemoteException
        {
            Message message = Message.obtain();
            message.what = 3;
            mMessenger.send(message);
        }

        private final Handler mHandler;
        private final Looper mLooper;
        private final Messenger mMessenger;
        private final WeakReference mWifiManager;

        LocalOnlyHotspotObserverProxy(WifiManager wifimanager, Looper looper, LocalOnlyHotspotObserver localonlyhotspotobserver)
        {
            mWifiManager = new WeakReference(wifimanager);
            mLooper = looper;
            mHandler = looper. new _cls1(localonlyhotspotobserver);
            mMessenger = new Messenger(mHandler);
        }
    }

    public class LocalOnlyHotspotReservation
        implements AutoCloseable
    {

        public void close()
        {
            WifiManager._2D_wrap1(WifiManager.this);
            mCloseGuard.close();
_L1:
            return;
            Exception exception;
            exception;
            Log.e("WifiManager", "Failed to stop Local Only Hotspot.");
              goto _L1
        }

        protected void finalize()
            throws Throwable
        {
            if(mCloseGuard != null)
                mCloseGuard.warnIfOpen();
            close();
            super.finalize();
            return;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        public WifiConfiguration getWifiConfiguration()
        {
            return mConfig;
        }

        private final CloseGuard mCloseGuard = CloseGuard.get();
        private final WifiConfiguration mConfig;
        final WifiManager this$0;

        public LocalOnlyHotspotReservation(WifiConfiguration wificonfiguration)
        {
            this$0 = WifiManager.this;
            super();
            mConfig = wificonfiguration;
            mCloseGuard.open("close");
        }
    }

    public class LocalOnlyHotspotSubscription
        implements AutoCloseable
    {

        public void close()
        {
            unregisterLocalOnlyHotspotObserver();
            mCloseGuard.close();
_L1:
            return;
            Exception exception;
            exception;
            Log.e("WifiManager", "Failed to unregister LocalOnlyHotspotObserver.");
              goto _L1
        }

        protected void finalize()
            throws Throwable
        {
            if(mCloseGuard != null)
                mCloseGuard.warnIfOpen();
            close();
            super.finalize();
            return;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        private final CloseGuard mCloseGuard = CloseGuard.get();
        final WifiManager this$0;

        public LocalOnlyHotspotSubscription()
        {
            this$0 = WifiManager.this;
            super();
            mCloseGuard.open("close");
        }
    }

    public class MulticastLock
    {

        public void acquire()
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            if(!mRefCounted) goto _L2; else goto _L1
_L1:
            int i;
            i = mRefCount + 1;
            mRefCount = i;
            if(i != 1) goto _L4; else goto _L3
_L3:
            mService.acquireMulticastLock(mBinder, mTag);
            Object obj = WifiManager.this;
            obj;
            JVM INSTR monitorenter ;
            if(WifiManager._2D_get0(WifiManager.this) >= 50)
            {
                mService.releaseMulticastLock();
                UnsupportedOperationException unsupportedoperationexception = JVM INSTR new #64  <Class UnsupportedOperationException>;
                unsupportedoperationexception.UnsupportedOperationException("Exceeded maximum number of wifi locks");
                throw unsupportedoperationexception;
            }
              goto _L5
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            obj;
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
            obj;
            ibinder;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            boolean flag = mHeld;
            if(flag ^ true) goto _L3; else goto _L4
_L4:
            ibinder;
            JVM INSTR monitorexit ;
            return;
_L5:
            WifiManager wifimanager = WifiManager.this;
            WifiManager._2D_set0(wifimanager, WifiManager._2D_get0(wifimanager) + 1);
            obj;
            JVM INSTR monitorexit ;
            mHeld = true;
              goto _L4
        }

        protected void finalize()
            throws Throwable
        {
            super.finalize();
            setReferenceCounted(false);
            release();
        }

        public boolean isHeld()
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            boolean flag = mHeld;
            ibinder;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public void release()
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            if(!mRefCounted) goto _L2; else goto _L1
_L1:
            int i;
            i = mRefCount - 1;
            mRefCount = i;
            if(i != 0) goto _L4; else goto _L3
_L3:
            mService.releaseMulticastLock();
            Object obj = WifiManager.this;
            obj;
            JVM INSTR monitorenter ;
            WifiManager wifimanager = WifiManager.this;
            WifiManager._2D_set0(wifimanager, WifiManager._2D_get0(wifimanager) - 1);
            obj;
            JVM INSTR monitorexit ;
            mHeld = false;
_L4:
            if(mRefCount < 0)
            {
                RuntimeException runtimeexception = JVM INSTR new #94  <Class RuntimeException>;
                obj = JVM INSTR new #96  <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                runtimeexception.RuntimeException(((StringBuilder) (obj)).append("MulticastLock under-locked ").append(mTag).toString());
                throw runtimeexception;
            }
            break MISSING_BLOCK_LABEL_151;
            obj;
            ibinder;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            boolean flag = mHeld;
            if(!flag) goto _L4; else goto _L3
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            RemoteException remoteexception;
            remoteexception;
            throw remoteexception.rethrowFromSystemServer();
            ibinder;
            JVM INSTR monitorexit ;
        }

        public void setReferenceCounted(boolean flag)
        {
            mRefCounted = flag;
        }

        public String toString()
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            String s = Integer.toHexString(System.identityHashCode(this));
            String s1;
            Object obj;
            if(mHeld)
                s1 = "held; ";
            else
                s1 = "";
            if(!mRefCounted)
                break MISSING_BLOCK_LABEL_115;
            obj = JVM INSTR new #96  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            obj = ((StringBuilder) (obj)).append("refcounted: refcount = ").append(mRefCount).toString();
_L1:
            StringBuilder stringbuilder = JVM INSTR new #96  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            s1 = stringbuilder.append("MulticastLock{ ").append(s).append("; ").append(s1).append(((String) (obj))).append(" }").toString();
            ibinder;
            JVM INSTR monitorexit ;
            return s1;
            obj = "not refcounted";
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        private final IBinder mBinder;
        private boolean mHeld;
        private int mRefCount;
        private boolean mRefCounted;
        private String mTag;
        final WifiManager this$0;

        private MulticastLock(String s)
        {
            this$0 = WifiManager.this;
            super();
            mTag = s;
            mBinder = new Binder();
            mRefCount = 0;
            mRefCounted = true;
            mHeld = false;
        }

        MulticastLock(String s, MulticastLock multicastlock)
        {
            this(s);
        }
    }

    private class ServiceHandler extends Handler
    {

        private void dispatchMessageToListeners(Message message)
        {
            Object obj = WifiManager._2D_wrap0(WifiManager.this, message.arg2);
            message.what;
            JVM INSTR lookupswitch 18: default 172
        //                       69632: 173
        //                       69634: 172
        //                       69636: 225
        //                       151554: 252
        //                       151555: 272
        //                       151557: 252
        //                       151558: 272
        //                       151560: 252
        //                       151561: 272
        //                       151563: 288
        //                       151564: 360
        //                       151565: 346
        //                       151567: 392
        //                       151568: 378
        //                       151570: 252
        //                       151571: 272
        //                       151573: 410
        //                       151574: 460;
               goto _L1 _L2 _L1 _L3 _L4 _L5 _L4 _L5 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L4 _L5 _L11 _L12
_L1:
            return;
_L2:
            if(message.arg1 == 0)
            {
                WifiManager._2D_get1(WifiManager.this).sendMessage(0x11001);
            } else
            {
                Log.e("WifiManager", "Failed to set up channel connection");
                WifiManager._2D_set1(WifiManager.this, null);
            }
            WifiManager._2D_get2(WifiManager.this).countDown();
            continue; /* Loop/switch isn't completed */
_L3:
            Log.e("WifiManager", "Channel connection lost");
            WifiManager._2D_set1(WifiManager.this, null);
            getLooper().quit();
            continue; /* Loop/switch isn't completed */
_L4:
            if(obj != null)
                ((ActionListener)obj).onFailure(message.arg1);
            continue; /* Loop/switch isn't completed */
_L5:
            if(obj != null)
                ((ActionListener)obj).onSuccess();
            continue; /* Loop/switch isn't completed */
_L6:
            if(obj == null)
                continue; /* Loop/switch isn't completed */
            WpsResult wpsresult = (WpsResult)message.obj;
            ((WpsCallback)obj).onStarted(wpsresult.pin);
            Object obj1 = WifiManager._2D_get4(WifiManager.this);
            obj1;
            JVM INSTR monitorenter ;
            WifiManager._2D_get3(WifiManager.this).put(message.arg2, obj);
            obj1;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L8:
            if(obj != null)
                ((WpsCallback)obj).onSucceeded();
            continue; /* Loop/switch isn't completed */
_L7:
            if(obj != null)
                ((WpsCallback)obj).onFailed(message.arg1);
            continue; /* Loop/switch isn't completed */
_L10:
            if(obj != null)
                ((WpsCallback)obj).onSucceeded();
            continue; /* Loop/switch isn't completed */
_L9:
            if(obj != null)
                ((WpsCallback)obj).onFailed(message.arg1);
            continue; /* Loop/switch isn't completed */
_L11:
            if(obj != null)
            {
                message = (RssiPacketCountInfo)message.obj;
                if(message != null)
                    ((TxPacketCountListener)obj).onSuccess(((RssiPacketCountInfo) (message)).txgood + ((RssiPacketCountInfo) (message)).txbad);
                else
                    ((TxPacketCountListener)obj).onFailure(0);
            }
            continue; /* Loop/switch isn't completed */
_L12:
            if(obj != null)
                ((TxPacketCountListener)obj).onFailure(message.arg1);
            if(true) goto _L1; else goto _L13
_L13:
        }

        public void handleMessage(Message message)
        {
            Object obj = WifiManager._2D_get5();
            obj;
            JVM INSTR monitorenter ;
            dispatchMessageToListeners(message);
            obj;
            JVM INSTR monitorexit ;
            return;
            message;
            throw message;
        }

        final WifiManager this$0;

        ServiceHandler(Looper looper)
        {
            this$0 = WifiManager.this;
            super(looper);
        }
    }

    public static interface TxPacketCountListener
    {

        public abstract void onFailure(int i);

        public abstract void onSuccess(int i);
    }

    public class WifiLock
    {

        public void acquire()
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            if(!mRefCounted) goto _L2; else goto _L1
_L1:
            int i;
            i = mRefCount + 1;
            mRefCount = i;
            if(i != 1) goto _L4; else goto _L3
_L3:
            mService.acquireWifiLock(mBinder, mLockType, mTag, mWorkSource);
            Object obj = WifiManager.this;
            obj;
            JVM INSTR monitorenter ;
            if(WifiManager._2D_get0(WifiManager.this) >= 50)
            {
                mService.releaseWifiLock(mBinder);
                UnsupportedOperationException unsupportedoperationexception = JVM INSTR new #72  <Class UnsupportedOperationException>;
                unsupportedoperationexception.UnsupportedOperationException("Exceeded maximum number of wifi locks");
                throw unsupportedoperationexception;
            }
              goto _L5
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            obj;
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
            obj;
            ibinder;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            boolean flag = mHeld;
            if(flag ^ true) goto _L3; else goto _L4
_L4:
            ibinder;
            JVM INSTR monitorexit ;
            return;
_L5:
            WifiManager wifimanager = WifiManager.this;
            WifiManager._2D_set0(wifimanager, WifiManager._2D_get0(wifimanager) + 1);
            obj;
            JVM INSTR monitorexit ;
            mHeld = true;
              goto _L4
        }

        protected void finalize()
            throws Throwable
        {
            super.finalize();
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            boolean flag = mHeld;
            if(!flag) goto _L2; else goto _L1
_L1:
            mService.releaseWifiLock(mBinder);
            WifiManager wifimanager = WifiManager.this;
            wifimanager;
            JVM INSTR monitorenter ;
            WifiManager wifimanager1 = WifiManager.this;
            WifiManager._2D_set0(wifimanager1, WifiManager._2D_get0(wifimanager1) - 1);
            wifimanager;
            JVM INSTR monitorexit ;
_L2:
            ibinder;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            wifimanager;
            JVM INSTR monitorexit ;
            throw exception;
            Object obj;
            obj;
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
            obj;
            ibinder;
            JVM INSTR monitorexit ;
            throw obj;
        }

        public boolean isHeld()
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            boolean flag = mHeld;
            ibinder;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public void release()
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            if(!mRefCounted) goto _L2; else goto _L1
_L1:
            int i;
            i = mRefCount - 1;
            mRefCount = i;
            if(i != 0) goto _L4; else goto _L3
_L3:
            mService.releaseWifiLock(mBinder);
            Object obj = WifiManager.this;
            obj;
            JVM INSTR monitorenter ;
            WifiManager wifimanager = WifiManager.this;
            WifiManager._2D_set0(wifimanager, WifiManager._2D_get0(wifimanager) - 1);
            obj;
            JVM INSTR monitorexit ;
            mHeld = false;
_L4:
            if(mRefCount < 0)
            {
                obj = JVM INSTR new #96  <Class RuntimeException>;
                StringBuilder stringbuilder = JVM INSTR new #98  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                ((RuntimeException) (obj)).RuntimeException(stringbuilder.append("WifiLock under-locked ").append(mTag).toString());
                throw obj;
            }
            break MISSING_BLOCK_LABEL_156;
            obj;
            ibinder;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            boolean flag = mHeld;
            if(!flag) goto _L4; else goto _L3
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            RemoteException remoteexception;
            remoteexception;
            throw remoteexception.rethrowFromSystemServer();
            ibinder;
            JVM INSTR monitorexit ;
        }

        public void setReferenceCounted(boolean flag)
        {
            mRefCounted = flag;
        }

        public void setWorkSource(WorkSource worksource)
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            WorkSource worksource1;
            worksource1 = worksource;
            if(worksource == null)
                break MISSING_BLOCK_LABEL_24;
            worksource1 = worksource;
            if(worksource.size() == 0)
                worksource1 = null;
            boolean flag = true;
            if(worksource1 != null) goto _L2; else goto _L1
_L1:
            mWorkSource = null;
_L4:
            if(!flag)
                break MISSING_BLOCK_LABEL_72;
            flag = mHeld;
            if(!flag)
                break MISSING_BLOCK_LABEL_72;
            mService.updateWifiLockWorkSource(mBinder, mWorkSource);
            ibinder;
            JVM INSTR monitorexit ;
            return;
_L2:
            worksource1.clearNames();
            if(mWorkSource != null)
                break MISSING_BLOCK_LABEL_124;
            if(mWorkSource != null)
                flag = true;
            else
                flag = false;
            worksource = JVM INSTR new #116 <Class WorkSource>;
            worksource.WorkSource(worksource1);
            mWorkSource = worksource;
            continue; /* Loop/switch isn't completed */
            worksource;
            throw worksource;
            boolean flag1 = mWorkSource.diff(worksource1);
            flag = flag1;
            if(!flag1)
                continue; /* Loop/switch isn't completed */
            mWorkSource.set(worksource1);
            flag = flag1;
            if(true) goto _L4; else goto _L3
_L3:
            worksource;
            throw worksource.rethrowFromSystemServer();
        }

        public String toString()
        {
            IBinder ibinder = mBinder;
            ibinder;
            JVM INSTR monitorenter ;
            String s = Integer.toHexString(System.identityHashCode(this));
            String s1;
            Object obj;
            if(mHeld)
                s1 = "held; ";
            else
                s1 = "";
            if(!mRefCounted)
                break MISSING_BLOCK_LABEL_115;
            obj = JVM INSTR new #98  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            obj = ((StringBuilder) (obj)).append("refcounted: refcount = ").append(mRefCount).toString();
_L1:
            StringBuilder stringbuilder = JVM INSTR new #98  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            s1 = stringbuilder.append("WifiLock{ ").append(s).append("; ").append(s1).append(((String) (obj))).append(" }").toString();
            ibinder;
            JVM INSTR monitorexit ;
            return s1;
            obj = "not refcounted";
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        private final IBinder mBinder;
        private boolean mHeld;
        int mLockType;
        private int mRefCount;
        private boolean mRefCounted;
        private String mTag;
        private WorkSource mWorkSource;
        final WifiManager this$0;

        private WifiLock(int i, String s)
        {
            this$0 = WifiManager.this;
            super();
            mTag = s;
            mLockType = i;
            mBinder = new Binder();
            mRefCount = 0;
            mRefCounted = true;
            mHeld = false;
        }

        WifiLock(int i, String s, WifiLock wifilock)
        {
            this(i, s);
        }
    }

    public static abstract class WpsCallback
    {

        public abstract void onFailed(int i);

        public abstract void onStarted(String s);

        public abstract void onSucceeded();

        public WpsCallback()
        {
        }
    }


    static int _2D_get0(WifiManager wifimanager)
    {
        return wifimanager.mActiveLockCount;
    }

    static AsyncChannel _2D_get1(WifiManager wifimanager)
    {
        return wifimanager.mAsyncChannel;
    }

    static CountDownLatch _2D_get2(WifiManager wifimanager)
    {
        return wifimanager.mConnected;
    }

    static SparseArray _2D_get3(WifiManager wifimanager)
    {
        return wifimanager.mListenerMap;
    }

    static Object _2D_get4(WifiManager wifimanager)
    {
        return wifimanager.mListenerMapLock;
    }

    static Object _2D_get5()
    {
        return sServiceHandlerDispatchLock;
    }

    static int _2D_set0(WifiManager wifimanager, int i)
    {
        wifimanager.mActiveLockCount = i;
        return i;
    }

    static AsyncChannel _2D_set1(WifiManager wifimanager, AsyncChannel asyncchannel)
    {
        wifimanager.mAsyncChannel = asyncchannel;
        return asyncchannel;
    }

    static Object _2D_wrap0(WifiManager wifimanager, int i)
    {
        return wifimanager.removeListener(i);
    }

    static void _2D_wrap1(WifiManager wifimanager)
    {
        wifimanager.stopLocalOnlyHotspot();
    }

    public WifiManager(Context context, IWifiManager iwifimanager, Looper looper)
    {
        mListenerKey = 1;
        Context context1 = context.getApplicationContext();
        Context context2 = context1;
        if(context1 == null)
            context2 = context;
        mContext = context2;
        mService = iwifimanager;
        mLooper = looper;
        mTargetSdkVersion = context.getApplicationInfo().targetSdkVersion;
    }

    private int addOrUpdateNetwork(WifiConfiguration wificonfiguration)
    {
        int i;
        try
        {
            i = mService.addOrUpdateNetwork(wificonfiguration);
        }
        // Misplaced declaration of an exception variable
        catch(WifiConfiguration wificonfiguration)
        {
            throw wificonfiguration.rethrowFromSystemServer();
        }
        return i;
    }

    public static int calculateSignalLevel(int i, int j)
    {
        if(i <= -100)
            return 0;
        if(i >= -55)
        {
            return j - 1;
        } else
        {
            float f = j - 1;
            return (int)(((float)(i + 100) * f) / 45F);
        }
    }

    public static int compareSignalLevel(int i, int j)
    {
        return i - j;
    }

    private AsyncChannel getChannel()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if(mAsyncChannel != null)
            break MISSING_BLOCK_LABEL_96;
        obj = getWifiServiceMessenger();
        if(obj != null)
            break MISSING_BLOCK_LABEL_36;
        obj = JVM INSTR new #518 <Class IllegalStateException>;
        ((IllegalStateException) (obj)).IllegalStateException("getWifiServiceMessenger() returned null!  This is invalid.");
        throw obj;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        Object obj1 = JVM INSTR new #525 <Class AsyncChannel>;
        ((AsyncChannel) (obj1)).AsyncChannel();
        mAsyncChannel = ((AsyncChannel) (obj1));
        obj1 = JVM INSTR new #528 <Class CountDownLatch>;
        ((CountDownLatch) (obj1)).CountDownLatch(1);
        mConnected = ((CountDownLatch) (obj1));
        obj1 = JVM INSTR new #34  <Class WifiManager$ServiceHandler>;
        ((ServiceHandler) (obj1)).this. ServiceHandler(mLooper);
        mAsyncChannel.connect(mContext, ((Handler) (obj1)), ((Messenger) (obj)));
        mConnected.await();
_L1:
        obj = mAsyncChannel;
        this;
        JVM INSTR monitorexit ;
        return ((AsyncChannel) (obj));
        InterruptedException interruptedexception;
        interruptedexception;
        Log.e("WifiManager", "interrupted wait at init");
          goto _L1
    }

    private int getSupportedFeatures()
    {
        int i;
        try
        {
            i = mService.getSupportedFeatures();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    private boolean isFeatureSupported(int i)
    {
        boolean flag;
        if((getSupportedFeatures() & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private int putListener(Object obj)
    {
        if(obj == null)
            return 0;
        Object obj1 = mListenerMapLock;
        obj1;
        JVM INSTR monitorenter ;
        int i;
        do
        {
            i = mListenerKey;
            mListenerKey = i + 1;
        } while(i == 0);
        mListenerMap.put(i, obj);
        obj1;
        JVM INSTR monitorexit ;
        return i;
        obj;
        throw obj;
    }

    private Object removeListener(int i)
    {
        if(i == 0)
            return null;
        Object obj = mListenerMapLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = mListenerMap.get(i);
        mListenerMap.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return obj1;
        Exception exception;
        exception;
        throw exception;
    }

    private void stopLocalOnlyHotspot()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        LocalOnlyHotspotCallbackProxy localonlyhotspotcallbackproxy = mLOHSCallbackProxy;
        if(localonlyhotspotcallbackproxy != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        mLOHSCallbackProxy = null;
        mService.stopLocalOnlyHotspot();
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public int addNetwork(WifiConfiguration wificonfiguration)
    {
        if(wificonfiguration == null)
        {
            return -1;
        } else
        {
            wificonfiguration.networkId = -1;
            return addOrUpdateNetwork(wificonfiguration);
        }
    }

    public void addOrUpdatePasspointConfiguration(PasspointConfiguration passpointconfiguration)
    {
        try
        {
            if(!mService.addOrUpdatePasspointConfiguration(passpointconfiguration))
            {
                passpointconfiguration = JVM INSTR new #585 <Class IllegalArgumentException>;
                passpointconfiguration.IllegalArgumentException();
                throw passpointconfiguration;
            }
        }
        // Misplaced declaration of an exception variable
        catch(PasspointConfiguration passpointconfiguration)
        {
            throw passpointconfiguration.rethrowFromSystemServer();
        }
    }

    public void cancelLocalOnlyHotspotRequest()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        stopLocalOnlyHotspot();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void cancelWps(WpsCallback wpscallback)
    {
        getChannel().sendMessage(0x2500e, 0, putListener(wpscallback));
    }

    public void connect(int i, ActionListener actionlistener)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("Network id cannot be negative");
        } else
        {
            getChannel().sendMessage(0x25001, i, putListener(actionlistener));
            return;
        }
    }

    public void connect(WifiConfiguration wificonfiguration, ActionListener actionlistener)
    {
        if(wificonfiguration == null)
        {
            throw new IllegalArgumentException("config cannot be null");
        } else
        {
            getChannel().sendMessage(0x25001, -1, putListener(actionlistener), wificonfiguration);
            return;
        }
    }

    public MulticastLock createMulticastLock(String s)
    {
        return new MulticastLock(s, null);
    }

    public WifiLock createWifiLock(int i, String s)
    {
        return new WifiLock(i, s, null);
    }

    public WifiLock createWifiLock(String s)
    {
        return new WifiLock(1, s, null);
    }

    public void deauthenticateNetwork(long l, boolean flag)
    {
        try
        {
            mService.deauthenticateNetwork(l, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void disable(int i, ActionListener actionlistener)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("Network id cannot be negative");
        } else
        {
            getChannel().sendMessage(0x25011, i, putListener(actionlistener));
            return;
        }
    }

    public void disableEphemeralNetwork(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("SSID cannot be null");
        try
        {
            mService.disableEphemeralNetwork(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean disableNetwork(int i)
    {
        boolean flag;
        try
        {
            flag = mService.disableNetwork(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean disconnect()
    {
        try
        {
            mService.disconnect();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return true;
    }

    public void enableAggressiveHandover(int i)
    {
        try
        {
            mService.enableAggressiveHandover(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public boolean enableNetwork(int i, boolean flag)
    {
        boolean flag1;
        if(flag && mTargetSdkVersion < 21)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            android.net.NetworkRequest networkrequest = (new android.net.NetworkRequest.Builder()).clearCapabilities().addTransportType(1).build();
            NetworkPinner.pin(mContext, networkrequest);
        }
        try
        {
            flag = mService.enableNetwork(i, flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(flag1 && flag ^ true)
            NetworkPinner.unpin();
        return flag;
    }

    public void enableVerboseLogging(int i)
    {
        mService.enableVerboseLogging(i);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("WifiManager", (new StringBuilder()).append("enableVerboseLogging ").append(exception.toString()).toString());
          goto _L1
    }

    public void enableWifiConnectivityManager(boolean flag)
    {
        try
        {
            mService.enableWifiConnectivityManager(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
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

    public void forget(int i, ActionListener actionlistener)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("Network id cannot be negative");
        } else
        {
            getChannel().sendMessage(0x25004, i, putListener(actionlistener));
            return;
        }
    }

    public int getAggressiveHandover()
    {
        int i;
        try
        {
            i = mService.getAggressiveHandover();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getAllowScansWithTraffic()
    {
        int i;
        try
        {
            i = mService.getAllowScansWithTraffic();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public List getBatchedScanResults()
    {
        return null;
    }

    public List getConfiguredNetworks()
    {
        Object obj;
        try
        {
            obj = mService.getConfiguredNetworks();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_18;
        return Collections.emptyList();
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
    }

    public List getConnectedStations()
    {
        List list;
        try
        {
            list = mService.getConnectedStations();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return list;
    }

    public WifiInfo getConnectionInfo()
    {
        WifiInfo wifiinfo;
        try
        {
            wifiinfo = mService.getConnectionInfo(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return wifiinfo;
    }

    public WifiConnectionStatistics getConnectionStatistics()
    {
        WifiConnectionStatistics wificonnectionstatistics;
        try
        {
            wificonnectionstatistics = mService.getConnectionStatistics();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return wificonnectionstatistics;
    }

    public WifiActivityEnergyInfo getControllerActivityEnergyInfo(int i)
    {
        if(mService == null)
            return null;
        this;
        JVM INSTR monitorenter ;
        WifiActivityEnergyInfo wifiactivityenergyinfo = mService.reportActivityInfo();
        this;
        JVM INSTR monitorexit ;
        return wifiactivityenergyinfo;
        Object obj;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
    }

    public String getCountryCode()
    {
        String s;
        try
        {
            s = mService.getCountryCode();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public Network getCurrentNetwork()
    {
        Network network;
        try
        {
            network = mService.getCurrentNetwork();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return network;
    }

    public String getCurrentNetworkWpsNfcConfigurationToken()
    {
        String s;
        try
        {
            s = mService.getCurrentNetworkWpsNfcConfigurationToken();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public DhcpInfo getDhcpInfo()
    {
        DhcpInfo dhcpinfo;
        try
        {
            dhcpinfo = mService.getDhcpInfo();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return dhcpinfo;
    }

    public boolean getEnableAutoJoinWhenAssociated()
    {
        boolean flag;
        try
        {
            flag = mService.getEnableAutoJoinWhenAssociated();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public List getMatchingOsuProviders(ScanResult scanresult)
    {
        try
        {
            scanresult = mService.getMatchingOsuProviders(scanresult);
        }
        // Misplaced declaration of an exception variable
        catch(ScanResult scanresult)
        {
            throw scanresult.rethrowFromSystemServer();
        }
        return scanresult;
    }

    public WifiConfiguration getMatchingWifiConfig(ScanResult scanresult)
    {
        try
        {
            scanresult = mService.getMatchingWifiConfig(scanresult);
        }
        // Misplaced declaration of an exception variable
        catch(ScanResult scanresult)
        {
            throw scanresult.rethrowFromSystemServer();
        }
        return scanresult;
    }

    public List getPasspointConfigurations()
    {
        List list;
        try
        {
            list = mService.getPasspointConfigurations();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getPrivilegedConfiguredNetworks()
    {
        Object obj;
        try
        {
            obj = mService.getPrivilegedConfiguredNetworks();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_18;
        return Collections.emptyList();
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
    }

    public List getScanResults()
    {
        SeempLog.record(55);
        List list;
        try
        {
            list = mService.getScanResults(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public void getTxPacketCount(TxPacketCountListener txpacketcountlistener)
    {
        getChannel().sendMessage(0x25014, 0, putListener(txpacketcountlistener));
    }

    public int getVerboseLoggingLevel()
    {
        int i;
        try
        {
            i = mService.getVerboseLoggingLevel();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public WifiConfiguration getWifiApConfiguration()
    {
        WifiConfiguration wificonfiguration;
        try
        {
            wificonfiguration = mService.getWifiApConfiguration();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return wificonfiguration;
    }

    public int getWifiApState()
    {
        int i;
        try
        {
            i = mService.getWifiApEnabledState();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public Messenger getWifiServiceMessenger()
    {
        Messenger messenger;
        try
        {
            messenger = mService.getWifiServiceMessenger();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return messenger;
    }

    public boolean getWifiStaSapConcurrency()
    {
        boolean flag;
        try
        {
            flag = mService.getWifiStaSapConcurrency();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public int getWifiState()
    {
        int i;
        try
        {
            i = mService.getWifiEnabledState();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public boolean initializeMulticastFiltering()
    {
        try
        {
            mService.initializeMulticastFiltering();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return true;
    }

    public boolean is5GHzBandSupported()
    {
        return isFeatureSupported(2);
    }

    public boolean isAdditionalStaSupported()
    {
        return isFeatureSupported(2048);
    }

    public boolean isBatchedScanSupported()
    {
        return false;
    }

    public boolean isDeviceToApRttSupported()
    {
        return isFeatureSupported(256);
    }

    public boolean isDeviceToDeviceRttSupported()
    {
        return isFeatureSupported(128);
    }

    public boolean isDualBandSupported()
    {
        boolean flag;
        try
        {
            flag = mService.isDualBandSupported();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isEnhancedPowerReportingSupported()
    {
        return isFeatureSupported(0x10000);
    }

    public boolean isExtendingNetworkCoverage()
    {
        boolean flag;
        try
        {
            flag = mService.isExtendingNetworkCoverage();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isMiWifi()
    {
        WifiInfo wifiinfo = getConnectionInfo();
        return wifiinfo != null && "XIAOMI_ROUTER".equalsIgnoreCase(wifiinfo.getVendorInfo());
    }

    public boolean isMulticastEnabled()
    {
        boolean flag;
        try
        {
            flag = mService.isMulticastEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isOffChannelTdlsSupported()
    {
        return isFeatureSupported(8192);
    }

    public boolean isP2pSupported()
    {
        return isFeatureSupported(8);
    }

    public boolean isPasspointSupported()
    {
        return isFeatureSupported(4);
    }

    public boolean isPortableHotspotSupported()
    {
        return isFeatureSupported(16);
    }

    public boolean isPreferredNetworkOffloadSupported()
    {
        return isFeatureSupported(1024);
    }

    public boolean isScanAlwaysAvailable()
    {
        boolean flag;
        try
        {
            flag = mService.isScanAlwaysAvailable();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isTdlsSupported()
    {
        return isFeatureSupported(4096);
    }

    public boolean isWifiApEnabled()
    {
        boolean flag;
        if(getWifiApState() == 13)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isWifiAwareSupported()
    {
        return isFeatureSupported(64);
    }

    public boolean isWifiEnabled()
    {
        boolean flag;
        if(getWifiState() == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isWifiScannerSupported()
    {
        return isFeatureSupported(32);
    }

    public int matchProviderWithCurrentNetwork(String s)
    {
        int i;
        try
        {
            i = mService.matchProviderWithCurrentNetwork(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public boolean pingSupplicant()
    {
        return isWifiEnabled();
    }

    public void queryPasspointIcon(long l, String s)
    {
        try
        {
            mService.queryPasspointIcon(l, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean reassociate()
    {
        try
        {
            mService.reassociate();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return true;
    }

    public boolean reconnect()
    {
        try
        {
            mService.reconnect();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return true;
    }

    public boolean removeNetwork(int i)
    {
        boolean flag;
        try
        {
            flag = mService.removeNetwork(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void removePasspointConfiguration(String s)
    {
        try
        {
            if(!mService.removePasspointConfiguration(s))
            {
                s = JVM INSTR new #585 <Class IllegalArgumentException>;
                s.IllegalArgumentException();
                throw s;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void restoreBackupData(byte abyte0[])
    {
        try
        {
            mService.restoreBackupData(abyte0);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
    }

    public void restoreSupplicantBackupData(byte abyte0[], byte abyte1[])
    {
        try
        {
            mService.restoreSupplicantBackupData(abyte0, abyte1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
    }

    public byte[] retrieveBackupData()
    {
        byte abyte0[];
        try
        {
            abyte0 = mService.retrieveBackupData();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return abyte0;
    }

    public void save(WifiConfiguration wificonfiguration, ActionListener actionlistener)
    {
        if(wificonfiguration == null)
        {
            throw new IllegalArgumentException("config cannot be null");
        } else
        {
            getChannel().sendMessage(0x25007, 0, putListener(actionlistener), wificonfiguration);
            return;
        }
    }

    public boolean saveConfiguration()
    {
        boolean flag;
        try
        {
            flag = mService.saveConfiguration();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setAllowScansWithTraffic(int i)
    {
        try
        {
            mService.setAllowScansWithTraffic(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setCountryCode(String s, boolean flag)
    {
        try
        {
            mService.setCountryCode(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean setEnableAutoJoinWhenAssociated(boolean flag)
    {
        try
        {
            flag = mService.setEnableAutoJoinWhenAssociated(flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setTdlsEnabled(InetAddress inetaddress, boolean flag)
    {
        try
        {
            mService.enableTdls(inetaddress.getHostAddress(), flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(InetAddress inetaddress)
        {
            throw inetaddress.rethrowFromSystemServer();
        }
    }

    public void setTdlsEnabledWithMacAddress(String s, boolean flag)
    {
        try
        {
            mService.enableTdlsWithMacAddress(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean setWifiApConfiguration(WifiConfiguration wificonfiguration)
    {
        try
        {
            mService.setWifiApConfiguration(wificonfiguration);
        }
        // Misplaced declaration of an exception variable
        catch(WifiConfiguration wificonfiguration)
        {
            throw wificonfiguration.rethrowFromSystemServer();
        }
        return true;
    }

    public boolean setWifiApEnabled(WifiConfiguration wificonfiguration, boolean flag)
    {
        wificonfiguration = mContext.getOpPackageName();
        Log.w("WifiManager", (new StringBuilder()).append(wificonfiguration).append(" attempted call to setWifiApEnabled: enabled = ").append(flag).toString());
        return false;
    }

    public boolean setWifiEnabled(boolean flag)
    {
        try
        {
            flag = mService.setWifiEnabled(mContext.getOpPackageName(), flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void startLocalOnlyHotspot(LocalOnlyHotspotCallback localonlyhotspotcallback, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(handler != null)
            break MISSING_BLOCK_LABEL_89;
        handler = mContext.getMainLooper();
_L1:
        LocalOnlyHotspotCallbackProxy localonlyhotspotcallbackproxy;
        localonlyhotspotcallbackproxy = JVM INSTR new #12  <Class WifiManager$LocalOnlyHotspotCallbackProxy>;
        localonlyhotspotcallbackproxy.LocalOnlyHotspotCallbackProxy(this, handler, localonlyhotspotcallback);
        int i;
        localonlyhotspotcallback = mContext.getOpPackageName();
        IWifiManager iwifimanager = mService;
        Messenger messenger = localonlyhotspotcallbackproxy.getMessenger();
        handler = JVM INSTR new #966 <Class Binder>;
        handler.Binder();
        i = iwifimanager.startLocalOnlyHotspot(messenger, handler, localonlyhotspotcallback);
        if(i == 0)
            break MISSING_BLOCK_LABEL_97;
        localonlyhotspotcallbackproxy.notifyFailed(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        handler = handler.getLooper();
          goto _L1
        mLOHSCallbackProxy = localonlyhotspotcallbackproxy;
        obj;
        JVM INSTR monitorexit ;
        return;
        localonlyhotspotcallback;
        throw localonlyhotspotcallback.rethrowFromSystemServer();
        localonlyhotspotcallback;
        obj;
        JVM INSTR monitorexit ;
        throw localonlyhotspotcallback;
    }

    public boolean startLocationRestrictedScan(WorkSource worksource)
    {
        return false;
    }

    public boolean startScan()
    {
        return startScan(null);
    }

    public boolean startScan(WorkSource worksource)
    {
        try
        {
            String s = mContext.getOpPackageName();
            mService.startScan(null, worksource, s);
        }
        // Misplaced declaration of an exception variable
        catch(WorkSource worksource)
        {
            throw worksource.rethrowFromSystemServer();
        }
        return true;
    }

    public boolean startSoftAp(WifiConfiguration wificonfiguration)
    {
        boolean flag;
        try
        {
            flag = mService.startSoftAp(wificonfiguration);
        }
        // Misplaced declaration of an exception variable
        catch(WifiConfiguration wificonfiguration)
        {
            throw wificonfiguration.rethrowFromSystemServer();
        }
        return flag;
    }

    public void startWps(WpsInfo wpsinfo, WpsCallback wpscallback)
    {
        if(wpsinfo == null)
        {
            throw new IllegalArgumentException("config cannot be null");
        } else
        {
            getChannel().sendMessage(0x2500a, 0, putListener(wpscallback), wpsinfo);
            return;
        }
    }

    public boolean stopSoftAp()
    {
        boolean flag;
        try
        {
            flag = mService.stopSoftAp();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void unregisterLocalOnlyHotspotObserver()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        LocalOnlyHotspotObserverProxy localonlyhotspotobserverproxy = mLOHSObserverProxy;
        if(localonlyhotspotobserverproxy != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        mLOHSObserverProxy = null;
        mService.stopWatchLocalOnlyHotspot();
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public void updateInterfaceIpState(String s, int i)
    {
        try
        {
            mService.updateInterfaceIpState(s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public int updateNetwork(WifiConfiguration wificonfiguration)
    {
        if(wificonfiguration == null || wificonfiguration.networkId < 0)
            return -1;
        else
            return addOrUpdateNetwork(wificonfiguration);
    }

    public void watchLocalOnlyHotspot(LocalOnlyHotspotObserver localonlyhotspotobserver, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(handler != null)
            break MISSING_BLOCK_LABEL_80;
        handler = mContext.getMainLooper();
_L1:
        LocalOnlyHotspotObserverProxy localonlyhotspotobserverproxy = JVM INSTR new #20  <Class WifiManager$LocalOnlyHotspotObserverProxy>;
        localonlyhotspotobserverproxy.LocalOnlyHotspotObserverProxy(this, handler, localonlyhotspotobserver);
        mLOHSObserverProxy = localonlyhotspotobserverproxy;
        handler = mService;
        localonlyhotspotobserver = mLOHSObserverProxy.getMessenger();
        Binder binder = JVM INSTR new #966 <Class Binder>;
        binder.Binder();
        handler.startWatchLocalOnlyHotspot(localonlyhotspotobserver, binder);
        mLOHSObserverProxy.registered();
        obj;
        JVM INSTR monitorexit ;
        return;
        handler = handler.getLooper();
          goto _L1
        localonlyhotspotobserver;
        mLOHSObserverProxy = null;
        throw localonlyhotspotobserver.rethrowFromSystemServer();
        localonlyhotspotobserver;
        obj;
        JVM INSTR monitorexit ;
        throw localonlyhotspotobserver;
    }

    public static final String ACTION_AUTH_PASSWORD_WRONG = "android.intent.action.AUTH_PASSWORD_WRONG";
    public static final String ACTION_PASSPOINT_DEAUTH_IMMINENT = "android.net.wifi.action.PASSPOINT_DEAUTH_IMMINENT";
    public static final String ACTION_PASSPOINT_ICON = "android.net.wifi.action.PASSPOINT_ICON";
    public static final String ACTION_PASSPOINT_OSU_PROVIDERS_LIST = "android.net.wifi.action.PASSPOINT_OSU_PROVIDERS_LIST";
    public static final String ACTION_PASSPOINT_SUBSCRIPTION_REMEDIATION = "android.net.wifi.action.PASSPOINT_SUBSCRIPTION_REMEDIATION";
    public static final String ACTION_PICK_WIFI_NETWORK = "android.net.wifi.PICK_WIFI_NETWORK";
    public static final String ACTION_REQUEST_DISABLE = "android.net.wifi.action.REQUEST_DISABLE";
    public static final String ACTION_REQUEST_ENABLE = "android.net.wifi.action.REQUEST_ENABLE";
    public static final String ACTION_REQUEST_SCAN_ALWAYS_AVAILABLE = "android.net.wifi.action.REQUEST_SCAN_ALWAYS_AVAILABLE";
    public static final String ACTION_WIFI_DISCONNECT_IN_PROGRESS = "wifi_disconnect_in_progress";
    private static final int BASE = 0x25000;
    public static final String BATCHED_SCAN_RESULTS_AVAILABLE_ACTION = "android.net.wifi.BATCHED_RESULTS";
    public static final int BUSY = 2;
    public static final int CANCEL_WPS = 0x2500e;
    public static final int CANCEL_WPS_FAILED = 0x2500f;
    public static final int CANCEL_WPS_SUCCEDED = 0x25010;
    public static final int CHANGE_REASON_ADDED = 0;
    public static final int CHANGE_REASON_CONFIG_CHANGE = 2;
    public static final int CHANGE_REASON_REMOVED = 1;
    public static final String CONFIGURED_NETWORKS_CHANGED_ACTION = "android.net.wifi.CONFIGURED_NETWORKS_CHANGE";
    public static final int CONNECT_NETWORK = 0x25001;
    public static final int CONNECT_NETWORK_FAILED = 0x25002;
    public static final int CONNECT_NETWORK_SUCCEEDED = 0x25003;
    public static final int DATA_ACTIVITY_IN = 1;
    public static final int DATA_ACTIVITY_INOUT = 3;
    public static final int DATA_ACTIVITY_NONE = 0;
    public static final int DATA_ACTIVITY_NOTIFICATION = 1;
    public static final int DATA_ACTIVITY_OUT = 2;
    public static final boolean DEFAULT_POOR_NETWORK_AVOIDANCE_ENABLED = false;
    public static final int DISABLE_NETWORK = 0x25011;
    public static final int DISABLE_NETWORK_FAILED = 0x25012;
    public static final int DISABLE_NETWORK_SUCCEEDED = 0x25013;
    public static final int ERROR = 0;
    public static final int ERROR_AUTHENTICATING = 1;
    public static final int ERROR_AUTH_FAILURE_EAP_FAILURE = 3;
    public static final int ERROR_AUTH_FAILURE_NONE = 0;
    public static final int ERROR_AUTH_FAILURE_TIMEOUT = 1;
    public static final int ERROR_AUTH_FAILURE_WRONG_PSWD = 2;
    public static final String EXTRA_ANQP_ELEMENT_DATA = "android.net.wifi.extra.ANQP_ELEMENT_DATA";
    public static final String EXTRA_BSSID = "bssid";
    public static final String EXTRA_BSSID_LONG = "android.net.wifi.extra.BSSID_LONG";
    public static final String EXTRA_CHANGE_REASON = "changeReason";
    public static final String EXTRA_COUNTRY_CODE = "country_code";
    public static final String EXTRA_DELAY = "android.net.wifi.extra.DELAY";
    public static final String EXTRA_ESS = "android.net.wifi.extra.ESS";
    public static final String EXTRA_FILENAME = "android.net.wifi.extra.FILENAME";
    public static final String EXTRA_ICON = "android.net.wifi.extra.ICON";
    public static final String EXTRA_LINK_PROPERTIES = "linkProperties";
    public static final String EXTRA_MULTIPLE_NETWORKS_CHANGED = "multipleChanges";
    public static final String EXTRA_NETWORK_CAPABILITIES = "networkCapabilities";
    public static final String EXTRA_NETWORK_INFO = "networkInfo";
    public static final String EXTRA_NEW_RSSI = "newRssi";
    public static final String EXTRA_NEW_STATE = "newState";
    public static final String EXTRA_PREVIOUS_WIFI_AP_STATE = "previous_wifi_state";
    public static final String EXTRA_PREVIOUS_WIFI_STATE = "previous_wifi_state";
    public static final String EXTRA_RESULTS_UPDATED = "resultsUpdated";
    public static final String EXTRA_SCAN_AVAILABLE = "scan_enabled";
    public static final String EXTRA_SUBSCRIPTION_REMEDIATION_METHOD = "android.net.wifi.extra.SUBSCRIPTION_REMEDIATION_METHOD";
    public static final String EXTRA_SUPPLICANT_CONNECTED = "connected";
    public static final String EXTRA_SUPPLICANT_ERROR = "supplicantError";
    public static final String EXTRA_SUPPLICANT_ERROR_REASON = "supplicantErrorReason";
    public static final String EXTRA_URL = "android.net.wifi.extra.URL";
    public static final String EXTRA_WIFI_AP_FAILURE_REASON = "wifi_ap_error_code";
    public static final String EXTRA_WIFI_AP_INTERFACE_NAME = "wifi_ap_interface_name";
    public static final String EXTRA_WIFI_AP_MODE = "wifi_ap_mode";
    public static final String EXTRA_WIFI_AP_STATE = "wifi_state";
    public static final String EXTRA_WIFI_CONFIGURATION = "wifiConfiguration";
    public static final String EXTRA_WIFI_CREDENTIAL_EVENT_TYPE = "et";
    public static final String EXTRA_WIFI_CREDENTIAL_SSID = "ssid";
    public static final String EXTRA_WIFI_INFO = "wifiInfo";
    public static final String EXTRA_WIFI_STATE = "wifi_state";
    public static final int FORGET_NETWORK = 0x25004;
    public static final int FORGET_NETWORK_FAILED = 0x25005;
    public static final int FORGET_NETWORK_SUCCEEDED = 0x25006;
    public static final int HOTSPOT_FAILED = 2;
    public static final int HOTSPOT_OBSERVER_REGISTERED = 3;
    public static final int HOTSPOT_STARTED = 0;
    public static final int HOTSPOT_STOPPED = 1;
    public static final int IFACE_IP_MODE_CONFIGURATION_ERROR = 0;
    public static final int IFACE_IP_MODE_LOCAL_ONLY = 2;
    public static final int IFACE_IP_MODE_TETHERED = 1;
    public static final int IFACE_IP_MODE_UNSPECIFIED = -1;
    public static final int INVALID_ARGS = 8;
    private static final int INVALID_KEY = 0;
    public static final int IN_PROGRESS = 1;
    public static final String LINK_CONFIGURATION_CHANGED_ACTION = "android.net.wifi.LINK_CONFIGURATION_CHANGED";
    private static final int MAX_ACTIVE_LOCKS = 50;
    private static final int MAX_RSSI = -55;
    private static final int MIN_RSSI = -100;
    public static final String NETWORK_IDS_CHANGED_ACTION = "android.net.wifi.NETWORK_IDS_CHANGED";
    public static final String NETWORK_STATE_CHANGED_ACTION = "android.net.wifi.STATE_CHANGE";
    public static final int NOT_AUTHORIZED = 9;
    public static final String RSSI_CHANGED_ACTION = "android.net.wifi.RSSI_CHANGED";
    public static final int RSSI_LEVELS = 5;
    public static final int RSSI_PKTCNT_FETCH = 0x25014;
    public static final int RSSI_PKTCNT_FETCH_FAILED = 0x25016;
    public static final int RSSI_PKTCNT_FETCH_SUCCEEDED = 0x25015;
    public static final int SAP_START_FAILURE_GENERAL = 0;
    public static final int SAP_START_FAILURE_NO_CHANNEL = 1;
    public static final int SAVE_NETWORK = 0x25007;
    public static final int SAVE_NETWORK_FAILED = 0x25008;
    public static final int SAVE_NETWORK_SUCCEEDED = 0x25009;
    public static final String SCAN_RESULTS_AVAILABLE_ACTION = "android.net.wifi.SCAN_RESULTS";
    public static final int START_WPS = 0x2500a;
    public static final int START_WPS_SUCCEEDED = 0x2500b;
    public static final String SUPPLICANT_CONNECTION_CHANGE_ACTION = "android.net.wifi.supplicant.CONNECTION_CHANGE";
    public static final String SUPPLICANT_STATE_CHANGED_ACTION = "android.net.wifi.supplicant.STATE_CHANGE";
    private static final String TAG = "WifiManager";
    public static final String WIFI_AP_STATE_CHANGED_ACTION = "android.net.wifi.WIFI_AP_STATE_CHANGED";
    public static final int WIFI_AP_STATE_DISABLED = 11;
    public static final int WIFI_AP_STATE_DISABLING = 10;
    public static final int WIFI_AP_STATE_ENABLED = 13;
    public static final int WIFI_AP_STATE_ENABLING = 12;
    public static final int WIFI_AP_STATE_FAILED = 14;
    public static final String WIFI_COUNTRY_CODE_CHANGED_ACTION = "android.net.wifi.COUNTRY_CODE_CHANGED";
    public static final String WIFI_CREDENTIAL_CHANGED_ACTION = "android.net.wifi.WIFI_CREDENTIAL_CHANGED";
    public static final int WIFI_CREDENTIAL_FORGOT = 1;
    public static final int WIFI_CREDENTIAL_SAVED = 0;
    public static final int WIFI_FEATURE_ADDITIONAL_STA = 2048;
    public static final int WIFI_FEATURE_AP_STA = 32768;
    public static final int WIFI_FEATURE_AWARE = 64;
    public static final int WIFI_FEATURE_BATCH_SCAN = 512;
    public static final int WIFI_FEATURE_CONFIG_NDO = 0x200000;
    public static final int WIFI_FEATURE_CONTROL_ROAMING = 0x800000;
    public static final int WIFI_FEATURE_D2AP_RTT = 256;
    public static final int WIFI_FEATURE_D2D_RTT = 128;
    public static final int WIFI_FEATURE_EPR = 16384;
    public static final int WIFI_FEATURE_HAL_EPNO = 0x40000;
    public static final int WIFI_FEATURE_IE_WHITELIST = 0x1000000;
    public static final int WIFI_FEATURE_INFRA = 1;
    public static final int WIFI_FEATURE_INFRA_5G = 2;
    public static final int WIFI_FEATURE_LINK_LAYER_STATS = 0x10000;
    public static final int WIFI_FEATURE_LOGGER = 0x20000;
    public static final int WIFI_FEATURE_MKEEP_ALIVE = 0x100000;
    public static final int WIFI_FEATURE_MOBILE_HOTSPOT = 16;
    public static final int WIFI_FEATURE_P2P = 8;
    public static final int WIFI_FEATURE_PASSPOINT = 4;
    public static final int WIFI_FEATURE_PNO = 1024;
    public static final int WIFI_FEATURE_RSSI_MONITOR = 0x80000;
    public static final int WIFI_FEATURE_SCANNER = 32;
    public static final int WIFI_FEATURE_SCAN_RAND = 0x2000000;
    public static final int WIFI_FEATURE_TDLS = 4096;
    public static final int WIFI_FEATURE_TDLS_OFFCHANNEL = 8192;
    public static final int WIFI_FEATURE_TRANSMIT_POWER = 0x400000;
    public static final int WIFI_FEATURE_TX_POWER_LIMIT = 0x4000000;
    public static final int WIFI_FREQUENCY_BAND_2GHZ = 2;
    public static final int WIFI_FREQUENCY_BAND_5GHZ = 1;
    public static final int WIFI_FREQUENCY_BAND_AUTO = 0;
    public static final int WIFI_MODE_FULL = 1;
    public static final int WIFI_MODE_FULL_HIGH_PERF = 3;
    public static final int WIFI_MODE_NO_LOCKS_HELD = 0;
    public static final int WIFI_MODE_SCAN_ONLY = 2;
    public static final String WIFI_SCAN_AVAILABLE = "wifi_scan_available";
    public static final String WIFI_STATE_CHANGED_ACTION = "android.net.wifi.WIFI_STATE_CHANGED";
    public static final int WIFI_STATE_DISABLED = 1;
    public static final int WIFI_STATE_DISABLING = 0;
    public static final int WIFI_STATE_ENABLED = 3;
    public static final int WIFI_STATE_ENABLING = 2;
    public static final int WIFI_STATE_UNKNOWN = 4;
    public static final int WPS_AUTH_FAILURE = 6;
    public static final int WPS_COMPLETED = 0x2500d;
    public static final int WPS_FAILED = 0x2500c;
    public static final int WPS_OVERLAP_ERROR = 3;
    public static final int WPS_TIMED_OUT = 7;
    public static final int WPS_TKIP_ONLY_PROHIBITED = 5;
    public static final int WPS_WEP_PROHIBITED = 4;
    private static final Object sServiceHandlerDispatchLock = new Object();
    private int mActiveLockCount;
    private AsyncChannel mAsyncChannel;
    private CountDownLatch mConnected;
    private Context mContext;
    private LocalOnlyHotspotCallbackProxy mLOHSCallbackProxy;
    private LocalOnlyHotspotObserverProxy mLOHSObserverProxy;
    private int mListenerKey;
    private final SparseArray mListenerMap = new SparseArray();
    private final Object mListenerMapLock = new Object();
    private final Object mLock = new Object();
    private Looper mLooper;
    IWifiManager mService;
    private final int mTargetSdkVersion;


    // Unreferenced inner class android/net/wifi/WifiManager$LocalOnlyHotspotCallbackProxy$1

/* anonymous class */
    class LocalOnlyHotspotCallbackProxy._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            WifiManager wifimanager;
            Log.d("WifiManager", (new StringBuilder()).append("LocalOnlyHotspotCallbackProxy: handle message what: ").append(message.what).append(" msg: ").append(message).toString());
            wifimanager = (WifiManager)LocalOnlyHotspotCallbackProxy._2D_get0(LocalOnlyHotspotCallbackProxy.this).get();
            if(wifimanager == null)
            {
                Log.w("WifiManager", "LocalOnlyHotspotCallbackProxy: handle message post GC");
                return;
            }
            message.what;
            JVM INSTR tableswitch 0 2: default 96
        //                       0 125
        //                       1 180
        //                       2 198;
               goto _L1 _L2 _L3 _L4
_L1:
            Log.e("WifiManager", (new StringBuilder()).append("LocalOnlyHotspotCallbackProxy unhandled message.  type: ").append(message.what).toString());
_L6:
            return;
_L2:
            message = (WifiConfiguration)message.obj;
            if(message == null)
            {
                Log.e("WifiManager", "LocalOnlyHotspotCallbackProxy: config cannot be null.");
                callback.onFailed(2);
                return;
            }
            LocalOnlyHotspotCallback localonlyhotspotcallback = callback;
            wifimanager.getClass();
            localonlyhotspotcallback.onStarted(wifimanager. new LocalOnlyHotspotReservation(message));
            continue; /* Loop/switch isn't completed */
_L3:
            Log.w("WifiManager", "LocalOnlyHotspotCallbackProxy: hotspot stopped");
            callback.onStopped();
            continue; /* Loop/switch isn't completed */
_L4:
            int i = message.arg1;
            Log.w("WifiManager", (new StringBuilder()).append("LocalOnlyHotspotCallbackProxy: failed to start.  reason: ").append(i).toString());
            callback.onFailed(i);
            Log.w("WifiManager", "done with the callback...");
            if(true) goto _L6; else goto _L5
_L5:
        }

        final LocalOnlyHotspotCallbackProxy this$1;
        final LocalOnlyHotspotCallback val$callback;

            
            {
                this$1 = final_localonlyhotspotcallbackproxy;
                callback = localonlyhotspotcallback;
                super(Looper.this);
            }
    }


    // Unreferenced inner class android/net/wifi/WifiManager$LocalOnlyHotspotObserverProxy$1

/* anonymous class */
    class LocalOnlyHotspotObserverProxy._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            WifiManager wifimanager;
            Log.d("WifiManager", (new StringBuilder()).append("LocalOnlyHotspotObserverProxy: handle message what: ").append(message.what).append(" msg: ").append(message).toString());
            wifimanager = (WifiManager)LocalOnlyHotspotObserverProxy._2D_get0(LocalOnlyHotspotObserverProxy.this).get();
            if(wifimanager == null)
            {
                Log.w("WifiManager", "LocalOnlyHotspotObserverProxy: handle message post GC");
                return;
            }
            message.what;
            JVM INSTR tableswitch 0 3: default 100
        //                       0 154
        //                       1 186
        //                       2 100
        //                       3 129;
               goto _L1 _L2 _L3 _L1 _L4
_L1:
            Log.e("WifiManager", (new StringBuilder()).append("LocalOnlyHotspotObserverProxy unhandled message.  type: ").append(message.what).toString());
_L6:
            return;
_L4:
            message = observer;
            wifimanager.getClass();
            message.onRegistered(wifimanager. new LocalOnlyHotspotSubscription());
            continue; /* Loop/switch isn't completed */
_L2:
            message = (WifiConfiguration)message.obj;
            if(message == null)
            {
                Log.e("WifiManager", "LocalOnlyHotspotObserverProxy: config cannot be null.");
                return;
            }
            observer.onStarted(message);
            continue; /* Loop/switch isn't completed */
_L3:
            observer.onStopped();
            if(true) goto _L6; else goto _L5
_L5:
        }

        final LocalOnlyHotspotObserverProxy this$1;
        final LocalOnlyHotspotObserver val$observer;

            
            {
                this$1 = final_localonlyhotspotobserverproxy;
                observer = localonlyhotspotobserver;
                super(Looper.this);
            }
    }

}
