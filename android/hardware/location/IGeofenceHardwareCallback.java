// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.location.Location;
import android.os.*;

public interface IGeofenceHardwareCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGeofenceHardwareCallback
    {

        public static IGeofenceHardwareCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IGeofenceHardwareCallback");
            if(iinterface != null && (iinterface instanceof IGeofenceHardwareCallback))
                return (IGeofenceHardwareCallback)iinterface;
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
                parcel1.writeString("android.hardware.location.IGeofenceHardwareCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
                j = parcel.readInt();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel1 = (Location)Location.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onGeofenceTransition(j, i, parcel1, parcel.readLong(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
                onGeofenceAdd(parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
                onGeofenceRemove(parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
                onGeofencePause(parcel.readInt(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.location.IGeofenceHardwareCallback");
                onGeofenceResume(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardwareCallback";
        static final int TRANSACTION_onGeofenceAdd = 2;
        static final int TRANSACTION_onGeofencePause = 4;
        static final int TRANSACTION_onGeofenceRemove = 3;
        static final int TRANSACTION_onGeofenceResume = 5;
        static final int TRANSACTION_onGeofenceTransition = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IGeofenceHardwareCallback");
        }
    }

    private static class Stub.Proxy
        implements IGeofenceHardwareCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IGeofenceHardwareCallback";
        }

        public void onGeofenceAdd(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGeofencePause(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGeofenceRemove(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGeofenceResume(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGeofenceTransition(int i, int j, Location location, long l, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(location == null)
                break MISSING_BLOCK_LABEL_76;
            parcel.writeInt(1);
            location.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            parcel.writeInt(k);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            location;
            parcel.recycle();
            throw location;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onGeofenceAdd(int i, int j)
        throws RemoteException;

    public abstract void onGeofencePause(int i, int j)
        throws RemoteException;

    public abstract void onGeofenceRemove(int i, int j)
        throws RemoteException;

    public abstract void onGeofenceResume(int i, int j)
        throws RemoteException;

    public abstract void onGeofenceTransition(int i, int j, Location location, long l, int k)
        throws RemoteException;
}
