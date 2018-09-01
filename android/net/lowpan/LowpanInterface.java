// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.content.Context;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.os.*;
import android.util.Log;
import java.util.HashMap;

// Referenced classes of package android.net.lowpan:
//            LowpanException, ILowpanInterface, LowpanScanner, LowpanIdentity, 
//            LowpanCommissioningSession, ILowpanInterfaceListener, LowpanProvision, LowpanCredential, 
//            LowpanChannelInfo, LowpanBeaconInfo

public class LowpanInterface
{
    public static abstract class Callback
    {

        public void onConnectedChanged(boolean flag)
        {
        }

        public void onEnabledChanged(boolean flag)
        {
        }

        public void onLinkAddressAdded(LinkAddress linkaddress)
        {
        }

        public void onLinkAddressRemoved(LinkAddress linkaddress)
        {
        }

        public void onLinkNetworkAdded(IpPrefix ipprefix)
        {
        }

        public void onLinkNetworkRemoved(IpPrefix ipprefix)
        {
        }

        public void onLowpanIdentityChanged(LowpanIdentity lowpanidentity)
        {
        }

        public void onRoleChanged(String s)
        {
        }

        public void onStateChanged(String s)
        {
        }

        public void onUpChanged(boolean flag)
        {
        }

        public Callback()
        {
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static Looper _2D_get1(LowpanInterface lowpaninterface)
    {
        return lowpaninterface.mLooper;
    }

    public LowpanInterface(Context context, ILowpanInterface ilowpaninterface, Looper looper)
    {
        mBinder = ilowpaninterface;
        mLooper = looper;
    }

    public void addExternalRoute(IpPrefix ipprefix, int i)
        throws LowpanException
    {
        try
        {
            mBinder.addExternalRoute(ipprefix, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IpPrefix ipprefix)
        {
            throw ipprefix.rethrowAsRuntimeException();
        }
        // Misplaced declaration of an exception variable
        catch(IpPrefix ipprefix)
        {
            throw LowpanException.rethrowFromServiceSpecificException(ipprefix);
        }
    }

    public void addOnMeshPrefix(IpPrefix ipprefix, int i)
        throws LowpanException
    {
        try
        {
            mBinder.addOnMeshPrefix(ipprefix, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IpPrefix ipprefix)
        {
            throw ipprefix.rethrowAsRuntimeException();
        }
        // Misplaced declaration of an exception variable
        catch(IpPrefix ipprefix)
        {
            throw LowpanException.rethrowFromServiceSpecificException(ipprefix);
        }
    }

    public void attach(LowpanProvision lowpanprovision)
        throws LowpanException
    {
        try
        {
            mBinder.attach(lowpanprovision);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(LowpanProvision lowpanprovision)
        {
            throw lowpanprovision.rethrowAsRuntimeException();
        }
        // Misplaced declaration of an exception variable
        catch(LowpanProvision lowpanprovision)
        {
            throw LowpanException.rethrowFromServiceSpecificException(lowpanprovision);
        }
    }

    public LowpanScanner createScanner()
    {
        return new LowpanScanner(mBinder);
    }

    public void form(LowpanProvision lowpanprovision)
        throws LowpanException
    {
        try
        {
            mBinder.form(lowpanprovision);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(LowpanProvision lowpanprovision)
        {
            throw lowpanprovision.rethrowAsRuntimeException();
        }
        // Misplaced declaration of an exception variable
        catch(LowpanProvision lowpanprovision)
        {
            throw LowpanException.rethrowFromServiceSpecificException(lowpanprovision);
        }
    }

    public LinkAddress[] getLinkAddresses()
        throws LowpanException
    {
        String as[];
        LinkAddress alinkaddress[];
        int i;
        int j;
        int k;
        try
        {
            as = mBinder.getLinkAddresses();
            alinkaddress = new LinkAddress[as.length];
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
        i = 0;
        j = as.length;
        k = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        alinkaddress[k] = new LinkAddress(as[i]);
        i++;
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        return alinkaddress;
    }

    public IpPrefix[] getLinkNetworks()
        throws LowpanException
    {
        IpPrefix aipprefix[];
        try
        {
            aipprefix = mBinder.getLinkNetworks();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
        return aipprefix;
    }

    public LowpanCredential getLowpanCredential()
    {
        LowpanCredential lowpancredential;
        try
        {
            lowpancredential = mBinder.getLowpanCredential();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return lowpancredential;
    }

    public LowpanIdentity getLowpanIdentity()
    {
        LowpanIdentity lowpanidentity;
        try
        {
            lowpanidentity = mBinder.getLowpanIdentity();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return new LowpanIdentity();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return lowpanidentity;
    }

    public String getName()
    {
        String s;
        try
        {
            s = mBinder.getName();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return "";
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return s;
    }

    public String getPartitionId()
    {
        String s;
        try
        {
            s = mBinder.getPartitionId();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return "";
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return s;
    }

    public String getRole()
    {
        String s;
        try
        {
            s = mBinder.getRole();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return "detached";
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return s;
    }

    public ILowpanInterface getService()
    {
        return mBinder;
    }

    public String getState()
    {
        String s;
        try
        {
            s = mBinder.getState();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return "fault";
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return s;
    }

    public LowpanChannelInfo[] getSupportedChannels()
        throws LowpanException
    {
        LowpanChannelInfo alowpanchannelinfo[];
        try
        {
            alowpanchannelinfo = mBinder.getSupportedChannels();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
        return alowpanchannelinfo;
    }

    public String[] getSupportedNetworkTypes()
        throws LowpanException
    {
        String as[];
        try
        {
            as = mBinder.getSupportedNetworkTypes();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
        return as;
    }

    public boolean isCommissioned()
    {
        boolean flag;
        try
        {
            flag = mBinder.isCommissioned();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return false;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return flag;
    }

    public boolean isConnected()
    {
        boolean flag;
        try
        {
            flag = mBinder.isConnected();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return false;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return flag;
    }

    public boolean isEnabled()
    {
        boolean flag;
        try
        {
            flag = mBinder.isEnabled();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return false;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return flag;
    }

    public boolean isUp()
    {
        boolean flag;
        try
        {
            flag = mBinder.isUp();
        }
        catch(DeadObjectException deadobjectexception)
        {
            return false;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        return flag;
    }

    public void join(LowpanProvision lowpanprovision)
        throws LowpanException
    {
        try
        {
            mBinder.join(lowpanprovision);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(LowpanProvision lowpanprovision)
        {
            throw lowpanprovision.rethrowAsRuntimeException();
        }
        // Misplaced declaration of an exception variable
        catch(LowpanProvision lowpanprovision)
        {
            throw LowpanException.rethrowFromServiceSpecificException(lowpanprovision);
        }
    }

    public void leave()
        throws LowpanException
    {
        try
        {
            mBinder.leave();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
    }

    public void registerCallback(Callback callback)
    {
        registerCallback(callback, null);
    }

    public void registerCallback(final Callback cb, final Handler handler)
    {
        ILowpanInterfaceListener.Stub stub = new ILowpanInterfaceListener.Stub() {

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_18511(Callback callback, boolean flag)
            {
                callback.onEnabledChanged(flag);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_18705(Callback callback, boolean flag)
            {
                callback.onConnectedChanged(flag);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_18894(Callback callback, boolean flag)
            {
                callback.onUpChanged(flag);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_19077(Callback callback, String s)
            {
                callback.onRoleChanged(s);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_19263(Callback callback, String s)
            {
                callback.onStateChanged(s);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_19467(Callback callback, LowpanIdentity lowpanidentity)
            {
                callback.onLowpanIdentityChanged(lowpanidentity);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_19669(Callback callback, IpPrefix ipprefix)
            {
                callback.onLinkNetworkAdded(ipprefix);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_19868(Callback callback, IpPrefix ipprefix)
            {
                callback.onLinkNetworkRemoved(ipprefix);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_20493(Callback callback, LinkAddress linkaddress)
            {
                callback.onLinkAddressAdded(linkaddress);
            }

            static void lambda$_2D_android_net_lowpan_LowpanInterface$1_21249(Callback callback, LinkAddress linkaddress)
            {
                callback.onLinkAddressRemoved(linkaddress);
            }

            public void onConnectedChanged(boolean flag)
            {
                mHandler.post(new _.Lambda.kGwbyTn61Si3sH7muskKIr7PCeU((byte)0, flag, cb));
            }

            public void onEnabledChanged(boolean flag)
            {
                mHandler.post(new _.Lambda.kGwbyTn61Si3sH7muskKIr7PCeU((byte)1, flag, cb));
            }

            public void onLinkAddressAdded(String s)
            {
                LinkAddress linkaddress;
                try
                {
                    linkaddress = new LinkAddress(s);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    Log.e(LowpanInterface._2D_get0(), (new StringBuilder()).append("onLinkAddressAdded: Bad LinkAddress \"").append(s).append("\", ").append(illegalargumentexception).toString());
                    return;
                }
                mHandler.post(new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)1, cb, linkaddress));
            }

            public void onLinkAddressRemoved(String s)
            {
                LinkAddress linkaddress;
                try
                {
                    linkaddress = new LinkAddress(s);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    Log.e(LowpanInterface._2D_get0(), (new StringBuilder()).append("onLinkAddressRemoved: Bad LinkAddress \"").append(s).append("\", ").append(illegalargumentexception).toString());
                    return;
                }
                mHandler.post(new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)2, cb, linkaddress));
            }

            public void onLinkNetworkAdded(IpPrefix ipprefix)
            {
                mHandler.post(new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)3, cb, ipprefix));
            }

            public void onLinkNetworkRemoved(IpPrefix ipprefix)
            {
                mHandler.post(new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)4, cb, ipprefix));
            }

            public void onLowpanIdentityChanged(LowpanIdentity lowpanidentity)
            {
                mHandler.post(new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)5, cb, lowpanidentity));
            }

            public void onReceiveFromCommissioner(byte abyte0[])
            {
            }

            public void onRoleChanged(String s)
            {
                mHandler.post(new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)6, cb, s));
            }

            public void onStateChanged(String s)
            {
                mHandler.post(new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)7, cb, s));
            }

            public void onUpChanged(boolean flag)
            {
                mHandler.post(new _.Lambda.kGwbyTn61Si3sH7muskKIr7PCeU((byte)2, flag, cb));
            }

            private Handler mHandler;
            final LowpanInterface this$0;
            final Callback val$cb;
            final Handler val$handler;

            
            {
                this$0 = LowpanInterface.this;
                handler = handler1;
                cb = callback;
                super();
                if(handler != null)
                    mHandler = handler;
                else
                if(LowpanInterface._2D_get1(LowpanInterface.this) != null)
                    mHandler = new Handler(LowpanInterface._2D_get1(LowpanInterface.this));
                else
                    mHandler = new Handler();
            }
        }
;
        try
        {
            mBinder.addListener(stub);
        }
        // Misplaced declaration of an exception variable
        catch(final Callback cb)
        {
            throw cb.rethrowAsRuntimeException();
        }
        handler = mListenerMap;
        handler;
        JVM INSTR monitorenter ;
        mListenerMap.put(Integer.valueOf(System.identityHashCode(cb)), stub);
        handler;
        JVM INSTR monitorexit ;
        return;
        cb;
        throw cb;
    }

    public void removeExternalRoute(IpPrefix ipprefix)
    {
        mBinder.removeExternalRoute(ipprefix);
_L1:
        return;
        ipprefix;
        Log.e(TAG, ipprefix.toString());
          goto _L1
        ipprefix;
        throw ipprefix.rethrowAsRuntimeException();
    }

    public void removeOnMeshPrefix(IpPrefix ipprefix)
    {
        mBinder.removeOnMeshPrefix(ipprefix);
_L1:
        return;
        ipprefix;
        Log.e(TAG, ipprefix.toString());
          goto _L1
        ipprefix;
        throw ipprefix.rethrowAsRuntimeException();
    }

    public void reset()
        throws LowpanException
    {
        try
        {
            mBinder.reset();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
    }

    public void setEnabled(boolean flag)
        throws LowpanException
    {
        try
        {
            mBinder.setEnabled(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
    }

    public LowpanCommissioningSession startCommissioningSession(LowpanBeaconInfo lowpanbeaconinfo)
        throws LowpanException
    {
        try
        {
            mBinder.startCommissioningSession(lowpanbeaconinfo);
            lowpanbeaconinfo = new LowpanCommissioningSession(mBinder, lowpanbeaconinfo, mLooper);
        }
        // Misplaced declaration of an exception variable
        catch(LowpanBeaconInfo lowpanbeaconinfo)
        {
            throw lowpanbeaconinfo.rethrowAsRuntimeException();
        }
        // Misplaced declaration of an exception variable
        catch(LowpanBeaconInfo lowpanbeaconinfo)
        {
            throw LowpanException.rethrowFromServiceSpecificException(lowpanbeaconinfo);
        }
        return lowpanbeaconinfo;
    }

    public void unregisterCallback(Callback callback)
    {
        int i = System.identityHashCode(callback);
        callback = mListenerMap;
        callback;
        JVM INSTR monitorenter ;
        ILowpanInterfaceListener ilowpaninterfacelistener = (ILowpanInterfaceListener)mListenerMap.get(Integer.valueOf(i));
        if(ilowpaninterfacelistener == null)
            break MISSING_BLOCK_LABEL_53;
        mListenerMap.remove(Integer.valueOf(i));
        mBinder.removeListener(ilowpaninterfacelistener);
_L2:
        callback;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowAsRuntimeException();
        obj;
        callback;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final String EMPTY_PARTITION_ID = "";
    public static final String NETWORK_TYPE_THREAD_V1 = "org.threadgroup.thread.v1";
    public static final String ROLE_COORDINATOR = "coordinator";
    public static final String ROLE_DETACHED = "detached";
    public static final String ROLE_END_DEVICE = "end-device";
    public static final String ROLE_LEADER = "leader";
    public static final String ROLE_ROUTER = "router";
    public static final String ROLE_SLEEPY_END_DEVICE = "sleepy-end-device";
    public static final String ROLE_SLEEPY_ROUTER = "sleepy-router";
    public static final String STATE_ATTACHED = "attached";
    public static final String STATE_ATTACHING = "attaching";
    public static final String STATE_COMMISSIONING = "commissioning";
    public static final String STATE_FAULT = "fault";
    public static final String STATE_OFFLINE = "offline";
    private static final String TAG = android/net/lowpan/LowpanInterface.getSimpleName();
    private final ILowpanInterface mBinder;
    private final HashMap mListenerMap = new HashMap();
    private final Looper mLooper;

}
