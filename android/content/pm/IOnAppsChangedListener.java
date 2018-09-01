// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

// Referenced classes of package android.content.pm:
//            ParceledListSlice

public interface IOnAppsChangedListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOnAppsChangedListener
    {

        public static IOnAppsChangedListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IOnAppsChangedListener");
            if(iinterface != null && (iinterface instanceof IOnAppsChangedListener))
                return (IOnAppsChangedListener)iinterface;
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
                parcel1.writeString("android.content.pm.IOnAppsChangedListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IOnAppsChangedListener");
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onPackageRemoved(parcel1, parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IOnAppsChangedListener");
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onPackageAdded(parcel1, parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.pm.IOnAppsChangedListener");
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onPackageChanged(parcel1, parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.content.pm.IOnAppsChangedListener");
                String as[];
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                as = parcel.createStringArray();
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onPackagesAvailable(parcel1, as, flag);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.pm.IOnAppsChangedListener");
                String as1[];
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                as1 = parcel.createStringArray();
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onPackagesUnavailable(parcel1, as1, flag1);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.pm.IOnAppsChangedListener");
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onPackagesSuspended(parcel1, parcel.createStringArray());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.content.pm.IOnAppsChangedListener");
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onPackagesUnsuspended(parcel1, parcel.createStringArray());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.content.pm.IOnAppsChangedListener");
                break;
            }
            String s;
            if(parcel.readInt() != 0)
                parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            s = parcel.readString();
            if(parcel.readInt() != 0)
                parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onShortcutChanged(parcel1, s, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.content.pm.IOnAppsChangedListener";
        static final int TRANSACTION_onPackageAdded = 2;
        static final int TRANSACTION_onPackageChanged = 3;
        static final int TRANSACTION_onPackageRemoved = 1;
        static final int TRANSACTION_onPackagesAvailable = 4;
        static final int TRANSACTION_onPackagesSuspended = 6;
        static final int TRANSACTION_onPackagesUnavailable = 5;
        static final int TRANSACTION_onPackagesUnsuspended = 7;
        static final int TRANSACTION_onShortcutChanged = 8;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IOnAppsChangedListener");
        }
    }

    private static class Stub.Proxy
        implements IOnAppsChangedListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IOnAppsChangedListener";
        }

        public void onPackageAdded(UserHandle userhandle, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            userhandle;
            parcel.recycle();
            throw userhandle;
        }

        public void onPackageChanged(UserHandle userhandle, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            userhandle;
            parcel.recycle();
            throw userhandle;
        }

        public void onPackageRemoved(UserHandle userhandle, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            userhandle;
            parcel.recycle();
            throw userhandle;
        }

        public void onPackagesAvailable(UserHandle userhandle, String as[], boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            userhandle;
            parcel.recycle();
            throw userhandle;
        }

        public void onPackagesSuspended(UserHandle userhandle, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            userhandle;
            parcel.recycle();
            throw userhandle;
        }

        public void onPackagesUnavailable(UserHandle userhandle, String as[], boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            userhandle;
            parcel.recycle();
            throw userhandle;
        }

        public void onPackagesUnsuspended(UserHandle userhandle, String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            userhandle;
            parcel.recycle();
            throw userhandle;
        }

        public void onShortcutChanged(UserHandle userhandle, String s, ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            userhandle;
            parcel.recycle();
            throw userhandle;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onPackageAdded(UserHandle userhandle, String s)
        throws RemoteException;

    public abstract void onPackageChanged(UserHandle userhandle, String s)
        throws RemoteException;

    public abstract void onPackageRemoved(UserHandle userhandle, String s)
        throws RemoteException;

    public abstract void onPackagesAvailable(UserHandle userhandle, String as[], boolean flag)
        throws RemoteException;

    public abstract void onPackagesSuspended(UserHandle userhandle, String as[])
        throws RemoteException;

    public abstract void onPackagesUnavailable(UserHandle userhandle, String as[], boolean flag)
        throws RemoteException;

    public abstract void onPackagesUnsuspended(UserHandle userhandle, String as[])
        throws RemoteException;

    public abstract void onShortcutChanged(UserHandle userhandle, String s, ParceledListSlice parceledlistslice)
        throws RemoteException;
}
