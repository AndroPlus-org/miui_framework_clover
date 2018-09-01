// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.*;

// Referenced classes of package android.app.backup:
//            RestoreSet

public interface IRestoreObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRestoreObserver
    {

        public static IRestoreObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.backup.IRestoreObserver");
            if(iinterface != null && (iinterface instanceof IRestoreObserver))
                return (IRestoreObserver)iinterface;
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
                parcel1.writeString("android.app.backup.IRestoreObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.backup.IRestoreObserver");
                restoreSetsAvailable((RestoreSet[])parcel.createTypedArray(RestoreSet.CREATOR));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.backup.IRestoreObserver");
                restoreStarting(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.backup.IRestoreObserver");
                onUpdate(parcel.readInt(), parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.backup.IRestoreObserver");
                restoreFinished(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.backup.IRestoreObserver";
        static final int TRANSACTION_onUpdate = 3;
        static final int TRANSACTION_restoreFinished = 4;
        static final int TRANSACTION_restoreSetsAvailable = 1;
        static final int TRANSACTION_restoreStarting = 2;

        public Stub()
        {
            attachInterface(this, "android.app.backup.IRestoreObserver");
        }
    }

    private static class Stub.Proxy
        implements IRestoreObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.backup.IRestoreObserver";
        }

        public void onUpdate(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void restoreFinished(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void restoreSetsAvailable(RestoreSet arestoreset[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
            parcel.writeTypedArray(arestoreset, 0);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            arestoreset;
            parcel.recycle();
            throw arestoreset;
        }

        public void restoreStarting(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
            parcel.writeInt(i);
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


    public abstract void onUpdate(int i, String s)
        throws RemoteException;

    public abstract void restoreFinished(int i)
        throws RemoteException;

    public abstract void restoreSetsAvailable(RestoreSet arestoreset[])
        throws RemoteException;

    public abstract void restoreStarting(int i)
        throws RemoteException;
}
