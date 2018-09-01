// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface IRecoverySystemProgressListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRecoverySystemProgressListener
    {

        public static IRecoverySystemProgressListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IRecoverySystemProgressListener");
            if(iinterface != null && (iinterface instanceof IRecoverySystemProgressListener))
                return (IRecoverySystemProgressListener)iinterface;
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
                parcel1.writeString("android.os.IRecoverySystemProgressListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IRecoverySystemProgressListener");
                onProgress(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IRecoverySystemProgressListener";
        static final int TRANSACTION_onProgress = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IRecoverySystemProgressListener");
        }
    }

    private static class Stub.Proxy
        implements IRecoverySystemProgressListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IRecoverySystemProgressListener";
        }

        public void onProgress(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IRecoverySystemProgressListener");
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


    public abstract void onProgress(int i)
        throws RemoteException;
}
