// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;

public interface IBluetoothStateChangeCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothStateChangeCallback
    {

        public static IBluetoothStateChangeCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothStateChangeCallback");
            if(iinterface != null && (iinterface instanceof IBluetoothStateChangeCallback))
                return (IBluetoothStateChangeCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothStateChangeCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothStateChangeCallback");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onBluetoothStateChange(flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothStateChangeCallback";
        static final int TRANSACTION_onBluetoothStateChange = 1;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothStateChangeCallback");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothStateChangeCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothStateChangeCallback";
        }

        public void onBluetoothStateChange(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothStateChangeCallback");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
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


    public abstract void onBluetoothStateChange(boolean flag)
        throws RemoteException;
}
