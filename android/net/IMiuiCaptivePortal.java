// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

public interface IMiuiCaptivePortal
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiCaptivePortal
    {

        public static IMiuiCaptivePortal asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.IMiuiCaptivePortal");
            if(iinterface != null && (iinterface instanceof IMiuiCaptivePortal))
                return (IMiuiCaptivePortal)iinterface;
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
                parcel1.writeString("android.net.IMiuiCaptivePortal");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.IMiuiCaptivePortal");
                appResponse(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.IMiuiCaptivePortal";
        static final int TRANSACTION_appResponse = 1;

        public Stub()
        {
            attachInterface(this, "android.net.IMiuiCaptivePortal");
        }
    }

    private static class Stub.Proxy
        implements IMiuiCaptivePortal
    {

        public void appResponse(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IMiuiCaptivePortal");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.IMiuiCaptivePortal";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void appResponse(int i)
        throws RemoteException;
}
