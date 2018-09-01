// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p;

import android.content.Context;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceResponse;
import android.net.wifi.p2p.nsd.WifiP2pServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pServiceRequest;
import android.net.wifi.p2p.nsd.WifiP2pServiceResponse;
import android.net.wifi.p2p.nsd.WifiP2pUpnpServiceResponse;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.AsyncChannel;
import dalvik.system.CloseGuard;
import java.util.*;

// Referenced classes of package android.net.wifi.p2p:
//            WifiP2pConfig, IWifiP2pManager, WifiP2pDevice, WifiP2pWfdInfo, 
//            WifiP2pDeviceList, WifiP2pInfo, WifiP2pGroup, WifiP2pGroupList

public class WifiP2pManager
{
    public static interface ActionListener
    {

        public abstract void onFailure(int i);

        public abstract void onSuccess();
    }

    public static class Channel
        implements AutoCloseable
    {

        static AsyncChannel _2D_get0(Channel channel)
        {
            return channel.mAsyncChannel;
        }

        static ChannelListener _2D_get1(Channel channel)
        {
            return channel.mChannelListener;
        }

        static P2pHandler _2D_get2(Channel channel)
        {
            return channel.mHandler;
        }

        static ChannelListener _2D_set0(Channel channel, ChannelListener channellistener)
        {
            channel.mChannelListener = channellistener;
            return channellistener;
        }

        static DnsSdServiceResponseListener _2D_set1(Channel channel, DnsSdServiceResponseListener dnssdserviceresponselistener)
        {
            channel.mDnsSdServRspListener = dnssdserviceresponselistener;
            return dnssdserviceresponselistener;
        }

        static DnsSdTxtRecordListener _2D_set2(Channel channel, DnsSdTxtRecordListener dnssdtxtrecordlistener)
        {
            channel.mDnsSdTxtListener = dnssdtxtrecordlistener;
            return dnssdtxtrecordlistener;
        }

        static ServiceResponseListener _2D_set3(Channel channel, ServiceResponseListener serviceresponselistener)
        {
            channel.mServRspListener = serviceresponselistener;
            return serviceresponselistener;
        }

        static UpnpServiceResponseListener _2D_set4(Channel channel, UpnpServiceResponseListener upnpserviceresponselistener)
        {
            channel.mUpnpServRspListener = upnpserviceresponselistener;
            return upnpserviceresponselistener;
        }

        static int _2D_wrap0(Channel channel, Object obj)
        {
            return channel.putListener(obj);
        }

        static Object _2D_wrap1(Channel channel, int i)
        {
            return channel.getListener(i);
        }

        static void _2D_wrap2(Channel channel, WifiP2pServiceResponse wifip2pserviceresponse)
        {
            channel.handleServiceResponse(wifip2pserviceresponse);
        }

        private Object getListener(int i)
        {
            if(i == 0)
                return null;
            Object obj = mListenerMapLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = mListenerMap.remove(Integer.valueOf(i));
            obj;
            JVM INSTR monitorexit ;
            return obj1;
            Exception exception;
            exception;
            throw exception;
        }

        private void handleDnsSdServiceResponse(WifiP2pDnsSdServiceResponse wifip2pdnssdserviceresponse)
        {
            if(wifip2pdnssdserviceresponse.getDnsType() != 12) goto _L2; else goto _L1
_L1:
            if(mDnsSdServRspListener != null)
                mDnsSdServRspListener.onDnsSdServiceAvailable(wifip2pdnssdserviceresponse.getInstanceName(), wifip2pdnssdserviceresponse.getDnsQueryName(), wifip2pdnssdserviceresponse.getSrcDevice());
_L4:
            return;
_L2:
            if(wifip2pdnssdserviceresponse.getDnsType() == 16)
            {
                if(mDnsSdTxtListener != null)
                    mDnsSdTxtListener.onDnsSdTxtRecordAvailable(wifip2pdnssdserviceresponse.getDnsQueryName(), wifip2pdnssdserviceresponse.getTxtRecord(), wifip2pdnssdserviceresponse.getSrcDevice());
            } else
            {
                Log.e("WifiP2pManager", (new StringBuilder()).append("Unhandled resp ").append(wifip2pdnssdserviceresponse).toString());
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void handleServiceResponse(WifiP2pServiceResponse wifip2pserviceresponse)
        {
            if(!(wifip2pserviceresponse instanceof WifiP2pDnsSdServiceResponse)) goto _L2; else goto _L1
_L1:
            handleDnsSdServiceResponse((WifiP2pDnsSdServiceResponse)wifip2pserviceresponse);
_L4:
            return;
_L2:
            if(wifip2pserviceresponse instanceof WifiP2pUpnpServiceResponse)
            {
                if(mUpnpServRspListener != null)
                    handleUpnpServiceResponse((WifiP2pUpnpServiceResponse)wifip2pserviceresponse);
            } else
            if(mServRspListener != null)
                mServRspListener.onServiceAvailable(wifip2pserviceresponse.getServiceType(), wifip2pserviceresponse.getRawData(), wifip2pserviceresponse.getSrcDevice());
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void handleUpnpServiceResponse(WifiP2pUpnpServiceResponse wifip2pupnpserviceresponse)
        {
            mUpnpServRspListener.onUpnpServiceAvailable(wifip2pupnpserviceresponse.getUniqueServiceNames(), wifip2pupnpserviceresponse.getSrcDevice());
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
            mListenerMap.put(Integer.valueOf(i), obj);
            obj1;
            JVM INSTR monitorexit ;
            return i;
            obj;
            throw obj;
        }

        public void close()
        {
            if(mP2pManager == null)
                Log.w("WifiP2pManager", "Channel.close(): Null mP2pManager!?");
            else
                try
                {
                    mP2pManager.mService.close(mBinder);
                }
                catch(RemoteException remoteexception)
                {
                    throw remoteexception.rethrowFromSystemServer();
                }
            mAsyncChannel.disconnect();
            mCloseGuard.close();
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

        private static final int INVALID_LISTENER_KEY = 0;
        private AsyncChannel mAsyncChannel;
        final Binder mBinder;
        private ChannelListener mChannelListener;
        private final CloseGuard mCloseGuard = CloseGuard.get();
        Context mContext;
        private DnsSdServiceResponseListener mDnsSdServRspListener;
        private DnsSdTxtRecordListener mDnsSdTxtListener;
        private P2pHandler mHandler;
        private int mListenerKey;
        private HashMap mListenerMap;
        private final Object mListenerMapLock = new Object();
        private final WifiP2pManager mP2pManager;
        private ServiceResponseListener mServRspListener;
        private UpnpServiceResponseListener mUpnpServRspListener;

        public Channel(Context context, Looper looper, ChannelListener channellistener, Binder binder, WifiP2pManager wifip2pmanager)
        {
            mListenerMap = new HashMap();
            mListenerKey = 0;
            mAsyncChannel = new AsyncChannel();
            mHandler = new P2pHandler(looper);
            mChannelListener = channellistener;
            mContext = context;
            mBinder = binder;
            mP2pManager = wifip2pmanager;
            mCloseGuard.open("close");
        }
    }

    class Channel.P2pHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            Object obj = Channel._2D_wrap1(Channel.this, message.arg2);
            message.what;
            JVM INSTR lookupswitch 49: default 420
        //                       69636: 446
        //                       139266: 480
        //                       139267: 500
        //                       139269: 480
        //                       139270: 500
        //                       139272: 480
        //                       139273: 500
        //                       139275: 480
        //                       139276: 500
        //                       139278: 480
        //                       139279: 500
        //                       139281: 480
        //                       139282: 500
        //                       139284: 516
        //                       139286: 541
        //                       139288: 566
        //                       139293: 480
        //                       139294: 500
        //                       139296: 480
        //                       139297: 500
        //                       139299: 480
        //                       139300: 500
        //                       139302: 480
        //                       139303: 500
        //                       139305: 480
        //                       139306: 500
        //                       139308: 480
        //                       139309: 500
        //                       139311: 480
        //                       139312: 500
        //                       139314: 591
        //                       139316: 480
        //                       139317: 500
        //                       139319: 480
        //                       139320: 500
        //                       139322: 610
        //                       139324: 480
        //                       139325: 500
        //                       139327: 480
        //                       139328: 500
        //                       139330: 480
        //                       139331: 500
        //                       139333: 480
        //                       139334: 500
        //                       139336: 480
        //                       139337: 500
        //                       139341: 635
        //                       139344: 500
        //                       139345: 480;
               goto _L1 _L2 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L5 _L6 _L7 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L8 _L3 _L4 _L3 _L4 _L9 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L3 _L4 _L10 _L4 _L3
_L1:
            Log.d("WifiP2pManager", (new StringBuilder()).append("Ignored ").append(message).toString());
_L12:
            return;
_L2:
            if(Channel._2D_get1(Channel.this) != null)
            {
                Channel._2D_get1(Channel.this).onChannelDisconnected();
                Channel._2D_set0(Channel.this, null);
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if(obj != null)
                ((ActionListener)obj).onFailure(message.arg1);
            continue; /* Loop/switch isn't completed */
_L4:
            if(obj != null)
                ((ActionListener)obj).onSuccess();
            continue; /* Loop/switch isn't completed */
_L5:
            message = (WifiP2pDeviceList)message.obj;
            if(obj != null)
                ((PeerListListener)obj).onPeersAvailable(message);
            continue; /* Loop/switch isn't completed */
_L6:
            message = (WifiP2pInfo)message.obj;
            if(obj != null)
                ((ConnectionInfoListener)obj).onConnectionInfoAvailable(message);
            continue; /* Loop/switch isn't completed */
_L7:
            message = (WifiP2pGroup)message.obj;
            if(obj != null)
                ((GroupInfoListener)obj).onGroupInfoAvailable(message);
            continue; /* Loop/switch isn't completed */
_L8:
            message = (WifiP2pServiceResponse)message.obj;
            Channel._2D_wrap2(Channel.this, message);
            continue; /* Loop/switch isn't completed */
_L9:
            message = (WifiP2pGroupList)message.obj;
            if(obj != null)
                ((PersistentGroupInfoListener)obj).onPersistentGroupInfoAvailable(message);
            continue; /* Loop/switch isn't completed */
_L10:
            message = (Bundle)message.obj;
            if(obj != null)
            {
                if(message != null)
                    message = message.getString("android.net.wifi.p2p.EXTRA_HANDOVER_MESSAGE");
                else
                    message = null;
                ((HandoverMessageListener)obj).onHandoverMessageAvailable(message);
            }
            if(true) goto _L12; else goto _L11
_L11:
        }

        final Channel this$1;

        Channel.P2pHandler(Looper looper)
        {
            this$1 = Channel.this;
            super(looper);
        }
    }

    public static interface ChannelListener
    {

        public abstract void onChannelDisconnected();
    }

    public static interface ConnectionInfoListener
    {

        public abstract void onConnectionInfoAvailable(WifiP2pInfo wifip2pinfo);
    }

    public static interface DnsSdServiceResponseListener
    {

        public abstract void onDnsSdServiceAvailable(String s, String s1, WifiP2pDevice wifip2pdevice);
    }

    public static interface DnsSdTxtRecordListener
    {

        public abstract void onDnsSdTxtRecordAvailable(String s, Map map, WifiP2pDevice wifip2pdevice);
    }

    public static interface GroupInfoListener
    {

        public abstract void onGroupInfoAvailable(WifiP2pGroup wifip2pgroup);
    }

    public static interface HandoverMessageListener
    {

        public abstract void onHandoverMessageAvailable(String s);
    }

    public static interface PeerListListener
    {

        public abstract void onPeersAvailable(WifiP2pDeviceList wifip2pdevicelist);
    }

    public static interface PersistentGroupInfoListener
    {

        public abstract void onPersistentGroupInfoAvailable(WifiP2pGroupList wifip2pgrouplist);
    }

    public static interface ServiceResponseListener
    {

        public abstract void onServiceAvailable(int i, byte abyte0[], WifiP2pDevice wifip2pdevice);
    }

    public static interface UpnpServiceResponseListener
    {

        public abstract void onUpnpServiceAvailable(List list, WifiP2pDevice wifip2pdevice);
    }


    public WifiP2pManager(IWifiP2pManager iwifip2pmanager)
    {
        mService = iwifip2pmanager;
    }

    private static void checkChannel(Channel channel)
    {
        if(channel == null)
            throw new IllegalArgumentException("Channel needs to be initialized");
        else
            return;
    }

    private static void checkP2pConfig(WifiP2pConfig wifip2pconfig)
    {
        if(wifip2pconfig == null)
            throw new IllegalArgumentException("config cannot be null");
        if(TextUtils.isEmpty(wifip2pconfig.deviceAddress))
            throw new IllegalArgumentException("deviceAddress cannot be empty");
        else
            return;
    }

    private static void checkServiceInfo(WifiP2pServiceInfo wifip2pserviceinfo)
    {
        if(wifip2pserviceinfo == null)
            throw new IllegalArgumentException("service info is null");
        else
            return;
    }

    private static void checkServiceRequest(WifiP2pServiceRequest wifip2pservicerequest)
    {
        if(wifip2pservicerequest == null)
            throw new IllegalArgumentException("service request is null");
        else
            return;
    }

    private Channel initalizeChannel(Context context, Looper looper, ChannelListener channellistener, Messenger messenger, Binder binder)
    {
        if(messenger == null)
            return null;
        looper = new Channel(context, looper, channellistener, binder, this);
        if(Channel._2D_get0(looper).connectSync(context, Channel._2D_get2(looper), messenger) == 0)
        {
            return looper;
        } else
        {
            looper.close();
            return null;
        }
    }

    public void addLocalService(Channel channel, WifiP2pServiceInfo wifip2pserviceinfo, ActionListener actionlistener)
    {
        checkChannel(channel);
        checkServiceInfo(wifip2pserviceinfo);
        Channel._2D_get0(channel).sendMessage(0x2201c, 0, Channel._2D_wrap0(channel, actionlistener), wifip2pserviceinfo);
    }

    public void addServiceRequest(Channel channel, WifiP2pServiceRequest wifip2pservicerequest, ActionListener actionlistener)
    {
        checkChannel(channel);
        checkServiceRequest(wifip2pservicerequest);
        Channel._2D_get0(channel).sendMessage(0x22025, 0, Channel._2D_wrap0(channel, actionlistener), wifip2pservicerequest);
    }

    public void cancelConnect(Channel channel, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x2200a, 0, Channel._2D_wrap0(channel, actionlistener));
    }

    public void clearLocalServices(Channel channel, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x22022, 0, Channel._2D_wrap0(channel, actionlistener));
    }

    public void clearServiceRequests(Channel channel, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x2202b, 0, Channel._2D_wrap0(channel, actionlistener));
    }

