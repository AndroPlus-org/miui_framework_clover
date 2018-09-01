// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            NetworkTemplate, NetworkStats, NetworkStatsHistory

public interface INetworkStatsSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkStatsSession
    {

        public static INetworkStatsSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetworkStatsSession");
            if(iinterface != null && (iinterface instanceof INetworkStatsSession))
                return (INetworkStatsSession)iinterface;
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
                parcel1.writeString("android.net.INetworkStatsSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetworkStatsSession");
                NetworkTemplate networktemplate;
                if(parcel.readInt() != 0)
                    networktemplate = (NetworkTemplate)NetworkTemplate.CREATOR.createFromParcel(parcel);
                else
                    networktemplate = null;
                parcel = getDeviceSummaryForNetwork(networktemplate, parcel.readLong(), parcel.readLong());
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

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetworkStatsSession");
                NetworkTemplate networktemplate1;
                if(parcel.readInt() != 0)
                    networktemplate1 = (NetworkTemplate)NetworkTemplate.CREATOR.createFromParcel(parcel);
                else
                    networktemplate1 = null;
                parcel = getSummaryForNetwork(networktemplate1, parcel.readLong(), parcel.readLong());
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
                parcel.enforceInterface("android.net.INetworkStatsSession");
                NetworkTemplate networktemplate2;
                if(parcel.readInt() != 0)
                    networktemplate2 = (NetworkTemplate)NetworkTemplate.CREATOR.createFromParcel(parcel);
                else
                    networktemplate2 = null;
                parcel = getHistoryForNetwork(networktemplate2, parcel.readInt());
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

            case 4: // '\004'
                parcel.enforceInterface("android.net.INetworkStatsSession");
                NetworkTemplate networktemplate3;
                long l;
                long l1;
                boolean flag;
                if(parcel.readInt() != 0)
                    networktemplate3 = (NetworkTemplate)NetworkTemplate.CREATOR.createFromParcel(parcel);
                else
                    networktemplate3 = null;
                l = parcel.readLong();
                l1 = parcel.readLong();
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                parcel = getSummaryForAllUid(networktemplate3, l, l1, flag);
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
                parcel.enforceInterface("android.net.INetworkStatsSession");
                NetworkTemplate networktemplate4;
                if(parcel.readInt() != 0)
                    networktemplate4 = (NetworkTemplate)NetworkTemplate.CREATOR.createFromParcel(parcel);
                else
                    networktemplate4 = null;
                parcel = getHistoryForUid(networktemplate4, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
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

            case 6: // '\006'
                parcel.enforceInterface("android.net.INetworkStatsSession");
                NetworkTemplate networktemplate5;
                if(parcel.readInt() != 0)
                    networktemplate5 = (NetworkTemplate)NetworkTemplate.CREATOR.createFromParcel(parcel);
                else
                    networktemplate5 = null;
                parcel = getHistoryIntervalForUid(networktemplate5, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readLong());
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
                parcel.enforceInterface("android.net.INetworkStatsSession");
                parcel = getRelevantUids();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.INetworkStatsSession");
                close();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.INetworkStatsSession";
        static final int TRANSACTION_close = 8;
        static final int TRANSACTION_getDeviceSummaryForNetwork = 1;
        static final int TRANSACTION_getHistoryForNetwork = 3;
        static final int TRANSACTION_getHistoryForUid = 5;
        static final int TRANSACTION_getHistoryIntervalForUid = 6;
        static final int TRANSACTION_getRelevantUids = 7;
        static final int TRANSACTION_getSummaryForAllUid = 4;
        static final int TRANSACTION_getSummaryForNetwork = 2;

        public Stub()
        {
            attachInterface(this, "android.net.INetworkStatsSession");
        }
    }

    private static class Stub.Proxy
        implements INetworkStatsSession
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void close()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsSession");
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

        public NetworkStats getDeviceSummaryForNetwork(NetworkTemplate networktemplate, long l, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsSession");
            if(networktemplate == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networktemplate.writeToParcel(parcel, 0);
_L3:
            parcel.writeLong(l);
            parcel.writeLong(l1);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            networktemplate = (NetworkStats)NetworkStats.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networktemplate;
_L2:
            parcel.writeInt(0);
              goto _L3
            networktemplate;
            parcel1.recycle();
            parcel.recycle();
            throw networktemplate;
            networktemplate = null;
              goto _L4
        }

        public NetworkStatsHistory getHistoryForNetwork(NetworkTemplate networktemplate, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsSession");
            if(networktemplate == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networktemplate.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_108;
            networktemplate = (NetworkStatsHistory)NetworkStatsHistory.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networktemplate;
_L2:
            parcel.writeInt(0);
              goto _L3
            networktemplate;
            parcel1.recycle();
            parcel.recycle();
            throw networktemplate;
            networktemplate = null;
              goto _L4
        }

        public NetworkStatsHistory getHistoryForUid(NetworkTemplate networktemplate, int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsSession");
            if(networktemplate == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networktemplate.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_137;
            networktemplate = (NetworkStatsHistory)NetworkStatsHistory.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networktemplate;
_L2:
            parcel.writeInt(0);
              goto _L3
            networktemplate;
            parcel1.recycle();
            parcel.recycle();
            throw networktemplate;
            networktemplate = null;
              goto _L4
        }

        public NetworkStatsHistory getHistoryIntervalForUid(NetworkTemplate networktemplate, int i, int j, int k, int l, long l1, 
                long l2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsSession");
            if(networktemplate == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networktemplate.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeLong(l1);
            parcel.writeLong(l2);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_152;
            networktemplate = (NetworkStatsHistory)NetworkStatsHistory.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networktemplate;
_L2:
            parcel.writeInt(0);
              goto _L3
            networktemplate;
            parcel1.recycle();
            parcel.recycle();
            throw networktemplate;
            networktemplate = null;
              goto _L4
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.INetworkStatsSession";
        }

        public int[] getRelevantUids()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.net.INetworkStatsSession");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public NetworkStats getSummaryForAllUid(NetworkTemplate networktemplate, long l, long l1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsSession");
            if(networktemplate == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networktemplate.writeToParcel(parcel, 0);
_L3:
            parcel.writeLong(l);
            parcel.writeLong(l1);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_145;
            networktemplate = (NetworkStats)NetworkStats.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networktemplate;
_L2:
            parcel.writeInt(0);
              goto _L3
            networktemplate;
            parcel1.recycle();
            parcel.recycle();
            throw networktemplate;
            networktemplate = null;
              goto _L4
        }

        public NetworkStats getSummaryForNetwork(NetworkTemplate networktemplate, long l, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkStatsSession");
            if(networktemplate == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networktemplate.writeToParcel(parcel, 0);
_L3:
            parcel.writeLong(l);
            parcel.writeLong(l1);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            networktemplate = (NetworkStats)NetworkStats.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networktemplate;
_L2:
            parcel.writeInt(0);
              goto _L3
            networktemplate;
            parcel1.recycle();
            parcel.recycle();
            throw networktemplate;
            networktemplate = null;
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void close()
        throws RemoteException;

    public abstract NetworkStats getDeviceSummaryForNetwork(NetworkTemplate networktemplate, long l, long l1)
        throws RemoteException;

    public abstract NetworkStatsHistory getHistoryForNetwork(NetworkTemplate networktemplate, int i)
        throws RemoteException;

    public abstract NetworkStatsHistory getHistoryForUid(NetworkTemplate networktemplate, int i, int j, int k, int l)
        throws RemoteException;

    public abstract NetworkStatsHistory getHistoryIntervalForUid(NetworkTemplate networktemplate, int i, int j, int k, int l, long l1, 
            long l2)
        throws RemoteException;

    public abstract int[] getRelevantUids()
        throws RemoteException;

    public abstract NetworkStats getSummaryForAllUid(NetworkTemplate networktemplate, long l, long l1, boolean flag)
        throws RemoteException;

    public abstract NetworkStats getSummaryForNetwork(NetworkTemplate networktemplate, long l, long l1)
        throws RemoteException;
}
