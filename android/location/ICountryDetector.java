// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

// Referenced classes of package android.location:
//            ICountryListener, Country

public interface ICountryDetector
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICountryDetector
    {

        public static ICountryDetector asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.ICountryDetector");
            if(iinterface != null && (iinterface instanceof ICountryDetector))
                return (ICountryDetector)iinterface;
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
                parcel1.writeString("android.location.ICountryDetector");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.ICountryDetector");
                parcel = detectCountry();
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
                parcel.enforceInterface("android.location.ICountryDetector");
                addCountryListener(ICountryListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.location.ICountryDetector");
                removeCountryListener(ICountryListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.ICountryDetector";
        static final int TRANSACTION_addCountryListener = 2;
        static final int TRANSACTION_detectCountry = 1;
        static final int TRANSACTION_removeCountryListener = 3;

        public Stub()
        {
            attachInterface(this, "android.location.ICountryDetector");
        }
    }

    private static class Stub.Proxy
        implements ICountryDetector
    {

        public void addCountryListener(ICountryListener icountrylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ICountryDetector");
            if(icountrylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = icountrylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            icountrylistener;
            parcel1.recycle();
            parcel.recycle();
            throw icountrylistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public Country detectCountry()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ICountryDetector");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Country country = (Country)Country.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return country;
_L2:
            country = null;
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
            return "android.location.ICountryDetector";
        }

        public void removeCountryListener(ICountryListener icountrylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ICountryDetector");
            if(icountrylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = icountrylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            icountrylistener;
            parcel1.recycle();
            parcel.recycle();
            throw icountrylistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addCountryListener(ICountryListener icountrylistener)
        throws RemoteException;

    public abstract Country detectCountry()
        throws RemoteException;

    public abstract void removeCountryListener(ICountryListener icountrylistener)
        throws RemoteException;
}
