// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

public interface IPinnedStackController
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPinnedStackController
    {

        public static IPinnedStackController asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IPinnedStackController");
            if(iinterface != null && (iinterface instanceof IPinnedStackController))
                return (IPinnedStackController)iinterface;
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
                parcel1.writeString("android.view.IPinnedStackController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IPinnedStackController");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setIsMinimized(flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.IPinnedStackController");
                setMinEdgeSize(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.IPinnedStackController");
                i = getDisplayRotation();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.IPinnedStackController";
        static final int TRANSACTION_getDisplayRotation = 3;
        static final int TRANSACTION_setIsMinimized = 1;
        static final int TRANSACTION_setMinEdgeSize = 2;

        public Stub()
        {
            attachInterface(this, "android.view.IPinnedStackController");
        }
    }

    private static class Stub.Proxy
        implements IPinnedStackController
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int getDisplayRotation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IPinnedStackController");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IPinnedStackController";
        }

        public void setIsMinimized(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IPinnedStackController");
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

        public void setMinEdgeSize(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IPinnedStackController");
            parcel.writeInt(i);
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


    public abstract int getDisplayRotation()
        throws RemoteException;

    public abstract void setIsMinimized(boolean flag)
        throws RemoteException;

    public abstract void setMinEdgeSize(int i)
        throws RemoteException;
}
