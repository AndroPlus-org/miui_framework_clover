// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.net.LocalSocket;
import android.os.*;
import android.util.Log;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

// Referenced classes of package android.bluetooth:
//            BluetoothAdapter, BluetoothInputStream, BluetoothOutputStream, BluetoothDevice, 
//            IBluetooth

public final class BluetoothSocket
    implements Closeable
{
    private static final class SocketState extends Enum
    {

        public static SocketState valueOf(String s)
        {
            return (SocketState)Enum.valueOf(android/bluetooth/BluetoothSocket$SocketState, s);
        }

        public static SocketState[] values()
        {
            return $VALUES;
        }

        private static final SocketState $VALUES[];
        public static final SocketState CLOSED;
        public static final SocketState CONNECTED;
        public static final SocketState INIT;
        public static final SocketState LISTENING;

        static 
        {
            INIT = new SocketState("INIT", 0);
            CONNECTED = new SocketState("CONNECTED", 1);
            LISTENING = new SocketState("LISTENING", 2);
            CLOSED = new SocketState("CLOSED", 3);
            $VALUES = (new SocketState[] {
                INIT, CONNECTED, LISTENING, CLOSED
            });
        }

        private SocketState(String s, int i)
        {
            super(s, i);
        }
    }


    BluetoothSocket(int i, int j, boolean flag, boolean flag1, BluetoothDevice bluetoothdevice, int k, ParcelUuid parceluuid)
        throws IOException
    {
        this(i, j, flag, flag1, bluetoothdevice, k, parceluuid, false, false);
    }

    BluetoothSocket(int i, int j, boolean flag, boolean flag1, BluetoothDevice bluetoothdevice, int k, ParcelUuid parceluuid, 
            boolean flag2, boolean flag3)
        throws IOException
    {
        mExcludeSdp = false;
        mAuthMitm = false;
        mMin16DigitPin = false;
        mL2capBuffer = null;
        mMaxTxPacketSize = 0;
        mMaxRxPacketSize = 0;
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("Creating new BluetoothSocket of type: ").append(i).toString());
        if(i == 1 && parceluuid == null && j == -1 && k != -2 && (k < 1 || k > 30))
            throw new IOException((new StringBuilder()).append("Invalid RFCOMM channel: ").append(k).toString());
        if(parceluuid != null)
            mUuid = parceluuid;
        else
            mUuid = new ParcelUuid(new UUID(0L, 0L));
        mType = i;
        mAuth = flag;
        mAuthMitm = flag2;
        mMin16DigitPin = flag3;
        mEncrypt = flag1;
        mDevice = bluetoothdevice;
        mPort = k;
        mFd = j;
        mSocketState = SocketState.INIT;
        if(bluetoothdevice == null)
            mAddress = BluetoothAdapter.getDefaultAdapter().getAddress();
        else
            mAddress = bluetoothdevice.getAddress();
        mInputStream = new BluetoothInputStream(this);
        mOutputStream = new BluetoothOutputStream(this);
    }

    private BluetoothSocket(int i, int j, boolean flag, boolean flag1, String s, int k)
        throws IOException
    {
        this(i, j, flag, flag1, new BluetoothDevice(s), k, null, false, false);
    }

    private BluetoothSocket(BluetoothSocket bluetoothsocket)
    {
        mExcludeSdp = false;
        mAuthMitm = false;
        mMin16DigitPin = false;
        mL2capBuffer = null;
        mMaxTxPacketSize = 0;
        mMaxRxPacketSize = 0;
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("Creating new Private BluetoothSocket of type: ").append(bluetoothsocket.mType).toString());
        mUuid = bluetoothsocket.mUuid;
        mType = bluetoothsocket.mType;
        mAuth = bluetoothsocket.mAuth;
        mEncrypt = bluetoothsocket.mEncrypt;
        mPort = bluetoothsocket.mPort;
        mInputStream = new BluetoothInputStream(this);
        mOutputStream = new BluetoothOutputStream(this);
        mMaxRxPacketSize = bluetoothsocket.mMaxRxPacketSize;
        mMaxTxPacketSize = bluetoothsocket.mMaxTxPacketSize;
        mServiceName = bluetoothsocket.mServiceName;
        mExcludeSdp = bluetoothsocket.mExcludeSdp;
        mAuthMitm = bluetoothsocket.mAuthMitm;
        mMin16DigitPin = bluetoothsocket.mMin16DigitPin;
    }

    private BluetoothSocket acceptSocket(String s)
        throws IOException
    {
        BluetoothSocket bluetoothsocket = new BluetoothSocket(this);
        bluetoothsocket.mSocketState = SocketState.CONNECTED;
        java.io.FileDescriptor afiledescriptor[] = mSocket.getAncillaryFileDescriptors();
        if(DBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("socket fd passed by stack fds: ").append(Arrays.toString(afiledescriptor)).toString());
        if(afiledescriptor == null || afiledescriptor.length != 1)
        {
            Log.e("BluetoothSocket", (new StringBuilder()).append("socket fd passed from stack failed, fds: ").append(Arrays.toString(afiledescriptor)).toString());
            bluetoothsocket.close();
            throw new IOException("bt socket acept failed");
        } else
        {
            bluetoothsocket.mPfd = new ParcelFileDescriptor(afiledescriptor[0]);
            bluetoothsocket.mSocket = LocalSocket.createConnectedLocalSocket(afiledescriptor[0]);
            bluetoothsocket.mSocketIS = bluetoothsocket.mSocket.getInputStream();
            bluetoothsocket.mSocketOS = bluetoothsocket.mSocket.getOutputStream();
            bluetoothsocket.mAddress = s;
            bluetoothsocket.mDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(s);
            bluetoothsocket.mPort = mPort;
            return bluetoothsocket;
        }
    }

    private String convertAddr(byte abyte0[])
    {
        return String.format(Locale.US, "%02X:%02X:%02X:%02X:%02X:%02X", new Object[] {
            Byte.valueOf(abyte0[0]), Byte.valueOf(abyte0[1]), Byte.valueOf(abyte0[2]), Byte.valueOf(abyte0[3]), Byte.valueOf(abyte0[4]), Byte.valueOf(abyte0[5])
        });
    }

    private void createL2capRxBuffer()
    {
        if(mType == 3)
        {
            if(VDBG)
                Log.v("BluetoothSocket", (new StringBuilder()).append("  Creating mL2capBuffer: mMaxPacketSize: ").append(mMaxRxPacketSize).toString());
            mL2capBuffer = ByteBuffer.wrap(new byte[mMaxRxPacketSize]);
            if(VDBG)
                Log.v("BluetoothSocket", (new StringBuilder()).append("mL2capBuffer.remaining()").append(mL2capBuffer.remaining()).toString());
            mL2capBuffer.limit(0);
            if(VDBG)
                Log.v("BluetoothSocket", (new StringBuilder()).append("mL2capBuffer.remaining() after limit(0):").append(mL2capBuffer.remaining()).toString());
        }
    }

    private int fillL2capRxBuffer()
        throws IOException
    {
        mL2capBuffer.rewind();
        int i = mSocketIS.read(mL2capBuffer.array());
        if(i == -1)
        {
            mL2capBuffer.limit(0);
            return -1;
        } else
        {
            mL2capBuffer.limit(i);
            return i;
        }
    }

    private int getSecurityFlags()
    {
        int i = 0;
        if(mAuth)
            i = 2;
        int j = i;
        if(mEncrypt)
            j = i | 1;
        i = j;
        if(mExcludeSdp)
            i = j | 4;
        j = i;
        if(mAuthMitm)
            j = i | 8;
        i = j;
        if(mMin16DigitPin)
            i = j | 0x10;
        return i;
    }

    private int readAll(InputStream inputstream, byte abyte0[])
        throws IOException
    {
        int i = abyte0.length;
        do
        {
            if(i <= 0)
                break;
            int j = inputstream.read(abyte0, abyte0.length - i, i);
            if(j <= 0)
                throw new IOException((new StringBuilder()).append("read failed, socket might closed or timeout, read ret: ").append(j).toString());
            j = i - j;
            i = j;
            if(j != 0)
            {
                Log.w("BluetoothSocket", (new StringBuilder()).append("readAll() looping, read partial size: ").append(abyte0.length - j).append(", expect size: ").append(abyte0.length).toString());
                i = j;
            }
        } while(true);
        return abyte0.length;
    }

    private int readInt(InputStream inputstream)
        throws IOException
    {
        byte abyte0[] = new byte[4];
        int i = readAll(inputstream, abyte0);
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("inputStream.read ret: ").append(i).toString());
        inputstream = ByteBuffer.wrap(abyte0);
        inputstream.order(ByteOrder.nativeOrder());
        return inputstream.getInt();
    }

    private String waitSocketSignal(InputStream inputstream)
        throws IOException
    {
        byte abyte0[] = new byte[SOCK_SIGNAL_SIZE];
        int i = readAll(inputstream, abyte0);
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("waitSocketSignal read ").append(SOCK_SIGNAL_SIZE).append(" bytes signal ret: ").append(i).toString());
        inputstream = ByteBuffer.wrap(abyte0);
        inputstream.order(ByteOrder.nativeOrder());
        short word0 = inputstream.getShort();
        if(word0 != SOCK_SIGNAL_SIZE)
            throw new IOException((new StringBuilder()).append("Connection failure, wrong signal size: ").append(word0).toString());
        abyte0 = new byte[6];
        inputstream.get(abyte0);
        i = inputstream.getInt();
        int j = inputstream.getInt();
        mMaxTxPacketSize = inputstream.getShort() & 0xffff;
        mMaxRxPacketSize = inputstream.getShort() & 0xffff;
        inputstream = convertAddr(abyte0);
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("waitSocketSignal: sig size: ").append(word0).append(", remote addr: ").append(inputstream).append(", channel: ").append(i).append(", status: ").append(j).append(" MaxRxPktSize: ").append(mMaxRxPacketSize).append(" MaxTxPktSize: ").append(mMaxTxPacketSize).toString());
        if(j != 0)
            throw new IOException((new StringBuilder()).append("Connection failure, status: ").append(j).toString());
        else
            return inputstream;
    }

    BluetoothSocket accept(int i)
        throws IOException
    {
        Object obj;
        if(mSocketState != SocketState.LISTENING)
            throw new IOException("bt socket is not in listen state");
        if(i > 0)
        {
            Log.d("BluetoothSocket", (new StringBuilder()).append("accept() set timeout (ms):").append(i).toString());
            mSocket.setSoTimeout(i);
        }
        obj = waitSocketSignal(mSocketIS);
        if(i > 0)
            mSocket.setSoTimeout(0);
        this;
        JVM INSTR monitorenter ;
        if(mSocketState != SocketState.LISTENING)
        {
            obj = JVM INSTR new #95  <Class IOException>;
            ((IOException) (obj)).IOException("bt socket is not in listen state");
            throw obj;
        }
        break MISSING_BLOCK_LABEL_110;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        obj = acceptSocket(((String) (obj)));
        this;
        JVM INSTR monitorexit ;
        return ((BluetoothSocket) (obj));
    }

    int available()
        throws IOException
    {
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("available: ").append(mSocketIS).toString());
        return mSocketIS.available();
    }

    int bindListen()
    {
        if(mSocketState == SocketState.CLOSED)
            return 77;
        Object obj = BluetoothAdapter.getDefaultAdapter().getBluetoothService(null);
        if(obj == null)
        {
            Log.e("BluetoothSocket", "bindListen fail, reason: bluetooth is off");
            return -1;
        }
        SocketState socketstate;
        try
        {
            mPfd = ((IBluetooth) (obj)).createSocketChannel(mType, mServiceName, mUuid, mPort, getSecurityFlags());
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothSocket", Log.getStackTraceString(new Throwable()));
            return -1;
        }
        this;
        JVM INSTR monitorenter ;
        if(DBG)
        {
            obj = JVM INSTR new #115 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.d("BluetoothSocket", ((StringBuilder) (obj)).append("bindListen(), SocketState: ").append(mSocketState).append(", mPfd: ").append(mPfd).toString());
        }
        obj = mSocketState;
        socketstate = SocketState.INIT;
        if(obj == socketstate)
            break MISSING_BLOCK_LABEL_156;
        this;
        JVM INSTR monitorexit ;
        return 77;
        Object obj1 = mPfd;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_169;
        this;
        JVM INSTR monitorexit ;
        return -1;
        obj1 = mPfd.getFileDescriptor();
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_194;
        Log.e("BluetoothSocket", "bindListen(), null file descriptor");
        this;
        JVM INSTR monitorexit ;
        return -1;
        if(DBG)
            Log.d("BluetoothSocket", "bindListen(), Create LocalSocket");
        mSocket = LocalSocket.createConnectedLocalSocket(((java.io.FileDescriptor) (obj1)));
        if(DBG)
            Log.d("BluetoothSocket", "bindListen(), new LocalSocket.getInputStream()");
        mSocketIS = mSocket.getInputStream();
        mSocketOS = mSocket.getOutputStream();
        this;
        JVM INSTR monitorexit ;
        if(DBG)
        {
            StringBuilder stringbuilder = JVM INSTR new #115 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("BluetoothSocket", stringbuilder.append("bindListen(), readInt mSocketIS: ").append(mSocketIS).toString());
        }
        int i = readInt(mSocketIS);
        this;
        JVM INSTR monitorenter ;
        if(mSocketState == SocketState.INIT)
            mSocketState = SocketState.LISTENING;
        this;
        JVM INSTR monitorexit ;
        if(DBG)
        {
            StringBuilder stringbuilder1 = JVM INSTR new #115 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.d("BluetoothSocket", stringbuilder1.append("channel: ").append(i).toString());
        }
        if(mPort <= -1)
            mPort = i;
        return 0;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        IOException ioexception1;
        ioexception1;
        if(mPfd != null)
        {
            Exception exception1;
            try
            {
                mPfd.close();
            }
            catch(IOException ioexception)
            {
                Log.e("BluetoothSocket", (new StringBuilder()).append("bindListen, close mPfd: ").append(ioexception).toString());
            }
            mPfd = null;
        }
        Log.e("BluetoothSocket", (new StringBuilder()).append("bindListen, fail to get port number, exception: ").append(ioexception1).toString());
        return -1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public void close()
        throws IOException
    {
        Log.d("BluetoothSocket", (new StringBuilder()).append("close() this: ").append(this).append(", channel: ").append(mPort).append(", mSocketIS: ").append(mSocketIS).append(", mSocketOS: ").append(mSocketOS).append("mSocket: ").append(mSocket).append(", mSocketState: ").append(mSocketState).toString());
        if(mSocketState == SocketState.CLOSED)
            return;
        this;
        JVM INSTR monitorenter ;
        SocketState socketstate;
        SocketState socketstate1;
        socketstate = mSocketState;
        socketstate1 = SocketState.CLOSED;
        if(socketstate != socketstate1)
            break MISSING_BLOCK_LABEL_121;
        this;
        JVM INSTR monitorexit ;
        return;
        mSocketState = SocketState.CLOSED;
        if(mSocket != null)
        {
            if(DBG)
            {
                StringBuilder stringbuilder = JVM INSTR new #115 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("BluetoothSocket", stringbuilder.append("Closing mSocket: ").append(mSocket).toString());
            }
            mSocket.shutdownInput();
            mSocket.shutdownOutput();
            mSocket.close();
            mSocket = null;
        }
        if(mPfd != null)
        {
            mPfd.close();
            mPfd = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void connect()
        throws IOException
    {
        if(mDevice == null)
            throw new IOException("Connect is called on null device");
        try
        {
            if(mSocketState == SocketState.CLOSED)
            {
                IOException ioexception = JVM INSTR new #95  <Class IOException>;
                ioexception.IOException("socket closed");
                throw ioexception;
            }
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothSocket", Log.getStackTraceString(new Throwable()));
            throw new IOException((new StringBuilder()).append("unable to send RPC: ").append(remoteexception.getMessage()).toString());
        }
        Object obj = BluetoothAdapter.getDefaultAdapter().getBluetoothService(null);
        if(obj != null)
            break MISSING_BLOCK_LABEL_114;
        obj = JVM INSTR new #95  <Class IOException>;
        ((IOException) (obj)).IOException("Bluetooth is off");
        throw obj;
        mPfd = ((IBluetooth) (obj)).connectSocket(mDevice, mType, mUuid, mPort, getSecurityFlags());
        this;
        JVM INSTR monitorenter ;
        if(DBG)
        {
            StringBuilder stringbuilder = JVM INSTR new #115 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("BluetoothSocket", stringbuilder.append("connect(), SocketState: ").append(mSocketState).append(", mPfd: ").append(mPfd).toString());
        }
        if(mSocketState == SocketState.CLOSED)
        {
            IOException ioexception1 = JVM INSTR new #95  <Class IOException>;
            ioexception1.IOException("socket closed");
            throw ioexception1;
        }
        break MISSING_BLOCK_LABEL_224;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(mPfd == null)
        {
            IOException ioexception2 = JVM INSTR new #95  <Class IOException>;
            ioexception2.IOException("bt socket connect failed");
            throw ioexception2;
        }
        mSocket = LocalSocket.createConnectedLocalSocket(mPfd.getFileDescriptor());
        mSocketIS = mSocket.getInputStream();
        mSocketOS = mSocket.getOutputStream();
        this;
        JVM INSTR monitorexit ;
        int i = readInt(mSocketIS);
        if(i > 0)
            break MISSING_BLOCK_LABEL_308;
        IOException ioexception3 = JVM INSTR new #95  <Class IOException>;
        ioexception3.IOException("bt socket connect failed");
        throw ioexception3;
        mPort = i;
        waitSocketSignal(mSocketIS);
        this;
        JVM INSTR monitorenter ;
        if(mSocketState == SocketState.CLOSED)
        {
            IOException ioexception4 = JVM INSTR new #95  <Class IOException>;
            ioexception4.IOException("bt socket closed");
            throw ioexception4;
        }
        break MISSING_BLOCK_LABEL_352;
        ioexception4;
        this;
        JVM INSTR monitorexit ;
        throw ioexception4;
        mSocketState = SocketState.CONNECTED;
        this;
        JVM INSTR monitorexit ;
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    void flush()
        throws IOException
    {
        if(mSocketOS == null)
            throw new IOException("flush is called on null OutputStream");
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("flush: ").append(mSocketOS).toString());
        mSocketOS.flush();
    }

    public int getConnectionType()
    {
        return mType;
    }

    public InputStream getInputStream()
        throws IOException
    {
        return mInputStream;
    }

    public int getMaxReceivePacketSize()
    {
        return mMaxRxPacketSize;
    }

    public int getMaxTransmitPacketSize()
    {
        return mMaxTxPacketSize;
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return mOutputStream;
    }

    int getPort()
    {
        return mPort;
    }

    public BluetoothDevice getRemoteDevice()
    {
        return mDevice;
    }

    public int getSocketOpt(int i, byte abyte0[])
        throws IOException
    {
        if(mSocketState == SocketState.CLOSED)
            throw new IOException("socket closed");
        IBluetooth ibluetooth = BluetoothAdapter.getDefaultAdapter().getBluetoothService(null);
        if(ibluetooth == null)
        {
            Log.e("BluetoothSocket", "getSocketOpt fail, reason: bluetooth is off");
            return -1;
        }
        try
        {
            if(VDBG)
            {
                StringBuilder stringbuilder = JVM INSTR new #115 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("BluetoothSocket", stringbuilder.append("getSocketOpt(), mType: ").append(mType).append(" mPort: ").append(mPort).toString());
            }
            i = ibluetooth.getSocketOpt(mType, mPort, i, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.e("BluetoothSocket", Log.getStackTraceString(new Throwable()));
            return -1;
        }
        return i;
    }

    public boolean isConnected()
    {
        boolean flag;
        if(mSocketState == SocketState.CONNECTED)
            flag = true;
        else
            flag = false;
        return flag;
    }

    int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("read in:  ").append(mSocketIS).append(" len: ").append(j).toString());
        if(mType == 3)
        {
            int k = j;
            if(VDBG)
                Log.v("BluetoothSocket", (new StringBuilder()).append("l2cap: read(): offset: ").append(i).append(" length:").append(j).append("mL2capBuffer= ").append(mL2capBuffer).toString());
            if(mL2capBuffer == null)
                createL2capRxBuffer();
            if(mL2capBuffer.remaining() == 0)
            {
                if(VDBG)
                    Log.v("BluetoothSocket", "l2cap buffer empty, refilling...");
                if(fillL2capRxBuffer() == -1)
                    return -1;
            }
            if(j > mL2capBuffer.remaining())
                k = mL2capBuffer.remaining();
            if(VDBG)
                Log.v("BluetoothSocket", (new StringBuilder()).append("get(): offset: ").append(i).append(" bytesToRead: ").append(k).toString());
            mL2capBuffer.get(abyte0, i, k);
            i = k;
        } else
        {
            if(VDBG)
                Log.v("BluetoothSocket", (new StringBuilder()).append("default: read(): offset: ").append(i).append(" length:").append(j).toString());
            i = mSocketIS.read(abyte0, i, j);
        }
        if(i < 0)
            throw new IOException((new StringBuilder()).append("bt socket closed, read return: ").append(i).toString());
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("read out:  ").append(mSocketIS).append(" ret: ").append(i).toString());
        return i;
    }

    void removeChannel()
    {
    }

    public void setExcludeSdp(boolean flag)
    {
        mExcludeSdp = flag;
    }

    void setServiceName(String s)
    {
        mServiceName = s;
    }

    public int setSocketOpt(int i, byte abyte0[], int j)
        throws IOException
    {
        if(mSocketState == SocketState.CLOSED)
            throw new IOException("socket closed");
        IBluetooth ibluetooth = BluetoothAdapter.getDefaultAdapter().getBluetoothService(null);
        if(ibluetooth == null)
        {
            Log.e("BluetoothSocket", "setSocketOpt fail, reason: bluetooth is off");
            return -1;
        }
        try
        {
            if(VDBG)
            {
                StringBuilder stringbuilder = JVM INSTR new #115 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("BluetoothSocket", stringbuilder.append("setSocketOpt(), mType: ").append(mType).append(" mPort: ").append(mPort).toString());
            }
            i = ibluetooth.setSocketOpt(mType, mPort, i, abyte0, j);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.e("BluetoothSocket", Log.getStackTraceString(new Throwable()));
            return -1;
        }
        return i;
    }

    int write(byte abyte0[], int i, int j)
        throws IOException
    {
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("write: ").append(mSocketOS).append(" length: ").append(j).toString());
        if(mType == 3)
        {
            if(j <= mMaxTxPacketSize)
            {
                mSocketOS.write(abyte0, i, j);
            } else
            {
                if(DBG)
                    Log.w("BluetoothSocket", (new StringBuilder()).append("WARNING: Write buffer larger than L2CAP packet size!\nPacket will be divided into SDU packets of size ").append(mMaxTxPacketSize).toString());
                int k = i;
                i = j;
                while(i > 0) 
                {
                    int l;
                    if(i > mMaxTxPacketSize)
                        l = mMaxTxPacketSize;
                    else
                        l = i;
                    mSocketOS.write(abyte0, k, l);
                    k += l;
                    i -= l;
                }
            }
        } else
        {
            mSocketOS.write(abyte0, i, j);
        }
        if(VDBG)
            Log.d("BluetoothSocket", (new StringBuilder()).append("write out: ").append(mSocketOS).append(" length: ").append(j).toString());
        return j;
    }

    static final int BTSOCK_FLAG_NO_SDP = 4;
    private static final boolean DBG = Log.isLoggable("BluetoothSocket", 3);
    static final int EADDRINUSE = 98;
    static final int EBADFD = 77;
    static final int MAX_L2CAP_PACKAGE_SIZE = 65535;
    public static final int MAX_RFCOMM_CHANNEL = 30;
    private static int PROXY_CONNECTION_TIMEOUT = 0;
    static final int SEC_FLAG_AUTH = 2;
    static final int SEC_FLAG_AUTH_16_DIGIT = 16;
    static final int SEC_FLAG_AUTH_MITM = 8;
    static final int SEC_FLAG_ENCRYPT = 1;
    private static int SOCK_SIGNAL_SIZE = 0;
    private static final String TAG = "BluetoothSocket";
    public static final int TYPE_L2CAP = 3;
    public static final int TYPE_RFCOMM = 1;
    public static final int TYPE_SCO = 2;
    private static final boolean VDBG = Log.isLoggable("BluetoothSocket", 2);
    private String mAddress;
    private final boolean mAuth;
    private boolean mAuthMitm;
    private BluetoothDevice mDevice;
    private final boolean mEncrypt;
    private boolean mExcludeSdp;
    private int mFd;
    private final BluetoothInputStream mInputStream;
    private ByteBuffer mL2capBuffer;
    private int mMaxRxPacketSize;
    private int mMaxTxPacketSize;
    private boolean mMin16DigitPin;
    private final BluetoothOutputStream mOutputStream;
    private ParcelFileDescriptor mPfd;
    private int mPort;
    private String mServiceName;
    private LocalSocket mSocket;
    private InputStream mSocketIS;
    private OutputStream mSocketOS;
    private volatile SocketState mSocketState;
    private final int mType;
    private final ParcelUuid mUuid;

    static 
    {
        PROXY_CONNECTION_TIMEOUT = 5000;
        SOCK_SIGNAL_SIZE = 20;
    }
}
