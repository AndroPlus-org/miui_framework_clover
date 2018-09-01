// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

// Referenced classes of package android.bluetooth:
//            BluetoothSocket

public final class BluetoothServerSocket
    implements Closeable
{

    BluetoothServerSocket(int i, boolean flag, boolean flag1, int j)
        throws IOException
    {
        mChannel = j;
        mSocket = new BluetoothSocket(i, -1, flag, flag1, null, j, null);
        if(j == -2)
            mSocket.setExcludeSdp(true);
    }

    BluetoothServerSocket(int i, boolean flag, boolean flag1, int j, boolean flag2, boolean flag3)
        throws IOException
    {
        mChannel = j;
        mSocket = new BluetoothSocket(i, -1, flag, flag1, null, j, null, flag2, flag3);
        if(j == -2)
            mSocket.setExcludeSdp(true);
    }

    BluetoothServerSocket(int i, boolean flag, boolean flag1, ParcelUuid parceluuid)
        throws IOException
    {
        mSocket = new BluetoothSocket(i, -1, flag, flag1, null, -1, parceluuid);
        mChannel = mSocket.getPort();
    }

    public BluetoothSocket accept()
        throws IOException
    {
        return accept(-1);
    }

    public BluetoothSocket accept(int i)
        throws IOException
    {
        return mSocket.accept(i);
    }

    public void close()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if(mHandler != null)
            mHandler.obtainMessage(mMessage).sendToTarget();
        this;
        JVM INSTR monitorexit ;
        mSocket.close();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int getChannel()
    {
        return mChannel;
    }

    void setChannel(int i)
    {
        if(mSocket != null && mSocket.getPort() != i)
            Log.w("BluetoothServerSocket", (new StringBuilder()).append("The port set is different that the underlying port. mSocket.getPort(): ").append(mSocket.getPort()).append(" requested newChannel: ").append(i).toString());
        mChannel = i;
    }

    void setCloseHandler(Handler handler, int i)
    {
        this;
        JVM INSTR monitorenter ;
        mHandler = handler;
        mMessage = i;
        this;
        JVM INSTR monitorexit ;
        return;
        handler;
        throw handler;
    }

    void setServiceName(String s)
    {
        mSocket.setServiceName(s);
    }

    public String toString()
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        stringbuilder.append("ServerSocket: Type: ");
        mSocket.getConnectionType();
        JVM INSTR tableswitch 1 3: default 48
    //                   1 67
    //                   2 87
    //                   3 77;
           goto _L1 _L2 _L3 _L4
_L1:
        stringbuilder.append(" Channel: ").append(mChannel);
        return stringbuilder.toString();
_L2:
        stringbuilder.append("TYPE_RFCOMM");
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append("TYPE_L2CAP");
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuilder.append("TYPE_SCO");
        if(true) goto _L1; else goto _L5
_L5:
    }

    private static final String TAG = "BluetoothServerSocket";
    private int mChannel;
    private Handler mHandler;
    private int mMessage;
    final BluetoothSocket mSocket;
}
