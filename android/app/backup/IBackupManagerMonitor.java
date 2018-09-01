// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.*;

public interface IBackupManagerMonitor
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBackupManagerMonitor
    {

        public static IBackupManagerMonitor asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.backup.IBackupManagerMonitor");
            if(iinterface != null && (iinterface instanceof IBackupManagerMonitor))
                return (IBackupManagerMonitor)iinterface;
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
                parcel1.writeString("android.app.backup.IBackupManagerMonitor");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.backup.IBackupManagerMonitor");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onEvent(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.app.backup.IBackupManagerMonitor";
        static final int TRANSACTION_onEvent = 1;

        public Stub()
        {
            attachInterface(this, "android.app.backup.IBackupManagerMonitor");
        }
    }

    private static class Stub.Proxy
        implements IBackupManagerMonitor
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.backup.IBackupManagerMonitor";
        }

        public void onEvent(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.backup.IBackupManagerMonitor");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onEvent(Bundle bundle)
        throws RemoteException;
}
