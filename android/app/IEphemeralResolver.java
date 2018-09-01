// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface IEphemeralResolver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IEphemeralResolver
    {

        public static IEphemeralResolver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IEphemeralResolver");
            if(iinterface != null && (iinterface instanceof IEphemeralResolver))
                return (IEphemeralResolver)iinterface;
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
                parcel1.writeString("android.app.IEphemeralResolver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IEphemeralResolver");
                getEphemeralResolveInfoList(android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IEphemeralResolver");
                getEphemeralIntentFilterList(android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IEphemeralResolver";
        static final int TRANSACTION_getEphemeralIntentFilterList = 2;
        static final int TRANSACTION_getEphemeralResolveInfoList = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IEphemeralResolver");
        }
    }

    private static class Stub.Proxy
        implements IEphemeralResolver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void getEphemeralIntentFilterList(IRemoteCallback iremotecallback, String s, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IEphemeralResolver");
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iremotecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            iremotecallback;
            parcel.recycle();
            throw iremotecallback;
        }

        public void getEphemeralResolveInfoList(IRemoteCallback iremotecallback, int ai[], int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IEphemeralResolver");
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iremotecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeIntArray(ai);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iremotecallback;
            parcel.recycle();
            throw iremotecallback;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IEphemeralResolver";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void getEphemeralIntentFilterList(IRemoteCallback iremotecallback, String s, int i)
        throws RemoteException;

    public abstract void getEphemeralResolveInfoList(IRemoteCallback iremotecallback, int ai[], int i)
        throws RemoteException;
}
