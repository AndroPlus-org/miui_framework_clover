// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;
import java.util.List;

// Referenced classes of package android.net:
//            NetworkScorerAppData, INetworkScoreCache, NetworkKey, ScoredNetwork

public interface INetworkScoreService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkScoreService
    {

        public static INetworkScoreService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetworkScoreService");
            if(iinterface != null && (iinterface instanceof INetworkScoreService))
                return (INetworkScoreService)iinterface;
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
                parcel1.writeString("android.net.INetworkScoreService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetworkScoreService");
                boolean flag = updateScores((ScoredNetwork[])parcel.createTypedArray(ScoredNetwork.CREATOR));
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetworkScoreService");
                boolean flag1 = clearScores();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.INetworkScoreService");
                boolean flag2 = setActiveScorer(parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.INetworkScoreService");
                disableScoring();
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.INetworkScoreService");
                registerNetworkScoreCache(parcel.readInt(), INetworkScoreCache.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.INetworkScoreService");
                unregisterNetworkScoreCache(parcel.readInt(), INetworkScoreCache.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.INetworkScoreService");
                boolean flag3 = requestScores((NetworkKey[])parcel.createTypedArray(NetworkKey.CREATOR));
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.INetworkScoreService");
                boolean flag4 = isCallerActiveScorer(parcel.readInt());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.INetworkScoreService");
                parcel = getActiveScorerPackage();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.INetworkScoreService");
                parcel = getActiveScorer();
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

            case 11: // '\013'
                parcel.enforceInterface("android.net.INetworkScoreService");
                parcel = getAllValidScorers();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.INetworkScoreService";
        static final int TRANSACTION_clearScores = 2;
        static final int TRANSACTION_disableScoring = 4;
        static final int TRANSACTION_getActiveScorer = 10;
        static final int TRANSACTION_getActiveScorerPackage = 9;
        static final int TRANSACTION_getAllValidScorers = 11;
        static final int TRANSACTION_isCallerActiveScorer = 8;
        static final int TRANSACTION_registerNetworkScoreCache = 5;
        static final int TRANSACTION_requestScores = 7;
        static final int TRANSACTION_setActiveScorer = 3;
        static final int TRANSACTION_unregisterNetworkScoreCache = 6;
        static final int TRANSACTION_updateScores = 1;

        public Stub()
        {
            attachInterface(this, "android.net.INetworkScoreService");
        }
    }

    private static class Stub.Proxy
        implements INetworkScoreService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean clearScores()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void disableScoring()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public NetworkScorerAppData getActiveScorer()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            NetworkScorerAppData networkscorerappdata = (NetworkScorerAppData)NetworkScorerAppData.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networkscorerappdata;
_L2:
            networkscorerappdata = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getActiveScorerPackage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public List getAllValidScorers()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(NetworkScorerAppData.CREATOR);
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
            return "android.net.INetworkScoreService";
        }

        public boolean isCallerActiveScorer(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void registerNetworkScoreCache(int i, INetworkScoreCache inetworkscorecache, int j)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            parcel.writeInt(i);
            if(inetworkscorecache == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = inetworkscorecache.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inetworkscorecache;
            parcel1.recycle();
            parcel.recycle();
            throw inetworkscorecache;
        }

        public boolean requestScores(NetworkKey anetworkkey[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            parcel.writeTypedArray(anetworkkey, 0);
            mRemote.transact(7, parcel, parcel1, 0);
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
            anetworkkey;
            parcel1.recycle();
            parcel.recycle();
            throw anetworkkey;
        }

        public boolean setActiveScorer(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void unregisterNetworkScoreCache(int i, INetworkScoreCache inetworkscorecache)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            parcel.writeInt(i);
            if(inetworkscorecache == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = inetworkscorecache.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inetworkscorecache;
            parcel1.recycle();
            parcel.recycle();
            throw inetworkscorecache;
        }

        public boolean updateScores(ScoredNetwork ascorednetwork[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetworkScoreService");
            parcel.writeTypedArray(ascorednetwork, 0);
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
            ascorednetwork;
            parcel1.recycle();
            parcel.recycle();
            throw ascorednetwork;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean clearScores()
        throws RemoteException;

    public abstract void disableScoring()
        throws RemoteException;

    public abstract NetworkScorerAppData getActiveScorer()
        throws RemoteException;

    public abstract String getActiveScorerPackage()
        throws RemoteException;

    public abstract List getAllValidScorers()
        throws RemoteException;

    public abstract boolean isCallerActiveScorer(int i)
        throws RemoteException;

    public abstract void registerNetworkScoreCache(int i, INetworkScoreCache inetworkscorecache, int j)
        throws RemoteException;

    public abstract boolean requestScores(NetworkKey anetworkkey[])
        throws RemoteException;

    public abstract boolean setActiveScorer(String s)
        throws RemoteException;

    public abstract void unregisterNetworkScoreCache(int i, INetworkScoreCache inetworkscorecache)
        throws RemoteException;

    public abstract boolean updateScores(ScoredNetwork ascorednetwork[])
        throws RemoteException;
}
