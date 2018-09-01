// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface IDeviceIdentifiersPolicyService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDeviceIdentifiersPolicyService
    {

        public static IDeviceIdentifiersPolicyService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IDeviceIdentifiersPolicyService");
            if(iinterface != null && (iinterface instanceof IDeviceIdentifiersPolicyService))
                return (IDeviceIdentifiersPolicyService)iinterface;
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
                parcel1.writeString("android.os.IDeviceIdentifiersPolicyService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IDeviceIdentifiersPolicyService");
                parcel = getSerial();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IDeviceIdentifiersPolicyService";
        static final int TRANSACTION_getSerial = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IDeviceIdentifiersPolicyService");
        }
    }

    private static class Stub.Proxy
        implements IDeviceIdentifiersPolicyService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IDeviceIdentifiersPolicyService";
        }

        public String getSerial()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.os.IDeviceIdentifiersPolicyService");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract String getSerial()
        throws RemoteException;
}
