// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.*;

public interface IPrintDocumentAdapterObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintDocumentAdapterObserver
    {

        public static IPrintDocumentAdapterObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrintDocumentAdapterObserver");
            if(iinterface != null && (iinterface instanceof IPrintDocumentAdapterObserver))
                return (IPrintDocumentAdapterObserver)iinterface;
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
                parcel1.writeString("android.print.IPrintDocumentAdapterObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrintDocumentAdapterObserver");
                onDestroy();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.print.IPrintDocumentAdapterObserver";
        static final int TRANSACTION_onDestroy = 1;

        public Stub()
        {
            attachInterface(this, "android.print.IPrintDocumentAdapterObserver");
        }
    }

    private static class Stub.Proxy
        implements IPrintDocumentAdapterObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrintDocumentAdapterObserver";
        }

        public void onDestroy()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintDocumentAdapterObserver");
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


    public abstract void onDestroy()
        throws RemoteException;
}
