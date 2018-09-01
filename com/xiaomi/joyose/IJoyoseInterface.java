// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.xiaomi.joyose;

import android.os.*;

// Referenced classes of package com.xiaomi.joyose:
//            IGameEngineCallback

public interface IJoyoseInterface
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IJoyoseInterface
    {

        public static IJoyoseInterface asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.xiaomi.joyose.IJoyoseInterface");
            if(iinterface != null && (iinterface instanceof IJoyoseInterface))
                return (IJoyoseInterface)iinterface;
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
                parcel1.writeString("com.xiaomi.joyose.IJoyoseInterface");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.xiaomi.joyose.IJoyoseInterface");
                registerGameEngineListener(parcel.readInt(), IGameEngineCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.xiaomi.joyose.IJoyoseInterface");
                unRegisterGameEngineListener(IGameEngineCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.xiaomi.joyose.IJoyoseInterface");
                handleGameBoosterForOneway(parcel.readInt(), parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.xiaomi.joyose.IJoyoseInterface");
                parcel = handleGameBoosterForSync(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.xiaomi.joyose.IJoyoseInterface";
        static final int TRANSACTION_handleGameBoosterForOneway = 3;
        static final int TRANSACTION_handleGameBoosterForSync = 4;
        static final int TRANSACTION_registerGameEngineListener = 1;
        static final int TRANSACTION_unRegisterGameEngineListener = 2;

        public Stub()
        {
            attachInterface(this, "com.xiaomi.joyose.IJoyoseInterface");
        }
    }

    private static class Stub.Proxy
        implements IJoyoseInterface
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.xiaomi.joyose.IJoyoseInterface";
        }

        public void handleGameBoosterForOneway(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.xiaomi.joyose.IJoyoseInterface");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public String handleGameBoosterForSync(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.xiaomi.joyose.IJoyoseInterface");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public void registerGameEngineListener(int i, IGameEngineCallback igameenginecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.xiaomi.joyose.IJoyoseInterface");
            parcel.writeInt(i);
            if(igameenginecallback == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = igameenginecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            igameenginecallback;
            parcel.recycle();
            throw igameenginecallback;
        }

        public void unRegisterGameEngineListener(IGameEngineCallback igameenginecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.xiaomi.joyose.IJoyoseInterface");
            if(igameenginecallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = igameenginecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            igameenginecallback;
            parcel.recycle();
            throw igameenginecallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void handleGameBoosterForOneway(int i, String s)
        throws RemoteException;

    public abstract String handleGameBoosterForSync(int i, String s)
        throws RemoteException;

    public abstract void registerGameEngineListener(int i, IGameEngineCallback igameenginecallback)
        throws RemoteException;

    public abstract void unRegisterGameEngineListener(IGameEngineCallback igameenginecallback)
        throws RemoteException;
}
