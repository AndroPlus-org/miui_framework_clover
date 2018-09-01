// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

public interface IMediaResourceMonitor
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaResourceMonitor
    {

        public static IMediaResourceMonitor asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IMediaResourceMonitor");
            if(iinterface != null && (iinterface instanceof IMediaResourceMonitor))
                return (IMediaResourceMonitor)iinterface;
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
                parcel1.writeString("android.media.IMediaResourceMonitor");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IMediaResourceMonitor");
                notifyResourceGranted(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IMediaResourceMonitor";
        static final int TRANSACTION_notifyResourceGranted = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IMediaResourceMonitor");
        }
    }

    private static class Stub.Proxy
        implements IMediaResourceMonitor
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IMediaResourceMonitor";
        }

        public void notifyResourceGranted(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaResourceMonitor");
            parcel.writeInt(i);
            parcel.writeInt(j);
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


    public abstract void notifyResourceGranted(int i, int j)
        throws RemoteException;
}
