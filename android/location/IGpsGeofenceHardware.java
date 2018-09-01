// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

public interface IGpsGeofenceHardware
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGpsGeofenceHardware
    {

        public static IGpsGeofenceHardware asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IGpsGeofenceHardware");
            if(iinterface != null && (iinterface instanceof IGpsGeofenceHardware))
                return (IGpsGeofenceHardware)iinterface;
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
            boolean flag4;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.location.IGpsGeofenceHardware");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IGpsGeofenceHardware");
                boolean flag = isHardwareGeofenceSupported();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.IGpsGeofenceHardware");
                boolean flag1 = addCircularHardwareGeofence(parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.location.IGpsGeofenceHardware");
                boolean flag2 = removeHardwareGeofence(parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.location.IGpsGeofenceHardware");
                boolean flag3 = pauseHardwareGeofence(parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.location.IGpsGeofenceHardware");
                flag4 = resumeHardwareGeofence(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(flag4)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.location.IGpsGeofenceHardware";
        static final int TRANSACTION_addCircularHardwareGeofence = 2;
        static final int TRANSACTION_isHardwareGeofenceSupported = 1;
        static final int TRANSACTION_pauseHardwareGeofence = 4;
        static final int TRANSACTION_removeHardwareGeofence = 3;
        static final int TRANSACTION_resumeHardwareGeofence = 5;

        public Stub()
        {
            attachInterface(this, "android.location.IGpsGeofenceHardware");
        }
    }

    private static class Stub.Proxy
        implements IGpsGeofenceHardware
    {

        public boolean addCircularHardwareGeofence(int i, double d, double d1, double d2, 
                int j, int k, int l, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGpsGeofenceHardware");
            parcel.writeInt(i);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeDouble(d2);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.IGpsGeofenceHardware";
        }

        public boolean isHardwareGeofenceSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.location.IGpsGeofenceHardware");
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

        public boolean pauseHardwareGeofence(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGpsGeofenceHardware");
            parcel.writeInt(i);
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

        public boolean removeHardwareGeofence(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGpsGeofenceHardware");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public boolean resumeHardwareGeofence(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGpsGeofenceHardware");
            parcel.writeInt(i);
            parcel.writeInt(j);
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


    public abstract boolean addCircularHardwareGeofence(int i, double d, double d1, double d2, 
            int j, int k, int l, int i1)
        throws RemoteException;

    public abstract boolean isHardwareGeofenceSupported()
        throws RemoteException;

    public abstract boolean pauseHardwareGeofence(int i)
        throws RemoteException;

    public abstract boolean removeHardwareGeofence(int i)
        throws RemoteException;

    public abstract boolean resumeHardwareGeofence(int i, int j)
        throws RemoteException;
}
