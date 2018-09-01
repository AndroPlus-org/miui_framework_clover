// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.os.*;

// Referenced classes of package android.hardware.location:
//            GeofenceHardwareRequestParcelable, IGeofenceHardwareCallback, IGeofenceHardwareMonitorCallback

public interface IGeofenceHardware
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGeofenceHardware
    {

        public static IGeofenceHardware asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IGeofenceHardware");
            if(iinterface != null && (iinterface instanceof IGeofenceHardware))
                return (IGeofenceHardware)iinterface;
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
            boolean flag5;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.hardware.location.IGeofenceHardware");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                setGpsGeofenceHardware(android.location.IGpsGeofenceHardware.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                setFusedGeofenceHardware(android.location.IFusedGeofenceHardware.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                parcel = getMonitoringTypes();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                i = getStatusOfMonitoringType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                i = parcel.readInt();
                GeofenceHardwareRequestParcelable geofencehardwarerequestparcelable;
                boolean flag;
                if(parcel.readInt() != 0)
                    geofencehardwarerequestparcelable = (GeofenceHardwareRequestParcelable)GeofenceHardwareRequestParcelable.CREATOR.createFromParcel(parcel);
                else
                    geofencehardwarerequestparcelable = null;
                flag = addCircularFence(i, geofencehardwarerequestparcelable, IGeofenceHardwareCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                boolean flag1 = removeGeofence(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                boolean flag2 = pauseGeofence(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                boolean flag3 = resumeGeofence(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                boolean flag4 = registerForMonitorStateChangeCallback(parcel.readInt(), IGeofenceHardwareMonitorCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardware");
                flag5 = unregisterForMonitorStateChangeCallback(parcel.readInt(), IGeofenceHardwareMonitorCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                break;
            }
            if(flag5)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardware";
        static final int TRANSACTION_addCircularFence = 5;
        static final int TRANSACTION_getMonitoringTypes = 3;
        static final int TRANSACTION_getStatusOfMonitoringType = 4;
        static final int TRANSACTION_pauseGeofence = 7;
        static final int TRANSACTION_registerForMonitorStateChangeCallback = 9;
        static final int TRANSACTION_removeGeofence = 6;
        static final int TRANSACTION_resumeGeofence = 8;
        static final int TRANSACTION_setFusedGeofenceHardware = 2;
        static final int TRANSACTION_setGpsGeofenceHardware = 1;
        static final int TRANSACTION_unregisterForMonitorStateChangeCallback = 10;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IGeofenceHardware");
        }
    }

    private static class Stub.Proxy
        implements IGeofenceHardware
    {

        public boolean addCircularFence(int i, GeofenceHardwareRequestParcelable geofencehardwarerequestparcelable, IGeofenceHardwareCallback igeofencehardwarecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            parcel.writeInt(i);
            if(geofencehardwarerequestparcelable == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            geofencehardwarerequestparcelable.writeToParcel(parcel, 0);
_L4:
            geofencehardwarerequestparcelable = obj;
            if(igeofencehardwarecallback == null)
                break MISSING_BLOCK_LABEL_57;
            geofencehardwarerequestparcelable = igeofencehardwarecallback.asBinder();
            parcel.writeStrongBinder(geofencehardwarerequestparcelable);
            mRemote.transact(5, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            geofencehardwarerequestparcelable;
            parcel1.recycle();
            parcel.recycle();
            throw geofencehardwarerequestparcelable;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IGeofenceHardware";
        }

        public int[] getMonitoringTypes()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getStatusOfMonitoringType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean pauseGeofence(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public boolean registerForMonitorStateChangeCallback(int i, IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            parcel.writeInt(i);
            if(igeofencehardwaremonitorcallback == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = igeofencehardwaremonitorcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, parcel1, 0);
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
            igeofencehardwaremonitorcallback;
            parcel1.recycle();
            parcel.recycle();
            throw igeofencehardwaremonitorcallback;
        }

        public boolean removeGeofence(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public boolean resumeGeofence(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void setFusedGeofenceHardware(IFusedGeofenceHardware ifusedgeofencehardware)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            if(ifusedgeofencehardware == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ifusedgeofencehardware.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ifusedgeofencehardware;
            parcel1.recycle();
            parcel.recycle();
            throw ifusedgeofencehardware;
        }

        public void setGpsGeofenceHardware(IGpsGeofenceHardware igpsgeofencehardware)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            if(igpsgeofencehardware == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = igpsgeofencehardware.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            igpsgeofencehardware;
            parcel1.recycle();
            parcel.recycle();
            throw igpsgeofencehardware;
        }

        public boolean unregisterForMonitorStateChangeCallback(int i, IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
            parcel.writeInt(i);
            if(igeofencehardwaremonitorcallback == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = igeofencehardwaremonitorcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
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
            igeofencehardwaremonitorcallback;
            parcel1.recycle();
            parcel.recycle();
            throw igeofencehardwaremonitorcallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean addCircularFence(int i, GeofenceHardwareRequestParcelable geofencehardwarerequestparcelable, IGeofenceHardwareCallback igeofencehardwarecallback)
        throws RemoteException;

    public abstract int[] getMonitoringTypes()
        throws RemoteException;

    public abstract int getStatusOfMonitoringType(int i)
        throws RemoteException;

    public abstract boolean pauseGeofence(int i, int j)
        throws RemoteException;

    public abstract boolean registerForMonitorStateChangeCallback(int i, IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback)
        throws RemoteException;

    public abstract boolean removeGeofence(int i, int j)
        throws RemoteException;

    public abstract boolean resumeGeofence(int i, int j, int k)
        throws RemoteException;

    public abstract void setFusedGeofenceHardware(IFusedGeofenceHardware ifusedgeofencehardware)
        throws RemoteException;

    public abstract void setGpsGeofenceHardware(IGpsGeofenceHardware igpsgeofencehardware)
        throws RemoteException;

    public abstract boolean unregisterForMonitorStateChangeCallback(int i, IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback)
        throws RemoteException;
}