    public void connect(Channel channel, WifiP2pConfig wifip2pconfig, ActionListener actionlistener)
    {
        checkChannel(channel);
        checkP2pConfig(wifip2pconfig);
        Channel._2D_get0(channel).sendMessage(0x22007, 0, Channel._2D_wrap0(channel, actionlistener), wifip2pconfig);
    }

    public void createGroup(Channel channel, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x2200d, -2, Channel._2D_wrap0(channel, actionlistener));
    }

    public void deletePersistentGroup(Channel channel, int i, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x22036, i, Channel._2D_wrap0(channel, actionlistener));
    }

    public void discoverPeers(Channel channel, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x22001, 0, Channel._2D_wrap0(channel, actionlistener));
    }

    public void discoverServices(Channel channel, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x2202e, 0, Channel._2D_wrap0(channel, actionlistener));
    }

    public Messenger getMessenger(Binder binder)
    {
        try
        {
            binder = mService.getMessenger(binder);
        }
        // Misplaced declaration of an exception variable
        catch(Binder binder)
        {
            throw binder.rethrowFromSystemServer();
        }
        return binder;
    }

    public void getNfcHandoverRequest(Channel channel, HandoverMessageListener handovermessagelistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x2204b, 0, Channel._2D_wrap0(channel, handovermessagelistener));
    }

    public void getNfcHandoverSelect(Channel channel, HandoverMessageListener handovermessagelistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x2204c, 0, Channel._2D_wrap0(channel, handovermessagelistener));
    }

    public Messenger getP2pStateMachineMessenger()
    {
        Messenger messenger;
        try
        {
            messenger = mService.getP2pStateMachineMessenger();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return messenger;
    }

    public Channel initialize(Context context, Looper looper, ChannelListener channellistener)
    {
        Binder binder = new Binder();
        Context context1 = context.getApplicationContext();
        if(context1 != null)
            context = context1;
        return initalizeChannel(context, looper, channellistener, getMessenger(binder), binder);
    }

    public Channel initializeInternal(Context context, Looper looper, ChannelListener channellistener)
    {
        return initalizeChannel(context, looper, channellistener, getP2pStateMachineMessenger(), null);
    }

    public void initiatorReportNfcHandover(Channel channel, String s, ActionListener actionlistener)
    {
        checkChannel(channel);
        Bundle bundle = new Bundle();
        bundle.putString("android.net.wifi.p2p.EXTRA_HANDOVER_MESSAGE", s);
        Channel._2D_get0(channel).sendMessage(0x2204e, 0, Channel._2D_wrap0(channel, actionlistener), bundle);
    }

    public void listen(Channel channel, boolean flag, ActionListener actionlistener)
    {
        checkChannel(channel);
        AsyncChannel asyncchannel = Channel._2D_get0(channel);
        int i;
        if(flag)
            i = 0x22041;
        else
            i = 0x22044;
        asyncchannel.sendMessage(i, 0, Channel._2D_wrap0(channel, actionlistener));
    }

    public void removeGroup(Channel channel, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x22010, 0, Channel._2D_wrap0(channel, actionlistener));
    }

    public void removeLocalService(Channel channel, WifiP2pServiceInfo wifip2pserviceinfo, ActionListener actionlistener)
    {
        checkChannel(channel);
        checkServiceInfo(wifip2pserviceinfo);
        Channel._2D_get0(channel).sendMessage(0x2201f, 0, Channel._2D_wrap0(channel, actionlistener), wifip2pserviceinfo);
    }

    public void removeServiceRequest(Channel channel, WifiP2pServiceRequest wifip2pservicerequest, ActionListener actionlistener)
    {
        checkChannel(channel);
        checkServiceRequest(wifip2pservicerequest);
        Channel._2D_get0(channel).sendMessage(0x22028, 0, Channel._2D_wrap0(channel, actionlistener), wifip2pservicerequest);
    }

    public void requestConnectionInfo(Channel channel, ConnectionInfoListener connectioninfolistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x22015, 0, Channel._2D_wrap0(channel, connectioninfolistener));
    }

    public void requestGroupInfo(Channel channel, GroupInfoListener groupinfolistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x22017, 0, Channel._2D_wrap0(channel, groupinfolistener));
    }

    public void requestPeers(Channel channel, PeerListListener peerlistlistener)
    {
        checkChannel(channel);
        Bundle bundle = new Bundle();
        bundle.putString("android.net.wifi.p2p.CALLING_PACKAGE", channel.mContext.getOpPackageName());
        Channel._2D_get0(channel).sendMessage(0x22013, 0, Channel._2D_wrap0(channel, peerlistlistener), bundle);
    }

    public void requestPersistentGroupInfo(Channel channel, PersistentGroupInfoListener persistentgroupinfolistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x22039, 0, Channel._2D_wrap0(channel, persistentgroupinfolistener));
    }

    public void responderReportNfcHandover(Channel channel, String s, ActionListener actionlistener)
    {
        checkChannel(channel);
        Bundle bundle = new Bundle();
        bundle.putString("android.net.wifi.p2p.EXTRA_HANDOVER_MESSAGE", s);
        Channel._2D_get0(channel).sendMessage(0x2204f, 0, Channel._2D_wrap0(channel, actionlistener), bundle);
    }

    public void setDeviceName(Channel channel, String s, ActionListener actionlistener)
    {
        checkChannel(channel);
        WifiP2pDevice wifip2pdevice = new WifiP2pDevice();
        wifip2pdevice.deviceName = s;
        Channel._2D_get0(channel).sendMessage(0x22033, 0, Channel._2D_wrap0(channel, actionlistener), wifip2pdevice);
    }

    public void setDnsSdResponseListeners(Channel channel, DnsSdServiceResponseListener dnssdserviceresponselistener, DnsSdTxtRecordListener dnssdtxtrecordlistener)
    {
        checkChannel(channel);
        Channel._2D_set1(channel, dnssdserviceresponselistener);
        Channel._2D_set2(channel, dnssdtxtrecordlistener);
    }

    public void setMiracastMode(int i)
    {
        try
        {
            mService.setMiracastMode(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setServiceResponseListener(Channel channel, ServiceResponseListener serviceresponselistener)
    {
        checkChannel(channel);
        Channel._2D_set3(channel, serviceresponselistener);
    }

    public void setUpnpServiceResponseListener(Channel channel, UpnpServiceResponseListener upnpserviceresponselistener)
    {
        checkChannel(channel);
        Channel._2D_set4(channel, upnpserviceresponselistener);
    }

    public void setWFDInfo(Channel channel, WifiP2pWfdInfo wifip2pwfdinfo, ActionListener actionlistener)
    {
        checkChannel(channel);
        try
        {
            mService.checkConfigureWifiDisplayPermission();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.rethrowFromSystemServer();
        }
        Channel._2D_get0(channel).sendMessage(0x2203b, 0, Channel._2D_wrap0(channel, actionlistener), wifip2pwfdinfo);
    }

    public void setWifiP2pChannels(Channel channel, int i, int j, ActionListener actionlistener)
    {
        checkChannel(channel);
        Bundle bundle = new Bundle();
        bundle.putInt("lc", i);
        bundle.putInt("oc", j);
        Channel._2D_get0(channel).sendMessage(0x22047, 0, Channel._2D_wrap0(channel, actionlistener), bundle);
    }

    public void startWps(Channel channel, WpsInfo wpsinfo, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x2203e, 0, Channel._2D_wrap0(channel, actionlistener), wpsinfo);
    }

    public void stopPeerDiscovery(Channel channel, ActionListener actionlistener)
    {
        checkChannel(channel);
        Channel._2D_get0(channel).sendMessage(0x22004, 0, Channel._2D_wrap0(channel, actionlistener));
    }

    public static final int ADD_LOCAL_SERVICE = 0x2201c;
    public static final int ADD_LOCAL_SERVICE_FAILED = 0x2201d;
    public static final int ADD_LOCAL_SERVICE_SUCCEEDED = 0x2201e;
    public static final int ADD_SERVICE_REQUEST = 0x22025;
    public static final int ADD_SERVICE_REQUEST_FAILED = 0x22026;
    public static final int ADD_SERVICE_REQUEST_SUCCEEDED = 0x22027;
    private static final int BASE = 0x22000;
    public static final int BUSY = 2;
    public static final String CALLING_PACKAGE = "android.net.wifi.p2p.CALLING_PACKAGE";
    public static final int CANCEL_CONNECT = 0x2200a;
    public static final int CANCEL_CONNECT_FAILED = 0x2200b;
    public static final int CANCEL_CONNECT_SUCCEEDED = 0x2200c;
    public static final int CLEAR_LOCAL_SERVICES = 0x22022;
    public static final int CLEAR_LOCAL_SERVICES_FAILED = 0x22023;
    public static final int CLEAR_LOCAL_SERVICES_SUCCEEDED = 0x22024;
    public static final int CLEAR_SERVICE_REQUESTS = 0x2202b;
    public static final int CLEAR_SERVICE_REQUESTS_FAILED = 0x2202c;
    public static final int CLEAR_SERVICE_REQUESTS_SUCCEEDED = 0x2202d;
    public static final int CONNECT = 0x22007;
    public static final int CONNECT_FAILED = 0x22008;
    public static final int CONNECT_SUCCEEDED = 0x22009;
    public static final int CREATE_GROUP = 0x2200d;
    public static final int CREATE_GROUP_FAILED = 0x2200e;
    public static final int CREATE_GROUP_SUCCEEDED = 0x2200f;
    public static final int DELETE_PERSISTENT_GROUP = 0x22036;
    public static final int DELETE_PERSISTENT_GROUP_FAILED = 0x22037;
    public static final int DELETE_PERSISTENT_GROUP_SUCCEEDED = 0x22038;
    public static final int DISCOVER_PEERS = 0x22001;
    public static final int DISCOVER_PEERS_FAILED = 0x22002;
    public static final int DISCOVER_PEERS_SUCCEEDED = 0x22003;
    public static final int DISCOVER_SERVICES = 0x2202e;
    public static final int DISCOVER_SERVICES_FAILED = 0x2202f;
    public static final int DISCOVER_SERVICES_SUCCEEDED = 0x22030;
    public static final int ERROR = 0;
    public static final String EXTRA_DISCOVERY_STATE = "discoveryState";
    public static final String EXTRA_HANDOVER_MESSAGE = "android.net.wifi.p2p.EXTRA_HANDOVER_MESSAGE";
    public static final String EXTRA_NETWORK_INFO = "networkInfo";
    public static final String EXTRA_P2P_DEVICE_LIST = "wifiP2pDeviceList";
    public static final String EXTRA_WIFI_P2P_DEVICE = "wifiP2pDevice";
    public static final String EXTRA_WIFI_P2P_GROUP = "p2pGroupInfo";
    public static final String EXTRA_WIFI_P2P_INFO = "wifiP2pInfo";
    public static final String EXTRA_WIFI_STATE = "wifi_p2p_state";
    public static final int GET_HANDOVER_REQUEST = 0x2204b;
    public static final int GET_HANDOVER_SELECT = 0x2204c;
    public static final int INITIATOR_REPORT_NFC_HANDOVER = 0x2204e;
    public static final int MIRACAST_DISABLED = 0;
    public static final int MIRACAST_SINK = 2;
    public static final int MIRACAST_SOURCE = 1;
    public static final int NO_SERVICE_REQUESTS = 3;
    public static final int P2P_UNSUPPORTED = 1;
    public static final int PING = 0x22031;
    public static final int REMOVE_GROUP = 0x22010;
    public static final int REMOVE_GROUP_FAILED = 0x22011;
    public static final int REMOVE_GROUP_SUCCEEDED = 0x22012;
    public static final int REMOVE_LOCAL_SERVICE = 0x2201f;
    public static final int REMOVE_LOCAL_SERVICE_FAILED = 0x22020;
    public static final int REMOVE_LOCAL_SERVICE_SUCCEEDED = 0x22021;
    public static final int REMOVE_SERVICE_REQUEST = 0x22028;
    public static final int REMOVE_SERVICE_REQUEST_FAILED = 0x22029;
    public static final int REMOVE_SERVICE_REQUEST_SUCCEEDED = 0x2202a;
    public static final int REPORT_NFC_HANDOVER_FAILED = 0x22051;
    public static final int REPORT_NFC_HANDOVER_SUCCEEDED = 0x22050;
    public static final int REQUEST_CONNECTION_INFO = 0x22015;
    public static final int REQUEST_GROUP_INFO = 0x22017;
    public static final int REQUEST_PEERS = 0x22013;
    public static final int REQUEST_PERSISTENT_GROUP_INFO = 0x22039;
    public static final int RESPONDER_REPORT_NFC_HANDOVER = 0x2204f;
    public static final int RESPONSE_CONNECTION_INFO = 0x22016;
    public static final int RESPONSE_GET_HANDOVER_MESSAGE = 0x2204d;
    public static final int RESPONSE_GROUP_INFO = 0x22018;
    public static final int RESPONSE_PEERS = 0x22014;
    public static final int RESPONSE_PERSISTENT_GROUP_INFO = 0x2203a;
    public static final int RESPONSE_SERVICE = 0x22032;
    public static final int SET_CHANNEL = 0x22047;
    public static final int SET_CHANNEL_FAILED = 0x22048;
    public static final int SET_CHANNEL_SUCCEEDED = 0x22049;
    public static final int SET_DEVICE_NAME = 0x22033;
    public static final int SET_DEVICE_NAME_FAILED = 0x22034;
    public static final int SET_DEVICE_NAME_SUCCEEDED = 0x22035;
    public static final int SET_WFD_INFO = 0x2203b;
    public static final int SET_WFD_INFO_FAILED = 0x2203c;
    public static final int SET_WFD_INFO_SUCCEEDED = 0x2203d;
    public static final int START_LISTEN = 0x22041;
    public static final int START_LISTEN_FAILED = 0x22042;
    public static final int START_LISTEN_SUCCEEDED = 0x22043;
    public static final int START_WPS = 0x2203e;
    public static final int START_WPS_FAILED = 0x2203f;
    public static final int START_WPS_SUCCEEDED = 0x22040;
    public static final int STOP_DISCOVERY = 0x22004;
    public static final int STOP_DISCOVERY_FAILED = 0x22005;
    public static final int STOP_DISCOVERY_SUCCEEDED = 0x22006;
    public static final int STOP_LISTEN = 0x22044;
    public static final int STOP_LISTEN_FAILED = 0x22045;
    public static final int STOP_LISTEN_SUCCEEDED = 0x22046;
    private static final String TAG = "WifiP2pManager";
    public static final String WIFI_P2P_CONNECTION_CHANGED_ACTION = "android.net.wifi.p2p.CONNECTION_STATE_CHANGE";
    public static final String WIFI_P2P_DISCOVERY_CHANGED_ACTION = "android.net.wifi.p2p.DISCOVERY_STATE_CHANGE";
    public static final int WIFI_P2P_DISCOVERY_STARTED = 2;
    public static final int WIFI_P2P_DISCOVERY_STOPPED = 1;
    public static final String WIFI_P2P_PEERS_CHANGED_ACTION = "android.net.wifi.p2p.PEERS_CHANGED";
    public static final String WIFI_P2P_PERSISTENT_GROUPS_CHANGED_ACTION = "android.net.wifi.p2p.PERSISTENT_GROUPS_CHANGED";
    public static final String WIFI_P2P_STATE_CHANGED_ACTION = "android.net.wifi.p2p.STATE_CHANGED";
    public static final int WIFI_P2P_STATE_DISABLED = 1;
    public static final int WIFI_P2P_STATE_ENABLED = 2;
    public static final String WIFI_P2P_THIS_DEVICE_CHANGED_ACTION = "android.net.wifi.p2p.THIS_DEVICE_CHANGED";
    IWifiP2pManager mService;
}
