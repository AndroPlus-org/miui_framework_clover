// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.content.ContentValues;
import android.os.*;

public interface IApnSourceService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IApnSourceService
    {

        public static IApnSourceService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.IApnSourceService");
            if(iinterface != null && (iinterface instanceof IApnSourceService))
                return (IApnSourceService)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.IApnSourceService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.IApnSourceService");
                parcel = getApns();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.IApnSourceService";
        static final int TRANSACTION_getApns = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.IApnSourceService");
        }
    }

    private static class Stub.Proxy
        implements IApnSourceService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public ContentValues[] getApns()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            ContentValues acontentvalues[];
            parcel.writeInterfaceToken("com.android.internal.telephony.IApnSourceService");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            acontentvalues = (ContentValues[])parcel1.createTypedArray(ContentValues.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return acontentvalues;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.IApnSourceService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract ContentValues[] getApns()
        throws RemoteException;
}
