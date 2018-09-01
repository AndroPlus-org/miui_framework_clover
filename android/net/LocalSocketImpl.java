// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.system.*;
import android.util.MutableInt;
import java.io.*;

// Referenced classes of package android.net:
//            LocalSocketAddress, Credentials

class LocalSocketImpl
{
    class SocketInputStream extends InputStream
    {

        public int available()
            throws IOException
        {
            FileDescriptor filedescriptor = LocalSocketImpl._2D_get0(LocalSocketImpl.this);
            if(filedescriptor == null)
                throw new IOException("socket closed");
            MutableInt mutableint = new MutableInt(0);
            try
            {
                Os.ioctlInt(filedescriptor, OsConstants.FIONREAD, mutableint);
            }
            catch(ErrnoException errnoexception)
            {
                throw errnoexception.rethrowAsIOException();
            }
            return mutableint.value;
        }

        public void close()
            throws IOException
        {
            LocalSocketImpl.this.close();
        }

        public int read()
            throws IOException
        {
            Object obj = LocalSocketImpl._2D_get1(LocalSocketImpl.this);
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = LocalSocketImpl._2D_get0(LocalSocketImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_39;
            obj1 = JVM INSTR new #21  <Class IOException>;
            ((IOException) (obj1)).IOException("socket closed");
            throw obj1;
            obj1;
            obj;
            JVM INSTR monitorexit ;
            throw obj1;
            int i = LocalSocketImpl._2D_wrap0(LocalSocketImpl.this, ((FileDescriptor) (obj1)));
            obj;
            JVM INSTR monitorexit ;
            return i;
        }

        public int read(byte abyte0[])
            throws IOException
        {
            return read(abyte0, 0, abyte0.length);
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            Object obj = LocalSocketImpl._2D_get1(LocalSocketImpl.this);
            obj;
            JVM INSTR monitorenter ;
            FileDescriptor filedescriptor = LocalSocketImpl._2D_get0(LocalSocketImpl.this);
            if(filedescriptor != null)
                break MISSING_BLOCK_LABEL_44;
            abyte0 = JVM INSTR new #21  <Class IOException>;
            abyte0.IOException("socket closed");
            throw abyte0;
            abyte0;
            obj;
            JVM INSTR monitorexit ;
            throw abyte0;
            do
            {
                abyte0 = JVM INSTR new #75  <Class ArrayIndexOutOfBoundsException>;
                abyte0.ArrayIndexOutOfBoundsException();
                throw abyte0;
            } while(i < 0 || j < 0 || i + j > abyte0.length);
            i = LocalSocketImpl._2D_wrap1(LocalSocketImpl.this, abyte0, i, j, filedescriptor);
            obj;
            JVM INSTR monitorexit ;
            return i;
        }

        final LocalSocketImpl this$0;

        SocketInputStream()
        {
            this$0 = LocalSocketImpl.this;
            super();
        }
    }

    class SocketOutputStream extends OutputStream
    {

        public void close()
            throws IOException
        {
            LocalSocketImpl.this.close();
        }

        public void flush()
            throws IOException
        {
            FileDescriptor filedescriptor;
            MutableInt mutableint;
            filedescriptor = LocalSocketImpl._2D_get0(LocalSocketImpl.this);
            if(filedescriptor == null)
                throw new IOException("socket closed");
            mutableint = new MutableInt(0);
_L3:
            try
            {
                Os.ioctlInt(filedescriptor, OsConstants.TIOCOUTQ, mutableint);
            }
            catch(ErrnoException errnoexception)
            {
                throw errnoexception.rethrowAsIOException();
            }
            if(mutableint.value > 0) goto _L2; else goto _L1
_L1:
            return;
_L2:
            Thread.sleep(10L);
              goto _L3
            InterruptedException interruptedexception;
            interruptedexception;
              goto _L1
        }

        public void write(int i)
            throws IOException
        {
            Object obj = LocalSocketImpl._2D_get2(LocalSocketImpl.this);
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = LocalSocketImpl._2D_get0(LocalSocketImpl.this);
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_39;
            obj1 = JVM INSTR new #20  <Class IOException>;
            ((IOException) (obj1)).IOException("socket closed");
            throw obj1;
            obj1;
            obj;
            JVM INSTR monitorexit ;
            throw obj1;
            LocalSocketImpl._2D_wrap2(LocalSocketImpl.this, i, ((FileDescriptor) (obj1)));
            obj;
            JVM INSTR monitorexit ;
        }

