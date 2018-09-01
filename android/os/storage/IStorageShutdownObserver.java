// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.os.*;

public interface IStorageShutdownObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStorageShutdownObserver
    {

        public static IStorageShutdownObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.storage.IStorageShutdownObserver");
            if(iinterface != null && (iinterface instanceof IStorageShutdownObserver))
                return (IStorageShutdownObserver)iinterface;
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
                parcel1.writeString("android.os.storage.IStorageShutdownObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.storage.IStorageShutdownObserver");
                onShutDownComplete(parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.storage.IStorageShutdownObserver";
        static final int TRANSACTION_onShutDownComplete = 1;

        public Stub()
        {
            attachInterface(this, "android.os.storage.IStorageShutdownObserver");
        }
    }

    private static class Stub.Proxy
        implements IStorageShutdownObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.storage.IStorageShutdownObserver";
        }

        public void onShutDownComplete(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IStorageShutdownObserver");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onShutDownComplete(int i)
        throws RemoteException;
}
