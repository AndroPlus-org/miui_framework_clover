// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice

public interface IBluetoothMapClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothMapClient
    {

        public static IBluetoothMapClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothMapClient");
            if(iinterface != null && (iinterface instanceof IBluetoothMapClient))
                return (IBluetoothMapClient)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothMapClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
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
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
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
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
                boolean flag2;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag2 = isConnected(parcel);
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
                parcel = getConnectedDevices();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
                parcel = getDevicesMatchingConnectionStates(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getConnectionState(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
                boolean flag3;
                BluetoothDevice bluetoothdevice;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                flag3 = setPriority(bluetoothdevice, parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getPriority(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
                boolean flag4;
                BluetoothDevice bluetoothdevice1;
                Uri auri[];
                String s;
                PendingIntent pendingintent;
                if(parcel.readInt() != 0)
                    bluetoothdevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice1 = null;
                auri = (Uri[])parcel.createTypedArray(Uri.CREATOR);
                s = parcel.readString();
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag4 = sendMessage(bluetoothdevice1, auri, s, pendingintent, parcel);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothMapClient");
                break;
            }
            boolean flag5;
            if(parcel.readInt() != 0)
                parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            flag5 = getUnreadMessages(parcel);
            parcel1.writeNoException();
            if(flag5)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothMapClient";
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_getConnectedDevices = 4;
        static final int TRANSACTION_getConnectionState = 6;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 5;
        static final int TRANSACTION_getPriority = 8;
        static final int TRANSACTION_getUnreadMessages = 10;
        static final int TRANSACTION_isConnected = 3;
        static final int TRANSACTION_sendMessage = 9;
        static final int TRANSACTION_setPriority = 7;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothMapClient");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothMapClient
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
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

        public boolean disconnect(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
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

        public List getConnectedDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
            mRemote.transact(4, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(6, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
            parcel.writeIntArray(ai);
            mRemote.transact(5, parcel, parcel1, 0);
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
            return "android.bluetooth.IBluetoothMapClient";
        }

        public int getPriority(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(8, parcel, parcel1, 0);
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

        public boolean getUnreadMessages(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean isConnected(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean sendMessage(BluetoothDevice bluetoothdevice, Uri auri[], String s, PendingIntent pendingintent, PendingIntent pendingintent1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L5:
            parcel.writeTypedArray(auri, 0);
            parcel.writeString(s);
            if(pendingintent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L6:
            if(pendingintent1 == null)
                break MISSING_BLOCK_LABEL_166;
            parcel.writeInt(1);
            pendingintent1.writeToParcel(parcel, 0);
_L7:
            int i;
            mRemote.transact(9, parcel, parcel1, 0);
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
              goto _L5
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
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

    public abstract int getPriority(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean getUnreadMessages(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean isConnected(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean sendMessage(BluetoothDevice bluetoothdevice, Uri auri[], String s, PendingIntent pendingintent, PendingIntent pendingintent1)
        throws RemoteException;

    public abstract boolean setPriority(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;
}
