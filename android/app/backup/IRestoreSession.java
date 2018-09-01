// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.*;

// Referenced classes of package android.app.backup:
//            IRestoreObserver, IBackupManagerMonitor

public interface IRestoreSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRestoreSession
    {

        public static IRestoreSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.backup.IRestoreSession");
            if(iinterface != null && (iinterface instanceof IRestoreSession))
                return (IRestoreSession)iinterface;
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
                parcel1.writeString("android.app.backup.IRestoreSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.backup.IRestoreSession");
                i = getAvailableRestoreSets(IRestoreObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.backup.IRestoreSession");
                i = restoreAll(parcel.readLong(), IRestoreObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.backup.IRestoreSession");
                i = restoreSome(parcel.readLong(), IRestoreObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.backup.IRestoreSession");
                i = restorePackage(parcel.readString(), IRestoreObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.backup.IRestoreSession");
                endRestoreSession();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.backup.IRestoreSession";
        static final int TRANSACTION_endRestoreSession = 5;
        static final int TRANSACTION_getAvailableRestoreSets = 1;
        static final int TRANSACTION_restoreAll = 2;
        static final int TRANSACTION_restorePackage = 4;
        static final int TRANSACTION_restoreSome = 3;

        public Stub()
        {
            attachInterface(this, "android.app.backup.IRestoreSession");
        }
    }

    private static class Stub.Proxy
        implements IRestoreSession
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void endRestoreSession()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreSession");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getAvailableRestoreSets(IRestoreObserver irestoreobserver, IBackupManagerMonitor ibackupmanagermonitor)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreSession");
            if(irestoreobserver == null)
                break MISSING_BLOCK_LABEL_96;
            irestoreobserver = irestoreobserver.asBinder();
_L1:
            parcel.writeStrongBinder(irestoreobserver);
            irestoreobserver = obj;
            if(ibackupmanagermonitor == null)
                break MISSING_BLOCK_LABEL_49;
            irestoreobserver = ibackupmanagermonitor.asBinder();
            int i;
            parcel.writeStrongBinder(irestoreobserver);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            irestoreobserver = null;
              goto _L1
            irestoreobserver;
            parcel1.recycle();
            parcel.recycle();
            throw irestoreobserver;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.backup.IRestoreSession";
        }

        public int restoreAll(long l, IRestoreObserver irestoreobserver, IBackupManagerMonitor ibackupmanagermonitor)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreSession");
            parcel.writeLong(l);
            if(irestoreobserver == null)
                break MISSING_BLOCK_LABEL_106;
            irestoreobserver = irestoreobserver.asBinder();
_L1:
            parcel.writeStrongBinder(irestoreobserver);
            irestoreobserver = obj;
            if(ibackupmanagermonitor == null)
                break MISSING_BLOCK_LABEL_59;
            irestoreobserver = ibackupmanagermonitor.asBinder();
            int i;
            parcel.writeStrongBinder(irestoreobserver);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            irestoreobserver = null;
              goto _L1
            irestoreobserver;
            parcel1.recycle();
            parcel.recycle();
            throw irestoreobserver;
        }

        public int restorePackage(String s, IRestoreObserver irestoreobserver, IBackupManagerMonitor ibackupmanagermonitor)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreSession");
            parcel.writeString(s);
            if(irestoreobserver == null)
                break MISSING_BLOCK_LABEL_104;
            s = irestoreobserver.asBinder();
_L1:
            parcel.writeStrongBinder(s);
            s = obj;
            if(ibackupmanagermonitor == null)
                break MISSING_BLOCK_LABEL_57;
            s = ibackupmanagermonitor.asBinder();
            int i;
            parcel.writeStrongBinder(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s = null;
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int restoreSome(long l, IRestoreObserver irestoreobserver, IBackupManagerMonitor ibackupmanagermonitor, String as[])
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreSession");
            parcel.writeLong(l);
            if(irestoreobserver == null)
                break MISSING_BLOCK_LABEL_113;
            irestoreobserver = irestoreobserver.asBinder();
_L1:
            parcel.writeStrongBinder(irestoreobserver);
            irestoreobserver = obj;
            if(ibackupmanagermonitor == null)
                break MISSING_BLOCK_LABEL_59;
            irestoreobserver = ibackupmanagermonitor.asBinder();
            int i;
            parcel.writeStrongBinder(irestoreobserver);
            parcel.writeStringArray(as);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            irestoreobserver = null;
              goto _L1
            irestoreobserver;
            parcel1.recycle();
            parcel.recycle();
            throw irestoreobserver;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void endRestoreSession()
        throws RemoteException;

    public abstract int getAvailableRestoreSets(IRestoreObserver irestoreobserver, IBackupManagerMonitor ibackupmanagermonitor)
        throws RemoteException;

    public abstract int restoreAll(long l, IRestoreObserver irestoreobserver, IBackupManagerMonitor ibackupmanagermonitor)
        throws RemoteException;

    public abstract int restorePackage(String s, IRestoreObserver irestoreobserver, IBackupManagerMonitor ibackupmanagermonitor)
        throws RemoteException;

    public abstract int restoreSome(long l, IRestoreObserver irestoreobserver, IBackupManagerMonitor ibackupmanagermonitor, String as[])
        throws RemoteException;
}
