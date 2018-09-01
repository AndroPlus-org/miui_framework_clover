// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

public interface IGestureStubListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGestureStubListener
    {

        public static IGestureStubListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IGestureStubListener");
            if(iinterface != null && (iinterface instanceof IGestureStubListener))
                return (IGestureStubListener)iinterface;
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
                parcel1.writeString("android.view.IGestureStubListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IGestureStubListener");
                onGestureReady();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.IGestureStubListener");
                onGestureStart();
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.IGestureStubListener");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onGestureFinish(flag);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.IGestureStubListener");
                skipAppTransition();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.IGestureStubListener";
        static final int TRANSACTION_onGestureFinish = 3;
        static final int TRANSACTION_onGestureReady = 1;
        static final int TRANSACTION_onGestureStart = 2;
        static final int TRANSACTION_skipAppTransition = 4;

        public Stub()
        {
            attachInterface(this, "android.view.IGestureStubListener");
        }
    }

    private static class Stub.Proxy
        implements IGestureStubListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IGestureStubListener";
        }

        public void onGestureFinish(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IGestureStubListener");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onGestureReady()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IGestureStubListener");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onGestureStart()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IGestureStubListener");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void skipAppTransition()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IGestureStubListener");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onGestureFinish(boolean flag)
        throws RemoteException;

    public abstract void onGestureReady()
        throws RemoteException;

    public abstract void onGestureStart()
        throws RemoteException;

    public abstract void skipAppTransition()
        throws RemoteException;
}