        public void write(byte abyte0[])
            throws IOException
        {
            write(abyte0, 0, abyte0.length);
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException
        {
            Object obj = LocalSocketImpl._2D_get2(LocalSocketImpl.this);
            obj;
            JVM INSTR monitorenter ;
            FileDescriptor filedescriptor = LocalSocketImpl._2D_get0(LocalSocketImpl.this);
            if(filedescriptor != null)
                break MISSING_BLOCK_LABEL_44;
            abyte0 = JVM INSTR new #20  <Class IOException>;
            abyte0.IOException("socket closed");
            throw abyte0;
            abyte0;
            obj;
            JVM INSTR monitorexit ;
            throw abyte0;
            do
            {
                abyte0 = JVM INSTR new #84  <Class ArrayIndexOutOfBoundsException>;
                abyte0.ArrayIndexOutOfBoundsException();
                throw abyte0;
            } while(i < 0 || j < 0 || i + j > abyte0.length);
            LocalSocketImpl._2D_wrap3(LocalSocketImpl.this, abyte0, i, j, filedescriptor);
            obj;
            JVM INSTR monitorexit ;
        }

        final LocalSocketImpl this$0;

        SocketOutputStream()
        {
            this$0 = LocalSocketImpl.this;
            super();
        }
    }


    static FileDescriptor _2D_get0(LocalSocketImpl localsocketimpl)
    {
        return localsocketimpl.fd;
    }

    static Object _2D_get1(LocalSocketImpl localsocketimpl)
    {
        return localsocketimpl.readMonitor;
    }

    static Object _2D_get2(LocalSocketImpl localsocketimpl)
    {
        return localsocketimpl.writeMonitor;
    }

    static int _2D_wrap0(LocalSocketImpl localsocketimpl, FileDescriptor filedescriptor)
    {
        return localsocketimpl.read_native(filedescriptor);
    }

    static int _2D_wrap1(LocalSocketImpl localsocketimpl, byte abyte0[], int i, int j, FileDescriptor filedescriptor)
    {
        return localsocketimpl.readba_native(abyte0, i, j, filedescriptor);
    }

    static void _2D_wrap2(LocalSocketImpl localsocketimpl, int i, FileDescriptor filedescriptor)
    {
        localsocketimpl.write_native(i, filedescriptor);
    }

    static void _2D_wrap3(LocalSocketImpl localsocketimpl, byte abyte0[], int i, int j, FileDescriptor filedescriptor)
    {
        localsocketimpl.writeba_native(abyte0, i, j, filedescriptor);
    }

    LocalSocketImpl()
    {
        readMonitor = new Object();
        writeMonitor = new Object();
    }

    LocalSocketImpl(FileDescriptor filedescriptor)
    {
        readMonitor = new Object();
        writeMonitor = new Object();
        fd = filedescriptor;
    }

    private native void bindLocal(FileDescriptor filedescriptor, String s, int i)
        throws IOException;

    private native void connectLocal(FileDescriptor filedescriptor, String s, int i)
        throws IOException;

    private native Credentials getPeerCredentials_native(FileDescriptor filedescriptor)
        throws IOException;

    private static int javaSoToOsOpt(int i)
    {
        switch(i)
        {
        default:
            throw new UnsupportedOperationException((new StringBuilder()).append("Unknown option: ").append(i).toString());

        case 4097: 
            return OsConstants.SO_SNDBUF;

        case 4098: 
            return OsConstants.SO_RCVBUF;

        case 4: // '\004'
            return OsConstants.SO_REUSEADDR;
        }
    }

    private native int read_native(FileDescriptor filedescriptor)
        throws IOException;

    private native int readba_native(byte abyte0[], int i, int j, FileDescriptor filedescriptor)
        throws IOException;

    private native void write_native(int i, FileDescriptor filedescriptor)
        throws IOException;

    private native void writeba_native(byte abyte0[], int i, int j, FileDescriptor filedescriptor)
        throws IOException;

    protected void accept(LocalSocketImpl localsocketimpl)
        throws IOException
    {
        if(fd == null)
            throw new IOException("socket not created");
        try
        {
            localsocketimpl.fd = Os.accept(fd, null);
            localsocketimpl.mFdCreatedInternally = true;
            return;
        }
        // Misplaced declaration of an exception variable
        catch(LocalSocketImpl localsocketimpl)
        {
            throw localsocketimpl.rethrowAsIOException();
        }
    }

    protected int available()
        throws IOException
    {
        return getInputStream().available();
    }

    public void bind(LocalSocketAddress localsocketaddress)
        throws IOException
    {
        if(fd == null)
        {
            throw new IOException("socket not created");
        } else
        {
            bindLocal(fd, localsocketaddress.getName(), localsocketaddress.getNamespace().getId());
            return;
        }
    }

