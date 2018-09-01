// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice, BluetoothHidDeviceAppConfiguration

public interface IBluetoothHidDeviceCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothHidDeviceCallback
    {

        public static IBluetoothHidDeviceCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothHidDeviceCallback");
            if(iinterface != null && (iinterface instanceof IBluetoothHidDeviceCallback))
                return (IBluetoothHidDeviceCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothHidDeviceCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
                BluetoothDevice bluetoothdevice;
                BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration;
                boolean flag;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                if(parcel.readInt() != 0)
                    bluetoothhiddeviceappconfiguration = (BluetoothHidDeviceAppConfiguration)BluetoothHidDeviceAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhiddeviceappconfiguration = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onAppStatusChanged(bluetoothdevice, bluetoothhiddeviceappconfiguration, flag);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
                BluetoothDevice bluetoothdevice1;
                if(parcel.readInt() != 0)
                    bluetoothdevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice1 = null;
                onConnectionStateChanged(bluetoothdevice1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
                BluetoothDevice bluetoothdevice2;
                if(parcel.readInt() != 0)
                    bluetoothdevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice2 = null;
                onGetReport(bluetoothdevice2, parcel.readByte(), parcel.readByte(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
                BluetoothDevice bluetoothdevice3;
                if(parcel.readInt() != 0)
                    bluetoothdevice3 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice3 = null;
                onSetReport(bluetoothdevice3, parcel.readByte(), parcel.readByte(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
                BluetoothDevice bluetoothdevice4;
                if(parcel.readInt() != 0)
                    bluetoothdevice4 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice4 = null;
                onSetProtocol(bluetoothdevice4, parcel.readByte());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
                BluetoothDevice bluetoothdevice5;
                if(parcel.readInt() != 0)
                    bluetoothdevice5 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice5 = null;
                onIntrData(bluetoothdevice5, parcel.readByte(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onVirtualCableUnplug(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHidDeviceCallback";
        static final int TRANSACTION_onAppStatusChanged = 1;
        static final int TRANSACTION_onConnectionStateChanged = 2;
        static final int TRANSACTION_onGetReport = 3;
        static final int TRANSACTION_onIntrData = 6;
        static final int TRANSACTION_onSetProtocol = 5;
        static final int TRANSACTION_onSetReport = 4;
        static final int TRANSACTION_onVirtualCableUnplug = 7;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothHidDeviceCallback");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothHidDeviceCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothHidDeviceCallback";
        }

        public void onAppStatusChanged(BluetoothDevice bluetoothdevice, BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L3:
            if(bluetoothhiddeviceappconfiguration == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            bluetoothhiddeviceappconfiguration.writeToParcel(parcel, 0);
_L4:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
            parcel.writeInt(0);
              goto _L4
        }

        public void onConnectionStateChanged(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void onGetReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_85;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeByte(byte0);
            parcel.writeByte(byte1);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void onIntrData(BluetoothDevice bluetoothdevice, byte byte0, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeByte(byte0);
            parcel.writeByteArray(abyte0);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void onSetProtocol(BluetoothDevice bluetoothdevice, byte byte0)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeByte(byte0);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void onSetReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_85;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeByte(byte0);
            parcel.writeByte(byte1);
            parcel.writeByteArray(abyte0);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void onVirtualCableUnplug(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAppStatusChanged(BluetoothDevice bluetoothdevice, BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration, boolean flag)
        throws RemoteException;

    public abstract void onConnectionStateChanged(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;

    public abstract void onGetReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, int i)
        throws RemoteException;

    public abstract void onIntrData(BluetoothDevice bluetoothdevice, byte byte0, byte abyte0[])
        throws RemoteException;

    public abstract void onSetProtocol(BluetoothDevice bluetoothdevice, byte byte0)
        throws RemoteException;

    public abstract void onSetReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, byte abyte0[])
        throws RemoteException;

    public abstract void onVirtualCableUnplug(BluetoothDevice bluetoothdevice)
        throws RemoteException;
}
