// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app.backup;

import android.os.*;

// Referenced classes of package miui.app.backup:
//            IBackupServiceStateObserver, IPackageBackupRestoreObserver

public interface IBackupManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBackupManager
    {

        public static IBackupManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.app.backup.IBackupManager");
            if(iinterface != null && (iinterface instanceof IBackupManager))
                return (IBackupManager)iinterface;
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
            boolean flag7;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("miui.app.backup.IBackupManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                ParcelFileDescriptor parcelfiledescriptor;
                ParcelFileDescriptor parcelfiledescriptor2;
                String s2;
                String s3;
                String s4;
                boolean flag;
                boolean flag8;
                boolean flag9;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor2 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor2 = null;
                s2 = parcel.readString();
                i = parcel.readInt();
                s3 = parcel.readString();
                s4 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                backupPackage(parcelfiledescriptor, parcelfiledescriptor2, s2, i, s3, s4, flag, flag8, flag9, IPackageBackupRestoreObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                startConfirmationUi(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                ParcelFileDescriptor parcelfiledescriptor1;
                String s1;
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor1 = null;
                s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                restoreFile(parcelfiledescriptor1, s1, flag1, IPackageBackupRestoreObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                boolean flag2 = acquire(IBackupServiceStateObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                release(IBackupServiceStateObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                errorOccur(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                parcel = getCurrentRunningPackage();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                i = getCurrentWorkingFeature();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                i = getState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                writeMiuiBackupHeader(parcel);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                readMiuiBackupHeader(parcel);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                onApkInstalled();
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                addCompletedSize(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                boolean flag3 = isNeedBeKilled(parcel.readString());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                String s = parcel.readString();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setIsNeedBeKilled(s, flag4);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                boolean flag5 = isRunningFromMiui(parcel.readInt());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                boolean flag6 = isServiceIdle();
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                setCustomProgress(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                i = getBackupTimeoutScale();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("miui.app.backup.IBackupManager");
                flag7 = shouldSkipData();
                parcel1.writeNoException();
                break;
            }
            if(flag7)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "miui.app.backup.IBackupManager";
        static final int TRANSACTION_acquire = 4;
        static final int TRANSACTION_addCompletedSize = 13;
        static final int TRANSACTION_backupPackage = 1;
        static final int TRANSACTION_errorOccur = 6;
        static final int TRANSACTION_getBackupTimeoutScale = 19;
        static final int TRANSACTION_getCurrentRunningPackage = 7;
        static final int TRANSACTION_getCurrentWorkingFeature = 8;
        static final int TRANSACTION_getState = 9;
        static final int TRANSACTION_isNeedBeKilled = 14;
        static final int TRANSACTION_isRunningFromMiui = 16;
        static final int TRANSACTION_isServiceIdle = 17;
        static final int TRANSACTION_onApkInstalled = 12;
        static final int TRANSACTION_readMiuiBackupHeader = 11;
        static final int TRANSACTION_release = 5;
        static final int TRANSACTION_restoreFile = 3;
        static final int TRANSACTION_setCustomProgress = 18;
        static final int TRANSACTION_setIsNeedBeKilled = 15;
        static final int TRANSACTION_shouldSkipData = 20;
        static final int TRANSACTION_startConfirmationUi = 2;
        static final int TRANSACTION_writeMiuiBackupHeader = 10;

        public Stub()
        {
            attachInterface(this, "miui.app.backup.IBackupManager");
        }
    }

    private static class Stub.Proxy
        implements IBackupManager
    {

        public boolean acquire(IBackupServiceStateObserver ibackupservicestateobserver, IBinder ibinder)
            throws RemoteException
        {
            IBinder ibinder1;
            Parcel parcel;
            Parcel parcel1;
            ibinder1 = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            if(ibackupservicestateobserver == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder1 = ibackupservicestateobserver.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibackupservicestateobserver;
            parcel1.recycle();
            parcel.recycle();
            throw ibackupservicestateobserver;
        }

        public void addCompletedSize(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            parcel.writeLong(l);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void backupPackage(ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, String s, int i, String s1, String s2, boolean flag, 
                boolean flag1, boolean flag2, IPackageBackupRestoreObserver ipackagebackuprestoreobserver)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
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
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag2)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(ipackagebackuprestoreobserver == null)
                break MISSING_BLOCK_LABEL_223;
            parcelfiledescriptor = ipackagebackuprestoreobserver.asBinder();
_L7:
            parcel.writeStrongBinder(parcelfiledescriptor);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcelfiledescriptor = null;
              goto _L7
        }

        public void errorOccur(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public int getBackupTimeoutScale()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            mRemote.transact(19, parcel, parcel1, 0);
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

        public String getCurrentRunningPackage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public int getCurrentWorkingFeature()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
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

        public String getInterfaceDescriptor()
        {
            return "miui.app.backup.IBackupManager";
        }

        public int getState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public boolean isNeedBeKilled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isRunningFromMiui(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isServiceIdle()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onApkInstalled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void readMiuiBackupHeader(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void release(IBackupServiceStateObserver ibackupservicestateobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            if(ibackupservicestateobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibackupservicestateobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibackupservicestateobserver;
            parcel1.recycle();
            parcel.recycle();
            throw ibackupservicestateobserver;
        }

        public void restoreFile(ParcelFileDescriptor parcelfiledescriptor, String s, boolean flag, IPackageBackupRestoreObserver ipackagebackuprestoreobserver)
            throws RemoteException
        {
            int i;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcelfiledescriptor = obj;
            if(ipackagebackuprestoreobserver == null)
                break MISSING_BLOCK_LABEL_73;
            parcelfiledescriptor = ipackagebackuprestoreobserver.asBinder();
            parcel.writeStrongBinder(parcelfiledescriptor);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void setCustomProgress(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(18, parcel, parcel1, 0);
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

        public void setIsNeedBeKilled(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean shouldSkipData()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void startConfirmationUi(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void writeMiuiBackupHeader(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupManager");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean acquire(IBackupServiceStateObserver ibackupservicestateobserver, IBinder ibinder)
        throws RemoteException;

    public abstract void addCompletedSize(long l)
        throws RemoteException;

    public abstract void backupPackage(ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, String s, int i, String s1, String s2, boolean flag, 
            boolean flag1, boolean flag2, IPackageBackupRestoreObserver ipackagebackuprestoreobserver)
        throws RemoteException;

    public abstract void errorOccur(int i)
        throws RemoteException;

    public abstract int getBackupTimeoutScale()
        throws RemoteException;

    public abstract String getCurrentRunningPackage()
        throws RemoteException;

    public abstract int getCurrentWorkingFeature()
        throws RemoteException;

    public abstract int getState()
        throws RemoteException;

    public abstract boolean isNeedBeKilled(String s)
        throws RemoteException;

    public abstract boolean isRunningFromMiui(int i)
        throws RemoteException;

    public abstract boolean isServiceIdle()
        throws RemoteException;

    public abstract void onApkInstalled()
        throws RemoteException;

    public abstract void readMiuiBackupHeader(ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void release(IBackupServiceStateObserver ibackupservicestateobserver)
        throws RemoteException;

    public abstract void restoreFile(ParcelFileDescriptor parcelfiledescriptor, String s, boolean flag, IPackageBackupRestoreObserver ipackagebackuprestoreobserver)
        throws RemoteException;

    public abstract void setCustomProgress(int i, int j, int k)
        throws RemoteException;

    public abstract void setIsNeedBeKilled(String s, boolean flag)
        throws RemoteException;

    public abstract boolean shouldSkipData()
        throws RemoteException;

    public abstract void startConfirmationUi(int i, String s)
        throws RemoteException;

    public abstract void writeMiuiBackupHeader(ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;
}
