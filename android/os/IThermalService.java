// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Temperature, IThermalEventListener, 
//            Binder, IBinder, Parcel

public interface IThermalService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IThermalService
    {

        public static IThermalService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IThermalService");
            if(iinterface != null && (iinterface instanceof IThermalService))
                return (IThermalService)iinterface;
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
            boolean flag2;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.os.IThermalService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IThermalService");
                registerThermalEventListener(IThermalEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IThermalService");
                unregisterThermalEventListener(IThermalEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IThermalService");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(parcel.readInt() != 0)
                    parcel = (Temperature)Temperature.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyThrottling(flag1, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.IThermalService");
                flag2 = isThrottling();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                break;
            }
            if(flag2)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IThermalService";
        static final int TRANSACTION_isThrottling = 4;
        static final int TRANSACTION_notifyThrottling = 3;
        static final int TRANSACTION_registerThermalEventListener = 1;
        static final int TRANSACTION_unregisterThermalEventListener = 2;

        public Stub()
        {
            attachInterface(this, "android.os.IThermalService");
        }
    }

    private static class Stub.Proxy
        implements IThermalService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IThermalService";
        }

        public boolean isThrottling()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IThermalService");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void notifyThrottling(boolean flag, Temperature temperature)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IThermalService");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(temperature == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            temperature.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            temperature;
            parcel.recycle();
            throw temperature;
        }

        public void registerThermalEventListener(IThermalEventListener ithermaleventlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IThermalService");
            if(ithermaleventlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ithermaleventlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ithermaleventlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ithermaleventlistener;
        }

        public void unregisterThermalEventListener(IThermalEventListener ithermaleventlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IThermalService");
            if(ithermaleventlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ithermaleventlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ithermaleventlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ithermaleventlistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean isThrottling()
        throws RemoteException;

    public abstract void notifyThrottling(boolean flag, Temperature temperature)
        throws RemoteException;

    public abstract void registerThermalEventListener(IThermalEventListener ithermaleventlistener)
        throws RemoteException;

    public abstract void unregisterThermalEventListener(IThermalEventListener ithermaleventlistener)
        throws RemoteException;
}
