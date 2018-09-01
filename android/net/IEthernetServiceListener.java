// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

public interface IEthernetServiceListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IEthernetServiceListener
    {

        public static IEthernetServiceListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.IEthernetServiceListener");
            if(iinterface != null && (iinterface instanceof IEthernetServiceListener))
                return (IEthernetServiceListener)iinterface;
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
                parcel1.writeString("android.net.IEthernetServiceListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.IEthernetServiceListener");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onAvailabilityChanged(flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.net.IEthernetServiceListener";
        static final int TRANSACTION_onAvailabilityChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.net.IEthernetServiceListener");
        }
    }

    private static class Stub.Proxy
        implements IEthernetServiceListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.IEthernetServiceListener";
        }

        public void onAvailabilityChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IEthernetServiceListener");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAvailabilityChanged(boolean flag)
        throws RemoteException;
}
