// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import java.io.*;

// Referenced classes of package android.net:
//            LocalSocketImpl, LocalSocketAddress, Credentials

public class LocalSocket
    implements Closeable
{

    public LocalSocket()
    {
        this(2);
    }

    public LocalSocket(int i)
    {
        this(new LocalSocketImpl(), i);
    }

    private LocalSocket(LocalSocketImpl localsocketimpl, int i)
    {
        impl = localsocketimpl;
        sockType = i;
        isConnected = false;
        isBound = false;
    }

    private static LocalSocket createConnectedLocalSocket(LocalSocketImpl localsocketimpl, int i)
    {
        localsocketimpl = new LocalSocket(localsocketimpl, i);
        localsocketimpl.isConnected = true;
        localsocketimpl.isBound = true;
        localsocketimpl.implCreated = true;
        return localsocketimpl;
    }

    public static LocalSocket createConnectedLocalSocket(FileDescriptor filedescriptor)
    {
        return createConnectedLocalSocket(new LocalSocketImpl(filedescriptor), 0);
    }

    static LocalSocket createLocalSocketForAccept(LocalSocketImpl localsocketimpl)
    {
        return createConnectedLocalSocket(localsocketimpl, 0);
    }

    private void implCreateIfNeeded()
        throws IOException
    {
        if(implCreated) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        boolean flag = implCreated;
        if(flag)
            break MISSING_BLOCK_LABEL_34;
        impl.create(sockType);
        implCreated = true;
        this;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        implCreated = true;
        throw exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void bind(LocalSocketAddress localsocketaddress)
        throws IOException
    {
        implCreateIfNeeded();
        this;
        JVM INSTR monitorenter ;
        if(isBound)
        {
            localsocketaddress = JVM INSTR new #61  <Class IOException>;
            localsocketaddress.IOException("already bound");
            throw localsocketaddress;
        }
        break MISSING_BLOCK_LABEL_30;
        localsocketaddress;
        this;
        JVM INSTR monitorexit ;
        throw localsocketaddress;
        localAddress = localsocketaddress;
        impl.bind(localAddress);
        isBound = true;
        this;
        JVM INSTR monitorexit ;
    }

    public void close()
        throws IOException
    {
        implCreateIfNeeded();
        impl.close();
    }

    public void connect(LocalSocketAddress localsocketaddress)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(isConnected)
        {
            localsocketaddress = JVM INSTR new #61  <Class IOException>;
            localsocketaddress.IOException("already connected");
            throw localsocketaddress;
        }
        break MISSING_BLOCK_LABEL_26;
        localsocketaddress;
        this;
        JVM INSTR monitorexit ;
        throw localsocketaddress;
        implCreateIfNeeded();
        impl.connect(localsocketaddress, 0);
        isConnected = true;
        isBound = true;
        this;
        JVM INSTR monitorexit ;
    }

    public void connect(LocalSocketAddress localsocketaddress, int i)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public FileDescriptor[] getAncillaryFileDescriptors()
        throws IOException
    {
        return impl.getAncillaryFileDescriptors();
    }

    public FileDescriptor getFileDescriptor()
    {
        return impl.getFileDescriptor();
    }

    public InputStream getInputStream()
        throws IOException
    {
        implCreateIfNeeded();
        return impl.getInputStream();
    }

    public LocalSocketAddress getLocalSocketAddress()
    {
        return localAddress;
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        implCreateIfNeeded();
        return impl.getOutputStream();
    }

    public Credentials getPeerCredentials()
        throws IOException
    {
        return impl.getPeerCredentials();
    }

    public int getReceiveBufferSize()
        throws IOException
    {
        return ((Integer)impl.getOption(4098)).intValue();
    }

    public LocalSocketAddress getRemoteSocketAddress()
    {
        throw new UnsupportedOperationException();
    }

    public int getSendBufferSize()
        throws IOException
    {
        return ((Integer)impl.getOption(4097)).intValue();
    }

    public int getSoTimeout()
        throws IOException
    {
        return ((Integer)impl.getOption(4102)).intValue();
    }

    public boolean isBound()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isBound;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isClosed()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isConnected()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isConnected;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isInputShutdown()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isOutputShutdown()
    {
        throw new UnsupportedOperationException();
    }

    public void setFileDescriptorsForSend(FileDescriptor afiledescriptor[])
    {
        impl.setFileDescriptorsForSend(afiledescriptor);
    }

    public void setReceiveBufferSize(int i)
        throws IOException
    {
        impl.setOption(4098, Integer.valueOf(i));
    }

    public void setSendBufferSize(int i)
        throws IOException
    {
        impl.setOption(4097, Integer.valueOf(i));
    }

    public void setSoTimeout(int i)
        throws IOException
    {
        impl.setOption(4102, Integer.valueOf(i));
    }

    public void shutdownInput()
        throws IOException
    {
        implCreateIfNeeded();
        impl.shutdownInput();
    }

    public void shutdownOutput()
        throws IOException
    {
        implCreateIfNeeded();
        impl.shutdownOutput();
    }

    public String toString()
    {
        return (new StringBuilder()).append(super.toString()).append(" impl:").append(impl).toString();
    }

    public static final int SOCKET_DGRAM = 1;
    public static final int SOCKET_SEQPACKET = 3;
    public static final int SOCKET_STREAM = 2;
    static final int SOCKET_UNKNOWN = 0;
    private final LocalSocketImpl impl;
    private volatile boolean implCreated;
    private boolean isBound;
    private boolean isConnected;
    private LocalSocketAddress localAddress;
    private final int sockType;
}
