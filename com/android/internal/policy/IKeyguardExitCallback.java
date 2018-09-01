// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.os.*;

public interface IKeyguardExitCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IKeyguardExitCallback
    {

        public static IKeyguardExitCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.policy.IKeyguardExitCallback");
            if(iinterface != null && (iinterface instanceof IKeyguardExitCallback))
                return (IKeyguardExitCallback)iinterface;
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
                parcel1.writeString("com.android.internal.policy.IKeyguardExitCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.policy.IKeyguardExitCallback");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onKeyguardExitResult(flag);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.policy.IKeyguardExitCallback";
        static final int TRANSACTION_onKeyguardExitResult = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.policy.IKeyguardExitCallback");
        }
    }

    private static class Stub.Proxy
        implements IKeyguardExitCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.policy.IKeyguardExitCallback";
        }

        public void onKeyguardExitResult(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.policy.IKeyguardExitCallback");
            if(!flag)
                i = 0;
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


    public abstract void onKeyguardExitResult(boolean flag)
        throws RemoteException;
}
