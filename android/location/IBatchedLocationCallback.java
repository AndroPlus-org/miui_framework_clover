// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;
import java.util.List;

// Referenced classes of package android.location:
//            Location

public interface IBatchedLocationCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBatchedLocationCallback
    {

        public static IBatchedLocationCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IBatchedLocationCallback");
            if(iinterface != null && (iinterface instanceof IBatchedLocationCallback))
                return (IBatchedLocationCallback)iinterface;
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
                parcel1.writeString("android.location.IBatchedLocationCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IBatchedLocationCallback");
                onLocationBatch(parcel.createTypedArrayList(Location.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.IBatchedLocationCallback";
        static final int TRANSACTION_onLocationBatch = 1;

        public Stub()
        {
            attachInterface(this, "android.location.IBatchedLocationCallback");
        }
    }

    private static class Stub.Proxy
        implements IBatchedLocationCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.IBatchedLocationCallback";
        }

        public void onLocationBatch(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IBatchedLocationCallback");
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


    public abstract void onLocationBatch(List list)
        throws RemoteException;
}
