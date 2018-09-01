// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.*;

// Referenced classes of package android.app.backup:
//            BackupProgress

public interface IBackupObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBackupObserver
    {

        public static IBackupObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.backup.IBackupObserver");
            if(iinterface != null && (iinterface instanceof IBackupObserver))
                return (IBackupObserver)iinterface;
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
                parcel1.writeString("android.app.backup.IBackupObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.backup.IBackupObserver");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (BackupProgress)BackupProgress.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onUpdate(parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.backup.IBackupObserver");
                onResult(parcel.readString(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.backup.IBackupObserver");
                backupFinished(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.backup.IBackupObserver";
        static final int TRANSACTION_backupFinished = 3;
        static final int TRANSACTION_onResult = 2;
        static final int TRANSACTION_onUpdate = 1;

        public Stub()
        {
            attachInterface(this, "android.app.backup.IBackupObserver");
        }
    }

    private static class Stub.Proxy
        implements IBackupObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void backupFinished(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.backup.IBackupObserver";
        }

        public void onResult(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onUpdate(String s, BackupProgress backupprogress)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupObserver");
            parcel.writeString(s);
            if(backupprogress == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            backupprogress.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
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


    public abstract void backupFinished(int i)
        throws RemoteException;

    public abstract void onResult(String s, int i)
        throws RemoteException;

    public abstract void onUpdate(String s, BackupProgress backupprogress)
        throws RemoteException;
}
