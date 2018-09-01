// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.os.*;

public interface ITagRemovedCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITagRemovedCallback
    {

        public static ITagRemovedCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.nfc.ITagRemovedCallback");
            if(iinterface != null && (iinterface instanceof ITagRemovedCallback))
                return (ITagRemovedCallback)iinterface;
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
                parcel1.writeString("android.nfc.ITagRemovedCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.nfc.ITagRemovedCallback");
                onTagRemoved();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.nfc.ITagRemovedCallback";
        static final int TRANSACTION_onTagRemoved = 1;

        public Stub()
        {
            attachInterface(this, "android.nfc.ITagRemovedCallback");
        }
    }

    private static class Stub.Proxy
        implements ITagRemovedCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.nfc.ITagRemovedCallback";
        }

        public void onTagRemoved()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.ITagRemovedCallback");
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


    public abstract void onTagRemoved()
        throws RemoteException;
}
