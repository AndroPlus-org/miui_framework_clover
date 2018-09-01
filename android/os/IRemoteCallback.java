// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Bundle, Binder, 
//            IBinder, Parcel

public interface IRemoteCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRemoteCallback
    {

        public static IRemoteCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IRemoteCallback");
            if(iinterface != null && (iinterface instanceof IRemoteCallback))
                return (IRemoteCallback)iinterface;
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
                parcel1.writeString("android.os.IRemoteCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IRemoteCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            sendResult(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IRemoteCallback";
        static final int TRANSACTION_sendResult = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IRemoteCallback");
        }
    }

    private static class Stub.Proxy
        implements IRemoteCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IRemoteCallback";
        }

        public void sendResult(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IRemoteCallback");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_44;
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void sendResult(Bundle bundle)
        throws RemoteException;
}
