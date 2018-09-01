// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.resolver;

import android.os.*;
import java.util.List;

// Referenced classes of package android.service.resolver:
//            ResolverTarget

public interface IResolverRankerResult
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IResolverRankerResult
    {

        public static IResolverRankerResult asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.resolver.IResolverRankerResult");
            if(iinterface != null && (iinterface instanceof IResolverRankerResult))
                return (IResolverRankerResult)iinterface;
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
                parcel1.writeString("android.service.resolver.IResolverRankerResult");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.resolver.IResolverRankerResult");
                sendResult(parcel.createTypedArrayList(ResolverTarget.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.resolver.IResolverRankerResult";
        static final int TRANSACTION_sendResult = 1;

        public Stub()
        {
            attachInterface(this, "android.service.resolver.IResolverRankerResult");
        }
    }

    private static class Stub.Proxy
        implements IResolverRankerResult
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.resolver.IResolverRankerResult";
        }

        public void sendResult(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.resolver.IResolverRankerResult");
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


    public abstract void sendResult(List list)
        throws RemoteException;
}
