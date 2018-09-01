// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

// Referenced classes of package android.view:
//            IWindowFocusObserver

public interface IWindowId
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWindowId
    {

        public static IWindowId asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IWindowId");
            if(iinterface != null && (iinterface instanceof IWindowId))
                return (IWindowId)iinterface;
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
            boolean flag;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.view.IWindowId");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IWindowId");
                registerFocusObserver(IWindowFocusObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.IWindowId");
                unregisterFocusObserver(IWindowFocusObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.IWindowId");
                flag = isFocused();
                parcel1.writeNoException();
                break;
            }
            if(flag)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.view.IWindowId";
        static final int TRANSACTION_isFocused = 3;
        static final int TRANSACTION_registerFocusObserver = 1;
        static final int TRANSACTION_unregisterFocusObserver = 2;

        public Stub()
        {
            attachInterface(this, "android.view.IWindowId");
        }
    }

    private static class Stub.Proxy
        implements IWindowId
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IWindowId";
        }

        public boolean isFocused()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.view.IWindowId");
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

        public void registerFocusObserver(IWindowFocusObserver iwindowfocusobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowId");
            if(iwindowfocusobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindowfocusobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindowfocusobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iwindowfocusobserver;
        }

        public void unregisterFocusObserver(IWindowFocusObserver iwindowfocusobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowId");
            if(iwindowfocusobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindowfocusobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindowfocusobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iwindowfocusobserver;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean isFocused()
        throws RemoteException;

    public abstract void registerFocusObserver(IWindowFocusObserver iwindowfocusobserver)
        throws RemoteException;

    public abstract void unregisterFocusObserver(IWindowFocusObserver iwindowfocusobserver)
        throws RemoteException;
}
