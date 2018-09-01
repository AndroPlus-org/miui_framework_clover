// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.location.Location;
import android.os.*;

public interface IFusedLocationHardwareSink
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFusedLocationHardwareSink
    {

        public static IFusedLocationHardwareSink asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IFusedLocationHardwareSink");
            if(iinterface != null && (iinterface instanceof IFusedLocationHardwareSink))
                return (IFusedLocationHardwareSink)iinterface;
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
                parcel1.writeString("android.hardware.location.IFusedLocationHardwareSink");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardwareSink");
                onLocationAvailable((Location[])parcel.createTypedArray(Location.CREATOR));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardwareSink");
                onDiagnosticDataAvailable(parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardwareSink");
                onCapabilities(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardwareSink");
                onStatusChanged(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.location.IFusedLocationHardwareSink";
        static final int TRANSACTION_onCapabilities = 3;
        static final int TRANSACTION_onDiagnosticDataAvailable = 2;
        static final int TRANSACTION_onLocationAvailable = 1;
        static final int TRANSACTION_onStatusChanged = 4;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IFusedLocationHardwareSink");
        }
    }

    private static class Stub.Proxy
        implements IFusedLocationHardwareSink
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IFusedLocationHardwareSink";
        }

        public void onCapabilities(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardwareSink");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDiagnosticDataAvailable(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardwareSink");
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onLocationAvailable(Location alocation[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardwareSink");
            parcel.writeTypedArray(alocation, 0);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            alocation;
            parcel.recycle();
            throw alocation;
        }

        public void onStatusChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardwareSink");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
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


    public abstract void onCapabilities(int i)
        throws RemoteException;

    public abstract void onDiagnosticDataAvailable(String s)
        throws RemoteException;

    public abstract void onLocationAvailable(Location alocation[])
        throws RemoteException;

    public abstract void onStatusChanged(int i)
        throws RemoteException;
}
