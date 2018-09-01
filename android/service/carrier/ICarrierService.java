// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.carrier;

import android.os.*;

// Referenced classes of package android.service.carrier:
//            CarrierIdentifier

public interface ICarrierService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICarrierService
    {

        public static ICarrierService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.carrier.ICarrierService");
            if(iinterface != null && (iinterface instanceof ICarrierService))
                return (ICarrierService)iinterface;
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
                parcel1.writeString("android.service.carrier.ICarrierService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.carrier.ICarrierService");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (CarrierIdentifier)CarrierIdentifier.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            parcel = getCarrierConfig(parcel);
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
        }

        private static final String DESCRIPTOR = "android.service.carrier.ICarrierService";
        static final int TRANSACTION_getCarrierConfig = 1;

        public Stub()
        {
            attachInterface(this, "android.service.carrier.ICarrierService");
        }
    }

    private static class Stub.Proxy
        implements ICarrierService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public PersistableBundle getCarrierConfig(CarrierIdentifier carrieridentifier)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierService");
            if(carrieridentifier == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            carrieridentifier.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_96;
            carrieridentifier = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return carrieridentifier;
_L2:
            parcel.writeInt(0);
              goto _L3
            carrieridentifier;
            parcel1.recycle();
            parcel.recycle();
            throw carrieridentifier;
            carrieridentifier = null;
              goto _L4
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.carrier.ICarrierService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract PersistableBundle getCarrierConfig(CarrierIdentifier carrieridentifier)
        throws RemoteException;
}
