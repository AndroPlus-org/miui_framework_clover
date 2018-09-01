// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.*;

public interface IOnSubscriptionsChangedListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOnSubscriptionsChangedListener
    {

        public static IOnSubscriptionsChangedListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.IOnSubscriptionsChangedListener");
            if(iinterface != null && (iinterface instanceof IOnSubscriptionsChangedListener))
                return (IOnSubscriptionsChangedListener)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.IOnSubscriptionsChangedListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.IOnSubscriptionsChangedListener");
                onSubscriptionsChanged();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.IOnSubscriptionsChangedListener";
        static final int TRANSACTION_onSubscriptionsChanged = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.IOnSubscriptionsChangedListener");
        }
    }

    private static class Stub.Proxy
        implements IOnSubscriptionsChangedListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.IOnSubscriptionsChangedListener";
        }

        public void onSubscriptionsChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IOnSubscriptionsChangedListener");
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


    public abstract void onSubscriptionsChanged()
        throws RemoteException;
}
