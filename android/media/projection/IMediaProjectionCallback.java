// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.projection;

import android.os.*;

public interface IMediaProjectionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaProjectionCallback
    {

        public static IMediaProjectionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.projection.IMediaProjectionCallback");
            if(iinterface != null && (iinterface instanceof IMediaProjectionCallback))
                return (IMediaProjectionCallback)iinterface;
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
                parcel1.writeString("android.media.projection.IMediaProjectionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.projection.IMediaProjectionCallback");
                onStop();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.projection.IMediaProjectionCallback";
        static final int TRANSACTION_onStop = 1;

        public Stub()
        {
            attachInterface(this, "android.media.projection.IMediaProjectionCallback");
        }
    }

    private static class Stub.Proxy
        implements IMediaProjectionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.projection.IMediaProjectionCallback";
        }

        public void onStop()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionCallback");
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


    public abstract void onStop()
        throws RemoteException;
}
