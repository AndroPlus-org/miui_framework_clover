// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.IntentSender;
import android.os.*;

public interface IPackageInstallerSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageInstallerSession
    {

        public static IPackageInstallerSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageInstallerSession");
            if(iinterface != null && (iinterface instanceof IPackageInstallerSession))
                return (IPackageInstallerSession)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageInstallerSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                setClientProgress(parcel.readFloat());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                addClientProgress(parcel.readFloat());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                parcel = getNames();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                parcel = openWrite(parcel.readString(), parcel.readLong(), parcel.readLong());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                parcel = openRead(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                removeSplit(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                close();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                IntentSender intentsender;
                boolean flag;
                if(parcel.readInt() != 0)
                    intentsender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel);
                else
                    intentsender = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                commit(intentsender, flag);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                transfer(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.content.pm.IPackageInstallerSession");
                abandon();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageInstallerSession";
        static final int TRANSACTION_abandon = 10;
        static final int TRANSACTION_addClientProgress = 2;
        static final int TRANSACTION_close = 7;
        static final int TRANSACTION_commit = 8;
        static final int TRANSACTION_getNames = 3;
        static final int TRANSACTION_openRead = 5;
        static final int TRANSACTION_openWrite = 4;
        static final int TRANSACTION_removeSplit = 6;
        static final int TRANSACTION_setClientProgress = 1;
        static final int TRANSACTION_transfer = 9;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageInstallerSession");
        }
    }

    private static class Stub.Proxy
        implements IPackageInstallerSession
    {

        public void abandon()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public void addClientProgress(float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            parcel.writeFloat(f);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void close()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void commit(IntentSender intentsender, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            if(intentsender == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            intentsender.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intentsender;
            parcel1.recycle();
            parcel.recycle();
            throw intentsender;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageInstallerSession";
        }

        public String[] getNames()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParcelFileDescriptor openRead(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParcelFileDescriptor openWrite(String s, long l, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeSplit(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setClientProgress(float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            parcel.writeFloat(f);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void transfer(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
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


    public abstract void abandon()
        throws RemoteException;

    public abstract void addClientProgress(float f)
        throws RemoteException;

    public abstract void close()
        throws RemoteException;

    public abstract void commit(IntentSender intentsender, boolean flag)
        throws RemoteException;

    public abstract String[] getNames()
        throws RemoteException;

    public abstract ParcelFileDescriptor openRead(String s)
        throws RemoteException;

    public abstract ParcelFileDescriptor openWrite(String s, long l, long l1)
        throws RemoteException;

    public abstract void removeSplit(String s)
        throws RemoteException;

    public abstract void setClientProgress(float f)
        throws RemoteException;

    public abstract void transfer(String s)
        throws RemoteException;
}
