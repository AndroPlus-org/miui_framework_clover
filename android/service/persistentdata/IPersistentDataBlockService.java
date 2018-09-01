// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.persistentdata;

import android.os.*;

public interface IPersistentDataBlockService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPersistentDataBlockService
    {

        public static IPersistentDataBlockService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.persistentdata.IPersistentDataBlockService");
            if(iinterface != null && (iinterface instanceof IPersistentDataBlockService))
                return (IPersistentDataBlockService)iinterface;
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
            boolean flag4;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.persistentdata.IPersistentDataBlockService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                i = write(parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                parcel = read();
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                wipe();
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                i = getDataBlockSize();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                long l = getMaximumDataBlockSize();
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                setOemUnlockEnabled(flag2);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                boolean flag3 = getOemUnlockEnabled();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                i = getFlashLockState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.service.persistentdata.IPersistentDataBlockService");
                flag4 = hasFrpCredentialHandle();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                break;
            }
            if(flag4)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.persistentdata.IPersistentDataBlockService";
        static final int TRANSACTION_getDataBlockSize = 4;
        static final int TRANSACTION_getFlashLockState = 8;
        static final int TRANSACTION_getMaximumDataBlockSize = 5;
        static final int TRANSACTION_getOemUnlockEnabled = 7;
        static final int TRANSACTION_hasFrpCredentialHandle = 9;
        static final int TRANSACTION_read = 2;
        static final int TRANSACTION_setOemUnlockEnabled = 6;
        static final int TRANSACTION_wipe = 3;
        static final int TRANSACTION_write = 1;

        public Stub()
        {
            attachInterface(this, "android.service.persistentdata.IPersistentDataBlockService");
        }
    }

    private static class Stub.Proxy
        implements IPersistentDataBlockService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int getDataBlockSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getFlashLockState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.persistentdata.IPersistentDataBlockService";
        }

        public long getMaximumDataBlockSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getOemUnlockEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public boolean hasFrpCredentialHandle()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public byte[] read()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOemUnlockEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public void wipe()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
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

        public int write(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.persistentdata.IPersistentDataBlockService");
            parcel.writeByteArray(abyte0);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int getDataBlockSize()
        throws RemoteException;

    public abstract int getFlashLockState()
        throws RemoteException;

    public abstract long getMaximumDataBlockSize()
        throws RemoteException;

    public abstract boolean getOemUnlockEnabled()
        throws RemoteException;

    public abstract boolean hasFrpCredentialHandle()
        throws RemoteException;

    public abstract byte[] read()
        throws RemoteException;

    public abstract void setOemUnlockEnabled(boolean flag)
        throws RemoteException;

    public abstract void wipe()
        throws RemoteException;

    public abstract int write(byte abyte0[])
        throws RemoteException;
}
