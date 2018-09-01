// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.*;

public interface IDeleteSubscriptionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDeleteSubscriptionCallback
    {

        public static IDeleteSubscriptionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.euicc.IDeleteSubscriptionCallback");
            if(iinterface != null && (iinterface instanceof IDeleteSubscriptionCallback))
                return (IDeleteSubscriptionCallback)iinterface;
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
                parcel1.writeString("android.service.euicc.IDeleteSubscriptionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.euicc.IDeleteSubscriptionCallback");
                onComplete(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.euicc.IDeleteSubscriptionCallback";
        static final int TRANSACTION_onComplete = 1;

        public Stub()
        {
            attachInterface(this, "android.service.euicc.IDeleteSubscriptionCallback");
        }
    }

    private static class Stub.Proxy
        implements IDeleteSubscriptionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.euicc.IDeleteSubscriptionCallback";
        }

        public void onComplete(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IDeleteSubscriptionCallback");
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
