// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.*;

public interface IGetEidCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGetEidCallback
    {

        public static IGetEidCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.euicc.IGetEidCallback");
            if(iinterface != null && (iinterface instanceof IGetEidCallback))
                return (IGetEidCallback)iinterface;
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
                parcel1.writeString("android.service.euicc.IGetEidCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.euicc.IGetEidCallback");
                onSuccess(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.euicc.IGetEidCallback";
        static final int TRANSACTION_onSuccess = 1;

        public Stub()
        {
            attachInterface(this, "android.service.euicc.IGetEidCallback");
        }
    }

    private static class Stub.Proxy
        implements IGetEidCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.euicc.IGetEidCallback";
        }

        public void onSuccess(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IGetEidCallback");
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onSuccess(String s)
        throws RemoteException;
}
