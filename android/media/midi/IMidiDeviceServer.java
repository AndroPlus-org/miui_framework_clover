// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.*;
import java.io.FileDescriptor;

// Referenced classes of package android.media.midi:
//            MidiDeviceInfo

public interface IMidiDeviceServer
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMidiDeviceServer
    {

        public static IMidiDeviceServer asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.midi.IMidiDeviceServer");
            if(iinterface != null && (iinterface instanceof IMidiDeviceServer))
                return (IMidiDeviceServer)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.media.midi.IMidiDeviceServer");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.midi.IMidiDeviceServer");
                parcel = openInputPort(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeRawFileDescriptor(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.midi.IMidiDeviceServer");
                parcel = openOutputPort(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeRawFileDescriptor(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.midi.IMidiDeviceServer");
                closePort(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.midi.IMidiDeviceServer");
                closeDevice();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.midi.IMidiDeviceServer");
                i = connectPorts(parcel.readStrongBinder(), parcel.readRawFileDescriptor(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.midi.IMidiDeviceServer");
                parcel = getDeviceInfo();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.midi.IMidiDeviceServer");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (MidiDeviceInfo)MidiDeviceInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            setDeviceInfo(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.midi.IMidiDeviceServer";
        static final int TRANSACTION_closeDevice = 4;
        static final int TRANSACTION_closePort = 3;
        static final int TRANSACTION_connectPorts = 5;
        static final int TRANSACTION_getDeviceInfo = 6;
        static final int TRANSACTION_openInputPort = 1;
        static final int TRANSACTION_openOutputPort = 2;
        static final int TRANSACTION_setDeviceInfo = 7;

        public Stub()
        {
            attachInterface(this, "android.media.midi.IMidiDeviceServer");
        }
    }

    private static class Stub.Proxy
        implements IMidiDeviceServer
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void closeDevice()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceServer");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void closePort(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceServer");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int connectPorts(IBinder ibinder, FileDescriptor filedescriptor, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceServer");
            parcel.writeStrongBinder(ibinder);
            parcel.writeRawFileDescriptor(filedescriptor);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public MidiDeviceInfo getDeviceInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceServer");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            MidiDeviceInfo midideviceinfo = (MidiDeviceInfo)MidiDeviceInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return midideviceinfo;
_L2:
            midideviceinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.midi.IMidiDeviceServer";
        }

        public FileDescriptor openInputPort(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceServer");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readRawFileDescriptor();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public FileDescriptor openOutputPort(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceServer");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readRawFileDescriptor();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setDeviceInfo(MidiDeviceInfo midideviceinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceServer");
            if(midideviceinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            midideviceinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            midideviceinfo;
            parcel.recycle();
            throw midideviceinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void closeDevice()
        throws RemoteException;

    public abstract void closePort(IBinder ibinder)
        throws RemoteException;

    public abstract int connectPorts(IBinder ibinder, FileDescriptor filedescriptor, int i)
        throws RemoteException;

    public abstract MidiDeviceInfo getDeviceInfo()
        throws RemoteException;

    public abstract FileDescriptor openInputPort(IBinder ibinder, int i)
        throws RemoteException;

    public abstract FileDescriptor openOutputPort(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void setDeviceInfo(MidiDeviceInfo midideviceinfo)
        throws RemoteException;
}
