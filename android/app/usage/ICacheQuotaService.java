// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.os.*;
import java.util.List;

// Referenced classes of package android.app.usage:
//            CacheQuotaHint

public interface ICacheQuotaService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICacheQuotaService
    {

        public static ICacheQuotaService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.usage.ICacheQuotaService");
            if(iinterface != null && (iinterface instanceof ICacheQuotaService))
                return (ICacheQuotaService)iinterface;
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
                parcel1.writeString("android.app.usage.ICacheQuotaService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.usage.ICacheQuotaService");
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            computeCacheQuotaHints(parcel1, parcel.createTypedArrayList(CacheQuotaHint.CREATOR));
            return true;
        }

        private static final String DESCRIPTOR = "android.app.usage.ICacheQuotaService";
        static final int TRANSACTION_computeCacheQuotaHints = 1;

        public Stub()
        {
            attachInterface(this, "android.app.usage.ICacheQuotaService");
        }
    }

    private static class Stub.Proxy
        implements ICacheQuotaService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void computeCacheQuotaHints(RemoteCallback remotecallback, List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.ICacheQuotaService");
            if(remotecallback == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            remotecallback.writeToParcel(parcel, 0);
_L1:
            parcel.writeTypedList(list);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            remotecallback;
            parcel.recycle();
            throw remotecallback;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.usage.ICacheQuotaService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void computeCacheQuotaHints(RemoteCallback remotecallback, List list)
        throws RemoteException;
}
