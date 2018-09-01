// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.*;

public interface ICamera
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICamera
    {

        public static ICamera asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.ICamera");
            if(iinterface != null && (iinterface instanceof ICamera))
                return (ICamera)iinterface;
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
                parcel1.writeString("android.hardware.ICamera");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.ICamera");
                disconnect();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.ICamera";
        static final int TRANSACTION_disconnect = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.ICamera");
        }
    }

    private static class Stub.Proxy
        implements ICamera
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void disconnect()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICamera");
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

        public String getInterfaceDescriptor()
        {
            return "android.hardware.ICamera";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void disconnect()
        throws RemoteException;
}
