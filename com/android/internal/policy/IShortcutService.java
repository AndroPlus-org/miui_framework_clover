// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.os.*;

public interface IShortcutService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IShortcutService
    {

        public static IShortcutService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.policy.IShortcutService");
            if(iinterface != null && (iinterface instanceof IShortcutService))
                return (IShortcutService)iinterface;
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
                parcel1.writeString("com.android.internal.policy.IShortcutService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.policy.IShortcutService");
                notifyShortcutKeyPressed(parcel.readLong());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.policy.IShortcutService";
        static final int TRANSACTION_notifyShortcutKeyPressed = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.policy.IShortcutService");
        }
    }

    private static class Stub.Proxy
        implements IShortcutService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.policy.IShortcutService";
        }

        public void notifyShortcutKeyPressed(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IShortcutService");
            parcel.writeLong(l);
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


    public abstract void notifyShortcutKeyPressed(long l)
        throws RemoteException;
}
