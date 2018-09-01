// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public interface IPackageInstallerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageInstallerCallback
    {

        public static IPackageInstallerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageInstallerCallback");
            if(iinterface != null && (iinterface instanceof IPackageInstallerCallback))
                return (IPackageInstallerCallback)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageInstallerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageInstallerCallback");
                onSessionCreated(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IPackageInstallerCallback");
                onSessionBadgingChanged(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.pm.IPackageInstallerCallback");
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onSessionActiveChanged(i, flag);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.content.pm.IPackageInstallerCallback");
                onSessionProgressChanged(parcel.readInt(), parcel.readFloat());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.pm.IPackageInstallerCallback");
                i = parcel.readInt();
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            onSessionFinished(i, flag1);
            return true;
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageInstallerCallback";
        static final int TRANSACTION_onSessionActiveChanged = 3;
        static final int TRANSACTION_onSessionBadgingChanged = 2;
        static final int TRANSACTION_onSessionCreated = 1;
        static final int TRANSACTION_onSessionFinished = 5;
        static final int TRANSACTION_onSessionProgressChanged = 4;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageInstallerCallback");
        }
    }

    private static class Stub.Proxy
        implements IPackageInstallerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageInstallerCallback";
        }

        public void onSessionActiveChanged(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSessionBadgingChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSessionCreated(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSessionFinished(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSessionProgressChanged(int i, float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
            parcel.writeInt(i);
            parcel.writeFloat(f);
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


    public abstract void onSessionActiveChanged(int i, boolean flag)
        throws RemoteException;

    public abstract void onSessionBadgingChanged(int i)
        throws RemoteException;

    public abstract void onSessionCreated(int i)
        throws RemoteException;

    public abstract void onSessionFinished(int i, boolean flag)
        throws RemoteException;

    public abstract void onSessionProgressChanged(int i, float f)
        throws RemoteException;
}
