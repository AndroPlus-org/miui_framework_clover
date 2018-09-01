// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.*;

public interface IUpdateSubscriptionNicknameCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUpdateSubscriptionNicknameCallback
    {

        public static IUpdateSubscriptionNicknameCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.euicc.IUpdateSubscriptionNicknameCallback");
            if(iinterface != null && (iinterface instanceof IUpdateSubscriptionNicknameCallback))
                return (IUpdateSubscriptionNicknameCallback)iinterface;
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
                parcel1.writeString("android.service.euicc.IUpdateSubscriptionNicknameCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.euicc.IUpdateSubscriptionNicknameCallback");
                onComplete(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.euicc.IUpdateSubscriptionNicknameCallback";
        static final int TRANSACTION_onComplete = 1;

        public Stub()
        {
            attachInterface(this, "android.service.euicc.IUpdateSubscriptionNicknameCallback");
        }
    }

    private static class Stub.Proxy
        implements IUpdateSubscriptionNicknameCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.euicc.IUpdateSubscriptionNicknameCallback";
        }

        public void onComplete(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IUpdateSubscriptionNicknameCallback");
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
