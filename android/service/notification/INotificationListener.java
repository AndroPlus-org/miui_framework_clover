// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.*;

// Referenced classes of package android.service.notification:
//            NotificationRankingUpdate, IStatusBarNotificationHolder

public interface INotificationListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INotificationListener
    {

        public static INotificationListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.notification.INotificationListener");
            if(iinterface != null && (iinterface instanceof INotificationListener))
                return (INotificationListener)iinterface;
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
                parcel1.writeString("android.service.notification.INotificationListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                if(parcel.readInt() != 0)
                    parcel = (NotificationRankingUpdate)NotificationRankingUpdate.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onListenerConnected(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                parcel1 = IStatusBarNotificationHolder.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (NotificationRankingUpdate)NotificationRankingUpdate.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onNotificationPosted(parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                IStatusBarNotificationHolder istatusbarnotificationholder = IStatusBarNotificationHolder.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel1 = (NotificationRankingUpdate)NotificationRankingUpdate.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onNotificationRemoved(istatusbarnotificationholder, parcel1, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                if(parcel.readInt() != 0)
                    parcel = (NotificationRankingUpdate)NotificationRankingUpdate.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onNotificationRankingUpdate(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                onListenerHintsChanged(parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                onInterruptionFilterChanged(parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                String s = parcel.readString();
                NotificationChannel notificationchannel;
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    notificationchannel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel);
                else
                    notificationchannel = null;
                onNotificationChannelModification(s, parcel1, notificationchannel, parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                String s1 = parcel.readString();
                NotificationChannelGroup notificationchannelgroup;
                if(parcel.readInt() != 0)
                    parcel1 = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    notificationchannelgroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel(parcel);
                else
                    notificationchannelgroup = null;
                onNotificationChannelGroupModification(s1, parcel1, notificationchannelgroup, parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                onNotificationEnqueued(IStatusBarNotificationHolder.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.service.notification.INotificationListener");
                onNotificationSnoozedUntilContext(IStatusBarNotificationHolder.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.notification.INotificationListener";
        static final int TRANSACTION_onInterruptionFilterChanged = 6;
        static final int TRANSACTION_onListenerConnected = 1;
        static final int TRANSACTION_onListenerHintsChanged = 5;
        static final int TRANSACTION_onNotificationChannelGroupModification = 8;
        static final int TRANSACTION_onNotificationChannelModification = 7;
        static final int TRANSACTION_onNotificationEnqueued = 9;
        static final int TRANSACTION_onNotificationPosted = 2;
        static final int TRANSACTION_onNotificationRankingUpdate = 4;
        static final int TRANSACTION_onNotificationRemoved = 3;
        static final int TRANSACTION_onNotificationSnoozedUntilContext = 10;

        public Stub()
        {
            attachInterface(this, "android.service.notification.INotificationListener");
        }
    }

    private static class Stub.Proxy
        implements INotificationListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.notification.INotificationListener";
        }

        public void onInterruptionFilterChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onListenerConnected(NotificationRankingUpdate notificationrankingupdate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            if(notificationrankingupdate == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            notificationrankingupdate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            notificationrankingupdate;
            parcel.recycle();
            throw notificationrankingupdate;
        }

        public void onListenerHintsChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onNotificationChannelGroupModification(String s, UserHandle userhandle, NotificationChannelGroup notificationchannelgroup, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            parcel.writeString(s);
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            if(notificationchannelgroup == null)
                break MISSING_BLOCK_LABEL_98;
            parcel.writeInt(1);
            notificationchannelgroup.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void onNotificationChannelModification(String s, UserHandle userhandle, NotificationChannel notificationchannel, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            parcel.writeString(s);
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            if(notificationchannel == null)
                break MISSING_BLOCK_LABEL_98;
            parcel.writeInt(1);
            notificationchannel.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void onNotificationEnqueued(IStatusBarNotificationHolder istatusbarnotificationholder)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            if(istatusbarnotificationholder == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = istatusbarnotificationholder.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            istatusbarnotificationholder;
            parcel.recycle();
            throw istatusbarnotificationholder;
        }

        public void onNotificationPosted(IStatusBarNotificationHolder istatusbarnotificationholder, NotificationRankingUpdate notificationrankingupdate)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            if(istatusbarnotificationholder == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = istatusbarnotificationholder.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(notificationrankingupdate == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            notificationrankingupdate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            istatusbarnotificationholder;
            parcel.recycle();
            throw istatusbarnotificationholder;
        }

        public void onNotificationRankingUpdate(NotificationRankingUpdate notificationrankingupdate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            if(notificationrankingupdate == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            notificationrankingupdate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            notificationrankingupdate;
            parcel.recycle();
            throw notificationrankingupdate;
        }

        public void onNotificationRemoved(IStatusBarNotificationHolder istatusbarnotificationholder, NotificationRankingUpdate notificationrankingupdate, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            if(istatusbarnotificationholder == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = istatusbarnotificationholder.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(notificationrankingupdate == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            notificationrankingupdate.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            istatusbarnotificationholder;
            parcel.recycle();
            throw istatusbarnotificationholder;
        }

        public void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder istatusbarnotificationholder, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.INotificationListener");
            if(istatusbarnotificationholder == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = istatusbarnotificationholder.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            istatusbarnotificationholder;
            parcel.recycle();
            throw istatusbarnotificationholder;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onInterruptionFilterChanged(int i)
        throws RemoteException;

    public abstract void onListenerConnected(NotificationRankingUpdate notificationrankingupdate)
        throws RemoteException;

    public abstract void onListenerHintsChanged(int i)
        throws RemoteException;

    public abstract void onNotificationChannelGroupModification(String s, UserHandle userhandle, NotificationChannelGroup notificationchannelgroup, int i)
        throws RemoteException;

    public abstract void onNotificationChannelModification(String s, UserHandle userhandle, NotificationChannel notificationchannel, int i)
        throws RemoteException;

    public abstract void onNotificationEnqueued(IStatusBarNotificationHolder istatusbarnotificationholder)
        throws RemoteException;

    public abstract void onNotificationPosted(IStatusBarNotificationHolder istatusbarnotificationholder, NotificationRankingUpdate notificationrankingupdate)
        throws RemoteException;

    public abstract void onNotificationRankingUpdate(NotificationRankingUpdate notificationrankingupdate)
        throws RemoteException;

    public abstract void onNotificationRemoved(IStatusBarNotificationHolder istatusbarnotificationholder, NotificationRankingUpdate notificationrankingupdate, int i)
        throws RemoteException;

    public abstract void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder istatusbarnotificationholder, String s)
        throws RemoteException;
}
