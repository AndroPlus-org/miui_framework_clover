// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.*;
import libcore.io.IoUtils;

// Referenced classes of package android.media.midi:
//            MidiReceiver, IMidiDeviceServer, MidiPortImpl

public final class MidiInputPort extends MidiReceiver
    implements Closeable
{

    MidiInputPort(IMidiDeviceServer imidideviceserver, IBinder ibinder, FileDescriptor filedescriptor, int i)
    {
        super(1015);
        mGuard = CloseGuard.get();
        mBuffer = new byte[1024];
        mDeviceServer = imidideviceserver;
        mToken = ibinder;
        mFileDescriptor = filedescriptor;
        mPortNumber = i;
        mOutputStream = new FileOutputStream(filedescriptor);
        mGuard.open("close");
    }

    MidiInputPort(FileDescriptor filedescriptor, int i)
    {
        this(null, null, filedescriptor, i);
    }

    FileDescriptor claimFileDescriptor()
    {
        CloseGuard closeguard = mGuard;
        closeguard;
        JVM INSTR monitorenter ;
        byte abyte0[] = mBuffer;
        abyte0;
        JVM INSTR monitorenter ;
        FileDescriptor filedescriptor = mFileDescriptor;
        if(filedescriptor != null)
            break MISSING_BLOCK_LABEL_29;
        abyte0;
        JVM INSTR monitorexit ;
        closeguard;
        JVM INSTR monitorexit ;
        return null;
        IoUtils.closeQuietly(mOutputStream);
        mFileDescriptor = null;
        mOutputStream = null;
        abyte0;
        JVM INSTR monitorexit ;
        mIsClosed = true;
        closeguard;
        JVM INSTR monitorexit ;
        return filedescriptor;
        Exception exception1;
        exception1;
        abyte0;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        closeguard;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void close()
        throws IOException
    {
        CloseGuard closeguard = mGuard;
        closeguard;
        JVM INSTR monitorenter ;
        boolean flag = mIsClosed;
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        closeguard;
        JVM INSTR monitorexit ;
        return;
        mGuard.close();
        byte abyte0[] = mBuffer;
        abyte0;
        JVM INSTR monitorenter ;
        if(mFileDescriptor != null)
        {
            IoUtils.closeQuietly(mFileDescriptor);
            mFileDescriptor = null;
        }
        if(mOutputStream != null)
        {
            mOutputStream.close();
            mOutputStream = null;
        }
        abyte0;
        JVM INSTR monitorexit ;
        IMidiDeviceServer imidideviceserver = mDeviceServer;
        if(imidideviceserver == null)
            break MISSING_BLOCK_LABEL_97;
        mDeviceServer.closePort(mToken);
_L1:
        mIsClosed = true;
        closeguard;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        abyte0;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        closeguard;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        Log.e("MidiInputPort", "RemoteException in MidiInputPort.close()");
          goto _L1
    }

    protected void finalize()
        throws Throwable
    {
        if(mGuard != null)
            mGuard.warnIfOpen();
        mDeviceServer = null;
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    IMidiDeviceServer getDeviceServer()
    {
        return mDeviceServer;
    }

    public final int getPortNumber()
    {
        return mPortNumber;
    }

    IBinder getToken()
    {
        return mToken;
    }

    public void onFlush()
        throws IOException
    {
        synchronized(mBuffer)
        {
            if(mOutputStream == null)
            {
                IOException ioexception = JVM INSTR new #79  <Class IOException>;
                ioexception.IOException("MidiInputPort is closed");
                throw ioexception;
            }
            break MISSING_BLOCK_LABEL_31;
        }
        int i = MidiPortImpl.packFlush(mBuffer);
        mOutputStream.write(mBuffer, 0, i);
        abyte0;
        JVM INSTR monitorexit ;
    }

    public void onSend(byte abyte0[], int i, int j, long l)
        throws IOException
    {
label0:
        {
            while(i < 0 || j < 0 || i + j > abyte0.length) 
                throw new IllegalArgumentException("offset or count out of range");
            if(j > 1015)
                throw new IllegalArgumentException("count exceeds max message size");
            synchronized(mBuffer)
            {
                if(mOutputStream == null)
                {
                    abyte0 = JVM INSTR new #79  <Class IOException>;
                    abyte0.IOException("MidiInputPort is closed");
                    throw abyte0;
                }
                break label0;
            }
        }
        i = MidiPortImpl.packData(abyte0, i, j, l, mBuffer);
        mOutputStream.write(mBuffer, 0, i);
        abyte1;
        JVM INSTR monitorexit ;
    }

    private static final String TAG = "MidiInputPort";
    private final byte mBuffer[];
    private IMidiDeviceServer mDeviceServer;
    private FileDescriptor mFileDescriptor;
    private final CloseGuard mGuard;
    private boolean mIsClosed;
    private FileOutputStream mOutputStream;
    private final int mPortNumber;
    private final IBinder mToken;
}
