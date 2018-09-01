// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.os.*;

public interface IKeyguardDismissCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IKeyguardDismissCallback
    {

        public static IKeyguardDismissCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.policy.IKeyguardDismissCallback");
            if(iinterface != null && (iinterface instanceof IKeyguardDismissCallback))
                return (IKeyguardDismissCallback)iinterface;
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
                parcel1.writeString("com.android.internal.policy.IKeyguardDismissCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardDismissCallback");
                onDismissError();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardDismissCallback");
                onDismissSucceeded();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardDismissCallback");
                onDismissCancelled();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.policy.IKeyguardDismissCallback";
        static final int TRANSACTION_onDismissCancelled = 3;
        static final int TRANSACTION_onDismissError = 1;
        static final int TRANSACTION_onDismissSucceeded = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.policy.IKeyguardDismissCallback");
        }
    }

    private static class Stub.Proxy
        implements IKeyguardDismissCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.policy.IKeyguardDismissCallback";
        }

        public void onDismissCancelled()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardDismissCallback");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDismissError()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardDismissCallback");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDismissSucceeded()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardDismissCallback");
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


    public abstract void onDismissCancelled()
        throws RemoteException;

    public abstract void onDismissError()
        throws RemoteException;

    public abstract void onDismissSucceeded()
        throws RemoteException;
}
