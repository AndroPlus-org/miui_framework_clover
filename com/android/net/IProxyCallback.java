// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.net;

import android.os.*;

public interface IProxyCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IProxyCallback
    {

        public static IProxyCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.net.IProxyCallback");
            if(iinterface != null && (iinterface instanceof IProxyCallback))
                return (IProxyCallback)iinterface;
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
                parcel1.writeString("com.android.net.IProxyCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.net.IProxyCallback");
                getProxyPort(parcel.readStrongBinder());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.net.IProxyCallback";
        static final int TRANSACTION_getProxyPort = 1;

        public Stub()
        {
            attachInterface(this, "com.android.net.IProxyCallback");
        }
    }

    private static class Stub.Proxy
        implements IProxyCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.net.IProxyCallback";
        }

        public void getProxyPort(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.net.IProxyCallback");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void getProxyPort(IBinder ibinder)
        throws RemoteException;
}
