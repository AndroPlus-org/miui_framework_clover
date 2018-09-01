// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

// Referenced classes of package android.location:
//            GnssMeasurementsEvent

public interface IGnssMeasurementsListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGnssMeasurementsListener
    {

        public static IGnssMeasurementsListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IGnssMeasurementsListener");
            if(iinterface != null && (iinterface instanceof IGnssMeasurementsListener))
                return (IGnssMeasurementsListener)iinterface;
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
                parcel1.writeString("android.location.IGnssMeasurementsListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IGnssMeasurementsListener");
                if(parcel.readInt() != 0)
                    parcel = (GnssMeasurementsEvent)GnssMeasurementsEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onGnssMeasurementsReceived(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.IGnssMeasurementsListener");
                onStatusChanged(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.IGnssMeasurementsListener";
        static final int TRANSACTION_onGnssMeasurementsReceived = 1;
        static final int TRANSACTION_onStatusChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.location.IGnssMeasurementsListener");
        }
    }

    private static class Stub.Proxy
        implements IGnssMeasurementsListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.IGnssMeasurementsListener";
        }

        public void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssmeasurementsevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssMeasurementsListener");
            if(gnssmeasurementsevent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            gnssmeasurementsevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            gnssmeasurementsevent;
            parcel.recycle();
            throw gnssmeasurementsevent;
        }

        public void onStatusChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssMeasurementsListener");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
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


    public abstract void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssmeasurementsevent)
        throws RemoteException;

    public abstract void onStatusChanged(int i)
        throws RemoteException;
}
