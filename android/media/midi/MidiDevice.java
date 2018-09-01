// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.*;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import libcore.io.IoUtils;

// Referenced classes of package android.media.midi:
//            IMidiManager, MidiDeviceInfo, MidiInputPort, IMidiDeviceServer, 
//            MidiOutputPort

public final class MidiDevice
    implements Closeable
{
    public class MidiConnection
        implements Closeable
    {

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
            mInputPortDeviceServer.closePort(mInputPortToken);
            MidiDevice._2D_get0(MidiDevice.this).closePort(mOutputPortToken);
_L1:
            mIsClosed = true;
            closeguard;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            Log.e("MidiDevice", "RemoteException in MidiConnection.close");
              goto _L1
            obj;
            throw obj;
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

        private final CloseGuard mGuard = CloseGuard.get();
        private final IMidiDeviceServer mInputPortDeviceServer;
        private final IBinder mInputPortToken;
        private boolean mIsClosed;
        private final IBinder mOutputPortToken;
        final MidiDevice this$0;

        MidiConnection(IBinder ibinder, MidiInputPort midiinputport)
        {
            this$0 = MidiDevice.this;
            super();
            mInputPortDeviceServer = midiinputport.getDeviceServer();
            mInputPortToken = midiinputport.getToken();
            mOutputPortToken = ibinder;
            mGuard.open("close");
        }
    }


    static IMidiDeviceServer _2D_get0(MidiDevice mididevice)
    {
        return mididevice.mDeviceServer;
    }

    MidiDevice(MidiDeviceInfo midideviceinfo, IMidiDeviceServer imidideviceserver, IMidiManager imidimanager, IBinder ibinder, IBinder ibinder1)
    {
        mDeviceInfo = midideviceinfo;
        mDeviceServer = imidideviceserver;
        mMidiManager = imidimanager;
        mClientToken = ibinder;
        mDeviceToken = ibinder1;
        mGuard.open("close");
    }

    private native long native_mirrorToNative(IBinder ibinder, int i);

    private native void native_removeFromNative(long l);

    public void close()
        throws IOException
    {
        CloseGuard closeguard = mGuard;
        closeguard;
        JVM INSTR monitorenter ;
        if(mIsDeviceClosed)
            break MISSING_BLOCK_LABEL_47;
        removeFromNative();
        mGuard.close();
        mIsDeviceClosed = true;
        mMidiManager.closeDevice(mClientToken, mDeviceToken);
_L1:
        closeguard;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        Log.e("MidiDevice", "RemoteException in closeDevice");
          goto _L1
        obj;
        throw obj;
    }

    public MidiConnection connectPorts(MidiInputPort midiinputport, int i)
    {
        if(i < 0 || i >= mDeviceInfo.getOutputPortCount())
            throw new IllegalArgumentException("outputPortNumber out of range");
        if(mIsDeviceClosed)
            return null;
        java.io.FileDescriptor filedescriptor = midiinputport.claimFileDescriptor();
        if(filedescriptor == null)
            return null;
        try
        {
            Binder binder = JVM INSTR new #128 <Class Binder>;
            binder.Binder();
            if(mDeviceServer.connectPorts(binder, filedescriptor, i) != Process.myPid())
                IoUtils.closeQuietly(filedescriptor);
            midiinputport = new MidiConnection(binder, midiinputport);
        }
        // Misplaced declaration of an exception variable
        catch(MidiInputPort midiinputport)
        {
            Log.e("MidiDevice", "RemoteException in connectPorts");
            return null;
        }
        return midiinputport;
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

    public MidiDeviceInfo getInfo()
    {
        return mDeviceInfo;
    }

    public long mirrorToNative()
        throws IOException
    {
        if(mIsDeviceClosed || mNativeHandle != 0L)
            return 0L;
        mNativeHandle = native_mirrorToNative(mDeviceServer.asBinder(), mDeviceInfo.getId());
        if(mNativeHandle == 0L)
            throw new IOException("Failed mirroring to native");
        HashSet hashset = mMirroredDevices;
        hashset;
        JVM INSTR monitorenter ;
        mMirroredDevices.add(this);
        hashset;
        JVM INSTR monitorexit ;
        return mNativeHandle;
        Exception exception;
        exception;
        throw exception;
    }

    public MidiInputPort openInputPort(int i)
    {
        if(mIsDeviceClosed)
            return null;
        Object obj;
        java.io.FileDescriptor filedescriptor;
        try
        {
            obj = JVM INSTR new #128 <Class Binder>;
            ((Binder) (obj)).Binder();
            filedescriptor = mDeviceServer.openInputPort(((IBinder) (obj)), i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MidiDevice", "RemoteException in openInputPort");
            return null;
        }
        if(filedescriptor == null)
            return null;
        obj = new MidiInputPort(mDeviceServer, ((IBinder) (obj)), filedescriptor, i);
        return ((MidiInputPort) (obj));
    }

    public MidiOutputPort openOutputPort(int i)
    {
        if(mIsDeviceClosed)
            return null;
        Object obj;
        java.io.FileDescriptor filedescriptor;
        try
        {
            obj = JVM INSTR new #128 <Class Binder>;
            ((Binder) (obj)).Binder();
            filedescriptor = mDeviceServer.openOutputPort(((IBinder) (obj)), i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MidiDevice", "RemoteException in openOutputPort");
            return null;
        }
        if(filedescriptor == null)
            return null;
        obj = new MidiOutputPort(mDeviceServer, ((IBinder) (obj)), filedescriptor, i);
        return ((MidiOutputPort) (obj));
    }

    public void removeFromNative()
    {
        if(mNativeHandle == 0L)
            return;
        CloseGuard closeguard = mGuard;
        closeguard;
        JVM INSTR monitorenter ;
        native_removeFromNative(mNativeHandle);
        mNativeHandle = 0L;
        closeguard;
        JVM INSTR monitorexit ;
        Object obj = mMirroredDevices;
        obj;
        JVM INSTR monitorenter ;
        mMirroredDevices.remove(this);
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        return (new StringBuilder()).append("MidiDevice: ").append(mDeviceInfo.toString()).toString();
    }

    private static final String TAG = "MidiDevice";
    private static HashSet mMirroredDevices = new HashSet();
    private final IBinder mClientToken;
    private final MidiDeviceInfo mDeviceInfo;
    private final IMidiDeviceServer mDeviceServer;
    private final IBinder mDeviceToken;
    private final CloseGuard mGuard = CloseGuard.get();
    private boolean mIsDeviceClosed;
    private final IMidiManager mMidiManager;
    private long mNativeHandle;

    static 
    {
        System.loadLibrary("media_jni");
    }
}
