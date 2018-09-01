// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice.recommendation;

import android.os.*;
import java.util.List;

// Referenced classes of package android.printservice.recommendation:
//            RecommendationInfo

public interface IRecommendationServiceCallbacks
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRecommendationServiceCallbacks
    {

        public static IRecommendationServiceCallbacks asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.printservice.recommendation.IRecommendationServiceCallbacks");
            if(iinterface != null && (iinterface instanceof IRecommendationServiceCallbacks))
                return (IRecommendationServiceCallbacks)iinterface;
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
                parcel1.writeString("android.printservice.recommendation.IRecommendationServiceCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.printservice.recommendation.IRecommendationServiceCallbacks");
                onRecommendationsUpdated(parcel.createTypedArrayList(RecommendationInfo.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.printservice.recommendation.IRecommendationServiceCallbacks";
        static final int TRANSACTION_onRecommendationsUpdated = 1;

        public Stub()
        {
            attachInterface(this, "android.printservice.recommendation.IRecommendationServiceCallbacks");
        }
    }

    private static class Stub.Proxy
        implements IRecommendationServiceCallbacks
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.printservice.recommendation.IRecommendationServiceCallbacks";
        }

        public void onRecommendationsUpdated(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.recommendation.IRecommendationServiceCallbacks");
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


    public abstract void onRecommendationsUpdated(List list)
        throws RemoteException;
}
