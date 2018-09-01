// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

// Referenced classes of package android.location:
//            Country

public interface ICountryListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICountryListener
    {

        public static ICountryListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.ICountryListener");
            if(iinterface != null && (iinterface instanceof ICountryListener))
                return (ICountryListener)iinterface;
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
                parcel1.writeString("android.location.ICountryListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.ICountryListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Country)Country.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onCountryDetected(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.location.ICountryListener";
        static final int TRANSACTION_onCountryDetected = 1;

        public Stub()
        {
            attachInterface(this, "android.location.ICountryListener");
        }
    }

    private static class Stub.Proxy
        implements ICountryListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.ICountryListener";
        }

        public void onCountryDetected(Country country)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ICountryListener");
            if(country == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            country.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            country;
            parcel.recycle();
            throw country;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onCountryDetected(Country country)
        throws RemoteException;
}
