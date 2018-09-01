// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.dreams;

import android.os.*;

public interface IDreamService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDreamService
    {

        public static IDreamService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.dreams.IDreamService");
            if(iinterface != null && (iinterface instanceof IDreamService))
                return (IDreamService)iinterface;
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
                parcel1.writeString("android.service.dreams.IDreamService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.dreams.IDreamService");
                parcel1 = parcel.readStrongBinder();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                attach(parcel1, flag, android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.dreams.IDreamService");
                detach();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.dreams.IDreamService");
                wakeUp();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.dreams.IDreamService";
        static final int TRANSACTION_attach = 1;
        static final int TRANSACTION_detach = 2;
        static final int TRANSACTION_wakeUp = 3;

        public Stub()
        {
            attachInterface(this, "android.service.dreams.IDreamService");
        }
    }

    private static class Stub.Proxy
        implements IDreamService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void attach(IBinder ibinder, boolean flag, IRemoteCallback iremotecallback)
            throws RemoteException
        {
            Object obj;
            int i;
            Parcel parcel;
            obj = null;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamService");
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            ibinder = obj;
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_49;
            ibinder = iremotecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void detach()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamService");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.dreams.IDreamService";
        }

        public void wakeUp()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.dreams.IDreamService");
            mRemote.transact(3, parcel, null, 1);
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


    public abstract void attach(IBinder ibinder, boolean flag, IRemoteCallback iremotecallback)
        throws RemoteException;

    public abstract void detach()
        throws RemoteException;

    public abstract void wakeUp()
        throws RemoteException;
}
