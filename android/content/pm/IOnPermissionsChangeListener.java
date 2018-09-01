// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public interface IOnPermissionsChangeListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOnPermissionsChangeListener
    {

        public static IOnPermissionsChangeListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IOnPermissionsChangeListener");
            if(iinterface != null && (iinterface instanceof IOnPermissionsChangeListener))
                return (IOnPermissionsChangeListener)iinterface;
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
                parcel1.writeString("android.content.pm.IOnPermissionsChangeListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IOnPermissionsChangeListener");
                onPermissionsChanged(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.IOnPermissionsChangeListener";
        static final int TRANSACTION_onPermissionsChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IOnPermissionsChangeListener");
        }
    }

    private static class Stub.Proxy
        implements IOnPermissionsChangeListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IOnPermissionsChangeListener";
        }

        public void onPermissionsChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnPermissionsChangeListener");
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


    public abstract void onPermissionsChanged(int i)
        throws RemoteException;
}
