// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

// Referenced classes of package android.media:
//            IRemoteDisplayCallback

public interface IRemoteDisplayProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRemoteDisplayProvider
    {

        public static IRemoteDisplayProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IRemoteDisplayProvider");
            if(iinterface != null && (iinterface instanceof IRemoteDisplayProvider))
                return (IRemoteDisplayProvider)iinterface;
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
                parcel1.writeString("android.media.IRemoteDisplayProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IRemoteDisplayProvider");
                setCallback(IRemoteDisplayCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IRemoteDisplayProvider");
                setDiscoveryMode(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.IRemoteDisplayProvider");
                connect(parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.IRemoteDisplayProvider");
                disconnect(parcel.readString());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.IRemoteDisplayProvider");
                setVolume(parcel.readString(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.IRemoteDisplayProvider");
                adjustVolume(parcel.readString(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IRemoteDisplayProvider";
        static final int TRANSACTION_adjustVolume = 6;
        static final int TRANSACTION_connect = 3;
        static final int TRANSACTION_disconnect = 4;
        static final int TRANSACTION_setCallback = 1;
        static final int TRANSACTION_setDiscoveryMode = 2;
        static final int TRANSACTION_setVolume = 5;

        public Stub()
        {
            attachInterface(this, "android.media.IRemoteDisplayProvider");
        }
    }

    private static class Stub.Proxy
        implements IRemoteDisplayProvider
    {

        public void adjustVolume(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteDisplayProvider");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
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

        public void connect(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteDisplayProvider");
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void disconnect(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteDisplayProvider");
            parcel.writeString(s);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IRemoteDisplayProvider";
        }

        public void setCallback(IRemoteDisplayCallback iremotedisplaycallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteDisplayProvider");
            if(iremotedisplaycallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iremotedisplaycallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iremotedisplaycallback;
            parcel.recycle();
            throw iremotedisplaycallback;
        }

        public void setDiscoveryMode(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteDisplayProvider");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setVolume(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteDisplayProvider");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
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


    public abstract void adjustVolume(String s, int i)
        throws RemoteException;

    public abstract void connect(String s)
        throws RemoteException;

    public abstract void disconnect(String s)
        throws RemoteException;

    public abstract void setCallback(IRemoteDisplayCallback iremotedisplaycallback)
        throws RemoteException;

    public abstract void setDiscoveryMode(int i)
        throws RemoteException;

    public abstract void setVolume(String s, int i)
        throws RemoteException;
}
