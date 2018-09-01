// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            ConnectivityMetricsEvent, INetdEventCallback

public interface IIpConnectivityMetrics
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IIpConnectivityMetrics
    {

        public static IIpConnectivityMetrics asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.IIpConnectivityMetrics");
            if(iinterface != null && (iinterface instanceof IIpConnectivityMetrics))
                return (IIpConnectivityMetrics)iinterface;
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
            boolean flag = false;
            boolean flag1 = false;
            boolean flag3;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.net.IIpConnectivityMetrics");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.IIpConnectivityMetrics");
                if(parcel.readInt() != 0)
                    parcel = (ConnectivityMetricsEvent)ConnectivityMetricsEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = logEvent(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.IIpConnectivityMetrics");
                boolean flag2 = registerNetdEventCallback(INetdEventCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.IIpConnectivityMetrics");
                flag3 = unregisterNetdEventCallback();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                break;
            }
            if(flag3)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.net.IIpConnectivityMetrics";
        static final int TRANSACTION_logEvent = 1;
        static final int TRANSACTION_registerNetdEventCallback = 2;
        static final int TRANSACTION_unregisterNetdEventCallback = 3;

        public Stub()
        {
            attachInterface(this, "android.net.IIpConnectivityMetrics");
        }
    }

    private static class Stub.Proxy
        implements IIpConnectivityMetrics
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.IIpConnectivityMetrics";
        }

        public int logEvent(ConnectivityMetricsEvent connectivitymetricsevent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpConnectivityMetrics");
            if(connectivitymetricsevent == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            connectivitymetricsevent.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            connectivitymetricsevent;
            parcel1.recycle();
            parcel.recycle();
            throw connectivitymetricsevent;
        }

        public boolean registerNetdEventCallback(INetdEventCallback inetdeventcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpConnectivityMetrics");
            if(inetdeventcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inetdeventcallback.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
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
            inetdeventcallback;
            parcel1.recycle();
            parcel.recycle();
            throw inetdeventcallback;
        }

        public boolean unregisterNetdEventCallback()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IIpConnectivityMetrics");
            mRemote.transact(3, parcel, parcel1, 0);
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


    public abstract int logEvent(ConnectivityMetricsEvent connectivitymetricsevent)
        throws RemoteException;

    public abstract boolean registerNetdEventCallback(INetdEventCallback inetdeventcallback)
        throws RemoteException;

    public abstract boolean unregisterNetdEventCallback()
        throws RemoteException;
}
