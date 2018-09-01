// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

public interface IMediaRouterClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaRouterClient
    {

        public static IMediaRouterClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IMediaRouterClient");
            if(iinterface != null && (iinterface instanceof IMediaRouterClient))
                return (IMediaRouterClient)iinterface;
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
                parcel1.writeString("android.media.IMediaRouterClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IMediaRouterClient");
                onStateChanged();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IMediaRouterClient");
                onRestoreRoute();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IMediaRouterClient";
        static final int TRANSACTION_onRestoreRoute = 2;
        static final int TRANSACTION_onStateChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IMediaRouterClient");
        }
    }

    private static class Stub.Proxy
        implements IMediaRouterClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IMediaRouterClient";
        }

        public void onRestoreRoute()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterClient");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStateChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterClient");
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


    public abstract void onRestoreRoute()
        throws RemoteException;

    public abstract void onStateChanged()
        throws RemoteException;
}
