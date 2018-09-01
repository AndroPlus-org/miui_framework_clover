// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.backup;

import android.app.backup.RestoreDescription;
import android.app.backup.RestoreSet;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.*;

public interface IBackupTransport
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBackupTransport
    {

        public static IBackupTransport asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.backup.IBackupTransport");
            if(iinterface != null && (iinterface instanceof IBackupTransport))
                return (IBackupTransport)iinterface;
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
                parcel1.writeString("com.android.internal.backup.IBackupTransport");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                parcel = name();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                parcel = configurationIntent();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                parcel = currentDestinationString();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                parcel = dataManagementIntent();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                parcel = dataManagementLabel();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                parcel = transportDirName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                long l = requestBackupTime();
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                i = initializeDevice();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                PackageInfo packageinfo;
                ParcelFileDescriptor parcelfiledescriptor;
                if(parcel.readInt() != 0)
                    packageinfo = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel);
                else
                    packageinfo = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                i = performBackup(packageinfo, parcelfiledescriptor, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                if(parcel.readInt() != 0)
                    parcel = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = clearBackupData(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                i = finishBackup();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                parcel = getAvailableRestoreSets();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                long l1 = getCurrentRestoreSet();
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                i = startRestore(parcel.readLong(), (PackageInfo[])parcel.createTypedArray(PackageInfo.CREATOR));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                parcel = nextRestorePackage();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getRestoreData(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                finishRestore();
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                long l2 = requestFullBackupTime();
                parcel1.writeNoException();
                parcel1.writeLong(l2);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                PackageInfo packageinfo1;
                ParcelFileDescriptor parcelfiledescriptor1;
                if(parcel.readInt() != 0)
                    packageinfo1 = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel);
                else
                    packageinfo1 = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor1 = null;
                i = performFullBackup(packageinfo1, parcelfiledescriptor1, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                i = checkFullBackupSize(parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                i = sendBackupData(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                cancelFullBackup();
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                PackageInfo packageinfo2;
                boolean flag;
                if(parcel.readInt() != 0)
                    packageinfo2 = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel);
                else
                    packageinfo2 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                flag = isAppEligibleForBackup(packageinfo2, flag);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                String s = parcel.readString();
                long l3;
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                l3 = getBackupQuota(s, flag1);
                parcel1.writeNoException();
                parcel1.writeLong(l3);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getNextFullRestoreDataChunk(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.backup.IBackupTransport");
                i = abortFullRestore();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.backup.IBackupTransport";
        static final int TRANSACTION_abortFullRestore = 26;
        static final int TRANSACTION_cancelFullBackup = 22;
        static final int TRANSACTION_checkFullBackupSize = 20;
        static final int TRANSACTION_clearBackupData = 10;
        static final int TRANSACTION_configurationIntent = 2;
        static final int TRANSACTION_currentDestinationString = 3;
        static final int TRANSACTION_dataManagementIntent = 4;
        static final int TRANSACTION_dataManagementLabel = 5;
        static final int TRANSACTION_finishBackup = 11;
        static final int TRANSACTION_finishRestore = 17;
        static final int TRANSACTION_getAvailableRestoreSets = 12;
        static final int TRANSACTION_getBackupQuota = 24;
        static final int TRANSACTION_getCurrentRestoreSet = 13;
        static final int TRANSACTION_getNextFullRestoreDataChunk = 25;
        static final int TRANSACTION_getRestoreData = 16;
        static final int TRANSACTION_initializeDevice = 8;
        static final int TRANSACTION_isAppEligibleForBackup = 23;
        static final int TRANSACTION_name = 1;
        static final int TRANSACTION_nextRestorePackage = 15;
        static final int TRANSACTION_performBackup = 9;
        static final int TRANSACTION_performFullBackup = 19;
        static final int TRANSACTION_requestBackupTime = 7;
        static final int TRANSACTION_requestFullBackupTime = 18;
        static final int TRANSACTION_sendBackupData = 21;
        static final int TRANSACTION_startRestore = 14;
        static final int TRANSACTION_transportDirName = 6;

        public Stub()
        {
            attachInterface(this, "com.android.internal.backup.IBackupTransport");
        }
    }

    private static class Stub.Proxy
        implements IBackupTransport
    {

        public int abortFullRestore()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelFullBackup()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(22, parcel, parcel1, 0);
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

        public int checkFullBackupSize(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            parcel.writeLong(l);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int clearBackupData(PackageInfo packageinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            if(packageinfo == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            packageinfo.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            packageinfo;
            parcel1.recycle();
            parcel.recycle();
            throw packageinfo;
        }

        public Intent configurationIntent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String currentDestinationString()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent dataManagementIntent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String dataManagementLabel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int finishBackup()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void finishRestore()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(17, parcel, parcel1, 0);
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

        public RestoreSet[] getAvailableRestoreSets()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            RestoreSet arestoreset[];
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            arestoreset = (RestoreSet[])parcel1.createTypedArray(RestoreSet.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arestoreset;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public long getBackupQuota(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            parcel.writeString(s);
            if(flag)
                i = 1;
            long l;
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public long getCurrentRestoreSet()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.backup.IBackupTransport";
        }

        public int getNextFullRestoreDataChunk(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public int getRestoreData(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public int initializeDevice()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isAppEligibleForBackup(PackageInfo packageinfo, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            if(packageinfo == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            packageinfo.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            packageinfo;
            parcel1.recycle();
            parcel.recycle();
            throw packageinfo;
        }

        public String name()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public RestoreDescription nextRestorePackage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            RestoreDescription restoredescription = (RestoreDescription)RestoreDescription.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return restoredescription;
_L2:
            restoredescription = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int performBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            if(packageinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            packageinfo.writeToParcel(parcel, 0);
_L3:
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            packageinfo;
            parcel1.recycle();
            parcel.recycle();
            throw packageinfo;
            parcel.writeInt(0);
              goto _L4
        }

        public int performFullBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            if(packageinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            packageinfo.writeToParcel(parcel, 0);
_L3:
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            packageinfo;
            parcel1.recycle();
            parcel.recycle();
            throw packageinfo;
            parcel.writeInt(0);
              goto _L4
        }

        public long requestBackupTime()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public long requestFullBackupTime()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int sendBackupData(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int startRestore(long l, PackageInfo apackageinfo[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            parcel.writeLong(l);
            parcel.writeTypedArray(apackageinfo, 0);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            apackageinfo;
            parcel1.recycle();
            parcel.recycle();
            throw apackageinfo;
        }

        public String transportDirName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.backup.IBackupTransport");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int abortFullRestore()
        throws RemoteException;

    public abstract void cancelFullBackup()
        throws RemoteException;

    public abstract int checkFullBackupSize(long l)
        throws RemoteException;

    public abstract int clearBackupData(PackageInfo packageinfo)
        throws RemoteException;

    public abstract Intent configurationIntent()
        throws RemoteException;

    public abstract String currentDestinationString()
        throws RemoteException;

    public abstract Intent dataManagementIntent()
        throws RemoteException;

    public abstract String dataManagementLabel()
        throws RemoteException;

    public abstract int finishBackup()
        throws RemoteException;

    public abstract void finishRestore()
        throws RemoteException;

    public abstract RestoreSet[] getAvailableRestoreSets()
        throws RemoteException;

    public abstract long getBackupQuota(String s, boolean flag)
        throws RemoteException;

    public abstract long getCurrentRestoreSet()
        throws RemoteException;

    public abstract int getNextFullRestoreDataChunk(ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract int getRestoreData(ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract int initializeDevice()
        throws RemoteException;

    public abstract boolean isAppEligibleForBackup(PackageInfo packageinfo, boolean flag)
        throws RemoteException;

    public abstract String name()
        throws RemoteException;

    public abstract RestoreDescription nextRestorePackage()
        throws RemoteException;

    public abstract int performBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor, int i)
        throws RemoteException;

    public abstract int performFullBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor, int i)
        throws RemoteException;

    public abstract long requestBackupTime()
        throws RemoteException;

    public abstract long requestFullBackupTime()
        throws RemoteException;

    public abstract int sendBackupData(int i)
        throws RemoteException;

    public abstract int startRestore(long l, PackageInfo apackageinfo[])
        throws RemoteException;

    public abstract String transportDirName()
        throws RemoteException;
}
