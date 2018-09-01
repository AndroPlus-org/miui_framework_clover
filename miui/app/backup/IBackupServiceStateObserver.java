// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app.backup;

import android.os.*;

public interface IBackupServiceStateObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBackupServiceStateObserver
    {

        public static IBackupServiceStateObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.app.backup.IBackupServiceStateObserver");
            if(iinterface != null && (iinterface instanceof IBackupServiceStateObserver))
                return (IBackupServiceStateObserver)iinterface;
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
                parcel1.writeString("miui.app.backup.IBackupServiceStateObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.app.backup.IBackupServiceStateObserver");
                onServiceStateIdle();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "miui.app.backup.IBackupServiceStateObserver";
        static final int TRANSACTION_onServiceStateIdle = 1;

        public Stub()
        {
            attachInterface(this, "miui.app.backup.IBackupServiceStateObserver");
        }
    }

    private static class Stub.Proxy
        implements IBackupServiceStateObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.app.backup.IBackupServiceStateObserver";
        }

        public void onServiceStateIdle()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.app.backup.IBackupServiceStateObserver");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onServiceStateIdle()
        throws RemoteException;
}
