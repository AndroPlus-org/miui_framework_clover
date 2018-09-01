// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface IProcessObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IProcessObserver
    {

        public static IProcessObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IProcessObserver");
            if(iinterface != null && (iinterface instanceof IProcessObserver))
                return (IProcessObserver)iinterface;
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
                parcel1.writeString("android.app.IProcessObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IProcessObserver");
                j = parcel.readInt();
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onForegroundActivitiesChanged(j, i, flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IProcessObserver");
                onProcessDied(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IProcessObserver";
        static final int TRANSACTION_onForegroundActivitiesChanged = 1;
        static final int TRANSACTION_onProcessDied = 2;

        public Stub()
        {
            attachInterface(this, "android.app.IProcessObserver");
        }
    }

    private static class Stub.Proxy
        implements IProcessObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IProcessObserver";
        }

        public void onForegroundActivitiesChanged(int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IProcessObserver");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
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

        public void onProcessDied(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IProcessObserver");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onForegroundActivitiesChanged(int i, int j, boolean flag)
        throws RemoteException;

    public abstract void onProcessDied(int i, int j)
        throws RemoteException;
}
