// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public interface IPackageInstallObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageInstallObserver
    {

        public static IPackageInstallObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageInstallObserver");
            if(iinterface != null && (iinterface instanceof IPackageInstallObserver))
                return (IPackageInstallObserver)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageInstallObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageInstallObserver");
                packageInstalled(parcel.readString(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver";
        static final int TRANSACTION_packageInstalled = 1;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageInstallObserver");
        }
    }

    private static class Stub.Proxy
        implements IPackageInstallObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageInstallObserver";
        }

        public void packageInstalled(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void packageInstalled(String s, int i)
        throws RemoteException;
}
