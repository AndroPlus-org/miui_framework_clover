// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.app.Notification;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import java.util.Objects;

public class NotificationMessagingUtil
{

    static void _2D_wrap0(NotificationMessagingUtil notificationmessagingutil, int i)
    {
        notificationmessagingutil.cacheDefaultSmsApp(i);
    }

    public NotificationMessagingUtil(Context context)
    {
        mDefaultSmsApp = new ArrayMap();
        mContext = context;
        mContext.getContentResolver().registerContentObserver(android.provider.Settings.Secure.getUriFor("sms_default_application"), false, mSmsContentObserver);
    }

    private void cacheDefaultSmsApp(int i)
    {
        mDefaultSmsApp.put(Integer.valueOf(i), android.provider.Settings.Secure.getStringForUser(mContext.getContentResolver(), "sms_default_application", i));
    }

    private boolean isDefaultMessagingApp(StatusBarNotification statusbarnotification)
    {
        int i = statusbarnotification.getUserId();
        if(i == -10000 || i == -1)
            return false;
        if(mDefaultSmsApp.get(Integer.valueOf(i)) == null)
            cacheDefaultSmsApp(i);
        return Objects.equals(mDefaultSmsApp.get(Integer.valueOf(i)), statusbarnotification.getPackageName());
    }

    public boolean isImportantMessaging(StatusBarNotification statusbarnotification, int i)
    {
        if(i < 2)
            return false;
        if(android/app/Notification$MessagingStyle.equals(statusbarnotification.getNotification().getNotificationStyle()))
            return true;
        return "msg".equals(statusbarnotification.getNotification().category) && isDefaultMessagingApp(statusbarnotification);
    }

    private static final String DEFAULT_SMS_APP_SETTING = "sms_default_application";
    private final Context mContext;
    private ArrayMap mDefaultSmsApp;
    private final ContentObserver mSmsContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {

        public void onChange(boolean flag, Uri uri, int i)
        {
            if(android.provider.Settings.Secure.getUriFor("sms_default_application").equals(uri))
                NotificationMessagingUtil._2D_wrap0(NotificationMessagingUtil.this, i);
        }

        final NotificationMessagingUtil this$0;

            
            {
                this$0 = NotificationMessagingUtil.this;
                super(handler);
            }
    }
;
}
