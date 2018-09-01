// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm.permission;

import android.os.*;

public interface IRuntimePermissionPresenter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRuntimePermissionPresenter
    {

        public static IRuntimePermissionPresenter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.permission.IRuntimePermissionPresenter");
            if(iinterface != null && (iinterface instanceof IRuntimePermissionPresenter))
                return (IRuntimePermissionPresenter)iinterface;
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
                parcel1.writeString("android.content.pm.permission.IRuntimePermissionPresenter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.permission.IRuntimePermissionPresenter");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                getAppPermissions(parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.permission.IRuntimePermissionPresenter");
                revokeRuntimePermission(parcel.readString(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.permission.IRuntimePermissionPresenter";
        static final int TRANSACTION_getAppPermissions = 1;
        static final int TRANSACTION_revokeRuntimePermission = 2;

        public Stub()
        {
            attachInterface(this, "android.content.pm.permission.IRuntimePermissionPresenter");
        }
    }

    private static class Stub.Proxy
        implements IRuntimePermissionPresenter
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void getAppPermissions(String s, RemoteCallback remotecallback)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.permission.IRuntimePermissionPresenter");
            parcel.writeString(s);
            if(remotecallback == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            remotecallback.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.permission.IRuntimePermissionPresenter";
        }

        public void revokeRuntimePermission(String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.permission.IRuntimePermissionPresenter");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void getAppPermissions(String s, RemoteCallback remotecallback)
        throws RemoteException;

    public abstract void revokeRuntimePermission(String s, String s1)
        throws RemoteException;
}
