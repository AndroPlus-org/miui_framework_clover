// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.*;

public interface IStreamingServiceCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStreamingServiceCallback
    {

        public static IStreamingServiceCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.telephony.mbms.IStreamingServiceCallback");
            if(iinterface != null && (iinterface instanceof IStreamingServiceCallback))
                return (IStreamingServiceCallback)iinterface;
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
                parcel1.writeString("android.telephony.mbms.IStreamingServiceCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.telephony.mbms.IStreamingServiceCallback");
                onError(parcel.readInt(), parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.telephony.mbms.IStreamingServiceCallback");
                onStreamStateUpdated(parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.telephony.mbms.IStreamingServiceCallback");
                onMediaDescriptionUpdated();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.telephony.mbms.IStreamingServiceCallback");
                onBroadcastSignalStrengthUpdated(parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.telephony.mbms.IStreamingServiceCallback");
                onStreamMethodUpdated(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.telephony.mbms.IStreamingServiceCallback";
        static final int TRANSACTION_onBroadcastSignalStrengthUpdated = 4;
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onMediaDescriptionUpdated = 3;
        static final int TRANSACTION_onStreamMethodUpdated = 5;
        static final int TRANSACTION_onStreamStateUpdated = 2;

        public Stub()
        {
            attachInterface(this, "android.telephony.mbms.IStreamingServiceCallback");
        }
    }

    private static class Stub.Proxy
        implements IStreamingServiceCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.telephony.mbms.IStreamingServiceCallback";
        }

        public void onBroadcastSignalStrengthUpdated(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IStreamingServiceCallback");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onError(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IStreamingServiceCallback");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onMediaDescriptionUpdated()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IStreamingServiceCallback");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStreamMethodUpdated(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IStreamingServiceCallback");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStreamStateUpdated(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IStreamingServiceCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
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


    public abstract void onBroadcastSignalStrengthUpdated(int i)
        throws RemoteException;

    public abstract void onError(int i, String s)
        throws RemoteException;

    public abstract void onMediaDescriptionUpdated()
        throws RemoteException;

    public abstract void onStreamMethodUpdated(int i)
        throws RemoteException;

    public abstract void onStreamStateUpdated(int i, int j)
        throws RemoteException;
}
