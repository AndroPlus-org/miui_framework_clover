// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            NetworkStats, NetworkTemplate, INetworkStatsSession, DataUsageRequest

public interface INetworkStatsService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkStatsService
    {

        public static INetworkStatsService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetworkStatsService");
            if(iinterface != null && (iinterface instanceof INetworkStatsService))
                return (INetworkStatsService)iinterface;
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
                parcel1.writeString("android.net.INetworkStatsService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetworkStatsService");
                parcel = openSession();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetworkStatsService");
                parcel = openSessionForUsageStats(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.INetworkStatsService");
                NetworkTemplate networktemplate;
                long l;
                if(parcel.readInt() != 0)
                    networktemplate = (NetworkTemplate)NetworkTemplate.CREATOR.createFromParcel(parcel);
                else
                    networktemplate = null;
                l = getNetworkTotalBytes(networktemplate, parcel.readLong(), parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.INetworkStatsService");
                parcel = getDataLayerSnapshotForUid(parcel.readInt());
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
                parcel.enforceInterface("android.net.INetworkStatsService");
                parcel = getMobileIfaces();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.INetworkStatsService");
                incrementOperationCount(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.INetworkStatsService");
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setUidForeground(i, flag);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.INetworkStatsService");
                forceUpdateIfaces();
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.INetworkStatsService");
                forceUpdate();
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.INetworkStatsService");
                advisePersistThreshold(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.INetworkStatsService");
                String s = parcel.readString();
                DataUsageRequest datausagerequest;
                Messenger messenger;
                if(parcel.readInt() != 0)
                    datausagerequest = (DataUsageRequest)DataUsageRequest.CREATOR.createFromParcel(parcel);
                else
                    datausagerequest = null;
                if(parcel.readInt() != 0)
                    messenger = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger = null;
                parcel = registerUsageCallback(s, datausagerequest, messenger, parcel.readStrongBinder());
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

            case 12: // '\f'
                parcel.enforceInterface("android.net.INetworkStatsService");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (DataUsageRequest)DataUsageRequest.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            unregisterUsageRequest(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.net.INetworkStatsService";
        static final int TRANSACTION_advisePersistThreshold = 10;
        static final int TRANSACTION_forceUpdate = 9;
        static final int TRANSACTION_forceUpdateIfaces = 8;
        static final int TRANSACTION_getDataLayerSnapshotForUid = 4;
        static final int TRANSACTION_getMobileIfaces = 5;
        static final int TRANSACTION_getNetworkTotalBytes = 3;
        static final int TRANSACTION_incrementOperationCount = 6;
        static final int TRANSACTION_openSession = 1;
        static final int TRANSACTION_openSessionForUsageStats = 2;
        static final int TRANSACTION_registerUsageCallback = 11;
        static final int TRANSACTION_setUidForeground = 7;
        static final int TRANSACTION_unregisterUsageRequest = 12;

        public Stub()
        {
            attachInterface(this, "android.net.INetworkStatsService");
        }
    }

    private static class Stub.Proxy
        implements INetworkStatsService
    {

        public void advisePersistThreshold(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            parcel.writeLong(l);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public void forceUpdate()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
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

        public void forceUpdateIfaces()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
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

        public NetworkStats getDataLayerSnapshotForUid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            NetworkStats networkstats = (NetworkStats)NetworkStats.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networkstats;
_L2:
            networkstats = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.INetworkStatsService";
        }

        public String[] getMobileIfaces()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public long getNetworkTotalBytes(NetworkTemplate networktemplate, long l, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            if(networktemplate == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            networktemplate.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            parcel.writeLong(l1);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            parcel.writeInt(0);
              goto _L1
            networktemplate;
            parcel1.recycle();
            parcel.recycle();
            throw networktemplate;
        }

        public void incrementOperationCount(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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

        public INetworkStatsSession openSession()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            INetworkStatsSession inetworkstatssession;
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            inetworkstatssession = INetworkStatsSession.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return inetworkstatssession;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public INetworkStatsSession openSessionForUsageStats(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = INetworkStatsSession.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public DataUsageRequest registerUsageCallback(String s, DataUsageRequest datausagerequest, Messenger messenger, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            parcel.writeString(s);
            if(datausagerequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            datausagerequest.writeToParcel(parcel, 0);
_L5:
            if(messenger == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L6:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_151;
            s = (DataUsageRequest)DataUsageRequest.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L5
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L6
            s = null;
              goto _L7
        }

        public void setUidForeground(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
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

        public void unregisterUsageRequest(DataUsageRequest datausagerequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsService");
            if(datausagerequest == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            datausagerequest.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            datausagerequest;
            parcel1.recycle();
            parcel.recycle();
            throw datausagerequest;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void advisePersistThreshold(long l)
        throws RemoteException;

    public abstract void forceUpdate()
        throws RemoteException;

    public abstract void forceUpdateIfaces()
        throws RemoteException;

    public abstract NetworkStats getDataLayerSnapshotForUid(int i)
        throws RemoteException;

    public abstract String[] getMobileIfaces()
        throws RemoteException;

    public abstract long getNetworkTotalBytes(NetworkTemplate networktemplate, long l, long l1)
        throws RemoteException;

    public abstract void incrementOperationCount(int i, int j, int k)
        throws RemoteException;

    public abstract INetworkStatsSession openSession()
        throws RemoteException;

    public abstract INetworkStatsSession openSessionForUsageStats(int i, String s)
        throws RemoteException;

    public abstract DataUsageRequest registerUsageCallback(String s, DataUsageRequest datausagerequest, Messenger messenger, IBinder ibinder)
        throws RemoteException;

    public abstract void setUidForeground(int i, boolean flag)
        throws RemoteException;

    public abstract void unregisterUsageRequest(DataUsageRequest datausagerequest)
        throws RemoteException;
}
