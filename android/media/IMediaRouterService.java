// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

// Referenced classes of package android.media:
//            IMediaRouterClient, MediaRouterClientState

public interface IMediaRouterService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaRouterService
    {

        public static IMediaRouterService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IMediaRouterService");
            if(iinterface != null && (iinterface instanceof IMediaRouterService))
                return (IMediaRouterService)iinterface;
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
            boolean flag = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.media.IMediaRouterService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IMediaRouterService");
                registerClientAsUser(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IMediaRouterService");
                unregisterClient(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.IMediaRouterService");
                parcel = getState(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()));
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

            case 4: // '\004'
                parcel.enforceInterface("android.media.IMediaRouterService");
                boolean flag1 = isPlaybackActive(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag1)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.IMediaRouterService");
                IMediaRouterClient imediarouterclient = IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                setDiscoveryRequest(imediarouterclient, i, flag2);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.IMediaRouterService");
                IMediaRouterClient imediarouterclient1 = IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder());
                String s = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setSelectedRoute(imediarouterclient1, s, flag3);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.IMediaRouterService");
                requestSetVolume(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.IMediaRouterService");
                requestUpdateVolume(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IMediaRouterService";
        static final int TRANSACTION_getState = 3;
        static final int TRANSACTION_isPlaybackActive = 4;
        static final int TRANSACTION_registerClientAsUser = 1;
        static final int TRANSACTION_requestSetVolume = 7;
        static final int TRANSACTION_requestUpdateVolume = 8;
        static final int TRANSACTION_setDiscoveryRequest = 5;
        static final int TRANSACTION_setSelectedRoute = 6;
        static final int TRANSACTION_unregisterClient = 2;

        public Stub()
        {
            attachInterface(this, "android.media.IMediaRouterService");
        }
    }

    private static class Stub.Proxy
        implements IMediaRouterService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IMediaRouterService";
        }

        public MediaRouterClientState getState(IMediaRouterClient imediarouterclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterService");
            if(imediarouterclient == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediarouterclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            imediarouterclient = (MediaRouterClientState)MediaRouterClientState.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return imediarouterclient;
_L2:
            imediarouterclient = null;
            if(true) goto _L4; else goto _L3
_L3:
            imediarouterclient;
            parcel1.recycle();
            parcel.recycle();
            throw imediarouterclient;
        }

        public boolean isPlaybackActive(IMediaRouterClient imediarouterclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterService");
            if(imediarouterclient == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediarouterclient.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            imediarouterclient;
            parcel1.recycle();
            parcel.recycle();
            throw imediarouterclient;
        }

        public void registerClientAsUser(IMediaRouterClient imediarouterclient, String s, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterService");
            if(imediarouterclient == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = imediarouterclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediarouterclient;
            parcel1.recycle();
            parcel.recycle();
            throw imediarouterclient;
        }

        public void requestSetVolume(IMediaRouterClient imediarouterclient, String s, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterService");
            if(imediarouterclient == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = imediarouterclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediarouterclient;
            parcel1.recycle();
            parcel.recycle();
            throw imediarouterclient;
        }

        public void requestUpdateVolume(IMediaRouterClient imediarouterclient, String s, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterService");
            if(imediarouterclient == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = imediarouterclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediarouterclient;
            parcel1.recycle();
            parcel.recycle();
            throw imediarouterclient;
        }

        public void setDiscoveryRequest(IMediaRouterClient imediarouterclient, int i, boolean flag)
            throws RemoteException
        {
            IBinder ibinder;
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterService");
            if(imediarouterclient == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = imediarouterclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediarouterclient;
            parcel1.recycle();
            parcel.recycle();
            throw imediarouterclient;
        }

        public void setSelectedRoute(IMediaRouterClient imediarouterclient, String s, boolean flag)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterService");
            if(imediarouterclient == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = imediarouterclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediarouterclient;
            parcel1.recycle();
            parcel.recycle();
            throw imediarouterclient;
        }

        public void unregisterClient(IMediaRouterClient imediarouterclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaRouterService");
            if(imediarouterclient == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediarouterclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediarouterclient;
            parcel1.recycle();
            parcel.recycle();
            throw imediarouterclient;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract MediaRouterClientState getState(IMediaRouterClient imediarouterclient)
        throws RemoteException;

    public abstract boolean isPlaybackActive(IMediaRouterClient imediarouterclient)
        throws RemoteException;

    public abstract void registerClientAsUser(IMediaRouterClient imediarouterclient, String s, int i)
        throws RemoteException;

    public abstract void requestSetVolume(IMediaRouterClient imediarouterclient, String s, int i)
        throws RemoteException;

    public abstract void requestUpdateVolume(IMediaRouterClient imediarouterclient, String s, int i)
        throws RemoteException;

    public abstract void setDiscoveryRequest(IMediaRouterClient imediarouterclient, int i, boolean flag)
        throws RemoteException;

    public abstract void setSelectedRoute(IMediaRouterClient imediarouterclient, String s, boolean flag)
        throws RemoteException;

    public abstract void unregisterClient(IMediaRouterClient imediarouterclient)
        throws RemoteException;
}
