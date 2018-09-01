// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.weeder;

import android.os.*;

public interface IWeeder
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWeeder
    {

        public static IWeeder asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.weeder.IWeeder");
            if(iinterface != null && (iinterface instanceof IWeeder))
                return (IWeeder)iinterface;
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
                parcel1.writeString("com.miui.weeder.IWeeder");
                break;
            }
            return true;
        }

        private static final String DESCRIPTOR = "com.miui.weeder.IWeeder";

        public Stub()
        {
            attachInterface(this, "com.miui.weeder.IWeeder");
        }
    }

    private static class Stub.Proxy
        implements IWeeder
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.weeder.IWeeder";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }

}
