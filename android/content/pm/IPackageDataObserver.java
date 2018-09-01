// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public interface IPackageDataObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageDataObserver
    {

        public static IPackageDataObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageDataObserver");
            if(iinterface != null && (iinterface instanceof IPackageDataObserver))
                return (IPackageDataObserver)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageDataObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageDataObserver");
                parcel1 = parcel.readString();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onRemoveCompleted(parcel1, flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageDataObserver";
        static final int TRANSACTION_onRemoveCompleted = 1;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageDataObserver");
        }
    }

    private static class Stub.Proxy
        implements IPackageDataObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageDataObserver";
        }

        public void onRemoveCompleted(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageDataObserver");
            parcel.writeString(s);
            if(!flag)
                i = 0;
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


    public abstract void onRemoveCompleted(String s, boolean flag)
        throws RemoteException;
}
