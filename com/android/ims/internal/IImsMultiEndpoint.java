// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;

// Referenced classes of package com.android.ims.internal:
//            IImsExternalCallStateListener

public interface IImsMultiEndpoint
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsMultiEndpoint
    {

        public static IImsMultiEndpoint asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsMultiEndpoint");
            if(iinterface != null && (iinterface instanceof IImsMultiEndpoint))
                return (IImsMultiEndpoint)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsMultiEndpoint");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsMultiEndpoint");
                setListener(IImsExternalCallStateListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsMultiEndpoint");
                requestImsExternalCallStateInfo();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsMultiEndpoint";
        static final int TRANSACTION_requestImsExternalCallStateInfo = 2;
        static final int TRANSACTION_setListener = 1;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsMultiEndpoint");
        }
    }

    private static class Stub.Proxy
        implements IImsMultiEndpoint
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsMultiEndpoint";
        }

        public void requestImsExternalCallStateInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsMultiEndpoint");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void setListener(IImsExternalCallStateListener iimsexternalcallstatelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsMultiEndpoint");
            if(iimsexternalcallstatelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iimsexternalcallstatelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iimsexternalcallstatelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iimsexternalcallstatelistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void requestImsExternalCallStateInfo()
        throws RemoteException;

    public abstract void setListener(IImsExternalCallStateListener iimsexternalcallstatelistener)
        throws RemoteException;
}
