// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.os.*;

// Referenced classes of package android.service.notification:
//            StatusBarNotification

public interface IStatusBarNotificationHolder
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStatusBarNotificationHolder
    {

        public static IStatusBarNotificationHolder asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.notification.IStatusBarNotificationHolder");
            if(iinterface != null && (iinterface instanceof IStatusBarNotificationHolder))
                return (IStatusBarNotificationHolder)iinterface;
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
                parcel1.writeString("android.service.notification.IStatusBarNotificationHolder");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.notification.IStatusBarNotificationHolder");
                parcel = get();
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.service.notification.IStatusBarNotificationHolder";
        static final int TRANSACTION_get = 1;

        public Stub()
        {
            attachInterface(this, "android.service.notification.IStatusBarNotificationHolder");
        }
    }

    private static class Stub.Proxy
        implements IStatusBarNotificationHolder
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public StatusBarNotification get()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.IStatusBarNotificationHolder");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            StatusBarNotification statusbarnotification = (StatusBarNotification)StatusBarNotification.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return statusbarnotification;
_L2:
            statusbarnotification = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.notification.IStatusBarNotificationHolder";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract StatusBarNotification get()
        throws RemoteException;
}
