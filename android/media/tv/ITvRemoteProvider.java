// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.*;

// Referenced classes of package android.media.tv:
//            ITvRemoteServiceInput

public interface ITvRemoteProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvRemoteProvider
    {

        public static ITvRemoteProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvRemoteProvider");
            if(iinterface != null && (iinterface instanceof ITvRemoteProvider))
                return (ITvRemoteProvider)iinterface;
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
                parcel1.writeString("android.media.tv.ITvRemoteProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvRemoteProvider");
                setRemoteServiceInputSink(ITvRemoteServiceInput.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvRemoteProvider");
                onInputBridgeConnected(parcel.readStrongBinder());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvRemoteProvider";
        static final int TRANSACTION_onInputBridgeConnected = 2;
        static final int TRANSACTION_setRemoteServiceInputSink = 1;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvRemoteProvider");
        }
    }

    private static class Stub.Proxy
        implements ITvRemoteProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvRemoteProvider";
        }

        public void onInputBridgeConnected(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteProvider");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void setRemoteServiceInputSink(ITvRemoteServiceInput itvremoteserviceinput)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteProvider");
            if(itvremoteserviceinput == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = itvremoteserviceinput.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            itvremoteserviceinput;
            parcel.recycle();
            throw itvremoteserviceinput;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onInputBridgeConnected(IBinder ibinder)
        throws RemoteException;

    public abstract void setRemoteServiceInputSink(ITvRemoteServiceInput itvremoteserviceinput)
        throws RemoteException;
}
