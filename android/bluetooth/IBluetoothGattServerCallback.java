// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;

// Referenced classes of package android.bluetooth:
//            BluetoothGattService

public interface IBluetoothGattServerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothGattServerCallback
    {

        public static IBluetoothGattServerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothGattServerCallback");
            if(iinterface != null && (iinterface instanceof IBluetoothGattServerCallback))
                return (IBluetoothGattServerCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothGattServerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                onServerRegistered(parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                i = parcel.readInt();
                j = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onServerConnectionState(i, j, flag, parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (BluetoothGattService)BluetoothGattService.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onServiceAdded(i, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onCharacteristicReadRequest(parcel1, i, j, flag1, parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                onDescriptorReadRequest(parcel1, i, j, flag2, parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                parcel1 = parcel.readString();
                j = parcel.readInt();
                int k = parcel.readInt();
                i = parcel.readInt();
                boolean flag3;
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                onCharacteristicWriteRequest(parcel1, j, k, i, flag3, flag6, parcel.readInt(), parcel.createByteArray());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                parcel1 = parcel.readString();
                j = parcel.readInt();
                int l = parcel.readInt();
                i = parcel.readInt();
                boolean flag4;
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                onDescriptorWriteRequest(parcel1, j, l, i, flag4, flag7, parcel.readInt(), parcel.createByteArray());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                onExecuteWrite(parcel1, i, flag5);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                onNotificationSent(parcel.readString(), parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                onMtuChanged(parcel.readString(), parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                onPhyUpdate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                onPhyRead(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
                onConnectionUpdated(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGattServerCallback";
        static final int TRANSACTION_onCharacteristicReadRequest = 4;
        static final int TRANSACTION_onCharacteristicWriteRequest = 6;
        static final int TRANSACTION_onConnectionUpdated = 13;
        static final int TRANSACTION_onDescriptorReadRequest = 5;
        static final int TRANSACTION_onDescriptorWriteRequest = 7;
        static final int TRANSACTION_onExecuteWrite = 8;
        static final int TRANSACTION_onMtuChanged = 10;
        static final int TRANSACTION_onNotificationSent = 9;
        static final int TRANSACTION_onPhyRead = 12;
        static final int TRANSACTION_onPhyUpdate = 11;
        static final int TRANSACTION_onServerConnectionState = 2;
        static final int TRANSACTION_onServerRegistered = 1;
        static final int TRANSACTION_onServiceAdded = 3;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothGattServerCallback");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothGattServerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothGattServerCallback";
        }

        public void onCharacteristicReadRequest(String s, int i, int j, boolean flag, int k)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(k);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onCharacteristicWriteRequest(String s, int i, int j, int k, boolean flag, boolean flag1, int l, 
                byte abyte0[])
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(l);
            parcel.writeByteArray(abyte0);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onConnectionUpdated(String s, int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onDescriptorReadRequest(String s, int i, int j, boolean flag, int k)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(k);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onDescriptorWriteRequest(String s, int i, int j, int k, boolean flag, boolean flag1, int l, 
                byte abyte0[])
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(l);
            parcel.writeByteArray(abyte0);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onExecuteWrite(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onMtuChanged(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onNotificationSent(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onPhyRead(String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onPhyUpdate(String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onServerConnectionState(int i, int j, boolean flag, String s)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onServerRegistered(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onServiceAdded(int i, BluetoothGattService bluetoothgattservice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
            parcel.writeInt(i);
            if(bluetoothgattservice == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            bluetoothgattservice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothgattservice;
            parcel.recycle();
            throw bluetoothgattservice;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onCharacteristicReadRequest(String s, int i, int j, boolean flag, int k)
        throws RemoteException;

    public abstract void onCharacteristicWriteRequest(String s, int i, int j, int k, boolean flag, boolean flag1, int l, 
            byte abyte0[])
        throws RemoteException;

    public abstract void onConnectionUpdated(String s, int i, int j, int k, int l)
        throws RemoteException;

    public abstract void onDescriptorReadRequest(String s, int i, int j, boolean flag, int k)
        throws RemoteException;

    public abstract void onDescriptorWriteRequest(String s, int i, int j, int k, boolean flag, boolean flag1, int l, 
            byte abyte0[])
        throws RemoteException;

    public abstract void onExecuteWrite(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void onMtuChanged(String s, int i)
        throws RemoteException;

    public abstract void onNotificationSent(String s, int i)
        throws RemoteException;

    public abstract void onPhyRead(String s, int i, int j, int k)
        throws RemoteException;

    public abstract void onPhyUpdate(String s, int i, int j, int k)
        throws RemoteException;

    public abstract void onServerConnectionState(int i, int j, boolean flag, String s)
        throws RemoteException;

    public abstract void onServerRegistered(int i, int j)
        throws RemoteException;

    public abstract void onServiceAdded(int i, BluetoothGattService bluetoothgattservice)
        throws RemoteException;
}
