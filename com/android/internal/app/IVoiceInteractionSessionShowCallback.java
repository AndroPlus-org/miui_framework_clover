// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

public interface IVoiceInteractionSessionShowCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractionSessionShowCallback
    {

        public static IVoiceInteractionSessionShowCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IVoiceInteractionSessionShowCallback");
            if(iinterface != null && (iinterface instanceof IVoiceInteractionSessionShowCallback))
                return (IVoiceInteractionSessionShowCallback)iinterface;
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
                parcel1.writeString("com.android.internal.app.IVoiceInteractionSessionShowCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionSessionShowCallback");
                onFailed();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionSessionShowCallback");
                onShown();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractionSessionShowCallback";
        static final int TRANSACTION_onFailed = 1;
        static final int TRANSACTION_onShown = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IVoiceInteractionSessionShowCallback");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractionSessionShowCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IVoiceInteractionSessionShowCallback";
        }

        public void onFailed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionSessionShowCallback");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onShown()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionSessionShowCallback");
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


    public abstract void onFailed()
        throws RemoteException;

    public abstract void onShown()
        throws RemoteException;
}
