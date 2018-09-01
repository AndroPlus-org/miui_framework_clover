// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

public interface ISyncStatusObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISyncStatusObserver
    {

        public static ISyncStatusObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.ISyncStatusObserver");
            if(iinterface != null && (iinterface instanceof ISyncStatusObserver))
                return (ISyncStatusObserver)iinterface;
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
                parcel1.writeString("android.content.ISyncStatusObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.ISyncStatusObserver");
                onStatusChanged(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.ISyncStatusObserver";
        static final int TRANSACTION_onStatusChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.content.ISyncStatusObserver");
        }
    }

    private static class Stub.Proxy
        implements ISyncStatusObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.ISyncStatusObserver";
        }

        public void onStatusChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.ISyncStatusObserver");
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


    public abstract void onStatusChanged(int i)
        throws RemoteException;
}
