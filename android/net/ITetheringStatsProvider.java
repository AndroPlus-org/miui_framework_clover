// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            NetworkStats

public interface ITetheringStatsProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITetheringStatsProvider
    {

        public static ITetheringStatsProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.ITetheringStatsProvider");
            if(iinterface != null && (iinterface instanceof ITetheringStatsProvider))
                return (ITetheringStatsProvider)iinterface;
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
                parcel1.writeString("android.net.ITetheringStatsProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.ITetheringStatsProvider");
                parcel = getTetherStats(parcel.readInt());
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
                parcel.enforceInterface("android.net.ITetheringStatsProvider");
                setInterfaceQuota(parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.ITetheringStatsProvider";
        static final int TRANSACTION_getTetherStats = 1;
        static final int TRANSACTION_setInterfaceQuota = 2;

        public Stub()
        {
            attachInterface(this, "android.net.ITetheringStatsProvider");
        }
    }

    private static class Stub.Proxy
        implements ITetheringStatsProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.ITetheringStatsProvider";
        }

        public NetworkStats getTetherStats(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.ITetheringStatsProvider");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void setInterfaceQuota(String s, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.ITetheringStatsProvider");
            parcel.writeString(s);
            parcel.writeLong(l);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract NetworkStats getTetherStats(int i)
        throws RemoteException;

    public abstract void setInterfaceQuota(String s, long l)
        throws RemoteException;

    public static final int QUOTA_UNLIMITED = -1;
}
