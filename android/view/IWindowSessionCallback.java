// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

public interface IWindowSessionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWindowSessionCallback
    {

        public static IWindowSessionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IWindowSessionCallback");
            if(iinterface != null && (iinterface instanceof IWindowSessionCallback))
                return (IWindowSessionCallback)iinterface;
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
                parcel1.writeString("android.view.IWindowSessionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IWindowSessionCallback");
                onAnimatorScaleChanged(parcel.readFloat());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.IWindowSessionCallback";
        static final int TRANSACTION_onAnimatorScaleChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.view.IWindowSessionCallback");
        }
    }

    private static class Stub.Proxy
        implements IWindowSessionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IWindowSessionCallback";
        }

        public void onAnimatorScaleChanged(float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWindowSessionCallback");
            parcel.writeFloat(f);
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


    public abstract void onAnimatorScaleChanged(float f)
        throws RemoteException;
}
