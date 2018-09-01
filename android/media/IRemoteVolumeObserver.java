// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

public interface IRemoteVolumeObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRemoteVolumeObserver
    {

        public static IRemoteVolumeObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IRemoteVolumeObserver");
            if(iinterface != null && (iinterface instanceof IRemoteVolumeObserver))
                return (IRemoteVolumeObserver)iinterface;
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
                parcel1.writeString("android.media.IRemoteVolumeObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IRemoteVolumeObserver");
                dispatchRemoteVolumeUpdate(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IRemoteVolumeObserver";
        static final int TRANSACTION_dispatchRemoteVolumeUpdate = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IRemoteVolumeObserver");
        }
    }

    private static class Stub.Proxy
        implements IRemoteVolumeObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dispatchRemoteVolumeUpdate(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteVolumeObserver");
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

        public String getInterfaceDescriptor()
        {
            return "android.media.IRemoteVolumeObserver";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dispatchRemoteVolumeUpdate(int i, int j)
        throws RemoteException;
}
