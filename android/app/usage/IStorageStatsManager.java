// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.os.*;

// Referenced classes of package android.app.usage:
//            ExternalStorageStats, StorageStats

public interface IStorageStatsManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStorageStatsManager
    {

        public static IStorageStatsManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.usage.IStorageStatsManager");
            if(iinterface != null && (iinterface instanceof IStorageStatsManager))
                return (IStorageStatsManager)iinterface;
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
                parcel1.writeString("android.app.usage.IStorageStatsManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                boolean flag = isQuotaSupported(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                long l = getTotalBytes(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                long l1 = getFreeBytes(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                long l2 = getCacheBytes(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l2);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                long l3 = getCacheQuotaBytes(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l3);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                parcel = queryStatsForPackage(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString());
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

            case 7: // '\007'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                parcel = queryStatsForUid(parcel.readString(), parcel.readInt(), parcel.readString());
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

            case 8: // '\b'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                parcel = queryStatsForUser(parcel.readString(), parcel.readInt(), parcel.readString());
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

            case 9: // '\t'
                parcel.enforceInterface("android.app.usage.IStorageStatsManager");
                parcel = queryExternalStatsForUser(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                break;
            }
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

        private static final String DESCRIPTOR = "android.app.usage.IStorageStatsManager";
        static final int TRANSACTION_getCacheBytes = 4;
        static final int TRANSACTION_getCacheQuotaBytes = 5;
        static final int TRANSACTION_getFreeBytes = 3;
        static final int TRANSACTION_getTotalBytes = 2;
        static final int TRANSACTION_isQuotaSupported = 1;
        static final int TRANSACTION_queryExternalStatsForUser = 9;
        static final int TRANSACTION_queryStatsForPackage = 6;
        static final int TRANSACTION_queryStatsForUid = 7;
        static final int TRANSACTION_queryStatsForUser = 8;

        public Stub()
        {
            attachInterface(this, "android.app.usage.IStorageStatsManager");
        }
    }

    private static class Stub.Proxy
        implements IStorageStatsManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public long getCacheBytes(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public long getCacheQuotaBytes(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public long getFreeBytes(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.app.usage.IStorageStatsManager";
        }

        public long getTotalBytes(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public boolean isQuotaSupported(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public ExternalStorageStats queryExternalStatsForUser(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ExternalStorageStats)ExternalStorageStats.CREATOR.createFromParcel(parcel1);
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

        public StorageStats queryStatsForPackage(String s, String s1, int i, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeString(s2);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel1);
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

        public StorageStats queryStatsForUid(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel1);
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

        public StorageStats queryStatsForUser(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IStorageStatsManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel1);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract long getCacheBytes(String s, String s1)
        throws RemoteException;

    public abstract long getCacheQuotaBytes(String s, int i, String s1)
        throws RemoteException;

    public abstract long getFreeBytes(String s, String s1)
        throws RemoteException;

    public abstract long getTotalBytes(String s, String s1)
        throws RemoteException;

    public abstract boolean isQuotaSupported(String s, String s1)
        throws RemoteException;

    public abstract ExternalStorageStats queryExternalStatsForUser(String s, int i, String s1)
        throws RemoteException;

    public abstract StorageStats queryStatsForPackage(String s, String s1, int i, String s2)
        throws RemoteException;

    public abstract StorageStats queryStatsForUid(String s, int i, String s1)
        throws RemoteException;

    public abstract StorageStats queryStatsForUser(String s, int i, String s1)
        throws RemoteException;
}
