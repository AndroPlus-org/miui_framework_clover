// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

public interface IWakePathCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWakePathCallback
    {

        public static IWakePathCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IWakePathCallback");
            if(iinterface != null && (iinterface instanceof IWakePathCallback))
                return (IWakePathCallback)iinterface;
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
                parcel1.writeString("com.android.internal.app.IWakePathCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IWakePathCallback");
                onRejectCall(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IWakePathCallback");
                onAllowCall(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IWakePathCallback";
        static final int TRANSACTION_onAllowCall = 2;
        static final int TRANSACTION_onRejectCall = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IWakePathCallback");
        }
    }

    private static class Stub.Proxy
        implements IWakePathCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IWakePathCallback";
        }

        public void onAllowCall(String s, String s1, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IWakePathCallback");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onRejectCall(String s, String s1, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IWakePathCallback");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAllowCall(String s, String s1, int i, int j)
        throws RemoteException;

    public abstract void onRejectCall(String s, String s1, int i, int j)
        throws RemoteException;
}
