// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

public interface IUidStateChangeCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUidStateChangeCallback
    {

        public static IUidStateChangeCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IUidStateChangeCallback");
            if(iinterface != null && (iinterface instanceof IUidStateChangeCallback))
                return (IUidStateChangeCallback)iinterface;
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
                parcel1.writeString("com.android.internal.app.IUidStateChangeCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IUidStateChangeCallback");
                onUidStateChange(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IUidStateChangeCallback";
        static final int TRANSACTION_onUidStateChange = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IUidStateChangeCallback");
        }
    }

    private static class Stub.Proxy
        implements IUidStateChangeCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IUidStateChangeCallback";
        }

        public void onUidStateChange(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IUidStateChangeCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
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


    public abstract void onUidStateChange(int i, int j)
        throws RemoteException;
}
