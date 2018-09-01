// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.accounts.Account;
import android.database.IContentObserver;
import android.net.Uri;
import android.os.*;
import java.util.List;

// Referenced classes of package android.content:
//            ISyncStatusObserver, SyncRequest, ComponentName, SyncAdapterType, 
//            SyncStatusInfo, SyncInfo, PeriodicSync

public interface IContentService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IContentService
    {

        public static IContentService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.IContentService");
            if(iinterface != null && (iinterface instanceof IContentService))
                return (IContentService)iinterface;
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
            String s9;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.content.IContentService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.IContentService");
                unregisterContentObserver(android.database.IContentObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.IContentService");
                Uri uri;
                boolean flag;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                registerContentObserver(uri, flag, android.database.IContentObserver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.IContentService");
                Uri uri1;
                boolean flag1;
                IContentObserver icontentobserver;
                if(parcel.readInt() != 0)
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri1 = null;
                icontentobserver = android.database.IContentObserver.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                notifyChange(uri1, icontentobserver, flag1, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.content.IContentService");
                Account account;
                String s;
                if(parcel.readInt() != 0)
                    account = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account = null;
                s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestSync(account, s, parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.IContentService");
                if(parcel.readInt() != 0)
                    parcel = (SyncRequest)SyncRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sync(parcel);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.IContentService");
                SyncRequest syncrequest;
                if(parcel.readInt() != 0)
                    syncrequest = (SyncRequest)SyncRequest.CREATOR.createFromParcel(parcel);
                else
                    syncrequest = null;
                syncAsUser(syncrequest, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.content.IContentService");
                Account account1;
                String s1;
                if(parcel.readInt() != 0)
                    account1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account1 = null;
                s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                cancelSync(account1, s1, parcel);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.content.IContentService");
                Account account2;
                ComponentName componentname;
                String s10;
                if(parcel.readInt() != 0)
                    account2 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account2 = null;
                s10 = parcel.readString();
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                cancelSyncAsUser(account2, s10, componentname, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.content.IContentService");
                if(parcel.readInt() != 0)
                    parcel = (SyncRequest)SyncRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                cancelRequest(parcel);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.content.IContentService");
                Account account3;
                boolean flag2;
                if(parcel.readInt() != 0)
                    account3 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account3 = null;
                flag2 = getSyncAutomatically(account3, parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.content.IContentService");
                Account account4;
                boolean flag3;
                if(parcel.readInt() != 0)
                    account4 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account4 = null;
                flag3 = getSyncAutomaticallyAsUser(account4, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.content.IContentService");
                Account account5;
                boolean flag4;
                String s2;
                if(parcel.readInt() != 0)
                    account5 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account5 = null;
                s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setSyncAutomatically(account5, s2, flag4);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.content.IContentService");
                Account account6;
                boolean flag5;
                String s3;
                if(parcel.readInt() != 0)
                    account6 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account6 = null;
                s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                setSyncAutomaticallyAsUser(account6, s3, flag5, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.content.IContentService");
                Account account7;
                String s4;
                if(parcel.readInt() != 0)
                    account7 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account7 = null;
                s4 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPeriodicSyncs(account7, s4, parcel);
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.content.IContentService");
                Account account8;
                Bundle bundle;
                String s11;
                if(parcel.readInt() != 0)
                    account8 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account8 = null;
                s11 = parcel.readString();
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                addPeriodicSync(account8, s11, bundle, parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.content.IContentService");
                Account account9;
                String s5;
                if(parcel.readInt() != 0)
                    account9 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account9 = null;
                s5 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                removePeriodicSync(account9, s5, parcel);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.content.IContentService");
                Account account10;
                if(parcel.readInt() != 0)
                    account10 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account10 = null;
                i = getIsSyncable(account10, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.content.IContentService");
                Account account11;
                if(parcel.readInt() != 0)
                    account11 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account11 = null;
                i = getIsSyncableAsUser(account11, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.content.IContentService");
                Account account12;
                if(parcel.readInt() != 0)
                    account12 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account12 = null;
                setIsSyncable(account12, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.content.IContentService");
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                setMasterSyncAutomatically(flag6);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.content.IContentService");
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                setMasterSyncAutomaticallyAsUser(flag7, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.content.IContentService");
                boolean flag8 = getMasterSyncAutomatically();
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.content.IContentService");
                boolean flag9 = getMasterSyncAutomaticallyAsUser(parcel.readInt());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.content.IContentService");
                parcel = getCurrentSyncs();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.content.IContentService");
                parcel = getCurrentSyncsAsUser(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.content.IContentService");
                parcel = getSyncAdapterTypes();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.content.IContentService");
                parcel = getSyncAdapterTypesAsUser(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.content.IContentService");
                parcel = getSyncAdapterPackagesForAuthorityAsUser(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.content.IContentService");
                Account account13;
                boolean flag10;
                String s6;
                if(parcel.readInt() != 0)
                    account13 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account13 = null;
                s6 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag10 = isSyncActive(account13, s6, parcel);
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.content.IContentService");
                Account account14;
                String s7;
                if(parcel.readInt() != 0)
                    account14 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account14 = null;
                s7 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getSyncStatus(account14, s7, parcel);
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

            case 31: // '\037'
                parcel.enforceInterface("android.content.IContentService");
                Account account15;
                ComponentName componentname1;
                String s12;
                if(parcel.readInt() != 0)
                    account15 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account15 = null;
                s12 = parcel.readString();
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                parcel = getSyncStatusAsUser(account15, s12, componentname1, parcel.readInt());
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

            case 32: // ' '
                parcel.enforceInterface("android.content.IContentService");
                Account account16;
                boolean flag11;
                String s8;
                if(parcel.readInt() != 0)
                    account16 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account16 = null;
                s8 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag11 = isSyncPending(account16, s8, parcel);
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.content.IContentService");
                Account account17;
                boolean flag12;
                ComponentName componentname2;
                String s13;
                if(parcel.readInt() != 0)
                    account17 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account17 = null;
                s13 = parcel.readString();
                if(parcel.readInt() != 0)
                    componentname2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname2 = null;
                flag12 = isSyncPendingAsUser(account17, s13, componentname2, parcel.readInt());
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.content.IContentService");
                addStatusChangeListener(parcel.readInt(), ISyncStatusObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.content.IContentService");
                removeStatusChangeListener(ISyncStatusObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.content.IContentService");
                String s14 = parcel.readString();
                Uri uri2;
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri2 = null;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                putCache(s14, uri2, bundle1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.content.IContentService");
                s9 = parcel.readString();
                break;
            }
            Uri uri3;
            if(parcel.readInt() != 0)
                uri3 = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                uri3 = null;
            parcel = getCache(s9, uri3, parcel.readInt());
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
        }

        private static final String DESCRIPTOR = "android.content.IContentService";
        static final int TRANSACTION_addPeriodicSync = 15;
        static final int TRANSACTION_addStatusChangeListener = 34;
        static final int TRANSACTION_cancelRequest = 9;
        static final int TRANSACTION_cancelSync = 7;
        static final int TRANSACTION_cancelSyncAsUser = 8;
        static final int TRANSACTION_getCache = 37;
        static final int TRANSACTION_getCurrentSyncs = 24;
        static final int TRANSACTION_getCurrentSyncsAsUser = 25;
        static final int TRANSACTION_getIsSyncable = 17;
        static final int TRANSACTION_getIsSyncableAsUser = 18;
        static final int TRANSACTION_getMasterSyncAutomatically = 22;
        static final int TRANSACTION_getMasterSyncAutomaticallyAsUser = 23;
        static final int TRANSACTION_getPeriodicSyncs = 14;
        static final int TRANSACTION_getSyncAdapterPackagesForAuthorityAsUser = 28;
        static final int TRANSACTION_getSyncAdapterTypes = 26;
        static final int TRANSACTION_getSyncAdapterTypesAsUser = 27;
        static final int TRANSACTION_getSyncAutomatically = 10;
        static final int TRANSACTION_getSyncAutomaticallyAsUser = 11;
        static final int TRANSACTION_getSyncStatus = 30;
        static final int TRANSACTION_getSyncStatusAsUser = 31;
        static final int TRANSACTION_isSyncActive = 29;
        static final int TRANSACTION_isSyncPending = 32;
        static final int TRANSACTION_isSyncPendingAsUser = 33;
        static final int TRANSACTION_notifyChange = 3;
        static final int TRANSACTION_putCache = 36;
        static final int TRANSACTION_registerContentObserver = 2;
        static final int TRANSACTION_removePeriodicSync = 16;
        static final int TRANSACTION_removeStatusChangeListener = 35;
        static final int TRANSACTION_requestSync = 4;
        static final int TRANSACTION_setIsSyncable = 19;
        static final int TRANSACTION_setMasterSyncAutomatically = 20;
        static final int TRANSACTION_setMasterSyncAutomaticallyAsUser = 21;
        static final int TRANSACTION_setSyncAutomatically = 12;
        static final int TRANSACTION_setSyncAutomaticallyAsUser = 13;
        static final int TRANSACTION_sync = 5;
        static final int TRANSACTION_syncAsUser = 6;
        static final int TRANSACTION_unregisterContentObserver = 1;

        public Stub()
        {
            attachInterface(this, "android.content.IContentService");
        }
    }

    private static class Stub.Proxy
        implements IContentService
    {

        public void addPeriodicSync(Account account, String s, Bundle bundle, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            parcel.writeLong(l);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public void addStatusChangeListener(int i, ISyncStatusObserver isyncstatusobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            parcel.writeInt(i);
            if(isyncstatusobserver == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = isyncstatusobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            isyncstatusobserver;
            parcel1.recycle();
            parcel.recycle();
            throw isyncstatusobserver;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelRequest(SyncRequest syncrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(syncrequest == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            syncrequest.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            syncrequest;
            parcel1.recycle();
            parcel.recycle();
            throw syncrequest;
        }

        public void cancelSync(Account account, String s, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public void cancelSyncAsUser(Account account, String s, ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public Bundle getCache(String s, Uri uri, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            parcel.writeString(s);
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            s = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            s = null;
              goto _L4
        }

        public List getCurrentSyncs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.content.IContentService");
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(SyncInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getCurrentSyncsAsUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.content.IContentService");
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(SyncInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.IContentService";
        }

        public int getIsSyncable(Account account, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public int getIsSyncableAsUser(Account account, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public boolean getMasterSyncAutomatically()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.IContentService");
            mRemote.transact(22, parcel, parcel1, 0);
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

        public boolean getMasterSyncAutomaticallyAsUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public List getPeriodicSyncs(Account account, String s, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_122;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            account = parcel1.createTypedArrayList(PeriodicSync.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return account;
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public String[] getSyncAdapterPackagesForAuthorityAsUser(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public SyncAdapterType[] getSyncAdapterTypes()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            SyncAdapterType asyncadaptertype[];
            parcel.writeInterfaceToken("android.content.IContentService");
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            asyncadaptertype = (SyncAdapterType[])parcel1.createTypedArray(SyncAdapterType.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return asyncadaptertype;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public SyncAdapterType[] getSyncAdapterTypesAsUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            SyncAdapterType asyncadaptertype[];
            parcel.writeInterfaceToken("android.content.IContentService");
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            asyncadaptertype = (SyncAdapterType[])parcel1.createTypedArray(SyncAdapterType.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return asyncadaptertype;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getSyncAutomatically(Account account, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
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
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public boolean getSyncAutomaticallyAsUser(Account account, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
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
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public SyncStatusInfo getSyncStatus(Account account, String s, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L5:
            parcel.writeString(s);
            if(componentname == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_144;
            account = (SyncStatusInfo)SyncStatusInfo.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return account;
_L2:
            parcel.writeInt(0);
              goto _L5
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
_L4:
            parcel.writeInt(0);
              goto _L6
            account = null;
              goto _L7
        }

        public SyncStatusInfo getSyncStatusAsUser(Account account, String s, ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L5:
            parcel.writeString(s);
            if(componentname == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_151;
            account = (SyncStatusInfo)SyncStatusInfo.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return account;
_L2:
            parcel.writeInt(0);
              goto _L5
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
_L4:
            parcel.writeInt(0);
              goto _L6
            account = null;
              goto _L7
        }

        public boolean isSyncActive(Account account, String s, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_129;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(29, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean isSyncPending(Account account, String s, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_129;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(32, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean isSyncPendingAsUser(Account account, String s, ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_136;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public void notifyChange(Uri uri, IContentObserver icontentobserver, boolean flag, int i, int j, int k)
            throws RemoteException
        {
            int l;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            l = 1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L4:
            uri = obj;
            if(icontentobserver == null)
                break MISSING_BLOCK_LABEL_54;
            uri = icontentobserver.asBinder();
            parcel.writeStrongBinder(uri);
            if(!flag)
                l = 0;
            parcel.writeInt(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public void putCache(String s, Uri uri, Bundle bundle, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            parcel.writeString(s);
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void registerContentObserver(Uri uri, boolean flag, IContentObserver icontentobserver, int i, int j)
            throws RemoteException
        {
            int k;
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            k = 1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(uri == null)
                break MISSING_BLOCK_LABEL_117;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                k = 0;
            parcel.writeInt(k);
            uri = obj;
            if(icontentobserver == null)
                break MISSING_BLOCK_LABEL_65;
            uri = icontentobserver.asBinder();
            parcel.writeStrongBinder(uri);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public void removePeriodicSync(Account account, String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public void removeStatusChangeListener(ISyncStatusObserver isyncstatusobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(isyncstatusobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = isyncstatusobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            isyncstatusobserver;
            parcel1.recycle();
            parcel.recycle();
            throw isyncstatusobserver;
        }

        public void requestSync(Account account, String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public void setIsSyncable(Account account, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void setMasterSyncAutomatically(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public void setMasterSyncAutomaticallyAsUser(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public void setSyncAutomatically(Account account, String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void setSyncAutomaticallyAsUser(Account account, String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(account == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void sync(SyncRequest syncrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(syncrequest == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            syncrequest.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            syncrequest;
            parcel1.recycle();
            parcel.recycle();
            throw syncrequest;
        }

        public void syncAsUser(SyncRequest syncrequest, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(syncrequest == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            syncrequest.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            syncrequest;
            parcel1.recycle();
            parcel.recycle();
            throw syncrequest;
        }

        public void unregisterContentObserver(IContentObserver icontentobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IContentService");
            if(icontentobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = icontentobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            icontentobserver;
            parcel1.recycle();
            parcel.recycle();
            throw icontentobserver;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addPeriodicSync(Account account, String s, Bundle bundle, long l)
        throws RemoteException;

    public abstract void addStatusChangeListener(int i, ISyncStatusObserver isyncstatusobserver)
        throws RemoteException;

    public abstract void cancelRequest(SyncRequest syncrequest)
        throws RemoteException;

    public abstract void cancelSync(Account account, String s, ComponentName componentname)
        throws RemoteException;

    public abstract void cancelSyncAsUser(Account account, String s, ComponentName componentname, int i)
        throws RemoteException;

    public abstract Bundle getCache(String s, Uri uri, int i)
        throws RemoteException;

    public abstract List getCurrentSyncs()
        throws RemoteException;

    public abstract List getCurrentSyncsAsUser(int i)
        throws RemoteException;

    public abstract int getIsSyncable(Account account, String s)
        throws RemoteException;

    public abstract int getIsSyncableAsUser(Account account, String s, int i)
        throws RemoteException;

    public abstract boolean getMasterSyncAutomatically()
        throws RemoteException;

    public abstract boolean getMasterSyncAutomaticallyAsUser(int i)
        throws RemoteException;

    public abstract List getPeriodicSyncs(Account account, String s, ComponentName componentname)
        throws RemoteException;

    public abstract String[] getSyncAdapterPackagesForAuthorityAsUser(String s, int i)
        throws RemoteException;

    public abstract SyncAdapterType[] getSyncAdapterTypes()
        throws RemoteException;

    public abstract SyncAdapterType[] getSyncAdapterTypesAsUser(int i)
        throws RemoteException;

    public abstract boolean getSyncAutomatically(Account account, String s)
        throws RemoteException;

    public abstract boolean getSyncAutomaticallyAsUser(Account account, String s, int i)
        throws RemoteException;

    public abstract SyncStatusInfo getSyncStatus(Account account, String s, ComponentName componentname)
        throws RemoteException;

    public abstract SyncStatusInfo getSyncStatusAsUser(Account account, String s, ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean isSyncActive(Account account, String s, ComponentName componentname)
        throws RemoteException;

    public abstract boolean isSyncPending(Account account, String s, ComponentName componentname)
        throws RemoteException;

    public abstract boolean isSyncPendingAsUser(Account account, String s, ComponentName componentname, int i)
        throws RemoteException;

    public abstract void notifyChange(Uri uri, IContentObserver icontentobserver, boolean flag, int i, int j, int k)
        throws RemoteException;

    public abstract void putCache(String s, Uri uri, Bundle bundle, int i)
        throws RemoteException;

    public abstract void registerContentObserver(Uri uri, boolean flag, IContentObserver icontentobserver, int i, int j)
        throws RemoteException;

    public abstract void removePeriodicSync(Account account, String s, Bundle bundle)
        throws RemoteException;

    public abstract void removeStatusChangeListener(ISyncStatusObserver isyncstatusobserver)
        throws RemoteException;

    public abstract void requestSync(Account account, String s, Bundle bundle)
        throws RemoteException;

    public abstract void setIsSyncable(Account account, String s, int i)
        throws RemoteException;

    public abstract void setMasterSyncAutomatically(boolean flag)
        throws RemoteException;

    public abstract void setMasterSyncAutomaticallyAsUser(boolean flag, int i)
        throws RemoteException;

    public abstract void setSyncAutomatically(Account account, String s, boolean flag)
        throws RemoteException;

    public abstract void setSyncAutomaticallyAsUser(Account account, String s, boolean flag, int i)
        throws RemoteException;

    public abstract void sync(SyncRequest syncrequest)
        throws RemoteException;

    public abstract void syncAsUser(SyncRequest syncrequest, int i)
        throws RemoteException;

    public abstract void unregisterContentObserver(IContentObserver icontentobserver)
        throws RemoteException;
}
