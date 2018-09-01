// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface IStopUserCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStopUserCallback
    {

        public static IStopUserCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IStopUserCallback");
            if(iinterface != null && (iinterface instanceof IStopUserCallback))
                return (IStopUserCallback)iinterface;
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
                parcel1.writeString("android.app.IStopUserCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IStopUserCallback");
                userStopped(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IStopUserCallback");
                userStopAborted(parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IStopUserCallback";
        static final int TRANSACTION_userStopAborted = 2;
        static final int TRANSACTION_userStopped = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IStopUserCallback");
        }
    }

    private static class Stub.Proxy
        implements IStopUserCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IStopUserCallback";
        }

        public void userStopAborted(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IStopUserCallback");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void userStopped(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IStopUserCallback");
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


    public abstract void userStopAborted(int i)
        throws RemoteException;

    public abstract void userStopped(int i)
        throws RemoteException;
}
