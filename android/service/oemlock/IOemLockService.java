// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.oemlock;

import android.os.*;

public interface IOemLockService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOemLockService
    {

        public static IOemLockService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.oemlock.IOemLockService");
            if(iinterface != null && (iinterface instanceof IOemLockService))
                return (IOemLockService)iinterface;
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
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag9;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.oemlock.IOemLockService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.oemlock.IOemLockService");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setOemUnlockAllowedByCarrier(flag4, parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.oemlock.IOemLockService");
                boolean flag5 = isOemUnlockAllowedByCarrier();
                parcel1.writeNoException();
                i = ((flag3) ? 1 : 0);
                if(flag5)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.oemlock.IOemLockService");
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                setOemUnlockAllowedByUser(flag6);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.oemlock.IOemLockService");
                boolean flag7 = isOemUnlockAllowedByUser();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag7)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.oemlock.IOemLockService");
                boolean flag8 = isOemUnlockAllowed();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag8)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.oemlock.IOemLockService");
                flag9 = isDeviceOemUnlocked();
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                break;
            }
            if(flag9)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.oemlock.IOemLockService";
        static final int TRANSACTION_isDeviceOemUnlocked = 6;
        static final int TRANSACTION_isOemUnlockAllowed = 5;
        static final int TRANSACTION_isOemUnlockAllowedByCarrier = 2;
        static final int TRANSACTION_isOemUnlockAllowedByUser = 4;
        static final int TRANSACTION_setOemUnlockAllowedByCarrier = 1;
        static final int TRANSACTION_setOemUnlockAllowedByUser = 3;

        public Stub()
        {
            attachInterface(this, "android.service.oemlock.IOemLockService");
        }
    }

    private static class Stub.Proxy
        implements IOemLockService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.oemlock.IOemLockService";
        }

        public boolean isDeviceOemUnlocked()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.oemlock.IOemLockService");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isOemUnlockAllowed()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.oemlock.IOemLockService");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isOemUnlockAllowedByCarrier()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.oemlock.IOemLockService");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isOemUnlockAllowedByUser()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.oemlock.IOemLockService");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOemUnlockAllowedByCarrier(boolean flag, byte abyte0[])
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.oemlock.IOemLockService");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void setOemUnlockAllowedByUser(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.oemlock.IOemLockService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean isDeviceOemUnlocked()
        throws RemoteException;

    public abstract boolean isOemUnlockAllowed()
        throws RemoteException;

    public abstract boolean isOemUnlockAllowedByCarrier()
        throws RemoteException;

    public abstract boolean isOemUnlockAllowedByUser()
        throws RemoteException;

    public abstract void setOemUnlockAllowedByCarrier(boolean flag, byte abyte0[])
        throws RemoteException;

    public abstract void setOemUnlockAllowedByUser(boolean flag)
        throws RemoteException;
}
