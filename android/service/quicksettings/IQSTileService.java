// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.quicksettings;

import android.os.*;

public interface IQSTileService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IQSTileService
    {

        public static IQSTileService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.quicksettings.IQSTileService");
            if(iinterface != null && (iinterface instanceof IQSTileService))
                return (IQSTileService)iinterface;
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
                parcel1.writeString("android.service.quicksettings.IQSTileService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.quicksettings.IQSTileService");
                onTileAdded();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.quicksettings.IQSTileService");
                onTileRemoved();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.quicksettings.IQSTileService");
                onStartListening();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.quicksettings.IQSTileService");
                onStopListening();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.quicksettings.IQSTileService");
                onClick(parcel.readStrongBinder());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.quicksettings.IQSTileService");
                onUnlockComplete();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.quicksettings.IQSTileService";
        static final int TRANSACTION_onClick = 5;
        static final int TRANSACTION_onStartListening = 3;
        static final int TRANSACTION_onStopListening = 4;
        static final int TRANSACTION_onTileAdded = 1;
        static final int TRANSACTION_onTileRemoved = 2;
        static final int TRANSACTION_onUnlockComplete = 6;

        public Stub()
        {
            attachInterface(this, "android.service.quicksettings.IQSTileService");
        }
    }

    private static class Stub.Proxy
        implements IQSTileService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.quicksettings.IQSTileService";
        }

        public void onClick(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSTileService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void onStartListening()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSTileService");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStopListening()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSTileService");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTileAdded()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSTileService");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTileRemoved()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSTileService");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUnlockComplete()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSTileService");
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


    public abstract void onClick(IBinder ibinder)
        throws RemoteException;

    public abstract void onStartListening()
        throws RemoteException;

    public abstract void onStopListening()
        throws RemoteException;

    public abstract void onTileAdded()
        throws RemoteException;

    public abstract void onTileRemoved()
        throws RemoteException;

    public abstract void onUnlockComplete()
        throws RemoteException;
}
