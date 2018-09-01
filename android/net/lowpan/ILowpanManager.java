// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.*;

// Referenced classes of package android.net.lowpan:
//            ILowpanInterface, ILowpanManagerListener

public interface ILowpanManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILowpanManager
    {

        public static ILowpanManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.lowpan.ILowpanManager");
            if(iinterface != null && (iinterface instanceof ILowpanManager))
                return (ILowpanManager)iinterface;
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
            Object obj = null;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.net.lowpan.ILowpanManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.lowpan.ILowpanManager");
                ILowpanInterface ilowpaninterface = getInterface(parcel.readString());
                parcel1.writeNoException();
                parcel = obj;
                if(ilowpaninterface != null)
                    parcel = ilowpaninterface.asBinder();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.lowpan.ILowpanManager");
                parcel = getInterfaceList();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.lowpan.ILowpanManager");
                addListener(ILowpanManagerListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.lowpan.ILowpanManager");
                removeListener(ILowpanManagerListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.lowpan.ILowpanManager");
                addInterface(ILowpanInterface.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.lowpan.ILowpanManager");
                removeInterface(ILowpanInterface.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanManager";
        static final int TRANSACTION_addInterface = 5;
        static final int TRANSACTION_addListener = 3;
        static final int TRANSACTION_getInterface = 1;
        static final int TRANSACTION_getInterfaceList = 2;
        static final int TRANSACTION_removeInterface = 6;
        static final int TRANSACTION_removeListener = 4;

        public Stub()
        {
            attachInterface(this, "android.net.lowpan.ILowpanManager");
        }
    }

    private static class Stub.Proxy
        implements ILowpanManager
    {

        public void addInterface(ILowpanInterface ilowpaninterface)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanManager");
            if(ilowpaninterface == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ilowpaninterface.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ilowpaninterface;
            parcel1.recycle();
            parcel.recycle();
            throw ilowpaninterface;
        }

        public void addListener(ILowpanManagerListener ilowpanmanagerlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanManager");
            if(ilowpanmanagerlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ilowpanmanagerlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ilowpanmanagerlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ilowpanmanagerlistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public ILowpanInterface getInterface(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanManager");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = ILowpanInterface.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.lowpan.ILowpanManager";
        }

        public String[] getInterfaceList()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanManager");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void removeInterface(ILowpanInterface ilowpaninterface)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanManager");
            if(ilowpaninterface == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ilowpaninterface.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ilowpaninterface;
            parcel1.recycle();
            parcel.recycle();
            throw ilowpaninterface;
        }

        public void removeListener(ILowpanManagerListener ilowpanmanagerlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanManager");
            if(ilowpanmanagerlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ilowpanmanagerlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ilowpanmanagerlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ilowpanmanagerlistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addInterface(ILowpanInterface ilowpaninterface)
        throws RemoteException;

    public abstract void addListener(ILowpanManagerListener ilowpanmanagerlistener)
        throws RemoteException;

    public abstract ILowpanInterface getInterface(String s)
        throws RemoteException;

    public abstract String[] getInterfaceList()
        throws RemoteException;

    public abstract void removeInterface(ILowpanInterface ilowpaninterface)
        throws RemoteException;

    public abstract void removeListener(ILowpanManagerListener ilowpanmanagerlistener)
        throws RemoteException;

    public static final String LOWPAN_SERVICE_NAME = "lowpan";
}
