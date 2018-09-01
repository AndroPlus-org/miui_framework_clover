// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.app.INotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.util.Log;

// Referenced classes of package android.service.notification:
//            Condition

public abstract class ConditionProviderService extends Service
{
    private final class H extends Handler
    {

        public void handleMessage(Message message)
        {
            String s;
            s = null;
            if(!ConditionProviderService._2D_wrap0(ConditionProviderService.this))
                return;
            message.what;
            JVM INSTR tableswitch 1 4: default 48
        //                       1 49
        //                       2 48
        //                       3 97
        //                       4 117;
               goto _L1 _L2 _L1 _L3 _L4
_L1:
            return;
_L2:
            s = "onConnected";
            try
            {
                onConnected();
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                Log.w(ConditionProviderService._2D_get0(ConditionProviderService.this), (new StringBuilder()).append("Error running ").append(s).toString(), message);
            }
              goto _L1
_L3:
            s = "onSubscribe";
            onSubscribe((Uri)message.obj);
              goto _L1
_L4:
            s = "onUnsubscribe";
            onUnsubscribe((Uri)message.obj);
              goto _L1
        }

        private static final int ON_CONNECTED = 1;
        private static final int ON_SUBSCRIBE = 3;
        private static final int ON_UNSUBSCRIBE = 4;
        final ConditionProviderService this$0;

        private H()
        {
            this$0 = ConditionProviderService.this;
            super();
        }

        H(H h)
        {
            this();
        }
    }

    private final class Provider extends IConditionProvider.Stub
    {

        public void onConnected()
        {
            ConditionProviderService._2D_get1(ConditionProviderService.this).obtainMessage(1).sendToTarget();
        }

        public void onSubscribe(Uri uri)
        {
            ConditionProviderService._2D_get1(ConditionProviderService.this).obtainMessage(3, uri).sendToTarget();
        }

        public void onUnsubscribe(Uri uri)
        {
            ConditionProviderService._2D_get1(ConditionProviderService.this).obtainMessage(4, uri).sendToTarget();
        }

        final ConditionProviderService this$0;

        private Provider()
        {
            this$0 = ConditionProviderService.this;
            super();
        }

        Provider(Provider provider)
        {
            this();
        }
    }


    static String _2D_get0(ConditionProviderService conditionproviderservice)
    {
        return conditionproviderservice.TAG;
    }

    static H _2D_get1(ConditionProviderService conditionproviderservice)
    {
        return conditionproviderservice.mHandler;
    }

    static boolean _2D_wrap0(ConditionProviderService conditionproviderservice)
    {
        return conditionproviderservice.isBound();
    }

    public ConditionProviderService()
    {
    }

    private final INotificationManager getNotificationInterface()
    {
        if(mNoMan == null)
            mNoMan = android.app.INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        return mNoMan;
    }

    private boolean isBound()
    {
        if(mProvider == null)
        {
            Log.w(TAG, "Condition provider service not yet bound.");
            return false;
        } else
        {
            return true;
        }
    }

    public static final void requestRebind(ComponentName componentname)
    {
        INotificationManager inotificationmanager = android.app.INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
        try
        {
            inotificationmanager.requestBindProvider(componentname);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public final void notifyCondition(Condition condition)
    {
        if(condition == null)
        {
            return;
        } else
        {
            notifyConditions(new Condition[] {
                condition
            });
            return;
        }
    }

    public final transient void notifyConditions(Condition acondition[])
    {
        if(!isBound() || acondition == null)
            return;
        getNotificationInterface().notifyConditions(getPackageName(), mProvider, acondition);
_L1:
        return;
        acondition;
        Log.v(TAG, "Unable to contact notification manager", acondition);
          goto _L1
    }

    public IBinder onBind(Intent intent)
    {
        if(mProvider == null)
            mProvider = new Provider(null);
        return mProvider;
    }

    public abstract void onConnected();

    public void onRequestConditions(int i)
    {
    }

    public abstract void onSubscribe(Uri uri);

    public abstract void onUnsubscribe(Uri uri);

    public final void requestUnbind()
    {
        INotificationManager inotificationmanager = getNotificationInterface();
        try
        {
            inotificationmanager.requestUnbindProvider(mProvider);
            mProvider = null;
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public static final String EXTRA_RULE_ID = "android.service.notification.extra.RULE_ID";
    public static final String META_DATA_CONFIGURATION_ACTIVITY = "android.service.zen.automatic.configurationActivity";
    public static final String META_DATA_RULE_INSTANCE_LIMIT = "android.service.zen.automatic.ruleInstanceLimit";
    public static final String META_DATA_RULE_TYPE = "android.service.zen.automatic.ruleType";
    public static final String SERVICE_INTERFACE = "android.service.notification.ConditionProviderService";
    private final String TAG = (new StringBuilder()).append(android/service/notification/ConditionProviderService.getSimpleName()).append("[").append(getClass().getSimpleName()).append("]").toString();
    private final H mHandler = new H(null);
    private INotificationManager mNoMan;
    private Provider mProvider;
}
