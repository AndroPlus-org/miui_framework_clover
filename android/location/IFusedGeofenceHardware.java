// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.hardware.location.GeofenceHardwareRequestParcelable;
import android.os.*;

public interface IFusedGeofenceHardware
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFusedGeofenceHardware
    {

        public static IFusedGeofenceHardware asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IFusedGeofenceHardware");
            if(iinterface != null && (iinterface instanceof IFusedGeofenceHardware))
                return (IFusedGeofenceHardware)iinterface;
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
                parcel1.writeString("android.location.IFusedGeofenceHardware");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IFusedGeofenceHardware");
                boolean flag = isSupported();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.IFusedGeofenceHardware");
                addGeofences((GeofenceHardwareRequestParcelable[])parcel.createTypedArray(GeofenceHardwareRequestParcelable.CREATOR));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.location.IFusedGeofenceHardware");
                removeGeofences(parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.location.IFusedGeofenceHardware");
                pauseMonitoringGeofence(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.location.IFusedGeofenceHardware");
                resumeMonitoringGeofence(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.location.IFusedGeofenceHardware");
                modifyGeofenceOptions(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.IFusedGeofenceHardware";
        static final int TRANSACTION_addGeofences = 2;
        static final int TRANSACTION_isSupported = 1;
        static final int TRANSACTION_modifyGeofenceOptions = 6;
        static final int TRANSACTION_pauseMonitoringGeofence = 4;
        static final int TRANSACTION_removeGeofences = 3;
        static final int TRANSACTION_resumeMonitoringGeofence = 5;

        public Stub()
        {
            attachInterface(this, "android.location.IFusedGeofenceHardware");
        }
    }

    private static class Stub.Proxy
        implements IFusedGeofenceHardware
    {

        public void addGeofences(GeofenceHardwareRequestParcelable ageofencehardwarerequestparcelable[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IFusedGeofenceHardware");
            parcel.writeTypedArray(ageofencehardwarerequestparcelable, 0);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ageofencehardwarerequestparcelable;
            parcel1.recycle();
            parcel.recycle();
            throw ageofencehardwarerequestparcelable;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.IFusedGeofenceHardware";
        }

        public boolean isSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.location.IFusedGeofenceHardware");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void modifyGeofenceOptions(int i, int j, int k, int l, int i1, int j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IFusedGeofenceHardware");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void pauseMonitoringGeofence(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IFusedGeofenceHardware");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void removeGeofences(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IFusedGeofenceHardware");
            parcel.writeIntArray(ai);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public void resumeMonitoringGeofence(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IFusedGeofenceHardware");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addGeofences(GeofenceHardwareRequestParcelable ageofencehardwarerequestparcelable[])
        throws RemoteException;

    public abstract boolean isSupported()
        throws RemoteException;

    public abstract void modifyGeofenceOptions(int i, int j, int k, int l, int i1, int j1)
        throws RemoteException;

    public abstract void pauseMonitoringGeofence(int i)
        throws RemoteException;

    public abstract void removeGeofences(int ai[])
        throws RemoteException;

    public abstract void resumeMonitoringGeofence(int i, int j)
        throws RemoteException;
}
