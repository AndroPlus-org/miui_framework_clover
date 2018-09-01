// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice, BluetoothHidDeviceAppConfiguration, BluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings, 
//            IBluetoothHidDeviceCallback

public interface IBluetoothInputHost
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothInputHost
    {

        public static IBluetoothInputHost asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothInputHost");
            if(iinterface != null && (iinterface instanceof IBluetoothInputHost))
                return (IBluetoothInputHost)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothInputHost");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration;
                BluetoothHidDeviceAppSdpSettings bluetoothhiddeviceappsdpsettings;
                BluetoothHidDeviceAppQosSettings bluetoothhiddeviceappqossettings;
                BluetoothHidDeviceAppQosSettings bluetoothhiddeviceappqossettings1;
                boolean flag;
                if(parcel.readInt() != 0)
                    bluetoothhiddeviceappconfiguration = (BluetoothHidDeviceAppConfiguration)BluetoothHidDeviceAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhiddeviceappconfiguration = null;
                if(parcel.readInt() != 0)
                    bluetoothhiddeviceappsdpsettings = (BluetoothHidDeviceAppSdpSettings)BluetoothHidDeviceAppSdpSettings.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhiddeviceappsdpsettings = null;
                if(parcel.readInt() != 0)
                    bluetoothhiddeviceappqossettings = (BluetoothHidDeviceAppQosSettings)BluetoothHidDeviceAppQosSettings.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhiddeviceappqossettings = null;
                if(parcel.readInt() != 0)
                    bluetoothhiddeviceappqossettings1 = (BluetoothHidDeviceAppQosSettings)BluetoothHidDeviceAppQosSettings.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhiddeviceappqossettings1 = null;
                flag = registerApp(bluetoothhiddeviceappconfiguration, bluetoothhiddeviceappsdpsettings, bluetoothhiddeviceappqossettings, bluetoothhiddeviceappqossettings1, IBluetoothHidDeviceCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothHidDeviceAppConfiguration)BluetoothHidDeviceAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = unregisterApp(parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                BluetoothDevice bluetoothdevice;
                boolean flag2;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                flag2 = sendReport(bluetoothdevice, parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                BluetoothDevice bluetoothdevice1;
                boolean flag3;
                if(parcel.readInt() != 0)
                    bluetoothdevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice1 = null;
                flag3 = replyReport(bluetoothdevice1, parcel.readByte(), parcel.readByte(), parcel.createByteArray());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                BluetoothDevice bluetoothdevice2;
                boolean flag4;
                if(parcel.readInt() != 0)
                    bluetoothdevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice2 = null;
                flag4 = reportError(bluetoothdevice2, parcel.readByte());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                boolean flag5;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag5 = unplug(parcel);
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                boolean flag6;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag6 = connect(parcel);
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                boolean flag7;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag7 = disconnect(parcel);
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                parcel = getConnectedDevices();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                parcel = getDevicesMatchingConnectionStates(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetoothInputHost");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            i = getConnectionState(parcel);
            parcel1.writeNoException();
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothInputHost";
        static final int TRANSACTION_connect = 7;
        static final int TRANSACTION_disconnect = 8;
        static final int TRANSACTION_getConnectedDevices = 9;
        static final int TRANSACTION_getConnectionState = 11;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 10;
        static final int TRANSACTION_registerApp = 1;
        static final int TRANSACTION_replyReport = 4;
        static final int TRANSACTION_reportError = 5;
        static final int TRANSACTION_sendReport = 3;
        static final int TRANSACTION_unplug = 6;
        static final int TRANSACTION_unregisterApp = 2;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothInputHost");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothInputHost
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean connect(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(7, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean disconnect(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(8, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public List getConnectedDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            mRemote.transact(9, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(11, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            parcel.writeIntArray(ai);
            mRemote.transact(10, parcel, parcel1, 0);
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
            return "android.bluetooth.IBluetoothInputHost";
        }

        public boolean registerApp(BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration, BluetoothHidDeviceAppSdpSettings bluetoothhiddeviceappsdpsettings, BluetoothHidDeviceAppQosSettings bluetoothhiddeviceappqossettings, BluetoothHidDeviceAppQosSettings bluetoothhiddeviceappqossettings1, IBluetoothHidDeviceCallback ibluetoothhiddevicecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothhiddeviceappconfiguration == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothhiddeviceappconfiguration.writeToParcel(parcel, 0);
_L7:
            if(bluetoothhiddeviceappsdpsettings == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bluetoothhiddeviceappsdpsettings.writeToParcel(parcel, 0);
_L8:
            if(bluetoothhiddeviceappqossettings == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            bluetoothhiddeviceappqossettings.writeToParcel(parcel, 0);
_L9:
            if(bluetoothhiddeviceappqossettings1 == null)
                break MISSING_BLOCK_LABEL_201;
            parcel.writeInt(1);
            bluetoothhiddeviceappqossettings1.writeToParcel(parcel, 0);
_L10:
            bluetoothhiddeviceappconfiguration = obj;
            if(ibluetoothhiddevicecallback == null)
                break MISSING_BLOCK_LABEL_106;
            bluetoothhiddeviceappconfiguration = ibluetoothhiddevicecallback.asBinder();
            int i;
            parcel.writeStrongBinder(bluetoothhiddeviceappconfiguration);
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
              goto _L7
            bluetoothhiddeviceappconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothhiddeviceappconfiguration;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public boolean replyReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_102;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeByte(byte0);
            parcel.writeByte(byte1);
            parcel.writeByteArray(abyte0);
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean reportError(BluetoothDevice bluetoothdevice, byte byte0)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_82;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeByte(byte0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean sendReport(BluetoothDevice bluetoothdevice, int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean unplug(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(6, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean unregisterApp(BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothInputHost");
            if(bluetoothhiddeviceappconfiguration == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            bluetoothhiddeviceappconfiguration.writeToParcel(parcel, 0);
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
            bluetoothhiddeviceappconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothhiddeviceappconfiguration;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean connect(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean disconnect(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract List getConnectedDevices()
        throws RemoteException;

    public abstract int getConnectionState(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract List getDevicesMatchingConnectionStates(int ai[])
        throws RemoteException;

    public abstract boolean registerApp(BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration, BluetoothHidDeviceAppSdpSettings bluetoothhiddeviceappsdpsettings, BluetoothHidDeviceAppQosSettings bluetoothhiddeviceappqossettings, BluetoothHidDeviceAppQosSettings bluetoothhiddeviceappqossettings1, IBluetoothHidDeviceCallback ibluetoothhiddevicecallback)
        throws RemoteException;

    public abstract boolean replyReport(BluetoothDevice bluetoothdevice, byte byte0, byte byte1, byte abyte0[])
        throws RemoteException;

    public abstract boolean reportError(BluetoothDevice bluetoothdevice, byte byte0)
        throws RemoteException;

    public abstract boolean sendReport(BluetoothDevice bluetoothdevice, int i, byte abyte0[])
        throws RemoteException;

    public abstract boolean unplug(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean unregisterApp(BluetoothHidDeviceAppConfiguration bluetoothhiddeviceappconfiguration)
        throws RemoteException;
}
