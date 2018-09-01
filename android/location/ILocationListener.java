// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

// Referenced classes of package android.location:
//            Location

public interface ILocationListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILocationListener
    {

        public static ILocationListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.ILocationListener");
            if(iinterface != null && (iinterface instanceof ILocationListener))
                return (ILocationListener)iinterface;
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
                parcel1.writeString("android.location.ILocationListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.ILocationListener");
                if(parcel.readInt() != 0)
                    parcel = (Location)Location.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onLocationChanged(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.ILocationListener");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onStatusChanged(parcel1, i, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.location.ILocationListener");
                onProviderEnabled(parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.location.ILocationListener");
                onProviderDisabled(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.ILocationListener";
        static final int TRANSACTION_onLocationChanged = 1;
        static final int TRANSACTION_onProviderDisabled = 4;
        static final int TRANSACTION_onProviderEnabled = 3;
        static final int TRANSACTION_onStatusChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.location.ILocationListener");
        }
    }

    private static class Stub.Proxy
        implements ILocationListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.ILocationListener";
        }

        public void onLocationChanged(Location location)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationListener");
            if(location == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            location.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            location;
            parcel.recycle();
            throw location;
        }

        public void onProviderDisabled(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationListener");
            parcel.writeString(s);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onProviderEnabled(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationListener");
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationListener");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onLocationChanged(Location location)
        throws RemoteException;

    public abstract void onProviderDisabled(String s)
        throws RemoteException;

    public abstract void onProviderEnabled(String s)
        throws RemoteException;

    public abstract void onStatusChanged(String s, int i, Bundle bundle)
        throws RemoteException;
}
