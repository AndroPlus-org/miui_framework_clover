// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, IBinder, Binder, 
//            Parcel

public interface IUpdateLock
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUpdateLock
    {

        public static IUpdateLock asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IUpdateLock");
            if(iinterface != null && (iinterface instanceof IUpdateLock))
                return (IUpdateLock)iinterface;
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
                parcel1.writeString("android.os.IUpdateLock");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IUpdateLock");
                acquireUpdateLock(parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IUpdateLock");
                releaseUpdateLock(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IUpdateLock";
        static final int TRANSACTION_acquireUpdateLock = 1;
        static final int TRANSACTION_releaseUpdateLock = 2;

        public Stub()
        {
            attachInterface(this, "android.os.IUpdateLock");
        }
    }

    private static class Stub.Proxy
        implements IUpdateLock
    {

        public void acquireUpdateLock(IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateLock");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IUpdateLock";
        }

        public void releaseUpdateLock(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateLock");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void acquireUpdateLock(IBinder ibinder, String s)
        throws RemoteException;

    public abstract void releaseUpdateLock(IBinder ibinder)
        throws RemoteException;
}
