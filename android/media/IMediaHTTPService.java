// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

// Referenced classes of package android.media:
//            IMediaHTTPConnection

public interface IMediaHTTPService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaHTTPService
    {

        public static IMediaHTTPService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IMediaHTTPService");
            if(iinterface != null && (iinterface instanceof IMediaHTTPService))
                return (IMediaHTTPService)iinterface;
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
            Object obj = null;
            IMediaHTTPConnection imediahttpconnection;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.media.IMediaHTTPService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IMediaHTTPService");
                imediahttpconnection = makeHTTPConnection();
                parcel1.writeNoException();
                parcel = obj;
                break;
            }
            if(imediahttpconnection != null)
                parcel = imediahttpconnection.asBinder();
            parcel1.writeStrongBinder(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.IMediaHTTPService";
        static final int TRANSACTION_makeHTTPConnection = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IMediaHTTPService");
        }
    }

    private static class Stub.Proxy
        implements IMediaHTTPService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IMediaHTTPService";
        }

        public IMediaHTTPConnection makeHTTPConnection()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IMediaHTTPConnection imediahttpconnection;
            parcel.writeInterfaceToken("android.media.IMediaHTTPService");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            imediahttpconnection = IMediaHTTPConnection.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return imediahttpconnection;
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


    public abstract IMediaHTTPConnection makeHTTPConnection()
        throws RemoteException;
}
