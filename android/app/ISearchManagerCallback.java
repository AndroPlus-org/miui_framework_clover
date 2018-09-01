// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface ISearchManagerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISearchManagerCallback
    {

        public static ISearchManagerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.ISearchManagerCallback");
            if(iinterface != null && (iinterface instanceof ISearchManagerCallback))
                return (ISearchManagerCallback)iinterface;
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
                parcel1.writeString("android.app.ISearchManagerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.ISearchManagerCallback");
                onDismiss();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.ISearchManagerCallback");
                onCancel();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.ISearchManagerCallback";
        static final int TRANSACTION_onCancel = 2;
        static final int TRANSACTION_onDismiss = 1;

        public Stub()
        {
            attachInterface(this, "android.app.ISearchManagerCallback");
        }
    }

    private static class Stub.Proxy
        implements ISearchManagerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.ISearchManagerCallback";
        }

        public void onCancel()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDismiss()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
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


    public abstract void onCancel()
        throws RemoteException;

    public abstract void onDismiss()
        throws RemoteException;
}
