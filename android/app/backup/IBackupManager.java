// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.content.ComponentName;
import android.content.Intent;
import android.os.*;

// Referenced classes of package android.app.backup:
//            IFullBackupRestoreObserver, IRestoreSession, IBackupObserver, IBackupManagerMonitor, 
//            ISelectBackupTransportCallback

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
            IInterface iinterface = ibinder.queryLocalInterface("android.app.backup.IBackupManager");
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.app.backup.IBackupManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                dataChanged(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                clearBackupData(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                initializeTransports(parcel.createStringArray(), IBackupObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                agentConnected(parcel.readString(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                agentDisconnected(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                restoreAtInstall(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setBackupEnabled(flag);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setAutoRestore(flag1);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                setBackupProvisioned(flag2);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag3 = isBackupEnabled();
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag4 = setBackupPassword(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag5 = hasBackupPassword();
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                backupNow();
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag6;
                ParcelFileDescriptor parcelfiledescriptor;
                boolean flag11;
                boolean flag12;
                boolean flag13;
                boolean flag14;
                boolean flag15;
                boolean flag16;
                boolean flag17;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                if(parcel.readInt() != 0)
                    flag13 = true;
                else
                    flag13 = false;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                adbBackup(parcelfiledescriptor, flag6, flag11, flag12, flag13, flag14, flag15, flag16, flag17, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                fullTransportBackup(parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                if(parcel.readInt() != 0)
                    parcel = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                adbRestore(parcel);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                i = parcel.readInt();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                acknowledgeFullBackupOrRestore(i, flag7, parcel.readString(), parcel.readString(), IFullBackupRestoreObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = getCurrentTransport();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = listAllTransports();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = listAllTransportComponents();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = getTransportWhitelist();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = selectBackupTransport(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                selectBackupTransportAsync(componentname, ISelectBackupTransportCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = getConfigurationIntent(parcel.readString());
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

            case 25: // '\031'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = getDestinationString(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = getDataManagementIntent(parcel.readString());
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

            case 27: // '\033'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = getDataManagementLabel(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                parcel = beginRestoreSession(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                opComplete(parcel.readInt(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                i = parcel.readInt();
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                setBackupServiceActive(i, flag8);
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag9 = isBackupServiceActive(parcel.readInt());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.app.backup.IBackupManager");
                long l = getAvailableRestoreToken(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                boolean flag10 = isAppEligibleForBackup(parcel.readString());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                i = requestBackup(parcel.createStringArray(), IBackupObserver.Stub.asInterface(parcel.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.app.backup.IBackupManager");
                cancelBackups();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.backup.IBackupManager";
        static final int TRANSACTION_acknowledgeFullBackupOrRestore = 17;
        static final int TRANSACTION_adbBackup = 14;
        static final int TRANSACTION_adbRestore = 16;
        static final int TRANSACTION_agentConnected = 4;
        static final int TRANSACTION_agentDisconnected = 5;
        static final int TRANSACTION_backupNow = 13;
        static final int TRANSACTION_beginRestoreSession = 28;
        static final int TRANSACTION_cancelBackups = 35;
        static final int TRANSACTION_clearBackupData = 2;
        static final int TRANSACTION_dataChanged = 1;
        static final int TRANSACTION_fullTransportBackup = 15;
        static final int TRANSACTION_getAvailableRestoreToken = 32;
        static final int TRANSACTION_getConfigurationIntent = 24;
        static final int TRANSACTION_getCurrentTransport = 18;
        static final int TRANSACTION_getDataManagementIntent = 26;
        static final int TRANSACTION_getDataManagementLabel = 27;
        static final int TRANSACTION_getDestinationString = 25;
        static final int TRANSACTION_getTransportWhitelist = 21;
        static final int TRANSACTION_hasBackupPassword = 12;
        static final int TRANSACTION_initializeTransports = 3;
        static final int TRANSACTION_isAppEligibleForBackup = 33;
        static final int TRANSACTION_isBackupEnabled = 10;
        static final int TRANSACTION_isBackupServiceActive = 31;
        static final int TRANSACTION_listAllTransportComponents = 20;
        static final int TRANSACTION_listAllTransports = 19;
        static final int TRANSACTION_opComplete = 29;
        static final int TRANSACTION_requestBackup = 34;
        static final int TRANSACTION_restoreAtInstall = 6;
        static final int TRANSACTION_selectBackupTransport = 22;
        static final int TRANSACTION_selectBackupTransportAsync = 23;
        static final int TRANSACTION_setAutoRestore = 8;
        static final int TRANSACTION_setBackupEnabled = 7;
        static final int TRANSACTION_setBackupPassword = 11;
        static final int TRANSACTION_setBackupProvisioned = 9;
        static final int TRANSACTION_setBackupServiceActive = 30;

        public Stub()
        {
            attachInterface(this, "android.app.backup.IBackupManager");
        }
    }

    private static class Stub.Proxy
        implements IBackupManager
    {

        public void acknowledgeFullBackupOrRestore(int i, boolean flag, String s, String s1, IFullBackupRestoreObserver ifullbackuprestoreobserver)
            throws RemoteException
        {
            Object obj;
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            s = obj;
            if(ifullbackuprestoreobserver == null)
                break MISSING_BLOCK_LABEL_73;
            s = ifullbackuprestoreobserver.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void adbBackup(ParcelFileDescriptor parcelfiledescriptor, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, 
                boolean flag6, boolean flag7, String as[])
            throws RemoteException
        {
            boolean flag8;
            Parcel parcel;
            Parcel parcel1;
            flag8 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_196;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            int i;
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
            if(flag3)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag4)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag5)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag6)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag7)
                i = ((flag8) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeStringArray(as);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void adbRestore(ParcelFileDescriptor parcelfiledescriptor)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, parcel1, 0);
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

        public void agentConnected(String s, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void agentDisconnected(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void backupNow()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
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

        public IRestoreSession beginRestoreSession(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            s = IRestoreSession.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void cancelBackups()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            mRemote.transact(35, parcel, parcel1, 0);
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

        public void clearBackupData(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public void dataChanged(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void fullTransportBackup(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeStringArray(as);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public long getAvailableRestoreToken(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(32, parcel, parcel1, 0);
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

        public Intent getConfigurationIntent(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getCurrentTransport()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public Intent getDataManagementIntent(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getDataManagementLabel(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getDestinationString(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.backup.IBackupManager";
        }

        public String[] getTransportWhitelist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean hasBackupPassword()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void initializeTransports(String as[], IBackupObserver ibackupobserver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeStringArray(as);
            as = obj;
            if(ibackupobserver == null)
                break MISSING_BLOCK_LABEL_38;
            as = ibackupobserver.asBinder();
            parcel.writeStrongBinder(as);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public boolean isAppEligibleForBackup(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(33, parcel, parcel1, 0);
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

        public boolean isBackupEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean isBackupServiceActive(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
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

        public ComponentName[] listAllTransportComponents()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            ComponentName acomponentname[];
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            acomponentname = (ComponentName[])parcel1.createTypedArray(ComponentName.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return acomponentname;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String[] listAllTransports()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void opComplete(int i, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeInt(i);
            parcel.writeLong(l);
            mRemote.transact(29, parcel, parcel1, 0);
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

        public int requestBackup(String as[], IBackupObserver ibackupobserver, IBackupManagerMonitor ibackupmanagermonitor, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeStringArray(as);
            if(ibackupobserver == null)
                break MISSING_BLOCK_LABEL_112;
            as = ibackupobserver.asBinder();
_L1:
            parcel.writeStrongBinder(as);
            as = obj;
            if(ibackupmanagermonitor == null)
                break MISSING_BLOCK_LABEL_57;
            as = ibackupmanagermonitor.asBinder();
            parcel.writeStrongBinder(as);
            parcel.writeInt(i);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            as = null;
              goto _L1
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void restoreAtInstall(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String selectBackupTransport(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void selectBackupTransportAsync(ComponentName componentname, ISelectBackupTransportCallback iselectbackuptransportcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            componentname = obj;
            if(iselectbackuptransportcallback == null)
                break MISSING_BLOCK_LABEL_49;
            componentname = iselectbackuptransportcallback.asBinder();
            parcel.writeStrongBinder(componentname);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setAutoRestore(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void setBackupEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public boolean setBackupPassword(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void setBackupProvisioned(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public void setBackupServiceActive(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void acknowledgeFullBackupOrRestore(int i, boolean flag, String s, String s1, IFullBackupRestoreObserver ifullbackuprestoreobserver)
        throws RemoteException;

    public abstract void adbBackup(ParcelFileDescriptor parcelfiledescriptor, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, 
            boolean flag6, boolean flag7, String as[])
        throws RemoteException;

    public abstract void adbRestore(ParcelFileDescriptor parcelfiledescriptor)
        throws RemoteException;

    public abstract void agentConnected(String s, IBinder ibinder)
        throws RemoteException;

    public abstract void agentDisconnected(String s)
        throws RemoteException;

    public abstract void backupNow()
        throws RemoteException;

    public abstract IRestoreSession beginRestoreSession(String s, String s1)
        throws RemoteException;

    public abstract void cancelBackups()
        throws RemoteException;

    public abstract void clearBackupData(String s, String s1)
        throws RemoteException;

    public abstract void dataChanged(String s)
        throws RemoteException;

    public abstract void fullTransportBackup(String as[])
        throws RemoteException;

    public abstract long getAvailableRestoreToken(String s)
        throws RemoteException;

    public abstract Intent getConfigurationIntent(String s)
        throws RemoteException;

    public abstract String getCurrentTransport()
        throws RemoteException;

    public abstract Intent getDataManagementIntent(String s)
        throws RemoteException;

    public abstract String getDataManagementLabel(String s)
        throws RemoteException;

    public abstract String getDestinationString(String s)
        throws RemoteException;

    public abstract String[] getTransportWhitelist()
        throws RemoteException;

    public abstract boolean hasBackupPassword()
        throws RemoteException;

    public abstract void initializeTransports(String as[], IBackupObserver ibackupobserver)
        throws RemoteException;

    public abstract boolean isAppEligibleForBackup(String s)
        throws RemoteException;

    public abstract boolean isBackupEnabled()
        throws RemoteException;

    public abstract boolean isBackupServiceActive(int i)
        throws RemoteException;

    public abstract ComponentName[] listAllTransportComponents()
        throws RemoteException;

    public abstract String[] listAllTransports()
        throws RemoteException;

    public abstract void opComplete(int i, long l)
        throws RemoteException;

    public abstract int requestBackup(String as[], IBackupObserver ibackupobserver, IBackupManagerMonitor ibackupmanagermonitor, int i)
        throws RemoteException;

    public abstract void restoreAtInstall(String s, int i)
        throws RemoteException;

    public abstract String selectBackupTransport(String s)
        throws RemoteException;

    public abstract void selectBackupTransportAsync(ComponentName componentname, ISelectBackupTransportCallback iselectbackuptransportcallback)
        throws RemoteException;

    public abstract void setAutoRestore(boolean flag)
        throws RemoteException;

    public abstract void setBackupEnabled(boolean flag)
        throws RemoteException;

    public abstract boolean setBackupPassword(String s, String s1)
        throws RemoteException;

    public abstract void setBackupProvisioned(boolean flag)
        throws RemoteException;

    public abstract void setBackupServiceActive(int i, boolean flag)
        throws RemoteException;
}
