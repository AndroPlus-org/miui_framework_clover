// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.*;

public interface ICameraServiceProxy
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICameraServiceProxy
    {

        public static ICameraServiceProxy asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.ICameraServiceProxy");
            if(iinterface != null && (iinterface instanceof ICameraServiceProxy))
                return (ICameraServiceProxy)iinterface;
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
                parcel1.writeString("android.hardware.ICameraServiceProxy");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.ICameraServiceProxy");
                pingForUserUpdate();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.ICameraServiceProxy");
                notifyCameraState(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.ICameraServiceProxy";
        static final int TRANSACTION_notifyCameraState = 2;
        static final int TRANSACTION_pingForUserUpdate = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.ICameraServiceProxy");
        }
    }

    private static class Stub.Proxy
        implements ICameraServiceProxy
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.ICameraServiceProxy";
        }

        public void notifyCameraState(String s, int i, int j, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void pingForUserUpdate()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
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


    public abstract void notifyCameraState(String s, int i, int j, String s1)
        throws RemoteException;

    public abstract void pingForUserUpdate()
        throws RemoteException;

    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_EXTERNAL = 2;
    public static final int CAMERA_FACING_FRONT = 1;
    public static final int CAMERA_STATE_ACTIVE = 1;
    public static final int CAMERA_STATE_CLOSED = 3;
    public static final int CAMERA_STATE_IDLE = 2;
    public static final int CAMERA_STATE_OPEN = 0;
}
