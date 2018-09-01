// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.xiaomi.joyose;

import android.os.*;

public interface IGameEngineCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGameEngineCallback
    {

        public static IGameEngineCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.xiaomi.joyose.IGameEngineCallback");
            if(iinterface != null && (iinterface instanceof IGameEngineCallback))
                return (IGameEngineCallback)iinterface;
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
                parcel1.writeString("com.xiaomi.joyose.IGameEngineCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.xiaomi.joyose.IGameEngineCallback");
                onUpdateGameInfo(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.xiaomi.joyose.IGameEngineCallback");
                onApplyHardwareResource(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.xiaomi.joyose.IGameEngineCallback");
                onRestoreDefaultConfig();
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.xiaomi.joyose.IGameEngineCallback");
                onEventHandle(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.xiaomi.joyose.IGameEngineCallback";
        static final int TRANSACTION_onApplyHardwareResource = 2;
        static final int TRANSACTION_onEventHandle = 4;
        static final int TRANSACTION_onRestoreDefaultConfig = 3;
        static final int TRANSACTION_onUpdateGameInfo = 1;

        public Stub()
        {
            attachInterface(this, "com.xiaomi.joyose.IGameEngineCallback");
        }
    }

    private static class Stub.Proxy
        implements IGameEngineCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.xiaomi.joyose.IGameEngineCallback";
        }

        public void onApplyHardwareResource(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.xiaomi.joyose.IGameEngineCallback");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void onEventHandle(int i, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.xiaomi.joyose.IGameEngineCallback");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void onRestoreDefaultConfig()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.xiaomi.joyose.IGameEngineCallback");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onUpdateGameInfo(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.xiaomi.joyose.IGameEngineCallback");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onApplyHardwareResource(String s)
        throws RemoteException;

    public abstract void onEventHandle(int i, String s, String s1)
        throws RemoteException;

    public abstract void onRestoreDefaultConfig()
        throws RemoteException;

    public abstract void onUpdateGameInfo(String s)
        throws RemoteException;
}
