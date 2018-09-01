// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

public interface IAppOpsCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAppOpsCallback
    {

        public static IAppOpsCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IAppOpsCallback");
            if(iinterface != null && (iinterface instanceof IAppOpsCallback))
                return (IAppOpsCallback)iinterface;
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
                parcel1.writeString("com.android.internal.app.IAppOpsCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IAppOpsCallback");
                opChanged(parcel.readInt(), parcel.readInt(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IAppOpsCallback";
        static final int TRANSACTION_opChanged = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IAppOpsCallback");
        }
    }

    private static class Stub.Proxy
        implements IAppOpsCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IAppOpsCallback";
        }

        public void opChanged(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
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


    public abstract void opChanged(int i, int j, String s)
        throws RemoteException;
}
