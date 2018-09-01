// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.bluetooth.BluetoothDevice;
import android.os.*;

public interface IBluetoothMidiService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothMidiService
    {

        public static IBluetoothMidiService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.midi.IBluetoothMidiService");
            if(iinterface != null && (iinterface instanceof IBluetoothMidiService))
                return (IBluetoothMidiService)iinterface;
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
                parcel1.writeString("android.media.midi.IBluetoothMidiService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.midi.IBluetoothMidiService");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            parcel = addBluetoothDevice(parcel);
            parcel1.writeNoException();
            parcel1.writeStrongBinder(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.midi.IBluetoothMidiService";
        static final int TRANSACTION_addBluetoothDevice = 1;

        public Stub()
        {
            attachInterface(this, "android.media.midi.IBluetoothMidiService");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothMidiService
    {

        public IBinder addBluetoothDevice(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IBluetoothMidiService");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            bluetoothdevice = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return bluetoothdevice;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.midi.IBluetoothMidiService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract IBinder addBluetoothDevice(BluetoothDevice bluetoothdevice)
        throws RemoteException;
}
