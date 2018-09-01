// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;
import java.util.List;

// Referenced classes of package android.net:
//            ScoredNetwork

public interface INetworkScoreCache
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkScoreCache
    {

        public static INetworkScoreCache asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetworkScoreCache");
            if(iinterface != null && (iinterface instanceof INetworkScoreCache))
                return (INetworkScoreCache)iinterface;
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
                parcel1.writeString("android.net.INetworkScoreCache");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetworkScoreCache");
                updateScores(parcel.createTypedArrayList(ScoredNetwork.CREATOR));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetworkScoreCache");
                clearScores();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.INetworkScoreCache";
        static final int TRANSACTION_clearScores = 2;
        static final int TRANSACTION_updateScores = 1;

        public Stub()
        {
            attachInterface(this, "android.net.INetworkScoreCache");
        }
    }

    private static class Stub.Proxy
        implements INetworkScoreCache
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearScores()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkScoreCache");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.INetworkScoreCache";
        }

        public void updateScores(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkScoreCache");
            parcel.writeTypedList(list);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void clearScores()
        throws RemoteException;

    public abstract void updateScores(List list)
        throws RemoteException;
}
