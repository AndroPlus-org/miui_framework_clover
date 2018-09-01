// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms.vendor;

import android.net.Uri;
import android.os.*;
import android.telephony.mbms.IMbmsStreamingSessionCallback;
import android.telephony.mbms.IStreamingServiceCallback;
import java.util.List;

public interface IMbmsStreamingService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMbmsStreamingService
    {

        public static IMbmsStreamingService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.telephony.mbms.vendor.IMbmsStreamingService");
            if(iinterface != null && (iinterface instanceof IMbmsStreamingService))
                return (IMbmsStreamingService)iinterface;
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
                parcel1.writeString("android.telephony.mbms.vendor.IMbmsStreamingService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsStreamingService");
                i = initialize(android.telephony.mbms.IMbmsStreamingSessionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsStreamingService");
                i = requestUpdateStreamingServices(parcel.readInt(), parcel.createStringArrayList());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsStreamingService");
                i = startStreaming(parcel.readInt(), parcel.readString(), android.telephony.mbms.IStreamingServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsStreamingService");
                parcel = getPlaybackUri(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsStreamingService");
                stopStreaming(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsStreamingService");
                dispose(parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.telephony.mbms.vendor.IMbmsStreamingService";
        static final int TRANSACTION_dispose = 6;
        static final int TRANSACTION_getPlaybackUri = 4;
        static final int TRANSACTION_initialize = 1;
        static final int TRANSACTION_requestUpdateStreamingServices = 2;
        static final int TRANSACTION_startStreaming = 3;
        static final int TRANSACTION_stopStreaming = 5;

        public Stub()
        {
            attachInterface(this, "android.telephony.mbms.vendor.IMbmsStreamingService");
        }
    }

    private static class Stub.Proxy
        implements IMbmsStreamingService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dispose(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsStreamingService");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.telephony.mbms.vendor.IMbmsStreamingService";
        }

        public Uri getPlaybackUri(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsStreamingService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Uri)Uri.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int initialize(IMbmsStreamingSessionCallback imbmsstreamingsessioncallback, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsStreamingService");
            if(imbmsstreamingsessioncallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = imbmsstreamingsessioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            imbmsstreamingsessioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw imbmsstreamingsessioncallback;
        }

        public int requestUpdateStreamingServices(int i, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsStreamingService");
            parcel.writeInt(i);
            parcel.writeStringList(list);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public int startStreaming(int i, String s, IStreamingServiceCallback istreamingservicecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsStreamingService");
            parcel.writeInt(i);
            parcel.writeString(s);
            s = obj;
            if(istreamingservicecallback == null)
                break MISSING_BLOCK_LABEL_46;
            s = istreamingservicecallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void stopStreaming(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsStreamingService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dispose(int i)
        throws RemoteException;

    public abstract Uri getPlaybackUri(int i, String s)
        throws RemoteException;

    public abstract int initialize(IMbmsStreamingSessionCallback imbmsstreamingsessioncallback, int i)
        throws RemoteException;

    public abstract int requestUpdateStreamingServices(int i, List list)
        throws RemoteException;

    public abstract int startStreaming(int i, String s, IStreamingServiceCallback istreamingservicecallback)
        throws RemoteException;

    public abstract void stopStreaming(int i, String s)
        throws RemoteException;
}
