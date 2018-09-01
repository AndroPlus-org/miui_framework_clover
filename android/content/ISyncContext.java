// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

// Referenced classes of package android.content:
//            SyncResult

public interface ISyncContext
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISyncContext
    {

        public static ISyncContext asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.ISyncContext");
            if(iinterface != null && (iinterface instanceof ISyncContext))
                return (ISyncContext)iinterface;
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
                parcel1.writeString("android.content.ISyncContext");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.ISyncContext");
                sendHeartbeat();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.ISyncContext");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (SyncResult)SyncResult.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onFinished(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.content.ISyncContext";
        static final int TRANSACTION_onFinished = 2;
        static final int TRANSACTION_sendHeartbeat = 1;

        public Stub()
        {
            attachInterface(this, "android.content.ISyncContext");
        }
    }

    private static class Stub.Proxy
        implements ISyncContext
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.ISyncContext";
        }

        public void onFinished(SyncResult syncresult)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.ISyncContext");
            if(syncresult == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            syncresult.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            syncresult;
            parcel1.recycle();
            parcel.recycle();
            throw syncresult;
        }

        public void sendHeartbeat()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.ISyncContext");
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


    public abstract void onFinished(SyncResult syncresult)
        throws RemoteException;

    public abstract void sendHeartbeat()
        throws RemoteException;
}
