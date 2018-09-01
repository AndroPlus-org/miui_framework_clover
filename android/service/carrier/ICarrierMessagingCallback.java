// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.carrier;

import android.os.*;

public interface ICarrierMessagingCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICarrierMessagingCallback
    {

        public static ICarrierMessagingCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.carrier.ICarrierMessagingCallback");
            if(iinterface != null && (iinterface instanceof ICarrierMessagingCallback))
                return (ICarrierMessagingCallback)iinterface;
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
                parcel1.writeString("android.service.carrier.ICarrierMessagingCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingCallback");
                onFilterComplete(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingCallback");
                onSendSmsComplete(parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingCallback");
                onSendMultipartSmsComplete(parcel.readInt(), parcel.createIntArray());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingCallback");
                onSendMmsComplete(parcel.readInt(), parcel.createByteArray());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingCallback");
                onDownloadMmsComplete(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.carrier.ICarrierMessagingCallback";
        static final int TRANSACTION_onDownloadMmsComplete = 5;
        static final int TRANSACTION_onFilterComplete = 1;
        static final int TRANSACTION_onSendMmsComplete = 4;
        static final int TRANSACTION_onSendMultipartSmsComplete = 3;
        static final int TRANSACTION_onSendSmsComplete = 2;

        public Stub()
        {
            attachInterface(this, "android.service.carrier.ICarrierMessagingCallback");
        }
    }

    private static class Stub.Proxy
        implements ICarrierMessagingCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.carrier.ICarrierMessagingCallback";
        }

        public void onDownloadMmsComplete(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingCallback");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onFilterComplete(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingCallback");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSendMmsComplete(int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingCallback");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void onSendMultipartSmsComplete(int i, int ai[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingCallback");
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            ai;
            parcel.recycle();
            throw ai;
        }

        public void onSendSmsComplete(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
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


    public abstract void onDownloadMmsComplete(int i)
        throws RemoteException;

    public abstract void onFilterComplete(int i)
        throws RemoteException;

    public abstract void onSendMmsComplete(int i, byte abyte0[])
        throws RemoteException;

    public abstract void onSendMultipartSmsComplete(int i, int ai[])
        throws RemoteException;

    public abstract void onSendSmsComplete(int i, int j)
        throws RemoteException;
}
