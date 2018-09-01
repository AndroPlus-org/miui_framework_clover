// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.*;
import android.util.Log;
import com.android.internal.midi.MidiDispatcher;
import dalvik.system.CloseGuard;
import java.io.*;
import libcore.io.IoUtils;

// Referenced classes of package android.media.midi:
//            MidiSender, IMidiDeviceServer, MidiReceiver, MidiPortImpl

public final class MidiOutputPort extends MidiSender
    implements Closeable
{

    static MidiDispatcher _2D_get0(MidiOutputPort midioutputport)
    {
        return midioutputport.mDispatcher;
    }

    static FileInputStream _2D_get1(MidiOutputPort midioutputport)
    {
        return midioutputport.mInputStream;
    }

    MidiOutputPort(IMidiDeviceServer imidideviceserver, IBinder ibinder, FileDescriptor filedescriptor, int i)
    {
        mDispatcher = new MidiDispatcher();
        mGuard = CloseGuard.get();
        mThread = new Thread() {

            public void run()
            {
                Object obj = new byte[1024];
_L7:
                int j = MidiOutputPort._2D_get1(MidiOutputPort.this).read(((byte []) (obj)));
                if(j >= 0) goto _L2; else goto _L1
_L1:
                IoUtils.closeQuietly(MidiOutputPort._2D_get1(MidiOutputPort.this));
_L22:
                return;
_L2:
                int k = MidiPortImpl.getPacketType(((byte []) (obj)), j);
                k;
                JVM INSTR tableswitch 1 2: default 64
            //                           1 120
            //                           2 172;
                   goto _L3 _L4 _L5
_L9:
                if(true) goto _L7; else goto _L6
_L6:
_L11:
                if(true) goto _L9; else goto _L8
_L8:
_L13:
                if(true) goto _L11; else goto _L10
_L10:
_L15:
                if(true) goto _L13; else goto _L12
_L12:
_L17:
                if(true) goto _L15; else goto _L14
_L14:
_L19:
                if(true) goto _L17; else goto _L16
_L16:
_L21:
                if(true) goto _L19; else goto _L18
_L18:
                if(true) goto _L21; else goto _L20
_L20:
_L3:
                StringBuilder stringbuilder = JVM INSTR new #43  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.e("MidiOutputPort", stringbuilder.append("Unknown packet type ").append(k).toString());
                  goto _L7
                obj;
                Log.e("MidiOutputPort", "read failed", ((Throwable) (obj)));
                IoUtils.closeQuietly(MidiOutputPort._2D_get1(MidiOutputPort.this));
                  goto _L22
_L4:
                int l = MidiPortImpl.getDataOffset(((byte []) (obj)), j);
                int i1 = MidiPortImpl.getDataSize(((byte []) (obj)), j);
                long l1 = MidiPortImpl.getPacketTimestamp(((byte []) (obj)), j);
                MidiOutputPort._2D_get0(MidiOutputPort.this).send(((byte []) (obj)), l, i1, l1);
                  goto _L7
                Exception exception;
                exception;
                IoUtils.closeQuietly(MidiOutputPort._2D_get1(MidiOutputPort.this));
                throw exception;
_L5:
                MidiOutputPort._2D_get0(MidiOutputPort.this).flush();
                  goto _L7
            }

            final MidiOutputPort this$0;

            
            {
                this$0 = MidiOutputPort.this;
                super();
            }
        }
;
        mDeviceServer = imidideviceserver;
        mToken = ibinder;
        mPortNumber = i;
        mInputStream = new android.os.ParcelFileDescriptor.AutoCloseInputStream(new ParcelFileDescriptor(filedescriptor));
        mThread.start();
        mGuard.open("close");
    }

    MidiOutputPort(FileDescriptor filedescriptor, int i)
    {
        this(null, null, filedescriptor, i);
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
        IMidiDeviceServer imidideviceserver;
        mGuard.close();
        mInputStream.close();
        imidideviceserver = mDeviceServer;
        if(imidideviceserver == null)
            break MISSING_BLOCK_LABEL_55;
        mDeviceServer.closePort(mToken);
_L1:
        mIsClosed = true;
        closeguard;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        Log.e("MidiOutputPort", "RemoteException in MidiOutputPort.close()");
          goto _L1
        obj;
        throw obj;
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

    public final int getPortNumber()
    {
        return mPortNumber;
    }

    public void onConnect(MidiReceiver midireceiver)
    {
        mDispatcher.getSender().connect(midireceiver);
    }

    public void onDisconnect(MidiReceiver midireceiver)
    {
        mDispatcher.getSender().disconnect(midireceiver);
    }

    private static final String TAG = "MidiOutputPort";
    private IMidiDeviceServer mDeviceServer;
    private final MidiDispatcher mDispatcher;
    private final CloseGuard mGuard;
    private final FileInputStream mInputStream;
    private boolean mIsClosed;
    private final int mPortNumber;
    private final Thread mThread;
    private final IBinder mToken;
}
