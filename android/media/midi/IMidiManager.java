// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.bluetooth.BluetoothDevice;
import android.os.*;

// Referenced classes of package android.media.midi:
//            MidiDeviceInfo, MidiDeviceStatus, IMidiDeviceOpenCallback, IMidiDeviceServer, 
//            IMidiDeviceListener

public interface IMidiManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMidiManager
    {

        public static IMidiManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.midi.IMidiManager");
            if(iinterface != null && (iinterface instanceof IMidiManager))
                return (IMidiManager)iinterface;
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
            IMidiDeviceServer imidideviceserver1;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.media.midi.IMidiManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                parcel = getDevices();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                registerListener(parcel.readStrongBinder(), IMidiDeviceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                unregisterListener(parcel.readStrongBinder(), IMidiDeviceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                IBinder ibinder = parcel.readStrongBinder();
                MidiDeviceInfo midideviceinfo;
                if(parcel.readInt() != 0)
                    midideviceinfo = (MidiDeviceInfo)MidiDeviceInfo.CREATOR.createFromParcel(parcel);
                else
                    midideviceinfo = null;
                openDevice(ibinder, midideviceinfo, IMidiDeviceOpenCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                IBinder ibinder1 = parcel.readStrongBinder();
                BluetoothDevice bluetoothdevice;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                openBluetoothDevice(ibinder1, bluetoothdevice, IMidiDeviceOpenCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                closeDevice(parcel.readStrongBinder(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                IMidiDeviceServer imidideviceserver = IMidiDeviceServer.Stub.asInterface(parcel.readStrongBinder());
                j = parcel.readInt();
                i = parcel.readInt();
                String as[] = parcel.createStringArray();
                String as1[] = parcel.createStringArray();
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                parcel = registerDeviceServer(imidideviceserver, j, i, as, as1, bundle, parcel.readInt());
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

            case 8: // '\b'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                unregisterDeviceServer(IMidiDeviceServer.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                parcel = getServiceDeviceInfo(parcel.readString(), parcel.readString());
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

            case 10: // '\n'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                if(parcel.readInt() != 0)
                    parcel = (MidiDeviceInfo)MidiDeviceInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getDeviceStatus(parcel);
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

            case 11: // '\013'
                parcel.enforceInterface("android.media.midi.IMidiManager");
                imidideviceserver1 = IMidiDeviceServer.Stub.asInterface(parcel.readStrongBinder());
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (MidiDeviceStatus)MidiDeviceStatus.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            setDeviceStatus(imidideviceserver1, parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.media.midi.IMidiManager";
        static final int TRANSACTION_closeDevice = 6;
        static final int TRANSACTION_getDeviceStatus = 10;
        static final int TRANSACTION_getDevices = 1;
        static final int TRANSACTION_getServiceDeviceInfo = 9;
        static final int TRANSACTION_openBluetoothDevice = 5;
        static final int TRANSACTION_openDevice = 4;
        static final int TRANSACTION_registerDeviceServer = 7;
        static final int TRANSACTION_registerListener = 2;
        static final int TRANSACTION_setDeviceStatus = 11;
        static final int TRANSACTION_unregisterDeviceServer = 8;
        static final int TRANSACTION_unregisterListener = 3;

        public Stub()
        {
            attachInterface(this, "android.media.midi.IMidiManager");
        }
    }

    private static class Stub.Proxy
        implements IMidiManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void closeDevice(IBinder ibinder, IBinder ibinder1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeStrongBinder(ibinder1);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public MidiDeviceStatus getDeviceStatus(MidiDeviceInfo midideviceinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            if(midideviceinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            midideviceinfo.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            midideviceinfo = (MidiDeviceStatus)MidiDeviceStatus.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return midideviceinfo;
_L2:
            parcel.writeInt(0);
              goto _L3
            midideviceinfo;
            parcel1.recycle();
            parcel.recycle();
            throw midideviceinfo;
            midideviceinfo = null;
              goto _L4
        }

        public MidiDeviceInfo[] getDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            MidiDeviceInfo amidideviceinfo[];
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            amidideviceinfo = (MidiDeviceInfo[])parcel1.createTypedArray(MidiDeviceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return amidideviceinfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.midi.IMidiManager";
        }

        public MidiDeviceInfo getServiceDeviceInfo(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (MidiDeviceInfo)MidiDeviceInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void openBluetoothDevice(IBinder ibinder, BluetoothDevice bluetoothdevice, IMidiDeviceOpenCallback imidideviceopencallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            parcel.writeStrongBinder(ibinder);
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L4:
            ibinder = obj;
            if(imidideviceopencallback == null)
                break MISSING_BLOCK_LABEL_57;
            ibinder = imidideviceopencallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void openDevice(IBinder ibinder, MidiDeviceInfo midideviceinfo, IMidiDeviceOpenCallback imidideviceopencallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            parcel.writeStrongBinder(ibinder);
            if(midideviceinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            midideviceinfo.writeToParcel(parcel, 0);
_L4:
            ibinder = obj;
            if(imidideviceopencallback == null)
                break MISSING_BLOCK_LABEL_57;
            ibinder = imidideviceopencallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public MidiDeviceInfo registerDeviceServer(IMidiDeviceServer imidideviceserver, int i, int j, String as[], String as1[], Bundle bundle, int k)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            if(imidideviceserver == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = imidideviceserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeStringArray(as);
            parcel.writeStringArray(as1);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(k);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_169;
            imidideviceserver = (MidiDeviceInfo)MidiDeviceInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return imidideviceserver;
_L2:
            parcel.writeInt(0);
              goto _L3
            imidideviceserver;
            parcel1.recycle();
            parcel.recycle();
            throw imidideviceserver;
            imidideviceserver = null;
              goto _L4
        }

        public void registerListener(IBinder ibinder, IMidiDeviceListener imididevicelistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(imididevicelistener == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = imididevicelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setDeviceStatus(IMidiDeviceServer imidideviceserver, MidiDeviceStatus mididevicestatus)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            if(imidideviceserver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = imidideviceserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(mididevicestatus == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            mididevicestatus.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imidideviceserver;
            parcel1.recycle();
            parcel.recycle();
            throw imidideviceserver;
        }

        public void unregisterDeviceServer(IMidiDeviceServer imidideviceserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            if(imidideviceserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imidideviceserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imidideviceserver;
            parcel1.recycle();
            parcel.recycle();
            throw imidideviceserver;
        }

        public void unregisterListener(IBinder ibinder, IMidiDeviceListener imididevicelistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiManager");
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(imididevicelistener == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = imididevicelistener.asBinder();
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void closeDevice(IBinder ibinder, IBinder ibinder1)
        throws RemoteException;

    public abstract MidiDeviceStatus getDeviceStatus(MidiDeviceInfo midideviceinfo)
        throws RemoteException;

    public abstract MidiDeviceInfo[] getDevices()
        throws RemoteException;

    public abstract MidiDeviceInfo getServiceDeviceInfo(String s, String s1)
        throws RemoteException;

    public abstract void openBluetoothDevice(IBinder ibinder, BluetoothDevice bluetoothdevice, IMidiDeviceOpenCallback imidideviceopencallback)
        throws RemoteException;

    public abstract void openDevice(IBinder ibinder, MidiDeviceInfo midideviceinfo, IMidiDeviceOpenCallback imidideviceopencallback)
        throws RemoteException;

    public abstract MidiDeviceInfo registerDeviceServer(IMidiDeviceServer imidideviceserver, int i, int j, String as[], String as1[], Bundle bundle, int k)
        throws RemoteException;

    public abstract void registerListener(IBinder ibinder, IMidiDeviceListener imididevicelistener)
        throws RemoteException;

    public abstract void setDeviceStatus(IMidiDeviceServer imidideviceserver, MidiDeviceStatus mididevicestatus)
        throws RemoteException;

    public abstract void unregisterDeviceServer(IMidiDeviceServer imidideviceserver)
        throws RemoteException;

    public abstract void unregisterListener(IBinder ibinder, IMidiDeviceListener imididevicelistener)
        throws RemoteException;
}
