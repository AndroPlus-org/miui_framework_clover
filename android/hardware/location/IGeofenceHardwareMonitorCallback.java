// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.*;

// Referenced classes of package android.hardware.location:
//            GeofenceHardwareMonitorEvent

public interface IGeofenceHardwareMonitorCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGeofenceHardwareMonitorCallback
    {

        public static IGeofenceHardwareMonitorCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IGeofenceHardwareMonitorCallback");
            if(iinterface != null && (iinterface instanceof IGeofenceHardwareMonitorCallback))
                return (IGeofenceHardwareMonitorCallback)iinterface;
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
                parcel1.writeString("android.hardware.location.IGeofenceHardwareMonitorCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardwareMonitorCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (GeofenceHardwareMonitorEvent)GeofenceHardwareMonitorEvent.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onMonitoringSystemChange(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardwareMonitorCallback";
        static final int TRANSACTION_onMonitoringSystemChange = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IGeofenceHardwareMonitorCallback");
        }
    }

    private static class Stub.Proxy
        implements IGeofenceHardwareMonitorCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IGeofenceHardwareMonitorCallback";
        }

        public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent geofencehardwaremonitorevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareMonitorCallback");
            if(geofencehardwaremonitorevent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            geofencehardwaremonitorevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            geofencehardwaremonitorevent;
            parcel.recycle();
            throw geofencehardwaremonitorevent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onMonitoringSystemChange(GeofenceHardwareMonitorEvent geofencehardwaremonitorevent)
        throws RemoteException;
}
