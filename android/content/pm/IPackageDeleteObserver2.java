// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.Intent;
import android.os.*;

public interface IPackageDeleteObserver2
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageDeleteObserver2
    {

        public static IPackageDeleteObserver2 asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageDeleteObserver2");
            if(iinterface != null && (iinterface instanceof IPackageDeleteObserver2))
                return (IPackageDeleteObserver2)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageDeleteObserver2");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageDeleteObserver2");
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onUserActionRequired(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IPackageDeleteObserver2");
                onPackageDeleted(parcel.readString(), parcel.readInt(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageDeleteObserver2";
        static final int TRANSACTION_onPackageDeleted = 2;
        static final int TRANSACTION_onUserActionRequired = 1;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageDeleteObserver2");
        }
    }

    private static class Stub.Proxy
        implements IPackageDeleteObserver2
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageDeleteObserver2";
        }

        public void onPackageDeleted(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver2");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onUserActionRequired(Intent intent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver2");
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


    public abstract void onPackageDeleted(String s, int i, String s1)
        throws RemoteException;

    public abstract void onUserActionRequired(Intent intent)
        throws RemoteException;
}
