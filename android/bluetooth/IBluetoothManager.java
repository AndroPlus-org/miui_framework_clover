// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;

// Referenced classes of package android.bluetooth:
//            IBluetoothProfileServiceConnection, IBluetoothGatt, IBluetoothManagerCallback, IBluetooth, 
//            IBluetoothStateChangeCallback

public interface IBluetoothManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothManager
    {

        public static IBluetoothManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothManager");
            if(iinterface != null && (iinterface instanceof IBluetoothManager))
                return (IBluetoothManager)iinterface;
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
            boolean flag8;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.bluetooth.IBluetoothManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                parcel = registerAdapter(IBluetoothManagerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                unregisterAdapter(IBluetoothManagerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                registerStateChangeCallback(IBluetoothStateChangeCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                unregisterStateChangeCallback(IBluetoothStateChangeCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                boolean flag = isEnabled();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                boolean flag1 = enable(parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                boolean flag2 = enableNoAutoConnect(parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                String s = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                flag3 = disable(s, flag3);
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                i = getState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                parcel = getBluetoothGatt();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                boolean flag4 = bindBluetoothProfileService(parcel.readInt(), IBluetoothProfileServiceConnection.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                unbindBluetoothProfileService(parcel.readInt(), IBluetoothProfileServiceConnection.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                parcel = getAddress();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                parcel = getName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                boolean flag5 = factoryReset();
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                boolean flag6 = isBleScanAlwaysAvailable();
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                IBinder ibinder = parcel.readStrongBinder();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                i = updateBleAppCount(ibinder, flag7, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.bluetooth.IBluetoothManager");
                flag8 = isBleAppPresent();
                parcel1.writeNoException();
                break;
            }
            if(flag8)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothManager";
        static final int TRANSACTION_bindBluetoothProfileService = 11;
        static final int TRANSACTION_disable = 8;
        static final int TRANSACTION_enable = 6;
        static final int TRANSACTION_enableNoAutoConnect = 7;
        static final int TRANSACTION_factoryReset = 15;
        static final int TRANSACTION_getAddress = 13;
        static final int TRANSACTION_getBluetoothGatt = 10;
        static final int TRANSACTION_getName = 14;
        static final int TRANSACTION_getState = 9;
        static final int TRANSACTION_isBleAppPresent = 18;
        static final int TRANSACTION_isBleScanAlwaysAvailable = 16;
        static final int TRANSACTION_isEnabled = 5;
        static final int TRANSACTION_registerAdapter = 1;
        static final int TRANSACTION_registerStateChangeCallback = 3;
        static final int TRANSACTION_unbindBluetoothProfileService = 12;
        static final int TRANSACTION_unregisterAdapter = 2;
        static final int TRANSACTION_unregisterStateChangeCallback = 4;
        static final int TRANSACTION_updateBleAppCount = 17;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothManager");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean bindBluetoothProfileService(int i, IBluetoothProfileServiceConnection ibluetoothprofileserviceconnection)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            parcel.writeInt(i);
            if(ibluetoothprofileserviceconnection == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = ibluetoothprofileserviceconnection.asBinder();
            parcel.writeStrongBinder(ibinder);
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
            ibluetoothprofileserviceconnection;
            parcel1.recycle();
            parcel.recycle();
            throw ibluetoothprofileserviceconnection;
        }

        public boolean disable(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean enable(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean enableNoAutoConnect(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean factoryReset()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            mRemote.transact(15, parcel, parcel1, 0);
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

        public String getAddress()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBluetoothGatt getBluetoothGatt()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IBluetoothGatt ibluetoothgatt;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            ibluetoothgatt = IBluetoothGatt.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ibluetoothgatt;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothManager";
        }

        public String getName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isBleAppPresent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public boolean isBleScanAlwaysAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            mRemote.transact(16, parcel, parcel1, 0);
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

        public boolean isEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBluetooth registerAdapter(IBluetoothManagerCallback ibluetoothmanagercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            if(ibluetoothmanagercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibluetoothmanagercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            ibluetoothmanagercallback = IBluetooth.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ibluetoothmanagercallback;
            ibluetoothmanagercallback;
            parcel1.recycle();
            parcel.recycle();
            throw ibluetoothmanagercallback;
        }

        public void registerStateChangeCallback(IBluetoothStateChangeCallback ibluetoothstatechangecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            if(ibluetoothstatechangecallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibluetoothstatechangecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibluetoothstatechangecallback;
            parcel1.recycle();
            parcel.recycle();
            throw ibluetoothstatechangecallback;
        }

        public void unbindBluetoothProfileService(int i, IBluetoothProfileServiceConnection ibluetoothprofileserviceconnection)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            parcel.writeInt(i);
            if(ibluetoothprofileserviceconnection == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = ibluetoothprofileserviceconnection.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibluetoothprofileserviceconnection;
            parcel1.recycle();
            parcel.recycle();
            throw ibluetoothprofileserviceconnection;
        }

        public void unregisterAdapter(IBluetoothManagerCallback ibluetoothmanagercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            if(ibluetoothmanagercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibluetoothmanagercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibluetoothmanagercallback;
            parcel1.recycle();
            parcel.recycle();
            throw ibluetoothmanagercallback;
        }

        public void unregisterStateChangeCallback(IBluetoothStateChangeCallback ibluetoothstatechangecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            if(ibluetoothstatechangecallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibluetoothstatechangecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibluetoothstatechangecallback;
            parcel1.recycle();
            parcel.recycle();
            throw ibluetoothstatechangecallback;
        }

        public int updateBleAppCount(IBinder ibinder, boolean flag, String s)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean bindBluetoothProfileService(int i, IBluetoothProfileServiceConnection ibluetoothprofileserviceconnection)
        throws RemoteException;

    public abstract boolean disable(String s, boolean flag)
        throws RemoteException;

    public abstract boolean enable(String s)
        throws RemoteException;

    public abstract boolean enableNoAutoConnect(String s)
        throws RemoteException;

    public abstract boolean factoryReset()
        throws RemoteException;

    public abstract String getAddress()
        throws RemoteException;

    public abstract IBluetoothGatt getBluetoothGatt()
        throws RemoteException;

    public abstract String getName()
        throws RemoteException;

    public abstract int getState()
        throws RemoteException;

    public abstract boolean isBleAppPresent()
        throws RemoteException;

    public abstract boolean isBleScanAlwaysAvailable()
        throws RemoteException;

    public abstract boolean isEnabled()
        throws RemoteException;

    public abstract IBluetooth registerAdapter(IBluetoothManagerCallback ibluetoothmanagercallback)
        throws RemoteException;

    public abstract void registerStateChangeCallback(IBluetoothStateChangeCallback ibluetoothstatechangecallback)
        throws RemoteException;

    public abstract void unbindBluetoothProfileService(int i, IBluetoothProfileServiceConnection ibluetoothprofileserviceconnection)
        throws RemoteException;

    public abstract void unregisterAdapter(IBluetoothManagerCallback ibluetoothmanagercallback)
        throws RemoteException;

    public abstract void unregisterStateChangeCallback(IBluetoothStateChangeCallback ibluetoothstatechangecallback)
        throws RemoteException;

    public abstract int updateBleAppCount(IBinder ibinder, boolean flag, String s)
        throws RemoteException;
}
