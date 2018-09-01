// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface IUserSwitchObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUserSwitchObserver
    {

        public static IUserSwitchObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IUserSwitchObserver");
            if(iinterface != null && (iinterface instanceof IUserSwitchObserver))
                return (IUserSwitchObserver)iinterface;
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
                parcel1.writeString("android.app.IUserSwitchObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IUserSwitchObserver");
                onUserSwitching(parcel.readInt(), android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IUserSwitchObserver");
                onUserSwitchComplete(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IUserSwitchObserver");
                onForegroundProfileSwitch(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IUserSwitchObserver");
                onLockedBootComplete(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IUserSwitchObserver";
        static final int TRANSACTION_onForegroundProfileSwitch = 3;
        static final int TRANSACTION_onLockedBootComplete = 4;
        static final int TRANSACTION_onUserSwitchComplete = 2;
        static final int TRANSACTION_onUserSwitching = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IUserSwitchObserver");
        }
    }

    private static class Stub.Proxy
        implements IUserSwitchObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IUserSwitchObserver";
        }

        public void onForegroundProfileSwitch(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onLockedBootComplete(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUserSwitchComplete(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUserSwitching(int i, IRemoteCallback iremotecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
            parcel.writeInt(i);
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = iremotecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iremotecallback;
            parcel.recycle();
            throw iremotecallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onForegroundProfileSwitch(int i)
        throws RemoteException;

    public abstract void onLockedBootComplete(int i)
        throws RemoteException;

    public abstract void onUserSwitchComplete(int i)
        throws RemoteException;

    public abstract void onUserSwitching(int i, IRemoteCallback iremotecallback)
        throws RemoteException;
}