    public void close()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(fd != null && mFdCreatedInternally)
            break MISSING_BLOCK_LABEL_24;
        fd = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Os.close(fd);
_L1:
        fd = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        ((ErrnoException) (obj)).rethrowAsIOException();
          goto _L1
        obj;
        throw obj;
    }

    protected void connect(LocalSocketAddress localsocketaddress, int i)
        throws IOException
    {
        if(fd == null)
        {
            throw new IOException("socket not created");
        } else
        {
            connectLocal(fd, localsocketaddress.getName(), localsocketaddress.getNamespace().getId());
            return;
        }
    }

    public void create(int i)
        throws IOException
    {
        if(fd != null)
            throw new IOException("LocalSocketImpl already has an fd");
        i;
        JVM INSTR tableswitch 1 3: default 44
    //                   1 54
    //                   2 76
    //                   3 83;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalStateException("unknown sockType");
_L2:
        i = OsConstants.SOCK_DGRAM;
_L5:
        fd = Os.socket(OsConstants.AF_UNIX, i, 0);
        mFdCreatedInternally = true;
_L6:
        return;
_L3:
        i = OsConstants.SOCK_STREAM;
          goto _L5
_L4:
        i = OsConstants.SOCK_SEQPACKET;
          goto _L5
        ErrnoException errnoexception;
        errnoexception;
        errnoexception.rethrowAsIOException();
          goto _L6
    }

    protected void finalize()
        throws IOException
    {
        close();
    }

    public FileDescriptor[] getAncillaryFileDescriptors()
        throws IOException
    {
        Object obj = readMonitor;
        obj;
        JVM INSTR monitorenter ;
        FileDescriptor afiledescriptor[];
        afiledescriptor = inboundFileDescriptors;
        inboundFileDescriptors = null;
        obj;
        JVM INSTR monitorexit ;
        return afiledescriptor;
        Exception exception;
        exception;
        throw exception;
    }

    protected FileDescriptor getFileDescriptor()
    {
        return fd;
    }

    protected InputStream getInputStream()
        throws IOException
    {
        if(fd == null)
            throw new IOException("socket not created");
        this;
        JVM INSTR monitorenter ;
        SocketInputStream socketinputstream1;
        if(fis == null)
        {
            SocketInputStream socketinputstream = JVM INSTR new #6   <Class LocalSocketImpl$SocketInputStream>;
            socketinputstream.this. SocketInputStream();
            fis = socketinputstream;
        }
        socketinputstream1 = fis;
        this;
        JVM INSTR monitorexit ;
        return socketinputstream1;
        Exception exception;
        exception;
        throw exception;
    }

