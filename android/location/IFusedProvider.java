// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.hardware.location.IFusedLocationHardware;
import android.os.*;

public interface IFusedProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFusedProvider
    {

        public static IFusedProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IFusedProvider");
            if(iinterface != null && (iinterface instanceof IFusedProvider))
                return (IFusedProvider)iinterface;
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
                parcel1.writeString("android.location.IFusedProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IFusedProvider");
                onFusedLocationHardwareChange(android.hardware.location.IFusedLocationHardware.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.IFusedProvider";
        static final int TRANSACTION_onFusedLocationHardwareChange = 1;

        public Stub()
        {
            attachInterface(this, "android.location.IFusedProvider");
        }
    }

    private static class Stub.Proxy
        implements IFusedProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.IFusedProvider";
        }

        public void onFusedLocationHardwareChange(IFusedLocationHardware ifusedlocationhardware)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IFusedProvider");
            if(ifusedlocationhardware == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ifusedlocationhardware.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ifusedlocationhardware;
            parcel.recycle();
            throw ifusedlocationhardware;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onFusedLocationHardwareChange(IFusedLocationHardware ifusedlocationhardware)
        throws RemoteException;
}
