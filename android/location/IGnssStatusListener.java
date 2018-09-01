// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

public interface IGnssStatusListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGnssStatusListener
    {

        public static IGnssStatusListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IGnssStatusListener");
            if(iinterface != null && (iinterface instanceof IGnssStatusListener))
                return (IGnssStatusListener)iinterface;
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
                parcel1.writeString("android.location.IGnssStatusListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IGnssStatusListener");
                onGnssStarted();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.IGnssStatusListener");
                onGnssStopped();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.location.IGnssStatusListener");
                onFirstFix(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.location.IGnssStatusListener");
                onSvStatusChanged(parcel.readInt(), parcel.createIntArray(), parcel.createFloatArray(), parcel.createFloatArray(), parcel.createFloatArray(), parcel.createFloatArray());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.location.IGnssStatusListener");
                onNmeaReceived(parcel.readLong(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.IGnssStatusListener";
        static final int TRANSACTION_onFirstFix = 3;
        static final int TRANSACTION_onGnssStarted = 1;
        static final int TRANSACTION_onGnssStopped = 2;
        static final int TRANSACTION_onNmeaReceived = 5;
        static final int TRANSACTION_onSvStatusChanged = 4;

        public Stub()
        {
            attachInterface(this, "android.location.IGnssStatusListener");
        }
    }

    private static class Stub.Proxy
        implements IGnssStatusListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.IGnssStatusListener";
        }

        public void onFirstFix(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssStatusListener");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGnssStarted()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssStatusListener");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGnssStopped()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssStatusListener");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onNmeaReceived(long l, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssStatusListener");
            parcel.writeLong(l);
            parcel.writeString(s);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onSvStatusChanged(int i, int ai[], float af[], float af1[], float af2[], float af3[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGnssStatusListener");
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            parcel.writeFloatArray(af);
            parcel.writeFloatArray(af1);
            parcel.writeFloatArray(af2);
            parcel.writeFloatArray(af3);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            ai;
            parcel.recycle();
            throw ai;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onFirstFix(int i)
        throws RemoteException;

    public abstract void onGnssStarted()
        throws RemoteException;

    public abstract void onGnssStopped()
        throws RemoteException;

    public abstract void onNmeaReceived(long l, String s)
        throws RemoteException;

    public abstract void onSvStatusChanged(int i, int ai[], float af[], float af1[], float af2[], float af3[])
        throws RemoteException;
}
