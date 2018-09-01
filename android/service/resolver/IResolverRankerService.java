// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.resolver;

import android.os.*;
import java.util.List;

// Referenced classes of package android.service.resolver:
//            IResolverRankerResult, ResolverTarget

public interface IResolverRankerService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IResolverRankerService
    {

        public static IResolverRankerService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.resolver.IResolverRankerService");
            if(iinterface != null && (iinterface instanceof IResolverRankerService))
                return (IResolverRankerService)iinterface;
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
                parcel1.writeString("android.service.resolver.IResolverRankerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.resolver.IResolverRankerService");
                predict(parcel.createTypedArrayList(ResolverTarget.CREATOR), IResolverRankerResult.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.resolver.IResolverRankerService");
                train(parcel.createTypedArrayList(ResolverTarget.CREATOR), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.resolver.IResolverRankerService";
        static final int TRANSACTION_predict = 1;
        static final int TRANSACTION_train = 2;

        public Stub()
        {
            attachInterface(this, "android.service.resolver.IResolverRankerService");
        }
    }

    private static class Stub.Proxy
        implements IResolverRankerService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.resolver.IResolverRankerService";
        }

        public void predict(List list, IResolverRankerResult iresolverrankerresult)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.resolver.IResolverRankerService");
            parcel.writeTypedList(list);
            list = obj;
            if(iresolverrankerresult == null)
                break MISSING_BLOCK_LABEL_33;
            list = iresolverrankerresult.asBinder();
            parcel.writeStrongBinder(list);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void train(List list, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.resolver.IResolverRankerService");
            parcel.writeTypedList(list);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
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


    public abstract void predict(List list, IResolverRankerResult iresolverrankerresult)
        throws RemoteException;

    public abstract void train(List list, int i)
        throws RemoteException;
}
