// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.timezone;

import android.os.*;

public interface ICallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICallback
    {

        public static ICallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.timezone.ICallback");
            if(iinterface != null && (iinterface instanceof ICallback))
                return (ICallback)iinterface;
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
                parcel1.writeString("android.app.timezone.ICallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.timezone.ICallback");
                onFinished(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.timezone.ICallback";
        static final int TRANSACTION_onFinished = 1;

        public Stub()
        {
            attachInterface(this, "android.app.timezone.ICallback");
        }
    }

    private static class Stub.Proxy
        implements ICallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.timezone.ICallback";
        }

        public void onFinished(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.timezone.ICallback");
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


    public abstract void onFinished(int i)
        throws RemoteException;
}
