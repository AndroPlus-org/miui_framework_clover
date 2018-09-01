// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.Intent;
import android.os.*;

public interface IPackageInstallObserver2
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageInstallObserver2
    {

        public static IPackageInstallObserver2 asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageInstallObserver2");
            if(iinterface != null && (iinterface instanceof IPackageInstallObserver2))
                return (IPackageInstallObserver2)iinterface;
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
            String s;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.content.pm.IPackageInstallObserver2");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageInstallObserver2");
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onUserActionRequired(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IPackageInstallObserver2");
                parcel1 = parcel.readString();
                i = parcel.readInt();
                s = parcel.readString();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onPackageInstalled(parcel1, i, s, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver2";
        static final int TRANSACTION_onPackageInstalled = 2;
        static final int TRANSACTION_onUserActionRequired = 1;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageInstallObserver2");
        }
    }

    private static class Stub.Proxy
        implements IPackageInstallObserver2
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageInstallObserver2";
        }

        public void onPackageInstalled(String s, int i, String s1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver2");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onUserActionRequired(Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver2");
            if(intent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel.recycle();
            throw intent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onPackageInstalled(String s, int i, String s1, Bundle bundle)
        throws RemoteException;

    public abstract void onUserActionRequired(Intent intent)
        throws RemoteException;
}
