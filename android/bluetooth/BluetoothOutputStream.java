// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package android.bluetooth:
//            BluetoothSocket

final class BluetoothOutputStream extends OutputStream
{

    BluetoothOutputStream(BluetoothSocket bluetoothsocket)
    {
        mSocket = bluetoothsocket;
    }

    public void close()
        throws IOException
    {
        mSocket.close();
    }

    public void flush()
        throws IOException
    {
        mSocket.flush();
    }

    public void write(int i)
        throws IOException
    {
        byte byte0 = (byte)i;
        mSocket.write(new byte[] {
            byte0
        }, 0, 1);
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        if(abyte0 == null)
            throw new NullPointerException("buffer is null");
        if((i | j) < 0 || j > abyte0.length - i)
        {
            throw new IndexOutOfBoundsException("invalid offset or length");
        } else
        {
            mSocket.write(abyte0, i, j);
            return;
        }
    }

    private BluetoothSocket mSocket;
}
