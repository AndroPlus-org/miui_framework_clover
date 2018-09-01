// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.location.Location;
import android.os.*;

public interface ILocationCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILocationCallback
    {

        public static ILocationCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.ILocationCallback");
            if(iinterface != null && (iinterface instanceof ILocationCallback))
                return (ILocationCallback)iinterface;
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
                parcel1.writeString("com.miui.whetstone.ILocationCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.ILocationCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Location)Location.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onLocationChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.ILocationCallback";
        static final int TRANSACTION_onLocationChanged = 1;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.ILocationCallback");
        }
    }

    private static class Stub.Proxy
        implements ILocationCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.ILocationCallback";
        }

        public void onLocationChanged(Location location)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.ILocationCallback");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onLocationChanged(Location location)
        throws RemoteException;
}
