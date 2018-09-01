// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.notification;

import android.app.*;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.os.RemoteException;
import java.util.*;

public class SystemNotificationChannels
{

    private SystemNotificationChannels()
    {
    }

    public static void createAccountChannelForPackage(String s, int i, Context context)
    {
        INotificationManager inotificationmanager = NotificationManager.getService();
        try
        {
            ParceledListSlice parceledlistslice = JVM INSTR new #87  <Class ParceledListSlice>;
            parceledlistslice.ParceledListSlice(Arrays.asList(new NotificationChannel[] {
                newAccountChannel(context)
            }));
            inotificationmanager.createNotificationChannelsForPackage(s, i, parceledlistslice);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public static void createAll(Context context)
    {
        NotificationManager notificationmanager = (NotificationManager)context.getSystemService(android/app/NotificationManager);
        ArrayList arraylist = new ArrayList();
        arraylist.add(new NotificationChannel(VIRTUAL_KEYBOARD, context.getString(0x10403e7), 2));
        NotificationChannel notificationchannel = new NotificationChannel(PHYSICAL_KEYBOARD, context.getString(0x10403e1), 3);
        notificationchannel.setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI, Notification.AUDIO_ATTRIBUTES_DEFAULT);
        arraylist.add(notificationchannel);
        arraylist.add(new NotificationChannel(SECURITY, context.getString(0x10403e3), 2));
        arraylist.add(new NotificationChannel(CAR_MODE, context.getString(0x10403d5), 2));
        arraylist.add(newAccountChannel(context));
        arraylist.add(new NotificationChannel(DEVELOPER, context.getString(0x10403d6), 2));
        arraylist.add(new NotificationChannel(UPDATES, context.getString(0x10403e5), 2));
        arraylist.add(new NotificationChannel(NETWORK_STATUS, context.getString(0x10403e0), 2));
        notificationchannel = new NotificationChannel(NETWORK_ALERTS, context.getString(0x10403de), 4);
        notificationchannel.setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI, Notification.AUDIO_ATTRIBUTES_DEFAULT);
        arraylist.add(notificationchannel);
        arraylist.add(new NotificationChannel(NETWORK_AVAILABLE, context.getString(0x10403df), 2));
        arraylist.add(new NotificationChannel(VPN, context.getString(0x10403e9), 2));
        arraylist.add(new NotificationChannel(DEVICE_ADMIN, context.getString(0x10403d7), 2));
        notificationchannel = new NotificationChannel(ALERTS, context.getString(0x10403d3), 3);
        notificationchannel.setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI, Notification.AUDIO_ATTRIBUTES_DEFAULT);
        arraylist.add(notificationchannel);
        arraylist.add(new NotificationChannel(RETAIL_MODE, context.getString(0x10403e2), 2));
        arraylist.add(new NotificationChannel(USB, context.getString(0x10403e6), 1));
        context = new NotificationChannel(FOREGROUND_SERVICE, context.getString(0x10403d9), 2);
        context.setBlockableSystem(true);
        arraylist.add(context);
        notificationmanager.createNotificationChannels(arraylist);
    }

    private static NotificationChannel newAccountChannel(Context context)
    {
        return new NotificationChannel(ACCOUNT, context.getString(0x10403d2), 2);
    }

    public static String ACCOUNT = "ACCOUNT";
    public static String ALERTS = "ALERTS";
    public static String CAR_MODE = "CAR_MODE";
    public static String DEVELOPER = "DEVELOPER";
    public static String DEVICE_ADMIN = "DEVICE_ADMIN";
    public static String FOREGROUND_SERVICE = "FOREGROUND_SERVICE";
    public static String NETWORK_ALERTS = "NETWORK_ALERTS";
    public static String NETWORK_AVAILABLE = "NETWORK_AVAILABLE";
    public static String NETWORK_STATUS = "NETWORK_STATUS";
    public static String PHYSICAL_KEYBOARD = "PHYSICAL_KEYBOARD";
    public static String RETAIL_MODE = "RETAIL_MODE";
    public static String SECURITY = "SECURITY";
    public static String UPDATES = "UPDATES";
    public static String USB = "USB";
    public static String VIRTUAL_KEYBOARD = "VIRTUAL_KEYBOARD";
    public static String VPN = "VPN";

}
