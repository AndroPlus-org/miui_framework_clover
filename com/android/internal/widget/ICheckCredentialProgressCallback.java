// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.os.*;

public interface ICheckCredentialProgressCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICheckCredentialProgressCallback
    {

        public static ICheckCredentialProgressCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.widget.ICheckCredentialProgressCallback");
            if(iinterface != null && (iinterface instanceof ICheckCredentialProgressCallback))
                return (ICheckCredentialProgressCallback)iinterface;
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
                parcel1.writeString("com.android.internal.widget.ICheckCredentialProgressCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.widget.ICheckCredentialProgressCallback");
                onCredentialVerified();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.widget.ICheckCredentialProgressCallback";
        static final int TRANSACTION_onCredentialVerified = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.widget.ICheckCredentialProgressCallback");
        }
    }

    private static class Stub.Proxy
        implements ICheckCredentialProgressCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.widget.ICheckCredentialProgressCallback";
        }

        public void onCredentialVerified()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ICheckCredentialProgressCallback");
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


    public abstract void onCredentialVerified()
        throws RemoteException;
}
