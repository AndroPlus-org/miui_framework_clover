// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

public interface IMiuiNetworkManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiNetworkManager
    {

        public static IMiuiNetworkManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.IMiuiNetworkManager");
            if(iinterface != null && (iinterface instanceof IMiuiNetworkManager))
                return (IMiuiNetworkManager)iinterface;
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
            boolean flag;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.net.IMiuiNetworkManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.IMiuiNetworkManager");
                flag = setNetworkTrafficPolicy(parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(flag)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.net.IMiuiNetworkManager";
        static final int TRANSACTION_setNetworkTrafficPolicy = 1;

        public Stub()
        {
            attachInterface(this, "android.net.IMiuiNetworkManager");
        }
    }

    private static class Stub.Proxy
        implements IMiuiNetworkManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.IMiuiNetworkManager";
        }

        public boolean setNetworkTrafficPolicy(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IMiuiNetworkManager");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean setNetworkTrafficPolicy(int i)
        throws RemoteException;
}
