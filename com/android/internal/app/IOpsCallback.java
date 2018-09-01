// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

public interface IOpsCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOpsCallback
    {

        public static IOpsCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IOpsCallback");
            if(iinterface != null && (iinterface instanceof IOpsCallback))
                return (IOpsCallback)iinterface;
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
            int k = 0;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.app.IOpsCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IOpsCallback");
                i = askOperation(parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IOpsCallback");
                i = getDefaultOp(parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IOpsCallback");
                boolean flag = isAppPermissionControlOpen(parcel.readInt());
                parcel1.writeNoException();
                i = k;
                if(flag)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IOpsCallback");
                j = parcel.readInt();
                parcel1 = parcel.readString();
                k = parcel.readInt();
                i = parcel.readInt();
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            onAppApplyOperation(j, parcel1, k, i, flag1);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IOpsCallback";
        static final int TRANSACTION_askOperation = 1;
        static final int TRANSACTION_getDefaultOp = 2;
        static final int TRANSACTION_isAppPermissionControlOpen = 3;
        static final int TRANSACTION_onAppApplyOperation = 4;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IOpsCallback");
        }
    }

    private static class Stub.Proxy
        implements IOpsCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int askOperation(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IOpsCallback");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getDefaultOp(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IOpsCallback");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IOpsCallback";
        }

        public boolean isAppPermissionControlOpen(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IOpsCallback");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onAppApplyOperation(int i, String s, int j, int k, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IOpsCallback");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
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


    public abstract int askOperation(int i, String s, int j)
        throws RemoteException;

    public abstract int getDefaultOp(int i, String s, int j)
        throws RemoteException;

    public abstract boolean isAppPermissionControlOpen(int i)
        throws RemoteException;

    public abstract void onAppApplyOperation(int i, String s, int j, int k, boolean flag)
        throws RemoteException;
}
