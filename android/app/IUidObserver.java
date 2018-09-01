// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface IUidObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUidObserver
    {

        public static IUidObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IUidObserver");
            if(iinterface != null && (iinterface instanceof IUidObserver))
                return (IUidObserver)iinterface;
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
                parcel1.writeString("android.app.IUidObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IUidObserver");
                onUidStateChanged(parcel.readInt(), parcel.readInt(), parcel.readLong());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IUidObserver");
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onUidGone(i, flag);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IUidObserver");
                onUidActive(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IUidObserver");
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onUidIdle(i, flag1);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.IUidObserver");
                i = parcel.readInt();
                break;
            }
            boolean flag2;
            if(parcel.readInt() != 0)
                flag2 = true;
            else
                flag2 = false;
            onUidCachedChanged(i, flag2);
            return true;
        }

        private static final String DESCRIPTOR = "android.app.IUidObserver";
        static final int TRANSACTION_onUidActive = 3;
        static final int TRANSACTION_onUidCachedChanged = 5;
        static final int TRANSACTION_onUidGone = 2;
        static final int TRANSACTION_onUidIdle = 4;
        static final int TRANSACTION_onUidStateChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IUidObserver");
        }
    }

    private static class Stub.Proxy
        implements IUidObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IUidObserver";
        }

        public void onUidActive(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUidObserver");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUidCachedChanged(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUidObserver");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUidGone(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUidObserver");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUidIdle(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUidObserver");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUidStateChanged(int i, int j, long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IUidObserver");
            parcel.writeInt(i);
            parcel.writeInt(j);
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


    public abstract void onUidActive(int i)
        throws RemoteException;

    public abstract void onUidCachedChanged(int i, boolean flag)
        throws RemoteException;

    public abstract void onUidGone(int i, boolean flag)
        throws RemoteException;

    public abstract void onUidIdle(int i, boolean flag)
        throws RemoteException;

    public abstract void onUidStateChanged(int i, int j, long l)
        throws RemoteException;
}
