// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.os.*;

public interface IRemoteViewsAdapterConnection
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRemoteViewsAdapterConnection
    {

        public static IRemoteViewsAdapterConnection asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.widget.IRemoteViewsAdapterConnection");
            if(iinterface != null && (iinterface instanceof IRemoteViewsAdapterConnection))
                return (IRemoteViewsAdapterConnection)iinterface;
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
                parcel1.writeString("com.android.internal.widget.IRemoteViewsAdapterConnection");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.widget.IRemoteViewsAdapterConnection");
                onServiceConnected(parcel.readStrongBinder());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.widget.IRemoteViewsAdapterConnection");
                onServiceDisconnected();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.widget.IRemoteViewsAdapterConnection";
        static final int TRANSACTION_onServiceConnected = 1;
        static final int TRANSACTION_onServiceDisconnected = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.widget.IRemoteViewsAdapterConnection");
        }
    }

    private static class Stub.Proxy
        implements IRemoteViewsAdapterConnection
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.widget.IRemoteViewsAdapterConnection";
        }

        public void onServiceConnected(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.IRemoteViewsAdapterConnection");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void onServiceDisconnected()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.IRemoteViewsAdapterConnection");
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


    public abstract void onServiceConnected(IBinder ibinder)
        throws RemoteException;

    public abstract void onServiceDisconnected()
        throws RemoteException;
}
