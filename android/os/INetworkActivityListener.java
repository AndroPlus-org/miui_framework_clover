// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface INetworkActivityListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkActivityListener
    {

        public static INetworkActivityListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.INetworkActivityListener");
            if(iinterface != null && (iinterface instanceof INetworkActivityListener))
                return (INetworkActivityListener)iinterface;
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
                parcel1.writeString("android.os.INetworkActivityListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.INetworkActivityListener");
                onNetworkActive();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.INetworkActivityListener";
        static final int TRANSACTION_onNetworkActive = 1;

        public Stub()
        {
            attachInterface(this, "android.os.INetworkActivityListener");
        }
    }

    private static class Stub.Proxy
        implements INetworkActivityListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.INetworkActivityListener";
        }

        public void onNetworkActive()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkActivityListener");
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


    public abstract void onNetworkActive()
        throws RemoteException;
}
