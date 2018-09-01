// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.*;

public interface ICameraClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICameraClient
    {

        public static ICameraClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.ICameraClient");
            if(iinterface != null && (iinterface instanceof ICameraClient))
                return (ICameraClient)iinterface;
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
                parcel1.writeString("android.hardware.ICameraClient");
                break;
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.ICameraClient";

        public Stub()
        {
            attachInterface(this, "android.hardware.ICameraClient");
        }
    }

    private static class Stub.Proxy
        implements ICameraClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.ICameraClient";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }

}
