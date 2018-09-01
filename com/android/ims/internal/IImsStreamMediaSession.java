// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;

public interface IImsStreamMediaSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsStreamMediaSession
    {

        public static IImsStreamMediaSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsStreamMediaSession");
            if(iinterface != null && (iinterface instanceof IImsStreamMediaSession))
                return (IImsStreamMediaSession)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsStreamMediaSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsStreamMediaSession");
                close();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsStreamMediaSession";
        static final int TRANSACTION_close = 1;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsStreamMediaSession");
        }
    }

    private static class Stub.Proxy
        implements IImsStreamMediaSession
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void close()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsStreamMediaSession");
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

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsStreamMediaSession";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void close()
        throws RemoteException;
}
