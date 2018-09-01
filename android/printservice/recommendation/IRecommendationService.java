// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice.recommendation;

import android.os.*;

// Referenced classes of package android.printservice.recommendation:
//            IRecommendationServiceCallbacks

public interface IRecommendationService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRecommendationService
    {

        public static IRecommendationService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.printservice.recommendation.IRecommendationService");
            if(iinterface != null && (iinterface instanceof IRecommendationService))
                return (IRecommendationService)iinterface;
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
                parcel1.writeString("android.printservice.recommendation.IRecommendationService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.printservice.recommendation.IRecommendationService");
                registerCallbacks(IRecommendationServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.printservice.recommendation.IRecommendationService";
        static final int TRANSACTION_registerCallbacks = 1;

        public Stub()
        {
            attachInterface(this, "android.printservice.recommendation.IRecommendationService");
        }
    }

    private static class Stub.Proxy
        implements IRecommendationService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.printservice.recommendation.IRecommendationService";
        }

        public void registerCallbacks(IRecommendationServiceCallbacks irecommendationservicecallbacks)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.recommendation.IRecommendationService");
            if(irecommendationservicecallbacks == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = irecommendationservicecallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            irecommendationservicecallbacks;
            parcel.recycle();
            throw irecommendationservicecallbacks;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void registerCallbacks(IRecommendationServiceCallbacks irecommendationservicecallbacks)
        throws RemoteException;
}
