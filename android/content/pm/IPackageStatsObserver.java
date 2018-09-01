// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

// Referenced classes of package android.content.pm:
//            PackageStats

public interface IPackageStatsObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageStatsObserver
    {

        public static IPackageStatsObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageStatsObserver");
            if(iinterface != null && (iinterface instanceof IPackageStatsObserver))
                return (IPackageStatsObserver)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageStatsObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageStatsObserver");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                parcel1 = (PackageStats)PackageStats.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onGetStatsCompleted(parcel1, flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageStatsObserver";
        static final int TRANSACTION_onGetStatsCompleted = 1;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageStatsObserver");
        }
    }

    private static class Stub.Proxy
        implements IPackageStatsObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageStatsObserver";
        }

        public void onGetStatsCompleted(PackageStats packagestats, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageStatsObserver");
            if(packagestats == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            packagestats.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            packagestats;
            parcel.recycle();
            throw packagestats;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onGetStatsCompleted(PackageStats packagestats, boolean flag)
        throws RemoteException;
}
