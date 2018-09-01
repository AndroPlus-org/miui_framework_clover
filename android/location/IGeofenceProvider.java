// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.hardware.location.IGeofenceHardware;
import android.os.*;

public interface IGeofenceProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGeofenceProvider
    {

        public static IGeofenceProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IGeofenceProvider");
            if(iinterface != null && (iinterface instanceof IGeofenceProvider))
                return (IGeofenceProvider)iinterface;
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
                parcel1.writeString("android.location.IGeofenceProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IGeofenceProvider");
                setGeofenceHardware(android.hardware.location.IGeofenceHardware.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.IGeofenceProvider";
        static final int TRANSACTION_setGeofenceHardware = 1;

        public Stub()
        {
            attachInterface(this, "android.location.IGeofenceProvider");
        }
    }

    private static class Stub.Proxy
        implements IGeofenceProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.IGeofenceProvider";
        }

        public void setGeofenceHardware(IGeofenceHardware igeofencehardware)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGeofenceProvider");
            if(igeofencehardware == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = igeofencehardware.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            igeofencehardware;
            parcel.recycle();
            throw igeofencehardware;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void setGeofenceHardware(IGeofenceHardware igeofencehardware)
        throws RemoteException;
}
