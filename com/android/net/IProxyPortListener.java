// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.net;

import android.os.*;

public interface IProxyPortListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IProxyPortListener
    {

        public static IProxyPortListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.net.IProxyPortListener");
            if(iinterface != null && (iinterface instanceof IProxyPortListener))
                return (IProxyPortListener)iinterface;
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
                parcel1.writeString("com.android.net.IProxyPortListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.net.IProxyPortListener");
                setProxyPort(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.net.IProxyPortListener";
        static final int TRANSACTION_setProxyPort = 1;

        public Stub()
        {
            attachInterface(this, "com.android.net.IProxyPortListener");
        }
    }

    private static class Stub.Proxy
        implements IProxyPortListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.net.IProxyPortListener";
        }

        public void setProxyPort(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.net.IProxyPortListener");
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


    public abstract void setProxyPort(int i)
        throws RemoteException;
}
