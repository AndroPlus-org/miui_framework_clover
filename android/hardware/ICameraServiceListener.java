// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.*;

public interface ICameraServiceListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICameraServiceListener
    {

        public static ICameraServiceListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.ICameraServiceListener");
            if(iinterface != null && (iinterface instanceof ICameraServiceListener))
                return (ICameraServiceListener)iinterface;
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
                parcel1.writeString("android.hardware.ICameraServiceListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.ICameraServiceListener");
                onStatusChanged(parcel.readInt(), parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.ICameraServiceListener");
                onTorchStatusChanged(parcel.readInt(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.ICameraServiceListener";
        static final int TRANSACTION_onStatusChanged = 1;
        static final int TRANSACTION_onTorchStatusChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.hardware.ICameraServiceListener");
        }
    }

    private static class Stub.Proxy
        implements ICameraServiceListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.ICameraServiceListener";
        }

        public void onStatusChanged(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onTorchStatusChanged(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onStatusChanged(int i, String s)
        throws RemoteException;

    public abstract void onTorchStatusChanged(int i, String s)
        throws RemoteException;

    public static final int STATUS_ENUMERATING = 2;
    public static final int STATUS_NOT_AVAILABLE = -2;
    public static final int STATUS_NOT_PRESENT = 0;
    public static final int STATUS_PRESENT = 1;
    public static final int STATUS_UNKNOWN = -1;
    public static final int TORCH_STATUS_AVAILABLE_OFF = 1;
    public static final int TORCH_STATUS_AVAILABLE_ON = 2;
    public static final int TORCH_STATUS_NOT_AVAILABLE = 0;
    public static final int TORCH_STATUS_UNKNOWN = -1;
}
