// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.os.Handler;
import android.os.UserHandle;

class ReceiverRestrictedContext extends ContextWrapper
{

    ReceiverRestrictedContext(Context context)
    {
        super(context);
    }

    public boolean bindService(Intent intent, ServiceConnection serviceconnection, int i)
    {
        throw new ReceiverCallNotAllowedException("BroadcastReceiver components are not allowed to bind to services");
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter)
    {
        return registerReceiver(broadcastreceiver, intentfilter, null, null);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter, String s, Handler handler)
    {
        if(broadcastreceiver == null)
            return super.registerReceiver(null, intentfilter, s, handler);
        else
            throw new ReceiverCallNotAllowedException("BroadcastReceiver components are not allowed to register to receive intents");
    }

    public Intent registerReceiverAsUser(BroadcastReceiver broadcastreceiver, UserHandle userhandle, IntentFilter intentfilter, String s, Handler handler)
    {
        if(broadcastreceiver == null)
            return super.registerReceiverAsUser(null, userhandle, intentfilter, s, handler);
        else
            throw new ReceiverCallNotAllowedException("BroadcastReceiver components are not allowed to register to receive intents");
    }
}
