// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;

public interface IImsFeatureStatusCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsFeatureStatusCallback
    {

        public static IImsFeatureStatusCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsFeatureStatusCallback");
            if(iinterface != null && (iinterface instanceof IImsFeatureStatusCallback))
                return (IImsFeatureStatusCallback)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsFeatureStatusCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsFeatureStatusCallback");
                notifyImsFeatureStatus(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsFeatureStatusCallback";
        static final int TRANSACTION_notifyImsFeatureStatus = 1;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsFeatureStatusCallback");
        }
    }

    private static class Stub.Proxy
        implements IImsFeatureStatusCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsFeatureStatusCallback";
        }

        public void notifyImsFeatureStatus(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsFeatureStatusCallback");
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


    public abstract void notifyImsFeatureStatus(int i)
        throws RemoteException;
}