    public Object getOption(int i)
        throws IOException
    {
        if(fd == null)
            throw new IOException("socket not created");
        i;
        JVM INSTR lookupswitch 6: default 76
    //                   1: 199
    //                   4: 136
    //                   128: 159
    //                   4097: 136
    //                   4098: 136
    //                   4102: 113;
           goto _L1 _L2 _L3 _L4 _L3 _L3 _L5
_L1:
        try
        {
            IOException ioexception = JVM INSTR new #69  <Class IOException>;
            StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            ioexception.IOException(stringbuilder.append("Unknown option: ").append(i).toString());
            throw ioexception;
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
_L5:
        Object obj = Integer.valueOf((int)Os.getsockoptTimeval(fd, OsConstants.SOL_SOCKET, OsConstants.SO_SNDTIMEO).toMillis());
_L7:
        return obj;
_L3:
        i = javaSoToOsOpt(i);
        obj = Integer.valueOf(Os.getsockoptInt(fd, OsConstants.SOL_SOCKET, i));
        continue; /* Loop/switch isn't completed */
_L4:
        obj = Os.getsockoptLinger(fd, OsConstants.SOL_SOCKET, OsConstants.SO_LINGER);
        if(!((StructLinger) (obj)).isOn())
        {
            obj = Integer.valueOf(-1);
            continue; /* Loop/switch isn't completed */
        }
        obj = Integer.valueOf(((StructLinger) (obj)).l_linger);
        continue; /* Loop/switch isn't completed */
_L2:
        i = Os.getsockoptInt(fd, OsConstants.IPPROTO_TCP, OsConstants.TCP_NODELAY);
        obj = Integer.valueOf(i);
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected OutputStream getOutputStream()
        throws IOException
    {
        if(fd == null)
            throw new IOException("socket not created");
        this;
        JVM INSTR monitorenter ;
        SocketOutputStream socketoutputstream1;
        if(fos == null)
        {
            SocketOutputStream socketoutputstream = JVM INSTR new #9   <Class LocalSocketImpl$SocketOutputStream>;
            socketoutputstream.this. SocketOutputStream();
            fos = socketoutputstream;
        }
        socketoutputstream1 = fos;
        this;
        JVM INSTR monitorexit ;
        return socketoutputstream1;
        Exception exception;
        exception;
        throw exception;
    }

    public Credentials getPeerCredentials()
        throws IOException
    {
        return getPeerCredentials_native(fd);
    }

    public LocalSocketAddress getSockAddress()
        throws IOException
    {
        return null;
    }

    protected void listen(int i)
        throws IOException
    {
        if(fd == null)
            throw new IOException("socket not created");
        try
        {
            Os.listen(fd, i);
            return;
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
    }

    protected void sendUrgentData(int i)
        throws IOException
    {
        throw new RuntimeException("not impled");
    }

    public void setFileDescriptorsForSend(FileDescriptor afiledescriptor[])
    {
        Object obj = writeMonitor;
        obj;
        JVM INSTR monitorenter ;
        outboundFileDescriptors = afiledescriptor;
        obj;
        JVM INSTR monitorexit ;
        return;
        afiledescriptor;
        throw afiledescriptor;
    }

    public void setOption(int i, Object obj)
        throws IOException
    {
        byte byte0;
        int j;
        if(fd == null)
            throw new IOException("socket not created");
        byte0 = -1;
        j = 0;
        StringBuilder stringbuilder;
        if(obj instanceof Integer)
            j = ((Integer)obj).intValue();
        else
        if(obj instanceof Boolean)
        {
            if(((Boolean)obj).booleanValue())
                byte0 = 1;
            else
                byte0 = 0;
        } else
        {
            throw new IOException((new StringBuilder()).append("bad value: ").append(obj).toString());
        }
        i;
        JVM INSTR lookupswitch 6: default 96
    //                   1: 276
    //                   4: 255
    //                   128: 191
    //                   4097: 255
    //                   4098: 255
    //                   4102: 217;
           goto _L1 _L2 _L3 _L4 _L3 _L3 _L5
_L2:
        break MISSING_BLOCK_LABEL_276;
_L1:
        try
        {
            obj = JVM INSTR new #69  <Class IOException>;
            stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            ((IOException) (obj)).IOException(stringbuilder.append("Unknown option: ").append(i).toString());
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw ((ErrnoException) (obj)).rethrowAsIOException();
        }
_L4:
        obj = JVM INSTR new #238 <Class StructLinger>;
        ((StructLinger) (obj)).StructLinger(byte0, j);
        Os.setsockoptLinger(fd, OsConstants.SOL_SOCKET, OsConstants.SO_LINGER, ((StructLinger) (obj)));
_L6:
        return;
_L5:
        obj = StructTimeval.fromMillis(j);
        Os.setsockoptTimeval(fd, OsConstants.SOL_SOCKET, OsConstants.SO_RCVTIMEO, ((StructTimeval) (obj)));
        Os.setsockoptTimeval(fd, OsConstants.SOL_SOCKET, OsConstants.SO_SNDTIMEO, ((StructTimeval) (obj)));
          goto _L6
_L3:
        i = javaSoToOsOpt(i);
        Os.setsockoptInt(fd, OsConstants.SOL_SOCKET, i, j);
          goto _L6
        Os.setsockoptInt(fd, OsConstants.IPPROTO_TCP, OsConstants.TCP_NODELAY, j);
          goto _L6
    }

    protected void shutdownInput()
        throws IOException
    {
        if(fd == null)
            throw new IOException("socket not created");
        try
        {
            Os.shutdown(fd, OsConstants.SHUT_RD);
            return;
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
    }

    protected void shutdownOutput()
        throws IOException
    {
        if(fd == null)
            throw new IOException("socket not created");
        try
        {
            Os.shutdown(fd, OsConstants.SHUT_WR);
            return;
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
    }

    protected boolean supportsUrgentData()
    {
        return false;
    }

    public String toString()
    {
        return (new StringBuilder()).append(super.toString()).append(" fd:").append(fd).toString();
    }

    private FileDescriptor fd;
    private SocketInputStream fis;
    private SocketOutputStream fos;
    FileDescriptor inboundFileDescriptors[];
    private boolean mFdCreatedInternally;
    FileDescriptor outboundFileDescriptors[];
    private Object readMonitor;
    private Object writeMonitor;
}
