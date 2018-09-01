// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.os.*;

public interface IWifiAwareEventCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWifiAwareEventCallback
    {

        public static IWifiAwareEventCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.wifi.aware.IWifiAwareEventCallback");
            if(iinterface != null && (iinterface instanceof IWifiAwareEventCallback))
                return (IWifiAwareEventCallback)iinterface;
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
                parcel1.writeString("android.net.wifi.aware.IWifiAwareEventCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareEventCallback");
                onConnectSuccess(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareEventCallback");
                onConnectFail(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareEventCallback");
                onIdentityChanged(parcel.createByteArray());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareEventCallback");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (android.net.wifi.RttManager.ParcelableRttResults)android.net.wifi.RttManager.ParcelableRttResults.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onRangingSuccess(i, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareEventCallback");
                onRangingFailure(parcel.readInt(), parcel.readInt(), parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.wifi.aware.IWifiAwareEventCallback");
                onRangingAborted(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.wifi.aware.IWifiAwareEventCallback";
        static final int TRANSACTION_onConnectFail = 2;
        static final int TRANSACTION_onConnectSuccess = 1;
        static final int TRANSACTION_onIdentityChanged = 3;
        static final int TRANSACTION_onRangingAborted = 6;
        static final int TRANSACTION_onRangingFailure = 5;
        static final int TRANSACTION_onRangingSuccess = 4;

        public Stub()
        {
            attachInterface(this, "android.net.wifi.aware.IWifiAwareEventCallback");
        }
    }

    private static class Stub.Proxy
        implements IWifiAwareEventCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.wifi.aware.IWifiAwareEventCallback";
        }

        public void onConnectFail(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareEventCallback");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onConnectSuccess(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareEventCallback");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onIdentityChanged(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareEventCallback");
            parcel.writeByteArray(abyte0);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void onRangingAborted(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareEventCallback");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRangingFailure(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareEventCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onRangingSuccess(int i, android.net.wifi.RttManager.ParcelableRttResults parcelablerttresults)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.aware.IWifiAwareEventCallback");
            parcel.writeInt(i);
            if(parcelablerttresults == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            parcelablerttresults.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelablerttresults;
            parcel.recycle();
            throw parcelablerttresults;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onConnectFail(int i)
        throws RemoteException;

    public abstract void onConnectSuccess(int i)
        throws RemoteException;

    public abstract void onIdentityChanged(byte abyte0[])
        throws RemoteException;

    public abstract void onRangingAborted(int i)
        throws RemoteException;

    public abstract void onRangingFailure(int i, int j, String s)
        throws RemoteException;

    public abstract void onRangingSuccess(int i, android.net.wifi.RttManager.ParcelableRttResults parcelablerttresults)
        throws RemoteException;
}
