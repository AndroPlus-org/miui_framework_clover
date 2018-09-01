// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.os.*;

public interface ISecurityCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISecurityCallback
    {

        public static ISecurityCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.security.ISecurityCallback");
            if(iinterface != null && (iinterface instanceof ISecurityCallback))
                return (ISecurityCallback)iinterface;
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
                parcel1.writeString("miui.security.ISecurityCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.security.ISecurityCallback");
                boolean flag = checkPreInstallNeeded(parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.security.ISecurityCallback");
                preInstallApps();
                return true;
            }
        }

        private static final String DESCRIPTOR = "miui.security.ISecurityCallback";
        static final int TRANSACTION_checkPreInstallNeeded = 1;
        static final int TRANSACTION_preInstallApps = 2;

        public Stub()
        {
            attachInterface(this, "miui.security.ISecurityCallback");
        }
    }

    private static class Stub.Proxy
        implements ISecurityCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean checkPreInstallNeeded(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityCallback");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.security.ISecurityCallback";
        }

        public void preInstallApps()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityCallback");
            mRemote.transact(2, parcel, null, 1);
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


    public abstract boolean checkPreInstallNeeded(String s)
        throws RemoteException;

    public abstract void preInstallApps()
        throws RemoteException;
}
