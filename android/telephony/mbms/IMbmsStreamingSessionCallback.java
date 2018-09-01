// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.*;
import java.util.List;

// Referenced classes of package android.telephony.mbms:
//            StreamingServiceInfo

public interface IMbmsStreamingSessionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMbmsStreamingSessionCallback
    {

        public static IMbmsStreamingSessionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.telephony.mbms.IMbmsStreamingSessionCallback");
            if(iinterface != null && (iinterface instanceof IMbmsStreamingSessionCallback))
                return (IMbmsStreamingSessionCallback)iinterface;
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
                parcel1.writeString("android.telephony.mbms.IMbmsStreamingSessionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.telephony.mbms.IMbmsStreamingSessionCallback");
                onError(parcel.readInt(), parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.telephony.mbms.IMbmsStreamingSessionCallback");
                onStreamingServicesUpdated(parcel.createTypedArrayList(StreamingServiceInfo.CREATOR));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.telephony.mbms.IMbmsStreamingSessionCallback");
                onMiddlewareReady();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.telephony.mbms.IMbmsStreamingSessionCallback";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onMiddlewareReady = 3;
        static final int TRANSACTION_onStreamingServicesUpdated = 2;

        public Stub()
        {
            attachInterface(this, "android.telephony.mbms.IMbmsStreamingSessionCallback");
        }
    }

    private static class Stub.Proxy
        implements IMbmsStreamingSessionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.telephony.mbms.IMbmsStreamingSessionCallback";
        }

        public void onError(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IMbmsStreamingSessionCallback");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onMiddlewareReady()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IMbmsStreamingSessionCallback");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStreamingServicesUpdated(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IMbmsStreamingSessionCallback");
            parcel.writeTypedList(list);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onError(int i, String s)
        throws RemoteException;

    public abstract void onMiddlewareReady()
        throws RemoteException;

    public abstract void onStreamingServicesUpdated(List list)
        throws RemoteException;
}
