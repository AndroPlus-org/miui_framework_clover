// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.content.pm;

import android.os.*;

public interface IPackageDeleteConfirmObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageDeleteConfirmObserver
    {

        public static IPackageDeleteConfirmObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.content.pm.IPackageDeleteConfirmObserver");
            if(iinterface != null && (iinterface instanceof IPackageDeleteConfirmObserver))
                return (IPackageDeleteConfirmObserver)iinterface;
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
                parcel1.writeString("miui.content.pm.IPackageDeleteConfirmObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.content.pm.IPackageDeleteConfirmObserver");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onConfirm(flag);
            return true;
        }

        private static final String DESCRIPTOR = "miui.content.pm.IPackageDeleteConfirmObserver";
        static final int TRANSACTION_onConfirm = 1;

        public Stub()
        {
            attachInterface(this, "miui.content.pm.IPackageDeleteConfirmObserver");
        }
    }

    private static class Stub.Proxy
        implements IPackageDeleteConfirmObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.content.pm.IPackageDeleteConfirmObserver";
        }

        public void onConfirm(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.content.pm.IPackageDeleteConfirmObserver");
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


    public abstract void onConfirm(boolean flag)
        throws RemoteException;
}
