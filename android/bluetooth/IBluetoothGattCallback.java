// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothGattService

public interface IBluetoothGattCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothGattCallback
    {

        public static IBluetoothGattCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothGattCallback");
            if(iinterface != null && (iinterface instanceof IBluetoothGattCallback))
                return (IBluetoothGattCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothGattCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onClientRegistered(parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                j = parcel.readInt();
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onClientConnectionState(j, i, flag, parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onPhyUpdate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onPhyRead(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onSearchComplete(parcel.readString(), parcel.createTypedArrayList(BluetoothGattService.CREATOR), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onCharacteristicRead(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onCharacteristicWrite(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onExecuteWrite(parcel.readString(), parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onDescriptorRead(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onDescriptorWrite(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onNotify(parcel.readString(), parcel.readInt(), parcel.createByteArray());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onReadRemoteRssi(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onConfigureMTU(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.bluetooth.IBluetoothGattCallback");
                onConnectionUpdated(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGattCallback";
        static final int TRANSACTION_onCharacteristicRead = 6;
        static final int TRANSACTION_onCharacteristicWrite = 7;
        static final int TRANSACTION_onClientConnectionState = 2;
        static final int TRANSACTION_onClientRegistered = 1;
        static final int TRANSACTION_onConfigureMTU = 13;
        static final int TRANSACTION_onConnectionUpdated = 14;
        static final int TRANSACTION_onDescriptorRead = 9;
        static final int TRANSACTION_onDescriptorWrite = 10;
        static final int TRANSACTION_onExecuteWrite = 8;
        static final int TRANSACTION_onNotify = 11;
        static final int TRANSACTION_onPhyRead = 4;
        static final int TRANSACTION_onPhyUpdate = 3;
        static final int TRANSACTION_onReadRemoteRssi = 12;
        static final int TRANSACTION_onSearchComplete = 5;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothGattCallback");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothGattCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothGattCallback";
        }

        public void onCharacteristicRead(String s, int i, int j, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onCharacteristicWrite(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onClientConnectionState(int i, int j, boolean flag, String s)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
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

        public void onClientRegistered(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
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

        public void onConfigureMTU(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(13, parcel, null, 1);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onDescriptorRead(String s, int i, int j, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onDescriptorWrite(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onExecuteWrite(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onNotify(String s, int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(11, parcel, null, 1);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(4, parcel, null, 1);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onReadRemoteRssi(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onSearchComplete(String s, List list, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
            parcel.writeString(s);
            parcel.writeTypedList(list);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onCharacteristicRead(String s, int i, int j, byte abyte0[])
        throws RemoteException;

    public abstract void onCharacteristicWrite(String s, int i, int j)
        throws RemoteException;

    public abstract void onClientConnectionState(int i, int j, boolean flag, String s)
        throws RemoteException;

    public abstract void onClientRegistered(int i, int j)
        throws RemoteException;

    public abstract void onConfigureMTU(String s, int i, int j)
        throws RemoteException;

    public abstract void onConnectionUpdated(String s, int i, int j, int k, int l)
        throws RemoteException;

    public abstract void onDescriptorRead(String s, int i, int j, byte abyte0[])
        throws RemoteException;

    public abstract void onDescriptorWrite(String s, int i, int j)
        throws RemoteException;

    public abstract void onExecuteWrite(String s, int i)
        throws RemoteException;

    public abstract void onNotify(String s, int i, byte abyte0[])
        throws RemoteException;

    public abstract void onPhyRead(String s, int i, int j, int k)
        throws RemoteException;

    public abstract void onPhyUpdate(String s, int i, int j, int k)
        throws RemoteException;

    public abstract void onReadRemoteRssi(String s, int i, int j)
        throws RemoteException;

    public abstract void onSearchComplete(String s, List list, int i)
        throws RemoteException;
}
