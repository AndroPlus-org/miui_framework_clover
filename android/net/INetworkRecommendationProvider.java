// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            NetworkKey

public interface INetworkRecommendationProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkRecommendationProvider
    {

        public static INetworkRecommendationProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetworkRecommendationProvider");
            if(iinterface != null && (iinterface instanceof INetworkRecommendationProvider))
                return (INetworkRecommendationProvider)iinterface;
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
                parcel1.writeString("android.net.INetworkRecommendationProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetworkRecommendationProvider");
                requestScores((NetworkKey[])parcel.createTypedArray(NetworkKey.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.INetworkRecommendationProvider";
        static final int TRANSACTION_requestScores = 1;

        public Stub()
        {
            attachInterface(this, "android.net.INetworkRecommendationProvider");
        }
    }

    private static class Stub.Proxy
        implements INetworkRecommendationProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.INetworkRecommendationProvider";
        }

        public void requestScores(NetworkKey anetworkkey[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkRecommendationProvider");
            parcel.writeTypedArray(anetworkkey, 0);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            anetworkkey;
            parcel.recycle();
            throw anetworkkey;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void requestScores(NetworkKey anetworkkey[])
        throws RemoteException;
}
