// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface IInstantAppResolver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInstantAppResolver
    {

        public static IInstantAppResolver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IInstantAppResolver");
            if(iinterface != null && (iinterface instanceof IInstantAppResolver))
                return (IInstantAppResolver)iinterface;
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
                parcel1.writeString("android.app.IInstantAppResolver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IInstantAppResolver");
                getInstantAppResolveInfoList(parcel.createIntArray(), parcel.readString(), parcel.readInt(), android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IInstantAppResolver");
                getInstantAppIntentFilterList(parcel.createIntArray(), parcel.readString(), parcel.readString(), android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IInstantAppResolver";
        static final int TRANSACTION_getInstantAppIntentFilterList = 2;
        static final int TRANSACTION_getInstantAppResolveInfoList = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IInstantAppResolver");
        }
    }

    private static class Stub.Proxy
        implements IInstantAppResolver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void getInstantAppIntentFilterList(int ai[], String s, String s1, IRemoteCallback iremotecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IInstantAppResolver");
            parcel.writeIntArray(ai);
            parcel.writeString(s);
            parcel.writeString(s1);
            ai = obj;
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_49;
            ai = iremotecallback.asBinder();
            parcel.writeStrongBinder(ai);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            ai;
            parcel.recycle();
            throw ai;
        }

        public void getInstantAppResolveInfoList(int ai[], String s, int i, IRemoteCallback iremotecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IInstantAppResolver");
            parcel.writeIntArray(ai);
            parcel.writeString(s);
            parcel.writeInt(i);
            ai = obj;
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_49;
            ai = iremotecallback.asBinder();
            parcel.writeStrongBinder(ai);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ai;
            parcel.recycle();
            throw ai;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IInstantAppResolver";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void getInstantAppIntentFilterList(int ai[], String s, String s1, IRemoteCallback iremotecallback)
        throws RemoteException;

    public abstract void getInstantAppResolveInfoList(int ai[], String s, int i, IRemoteCallback iremotecallback)
        throws RemoteException;
}
