// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.media;

import android.content.pm.ParceledListSlice;
import android.os.*;

public interface IMediaBrowserServiceCallbacks
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaBrowserServiceCallbacks
    {

        public static IMediaBrowserServiceCallbacks asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.media.IMediaBrowserServiceCallbacks");
            if(iinterface != null && (iinterface instanceof IMediaBrowserServiceCallbacks))
                return (IMediaBrowserServiceCallbacks)iinterface;
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
            String s1;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.media.IMediaBrowserServiceCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.media.IMediaBrowserServiceCallbacks");
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (android.media.session.MediaSession.Token)android.media.session.MediaSession.Token.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onConnect(s, parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.media.IMediaBrowserServiceCallbacks");
                onConnectFailed();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.media.IMediaBrowserServiceCallbacks");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onLoadChildren(parcel1, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.media.IMediaBrowserServiceCallbacks");
                s1 = parcel.readString();
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onLoadChildrenWithOptions(s1, parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.media.IMediaBrowserServiceCallbacks";
        static final int TRANSACTION_onConnect = 1;
        static final int TRANSACTION_onConnectFailed = 2;
        static final int TRANSACTION_onLoadChildren = 3;
        static final int TRANSACTION_onLoadChildrenWithOptions = 4;

        public Stub()
        {
            attachInterface(this, "android.service.media.IMediaBrowserServiceCallbacks");
        }
    }

    private static class Stub.Proxy
        implements IMediaBrowserServiceCallbacks
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.media.IMediaBrowserServiceCallbacks";
        }

        public void onConnect(String s, android.media.session.MediaSession.Token token, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserServiceCallbacks");
            parcel.writeString(s);
            if(token == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            token.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_90;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void onConnectFailed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserServiceCallbacks");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onLoadChildren(String s, ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserServiceCallbacks");
            parcel.writeString(s);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onLoadChildrenWithOptions(String s, ParceledListSlice parceledlistslice, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.media.IMediaBrowserServiceCallbacks");
            parcel.writeString(s);
            if(parceledlistslice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_90;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onConnect(String s, android.media.session.MediaSession.Token token, Bundle bundle)
        throws RemoteException;

    public abstract void onConnectFailed()
        throws RemoteException;

    public abstract void onLoadChildren(String s, ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void onLoadChildrenWithOptions(String s, ParceledListSlice parceledlistslice, Bundle bundle)
        throws RemoteException;
}
