// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.backup;

import android.app.backup.IBackupManager;
import android.os.*;

public interface IObbBackupService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IObbBackupService
    {

        public static IObbBackupService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.backup.IObbBackupService");
            if(iinterface != null && (iinterface instanceof IObbBackupService))
                return (IObbBackupService)iinterface;
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
            String s1;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.backup.IObbBackupService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.backup.IObbBackupService");
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                backupObbs(s, parcel1, parcel.readInt(), android.app.backup.IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.backup.IObbBackupService");
                s1 = parcel.readString();
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            restoreObbFile(s1, parcel1, parcel.readLong(), parcel.readInt(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt(), android.app.backup.IBackupManager.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.backup.IObbBackupService";
        static final int TRANSACTION_backupObbs = 1;
        static final int TRANSACTION_restoreObbFile = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.backup.IObbBackupService");
        }
    }

    private static class Stub.Proxy
        implements IObbBackupService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void backupObbs(String s, ParcelFileDescriptor parcelfiledescriptor, int i, IBackupManager ibackupmanager)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IObbBackupService");
            parcel.writeString(s);
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            s = obj;
            if(ibackupmanager == null)
                break MISSING_BLOCK_LABEL_60;
            s = ibackupmanager.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.backup.IObbBackupService";
        }

        public void restoreObbFile(String s, ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s1, long l1, long l2, int j, IBackupManager ibackupmanager)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IObbBackupService");
            parcel.writeString(s);
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L3:
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeLong(l1);
            parcel.writeLong(l2);
            parcel.writeInt(j);
            if(ibackupmanager == null)
                break MISSING_BLOCK_LABEL_133;
            s = ibackupmanager.asBinder();
_L4:
            parcel.writeStrongBinder(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            s = null;
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void backupObbs(String s, ParcelFileDescriptor parcelfiledescriptor, int i, IBackupManager ibackupmanager)
        throws RemoteException;

    public abstract void restoreObbFile(String s, ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s1, long l1, long l2, int j, IBackupManager ibackupmanager)
        throws RemoteException;
}
