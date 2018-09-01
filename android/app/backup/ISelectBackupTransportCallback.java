// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.*;

public interface ISelectBackupTransportCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISelectBackupTransportCallback
    {

        public static ISelectBackupTransportCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.backup.ISelectBackupTransportCallback");
            if(iinterface != null && (iinterface instanceof ISelectBackupTransportCallback))
                return (ISelectBackupTransportCallback)iinterface;
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
                parcel1.writeString("android.app.backup.ISelectBackupTransportCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.backup.ISelectBackupTransportCallback");
                onSuccess(parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.backup.ISelectBackupTransportCallback");
                onFailure(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.backup.ISelectBackupTransportCallback";
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onSuccess = 1;

        public Stub()
        {
            attachInterface(this, "android.app.backup.ISelectBackupTransportCallback");
        }
    }

    private static class Stub.Proxy
        implements ISelectBackupTransportCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.backup.ISelectBackupTransportCallback";
        }

        public void onFailure(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.ISelectBackupTransportCallback");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSuccess(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.ISelectBackupTransportCallback");
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
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


    public abstract void onFailure(int i)
        throws RemoteException;

    public abstract void onSuccess(String s)
        throws RemoteException;
}
