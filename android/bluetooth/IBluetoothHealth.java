// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice, BluetoothHealthAppConfiguration, IBluetoothHealthCallback

public interface IBluetoothHealth
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothHealth
    {

        public static IBluetoothHealth asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothHealth");
            if(iinterface != null && (iinterface instanceof IBluetoothHealth))
                return (IBluetoothHealth)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothHealth");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                BluetoothHealthAppConfiguration bluetoothhealthappconfiguration;
                boolean flag;
                if(parcel.readInt() != 0)
                    bluetoothhealthappconfiguration = (BluetoothHealthAppConfiguration)BluetoothHealthAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhealthappconfiguration = null;
                flag = registerAppConfiguration(bluetoothhealthappconfiguration, IBluetoothHealthCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothHealthAppConfiguration)BluetoothHealthAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = unregisterAppConfiguration(parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                BluetoothDevice bluetoothdevice;
                boolean flag2;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothHealthAppConfiguration)BluetoothHealthAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag2 = connectChannelToSource(bluetoothdevice, parcel);
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                BluetoothDevice bluetoothdevice1;
                boolean flag3;
                BluetoothHealthAppConfiguration bluetoothhealthappconfiguration1;
                if(parcel.readInt() != 0)
                    bluetoothdevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice1 = null;
                if(parcel.readInt() != 0)
                    bluetoothhealthappconfiguration1 = (BluetoothHealthAppConfiguration)BluetoothHealthAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhealthappconfiguration1 = null;
                flag3 = connectChannelToSink(bluetoothdevice1, bluetoothhealthappconfiguration1, parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                BluetoothDevice bluetoothdevice2;
                boolean flag4;
                BluetoothHealthAppConfiguration bluetoothhealthappconfiguration2;
                if(parcel.readInt() != 0)
                    bluetoothdevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice2 = null;
                if(parcel.readInt() != 0)
                    bluetoothhealthappconfiguration2 = (BluetoothHealthAppConfiguration)BluetoothHealthAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhealthappconfiguration2 = null;
                flag4 = disconnectChannel(bluetoothdevice2, bluetoothhealthappconfiguration2, parcel.readInt());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                BluetoothDevice bluetoothdevice3;
                if(parcel.readInt() != 0)
                    bluetoothdevice3 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice3 = null;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothHealthAppConfiguration)BluetoothHealthAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getMainChannelFd(bluetoothdevice3, parcel);
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
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                parcel = getConnectedHealthDevices();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                parcel = getHealthDevicesMatchingConnectionStates(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealth");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            i = getHealthDeviceConnectionState(parcel);
            parcel1.writeNoException();
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHealth";
        static final int TRANSACTION_connectChannelToSink = 4;
        static final int TRANSACTION_connectChannelToSource = 3;
        static final int TRANSACTION_disconnectChannel = 5;
        static final int TRANSACTION_getConnectedHealthDevices = 7;
        static final int TRANSACTION_getHealthDeviceConnectionState = 9;
        static final int TRANSACTION_getHealthDevicesMatchingConnectionStates = 8;
        static final int TRANSACTION_getMainChannelFd = 6;
        static final int TRANSACTION_registerAppConfiguration = 1;
        static final int TRANSACTION_unregisterAppConfiguration = 2;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothHealth");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothHealth
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean connectChannelToSink(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L3:
            if(bluetoothhealthappconfiguration == null)
                break MISSING_BLOCK_LABEL_126;
            parcel.writeInt(1);
            bluetoothhealthappconfiguration.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
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

        public boolean connectChannelToSource(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L3:
            if(bluetoothhealthappconfiguration == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            bluetoothhealthappconfiguration.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
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

        public boolean disconnectChannel(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L3:
            if(bluetoothhealthappconfiguration == null)
                break MISSING_BLOCK_LABEL_126;
            parcel.writeInt(1);
            bluetoothhealthappconfiguration.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
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

        public List getConnectedHealthDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(BluetoothDevice.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getHealthDeviceConnectionState(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public List getHealthDevicesMatchingConnectionStates(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            parcel.writeIntArray(ai);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createTypedArrayList(BluetoothDevice.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothHealth";
        }

        public ParcelFileDescriptor getMainChannelFd(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L5:
            if(bluetoothhealthappconfiguration == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bluetoothhealthappconfiguration.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_127;
            bluetoothdevice = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return bluetoothdevice;
_L2:
            parcel.writeInt(0);
              goto _L5
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
_L4:
            parcel.writeInt(0);
              goto _L6
            bluetoothdevice = null;
              goto _L7
        }

        public boolean registerAppConfiguration(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, IBluetoothHealthCallback ibluetoothhealthcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            if(bluetoothhealthappconfiguration == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothhealthappconfiguration.writeToParcel(parcel, 0);
_L4:
            bluetoothhealthappconfiguration = obj;
            if(ibluetoothhealthcallback == null)
                break MISSING_BLOCK_LABEL_49;
            bluetoothhealthappconfiguration = ibluetoothhealthcallback.asBinder();
            int i;
            parcel.writeStrongBinder(bluetoothhealthappconfiguration);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            bluetoothhealthappconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothhealthappconfiguration;
        }

        public boolean unregisterAppConfiguration(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealth");
            if(bluetoothhealthappconfiguration == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            bluetoothhealthappconfiguration.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            bluetoothhealthappconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothhealthappconfiguration;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean connectChannelToSink(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
        throws RemoteException;

    public abstract boolean connectChannelToSource(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
        throws RemoteException;

    public abstract boolean disconnectChannel(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
        throws RemoteException;

    public abstract List getConnectedHealthDevices()
        throws RemoteException;

    public abstract int getHealthDeviceConnectionState(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract List getHealthDevicesMatchingConnectionStates(int ai[])
        throws RemoteException;

    public abstract ParcelFileDescriptor getMainChannelFd(BluetoothDevice bluetoothdevice, BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
        throws RemoteException;

    public abstract boolean registerAppConfiguration(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, IBluetoothHealthCallback ibluetoothhealthcallback)
        throws RemoteException;

    public abstract boolean unregisterAppConfiguration(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration)
        throws RemoteException;
}
