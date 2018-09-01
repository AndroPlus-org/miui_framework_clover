// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal.uce.uceservice;

import android.os.*;

public interface IUceListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUceListener
    {

        public static IUceListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.uce.uceservice.IUceListener");
            if(iinterface != null && (iinterface instanceof IUceListener))
                return (IUceListener)iinterface;
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
                parcel1.writeString("com.android.ims.internal.uce.uceservice.IUceListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.uce.uceservice.IUceListener");
                setStatus(parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.uce.uceservice.IUceListener";
        static final int TRANSACTION_setStatus = 1;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.uce.uceservice.IUceListener");
        }
    }

    private static class Stub.Proxy
        implements IUceListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.uce.uceservice.IUceListener";
        }

        public void setStatus(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.uce.uceservice.IUceListener");
            parcel.writeInt(i);
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


    public abstract void setStatus(int i)
        throws RemoteException;
}
