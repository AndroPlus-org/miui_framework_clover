// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.os.*;

public interface IDisplayManagerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDisplayManagerCallback
    {

        public static IDisplayManagerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.display.IDisplayManagerCallback");
            if(iinterface != null && (iinterface instanceof IDisplayManagerCallback))
                return (IDisplayManagerCallback)iinterface;
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
                parcel1.writeString("android.hardware.display.IDisplayManagerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.display.IDisplayManagerCallback");
                onDisplayEvent(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.display.IDisplayManagerCallback";
        static final int TRANSACTION_onDisplayEvent = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.display.IDisplayManagerCallback");
        }
    }

    private static class Stub.Proxy
        implements IDisplayManagerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.display.IDisplayManagerCallback";
        }

        public void onDisplayEvent(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.display.IDisplayManagerCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
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


    public abstract void onDisplayEvent(int i, int j)
        throws RemoteException;
}
