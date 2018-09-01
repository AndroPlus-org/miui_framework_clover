// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.*;

// Referenced classes of package android.media.tv:
//            TvStreamConfig

public interface ITvInputHardwareCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputHardwareCallback
    {

        public static ITvInputHardwareCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputHardwareCallback");
            if(iinterface != null && (iinterface instanceof ITvInputHardwareCallback))
                return (ITvInputHardwareCallback)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputHardwareCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputHardwareCallback");
                onReleased();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputHardwareCallback");
                onStreamConfigChanged((TvStreamConfig[])parcel.createTypedArray(TvStreamConfig.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputHardwareCallback";
        static final int TRANSACTION_onReleased = 1;
        static final int TRANSACTION_onStreamConfigChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputHardwareCallback");
        }
    }

    private static class Stub.Proxy
        implements ITvInputHardwareCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputHardwareCallback";
        }

        public void onReleased()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputHardwareCallback");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStreamConfigChanged(TvStreamConfig atvstreamconfig[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputHardwareCallback");
            parcel.writeTypedArray(atvstreamconfig, 0);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            atvstreamconfig;
            parcel.recycle();
            throw atvstreamconfig;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onReleased()
        throws RemoteException;

    public abstract void onStreamConfigChanged(TvStreamConfig atvstreamconfig[])
        throws RemoteException;
}
