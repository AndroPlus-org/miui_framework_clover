// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.os.*;

public interface IWifiAwareDiscoverySessionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWifiAwareDiscoverySessionCallback
    {

        public static IWifiAwareDiscoverySessionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            if(iinterface != null && (iinterface instanceof IWifiAwareDiscoverySessionCallback))
                return (IWifiAwareDiscoverySessionCallback)iinterface;
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
                parcel1.writeString("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                onSessionStarted(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                onSessionConfigSuccess();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                onSessionConfigFail(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                onSessionTerminated(parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                onMatch(parcel.readInt(), parcel.createByteArray(), parcel.createByteArray());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                onMessageSendSuccess(parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                onMessageSendFail(parcel.readInt(), parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
                onMessageReceived(parcel.readInt(), parcel.createByteArray());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.wifi.aware.IWifiAwareDiscoverySessionCallback";
        static final int TRANSACTION_onMatch = 5;
        static final int TRANSACTION_onMessageReceived = 8;
        static final int TRANSACTION_onMessageSendFail = 7;
        static final int TRANSACTION_onMessageSendSuccess = 6;
        static final int TRANSACTION_onSessionConfigFail = 3;
        static final int TRANSACTION_onSessionConfigSuccess = 2;
        static final int TRANSACTION_onSessionStarted = 1;
        static final int TRANSACTION_onSessionTerminated = 4;

        public Stub()
        {
            attachInterface(this, "android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
        }
    }

    private static class Stub.Proxy
        implements IWifiAwareDiscoverySessionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.wifi.aware.IWifiAwareDiscoverySessionCallback";
        }

        public void onMatch(int i, byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void onMessageReceived(int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void onMessageSendFail(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onMessageSendSuccess(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSessionConfigFail(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSessionConfigSuccess()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSessionStarted(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSessionTerminated(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareDiscoverySessionCallback");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
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


    public abstract void onMatch(int i, byte abyte0[], byte abyte1[])
        throws RemoteException;

    public abstract void onMessageReceived(int i, byte abyte0[])
        throws RemoteException;

    public abstract void onMessageSendFail(int i, int j)
        throws RemoteException;

    public abstract void onMessageSendSuccess(int i)
        throws RemoteException;

    public abstract void onSessionConfigFail(int i)
        throws RemoteException;

    public abstract void onSessionConfigSuccess()
        throws RemoteException;

    public abstract void onSessionStarted(int i)
        throws RemoteException;

    public abstract void onSessionTerminated(int i)
        throws RemoteException;
}
