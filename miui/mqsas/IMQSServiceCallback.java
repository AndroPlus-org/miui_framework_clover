// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas;

import android.os.*;

public interface IMQSServiceCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMQSServiceCallback
    {

        public static IMQSServiceCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.mqsas.IMQSServiceCallback");
            if(iinterface != null && (iinterface instanceof IMQSServiceCallback))
                return (IMQSServiceCallback)iinterface;
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
                parcel1.writeString("miui.mqsas.IMQSServiceCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.mqsas.IMQSServiceCallback");
                onReportFinished();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "miui.mqsas.IMQSServiceCallback";
        static final int TRANSACTION_onReportFinished = 1;

        public Stub()
        {
            attachInterface(this, "miui.mqsas.IMQSServiceCallback");
        }
    }

    private static class Stub.Proxy
        implements IMQSServiceCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.mqsas.IMQSServiceCallback";
        }

        public void onReportFinished()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSServiceCallback");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onReportFinished()
        throws RemoteException;
}
