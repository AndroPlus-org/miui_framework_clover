// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.*;

public interface ILowpanEnergyScanCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILowpanEnergyScanCallback
    {

        public static ILowpanEnergyScanCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.lowpan.ILowpanEnergyScanCallback");
            if(iinterface != null && (iinterface instanceof ILowpanEnergyScanCallback))
                return (ILowpanEnergyScanCallback)iinterface;
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
                parcel1.writeString("android.net.lowpan.ILowpanEnergyScanCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.lowpan.ILowpanEnergyScanCallback");
                onEnergyScanResult(parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.lowpan.ILowpanEnergyScanCallback");
                onEnergyScanFinished();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanEnergyScanCallback";
        static final int TRANSACTION_onEnergyScanFinished = 2;
        static final int TRANSACTION_onEnergyScanResult = 1;

        public Stub()
        {
            attachInterface(this, "android.net.lowpan.ILowpanEnergyScanCallback");
        }
    }

    private static class Stub.Proxy
        implements ILowpanEnergyScanCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.lowpan.ILowpanEnergyScanCallback";
        }

        public void onEnergyScanFinished()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanEnergyScanCallback");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onEnergyScanResult(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanEnergyScanCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, null, 1);
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


    public abstract void onEnergyScanFinished()
        throws RemoteException;

    public abstract void onEnergyScanResult(int i, int j)
        throws RemoteException;
}
