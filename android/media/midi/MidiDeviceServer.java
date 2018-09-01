// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.*;
import android.system.*;
import android.util.Log;
import com.android.internal.midi.MidiDispatcher;
import dalvik.system.CloseGuard;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import libcore.io.IoUtils;

// Referenced classes of package android.media.midi:
//            MidiOutputPort, MidiDeviceInfo, MidiDeviceStatus, IMidiManager, 
//            IMidiDeviceServer, MidiInputPort, MidiReceiver, MidiSender

public final class MidiDeviceServer
    implements Closeable
{
    public static interface Callback
    {

        public abstract void onClose();

        public abstract void onDeviceStatusChanged(MidiDeviceServer midideviceserver, MidiDeviceStatus mididevicestatus);
    }

    private class InputPortClient extends PortClient
    {

        void close()
        {
            mToken.unlinkToDeath(this, 0);
            MidiOutputPort amidioutputport[] = MidiDeviceServer._2D_get5(MidiDeviceServer.this);
            amidioutputport;
            JVM INSTR monitorenter ;
            int i = mOutputPort.getPortNumber();
            MidiDeviceServer._2D_get5(MidiDeviceServer.this)[i] = null;
            MidiDeviceServer._2D_get4(MidiDeviceServer.this)[i] = false;
            MidiDeviceServer._2D_wrap1(MidiDeviceServer.this);
            amidioutputport;
            JVM INSTR monitorexit ;
            IoUtils.closeQuietly(mOutputPort);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private final MidiOutputPort mOutputPort;
        final MidiDeviceServer this$0;

        InputPortClient(IBinder ibinder, MidiOutputPort midioutputport)
        {
            this$0 = MidiDeviceServer.this;
            super(ibinder);
            mOutputPort = midioutputport;
        }
    }

    private class OutputPortClient extends PortClient
    {

        void close()
        {
            int i;
            mToken.unlinkToDeath(this, 0);
            i = mInputPort.getPortNumber();
            MidiDispatcher mididispatcher = MidiDeviceServer._2D_get9(MidiDeviceServer.this)[i];
            mididispatcher;
            JVM INSTR monitorenter ;
            mididispatcher.getSender().disconnect(mInputPort);
            int j = mididispatcher.getReceiverCount();
            MidiDeviceServer._2D_get10(MidiDeviceServer.this)[i] = j;
            MidiDeviceServer._2D_wrap1(MidiDeviceServer.this);
            mididispatcher;
            JVM INSTR monitorexit ;
            MidiDeviceServer._2D_get7(MidiDeviceServer.this).remove(mInputPort);
            IoUtils.closeQuietly(mInputPort);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        MidiInputPort getInputPort()
        {
            return mInputPort;
        }

        private final MidiInputPort mInputPort;
        final MidiDeviceServer this$0;

        OutputPortClient(IBinder ibinder, MidiInputPort midiinputport)
        {
            this$0 = MidiDeviceServer.this;
            super(ibinder);
            mInputPort = midiinputport;
        }
    }

    private abstract class PortClient
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            close();
        }

        abstract void close();

        MidiInputPort getInputPort()
        {
            return null;
        }

        final IBinder mToken;
        final MidiDeviceServer this$0;

        PortClient(IBinder ibinder)
        {
            this$0 = MidiDeviceServer.this;
            super();
            mToken = ibinder;
            ibinder.linkToDeath(this, 0);
_L1:
            return;
            midideviceserver;
            close();
              goto _L1
        }
    }


    static Callback _2D_get0(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mCallback;
    }

    static MidiDeviceInfo _2D_get1(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mDeviceInfo;
    }

    static int[] _2D_get10(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mOutputPortOpenCount;
    }

    static HashMap _2D_get11(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mPortClients;
    }

    static HashMap _2D_get2(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mInputPortClients;
    }

    static int _2D_get3(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mInputPortCount;
    }

    static boolean[] _2D_get4(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mInputPortOpen;
    }

    static MidiOutputPort[] _2D_get5(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mInputPortOutputPorts;
    }

    static MidiReceiver[] _2D_get6(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mInputPortReceivers;
    }

    static CopyOnWriteArrayList _2D_get7(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mInputPorts;
    }

    static int _2D_get8(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mOutputPortCount;
    }

    static MidiDispatcher[] _2D_get9(MidiDeviceServer midideviceserver)
    {
        return midideviceserver.mOutputPortDispatchers;
    }

    static MidiDeviceInfo _2D_set0(MidiDeviceServer midideviceserver, MidiDeviceInfo midideviceinfo)
    {
        midideviceserver.mDeviceInfo = midideviceinfo;
        return midideviceinfo;
    }

    static FileDescriptor[] _2D_wrap0()
    {
        return createSeqPacketSocketPair();
    }

    static void _2D_wrap1(MidiDeviceServer midideviceserver)
    {
        midideviceserver.updateDeviceStatus();
    }

    MidiDeviceServer(IMidiManager imidimanager, MidiReceiver amidireceiver[], int i, Callback callback)
    {
        mInputPorts = new CopyOnWriteArrayList();
        mGuard = CloseGuard.get();
        mPortClients = new HashMap();
        mInputPortClients = new HashMap();
        mServer = new IMidiDeviceServer.Stub() {

            public void closeDevice()
            {
                if(MidiDeviceServer._2D_get0(MidiDeviceServer.this) != null)
                    MidiDeviceServer._2D_get0(MidiDeviceServer.this).onClose();
                IoUtils.closeQuietly(MidiDeviceServer.this);
            }

            public void closePort(IBinder ibinder)
            {
                HashMap hashmap = null;
                HashMap hashmap1 = MidiDeviceServer._2D_get11(MidiDeviceServer.this);
                hashmap1;
                JVM INSTR monitorenter ;
                PortClient portclient = (PortClient)MidiDeviceServer._2D_get11(MidiDeviceServer.this).remove(ibinder);
                ibinder = hashmap;
                if(portclient == null)
                    break MISSING_BLOCK_LABEL_46;
                ibinder = portclient.getInputPort();
                portclient.close();
                hashmap1;
                JVM INSTR monitorexit ;
                if(ibinder == null) goto _L2; else goto _L1
_L1:
                hashmap = MidiDeviceServer._2D_get2(MidiDeviceServer.this);
                hashmap;
                JVM INSTR monitorenter ;
                MidiDeviceServer._2D_get2(MidiDeviceServer.this).remove(ibinder);
                hashmap;
                JVM INSTR monitorexit ;
_L2:
                return;
                ibinder;
                throw ibinder;
                ibinder;
                throw ibinder;
            }

            public int connectPorts(IBinder ibinder, FileDescriptor filedescriptor, int k)
            {
                filedescriptor = new MidiInputPort(filedescriptor, k);
                Object obj = MidiDeviceServer._2D_get9(MidiDeviceServer.this)[k];
                obj;
                JVM INSTR monitorenter ;
                ((MidiDispatcher) (obj)).getSender().connect(filedescriptor);
                int l = ((MidiDispatcher) (obj)).getReceiverCount();
                MidiDeviceServer._2D_get10(MidiDeviceServer.this)[k] = l;
                MidiDeviceServer._2D_wrap1(MidiDeviceServer.this);
                obj;
                JVM INSTR monitorexit ;
                OutputPortClient outputportclient;
                MidiDeviceServer._2D_get7(MidiDeviceServer.this).add(filedescriptor);
                outputportclient = new OutputPortClient(ibinder, filedescriptor);
                obj = MidiDeviceServer._2D_get11(MidiDeviceServer.this);
                obj;
                JVM INSTR monitorenter ;
                MidiDeviceServer._2D_get11(MidiDeviceServer.this).put(ibinder, outputportclient);
                obj;
                JVM INSTR monitorexit ;
                ibinder = MidiDeviceServer._2D_get2(MidiDeviceServer.this);
                ibinder;
                JVM INSTR monitorenter ;
                MidiDeviceServer._2D_get2(MidiDeviceServer.this).put(filedescriptor, outputportclient);
                ibinder;
                JVM INSTR monitorexit ;
                return Process.myPid();
                ibinder;
                throw ibinder;
                ibinder;
                throw ibinder;
                filedescriptor;
                throw filedescriptor;
            }

            public MidiDeviceInfo getDeviceInfo()
            {
                return MidiDeviceServer._2D_get1(MidiDeviceServer.this);
            }

            public FileDescriptor openInputPort(IBinder ibinder, int k)
            {
                if(MidiDeviceServer._2D_get1(MidiDeviceServer.this).isPrivate() && Binder.getCallingUid() != Process.myUid())
                    throw new SecurityException("Can't access private device from different UID");
                if(k < 0 || k >= MidiDeviceServer._2D_get3(MidiDeviceServer.this))
                {
                    Log.e("MidiDeviceServer", (new StringBuilder()).append("portNumber out of range in openInputPort: ").append(k).toString());
                    return null;
                }
                MidiOutputPort amidioutputport[] = MidiDeviceServer._2D_get5(MidiDeviceServer.this);
                amidioutputport;
                JVM INSTR monitorenter ;
                if(MidiDeviceServer._2D_get5(MidiDeviceServer.this)[k] == null)
                    break MISSING_BLOCK_LABEL_132;
                ibinder = JVM INSTR new #153 <Class StringBuilder>;
                ibinder.StringBuilder();
                Log.d("MidiDeviceServer", ibinder.append("port ").append(k).append(" already open").toString());
                amidioutputport;
                JVM INSTR monitorexit ;
                return null;
                FileDescriptor afiledescriptor[];
                InputPortClient inputportclient;
                afiledescriptor = MidiDeviceServer._2D_wrap0();
                MidiOutputPort midioutputport = JVM INSTR new #190 <Class MidiOutputPort>;
                midioutputport.MidiOutputPort(afiledescriptor[0], k);
                MidiDeviceServer._2D_get5(MidiDeviceServer.this)[k] = midioutputport;
                midioutputport.connect(MidiDeviceServer._2D_get6(MidiDeviceServer.this)[k]);
                inputportclient = JVM INSTR new #198 <Class MidiDeviceServer$InputPortClient>;
                inputportclient.MidiDeviceServer.this. InputPortClient(ibinder, midioutputport);
                HashMap hashmap = MidiDeviceServer._2D_get11(MidiDeviceServer.this);
                hashmap;
                JVM INSTR monitorenter ;
                MidiDeviceServer._2D_get11(MidiDeviceServer.this).put(ibinder, inputportclient);
                hashmap;
                JVM INSTR monitorexit ;
                MidiDeviceServer._2D_get4(MidiDeviceServer.this)[k] = true;
                MidiDeviceServer._2D_wrap1(MidiDeviceServer.this);
                ibinder = afiledescriptor[1];
                amidioutputport;
                JVM INSTR monitorexit ;
                return ibinder;
                ibinder;
                hashmap;
                JVM INSTR monitorexit ;
                throw ibinder;
                ibinder;
                Log.e("MidiDeviceServer", "unable to create FileDescriptors in openInputPort");
                amidioutputport;
                JVM INSTR monitorexit ;
                return null;
                ibinder;
                throw ibinder;
            }

            public FileDescriptor openOutputPort(IBinder ibinder, int k)
            {
                if(MidiDeviceServer._2D_get1(MidiDeviceServer.this).isPrivate() && Binder.getCallingUid() != Process.myUid())
                    throw new SecurityException("Can't access private device from different UID");
                if(k < 0 || k >= MidiDeviceServer._2D_get8(MidiDeviceServer.this))
                {
                    Log.e("MidiDeviceServer", (new StringBuilder()).append("portNumber out of range in openOutputPort: ").append(k).toString());
                    return null;
                }
                FileDescriptor afiledescriptor[];
                MidiInputPort midiinputport;
                afiledescriptor = MidiDeviceServer._2D_wrap0();
                midiinputport = JVM INSTR new #60  <Class MidiInputPort>;
                midiinputport.MidiInputPort(afiledescriptor[0], k);
                if(MidiDeviceServer._2D_get1(MidiDeviceServer.this).getType() != 2)
                    IoUtils.setBlocking(afiledescriptor[0], false);
                Object obj = MidiDeviceServer._2D_get9(MidiDeviceServer.this)[k];
                obj;
                JVM INSTR monitorenter ;
                ((MidiDispatcher) (obj)).getSender().connect(midiinputport);
                int l = ((MidiDispatcher) (obj)).getReceiverCount();
                MidiDeviceServer._2D_get10(MidiDeviceServer.this)[k] = l;
                MidiDeviceServer._2D_wrap1(MidiDeviceServer.this);
                obj;
                JVM INSTR monitorexit ;
                MidiDeviceServer._2D_get7(MidiDeviceServer.this).add(midiinputport);
                obj = JVM INSTR new #102 <Class MidiDeviceServer$OutputPortClient>;
                ((OutputPortClient) (obj)).MidiDeviceServer.this. OutputPortClient(ibinder, midiinputport);
                HashMap hashmap = MidiDeviceServer._2D_get11(MidiDeviceServer.this);
                hashmap;
                JVM INSTR monitorenter ;
                MidiDeviceServer._2D_get11(MidiDeviceServer.this).put(ibinder, obj);
                hashmap;
                JVM INSTR monitorexit ;
                ibinder = MidiDeviceServer._2D_get2(MidiDeviceServer.this);
                ibinder;
                JVM INSTR monitorenter ;
                MidiDeviceServer._2D_get2(MidiDeviceServer.this).put(midiinputport, obj);
                ibinder;
                JVM INSTR monitorexit ;
                return afiledescriptor[1];
                ibinder;
                obj;
                JVM INSTR monitorexit ;
                throw ibinder;
                ibinder;
                Log.e("MidiDeviceServer", "unable to create FileDescriptors in openOutputPort");
                return null;
                ibinder;
                hashmap;
                JVM INSTR monitorexit ;
                throw ibinder;
                Exception exception;
                exception;
                ibinder;
                JVM INSTR monitorexit ;
                throw exception;
            }

            public void setDeviceInfo(MidiDeviceInfo midideviceinfo)
            {
                if(Binder.getCallingUid() != 1000)
                    throw new SecurityException("setDeviceInfo should only be called by MidiService");
                if(MidiDeviceServer._2D_get1(MidiDeviceServer.this) != null)
                {
                    throw new IllegalStateException("setDeviceInfo should only be called once");
                } else
                {
                    MidiDeviceServer._2D_set0(MidiDeviceServer.this, midideviceinfo);
                    return;
                }
            }

            final MidiDeviceServer this$0;

            
            {
                this$0 = MidiDeviceServer.this;
                super();
            }
        }
;
        mInputPortFailureHandler = new com.android.internal.midi.MidiDispatcher.MidiReceiverFailureHandler() {

            public void onReceiverFailure(MidiReceiver midireceiver, IOException ioexception)
            {
                Log.e("MidiDeviceServer", "MidiInputPort failed to send data", ioexception);
                ioexception = MidiDeviceServer._2D_get2(MidiDeviceServer.this);
                ioexception;
                JVM INSTR monitorenter ;
                midireceiver = (PortClient)MidiDeviceServer._2D_get2(MidiDeviceServer.this).remove(midireceiver);
                ioexception;
                JVM INSTR monitorexit ;
                if(midireceiver != null)
                    midireceiver.close();
                return;
                midireceiver;
                throw midireceiver;
            }

            final MidiDeviceServer this$0;

            
            {
                this$0 = MidiDeviceServer.this;
                super();
            }
        }
;
        mMidiManager = imidimanager;
        mInputPortReceivers = amidireceiver;
        mInputPortCount = amidireceiver.length;
        mOutputPortCount = i;
        mCallback = callback;
        mInputPortOutputPorts = new MidiOutputPort[mInputPortCount];
        mOutputPortDispatchers = new MidiDispatcher[i];
        for(int j = 0; j < i; j++)
            mOutputPortDispatchers[j] = new MidiDispatcher(mInputPortFailureHandler);

        mInputPortOpen = new boolean[mInputPortCount];
        mOutputPortOpenCount = new int[i];
        mGuard.open("close");
    }

    MidiDeviceServer(IMidiManager imidimanager, MidiReceiver amidireceiver[], MidiDeviceInfo midideviceinfo, Callback callback)
    {
        this(imidimanager, amidireceiver, midideviceinfo.getOutputPortCount(), callback);
        mDeviceInfo = midideviceinfo;
    }

    private static FileDescriptor[] createSeqPacketSocketPair()
        throws IOException
    {
        FileDescriptor filedescriptor;
        FileDescriptor filedescriptor1;
        try
        {
            filedescriptor = JVM INSTR new #176 <Class FileDescriptor>;
            filedescriptor.FileDescriptor();
            filedescriptor1 = JVM INSTR new #176 <Class FileDescriptor>;
            filedescriptor1.FileDescriptor();
            Os.socketpair(OsConstants.AF_UNIX, OsConstants.SOCK_SEQPACKET, 0, filedescriptor, filedescriptor1);
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
        return (new FileDescriptor[] {
            filedescriptor, filedescriptor1
        });
    }

    private void updateDeviceStatus()
    {
        long l;
        MidiDeviceStatus mididevicestatus;
        l = Binder.clearCallingIdentity();
        mididevicestatus = new MidiDeviceStatus(mDeviceInfo, mInputPortOpen, mOutputPortOpenCount);
        if(mCallback != null)
            mCallback.onDeviceStatusChanged(this, mididevicestatus);
        mMidiManager.setDeviceStatus(mServer, mididevicestatus);
        Binder.restoreCallingIdentity(l);
_L2:
        return;
        Object obj;
        obj;
        Log.e("MidiDeviceServer", "RemoteException in updateDeviceStatus");
        Binder.restoreCallingIdentity(l);
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        Binder.restoreCallingIdentity(l);
        throw obj;
    }

    public IBinder asBinder()
    {
        return mServer.asBinder();
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
        int i = 0;
_L2:
        MidiOutputPort midioutputport;
        if(i >= mInputPortCount)
            break; /* Loop/switch isn't completed */
        midioutputport = mInputPortOutputPorts[i];
        if(midioutputport == null)
            break MISSING_BLOCK_LABEL_61;
        IoUtils.closeQuietly(midioutputport);
        mInputPortOutputPorts[i] = null;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        for(Iterator iterator = mInputPorts.iterator(); iterator.hasNext(); IoUtils.closeQuietly((MidiInputPort)iterator.next()));
        break MISSING_BLOCK_LABEL_111;
        Object obj;
        obj;
        throw obj;
        mInputPorts.clear();
        mMidiManager.unregisterDeviceServer(mServer);
_L3:
        mIsClosed = true;
        closeguard;
        JVM INSTR monitorexit ;
        return;
        obj;
        Log.e("MidiDeviceServer", "RemoteException in unregisterDeviceServer");
          goto _L3
    }

    protected void finalize()
        throws Throwable
    {
        if(mGuard != null)
            mGuard.warnIfOpen();
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    IMidiDeviceServer getBinderInterface()
    {
        return mServer;
    }

    public MidiReceiver[] getOutputPortReceivers()
    {
        MidiReceiver amidireceiver[] = new MidiReceiver[mOutputPortCount];
        System.arraycopy(mOutputPortDispatchers, 0, amidireceiver, 0, mOutputPortCount);
        return amidireceiver;
    }

    private static final String TAG = "MidiDeviceServer";
    private final Callback mCallback;
    private MidiDeviceInfo mDeviceInfo;
    private final CloseGuard mGuard;
    private final HashMap mInputPortClients;
    private final int mInputPortCount;
    private final com.android.internal.midi.MidiDispatcher.MidiReceiverFailureHandler mInputPortFailureHandler;
    private final boolean mInputPortOpen[];
    private final MidiOutputPort mInputPortOutputPorts[];
    private final MidiReceiver mInputPortReceivers[];
    private final CopyOnWriteArrayList mInputPorts;
    private boolean mIsClosed;
    private final IMidiManager mMidiManager;
    private final int mOutputPortCount;
    private MidiDispatcher mOutputPortDispatchers[];
    private final int mOutputPortOpenCount[];
    private final HashMap mPortClients;
    private final IMidiDeviceServer mServer;
}
