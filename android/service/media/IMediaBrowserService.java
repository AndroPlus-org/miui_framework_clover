// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.media;

import android.os.*;

// Referenced classes of package android.service.media:
//            IMediaBrowserServiceCallbacks

public interface IMediaBrowserService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaBrowserService
    {

        public static IMediaBrowserService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.media.IMediaBrowserService");
            if(iinterface != null && (iinterface instanceof IMediaBrowserService))
                return (IMediaBrowserService)iinterface;
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
                parcel1.writeString("android.service.media.IMediaBrowserService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                connect(s, parcel1, IMediaBrowserServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                disconnect(IMediaBrowserServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                addSubscriptionDeprecated(parcel.readString(), IMediaBrowserServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                removeSubscriptionDeprecated(parcel.readString(), IMediaBrowserServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                String s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                getMediaItem(s1, parcel1, IMediaBrowserServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                String s2 = parcel.readString();
                IBinder ibinder = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                addSubscription(s2, ibinder, parcel1, IMediaBrowserServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.media.IMediaBrowserService");
                removeSubscription(parcel.readString(), parcel.readStrongBinder(), IMediaBrowserServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.media.IMediaBrowserService";
        static final int TRANSACTION_addSubscription = 6;
        static final int TRANSACTION_addSubscriptionDeprecated = 3;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_getMediaItem = 5;
        static final int TRANSACTION_removeSubscription = 7;
        static final int TRANSACTION_removeSubscriptionDeprecated = 4;

        public Stub()
        {
            attachInterface(this, "android.service.media.IMediaBrowserService");
        }
    }

    private static class Stub.Proxy
        implements IMediaBrowserService
    {

        public void addSubscription(String s, IBinder ibinder, Bundle bundle, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserService");
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            s = obj;
            if(imediabrowserservicecallbacks == null)
                break MISSING_BLOCK_LABEL_60;
            s = imediabrowserservicecallbacks.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel.recycle();
            throw s;
        }

        public void addSubscriptionDeprecated(String s, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserService");
            parcel.writeString(s);
            s = obj;
            if(imediabrowserservicecallbacks == null)
                break MISSING_BLOCK_LABEL_33;
            s = imediabrowserservicecallbacks.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void connect(String s, Bundle bundle, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserService");
            parcel.writeString(s);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            s = obj;
            if(imediabrowserservicecallbacks == null)
                break MISSING_BLOCK_LABEL_52;
            s = imediabrowserservicecallbacks.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel.recycle();
            throw s;
        }

        public void disconnect(IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserService");
            if(imediabrowserservicecallbacks == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = imediabrowserservicecallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            imediabrowserservicecallbacks;
            parcel.recycle();
            throw imediabrowserservicecallbacks;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.media.IMediaBrowserService";
        }

        public void getMediaItem(String s, ResultReceiver resultreceiver, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserService");
            parcel.writeString(s);
            if(resultreceiver == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L4:
            s = obj;
            if(imediabrowserservicecallbacks == null)
                break MISSING_BLOCK_LABEL_52;
            s = imediabrowserservicecallbacks.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel.recycle();
            throw s;
        }

        public void removeSubscription(String s, IBinder ibinder, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserService");
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            s = obj;
            if(imediabrowserservicecallbacks == null)
                break MISSING_BLOCK_LABEL_41;
            s = imediabrowserservicecallbacks.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void removeSubscriptionDeprecated(String s, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserService");
            parcel.writeString(s);
            s = obj;
            if(imediabrowserservicecallbacks == null)
                break MISSING_BLOCK_LABEL_33;
            s = imediabrowserservicecallbacks.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addSubscription(String s, IBinder ibinder, Bundle bundle, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        throws RemoteException;

    public abstract void addSubscriptionDeprecated(String s, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        throws RemoteException;

    public abstract void connect(String s, Bundle bundle, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        throws RemoteException;

    public abstract void disconnect(IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        throws RemoteException;

    public abstract void getMediaItem(String s, ResultReceiver resultreceiver, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        throws RemoteException;

    public abstract void removeSubscription(String s, IBinder ibinder, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        throws RemoteException;

    public abstract void removeSubscriptionDeprecated(String s, IMediaBrowserServiceCallbacks imediabrowserservicecallbacks)
        throws RemoteException;
}
