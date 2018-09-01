// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.car;

import android.os.*;

public interface ICarServiceHelper
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICarServiceHelper
    {

        public static ICarServiceHelper asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.car.ICarServiceHelper");
            if(iinterface != null && (iinterface instanceof ICarServiceHelper))
                return (ICarServiceHelper)iinterface;
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
                parcel1.writeString("com.android.internal.car.ICarServiceHelper");
                break;
            }
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.car.ICarServiceHelper";

        public Stub()
        {
            attachInterface(this, "com.android.internal.car.ICarServiceHelper");
        }
    }

    private static class Stub.Proxy
        implements ICarServiceHelper
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.car.ICarServiceHelper";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }

}
