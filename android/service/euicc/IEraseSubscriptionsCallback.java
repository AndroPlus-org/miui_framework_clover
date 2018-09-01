// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.*;

public interface IEraseSubscriptionsCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IEraseSubscriptionsCallback
    {

        public static IEraseSubscriptionsCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.euicc.IEraseSubscriptionsCallback");
            if(iinterface != null && (iinterface instanceof IEraseSubscriptionsCallback))
                return (IEraseSubscriptionsCallback)iinterface;
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
                parcel1.writeString("android.service.euicc.IEraseSubscriptionsCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.euicc.IEraseSubscriptionsCallback");
                onComplete(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.euicc.IEraseSubscriptionsCallback";
        static final int TRANSACTION_onComplete = 1;

        public Stub()
        {
            attachInterface(this, "android.service.euicc.IEraseSubscriptionsCallback");
        }
    }

    private static class Stub.Proxy
        implements IEraseSubscriptionsCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.euicc.IEraseSubscriptionsCallback";
        }

        public void onComplete(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEraseSubscriptionsCallback");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onComplete(int i)
        throws RemoteException;
}
