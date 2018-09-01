// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;
import android.util.AndroidException;
import android.util.Log;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.*;

// Referenced classes of package android.net:
//            IIpSecService, IpSecTransform, Network, IpSecSpiResponse, 
//            IpSecUdpEncapResponse

public final class IpSecManager
{
    public static final class ResourceUnavailableException extends AndroidException
    {

        ResourceUnavailableException(String s)
        {
            super(s);
        }
    }

    public static final class SecurityParameterIndex
        implements AutoCloseable
    {

        public void close()
        {
            try
            {
                mService.releaseSecurityParameterIndex(mResourceId);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            mCloseGuard.close();
        }

        protected void finalize()
        {
            if(mCloseGuard != null)
                mCloseGuard.warnIfOpen();
            close();
        }

        int getResourceId()
        {
            return mResourceId;
        }

        public int getSpi()
        {
            return mSpi;
        }

        private final CloseGuard mCloseGuard;
        private final InetAddress mRemoteAddress;
        private int mResourceId;
        private final IIpSecService mService;
        private int mSpi;

        private SecurityParameterIndex(IIpSecService iipsecservice, int i, InetAddress inetaddress, int j)
            throws ResourceUnavailableException, SpiUnavailableException
        {
            mCloseGuard = CloseGuard.get();
            mSpi = 0;
            mService = iipsecservice;
            mRemoteAddress = inetaddress;
            try
            {
                iipsecservice = mService;
                inetaddress = inetaddress.getHostAddress();
                Binder binder = JVM INSTR new #51  <Class Binder>;
                binder.Binder();
                iipsecservice = iipsecservice.reserveSecurityParameterIndex(i, inetaddress, j, binder);
            }
            // Misplaced declaration of an exception variable
            catch(IIpSecService iipsecservice)
            {
                throw iipsecservice.rethrowFromSystemServer();
            }
            if(iipsecservice != null)
                break MISSING_BLOCK_LABEL_81;
            iipsecservice = JVM INSTR new #60  <Class NullPointerException>;
            iipsecservice.NullPointerException("Received null response from IpSecService");
            throw iipsecservice;
            i = ((IpSecSpiResponse) (iipsecservice)).status;
            i;
            JVM INSTR tableswitch 0 2: default 112
        //                       0 169
        //                       1 143
        //                       2 155;
               goto _L1 _L2 _L3 _L4
_L1:
            iipsecservice = JVM INSTR new #76  <Class RuntimeException>;
            inetaddress = JVM INSTR new #78  <Class StringBuilder>;
            inetaddress.StringBuilder();
            iipsecservice.RuntimeException(inetaddress.append("Unknown status returned by IpSecService: ").append(i).toString());
            throw iipsecservice;
_L3:
            iipsecservice = JVM INSTR new #22  <Class IpSecManager$ResourceUnavailableException>;
            iipsecservice.ResourceUnavailableException("No more SPIs may be allocated by this requester.");
            throw iipsecservice;
_L4:
            iipsecservice = JVM INSTR new #24  <Class IpSecManager$SpiUnavailableException>;
            iipsecservice.SpiUnavailableException("Requested SPI is unavailable", j);
            throw iipsecservice;
_L2:
            mSpi = ((IpSecSpiResponse) (iipsecservice)).spi;
            mResourceId = ((IpSecSpiResponse) (iipsecservice)).resourceId;
            if(mSpi == 0)
            {
                inetaddress = JVM INSTR new #76  <Class RuntimeException>;
                iipsecservice = JVM INSTR new #78  <Class StringBuilder>;
                iipsecservice.StringBuilder();
                inetaddress.RuntimeException(iipsecservice.append("Invalid SPI returned by IpSecService: ").append(i).toString());
                throw inetaddress;
            }
            if(mResourceId == 0)
            {
                inetaddress = JVM INSTR new #76  <Class RuntimeException>;
                iipsecservice = JVM INSTR new #78  <Class StringBuilder>;
                iipsecservice.StringBuilder();
                inetaddress.RuntimeException(iipsecservice.append("Invalid Resource ID returned by IpSecService: ").append(i).toString());
                throw inetaddress;
            }
            mCloseGuard.open("open");
            return;
        }

        SecurityParameterIndex(IIpSecService iipsecservice, int i, InetAddress inetaddress, int j, SecurityParameterIndex securityparameterindex)
        {
            this(iipsecservice, i, inetaddress, j);
        }
    }

    public static final class SpiUnavailableException extends AndroidException
    {

        public int getSpi()
        {
            return mSpi;
        }

        private final int mSpi;

        SpiUnavailableException(String s, int i)
        {
            super((new StringBuilder()).append(s).append("(spi: ").append(i).append(")").toString());
            mSpi = i;
        }
    }

    public static interface Status
    {

        public static final int OK = 0;
        public static final int RESOURCE_UNAVAILABLE = 1;
        public static final int SPI_UNAVAILABLE = 2;
    }

    public static final class UdpEncapsulationSocket
        implements AutoCloseable
    {

        public void close()
            throws IOException
        {
            try
            {
                mService.closeUdpEncapsulationSocket(mResourceId);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            try
            {
                mPfd.close();
            }
            catch(IOException ioexception)
            {
                Log.e("IpSecManager", (new StringBuilder()).append("Failed to close UDP Encapsulation Socket with Port= ").append(mPort).toString());
                throw ioexception;
            }
            mCloseGuard.close();
        }

        protected void finalize()
            throws Throwable
        {
            if(mCloseGuard != null)
                mCloseGuard.warnIfOpen();
            close();
        }

        public int getPort()
        {
            return mPort;
        }

        int getResourceId()
        {
            return mResourceId;
        }

        public FileDescriptor getSocket()
        {
            if(mPfd == null)
                return null;
            else
                return mPfd.getFileDescriptor();
        }

        private final CloseGuard mCloseGuard;
        private final ParcelFileDescriptor mPfd;
        private final int mPort;
        private final int mResourceId;
        private final IIpSecService mService;

        private UdpEncapsulationSocket(IIpSecService iipsecservice, int i)
            throws ResourceUnavailableException, IOException
        {
            mCloseGuard = CloseGuard.get();
            mService = iipsecservice;
            Object obj;
            iipsecservice = mService;
            obj = JVM INSTR new #41  <Class Binder>;
            ((Binder) (obj)).Binder();
            obj = iipsecservice.openUdpEncapsulationSocket(i, ((android.os.IBinder) (obj)));
            ((IpSecUdpEncapResponse) (obj)).status;
            JVM INSTR tableswitch 0 1: default 64
        //                       0 119
        //                       1 107;
               goto _L1 _L2 _L3
_L1:
            RuntimeException runtimeexception = JVM INSTR new #55  <Class RuntimeException>;
            iipsecservice = JVM INSTR new #57  <Class StringBuilder>;
            iipsecservice.StringBuilder();
            runtimeexception.RuntimeException(iipsecservice.append("Unknown status returned by IpSecService: ").append(((IpSecUdpEncapResponse) (obj)).status).toString());
            throw runtimeexception;
            iipsecservice;
            throw iipsecservice.rethrowFromSystemServer();
_L3:
            iipsecservice = JVM INSTR new #22  <Class IpSecManager$ResourceUnavailableException>;
            iipsecservice.ResourceUnavailableException("No more Sockets may be allocated by this requester.");
            throw iipsecservice;
_L2:
            mResourceId = ((IpSecUdpEncapResponse) (obj)).resourceId;
            mPort = ((IpSecUdpEncapResponse) (obj)).port;
            mPfd = ((IpSecUdpEncapResponse) (obj)).fileDescriptor;
            mCloseGuard.open("constructor");
            return;
        }

        UdpEncapsulationSocket(IIpSecService iipsecservice, int i, UdpEncapsulationSocket udpencapsulationsocket)
        {
            this(iipsecservice, i);
        }
    }


    public IpSecManager(IIpSecService iipsecservice)
    {
        mService = (IIpSecService)Preconditions.checkNotNull(iipsecservice, "missing service");
    }

    private void applyTransportModeTransform(ParcelFileDescriptor parcelfiledescriptor, IpSecTransform ipsectransform)
    {
        try
        {
            mService.applyTransportModeTransform(parcelfiledescriptor, ipsectransform.getResourceId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            throw parcelfiledescriptor.rethrowFromSystemServer();
        }
    }

    private void removeTransportModeTransform(ParcelFileDescriptor parcelfiledescriptor, IpSecTransform ipsectransform)
    {
        try
        {
            mService.removeTransportModeTransform(parcelfiledescriptor, ipsectransform.getResourceId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            throw parcelfiledescriptor.rethrowFromSystemServer();
        }
    }

    public void applyTransportModeTransform(FileDescriptor filedescriptor, IpSecTransform ipsectransform)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        filedescriptor = ParcelFileDescriptor.dup(filedescriptor);
        obj3 = filedescriptor;
        obj2 = filedescriptor;
        applyTransportModeTransform(((ParcelFileDescriptor) (filedescriptor)), ipsectransform);
        ipsectransform = obj1;
        if(filedescriptor == null)
            break MISSING_BLOCK_LABEL_42;
        filedescriptor.close();
        ipsectransform = obj1;
_L1:
        if(ipsectransform != null)
            throw ipsectransform;
        else
            return;
        ipsectransform;
          goto _L1
        filedescriptor;
        throw filedescriptor;
        ipsectransform;
_L4:
        obj2 = filedescriptor;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_72;
        ((ParcelFileDescriptor) (obj3)).close();
        obj2 = filedescriptor;
_L2:
        if(obj2 != null)
            throw obj2;
        else
            throw ipsectransform;
        obj3;
        if(filedescriptor == null)
        {
            obj2 = obj3;
        } else
        {
            obj2 = filedescriptor;
            if(filedescriptor != obj3)
            {
                filedescriptor.addSuppressed(((Throwable) (obj3)));
                obj2 = filedescriptor;
            }
        }
          goto _L2
        ipsectransform;
        obj3 = obj2;
        filedescriptor = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void applyTransportModeTransform(DatagramSocket datagramsocket, IpSecTransform ipsectransform)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        datagramsocket = ParcelFileDescriptor.fromDatagramSocket(datagramsocket);
        obj3 = datagramsocket;
        obj2 = datagramsocket;
        applyTransportModeTransform(((ParcelFileDescriptor) (datagramsocket)), ipsectransform);
        ipsectransform = obj1;
        if(datagramsocket == null)
            break MISSING_BLOCK_LABEL_42;
        datagramsocket.close();
        ipsectransform = obj1;
_L1:
        if(ipsectransform != null)
            throw ipsectransform;
        else
            return;
        ipsectransform;
          goto _L1
        datagramsocket;
        throw datagramsocket;
        ipsectransform;
_L4:
        obj2 = datagramsocket;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_72;
        ((ParcelFileDescriptor) (obj3)).close();
        obj2 = datagramsocket;
_L2:
        if(obj2 != null)
            throw obj2;
        else
            throw ipsectransform;
        obj3;
        if(datagramsocket == null)
        {
            obj2 = obj3;
        } else
        {
            obj2 = datagramsocket;
            if(datagramsocket != obj3)
            {
                datagramsocket.addSuppressed(((Throwable) (obj3)));
                obj2 = datagramsocket;
            }
        }
          goto _L2
        ipsectransform;
        obj3 = obj2;
        datagramsocket = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void applyTransportModeTransform(Socket socket, IpSecTransform ipsectransform)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        socket = ParcelFileDescriptor.fromSocket(socket);
        obj3 = socket;
        obj2 = socket;
        applyTransportModeTransform(((ParcelFileDescriptor) (socket)), ipsectransform);
        ipsectransform = obj1;
        if(socket == null)
            break MISSING_BLOCK_LABEL_42;
        socket.close();
        ipsectransform = obj1;
_L1:
        if(ipsectransform != null)
            throw ipsectransform;
        else
            return;
        ipsectransform;
          goto _L1
        socket;
        throw socket;
        ipsectransform;
_L4:
        obj2 = socket;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_72;
        ((ParcelFileDescriptor) (obj3)).close();
        obj2 = socket;
_L2:
        if(obj2 != null)
            throw obj2;
        else
            throw ipsectransform;
        obj3;
        if(socket == null)
        {
            obj2 = obj3;
        } else
        {
            obj2 = socket;
            if(socket != obj3)
            {
                socket.addSuppressed(((Throwable) (obj3)));
                obj2 = socket;
            }
        }
          goto _L2
        ipsectransform;
        obj3 = obj2;
        socket = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void applyTunnelModeTransform(Network network, IpSecTransform ipsectransform)
    {
    }

    public UdpEncapsulationSocket openUdpEncapsulationSocket()
        throws IOException, ResourceUnavailableException
    {
        return new UdpEncapsulationSocket(mService, 0, null);
    }

    public UdpEncapsulationSocket openUdpEncapsulationSocket(int i)
        throws IOException, ResourceUnavailableException
    {
        if(i == 0)
            throw new IllegalArgumentException("Specified port must be a valid port number!");
        else
            return new UdpEncapsulationSocket(mService, i, null);
    }

    public void removeTransportModeTransform(FileDescriptor filedescriptor, IpSecTransform ipsectransform)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        filedescriptor = ParcelFileDescriptor.dup(filedescriptor);
        obj3 = filedescriptor;
        obj2 = filedescriptor;
        removeTransportModeTransform(((ParcelFileDescriptor) (filedescriptor)), ipsectransform);
        ipsectransform = obj1;
        if(filedescriptor == null)
            break MISSING_BLOCK_LABEL_42;
        filedescriptor.close();
        ipsectransform = obj1;
_L1:
        if(ipsectransform != null)
            throw ipsectransform;
        else
            return;
        ipsectransform;
          goto _L1
        filedescriptor;
        throw filedescriptor;
        ipsectransform;
_L4:
        obj2 = filedescriptor;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_72;
        ((ParcelFileDescriptor) (obj3)).close();
        obj2 = filedescriptor;
_L2:
        if(obj2 != null)
            throw obj2;
        else
            throw ipsectransform;
        obj3;
        if(filedescriptor == null)
        {
            obj2 = obj3;
        } else
        {
            obj2 = filedescriptor;
            if(filedescriptor != obj3)
            {
                filedescriptor.addSuppressed(((Throwable) (obj3)));
                obj2 = filedescriptor;
            }
        }
          goto _L2
        ipsectransform;
        obj3 = obj2;
        filedescriptor = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void removeTransportModeTransform(DatagramSocket datagramsocket, IpSecTransform ipsectransform)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        datagramsocket = ParcelFileDescriptor.fromDatagramSocket(datagramsocket);
        obj3 = datagramsocket;
        obj2 = datagramsocket;
        removeTransportModeTransform(((ParcelFileDescriptor) (datagramsocket)), ipsectransform);
        ipsectransform = obj1;
        if(datagramsocket == null)
            break MISSING_BLOCK_LABEL_42;
        datagramsocket.close();
        ipsectransform = obj1;
_L1:
        if(ipsectransform != null)
            throw ipsectransform;
        else
            return;
        ipsectransform;
          goto _L1
        datagramsocket;
        throw datagramsocket;
        ipsectransform;
_L4:
        obj2 = datagramsocket;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_72;
        ((ParcelFileDescriptor) (obj3)).close();
        obj2 = datagramsocket;
_L2:
        if(obj2 != null)
            throw obj2;
        else
            throw ipsectransform;
        obj3;
        if(datagramsocket == null)
        {
            obj2 = obj3;
        } else
        {
            obj2 = datagramsocket;
            if(datagramsocket != obj3)
            {
                datagramsocket.addSuppressed(((Throwable) (obj3)));
                obj2 = datagramsocket;
            }
        }
          goto _L2
        ipsectransform;
        obj3 = obj2;
        datagramsocket = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void removeTransportModeTransform(Socket socket, IpSecTransform ipsectransform)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        socket = ParcelFileDescriptor.fromSocket(socket);
        obj3 = socket;
        obj2 = socket;
        removeTransportModeTransform(((ParcelFileDescriptor) (socket)), ipsectransform);
        ipsectransform = obj1;
        if(socket == null)
            break MISSING_BLOCK_LABEL_42;
        socket.close();
        ipsectransform = obj1;
_L1:
        if(ipsectransform != null)
            throw ipsectransform;
        else
            return;
        ipsectransform;
          goto _L1
        socket;
        throw socket;
        ipsectransform;
_L4:
        obj2 = socket;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_72;
        ((ParcelFileDescriptor) (obj3)).close();
        obj2 = socket;
_L2:
        if(obj2 != null)
            throw obj2;
        else
            throw ipsectransform;
        obj3;
        if(socket == null)
        {
            obj2 = obj3;
        } else
        {
            obj2 = socket;
            if(socket != obj3)
            {
                socket.addSuppressed(((Throwable) (obj3)));
                obj2 = socket;
            }
        }
          goto _L2
        ipsectransform;
        obj3 = obj2;
        socket = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void removeTunnelModeTransform(Network network, IpSecTransform ipsectransform)
    {
    }

    public SecurityParameterIndex reserveSecurityParameterIndex(int i, InetAddress inetaddress)
        throws ResourceUnavailableException
    {
        try
        {
            inetaddress = new SecurityParameterIndex(mService, i, inetaddress, 0, null);
        }
        // Misplaced declaration of an exception variable
        catch(InetAddress inetaddress)
        {
            throw new ResourceUnavailableException("No SPIs available");
        }
        return inetaddress;
    }

    public SecurityParameterIndex reserveSecurityParameterIndex(int i, InetAddress inetaddress, int j)
        throws SpiUnavailableException, ResourceUnavailableException
    {
        if(j == 0)
            throw new IllegalArgumentException("Requested SPI must be a valid (non-zero) SPI");
        else
            return new SecurityParameterIndex(mService, i, inetaddress, j, null);
    }

    public static final int INVALID_RESOURCE_ID = 0;
    public static final int INVALID_SECURITY_PARAMETER_INDEX = 0;
    private static final String TAG = "IpSecManager";
    private final IIpSecService mService;
}
