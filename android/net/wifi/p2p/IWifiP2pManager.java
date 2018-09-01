// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p;

import android.os.*;

public interface IWifiP2pManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWifiP2pManager
    {

        public static IWifiP2pManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.wifi.p2p.IWifiP2pManager");
            if(iinterface != null && (iinterface instanceof IWifiP2pManager))
                return (IWifiP2pManager)iinterface;
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
                parcel1.writeString("android.net.wifi.p2p.IWifiP2pManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.wifi.p2p.IWifiP2pManager");
                parcel = getMessenger(parcel.readStrongBinder());
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

            case 2: // '\002'
                parcel.enforceInterface("android.net.wifi.p2p.IWifiP2pManager");
                parcel = getP2pStateMachineMessenger();
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

            case 3: // '\003'
                parcel.enforceInterface("android.net.wifi.p2p.IWifiP2pManager");
                close(parcel.readStrongBinder());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.wifi.p2p.IWifiP2pManager");
                setMiracastMode(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.wifi.p2p.IWifiP2pManager");
                checkConfigureWifiDisplayPermission();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.wifi.p2p.IWifiP2pManager";
        static final int TRANSACTION_checkConfigureWifiDisplayPermission = 5;
        static final int TRANSACTION_close = 3;
        static final int TRANSACTION_getMessenger = 1;
        static final int TRANSACTION_getP2pStateMachineMessenger = 2;
        static final int TRANSACTION_setMiracastMode = 4;

        public Stub()
        {
            attachInterface(this, "android.net.wifi.p2p.IWifiP2pManager");
        }
    }

    private static class Stub.Proxy
        implements IWifiP2pManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void checkConfigureWifiDisplayPermission()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.p2p.IWifiP2pManager");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public void close(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.p2p.IWifiP2pManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.wifi.p2p.IWifiP2pManager";
        }

        public Messenger getMessenger(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.p2p.IWifiP2pManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ibinder = (Messenger)Messenger.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            ibinder = null;
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public Messenger getP2pStateMachineMessenger()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.p2p.IWifiP2pManager");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Messenger messenger = (Messenger)Messenger.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return messenger;
_L2:
            messenger = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setMiracastMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.p2p.IWifiP2pManager");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void checkConfigureWifiDisplayPermission()
        throws RemoteException;

    public abstract void close(IBinder ibinder)
        throws RemoteException;

    public abstract Messenger getMessenger(IBinder ibinder)
        throws RemoteException;

    public abstract Messenger getP2pStateMachineMessenger()
        throws RemoteException;

    public abstract void setMiracastMode(int i)
        throws RemoteException;
}
