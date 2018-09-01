// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.app.INotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import com.android.internal.os.SomeArgs;
import java.util.List;

// Referenced classes of package android.service.notification:
//            NotificationListenerService, Adjustment, StatusBarNotification, IStatusBarNotificationHolder

public abstract class NotificationAssistantService extends NotificationListenerService
{
    private final class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 111;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            message = (SomeArgs)message.obj;
            StatusBarNotification statusbarnotification = (StatusBarNotification)((SomeArgs) (message)).arg1;
            message.recycle();
            message = onNotificationEnqueued(statusbarnotification);
            if(message != null)
            {
                if(!isBound())
                    return;
                try
                {
                    getNotificationInterface().applyEnqueuedAdjustmentFromAssistant(mWrapper, message);
                }
                // Misplaced declaration of an exception variable
                catch(Message message)
                {
                    Log.v("NotificationAssistants", "Unable to contact notification manager", message);
                    throw message.rethrowFromSystemServer();
                }
            }
            continue; /* Loop/switch isn't completed */
_L3:
            SomeArgs someargs = (SomeArgs)message.obj;
            message = (StatusBarNotification)someargs.arg1;
            String s = (String)someargs.arg2;
            someargs.recycle();
            onNotificationSnoozedUntilContext(message, s);
            if(true) goto _L1; else goto _L4
_L4:
        }

        public static final int MSG_ON_NOTIFICATION_ENQUEUED = 1;
        public static final int MSG_ON_NOTIFICATION_SNOOZED = 2;
        final NotificationAssistantService this$0;

        public MyHandler(Looper looper)
        {
            this$0 = NotificationAssistantService.this;
            super(looper, null, false);
        }
    }

    private class NotificationAssistantServiceWrapper extends NotificationListenerService.NotificationListenerWrapper
    {

        public void onNotificationEnqueued(IStatusBarNotificationHolder istatusbarnotificationholder)
        {
            SomeArgs someargs;
            try
            {
                istatusbarnotificationholder = istatusbarnotificationholder.get();
            }
            // Misplaced declaration of an exception variable
            catch(IStatusBarNotificationHolder istatusbarnotificationholder)
            {
                Log.w("NotificationAssistants", "onNotificationEnqueued: Error receiving StatusBarNotification", istatusbarnotificationholder);
                return;
            }
            someargs = SomeArgs.obtain();
            someargs.arg1 = istatusbarnotificationholder;
            NotificationAssistantService._2D_get0(NotificationAssistantService.this).obtainMessage(1, someargs).sendToTarget();
        }

        public void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder istatusbarnotificationholder, String s)
            throws RemoteException
        {
            SomeArgs someargs;
            try
            {
                istatusbarnotificationholder = istatusbarnotificationholder.get();
            }
            // Misplaced declaration of an exception variable
            catch(IStatusBarNotificationHolder istatusbarnotificationholder)
            {
                Log.w("NotificationAssistants", "onNotificationSnoozed: Error receiving StatusBarNotification", istatusbarnotificationholder);
                return;
            }
            someargs = SomeArgs.obtain();
            someargs.arg1 = istatusbarnotificationholder;
            someargs.arg2 = s;
            NotificationAssistantService._2D_get0(NotificationAssistantService.this).obtainMessage(2, someargs).sendToTarget();
        }

        final NotificationAssistantService this$0;

        private NotificationAssistantServiceWrapper()
        {
            this$0 = NotificationAssistantService.this;
            super(NotificationAssistantService.this);
        }

        NotificationAssistantServiceWrapper(NotificationAssistantServiceWrapper notificationassistantservicewrapper)
        {
            this();
        }
    }


    static Handler _2D_get0(NotificationAssistantService notificationassistantservice)
    {
        return notificationassistantservice.mHandler;
    }

    public NotificationAssistantService()
    {
    }

    public final void adjustNotification(Adjustment adjustment)
    {
        if(!isBound())
            return;
        try
        {
            getNotificationInterface().applyAdjustmentFromAssistant(mWrapper, adjustment);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Adjustment adjustment)
        {
            Log.v("NotificationAssistants", "Unable to contact notification manager", adjustment);
        }
        throw adjustment.rethrowFromSystemServer();
    }

    public final void adjustNotifications(List list)
    {
        if(!isBound())
            return;
        try
        {
            getNotificationInterface().applyAdjustmentsFromAssistant(mWrapper, list);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            Log.v("NotificationAssistants", "Unable to contact notification manager", list);
        }
        throw list.rethrowFromSystemServer();
    }

    protected void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        mHandler = new MyHandler(getContext().getMainLooper());
    }

    public final IBinder onBind(Intent intent)
    {
        if(mWrapper == null)
            mWrapper = new NotificationAssistantServiceWrapper(null);
        return mWrapper;
    }

    public abstract Adjustment onNotificationEnqueued(StatusBarNotification statusbarnotification);

    public abstract void onNotificationSnoozedUntilContext(StatusBarNotification statusbarnotification, String s);

    public final void unsnoozeNotification(String s)
    {
        if(!isBound())
            return;
        getNotificationInterface().unsnoozeNotificationFromAssistant(mWrapper, s);
_L1:
        return;
        s;
        Log.v("NotificationAssistants", "Unable to contact notification manager", s);
          goto _L1
    }

    public static final String SERVICE_INTERFACE = "android.service.notification.NotificationAssistantService";
    private static final String TAG = "NotificationAssistants";
    private Handler mHandler;
}
