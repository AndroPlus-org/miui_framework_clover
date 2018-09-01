// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

public interface IVoiceInteractorRequest
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractorRequest
    {

        public static IVoiceInteractorRequest asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IVoiceInteractorRequest");
            if(iinterface != null && (iinterface instanceof IVoiceInteractorRequest))
                return (IVoiceInteractorRequest)iinterface;
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
                parcel1.writeString("com.android.internal.app.IVoiceInteractorRequest");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractorRequest");
                cancel();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractorRequest";
        static final int TRANSACTION_cancel = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IVoiceInteractorRequest");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractorRequest
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractorRequest");
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
            return "com.android.internal.app.IVoiceInteractorRequest";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cancel()
        throws RemoteException;
}
