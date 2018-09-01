// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telecom;

import android.content.ComponentName;
import android.os.*;
import java.util.List;

public interface RemoteServiceCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements RemoteServiceCallback
    {

        public static RemoteServiceCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telecom.RemoteServiceCallback");
            if(iinterface != null && (iinterface instanceof RemoteServiceCallback))
                return (RemoteServiceCallback)iinterface;
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
                parcel1.writeString("com.android.internal.telecom.RemoteServiceCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telecom.RemoteServiceCallback");
                onError();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telecom.RemoteServiceCallback");
                onResult(parcel.createTypedArrayList(ComponentName.CREATOR), parcel.createBinderArrayList());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telecom.RemoteServiceCallback";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onResult = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telecom.RemoteServiceCallback");
        }
    }

    private static class Stub.Proxy
        implements RemoteServiceCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telecom.RemoteServiceCallback";
        }

        public void onError()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.RemoteServiceCallback");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onResult(List list, List list1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.RemoteServiceCallback");
            parcel.writeTypedList(list);
            parcel.writeBinderList(list1);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onError()
        throws RemoteException;

    public abstract void onResult(List list, List list1)
        throws RemoteException;
}
