// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.net.Uri;
import android.os.*;

public interface IConditionProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IConditionProvider
    {

        public static IConditionProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.notification.IConditionProvider");
            if(iinterface != null && (iinterface instanceof IConditionProvider))
                return (IConditionProvider)iinterface;
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
                parcel1.writeString("android.service.notification.IConditionProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.notification.IConditionProvider");
                onConnected();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.notification.IConditionProvider");
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onSubscribe(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.notification.IConditionProvider");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onUnsubscribe(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.notification.IConditionProvider";
        static final int TRANSACTION_onConnected = 1;
        static final int TRANSACTION_onSubscribe = 2;
        static final int TRANSACTION_onUnsubscribe = 3;

        public Stub()
        {
            attachInterface(this, "android.service.notification.IConditionProvider");
        }
    }

    private static class Stub.Proxy
        implements IConditionProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.notification.IConditionProvider";
        }

        public void onConnected()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.IConditionProvider");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSubscribe(Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.IConditionProvider");
            if(uri == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void onUnsubscribe(Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.IConditionProvider");
            if(uri == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onConnected()
        throws RemoteException;

    public abstract void onSubscribe(Uri uri)
        throws RemoteException;

    public abstract void onUnsubscribe(Uri uri)
        throws RemoteException;
}
