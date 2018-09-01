// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

public interface IApplicationToken
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IApplicationToken
    {

        public static IApplicationToken asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IApplicationToken");
            if(iinterface != null && (iinterface instanceof IApplicationToken))
                return (IApplicationToken)iinterface;
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
                parcel1.writeString("android.view.IApplicationToken");
                break;
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.view.IApplicationToken";

        public Stub()
        {
            attachInterface(this, "android.view.IApplicationToken");
        }
    }

    private static class Stub.Proxy
        implements IApplicationToken
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IApplicationToken";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }

}
