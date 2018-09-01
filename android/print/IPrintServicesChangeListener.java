// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.*;

public interface IPrintServicesChangeListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintServicesChangeListener
    {

        public static IPrintServicesChangeListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrintServicesChangeListener");
            if(iinterface != null && (iinterface instanceof IPrintServicesChangeListener))
                return (IPrintServicesChangeListener)iinterface;
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
                parcel1.writeString("android.print.IPrintServicesChangeListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrintServicesChangeListener");
                onPrintServicesChanged();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.print.IPrintServicesChangeListener";
        static final int TRANSACTION_onPrintServicesChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.print.IPrintServicesChangeListener");
        }
    }

    private static class Stub.Proxy
        implements IPrintServicesChangeListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrintServicesChangeListener";
        }

        public void onPrintServicesChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintServicesChangeListener");
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


    public abstract void onPrintServicesChanged()
        throws RemoteException;
}
