// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.net;

import android.os.*;

public interface IProxyService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IProxyService
    {

        public static IProxyService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.net.IProxyService");
            if(iinterface != null && (iinterface instanceof IProxyService))
                return (IProxyService)iinterface;
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
                parcel1.writeString("com.android.net.IProxyService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.net.IProxyService");
                parcel = resolvePacFile(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.net.IProxyService");
                setPacFile(parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.net.IProxyService");
                startPacSystem();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.net.IProxyService");
                stopPacSystem();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.net.IProxyService";
        static final int TRANSACTION_resolvePacFile = 1;
        static final int TRANSACTION_setPacFile = 2;
        static final int TRANSACTION_startPacSystem = 3;
        static final int TRANSACTION_stopPacSystem = 4;

        public Stub()
        {
            attachInterface(this, "com.android.net.IProxyService");
        }
    }

    private static class Stub.Proxy
        implements IProxyService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.net.IProxyService";
        }

        public String resolvePacFile(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.net.IProxyService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setPacFile(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.net.IProxyService");
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void startPacSystem()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.net.IProxyService");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void stopPacSystem()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.net.IProxyService");
            mRemote.transact(4, parcel, null, 1);
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


    public abstract String resolvePacFile(String s, String s1)
        throws RemoteException;

    public abstract void setPacFile(String s)
        throws RemoteException;

    public abstract void startPacSystem()
        throws RemoteException;

    public abstract void stopPacSystem()
        throws RemoteException;
}
