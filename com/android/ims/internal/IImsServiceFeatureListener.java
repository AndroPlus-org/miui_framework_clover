// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;

public interface IImsServiceFeatureListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsServiceFeatureListener
    {

        public static IImsServiceFeatureListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsServiceFeatureListener");
            if(iinterface != null && (iinterface instanceof IImsServiceFeatureListener))
                return (IImsServiceFeatureListener)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsServiceFeatureListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceFeatureListener");
                imsFeatureCreated(parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceFeatureListener");
                imsFeatureRemoved(parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsServiceFeatureListener");
                imsStatusChanged(parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsServiceFeatureListener";
        static final int TRANSACTION_imsFeatureCreated = 1;
        static final int TRANSACTION_imsFeatureRemoved = 2;
        static final int TRANSACTION_imsStatusChanged = 3;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsServiceFeatureListener");
        }
    }

    private static class Stub.Proxy
        implements IImsServiceFeatureListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsServiceFeatureListener";
        }

        public void imsFeatureCreated(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceFeatureListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void imsFeatureRemoved(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceFeatureListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void imsStatusChanged(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsServiceFeatureListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(3, parcel, null, 1);
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


    public abstract void imsFeatureCreated(int i, int j)
        throws RemoteException;

    public abstract void imsFeatureRemoved(int i, int j)
        throws RemoteException;

    public abstract void imsStatusChanged(int i, int j, int k)
        throws RemoteException;
}
