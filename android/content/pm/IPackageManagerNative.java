// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public interface IPackageManagerNative
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageManagerNative
    {

        public static IPackageManagerNative asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageManagerNative");
            if(iinterface != null && (iinterface instanceof IPackageManagerNative))
                return (IPackageManagerNative)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageManagerNative");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageManagerNative");
                parcel = getNamesForUids(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IPackageManagerNative");
                parcel = getInstallerForPackage(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.pm.IPackageManagerNative");
                i = getVersionCodeForPackage(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageManagerNative";
        static final int TRANSACTION_getInstallerForPackage = 2;
        static final int TRANSACTION_getNamesForUids = 1;
        static final int TRANSACTION_getVersionCodeForPackage = 3;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageManagerNative");
        }
    }

    private static class Stub.Proxy
        implements IPackageManagerNative
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInstallerForPackage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManagerNative");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageManagerNative";
        }

        public String[] getNamesForUids(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageManagerNative");
            parcel.writeIntArray(ai);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public int getVersionCodeForPackage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IPackageManagerNative");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract String getInstallerForPackage(String s)
        throws RemoteException;

    public abstract String[] getNamesForUids(int ai[])
        throws RemoteException;

    public abstract int getVersionCodeForPackage(String s)
        throws RemoteException;
}
