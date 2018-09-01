// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.*;

public interface IFullBackupRestoreObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFullBackupRestoreObserver
    {

        public static IFullBackupRestoreObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.backup.IFullBackupRestoreObserver");
            if(iinterface != null && (iinterface instanceof IFullBackupRestoreObserver))
                return (IFullBackupRestoreObserver)iinterface;
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
                parcel1.writeString("android.app.backup.IFullBackupRestoreObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
                onStartBackup();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
                onBackupPackage(parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
                onEndBackup();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
                onStartRestore();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
                onRestorePackage(parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
                onEndRestore();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.backup.IFullBackupRestoreObserver");
                onTimeout();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.backup.IFullBackupRestoreObserver";
        static final int TRANSACTION_onBackupPackage = 2;
        static final int TRANSACTION_onEndBackup = 3;
        static final int TRANSACTION_onEndRestore = 6;
        static final int TRANSACTION_onRestorePackage = 5;
        static final int TRANSACTION_onStartBackup = 1;
        static final int TRANSACTION_onStartRestore = 4;
        static final int TRANSACTION_onTimeout = 7;

        public Stub()
        {
            attachInterface(this, "android.app.backup.IFullBackupRestoreObserver");
        }
    }

    private static class Stub.Proxy
        implements IFullBackupRestoreObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.backup.IFullBackupRestoreObserver";
        }

        public void onBackupPackage(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onEndBackup()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onEndRestore()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRestorePackage(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
            parcel.writeString(s);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onStartBackup()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStartRestore()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTimeout()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IFullBackupRestoreObserver");
            mRemote.transact(7, parcel, null, 1);
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


    public abstract void onBackupPackage(String s)
        throws RemoteException;

    public abstract void onEndBackup()
        throws RemoteException;

    public abstract void onEndRestore()
        throws RemoteException;

    public abstract void onRestorePackage(String s)
        throws RemoteException;

    public abstract void onStartBackup()
        throws RemoteException;

    public abstract void onStartRestore()
        throws RemoteException;

    public abstract void onTimeout()
        throws RemoteException;
}
