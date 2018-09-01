// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.inputmethod;

import android.os.*;

public interface IInputContentUriToken
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputContentUriToken
    {

        public static IInputContentUriToken asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.inputmethod.IInputContentUriToken");
            if(iinterface != null && (iinterface instanceof IInputContentUriToken))
                return (IInputContentUriToken)iinterface;
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
                parcel1.writeString("com.android.internal.inputmethod.IInputContentUriToken");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.inputmethod.IInputContentUriToken");
                take();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.inputmethod.IInputContentUriToken");
                release();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.inputmethod.IInputContentUriToken";
        static final int TRANSACTION_release = 2;
        static final int TRANSACTION_take = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.inputmethod.IInputContentUriToken");
        }
    }

    private static class Stub.Proxy
        implements IInputContentUriToken
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.inputmethod.IInputContentUriToken";
        }

        public void release()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.inputmethod.IInputContentUriToken");
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

        public void take()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.inputmethod.IInputContentUriToken");
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


    public abstract void release()
        throws RemoteException;

    public abstract void take()
        throws RemoteException;
}
