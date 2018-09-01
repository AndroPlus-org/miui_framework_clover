// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telecom;

import android.os.*;

public interface ICallScreeningAdapter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICallScreeningAdapter
    {

        public static ICallScreeningAdapter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telecom.ICallScreeningAdapter");
            if(iinterface != null && (iinterface instanceof ICallScreeningAdapter))
                return (ICallScreeningAdapter)iinterface;
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
                parcel1.writeString("com.android.internal.telecom.ICallScreeningAdapter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telecom.ICallScreeningAdapter");
                allowCall(parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telecom.ICallScreeningAdapter");
                parcel1 = parcel.readString();
                break;
            }
            boolean flag;
            boolean flag1;
            boolean flag2;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            if(parcel.readInt() != 0)
                flag2 = true;
            else
                flag2 = false;
            disallowCall(parcel1, flag, flag1, flag2);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.telecom.ICallScreeningAdapter";
        static final int TRANSACTION_allowCall = 1;
        static final int TRANSACTION_disallowCall = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telecom.ICallScreeningAdapter");
        }
    }

    private static class Stub.Proxy
        implements ICallScreeningAdapter
    {

        public void allowCall(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ICallScreeningAdapter");
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void disallowCall(String s, boolean flag, boolean flag1, boolean flag2)
            throws RemoteException
        {
            boolean flag3;
            Parcel parcel;
            flag3 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ICallScreeningAdapter");
            parcel.writeString(s);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag2)
                i = ((flag3) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telecom.ICallScreeningAdapter";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void allowCall(String s)
        throws RemoteException;

    public abstract void disallowCall(String s, boolean flag, boolean flag1, boolean flag2)
        throws RemoteException;
}
