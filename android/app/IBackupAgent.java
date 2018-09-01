// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.app.backup.IBackupManager;
import android.os.*;

public interface IBackupAgent
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBackupAgent
    {

        public static IBackupAgent asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IBackupAgent");
            if(iinterface != null && (iinterface instanceof IBackupAgent))
                return (IBackupAgent)iinterface;
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
                parcel1.writeString("android.app.IBackupAgent");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IBackupAgent");
                ParcelFileDescriptor parcelfiledescriptor;
                ParcelFileDescriptor parcelfiledescriptor2;
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor2 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor2 = null;
                doBackup(parcel1, parcelfiledescriptor, parcelfiledescriptor2, parcel.readLong(), parcel.readInt(), android.app.backup.IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IBackupAgent");
                ParcelFileDescriptor parcelfiledescriptor1;
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcelfiledescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor1 = null;
                doRestore(parcel1, i, parcelfiledescriptor1, parcel.readInt(), android.app.backup.IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IBackupAgent");
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                doFullBackup(parcel1, parcel.readLong(), parcel.readInt(), android.app.backup.IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IBackupAgent");
                doMeasureFullBackup(parcel.readLong(), parcel.readInt(), android.app.backup.IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.IBackupAgent");
                doQuotaExceeded(parcel.readLong(), parcel.readLong());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.IBackupAgent");
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                doRestoreFile(parcel1, parcel.readLong(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt(), android.app.backup.IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.IBackupAgent");
                doRestoreFinished(parcel.readInt(), android.app.backup.IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.IBackupAgent");
                fail(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IBackupAgent";
        static final int TRANSACTION_doBackup = 1;
        static final int TRANSACTION_doFullBackup = 3;
        static final int TRANSACTION_doMeasureFullBackup = 4;
        static final int TRANSACTION_doQuotaExceeded = 5;
        static final int TRANSACTION_doRestore = 2;
        static final int TRANSACTION_doRestoreFile = 6;
        static final int TRANSACTION_doRestoreFinished = 7;
        static final int TRANSACTION_fail = 8;

        public Stub()
        {
            attachInterface(this, "android.app.IBackupAgent");
        }
    }

    private static class Stub.Proxy
        implements IBackupAgent
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void doBackup(ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, ParcelFileDescriptor parcelfiledescriptor2, long l, int i, IBackupManager ibackupmanager)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IBackupAgent");
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L5:
            if(parcelfiledescriptor1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            parcelfiledescriptor1.writeToParcel(parcel, 0);
_L6:
            if(parcelfiledescriptor2 == null)
                break MISSING_BLOCK_LABEL_149;
            parcel.writeInt(1);
            parcelfiledescriptor2.writeToParcel(parcel, 0);
_L7:
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcelfiledescriptor = obj;
            if(ibackupmanager == null)
                break MISSING_BLOCK_LABEL_96;
            parcelfiledescriptor = ibackupmanager.asBinder();
            parcel.writeStrongBinder(parcelfiledescriptor);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void doFullBackup(ParcelFileDescriptor parcelfiledescriptor, long l, int i, IBackupManager ibackupmanager)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IBackupAgent");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcelfiledescriptor = obj;
            if(ibackupmanager == null)
                break MISSING_BLOCK_LABEL_61;
            parcelfiledescriptor = ibackupmanager.asBinder();
            parcel.writeStrongBinder(parcelfiledescriptor);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void doMeasureFullBackup(long l, int i, IBackupManager ibackupmanager)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IBackupAgent");
            parcel.writeLong(l);
            parcel.writeInt(i);
            if(ibackupmanager == null)
                break MISSING_BLOCK_LABEL_41;
            ibinder = ibackupmanager.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            ibackupmanager;
            parcel.recycle();
            throw ibackupmanager;
        }

        public void doQuotaExceeded(long l, long l1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IBackupAgent");
            parcel.writeLong(l);
            parcel.writeLong(l1);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void doRestore(ParcelFileDescriptor parcelfiledescriptor, int i, ParcelFileDescriptor parcelfiledescriptor1, int j, IBackupManager ibackupmanager)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IBackupAgent");
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(parcelfiledescriptor1 == null)
                break MISSING_BLOCK_LABEL_122;
            parcel.writeInt(1);
            parcelfiledescriptor1.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(j);
            parcelfiledescriptor = obj;
            if(ibackupmanager == null)
                break MISSING_BLOCK_LABEL_78;
            parcelfiledescriptor = ibackupmanager.asBinder();
            parcel.writeStrongBinder(parcelfiledescriptor);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
            parcel.writeInt(0);
              goto _L4
        }

        public void doRestoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s, String s1, long l1, long l2, int j, IBackupManager ibackupmanager)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IBackupAgent");
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L3:
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeLong(l1);
            parcel.writeLong(l2);
            parcel.writeInt(j);
            if(ibackupmanager == null)
                break MISSING_BLOCK_LABEL_135;
            parcelfiledescriptor = ibackupmanager.asBinder();
_L4:
            parcel.writeStrongBinder(parcelfiledescriptor);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
            parcelfiledescriptor = null;
              goto _L4
        }

        public void doRestoreFinished(int i, IBackupManager ibackupmanager)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IBackupAgent");
            parcel.writeInt(i);
            if(ibackupmanager == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = ibackupmanager.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            ibackupmanager;
            parcel.recycle();
            throw ibackupmanager;
        }

        public void fail(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IBackupAgent");
            parcel.writeString(s);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IBackupAgent";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void doBackup(ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, ParcelFileDescriptor parcelfiledescriptor2, long l, int i, IBackupManager ibackupmanager)
        throws RemoteException;

    public abstract void doFullBackup(ParcelFileDescriptor parcelfiledescriptor, long l, int i, IBackupManager ibackupmanager)
        throws RemoteException;

    public abstract void doMeasureFullBackup(long l, int i, IBackupManager ibackupmanager)
        throws RemoteException;

    public abstract void doQuotaExceeded(long l, long l1)
        throws RemoteException;

    public abstract void doRestore(ParcelFileDescriptor parcelfiledescriptor, int i, ParcelFileDescriptor parcelfiledescriptor1, int j, IBackupManager ibackupmanager)
        throws RemoteException;

    public abstract void doRestoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s, String s1, long l1, long l2, int j, IBackupManager ibackupmanager)
        throws RemoteException;

    public abstract void doRestoreFinished(int i, IBackupManager ibackupmanager)
        throws RemoteException;

    public abstract void fail(String s)
        throws RemoteException;
}
