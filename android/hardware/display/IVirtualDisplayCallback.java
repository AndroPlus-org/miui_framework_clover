// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.os.*;

public interface IVirtualDisplayCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVirtualDisplayCallback
    {

        public static IVirtualDisplayCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.display.IVirtualDisplayCallback");
            if(iinterface != null && (iinterface instanceof IVirtualDisplayCallback))
                return (IVirtualDisplayCallback)iinterface;
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
                parcel1.writeString("android.hardware.display.IVirtualDisplayCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
                onPaused();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
                onResumed();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
                onStopped();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.display.IVirtualDisplayCallback";
        static final int TRANSACTION_onPaused = 1;
        static final int TRANSACTION_onResumed = 2;
        static final int TRANSACTION_onStopped = 3;

        public Stub()
        {
            attachInterface(this, "android.hardware.display.IVirtualDisplayCallback");
        }
    }

    private static class Stub.Proxy
        implements IVirtualDisplayCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.display.IVirtualDisplayCallback";
        }

        public void onPaused()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onResumed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStopped()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
            mRemote.transact(3, parcel, null, 1);
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


    public abstract void onPaused()
        throws RemoteException;

    public abstract void onResumed()
        throws RemoteException;

    public abstract void onStopped()
        throws RemoteException;
}
