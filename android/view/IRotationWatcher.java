// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

public interface IRotationWatcher
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRotationWatcher
    {

        public static IRotationWatcher asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IRotationWatcher");
            if(iinterface != null && (iinterface instanceof IRotationWatcher))
                return (IRotationWatcher)iinterface;
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
                parcel1.writeString("android.view.IRotationWatcher");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IRotationWatcher");
                onRotationChanged(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.IRotationWatcher";
        static final int TRANSACTION_onRotationChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.view.IRotationWatcher");
        }
    }

    private static class Stub.Proxy
        implements IRotationWatcher
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IRotationWatcher";
        }

        public void onRotationChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IRotationWatcher");
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


    public abstract void onRotationChanged(int i)
        throws RemoteException;
}
