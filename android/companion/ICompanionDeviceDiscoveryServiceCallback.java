// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.os.*;

public interface ICompanionDeviceDiscoveryServiceCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICompanionDeviceDiscoveryServiceCallback
    {

        public static ICompanionDeviceDiscoveryServiceCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.companion.ICompanionDeviceDiscoveryServiceCallback");
            if(iinterface != null && (iinterface instanceof ICompanionDeviceDiscoveryServiceCallback))
                return (ICompanionDeviceDiscoveryServiceCallback)iinterface;
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
                parcel1.writeString("android.companion.ICompanionDeviceDiscoveryServiceCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.companion.ICompanionDeviceDiscoveryServiceCallback");
                onDeviceSelected(parcel.readString(), parcel.readInt(), parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.companion.ICompanionDeviceDiscoveryServiceCallback");
                onDeviceSelectionCancel();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.companion.ICompanionDeviceDiscoveryServiceCallback";
        static final int TRANSACTION_onDeviceSelected = 1;
        static final int TRANSACTION_onDeviceSelectionCancel = 2;

        public Stub()
        {
            attachInterface(this, "android.companion.ICompanionDeviceDiscoveryServiceCallback");
        }
    }

    private static class Stub.Proxy
        implements ICompanionDeviceDiscoveryServiceCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.companion.ICompanionDeviceDiscoveryServiceCallback";
        }

        public void onDeviceSelected(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceDiscoveryServiceCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onDeviceSelectionCancel()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceDiscoveryServiceCallback");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onDeviceSelected(String s, int i, String s1)
        throws RemoteException;

    public abstract void onDeviceSelectionCancel()
        throws RemoteException;
}
