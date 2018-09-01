// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice, BluetoothAvrcpPlayerSettings

public interface IBluetoothAvrcpController
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothAvrcpController
    {

        public static IBluetoothAvrcpController asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothAvrcpController");
            if(iinterface != null && (iinterface instanceof IBluetoothAvrcpController))
                return (IBluetoothAvrcpController)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothAvrcpController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
                parcel = getConnectedDevices();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
                parcel = getDevicesMatchingConnectionStates(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getConnectionState(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPlayerSettings(parcel);
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

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothAvrcpPlayerSettings)BluetoothAvrcpPlayerSettings.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = setPlayerApplicationSetting(parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
                break;
            }
            BluetoothDevice bluetoothdevice;
            if(parcel.readInt() != 0)
                bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
            else
                bluetoothdevice = null;
            sendGroupNavigationCmd(bluetoothdevice, parcel.readInt(), parcel.readInt());
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothAvrcpController";
        static final int TRANSACTION_getConnectedDevices = 1;
        static final int TRANSACTION_getConnectionState = 3;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 2;
        static final int TRANSACTION_getPlayerSettings = 4;
        static final int TRANSACTION_sendGroupNavigationCmd = 6;
        static final int TRANSACTION_setPlayerApplicationSetting = 5;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothAvrcpController");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothAvrcpController
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public List getConnectedDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public int getConnectionState(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(3, parcel, parcel1, 0);
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

        public List getDevicesMatchingConnectionStates(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
            parcel.writeIntArray(ai);
            mRemote.transact(2, parcel, parcel1, 0);
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
            return "android.bluetooth.IBluetoothAvrcpController";
        }

        public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_96;
            bluetoothdevice = (BluetoothAvrcpPlayerSettings)BluetoothAvrcpPlayerSettings.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bluetoothdevice;
_L2:
            parcel.writeInt(0);
              goto _L3
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
            bluetoothdevice = null;
              goto _L4
        }

        public void sendGroupNavigationCmd(BluetoothDevice bluetoothdevice, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings bluetoothavrcpplayersettings)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
            if(bluetoothavrcpplayersettings == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            bluetoothavrcpplayersettings.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            bluetoothavrcpplayersettings;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothavrcpplayersettings;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract List getConnectedDevices()
        throws RemoteException;

    public abstract int getConnectionState(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract List getDevicesMatchingConnectionStates(int ai[])
        throws RemoteException;

    public abstract BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract void sendGroupNavigationCmd(BluetoothDevice bluetoothdevice, int i, int j)
        throws RemoteException;

    public abstract boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings bluetoothavrcpplayersettings)
        throws RemoteException;
}
