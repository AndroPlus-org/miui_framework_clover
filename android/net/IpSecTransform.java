// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.os.*;
import android.util.Log;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.IOException;
import java.net.InetAddress;

// Referenced classes of package android.net:
//            IIpSecService, IpSecTransformResponse, IpSecConfig, ConnectivityManager, 
//            IpSecAlgorithm, Network

public final class IpSecTransform
    implements AutoCloseable
{
    public static class Builder
    {

        public IpSecTransform buildTransportModeTransform(InetAddress inetaddress)
            throws IpSecManager.ResourceUnavailableException, IpSecManager.SpiUnavailableException, IOException
        {
            mConfig.mode = 1;
            mConfig.remoteAddress = inetaddress;
            return IpSecTransform._2D_wrap0(new IpSecTransform(mContext, mConfig, null));
        }

        public IpSecTransform buildTunnelModeTransform(InetAddress inetaddress, InetAddress inetaddress1)
        {
            mConfig.localAddress = inetaddress;
            mConfig.remoteAddress = inetaddress1;
            mConfig.mode = 0;
            return new IpSecTransform(mContext, mConfig, null);
        }

        public Builder setAuthentication(int i, IpSecAlgorithm ipsecalgorithm)
        {
            mConfig.flow[i].authentication = ipsecalgorithm;
            return this;
        }

        public Builder setEncryption(int i, IpSecAlgorithm ipsecalgorithm)
        {
            mConfig.flow[i].encryption = ipsecalgorithm;
            return this;
        }

        public Builder setIpv4Encapsulation(IpSecManager.UdpEncapsulationSocket udpencapsulationsocket, int i)
        {
            mConfig.encapType = 2;
            mConfig.encapLocalPortResourceId = udpencapsulationsocket.getResourceId();
            mConfig.encapRemotePort = i;
            return this;
        }

        public Builder setNattKeepalive(int i)
        {
            mConfig.nattKeepaliveInterval = i;
            return this;
        }

        public Builder setSpi(int i, IpSecManager.SecurityParameterIndex securityparameterindex)
        {
            mConfig.flow[i].spiResourceId = securityparameterindex.getResourceId();
            return this;
        }

        public Builder setUnderlyingNetwork(Network network)
        {
            mConfig.network = network;
            return this;
        }

        private IpSecConfig mConfig;
        private Context mContext;

        public Builder(Context context)
        {
            Preconditions.checkNotNull(context);
            mContext = context;
            mConfig = new IpSecConfig();
        }
    }


    static Object _2D_get0(IpSecTransform ipsectransform)
    {
        return ipsectransform.mKeepaliveSyncLock;
    }

    static int _2D_set0(IpSecTransform ipsectransform, int i)
    {
        ipsectransform.mKeepaliveStatus = i;
        return i;
    }

    static IpSecTransform _2D_wrap0(IpSecTransform ipsectransform)
    {
        return ipsectransform.activate();
    }

    private IpSecTransform(Context context, IpSecConfig ipsecconfig)
    {
        mCloseGuard = CloseGuard.get();
        mKeepaliveStatus = -1;
        mKeepaliveSyncLock = new Object();
        mContext = context;
        mConfig = ipsecconfig;
        mResourceId = 0;
    }

    IpSecTransform(Context context, IpSecConfig ipsecconfig, IpSecTransform ipsectransform)
    {
        this(context, ipsecconfig);
    }

    private IpSecTransform activate()
        throws IOException, IpSecManager.ResourceUnavailableException, IpSecManager.SpiUnavailableException
    {
        this;
        JVM INSTR monitorenter ;
        IIpSecService iipsecservice = getIpSecService();
        IpSecConfig ipsecconfig = mConfig;
        Object obj = JVM INSTR new #96  <Class Binder>;
        ((Binder) (obj)).Binder();
        obj = iipsecservice.createTransportModeTransform(ipsecconfig, ((android.os.IBinder) (obj)));
        checkResultStatusAndThrow(((IpSecTransformResponse) (obj)).status);
        mResourceId = ((IpSecTransformResponse) (obj)).resourceId;
        startKeepalive(mContext);
        obj = JVM INSTR new #121 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.d("IpSecTransform", ((StringBuilder) (obj)).append("Added Transform with Id ").append(mResourceId).toString());
        mCloseGuard.open("build");
        this;
        JVM INSTR monitorexit ;
        return this;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowAsRuntimeException();
        obj1;
        this;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    private void checkResultStatusAndThrow(int i)
        throws IOException, IpSecManager.ResourceUnavailableException, IpSecManager.SpiUnavailableException
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 55
    //                   1 56
    //                   2 66;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalStateException((new StringBuilder()).append("Failed to Create a Transform with status code ").append(i).toString());
_L2:
        return;
_L3:
        throw new IpSecManager.ResourceUnavailableException("Failed to allocate a new IpSecTransform");
_L4:
        Log.wtf("IpSecTransform", "Attempting to use an SPI that was somehow not reserved");
        if(true) goto _L1; else goto _L5
_L5:
    }

    private IIpSecService getIpSecService()
    {
        android.os.IBinder ibinder = ServiceManager.getService("ipsec");
        if(ibinder == null)
            throw (new RemoteException("Failed to connect to IpSecService")).rethrowAsRuntimeException();
        else
            return IIpSecService.Stub.asInterface(ibinder);
    }

    public void close()
    {
        Log.d("IpSecTransform", (new StringBuilder()).append("Removing Transform with Id ").append(mResourceId).toString());
        if(mResourceId == 0)
        {
            mCloseGuard.close();
            return;
        }
        getIpSecService().deleteTransportModeTransform(mResourceId);
        stopKeepalive();
        mResourceId = 0;
        mCloseGuard.close();
        return;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowAsRuntimeException();
        obj;
        mResourceId = 0;
        mCloseGuard.close();
        throw obj;
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        close();
    }

    IpSecConfig getConfig()
    {
        return mConfig;
    }

    int getResourceId()
    {
        return mResourceId;
    }

    void startKeepalive(Context context)
    {
        ConnectivityManager connectivitymanager;
        if(mConfig.getNattKeepaliveInterval() == 0)
            return;
        connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
        if(mKeepalive != null)
        {
            Log.wtf("IpSecTransform", "Keepalive already started for this IpSecTransform.");
            return;
        }
        context = ((Context) (mKeepaliveSyncLock));
        context;
        JVM INSTR monitorenter ;
        mKeepalive = connectivitymanager.startNattKeepalive(mConfig.getNetwork(), mConfig.getNattKeepaliveInterval(), mKeepaliveCallback, mConfig.getLocalAddress(), 4660, mConfig.getRemoteAddress());
        Exception exception;
        try
        {
            mKeepaliveSyncLock.wait(2000L);
        }
        catch(InterruptedException interruptedexception) { }
        context;
        JVM INSTR monitorexit ;
        if(mKeepaliveStatus != 0)
            throw new UnsupportedOperationException("Packet Keepalive cannot be started");
        else
            return;
        exception;
        throw exception;
    }

    void stopKeepalive()
    {
        if(mKeepalive == null)
            return;
        mKeepalive.stop();
        Object obj = mKeepaliveSyncLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mKeepaliveStatus;
        if(i != 0)
            break MISSING_BLOCK_LABEL_41;
        Exception exception;
        try
        {
            mKeepaliveSyncLock.wait(2000L);
        }
        catch(InterruptedException interruptedexception) { }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    public static final int DIRECTION_IN = 0;
    public static final int DIRECTION_OUT = 1;
    public static final int ENCAP_ESPINUDP = 2;
    public static final int ENCAP_ESPINUDP_NON_IKE = 1;
    public static final int ENCAP_NONE = 0;
    private static final int MODE_TRANSPORT = 1;
    private static final int MODE_TUNNEL = 0;
    private static final String TAG = "IpSecTransform";
    private final CloseGuard mCloseGuard;
    private final IpSecConfig mConfig;
    private final Context mContext;
    private ConnectivityManager.PacketKeepalive mKeepalive;
    private ConnectivityManager.PacketKeepaliveCallback mKeepaliveCallback = new ConnectivityManager.PacketKeepaliveCallback() {

        public void onError(int i)
        {
            Object obj = IpSecTransform._2D_get0(IpSecTransform.this);
            obj;
            JVM INSTR monitorenter ;
            IpSecTransform._2D_set0(IpSecTransform.this, i);
            IpSecTransform._2D_get0(IpSecTransform.this).notifyAll();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onStarted()
        {
            Object obj = IpSecTransform._2D_get0(IpSecTransform.this);
            obj;
            JVM INSTR monitorenter ;
            IpSecTransform._2D_set0(IpSecTransform.this, 0);
            IpSecTransform._2D_get0(IpSecTransform.this).notifyAll();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onStopped()
        {
            Object obj = IpSecTransform._2D_get0(IpSecTransform.this);
            obj;
            JVM INSTR monitorenter ;
            IpSecTransform._2D_set0(IpSecTransform.this, -1);
            IpSecTransform._2D_get0(IpSecTransform.this).notifyAll();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final IpSecTransform this$0;

            
            {
                this$0 = IpSecTransform.this;
                super();
            }
    }
;
    private int mKeepaliveStatus;
    private Object mKeepaliveSyncLock;
    private int mResourceId;
}
