// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, BatteryProperty, IBatteryPropertiesListener, 
//            Binder, IBinder, Parcel

public interface IBatteryPropertiesRegistrar
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBatteryPropertiesRegistrar
    {

        public static IBatteryPropertiesRegistrar asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IBatteryPropertiesRegistrar");
            if(iinterface != null && (iinterface instanceof IBatteryPropertiesRegistrar))
                return (IBatteryPropertiesRegistrar)iinterface;
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
                parcel1.writeString("android.os.IBatteryPropertiesRegistrar");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IBatteryPropertiesRegistrar");
                registerListener(IBatteryPropertiesListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IBatteryPropertiesRegistrar");
                unregisterListener(IBatteryPropertiesListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IBatteryPropertiesRegistrar");
                i = parcel.readInt();
                parcel = new BatteryProperty();
                i = getProperty(i, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
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
                parcel.enforceInterface("android.os.IBatteryPropertiesRegistrar");
                scheduleUpdate();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IBatteryPropertiesRegistrar";
        static final int TRANSACTION_getProperty = 3;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_scheduleUpdate = 4;
        static final int TRANSACTION_unregisterListener = 2;

        public Stub()
        {
            attachInterface(this, "android.os.IBatteryPropertiesRegistrar");
        }
    }

    private static class Stub.Proxy
        implements IBatteryPropertiesRegistrar
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IBatteryPropertiesRegistrar";
        }

        public int getProperty(int i, BatteryProperty batteryproperty)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IBatteryPropertiesRegistrar");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                batteryproperty.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            batteryproperty;
            parcel1.recycle();
            parcel.recycle();
            throw batteryproperty;
        }

        public void registerListener(IBatteryPropertiesListener ibatterypropertieslistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IBatteryPropertiesRegistrar");
            if(ibatterypropertieslistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibatterypropertieslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibatterypropertieslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ibatterypropertieslistener;
        }

        public void scheduleUpdate()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IBatteryPropertiesRegistrar");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void unregisterListener(IBatteryPropertiesListener ibatterypropertieslistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IBatteryPropertiesRegistrar");
            if(ibatterypropertieslistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibatterypropertieslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibatterypropertieslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ibatterypropertieslistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int getProperty(int i, BatteryProperty batteryproperty)
        throws RemoteException;

    public abstract void registerListener(IBatteryPropertiesListener ibatterypropertieslistener)
        throws RemoteException;

    public abstract void scheduleUpdate()
        throws RemoteException;

    public abstract void unregisterListener(IBatteryPropertiesListener ibatterypropertieslistener)
        throws RemoteException;
}
