// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.*;

public interface IAdvertisingSetCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAdvertisingSetCallback
    {

        public static IAdvertisingSetCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.le.IAdvertisingSetCallback");
            if(iinterface != null && (iinterface instanceof IAdvertisingSetCallback))
                return (IAdvertisingSetCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.le.IAdvertisingSetCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                onAdvertisingSetStarted(parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                onOwnAddressRead(parcel.readInt(), parcel.readInt(), parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                onAdvertisingSetStopped(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onAdvertisingEnabled(i, flag, parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                onAdvertisingDataSet(parcel.readInt(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                onScanResponseDataSet(parcel.readInt(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                onAdvertisingParametersUpdated(parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                onPeriodicAdvertisingParametersUpdated(parcel.readInt(), parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                onPeriodicAdvertisingDataSet(parcel.readInt(), parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
                i = parcel.readInt();
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            onPeriodicAdvertisingEnabled(i, flag1, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.le.IAdvertisingSetCallback";
        static final int TRANSACTION_onAdvertisingDataSet = 5;
        static final int TRANSACTION_onAdvertisingEnabled = 4;
        static final int TRANSACTION_onAdvertisingParametersUpdated = 7;
        static final int TRANSACTION_onAdvertisingSetStarted = 1;
        static final int TRANSACTION_onAdvertisingSetStopped = 3;
        static final int TRANSACTION_onOwnAddressRead = 2;
        static final int TRANSACTION_onPeriodicAdvertisingDataSet = 9;
        static final int TRANSACTION_onPeriodicAdvertisingEnabled = 10;
        static final int TRANSACTION_onPeriodicAdvertisingParametersUpdated = 8;
        static final int TRANSACTION_onScanResponseDataSet = 6;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.le.IAdvertisingSetCallback");
        }
    }

    private static class Stub.Proxy
        implements IAdvertisingSetCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.le.IAdvertisingSetCallback";
        }

        public void onAdvertisingDataSet(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onAdvertisingEnabled(int i, boolean flag, int j)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onAdvertisingParametersUpdated(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onAdvertisingSetStarted(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onAdvertisingSetStopped(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onOwnAddressRead(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onPeriodicAdvertisingDataSet(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPeriodicAdvertisingEnabled(int i, boolean flag, int j)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPeriodicAdvertisingParametersUpdated(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onScanResponseDataSet(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, null, 1);
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


    public abstract void onAdvertisingDataSet(int i, int j)
        throws RemoteException;

    public abstract void onAdvertisingEnabled(int i, boolean flag, int j)
        throws RemoteException;

    public abstract void onAdvertisingParametersUpdated(int i, int j, int k)
        throws RemoteException;

    public abstract void onAdvertisingSetStarted(int i, int j, int k)
        throws RemoteException;

    public abstract void onAdvertisingSetStopped(int i)
        throws RemoteException;

    public abstract void onOwnAddressRead(int i, int j, String s)
        throws RemoteException;

    public abstract void onPeriodicAdvertisingDataSet(int i, int j)
        throws RemoteException;

    public abstract void onPeriodicAdvertisingEnabled(int i, boolean flag, int j)
        throws RemoteException;

    public abstract void onPeriodicAdvertisingParametersUpdated(int i, int j)
        throws RemoteException;

    public abstract void onScanResponseDataSet(int i, int j)
        throws RemoteException;
}
