// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

public interface IVoiceInteractionSessionListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractionSessionListener
    {

        public static IVoiceInteractionSessionListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IVoiceInteractionSessionListener");
            if(iinterface != null && (iinterface instanceof IVoiceInteractionSessionListener))
                return (IVoiceInteractionSessionListener)iinterface;
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
                parcel1.writeString("com.android.internal.app.IVoiceInteractionSessionListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionSessionListener");
                onVoiceSessionShown();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionSessionListener");
                onVoiceSessionHidden();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractionSessionListener";
        static final int TRANSACTION_onVoiceSessionHidden = 2;
        static final int TRANSACTION_onVoiceSessionShown = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IVoiceInteractionSessionListener");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractionSessionListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IVoiceInteractionSessionListener";
        }

        public void onVoiceSessionHidden()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionSessionListener");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onVoiceSessionShown()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionSessionListener");
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


    public abstract void onVoiceSessionHidden()
        throws RemoteException;

    public abstract void onVoiceSessionShown()
        throws RemoteException;
}
