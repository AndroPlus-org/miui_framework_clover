// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.*;
import android.service.notification.*;
import java.util.List;

// Referenced classes of package android.app:
//            AutomaticZenRule, ITransientNotification, Notification, NotificationChannel, 
//            NotificationChannelGroup

public interface INotificationManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INotificationManager
    {

        public static INotificationManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.INotificationManager");
            if(iinterface != null && (iinterface instanceof INotificationManager))
                return (INotificationManager)iinterface;
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
                parcel1.writeString("android.app.INotificationManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.INotificationManager");
                cancelAllNotifications(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.INotificationManager");
                String s = parcel.readString();
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                clearData(s, i, flag);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.INotificationManager");
                enqueueToast(parcel.readString(), ITransientNotification.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.INotificationManager");
                cancelToast(parcel.readString(), ITransientNotification.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.INotificationManager");
                String s17 = parcel.readString();
                String s20 = parcel.readString();
                String s21 = parcel.readString();
                i = parcel.readInt();
                Notification notification;
                if(parcel.readInt() != 0)
                    notification = (Notification)Notification.CREATOR.createFromParcel(parcel);
                else
                    notification = null;
                enqueueNotificationWithTag(s17, s20, s21, i, notification, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.INotificationManager");
                cancelNotificationWithTag(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.INotificationManager");
                String s1 = parcel.readString();
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setShowBadge(s1, i, flag1);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag2 = canShowBadge(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.INotificationManager");
                String s2 = parcel.readString();
                i = parcel.readInt();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setNotificationsEnabledForPackage(s2, i, flag3);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag4 = areNotificationsEnabledForPackage(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag5 = areNotificationsEnabled(parcel.readString());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.app.INotificationManager");
                i = getPackageImportance(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.app.INotificationManager");
                String s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                createNotificationChannelGroups(s3, parcel);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.app.INotificationManager");
                String s4 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                createNotificationChannels(s4, parcel);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.app.INotificationManager");
                String s5 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                createNotificationChannelsForPackage(s5, i, parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.app.INotificationManager");
                String s6 = parcel.readString();
                i = parcel.readInt();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                parcel = getNotificationChannelGroupsForPackage(s6, i, flag6);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getNotificationChannelGroupForPackage(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.app.INotificationManager");
                String s7 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateNotificationChannelForPackage(s7, i, parcel);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getNotificationChannel(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.app.INotificationManager");
                String s8 = parcel.readString();
                i = parcel.readInt();
                String s18 = parcel.readString();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                parcel = getNotificationChannelForPackage(s8, i, s18, flag7);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.app.INotificationManager");
                deleteNotificationChannel(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getNotificationChannels(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.app.INotificationManager");
                String s9 = parcel.readString();
                i = parcel.readInt();
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                parcel = getNotificationChannelsForPackage(s9, i, flag8);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.app.INotificationManager");
                String s10 = parcel.readString();
                i = parcel.readInt();
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                i = getNumNotificationChannelsForPackage(s10, i, flag9);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.app.INotificationManager");
                i = getDeletedChannelCount(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.app.INotificationManager");
                deleteNotificationChannelGroup(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getNotificationChannelGroups(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag10 = onlyHasDefaultChannel(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getActiveNotifications(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getHistoricalNotifications(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.app.INotificationManager");
                INotificationListener inotificationlistener2 = android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder());
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                registerListener(inotificationlistener2, componentname, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.app.INotificationManager");
                unregisterListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.app.INotificationManager");
                cancelNotificationFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.app.INotificationManager");
                cancelNotificationsFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.app.INotificationManager");
                snoozeNotificationUntilContextFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.app.INotificationManager");
                snoozeNotificationUntilFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.app.INotificationManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestBindListener(parcel);
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.app.INotificationManager");
                requestUnbindListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.app.INotificationManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestBindProvider(parcel);
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.app.INotificationManager");
                requestUnbindProvider(android.service.notification.IConditionProvider.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.app.INotificationManager");
                setNotificationsShownFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getActiveNotificationsFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArray(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getSnoozedNotificationsFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 44: // ','
                parcel.enforceInterface("android.app.INotificationManager");
                requestHintsFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.app.INotificationManager");
                i = getHintsFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.app.INotificationManager");
                requestInterruptionFilterFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.app.INotificationManager");
                i = getInterruptionFilterFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.app.INotificationManager");
                setOnNotificationPostedTrimFromListener(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.app.INotificationManager");
                setInterruptionFilter(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.app.INotificationManager");
                INotificationListener inotificationlistener5 = android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder());
                String s19 = parcel.readString();
                UserHandle userhandle;
                if(parcel.readInt() != 0)
                    userhandle = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    userhandle = null;
                if(parcel.readInt() != 0)
                    parcel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateNotificationChannelFromPrivilegedListener(inotificationlistener5, s19, userhandle, parcel);
                parcel1.writeNoException();
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.app.INotificationManager");
                INotificationListener inotificationlistener3 = android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder());
                String s11 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getNotificationChannelsFromPrivilegedListener(inotificationlistener3, s11, parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.app.INotificationManager");
                INotificationListener inotificationlistener4 = android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder());
                String s12 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getNotificationChannelGroupsFromPrivilegedListener(inotificationlistener4, s12, parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.app.INotificationManager");
                INotificationListener inotificationlistener = android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Adjustment)Adjustment.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                applyEnqueuedAdjustmentFromAssistant(inotificationlistener, parcel);
                parcel1.writeNoException();
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.app.INotificationManager");
                INotificationListener inotificationlistener1 = android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Adjustment)Adjustment.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                applyAdjustmentFromAssistant(inotificationlistener1, parcel);
                parcel1.writeNoException();
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.app.INotificationManager");
                applyAdjustmentsFromAssistant(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createTypedArrayList(Adjustment.CREATOR));
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.app.INotificationManager");
                unsnoozeNotificationFromAssistant(android.service.notification.INotificationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getEffectsSuppressor();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag11;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag11 = matchesCallFilter(parcel);
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag12 = isSystemConditionProviderEnabled(parcel.readString());
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag13;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag13 = isNotificationListenerAccessGranted(parcel);
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("android.app.INotificationManager");
                ComponentName componentname1;
                boolean flag14;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                flag14 = isNotificationListenerAccessGrantedForUser(componentname1, parcel.readInt());
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag15;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag15 = isNotificationAssistantAccessGranted(parcel);
                parcel1.writeNoException();
                if(flag15)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.app.INotificationManager");
                ComponentName componentname2;
                boolean flag16;
                if(parcel.readInt() != 0)
                    componentname2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname2 = null;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                setNotificationListenerAccessGranted(componentname2, flag16);
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.app.INotificationManager");
                ComponentName componentname3;
                boolean flag17;
                if(parcel.readInt() != 0)
                    componentname3 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname3 = null;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                setNotificationAssistantAccessGranted(componentname3, flag17);
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.app.INotificationManager");
                ComponentName componentname4;
                boolean flag18;
                if(parcel.readInt() != 0)
                    componentname4 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname4 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                setNotificationListenerAccessGrantedForUser(componentname4, i, flag18);
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.app.INotificationManager");
                ComponentName componentname5;
                boolean flag19;
                if(parcel.readInt() != 0)
                    componentname5 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname5 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag19 = true;
                else
                    flag19 = false;
                setNotificationAssistantAccessGrantedForUser(componentname5, i, flag19);
                parcel1.writeNoException();
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getEnabledNotificationListenerPackages();
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getEnabledNotificationListeners(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("android.app.INotificationManager");
                i = getZenMode();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getZenModeConfig();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.app.INotificationManager");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setZenMode(i, parcel1, parcel.readString());
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.app.INotificationManager");
                notifyConditions(parcel.readString(), android.service.notification.IConditionProvider.Stub.asInterface(parcel.readStrongBinder()), (Condition[])parcel.createTypedArray(Condition.CREATOR));
                return true;

            case 73: // 'I'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag20 = isNotificationPolicyAccessGranted(parcel.readString());
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getNotificationPolicy(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.app.INotificationManager");
                String s13 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (NotificationManager.Policy)NotificationManager.Policy.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setNotificationPolicy(s13, parcel);
                parcel1.writeNoException();
                return true;

            case 76: // 'L'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag21 = isNotificationPolicyAccessGrantedForPackage(parcel.readString());
                parcel1.writeNoException();
                if(flag21)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 77: // 'M'
                parcel.enforceInterface("android.app.INotificationManager");
                String s14 = parcel.readString();
                boolean flag22;
                if(parcel.readInt() != 0)
                    flag22 = true;
                else
                    flag22 = false;
                setNotificationPolicyAccessGranted(s14, flag22);
                parcel1.writeNoException();
                return true;

            case 78: // 'N'
                parcel.enforceInterface("android.app.INotificationManager");
                String s15 = parcel.readString();
                i = parcel.readInt();
                boolean flag23;
                if(parcel.readInt() != 0)
                    flag23 = true;
                else
                    flag23 = false;
                setNotificationPolicyAccessGrantedForUser(s15, i, flag23);
                parcel1.writeNoException();
                return true;

            case 79: // 'O'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getAutomaticZenRule(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 80: // 'P'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getZenRules();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("android.app.INotificationManager");
                if(parcel.readInt() != 0)
                    parcel = (AutomaticZenRule)AutomaticZenRule.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = addAutomaticZenRule(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 82: // 'R'
                parcel.enforceInterface("android.app.INotificationManager");
                String s16 = parcel.readString();
                boolean flag24;
                if(parcel.readInt() != 0)
                    parcel = (AutomaticZenRule)AutomaticZenRule.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag24 = updateAutomaticZenRule(s16, parcel);
                parcel1.writeNoException();
                if(flag24)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 83: // 'S'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag25 = removeAutomaticZenRule(parcel.readString());
                parcel1.writeNoException();
                if(flag25)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 84: // 'T'
                parcel.enforceInterface("android.app.INotificationManager");
                boolean flag26 = removeAutomaticZenRules(parcel.readString());
                parcel1.writeNoException();
                if(flag26)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 85: // 'U'
                parcel.enforceInterface("android.app.INotificationManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getRuleInstanceCount(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 86: // 'V'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getBackupPayload(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 87: // 'W'
                parcel.enforceInterface("android.app.INotificationManager");
                applyRestore(parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 88: // 'X'
                parcel.enforceInterface("android.app.INotificationManager");
                parcel = getAppActiveNotifications(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 89: // 'Y'
                parcel.enforceInterface("android.app.INotificationManager");
                buzzBeepBlinkForNotification(parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.INotificationManager";
        static final int TRANSACTION_addAutomaticZenRule = 81;
        static final int TRANSACTION_applyAdjustmentFromAssistant = 54;
        static final int TRANSACTION_applyAdjustmentsFromAssistant = 55;
        static final int TRANSACTION_applyEnqueuedAdjustmentFromAssistant = 53;
        static final int TRANSACTION_applyRestore = 87;
        static final int TRANSACTION_areNotificationsEnabled = 11;
        static final int TRANSACTION_areNotificationsEnabledForPackage = 10;
        static final int TRANSACTION_buzzBeepBlinkForNotification = 89;
        static final int TRANSACTION_canShowBadge = 8;
        static final int TRANSACTION_cancelAllNotifications = 1;
        static final int TRANSACTION_cancelNotificationFromListener = 33;
        static final int TRANSACTION_cancelNotificationWithTag = 6;
        static final int TRANSACTION_cancelNotificationsFromListener = 34;
        static final int TRANSACTION_cancelToast = 4;
        static final int TRANSACTION_clearData = 2;
        static final int TRANSACTION_createNotificationChannelGroups = 13;
        static final int TRANSACTION_createNotificationChannels = 14;
        static final int TRANSACTION_createNotificationChannelsForPackage = 15;
        static final int TRANSACTION_deleteNotificationChannel = 21;
        static final int TRANSACTION_deleteNotificationChannelGroup = 26;
        static final int TRANSACTION_enqueueNotificationWithTag = 5;
        static final int TRANSACTION_enqueueToast = 3;
        static final int TRANSACTION_getActiveNotifications = 29;
        static final int TRANSACTION_getActiveNotificationsFromListener = 42;
        static final int TRANSACTION_getAppActiveNotifications = 88;
        static final int TRANSACTION_getAutomaticZenRule = 79;
        static final int TRANSACTION_getBackupPayload = 86;
        static final int TRANSACTION_getDeletedChannelCount = 25;
        static final int TRANSACTION_getEffectsSuppressor = 57;
        static final int TRANSACTION_getEnabledNotificationListenerPackages = 67;
        static final int TRANSACTION_getEnabledNotificationListeners = 68;
        static final int TRANSACTION_getHintsFromListener = 45;
        static final int TRANSACTION_getHistoricalNotifications = 30;
        static final int TRANSACTION_getInterruptionFilterFromListener = 47;
        static final int TRANSACTION_getNotificationChannel = 19;
        static final int TRANSACTION_getNotificationChannelForPackage = 20;
        static final int TRANSACTION_getNotificationChannelGroupForPackage = 17;
        static final int TRANSACTION_getNotificationChannelGroups = 27;
        static final int TRANSACTION_getNotificationChannelGroupsForPackage = 16;
        static final int TRANSACTION_getNotificationChannelGroupsFromPrivilegedListener = 52;
        static final int TRANSACTION_getNotificationChannels = 22;
        static final int TRANSACTION_getNotificationChannelsForPackage = 23;
        static final int TRANSACTION_getNotificationChannelsFromPrivilegedListener = 51;
        static final int TRANSACTION_getNotificationPolicy = 74;
        static final int TRANSACTION_getNumNotificationChannelsForPackage = 24;
        static final int TRANSACTION_getPackageImportance = 12;
        static final int TRANSACTION_getRuleInstanceCount = 85;
        static final int TRANSACTION_getSnoozedNotificationsFromListener = 43;
        static final int TRANSACTION_getZenMode = 69;
        static final int TRANSACTION_getZenModeConfig = 70;
        static final int TRANSACTION_getZenRules = 80;
        static final int TRANSACTION_isNotificationAssistantAccessGranted = 62;
        static final int TRANSACTION_isNotificationListenerAccessGranted = 60;
        static final int TRANSACTION_isNotificationListenerAccessGrantedForUser = 61;
        static final int TRANSACTION_isNotificationPolicyAccessGranted = 73;
        static final int TRANSACTION_isNotificationPolicyAccessGrantedForPackage = 76;
        static final int TRANSACTION_isSystemConditionProviderEnabled = 59;
        static final int TRANSACTION_matchesCallFilter = 58;
        static final int TRANSACTION_notifyConditions = 72;
        static final int TRANSACTION_onlyHasDefaultChannel = 28;
        static final int TRANSACTION_registerListener = 31;
        static final int TRANSACTION_removeAutomaticZenRule = 83;
        static final int TRANSACTION_removeAutomaticZenRules = 84;
        static final int TRANSACTION_requestBindListener = 37;
        static final int TRANSACTION_requestBindProvider = 39;
        static final int TRANSACTION_requestHintsFromListener = 44;
        static final int TRANSACTION_requestInterruptionFilterFromListener = 46;
        static final int TRANSACTION_requestUnbindListener = 38;
        static final int TRANSACTION_requestUnbindProvider = 40;
        static final int TRANSACTION_setInterruptionFilter = 49;
        static final int TRANSACTION_setNotificationAssistantAccessGranted = 64;
        static final int TRANSACTION_setNotificationAssistantAccessGrantedForUser = 66;
        static final int TRANSACTION_setNotificationListenerAccessGranted = 63;
        static final int TRANSACTION_setNotificationListenerAccessGrantedForUser = 65;
        static final int TRANSACTION_setNotificationPolicy = 75;
        static final int TRANSACTION_setNotificationPolicyAccessGranted = 77;
        static final int TRANSACTION_setNotificationPolicyAccessGrantedForUser = 78;
        static final int TRANSACTION_setNotificationsEnabledForPackage = 9;
        static final int TRANSACTION_setNotificationsShownFromListener = 41;
        static final int TRANSACTION_setOnNotificationPostedTrimFromListener = 48;
        static final int TRANSACTION_setShowBadge = 7;
        static final int TRANSACTION_setZenMode = 71;
        static final int TRANSACTION_snoozeNotificationUntilContextFromListener = 35;
        static final int TRANSACTION_snoozeNotificationUntilFromListener = 36;
        static final int TRANSACTION_unregisterListener = 32;
        static final int TRANSACTION_unsnoozeNotificationFromAssistant = 56;
        static final int TRANSACTION_updateAutomaticZenRule = 82;
        static final int TRANSACTION_updateNotificationChannelForPackage = 18;
        static final int TRANSACTION_updateNotificationChannelFromPrivilegedListener = 50;

        public Stub()
        {
            attachInterface(this, "android.app.INotificationManager");
        }
    }

    private static class Stub.Proxy
        implements INotificationManager
    {

        public String addAutomaticZenRule(AutomaticZenRule automaticzenrule)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(automaticzenrule == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            automaticzenrule.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(81, parcel, parcel1, 0);
            parcel1.readException();
            automaticzenrule = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return automaticzenrule;
            parcel.writeInt(0);
              goto _L1
            automaticzenrule;
            parcel1.recycle();
            parcel.recycle();
            throw automaticzenrule;
        }

        public void applyAdjustmentFromAssistant(INotificationListener inotificationlistener, Adjustment adjustment)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(adjustment == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            adjustment.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(54, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void applyAdjustmentsFromAssistant(INotificationListener inotificationlistener, List list)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeTypedList(list);
            mRemote.transact(55, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void applyEnqueuedAdjustmentFromAssistant(INotificationListener inotificationlistener, Adjustment adjustment)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(adjustment == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            adjustment.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(53, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void applyRestore(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(87, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public boolean areNotificationsEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean areNotificationsEnabledForPackage(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void buzzBeepBlinkForNotification(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(89, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean canShowBadge(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void cancelAllNotifications(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void cancelNotificationFromListener(INotificationListener inotificationlistener, String s, String s1, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void cancelNotificationWithTag(String s, String s1, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void cancelNotificationsFromListener(INotificationListener inotificationlistener, String as[])
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void cancelToast(String s, ITransientNotification itransientnotification)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            s = obj;
            if(itransientnotification == null)
                break MISSING_BLOCK_LABEL_38;
            s = itransientnotification.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearData(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void createNotificationChannelGroups(String s, ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void createNotificationChannels(String s, ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void createNotificationChannelsForPackage(String s, int i, ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void deleteNotificationChannel(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void deleteNotificationChannelGroup(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void enqueueNotificationWithTag(String s, String s1, String s2, int i, Notification notification, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i);
            if(notification == null)
                break MISSING_BLOCK_LABEL_100;
            parcel.writeInt(1);
            notification.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void enqueueToast(String s, ITransientNotification itransientnotification, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            s = obj;
            if(itransientnotification == null)
                break MISSING_BLOCK_LABEL_40;
            s = itransientnotification.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public StatusBarNotification[] getActiveNotifications(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            s = (StatusBarNotification[])parcel1.createTypedArray(StatusBarNotification.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice getActiveNotificationsFromListener(INotificationListener inotificationlistener, String as[], int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            inotificationlistener = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return inotificationlistener;
_L2:
            inotificationlistener = null;
            if(true) goto _L4; else goto _L3
_L3:
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public ParceledListSlice getAppActiveNotifications(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(88, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public AutomaticZenRule getAutomaticZenRule(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(79, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (AutomaticZenRule)AutomaticZenRule.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public byte[] getBackupPayload(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeInt(i);
            mRemote.transact(86, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getDeletedChannelCount(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ComponentName getEffectsSuppressor()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            mRemote.transact(57, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ComponentName componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            componentname = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getEnabledNotificationListenerPackages()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            mRemote.transact(67, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getEnabledNotificationListeners(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeInt(i);
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(ComponentName.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getHintsFromListener(INotificationListener inotificationlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inotificationlistener.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public StatusBarNotification[] getHistoricalNotifications(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            s = (StatusBarNotification[])parcel1.createTypedArray(StatusBarNotification.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.INotificationManager";
        }

        public int getInterruptionFilterFromListener(INotificationListener inotificationlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inotificationlistener.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public NotificationChannel getNotificationChannel(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public NotificationChannel getNotificationChannelForPackage(String s, int i, String s1, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public NotificationChannelGroup getNotificationChannelGroupForPackage(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice getNotificationChannelGroups(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice getNotificationChannelGroupsForPackage(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener inotificationlistener, String s, UserHandle userhandle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(52, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_140;
            inotificationlistener = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return inotificationlistener;
_L2:
            parcel.writeInt(0);
              goto _L3
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
            inotificationlistener = null;
              goto _L4
        }

        public ParceledListSlice getNotificationChannels(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice getNotificationChannelsForPackage(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener inotificationlistener, String s, UserHandle userhandle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_140;
            inotificationlistener = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return inotificationlistener;
_L2:
            parcel.writeInt(0);
              goto _L3
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
            inotificationlistener = null;
              goto _L4
        }

        public NotificationManager.Policy getNotificationPolicy(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(74, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (NotificationManager.Policy)NotificationManager.Policy.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getNumNotificationChannelsForPackage(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getPackageImportance(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getRuleInstanceCount(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(85, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener inotificationlistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            inotificationlistener = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return inotificationlistener;
_L2:
            inotificationlistener = null;
            if(true) goto _L4; else goto _L3
_L3:
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public int getZenMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ZenModeConfig getZenModeConfig()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            mRemote.transact(70, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ZenModeConfig zenmodeconfig = (ZenModeConfig)ZenModeConfig.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return zenmodeconfig;
_L2:
            zenmodeconfig = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getZenRules()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            mRemote.transact(80, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(android.service.notification.ZenModeConfig.ZenRule.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isNotificationAssistantAccessGranted(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(62, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isNotificationListenerAccessGranted(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(60, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isNotificationListenerAccessGrantedForUser(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(61, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isNotificationPolicyAccessGranted(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(73, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isNotificationPolicyAccessGrantedForPackage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(76, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isSystemConditionProviderEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(59, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean matchesCallFilter(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(58, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public void notifyConditions(String s, IConditionProvider iconditionprovider, Condition acondition[])
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            s = obj;
            if(iconditionprovider == null)
                break MISSING_BLOCK_LABEL_35;
            s = iconditionprovider.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeTypedArray(acondition, 0);
            mRemote.transact(72, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public boolean onlyHasDefaultChannel(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void registerListener(INotificationListener inotificationlistener, ComponentName componentname, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public boolean removeAutomaticZenRule(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(83, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean removeAutomaticZenRules(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            mRemote.transact(84, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void requestBindListener(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void requestBindProvider(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void requestHintsFromListener(INotificationListener inotificationlistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void requestInterruptionFilterFromListener(INotificationListener inotificationlistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void requestUnbindListener(INotificationListener inotificationlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void requestUnbindProvider(IConditionProvider iconditionprovider)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(iconditionprovider == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iconditionprovider.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iconditionprovider;
            parcel1.recycle();
            parcel.recycle();
            throw iconditionprovider;
        }

        public void setInterruptionFilter(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setNotificationAssistantAccessGranted(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setNotificationAssistantAccessGrantedForUser(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(66, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setNotificationListenerAccessGranted(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(63, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setNotificationListenerAccessGrantedForUser(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setNotificationPolicy(String s, NotificationManager.Policy policy)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            if(policy == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            policy.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(75, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setNotificationPolicyAccessGranted(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(77, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setNotificationPolicyAccessGrantedForUser(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(78, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setNotificationsEnabledForPackage(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setNotificationsShownFromListener(INotificationListener inotificationlistener, String as[])
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void setOnNotificationPostedTrimFromListener(INotificationListener inotificationlistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void setShowBadge(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setZenMode(int i, Uri uri, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeInt(i);
            if(uri == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(71, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void snoozeNotificationUntilContextFromListener(INotificationListener inotificationlistener, String s, String s1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void snoozeNotificationUntilFromListener(INotificationListener inotificationlistener, String s, long l)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeLong(l);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void unregisterListener(INotificationListener inotificationlistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public void unsnoozeNotificationFromAssistant(INotificationListener inotificationlistener, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(56, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
        }

        public boolean updateAutomaticZenRule(String s, AutomaticZenRule automaticzenrule)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            if(automaticzenrule == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            automaticzenrule.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(82, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateNotificationChannelForPackage(String s, int i, NotificationChannel notificationchannel)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(notificationchannel == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            notificationchannel.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateNotificationChannelFromPrivilegedListener(INotificationListener inotificationlistener, String s, UserHandle userhandle, NotificationChannel notificationchannel)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.INotificationManager");
            if(inotificationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = inotificationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            if(notificationchannel == null)
                break MISSING_BLOCK_LABEL_136;
            parcel.writeInt(1);
            notificationchannel.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            inotificationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw inotificationlistener;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract String addAutomaticZenRule(AutomaticZenRule automaticzenrule)
        throws RemoteException;

    public abstract void applyAdjustmentFromAssistant(INotificationListener inotificationlistener, Adjustment adjustment)
        throws RemoteException;

    public abstract void applyAdjustmentsFromAssistant(INotificationListener inotificationlistener, List list)
        throws RemoteException;

    public abstract void applyEnqueuedAdjustmentFromAssistant(INotificationListener inotificationlistener, Adjustment adjustment)
        throws RemoteException;

    public abstract void applyRestore(byte abyte0[], int i)
        throws RemoteException;

    public abstract boolean areNotificationsEnabled(String s)
        throws RemoteException;

    public abstract boolean areNotificationsEnabledForPackage(String s, int i)
        throws RemoteException;

    public abstract void buzzBeepBlinkForNotification(String s)
        throws RemoteException;

    public abstract boolean canShowBadge(String s, int i)
        throws RemoteException;

    public abstract void cancelAllNotifications(String s, int i)
        throws RemoteException;

    public abstract void cancelNotificationFromListener(INotificationListener inotificationlistener, String s, String s1, int i)
        throws RemoteException;

    public abstract void cancelNotificationWithTag(String s, String s1, int i, int j)
        throws RemoteException;

    public abstract void cancelNotificationsFromListener(INotificationListener inotificationlistener, String as[])
        throws RemoteException;

    public abstract void cancelToast(String s, ITransientNotification itransientnotification)
        throws RemoteException;

    public abstract void clearData(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void createNotificationChannelGroups(String s, ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void createNotificationChannels(String s, ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void createNotificationChannelsForPackage(String s, int i, ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void deleteNotificationChannel(String s, String s1)
        throws RemoteException;

    public abstract void deleteNotificationChannelGroup(String s, String s1)
        throws RemoteException;

    public abstract void enqueueNotificationWithTag(String s, String s1, String s2, int i, Notification notification, int j)
        throws RemoteException;

    public abstract void enqueueToast(String s, ITransientNotification itransientnotification, int i)
        throws RemoteException;

    public abstract StatusBarNotification[] getActiveNotifications(String s)
        throws RemoteException;

    public abstract ParceledListSlice getActiveNotificationsFromListener(INotificationListener inotificationlistener, String as[], int i)
        throws RemoteException;

    public abstract ParceledListSlice getAppActiveNotifications(String s, int i)
        throws RemoteException;

    public abstract AutomaticZenRule getAutomaticZenRule(String s)
        throws RemoteException;

    public abstract byte[] getBackupPayload(int i)
        throws RemoteException;

    public abstract int getDeletedChannelCount(String s, int i)
        throws RemoteException;

    public abstract ComponentName getEffectsSuppressor()
        throws RemoteException;

    public abstract List getEnabledNotificationListenerPackages()
        throws RemoteException;

    public abstract List getEnabledNotificationListeners(int i)
        throws RemoteException;

    public abstract int getHintsFromListener(INotificationListener inotificationlistener)
        throws RemoteException;

    public abstract StatusBarNotification[] getHistoricalNotifications(String s, int i)
        throws RemoteException;

    public abstract int getInterruptionFilterFromListener(INotificationListener inotificationlistener)
        throws RemoteException;

    public abstract NotificationChannel getNotificationChannel(String s, String s1)
        throws RemoteException;

    public abstract NotificationChannel getNotificationChannelForPackage(String s, int i, String s1, boolean flag)
        throws RemoteException;

    public abstract NotificationChannelGroup getNotificationChannelGroupForPackage(String s, String s1, int i)
        throws RemoteException;

    public abstract ParceledListSlice getNotificationChannelGroups(String s)
        throws RemoteException;

    public abstract ParceledListSlice getNotificationChannelGroupsForPackage(String s, int i, boolean flag)
        throws RemoteException;

    public abstract ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener inotificationlistener, String s, UserHandle userhandle)
        throws RemoteException;

    public abstract ParceledListSlice getNotificationChannels(String s)
        throws RemoteException;

    public abstract ParceledListSlice getNotificationChannelsForPackage(String s, int i, boolean flag)
        throws RemoteException;

    public abstract ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener inotificationlistener, String s, UserHandle userhandle)
        throws RemoteException;

    public abstract NotificationManager.Policy getNotificationPolicy(String s)
        throws RemoteException;

    public abstract int getNumNotificationChannelsForPackage(String s, int i, boolean flag)
        throws RemoteException;

    public abstract int getPackageImportance(String s)
        throws RemoteException;

    public abstract int getRuleInstanceCount(ComponentName componentname)
        throws RemoteException;

    public abstract ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener inotificationlistener, int i)
        throws RemoteException;

    public abstract int getZenMode()
        throws RemoteException;

    public abstract ZenModeConfig getZenModeConfig()
        throws RemoteException;

    public abstract List getZenRules()
        throws RemoteException;

    public abstract boolean isNotificationAssistantAccessGranted(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isNotificationListenerAccessGranted(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isNotificationListenerAccessGrantedForUser(ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean isNotificationPolicyAccessGranted(String s)
        throws RemoteException;

    public abstract boolean isNotificationPolicyAccessGrantedForPackage(String s)
        throws RemoteException;

    public abstract boolean isSystemConditionProviderEnabled(String s)
        throws RemoteException;

    public abstract boolean matchesCallFilter(Bundle bundle)
        throws RemoteException;

    public abstract void notifyConditions(String s, IConditionProvider iconditionprovider, Condition acondition[])
        throws RemoteException;

    public abstract boolean onlyHasDefaultChannel(String s, int i)
        throws RemoteException;

    public abstract void registerListener(INotificationListener inotificationlistener, ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean removeAutomaticZenRule(String s)
        throws RemoteException;

    public abstract boolean removeAutomaticZenRules(String s)
        throws RemoteException;

    public abstract void requestBindListener(ComponentName componentname)
        throws RemoteException;

    public abstract void requestBindProvider(ComponentName componentname)
        throws RemoteException;

    public abstract void requestHintsFromListener(INotificationListener inotificationlistener, int i)
        throws RemoteException;

    public abstract void requestInterruptionFilterFromListener(INotificationListener inotificationlistener, int i)
        throws RemoteException;

    public abstract void requestUnbindListener(INotificationListener inotificationlistener)
        throws RemoteException;

    public abstract void requestUnbindProvider(IConditionProvider iconditionprovider)
        throws RemoteException;

    public abstract void setInterruptionFilter(String s, int i)
        throws RemoteException;

    public abstract void setNotificationAssistantAccessGranted(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setNotificationAssistantAccessGrantedForUser(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setNotificationListenerAccessGranted(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setNotificationListenerAccessGrantedForUser(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setNotificationPolicy(String s, NotificationManager.Policy policy)
        throws RemoteException;

    public abstract void setNotificationPolicyAccessGranted(String s, boolean flag)
        throws RemoteException;

    public abstract void setNotificationPolicyAccessGrantedForUser(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void setNotificationsEnabledForPackage(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void setNotificationsShownFromListener(INotificationListener inotificationlistener, String as[])
        throws RemoteException;

    public abstract void setOnNotificationPostedTrimFromListener(INotificationListener inotificationlistener, int i)
        throws RemoteException;

    public abstract void setShowBadge(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void setZenMode(int i, Uri uri, String s)
        throws RemoteException;

    public abstract void snoozeNotificationUntilContextFromListener(INotificationListener inotificationlistener, String s, String s1)
        throws RemoteException;

    public abstract void snoozeNotificationUntilFromListener(INotificationListener inotificationlistener, String s, long l)
        throws RemoteException;

    public abstract void unregisterListener(INotificationListener inotificationlistener, int i)
        throws RemoteException;

    public abstract void unsnoozeNotificationFromAssistant(INotificationListener inotificationlistener, String s)
        throws RemoteException;

    public abstract boolean updateAutomaticZenRule(String s, AutomaticZenRule automaticzenrule)
        throws RemoteException;

    public abstract void updateNotificationChannelForPackage(String s, int i, NotificationChannel notificationchannel)
        throws RemoteException;

    public abstract void updateNotificationChannelFromPrivilegedListener(INotificationListener inotificationlistener, String s, UserHandle userhandle, NotificationChannel notificationchannel)
        throws RemoteException;
}
