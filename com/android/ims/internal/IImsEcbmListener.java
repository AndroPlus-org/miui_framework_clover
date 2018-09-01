// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;

public interface IImsEcbmListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsEcbmListener
    {

        public static IImsEcbmListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsEcbmListener");
            if(iinterface != null && (iinterface instanceof IImsEcbmListener))
                return (IImsEcbmListener)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsEcbmListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsEcbmListener");
                enteredECBM();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsEcbmListener");
                exitedECBM();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsEcbmListener";
        static final int TRANSACTION_enteredECBM = 1;
        static final int TRANSACTION_exitedECBM = 2;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsEcbmListener");
        }
    }

    private static class Stub.Proxy
        implements IImsEcbmListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void enteredECBM()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsEcbmListener");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void exitedECBM()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsEcbmListener");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsEcbmListener";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void enteredECBM()
        throws RemoteException;

    public abstract void exitedECBM()
        throws RemoteException;
}
