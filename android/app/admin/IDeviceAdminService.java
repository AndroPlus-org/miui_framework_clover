// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.os.*;

public interface IDeviceAdminService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDeviceAdminService
    {

        public static IDeviceAdminService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.admin.IDeviceAdminService");
            if(iinterface != null && (iinterface instanceof IDeviceAdminService))
                return (IDeviceAdminService)iinterface;
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
                parcel1.writeString("android.app.admin.IDeviceAdminService");
                break;
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.app.admin.IDeviceAdminService";

        public Stub()
        {
            attachInterface(this, "android.app.admin.IDeviceAdminService");
        }
    }

    private static class Stub.Proxy
        implements IDeviceAdminService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.admin.IDeviceAdminService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }

}
