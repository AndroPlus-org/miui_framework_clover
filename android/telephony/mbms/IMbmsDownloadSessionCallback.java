// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.*;
import java.util.List;

// Referenced classes of package android.telephony.mbms:
//            FileServiceInfo

public interface IMbmsDownloadSessionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMbmsDownloadSessionCallback
    {

        public static IMbmsDownloadSessionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.telephony.mbms.IMbmsDownloadSessionCallback");
            if(iinterface != null && (iinterface instanceof IMbmsDownloadSessionCallback))
                return (IMbmsDownloadSessionCallback)iinterface;
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
                parcel1.writeString("android.telephony.mbms.IMbmsDownloadSessionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.telephony.mbms.IMbmsDownloadSessionCallback");
                onError(parcel.readInt(), parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.telephony.mbms.IMbmsDownloadSessionCallback");
                onFileServicesUpdated(parcel.createTypedArrayList(FileServiceInfo.CREATOR));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.telephony.mbms.IMbmsDownloadSessionCallback");
                onMiddlewareReady();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.telephony.mbms.IMbmsDownloadSessionCallback";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onFileServicesUpdated = 2;
        static final int TRANSACTION_onMiddlewareReady = 3;

        public Stub()
        {
            attachInterface(this, "android.telephony.mbms.IMbmsDownloadSessionCallback");
        }
    }

    private static class Stub.Proxy
        implements IMbmsDownloadSessionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.telephony.mbms.IMbmsDownloadSessionCallback";
        }

        public void onError(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IMbmsDownloadSessionCallback");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onFileServicesUpdated(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IMbmsDownloadSessionCallback");
            parcel.writeTypedList(list);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void onMiddlewareReady()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IMbmsDownloadSessionCallback");
            mRemote.transact(3, parcel, null, 1);
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


    public abstract void onError(int i, String s)
        throws RemoteException;

    public abstract void onFileServicesUpdated(List list)
        throws RemoteException;

    public abstract void onMiddlewareReady()
        throws RemoteException;
}
