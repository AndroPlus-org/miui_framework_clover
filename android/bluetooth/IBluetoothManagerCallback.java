// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;

// Referenced classes of package android.bluetooth:
//            IBluetooth

public interface IBluetoothManagerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothManagerCallback
    {

        public static IBluetoothManagerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothManagerCallback");
            if(iinterface != null && (iinterface instanceof IBluetoothManagerCallback))
                return (IBluetoothManagerCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothManagerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
                onBluetoothServiceUp(IBluetooth.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
                onBluetoothServiceDown();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
                onBrEdrDown();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothManagerCallback";
        static final int TRANSACTION_onBluetoothServiceDown = 2;
        static final int TRANSACTION_onBluetoothServiceUp = 1;
        static final int TRANSACTION_onBrEdrDown = 3;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothManagerCallback");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothManagerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothManagerCallback";
        }

        public void onBluetoothServiceDown()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onBluetoothServiceUp(IBluetooth ibluetooth)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
            if(ibluetooth == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ibluetooth.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ibluetooth;
            parcel.recycle();
            throw ibluetooth;
        }

        public void onBrEdrDown()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onBluetoothServiceDown()
        throws RemoteException;

    public abstract void onBluetoothServiceUp(IBluetooth ibluetooth)
        throws RemoteException;

    public abstract void onBrEdrDown()
        throws RemoteException;
}
