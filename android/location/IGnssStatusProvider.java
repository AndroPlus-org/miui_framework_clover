// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

// Referenced classes of package android.location:
//            IGnssStatusListener

public interface IGnssStatusProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGnssStatusProvider
    {

        public static IGnssStatusProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IGnssStatusProvider");
            if(iinterface != null && (iinterface instanceof IGnssStatusProvider))
                return (IGnssStatusProvider)iinterface;
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
                parcel1.writeString("android.location.IGnssStatusProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IGnssStatusProvider");
                registerGnssStatusCallback(IGnssStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.IGnssStatusProvider");
                unregisterGnssStatusCallback(IGnssStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.IGnssStatusProvider";
        static final int TRANSACTION_registerGnssStatusCallback = 1;
        static final int TRANSACTION_unregisterGnssStatusCallback = 2;

        public Stub()
        {
            attachInterface(this, "android.location.IGnssStatusProvider");
        }
    }

    private static class Stub.Proxy
        implements IGnssStatusProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.IGnssStatusProvider";
        }

        public void registerGnssStatusCallback(IGnssStatusListener ignssstatuslistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssStatusProvider");
            if(ignssstatuslistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ignssstatuslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ignssstatuslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ignssstatuslistener;
        }

        public void unregisterGnssStatusCallback(IGnssStatusListener ignssstatuslistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssStatusProvider");
            if(ignssstatuslistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ignssstatuslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ignssstatuslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ignssstatuslistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void registerGnssStatusCallback(IGnssStatusListener ignssstatuslistener)
        throws RemoteException;

    public abstract void unregisterGnssStatusCallback(IGnssStatusListener ignssstatuslistener)
        throws RemoteException;
}
