// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            IEthernetServiceListener, IpConfiguration

public interface IEthernetManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IEthernetManager
    {

        public static IEthernetManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.IEthernetManager");
            if(iinterface != null && (iinterface instanceof IEthernetManager))
                return (IEthernetManager)iinterface;
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
                parcel1.writeString("android.net.IEthernetManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.IEthernetManager");
                parcel = getConfiguration();
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
                parcel.enforceInterface("android.net.IEthernetManager");
                if(parcel.readInt() != 0)
                    parcel = (IpConfiguration)IpConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setConfiguration(parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.IEthernetManager");
                boolean flag1 = isAvailable();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag1)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.IEthernetManager");
                addListener(IEthernetServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.IEthernetManager");
                removeListener(IEthernetServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.IEthernetManager";
        static final int TRANSACTION_addListener = 4;
        static final int TRANSACTION_getConfiguration = 1;
        static final int TRANSACTION_isAvailable = 3;
        static final int TRANSACTION_removeListener = 5;
        static final int TRANSACTION_setConfiguration = 2;

        public Stub()
        {
            attachInterface(this, "android.net.IEthernetManager");
        }
    }

    private static class Stub.Proxy
        implements IEthernetManager
    {

        public void addListener(IEthernetServiceListener iethernetservicelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IEthernetManager");
            if(iethernetservicelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iethernetservicelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iethernetservicelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iethernetservicelistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public IpConfiguration getConfiguration()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IEthernetManager");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            IpConfiguration ipconfiguration = (IpConfiguration)IpConfiguration.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ipconfiguration;
_L2:
            ipconfiguration = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.IEthernetManager";
        }

        public boolean isAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IEthernetManager");
            mRemote.transact(3, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void removeListener(IEthernetServiceListener iethernetservicelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IEthernetManager");
            if(iethernetservicelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iethernetservicelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iethernetservicelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iethernetservicelistener;
        }

        public void setConfiguration(IpConfiguration ipconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IEthernetManager");
            if(ipconfiguration == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            ipconfiguration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ipconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw ipconfiguration;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addListener(IEthernetServiceListener iethernetservicelistener)
        throws RemoteException;

    public abstract IpConfiguration getConfiguration()
        throws RemoteException;

    public abstract boolean isAvailable()
        throws RemoteException;

    public abstract void removeListener(IEthernetServiceListener iethernetservicelistener)
        throws RemoteException;

    public abstract void setConfiguration(IpConfiguration ipconfiguration)
        throws RemoteException;
}
