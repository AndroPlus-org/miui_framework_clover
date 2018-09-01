// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app.backup;

import android.os.*;

public interface IPackageBackupRestoreObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageBackupRestoreObserver
    {

        public static IPackageBackupRestoreObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.app.backup.IPackageBackupRestoreObserver");
            if(iinterface != null && (iinterface instanceof IPackageBackupRestoreObserver))
                return (IPackageBackupRestoreObserver)iinterface;
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
                parcel1.writeString("miui.app.backup.IPackageBackupRestoreObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.app.backup.IPackageBackupRestoreObserver");
                onBackupStart(parcel.readString(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.app.backup.IPackageBackupRestoreObserver");
                onBackupEnd(parcel.readString(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.app.backup.IPackageBackupRestoreObserver");
                onRestoreStart(parcel.readString(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("miui.app.backup.IPackageBackupRestoreObserver");
                onRestoreEnd(parcel.readString(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("miui.app.backup.IPackageBackupRestoreObserver");
                onRestoreError(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("miui.app.backup.IPackageBackupRestoreObserver");
                onError(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("miui.app.backup.IPackageBackupRestoreObserver");
                onCustomProgressChange(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readLong());
                return true;
            }
        }

        private static final String DESCRIPTOR = "miui.app.backup.IPackageBackupRestoreObserver";
        static final int TRANSACTION_onBackupEnd = 2;
        static final int TRANSACTION_onBackupStart = 1;
        static final int TRANSACTION_onCustomProgressChange = 7;
        static final int TRANSACTION_onError = 6;
        static final int TRANSACTION_onRestoreEnd = 4;
        static final int TRANSACTION_onRestoreError = 5;
        static final int TRANSACTION_onRestoreStart = 3;

        public Stub()
        {
            attachInterface(this, "miui.app.backup.IPackageBackupRestoreObserver");
        }
    }

    private static class Stub.Proxy
        implements IPackageBackupRestoreObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.app.backup.IPackageBackupRestoreObserver";
        }

        public void onBackupEnd(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IPackageBackupRestoreObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onBackupStart(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IPackageBackupRestoreObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onCustomProgressChange(String s, int i, int j, long l, long l1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IPackageBackupRestoreObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onError(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IPackageBackupRestoreObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onRestoreEnd(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IPackageBackupRestoreObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onRestoreError(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IPackageBackupRestoreObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onRestoreStart(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IPackageBackupRestoreObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onBackupEnd(String s, int i)
        throws RemoteException;

    public abstract void onBackupStart(String s, int i)
        throws RemoteException;

    public abstract void onCustomProgressChange(String s, int i, int j, long l, long l1)
        throws RemoteException;

    public abstract void onError(String s, int i, int j)
        throws RemoteException;

    public abstract void onRestoreEnd(String s, int i)
        throws RemoteException;

    public abstract void onRestoreError(String s, int i, int j)
        throws RemoteException;

    public abstract void onRestoreStart(String s, int i)
        throws RemoteException;
}
