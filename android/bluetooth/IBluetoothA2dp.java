// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice, BluetoothCodecStatus, BluetoothCodecConfig

public interface IBluetoothA2dp
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothA2dp
    {

        public static IBluetoothA2dp asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothA2dp");
            if(iinterface != null && (iinterface instanceof IBluetoothA2dp))
                return (IBluetoothA2dp)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothA2dp");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = connect(parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = disconnect(parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                parcel = getConnectedDevices();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                parcel = getDevicesMatchingConnectionStates(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getConnectionState(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                boolean flag2;
                BluetoothDevice bluetoothdevice;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                flag2 = setPriority(bluetoothdevice, parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getPriority(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                boolean flag3 = isAvrcpAbsoluteVolumeSupported();
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                adjustAvrcpAbsoluteVolume(parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                setAvrcpAbsoluteVolume(parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                boolean flag4;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag4 = isA2dpPlaying(parcel);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                parcel = getCodecStatus();
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

            case 13: // '\r'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothCodecConfig)BluetoothCodecConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setCodecConfigPreference(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                enableOptionalCodecs();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                disableOptionalCodecs();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = supportsOptionalCodecs(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getOptionalCodecsEnabled(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                if(parcel.readInt() != 0)
                    parcel1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setOptionalCodecsEnabled(parcel1, parcel.readInt());
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.bluetooth.IBluetoothA2dp");
                break;
            }
            boolean flag5;
            if(parcel.readInt() != 0)
                parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            flag5 = selectStream(parcel);
            parcel1.writeNoException();
            if(flag5)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothA2dp";
        static final int TRANSACTION_adjustAvrcpAbsoluteVolume = 9;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disableOptionalCodecs = 15;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_enableOptionalCodecs = 14;
        static final int TRANSACTION_getCodecStatus = 12;
        static final int TRANSACTION_getConnectedDevices = 3;
        static final int TRANSACTION_getConnectionState = 5;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
        static final int TRANSACTION_getOptionalCodecsEnabled = 17;
        static final int TRANSACTION_getPriority = 7;
        static final int TRANSACTION_isA2dpPlaying = 11;
        static final int TRANSACTION_isAvrcpAbsoluteVolumeSupported = 8;
        static final int TRANSACTION_selectStream = 19;
        static final int TRANSACTION_setAvrcpAbsoluteVolume = 10;
        static final int TRANSACTION_setCodecConfigPreference = 13;
        static final int TRANSACTION_setOptionalCodecsEnabled = 18;
        static final int TRANSACTION_setPriority = 6;
        static final int TRANSACTION_supportsOptionalCodecs = 16;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothA2dp");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothA2dp
    {

        public void adjustAvrcpAbsoluteVolume(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void disableOptionalCodecs()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public boolean disconnect(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void enableOptionalCodecs()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public BluetoothCodecStatus getCodecStatus()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            BluetoothCodecStatus bluetoothcodecstatus = (BluetoothCodecStatus)BluetoothCodecStatus.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bluetoothcodecstatus;
_L2:
            bluetoothcodecstatus = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getConnectedDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            mRemote.transact(3, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(5, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            parcel.writeIntArray(ai);
            mRemote.transact(4, parcel, parcel1, 0);
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
            return "android.bluetooth.IBluetoothA2dp";
        }

        public int getOptionalCodecsEnabled(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(17, parcel, parcel1, 0);
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

        public int getPriority(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(7, parcel, parcel1, 0);
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

        public boolean isA2dpPlaying(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(11, parcel, parcel1, 0);
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

        public boolean isAvrcpAbsoluteVolumeSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean selectStream(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(19, parcel, parcel1, 0);
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

        public void setAvrcpAbsoluteVolume(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setCodecConfigPreference(BluetoothCodecConfig bluetoothcodecconfig)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothcodecconfig == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bluetoothcodecconfig.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothcodecconfig;
            parcel.recycle();
            throw bluetoothcodecconfig;
        }

        public void setOptionalCodecsEnabled(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
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

        public int supportsOptionalCodecs(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(16, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void adjustAvrcpAbsoluteVolume(int i)
        throws RemoteException;

    public abstract boolean connect(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract void disableOptionalCodecs()
        throws RemoteException;

    public abstract boolean disconnect(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract void enableOptionalCodecs()
        throws RemoteException;

    public abstract BluetoothCodecStatus getCodecStatus()
        throws RemoteException;

    public abstract List getConnectedDevices()
        throws RemoteException;

    public abstract int getConnectionState(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract List getDevicesMatchingConnectionStates(int ai[])
        throws RemoteException;

    public abstract int getOptionalCodecsEnabled(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getPriority(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean isA2dpPlaying(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean isAvrcpAbsoluteVolumeSupported()
        throws RemoteException;

    public abstract boolean selectStream(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract void setAvrcpAbsoluteVolume(int i)
        throws RemoteException;

    public abstract void setCodecConfigPreference(BluetoothCodecConfig bluetoothcodecconfig)
        throws RemoteException;

    public abstract void setOptionalCodecsEnabled(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;

    public abstract boolean setPriority(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;

    public abstract int supportsOptionalCodecs(BluetoothDevice bluetoothdevice)
        throws RemoteException;
}
