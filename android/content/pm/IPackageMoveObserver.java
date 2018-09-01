// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public interface IPackageMoveObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageMoveObserver
    {

        public static IPackageMoveObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageMoveObserver");
            if(iinterface != null && (iinterface instanceof IPackageMoveObserver))
                return (IPackageMoveObserver)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageMoveObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageMoveObserver");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onCreated(i, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IPackageMoveObserver");
                onStatusChanged(parcel.readInt(), parcel.readInt(), parcel.readLong());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageMoveObserver";
        static final int TRANSACTION_onCreated = 1;
        static final int TRANSACTION_onStatusChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageMoveObserver");
        }
    }

    private static class Stub.Proxy
        implements IPackageMoveObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageMoveObserver";
        }

        public void onCreated(int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageMoveObserver");
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void onStatusChanged(int i, int j, long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageMoveObserver");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeLong(l);
            mRemote.transact(2, parcel, null, 1);
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


    public abstract void onCreated(int i, Bundle bundle)
        throws RemoteException;

    public abstract void onStatusChanged(int i, int j, long l)
        throws RemoteException;
}
